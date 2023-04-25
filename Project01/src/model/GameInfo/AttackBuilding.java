package model.GameInfo;

import model.User;

public class AttackBuilding extends Building{

    public AttackBuilding(Integer hitpoint, User owner, String type, Integer maxHitpoint,Integer powerOfDestroying, Integer needWorkers) {
        super(hitpoint, owner, type, maxHitpoint, powerOfDestroying, needWorkers);
    }
}
