package fr.nelhane.messages;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.util.HashMap;

public class PMListener implements PluginMessageListener {

    private static HashMap<String, Integer> playerCounts = new HashMap<>();

    @Override
    public void onPluginMessageReceived(String channel, Player p, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("PlayerCount")) {
            String server = in.readUTF(); // Name of server, as given in the arguments
            int playerCount = in.readInt();

            playerCounts.put(server, playerCount);
        }
    }

    public static int getPlayerCount(String server) {
        return playerCounts.getOrDefault(server, 0);
    }

}
