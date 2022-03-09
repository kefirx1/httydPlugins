package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.Converter;
import pl.dev.httyd.httydplugins.MessagesDataClass;
import pl.dev.httyd.httydplugins.PowerRanksExtensions;
import pl.dev.httyd.httydplugins.database.DBExecute;

public class SakiewkaCommand implements CommandExecutor {

    DBExecute dbExecute = new DBExecute();
    PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
    Converter converter = new Converter();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        String playerUsertag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));

        int playerBalance = dbExecute.getPlayerBalance(player);

        MessagesDataClass.sCBalance(player, playerUsertag, playerBalance);

        return true;
    }
}
