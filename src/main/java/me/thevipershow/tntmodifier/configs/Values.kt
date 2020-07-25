package me.thevipershow.tntmodifier.configs

import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.plugin.Plugin

object Values {

    var plugin: Plugin? = null

    var disableTNTOnRain: Boolean? = null

    var fuseFailSound: String? = null
    var fuseFailEffect: String? = null

    var fuseFailSoundType: Sound? = null
    var fuseFailEffectType: Particle? = null

    fun updateAll() {
        plugin?.reloadConfig()
        disableTNTOnRain = plugin?.config?.get("disable-tnt-rain") as Boolean?
        fuseFailSound = plugin?.config?.get("fuse-fail-sound") as String?
        fuseFailEffect = plugin?.config?.get("fuse-fail-effect") as String?

        try {
            fuseFailSoundType = Sound.valueOf(fuseFailSound!!)
            fuseFailEffectType = Particle.valueOf(fuseFailEffect!!)
        } catch (e : IllegalArgumentException) {
            plugin?.logger?.warning("You have provided an invalid effect in the config.yml")
            e.printStackTrace()
        }
    }

}