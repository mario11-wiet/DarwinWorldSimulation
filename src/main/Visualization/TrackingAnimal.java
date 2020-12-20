package Visualization;


import Constants.Constants;
import Images.Image;
import Images.ImageFactory;
import Map.MapSimulation;
import Objects.Animal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.JOptionPane.showInputDialog;


class TrackingAnimal extends JDialog implements ActionListener {

    private Constants constants = new Constants();
    public MapSimulation mapSimulation;
    public Animal animal;
    public int widht = (int) ((constants.heightMapInCale * ((float) constants.widthMap1 / constants.heightMap1)) + 600);
    public int height = 200;
    public JLabel text1;
    public JLabel text2;
    public JLabel text3;
    public JButton track;
    public int result;
    public MonitoringAnimal monitoringAnimal;
    public int textSize = (int) (constants.widthMap1 / constants.heightMap1) * 12;

    public TrackingAnimal(Animal animal, MapSimulation mapSimulation) throws IOException, IllegalAccessException {
        this.animal = animal;
        this.mapSimulation = mapSimulation;
        this.monitoringAnimal = new MonitoringAnimal(animal, mapSimulation);
        if (widht > 2000) {
            widht = 2000;
        }
        iniitializeLayout();
        initializeVariables();

    }

    private void iniitializeLayout() {
        this.setSize(widht / 2, height);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        text1 = new JLabel("Geny zwierzaka");
        this.textModyfication(text1);

        text3 = new JLabel(animal.genes.toString());
        this.textModyfication(text3);

        text2 = new JLabel("Pozycja zwierzaka: " + animal.getPosition());
        this.textModyfication(text2);

        track = new JButton("TRACK");
        track.addActionListener(this);
        track.setFont(new Font("Courier New", (Font.BOLD | Font.ITALIC), textSize * 2));
        track.setForeground(Color.BLACK);
        add(track);

    }

    void textModyfication(JLabel text) {
        EmptyBorder border = new EmptyBorder(0, widht / 5, 0, widht / 5);
        text.setFont(new Font("Courier New", (Font.BOLD | Font.ITALIC), textSize));
        text.setForeground(Color.BLACK);
        text.setBorder(border);
        add(text);
    }

    void initializeVariables() throws IllegalAccessException {
        setTitle(constants.TITLESECONDWINDOWS);
        setIconImage(Objects.requireNonNull(ImageFactory.createImage(Image.AnimalPhoto)).getImage());
        setLocation(300, 100);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.animal.numberOfChildrenAfterFollow = this.animal.numberOfChildren;
        this.animal.follow = true;
        this.animal.trackingAnimal = true;
        this.animal.childrenFollowAnimal = true;
        String s1 = showInputDialog(
                null
        , "Podaj dzień w którym mam ci pokazać statystyki, jeśli zwierzak prze zyje", "Statistics Day", JOptionPane.PLAIN_MESSAGE);
        int i = 0;
        if (s1 != null) {
            i = Integer.parseInt(s1);
        }
            mapSimulation.statisticDay = i;
            if (mapSimulation.statisticDay < mapSimulation.day) try {
                throw new IllegalAccessException("Ta epoka już była");
            } catch (IllegalAccessException illegalAccessException) {
                illegalAccessException.printStackTrace();
            }
            try {
                monitoringAnimal = new MonitoringAnimal(animal, mapSimulation, mapSimulation.statisticDay);
            } catch (IllegalAccessException ioException) {
                ioException.printStackTrace();
            }

        }

    
}
