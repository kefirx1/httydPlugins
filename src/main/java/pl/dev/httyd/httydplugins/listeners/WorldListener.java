package pl.dev.httyd.httydplugins.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldSaveEvent;
import pl.dev.httyd.httydplugins.HttydPlugins;
import pl.dev.httyd.httydplugins.ScoreboardInfo;
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

    public WorldListener(HttydPlugins instance){
        this.instance = instance;
    }


    private String translateDayOfWeek(DayOfWeek dayOfWeek){
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
            case "11": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "51": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "71": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "111": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "151": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "191": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "231": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "271": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "311": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "32": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "72": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "112": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "152": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "192": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "222": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "252": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "13": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "53": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "93": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "133": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "173": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "213": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "253": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "293": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "24": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "64": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "84": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "124": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "164": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "204": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "244": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "284": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "25": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "65": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "105": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "145": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "185": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "225": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "245": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "285": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "16": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "56": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "96": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "136": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "176": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "216": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "256": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "296": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "37": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "77": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "97": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "137": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "177": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "217": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "257": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "297": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "28": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "68": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "108": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "148": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "188": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "228": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "248": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "288": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "19": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "59": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "99": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "139": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "179": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "219": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "259": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "299": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "310": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "710": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "910": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "1310": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "1710": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "2110": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "2510": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "2910": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "211": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "611": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "1011": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
            case "1411": {
                setNewServerDate(newDayOfWeek, 12, serverYear);
                break;
            }
            case "1811": {
                int newServerYear = serverYear++;
                setNewServerDate(newDayOfWeek, 1, newServerYear);
                break;
            }
            case "2211": {
                setNewServerDate(newDayOfWeek, 2, serverYear);
                break;
            }
            case "2411": {
                setNewServerDate(newDayOfWeek, 3, serverYear);
                break;
            }
            case "2811": {
                setNewServerDate(newDayOfWeek, 4, serverYear);
                break;
            }
            case "212": {
                setNewServerDate(newDayOfWeek, 5, serverYear);
                break;
            }
            case "612": {
                setNewServerDate(newDayOfWeek, 6, serverYear);
                break;
            }
            case "1012": {
                setNewServerDate(newDayOfWeek, 7, serverYear);
                break;
            }
            case "1412": {
                setNewServerDate(newDayOfWeek, 8, serverYear);
                break;
            }
            case "1812": {
                setNewServerDate(newDayOfWeek, 9, serverYear);
                break;
            }
            case "2212": {
                setNewServerDate(newDayOfWeek, 10, serverYear);
                break;
            }
            case "2612": {
                setNewServerDate(newDayOfWeek, 11, serverYear);
                break;
            }
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

    private void setTemperatureWeather(){
        int serverMonthValue = dbExecute.getServerMonthValue();

        switch (serverMonthValue){
            case 1:{
                int newTemperature = ThreadLocalRandom.current().nextInt(-3, 3 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 2:{
                int newTemperature = ThreadLocalRandom.current().nextInt(-2, 4 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 3:{
                int newTemperature = ThreadLocalRandom.current().nextInt(1, 8 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 4:{
                int newTemperature = ThreadLocalRandom.current().nextInt(6, 13 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 5:{
                int newTemperature = ThreadLocalRandom.current().nextInt(12, 18 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 6:{
                int newTemperature = ThreadLocalRandom.current().nextInt(18, 23 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 7:{
                int newTemperature = ThreadLocalRandom.current().nextInt(21, 26 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 8:{
                int newTemperature = ThreadLocalRandom.current().nextInt(21, 26 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 9:{
                int newTemperature = ThreadLocalRandom.current().nextInt(18, 23 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 10:{
                int newTemperature = ThreadLocalRandom.current().nextInt(12, 17 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 11:{
                int newTemperature = ThreadLocalRandom.current().nextInt(7, 12 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
            case 12:{
                int newTemperature = ThreadLocalRandom.current().nextInt(2, 7 + 1);
                dbExecute.updateServerTemperature(newTemperature);
                setWeather(newTemperature);
                break;
            }
        }
    }

    private void setWeather(int temperature) {
        World world = Bukkit.getWorld("world");

        if(random.nextInt(100)<=30){
            if(temperature>2){
                if(random.nextInt(100)<=60) {
                    for(int i = -480; i<=320; i++){
                        for (int j = -525; j<=600; j++){
                            world.setBiome(i, j, Biome.PLAINS);
                        }
                    }
                    dbExecute.updateServerWeather("DESZCZOWO");
                    world.setStorm(true);
                    world.setThundering(false);
                    world.setWeatherDuration(1728000);
                }else{
                    for(int i = -480; i<=320; i++){
                        for (int j = -525; j<=600; j++){
                            world.setBiome(i, j, Biome.PLAINS);
                        }
                    }
                    dbExecute.updateServerWeather("BURZOWO");
                    world.setStorm(true);
                    world.setThundering(true);
                    world.setThunderDuration(600);
                    world.setWeatherDuration(1728000);
                }
            }else {
                for(int i = -480; i<=320; i++){
                    for (int j = -525; j<=600; j++){
                        world.setBiome(i, j, Biome.TAIGA_COLD);
                    }
                }
                dbExecute.updateServerWeather("SNIEZNIE");
                world.setStorm(true);
                world.setThundering(false);
                world.setWeatherDuration(1728000);
            }
        }else {
            for(int i = -480; i<=320; i++){
                for (int j = -525; j<=600; j++){
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
    public void onWorldSave (WorldSaveEvent event){

        LocalDate localDateNow = LocalDate.now();
        DayOfWeek dayOfWeek = localDateNow.getDayOfWeek();
        String dayOfWeekTranslated = translateDayOfWeek(dayOfWeek);
        String dayOfWeekServer = dbExecute.getServerDayOfWeek();

        if(!dayOfWeekServer.equals(dayOfWeekTranslated)){
            setNextDay(dayOfWeekTranslated);
            setTemperatureWeather();
            ScoreboardInfo scoreboardInfo = new ScoreboardInfo();
            for (Player p: Bukkit.getOnlinePlayers()) {
                scoreboardInfo.updateScoreboard(p);
            }

        }

    }

}
