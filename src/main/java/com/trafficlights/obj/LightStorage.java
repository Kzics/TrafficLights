package com.trafficlights.obj;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.trafficlights.LightState;
import com.trafficlights.TraficLightsMain;
import com.trafficlights.obj.trafficlights.*;
import com.trafficlights.utils.LocationSerializer;
import org.bukkit.block.BlockFace;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class LightStorage {
    public static void saveTrafficLights(final TraficLightsMain manager) {
        final File file = new File(manager.getDataFolder(), "traffic_lights.json");

        try (JsonWriter writer = new JsonWriter(new FileWriter(file))) {
            writer.beginObject();
            writer.name("traffic_lights");
            writer.beginArray();

            for (Traffic trafficLight : manager.getTrafficLightsManager().getTrafficLights()) {
                writer.beginObject();
                writer.name("intervals");
                writer.beginArray();
                for (int interval : trafficLight.getInterval()) {
                    writer.value(interval);
                }
                writer.endArray();
                writer.name("location").value(LocationSerializer.serializeLocation(trafficLight.getLocation()));
                writer.name("face").value(trafficLight.getFace().name());

                writer.name("startLight").value(trafficLight.getStartLight().name());

                if (trafficLight instanceof BikeTrafficLight) {
                    writer.name("type").value("bike");
                } else if (trafficLight instanceof TrafficLight) {
                    writer.name("type").value("standard");
                } else if (trafficLight instanceof PedestrianLight) {
                    writer.name("type").value("pedestrian");
                } else if (trafficLight instanceof TrotinetteLight) {
                    writer.name("type").value("trot");
                } else if (trafficLight instanceof LeftLight) {
                    writer.name("type").value("left");
                } else if (trafficLight instanceof LeftUpLight) {
                    writer.name("type").value("leftup");
                } else if (trafficLight instanceof RightLeftLight) {
                    writer.name("type").value("rightleft");
                } else if (trafficLight instanceof RightUpLight) {
                    writer.name("type").value("rightup");
                } else if (trafficLight instanceof UpLight) {
                    writer.name("type").value("uplight");
                }
                writer.endObject();
            }

            writer.endArray();
            writer.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadTrafficLights(final TraficLightsMain main) {
        final File file = new File(main.getDataFolder(), "traffic_lights.json");

        try (JsonReader reader = new JsonReader(new FileReader(file))) {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("traffic_lights")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        reader.beginObject();
                        int[] intervals = null;
                        String location = "";
                        String face = "";
                        String type = "";
                        LightState startLight = LightState.GREEN; // Default startLight value

                        while (reader.hasNext()) {
                            String property = reader.nextName();
                            switch (property) {
                                case "intervals":
                                    List<Integer> intervalList = new ArrayList<>();
                                    reader.beginArray();
                                    while (reader.hasNext()) {
                                        intervalList.add(reader.nextInt());
                                    }
                                    reader.endArray();
                                    intervals = intervalList.stream().mapToInt(i -> i).toArray();
                                    break;
                                case "location":
                                    location = reader.nextString();
                                    break;
                                case "face":
                                    face = reader.nextString();
                                    break;
                                case "type":
                                    type = reader.nextString();
                                    break;
                                case "startLight":
                                    startLight = LightState.valueOf(reader.nextString());
                                    break;
                            }
                        }

                        Traffic traffic;
                        switch (type) {
                            case "bike":
                                traffic = new BikeTrafficLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "standard":
                                traffic = new TrafficLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "pedestrian":
                                traffic = new PedestrianLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "trot":
                                traffic = new TrotinetteLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "left":
                                traffic = new LeftLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "rightleft":
                                traffic = new RightLeftLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "up":
                                traffic = new UpLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "rightup":
                                traffic = new RightUpLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            case "leftup":
                                traffic = new LeftUpLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                                break;
                            default:
                                traffic = new TrafficLight(LocationSerializer.deserializeLocation(location), intervals, BlockFace.valueOf(face));
                        }

                        // Set startLight before creating the traffic light
                        traffic.setLightState(startLight);

                        main.getTrafficLightsManager().addTrafficLight(traffic);
                        reader.endObject();
                        traffic.create(); // Move this line here
                    }
                    reader.endArray();
                }
            }
            reader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
