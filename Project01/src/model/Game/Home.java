package model.Game;

import java.util.ArrayList;

public class Home {
    private Integer x;
    private Integer y;
    private boolean isOilExist=false;
    private boolean isTunnelCreated=false;
    private String typeOfFloor;
    private Building building;
    private ArrayList troops=new ArrayList<Troop>();

    public Home(Integer x, Integer y,  String typeOfFloor) {
        this.x = x;
        this.y = y;
        this.typeOfFloor = typeOfFloor;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }



    public String getTypeOfFloor() {
        return typeOfFloor;
    }

    public Building getBuilding() {
        return building;
    }

    public ArrayList getTroops() {
        return troops;
    }

    public ArrayList getMines() {
        return mines;
    }

}
