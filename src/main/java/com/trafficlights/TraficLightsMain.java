package com.trafficlights;


import com.trafficlights.commands.TrafficLightCommand;
import com.trafficlights.manager.TrafficLightsManager;
import com.trafficlights.obj.LightStorage;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class TraficLightsMain extends JavaPlugin implements Listener {


    private TrafficLightsManager trafficLightsManager;
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();
        if(!new File(getDataFolder(), "config.yml").exists()) {
            try {
                new File(getDataFolder(), "config.yml").createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(!new File(getDataFolder(), "traffic_lights.json").exists()) {
            try {
                getDataFolder().createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        getConfig().addDefault("interval",60);


        this.trafficLightsManager = new TrafficLightsManager(this);
        LightStorage.loadTrafficLights(this);

        getCommand("tl").setExecutor(new TrafficLightCommand(trafficLightsManager));
        getCommand("tl").setTabCompleter(new TrafficLightCommand(trafficLightsManager));
        new TrafficLightsTask(this);
    }

    @Override
    public void onDisable() {
        LightStorage.saveTrafficLights(this);
    }

    public TrafficLightsManager getTrafficLightsManager() {
        return trafficLightsManager;
    }
}
