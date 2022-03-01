package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class OocCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            Player player = (Player) sender;
            String playerName = player.getName();
            String msg = String.join(" ", args);

            player.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
            for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
