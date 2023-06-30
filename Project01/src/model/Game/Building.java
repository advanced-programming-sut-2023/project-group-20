package model.Game;

import model.User;

public class Building {
    private Integer hitpoint;
    private User owner;
    private String type;
    private final Integer maxHitpoint;
    private final Integer needWorkers;
    private Integer activeWorkers=0;

    public Building(Integer hitpoint, User owner, String type, Integer maxHitpoint, Integer needWorkers) {
        this.hitpoint = hitpoint;
        this.owner = owner;
        this.type = type;
        this.maxHitpoint = maxHitpoint;
        this.needWorkers = needWorkers;
    }
    public void setHitpoint(Integer hitpoint) {
        this.hitpoint = hitpoint;
    }

    public Integer getHitpoint() {
        return hitpoint;
    }

    public void setActiveWorkers(Integer activeWorkers) {
        this.activeWorkers = activeWorkers;
    }

    public Integer getActiveWorkers() {
        return activeWorkers;
    }
    public void runBuilding(){}
}
