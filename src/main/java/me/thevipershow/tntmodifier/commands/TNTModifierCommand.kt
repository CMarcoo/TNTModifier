package me.thevipershow.tntmodifier.commands

import me.thevipershow.tntmodifier.configs.Values
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class TNTModifierCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {
        if (args != null) {
            if (args[0].equals("reload", true) && args.isEmpty()) {
                Values.updateAll()
                sender?.sendMessage("§8[§cTNTModifire§8]§7: Config.yml values updated correctly.")
                return true
            }
        }
        return false
    }
}