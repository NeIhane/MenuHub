package fr.nelhane.events.block;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(!p.isOp()){
            e.setCancelled(true);
            p.sendMessage("§7[§cRYCHub§7] §cVous n'avez pas la permission !");
        }
    }

}
