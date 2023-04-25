package model.GameInfo;

import java.util.ArrayList;

public class Map {
    private Building selectedBuilding;
    private Integer xSize;
    private Integer ySize;
    private ArrayList<Home> homes = new ArrayList<>();

    public Map(Integer xSize, Integer ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        createHomes();
    }

//    public void addHomeToMap(Home home) {
//        this.homes.add(home);
//    }

    public ArrayList getHomes() {
        return homes;
    }

    public Home getHomeByPosition(Integer x, Integer y) {
    }

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
