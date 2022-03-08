package pl.dev.httyd.httydplugins.commands;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.time.LocalTime;

public class DoorCommand implements CommandExecutor {

    Converter converter = new Converter();
    World world = Bukkit.getWorld("world");
    Location bigDoorLocation = new Location(world, -37, 119, 192);
    Location gate1Location = new Location(world, 7, 80, 200);


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1){
            return  false;
        }

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Location bigDoorBlockFront1 = new Location(world, -38, 119, 192 ); //deski debowe
        Location bigDoorBlockFront2 = new Location(world, -38, 120, 192 ); //wood_wall_3
        Location bigDoorBlockFront3 = new Location(world, -38, 121, 192 );
        Location bigDoorBlockFront4 = new Location(world, -38, 122, 192 );
        Location bigDoorBlockFront5 = new Location(world, -38, 123, 192 );
        Location bigDoorBlockFront6 = new Location(world, -38, 124, 192 );
        Location bigDoorBlockFront7 = new Location(world, -38, 125, 192 );
        Location bigDoorBlockFront9 = new Location(world, -38, 126, 192 );
        Location bigDoorBlockFront10 = new Location(world, -38, 127, 192 );
        Location bigDoorBlockFront11 = new Location(world, -38, 128, 192 );
        Location bigDoorBlockFront12 = new Location(world, -38, 129, 192 );
        Location bigDoorBlockFront13 = new Location(world, -38, 130, 192 );
        Location bigDoorBlockFront14 = new Location(world, -38, 131, 192 ); //deski debowe




        Location gate1Block1 = new Location(world, 6, 80, 199);
        Location gate1Block2 = new Location(world, 7, 80, 199);
        Location gate1Block3 = new Location(world, 8, 80, 199);
        Location gate1Block4 = new Location(world, 6, 81, 199);
        Location gate1Block5 = new Location(world, 7, 81, 199);
        Location gate1Block6 = new Location(world, 8, 81, 199);
        Location gate1Block7 = new Location(world, 6, 82, 199);
        Location gate1Block8 = new Location(world, 7, 82, 199);
        Location gate1Block9 = new Location(world, 8, 82, 199);
        Location gate1Block10 = new Location(world, 6, 83, 199);
        Location gate1Block11 = new Location(world, 7, 83, 199);
        Location gate1Block12 = new Location(world, 8, 83, 199);
        Location gate1Block13 = new Location(world, 6, 84, 199);
        Location gate1Block14 = new Location(world, 7, 84, 199);
        Location gate1Block15 = new Location(world, 8, 84, 199);
        Location gate1Block16 = new Location(world, 6, 85, 199);
        Location gate1Block17 = new Location(world, 7, 85, 199);
        Location gate1Block18 = new Location(world, 8, 85, 199);
        Location gate1Block19 = new Location(world, 6, 86, 199);
        Location gate1Block20 = new Location(world, 7, 86, 199);
        Location gate1Block21 = new Location(world, 8, 86, 199);

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
                    if(isOpened(bigDoorBlockFront1)){
                        bigDoorBlockFront1.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront2.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront3.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront4.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront5.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront6.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront7.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront9.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront10.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront11.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront12.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront13.getBlock().setType(Material.WOOD);
                        bigDoorBlockFront14.getBlock().setType(Material.WOOD);

                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales drzwi*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " zamknal drzwi**");
                            }
                        }

                    }else{
                        bigDoorBlockFront1.getBlock().setType(Material.AIR);
                        bigDoorBlockFront2.getBlock().setType(Material.AIR);
                        bigDoorBlockFront3.getBlock().setType(Material.AIR);
                        bigDoorBlockFront4.getBlock().setType(Material.AIR);
                        bigDoorBlockFront5.getBlock().setType(Material.AIR);
                        bigDoorBlockFront6.getBlock().setType(Material.AIR);
                        bigDoorBlockFront7.getBlock().setType(Material.AIR);
                        bigDoorBlockFront9.getBlock().setType(Material.AIR);
                        bigDoorBlockFront10.getBlock().setType(Material.AIR);
                        bigDoorBlockFront11.getBlock().setType(Material.AIR);
                        bigDoorBlockFront12.getBlock().setType(Material.AIR);
                        bigDoorBlockFront13.getBlock().setType(Material.AIR);
                        bigDoorBlockFront14.getBlock().setType(Material.AIR);

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
                    if(isOpened(gate1Block1)){
                        gate1Block1.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block2.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block3.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block4.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block5.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block6.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block7.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block8.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block9.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block10.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block11.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block12.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block13.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block14.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block15.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block16.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block17.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block18.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block19.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block20.getBlock().setType(Material.SPRUCE_FENCE);
                        gate1Block21.getBlock().setType(Material.SPRUCE_FENCE);

                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales brame*");
                        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "** " + playerUserTag  + ChatColor.GOLD + " zamknal brame*");
                            }
                        }

                    }else{
                        gate1Block1.getBlock().setType(Material.AIR);
                        gate1Block2.getBlock().setType(Material.AIR);
                        gate1Block3.getBlock().setType(Material.AIR);
                        gate1Block4.getBlock().setType(Material.AIR);
                        gate1Block5.getBlock().setType(Material.AIR);
                        gate1Block6.getBlock().setType(Material.AIR);
                        gate1Block7.getBlock().setType(Material.AIR);
                        gate1Block8.getBlock().setType(Material.AIR);
                        gate1Block9.getBlock().setType(Material.AIR);
                        gate1Block10.getBlock().setType(Material.AIR);
                        gate1Block11.getBlock().setType(Material.AIR);
                        gate1Block12.getBlock().setType(Material.AIR);
                        gate1Block13.getBlock().setType(Material.AIR);
                        gate1Block14.getBlock().setType(Material.AIR);
                        gate1Block15.getBlock().setType(Material.AIR);
                        gate1Block16.getBlock().setType(Material.AIR);
                        gate1Block17.getBlock().setType(Material.AIR);
                        gate1Block18.getBlock().setType(Material.AIR);
                        gate1Block19.getBlock().setType(Material.AIR);
                        gate1Block20.getBlock().setType(Material.AIR);
                        gate1Block21.getBlock().setType(Material.AIR);

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
