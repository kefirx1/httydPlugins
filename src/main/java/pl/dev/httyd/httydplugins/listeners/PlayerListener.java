package pl.dev.httyd.httydplugins.listeners;

import nl.svenar.PowerRanks.PowerRanks;
import nl.svenar.PowerRanks.api.PowerRanksAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.dev.httyd.httydplugins.*;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.util.*;

import static pl.dev.httyd.httydplugins.CreateCharacterCard.newPlayerStatistics;

public class PlayerListener implements Listener {

    HttydPlugins instance;
    PowerRanksAPI apiPR;
    PowerRanksExtensions powerRanksExtensions;
    String playerName;
    String playerRank;
    String playerPrefix;
    String playerUserTag;
    String playerClickedName;
    String playerClickedRank;
    String playerClickedPrefix;
    String playerClickedUserTag;
    Player playerClicked;

    CreateCharacterCard createCharacterCard = new CreateCharacterCard(instance);
    DBExecute dbExecute = new DBExecute();
    CreatePlayerMenu createPlayerMenu = new CreatePlayerMenu();
    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();
    Converter converter = new Converter();

    public PlayerListener(HttydPlugins instance){
        this.instance = instance;
    }



    World world = Bukkit.getWorld("world");
    Location locationOfStart = new Location(world, 29996, 4, 30000);
    ArrayList<Integer> stats = new ArrayList<>();

    private boolean isPassenger(Player player){
        List<Entity> passengersList = player.getPassengers();
        for(Entity passenger: passengersList){
            if(passenger == playerClicked){
                return true;
            }
        }
        return false;
    }


    @EventHandler
    public void onRightClick(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();


        apiPR = PowerRanks.getInstance().loadAPI();
        powerRanksExtensions = new PowerRanksExtensions();

        if (e.getRightClicked() instanceof Player && e.getHand() == EquipmentSlot.HAND) {
            String nameClickedPlayer = e.getRightClicked().getName();
            playerClicked = Bukkit.getPlayerExact(nameClickedPlayer);

            playerName = player.getName();
            playerRank = apiPR.getPrimaryRank(player);
            playerPrefix = converter.getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
            playerUserTag = "null";

            playerClickedName = playerClicked.getName();
            playerClickedRank = apiPR.getPrimaryRank(playerClicked);
            playerClickedPrefix = converter.getPlayerPrefixWithColor(apiPR.getPrefix(playerClickedRank));
            playerClickedUserTag = "null";

            try{
                playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }
            try{
                playerClickedUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(playerClicked));
            }catch (Exception ignored){
            }

            if(isPassenger(player)){
                player.removePassenger(playerClicked);
            }

            createPlayerMenu.showGUI(player, playerClickedName);
        }
    }

    @EventHandler
    public void clickMenuEvent(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.WHITE + "" + ChatColor.BOLD + playerClickedName)) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case BOOK_AND_QUILL:{
                    //opis
                    String playerDesc;
                    try{
                        playerDesc = dbExecute.getPlayerDesc(playerClicked);
                    }catch (Exception ex){
                        playerDesc = "Blad";
                    }
                    MessagesDataClass.pLMenuDescription(player, playerClickedUserTag, playerDesc);
                    player.closeInventory();
                    break;
                }
                case SADDLE:{
                    //jazda
                    player.closeInventory();
                    playerClicked.addPassenger(player);
                    break;
                }
                case BONE:{
                    //stan
                    String playerCondition;
                    try{
                        playerCondition = dbExecute.getPlayerCondition(playerClicked);
                    }catch (Exception ex){
                        playerCondition = "Blad";
                    }
                    MessagesDataClass.pLMenuCondition(player, playerClickedUserTag, playerCondition);
                    player.closeInventory();
                    break;
                }
                case SKULL_ITEM:{
                    //wizerunek
                    String playerView;
                    try{
                        playerView = dbExecute.getPlayerView(playerClicked);
                    }catch (Exception ex){
                        playerView = "Blad";
                    }
                    MessagesDataClass.pLMenuView(player, playerClickedUserTag, playerView);
                    player.closeInventory();
                    break;
                }
                case LEASH:{
                    //zlap
                    MessagesDataClass.pLMenuCatchPlayer(player, playerClickedUserTag, playerUserTag);
                    playerClicked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 100, false, false));
                    player.closeInventory();
                    break;
                }
                case BED:{
                    //obudz
                    MessagesDataClass.pLMenuWakeUpPlayer(player, playerClicked);
                    player.closeInventory();
                    break;
                }
                default:{
                    player.closeInventory();
                    break;
                }

            }

        }else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.WHITE + "" + ChatColor.BOLD + "MOJE MENU")){
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                default:{
                    player.closeInventory();
                    break;
                }
            }


        }else if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "PODAJ PLEC POSTACI")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case RED_GLAZED_TERRACOTTA:
                    player.closeInventory();
                    newPlayerStatistics.sex = "mezczyzna";
                    break;
                case PINK_GLAZED_TERRACOTTA:
                    player.closeInventory();
                    newPlayerStatistics.sex = "kobieta";
                    break;
                case GREEN_GLAZED_TERRACOTTA:
                    player.closeInventory();
                    newPlayerStatistics.sex = "inna";
                    break;
            }

            createCharacterCard.guiIslandStep(player);

        } else if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "  WITAJ " + newPlayerStatistics.prefix + " SKAD POCHODZISZ?  ")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case DIAMOND_AXE:
                    player.closeInventory();
                    newPlayerStatistics.island = "Wandale";
                    break;
                case DIAMOND_SWORD:
                    player.closeInventory();
                    newPlayerStatistics.island = "Lupiezcy";
                    break;
            }

            createCharacterCard.guiQuestionStep1(player);

        } else if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "" + ChatColor.BOLD + "------1-----")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case DIRT: {
                    player.closeInventory();
                    for (int i = 0; i<23; i++){
                        stats.add(10);
                    }
                    break;
                }
                case STONE: {
                    player.closeInventory();
                    for (int i = 0; i<23; i++){
                        stats.add(-10);
                    }
                    break;
                }
                case SAND: {
                    player.closeInventory();
                    for (int i = 0; i<23; i++){
                        stats.add(0);
                    }
                    break;
                }
            }

            //tu będzie odnosnik do innych pytań

            createCharacterCard.completedCard(player, stats, instance);

        }
    }

    private boolean haveBrackets(String msg){
        return msg.contains("(") || msg.contains(")");
    }
    private boolean haveNoAction(String msg){
        return Objects.equals(msg.toLowerCase(Locale.ROOT), "**nie bylo**") || Objects.equals(msg.toLowerCase(Locale.ROOT), "**nie było**");
    }
    private boolean haveYesAction(String msg){
        return Objects.equals(msg.toLowerCase(Locale.ROOT), "**bylo**") || Objects.equals(msg.toLowerCase(Locale.ROOT), "**było**");
    }
    private boolean haveStars(String msg){
        return msg.contains("*");
    }

    private boolean haveExclamations(String msg){
        return msg.contains("!!");
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        e.setCancelled(true);

        PowerRanksAPI apiPR = PowerRanks.getInstance().loadAPI();
        PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

        String msg = converter.getPlayerPrefixWithColor(e.getMessage());
        String playerName = player.getName();
        String playerRank = apiPR.getPrimaryRank(player);
        String playerPrefix = converter.getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
        String playerUserTag = "";
        try{
            playerUserTag = converter.getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
        }catch (Exception ignored){
        }

        if(haveBrackets(msg)){
            msg = msg.replace("(", "");
            msg = msg.replace(")", "");
            MessagesDataClass.pLChatOOC(player, playerName, msg);
        }else if(haveNoAction(msg)){
            MessagesDataClass.pLChatNoAction(player, playerName, playerPrefix, playerUserTag, msg);
        }else if(haveYesAction(msg)){
            MessagesDataClass.pLChatYesAction(player, playerName, playerPrefix, playerUserTag, msg);
        }else if(haveStars(msg)){
            msg = ChatColor.GRAY + msg;
            String[] messageList = msg.split("");
            StringBuilder coloredMessage = new StringBuilder("");
            short tempDouble = 0;
            short tempOne = 0;

            for(int i = 0; i<messageList.length; i++){
                if(i<messageList.length-1){
                    if(Objects.equals(messageList[i], "*") && Objects.equals(messageList[i + 1], "*")){
                        if(tempDouble == 0){
                            coloredMessage.append(ChatColor.GOLD).append(messageList[i]).append(messageList[i+1]);
                            tempDouble = 1;
                        }else{
                            coloredMessage.append(messageList[i]).append(messageList[i+1]).append(ChatColor.GRAY);
                            tempDouble = 0;
                        }
                        i++;
                        continue;
                    }
                }
                if(Objects.equals(messageList[i], "*")){
                    if(tempOne == 0){
                        coloredMessage.append(ChatColor.YELLOW).append(messageList[i]);
                        tempOne = 1;
                    }else{
                        coloredMessage.append(messageList[i]).append(ChatColor.GRAY);
                        tempOne = 0;
                    }
                    continue;
                }

                coloredMessage.append(messageList[i]);

            }

            if(haveExclamations(coloredMessage.toString())){
                MessagesDataClass.pLChatStarsExclamations(player, playerName, playerPrefix, playerUserTag, coloredMessage);
            }else{
                MessagesDataClass.pLChatStars(player, playerName, playerPrefix, playerUserTag, coloredMessage);
            }

        }else{

            if(haveExclamations(msg)) {
                MessagesDataClass.pLChatICExclamations(player, playerName, playerPrefix, playerUserTag, msg);
            }else{
                MessagesDataClass.pLChatIC(player, playerName, playerPrefix, playerUserTag, msg);
            }
        }

    }



    @EventHandler
    public void onPlayerJoinNewPlayer(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
        PermissionAttachment attachment = p.addAttachment(instance);
        perms.put(p.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(p.getUniqueId());

        pperms.setPermission("create.setname", true);

        if(dbExecute.getIsNewPlayer(p)){
            p.teleport(locationOfStart);
            createCharacterCard.personalInfoQuestion(p);
        }else{
            pperms.setPermission("create.setname", false);
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        MessagesDataClass.pLOnJoinPlayer(player);
    }

    @EventHandler
    public void onPlayerJoinSetScoreboard(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        scoreboardInfo.updateScoreboard(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        MessagesDataClass.pLOnQuitPlayer(player);
    }


}
