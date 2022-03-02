package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DoorCommand implements CommandExecutor {

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

        World world = Bukkit.getWorld("world");
        Location bigDoorLocation = new Location(world, -37, 119, 192);

        Player player = (Player) sender;

        Location playerLocation = player.getLocation();

        if(bigDoorLocation.distance(playerLocation) > 4){
            return false;
        }


        PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

//        String playerUserTag = "";
//        try{
//            playerUserTag = getPlayerPrefixWithColor(powerRanksExtensionsChat.getUserTaq(player));
//        }catch (Exception ignored){
//        }

        player.performCommand("dtoggle sala");
//        player.sendMessage(ChatColor.YELLOW + "*Otworzyles drzwi*");
//        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
//            if (entity instanceof Player) {
//                Player p = (Player) entity;
//                p.sendMessage(ChatColor.GOLD + "** " + playerUserTag  + ChatColor.GOLD + " otworzyl drzwi*");
//            }
//        }

        return true;
    }
}
