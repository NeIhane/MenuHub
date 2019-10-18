package fr.nelhane.commands;

import fr.hugoooo.api.commands.Command;
import fr.hugoooo.api.commands.CommandArgs;
import fr.hugoooo.api.commands.CommandPermission;
import fr.hugoooo.api.commands.CommandType;
import fr.hugoooo.api.config.Configuration;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends Command {
    public SetSpawnCommand() {
        super("setspawn", CommandType.PLAYER, CommandPermission.op());
    }

    @Override
    public void performCommand(Player p, CommandArgs args) {
        Configuration.manager().getConfiguration("config").setLocation("spawn", p.getLocation());
        Configuration.manager().getConfiguration("config").saveConfig();
        p.sendMessage("§7[§cRYCHub§7] §aLe point de spawn a bien été mis à jour !");
    }
}
