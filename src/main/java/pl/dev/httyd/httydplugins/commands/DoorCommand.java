package pl.dev.httyd.httydplugins.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.data.BigDoorData;
import pl.dev.httyd.httydplugins.data.Gate1Data;

import java.time.LocalTime;

public class DoorCommand implements CommandExecutor {

    Converter converter = new Converter();
    World world = Bukkit.getWorld("world");
    Location bigDoorLocation = new Location(world, -38, 120, 192 );
    Location gate1Location = new Location(world, 7, 80, 199);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1){
            return  false;
        }

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

        BigDoorData bigDoorData = new BigDoorData();
        Gate1Data gate1Data = new Gate1Data();

        Player player = (Player) sender;

        Location playerLocation = player.getLocation();

        String doors = args[0];

        PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

        String playerUserTag = "";
        try{
            playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
        }catch (Exception ignored){
        }

        switch (doors){
            case "wrota":{

                if(bigDoorLocation.distance(playerLocation) > 4){
                    return false;
                }else{
                    if(isOpened(bigDoorLocation)){
                        bigDoorData.setToWood();

                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales drzwi*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " zamknal drzwi**");
                            }
                        }

                    }else{
                        bigDoorData.setToAir();

                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Otworzyles drzwi*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " otworzyl drzwi**");
                            }
                        }

                    }
                }

                break;
            }
            case "brama":{

                if(gate1Location.distance(playerLocation) > 6){
                    return false;
                }else{
                    if(isOpened(gate1Location)){
                        gate1Data.setToFence();
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales brame*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "** " + playerUserTag  + ChatColor.GOLD + " zamknal brame*");
                            }
                        }

                    }else{
                        gate1Data.setToAir();
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Otworzyles brame*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " otworzyl brame**");
                            }
                        }
                    }
                }
                break;
            }
            default:{
                return false;
            }
        }

        return true;
    }


    private boolean isOpened(Location door){

        return door.getBlock().getType() == Material.AIR;

    }


}
