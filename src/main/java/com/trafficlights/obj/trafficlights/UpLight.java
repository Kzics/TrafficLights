package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class UpLight extends Traffic {
    public UpLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        this.lightState = LightState.GREEN;

    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmNhOTQyYTkyOWQzNjc5YTI2ZjAwOTIwMDVlOTg1YTU1ZTc4OGExYjU2NDkxMDEzMDc0OWQ5ZmM4OTZlOTAxMyJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I0NWM1ZWI3OGRmZjZmYzQzZjdmOGUzOTg3Mjk0MTQ0MjJhOGViNmYzMTQ1MDVkZjhmZjlhMzNiZGQ2ZDEyZiJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjdkYTliMmRiM2M1YmUxZTFmNmUxYzJmM2U3MTljMjBiZThhNjY0OGEwODdmYTQ1NDI4NGMyMjFhZDVhOWI3OCJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjg1MzU1MTZlOGYwZTc2ODMwN2I5YTMwZGMwZjc5Njg3ZjAzMzJkN2NiMjc2MDc2NDlkYWQ3NWNlZTg1YWM1YiJ9fX0=";

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
