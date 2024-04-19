package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class LeftUpLight extends Traffic {

    public LeftUpLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        this.lightState = LightState.GREEN;

    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI2MWZkNjkyNmI1MjI3OGMzMzYyZGQ5YjA5ZDNkMDBmODk0ZTc1ZDNjZGZkNDI3MTJlNzBlNzM0NTExYzM1YSJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFjMTg3NzE4NTIwZjczNDI2NmM1YWQxZDc1MDA0MjY1YWVmNGVlNGVhMzYzM2YzM2M1NzIyNmZlOTRjYTRmOSJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQ2NzczZDM0Y2RmMmI3ZDVjMTk4MjNiY2RkNjViMWM3MTBhODViZWU5ZDU0ODBhNmJiOWMwZTRlN2RjOTQ1MSJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjI2YTMwNThmMjEwOGY2ZGRkODk5NzNiODQ1OTNkNDAzOGM4ODJjZGM5MTAxNWYzMjg3YmFhZDZiMDM0NjIyMSJ9fX0=";

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
