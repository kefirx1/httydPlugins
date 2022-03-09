package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.data.BigDoorData;
import pl.dev.httyd.httydplugins.data.Gate1Data;

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
                        MessagesDataClass.dCCloseDoor(player, playerUserTag);
                    }else{
                        bigDoorData.setToAir();
                        MessagesDataClass.dCOpenDoor(player, playerUserTag);
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
                        MessagesDataClass.dCCloseGate1(player, playerUserTag);
                    }else{
                        gate1Data.setToAir();
                        MessagesDataClass.dCOpenGate1(player, playerUserTag);
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
