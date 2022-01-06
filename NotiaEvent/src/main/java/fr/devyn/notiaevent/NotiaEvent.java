package fr.devyn.notiaevent;

import fr.devyn.notiaevent.listeners.NPlayerListeners;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class NotiaEvent extends JavaPlugin {

    private List<Player> players = new ArrayList<>();
    private List<Location> spawns = new ArrayList<>();
    private NState state;

    @Override
    public void onEnable() {
        System.out.println("[NotiaEvent] Enable");
        setState(NState.WAITING);

        //SpawnRegister
        World world = Bukkit.getWorld("world");
        spawns.add(new Location(world,300.372,112.0,210.575, 1.6f, 4.2f));
        spawns.add(new Location(world,300.372,112.0,210.575, 1.6f, 4.2f));
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new NPlayerListeners(this), this);
    }

    public void setState(NState state) {
        this.state = state;
    }

    public Boolean isState(NState state){
        return this.state == state;
    }
    @Override
    public void onDisable() {
        System.out.println("[NotiaEvent] Disable");
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Location> getSpawns() {
        return spawns;
    }
}
