package pl.dev.httyd.httydplugins.commands;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PayCommand  implements CommandExecutor {


    DBExecute dbExecute = new DBExecute();
    LocalTime time = LocalTime.now();
    LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2){

            Player player = (Player) sender;
            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }

            int amount;

            try{
                amount = Integer.getInteger(args[1]);
            }catch (Exception e){
                return false;
            }

            int playerBalance = dbExecute.getPlayerBalance(player);
            int player2Balance = dbExecute.getPlayerBalance(player2);

            int newPlayerBalance = playerBalance-amount;
            int newPlayer2Balance = player2Balance+amount;

            if(newPlayer2Balance<0){
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nie masz wystarczajacej ilosci monet!");
            }else{

                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

                String playerUserTag;
                String playerUserTag2;
                try{
                    playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                    playerUserTag2 = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
                }catch (Exception ignored){
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
                    return true;
                }


                if(dbExecute.updatePlayerBalance(player, newPlayerBalance)){
                    if(dbExecute.updatePlayerBalance(player2, newPlayer2Balance)){
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Przekazales " + amount + " monet " + playerUserTag2 + ChatColor.YELLOW + "*" );
                        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag + ChatColor.GOLD + " przekazal " + amount + " monet " + playerUserTag2 + ChatColor.GOLD + "**" );
                            }
                        }
                    }else{
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
                        return true;
                    }
                }else{
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
                    return true;
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

}
