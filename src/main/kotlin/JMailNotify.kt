package top.jie65535.mirai.mailnotify

import jakarta.mail.Authenticator
import jakarta.mail.PasswordAuthentication
import jakarta.mail.Session
import jakarta.mail.internet.InternetAddress
import jakarta.mail.internet.MimeMessage
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.events.BotOfflineEvent
import net.mamoe.mirai.event.events.BotOnlineEvent
import net.mamoe.mirai.event.globalEventChannel
import net.mamoe.mirai.utils.info
import java.util.*

object JMailNotify : KotlinPlugin(
    JvmPluginDescription(
        id = "top.jie65535.mail-notify",
        name = "J Mail Notify",
        version = "0.1.0",
    ) {
        author("jie65535")
        info("""订阅事件进行邮件通知""")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
        PluginCommands.register()
        PluginConfig.reload()

        // 订阅上线事件
        globalEventChannel().subscribeAlways<BotOnlineEvent> {
            if (PluginConfig.loginNotify) {
                sendMail("[Mirai] Bot上线通知", "您的 Mirai bot [${bot.id}(${bot.nick})] 登录成功！")
            }
        }
        // 订阅下线事件
        globalEventChannel().subscribeAlways<BotOfflineEvent> {
            if (PluginConfig.logoutNotify) {
                sendMail("[Mirai] Bot离线通知", "您的 Mirai bot [${bot.id}(${bot.nick})] 下线了！\n" +
                        "触发事件：$this")
            }
        }
    }

    override fun onDisable() {
        if (!PluginConfig.disableNotify)
            return
        sendMail("[Mirai] 控制台关闭通知", "J Mail Notify 插件已关闭")
    }

    /**
     * 发送邮件
     * @param title 邮件标题
     * @param message 邮件内容
     * @return 是否发送完成
     */
    fun sendMail(title: String, message: String): Boolean {
        logger.info("正在尝试向 ${PluginConfig.sendToEMail} 发送邮件 \"$title | $message\"")
        if (PluginConfig.smtpEMail.isEmpty()
            || PluginConfig.smtpPassword.isEmpty()
            || PluginConfig.smtpHost.isEmpty()
            || PluginConfig.sendToEMail.isEmpty()) {
            logger.warning("邮箱参数未设置，无法发送邮件。请先设置发件人与收件人的邮箱。QQ邮箱帮助： https://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256")
            return false
        }

        try {
            val props = Properties()
            props["mail.smtp.host"] = PluginConfig.smtpHost
            props["mail.smtp.port"] = PluginConfig.smtpPort.toString()
            props["mail.smtp.auth"] = "true"
            props["mail.smtp.ssl.enable"] = if (PluginConfig.useSSL) "true" else "false"
            val session = Session.getDefaultInstance(props, object : Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(PluginConfig.smtpEMail, PluginConfig.smtpPassword)
                }
            })
//            session.debug = true

            val mimeMessage = MimeMessage(session)
            mimeMessage.setFrom(InternetAddress(PluginConfig.smtpEMail))
            mimeMessage.setRecipients(
                jakarta.mail.Message.RecipientType.TO, InternetAddress.parse(PluginConfig.sendToEMail, false))
            mimeMessage.setText(message)
            mimeMessage.subject = title
            mimeMessage.sentDate = Date()

            session.getTransport("smtp").use {
                it.connect()
                it.sendMessage(mimeMessage, mimeMessage.allRecipients)
            }
            logger.info("邮件发送成功 \"$title\"")
            return true
        } catch (e: Throwable) {
            logger.error("发送邮件时发生异常", e)
            return false
        }
    }
}