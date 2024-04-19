package com.trafficlights.obj.trafficlights;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.trafficlights.LightState;
import com.trafficlights.obj.Traffic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;


import java.lang.reflect.Field;
import java.util.UUID;

public class TrafficLight extends Traffic {
    private LightState lightState;

    public TrafficLight(Location location, int[] intervals, BlockFace face){
        super(location, intervals, face);
        this.lightState = LightState.GREEN;

    }


    public void setLightState(final LightState lightState){
        this.lightState = lightState;

        setSkulls(up,mid,down);
    }
    @Override
    public BlockFace getFace() {
        return face;
    }

    @Override
    public int[] getInterval() {
        return intervals;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void reset() {
        this.up.setType(Material.AIR);
        this.mid.setType(Material.AIR);
        this.down.setType(Material.AIR);
    }


    @Override
    public boolean canChange(int timer) {
        for (int interval : intervals){
            if(timer == interval){
                return true;
            }
        }
        return false;
    }

    @Override
    public void create() {


        up.setType(Material.BLACK_WOOL);
        mid.setType(Material.BLACK_WOOL);
        down.setType(Material.BLACK_WOOL);

        setSkulls(up, mid, down);
    }

    protected void setSkulls(Block up, Block mid, Block down){
        String greyValue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ1ZjE2OTBhOWJhMmIwZTliNTlkZTQxN2I4ODkzNzZiYzI3M2JjZjY5ZTBlZTIzNzUzNDc0NjAzNTlkZTg3NSJ9fX0=";

        switch (getLightState()){
            case RED:
                setSkull(up, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE0NGRhMGFmOTBhY2I2MDdlYWIyOGYyODc5ODUwNGE3MzE4OTM3YTE1N2ZiM2EwM2UxNDdhZTcwZTM1MzFjZSJ9fX0=", false); // Rouge
                setSkull(mid, greyValue, false);
                setSkull(down, greyValue, false);
                break;
            case GREEN:
                setSkull(up, greyValue, false);
                setSkull(mid, greyValue, false);
                setSkull(down, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI5YjBiMmY3YzhhNWYwNjBmYjY3NDBjZmM0Y2I3OGVmYjYxZjlmMTZjOGU5NGYxYjc3MjU2N2ZkNDJjNjViYSJ9fX0=",false); // Rouge
                break;
            case ORANGE:
                setSkull(up, greyValue,false);
                setSkull(mid, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGUyZjJiMTY4OTFjNmE0NWQzYmY2ODk0OTdmNjJjOWE4Y2FlZDFhZGJmNzUwNDg2YWI0YmE3MmE2ZGNmMDZmMyJ9fX0=",false); // Orange
                setSkull(down, greyValue,false);

                break;
        }
    }

    public void setSkull(Block block, String texture, boolean isBoy) {
        block = block.getRelative(isBoy ? face.getModX() != 0 ? BlockFace.NORTH : BlockFace.EAST : face);

        block.setType(Material.PLAYER_WALL_HEAD);
        Skull skull = (Skull) block.getState();


        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        PropertyMap propertyMap = profile.getProperties();
        if (propertyMap == null) {
            throw new IllegalStateException("Profile doesn't contain a property map");
        }
        propertyMap.clear();
        propertyMap.put("textures", new Property("textures", texture));

        Field profileField;
        try {
            profileField = skull.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skull, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        BlockData blockData = skull.getBlockData();
        if(blockData instanceof Directional directional){
            directional.setFacing(isBoy ? face.getModX() != 0 ? BlockFace.NORTH : BlockFace.EAST : face);
            skull.setBlockData(directional);
        }

        skull.update();
    }


    @Override
    public LightState getLightState() {
        return lightState;
    }

    @Override
    public void changeLight() {
        int newOrdinal = this.lightState.getOrdinal() + 1 >= 3 ? 0 : this.lightState.getOrdinal() + 1;
        this.lightState = LightState.getLightState(newOrdinal);

        this.currentInterval = newOrdinal;

        setSkulls(up, mid, down);
    }
}
