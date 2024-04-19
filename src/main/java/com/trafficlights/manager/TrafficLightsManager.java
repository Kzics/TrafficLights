package com.trafficlights.manager;

import com.trafficlights.TraficLightsMain;
import com.trafficlights.obj.Traffic;

import java.util.HashSet;
import java.util.Set;

public class TrafficLightsManager {
    private final Set<Traffic> trafficLights;
    private int globalInterval;
    private TraficLightsMain main;
    public TrafficLightsManager(TraficLightsMain main){
        this.main = main;
        this.trafficLights = new HashSet<>();
        this.globalInterval = main.getConfig().getInt("interval");
    }

    public TraficLightsMain getMain() {
        return main;
    }

    public Set<Traffic> getTrafficLights() {
        return trafficLights;
    }


    public int getGlobalInterval() {
        return globalInterval;
    }

    public void addTrafficLight(Traffic trafficLight){
        this.trafficLights.add(trafficLight);
    }

    public void removeTrafficLight(Traffic trafficLight){
        this.trafficLights.remove(trafficLight);
    }

    public void setGlobalInterval(int interval){
        this.globalInterval = interval;

    }
}
