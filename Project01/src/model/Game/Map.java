package model.Game;

import java.util.ArrayList;

public class Map {
    private Building selectedBuilding;
    private Integer xSize;
    private Integer ySize;
    private ArrayList<Home> homes=new ArrayList<>();
    public void addHomeToMap(Home home){
        this.homes.add(home);
    }
    public ArrayList getHomes() {
        return homes;
    }
    private Home getHomeByPosition(Integer x, Integer y){}

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }
}
