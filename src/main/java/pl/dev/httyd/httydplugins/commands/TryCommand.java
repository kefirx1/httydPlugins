package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.time.LocalTime;
import java.util.Random;

public class TryCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Random random = new Random();

        if(args.length == 1){


            Player player = (Player) sender;
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
            String playerUserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }

            try {
                int value = Integer.parseInt(args[0]);

                if(random.nextInt(100) <= value){
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_GREEN + ChatColor.BOLD + " Udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
                    for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_GREEN + ChatColor.BOLD + " Udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
                        }
                    }
                }else {
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_RED + ChatColor.BOLD + " Nie udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
                    for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_RED + ChatColor.BOLD + " Nie udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
                        }
                    }
                }

            }catch (Exception e){
                return false;
            }

        return true;
        }else{
            return false;
        }
    }

}
