package model;

import model.GameInfo.Mine;
import model.GameInfo.Troop;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();
    private final static ArrayList<String> securityQuestions = new ArrayList<>();

    static {
        securityQuestions.add("What is my father’s name?");
        securityQuestions.add("What was my first pet’s name?");
        securityQuestions.add("What is my mother’s last name");
    }

    private final static ArrayList<String> typesOfFloor = new ArrayList<>();

    static {
        typesOfFloor.add("FlatGround");
        typesOfFloor.add("GravelGround");
        typesOfFloor.add("Rock");
        typesOfFloor.add("StoneGround");
        typesOfFloor.add("IronGround");
        typesOfFloor.add("Grass");
        typesOfFloor.add("Meadow");
        typesOfFloor.add("FoolMeadow");
        typesOfFloor.add("WaterArea");
    }

    private final static ArrayList<String> typesOftree = new ArrayList<>();

    static {
        typesOftree.add("Palm");
        typesOftree.add("DesertTree");
        typesOftree.add("Olive");
        typesOftree.add("Cherry");
        typesOftree.add("Coconut");

    }

    private final static ArrayList<String> typesOfWaterArea = new ArrayList<>();

    static {
        typesOfWaterArea.add("Plain");
        typesOfWaterArea.add("DeapLessWater");
        typesOfWaterArea.add("River");
        typesOfWaterArea.add("SmallPond");
        typesOfWaterArea.add("BigPond");
        typesOfWaterArea.add("Oil");
        typesOfWaterArea.add("Sea");
        typesOfWaterArea.add("Beach");
    }

    private final static ArrayList<String> typesOfTroops = new ArrayList<>();

    static {
        typesOfTroops.add("CrossbowMen");
        typesOfTroops.add("SpearMen");
        typesOfTroops.add("PikeMen");
        typesOfTroops.add("MaceMen");
        typesOfTroops.add("SwordsMen");
        typesOfTroops.add("Knights");
        typesOfTroops.add("Tunnelers");
        typesOfTroops.add("LadderMen");
        typesOfTroops.add("Engineers");
        typesOfTroops.add("BlackMonks");
        typesOfTroops.add("ArabianBows");
        typesOfTroops.add("Slaves");
        typesOfTroops.add("Slingers");
        typesOfTroops.add("Assassins");
        typesOfTroops.add("HorseArchers");
        typesOfTroops.add("ArabianSwordsMen");
        typesOfTroops.add("FireThrowers");
    }

    private final static ArrayList<String> typeOfMines = new ArrayList<>();

    static {
        typeOfMines.add("IronMine");
        typeOfMines.add("StoneMine");
        typeOfMines.add("AppleFarm");
        typeOfMines.add("CowFarm");
        typeOfMines.add("MeatCamp");
        typeOfMines.add("WoodCut");

    }

    private final static ArrayList<String> typeOfWarBuilding = new ArrayList<>();

    static {
        typeOfWarBuilding.add("Gate");
        typeOfWarBuilding.add("ShortWall");
        typeOfWarBuilding.add("HighWall");
        typeOfWarBuilding.add("Tower");
        typeOfWarBuilding.add("LittleTower");
        typeOfWarBuilding.add("Step");
        typeOfWarBuilding.add("Trap");
        typeOfWarBuilding.add("Ditch");
        typeOfWarBuilding.add("OilBurning");
        typeOfWarBuilding.add("GateCapture");
        typeOfWarBuilding.add("Shield");
        typeOfWarBuilding.add("BatteringRam");
        typeOfWarBuilding.add("MovableTower");
        typeOfWarBuilding.add("Catapult");
        typeOfWarBuilding.add("FixedCatapult");
        typeOfWarBuilding.add("FireCatapult");

    }

    public static void addUserToDataBase(User user) {
        users.add(user);
    }

    public static User getUserByName(String username) {
    }

    public static Integer rank(User user) {
    }

    public static String selectSecurityQuestion(Integer number) {
    }

    public static ArrayList getTypesOfTroops() {
        return typesOfTroops;
    }
}
