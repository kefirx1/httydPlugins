package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;

public class ViewCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();
    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            Player player = (Player) sender;

            if(args[0].equals("sprawdz")){
                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
                String playerUserTag = "";
                try{
                    playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                }catch (Exception ignored){
                }

                String playerView = dbExecute.getPlayerView(player);

                MessagesDataClass.vCPlayerView(player, playerUserTag, playerView);

                return true;
            }else{

                String playerName = player.getName();
                String newView = String.join(" ", args);

                if(dbExecute.updatePlayerView(playerName, newView)){
                    MessagesDataClass.vCPlayerViewChange(player);
                    return true;
                }else{
                    MessagesDataClass.vCPlayerViewError(player);
                    return true;
                }
            }
        } else {
            return false;
        }
    }

}
