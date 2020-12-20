package Visualization;

import Constants.Constants;
import Images.Image;
import Images.ImageFactory;
import Map.MapSimulation;
import Maths.Statistics;
import Maths.Vector2d;
import Objects.Animal;
import Objects.Grass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class GamePanel extends JPanel implements MouseListener {

    private Constants constants=new Constants();
    private ImageIcon animalson;
    private ImageIcon grasson;
    private ImageIcon mapPhoto;
    private ImageIcon junglePhoto;
    public MapSimulation mapSimulation;
    private int height=constants.heightMapInCale;
    private float widht1=(height*((float)constants.widthMap1/constants.heightMap1));
    public Statistics statistics;
    private int widht= (int) widht1;
    public GameMainFrame gameMainFrame;
    private StatisticPanel statisticPanel;
    private ImageIcon backgroundImage;
    public boolean Domination=false;



    public GamePanel(MapSimulation mapSimulation,GameMainFrame gameMainFrame) throws IllegalAccessException {
        addMouseListener(this);
        this.mapSimulation = mapSimulation;
        this.gameMainFrame=gameMainFrame;
        this.statistics=new Statistics(mapSimulation);
        if(widht>2000)
        {
            widht=2000;
        }
        initializeVariables();
        initializeLayout();


    }

    private void initializeVariables() throws IllegalAccessException {
        this.animalson= ImageFactory.createImage(Image.AnimalPhoto);
        this.grasson= ImageFactory.createImage(Image.GrassPhoto);
        this.backgroundImage=ImageFactory.createImage(Image.BacKGroundPhoto);
        this.mapPhoto= ImageFactory.createImage(Image.MapPhoto);
        this.junglePhoto= ImageFactory.createImage(Image.JunglePhoto);


    }

    private void initializeLayout() {
        setPreferredSize(new Dimension(widht, height));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int widthScale = (int) (widht / constants.widthMap1);
        int heightScale = (int) (height / constants.heightMap1);
        g.drawImage(mapPhoto.getImage(), 0, 0, widht, height, null);

        g.drawImage(junglePhoto.getImage(), mapSimulation.getLowerLeftJungle().x * widthScale,
                mapSimulation.getLowerLeftJungle().y * heightScale,
                mapSimulation.jungleWidth * widthScale,
                mapSimulation.jungleHeight * heightScale, null);

            for (Grass grass : mapSimulation.getGrassList()) {
                g.setColor(grass.toColor());
                int y = (grass.getPosition()).y * heightScale;
                int x = (grass.getPosition()).x * widthScale;
                g.drawImage(grasson.getImage(), x, y, widthScale, heightScale, null);
            }
            for (Animal a : mapSimulation.getAnimalList()) {
                g.setColor(a.toColor());
                int y = (a.getPosition()).y * heightScale;
                int x = (a.getPosition()).x * widthScale;
                g.fillOval(x, y, widthScale, heightScale);
            }
        {
            for (Animal a : mapSimulation.getAnimalList()) {
                if(a.dominationGen){
                g.setColor(Color.blue);
                int y = (a.getPosition()).y * heightScale;
                int x = (a.getPosition()).x * widthScale;
                g.fillOval(x, y, widthScale, heightScale);
                a.dominationGen=false;
            }
            }
        }

    }


    public void doOneLoop() throws InterruptedException {
        update();
        repaint();

    }

    private void update() {
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if (!gameMainFrame.timer.isRunning()) {
            float objSizex=height/constants.heightMap1;
            float objSizey=widht/constants.widthMap1;
            int x = (int) (mouseEvent.getX() / objSizex);
            int y = (int) (mouseEvent.getY() / objSizey);
            Vector2d vector2d = new Vector2d(x, y);
            if (mapSimulation.isOccupied(vector2d)) {
                List<Animal> animals= mapSimulation.getAnimalHashMap().get(vector2d);
                animals.sort(Comparator.comparing(animal -> animal.getEnergy()));
                Animal animal = mapSimulation.getAnimalHashMap().get(vector2d).get(animals.size()-1);
                TrackingAnimal trackingAnimal = null;
                try {
                    trackingAnimal = new TrackingAnimal(animal, mapSimulation);
                } catch (IOException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                trackingAnimal.setVisible(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
