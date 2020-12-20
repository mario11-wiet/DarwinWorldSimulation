package StartParameters;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Read {
    private static Read instance = null;
    public int numberOfAnimal2;
    public int widthMap2;
    public int heightMap2;
    public float jungleRatio2;
    public int startEnergy2;
    public int grassEnergy2;
    public int energyInOneDay2;
    public int grassInTheBeginning2;
    public int energyToCopulation2;
    public int speed2;
    public int heightMapInCale;
    public int numberOfGrassInOneDay;

    public static Read getInstance() {
        if (instance == null) {
            Gson gson = new Gson();
            String path = "./src/StartParameters.json";
            try {
                JsonReader reader = new JsonReader(new FileReader(path));
                instance = gson.fromJson(reader, Read.class);
            } catch (FileNotFoundException e) {
                instance = new Read();
                e.printStackTrace();
            }
        }
        return instance;
    }
}
