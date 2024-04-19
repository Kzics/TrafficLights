package com.trafficlights;

import com.trafficlights.obj.Traffic;
import com.trafficlights.obj.trafficlights.TrafficLight;
import org.bukkit.scheduler.BukkitRunnable;

public class TrafficLightsTask extends BukkitRunnable {

    private TraficLightsMain traficLightsMain;
    private int current;
    public TrafficLightsTask(TraficLightsMain traficLightsMain) {
        this.traficLightsMain = traficLightsMain;
        this.current  ++;
        this.runTaskTimer(traficLightsMain, 0L, 20L);
    }

    @Override
    public void run() {
        this.current++;
        if (this.current >= traficLightsMain.getTrafficLightsManager().getGlobalInterval()) {
            this.current = 0;
        }
        for (Traffic trafficLight : traficLightsMain.getTrafficLightsManager().getTrafficLights()){
            if(trafficLight.canChange(this.current)) trafficLight.changeLight();
        }
    }
}
