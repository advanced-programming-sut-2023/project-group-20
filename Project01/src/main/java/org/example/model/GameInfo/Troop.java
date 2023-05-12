package org.example.model.GameInfo;

import org.example.model.User;

public class Troop extends People {
    private String state;
    private Integer power;
    private Integer hitpoint;
    private Integer speed;
    private String TroopGroup;
    public void setState(String state) {
        this.state = state;
    }

    public Troop(String type, String troopGroup, User owner, Integer power, Integer hitpoint) {
        super(type, owner);
        this.power = power;
        this.hitpoint = hitpoint;
        if(type.equals("Archer")){
            setSpeed(3);
        }else if(false){

        }

    }

    private void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
