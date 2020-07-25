package me.thevipershow.tntmodifier.listeners

import me.thevipershow.tntmodifier.configs.Values
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.concurrent.ThreadLocalRandom

class InteractionListener : Listener {

    val random = ThreadLocalRandom.current()

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        val action = event.action
        if (action != Action.RIGHT_CLICK_BLOCK) return

        val clickedBlock = event.clickedBlock
        if (clickedBlock.type != Material.TNT) return

        if (Values.disableTNTOnRain!!) {
            if (clickedBlock.world.hasStorm() || clickedBlock.world.isThundering) return
        }

        clickedBlock.type = Material.AIR
        val adjustedSpawnLocation = clickedBlock.location.add(0.50, 0.50, 0.50)
        clickedBlock.world.spawnEntity(adjustedSpawnLocation, EntityType.PRIMED_TNT)
    }

    @EventHandler
    fun onPrime(event: ExplosionPrimeEvent) {
        val entity = event.entity
        val entityType = entity.type
        if (entityType != EntityType.PRIMED_TNT) return

        if (Values.disableTNTOnRain!!) {
            if (entity.world.hasStorm() || entity.world.isThundering) {
                event.isCancelled = true
                val location = entity.location
                val world = location.world

                world.playSound(location, Values.fuseFailSoundType, 10.0f, 1.0f)
                for (i in 1..50)
                    world.spawnParticle(Values.fuseFailEffectType, location.x, location.y, location.z, 1, 1.5, 1.5, 1.5, 0.5, null)
            }
        }
    }
}