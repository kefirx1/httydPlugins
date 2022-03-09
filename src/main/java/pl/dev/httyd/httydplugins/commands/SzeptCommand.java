package pl.dev.httyd.httydplugins.commands;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;

public class SzeptCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>0){

            Player player = (Player) sender;

            PowerRanksAPI apiPR = PowerRanks.getInstance().loadAPI();
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

            String playerName = player.getName();
            String msg = String.join(" ", args);
            String playerRank = apiPR.getPrimaryRank(player);
            String playerPrefix = converter.getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
            String playerUserTag = "";
            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }
            MessagesDataClass.sCWhisper(player, playerPrefix, playerUserTag, playerName, msg);
            return true;
        }else{
            return false;
        }

    }
}
