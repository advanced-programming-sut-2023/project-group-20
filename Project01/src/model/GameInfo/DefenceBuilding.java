package model.GameInfo;

import model.User;

public class DefenceBuilding extends Building{

    public DefenceBuilding(Integer hitpoint, User owner, String type, Integer maxHitpoint,Integer powerOfDestroying, Integer needWorkers) {
        super(hitpoint, owner, type, maxHitpoint, powerOfDestroying, needWorkers);
    }
}
