package com.trafficlights.obj;

public enum SkullsEnum {
    STANDARD("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmQ1ZjE2OTBhOWJhMmIwZTliNTlkZTQxN2I4ODkzNzZiYzI3M2JjZjY5ZTBlZTIzNzUzNDc0NjAzNTlkZTg3NSJ9fX0=",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWE0NGRhMGFmOTBhY2I2MDdlYWIyOGYyODc5ODUwNGE3MzE4OTM3YTE1N2ZiM2EwM2UxNDdhZTcwZTM1MzFjZSJ9fX0=",
            "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTI5YjBiMmY3YzhhNWYwNjBmYjY3NDBjZmM0Y2I3OGVmYjYxZjlmMTZjOGU5NGYxYjc3MjU2N2ZkNDJjNjViYSJ9fX0="),
    BIKE("","",""),
    PEDESTRIAN("","",""),
    DIRECTIONAL_RIGHT("","",""),
    DIRECTIONAL_LEFT("","","");


    private final String red;
    private final String  orange;
    private final String green;
    SkullsEnum(String red, String orange, String green){
        this.green = green;
        this.orange = orange;
        this.red = red;
    }

}
