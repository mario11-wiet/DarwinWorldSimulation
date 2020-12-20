package Map;

import Map.MapDirection;

public class OptionsParser {
    public static MapDirection parse(int imput){
        return MapDirection.values()[imput];
    }
    public static int parseDirection(MapDirection imput) {
        return imput.ordinal();
    }}



