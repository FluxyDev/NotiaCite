package fr.devyn.notiaevent.listeners;

import fr.devyn.notiaevent.NState;
import fr.devyn.notiaevent.NotiaEvent;
import fr.devyn.notiaevent.tasks.NAutoStart;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NPlayerListeners implements Listener{

    private NotiaEvent main;

    public NPlayerListeners(NotiaEvent main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        Player player = event.getPlayer();
        Location spawn = new Location(Bukkit.getWorld("world"), 324.646, 71.0, 107.300, 179.4f, 16.7f);
        player.teleport(spawn);
        player.getInventory().clear();
        player.setFoodLevel(20);
        player.setHealth(20);

        if (!main.isState(NState.WAITING)){
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage("La cité a déjà demarré !");
            event.setJoinMessage(null);
            return;
        }
        if (!main.getPlayers().contains(player))main.getPlayers().add(player);
        player.setGameMode(GameMode.ADVENTURE);
        event.setJoinMessage("§7[§5CitéFéerique§7]§r" + player.getName() + " à rejoint la CitéFéerique ! <" + main.getPlayers().size()+"/"+ Bukkit.getMaxPlayers()+">") ;

        if (main.isState(NState.WAITING) && main.getPlayers().size() == 1){
            NAutoStart start = new NAutoStart(main);
            start.runTaskTimer(main, 0, 20);
            main.setState(NState.STARTING  );
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        Player player = event.getPlayer();;
        if (main.getPlayers().contains(player)){
            main.getPlayers().remove(player);
        }
    }
}
