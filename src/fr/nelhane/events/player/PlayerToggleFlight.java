package fr.nelhane.events.player;

import fr.nelhane.menuhub.MenuHub;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class PlayerToggleFlight implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e){
        Player p = e.getPlayer();

        if(p.getGameMode() == GameMode.CREATIVE)
            return;

        e.setCancelled(true);
        p.setAllowFlight(false);
        p.setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.6D).setY(1.0D));
        p.playSound(e.getPlayer().getLocation(), Sound.ENDERDRAGON_WINGS, 1.0F, 1.0F);

        Bukkit.getScheduler().runTaskLater(MenuHub.getPlugin(), () -> {
            p.setAllowFlight(true);
        }, 5 * 20);
    }

}
