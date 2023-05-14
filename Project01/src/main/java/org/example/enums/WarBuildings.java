package org.example.enums;

public enum WarBuildings {
    ;
    private String name;
    private Integer fireRange;
    private Integer defendRange;
    private Integer powerOfDestroying;

    WarBuildings(String name, Integer fireRange, Integer defendRange, Integer powerOfDestroying) {
        this.name = name;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.powerOfDestroying = powerOfDestroying;
    }

    public String getName() {
        return name;
    }

    public Integer getFireRange() {
        return fireRange;
    }

    public Integer getDefendRange() {
        return defendRange;
    }

    public Integer getPowerOfDestroying() {
        return powerOfDestroying;
    }
}
