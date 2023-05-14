package org.example.model.GameInfo;

import java.util.ArrayList;

public class Game {
    private ArrayList<Trade> trades = new ArrayList<>();
    private Integer turnNumber;
    private Integer currentTurn = 1;
    private Map map;
    private Home selectedBuildingHome;
    private ArrayList<Troop> selectedTroops;
    private Home selectedTroopsHome;
    private ArrayList<Government> governments = new ArrayList<>();
    private int playerNumber;

    public Game(Integer turnNumber, Map map, int playerNumber) {
        this.turnNumber = turnNumber;
        this.map = map;
//        this.governments = governments;
        this.playerNumber = playerNumber;
    }

    public void addGovernment(Government government) {
        this.governments.add(government);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    private void setTurnNumber(Integer turnNumber) {
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

    public void increaseCurrentTurn() {
        this.currentTurn++;
    }

    public Home getSelectedBuildingHome() {
        return selectedBuildingHome;
    }

    public void setSelectedBuildingHome(Home selectedBuildingHome) {
        this.selectedBuildingHome = selectedBuildingHome;
    }

    public ArrayList<Troop> getSelectedTroops() {
        return selectedTroops;
    }

    public void setSelectedTroops(ArrayList<Troop> selectedTroops) {
        this.selectedTroops = selectedTroops;
    }

    public Home getSelectedTroopsHome() {
        return selectedTroopsHome;
    }

    public void setSelectedTroopsHome(Home selectedTroopsHome) {
        this.selectedTroopsHome = selectedTroopsHome;
    }

    public Integer getCurrentTurn() {
        return currentTurn;
    }
}
