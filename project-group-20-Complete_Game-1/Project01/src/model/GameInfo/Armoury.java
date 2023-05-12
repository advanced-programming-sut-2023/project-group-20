package model.GameInfo;


public class Armoury extends Cellar{

    public Armoury(Government owner) {
        super(100.0 ,owner, "StockPile", 350, 0, 100, 0, 0);
        setElements("LeatherArmour",0.0);
        setElements("Mace",0.0);
        setElements("Sword",0.0);
        setElements("MetalArmour",0.0);
        setElements("Bow",0.0);
        setElements("Crossbow",0.0);
        setElements("Spear",0.0);
        setElements("Pike",0.0);
    }

}
