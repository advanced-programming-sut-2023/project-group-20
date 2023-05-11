package controller;

import enums.BuildingTypes;
import enums.TreeTypes;
import enums.TroopTypes;
import model.DataBase;
import model.GameInfo.*;
import model.User;
import view.GameMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
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
        this.divideTheMap();
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
                    mapController.getHomeByPosition(j, k).setOwner(getCurrentGame().getGovernments().get(i));
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
        String output = "";
        output += "All Foods Name:\n";
        double[] foodAmountInAllGranery = new double[Granery.foodNames.size()];
        for (int i = 0; i < getCurrentGovernment().getGraneries().size(); i++) {
            HashMap<String, Double> allFoods = getCurrentGovernment().getGraneries().get(i).getAllFoods();
            for (int j = 0; j < allFoods.size(); j++) {
                foodAmountInAllGranery[j] += allFoods.get(Granery.foodNames.get(j));
            }
        }
        for (int i = 0; i < Granery.foodNames.size(); i++) {
            output += Granery.foodNames.get(i) + "-->" + foodAmountInAllGranery[i];
        }
        return output;
    }

    private Government getCurrentGovernment() {
        return getGovernmentByUser(currentUser);
    }

    public String showPopularityFactors() {
        String output = "";
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
        if (!isYourHome(home))
            return "Please select a home in your home";
        getCurrentGame().setSelectedBuildingHome(home);
        return "We select a building in x:<< " + x + " >>" + " and y:<< " + y + " >> home";
    }

    private boolean isYourHome(Home home) {
        if (home.getOwner().equals(getCurrentGovernment()))
            return true;
        return false;
    }

    //TODO
    public String dropBuilding(Matcher matcher) {


        if (CheckController.checkTheNumberInputTruth(matcher.group("x")) || CheckController.checkTheNumberInputTruth(matcher.group("y")))
            return "Please enter the valid x and y";

        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);


        Integer maxHitpoint = getTheMaxHitpointByType(matcher.group("type"));
        Integer neededWorkers = getTheNeededWorkersByType(matcher.group("type"));
        Integer price = getThePriceOfTroop(matcher.group("type"));
        Integer neededStone = getTheNeededStoneByType(matcher.group("type"));
        Integer neededWood = getTheNeededWoodByType(matcher.group("type"));
        String buildingName = matcher.group("type");
        if (home.getBuilding() != null)
            return "You can not drop building in this home because a building is exist already";
        if (buildingName.equals("StockPile")){
            StockPile stockPile=new StockPile(getCurrentGovernment());
            getCurrentGovernment().addStockPile(stockPile);
        }else if(buildingName.equals("Granery")){
            Granery granery=new Granery(getCurrentGovernment());
        }
            if (isWarBuilding(matcher.group("type"))) {
                if (!isEnoughCoinToBuyBuilding(matcher.group("type")))
                    return "more gold needed";
                Integer fireRange;
                Integer defendRange;
                Integer powerOfDestroying;
                WarBuilding warBuilding = new WarBuilding(getCurrentGovernment(), matcher.group("type"), maxHitpoint, powerOfDestroying, neededWorkers, price, neededStone, neededWorkers, fireRange, defendRange);
                home.setBuilding(warBuilding);
            } else if (isFoodFarm(matcher.group("type"))) {
                if (!isEnoughCoinToBuyBuilding(matcher.group("type")))
                    return "more gold needed";
                Integer productionNumber;
                String productionName;
                FoodFarm foodFarm = new FoodFarm(getCurrentGovernment(), matcher.group("type"), maxHitpoint, neededWorkers, price, neededStone, neededWood, productionNumber, productionName);
                home.setBuilding(foodFarm);
            } else if (isTownBuilding(matcher.group("type"))) {
                if (!isEnoughCoinToBuyBuilding(matcher.group("type")))
                    return "more gold needed";
                Integer populationIncrease;
                Integer popularityIncrease;
                TownBuilding townBuilding = new TownBuilding(getCurrentGovernment(), matcher.group("type"), maxHitpoint, neededWorkers, price, neededStone, neededWood, populationIncrease, popularityIncrease);
                home.setBuilding(townBuilding);
            } else if (isMine(matcher.group("type"))) {
                if (!isEnoughCoinToBuyBuilding(matcher.group("type")))
                    return "more gold needed";
                Integer productionNumber;
                String productionName;
                String validFloorType;
                Mine mine = new Mine(getCurrentGovernment(), matcher.group("type"), maxHitpoint, neededWorkers, price, neededStone, neededWood, productionNumber, productionName, validFloorType);
                home.setBuilding(mine);
            } else {
                return "Please enter true type for your building name";
            }
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfBuilding(matcher.group("type")));
        return "We build a " + matcher.group("type") + " for you MyLord";
    }

    public String createUnit(Matcher matcher) {
        if (getCurrentGame().getSelectedBuildingHome() == null)
            return "You have not choose a home yet";
        Integer hitponit = getTheHitpointByType(matcher.group("type"));
        Integer power = getThePowerByType(matcher.group("type"));
        Integer speed = getTheSpeedByType(matcher.group("type"));
        Home selectedHome = getCurrentGame().getSelectedBuildingHome();
        Troop troop;
        if (selectedHome.getBuilding().getType().equals("Barrack")) {
            if (isItBarrackTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                if (!setBarrackTroopNeededWeapons(matcher.group("type")))
                    return "We do not have enough needed weapons to create this unit";
                troop = new Troop(matcher.group("type"), "Barrack", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in Barrack building please first select a true building then try again!";
            }
        } else if (selectedHome.getBuilding().getType().equals("MercenaryPost")) {
            if (isItMercenaryPostTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                troop = new Troop(matcher.group("type"), "MercenaryPost", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in MercenaryPost building please first select a true building then try again!";
            }
        } else if (selectedHome.getBuilding().getType().equals("EngineerGuild")) {
            if (isItEngineerGuildTroop(matcher.group("type"))) {
                if (!isEnoughCoinToBuyTroop(matcher.group("type")))
                    return "We do not have enough needed coin to create this unit";
                troop = new Troop(matcher.group("type"), "EngineerGuild", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in EngineerGuild building please first select a true building then try again!";
            }
        } else {
            return "Please choose a correct place for building troop!";
        }
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfTroop(matcher.group("type")));
        troop.setHome(selectedHome);
        selectedHome.addTroop(troop);
        return "We create a << " + matcher.group("type") + " >> successfully";
    }

    private boolean setBarrackTroopNeededWeapons(String type) {
        ArrayList<String> weapons = new ArrayList<>();
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(type)) {
                weapons = TroopTypes.values()[i].weapons;
            }
        }
        for (int i = 0; i < weapons.size(); i++) {
            if (findTheWeaponNumberInArmories(weapons.get(i)) <= 0)
                return false;
        }
        for (int i = 0; i < weapons.size(); i++) {
            decreaseElementFromArmory(weapons.get(i), 1);
        }
        return true;
    }


    private int findTheWeaponNumberInArmories(String weaponName) {
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            if (((Armoury) getCurrentGovernment().getArmouries().get(i)).getElements(weaponName) > 0)
                return i;
        }
        return -1;
    }

    private boolean isEnoughCoinToBuyTroop(String troopName) {
        Integer price = getThePriceOfTroop(troopName);
        if (price != null && price < getCurrentGovernment().getCoin())
            return true;
        return false;
    }

    private boolean isEnoughCoinToBuyBuilding(String buildingType) {
        Integer price = getThePriceOfTroop(buildingType);
        if (price != null && price < getCurrentGovernment().getCoin())
            return true;
        return false;
    }


    private Integer getThePriceOfTroop(String troopName) {
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(troopName))
                return TroopTypes.values()[i].price;
        }
        return null;
    }

    private Integer getThePriceOfBuilding(String buildingType) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingType))
                return BuildingTypes.values()[i].getPrice();
        }
        return null;
    }

    public String repair() {
        if (getCurrentGame().getSelectedBuildingHome() == null)
            return "You have not choose a home yet";
        int neededStone = getCurrentGame().getSelectedBuildingHome().getBuilding().getNeededStone();
        if (getAllElementFromStockPile("stone") < neededStone)
            return "Sorry more stone needed";
        if (isEnemyNearHome(getCurrentGame().getSelectedBuildingHome()))
            return "You can not repair this building because enemy is near that";
        Building building = getCurrentGame().getSelectedBuildingHome().getBuilding();
        building.setHitpoint(building.getMaxHitpoint());
        decreaseElementFromStockPlie("stone", ((WarBuilding) building).getNeededStone());
        resetSelectedBuildingFromGame();
        return "We repair the building successfully";
    }

    private void resetSelectedBuildingFromGame() {
        getCurrentGame().setSelectedBuildingHome(null);
    }

    private int getAllElementFromStockPile(String elementType) {
        int allAmount = 0;
        for (int i = 0; i < getCurrentGovernment().getStockPiles().size(); i++) {
            StockPile stockPile = getCurrentGovernment().getStockPiles().get(i);
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
                //TODO
                // Check it!!!!!
                stockPile.setElements(elementType, 0);
            }
        }
    }

    private void decreaseElementFromArmory(String elementType, int amount) {
        int decreaseAmount = amount;
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            Armoury armoury = ((Armoury) getCurrentGovernment().getArmouries().get(i));
            decreaseAmount = decreaseAmount - armoury.getElements(elementType);
            if (decreaseAmount <= 0) {
                armoury.setElements(elementType, armoury.getElements(elementType) - amount);
                return;
            } else {
                //TODO
                // Check it!!!!!
                armoury.setElements(elementType, 0);
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
        Home home = mapController.getHomeByPosition(x, y);
        ArrayList<Troop> selectedTroops = returnYourTroopsFromAll(home.getTroops());

        if (selectedTroops.size() == 0)
            return "You do not have any troop here";
        getCurrentGame().setSelectedTroops(selectedTroops);
        getCurrentGame().setSelectedTroopsHome(home);
        return "We select all of your troops that are in this home.";
    }

    public String moveUnit(Matcher matcher, String groupX, String groupY) {
        if (getCurrentGame().getSelectedTroops() == null) {
            return "Please select unit First";
        }
        Home firstHome = getCurrentGame().getSelectedTroopsHome();

        int currentPosX = firstHome.getX();
        int currentPosY = firstHome.getY();
        int targetX = Integer.parseInt(matcher.group(groupX));
        int targetY = Integer.parseInt(matcher.group(groupY));
        resetSelectedTroopsFromGame();
        return moveUnitByPos(firstHome, currentPosX, currentPosY, targetX, targetY);
    }

    private String moveUnitByPos(Home firstHome, int currentPosX, int currentPosY, int targetX, int targetY) {
        int speed = getTheMinimumSpeedOfTroops(getCurrentGame().getSelectedTroops());
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
        setTheTroopsHitpointAfterMoving(unitMotionController.findTheDestinationRace(speed, currentPosX, currentPosY, targetX, targetY));
        return "We move our selected troops to your destination";
    }

    private void setTheTroopsHitpointAfterMoving(ArrayList<Home> race) {
        //TODO
        // The effects of TALE_HA and ENEMY_TROOPS ,....
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

    public String patrolUnit(Matcher matcher) {
        if (getCurrentGame().getSelectedTroops() == null) {
            return "Please select unit First";
        }
        int firsX = Integer.parseInt(matcher.group("x1"));
        int firsY = Integer.parseInt(matcher.group("y1"));
        int targetX = Integer.parseInt(matcher.group("x2"));
        int targetY = Integer.parseInt(matcher.group("y2"));
        Home currentHome = getCurrentGame().getSelectedTroopsHome();
        String firstMove = moveUnitByPos(currentHome, currentHome.getX(), currentHome.getY(), firsX, firsY);
        if (firstMove.equals("We move our selected troops to your destination")) {
            int speed = getTheMinimumSpeedOfTroops(getCurrentGame().getSelectedTroops());
            if (!unitMotionController.isAnyAvailableDestination(speed, firsX, firsY, targetX, targetY))
                return "Can not find available race";
            PatrolTroops patrolTroops = new PatrolTroops(getCurrentGame().getSelectedTroops(), unitMotionController.findTheDestinationRace(speed, firsX, firsY, targetX, targetY), getCurrentGovernment());
            unitMotionController.addPatrolTroops(patrolTroops);
            resetSelectedTroopsFromGame();
            return "Your troops will patrol in your border homes MyLord";
        } else
            return firstMove;
    }

    private void resetSelectedTroopsFromGame() {
        getCurrentGame().setSelectedTroopsHome(null);
        getCurrentGame().setSelectedTroops(null);
    }

    //TODO
    // Make a Command for it
    public String stopPatrolUint(Matcher matcher) {
        Integer indexOfYourLastPatrolledTroops = getTheLastYourPatrolTroops();
        if (indexOfYourLastPatrolledTroops == null)
            return "You have not have any patrol troops yet";
        unitMotionController.removeThePatrolTroopsByIndex(indexOfYourLastPatrolledTroops);
        return "We removed the last ";
    }

    private Integer getTheLastYourPatrolTroops() {
        ArrayList<PatrolTroops> allPatrolTroops = unitMotionController.getPatrolTroops();
        for (int i = allPatrolTroops.size() - 1; i >= 0; i--) {
            if (allPatrolTroops.get(i).getGovernment().equals(getCurrentGovernment()))
                return i;
        }
        return null;
    }

    public String setTroopsState(Matcher matcher) {
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        String state = matcher.group("state");
        Home home = mapController.getHomeByPosition(x, y);
        ArrayList<Troop> yourTroops = returnYourTroopsFromAll(home.getTroops());
        if (yourTroops.size() == 0)
            return "You do not have any troops please change your wanted home or first make a troop hear";
        for (int i = 0; i < yourTroops.size(); i++) {
            yourTroops.get(i).setState(state);
        }
        return "We change your troops state";
    }

    public String attackToEnemy(Matcher matcher) {
        ArrayList<Troop> yourTroops = getCurrentGame().getSelectedTroops();
        if (yourTroops == null)
            return "You do not have any troop here";
        Integer enemyX = Integer.parseInt(matcher.group("x"));
        Integer enemyY = Integer.parseInt(matcher.group("y"));
        Home enemyHome = mapController.getHomeByPosition(enemyX, enemyY);
        ArrayList<Troop> allTroops = enemyHome.getTroops();
        ArrayList<Troop> enemyTroops = returnEnemyTroopsFromAll(allTroops);
        if (enemyTroops.size() == 0)
            return "You do not have any enemy here";
        Home currentHome = getCurrentGame().getSelectedTroopsHome();
        Integer yourX = currentHome.getX();
        Integer yourY = currentHome.getY();
        String moveBeforeAttack = moveUnitByPos(currentHome, yourX, yourY, enemyX, enemyY);
        if (!moveBeforeAttack.equals("We move our selected troops to your destination"))
            return moveBeforeAttack;
        this.setTheTroopsHitpointAfterMoving(unitMotionController.findTheDestinationRace(getTheMinimumSpeedOfTroops(yourTroops), yourX, yourY, enemyX, enemyY));
        for (int i = 0; i < enemyTroops.size() && i < yourTroops.size(); i++) {
            reduceFromTroopHitpoint(yourTroops.get(i), enemyTroops.get(i));
        }
        return "Attacked run successfully MyLord";
    }

    private void setAttackBetweenEnemyAndYours(ArrayList<Troop> yourTroops, ArrayList<Troop> enemyTroops) {
        if (yourTroops.size() >= enemyTroops.size()) {
            for (int j = 0; j < yourTroops.size(); j++) {
                for (int i = 0; i < enemyTroops.size() && i < yourTroops.size(); i++) {
                    reduceFromTroopHitpoint(yourTroops.get(j), enemyTroops.get(i));
                }
            }
        } else {
            for (int j = 0; j < enemyTroops.size(); j++) {
                for (int i = 0; i < enemyTroops.size() && i < yourTroops.size(); i++) {
                    reduceFromTroopHitpoint(yourTroops.get(i), enemyTroops.get(j));
                }
            }
        }
    }

    public String attack(Matcher matcher) {
        int selectedX = Integer.parseInt(matcher.group("x"));
        int selectedY = Integer.parseInt(matcher.group("y"));
        if (getCurrentGame().getSelectedTroops() == null)
            return "Dose not any troops here";
        ArrayList<Troop> yourTroops = returnYourTroopsFromAll(getCurrentGame().getSelectedTroops());
        Home enemyHome = mapController.getHomeByPosition(selectedX, selectedY);
        ArrayList<Troop> enemyTroops = returnEnemyTroopsFromAll(enemyHome.getTroops());
        int yourArcherNumber = 0;
        ArrayList<Troop> archerAndCrossbowMen = new ArrayList<>();
        for (int i = 0; i < yourTroops.size(); i++) {
            if (yourTroops.get(i).getType().equals("Archer") || yourTroops.get(i).getType().equals("CrossbowMen")) {
                yourArcherNumber++;
                yourTroops.get(i).setInBord(true);
                archerAndCrossbowMen.add(yourTroops.get(i));
            }
        }
        if (yourArcherNumber == 0)
            return "You do not have any Archer or CrossbowMen here !";
        for (int i = 0; i < enemyTroops.size() && i < yourArcherNumber; i++) {
            reduceFromTroopHitpoint(archerAndCrossbowMen.get(i), enemyTroops.get(i));
        }
        resetArcherBorder(yourTroops);
        return "We attack to troops which are in target home";
    }

    private void resetArcherBorder(ArrayList<Troop> targetTroops) {
        for (int i = 0; i < targetTroops.size(); i++) {
            targetTroops.get(i).setInBord(false);
        }
    }

    private void reduceFromTroopHitpoint(Troop attacker, Troop target) {
        int attackerPower = attacker.getPower();
        int targetHitpoint = target.getHitpoint();
        if (attackerPower - targetHitpoint > 0)
            target.setHitpoint(0);
        else
            target.setHitpoint(targetHitpoint - attackerPower);
    }

    private ArrayList<Troop> returnEnemyTroopsFromAll(ArrayList<Troop> allTroops) {
        ArrayList<Troop> enemy = new ArrayList<>();
        for (int i = 0; i < allTroops.size(); i++) {
            if (!allTroops.get(i).getOwner().equals(getCurrentGovernment()))
                enemy.add(allTroops.get(i));
        }
        return enemy;
    }

    private ArrayList<Troop> returnYourTroopsFromAll(ArrayList<Troop> allTroops) {
        ArrayList<Troop> yours = new ArrayList<>();
        for (int i = 0; i < allTroops.size(); i++) {
            if (allTroops.get(i).getOwner().equals(getCurrentGovernment()))
                yours.add(allTroops.get(i));
        }
        return yours;
    }

    public String pourOil(Matcher matcher) {

        Home home = getCurrentGame().getSelectedBuildingHome();
        if (home == null)
            return "Please select a pour building first";
        int neighborX = getTheNeighborX(matcher.group("direction"), home.getX());
        int neighborY = getTheNeighborY(matcher.group("direction"), home.getY());
        ArrayList<Troop> yourTroops = returnYourTroopsFromAll(home.getTroops());
        if (!isEnoughEngineer(1, yourTroops))
            return "Not enough engineer!";
        if (!home.getBuilding().getType().equals("OilSmelter"))
            return "Your selected building is not pour building";
        Home neighborHome = mapController.getHomeByPosition(neighborX, neighborY);
        if (neighborHome == null)
            return "Your wanted home is out of border";
        home.setOilExist(true);
        return "We pour oil in " + matcher.group("direction") + " successfully MyLord";
    }

    private boolean isEnoughEngineer(int neededEngineer, ArrayList<Troop> yourTroops) {
        for (int i = 0; i < yourTroops.size(); i++) {
            if (yourTroops.get(i).getType().equals("Engineer"))
                neededEngineer--;
        }
        if (neededEngineer == 0)
            return true;
        return false;
    }

    private int getTheNeighborY(String string, int currentY) {
        if (string.equals("north")) {
            return currentY + 1;
        } else if (string.equals("south")) {
            return currentY - 1;
        }
        return currentY;
    }

    private int getTheNeighborX(String string, int currentX) {
        if (string.equals("east")) {
            return currentX + 1;
        } else if (string.equals("west")) {
            return currentX - 1;
        }
        return currentX;
    }

    public String digTunnel(Matcher matcher) {
        ArrayList<Troop> selectedTroops = getCurrentGame().getSelectedTroops();
        Integer xPos = Integer.parseInt(matcher.group("x"));
        Integer yPos = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(xPos, yPos);
        int tunnelers = theNumberOfTroopOfThisType("Tunneler", selectedTroops);
        if (tunnelers == 0)
            return "You do not have any tunnellers in selected home!";
        home.setTunnelCreated(true);
        return "We created a tunnel for you here MyLord";
    }

    private int theNumberOfTroopOfThisType(String type, ArrayList<Troop> troops) {
        int counterTroop = 0;
        for (int i = 0; i < troops.size(); i++) {
            if (troops.get(i).getType().equals(type))
                counterTroop++;
        }
        return counterTroop;
    }

    public String buildBuilding(Matcher matcher) {
        //TODO
    }

    public String disbandUnit() {
        ArrayList<Troop> selectedTroops = getCurrentGame().getSelectedTroops();
        if (selectedTroops == null)
            return "Please select troops first";
        if (selectedTroops.size() == 0)
            return "You do not have any troops!";

        int populationIncrease = selectedTroops.size();
        getCurrentGovernment().setPopularity(getCurrentGovernment().getPopularity() + populationIncrease);
        removeKilTroopsFromHome(selectedTroops, getCurrentGame().getSelectedTroopsHome());
        return "We disband your selected troops successfully";
    }

    private void removeKilTroopsFromHome(ArrayList<Troop> mustBeReMoved, Home home) {
        ArrayList<Troop> allTroops = home.getTroops();
        int counter = 0;
        for (int i = 0; i < allTroops.size(); i++) {
            for (int j = counter; j < mustBeReMoved.size(); j++) {
                if (allTroops.get(i).equals(mustBeReMoved.get(j))) {
                    allTroops.remove(i);
                    counter++;
                }
            }
        }
        home.setTroops(allTroops);
    }

    public String setTexture(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        if (!checkFloorType(matcher.group("type")))
            return "UnValid type of floor entered!!";
        Home home = mapController.getHomeByPosition(x, y);
        if (home == null)
            return "Home is out of border";
        if (!home.getOwner().equals(getCurrentGovernment()))
            return "You do not have permission of changing the type of Enemies Homes!!!";
        if (home.getBuilding() != null) {
            return "There is a building in << x:" + x + " y:" + y + " >> so you can not change this floor type";
        }
        home.setTypeOfFloor(matcher.group("type"));
        return "We change the home type successfully";
    }


    public String setTextureRectangle(Matcher matcher) {
        int x1 = Integer.parseInt(matcher.group("x1"));
        int y1 = Integer.parseInt(matcher.group("y1"));
        int x2 = Integer.parseInt(matcher.group("x2"));
        int y2 = Integer.parseInt(matcher.group("y2"));
        String type = matcher.group("type");
        if (!checkFloorType(matcher.group("type")))
            return "UnValid type of floor entered!!";
        Home targetHome;
        int minX;
        int minY;
        int maxX;
        int maxY;
        if (x1 < x2) {
            minX = x1;
            maxX = x2;
        } else {
            minX = x2;
            maxX = x1;
        }
        if (y1 < y2) {
            minY = y1;
            maxY = y2;
        } else {
            minY = y2;
            maxY = y1;
        }
        int counterChangeHomes = 0;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                targetHome = mapController.getHomeByPosition(i, j);
                if (targetHome != null) {
                    if (targetHome.getBuilding() != null) {
                        return "There is a building in << x:" + i + " y:" + j + " >> so you can not change this floor type";
                    }
                    if (targetHome.getOwner().equals(getCurrentGovernment())) {
                        targetHome.setTypeOfFloor(type);
                        counterChangeHomes++;
                    }
                }
            }
        }
        if (counterChangeHomes == 0)
            return "The available home in this range is ZERO !";
        return "We change << " + counterChangeHomes + " >> of homes if they are less home were not available to change type";
    }

    public String clear(Matcher matcher) {
        int xPos = Integer.parseInt(matcher.group("x"));
        int yPos = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(xPos, yPos);
        if (home == null)
            return "Out of border home!";
        refreshHomeAsFirstCreated(home);
        return "We clear home and refresh it to it's first";
    }

    private void refreshHomeAsFirstCreated(Home home) {
        home.setTroops(null);
        home.setBuilding(null);
        home.setTypeOfFloor("FlatGround");
        home.setOilExist(false);
        home.setTunnelCreated(false);
        home.setRock(null);
    }

    public String droprock(Matcher matcher) {
        String direction = matcher.group("direction");
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        if (home == null)
            return "Out of board Home!!";
        //TODO
        // Check the command truth
        if (direction.equals("r")) {
            direction = prockRandomDirection();
        }
//        if (direction.equals("n")) {
//
//        } else if (direction.equals("s")) {
//
//        } else if (direction.equals("e")) {
//
//        } else if (direction.equals("w")) {
//
//        } else {
//            return "incorrect direction!";
//        }
        if (!(direction.equals("n") || direction.equals("s") || direction.equals("e") || direction.equals("w")))
            return "incorrect direction!";
        home.setRock(direction);
        return "We set a rock in your chose pos";
    }

    public String prockRandomDirection() {
        Random random = new Random();
        int randomNumber = random.nextInt(10) % 4;
        if (randomNumber == 0)
            return "n";
        else if (randomNumber == 1)
            return "s";
        else if (randomNumber == 2)
            return "e";
        else return "w";
    }

    public String dropTree(Matcher matcher) {
        String treeName = matcher.group("type");
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        if (home == null)
            return "Out of board Home!!";
        if (!checkTreeType(treeName))
            return "incorrect type of tree!";
        if (home.getTree() != null)
            return "A tree does exist already!";
        if (!isTruePlaceForTree(home.getTypeOfFloor()))
            return "You can not drop tree in " + home.getTypeOfFloor() + " !";
        Tree tree = new Tree(treeName, treePrice(treeName), treeProductionRate(treeName), treeProductionName(treeName));
        home.setTree(tree);
        return "We set tree in your chosen home MyLord";
    }

    private boolean isTruePlaceForTree(String floorType) {
        //TODO
        if (floorType.equals("") || floorType.equals("") || floorType.equals("") ||)
            return false;
        return true;
    }

    private String treeProductionName(String treeType) {
        for (int i = 0; i < TreeTypes.values().length; i++) {
            if (TreeTypes.values()[i].getTypeOfTree().equals(treeType))
                return TreeTypes.values()[i].getProductionName();
        }
        return null;
    }

    private Integer treeProductionRate(String treeType) {
        for (int i = 0; i < TreeTypes.values().length; i++) {
            if (TreeTypes.values()[i].getTypeOfTree().equals(treeType))
                return TreeTypes.values()[i].getProductionRate();
        }
        return null;
    }

    private Integer treePrice(String treeType) {
        for (int i = 0; i < TreeTypes.values().length; i++) {
            if (TreeTypes.values()[i].getTypeOfTree().equals(treeType))
                return TreeTypes.values()[i].getPrice();
        }
        return null;
    }

    public String dropUnit(Matcher matcher) {
        //TODO
    }

    private boolean checkFloorType(String type) {
        for (int i = 0; i < DataBase.getTypesOfFloor().size(); i++) {
            if (DataBase.getTypesOfFloor().get(i).equals(type))
                return true;
        }
        return false;
    }

    public String openTrade(Matcher matcher) {
    }

    public String openShop() {
    }

    public void changeTurn() {
        //TODO
        //change the user and apply changes like rates , production of mines and farms attack between troops
    }

    private void applyChanges() {
    }


//    private String checkWaterAreaType(String type) {
//    }

    private boolean checkTreeType(String type) {
        for (int i = 0; i < DataBase.getTypesOfTree().size(); i++) {
            if (DataBase.getTypesOfTree().get(i).equals(type))
                return true;
        }
        return false;
    }

    private void updateOilEngineersBeforeTurn() { //Pour oil if enemy near them

    }

    private void updateAngryTroopBeforeTurn() {
//        except engineers
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
        return ((double) 1 / 2 * foodRate + 1);
    }


    private Integer getTheTaxPopularityEffect(int taxRate) {
        if (taxRate < 0) {
            return (-2 * taxRate + 1);
        } else if (taxRate == 0) {
            return 1;
        } else if (taxRate < 5) {
            return -2 * taxRate;
        } else {
            return (-4 * taxRate + 8);
        }
    }

    private double getTheCoinChangeAfterTax(int taxRate) {
        if (taxRate < 0) {
            return (0.2 * taxRate - 0.4);
        } else if (taxRate == 0) {
            return 0;
        } else {
            return (0.2 * taxRate + 0.4);
        }
    }

    public Government getGovernmentByUser(User user) {
        ArrayList<Government> allGovernments = this.currentGame.getGovernments();
        for (int i = 0; i < allGovernments.size(); i++) {
            if (allGovernments.get(i).getOwner().equals(user))
                return allGovernments.get(i);
        }
        return null;
    }

    public static boolean isItBarrackTroop(String type) {
        for (int i = 0; i < DataBase.getTypesOfBarrack().size(); i++) {
            if (DataBase.getTypesOfBarrack().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isItMercenaryPostTroop(String type) {
        for (int i = 0; i < DataBase.getTypesOfMercenaryPost().size(); i++) {
            if (DataBase.getTypesOfMercenaryPost().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isItEngineerGuildTroop(String type) {
        for (int i = 0; i < DataBase.getTypesOfEngineerGuild().size(); i++) {
            if (DataBase.getTypesOfEngineerGuild().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static Integer getThePowerByType(String type) {
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(type))
                return TroopTypes.values()[i].power;
        }
        return null;
    }

    public static Integer getTheHitpointByType(String type) {
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(type))
                return TroopTypes.values()[i].hitpoint;
        }
        return null;
    }

    public static Integer getTheSpeedByType(String type) {
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(type))
                return TroopTypes.values()[i].speed;
        }
        return null;
    }

    public static Integer getTheMaxHitpointByType(String buildingType) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingType))
                return BuildingTypes.values()[i].getMaxHitpoint();
        }
        return null;
    }

//    public static Integer getThePriceByType(String buildingType) {
//        for (int i = 0; i < BuildingTypes.values().length; i++) {
//            if (BuildingTypes.values()[i].getType().equals(buildingType))
//                return BuildingTypes.values()[i].getPrice();
//        }
//        return null;
//    }

    public static Integer getTheNeededWorkersByType(String buildingType) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingType))
                return BuildingTypes.values()[i].getNeededWorkers();
        }
        return null;
    }

    public static Integer getTheNeededStoneByType(String buildingType) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingType))
                return BuildingTypes.values()[i].getNeededStone();
        }
        return null;
    }

    public static Integer getTheNeededWoodByType(String buildingType) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingType))
                return BuildingTypes.values()[i].getNeededWood();
        }
        return null;
    }

    public static boolean isWarBuilding(String type) {
        for (int i = 0; i < DataBase.getTypeOfWarBuilding().size(); i++) {
            if (DataBase.getTypeOfWarBuilding().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isMine(String type) {
        for (int i = 0; i < DataBase.getTypeOfMines().size(); i++) {
            if (DataBase.getTypeOfMines().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isFoodFarm(String type) {
        for (int i = 0; i < DataBase.getTypeOfFoodFarm().size(); i++) {
            if (DataBase.getTypeOfFoodFarm().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean isTownBuilding(String type) {
        for (int i = 0; i < DataBase.getTypeOfTownBuilding().size(); i++) {
            if (DataBase.getTypeOfTownBuilding().get(i).equals(type))
                return true;
        }
        return false;
    }

    public static boolean getTheProductionNumber() {

    }

}
