package fr.nelhane.utils;

import fr.hugoooo.api.item.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Reference {


    public static final ItemStack HIDEPLAYER_ITEM = ItemBuilder.builder()
            .item(Material.INK_SACK)
            .name("§aCacher les joueurs (visible)")
            .lore("§7Clic droit pour cacher les joueurs")
            .dyeColor(DyeColor.LIME)
            .itemStack();
    public static final ItemStack SHOWPLAYER_ITEM = ItemBuilder.builder()
            .item(Material.INK_SACK)
            .name("§aAfficher les joueurs (invisible)")
            .lore("§7Clic droit pour afficher les joueurs")
            .dyeColor(DyeColor.GRAY)
            .itemStack();



}
