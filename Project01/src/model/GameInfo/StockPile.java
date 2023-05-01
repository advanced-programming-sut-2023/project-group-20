package model.GameInfo;

import model.User;

import java.util.HashMap;

public class StockPile {
    private Government owner;
    HashMap elements = new HashMap<String, Integer>();

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
        elements.put(((Integer) elements.get(elementsKey)),amount);
    }

    public HashMap getElements() {
        return elements;
    }
}
