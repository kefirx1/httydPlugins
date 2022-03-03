package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class ViewCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();


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
        if (args.length > 0) {

            if(args[1].equals("sprawdz")){
                LocalTime time = LocalTime.now();
                LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

                Player player = (Player) sender;
                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
                String playerUserTag = "";
                try{
                    playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                }catch (Exception ignored){
                }

                String playerView = dbExecute.getPlayerView(player);

                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Wizerunek] " + playerUserTag + ChatColor.GOLD + ": " + playerView);

                return true;
            }else{
                LocalTime time = LocalTime.now();
                LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

                Player player = (Player) sender;
                String playerName = player.getName();
                String newView = String.join(" ", args);

                if(dbExecute.updatePlayerView(playerName, newView)){
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_GREEN + "Wizerunek postaci zostal zmieniony!");
                    return true;
                }else{
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "Wizerunek postaci nie mogl byc zmieniony");
                    return true;
                }
            }
        } else {
            return false;
        }
    }

}
