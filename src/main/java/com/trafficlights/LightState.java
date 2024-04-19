package com.trafficlights;

import java.util.Arrays;

public enum LightState {
    GREEN(0),
    ORANGE(1),
    RED(2);

     final int ordinal;
     LightState(int ordinal){
         this.ordinal = ordinal;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static LightState getLightState(int ordinal){
       return Arrays.stream(values()).filter(v->v.getOrdinal() == ordinal)
                .findFirst()
                .get();
    }
}
