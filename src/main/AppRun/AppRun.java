package AppRun;

import Constants.Constants;
import Map.MapSimulation;
import Visualization.GameMainFrame;

import java.awt.*;
import java.io.IOException;

public class AppRun {
    public static void main(String[] args) throws IllegalAccessException {
        Constants constants= new Constants();
        EventQueue.invokeLater(()->{
            MapSimulation mapSimulation = null;
            GameMainFrame gameMainFrame = null;
            try {
                mapSimulation = new MapSimulation(constants.numberOfAnimal1,constants.widthMap1,
                        constants.heightMap1,constants.grassInTheBeginning1,
                        (float) constants.jungleRatio1);
            } catch (InterruptedException | IllegalAccessException e) {
                e.printStackTrace();
            }

            try {
                gameMainFrame =new GameMainFrame(mapSimulation,true);
            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
            }

            gameMainFrame.startSimulation();

        });
    }
}
