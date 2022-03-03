package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DoorCommand implements CommandExecutor {

    Converter converter = new Converter();

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
