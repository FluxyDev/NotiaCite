package fr.devyn.notiaevent.tasks;

import fr.devyn.notiaevent.NState;
import fr.devyn.notiaevent.NotiaEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NAutoStart extends BukkitRunnable {

    private int timer = 10;
    private NotiaEvent main;

    public NAutoStart(NotiaEvent main) {
        this.main = main;
    }

    @Override
    public void run() {

        for (Player pls : main.getPlayers()){
            pls.setLevel(timer);
        }

        if(timer ==10 || timer== 5 || timer == 4 || timer == 3 || timer == 2 || timer == 1){
            Bukkit.broadcastMessage("Lancement de la CitéFéerique dans : §d" + timer+"s");
        }

        if (timer == 0){
            Bukkit.broadcastMessage("Lancement de la CitéFéerique");
            main.setState(NState.PLAYING);

            for (int i = 0; i < main.getPlayers().size(); i++){
                Player player = main.getPlayers().get(i);
                Location spawn = main.getSpawns().get(i);
                player.teleport(spawn);
            }
            NGameCycle cycle = new NGameCycle(main);
            cycle.runTaskTimer(main, 0, 20);
            cancel();
        }

        timer--;
    }
}
