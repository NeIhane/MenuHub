package fr.nelhane.events.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickUp implements Listener {

    @EventHandler
    public void onPick(PlayerPickupItemEvent event){
        event.setCancelled(true);
    }
}
