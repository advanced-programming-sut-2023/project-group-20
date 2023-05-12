package org.example.model.GameInfo;

import org.example.model.User;

public class Mine extends Building {
    private Integer productionAmount;
    private final String validFloorType;

    public Mine(Integer hitpoint, User owner, String type, Integer maxHitpoint, Integer powerOfDestroying, Integer needWorkers, String validFloorType) {
        super(hitpoint, owner, type, maxHitpoint, powerOfDestroying, needWorkers);
        this.validFloorType = validFloorType;
    }

    public Integer getProductionAmount() {
        return productionAmount;
    }
}
