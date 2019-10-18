package fr.nelhane.events.player;

import fr.hugoooo.api.config.Configuration;
import fr.hugoooo.api.item.ItemBuilder;
import fr.hugoooo.api.title.TitleAPI;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;


public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.getInventory().clear();
        p.setHealth(20);
        p.setFoodLevel(2000);
        p.setGameMode(GameMode.ADVENTURE);
        event.setJoinMessage("§e" + p.getName() + "§a a rejoint le Hub !");

        p.getInventory().setItem(0, ItemBuilder.builder()
                .item(Material.COMPASS)
                .name("§aMenu de jeux §7(Clic droit)")
                .lore("Clic droit pour ouvrir le menu")
                .enchant(Enchantment.DURABILITY, 1)
                .flag(ItemFlag.HIDE_ENCHANTS)
                .itemStack());

        p.getInventory().setItem(8, ItemBuilder.builder()
                .item(Material.INK_SACK)
                .name("§aCacher les joueurs (visible)")
                .lore("§7Clic droit pour cacher les joueurs")
                .dyeColor(DyeColor.LIME)
                .itemStack());

        p.getInventory().setItem(4, ItemBuilder.builder()
                .item(Material.SKULL_ITEM)
                .skullType(SkullType.PLAYER)
                .skull(p.getName())
                .name("§aProfil")
                .lore("§7Clic droit pour afficher votre profil")
                .itemStack());

        p.updateInventory();

        TitleAPI.sendTabTitle(p, "§bVous jouez sur §e§lReviveYourCraft", "§aVotez sur : §c§lReviveYourCraft.fr/vote");

        p.teleport(Configuration.manager().getConfiguration("config").getLocation("spawn"));
        TitleAPI.sendTitle(p, 60, 60, 20, "§cBienvenue sur §a§lReviveYourCraft",null);

        for(Player pls : Bukkit.getOnlinePlayers()) {
            ItemStack is = pls.getInventory().getItem(8);
            if (is != null
                    && is.getType() == Material.INK_SACK
                    && is.getItemMeta().hasDisplayName()
                    && is.getItemMeta().getDisplayName().equalsIgnoreCase("§aAfficher les joueurs (invisible)")) {
                pls.hidePlayer(p);
            }
        }

        if(p.hasPermission("ryc.dj")) {
            p.setAllowFlight(true);
        }
    }

}
