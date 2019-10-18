package fr.nelhane.events.player;

import fr.hugoooo.api.cooldowns.Cooldown;
import fr.hugoooo.api.item.ItemBuilder;
import fr.nelhane.inventories.HubInventoryProvider;
import fr.nelhane.inventories.ProfilInventoryProvider;
import fr.nelhane.menuhub.MenuHub;
import fr.nelhane.utils.Reference;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
//import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack it = event.getItem();

        if (it == null) return;

        if(it.getType() != Material.SIGN || !p.hasPermission("ryc.admin")){
            event.setCancelled(true);
        }

        if (it.getType() == Material.COMPASS) {
            HubInventoryProvider.INVENTORY.open(p);
        }

        if (it.getType() == Material.SKULL_ITEM){
            ProfilInventoryProvider.INVENTORY.open(p);
        }
    }

    @EventHandler
    public void onHide(PlayerInteractEvent event){
        Player p = event.getPlayer();
        ItemStack it = event.getItem();

        if (it == null) return;

        if (it.getType() == Material.INK_SACK
                && it.hasItemMeta()
                && it.getItemMeta().hasDisplayName()
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§aCacher les joueurs (visible)")){
            if(Cooldown.hasCooldown(p, "hideplayer")) {
                p.sendMessage("§cVous devez encore attendre " + Cooldown.getSeconds(p, "hideplayer") + " secondes!");
            } else {
                MenuHub.clickHide(p);
                p.getInventory().setItem(8, Reference.SHOWPLAYER_ITEM);
                p.sendMessage("§aLes joueurs sont désormais invisibles !");
                Cooldown.startCooldown(p, "showplayer", 5);
            }
        } else if (it.getType() == Material.INK_SACK
                && it.hasItemMeta()
                && it.getItemMeta().hasDisplayName()
                && it.getItemMeta().getDisplayName().equalsIgnoreCase("§aAfficher les joueurs (invisible)")) {
            if(Cooldown.hasCooldown(p, "showplayer")) {
                p.sendMessage("§cVous devez encore attendre " + Cooldown.getSeconds(p, "showplayer") + " secondes!");
            } else {
                MenuHub.clickShow(p);
                p.getInventory().setItem(8, Reference.HIDEPLAYER_ITEM);
                p.sendMessage("§aLes joueurs sont de nouveau visibles !");
                Cooldown.startCooldown(p, "hideplayer", 5);
            }
        }
    }

}
