package model.GameInfo;

import enums.TreeTypes;
import enums.TroopTypes;
import model.User;

public class People {
    protected Government owner;
    protected String type;
    protected TroopTypes troopType;

    public People(String type, Government owner) {
        this.type = type;
        this.owner = owner;
    }

    public String getType() {
        return type;
    }

    public Government getOwner() {
        return owner;
    }
}
