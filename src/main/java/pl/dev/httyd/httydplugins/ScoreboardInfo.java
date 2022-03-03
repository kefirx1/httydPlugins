package pl.dev.httyd.httydplugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.util.Objects;

public class ScoreboardInfo {

    DBExecute dbExecute = new DBExecute();
    Converter converter = new Converter();

    public void updateScoreboard(Player player){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("playerInfo", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "SMOKI RP");

        Score scoreNull5 = objective.getScore(ChatColor.BOLD + "" + ChatColor.YELLOW + "===================");
        scoreNull5.setScore(15);

        String playerUsertag = dbExecute.getPlayerPrefix(player);
        Score scoreUsertag = objective.getScore(converter.getPlayerPrefixWithColor(playerUsertag));
        scoreUsertag.setScore(14);

        int playerDateOfBirth = dbExecute.getPlayerDateOfBirth(player);
        int serverYear = dbExecute.getServerYear();
        int playerAge = serverYear-playerDateOfBirth;
        Score scoreAge = objective.getScore(ChatColor.WHITE + "Wiek: " + ChatColor.GRAY + playerAge);
        scoreAge.setScore(13);

        String playerSuffix = dbExecute.getPlayerSuffix(player);
        Score scoreSuffix = objective.getScore(ChatColor.WHITE + "Funkcja: " + ChatColor.GRAY + playerSuffix);
        scoreSuffix.setScore(12);

        Score scoreNull4 = objective.getScore(ChatColor.BOLD + "" + ChatColor.YELLOW + "===================");
        scoreNull4.setScore(11);

        String playerIsland = dbExecute.getPlayerIsland(player);
        Score scoreIsland = objective.getScore(ChatColor.WHITE + "Plemie: " + ChatColor.BOLD + "" + ChatColor.DARK_RED + playerIsland);
        scoreIsland.setScore(10);

        Score scoreNull3 = objective.getScore(ChatColor.BOLD + "" + ChatColor.YELLOW + "===================");
        scoreNull3.setScore(9);

        String playerCondition = dbExecute.getPlayerCondition(player);
        Score scoreCondition;
        if(Objects.equals(playerCondition, "Zdrowy")){
            scoreCondition = objective.getScore(ChatColor.WHITE + "Stan: " + ChatColor.GREEN + playerCondition);
        }else{
            scoreCondition = objective.getScore(ChatColor.WHITE + "Stan: " + ChatColor.DARK_RED + playerCondition);
        }
        scoreCondition.setScore(8);
        
        int balance = dbExecute.getPlayerBalance(player);
        Score scoreBalance = objective.getScore(ChatColor.WHITE + "Sakiewka: " + ChatColor.GRAY + balance);
        scoreBalance.setScore(7);

        Score scoreNull2 = objective.getScore(ChatColor.BOLD + "" + ChatColor.YELLOW + "===================");
        scoreNull2.setScore(6);

        int currentYear = dbExecute.getServerYear();
        int currentMonthValues = dbExecute.getServerMonthValue();
        Score scoreYear = objective.getScore(ChatColor.WHITE + "Data: " + ChatColor.GRAY + currentMonthValues + "/" + currentYear);
        scoreYear.setScore(5);

        String currentDay = dbExecute.getServerDayOfWeek();
        Score scoreDay = objective.getScore(ChatColor.WHITE + "Dzien: " + ChatColor.GRAY + currentDay);
        scoreDay.setScore(4);

        int currentTemperature = dbExecute.getServerTemperature();
        Score scoreTemperature = objective.getScore(ChatColor.WHITE + "Temperatura: " + ChatColor.GRAY + currentTemperature);
        scoreTemperature.setScore(3);

        String currentWeather = dbExecute.getServerWeather();
        Score scoreWeather = objective.getScore(ChatColor.WHITE + "Pogoda: " + ChatColor.GRAY + currentWeather);
        scoreWeather.setScore(2);

        Score scoreNull1 = objective.getScore(ChatColor.BOLD + "" + ChatColor.YELLOW + "===================");
        scoreNull1.setScore(1);

        player.setScoreboard(scoreboard);
    }

}
