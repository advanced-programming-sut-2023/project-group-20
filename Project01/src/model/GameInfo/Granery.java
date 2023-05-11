package model.GameInfo;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Granery {
    private Government owner;
    private HashMap<String, Double> allFoods = new HashMap<>();
    public static final ArrayList<String> foodNames = new ArrayList<>();

    static {
        foodNames.add("Apple");
        foodNames.add("Meat");
        foodNames.add("Cheese");
        foodNames.add("Bread");
    }

    public Granery(Government owner) {
        this.owner = owner;
        setAllFoods(foodNames.get(0), 0.0);
        setAllFoods(foodNames.get(1), 0.0);
        setAllFoods(foodNames.get(2), 0.0);
        setAllFoods(foodNames.get(3), 0.0);
    }

    public void setAllFoods(String elementsKey, Double amount) {
        allFoods.put(elementsKey, amount);
    }

    public HashMap<String, Double> getAllFoods() {
        return allFoods;
    }
}
