package fr.nelhane.runnables;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class BroadcastRunnable extends BukkitRunnable {

    private static final BaseComponent[][] MESSAGES = {
            new ComponentBuilder("§c➡ N'hésite pas à passer sur notre boutique ! ").append("§b§l[Lien]").event(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://reviveyourcraft.fr/shop")).create(),
            new ComponentBuilder("§c➡ N'hésite pas à passer sur notre boutique ! ").append("§b§l[Lien]").event(new ClickEvent(ClickEvent.Action.OPEN_URL,"https://reviveyourcraft.fr/shop")).create(),
    };

    private Random r = new Random();

    @Override
    public void run() {
        BaseComponent[] now = MESSAGES[r.nextInt(MESSAGES.length)];
        Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(now));
    }
}
