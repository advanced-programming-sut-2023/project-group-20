package org.example.model.GameInfo;

import java.util.HashMap;

public class Armoury {
    private Government owner;
    HashMap<String, Integer> elements = new HashMap<>();

    public Armoury(Government owner) {
        this.owner = owner;
        setElements("LeatherArmour",0);
        setElements("Mace",0);
        setElements("Sword",0);
        setElements("MetalArmour",0);
        setElements("Bow",0);
        setElements("Crossbow",0);
        setElements("Spear",0);
        setElements("Pike",0);
    }
    public void setElements(String elementsKey,Integer amount){
        elements.put(elementsKey,amount);
    }

    public int getElements(String weaponName) {
        return elements.get(weaponName);
    }
}
