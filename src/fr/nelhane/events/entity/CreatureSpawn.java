package fr.nelhane.events.entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn implements Listener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if(event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM){
            event.setCancelled(true);
        }
    }

}
