package pl.dev.httyd.httydplugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.dev.httyd.httydplugins.database.DBExecute;

import java.util.Objects;

public class CreatePlayerMenu {

    DBExecute dbExecute = new DBExecute();

    public void showGUI(Player player, String playerClickedName){

        Inventory menuPlayer = Bukkit.getServer().createInventory(null, 45, ChatColor.WHITE + "" + ChatColor.BOLD + playerClickedName);

        if(Objects.equals(player.getName(), playerClickedName)){
            menuPlayer = Bukkit.getServer().createInventory(null, 45, ChatColor.WHITE + "" + ChatColor.BOLD + "MOJE MENU");
        }

        ItemStack descPlayer = new ItemStack(Material.BOOK_AND_QUILL);
        ItemStack viewPlayer = new ItemStack(Material.SKULL_ITEM);
        ItemStack genderPlayer = null;
        ItemStack onPlayer = new ItemStack(Material.SADDLE);
        ItemStack conditionPlayer = new ItemStack(Material.BONE);
        ItemStack catchPlayer = new ItemStack(Material.LEASH);
        ItemStack wakeUpPlayer = new ItemStack(Material.BED);

        switch (dbExecute.getPlayerGender(playerClickedName)){
            case "mezczyzna":{
                genderPlayer = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 11);
                break;
            }
            case "kobieta":{
                genderPlayer = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 6);
                break;
            }
            case "inna":{
                genderPlayer = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
                break;
            }
        }

        ItemMeta descPlayerMeta = descPlayer.getItemMeta();
        descPlayerMeta.setDisplayName(ChatColor.GOLD + "Opis postaci");
        descPlayer.setItemMeta(descPlayerMeta);

        ItemMeta onPlayerMeta = onPlayer.getItemMeta();
        onPlayerMeta.setDisplayName(ChatColor.GOLD + "Wejdz");
        onPlayer.setItemMeta(onPlayerMeta);

        ItemMeta genderPlayerMeta = genderPlayer.getItemMeta();
        genderPlayerMeta.setDisplayName(ChatColor.GOLD + "-");
        genderPlayer.setItemMeta(genderPlayerMeta);

        ItemMeta viewPlayerMeta = viewPlayer.getItemMeta();
        viewPlayerMeta.setDisplayName(ChatColor.GOLD + "Wizerunek postaci");
        viewPlayer.setItemMeta(viewPlayerMeta);

        ItemMeta conditionPlayerMeta = conditionPlayer.getItemMeta();
        conditionPlayerMeta.setDisplayName(ChatColor.GOLD + "Stan gracza");
        conditionPlayer.setItemMeta(conditionPlayerMeta);

        ItemMeta catchPlayerMeta = catchPlayer.getItemMeta();
        catchPlayerMeta.setDisplayName(ChatColor.GOLD + "Zlap gracza");
        catchPlayer.setItemMeta(catchPlayerMeta);

        ItemMeta wakeUpPlayerMeta = wakeUpPlayer.getItemMeta();
        wakeUpPlayerMeta.setDisplayName(ChatColor.GOLD + "Obudz gracza");
        wakeUpPlayer.setItemMeta(wakeUpPlayerMeta);

        menuPlayer.setItem(12, descPlayer);
        menuPlayer.setItem(22, genderPlayer);
        menuPlayer.setItem(14, viewPlayer);
        menuPlayer.setItem(23, onPlayer);
        menuPlayer.setItem(31, conditionPlayer);
        menuPlayer.setItem(21, catchPlayer);
        menuPlayer.setItem(25, wakeUpPlayer);

        player.openInventory(menuPlayer);


    }

}
