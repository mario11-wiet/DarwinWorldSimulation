package Visualization;

import Map.MapSimulation;
import Maths.Statistics;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;

public class SaveInformation {

    public MapSimulation mapSimulation;
    public Statistics statistics;
    public int n =0;

    public SaveInformation(MapSimulation mapSimulation) {
        this.mapSimulation=mapSimulation;
        this.statistics=new Statistics(mapSimulation);
    }

    public void writeFile() throws IOException {
        FileWriter file = new FileWriter("src/main/SaveFile" + n +"."+ mapSimulation.day+ ".txt");
        file.write("Liczba zwierząt: " + statistics.numberOfAnimal()+"\n"+
                "Liczba trawy: " + statistics.numberOfGrass()+"\n"+
                "Dominujący genotyp: " + statistics.dominatingGene()+"\n"+
                "Średni poziom energi: " + statistics.aveageEnergyLevelInOneDay()+"\n"+
                "Średnia długość życia: " +  statistics.averageLengthOfLifeInOneDay()+"\n"+
                "Średnia liczba dzieci: " + statistics.averageNumberOfChildrenInOneDay()+"\n"+
                "Ilość pochowanych zwierząt [*]: "+ mapSimulation.numborOfDeadAnimal);
        file.close();
        n++;
        JOptionPane.showMessageDialog(null,"Teraz już zawsze będziesz wiedział co wydarzyło się dnia "+mapSimulation.day,"Zapisano plik", JOptionPane.PLAIN_MESSAGE);
    }
}
