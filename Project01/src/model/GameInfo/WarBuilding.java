package model.GameInfo;

import model.User;

public class WarBuilding extends Building{

    public WarBuilding(Integer hitpoint, User owner, String type, Integer maxHitpoint, Integer powerOfDestroying, Integer needWorkers) {
        super(hitpoint, owner, type, maxHitpoint, powerOfDestroying, needWorkers);
    }
}
