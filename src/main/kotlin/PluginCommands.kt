package top.jie65535.mirai.mailnotify

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.CompositeCommand

object PluginCommands : CompositeCommand(JMailNotify, "jmn",
    description = "邮件通知命令") {

    @SubCommand
    @Description("设置SMTP发件人邮箱")
    suspend fun CommandSender.setSender(email: String, smtpPassword: String) {
        if (email.isEmpty() || smtpPassword.isEmpty()) {
            sendMessage("参数不能为空")
        } else {
            PluginConfig.smtpEMail = email
            PluginConfig.smtpPassword = smtpPassword
            sendMessage("OK")
        }
    }

    @SubCommand
    @Description("设置SMTP服务器")
    suspend fun CommandSender.setSmtpServer(host: String, port: Int) {
        if (host.isEmpty() || port == 0) {
            sendMessage("参数不能为空")
        } else {
            PluginConfig.smtpHost = host
            PluginConfig.smtpPort = port
            sendMessage("OK")
        }
    }

    @SubCommand
    @Description("设置收件人邮箱地址")
    suspend fun CommandSender.setSendTo(email: String) {
        if (email.isEmpty()) {
            sendMessage("参数不能为空")
        } else {
            PluginConfig.sendToEMail = email
            sendMessage("OK")
        }
    }

    @SubCommand
    @Description("发送测试邮件")
    suspend fun CommandSender.test(
        title: String = "[Mirai] 测试邮件", message: String = "这是一封测试邮件，若您收到这封邮件，说明当前插件参数配置正确。") {
        if (JMailNotify.sendMail(title, message)) {
            sendMessage("邮件已发送")
        } else {
            sendMessage("邮件发送失败，具体错误信息请查看控制台日志")
        }
    }

    @SubCommand
    @Description("重载配置")
    suspend fun CommandSender.reload() {
        reload()
        sendMessage("OK")
    }

    @SubCommand
    @Description("设置登录通知")
    suspend fun CommandSender.setLogin(isEnabled: Boolean) {
        PluginConfig.loginNotify = isEnabled
        sendMessage("OK")
    }

    @SubCommand
    @Description("设置登出通知")
    suspend fun CommandSender.setLogout(isEnabled: Boolean) {
        PluginConfig.logoutNotify = isEnabled
        sendMessage("OK")
    }

    @SubCommand
    @Description("设置关闭通知")
    suspend fun CommandSender.setDisabled(isEnabled: Boolean) {
        PluginConfig.disableNotify = isEnabled
        sendMessage("OK")
    }
}