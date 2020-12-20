package Constants;

import StartParameters.Read;

public class Constants {

    public String MONITORING;
    public String TITLESECONDWINDOWS;
    public String TITLE;
    public String AnimalsPhoto;
    public String GrassPhoto;
    public String JunglePhoto;
    public String MapPhoto;
    public String BacKGroundPhoto;
    public int numberOfGrassInOneDay;
    public int heightMapInCale;
    public int numberOfAnimal1;
    public int grassInTheBeginning1;
    public int widthMap1;
    public int heightMap1;
    public int gameSpeed;
    public int startEnergy1;
    public int energyInOneDay1;
    public int grassEnergy1;
    public int energyToCopulation1;
    public double jungleRatio1;

    public Constants() throws IllegalAccessException
    {
        this.MONITORING="MONITORING ANIMAL";
        this.TITLESECONDWINDOWS="INFORMATION ABOUT ANIMAL";
        this.TITLE="DarwinWorld";
        this.AnimalsPhoto="src/main/Images/ak.png";
        this.GrassPhoto="src/main/Images/t3.png";
        this.JunglePhoto="src/main/Images/img.png";
        this.MapPhoto="src/main/Images/t0.jpg";
        this.BacKGroundPhoto="src/main/Images/w1.png";
        this.numberOfGrassInOneDay=Read.getInstance().numberOfGrassInOneDay;
        this.heightMapInCale =Read.getInstance().heightMapInCale;
        this.numberOfAnimal1 = Read.getInstance().numberOfAnimal2;
        this.grassInTheBeginning1 = Read.getInstance().grassInTheBeginning2;
        this.widthMap1 = Read.getInstance().widthMap2;
        this.heightMap1 = Read.getInstance().heightMap2;
        this.gameSpeed = Read.getInstance().speed2;
        this.startEnergy1 = Read.getInstance().startEnergy2;
        this.energyInOneDay1 = Read.getInstance().energyInOneDay2;
        this.grassEnergy1 = Read.getInstance().grassEnergy2;
        this.energyToCopulation1 = Read.getInstance().energyToCopulation2;
        this.jungleRatio1 = Read.getInstance().jungleRatio2;

        if (widthMap1<=0) throw new IllegalAccessException("Szerokość mapy musi być dodatnia");
        if (heightMap1<=0) throw new IllegalAccessException("Wysokość mapy musi być dodatnia");
        if(numberOfAnimal1<=0) throw new IllegalAccessException("Liczba zwierząt musi być naturalna");
        if(numberOfAnimal1>(widthMap1*heightMap1)) throw new IllegalAccessException("Za dużo zwierząt na mapie");
        if(grassInTheBeginning1+numberOfAnimal1>widthMap1*heightMap1) throw new IllegalAccessException("Za mało miejsce dla trawy");
        if(jungleRatio1>1 || jungleRatio1<0) throw new IllegalAccessException("Rozmiar jungli musi być z przedziału od 0 do 1");
        if(energyInOneDay1>widthMap1*heightMap1) throw new IllegalAccessException("Za dużo próbujesz dodać trawy dziennie");
    }
}
