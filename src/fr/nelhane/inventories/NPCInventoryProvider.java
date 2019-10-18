package fr.nelhane.inventories;

import fr.hugoooo.api.inv.ClickableItem;
import fr.hugoooo.api.inv.SmartInventory;
import fr.hugoooo.api.inv.content.InventoryContents;
import fr.hugoooo.api.inv.content.InventoryProvider;
import fr.hugoooo.api.inv.content.Pagination;
import fr.hugoooo.api.inv.content.SlotPos;
import fr.hugoooo.api.item.ItemBuilder;
import fr.nelhane.menuhub.MenuHub;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NPCInventoryProvider implements InventoryProvider {

    public static final SmartInventory INVENTORY_BP = SmartInventory.builder()
            .id("bp_inv")
            .provider(new NPCInventoryProvider("bp", true))
            .size(3,9)
            .title("§8RYC BlockParty")
            .build();
    public static final SmartInventory INVENTORY_CTF = SmartInventory.builder()
            .id("ctf_inv")
            .provider(new NPCInventoryProvider("ctf", false))
            .size(3, 9)
            .title("§8RYC Capture The Flag")
            .build();
    public static final SmartInventory INVENTORY_HG = SmartInventory.builder()
            .id("hg_inv")
            .provider(new NPCInventoryProvider("hg", false))
            .size(3, 9)
            .title("§8RYC Hunger Games")
            .build();
    public static final SmartInventory INVENTORY_HIDESEEK = SmartInventory.builder()
            .id("hideseek_inv")
            .provider(new NPCInventoryProvider("hideseek", true))
            .size(3, 9)
            .title("§8RYC Hide & Seek")
            .build();
    public static final SmartInventory INVENTORY_SKYWARS = SmartInventory.builder()
            .id("skywars_inv")
            .provider(new NPCInventoryProvider("skywars", false))
            .size(3, 9)
            .title("§8RYC SkyWars")
            .build();
    public static final SmartInventory INVENTORY_INFECTED = SmartInventory.builder()
            .id("infected_inv")
            .provider(new NPCInventoryProvider("infected", false))
            .size(3, 9)
            .title("§8RYC SkyWars")
            .build();



    private String serverId;
    private boolean forceVanilla;

    public NPCInventoryProvider(String serverId, boolean forceVanilla) {
        this.serverId = serverId;
        this.forceVanilla = forceVanilla;
    }

    @Override
    public void init(Player p, InventoryContents inventoryContents) {
        Pagination pagination = inventoryContents.pagination();

        if(pagination.getPage() == 0) {
            //NO UPDATE
            inventoryContents.set(1, 3, ClickableItem.of(ItemBuilder.builder().item(Material.SIGN).name("§aRejoindre une partie!"), e -> {
                //SEND TO SERVER
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                        DataOutputStream out = new DataOutputStream(b);
                        try {
                            out.writeUTF("Connect");
                            out.writeUTF("mj_" + serverId);
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }

                        p.sendPluginMessage(MenuHub.getPlugin(),"BungeeCord", b.toByteArray());
            }));

            inventoryContents.set(1, 5, ClickableItem.of(ItemBuilder.builder().item(Material.WOOL).woolColor(DyeColor.LIME).name("§aVoir la liste des serveurs!"), e -> {
                inventoryContents.inventory().open(p, 1);
            }));

            if(forceVanilla) {
                inventoryContents.set(2, 4, ClickableItem.empty(ItemBuilder.builder().item(Material.MILK_BUCKET).name("§7Mode Vanilla imposé")));
            } else {
                inventoryContents.set(2, 4, ClickableItem.of(ItemBuilder.builder().item(Material.DIODE).name("§7Choix du mode de jeu").lore("§cMode de jeu actuel : DEFAULT"), e -> {
                    inventoryContents.inventory().open(p, 2);
                }));
            }
        } else if (pagination.getPage() == 1) {
            //UPDATES
            inventoryContents.set(0, 0, ClickableItem.of(ItemBuilder.builder().item(Material.WOOL).woolColor(DyeColor.LIME), e -> {

            }));

        } else if (pagination.getPage() == 2) {
            //NO UPDATES

        }
    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {

    }
}
