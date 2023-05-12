package org.example.enums;

import java.util.ArrayList;

public enum TroopTypes {
    //TODO
    // Add The Troops

    //TODO
    Archer("Archer",50,10,6,50,new ArrayList<>()),
//    CrossbowMen (),
//    SpearMen(),
//    PikeMen(),
//    MaceMen(),
//    SwordsMen(),
//    Knight (),
//    Tunneler(),
//    LadderMen(),
//    Engineer (),
//    BlackMonk (),
//    ArabianBow(),
//    Slaves (),
//    Slingers (),
//    Assassins(),
//    HorseArchers (),
//    ArabianSwordsMen(),
//    FireThrowers (),
    ;

    static {
        //TODO
        // Set the weapons for troops
        Archer.weapons.add("Bow");
    }

    public String type;
    public int hitpoint;
    public int power;
    public int speed;
    public int price;
    public ArrayList<String> weapons;

    TroopTypes(String type, int hitpoint, int power, int speed, int price, ArrayList<String> weapons) {
        this.type = type;
        this.hitpoint = hitpoint;
        this.power = power;
        this.speed = speed;
        this.price = price;
        this.weapons = weapons;
    }

}
