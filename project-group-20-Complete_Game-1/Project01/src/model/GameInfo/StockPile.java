package model.GameInfo;


public class StockPile extends Cellar{

    public StockPile(Government owner) {
        super(20.0 ,owner, "StockPile", 350, 0, 100, 0, 0);
        setElements("stone",0.0);
        setElements("wood",0.0);
        setElements("iron",0.0);
        setElements("pitch",0.0);
        setElements("wheat",0.0);
        setElements("flour",0.0);
    }



}
