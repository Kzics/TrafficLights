package com.trafficlights.commands;

import com.trafficlights.LightState;
import com.trafficlights.manager.TrafficLightsManager;
import com.trafficlights.obj.Traffic;
import com.trafficlights.obj.trafficlights.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightCommand implements CommandExecutor, TabCompleter {

    private final TrafficLightsManager trafficLightsManager;
    public TrafficLightCommand(final TrafficLightsManager trafficLightsManager){
        this.trafficLightsManager = trafficLightsManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player player)) return false;

        if(!player.isOp() || !player.hasPermission("trafficlight.admin")) return false;

        if(strings.length < 1) return false;

        String action = strings[0];

        if(action.equalsIgnoreCase("add")){
            if(strings.length < 6 || !isInteger(strings[2]) || !isInteger(strings[3]) || !isInteger(strings[4])) return false;
            String type = strings[1];
            int interval1 = Integer.parseInt(strings[2]);
            int interval2 = Integer.parseInt(strings[3]);
            int interval3 = Integer.parseInt(strings[4]);
            int[] intervals = {interval1, interval2, interval3};
            String face = strings[5];

            Traffic traffic;
            switch (type){
                case "standard":
                    traffic = new TrafficLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "bike":
                    traffic = new BikeTrafficLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "pedestrian":
                    traffic = new PedestrianLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "trot":
                    traffic = new TrotinetteLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "left":
                    traffic = new LeftLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "right":
                    traffic = new RightLeftLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "up":
                    traffic = new UpLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "rightup":
                    traffic = new RightUpLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "rightleft":
                    traffic = new RightLeftLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                case "leftup":
                    traffic = new LeftUpLight(player.getLocation(), intervals, BlockFace.valueOf(face));
                    break;
                default:
                    traffic = new TrafficLight(player.getLocation(), intervals, BlockFace.valueOf(face));
            }
            LightState start = LightState.valueOf(strings[6].toUpperCase());
            traffic.setLightState(start);
            traffic.setStartLight(start);

            trafficLightsManager.addTrafficLight(traffic);
            player.sendMessage("Added a trafficLight");
            traffic.create();

        } else if(action.equalsIgnoreCase("remove")){
            Location playerLocation = player.getLocation();
            double minDistance = Double.MAX_VALUE;
            Traffic nearestTrafficLight = null;

            for(Traffic trafficLight : trafficLightsManager.getTrafficLights()){
                double distance = trafficLight.getLocation().distance(playerLocation);
                if(distance < 10 && distance < minDistance){
                    minDistance = distance;
                    nearestTrafficLight = trafficLight;
                }
            }

            if(nearestTrafficLight != null){
                nearestTrafficLight.reset();

                trafficLightsManager.removeTrafficLight(nearestTrafficLight);
                player.sendMessage("Removed the nearest trafficLights");
            }
        }else if(action.equalsIgnoreCase("interval")){
            if(!isInteger(strings[1])) return false;

            int interval = Integer.parseInt(strings[1]);
            trafficLightsManager.setGlobalInterval(interval);
            trafficLightsManager.getMain().getConfig().set("interval",interval);
        }else if (action.equalsIgnoreCase("reset")) {
            for (Traffic traffic : trafficLightsManager.getTrafficLights()) {
                traffic.r();
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReset all traffic lights"));
        }

        return true;
    }



    private boolean isInteger(String str){
        try{
            Integer.parseInt(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) { // Premier argument
            completions.add("add");
            completions.add("remove");
            completions.add("interval");
        } else if (args.length == 2) { // Deuxième argument
            if (args[0].equalsIgnoreCase("add")) {
                completions.add("standard");
                completions.add("bike");
                completions.add("pedestrian");
                completions.add("trot");
                completions.add("left");
                completions.add("right");
                completions.add("up");
                completions.add("rightup");
                completions.add("rightleft");
                completions.add("leftup");
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("add")) { // Troisième argument
            completions.add("<interval1>");
        } else if (args.length == 4 && args[0].equalsIgnoreCase("add")) { // Quatrième argument
            completions.add("<interval2>");
        } else if (args.length == 5 && args[0].equalsIgnoreCase("add")) { // Cinquième argument
            completions.add("<interval3>");
        } else if (args.length == 6 && args[0].equalsIgnoreCase("add")) { // Sixième argument
            completions.add("<face>");
        } else if (args.length == 7 && args[0].equalsIgnoreCase("add")) { // Septième argument
            completions.add("<start_light_state>");
        }

        return completions;
    }
}
