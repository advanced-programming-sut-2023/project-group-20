package model.GameInfo;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Granery {
    private User owner;
    private HashMap allFoods=new HashMap<String,Double>();
    public static final ArrayList foodNames=new ArrayList<String>();
    static {
        foodNames.add("Apple");
        foodNames.add("Meat");
        foodNames.add("Cheese");
        foodNames.add("Bread");
    }
    public Granery(User owner) {
        this.owner = owner;
        setAllFoods(((String) foodNames.get(0)),0);
        setAllFoods(((String) foodNames.get(1)),0);
        setAllFoods(((String) foodNames.get(2)),0);
        setAllFoods(((String) foodNames.get(3)),0);

    }
    public void setAllFoods(String elementsKey,Integer amount){
        allFoods.put(((Integer) allFoods.get(elementsKey)),amount);
    }

    public HashMap getAllFoods() {
        return allFoods;
    }
}
