package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalTime;

public class GlobalnarCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>0){

            LocalTime time = LocalTime.now();
            LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


            Player player = (Player) sender;

            String msg = String.join(" ", args);

            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[G]: " + ChatColor.YELLOW + msg);
            for (Player p: Bukkit.getOnlinePlayers()) {
                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[G]: " + ChatColor.YELLOW + msg);
            }
            return true;
        }else{
            return false;
        }

    }
}
