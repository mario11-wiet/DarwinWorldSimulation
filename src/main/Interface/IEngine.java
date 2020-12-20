package Interface;

import Objects.Animal;
import Maths.Vector2d;

import java.util.HashMap;
import java.util.List;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IEngine {
    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     *
     */
    void runAnimal() throws InterruptedException;
    void oneDaySymulation() throws InterruptedException;
    void startDay();
    Vector2d addStartObject();
    void addAnimal(Animal animal, boolean isChangePosition);
    void removeAnimal(Animal animal, boolean isChangePosition);
    void eatingByAnimals();
    void lostEnergyInOneDay();
    void deadAnimals();
    void copulationAnimal();
    Vector2d randomPosistionForNewChildren(Vector2d position);
    void spawnGrassInOneDay();



    //List<Animal> getAnimalList();
    HashMap<Vector2d, List<Animal>> getAnimalHashMap();
}