package org.example.model.GameInfo;

import org.example.model.User;

public class Building {
    private Integer hitpoint;
    private User owner;
    private String type;
    private final Integer maxHitpoint;
    protected final Integer powerOfDestroying;
    private final Integer needWorkers;
    private Integer activeWorkers=0;

    public Building(Integer hitpoint, User owner, String type, Integer maxHitpoint, Integer powerOfDestroying, Integer needWorkers) {
        this.hitpoint = hitpoint;
        this.owner = owner;
        this.type = type;
        this.maxHitpoint = maxHitpoint;
        this.powerOfDestroying = powerOfDestroying;
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

    public String getType() {
        return type;
    }
}
