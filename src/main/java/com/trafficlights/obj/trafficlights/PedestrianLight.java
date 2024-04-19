package com.trafficlights.obj.trafficlights;

import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class PedestrianLight extends Traffic {
    public PedestrianLight(Location location, int[] intervals, BlockFace face) {
        super(location, intervals, face);
        this.lightState = LightState.GREEN;

        this.down.setType(Material.AIR);
    }

    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    protected void setSkulls(Block up, Block mid, Block down){
        String greyValueWaiting = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ1YWM4NjBhZjE1ZGExMjFjYjlhMzFmZmRjMzg0NjdiODUxNjY4ZDU3ZTE3ZjRjNjAwOWE4MmJjZGY3MDYwZiJ9fX0=";
        String greyValueWalking = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk1ZjYxOTI0NTdkNjZkY2FiYjFiNjQ3MmQ1YzMwZjdjMDg1NzI2MGNmN2QwODc5ODExMzVlYjg2N2NhOTdmOSJ9fX0=";
        String greenValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA0OGNjMGRlZTA5NWJlNTgzOWZkZjJmN2QzODI1NTZjMDBjNjg2YzdlYjRiM2M2OTBiZTRkZjAyZjIxMmFhZCJ9fX0=";
        String redValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM0YzMzZmMxZjFiYTVmN2ZkODc3MmZlNjVkMjNmZDU0OTUwNmI4OTkxY2RhZGRkNDdjYzc3OTk0NGFhMWIxYiJ9fX0=";

        switch (getLightState()){
            case RED:
                setSkull(up, redValue, false);
                setSkull(mid, greyValueWalking, false);
                break;
            case GREEN:
                setSkull(up, greyValueWaiting, false);
                setSkull(mid, greenValue, false);
                break;
            case ORANGE:
                break;
        }
    }
}
