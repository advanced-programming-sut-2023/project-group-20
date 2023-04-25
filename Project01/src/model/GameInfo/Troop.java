package model.GameInfo;

import model.User;

public class Troop extends People {
    private String state;
    private Integer power;
    private Integer hitpoint;
    private Integer speed;
    public void setState(String state) {
        this.state = state;
    }

    public Troop(String type, User owner, Integer power, Integer hitpoint) {
        super(type, owner);
        this.power = power;
        this.hitpoint = hitpoint;
        if(type.equals("Archer")){
            setSpeed(3);
        }else if(){

        }

    }

    private void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
