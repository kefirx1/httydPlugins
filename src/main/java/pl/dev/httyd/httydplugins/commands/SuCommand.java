package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.util.*;

public class SuCommand implements CommandExecutor {
    private ChatColor convertColor(String colorMC){
        Map<String, ChatColor> colorsMap = new HashMap<>();
        colorsMap.put("&0", ChatColor.BLACK);
        colorsMap.put("&1", ChatColor.DARK_BLUE);
        colorsMap.put("&2", ChatColor.DARK_GREEN);
        colorsMap.put("&3", ChatColor.DARK_AQUA);
        colorsMap.put("&4", ChatColor.DARK_RED);
        colorsMap.put("&5", ChatColor.DARK_PURPLE);
        colorsMap.put("&6", ChatColor.GOLD);
        colorsMap.put("&7", ChatColor.GRAY);
        colorsMap.put("&8", ChatColor.DARK_GRAY);
        colorsMap.put("&9", ChatColor.BLUE);
        colorsMap.put("&a", ChatColor.GREEN);
        colorsMap.put("&b", ChatColor.AQUA);
        colorsMap.put("&c", ChatColor.RED);
        colorsMap.put("&d", ChatColor.LIGHT_PURPLE);
        colorsMap.put("&e", ChatColor.YELLOW);
        colorsMap.put("&f", ChatColor.WHITE);
        colorsMap.put("&l", ChatColor.BOLD);
        colorsMap.put("&n", ChatColor.UNDERLINE);
        colorsMap.put("&o", ChatColor.ITALIC);
        colorsMap.put("&r", ChatColor.RESET);
        colorsMap.put("&m", ChatColor.STRIKETHROUGH);
        colorsMap.put("&k", ChatColor.MAGIC);
        return colorsMap.get(colorMC);
    }

    private String getPlayerPrefixWithColor(String basicPrefix) {
        if (basicPrefix.contains("&")) {
            String[] playerPrefixList = basicPrefix.split("");
            StringBuilder convertedPrefix = new StringBuilder("");
            for (int i = 0; i < playerPrefixList.length; i++) {
                if (Objects.equals(playerPrefixList[i], "&")) {
                    String color = playerPrefixList[i] + playerPrefixList[i + 1];

                    ChatColor newColor = convertColor(color);

                    convertedPrefix.append(newColor);
                    i++;
                    continue;
                }
                convertedPrefix.append(playerPrefixList[i]);
            }
            return convertedPrefix.toString();
        } else {
            return basicPrefix;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>1){

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
                playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                player2UserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
            }catch (Exception ignored){
            }

            List<Player> playersFirstStep = new ArrayList<>();


            player.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "*" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepczesz do ucha " + ChatColor.BOLD + player2UserTag + "* " + ChatColor.RESET + ChatColor.GRAY + msg);
            for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze ci do ucha** " + ChatColor.RESET + ChatColor.GRAY + msg);
                    playersFirstStep.add(p);
                }
            }
            for (Entity entity : player.getNearbyEntities(6, 6, 6)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    if(!playersFirstStep.contains(p)){
                        p.sendMessage(ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze  do ucha " + ChatColor.BOLD + player2UserTag + "**");
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
