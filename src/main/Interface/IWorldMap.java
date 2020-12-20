package Interface;

import Maths.Vector2d;

public interface IWorldMap {

    boolean isOccupied(Vector2d position);

    Object object(Vector2d position);
}
