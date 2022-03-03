package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.ScoreboardInfo;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.time.LocalTime;
import java.util.Objects;

public class PrefixCommand implements CommandExecutor {

    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        if(args.length == 2 ){

            Player player = (Player) sender;

            DBExecute dbExecute = new DBExecute();
            PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }
            String player2Name = args[0];

            String player2UserTagOld = "";
            try{
                player2UserTagOld = powerRanksExtensions.getUserTaq(player2);
            }catch (Exception ignored){
            }

            String newUsertag = getConvertedUsertag(args[1]);

            if(dbExecute.updatePlayerUsertag(player2, newUsertag)){
                player.performCommand("pr clearusertag " + player2Name);
                try{
                    player.performCommand("pr removeusertag " + player2UserTagOld);
                }catch (Exception ignored){
                }
                player.performCommand("pr createusertag " + newUsertag + " " + newUsertag);
                player.performCommand("pr setusertag " + player2Name + " " + newUsertag);
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED + "Prefix zostal zmieniony pomyslenie");
                scoreboardInfo.updateScoreboard(player2);
                return true;
            }else {
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED + "Blad - prefix nie mogl byc zmieniony");
                return false;
            }

        }else{
            return false;
        }
    }

    private String getConvertedUsertag(String prefix){

        String[] prefixList = prefix.split("");
        StringBuilder prefixString = new StringBuilder();
        prefixString.append("[");
        String temp = "";

        for(int i = 0; i<prefixList.length; i++){
            if(Objects.equals(prefixList[i], "&")){
                temp = prefixList[i]+prefixList[i+1];
                i++;
                continue;
            }
            prefixString.append(prefixList[i]);
        }
        prefixString.append("]");

        return temp+prefixString;

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
