package Visualization;

import Constants.Constants;
import Images.ImageFactory;
import Map.MapSimulation;
import Objects.Animal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class MonitoringAnimal extends JDialog{

    private Constants constants= new Constants();
    public MapSimulation mapSimulation;
    private Animal animal;
    public int widht= (int) ((constants.heightMapInCale*((float)constants.widthMap1/constants.heightMap1))+600);
    public int height=200;
    public JLabel text1;
    public JLabel text2;
    public JLabel text3;
    public JLabel text4;
    public int statisticday;
    public int cout=0;
    public GameMainFrame gameMainFrame;
    public int textSize=(int)(constants.widthMap1/constants.heightMap1)*12;

    public MonitoringAnimal(Animal animal, MapSimulation mapSimulation) throws IOException, IllegalAccessException {
        this.mapSimulation=mapSimulation;
        this.animal=animal;
        initialize();
        initializeVariabl();
        updateday();

    }
    public MonitoringAnimal(Animal animal, MapSimulation mapSimulation, int statisticday) throws IllegalAccessException {
        this.mapSimulation=mapSimulation;
        this.animal=animal;
        this.statisticday=statisticday;
        initialize();
        initializeVariabl();
        updateday();
    }

    public void initialize() {
        this.setSize(widht/2+15,height);
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        text1 = new JLabel("Pozycja: "+animal.getPosition());
        textModyfication(text1);

        text2 = new JLabel("Liczba dzieci:" +(animal.numberOfChildren-animal.numberOfChildrenAfterFollow));
        textModyfication(text2);

        text3 = new JLabel("Liczba potomków:"+ mapSimulation.numberOfChildAniaml);
        textModyfication(text3);

        text4 =new JLabel("Zwierze żyje!!!");
        textModyfication(text4);


    }
    void textModyfication(JLabel text) {
        EmptyBorder border = new EmptyBorder(0,  widht/5 , 0, widht/5);
        text.setFont(new Font("Courier New", (Font.BOLD | Font.ITALIC), textSize*2));
        text.setForeground(Color.BLACK);
        text.setBorder(border);
        add(text);
    }
    void initializeVariabl() throws IllegalAccessException {
        setTitle(constants.MONITORING);
        setIconImage(ImageFactory.createImage(Images.Image.AnimalPhoto).getImage());
        setLocation(300+widht/2,100);

    }

    public void updateday()
    {
        if(statisticday==mapSimulation.day)
        {
            JOptionPane.showMessageDialog(null,"Pozycja : "+this.animal.getPosition()+"\n"+
                    "Liczba dzieci: " +(this.animal.numberOfChildren-this.animal.numberOfChildrenAfterFollow)+"\n"+
                    "Liczba potomków: "+mapSimulation.numberOfChildAniaml+"\n" +
                    "Liczba energi: "+animal.getEnergy(),"Statystyki w "+ mapSimulation.day+" dniu", JOptionPane.PLAIN_MESSAGE);

        }
        text1.setText("Pozycja : "+this.animal.getPosition());
        text2.setText("Liczba dzieci:" +(this.animal.numberOfChildren-this.animal.numberOfChildrenAfterFollow));
        text3.setText("Liczba potomków:"+mapSimulation.numberOfChildAniaml );
        if(animal.getEnergy()<=0)
        {
            JOptionPane.showMessageDialog(null,"Słodziak nie żyje [*]","Ważna informacja!!!",JOptionPane.WARNING_MESSAGE);
            text4.setText("Zwierze zmarło w dniu: "+mapSimulation.getDay());
            animal.follow=false;
            for (Animal animals: mapSimulation.getAnimalList())
            {
                animals.childrenFollowAnimal=false;
                animals.follow=false;
            }
            mapSimulation.numberOfChildAniaml=0;
        }
        else
            {
            text4.setText("Zwierze żyje!!!");
        }
    }

}
