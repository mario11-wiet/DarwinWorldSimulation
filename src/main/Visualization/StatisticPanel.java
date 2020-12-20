package Visualization;

import Constants.Constants;
import Images.Image;
import Images.ImageFactory;
import Map.MapSimulation;
import Maths.Statistics;
import Objects.Animal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StatisticPanel extends JPanel implements ActionListener {

    private Constants constants= new Constants();
    public MapSimulation mapSimulation;
    private int widht = 600;
    private int height = constants.heightMapInCale;
    public GameMainFrame gameMainFrame;
    public JLabel text1;
    public JLabel text2;
    public JLabel text3;
    public JLabel text4;
    public JLabel text5;
    public JLabel text6;
    public JLabel text7;
    public JLabel text8;
    public JLabel text9;
    public JLabel text10;
    private JButton startButton;
    private JButton pauseButton;
    private JButton saveButton;
    private JButton showAnimalWithDominationGenes;
    public GamePanel gamePanel;
    public Statistics statistics;
    private JButton newWorld;
    SaveInformation saveInformation;
    private ImageIcon backgroundImage;


    public StatisticPanel(MapSimulation mapSimulation, GameMainFrame gameMainFrame) throws IOException, IllegalAccessException {
        this.mapSimulation = mapSimulation;
        this.gameMainFrame = gameMainFrame;
        this.statistics=new Statistics(mapSimulation);
        this.saveInformation=new SaveInformation(mapSimulation);
        gamePanel= new GamePanel(mapSimulation, gameMainFrame);
        initializeLayout();
        initializeVariables(mapSimulation);
    }

    private void initializeLayout() {
        setPreferredSize(new Dimension(600,height));
        setLayout(new FlowLayout(FlowLayout.RIGHT,40,20));

        text1 = new JLabel("Obecny dzień: " + mapSimulation.getDay());
        text2 = new JLabel("Liczba zwierząt: " + this.statistics.numberOfAnimal());
        text3 = new JLabel("Liczba trawy: " + statistics.numberOfGrass());
        text4 = new JLabel("Dominujący genotyp: " + statistics.dominatingGene());
        text5 = new JLabel("Średni poziom energi: " + statistics.aveageEnergyLevelInOneDay());
        text6 = new JLabel("Średnia długość życia " + statistics.averageLengthOfLifeInOneDay());
        text7 = new JLabel("Średnia liczba dzieci: " + statistics.averageNumberOfChildrenInOneDay());
        textModyfication(text1);
        textModyfication(text2);
        textModyfication(text3);
        textModyfication(text4);
        textModyfication(text5);
        textModyfication(text6);
        textModyfication(text7);

        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        buttonModicitation(startButton);
        buttonModicitation(pauseButton);

        EmptyBorder border3 = new EmptyBorder(0,  0 , 0, 0);
        text8 = new JLabel("Zapisz statystyki");
        textModyfication(text8);
        text8.setBorder(border3);

        saveButton = new JButton("Save");
        buttonModicitation(saveButton);
        EmptyBorder border4 = new EmptyBorder(0,  63 , 10, 63);
        saveButton.setBorder(border4);

        text9= new JLabel("Dodaj świat");
        textModyfication(text9);
        text9.setBorder(border3);

        newWorld=new JButton("New World");
        buttonModicitation(newWorld);
        EmptyBorder border5 = new EmptyBorder(0,  10 , 10, 10);
        newWorld.setBorder(border5);

        text10= new JLabel("Dominujący gen");
        textModyfication(text10);
        text10.setBorder(border3);

        showAnimalWithDominationGenes=new JButton("Show Gene");
        buttonModicitation(showAnimalWithDominationGenes);
        EmptyBorder border6 = new EmptyBorder(0,  10 , 10, 10);
        showAnimalWithDominationGenes.setBorder(border6);



    }

    private void initializeVariables(MapSimulation mapSimulation) throws IllegalAccessException {
        this.backgroundImage= ImageFactory.createImage(Image.BacKGroundPhoto);

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(),0,0, widht, height+3,null);



    }
    public void textModyfication(JLabel text){
        EmptyBorder border = new EmptyBorder(0,  widht , 0, 0);
        text.setFont(new Font("Courier New", (Font.BOLD | Font.ITALIC), 25));
        text.setForeground(Color.WHITE);
        text.setBorder(border);
        add(text);

    }
    public void buttonModicitation(JButton button){
        button.addActionListener(this);
        EmptyBorder border2 = new EmptyBorder(0,  53 , 10, 53);
        button.setBorder(border2);
        button.setFont(new Font("Courier New", (Font.BOLD | Font.ITALIC), 35));
        button.setForeground(Color.BLACK);
        add(button);

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton && !gameMainFrame.timer.isRunning())
        {
            gameMainFrame.timer.start();
        }
        if(e.getSource()==pauseButton && gameMainFrame.timer.isRunning())
        {
            gameMainFrame.timer.stop();
        }
        if(e.getSource()==saveButton && !gameMainFrame.timer.isRunning())
        {
            try {
                saveInformation.writeFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        if(e.getSource()==newWorld)
        {
            MapSimulation mapSimulation = null;
            GameMainFrame gameMainFrame = null;

            try {
                mapSimulation = new MapSimulation(constants.numberOfAnimal1,constants.widthMap1,
                        constants.heightMap1,constants.grassInTheBeginning1,
                            (float) constants.jungleRatio1);
            } catch (InterruptedException | IllegalAccessException interruptedException) {
                interruptedException.printStackTrace();
            }

            try {
                gameMainFrame =new GameMainFrame(mapSimulation,false);
            } catch (IOException | IllegalAccessException ioException) {
                ioException.printStackTrace();
            }

            gameMainFrame.startSimulation();

        }
        if(e.getSource()==showAnimalWithDominationGenes && !gameMainFrame.timer.isRunning())
        {
            for(Animal animal: mapSimulation.getAnimalList())
            {
                if(animal.genes.dominatingGene()==statistics.dominatingGene())
                {
                    animal.dominationGen=true;
                }
            }
            gameMainFrame.gamePanel.repaint();
        }
    }
    public void updateInformation() {
        text1.setText("Obecny dzień: " + mapSimulation.getDay());
        text2.setText("Liczba zwierząt: " + statistics.numberOfAnimal());
        text3.setText("Liczba trawy: " + statistics.numberOfGrass());
        text4.setText("Dominujący genotyp: " + statistics.dominatingGene());
        text5.setText("Średni poziom energi: " + statistics.aveageEnergyLevelInOneDay());
        if(statistics.aveageEnergyLevelInOneDay()<0)
        {
            text5.setText("Średni poziom energi: 0");
        }
        text6.setText("Średnia długość życia: " + statistics.averageLengthOfLifeInOneDay());
        text7.setText("Średnia liczba dzieci: " + statistics.averageNumberOfChildrenInOneDay());


    }
}
