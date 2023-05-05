package controller;

import model.DataBase;
import model.GameInfo.*;
import model.User;
import view.GameMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameController {
    private User currentUser;
    private Game currentGame;
    private int currentTurn;
    private MapController mapController;
    private UnitMotionController unitMotionController;


    public GameController(Game game) {
        setCurrentGame(game);
        this.mapController = new MapController(getCurrentGame().getMap(), getCurrentGame());
        this.unitMotionController = new UnitMotionController(getCurrentGame().getMap(), getCurrentGame());
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    private void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public void start() {
        GameMenu gameMenu = new GameMenu();
        gameMenu.setGameController(this);
        gameMenu.run();
    }

    private void divideTheMap() {
        Map map = getCurrentGame().getMap();
        int playerNumber = getCurrentGame().getPlayerNumber();
        int dividedX = map.getxSize() / playerNumber;
        int dividedY = map.getySize() / playerNumber;
        for (int i = 0; i < playerNumber; i++) {
            for (int j = dividedX * i + 1; j <= dividedX * (i + 1); j++) {
                for (int k = dividedY * i + 1; k < dividedY * (i + 1); k++) {
                    if (mapController.getHomeByPosition(j, k) == null) {
                        continue;
                    }
                    mapController.getHomeByPosition(j, k).setOwner(((Government) getCurrentGame().getGovernments().get(i)));
                }
            }
        }
    }

    public void showMap(Matcher matcher) {
        mapController.start(matcher);
    }

    public String showPopularity() {
        int popularity = getCurrentGovernment().getPopularity();
        return "The popularity is: " + popularity;
    }

    public String showFoodList() {
        String output = new String();
        output += "All Foods Name:\n";
        double[] foodAmountInAllGranery = new double[Granery.foodNames.size()];
        for (int i = 0; i < getCurrentGovernment().getGraneries().size(); i++) {
            HashMap allFoods = ((Granery) getCurrentGovernment().getGraneries().get(i)).getAllFoods();
            for (int j = 0; j < allFoods.size(); j++) {
                foodAmountInAllGranery[j] += ((double) allFoods.get(Granery.foodNames.get(j)));
            }
        }
        for (int i = 0; i < Granery.foodNames.size(); i++) {
            output += ((String) Granery.foodNames.get(i)) + "-->" + foodAmountInAllGranery[i];
        }
        return output;
    }

    private Government getCurrentGovernment() {
        return getGovernmentByUser(currentUser);
    }

    public String showPopularityFactors() {
        String output = new String();
        output += "The popularity factors are:";
        output += "Food --> " + getCurrentGovernment().getFoodRate() + "\n";
        output += "Tax --> " + getCurrentGovernment().getTaxRate() + "\n";
        output += "Religious" + "\n";
        output += "Fear" + "\n";
        return output;
    }

    public String showFoodRate() {
        return "FoodRate is:\n" + getCurrentGovernment().getFoodRate();
    }

    public String setTaxRate(Matcher matcher) {
        int taxRate = Integer.parseInt(matcher.group("r"));
        getCurrentGovernment().setTaxRate(taxRate);
        return "We set the taxRate successfully";
    }

    public String showTaxRate() {
        return "TexRate is:\n" + getCurrentGovernment().getTaxRate();
    }

    public String setFoodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("r"));
        getCurrentGovernment().setFoodRate(foodRate);
        return "We set the foodRate successfully";
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        home.setBuildingSelected(true);
        getCurrentGame().setSelectedBuildingHome(home);
        return "We select a building in x:<< " + x + " >>" + " and y:<< " + y + " >> home";
    }

    public String createUnit(Matcher matcher) {
        if (getCurrentGame().getSelectedBuildingHome() == null)
            return "You have not choose a home yet";

        int hitponit = getTheHitpointByType(matcher.group("type"));
        int power = getThePowerByType(matcher.group("type"));
        int speed = getTheSpeedByType(matcher.group("type"));
        Home selectedHome = getCurrentGame().getSelectedBuildingHome();
        Troop troop;
        if (selectedHome.getBuilding().getType().equals("Barrack")) {
            if (isItBarrackTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                if (!setBarrackTroopNeededWeapons(matcher.group("type")))
                    return "We do not have enough needed weapons to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                troop = new Troop(matcher.group("type"), "Barrack", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in Barrack building please first select a true building then try again!";
            }
        } else if (selectedHome.getBuilding().getType().equals("MercenaryPost")) {
            if (isItMercenaryPostTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                troop = new Troop(matcher.group("type"), "MercenaryPost", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in MercenaryPost building please first select a true building then try again!";
            }
        } else if (selectedHome.getBuilding().getType().equals("EngineerGuild")) {
            if (isItEngineerGuildTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                troop = new Troop(matcher.group("type"), "EngineerGuild", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in EngineerGuild building please first select a true building then try again!";
            }
        } else {
            return "Please choose a correct place for building troop!";
        }
        //TODO
        // SET THE FIRST POSITION FOR TROOPS
        troop.setHome(selectedHome);
        selectedHome.addTroop(troop);
        return "We create a << " + matcher.group("type") + " >> successfully";
    }

    private boolean setBarrackTroopNeededWeapons(String type) {
        //TODO
        if (type.equals("Archer")) {
            if (findTheWeaponInArmory("Tir") == -1 || findTheWeaponInArmory("Kaman") == -1)
                return false;
            else {
                Armoury armoury = ((Armoury) getCurrentGovernment().getArmouries().get(findTheWeaponInArmory("Tir")));
                Armoury armoury2 = ((Armoury) getCurrentGovernment().getArmouries().get(findTheWeaponInArmory("Kaman")));
                armoury.setElements("Tir", armoury.getElements("Tir") - 1);
                armoury2.setElements("Kaman", armoury2.getElements("Kaman") - 1);
                return true;
            }
        }
        return false;
    }

    private int findTheWeaponInArmory(String weaponName) {
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            if (((Armoury) getCurrentGovernment().getArmouries().get(i)).getElements(weaponName) > 0)
                return i;
        }
        return -1;
    }

    private boolean isEnoughCoinToBuyTroop(String troopName) {
        //TODO
        int price = getThePriceOfTroop(troopName);
        if (troopName.equals("")) {
            if (price < getCurrentGovernment().getCoin())
                return false;
            return true;
        }
    }

    private int getThePriceOfTroop(String troopName) {
        //TODO
        if (troopName.equals("")) {
            return ?;
        }

    }

    public String repair() {
        if (getCurrentGame().getSelectedBuildingHome() == null)
            return "You have not choose a home yet";
        int neededStone = ((WarBuilding) getCurrentGame().getSelectedBuildingHome().getBuilding()).getNeededStone();
        if (getAllElementFromStockPile("stone") < neededStone)
            return "Sorry more stone needed";
        if (isEnemyNearHome(getCurrentGame().getSelectedBuildingHome()))
            return "You can not repair this building because enemy is near that";
        Building building = getCurrentGame().getSelectedBuildingHome().getBuilding();
        building.setHitpoint(building.getMaxHitpoint());
        decreaseElementFromStockPlie("stone", ((WarBuilding) building).getNeededStone());
        return "We repair the building successfully";
    }

    private int getAllElementFromStockPile(String elementType) {
        int allAmount = 0;
        for (int i = 0; i < getCurrentGovernment().getStockPiles().size(); i++) {
            StockPile stockPile = ((StockPile) getCurrentGovernment().getStockPiles().get(i));
            allAmount += ((Integer) stockPile.getElements().get(elementType));
        }
        return allAmount;
    }

    private void decreaseElementFromStockPlie(String elementType, int amount) {
        int decreaseAmount = amount;
        for (int i = 0; i < getCurrentGovernment().getStockPiles().size(); i++) {
            StockPile stockPile = ((StockPile) getCurrentGovernment().getStockPiles().get(i));
            decreaseAmount = decreaseAmount - ((Integer) stockPile.getElements().get(elementType));
            if (decreaseAmount <= 0) {
                stockPile.setElements(elementType, ((Integer) stockPile.getElements().get(elementType)) - amount);
                return;
            } else {
                stockPile.setElements(elementType, 0);
            }
        }
    }

    private boolean isEnemyNearHome(Home home) {
        int x = home.getX();
        int y = home.getY();
        for (int i = x - 2; i < x + 2; i++) {
            for (int j = y - 2; j < y + 2; j++) {
                if (i < 1 || i > getCurrentGame().getMap().getxSize() || j < 1 || j > getCurrentGame().getMap().getySize())
                    continue;
                if (!home.getOwner().equals(mapController.getHomeByPosition(i, j).getOwner()))
                    return true;
            }
        }
        return false;
    }

    public String selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        ArrayList<Troop> selectedTroops = new ArrayList<>();
        Home home = mapController.getHomeByPosition(x, y);
        for (int i = 0; i < home.getTroops().size(); i++) {
            if (((Troop) home.getTroops().get(i)).getOwner().equals(getCurrentGovernment()))
                selectedTroops.add(((Troop) home.getTroops().get(i)));
        }
        getCurrentGame().setSelectedTroops(selectedTroops);
        getCurrentGame().setSelectedTroopsHome(home);
        return "We select all of your troops that are in this home.";
    }

    public String moveUnit(Matcher matcher, String groupX, String groupY) {
        if (getCurrentGame().getSelectedTroops().size() == 0) {
            return "Please select unit First";
        }
        Home firstHome = getCurrentGame().getSelectedTroopsHome();
        int speed = getTheMinimumSpeedOfTroops(getCurrentGame().getSelectedTroops());
        int currentPosX = firstHome.getX();
        int currentPosY = firstHome.getY();
        int targetX = Integer.parseInt(matcher.group(groupX));
        int targetY = Integer.parseInt(matcher.group(groupY));
        int distanceX = Math.abs(currentPosX - targetX);
        int distanceY = Math.abs(currentPosY - targetY);
        if ((distanceX + distanceY) > speed)
            return "The Speed of Your Troops is lower than your destination";
        if (unitMotionController.isAnyAvailableDestination(speed, currentPosX, currentPosY, targetX, targetY)) {
            return "Can not find available race";
        }
        removeTroopsFromHome(firstHome, getCurrentGame().getSelectedTroops());
        Home seccondHome = mapController.getHomeByPosition(targetX, targetY);
        seccondHome.addTroops(getCurrentGame().getSelectedTroops());
        //TODO
        // Set the troops after moving from TALE
        return "We move our selected troops to your destination";
    }

    private int getTheMinimumSpeedOfTroops(ArrayList<Troop> troops) {
        int minimumSpeed = troops.get(0).getSpeed();
        for (int i = 0; i < troops.size(); i++) {
            if (minimumSpeed > troops.get(i).getSpeed())
                minimumSpeed = troops.get(i).getSpeed();
        }
        return minimumSpeed;
    }

    private void removeTroopsFromHome(Home home, ArrayList<Troop> mustBeRemovedTroops) {
        ArrayList<Troop> updatedTroops = new ArrayList<>();
        Government troopsGovernment = mustBeRemovedTroops.get(0).getOwner();
        int numberOfRemovedTroops = mustBeRemovedTroops.size();
        for (int i = 0; i < home.getTroops().size(); i++) {
            if (numberOfRemovedTroops != 0 && troopsGovernment.equals(((Troop) home.getTroops().get(i)).getOwner())) {
                numberOfRemovedTroops--;
                continue;
            }
            updatedTroops.add(((Troop) home.getTroops().get(i)));
        }
        home.setTroops(updatedTroops);
    }

//    private boolean isAnyBarrier(int firstX, int firstY, int targetX, int targetY) {
//        //TODO
//    }

    public String patrolUnit(Matcher matcher) {
        String firstMove = moveUnit(matcher, "x1", "y1");
        int firsX = Integer.parseInt(matcher.group("x1"));
        int firsY = Integer.parseInt(matcher.group("y1"));
        int targetX = Integer.parseInt(matcher.group("x2"));
        int targetY = Integer.parseInt(matcher.group("y2"));
        if (firstMove.equals("We move our selected troops to your destination")) {
            int speed = getTheMinimumSpeedOfTroops(getCurrentGame().getSelectedTroops());
            if (!unitMotionController.isAnyAvailableDestination(speed, firsX, firsY, targetX, targetY))
                return "Can not find available race";
            unitMotionController.addPatrolTroops(getCurrentGame().getSelectedTroops());
            return "Your troops will patrol in your border homes MyLord";
        } else
            return firstMove;
    }

    public String stopPatrolUint(Matcher matcher) {

    }

    public String setTroopsState(Matcher matcher) {
    }

    public String attackToEnemy(Matcher matcher) {
    }

    public String attack(Matcher matcher) {
    }

    public String pourOil(Matcher matcher) {
    }

    public String digTunnel(Matcher matcher) {
    }

    public String buildBuilding(Matcher matcher) {
    }

    public String disbandUnit() {
    }

    ;

    public String setTexture(Matcher matcher) {
    }

    public String clear(Matcher matcher) {
    }

    public String droprock(Matcher matcher) {

    }

    public String prockRandomDirection() {
    }

    public String dropTree(Matcher matcher) {
    }

    public boolean checkTreeName(String name) {

    }

    public String dropBuilding(Matcher matcher) {

        if (CheckController.checkTheNumberInputTruth(matcher.group("x")) || CheckController.checkTheNumberInputTruth(matcher.group("y")))
            return "Please enter the valid x and y";
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        if (isWarBuilding(matcher.group("type"))) {

        } else if (isFoodFarm(matcher.group("type"))) {

        } else if (isTownBuilding(matcher.group("type"))) {

        } else if (isMine(matcher.group("type"))) {

        } else {
            return "Please enter true type for your building name";
        }

    }

    public String dropUnit(Matcher matcher) {
    }

    private boolean checkFloorType(String typeOfHome, String targetName) {
    }

//    private boolean isMovable(String typeOfFloor) {
//
//    }

    public String openTrade(Matcher matcher) {
    }

    public String openShop() {
    }

    public void changeTurn() {
    }

    private void applyChanges() {
    }

    private String checkFloorType(String type) {
    }

//    private String checkWaterAreaType(String type) {
//    }

    private String checkTreeType(String type) {

    }

    private void updatePopularityAfterTurn() {
        int foodRate = getCurrentGovernment().getFoodRate();
        int fearRate = getCurrentGovernment().getFearRate();
        int taxRate = getCurrentGovernment().getTaxRate();
        int mustBeAddedPopularity = 0;
        mustBeAddedPopularity += getTheFoodPopularityEffect(foodRate);
        mustBeAddedPopularity += getTheTaxPopularityEffect(taxRate);
        mustBeAddedPopularity += fearRate;
        //TODO Handle Buildings Effect
        getCurrentGovernment().setPopularity(getCurrentGovernment().getPopularity() + mustBeAddedPopularity);
    }

    private void updateGraneriesAfterTurn() {   // The production of farms

    }

    private void updateStockPilesAfterTurn() {   // The production of mines

    }

    private void updateTroopsAfterTurn() {   // The war effects

    }

    private void updateBuildingsAfterTurn() {   // The war effects The Tunnel effects

    }

    private void updateCoinAfterTurn() {
        int changeAmount = 0;
        //TODO
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() + changeAmount);
    }

    private void updateHomeAfterTurn() { // set the new type if it change and reset all homes (selectionBuilding)
        mapController.resetAllHomes();
//        TODO
    }

    private void updateGameAfterTurn() {
//        TODO
    }

    private void updatePatrolTroopsAfterTurn() {
        //TODO
    }


    private int getTheFoodPopularityEffect(int foodRate) {
        return foodRate * 4;
    }

    private double getTheEatenFoodAmount(int foodRate) {
        return (1 / 2 * foodRate + 1);
    }


    private Integer getTheTaxPopularityEffect(int taxRate) {
        if (taxRate < 0) {
            return (-2 * taxRate + 1);
        } else if (taxRate == 0) {
            return 1;
        } else if (taxRate > 0 && taxRate < 5) {
            return -2 * taxRate;
        } else if (taxRate >= 5) {
            return (-4 * taxRate + 8);
        }
        return null;
    }

    private double getTheCoinChangeAfterTax(int taxRate) {
        if (taxRate < 0) {
            return (0.2 * taxRate - 0.4);
        } else if (taxRate == 0) {
            return 0;
        } else if (taxRate > 0) {
            return (0.2 * taxRate + 0.4);
        }
        return 0;
    }

    public Government getGovernmentByUser(User user) {
        ArrayList allGovernments = this.currentGame.getGovernments();
        for (int i = 0; i < allGovernments.size(); i++) {
            if (((Government) allGovernments.get(i)).getOwner().equals(user))
                return ((Government) allGovernments.get(i));
        }
        return null;
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

    public static int getThePowerByType(String type) {
        if (type.equals("")) {
            return;
        } else if (type.equals("")) {

        }
        return -1;
    }

    public static int getTheHitpointByType(String type) {
        if (type.equals("")) {
            return;
        } else if (type.equals("")) {

        }
        return -1;
    }

    public static int getTheSpeedByType(String type) {
        if (type.equals("")) {
            return;
        } else if (type.equals("")) {
            return;
        }
        return;
    }

    public static boolean isWarBuilding(String type) {
        for (int i = 0; i < DataBase.getTypeOfWarBuilding().size(); i++) {
            if (((String) DataBase.getTypeOfWarBuilding().get(i)).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isMine(String type) {
        for (int i = 0; i < DataBase.getTypeOfMines().size(); i++) {
            if (((String) DataBase.getTypeOfMines().get(i)).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isFoodFarm(String type) {
        for (int i = 0; i < DataBase.getTypeOfFoodFarm().size(); i++) {
            if (((String) DataBase.getTypeOfFoodFarm().get(i)).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isTownBuilding(String type) {
        for (int i = 0; i < DataBase.getTypeOfTownBuilding().size(); i++) {
            if (((String) DataBase.getTypeOfTownBuilding().get(i)).equals(type))
                return true;
        }
        return false;
    }

    public static boolean getTheProductionNumber() {

    }

}
