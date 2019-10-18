package fr.nelhane.inventories;

import fr.hugoooo.api.inv.ClickableItem;
import fr.hugoooo.api.inv.SmartInventory;
import fr.hugoooo.api.inv.content.InventoryContents;
import fr.hugoooo.api.inv.content.InventoryProvider;
import fr.hugoooo.api.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;

import java.util.Random;

public class ProfilInventoryProvider implements InventoryProvider {

    public static final SmartInventory INVENTORY = SmartInventory.builder()
            .id("profil_inv")
            .provider(new ProfilInventoryProvider())
            .size(5, 9)
            .title("§8Menu RYC")
            .build();
    private final Random random = new Random();

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1,1, ClickableItem.of(ItemBuilder.builder().item(Material.MOB_SPAWNER).name("§c§lAnimaux").lore("","§7Affiche la liste de vos animaux","","§b> Ouvrir le menu animaux"), e -> {
            player.sendMessage("Ouvrir menu > Animaux");
        }));
        contents.set(1,3, ClickableItem.of(ItemBuilder.builder().item(Material.SADDLE).name("§b§lMontures").lore("","§7Affiche la liste de vos montures","","§b> Ouvrir le menu montures"), e -> {
            player.sendMessage("Ouvrir menu > Montures");
        }));
        contents.set(1,5, ClickableItem.of(ItemBuilder.builder().item(Material.CHAINMAIL_HELMET).name("§a§lChapeaux").lore("","§7Affiche la liste de vos chapeaux","","§b> Ouvrir le menu chapeaux"), e -> {
            player.sendMessage("Ouvrir menu > Chapeaux");
        }));
        contents.set(1,7, ClickableItem.of(ItemBuilder.builder().item(Material.FIREWORK).name("§6§lTraînées").lore("","§7Affiche la liste de vos traînées","","§b> Ouvrir le menu traînées"), e -> {
            player.sendMessage("Ouvrir menu > Traînées");
        }));
        contents.set(3,1, ClickableItem.of(ItemBuilder.builder().item(Material.NAME_TAG).name("§c§lChanger couleur du Rang").lore("","§7Changez la couleur de votre","§7rang dans le chat et le TAB","","§dMVP Uniquement", "", "§b> Ouvrir le menu de rang"), e -> {
            player.sendMessage("Ouvrir menu > Changer couleur du rang");
        }));
        contents.set(3,3, ClickableItem.of(ItemBuilder.builder().item(Material.ENCHANTED_BOOK).name("§e§lSuccès").lore("","§7Affiche la liste de vos succès","","§b> Ouvrir le menu succès"), e -> {
            player.sendMessage("Ouvrir menu > Succès");
        }));
        contents.set(3,5, ClickableItem.of(ItemBuilder.builder().item(Material.ENDER_CHEST).name("§9§lBoutique RYC").lore("","§7Raccourci vers notre boutique","","§b> Cliquez pour obtenir le lien"), e -> {
            player.sendMessage("§7| §aBoutique RYC §7| §c§lhttps://reviveyourcraft.fr/shop");
        }));
        contents.set(3,7, ClickableItem.of(ItemBuilder.builder().item(Material.SKULL_ITEM).skullType(SkullType.PLAYER).skull(player.getName()).name("§8§lRéseau Social").lore("","§7Affichez vos amis, votre guilde, etc…","","§b> Ouvrir le menu réseau social"), e -> {
            player.sendMessage("§Ouvrir menu > Social");
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
//        int state = contents.property("state", 0);
//        contents.setProperty("state", state + 1);
//
//        if(state % 20 != 0){
//            return;
//        }
//
//        byte data = (byte)random.nextInt(15);
//        contents.fillRow(1, ClickableItem.empty(ItemBuilder.builder().item(Material.STAINED_GLASS_PANE).name("").woolColor(DyeColor.getByWoolData(data))));
    }
}
