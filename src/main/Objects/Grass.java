package Objects;

import Maths.Vector2d;

import java.awt.*;

public class Grass {
    private Vector2d wektor;

    public Grass(Vector2d wektor){
        this.wektor=wektor;
    }
    public Vector2d getPosition(){
        return this.wektor;
    }
    public String toString()
    {
        return "*";
    }

    public Color toColor() {
        return new Color(49, 187, 4);
    }
}
