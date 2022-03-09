package pl.dev.httyd.httydplugins.data;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class Gate1Data {

    World world = Bukkit.getWorld("world");

    Location gate1Block1 = new Location(world, 6, 80, 199);
    Location gate1Block2 = new Location(world, 7, 80, 199);
    Location gate1Block3 = new Location(world, 8, 80, 199);
    Location gate1Block4 = new Location(world, 6, 81, 199);
    Location gate1Block5 = new Location(world, 7, 81, 199);
    Location gate1Block6 = new Location(world, 8, 81, 199);
    Location gate1Block7 = new Location(world, 6, 82, 199);
    Location gate1Block8 = new Location(world, 7, 82, 199);
    Location gate1Block9 = new Location(world, 8, 82, 199);
    Location gate1Block10 = new Location(world, 6, 83, 199);
    Location gate1Block11 = new Location(world, 7, 83, 199);
    Location gate1Block12 = new Location(world, 8, 83, 199);
    Location gate1Block13 = new Location(world, 6, 84, 199);
    Location gate1Block14 = new Location(world, 7, 84, 199);
    Location gate1Block15 = new Location(world, 8, 84, 199);
    Location gate1Block16 = new Location(world, 6, 85, 199);
    Location gate1Block17 = new Location(world, 7, 85, 199);
    Location gate1Block18 = new Location(world, 8, 85, 199);
    Location gate1Block19 = new Location(world, 6, 86, 199);
    Location gate1Block20 = new Location(world, 7, 86, 199);
    Location gate1Block21 = new Location(world, 8, 86, 199);


    public void setToAir(){
        gate1Block1.getBlock().setType(Material.AIR);
        gate1Block2.getBlock().setType(Material.AIR);
        gate1Block3.getBlock().setType(Material.AIR);
        gate1Block4.getBlock().setType(Material.AIR);
        gate1Block5.getBlock().setType(Material.AIR);
        gate1Block6.getBlock().setType(Material.AIR);
        gate1Block7.getBlock().setType(Material.AIR);
        gate1Block8.getBlock().setType(Material.AIR);
        gate1Block9.getBlock().setType(Material.AIR);
        gate1Block10.getBlock().setType(Material.AIR);
        gate1Block11.getBlock().setType(Material.AIR);
        gate1Block12.getBlock().setType(Material.AIR);
        gate1Block13.getBlock().setType(Material.AIR);
        gate1Block14.getBlock().setType(Material.AIR);
        gate1Block15.getBlock().setType(Material.AIR);
        gate1Block16.getBlock().setType(Material.AIR);
        gate1Block17.getBlock().setType(Material.AIR);
        gate1Block18.getBlock().setType(Material.AIR);
        gate1Block19.getBlock().setType(Material.AIR);
        gate1Block20.getBlock().setType(Material.AIR);
        gate1Block21.getBlock().setType(Material.AIR);
    }

    public void setToFence(){
        gate1Block1.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block2.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block3.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block4.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block5.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block6.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block7.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block8.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block9.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block10.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block11.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block12.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block13.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block14.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block15.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block16.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block17.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block18.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block19.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block20.getBlock().setType(Material.SPRUCE_FENCE);
        gate1Block21.getBlock().setType(Material.SPRUCE_FENCE);
    }

}
