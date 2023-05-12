package model.GameInfo;

import enums.TroopTypes;

public class People {
    protected Government owner;
    protected String type;

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
