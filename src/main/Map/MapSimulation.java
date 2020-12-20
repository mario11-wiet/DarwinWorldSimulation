package Map;
import Interface.IEngine;
import Interface.IWorldMap;
import Maths.GenesAnimal;
import Maths.Vector2d;
import Objects.Animal;
import Objects.Grass;
import Constants.Constants;

import java.util.*;

public class MapSimulation implements IEngine, IWorldMap {
    private Constants constants=new Constants();
    private HashMap<Vector2d,List<Animal>> animalHashMap;
    private HashMap<Vector2d, Grass> grassHashMap;
    private List<Animal> animalList;
    private List<Grass> grassList;
    public int widthMap;
    public int heightMap;
    private int numberOfAnimal;
    private int grassInTheBeginning;
    private float jungleRatio;
    private Vector2d lowerLeftJungle;
    private Vector2d upperRightJungle;
    public int jungleWidth;
    public int jungleHeight;
    public int numberOfChildAniaml;
    public int numborOfDeadAnimal;
    public int allDayLiveDeadAnimal;
    public int statisticDay=Integer.MAX_VALUE;
    Random genarator = new Random();
    public int day=0;

    public MapSimulation(int numberOfAnimal, int widthMap, int heightMap, int grassInTheBeginning,
                         float jungleRatio) throws InterruptedException, IllegalAccessException {

        this.animalList = new ArrayList<>();
        this.grassInTheBeginning=grassInTheBeginning;
        this.animalHashMap =new HashMap<>();
        this.grassHashMap= new HashMap<>();
        this.grassList= new ArrayList<>();
        this.jungleRatio=jungleRatio;
        this.heightMap=heightMap;
        this.widthMap=widthMap;
        this.numberOfAnimal=numberOfAnimal;

        int widthJungle= (int) (this.widthMap*this.jungleRatio);
        int heightJungle= (int) (this.jungleRatio*this.heightMap);

        int lx=0;
        int ly=0;
        int rx=0;
        int ry=0;

        if(widthJungle%2!=this.widthMap%2)
        {
            widthJungle=widthJungle+1;
        }
        if(heightJungle%2!=this.heightMap%2)
        {
            heightJungle=heightJungle+1;
        }
        lx=(this.widthMap-widthJungle)/2;
        rx=this.widthMap-lx;
        ly=(this.heightMap-heightJungle)/2;
        ry=this.heightMap-ly;
        this.lowerLeftJungle= new Vector2d(lx, ly);
        this.upperRightJungle= new Vector2d(rx, ry);
        this.jungleWidth = rx-lx;
        this.jungleHeight = ry-ly;
        startDay();
        day++;

    }

    public void oneDaySymulation() throws InterruptedException {
        {
            deadAnimals();
            runAnimal();
            eatingByAnimals();
            copulationAnimal();
            for(int i=0;i<constants.numberOfGrassInOneDay;i++) {
                spawnGrassInOneDay();
            }
            lostEnergyInOneDay();

            day++;

        }
    }
    public void startDay()
    {
        int numberOfAnimal=getNumberOfAnimal();
        Vector2d vector2d;
        MapDirection randomDirecetion;
        GenesAnimal genes=new GenesAnimal();
        for(int i = 0; i < numberOfAnimal && widthMap*heightMap*0.6>i ; i++) {
            randomDirecetion= OptionsParser.parse(genarator.nextInt(8));
            genes.createGenesForAnimal();
            vector2d=addStartObject();
            if(vector2d==null){break;}
            Animal addAnimals = new Animal(constants.startEnergy1,genes,vector2d,
                    randomDirecetion);
            addAnimal(addAnimals,false);
            addAnimals.firstDay=day;
        }

        int grassInTheBeginning=getGrassInTheBeginning();
        boolean error=false;
        int numberOfDraws=0;
        for (int i=0;i<grassInTheBeginning && error!=true;i++)
        {
            vector2d=addStartObject();
            if(vector2d==null)
            {
                error=true;
            }
            Grass grass=new Grass(vector2d);
            addGrass(grass);
            numberOfDraws=numberOfDraws+1;
        }


    }
    public Vector2d addStartObject(){
        int randomPositionX;
        int randomPositionY;
        randomPositionX=genarator.nextInt(widthMap);
        randomPositionY=genarator.nextInt(heightMap);
        int numberOfDraws=0;
        Vector2d vector2d= new Vector2d(randomPositionX, randomPositionY);
        while (isOccupied(vector2d))
        {
            randomPositionX=genarator.nextInt(widthMap);
            randomPositionY=genarator.nextInt(heightMap);
            vector2d= new Vector2d(randomPositionX, randomPositionY);
            numberOfDraws=numberOfDraws+1;
            if(numberOfDraws>10){
                break;
            }
        }
        if(numberOfDraws>=10) return null;
        return vector2d;
    }
    public void addAnimal(Animal animal, boolean isChangePosition)
    {
        Vector2d vector2d = animal.getPosition();
        List<Animal> list = animalHashMap.get(vector2d);
        if(list==null){
            List<Animal> newList= new LinkedList<>();
            newList.add(animal);
            animalHashMap.put(vector2d, newList);
        }
        else if(list!=null)
        {
            list.add(animal);
        }
        if(!isChangePosition)
        {
            animalList.add(animal);
        }

    }
    public void removeAnimal(Animal animal, boolean isChangePosition){

        Vector2d vector2d = animal.getPosition();
        List<Animal> list = animalHashMap.get(vector2d);
        if(list!=null){
            list.remove(animal);
            if(list.size()==0)
            {
                animalHashMap.remove(vector2d);
            }
        }
        if(!isChangePosition)
        {
            animalList.remove(animal);
        }

    }

    public void runAnimal() throws InterruptedException {
        int numerOfGene;
        GenesAnimal genesAnimal;
        List<Animal> list = getAnimalList();
        int listSize=list.size();
        for (int i=0;i<listSize;i++) {
            Animal animal= list.get(i);
            genesAnimal = list.get(i).getGenes();
            numerOfGene = genarator.nextInt(32);
            list.get(i).direction = OptionsParser.parse((
                    OptionsParser.parseDirection(list.get(i).getDirection()) +
                            genesAnimal.getGenes(numerOfGene)) % 8);

            removeAnimal(list.get(i),true);
            animal.move(heightMap, widthMap);
            addAnimal(animal,true);
        }



    }
    public void eatingByAnimals(){
        List<Grass> removeGrass= new ArrayList<>();
        for(Grass grass: grassHashMap.values())
        {
            List<Animal> animals = animalHashMap.get(grass.getPosition());
            if(animals!= null && animals.size()>0)
            {
                animals.sort(Comparator.comparing(animal -> animal.getEnergy()));
                List<Animal> animalWithMostEnergy = new ArrayList<>();
                animalWithMostEnergy.add(animals.get(animals.size()-1));
                for(int i=animals.size()-2;i>0;i--)
                {
                    if(animals.get(i).getEnergy()==animals.get(animals.size()-1).getEnergy())
                    {
                        animalWithMostEnergy.add(animals.get(i));

                    }
                    else {
                        break;
                    }
                }
                for (Animal animal: animalWithMostEnergy)
                {
                    animal.energy(constants.grassEnergy1/animalWithMostEnergy.size());
                    removeGrass.add(grass);
                }
            }
        }
        for (Grass grass: removeGrass)
        {
            grassList.remove(grass);
            grassHashMap.remove(grass.getPosition());
        }
    }
    public void lostEnergyInOneDay()
    {
        for (List<Animal> animals : animalHashMap.values())
        {
            if (animals != null && animals.size() > 0){
                for (Animal animal : animals) {
                    animal.energy(-constants.energyInOneDay1);
                }
            }
        }
    }
    public void deadAnimals(){
        List<Animal> toAdd=new ArrayList<>();

        for (List<Animal> animals : animalHashMap.values())
        {
            if (animals != null && animals.size() > 0)
            {
                for (Animal animal : animals)
                {
                    if(animal.getEnergy()<=0)
                    {
                        toAdd.add(animal);

                    }
                }
            }
        }
        for(Animal animal:toAdd){
            numborOfDeadAnimal+=1;
            allDayLiveDeadAnimal+=(day-animal.firstDay);
            removeAnimal(animal, false);
        }
    }
    public void copulationAnimal(){
        List<Animal> toAdd=new ArrayList<>();
        for(List<Animal> animalList: animalHashMap.values())
        {
            if(animalList!=null && animalList.size() >= 2) {

                animalList.sort(Comparator.comparing(animal -> animal.getEnergy()));
                Animal animalFirstInCopulation = animalList.get(animalList.size() - 1);
                Animal animalSecondInCopulation = animalList.get(animalList.size() - 2);
                if (animalFirstInCopulation.getEnergy() >= constants.energyToCopulation1 &&
                        animalSecondInCopulation.getEnergy() >= constants.energyToCopulation1) {
                    Vector2d positionChildren = randomPosistionForNewChildren(animalFirstInCopulation.getPosition());
                    Animal children = new Animal((int) (animalSecondInCopulation.getEnergy()*0.25+
                            animalFirstInCopulation.getEnergy()*0.25),
                            positionChildren, OptionsParser.parse(genarator.nextInt(8)),
                            animalFirstInCopulation.getGenes(),
                            animalSecondInCopulation.getGenes());
                    animalFirstInCopulation.numberOfChildren+=1;
                    animalSecondInCopulation.numberOfChildren+=1;
                    toAdd.add(children);
                    if(animalFirstInCopulation.childrenFollowAnimal || animalSecondInCopulation.childrenFollowAnimal)
                    {
                        children.childrenFollowAnimal=true;
                        numberOfChildAniaml++;
                    }
                    animalFirstInCopulation.energy((float) (-animalFirstInCopulation.getEnergy()*0.25));
                    animalSecondInCopulation.energy((float) (-animalSecondInCopulation.getEnergy()*0.25));
                }
            }
        }
        for(Animal animal:toAdd){
            addAnimal(animal, false);
            animal.firstDay=day;

        }
    }

    public Vector2d randomPosistionForNewChildren(Vector2d position)
    {
        List<Vector2d> freePossition=new ArrayList<>();
        List<Vector2d> allPossition=new ArrayList<>();
        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                Vector2d vector=position.add(new Vector2d(i, j));
                if(vector.x>widthMap-1)
                {
                    vector.x=vector.x%(widthMap);
                }
                if(vector.y>heightMap-1)
                {
                    vector.y=vector.y%(heightMap);
                }
                if(!isOccupied(vector)) {
                    freePossition.add(vector);
                }
                allPossition.add(vector);
            }
        }
        if(freePossition.size()!=0)
        {
            return freePossition.get(genarator.nextInt(freePossition.size()));
        }
        else {
            return allPossition.get(genarator.nextInt(allPossition.size()));
        }

    }
    public void addGrass(Grass grass)
    {
        Vector2d pleaceGrass=grass.getPosition();
        grassList.add(grass);
        grassHashMap.put(pleaceGrass, grass);

    }
    public void spawnGrassInOneDay()
    {
        int lxJungle= getLowerLeftJungle().x;
        int rxJungle= getUpperRightJungle().x;
        int lyJungle= getLowerLeftJungle().y;
        int ryJungle= getUpperRightJungle().y;
        int numberOfDraws=0;
        while (numberOfDraws<(2*(rxJungle-lxJungle)*(ryJungle-lyJungle)))
        {
            Vector2d positionForGrass = new Vector2d(genarator.nextInt(rxJungle-lxJungle)+lxJungle,
                    genarator.nextInt(ryJungle-lyJungle)+lyJungle);
            if(!isOccupied(positionForGrass)){
                Grass newGrass= new Grass(positionForGrass);
                addGrass(newGrass);
                break;
            }
            numberOfDraws=numberOfDraws+1;
        }
        numberOfDraws=0;
        while (numberOfDraws<(heightMap*widthMap))
        {
            Vector2d positionForGrass = new Vector2d(genarator.nextInt(widthMap),
                    genarator.nextInt(heightMap));
            if(!isOccupied(positionForGrass) && !(positionForGrass.x>lxJungle &&
                    positionForGrass.x<rxJungle && positionForGrass.y>lyJungle
                    && positionForGrass.y<ryJungle))
            {
                Grass newGrass= new Grass(positionForGrass);
                addGrass(newGrass);
                break;
            }
            numberOfDraws=numberOfDraws+1;

        }

    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if (this.object(position) != null)
        {
            return true;
        }
        return false;
    }

    @Override
    public Object object(Vector2d position) {
        List<Animal> list = animalHashMap.get(position);
        if(list==null)
        {
            return grassHashMap.get(position);
        }
        if(list.size()==0)
        {
            return grassHashMap.get(position);
        }

        else return list.get(0);
    }

    public HashMap<Vector2d, List<Animal>> getAnimalHashMap() {
        return this.animalHashMap;
    }

    Vector2d minValue() {
        return (new Vector2d(0,0));
    }
    public List<Grass> getGrassList(){return grassList;
    }
    public int getDay() { return day;}
    public HashMap<Vector2d, Grass> getGrassHashMap(){return grassHashMap;}
    public int getWidthMap()
    {
        return this.widthMap;
    }
    public int getNumberOfAnimal()
    {
        return this.numberOfAnimal;
    }
    public int getGrassInTheBeginning(){
        return this.grassInTheBeginning;
    }
    public int getHeightMap(){
        return this.heightMap;
    }
    public Vector2d getUpperRightJungle(){
        return this.upperRightJungle;
    }
    public Vector2d getLowerLeftJungle(){
        return this.lowerLeftJungle;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
    public int getStartEnergy()
    {
        return constants.startEnergy1;
    }

    Vector2d maxValue() {
        return (new Vector2d(heightMap-1,widthMap-1));
    }
}