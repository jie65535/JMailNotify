package top.jie65535.mirai

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

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
    }
}