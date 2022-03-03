package pl.dev.httyd.httydplugins.database;

import org.bukkit.entity.Player;
import pl.dev.httyd.httydplugins.CreateCharacterCard;

import java.sql.ResultSet;
import java.sql.Statement;

public class DBExecute {

    DBConnection dbConnection = new DBConnection();
    Statement statement;

    public boolean addNewCard(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String values = "'"
                    + CreateCharacterCard.newPlayerStatistics.nick + "','"
                    + CreateCharacterCard.newPlayerStatistics.prefix + "','"
                    + CreateCharacterCard.newPlayerStatistics.suffix + "','"
                    + CreateCharacterCard.newPlayerStatistics.dateOfBirth + "','"
                    + CreateCharacterCard.newPlayerStatistics.sex + "','"
                    + CreateCharacterCard.newPlayerStatistics.island + "','"
                    + CreateCharacterCard.newPlayerStatistics.balance + "','"
                    + CreateCharacterCard.newPlayerStatistics.descPlayer + "','"
                    + CreateCharacterCard.newPlayerStatistics.conditionPlayer + "','"
                    + CreateCharacterCard.newPlayerStatistics.playerView + "','"
                    + CreateCharacterCard.newPlayerStatistics.herbology + "','"
                    + CreateCharacterCard.newPlayerStatistics.herbologyTime + "','"
                    + CreateCharacterCard.newPlayerStatistics.herbologyCrops + "','"
                    + CreateCharacterCard.newPlayerStatistics.blacksmithing + "','"
                    + CreateCharacterCard.newPlayerStatistics.blacksmithingAppearance + "','"
                    + CreateCharacterCard.newPlayerStatistics.blacksmithingQuality + "','"
                    + CreateCharacterCard.newPlayerStatistics.blacksmithingDurability + "','"
                    + CreateCharacterCard.newPlayerStatistics.attack + "','"
                    + CreateCharacterCard.newPlayerStatistics.attackStrength + "','"
                    + CreateCharacterCard.newPlayerStatistics.attackSpeed + "','"
                    + CreateCharacterCard.newPlayerStatistics.attackStamina + "','"
                    + CreateCharacterCard.newPlayerStatistics.farming + "','"
                    + CreateCharacterCard.newPlayerStatistics.farmingTime + "','"
                    + CreateCharacterCard.newPlayerStatistics.farmingCrops + "','"
                    + CreateCharacterCard.newPlayerStatistics.farmingAnimals + "','"
                    + CreateCharacterCard.newPlayerStatistics.brain + "','"
                    + CreateCharacterCard.newPlayerStatistics.brainControl + "','"
                    + CreateCharacterCard.newPlayerStatistics.brainPatience + "','"
                    + CreateCharacterCard.newPlayerStatistics.dragons + "','"
                    + CreateCharacterCard.newPlayerStatistics.dragonsDomestication + "','"
                    + CreateCharacterCard.newPlayerStatistics.dragonsDuels + "','"
                    + CreateCharacterCard.newPlayerStatistics.dragonsFlying + "','"
                    + CreateCharacterCard.newPlayerStatistics.dragonsCare + "'";
            String query = "INSERT INTO players_statistics(`nick`,`prefix`,`suffix`,`dateOfBirth`,`sex`,`island`,`balance`, `descPlayer`, `conditionPlayer`, `playerView`, `herbology`,`herbologyTime`,`herbologyCrops`,`blacksmithing`,`blacksmithingAppearance`,`blacksmithingQuality`,`blacksmithingDurability`,`attack`,`attackStrength`,`attackSpeed`,`attackStamina`,`farming`,`farmingTime`,`FarmingCrops`,`FarmingAnimals`,`brain`,`brainControl`,`brainPatience`,`dragons`,`dragonsDomestication`,`dragonsDuels`,`dragonsFlying`,`dragonsCare`) VALUES( " + values + " )";

            try{

                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean getIsNewPlayer(Player p){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = p.getName();
            String query = "SELECT count(id) as 'id' FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet isNewPlayerQueryResult = statement.executeQuery(query);

                isNewPlayerQueryResult.next();

                int isNewPlayerInt = isNewPlayerQueryResult.getInt("id");

                return isNewPlayerInt == 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public String getPlayerDesc(Player p){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = p.getName();
            String query = "SELECT descPlayer FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet descPlayerQueryResult = statement.executeQuery(query);

                descPlayerQueryResult.next();

                return descPlayerQueryResult.getString("descPlayer");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public int getPlayerDateOfBirth(Player p){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = p.getName();
            String query = "SELECT dateOfBirth FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet dateOfBirthPlayerQueryResult = statement.executeQuery(query);

                dateOfBirthPlayerQueryResult.next();

                return dateOfBirthPlayerQueryResult.getInt("dateOfBirth");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public String getPlayerPrefix(Player p) {
        statement = dbConnection.getStatementDB();

        if (statement != null) {

            String playerName = p.getName();
            String query = "SELECT prefix FROM players_statistics WHERE nick = '" + playerName + "'";

            try {
                ResultSet prefixPlayerQueryResult = statement.executeQuery(query);

                prefixPlayerQueryResult.next();

                return prefixPlayerQueryResult.getString("prefix");

            } catch (Exception ignored) {
                return null;
            }

        } else {
            return null;
        }
    }

    public String getPlayerIsland(Player p) {
        statement = dbConnection.getStatementDB();

        if (statement != null) {

            String playerName = p.getName();
            String query = "SELECT island FROM players_statistics WHERE nick = '" + playerName + "'";

            try {
                ResultSet islandPlayerQueryResult = statement.executeQuery(query);

                islandPlayerQueryResult.next();

                return islandPlayerQueryResult.getString("island");

            } catch (Exception ignored) {
                return null;
            }

        } else {
            return null;
        }
    }

    public String getPlayerSuffix(Player p) {
        statement = dbConnection.getStatementDB();

        if (statement != null) {

            String playerName = p.getName();
            String query = "SELECT suffix FROM players_statistics WHERE nick = '" + playerName + "'";

            try {
                ResultSet suffixPlayerQueryResult = statement.executeQuery(query);

                suffixPlayerQueryResult.next();

                return suffixPlayerQueryResult.getString("suffix");

            } catch (Exception ignored) {
                return null;
            }

        } else {
            return null;
        }
    }


    public String getPlayerCondition(Player p){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = p.getName();
            String query = "SELECT conditionPlayer FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet conditionPlayerQueryResult = statement.executeQuery(query);

                conditionPlayerQueryResult.next();

                return conditionPlayerQueryResult.getString("conditionPlayer");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public String getPlayerView(Player p){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = p.getName();
            String query = "SELECT playerView FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet viewPlayerQueryResult = statement.executeQuery(query);

                viewPlayerQueryResult.next();

                return viewPlayerQueryResult.getString("playerView");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public String getPlayerGender(String playerName){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT sex FROM players_statistics WHERE nick = '"+playerName+"'";

            try{
                ResultSet genderPlayerQueryResult = statement.executeQuery(query);

                genderPlayerQueryResult.next();

                return genderPlayerQueryResult.getString("sex");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public String getServerDayOfWeek(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT dayOfWeek FROM server_info";

            try{
                ResultSet dayOfWeekQueryResult = statement.executeQuery(query);

                dayOfWeekQueryResult.next();

                return dayOfWeekQueryResult.getString("dayOfWeek");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public int getServerMonthValue(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT month FROM server_info";

            try{
                ResultSet monthQueryResult = statement.executeQuery(query);

                monthQueryResult.next();

                return monthQueryResult.getInt("month");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getServerYear(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT year FROM server_info";

            try{
                ResultSet yearQueryResult = statement.executeQuery(query);

                yearQueryResult.next();

                return yearQueryResult.getInt("year");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getServerTemperature(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT temperature FROM server_info";

            try{
                ResultSet temperatureQueryResult = statement.executeQuery(query);

                temperatureQueryResult.next();

                return temperatureQueryResult.getInt("temperature");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public String getServerWeather(){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "SELECT weather FROM server_info";

            try{
                ResultSet weatherQueryResult = statement.executeQuery(query);

                weatherQueryResult.next();

                return weatherQueryResult.getString("weather");

            }catch (Exception ignored){
                return null;
            }

        }else{
            return null;
        }
    }

    public int getPlayerBalance(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT balance FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet balanceQueryResult = statement.executeQuery(query);

                balanceQueryResult.next();

                return balanceQueryResult.getInt("balance");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public boolean updatePlayerDescription(String playerName, String description){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "UPDATE players_statistics SET descPlayer='" + description + "' WHERE nick = '" + playerName + "'";

            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean updatePlayerView(String playerName, String view){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String query = "UPDATE players_statistics SET playerView='" + view + "' WHERE nick = '" + playerName + "'";

            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean updatePlayerUsertag(Player player, String usertag){

        statement = dbConnection.getStatementDB();
        if(statement != null){
            String playerName = player.getName();

            String query = "UPDATE players_statistics SET prefix='" + usertag + "' WHERE nick = '" + playerName + "'";


            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean updatePlayerBalance(Player player, int amount){

        statement = dbConnection.getStatementDB();
        if(statement != null){
            String playerName = player.getName();

            String query = "UPDATE players_statistics SET balance='" + amount + "' WHERE nick = '" + playerName + "'";


            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public boolean updateServerDayOfWeek(String newDayOfWeek){

        statement = dbConnection.getStatementDB();
        if(statement != null){

            String query = "UPDATE server_info SET dayOfWeek='" + newDayOfWeek + "'";


            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }
    public boolean updateServerDate(String newDayOfWeek, int monthValue, int newYear ){

        statement = dbConnection.getStatementDB();
        if(statement != null){

            String query = "UPDATE server_info SET dayOfWeek='" + newDayOfWeek + "'";


            try{
                int result = statement.executeUpdate(query);

                return result > 0;

            }catch (Exception ignored){
                return false;
            }

        }else{
            return false;
        }
    }

    public void updateServerTemperature(int newTemperature){

        statement = dbConnection.getStatementDB();
        if(statement != null){

            String query = "UPDATE server_info SET temperature='" + newTemperature + "'";


            try{
                int result = statement.executeUpdate(query);

            }catch (Exception ignored){
            }

        }else{
        }
    }
    public void updateServerWeather(String newWeather){

        statement = dbConnection.getStatementDB();
        if(statement != null){

            String query = "UPDATE server_info SET weather='" + newWeather + "'";

            try{
                int result = statement.executeUpdate(query);

            }catch (Exception ignored){
            }

        }else{
        }
    }


}
