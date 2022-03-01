package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class DiceCommand implements CommandExecutor {

    private ChatColor convertColor (String colorMC){
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

        Random random = new Random();

        if(args.length == 1){

            Player player = (Player) sender;
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
            String playerUserTag = "";
            try{
                playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }

            try {
                int value = Integer.parseInt(args[0]);
                int diceValue = random.nextInt(value) + 1;

                if(value>300 || value<1){
                    return false;
                }

                player.sendMessage(playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [k" + value + "]: " + ChatColor.GRAY + diceValue);
                for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [k" + value + "]: " + ChatColor.GRAY + diceValue);
                    }
                }
            }catch (Exception e){
                return false;
            }

            return true;
        }else if (args.length == 2){

            Player player = (Player) sender;
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
            String playerUserTag = "";
            try{
                playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }

            try {
                int number = Integer.parseInt(args[0]);
                int value = Integer.parseInt(args[1]);
                StringBuilder diceValueString = new StringBuilder();

                if(value>300 || value<1 || number<1 || number>10){
                    return false;
                }

                for(int i = 0; i<number; i++){
                    int diceValue = random.nextInt(value) + 1;
                    diceValueString.append(diceValue).append(" ");
                }

                player.sendMessage(playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [" + number + "k" + value + "]: " + ChatColor.GRAY + diceValueString);
                for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [" + number + "k" + value + "]: " + ChatColor.GRAY + diceValueString);
                    }
                }
            }catch (Exception e){
                return false;
            }

            return true;
        }else {
            return false;
        }
    }

}
