package model;

import enums.WarBuildingType;
import model.GameInfo.Building;
import model.GameInfo.Mine;
import model.GameInfo.Troop;
import model.GameInfo.WarBuilding;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<String> emails = new ArrayList<>();
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
        typesOfFloor.add("Floor");
        typesOfFloor.add("Plain");
        typesOfFloor.add("DeapLessWater");
        typesOfFloor.add("River");
        typesOfFloor.add("SmallPond");
        typesOfFloor.add("BigPond");
        typesOfFloor.add("Oil");
        typesOfFloor.add("Sea");
        typesOfFloor.add("Beach");
    }

    private final static ArrayList<String> typesOftree = new ArrayList<>();

    static {
        typesOftree.add("Palm");
        typesOftree.add("DesertTree");
        typesOftree.add("Olive");
        typesOftree.add("Cherry");
        typesOftree.add("Coconut");

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
    }

    public static ArrayList<String> getEmails() {
        return emails;
    }

    public static String selectSecurityQuestion(Integer number) {
    }

    public static ArrayList<String> getTypesOfTroops() {
        return typesOfTroops;
    }

    public static boolean isItBarrackTroop(String type) {
        //TODO
        if (type.equals("") || type.equals("") || type.equals("") || type.equals("") ||)
            return true;
        return false;
    }

    public static boolean isItMercenaryPostTroop(String type) {
        if (type.equals("") || type.equals("") || type.equals("") || type.equals("") ||)
            return true;
        return false;
    }

    public static boolean isItEngineerGuildTroop(String type) {
        if (type.equals("") || type.equals("") || type.equals("") || type.equals("") ||)
            return true;
        return false;
    }
    public static int getThePowerByType(String type){
        if(type.equals("")){
            return ;
        } else if (type.equals("")) {

        }
        return -1;
    }
    public static int getTheHitpointByType(String type){
        if(type.equals("")){
            return ;
        } else if (type.equals("")) {

        }
        return -1;
    }

}
