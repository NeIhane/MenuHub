package fr.nelhane.inventories;

import fr.hugoooo.api.inv.ClickableItem;
import fr.hugoooo.api.inv.SmartInventory;
import fr.hugoooo.api.inv.content.InventoryContents;
import fr.hugoooo.api.inv.content.InventoryProvider;
import fr.hugoooo.api.item.ItemBuilder;
import fr.nelhane.messages.PMListener;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;


import java.util.Random;


public class HubInventoryProvider implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("hub_mdj")
            .provider(new HubInventoryProvider())
            .size(6, 9)
            .title("§8Menu de jeux")
            .build();
    private final Random random = new Random();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(ItemBuilder.builder().item(Material.STAINED_GLASS_PANE).name("").woolColor(DyeColor.RED)));
        contents.set(2, 2,ClickableItem.of(ItemBuilder.builder().item(Material.BANNER).woolColor(DyeColor.RED).name("§aCapture The Flag").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_ctf")), e -> {
            player.sendMessage("TP MAP CTF");
        } ));
        contents.set(2, 4,ClickableItem.of(ItemBuilder.builder().item(Material.IRON_SWORD).name("§aHunger Games").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_hg")), e -> {
            player.sendMessage("TP MAP HG");
        } ));
        contents.set(2, 6,ClickableItem.of(ItemBuilder.builder().item(Material.ENDER_PEARL).name("§aSkyWars").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_skywars")), e -> {
            player.sendMessage("TP MAP SKYWARS");
        } ));
        contents.set(3, 2,ClickableItem.of(ItemBuilder.builder().item(Material.WORKBENCH).name("§aHide & Seek").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_hideseek")), e -> {
            player.sendMessage("TP MAP SKYWARS");
        } ));
        contents.set(3, 4,ClickableItem.of(ItemBuilder.builder().item(Material.SKULL_ITEM, 1, (byte)2).name("§aInfecté").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_infected")), e -> {
            player.sendMessage("TP MAP INFECTE");
        } ));
        contents.set(3, 6,ClickableItem.of(ItemBuilder.builder().item(Material.WOOL).woolColor(DyeColor.RED).name("§aBlockParty").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_bp")), e -> {
            player.sendMessage("TP MAP BLOCKPARTY");
        } ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 10 != 0){
            return;
        }

        byte databanner = (byte) (state / 10 % 2 * 3 + 1);
        contents.set(2, 2,ClickableItem.of(ItemBuilder.builder().item(Material.BANNER).woolColor(DyeColor.getByWoolData(databanner)).name("§aCapture The Flag").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_ctf")), e -> {
            player.sendMessage("TP MAP CTF");
        } ));
        byte datawool = (byte)random.nextInt(15);
        contents.set(3, 6,ClickableItem.of(ItemBuilder.builder().item(Material.WOOL).woolColor(DyeColor.getByWoolData(datawool)).name("§aBlockParty").lore("§7Cliquez pour rejoindre !", "Joueurs en ligne : " + PMListener.getPlayerCount("mj_bp")), e -> {
            player.sendMessage("TP MAP BLOCKPARTY");
        } ));
        byte data = (byte)random.nextInt(15);
        contents.fillBorders(ClickableItem.empty(ItemBuilder.builder().item(Material.STAINED_GLASS_PANE).name("").woolColor(DyeColor.getByWoolData(data))));

//        if(state % 200 != 0) {
//
//        }

    }
}
