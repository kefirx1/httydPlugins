package pl.dev.httyd.httydplugins.listeners;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;
import pl.dev.httyd.httydplugins.*;
import pl.dev.httyd.httydplugins.database.DBExecute;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class WorldListener implements Listener {

    HttydPlugins instance;
    DBExecute dbExecute = new DBExecute();
    Random random = new Random();
    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();
    Converter converter = new Converter();

    public WorldListener(HttydPlugins instance) {
        this.instance = instance;
    }


    private String translateDayOfWeek(DayOfWeek dayOfWeek) {
        Map<DayOfWeek, String> daysNameMap = new HashMap<>();
        daysNameMap.put(DayOfWeek.MONDAY, "PONIEDZIALEK");
        daysNameMap.put(DayOfWeek.TUESDAY, "WTOREK");
        daysNameMap.put(DayOfWeek.WEDNESDAY, "SRODA");
        daysNameMap.put(DayOfWeek.THURSDAY, "CZWARTEK");
        daysNameMap.put(DayOfWeek.FRIDAY, "PIATEK");
        daysNameMap.put(DayOfWeek.SATURDAY, "SOBOTA");
        daysNameMap.put(DayOfWeek.SUNDAY, "NIEDZIELA");

        return daysNameMap.get(dayOfWeek);
    }


    private void setNextDay(String newDayOfWeek) {


        LocalDate localDateNow = LocalDate.now();
        int dayOfMonth = localDateNow.getDayOfMonth();
        int monthValue = localDateNow.getMonthValue();
        int serverMonthValue = dbExecute.getServerMonthValue();
        int serverYear = dbExecute.getServerYear();

        String dayMonthString = dayOfMonth + String.valueOf(monthValue);

        switch (dayMonthString) {
            case "11":
            case "1811":
            case "24":
            case "185":
            case "37":
            case "188":
            case "310":
            case "152": {
                int newServerYear = serverYear + 1;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "51":
            case "64":
            case "225":
            case "77":
            case "228":
            case "710":
            case "2211":
            case "192": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "71":
            case "84":
            case "245":
            case "97":
            case "248":
            case "910":
            case "2411":
            case "222": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "111":
            case "124":
            case "285":
            case "137":
            case "288":
            case "1310":
            case "2811":
            case "252": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "151":
            case "13":
            case "164":
            case "16":
            case "177":
            case "19":
            case "1710":
            case "212": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "191":
            case "53":
            case "204":
            case "56":
            case "217":
            case "59":
            case "2110":
            case "612": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "231":
            case "93":
            case "244":
            case "96":
            case "257":
            case "99":
            case "2510":
            case "1012": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "271":
            case "133":
            case "284":
            case "136":
            case "297":
            case "139":
            case "2910":
            case "1412": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "311":
            case "173":
            case "25":
            case "176":
            case "28":
            case "179":
            case "211":
            case "1812": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "32":
            case "213":
            case "65":
            case "216":
            case "68":
            case "219":
            case "611":
            case "2212": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "72":
            case "253":
            case "105":
            case "256":
            case "259":
            case "1011":
            case "2612":
            case "108": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "112":
            case "293":
            case "145":
            case "296":
            case "148":
            case "1411":
            case "299":
            case "3012": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            default: {
                setNewServerDate(newDayOfWeek, serverMonthValue, serverYear);
                break;
            }

        }

    }

    private void setNewServerDate(String newDayOfWeek, int serverMonthValue, int serverYear) {

        dbExecute.updateServerDate(newDayOfWeek, serverMonthValue, serverYear);

    }

    private void setTemperatureWeather() {
        int serverMonthValue = dbExecute.getServerMonthValue();

        switch (serverMonthValue) {
            case 1: {
                int newTemperature = ThreadLocalRandom.current().nextInt(-3, 3 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 2: {
                int newTemperature = ThreadLocalRandom.current().nextInt(-2, 4 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 3: {
                int newTemperature = ThreadLocalRandom.current().nextInt(1, 8 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 4: {
                int newTemperature = ThreadLocalRandom.current().nextInt(6, 13 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 5: {
                int newTemperature = ThreadLocalRandom.current().nextInt(12, 18 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 6:
            case 9: {
                int newTemperature = ThreadLocalRandom.current().nextInt(18, 23 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 7:
            case 8: {
                int newTemperature = ThreadLocalRandom.current().nextInt(21, 26 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 10: {
                int newTemperature = ThreadLocalRandom.current().nextInt(12, 17 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 11: {
                int newTemperature = ThreadLocalRandom.current().nextInt(7, 12 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 12: {
                int newTemperature = ThreadLocalRandom.current().nextInt(2, 7 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
        }
    }

    private void setWeather(int temperature) {
        World world = Bukkit.getWorld("world");

        if (random.nextInt(100) <= 30) {
            if (temperature > 2) {
                if (random.nextInt(100) <= 60) {
                    for (int i = -480; i <= 320; i++) {
                        for (int j = -525; j <= 600; j++) {
                            world.setBiome(i, j, Biome.PLAINS);
                        }
                    }
                    dbExecute.updateServerWeather("DESZCZOWO");
                    world.setStorm(true);
                    world.setThundering(false);
                    world.setWeatherDuration(1728000);
                } else {
                    for (int i = -480; i <= 320; i++) {
                        for (int j = -525; j <= 600; j++) {
                            world.setBiome(i, j, Biome.PLAINS);
                        }
                    }
                    dbExecute.updateServerWeather("BURZOWO");
                    world.setStorm(true);
                    world.setThundering(true);
                    world.setThunderDuration(600);
                    world.setWeatherDuration(1728000);
                }
            } else {
                for (int i = -480; i <= 320; i++) {
                    for (int j = -525; j <= 600; j++) {
                        world.setBiome(i, j, Biome.TAIGA_COLD);
                    }
                }
                dbExecute.updateServerWeather("SNIEZNIE");
                world.setStorm(true);
                world.setThundering(false);
                world.setWeatherDuration(1728000);
            }
        } else {
            for (int i = -480; i <= 320; i++) {
                for (int j = -525; j <= 600; j++) {
                    world.setBiome(i, j, Biome.PLAINS);
                }
            }
            dbExecute.updateServerWeather("SLONECZNIE");
            world.setStorm(false);
            world.setThundering(false);
            world.setWeatherDuration(1728000);
        }
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent event) {

        LocalDate localDateNow = LocalDate.now();
        DayOfWeek dayOfWeek = localDateNow.getDayOfWeek();
        String dayOfWeekTranslated = translateDayOfWeek(dayOfWeek);
        String dayOfWeekServer = dbExecute.getServerDayOfWeek();
        String currentWeather = dbExecute.getServerWeather();

        if (!dayOfWeekServer.equals(dayOfWeekTranslated)) {
            setNextDay(dayOfWeekTranslated);
            setTemperatureWeather();
            if(currentWeather.equals("DESZCZOWO") || currentWeather.equals("BURZOWO")){
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(isPlayerOutside(p)){
                        int playerWetting = dbExecute.getPlayerWetting(p);
                        switch (playerWetting){
                            case 0:
                            case 1:
                            case 3:
                            case 5:
                            case 7: { //suchy
                                dbExecute.updatePlayerWetting(p.getName(), playerWetting+1);
                                break;
                            }
                            case 2:{ //Krople deszczu zaczynaja coraz mocniej pojawiac sie na ubraniach 10
                                MessagesDataClass.wLWetting2(p);
                                dbExecute.updatePlayerWetting(p.getName(), playerWetting+1);
                                break;
                            }
                            case 4:{ //Ubrania sa przemoczone 20
                                MessagesDataClass.wLWetting4(p);
                                dbExecute.updatePlayerWetting(p.getName(), playerWetting+1);
                                break;
                            }
                            case 6:{ //Twoje ubrania sa całkowice mokre 30
                                MessagesDataClass.wLWetting6(p);
                                dbExecute.updatePlayerWetting(p.getName(), playerWetting+1);
                                break;
                            }
                            case 8:{  //Jestes cały mokry, zaczyna Ci sie robić zimno 40
                                MessagesDataClass.wLWetting8(p);
                                dbExecute.updatePlayerWetting(p.getName(), playerWetting+1);
                                //start illness
                                break;
                            }
                            case 9:{
                                PowerRanksAPI apiPR = PowerRanks.getInstance().loadAPI();
                                PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();
                                String playerName = p.getName();
                                String playerRank = apiPR.getPrimaryRank(p);
                                String playerPrefix = converter.getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
                                String playerUserTag = "";
                                try{
                                    playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(p));
                                }catch (Exception ignored){
                                }
                                MessagesDataClass.wLWetting9(p, playerPrefix, playerUserTag, playerName);
                                break;
                            }
                        }
                    }
                    scoreboardInfo.updateScoreboard(p);
                }
            }else if(currentWeather.equals("SNIEZNIE")){
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if(isPlayerOutside(p)){

                        int playerWetting = dbExecute.getPlayerWetting(p);

                    }
                    scoreboardInfo.updateScoreboard(p);
                }
            }else{
                for (Player p : Bukkit.getOnlinePlayers()) {
                    scoreboardInfo.updateScoreboard(p);
                }
            }
        } else {
            LocalTime time = LocalTime.now();
            int hour = time.getHour();
            int min = time.getMinute();

            if ((hour == 12 && min < 6) || (hour == 18 && min < 6)) {
                setTemperatureWeather();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    scoreboardInfo.updateScoreboard(p);
                }
            }
        }
    }


    private boolean isPlayerOutside(Player player){
        Location loc = player.getEyeLocation().add(0,1,0);

        while(loc.getY() < 256){
            if(loc.getBlock().getType() != Material.AIR){
                return false;
            }
            loc.add(0,1,0);
        }
        return true;
    }


}
