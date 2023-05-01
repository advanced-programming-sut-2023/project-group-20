package controller;

import model.DataBase;
import model.GameInfo.*;
import model.User;
import view.GameMenu;
import view.MapMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameController {
    private User currentUser;
    private Game currentGame;
    private int currentTurn;
    private MapController mapController;


    public GameController(Game game) {
        setCurrentGame(game);
        this.mapController = new MapController(getCurrentGame().getMap(), getCurrentGame());
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
        getCurrentGame().setSelectedHome(home);
        return "We select a building in x:<< " + x + " >>" + " and y:<< " + y + " >> home";
    }

    public String createUnit(Matcher matcher) {
        if (getCurrentGame().getSelectedHome() == null)
            return "You have not choose a home yet";
        int hitponit = DataBase.getTheHitpointByType(matcher.group("type"));
        int power = DataBase.getThePowerByType(matcher.group("type"));

        if (getCurrentGame().getSelectedHome().getBuilding().getType().equals("Barrack")) {
            if (DataBase.isItBarrackTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                if (!setBarrackTroopNeededWeapons(matcher.group("type")))
                    return "We do not have enough needed weapons to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                Troop troop = new Troop(matcher.group("type"), "Barrack", getCurrentGovernment(), power, hitponit);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in Barrack building please first select a true building then try again!";
            }
        } else if (getCurrentGame().getSelectedHome().getBuilding().getType().equals("MercenaryPost")) {
            if (DataBase.isItMercenaryPostTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                Troop troop = new Troop(matcher.group("type"), "MercenaryPost", getCurrentGovernment(), power, hitponit);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in MercenaryPost building please first select a true building then try again!";
            }
        } else if (getCurrentGame().getSelectedHome().getBuilding().getType().equals("EngineerGuild")) {
            if (DataBase.isItEngineerGuildTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
                Troop troop = new Troop(matcher.group("type"), "EngineerGuild", getCurrentGovernment(), power, hitponit);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in EngineerGuild building please first select a true building then try again!";
            }
        }
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
        if (getCurrentGame().getSelectedHome() == null)
            return "You have not choose a home yet";
        int neededStone = ((WarBuilding) getCurrentGame().getSelectedHome().getBuilding()).getNeededStone();
        if (getAllElementFromStockPile("stone") < neededStone)
            return "Sorry more stone needed";
        if (isEnemyNearHome(getCurrentGame().getSelectedHome()))
            return "You can not repair this building because enemy is near that";
        Building building = getCurrentGame().getSelectedHome().getBuilding();
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
    }

    public String patrolUnit(Matcher matcher) {
    }

    public String moveUnit(Matcher matcher) {
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
    }

    public String dropUnit(Matcher matcher) {
    }

    private boolean checkFloorType(String typeOfHome, String targetName) {
    }

    private boolean isMovable(String typeOfFloor) {
    }

    public String openTrade(Matcher matcher) {
    }

    public String openShop() {
    }

    public void changeTurn() {
    }

    public void applyChanges() {
    }

    public String checkFloorType(String type) {
    }

    public String checkWaterAreaType(String type) {
    }

    public String checkTreeType(String type) {
    }

    private void updatePopularity() {
        int foodRate = getCurrentGovernment().getFoodRate();
        int fearRate = getCurrentGovernment().getFearRate();
        int taxRate = getCurrentGovernment().getTaxRate();
        int mustBeAddedPopularity = 0;
        mustBeAddedPopularity += getTheFoodPopularityEffect(foodRate);
        mustBeAddedPopularity += getTheTaxPopularityEffect(taxRate);
        mustBeAddedPopularity += fearRate;
        getCurrentGovernment().setPopularity(getCurrentGovernment().getPopularity() + mustBeAddedPopularity);
    }

    private void updateGranery() {

    }

    private int getTheFoodPopularityEffect(int foodRate) {
        return foodRate * 4;
    }

    private double getTheEatenFoodAmount(int foodRate) {
        return (1 / 2 * foodRate + 1);
    }

    private void updateCoin() {
        int changeAmount = 0;
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() + changeAmount);
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

    }


}
