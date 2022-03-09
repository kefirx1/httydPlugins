package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.ScoreboardInfo;
import pl.dev.httyd.httydplugins.database.DBExecute;
import java.util.Objects;

public class PayCommand  implements CommandExecutor {

    Converter converter = new Converter();
    DBExecute dbExecute = new DBExecute();
    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2){

            Player player = (Player) sender;
            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }

            if(player.getName().equals(player2.getName())){
                MessagesDataClass.payCFailedU(player);
                return true;
            }

            int amount;
            try{
                amount = Integer.parseInt(args[1]);
            }catch (Exception e){
                return false;
            }

            if(amount<=0){
                MessagesDataClass.payCZero(player);
                return true;
            }

            int playerBalance = dbExecute.getPlayerBalance(player);
            int player2Balance = dbExecute.getPlayerBalance(player2);

            int newPlayerBalance = playerBalance-amount;
            int newPlayer2Balance = player2Balance+amount;

            if(newPlayer2Balance<0){
                MessagesDataClass.payCTooLittle(player);
            }else{

                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

                String playerUserTag;
                String playerUserTag2;
                try{
                    playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                    playerUserTag2 = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
                }catch (Exception ignored){
                    MessagesDataClass.payCFailed(player);
                    return true;
                }


                if(dbExecute.updatePlayerBalance(player, newPlayerBalance)){
                    if(dbExecute.updatePlayerBalance(player2, newPlayer2Balance)){
                        MessagesDataClass.payCCorrect(player, playerUserTag, playerUserTag2, amount);
                        scoreboardInfo.updateScoreboard(player);
                        scoreboardInfo.updateScoreboard(player2);
                    }else{
                        MessagesDataClass.payCFailed(player);
                        return true;
                    }
                }else{
                    MessagesDataClass.payCFailed(player);
                    return true;
                }
            }
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
