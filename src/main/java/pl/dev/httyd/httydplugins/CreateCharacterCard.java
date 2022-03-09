package pl.dev.httyd.httydplugins;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.PermissionAttachment;
import pl.dev.httyd.httydplugins.data.PlayerStatistics;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CreateCharacterCard {

    public CreateCharacterCard(HttydPlugins instance){
        this.instance = instance;
    }

    public CreateCharacterCard(){
    }

    HttydPlugins instance;

    public static PlayerStatistics newPlayerStatistics = new PlayerStatistics("", "", "-", 0, "", "", 0, "-", "Zdrowy", "-" ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    World world = Bukkit.getWorld("world");
    Location berkCords = new Location(world, -77, 97, 217);
    Location outcastIslandCords = new Location(world, 113, 96, 134);
    ScoreboardInfo scoreboardInfo = new ScoreboardInfo();

    DBExecute dbExecute = new DBExecute();


    public void personalInfoQuestion(Player player) {
        MessagesDataClass.cPMInnkeeper(player);
    }

    public void setStatistics(ArrayList<Integer> stats){
        newPlayerStatistics.herbology = stats.get(0);
        newPlayerStatistics.herbologyTime = stats.get(1);
        newPlayerStatistics.herbologyCrops = stats.get(2);
        newPlayerStatistics.blacksmithing = stats.get(3);
        newPlayerStatistics.blacksmithingAppearance = stats.get(4);
        newPlayerStatistics.blacksmithingQuality = stats.get(5);
        newPlayerStatistics.blacksmithingDurability = stats.get(6);
        newPlayerStatistics.attack = stats.get(7);
        newPlayerStatistics.attackStrength = stats.get(8);
        newPlayerStatistics.attackSpeed = stats.get(9);
        newPlayerStatistics.attackStamina = stats.get(10);
        newPlayerStatistics.farming = stats.get(11);
        newPlayerStatistics.farmingTime = stats.get(12);
        newPlayerStatistics.farmingCrops = stats.get(13);
        newPlayerStatistics.farmingAnimals = stats.get(14);
        newPlayerStatistics.brain = stats.get(15);
        newPlayerStatistics.brainControl = stats.get(16);
        newPlayerStatistics.brainPatience = stats.get(17);
        newPlayerStatistics.dragons = stats.get(18);
        newPlayerStatistics.dragonsDomestication = stats.get(19);
        newPlayerStatistics.dragonsDuels = stats.get(20);
        newPlayerStatistics.dragonsFlying = stats.get(21);
        newPlayerStatistics.dragonsCare = stats.get(22);
    }

    public void completedCard(Player player, ArrayList<Integer> stats, HttydPlugins instance){
        setStatistics(stats);
        if(dbExecute.addNewCard()){
            HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
            PermissionAttachment attachment = player.addAttachment(instance);
            perms.put(player.getUniqueId(), attachment);
            PermissionAttachment pperms = perms.get(player.getUniqueId());

            pperms.setPermission("roleplay.narrator", true);
            player.performCommand("prefix " + player.getName() + " " + newPlayerStatistics.prefix);
            pperms.setPermission("roleplay.narrator", false);
            teleportToIsland(player);
        }else{
            MessagesDataClass.cPMCharacterCardError(player);
        }
    }

    public void teleportToIsland(Player player){
        LocalTime time = LocalTime.now();
        LocalTime timeC = LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());

        switch (newPlayerStatistics.island){
            case "Wandale":{
                player.teleport(berkCords);
                MessagesDataClass.cPMWandale(player);
                break;
            }
            case "Lupiezcy":{
                player.teleport(outcastIslandCords);
                MessagesDataClass.cPMLupiezcy(player);
                break;
            }
        }
        scoreboardInfo.updateScoreboard(player);
        denySetnamePer(player);
    }

    private void denySetnamePer(Player player){
        HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
        PermissionAttachment attachment = player.addAttachment(instance);
        perms.put(player.getUniqueId(), attachment);
        PermissionAttachment pperms = perms.get(player.getUniqueId());

        pperms.setPermission("create.setname", true);
    }

    public void guiSexStep(Player p){

        Inventory guiSex = Bukkit.createInventory(p, 27, ChatColor.RED + "PODAJ PLEC POSTACI");

        ItemStack male = new ItemStack(Material.RED_GLAZED_TERRACOTTA);
        ItemStack female = new ItemStack(Material.PINK_GLAZED_TERRACOTTA);
        ItemStack other = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);

        ItemMeta male_meta = male.getItemMeta();
        male_meta.setDisplayName(ChatColor.RED + "MEZCZYZNA");
        male.setItemMeta(male_meta);
        ItemMeta female_meta = female.getItemMeta();
        female_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "KOBIETA");
        female.setItemMeta(female_meta);
        ItemMeta other_meta = other.getItemMeta();
        other_meta.setDisplayName(ChatColor.GREEN + "INNA");
        other.setItemMeta(other_meta);

        guiSex.setItem(11, male);
        guiSex.setItem(13, female);
        guiSex.setItem(15, other);

        p.openInventory(guiSex);

    }

    public void guiIslandStep(Player p){

        Inventory guiIsland = Bukkit.createInventory(p, 27, ChatColor.RED + "  WITAJ " + newPlayerStatistics.prefix + " SKAD POCHODZISZ?  ");

        ItemStack berk = new ItemStack(Material.DIAMOND_AXE);
        ItemStack outcastIsland = new ItemStack(Material.DIAMOND_SWORD);

        ItemMeta berk_meta = berk.getItemMeta();
        berk_meta.setDisplayName(ChatColor.GOLD + "WANDALE");
        berk.setItemMeta(berk_meta);
        ItemMeta outcastIsland_meta = outcastIsland.getItemMeta();
        outcastIsland_meta.setDisplayName(ChatColor.DARK_GRAY + "LUPIEZCY");
        outcastIsland.setItemMeta(outcastIsland_meta);

        guiIsland.setItem(12, berk);
        guiIsland.setItem(14, outcastIsland);

        p.openInventory(guiIsland);
    }

    public void guiQuestionStep1(Player p){

        Inventory guiQuestion1 = Bukkit.createInventory(p, 27, ChatColor.RED + "" + ChatColor.BOLD + "------1-----");

        ItemStack question = new ItemStack(Material.BOOK);
        ItemStack first = new ItemStack(Material.DIRT);
        ItemStack second = new ItemStack(Material.STONE);
        ItemStack third = new ItemStack(Material.SAND);

        ItemMeta question_meta = question.getItemMeta();
        question_meta.setDisplayName(ChatColor.GOLD + "[Karczmarz]: ");
        ArrayList<String> question_lore = new ArrayList<>();
        question_lore.add(ChatColor.GRAY + "Czy lubisz kefira?");
        question_meta.setLore(question_lore);
        question.setItemMeta(question_meta);

        ItemMeta first_meta = first.getItemMeta();
        first_meta.setDisplayName(ChatColor.RED + "TAK");
        first.setItemMeta(first_meta);
        ItemMeta second_meta = second.getItemMeta();
        second_meta.setDisplayName(ChatColor.BLUE + "NIE");
        second.setItemMeta(second_meta);
        ItemMeta third_meta = third.getItemMeta();
        third_meta.setDisplayName(ChatColor.GREEN + "NIE WIEM");
        third.setItemMeta(third_meta);

        guiQuestion1.setItem(1, question);
        guiQuestion1.setItem(11, first);
        guiQuestion1.setItem(13, second);
        guiQuestion1.setItem(15, third);

        p.openInventory(guiQuestion1);
    }


}
