package com.trafficlights.obj;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.trafficlights.LightState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Directional;

import java.util.UUID;

public abstract class Traffic implements ITrafficLight {

    protected Location location;
    protected LightState lightState;
    protected BlockFace face;
    protected Block up, mid, down;
    protected int[] intervals;
    protected int currentInterval;
    protected int current;
    protected LightState startLight;

    public Traffic(Location location, int[] intervals, BlockFace face){
        this.location = location;
        this.face = face;
        this.intervals = intervals;

        this.up = location.getBlock().getRelative(face);
        this.mid = this.up.getRelative(BlockFace.DOWN);
        this.down = this.mid.getRelative(BlockFace.DOWN);

        this.currentInterval = 0;
        this.current = 0;
        this.lightState = LightState.GREEN;
    }

    public void setStartLight(LightState startLight){
        this.startLight = startLight;
    }

    public LightState getStartLight() {
        return startLight;
    }

    public void setLightState(LightState lightState) {
        this.lightState = lightState;
    }

    public void create() {
        up.setType(Material.BLACK_WOOL);
        mid.setType(Material.BLACK_WOOL);
        down.setType(Material.BLACK_WOOL);

        setSkulls(up, mid, down);
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

    }
    public void r(){
        setLightState(getStartLight());
        setSkulls(up, mid, down);
    }
    @Override
    public BlockFace getFace() {
        return face;
    }

    @Override
    public int[] getInterval() {
        return intervals;
    }

    protected abstract void setSkulls(Block up, Block mid, Block down);

    public void setSkull(Block block, String texture, boolean isBoy){
        block = block.getRelative(isBoy ? face.getModX() != 0 ? BlockFace.NORTH : BlockFace.EAST : face);

        block.setType(Material.PLAYER_WALL_HEAD);
        Skull skull = (Skull) block.getState();
        PlayerProfile profile = skull.getPlayerProfile() == null ? Bukkit.createProfile(UUID.randomUUID()): skull.getPlayerProfile();

        ProfileProperty property = new ProfileProperty("textures", texture);
        profile.getProperties().clear();
        profile.getProperties().add(property);
        skull.setPlayerProfile(profile);
        BlockData blockData = skull.getBlockData();
        if(blockData instanceof Directional directional){
            directional.setFacing(isBoy ? face.getModX() != 0 ? BlockFace.NORTH : BlockFace.EAST : face);
            skull.setBlockData(directional);
        }

        skull.update();
    }

    public void changeLight(){
        int newOrdinal = this.lightState.getOrdinal() + 1 >= 3 ? 0 : this.lightState.getOrdinal() + 1;
        this.lightState = LightState.getLightState(newOrdinal);

        this.currentInterval = newOrdinal;

        setSkulls(up, mid, down);
    }
    public boolean canChange(int timer){
        for (int interval : intervals){
            if(timer == interval){
                return true;
            }
        }
        return false;
    }
}