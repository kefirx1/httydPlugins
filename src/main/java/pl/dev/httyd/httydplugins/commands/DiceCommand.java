package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import java.util.Random;

public class DiceCommand implements CommandExecutor {

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
                int diceValue = random.nextInt(value) + 1;

                if(value>300 || value<1){
                    return false;
                }
                MessagesDataClass.diceCOne(player, playerUserTag, value, diceValue);
            }catch (Exception e){
                return false;
            }

            return true;
        }else if (args.length == 2){

            Player player = (Player) sender;
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
            String playerUserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
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
                MessagesDataClass.diceCMore(player, playerUserTag, value, number, diceValueString);
            }catch (Exception e){
                return false;
            }
            return true;
        }else {
            return false;
        }
    }

}
