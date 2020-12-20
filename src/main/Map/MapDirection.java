package Map;

import Maths.Vector2d;

public enum MapDirection {
    NORTH,
    NORTHWEST,
    WEST,
    SOUTHWEST,
    SOUTH,
    SOUTHEAST,
    EAST,
    NORTHEAST;


    public String toString()
    {
    switch (this){
        case EAST:
            return "E";
        case WEST:
            return "W";
        case SOUTH:
            return "S";
        case NORTH:
            return "N";
        case NORTHWEST:
            return "NW";
        case NORTHEAST:
            return "NE";
        case SOUTHWEST:
            return "SW";
        case SOUTHEAST:
            return "SE";

    }
    return"";
    }
//    public MapDirection next(){
//        switch (this){
//            case EAST:
//                return SOUTH;
//            case WEST:
//                return NORTH;
//            case SOUTH:
//                return WEST;
//            case NORTH:
//                return EAST;
//        }
//    return this;
//    }
//    public MapDirection previous(){
//        switch (this){
//            case EAST:
//                return NORTH;
//            case WEST:
//                return SOUTH;
//            case SOUTH:
//                return EAST;
//            case NORTH:
//                return WEST;
//        }
//        return this;
//    }
    public Vector2d toUnitVector(){
        switch (this) {
            case EAST:
                return new Vector2d(1, 0);
            case WEST:
                return new Vector2d(-1, 0);
            case SOUTH:
                return new Vector2d(0, -1);
            case NORTH:
                return new Vector2d(0, 1);
            case NORTHWEST:
                return new Vector2d(-1, 1);
            case NORTHEAST:
                return new Vector2d(1, 1);
            case SOUTHWEST:
                return new Vector2d(-1, -1);
            case SOUTHEAST:
                return new Vector2d(1, -1);
            default:
                return new Vector2d(0, 0);
        }
    }
}
