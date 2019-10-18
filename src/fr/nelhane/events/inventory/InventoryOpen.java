package fr.nelhane.events.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryOpen implements Listener {

    @EventHandler
    public static void onOpen(InventoryOpenEvent e){
        if(!(e.getPlayer() instanceof Player)) return;

        Player p = (Player)e.getPlayer();
        if(e.getInventory().getType() != InventoryType.PLAYER && !p.isOp()){
            p.sendMessage("§7[§cRYCHub§7] Vous ne pouvez pas faire cela !");
            e.setCancelled(true);
        }
    }

}
