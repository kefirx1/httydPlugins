package pl.dev.httyd.httydplugins;

import org.bukkit.plugin.java.JavaPlugin;
import pl.dev.httyd.httydplugins.commands.*;
import pl.dev.httyd.httydplugins.listeners.PlayerListener;

public final class HttydPlugins extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        getCommand("opis").setExecutor(new DescCommand());
        getCommand("wizerunek").setExecutor(new ViewCommand());
        getCommand("menu").setExecutor(new MenuCommand());
        getCommand("setname").setExecutor(new SetNameCommand());
        getCommand("ooc").setExecutor(new OocCommand());
        getCommand("me").setExecutor(new MeCommand());
        getCommand("do").setExecutor(new DoCommand());
        getCommand("localnar").setExecutor(new LocalnarCommand());
        getCommand("nar").setExecutor(new NarCommand());
        getCommand("globalnar").setExecutor(new GlobalnarCommand());
        getCommand("szept").setExecutor(new SzeptCommand());
        getCommand("su").setExecutor(new SuCommand());
        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("prefix").setExecutor(new PrefixCommand());
        getCommand("sprobuj").setExecutor(new TryCommand());
        getCommand("kostki").setExecutor(new DiceCommand());
        getCommand("drzwi").setExecutor(new DoorCommand());


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
