package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class LeftLight extends Traffic {
    public LeftLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);

        this.lightState = LightState.GREEN;

    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc2MjMwYTBhYzUyYWYxMWU0YmM4NDAwOWM2ODkwYTQwMjk0NzJmMzk0N2I0ZjQ2NWI1YjU3MjI4ODFhYWNjNyJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzJmZjhhYWE0YjJlYzMwYmM1NTQxZDQxYzg3ODIxOTliYWEyNWFlNmQ4NTRjZGE2NTFmMTU5OWU2NTRjZmM3OSJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWMwNjQ2YTI4YjQ1MWRhNzQzOTRlNjk4YjA0ZmFjOTM1YmExOTc1ZjQyODI5MDY3YTBmYmZlZDE4MWEzNjU5NCJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM1YThhYThhNGMwMzYwMGEyYjVhNGViNmJlYjUxZDU5MDI2MGIwOTVlZTFjZGFhOTc2YjA5YmRmZTU2NjFjNiJ9fX0=";


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
