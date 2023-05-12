package org.example.model.GameInfo;

import java.util.HashMap;

public class StockPile {
    private final Government owner;
    HashMap<String, Integer> elements = new HashMap<>();

    public StockPile(Government owner) {
        this.owner = owner;
        setElements("stone",0);
        setElements("wood",0);
        setElements("iron",0);
        setElements("pitch",0);
        setElements("wheat",0);
        setElements("flour",0);
    }
    public void setElements(String elementsKey,Integer amount){
        elements.put(elementsKey,amount);
    }

    public HashMap<String, Integer> getElements() {
        return elements;
    }

}
