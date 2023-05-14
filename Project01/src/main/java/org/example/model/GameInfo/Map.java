package org.example.model.GameInfo;

import java.util.ArrayList;

public class Map {
    private Building selectedBuilding;
    private Integer xSize;
    private Integer ySize;
    private ArrayList<Home> homes = new ArrayList<>();

//    private ArrayList<User>

    public Map(Integer xSize, Integer ySize, Integer playerNumber) {
        this.xSize = xSize;
        this.ySize = ySize;
        createHomes();
    }

//    public void addHomeToMap(Home home) {
//        this.homes.add(home);
//    }

    public ArrayList<Home> getHomes() {
        return homes;
    }

//    public Home getHomeByPosition(Integer x, Integer y) {
//        mapController.
//    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public Integer getxSize() {
        return xSize;
    }

    public Integer getySize() {
        return ySize;
    }

    private void createHomes() {
        for (int i = 1; i < this.xSize; i++) {
            for (int j = 1; j < this.ySize; j++) {
                homes.add(new Home(i, j, "FlatGround"));
            }
        }
    }
}
