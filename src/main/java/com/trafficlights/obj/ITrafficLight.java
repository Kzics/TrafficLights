package com.trafficlights.obj;

import com.trafficlights.LightState;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;

public interface ITrafficLight {

    Location getLocation();
    String getId();
    void create();
    LightState getLightState();

    void changeLight();

    void reset();
    BlockFace getFace();
    int[] getInterval();
    boolean canChange(int timer);
}
