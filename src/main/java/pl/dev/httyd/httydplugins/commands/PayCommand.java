package pl.dev.httyd.httydplugins.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.time.LocalTime;
import java.util.Objects;

public class PayCommand  implements CommandExecutor {

    Converter converter = new Converter();
    DBExecute dbExecute = new DBExecute();
    LocalTime time = LocalTime.now();
    LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2){

            Player player = (Player) sender;
            Player player2 = getDestinationPlayer(args[0]);
            if (player2 == null){
                return false;
            }

            int amount;

            try{
                amount = Integer.parseInt(args[1]);
            }catch (Exception e){
                return false;
            }

            int playerBalance = dbExecute.getPlayerBalance(player);
            int player2Balance = dbExecute.getPlayerBalance(player2);

            int newPlayerBalance = playerBalance-amount;
            int newPlayer2Balance = player2Balance+amount;

            if(newPlayer2Balance<0){
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nie masz wystarczajacej ilosci monet!");
            }else{

                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

                String playerUserTag;
                String playerUserTag2;
                try{
                    playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
                    playerUserTag2 = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player2));
                }catch (Exception ignored){
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
                    return true;
                }


                if(dbExecute.updatePlayerBalance(player, newPlayerBalance)){
                    if(dbExecute.updatePlayerBalance(player2, newPlayer2Balance)){
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Przekazales " + amount + " monet " + playerUserTag2 + ChatColor.YELLOW + "*" );
                        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
                            if (entity instanceof Player) {
                                Player p = (Player) entity;
                                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag + ChatColor.GOLD + " przekazal " + amount + " monet " + playerUserTag2 + ChatColor.GOLD + "**" );
                            }
                        }
                    }else{
                        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
                        return true;
                    }
                }else{
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
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
