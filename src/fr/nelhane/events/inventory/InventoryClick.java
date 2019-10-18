package fr.nelhane.events.inventory;

import fr.hugoooo.api.cooldowns.Cooldown;
import fr.nelhane.inventories.HubInventoryProvider;
import fr.nelhane.inventories.ProfilInventoryProvider;
import fr.nelhane.menuhub.MenuHub;
import fr.nelhane.utils.Reference;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Inventory inv = event.getInventory();
        Player p = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        event.setCancelled(true);

        if (current != null && current.getType() == Material.COMPASS) {
            HubInventoryProvider.INVENTORY.open(p);
            return;
        } else if (current != null
                && current.getType() == Material.SKULL_ITEM
                && current.getItemMeta().hasDisplayName()
                && current.getItemMeta().getDisplayName().equalsIgnoreCase("§cProfil")){
            ProfilInventoryProvider.INVENTORY.open(p);
        } else if (current != null
                && current.getType() == Material.INK_SACK
                && current.getItemMeta().hasDisplayName()
                && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aAfficher les joueurs (invisible)")){
            if(Cooldown.hasCooldown(p, "showplayer")) {
                p.sendMessage("§cVous devez encore attendre " + Cooldown.getSeconds(p, "showplayer") + " secondes!");
            } else {
                MenuHub.clickShow(p);
                p.getInventory().setItem(8, Reference.HIDEPLAYER_ITEM);
                p.sendMessage("§aLes joueurs sont de nouveau visibles !");
                Cooldown.startCooldown(p, "hideplayer", 5);
            }
        } else if (current != null
                && current.getType() == Material.INK_SACK
                && current.getItemMeta().hasDisplayName()
                && current.getItemMeta().getDisplayName().equalsIgnoreCase("§aCacher les joueurs (visible)")){
            if(Cooldown.hasCooldown(p, "hideplayer")) {
                p.sendMessage("§cVous devez encore attendre " + Cooldown.getSeconds(p, "hideplayer") + " secondes!");
            } else {
                MenuHub.clickHide(p);
                p.getInventory().setItem(8, Reference.SHOWPLAYER_ITEM);
                p.sendMessage("§aLes joueurs sont désormais invisibles !");
                Cooldown.startCooldown(p, "showplayer", 5);
            }
        }
    }

}
