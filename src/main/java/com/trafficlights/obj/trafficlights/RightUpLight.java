package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class RightUpLight extends Traffic {
    public RightUpLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        this.lightState = LightState.GREEN;
    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTUzOTVjNTg0NDdhZjFkZjEyYzlkOTNkNDZjYjU1ZGFkOTZhY2I4NzY3NGU3NDBlOTRhYTMwOTMwM2RiMDRiNSJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA4NzZjNTJiZjZmZmMxNWIzMmE3NTFkZDcwZjYzYjhlZmJhM2U4MGJmYjMxM2Y1ODU0ZGE4NDRmMjE3YmZiNCJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE0NWY5NDZkNmZmYWEwZmQ4ZWUwZGYzOGE1MWQ4OTA1NmQ4YWEwYWQwZGE2MDk3NTFmOGMyNDc4MTgzODM5YiJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIxOTA1MmI4YzA2YjgzOGZjYWYxM2E5NTVlMzc4ZjljY2FhM2YzYTU2MjhhYjI1NWU3MzdjN2Q5NDYwYTVjNSJ9fX0=";

        switch (getLightState()){
            case RED:
                setSkull(up, redValue, false);
                setSkull(mid, greyValue, false);
                setSkull(down, greyValue, false);
                break;

            case GREEN:
                setSkull(up, greyValue, false);
                setSkull(mid, greyValue, false);
                setSkull(down, greenValue, false);
                break;

            case ORANGE:
                setSkull(up, greyValue, false);
                setSkull(mid, orangeValue, false);
                setSkull(down, greyValue, false);
                break;
        }
    }
}
