package pl.dev.httyd.httydplugins.commands;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import java.time.LocalTime;

public class MeCommand implements CommandExecutor {

    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>0){

            LocalTime time = LocalTime.now();
            LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

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

            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.YELLOW + "*" + msg + "*");
            for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.YELLOW + "*" + msg + "*");
                }
            }

            return true;
        }else{
            return false;
        }

    }
}
