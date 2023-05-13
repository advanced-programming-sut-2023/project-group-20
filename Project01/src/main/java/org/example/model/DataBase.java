package org.example.model;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<String> emails = new ArrayList<>();
    private static ArrayList<String> slogans = new ArrayList<>();
    private final static ArrayList<String> securityQuestions = new ArrayList<>();

    static {
        securityQuestions.add("What is my father’s name?");
        securityQuestions.add("What was my first pet’s name?");
        securityQuestions.add("What is my mother’s last name");
    }

    static {
        slogans.add("I shall have my revenge, in this life or the next.");
        slogans.add("o MAHDI adrekni.");
        slogans.add("It is dutchman.");
        slogans.add("We will walk to Jerusalem with the Muslims together.");
        slogans.add("If IRAN does not ,it is better for me to die.");
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
        typesOfFloor.add("Floor");
        typesOfFloor.add("Oil");
        typesOfFloor.add("Plain");
        typesOfFloor.add("DeapLessWater");
        typesOfFloor.add("River");
        typesOfFloor.add("SmallPond");
        typesOfFloor.add("BigPond");
        typesOfFloor.add("Beach");
        typesOfFloor.add("Sea");
    }

    private final static ArrayList<String> typesOfTree = new ArrayList<>();

    static {
        typesOfTree.add("Palm");
        typesOfTree.add("DesertTree");
        typesOfTree.add("Olive");
        typesOfTree.add("Cherry");
        typesOfTree.add("Coconut");

    }

    private final static ArrayList<String> typesOfTroops = new ArrayList<>();

    static {
        typesOfTroops.add("Archer");
        typesOfTroops.add("CrossbowMen");
        typesOfTroops.add("SpearMen");
        typesOfTroops.add("PikeMen");
        typesOfTroops.add("MaceMen");
        typesOfTroops.add("SwordsMen");
        typesOfTroops.add("Knight");
        typesOfTroops.add("Tunneler");
        typesOfTroops.add("LadderMen");
        typesOfTroops.add("Engineer");
        typesOfTroops.add("BlackMonk");
        typesOfTroops.add("ArabianBow");
        typesOfTroops.add("Slaves");
        typesOfTroops.add("Slingers");
        typesOfTroops.add("Assassins");
        typesOfTroops.add("HorseArchers");
        typesOfTroops.add("ArabianSwordsMen");
        typesOfTroops.add("FireThrowers");
    }

    private final static ArrayList<String> typesOfBarrack = new ArrayList<>();

    static {
        typesOfBarrack.add("Archer");
        typesOfBarrack.add("CrossbowMen");
        typesOfBarrack.add("SpearMen");
        typesOfBarrack.add("PikeMen");
        typesOfBarrack.add("MaceMen");
        typesOfBarrack.add("SwordsMen");
        typesOfBarrack.add("Knight");
        typesOfBarrack.add("Tunneler");
        typesOfBarrack.add("LadderMen");
        typesOfBarrack.add("BlackMonk");
    }

    private final static ArrayList<String> typesOfEngineerGuild = new ArrayList<>();

    static {
        typesOfEngineerGuild.add("Engineer");
    }

    private final static ArrayList<String> typesOfMercenaryPost = new ArrayList<>();

    static {
        typesOfMercenaryPost.add("ArabianBow");
        typesOfMercenaryPost.add("Slaves");
        typesOfMercenaryPost.add("Slingers");
        typesOfMercenaryPost.add("Assassins");
        typesOfMercenaryPost.add("HorseArchers");
        typesOfMercenaryPost.add("ArabianSwordsMen");
        typesOfMercenaryPost.add("FireThrowers");
    }

    private final static ArrayList<String> typeOfMines = new ArrayList<>();

    static {
        typeOfMines.add("IronMine");
        typeOfMines.add("StoneMine");
        typeOfMines.add("WoodCutter");
        typeOfMines.add("OxTether");
        typeOfMines.add("StockPile");
        typeOfMines.add("armourer");
        typeOfMines.add("blacksmith");
        typeOfMines.add("Fletcher");
        typeOfMines.add("Poleturner");
    }

    private final static ArrayList<String> typeOfWarBuilding = new ArrayList<>();

    static {
//        typeOfWarBuilding.add("Gate");
//        typeOfWarBuilding.add("ShortWall");
//        typeOfWarBuilding.add("HighWall");
//        typeOfWarBuilding.add("Tower");
//        typeOfWarBuilding.add("LittleTower");
//        typeOfWarBuilding.add("Step");
//        typeOfWarBuilding.add("Trap");
//        typeOfWarBuilding.add("Ditch");
//        typeOfWarBuilding.add("OilBurning");
//        typeOfWarBuilding.add("GateCapture");
//        typeOfWarBuilding.add("Shield");
//        typeOfWarBuilding.add("BatteringRam");
//        typeOfWarBuilding.add("MovableTower");
//        typeOfWarBuilding.add("Catapult");
//        typeOfWarBuilding.add("FixedCatapult");
//        typeOfWarBuilding.add("FireCatapult");
//        .......................................
        typeOfWarBuilding.add("SmallStoneGatehouse");
        typeOfWarBuilding.add("BigStoneGatehouse");
        typeOfWarBuilding.add("Drawbridge");
        typeOfWarBuilding.add("LookoutTower");
        typeOfWarBuilding.add("PerimeterTower");
        typeOfWarBuilding.add("DefensiveTower");
        typeOfWarBuilding.add("SquareTower");
        typeOfWarBuilding.add("CircleTower");
        typeOfWarBuilding.add("Armoury");
        typeOfWarBuilding.add("Barrack");
        typeOfWarBuilding.add("MercenaryPost");
        typeOfWarBuilding.add("EngineerGuild");
        typeOfWarBuilding.add("KillingPit");
        typeOfWarBuilding.add("OilSmelter");
        typeOfWarBuilding.add("PitchDitch");
        typeOfWarBuilding.add("CagedWarDogs");
        typeOfWarBuilding.add("SiegeTent");
        typeOfWarBuilding.add("Stable");

    }

    private final static ArrayList<String> typeOfFoodFarm = new ArrayList<>();

    static {
        typeOfFoodFarm.add("Mill");
        typeOfFoodFarm.add("Baker");
        typeOfFoodFarm.add("Brewery"); //AbeJo Sazi
        typeOfFoodFarm.add("Granery");
        typeOfFoodFarm.add("AppleGarden");
        typeOfFoodFarm.add("DairyProducts");
        typeOfFoodFarm.add("BarleyField");
        typeOfFoodFarm.add("Hunt");
        typeOfFoodFarm.add("WheatField");
    }

    private final static ArrayList<String> typeOfTownBuilding = new ArrayList<>();

    static {
        typeOfTownBuilding.add("Inn");
        typeOfTownBuilding.add("Hovel");
        typeOfTownBuilding.add("Church");
        typeOfTownBuilding.add("Cathedral");
    }

    public static void addUserToDataBase(User user) {
        users.add(user);
        emails.add(user.getEmail());
    }

    public static User getUserByName(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username))
                return users.get(i);
        }
        return null;
    }

    public static Integer rank(User user) {
        Integer rank = 1;
        for (User value : users) {
            if (user.getHighScore() < value.getHighScore())
                rank++;
        }
        return rank;
    }

    public static ArrayList<String> getEmails() {
        return emails;
    }

    public static ArrayList<String> getSlogans() {
        return slogans;
    }

    public static ArrayList<String> getTypesOfTroops() {
        return typesOfTroops;
    }

    public static ArrayList<String> getTypeOfFoodFarm() {
        return typeOfFoodFarm;
    }

    public static ArrayList<String> getTypeOfMines() {
        return typeOfMines;
    }

    public static ArrayList<String> getTypeOfTownBuilding() {
        return typeOfTownBuilding;
    }

    public static ArrayList<String> getTypeOfWarBuilding() {
        return typeOfWarBuilding;
    }

    public static ArrayList<String> getTypesOfFloor() {
        return typesOfFloor;
    }

    public static ArrayList<String> getTypesOfTree() {
        return typesOfTree;
    }

    public static ArrayList<String> getTypesOfBarrack() {
        return typesOfBarrack;
    }

    public static ArrayList<String> getTypesOfEngineerGuild() {
        return typesOfEngineerGuild;
    }

    public static ArrayList<String> getTypesOfMercenaryPost() {
        return typesOfMercenaryPost;
    }
}
