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

import java.time.LocalTime;
import java.util.*;

import static pl.dev.httyd.httydplugins.CreateCharacterCard.newPlayerStatistics;

public class PlayerListener implements Listener {

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

    DBExecute dbExecute = new DBExecute();
    CreatePlayerMenu createPlayerMenu = new CreatePlayerMenu();
    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();

    HttydPlugins instance;

    public PlayerListener(HttydPlugins instance){
        this.instance = instance;
    }

    CreateCharacterCard createCharacterCard = new CreateCharacterCard();

    World world = Bukkit.getWorld("world");
    Location locationOfStart = new Location(world, -35., 89., 246.);
    ArrayList<Integer> stats = new ArrayList<>();


    private ChatColor convertColor(String colorMC){
        Map<String, ChatColor> colorsMap = new HashMap<>();
        colorsMap.put("&0", ChatColor.BLACK);
        colorsMap.put("&1", ChatColor.DARK_BLUE);
        colorsMap.put("&2", ChatColor.DARK_GREEN);
        colorsMap.put("&3", ChatColor.DARK_AQUA);
        colorsMap.put("&4", ChatColor.DARK_RED);
        colorsMap.put("&5", ChatColor.DARK_PURPLE);
        colorsMap.put("&6", ChatColor.GOLD);
        colorsMap.put("&7", ChatColor.GRAY);
        colorsMap.put("&8", ChatColor.DARK_GRAY);
        colorsMap.put("&9", ChatColor.BLUE);
        colorsMap.put("&a", ChatColor.GREEN);
        colorsMap.put("&b", ChatColor.AQUA);
        colorsMap.put("&c", ChatColor.RED);
        colorsMap.put("&d", ChatColor.LIGHT_PURPLE);
        colorsMap.put("&e", ChatColor.YELLOW);
        colorsMap.put("&f", ChatColor.WHITE);
        colorsMap.put("&l", ChatColor.BOLD);
        colorsMap.put("&n", ChatColor.UNDERLINE);
        colorsMap.put("&o", ChatColor.ITALIC);
        colorsMap.put("&r", ChatColor.RESET);
        colorsMap.put("&m", ChatColor.STRIKETHROUGH);
        colorsMap.put("&k", ChatColor.MAGIC);
        return colorsMap.get(colorMC);
    }

    private String getPlayerPrefixWithColor(String basicPrefix) {
        if (basicPrefix.contains("&")) {
            String[] playerPrefixList = basicPrefix.split("");
            StringBuilder convertedPrefix = new StringBuilder("");

            for (int i = 0; i < playerPrefixList.length; i++) {

                if (Objects.equals(playerPrefixList[i], "&")) {
                    String color = playerPrefixList[i] + playerPrefixList[i + 1];

                    ChatColor newColor = convertColor(color);

                    convertedPrefix.append(newColor);
                    i++;
                    continue;
                }

                convertedPrefix.append(playerPrefixList[i]);
            }

            return convertedPrefix.toString();

        } else {
            return basicPrefix;
        }
    }


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
            playerPrefix = getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
            playerUserTag = "null";

            playerClickedName = playerClicked.getName();
            playerClickedRank = apiPR.getPrimaryRank(playerClicked);
            playerClickedPrefix = getPlayerPrefixWithColor(apiPR.getPrefix(playerClickedRank));
            playerClickedUserTag = "null";

            try{
                playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
            }catch (Exception ignored){
            }
            try{
                playerClickedUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(playerClicked));
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

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Player player = (Player) e.getWhoClicked();

        if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.WHITE + "" + ChatColor.BOLD + playerClickedName)) {
            e.setCancelled(false);

            switch (e.getCurrentItem().getType()) {
                case BOOK_AND_QUILL:{
                    //opis
                    String playerDesc = dbExecute.getPlayerDesc(playerClicked);
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Opis] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerDesc);
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
                    String playerCondition = dbExecute.getPlayerCondition(playerClicked);
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Stan] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerCondition);
                    player.closeInventory();
                    break;
                }
                case SKULL_ITEM:{
                    //wizerunek
                    String playerView = dbExecute.getPlayerView(playerClicked);
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Wizerunek] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerView);
                    player.closeInventory();
                    break;
                }
                case LEASH:{
                    //zlap
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "**Zlapales " + playerClickedUserTag + ChatColor.YELLOW + "**");
                    for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
                        if (entity instanceof Player) {
                            Player p = (Player) entity;
                            p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "**" + playerUserTag + ChatColor.YELLOW + " zlapal " + playerClickedUserTag + ChatColor.YELLOW + "**");
                        }
                    }
                    playerClicked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 100, false, false));
                    player.closeInventory();
                    break;
                }
                case BED:{
                    //obudz
                    player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + "Obudzono");
                    playerClicked.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + "Obudz sie!");
                    player.closeInventory();
                    break;
                }
                case STAINED_GLASS_PANE:{
                    player.closeInventory();
                    break;
                }
                default:{
                    player.closeInventory();
                    break;
                }

            }

        }else if (e.getInventory().getTitle().equalsIgnoreCase(ChatColor.WHITE + "" + ChatColor.BOLD + "MOJE MENU")){
            e.setCancelled(false);

            switch (e.getCurrentItem().getType()) {
                default:{
                    player.closeInventory();
                    break;
                }
            }


        }else if (e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.RED + "PODAJ PLEC POSTACI")) {
            e.setCancelled(false);

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
            e.setCancelled(false);

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
            e.setCancelled(false);

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

        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        PowerRanksAPI apiPR = PowerRanks.getInstance().loadAPI();
        PowerRanksExtensions powerRanksExtensions = new PowerRanksExtensions();

        String msg = getPlayerPrefixWithColor(e.getMessage());
        String playerName = player.getName();
        String playerRank = apiPR.getPrimaryRank(player);
        String playerPrefix = getPlayerPrefixWithColor(apiPR.getPrefix(playerRank));
        String playerUserTag = "";
        try{
            playerUserTag = getPlayerPrefixWithColor(powerRanksExtensions.getUserTaq(player));
        }catch (Exception ignored){
        }

        if(haveBrackets(msg)){
            msg = msg.replace("(", "");
            msg = msg.replace(")", "");
            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
            for(Entity entity : player.getNearbyEntities(12,12,12)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
                }
            }
        }else if(haveNoAction(msg)){
            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.RED + msg);
            for(Entity entity : player.getNearbyEntities(12,12,12)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.RED + msg);
                }
            }
        }else if(haveYesAction(msg)){
            player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.DARK_GREEN + msg);
            for(Entity entity : player.getNearbyEntities(12,12,12)) {
                if (entity instanceof Player) {
                    Player p = (Player) entity;
                    p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.DARK_GREEN + msg);
                }
            }
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
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + coloredMessage);
                for(Entity entity : player.getNearbyEntities(24,24,24)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + coloredMessage);
                    }
                }
            }else{
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + coloredMessage);
                for(Entity entity : player.getNearbyEntities(12,12,12)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + coloredMessage);
                    }
                }
            }


        }else{

            if(haveExclamations(msg)) {
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + ChatColor.GRAY + msg);
                for(Entity entity : player.getNearbyEntities(24,24,24)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + ChatColor.GRAY + msg);
                    }
                }
            }else{
                player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GRAY + msg);
                for(Entity entity : player.getNearbyEntities(12,12,12)) {
                    if (entity instanceof Player) {
                        Player p = (Player) entity;
                        p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GRAY + msg);
                    }
                }
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
        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Player player = event.getPlayer();

        for(Entity entity : player.getNearbyEntities(12,12,12)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "[+] " + ChatColor.GRAY + player.getName());
            }
        }
    }

    @EventHandler
    public void onPlayerJoinSetScoreboard(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        scoreboardInfo.updateScoreboard(player);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());


        Player player = event.getPlayer();

        for(Entity entity : player.getNearbyEntities(12,12,12)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[-] " + ChatColor.GRAY + player.getName());
            }
        }

    }


}
