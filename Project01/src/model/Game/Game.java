package model.Game;

import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class Game {
    private ArrayList<Trade> trades=new ArrayList();
    private Integer turnNumber=0;
    private  Map map=new Map();
    private ArrayList<Government> governments=new ArrayList<>();
    public void addGovernment(Government government){
        this.governments.add(government);
    }
    public Government getGovernmentByUser(User user){}

    public void setTurnNumber(Integer turnNumber) {
        this.turnNumber = turnNumber;
    }

    public Integer getTurnNumber() {
        return turnNumber;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<Government> getGovernments() {
        return governments;
    }
    public void addTrade(Trade trade) {
        this.trades.add(trade);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }
}
