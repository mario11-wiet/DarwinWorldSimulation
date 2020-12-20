package Visualization;

import Constants.Constants;
import Images.Image;
import Images.ImageFactory;
import Map.MapSimulation;
import Maths.Statistics;
import Objects.Animal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class GameMainFrame extends JFrame implements ActionListener{

    private MapSimulation mapSimulation;
    public Timer timer;
    public GamePanel gamePanel;
    public boolean firstWorld;
    public StatisticPanel statisticPanel;
    public MonitoringAnimal monitoringAnimal;
    public Statistics statistics;
    public Animal animal;
    public Constants constants= new Constants();
    public boolean b=true;



    public GameMainFrame(MapSimulation mapSimulation,boolean firstWorld) throws IOException, IllegalAccessException {
        timer = new Timer(constants.gameSpeed, this);
        this.firstWorld=firstWorld;
        this.statistics=new Statistics(mapSimulation);

        iniitializeLayout(mapSimulation);

    }

    private void iniitializeLayout(MapSimulation mapSimulation) throws IOException, IllegalAccessException {

        this.mapSimulation = mapSimulation;
        gamePanel=new GamePanel(mapSimulation, this);
        add(gamePanel,BorderLayout.LINE_START);
        statisticPanel=new StatisticPanel(mapSimulation, this);
        add(statisticPanel,BorderLayout.LINE_END);
        setTitle(constants.TITLE);
        setIconImage(ImageFactory.createImage(Image.AnimalPhoto).getImage());
        pack();
        setLocation(300,300);
        if(this.firstWorld) {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        setResizable(false);
        setVisible(true);

    }
    public void startSimulation(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            mapSimulation.oneDaySymulation();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        try {
            gamePanel.doOneLoop();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        statisticPanel.updateInformation();
        if (statistics.followAnimal() != null && b) {
            System.out.println(statistics.followAnimal());
            animal = statistics.followAnimal();
            for(Animal animal: mapSimulation.getAnimalList()) {
                if (animal.follow) {
                    try {
                        this.monitoringAnimal = new MonitoringAnimal(animal, mapSimulation,mapSimulation.statisticDay);
                    } catch (IllegalAccessException ioException) {
                        ioException.printStackTrace();
                    }
                    b = false;
                    monitoringAnimal.setVisible(true);
                }
            }

        }
        if(animal!=null && statistics.followAnimal() != null) {
            for(Animal animal: mapSimulation.getAnimalList()) {
                if (animal.follow) {
                    monitoringAnimal.updateday();
                }}
        }
        else {
            b=true;
        }

    }
}