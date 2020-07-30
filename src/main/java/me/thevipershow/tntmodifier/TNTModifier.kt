package me.thevipershow.tntmodifier

import me.thevipershow.tntmodifier.commands.TNTModifierCommand
import me.thevipershow.tntmodifier.configs.Values
import me.thevipershow.tntmodifier.listeners.InteractionListener
import org.bukkit.plugin.java.JavaPlugin

class TNTModifier : JavaPlugin() {

    override fun onEnable() {
        saveDefaultConfig()
        Values.plugin = this
        Values.updateAll()
        server.pluginManager.registerEvents(InteractionListener(), this)
        getCommand("tnt-modifier").executor = TNTModifierCommand()
    }
}