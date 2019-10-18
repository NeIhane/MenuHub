package fr.nelhane.menuhub;

import fr.hugoooo.api.commands.Command;
import fr.hugoooo.api.config.Configuration;
import fr.hugoooo.api.npc.NPCManager;
import fr.nelhane.commands.SetSpawnCommand;
import fr.nelhane.events.block.BlockBreak;
import fr.nelhane.events.block.BlockExplode;
import fr.nelhane.events.block.BlockPlace;
import fr.nelhane.events.entity.CreatureSpawn;
import fr.nelhane.events.entity.EntityDamage;
import fr.nelhane.events.entity.FoodLevelChange;
import fr.nelhane.events.inventory.InventoryClick;
import fr.nelhane.events.inventory.InventoryOpen;
import fr.nelhane.events.player.*;
import fr.nelhane.events.world.WeatherChange;
import fr.nelhane.inventories.*;
import fr.nelhane.messages.PMListener;
import fr.nelhane.runnables.BroadcastRunnable;
import fr.nelhane.runnables.PlayerCountRunnable;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;


public class MenuHub extends JavaPlugin {

    @Getter
    private static MenuHub plugin;

    @Override
    public void onEnable() {
        plugin = this;

        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        Configuration.manager().setupConfig("config", new File(getDataFolder(),"config.yml"));

        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),196.5, 78, -454.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bBlockParty","§c" + PMListener.getPlayerCount("mj_bp") + " §aJoueurs"),
                NPCInventoryProvider.INVENTORY_BP::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_bp") + " §aJoueurs"); });
        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),192.5, 78, -454.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bCapture The Flag","§c" + PMListener.getPlayerCount("mj_ctf") + " §aJoueurs"),
                "eyJ0aW1lc3RhbXAiOjE1NjYxMzQwMzk1NzQsInByb2ZpbGVJZCI6IjFjYjdjODZiYmI4NTRjYzNiZWZmMTMxNmE3NjI5ZjY5IiwicHJvZmlsZU5hbWUiOiJTZW5rdTgzIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzk4ZWM3NDdlODQzMTM1OWUxYjEwYTNjZThmODc0OTE0MDljZGE5OTUxODkxZmZiMTI0NWQ3N2Y5NTg1YzZkIn19fQ==",
                "dCTioio4nvGm7OPwyLzam59fpH2R/JVilqmCrAHhPcHQHckyWR0yQ2QewN/bPI12TSTUlKjSQEQ3GfwTUuy0LhG2EE1n2+35Kj1fT8wEvD2kg5fX/ph5owCGEMXxnKZyWxhPBzywkOkPeucx87UkWG2+EFaY+lh5I9Q3rYrxxItMwJRPHHaKMJBTr1zpGTVnrWy2JGJuErUeXez5TTalxAVADMC/rXtOL5NK+DuZOF3nMCrCLEVQKKLlQnN5X3hDnOOib5+QpjlCJnXHPQOL9gEU2U1u/z5HRsUonBAlwN4WEhITHUPQL04fd2RJYNN6GMNFitsWvaa0KyHnjoCNfiCWFf7nnTTTDzRsPH0SBznCPy/tNv6LiRT4lVM6HrKfhWv55mKUTbw7UrZjx2WAhkgRA5GCs2GRvxJmiIlTvDGqF4eZ92oiQTC5Lp+5CoPhCofMcgKGnbRnnE2s1eUu1Fey3PS8INHP9ySlXwf7uCIEqD9ZED4A/YSDOESkb3sqBnUITLrGKw8w22tC0yMWmk3GW5PjFN6aSrb2K5Mo6auSDIkzVIgmfDibnHpbZ8JKPYFKvRnqoHXjO0/XlqVMM2dKfTb8IVH5PRref5gJGM8Nc3lLjC6cT/YHzD6PVZfjLzdQaq6/AyGITAU+/GGEwpOQzJBPxi8j0CjHBlUc1qo=",
                NPCInventoryProvider.INVENTORY_CTF::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_ctf") + " §aJoueurs"); });
        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),188.5, 78, -454.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bHunger Games","§c" + PMListener.getPlayerCount("mj_hg") + " §aJoueurs"),
                "eyJ0aW1lc3RhbXAiOjE1NjYxMzQ2NzMxMzIsInByb2ZpbGVJZCI6ImQwNDMzODA4ZmZhNDRkMWFiZDNmMjU2OTg4YzU5N2U5IiwicHJvZmlsZU5hbWUiOiJUZXVkeXkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzdmYWEyNzkxYWQ5YTlkZjZiODg4Mzk4NjY3MDBmM2Q1NGM1NjczMDQ2NDAyODlmNTQwYmQxZjhkZWRmZThjZWIifX19",
                "bN+Wc0tj/3RuP8ujqyELZ0rz1h4L3lfocNxmtn3O/SVX+w2LM2AjDiyR9b3y9lQDUzRWBqbL2RCSll4vZzur4eKF+ll+FGn7GqHs0lwn9Zy3TRys0QRCuarRLlmnkcs+QVzxxm+9+ysrBhPQiW3Jk2AcvYv5R9MOgCAmuoXAGhTCr8YCZpJB5AYtDuiM5Jz1SRauG7nAU/DaDKWyS0G/u2pnPsCPa+lAdAcyfWe7fK9yymaVHy3TNCuYjtCemeQ5cWhJSLJFrfJfomCFD9ckp8NkJQvIx607q6dIUH1UZp9AqPBc+gGFOra/Bh/5Ye8XnTnsJ8kRQt8jETw0HwIlgOb8Uq4odt+VZ4pGT7OF7unOrIFKl28fXGxyLumoi35PmDd6fllM4BvKuLPbKuTXRwqBmDzhoGc4S3dzZVvgr+jQY55L8EVRvGghyF9NOiVDe60xnja4Pguj9ybYD1Nb84fpETumDePr8ginCdZfmVR9n1ix2V3bJmn2gDGMbo616hYaGw2fB8LXIukYPz1ZkVKZbxRoKI6nB4svQ8pctf2p2/g0nRIb5e5ZMqPWa7Z9bkn2yrkTl7AN+Dnl54sLBdiGmS2TsWRuM4xJNWF0B70GlgILwKt1zKjCnQtf9nac87Eia1eurzZ8lM0ZZY6tp/oT8z0FG4a+8X3A++l9JXY=",
                NPCInventoryProvider.INVENTORY_HG::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_hg") + " §aJoueurs"); });
        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),188.5, 78, -450.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bSkyWars","§c" + PMListener.getPlayerCount("mj_skywars") + " §aJoueurs"),
                "eyJ0aW1lc3RhbXAiOjE1NjYxMzQ0Mjg5NjQsInByb2ZpbGVJZCI6IjNmMTM3N2FkZjIyMDRjMGE5NjcxZmFlM2E4YmFmMDRjIiwicHJvZmlsZU5hbWUiOiJSYWNobWFuaXNreSIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGI5NWYzNjI1MmZiNzA0NGQzNDRhYTU1OWQyZTJhYTQzZGI1NzcwMDA1YzllMjQ2ZjAwMmE3YTVhYWM0ZmYxIn19fQ==",
                "b7fFuLN77zyPT3ZZ9JH7KvamohEtX+GhIP/joAxewFdEVgV/Xv4pcPefgdeaHgWsA4wl9iHwUOxfTuSfU8eH/Ca29mn92VSqfEH3Ax/5AJB9iOTmHIB0sxfyKXJF/hvNnS4scn0ql/ayhJ6MDwahten5hMy9D65148yBkVk5JEf+Myeoov2cwd6G5nENLEdB989i7sJwRMQa8F71KpFq/+Pn0CzgcKHQfpXAmZNaCzv5yqN8bKIi6VWXQVoZ9LWeg+HF95b5nFIL4+SFHXjd9IFrWhDM/fBJPHHNXzf20cU8/AbcGDcB4YjdV9KyEX69IbBcg2uzUWg88k8//UVC0bZQcUe0vLIGPb8ZMXPQWhZf2sSdcHn8KloKv0hFue7c97YyV76X9a4k+3uC9k5g88EZ6YupLgUMFM2oCzLi9N9BYqU/aahFVsHdGkSOrhXPn08pePIwvpaWGU+V9m5hvrWlVrYL57BdFQCPwn9QHMOL9LrFpQxvfJ1PVDtMe3iqFnalq51BRaBw+Vo6vEQPX0/DZ9/xwKFNqNzwwcFndZoDBSDsAI6LdRmvTVro0YqYhk/Sh4dfcNRoS4XHh76QUzNFcgeFQdyMPofhQeG7ktZlG6lbfpeVAfpELuCyzzRXWd+isUIxczNKt4ewkCpn3fJdtvIXmYjwSZgdPrfBVws=",
                NPCInventoryProvider.INVENTORY_SKYWARS::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_skywars") + " §aJoueurs"); });
        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),192.5, 78, -450.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bInfecté","§c" + PMListener.getPlayerCount("mj_infected") + " §aJoueurs"),
                "eyJ0aW1lc3RhbXAiOjE1NjYxMzQ3NjQyNDgsInByb2ZpbGVJZCI6IjJiMjUyNzY1OTU2ZDRmMmI4YTBjZmRiYmNjODNiMjZmIiwicHJvZmlsZU5hbWUiOiJhdXRvbWF0ZTU5Z28iLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzhhNmNkNjBkYWE3ZjBjZmI3NzQ0OGI5NmQ4MmYzMGMzZjlkNzIwZDg0NjZlNmU0MDQ5NThlN2NkYTQ3NmE4ZjUifX19",
                "HxDuKkK3x3QcNxM17ek/RzFhvDasWd8ztVidWo8IKCer09HbP9kWHlvsRw7rkprketFger9Vx8d0OBXEM9fcMPHSqn/XN7fPZ9fKj3366ypPQHwebSY46GbLYMQOQavB2JZ6o1gqgVUdaIgKTfE56+e/3feLMZO5V0HVuv6YvKhpmWkVlevwJtItLDXlAtqXbg6gstQprJfSPcPGIzUhPEu6ede014C3z4oZppNP6896lcr0FCRfo9loN31eKLvgupIwYFpm+6TEHTlLDvIhTSLu2J6zdVGp125VVcLKdfUNSSyR4VLiqTIuELOnJKEy+3VGS13euGWWxPAF/tWxbIeuSauoyHZ0zUCJQNcVxu9ZQVE0BEFKF6A8ABqT3ssZ33I2j5nwQofJEom+2Bk8Rb1tmWCFi+VQZhImhDWlP+5VwApP46oIUD8LS73B7qX7W3BPZf+YjIiZwYlAs+A5GA362yFNbP8ffSc+ncB4dfh8aAo335WAMmnGh1fNRJOB08fhgQpF0c3f/T9MgnUzmGmwZC/hcEqOkMqlBr4HP6rUp2ZE03zZBGfwwUBauLEbmMJsVO9kdLu3dpcLH4DA+Z/pF+JIl9GheBgTbEIPvp+riZU226YIdKKzBOq3h5X7Xc+dNEXMrajMYSl0FybsNdm3sCpRKOOMRObC2ca27Gw=",
                NPCInventoryProvider.INVENTORY_INFECTED::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_infected") + " §aJoueurs"); });
        NPCManager.manager().createNPC(new Location(Bukkit.getWorld("world"),196.5, 78, -450.5, 90f, 0f),
                Arrays.asList("§e§lCLIQUE POUR JOUER", "§bHide & Seek","§c" + PMListener.getPlayerCount("mj_hideseek") + " §aJoueurs"),
                "eyJ0aW1lc3RhbXAiOjE1NjYxMzU4NjA1NzQsInByb2ZpbGVJZCI6ImE2OWY0MmJiODI0MTQyMzE5YzlmOTJjZmYwYjIyOTEyIiwicHJvZmlsZU5hbWUiOiJFeHkxIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9mNGRlZjE2N2JiYTI2MjQwMzAxZTExNzQ1MTU2OTlkM2UwOTRkM2I1N2QyZjE2MTk5NmQwZmY5MDJkNWM0Y2MxIn19fQ==",
                "vwxiXCXRkS/qerUm9si4h/dHomyUV5Um1RZFJaHAdf9Cmwp83ySI3yu1x33bCfANOcsuubpb/bpDcmK6db5r6KeRofF+edjsfjzc7AzuImTA6YkGmAfG3T9Zx2E1Vkkf5bZtrk5sS5k4sc3Es1aVq3xlqlhUu1PAiHxKr6Z3lZh2EsjpJWJuvEGdG5dNFrHTvWix37r+mBWNaz8AYQW6M7/j+Mlx3/hd59p6tjteXpsD5FiFIxs+CsTRjg0FytmhrFn2eBDMAdHXwFwORtNssqI6XyNBLbR80gKlDis7svO37T0nkU2N7zP7VORcDNIXIDZuXr3JTx2bprKdjoHXguPdmOaWuBO1e0YvxgJb2nM7LCpOf1B06TiBbSizFNrpNOwe+kwVN6Biv7MEjr48+5dwERKX+E6llpT2ZKq+jJhktpicHf8YvfkO/coR117GEjZ7sD0INlHL48KogM4xLSFJTECyCtJwKkjiDovj08iy8SZVKqWDBc1s1LHtwVmCPEFKgP8jWM8NueMry+XDnZglQep37xH3OkSGtjKq8M3Gf/q2Uqo51G1QFFKTD+QNOKMhrU+xIF02YqYUOnLYJobGgydeFB8wD/k9s0+cEX6MWT/TtD08kWCPJGycePL6WdNV9ncfdIoDjd4EHYQMXGTwhIVemOO4QXTMf3NWqCs=",
                NPCInventoryProvider.INVENTORY_HIDESEEK::open,
                npc -> { npc.getHologram().setLine(2, "§c" + PMListener.getPlayerCount("mj_hideseek") + " §aJoueurs"); });

        new BroadcastRunnable().runTaskTimer(this, 2000, 18000);
        new PlayerCountRunnable().runTaskTimer(this, 200, 200);

        registerEvents();
        registerPluginMessage();
        registerCommands();
    }

    private void registerPluginMessage(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PMListener());
    }

    private void registerCommands(){
        Command.manager().registerCommand(new SetSpawnCommand());
    }

    private void registerEvents() {
        PluginManager pm  = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerInteract(), this);
        pm.registerEvents(new PlayerDrop(), this);
        pm.registerEvents(new InventoryClick(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
        pm.registerEvents(new EntityDamage(), this);
        pm.registerEvents(new FoodLevelChange(), this);
        pm.registerEvents(new InventoryOpen(), this);
        pm.registerEvents(new PlayerPickUp(), this);
        pm.registerEvents(new PlayerToggleFlight(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new WeatherChange(), this);
        pm.registerEvents(new BlockExplode(), this);
        pm.registerEvents(new CreatureSpawn(), this);
    }

    public static void clickHide(Player p){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player != p){
                p.hidePlayer(player);
            }
        }
    }

    public static void clickShow(Player p){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(player != p){
                p.showPlayer(player);
            }
        }
    }

}
