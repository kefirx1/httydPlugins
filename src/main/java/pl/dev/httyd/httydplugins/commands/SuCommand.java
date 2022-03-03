package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import java.time.LocalTime;
import java.util.*;

public class SuCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>1){

            LocalTime time = LocalTime.now();
            LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


            Player player = (Player) sender;
            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }

            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

            List<String> messageList = new ArrayList<>();

            for(String message: args){
                if(Objects.equals(message, args[0])){
                    continue;
                }
                messageList.add(message);
            }

            String msg = String.join(" ", messageList);

            String playerUserTag = "";
            String player2UserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                player2UserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
            }catch (Exception ignored){
            }

            List<Player> playersFirstStep = new ArrayList<>();


            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "*" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepczesz do ucha " + ChatColor.BOLD + player2UserTag + "* " + ChatColor.RESET + ChatColor.GRAY + msg);
            for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze ci do ucha** " + ChatColor.RESET + ChatColor.GRAY + msg);
                    playersFirstStep.add(p);
                }
            }
            for (Entity entity : player.getNearbyEntities(6, 6, 6)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    if(!playersFirstStep.contains(p)){
                        p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze  do ucha " + ChatColor.BOLD + player2UserTag + "**");
                    }
                }
            }

            return true;
        }else{
            return false;
        }

    }

    private Player getDestinationPlayer(String arg) {
        for (Player p: Bukkit.getOnlinePlayers()) {
            if(Objects.equals(p.getName(), arg)){
                return p;
            }
        }
        return null;
    }
}
