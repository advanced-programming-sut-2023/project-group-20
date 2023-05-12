package model.GameInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class Granery extends Cellar {


    public static final ArrayList<String> foodNames = new ArrayList<>();

    static {
        foodNames.add("Apple");
        foodNames.add("Meat");
        foodNames.add("Cheese");
        foodNames.add("Bread");
    }

    public Granery(Government owner) {
        super(100.0 ,owner, "Granery", 350, 0, 100, 0, 0);
        setElements(foodNames.get(0), 0.0);
        setElements(foodNames.get(1), 0.0);
        setElements(foodNames.get(2), 0.0);
        setElements(foodNames.get(3), 0.0);
    }

    public HashMap<String, Double> getAllFoods() {
        return (super.getElements());
    }


}
