package top.jie65535.mirai.mailnotify

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

/**
 * 插件配置
 */
object PluginConfig : AutoSavePluginConfig("config") {

    /**
     * SMTP地址
     */
    @ValueDescription("SMTP地址（ QQ邮箱是 smtp.qq.com ）")
    var smtpHost: String by value("smtp.qq.com")

    /**
     * SMTP端口
     */
    @ValueDescription("SMTP端口号（ QQ邮箱默认 465 ）")
    var smtpPort: Int by value(465)

    /**
     * 是否使用SSL
     */
    @ValueDescription("是否使用SSL（ QQ邮箱默认 是 ）")
    val useSSL: Boolean by value(true)

    /**
     * SMTP邮箱（发件人）
     */
    @ValueDescription("SMTP邮箱（发件人）")
    var smtpEMail: String by value()

    /**
     * SMTP授权码
     */
    @ValueDescription("SMTP授权码")
    var smtpPassword: String by value()

    /**
     * 收件人邮箱
     */
    @ValueDescription("收件人邮箱")
    var sendToEMail: String by value()

    /**
     * 机器人登录时通知
     */
    @ValueDescription("机器人登录时通知")
    var loginNotify: Boolean by value(true)

    /**
     * 机器人登出时通知
     */
    @ValueDescription("机器人登出时通知")
    var logoutNotify: Boolean by value(true)

    /**
     * 插件关闭时通知
     */
    @ValueDescription("插件关闭时通知")
    var disableNotify: Boolean by value(true)
}