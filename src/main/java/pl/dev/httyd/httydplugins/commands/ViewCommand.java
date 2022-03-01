package pl.dev.httyd.httydplugins.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.database.DBExecute;

public class ViewCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0) {

            Player player = (Player) sender;
            String playerName = player.getName();
            String newView = String.join(" ", args);

            if(dbExecute.updatePlayerView(playerName, newView)){
                player.sendMessage(ChatColor.DARK_GREEN + "Wizerunek postaci zostal zmieniony!");
                return true;
            }else{
                player.sendMessage(ChatColor.DARK_RED + "Wizerunek postaci nie mogl byc zmieniony");
                return true;
            }
        } else {
            return false;
        }
    }

}
