package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BikeTrafficLight extends Traffic {


    public BikeTrafficLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        down.setType(Material.AIR);

        this.lightState = LightState.GREEN;

    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down){
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjA4OTM0MDE5ZDY4ZmMzZjhhZTcwY2FjYWY3ZjVjMjA4ZGMxMzAxMmRlMjNlODRhZWU0Nzc4MGY3MDg0YzZkOSJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTNmM2RkNTE2MmVkOTI2MWY1ZmQ5YzZiZmE4YzhjNDVjNzA3Y2Q3YTcwMjY5M2I3MjQ3NTZiODZiYjZmNWZlYiJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjFhOTE0YjQzYWVhNWRlY2ViZjJhM2M4OTMyYzAzNTIwOTVhZmE0YjdhNDE0MTI1NmU4NjdjNDQ1MDY4YmFhNCJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGE1NjliZjEzOWM2NjY4Y2MxOGM0MWViYTlhZjgwNzdlZjFhMzkxMDFmNmFhNzQ0NGMyMjZjNmYzN2NhNTJiOCJ9fX0=";

        switch (getLightState()){
            case RED:
                setSkull(up, redValue, false); // Rouge
                setSkull(mid, greyValue, false);
                setSkull(down, greyValue, false);

                break;
            case GREEN:
                setSkull(up, greyValue, false);
                setSkull(mid, greyValue, false);
                setSkull(down, greenValue,false); // Rouge

                break;
            case ORANGE:
                setSkull(up, greyValue,false);
                setSkull(mid, orangeValue,false); // Orange
                setSkull(down, greyValue,false);
                break;
        }
    }
}
