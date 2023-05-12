package org.example.enums;

public enum TypeOfEngineerBuildings {
    //TODO
    ;
    private String name;
    private String type;
    private Integer price;
    private Integer neededEngineers;
    private Integer maxHitpoint;
    private Integer destroyingPower;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getNeededEngineers() {
        return neededEngineers;
    }

    public Integer getMaxHitpoint() {
        return maxHitpoint;
    }

    public Integer getDestroyingPower() {
        return destroyingPower;
    }
}
