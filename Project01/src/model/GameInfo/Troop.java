package model.GameInfo;

import enums.TroopTypes;
import model.User;

public class Troop extends People {
    private String state;
    private Integer power;
    private Integer hitpoint;
    private Integer speed;
    private String troopGroup;
    private Home home;
    public void setState(String state) {
        this.state = state;
    }

    public Troop(String type,String troopGroup, Government owner, Integer power, Integer hitpoint,Integer speed) {
        super(type, owner);
        this.power = power;
        this.hitpoint = hitpoint;
        this.troopGroup=troopGroup;
        setSpeed(speed);
    }

    private void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}
