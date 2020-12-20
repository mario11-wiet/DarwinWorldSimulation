package Maths;

import java.util.Objects;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Vector2d {
    public int x;
    public int y;
    public Vector2d(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    public String toString(){
        return "("+x+","+y+")";
    }
    boolean precedes(Vector2d other){
        return (this.x<=other.x && this.y<=other.y);
    }
    boolean follows(Vector2d other){
        return (this.x>=other.x && this.y>=other.y);
    }
    public Vector2d upperRight(Vector2d other){
        return new Vector2d(max(this.x,other.x),max(this.y,other.y));
    }
    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(min(this.x,other.x),min(this.y,other.y));}
    public Vector2d add(Vector2d other){
        return new Vector2d(this.x+other.x,this.y+other.y);}
    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x-other.x,this.y-other.y);}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2d vector2d = (Vector2d) o;
        return x == vector2d.x &&
                y == vector2d.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public Vector2d opposite(){
        return new Vector2d((-1)*this.x,(-1)*this.y);
    }

}
