package pl.dev.httyd.httydplugins.commands;

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

public class SakiewkaCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();
    PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

         LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Player player = (Player) sender;

        String playerUsertag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));

        int playerBalance = dbExecute.getPlayerBalance(player);

        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "W sakiewce posiadasz: " + playerBalance + " monet");
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUsertag + " zaglada do swojej sakiewki**");
            }
        }

        return true;
    }
}
