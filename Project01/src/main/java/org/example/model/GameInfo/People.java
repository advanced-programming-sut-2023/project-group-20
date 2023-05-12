package org.example.model.GameInfo;

import org.example.model.User;

public class People {
    protected User owner;
    protected String type;
    public People(String type, User owner) {
        this.type = type;
        this.owner = owner;
    }

    public String getType() {
        return type;
    }
}
