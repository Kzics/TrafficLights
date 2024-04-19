package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class RightLeftLight extends Traffic {
    public RightLeftLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        this.lightState = LightState.GREEN;

    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJmOGI2Mjc3Y2QzNjI2NjI4M2NiNWE5ZTY5NDM5NTNjNzgzZTZmZjdkNmEyZDU5ZDE1YWQwNjk3ZTkxZDQzYyJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWFiOTVhODc1MWFlYWEzYzY3MWE4ZTkwYjgzZGU3NmEwMjA0ZjFiZTY1NzUyYWMzMWJlMmY5OGZlYjY0YmY3ZiJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDYzNjgyZjE5ZGU0OTZjYjNjMzE0ZDYyOWQzZmMxZjQyNWU2NjAyNTI4MmQyY2U4YTFmMGUyMjQ3NmMwMWMwNCJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZhZjIxN2FlZGRmMGY0MDA2NDk2OWViYjIwNDJmN2FlYWZiYzdkMGYxNzVhMjc2MjQxMzNhM2JlZmQxMDI4MSJ9fX0=";

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
