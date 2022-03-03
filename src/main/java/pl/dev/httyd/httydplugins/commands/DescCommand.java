package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;
import java.time.LocalTime;


public class DescCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();
    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            if(args[1].equals("sprawdz")) {
                LocalTime time = LocalTime.now();
                LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

                Player player = (Player) sender;
                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
                String playerUserTag = "";
                try {
                    playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                } catch (Exception ignored) {
                }

                String playerDesc = dbExecute.getPlayerDesc(player);

                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Opis] " + playerUserTag + ChatColor.GOLD + ": " + playerDesc);

                return true;
            }else{
                LocalTime time = LocalTime.now();
                LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


                Player player = (Player) sender;
                String playerName = player.getName();
                String newDesc = String.join(" ", args);


                if(dbExecute.updatePlayerDescription(playerName, newDesc)){
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_GREEN + "Opis postaci zostal zmieniony!");
                    return true;
                }else{
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "Opis postaci nie mogl byc zmieniony");
                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
