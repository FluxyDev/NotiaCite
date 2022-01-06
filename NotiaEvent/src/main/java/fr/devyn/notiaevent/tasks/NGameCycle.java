package fr.devyn.notiaevent.tasks;

import fr.devyn.notiaevent.NotiaEvent;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class NGameCycle extends BukkitRunnable {

    private NotiaEvent main;
    private int timer = 300;

    public NGameCycle(NotiaEvent main){
        this.main = main;
    }
    @Override
    public void run() {

        if (timer == 0){
            Bukkit.broadcastMessage("Bon jeux à tous et à toute !");
            cancel();
        }
    }
}
