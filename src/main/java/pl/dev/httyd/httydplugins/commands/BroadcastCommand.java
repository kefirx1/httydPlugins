package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.MessagesDataClass;

public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>0){
            Player player = (Player) sender;
            String msg = String.join(" ", args);
            MessagesDataClass.bCSend(player, msg);
            return true;
        }else{
            return false;
        }

    }
}
