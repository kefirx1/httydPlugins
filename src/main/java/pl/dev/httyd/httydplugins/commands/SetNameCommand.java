package pl.dev.httyd.httydplugins.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.CreateCharacterCard;
import pl.dev.httyd.httydplugins.database.DBExecute;

public class SetNameCommand implements CommandExecutor {

    String name;
    String surname;
    int age;
    CreateCharacterCard createCharacterCard = new CreateCharacterCard();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length>1){
            if(args.length==2){
                name = args[0];
                age = Integer.parseInt(args[1]);
                CreateCharacterCard.newPlayerStatistics.nick = sender.getName();
                CreateCharacterCard.newPlayerStatistics.prefix = "&2" + name;
                CreateCharacterCard.newPlayerStatistics.dateOfBirth = getDateOfBirth(age);
            }else if (args.length==3){
                name = args[0];
                surname = args[1];
                age = Integer.parseInt(args[2]);
                CreateCharacterCard.newPlayerStatistics.nick = sender.getName();
                CreateCharacterCard.newPlayerStatistics.prefix = "&2" + name + surname;
                CreateCharacterCard.newPlayerStatistics.dateOfBirth = getDateOfBirth(age);
            }else {
                return false;
            }

            createCharacterCard.guiSexStep((Player) sender);

            return true;
        }else {
            return false;
        }

    }

    private int getDateOfBirth(int age) {

        DBExecute dbExecute = new DBExecute();

        int currentICDate =  dbExecute.getServerYear();

        return currentICDate - age;
    }
}
