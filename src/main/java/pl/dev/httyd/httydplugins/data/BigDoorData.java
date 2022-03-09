package pl.dev.httyd.httydplugins.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class BigDoorData {

    World world = Bukkit.getWorld("world");

    Location bigDoorBlock1 = new Location(world, -38, 119, 192 );
    Location bigDoorBlock2 = new Location(world, -38, 120, 192 );
    Location bigDoorBlock3 = new Location(world, -38, 121, 192 );
    Location bigDoorBlock4 = new Location(world, -38, 122, 192 );
    Location bigDoorBlock5 = new Location(world, -38, 123, 192 );
    Location bigDoorBlock6 = new Location(world, -38, 124, 192 );
    Location bigDoorBlock7 = new Location(world, -38, 125, 192 );
    Location bigDoorBlock9 = new Location(world, -38, 126, 192 );
    Location bigDoorBlock10 = new Location(world, -38, 127, 192 );
    Location bigDoorBlock11 = new Location(world, -38, 128, 192 );
    Location bigDoorBlock12 = new Location(world, -38, 129, 192 );
    Location bigDoorBlock13 = new Location(world, -38, 130, 192 );
    Location bigDoorBlock14 = new Location(world, -38, 131, 192 );

    public void setToAir(){
        bigDoorBlock1.getBlock().setType(Material.AIR);
        bigDoorBlock2.getBlock().setType(Material.AIR);
        bigDoorBlock3.getBlock().setType(Material.AIR);
        bigDoorBlock4.getBlock().setType(Material.AIR);
        bigDoorBlock5.getBlock().setType(Material.AIR);
        bigDoorBlock6.getBlock().setType(Material.AIR);
        bigDoorBlock7.getBlock().setType(Material.AIR);
        bigDoorBlock9.getBlock().setType(Material.AIR);
        bigDoorBlock10.getBlock().setType(Material.AIR);
        bigDoorBlock11.getBlock().setType(Material.AIR);
        bigDoorBlock12.getBlock().setType(Material.AIR);
        bigDoorBlock13.getBlock().setType(Material.AIR);
        bigDoorBlock14.getBlock().setType(Material.AIR);
    }

    public void setToWood(){
        bigDoorBlock1.getBlock().setType(Material.WOOD);
        bigDoorBlock2.getBlock().setType(Material.WOOD);
        bigDoorBlock3.getBlock().setType(Material.WOOD);
        bigDoorBlock4.getBlock().setType(Material.WOOD);
        bigDoorBlock5.getBlock().setType(Material.WOOD);
        bigDoorBlock6.getBlock().setType(Material.WOOD);
        bigDoorBlock7.getBlock().setType(Material.WOOD);
        bigDoorBlock9.getBlock().setType(Material.WOOD);
        bigDoorBlock10.getBlock().setType(Material.WOOD);
        bigDoorBlock11.getBlock().setType(Material.WOOD);
        bigDoorBlock12.getBlock().setType(Material.WOOD);
        bigDoorBlock13.getBlock().setType(Material.WOOD);
        bigDoorBlock14.getBlock().setType(Material.WOOD);
    }

}
