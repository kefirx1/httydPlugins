package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.CreatePlayerMenu;

public class MenuCommand implements CommandExecutor{

    String playerClickedName;
    Player playerClicked;

    CreatePlayerMenu createPlayerMenu = new CreatePlayerMenu();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;
        playerClicked = player;
        playerClickedName = playerClicked.getName();

        createPlayerMenu.showGUI(player, playerClickedName);

        return true;
    }

}
