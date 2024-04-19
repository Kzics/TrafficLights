package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class TrotinetteLight extends Traffic {
    public TrotinetteLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        down.setType(Material.AIR);
        this.lightState = LightState.GREEN;
    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down) {
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM0NjcwYTVjYjcwY2Q3MmY0ZGE3ZGViNWQ3MmQwN2E3MzM0OTQxNjBkYjliYzUyZDk2MTM4MmY5NjE3Y2I1MCJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWUwZTM5NDdjYzMyODA1YWEzMDI5ODVmODhhMGJkZjYxYWRmZTMwNDQ1NThmODBkYTBiZDFiYjdiMTBhNTU0OSJ9fX0=";
        String orangeValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzEwM2QxOTFjYTcyMzk3YWZjYWI1MzQ1ZGJmNWRmMzJiMzk3NWMzNDlhZWU0MjU2NWVlN2ZmNDk4MDIyMzllYyJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDIyOWMwZWZiMzgxN2QyYzVkYjQ1OTdhZWI5MjVlNmViOWFkNTI4ZWE2MDAwMzdjNTNlY2Q1NTU5M2RkNjkzMyJ9fX0=";

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
