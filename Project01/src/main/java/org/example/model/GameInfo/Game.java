package org.example.model.GameInfo;

import org.example.model.User;

import java.util.ArrayList;

public class Game {
    private ArrayList<Trade> trades=new ArrayList();
    private Integer turnNumber=0;
    private Integer currentTurn=0;
    private  Map map;
    private ArrayList<Government> governments=new ArrayList<>();

    public Game(Integer turnNumber, Map map) {
        this.turnNumber = turnNumber;
        this.map = map;
    }

    public void addGovernment(Government government){
        this.governments.add(government);
    }
    public Government getGovernmentByUser(User user){return null;}

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
    public void increaseCurrentTurn(){
        this.currentTurn++;
    }
}
