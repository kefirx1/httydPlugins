package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class NarCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {

            Player player = (Player) sender;

            String msg = String.join(" ", args);

            player.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[N]: " + ChatColor.AQUA + msg);
            for (Entity entity : player.getNearbyEntities(50, 50, 50)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[N]: " + ChatColor.AQUA + msg);
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
