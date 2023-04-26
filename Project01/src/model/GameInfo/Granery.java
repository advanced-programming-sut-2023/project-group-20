package model.GameInfo;

import model.User;

import java.util.HashMap;

public class Granery {
    private User owner;
    private HashMap allFoods=new HashMap<String,Integer>();

    public Granery(User owner) {
        this.owner = owner;
        setAllFoods("Apple",0);
        setAllFoods("Meat",0);
        setAllFoods("Cheese",0);
        setAllFoods("Bread",0);
    }
    public void setAllFoods(String elementsKey,Integer amount){
        allFoods.put(((Integer) allFoods.get(elementsKey)),amount);
    }
}
