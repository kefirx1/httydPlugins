package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SuCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>1){

            Player player = (Player) sender;
            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }

            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

            List<String> messageList = new ArrayList<>();

            for(String message: args){
                if(Objects.equals(message, args[0])){
                    continue;
                }
                messageList.add(message);
            }

            String msg = String.join(" ", messageList);

            String playerUserTag = "";
            String player2UserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                player2UserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
            }catch (Exception ignored){
            }
            MessagesDataClass.sUCWhisper(player, playerUserTag, player2UserTag, msg);
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
}
