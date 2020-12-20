package Objects;

import Interface.IPositionChangeObserver;
import Interface.IWorldMap;
import Map.MapDirection;
import Maths.GenesAnimal;
import Maths.Vector2d;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Animal{
    public MapDirection direction;
    private Vector2d position;
    private IWorldMap worldMap;
    private List<IPositionChangeObserver> obserator =new LinkedList<>();
    public GenesAnimal genes=new GenesAnimal();
    private float energy;
    private int startEnergy;
    public int firstDay;
    public int numberOfChildren;
    public int numberOfChildrenAfterFollow;
    public boolean dominationGen=false;
    public boolean follow=false;
    public boolean childrenFollowAnimal=false;
    public boolean trackingAnimal=false;

    public Animal(int startEnergy,GenesAnimal genes,Vector2d startPosiition, MapDirection startDirection) {
        this.energy = startEnergy;
        this.direction = startDirection;
        this.position = startPosiition;
        this.genes.createGenesForAnimal();
        this.startEnergy=startEnergy;
        this.obserator = new LinkedList<>();
    }
    public Animal(int startEnergy,Vector2d startPosiition, MapDirection startDirection,
                  GenesAnimal firstParent, GenesAnimal secondParent)
    {
        this.energy=startEnergy;
        this.direction = startDirection;
        this.position = startPosiition;
        this.genes.genesInheritFromParents(firstParent, secondParent);
        this.obserator=new LinkedList<>();
    }

    public GenesAnimal getGenes(){return genes;}
    public float getEnergy(){return energy;}
    public MapDirection getDirection(){
        return direction;
    }
    public Vector2d getPosition(){
        return position;
    }
    void addObserver(IPositionChangeObserver observer)
    {
        this.obserator.add(observer);
    }
    void removeObserver(IPositionChangeObserver observer)
    {
        this.obserator.remove(observer);
    }
    void positionChanged(Vector2d oldp, Vector2d newp)
    {
        for(IPositionChangeObserver g: obserator)
        {
            g.positionChanged(oldp, newp);
        }
    }


    public void energy(float changeEnergy)
    {
        this.energy=this.energy+changeEnergy;
    }

    public void move(int weightMap, int heightMap) {
        Vector2d newposition;
        newposition = this.position.add(this.direction.toUnitVector());
        newposition.x= (newposition.x%(heightMap));
        newposition.y= (newposition.y%(weightMap));
        if(newposition.x == -1){
            newposition.x=heightMap-1;
        }
        if(newposition.y==-1){
            newposition.y=weightMap-1;
        }
        positionChanged(this.position,newposition);
        this.position = newposition;
        }

    public Animal()
    {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }
    public Animal(IWorldMap map) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.worldMap = map;
        this.obserator=new LinkedList<>();

    }
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.direction = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
        this.worldMap = map;
        this.position = initialPosition;
        this.obserator = new LinkedList<>();
    }


    public Color toColor() {
        if (energy == 0) return new Color(114, 2, 2);
        if (energy < 0.25 * startEnergy) return new Color(175, 9, 9);
        if (energy < 0.5 * startEnergy) return new Color(238, 61, 61);
        if (energy < 0.75 * startEnergy) return new Color(255, 179, 179);
        if (energy <  3 * startEnergy) return new Color(0, 45, 5);
        if (energy <  2 * startEnergy) return new Color(13, 151, 27);
        if (energy <  startEnergy) return new Color(60, 255, 78);
        return new Color(142, 243, 143);
    }

}




