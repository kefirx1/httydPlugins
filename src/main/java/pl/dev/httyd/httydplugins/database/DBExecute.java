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

    public int getPlayerHerbology(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT herbology FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("herbology");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerHerbologyTime(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT herbologyTime FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("herbologyTime");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerHerbologyCrops(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT herbologyCrops FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("herbologyCrops");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBlacksmithing(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT blacksmithing FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("blacksmithing");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBlacksmithingAppearance(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT blacksmithingAppearance FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("blacksmithingAppearance");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBlacksmithingQuality(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT blacksmithingQuality FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("blacksmithingQuality");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBlacksmithingDurability(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT blacksmithingDurability FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("blacksmithingDurability");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerAttack(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT attack FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("attack");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerAttackStrength(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT attackStrength FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("attackStrength");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerAttackSpeed(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT attackSpeed FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("attackSpeed");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerAttackStamina(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT attackStamina FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("attackStamina");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerFarming(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT farming FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("farming");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerFarmingTime(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT farmingTime FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("farmingTime");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerFarmingCrops(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT farmingCrops FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("farmingCrops");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerFarmingAnimals(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT farmingAnimals FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("farmingAnimals");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBrain(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT brain FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("brain");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBrainControl(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT brainControl FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("brainControl");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerBrainPatience(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT brainPatience FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("brainPatience");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerDragons(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT dragons FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("dragons");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerDragonsDomestication(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT dragonsDomestication FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("dragonsDomestication");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerDragonsDuels(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT dragonsDuels FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("dragonsDuels");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerDragonsFlying(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT dragonsFlying FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("dragonsFlying");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }

    public int getPlayerDragonsCare(Player player){
        statement = dbConnection.getStatementDB();

        if(statement != null){

            String playerName = player.getName();

            String query = "SELECT dragonsCare FROM players_statistics WHERE nick = '" + playerName + "'";

            try{
                ResultSet result = statement.executeQuery(query);

                result.next();
                return result.getInt("dragonsCare");

            }catch (Exception ignored){
                return 0;
            }

        }else{
            return 0;
        }
    }


    public boolean updatePlayerHerbology(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET herbology='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerHerbologyTime(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET herbologyTime='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerHerbologyCrops(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET herbologyCrops='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBlacksmithing(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET blacksmithing='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBlacksmithingAppearance(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET blacksmithingAppearance ='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBlacksmithingQuality(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET blacksmithingQuality='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBlacksmithingDurability(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET blacksmithingDurability='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerAttack(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET attack='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerAttackStrength(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET attackStrength='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerAttackSpeed(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET attackSpeed='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerAttackStamina(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET attackStamina='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerFarming(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET farming='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerFarmingTime(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET farmingTime='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerFarmingCrops(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET farmingCrops='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerFarmingAnimals(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET farmingAnimals='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBrain(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET brain='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBrainControl(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET brainControl='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerBrainPatience(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET brainPatience='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerDragons(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET dragons='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerDragonsDomestication(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET dragonsDomestication='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerDragonsDuels(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET dragonsDuels='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerDragonsFlying(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET dragonsFlying='" + value + "' WHERE nick = '" + playerName + "'";

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

    public boolean updatePlayerDragonsCare(Player player, int value){
        statement = dbConnection.getStatementDB();
        String playerName = player.getName();
        if(statement != null){

            String query = "UPDATE players_statistics SET dragonsCare='" + value + "' WHERE nick = '" + playerName + "'";

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

            String query = "UPDATE server_info SET dayOfWeek='" + newDayOfWeek + "'," + " month='" + monthValue + "'," + "year='" + newYear + "'";


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
