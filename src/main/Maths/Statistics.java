package Maths;

import Map.MapSimulation;
import Objects.Animal;

public class Statistics {

    private MapSimulation mapSimulation;


    public Statistics(MapSimulation mapSimulation){
        this.mapSimulation=mapSimulation;
    }

    public int numberOfAnimal()
    {
        return mapSimulation.getAnimalList().size();
    }
    public int numberOfGrass()
    {
        return mapSimulation.getGrassList().size();
    }
    public int dominatingGene()
    {
        int [] count=new int[8];
        for (int i=0;i<8;i++)
        {
            count[i]=0;
        }
        for(Animal animal: mapSimulation.getAnimalList())
        {
            count[animal.genes.dominatingGene()]+=1;
        }
        int dominating=0;
        for(int i=1;i<count.length;i++)
        {
            if(count[i]>count[dominating])
            {
                dominating=i;
            }
        }
        return dominating;
    }
    public float aveageEnergyLevelInOneDay()
    {
        float sumEnergy=0;
        for (Animal animal:mapSimulation.getAnimalList())
        {
            sumEnergy+=animal.getEnergy();
        }
        if(mapSimulation.getAnimalList().size()==0)
        {
            return 0;
        }
        return sumEnergy/mapSimulation.getAnimalList().size();
    }
    public float averageLengthOfLifeInOneDay()
    {
        if(mapSimulation.numborOfDeadAnimal==0)
        {
            return 0;
        }
        float live=(float) (mapSimulation.allDayLiveDeadAnimal*1.000/mapSimulation.numborOfDeadAnimal);
        return live;
    }
    public float averageNumberOfChildrenInOneDay()
    {
        int sumChildren=0;
        for(Animal animal: mapSimulation.getAnimalList())
        {
            sumChildren+=animal.numberOfChildren;
        }
        if(mapSimulation.getAnimalList().size()==0)
        {
            return 0;
        }
        return (float) sumChildren/mapSimulation.getAnimalList().size();
    }

    public Animal followAnimal()
    {
        for(Animal animal: mapSimulation.getAnimalList())
        {
            if(animal.follow)
            {
                return animal;
            }
        }
        return null;
    }

}
