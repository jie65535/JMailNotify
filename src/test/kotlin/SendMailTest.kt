import net.mamoe.mirai.console.MiraiConsole
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.enable
import net.mamoe.mirai.console.plugin.PluginManager.INSTANCE.load
import net.mamoe.mirai.console.terminal.MiraiConsoleTerminalLoader
import top.jie65535.mirai.mailnotify.JMailNotify

@net.mamoe.mirai.console.util.ConsoleExperimentalApi
suspend fun main() {
    MiraiConsoleTerminalLoader.startAsDaemon()
    JMailNotify.load()
    JMailNotify.enable()
    MiraiConsole.job.join()
}