package model.GameInfo;

import enums.WarBuildingType;
import model.User;

public class WarBuilding extends Building{
    private int neededStone;
    public WarBuilding(Integer hitpoint, User owner, String type, Integer maxHitpoint, Integer powerOfDestroying, Integer needWorkers,int neededStone) {
        super(hitpoint, owner, type, maxHitpoint, powerOfDestroying, needWorkers);
        this.neededStone=neededStone;
    }

    public int getNeededStone() {
        return neededStone;
    }
}
