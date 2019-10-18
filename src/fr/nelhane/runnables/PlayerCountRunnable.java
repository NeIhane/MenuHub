package fr.nelhane.runnables;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.nelhane.menuhub.MenuHub;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;


public class PlayerCountRunnable extends BukkitRunnable {

    private static final String SERVER_PREFIX = "mj_";

    private static List<String> servers = Arrays.asList("ctf", "hg", "skywars", "bp", "infected", "hideseek");

    @Override
    public void run() {
        for(String server : servers) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerCount");
            out.writeUTF(SERVER_PREFIX + server);

            Player p = Iterables.getFirst(Bukkit.getOnlinePlayers(), null);

            if (p != null) {
                p.sendPluginMessage(MenuHub.getPlugin(), "BungeeCord", out.toByteArray());
            }
        }
    }
}
