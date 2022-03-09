package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

import java.util.Random;

public class TryCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Random random = new Random();

        if(args.length == 1){


            Player player = (Player) sender;
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
            String playerUserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }

            try {
                int value = Integer.parseInt(args[0]);

                if(random.nextInt(100) <= value){
                    MessagesDataClass.tCWin(player, playerUserTag, value);
                }else {
                    MessagesDataClass.tCFailed(player, playerUserTag, value);
                }

            }catch (Exception e){
                return false;
            }

        return true;
        }else{
            return false;
        }
    }

}
