package pl.dev.httyd.httydplugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MessagesDataClass {

    static LocalTime time = LocalTime.now();
    static LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

    private static void sendToAnotherPlayers2(Player player, String message, List<Player> playersFirstStep){
        for (Entity entity : player.getNearbyEntities(2, 2, 2)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
                playersFirstStep.add(p);
            }
        }
    }
    private static void sendToAnotherPlayers3(Player player, String message){
        for(Entity entity : player.getNearbyEntities(3,3,3)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
            }
        }
    }
    private static void sendToAnotherPlayers6(Player player, String message, List<Player> playersFirstStep){
        for (Entity entity : player.getNearbyEntities(6, 6, 6)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                if(!playersFirstStep.contains(p)){
                    p.sendMessage(message);
                }
            }
        }
    }
    private static void sendToAnotherPlayers12(Player player, String message){
        for (Entity entity : player.getNearbyEntities(12, 12, 12)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
            }
        }
    }
    private static void sendToAnotherPlayers24(Player player, String message){
        for(Entity entity : player.getNearbyEntities(24,24,24)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
            }
        }
    }
    private static void sendToAnotherPlayers25(Player player, String message){
        for (Entity entity : player.getNearbyEntities(25, 25, 25)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
            }
        }
    }
    private static void sendToAnotherPlayers50(Player player, String message){
        for (Entity entity : player.getNearbyEntities(50, 50, 50)) {
            if (entity instanceof Player) {
                Player p = (Player) entity;
                p.sendMessage(message);
            }
        }
    }


    public static void cPMInnkeeper(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[Karczmarz]: " + ChatColor.RESET + ChatColor.GRAY + "Witaj przybyszu, jak sie nazywasz?");
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED +"[POMOC]: Uzyj /setname [Imie] [Nazwisko] [wiek] do wybrnia imienia i nazwiska swojej postaci - /setname Martin Wilson 30");
    }

    public static void cPMCharacterCardError(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED + "[LOG]: Niestety nie udalo sie dodac karty postaci, popros moderatora na /helpop o pomoc :c");
    }

    public static void cPMWandale(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "[WANDALE] WITAMY!");
    }

    public static void cPMLupiezcy(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "[LUPIEZCY] WITAMY!");
    }

    public static void pLMenuDescription(Player player, String playerClickedUserTag, String playerDesc){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Opis] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerDesc);
    }

    public static void pLMenuCondition(Player player, String playerClickedUserTag, String playerCondition){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Stan] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerCondition);
    }

    public static void pLMenuView(Player player, String playerClickedUserTag, String playerView){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Wizerunek] " + playerClickedUserTag + ChatColor.GOLD + ": " + playerView);
    }

    public static void pLMenuCatchPlayer(Player player, String playerClickedUserTag, String playerUserTag){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "**Zlapales " + playerClickedUserTag + ChatColor.YELLOW + "**");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "**" + playerUserTag + ChatColor.YELLOW + " zlapal " + playerClickedUserTag + ChatColor.YELLOW + "**";
        sendToAnotherPlayers12(player, message);
    }

    public static void pLMenuWakeUpPlayer(Player player, Player playerClicked){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + "Obudzono");
        playerClicked.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + "Obudz sie!");
    }

    public static void pLChatOOC(Player player, String playerName, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
        String message = ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")";
        sendToAnotherPlayers12(player, message);
    }

    public static void pLChatNoAction(Player player, String playerName, String playerPrefix, String playerUserTag, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.RED + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.RED + msg;
        sendToAnotherPlayers12(player, message);
    }

    public static void pLChatYesAction(Player player, String playerName, String playerPrefix, String playerUserTag, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.DARK_GREEN + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.BOLD + "" +  ChatColor.DARK_GREEN + msg;
        sendToAnotherPlayers12(player, message);
    }

    public static void pLChatStarsExclamations(Player player, String playerName, String playerPrefix, String playerUserTag, StringBuilder coloredMessage){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + coloredMessage);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + coloredMessage;
        sendToAnotherPlayers24(player, message);
    }

    public static void pLChatStars(Player player, String playerName, String playerPrefix, String playerUserTag, StringBuilder coloredMessage){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + coloredMessage);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + coloredMessage;
        sendToAnotherPlayers12(player, message);
    }

    public static void pLChatICExclamations(Player player, String playerName, String playerPrefix, String playerUserTag, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + ChatColor.GRAY + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": [!!] " + ChatColor.GRAY + msg;
        sendToAnotherPlayers24(player, message);
    }

    public static void pLChatIC(Player player, String playerName, String playerPrefix, String playerUserTag, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GRAY + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " +  playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GRAY + msg;
        sendToAnotherPlayers12(player, message);
    }

    public static void pLOnJoinPlayer(Player player){
        String message = ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "[+] " + ChatColor.GRAY + player.getName();
        sendToAnotherPlayers12(player, message);
    }

    public static void pLOnQuitPlayer(Player player){
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[-] " + ChatColor.GRAY + player.getName();
        sendToAnotherPlayers12(player, message);
    }

    public static void vCPlayerView(Player player, String playerUserTag, String playerView){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Wizerunek] " + playerUserTag + ChatColor.GOLD + ": " + playerView);
    }

    public static void vCPlayerViewChange(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_GREEN + "Wizerunek postaci zostal zmieniony!");
    }

    public static void vCPlayerViewError(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "Wizerunek postaci nie mogl byc zmieniony");
    }

    public static void tCWin(Player player, String playerUserTag, int value){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_GREEN + ChatColor.BOLD + " Udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_GREEN + ChatColor.BOLD + " Udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]";
        sendToAnotherPlayers12(player, message);
    }

    public static void tCFailed(Player player, String playerUserTag, int value){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_RED + ChatColor.BOLD + " Nie udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]");
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ":" + ChatColor.DARK_RED + ChatColor.BOLD + " Nie udalo sie! " + ChatColor.YELLOW + "[" + value + "% szansy na powodzenia]";
        sendToAnotherPlayers12(player, message);
    }

    public static void sCWhisper(Player player, String playerPrefix, String playerUserTag, String playerName, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ChatColor.BOLD + "" + ChatColor.DARK_GRAY + " [szepcze]" + ChatColor.RESET + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + msg );
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ChatColor.BOLD + "" + ChatColor.DARK_GRAY + " [szepcze]" + ChatColor.RESET + ChatColor.DARK_GRAY + ": " + ChatColor.GRAY + msg;
        sendToAnotherPlayers3(player, message);
    }

    public static void sUCWhisper(Player player, String playerUserTag, String player2UserTag, String msg){
        List<Player> playersFirstStep = new ArrayList<>();
        String message1 = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze ci do ucha** " + ChatColor.RESET + ChatColor.GRAY + msg;
        String message2 = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "**" + playerUserTag + ChatColor.RESET + ChatColor.GOLD + " Szepcze  do ucha " + ChatColor.BOLD + player2UserTag + ChatColor.GOLD + "**";
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "*" + playerUserTag + ChatColor.RESET + ChatColor.YELLOW + " Szepczesz do ucha " + ChatColor.BOLD + player2UserTag + ChatColor.YELLOW + "* " + ChatColor.RESET + ChatColor.GRAY + msg);
        sendToAnotherPlayers2(player, message1, playersFirstStep);
        sendToAnotherPlayers6(player, message2, playersFirstStep);
    }

    public static void sCBalance(Player player, String playerUsertag, int playerBalance){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.GOLD + "W sakiewce posiadasz: " + playerBalance + " monet");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUsertag + " zaglada do swojej sakiewki**";
        sendToAnotherPlayers12(player, message);
    }

    public static void pCCorrect(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED + "Prefix zostal zmieniony pomyslenie");
    }

    public static void pCFailed(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.RED + "Blad - prefix nie mogl byc zmieniony");
    }

    public static void payCFailedU(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nie mozesz przekazac sam sobie pieniedzy!");
    }

    public static void payCZero(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nie mozesz zaplacic nic!");
    }

    public static void payCTooLittle(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Nie masz wystarczajacej ilosci monet!");
    }

    public static void payCFailed(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "" + ChatColor.BOLD + "Cos poszlo nie tak!");
    }

    public static void payCCorrect(Player player, String playerUserTag, String playerUserTag2, int amount){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Przekazales " + amount + " monet " + playerUserTag2 + ChatColor.YELLOW + "*" );
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag + ChatColor.GOLD + " przekazal " + amount + " monet " + playerUserTag2 + ChatColor.GOLD + "**";
        sendToAnotherPlayers3(player, message);
    }

    public static void oocCSend(Player player, String playerName, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[OOC] " + ChatColor.DARK_GRAY + playerName + ": (" + msg + ")";
        sendToAnotherPlayers12(player, message);
    }

    public static void narCSend(Player player, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[N]: " + ChatColor.AQUA + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[N]: " + ChatColor.AQUA + msg;
        sendToAnotherPlayers50(player, message);
    }

    public static void meCSend(Player player, String playerPrefix, String playerUserTag, String playerName, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.YELLOW + "*" + msg + "*");
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.YELLOW + "*" + msg + "*";
        sendToAnotherPlayers12(player, message);
    }

    public static void lNarCSend(Player player, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[L]: " + ChatColor.DARK_AQUA + msg);
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_PURPLE + "[L]: " + ChatColor.DARK_AQUA + msg;
        sendToAnotherPlayers25(player, message);
    }

    public static void gNarCSend(Player player, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[G]: " + ChatColor.YELLOW + msg);
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.BOLD + "" + ChatColor.DARK_RED + "[G]: " + ChatColor.YELLOW + msg);
        }
    }

    public static void dCCloseDoor(Player player, String playerUserTag){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales drzwi*");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " zamknal drzwi**";
        sendToAnotherPlayers12(player, message);
    }

    public static void dCOpenDoor(Player player, String playerUserTag){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Otworzyles drzwi*");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " otworzyl drzwi**";
        sendToAnotherPlayers12(player, message);
    }

    public static void dCCloseGate1(Player player, String playerUserTag){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Zamknales brame*");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "** " + playerUserTag  + ChatColor.GOLD + " zamknal brame*";
        sendToAnotherPlayers12(player, message);
    }

    public static void dCOpenGate1(Player player, String playerUserTag){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.YELLOW + "*Otworzyles brame*");
        String message = ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "**" + playerUserTag  + ChatColor.GOLD + " otworzyl brame**";
        sendToAnotherPlayers12(player, message);
    }

    public static void doCSend(Player player, String playerPrefix, String playerUserTag, String playerName, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GOLD + "**" + msg + "**");
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.GOLD + "**" + msg + "**";
        sendToAnotherPlayers12(player, message);
    }

    public static void diceCOne(Player player, String  playerUserTag, int value, int diceValue){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [k" + value + "]: " + ChatColor.GRAY + diceValue);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [k" + value + "]: " + ChatColor.GRAY + diceValue;
        sendToAnotherPlayers12(player, message);
    }

    public static void diceCMore(Player player, String  playerUserTag, int value, int number, StringBuilder diceValueString){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [" + number + "k" + value + "]: " + ChatColor.GRAY + diceValueString);
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerUserTag + " " + ChatColor.WHITE + player.getName() + ChatColor.DARK_GRAY + " [" + number + "k" + value + "]: " + ChatColor.GRAY + diceValueString;
        sendToAnotherPlayers12(player, message);
    }

    public static void descCShow(Player player, String playerUserTag, String playerDesc){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.GOLD + "[Opis] " + playerUserTag + ChatColor.GOLD + ": " + playerDesc);
    }

    public static void descCCorrect(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_GREEN + "Opis postaci zostal zmieniony!");
    }

    public static void descCFailed(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " + ChatColor.DARK_RED + "Opis postaci nie mogl byc zmieniony");
    }

    public static void bCSend(Player player, String msg){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Ogloszenie" + ChatColor.GOLD +"]: " + ChatColor.GREEN + msg);
        for (Player p: Bukkit.getOnlinePlayers()) {
            p.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.GOLD + "[" + ChatColor.DARK_RED + "Ogloszenie" + ChatColor.GOLD +"]: " + ChatColor.GREEN + msg);
        }
    }

    public static void wLWetting2(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.YELLOW + "" + ChatColor.BOLD + "**Na Twoich ubraniach zaczynaja pojawiac sie coraz to wieksze krople deszczu**");
    }

    public static void wLWetting4(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.YELLOW + "" + ChatColor.BOLD + "**Twoje ubrania sa przemoczone**");
    }

    public static void wLWetting6(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.YELLOW + "" + ChatColor.BOLD + "**Jestes calkowcie mokry od stop po glowe**");
    }

    public static void wLWetting8(Player player){
        player.sendMessage(ChatColor.WHITE + "[" + timeC + "] " +  ChatColor.YELLOW + "" + ChatColor.BOLD + "**Procz calkowitego przemoczenia, z kazda sekunda zaczyna Ci sie robic coraz to zimniej**");
    }

    public static void wLWetting9(Player player, String playerPrefix, String playerUserTag, String playerName){
        String message = ChatColor.WHITE + "[" + timeC + "] " + playerPrefix + " " + playerUserTag + " " + ChatColor.WHITE + playerName + ": " + ChatColor.YELLOW + "*Trzesie sie z zimna*";
        player.sendMessage(message);
        sendToAnotherPlayers12(player, message);
    }
}
