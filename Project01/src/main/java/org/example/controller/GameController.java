package org.example.controller;

import org.example.enums.*;
import org.example.model.DataBase;
import org.example.model.GameInfo.*;
import org.example.view.GameMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;


public class GameController {
    private Government currentGovernment;
    private Game currentGame;
    private int currentTurn;
    private MapController mapController;
    private UnitMotionController unitMotionController;

    public GameController(Game game, Government firstPlayer) {
        currentTurn = 1;
        setCurrentGame(game);
        this.currentGovernment = firstPlayer;
        this.mapController = new MapController(getCurrentGame().getMap(), getCurrentGame());
        this.unitMotionController = new UnitMotionController(getCurrentGame().getMap(), getCurrentGame());
        this.divideTheMap();
    }

    public void setCurrentGovernment(Government currentGovernment) {
        this.currentGovernment = currentGovernment;
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
        for (int i = 0; i < playerNumber; i++) {
            for (int j = dividedX * i + 1; j <= dividedX * (i + 1); j++) {
                for (int k = 1; k < map.getySize(); k++) {
                    if (mapController.getHomeByPosition(j, k) == null) {
                        continue;
                    }
                    mapController.getHomeByPosition(j, k).setOwner(getCurrentGame().getGovernments().get(i));
                }
            }
        }
        for (int i = 0; i < map.getHomes().size(); i++) {
            if (map.getHomes().get(i).getOwner() == null)
                map.getHomes().get(i).setOwner(getCurrentGovernment());
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
        StringBuilder output = new StringBuilder();
        output.append("All Foods Name:\n");
        double[] foodAmountInAllGranery = new double[Granery.foodNames.size()];
        for (int i = 0; i < getCurrentGovernment().getGraneries().size(); i++) {
            HashMap<String, Double> allFoods = getCurrentGovernment().getGraneries().get(i).getAllFoods();
            for (int j = 0; j < allFoods.size(); j++) {
                foodAmountInAllGranery[j] += allFoods.get(Granery.foodNames.get(j));
            }
        }
        for (int i = 0; i < Granery.foodNames.size(); i++) {
            output.append(Granery.foodNames.get(i)).append("-->").append(foodAmountInAllGranery[i]).append(" , ");
        }
        return output.toString();
    }

    private Government getCurrentGovernment() {
        return currentGovernment;
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
        if (taxRate < -3 || taxRate > 8)
            return "Invalid rate !\nPleas enter Rate between -3 to 8 ";
        return "We set the taxRate successfully";
    }

    public String showTaxRate() {
        return "TexRate is:\n" + getCurrentGovernment().getTaxRate();
    }

    public String setFoodRate(Matcher matcher) {
        int foodRate = Integer.parseInt(matcher.group("r"));
        if (foodRate < -2 || foodRate > 2)
            return "Invalid rate !\nPleas enter Rate between -2 to 2 ";
        getCurrentGovernment().setFoodRate(foodRate);
        return "We set the foodRate successfully";
    }

    public String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        if (!isYourHome(home))
            return "Please select a home in your home";
        if (home.getBuilding() == null)
            return "Not any buildings";
        getCurrentGame().setSelectedBuildingHome(home);
        return "We select a building in Home --> x:<< " + x + " >>" + " and y:<< " + y + " >>";
    }

    private boolean isYourHome(Home home) {
        if (home.getOwner().equals(getCurrentGovernment()))
            return true;
        return false;
    }

    public String dropBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        String buildingName = matcher.group("type");
        return tryToMakeBuilding(home, buildingName, new boolean[1]);
    }

    public String tryToMakeBuilding(Home buildingHome, String buildingName, boolean[] isSuccessful) {
        isSuccessful[0] = false;
        Integer maxHitpoint = getTheMaxHitpointByType(buildingName);
        Integer neededWorkers = getTheNeededWorkersByType(buildingName);
        Integer price = getThePriceOfBuilding(buildingName);
        Integer neededStone = getTheNeededStoneByType(buildingName);
        Integer neededWood = getTheNeededWoodByType(buildingName);
        if (buildingHome.getBuilding() != null)
            return "You can not drop building in this home because a building is exist already";
        if (!isItValidBuildName(buildingName))
            return "Enter Valid Building Name!";
        if (!buildingHome.getOwner().equals(getCurrentGovernment()))
            return "This home is not for you !";
        if (!isValidForBuilding(buildingHome.getTypeOfFloor()))
            return "You can not build a building in " + buildingHome.getTypeOfFloor() + " !";
        if (!isEnoughCoinTo(price))
            return "more gold needed";
        if (!isEnoughWorkers(neededWorkers)) {
            return "Not enough workers to run this building so first increase your unemployed !";
        }
        if (getAllElementFromStockPile("stone") < neededStone || getAllElementFromStockPile("wood") < neededWood) {
            return "Sorry more stone or wood needed !";
        }
        if (buildingName.equals("StockPile")) {
            StockPile stockPile = new StockPile(getCurrentGovernment());
            buildingHome.setBuilding(stockPile);
            getCurrentGovernment().addStockPile(stockPile);
        } else if (buildingName.equals("Granery")) {
            Granery granery = new Granery(getCurrentGovernment());
            getCurrentGovernment().addGranery(granery);
            buildingHome.setBuilding(granery);
        } else if (buildingName.equals("Armoury")) {
            Armoury armoury = new Armoury(getCurrentGovernment());
            getCurrentGovernment().addArmoury(armoury);
            buildingHome.setBuilding(armoury);
        } else if (isWarBuilding(buildingName)) {
            Integer fireRange = getTheWarBuildingFireRange(buildingName);
            Integer defendRange = getTheWarBuildingDefendRange(buildingName);
            Integer powerOfDestroying = getTheWarBuildingPowerOfDestroying(buildingName);
            WarBuilding warBuilding = new WarBuilding(getCurrentGovernment(), buildingName, maxHitpoint, powerOfDestroying, neededWorkers, price, neededStone, neededWorkers, fireRange, defendRange);
            if (buildingName.equals("SmallStoneGatehouse")) {
                getCurrentGovernment().setPopulation(getCurrentGovernment().getPopulation() + 8);
                getCurrentGovernment().setUnemployed(getCurrentGovernment().getUnemployed() + 8);
            } else if (buildingName.equals("BigStoneGatehouse")) {
                getCurrentGovernment().setPopulation(getCurrentGovernment().getPopulation() + 10);
                getCurrentGovernment().setUnemployed(getCurrentGovernment().getUnemployed() + 10);
            }
            buildingHome.setBuilding(warBuilding);
        } else if (isFoodFarm(buildingName)) {
            Integer productionNumber = getTheFoodFarmProductionRate(buildingName);
            String productionName = getTheFoodFarmProductionName(buildingName);
            if (!(buildingHome.getTypeOfFloor().equals("Grass") || buildingHome.getTypeOfFloor().equals("FoolMeadow")))
                return "Invalid floor for building << " + buildingName + " >>\n" + "<<< Try home in -->" + "Grass OR --> FoolMeadow" + "types >>>";
            FoodFarm foodFarm = new FoodFarm(getCurrentGovernment(), buildingName, maxHitpoint, neededWorkers, price, neededStone, neededWood, productionNumber, productionName);
            buildingHome.setBuilding(foodFarm);
        } else if (isTownBuilding(buildingName)) {
            Integer populationIncrease = getTheTownPopulationRate(buildingName);
            Integer popularityIncrease = getTheTownPopularity(buildingName);
            TownBuilding townBuilding = new TownBuilding(getCurrentGovernment(), buildingName, maxHitpoint, neededWorkers, price, neededStone, neededWood, populationIncrease, popularityIncrease);
            buildingHome.setBuilding(townBuilding);
        } else if (isMine(buildingName)) {
            Integer productionNumber = getTheMineProductionRate(buildingName);
            String productionName = getTheMineProductionName(buildingName);
            String validFloorType = getTheMineValidType(buildingName);
            if (!validFloorType.equals(buildingHome.getTypeOfFloor()) && (!validFloorType.equals("")))
                return "Invalid floor for building << " + buildingName + " >>\n" + "<<< Try home in --> " + validFloorType + " types >>>";
            Mine mine = new Mine(getCurrentGovernment(), buildingName, maxHitpoint, neededWorkers, price, neededStone, neededWood, productionNumber, productionName);
            buildingHome.setBuilding(mine);
        } else {
            return "Please enter true type for your building name";
        }
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - getThePriceOfBuilding(buildingName));
        isSuccessful[0] = true;
        decreaseElementFromStockPlie("stone", neededStone);
        decreaseElementFromStockPlie("wood", neededStone);
        decreaseUnemployedPeople(neededWorkers);
        return "We build a " + buildingName + " for you MyLord";
    }

    private void decreaseUnemployedPeople(Integer decreaseAmount) {
        getCurrentGovernment().setUnemployed(getCurrentGovernment().getUnemployed() - decreaseAmount);
    }

    private boolean isEnoughWorkers(Integer workers) {
        Integer unemployed = getCurrentGovernment().getUnemployed();
        if (unemployed > workers)
            return true;
        return false;
    }


    private boolean isItValidBuildName(String buildingName) {
        for (int i = 0; i < BuildingTypes.values().length; i++) {
            if (BuildingTypes.values()[i].getType().equals(buildingName))
                return true;
        }
        return false;
    }

    private boolean isValidForBuilding(String floorName) {
        if (floorName.equals("DeapLessWater") || floorName.equals("River") || floorName.equals("Sea") || floorName.equals("Rock"))
            return false;
        return true;
    }

    private boolean isValidForCreateUnit(String floorName) {
        if (floorName.equals("River") || floorName.equals("Sea") || floorName.equals("Rock") || floorName.equals("FoolMeadow") || floorName.equals("Grass"))
            return false;
        return true;
    }

    private String getTheFoodFarmProductionName(String farmName) {
        for (int i = 0; i < FoodFarms.values().length; i++) {
            if (FoodFarms.values()[i].getFarmName().equals(farmName)) {
                return FoodFarms.values()[i].getProductionName();
            }
        }
        return null;
    }

    private Integer getTheFoodFarmProductionRate(String farmName) {
        for (int i = 0; i < FoodFarms.values().length; i++) {
            if (FoodFarms.values()[i].getFarmName().equals(farmName)) {
                return FoodFarms.values()[i].getProductionRate();
            }
        }
        return null;
    }

    private Integer getTheMineProductionRate(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getProductionRate();
            }
        }
        return null;
    }

    private String getTheMineProductionName(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getProductionName();
            }
        }
        return null;
    }

    private String getTheMineValidType(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getValidFloorType();
            }
        }
        return null;
    }

    private Integer getTheMineStoneUsed(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getUsedStonePerTurn();
            }
        }
        return null;
    }

    private Integer getTheMineWoodUsed(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getUsedWoodPerTurn();
            }
        }
        return null;
    }

    private Integer getTheMineIronUsed(String mineName) {
        for (int i = 0; i < Mines.values().length; i++) {
            if (Mines.values()[i].getName().equals(mineName)) {
                return Mines.values()[i].getUsedIronPerTurn();
            }
        }
        return null;
    }

    private Integer getTheTownPopularity(String townName) {
        for (int i = 0; i < TownBuildings.values().length; i++) {
            if (TownBuildings.values()[i].getName().equals(townName)) {
                return TownBuildings.values()[i].getPopularityIncrease();
            }
        }
        return null;
    }

    private Integer getTheTownPopulationRate(String townName) {
        for (int i = 0; i < TownBuildings.values().length; i++) {
            if (TownBuildings.values()[i].getName().equals(townName)) {
                return TownBuildings.values()[i].getPopulationIncrease();
            }
        }
        return null;
    }

    private Integer getTheWarBuildingFireRange(String warBuildingName) {
        for (int i = 0; i < WarBuildings.values().length; i++) {
            if (WarBuildings.values()[i].getName().equals(warBuildingName)) {
                return WarBuildings.values()[i].getFireRange();
            }
        }
        return null;
    }

    private Integer getTheWarBuildingDefendRange(String warBuildingName) {
        for (int i = 0; i < WarBuildings.values().length; i++) {
            if (WarBuildings.values()[i].getName().equals(warBuildingName)) {
                return WarBuildings.values()[i].getDefendRange();
            }
        }
        return null;
    }

    private Integer getTheWarBuildingPowerOfDestroying(String warBuildingName) {
        for (int i = 0; i < WarBuildings.values().length; i++) {
            if (WarBuildings.values()[i].getName().equals(warBuildingName)) {
                return WarBuildings.values()[i].getPowerOfDestroying();
            }
        }
        return null;
    }


    public String createUnit(Matcher matcher) {
        if (getCurrentGame().getSelectedBuildingHome() == null)
            return "You have not choose a home yet";
        if (matcher.group("type") == null)
            return "Please enter troop type!";
        Home selectedHome = getCurrentGame().getSelectedBuildingHome();
        String troopName = matcher.group("type");
        String output = new String();
        Integer count = Integer.parseInt(matcher.group("count"));
        boolean[] isSuccessful = new boolean[1];
        isSuccessful[0] = false;
        int i;
        for (i = 0; i < count; i++) {
            output = tryToCreateUnit(selectedHome, troopName, isSuccessful);
            if (!isSuccessful[0])
                break;
        }
        if (isSuccessful[0])
            return output;
        else {
            return "An error cause not create all troops but we create --> " + i + " :\nError is : " + output;
        }
    }

    public String tryToCreateUnit(Home targetHome, String troopName, boolean[] isSuccessful) {

        isSuccessful[0] = false;
        if (targetHome == null)
            return "You have not choose a home yet";

        Integer hitponit = getTheHitpointByType(troopName);
        Integer power = getThePowerByType(troopName);
        Integer speed = getTheSpeedByType(troopName);
        Integer price = getThePriceOfTroop(troopName);
        Troop troop;
        if (!isValidForCreateUnit(targetHome.getTypeOfFloor())) {
            return "Can not crate troop in " + targetHome.getTypeOfFloor() + " !";
        }
        if (!isValidTroop(troopName))
            return "<< " + troopName + " >> is not valid Troop !";
        if (!isEnoughCoinTo(price))
            return "We do not have enough needed coin to create this unit";
        if (targetHome.getBuilding().getType().equals("Barrack")) {
            if (isItBarrackTroop(troopName)) {
                if (!setBarrackTroopNeededWeapons(troopName))
                    return "We do not have enough needed weapons to create this unit";
                troop = new Troop(troopName, "Barrack", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in Barrack building please first select a true building then try again!";
            }
        } else if (targetHome.getBuilding().getType().equals("MercenaryPost")) {
            if (isItMercenaryPostTroop(troopName)) {
                troop = new Troop(troopName, "MercenaryPost", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in MercenaryPost building please first select a true building then try again!";
            }
        } else if (targetHome.getBuilding().getType().equals("EngineerGuild")) {
            if (isItEngineerGuildTroop(troopName)) {
                troop = new Troop(troopName, "EngineerGuild", getCurrentGovernment(), power, hitponit, speed);
                getCurrentGovernment().addTroop(troop);
            } else {
                return "You are in EngineerGuild building please first select a true building then try again!";
            }
        } else {
            return "Please choose a correct place for building troop!";
        }
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - price);
        troop.setHome(targetHome);
        targetHome.addTroop(troop);
        isSuccessful[0] = true;
        return "We create a << " + troopName + " >> successfully";
    }

    private boolean isValidTroop(String troopName) {
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(troopName))
                return true;
        }
        return false;
    }

    private boolean setBarrackTroopNeededWeapons(String type) {
        ArrayList<String> weapons = new ArrayList<>();
        for (int i = 0; i < TroopTypes.values().length; i++) {
            if (TroopTypes.values()[i].type.equals(type)) {
                weapons = TroopTypes.values()[i].weapons;
                System.out.println("<<setBarrackTroop...>>" + weapons.size());
            }
        }
        for (int i = 0; i < weapons.size(); i++) {
            double exist = getAllElementArmoury(weapons.get(i));
            if (exist <= 0) {
                System.out.println("<<setBarrackTroop...>>" + weapons.get(i) + " -->> " + exist);
                return false;
            }
        }
        for (int i = 0; i < weapons.size(); i++) {
            decreaseElementFromArmory(weapons.get(i), 1);
        }
        return true;
    }


    private double findTheWeaponNumberInArmories(String weaponName) {
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            System.out.println(">>>>>>>>>>>");
            if (getCurrentGovernment().getArmouries().get(i).getElements(weaponName) > 0) {
                System.out.println("TRUE WEAPON <<findTheWeapon..>>" + i);
                return i;
            }
        }
        return -1;
    }

    private boolean isEnoughCoinTo(Integer price) {
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
            return "You have not choose a building yet";
        int neededStone = getCurrentGame().getSelectedBuildingHome().getBuilding().getNeededStone();
        if (getAllElementFromStockPile("stone") < neededStone)
            return "Sorry more stone needed";
        if (isEnemyNearHome(getCurrentGame().getSelectedBuildingHome()))
            return "You can not repair this building because enemy is near that";
        Building building = getCurrentGame().getSelectedBuildingHome().getBuilding();
        building.setHitpoint(building.getMaxHitpoint());
        decreaseElementFromStockPlie("stone", building.getNeededStone());
        resetSelectedBuildingFromGame();
        return "We repair the building successfully";
    }

    private void resetSelectedBuildingFromGame() {
        getCurrentGame().setSelectedBuildingHome(null);
    }

    private double getAllElementFromStockPile(String elementType) {
        double allAmount = 0;
        for (int i = 0; i < getCurrentGovernment().getStockPiles().size(); i++) {
            StockPile stockPile = getCurrentGovernment().getStockPiles().get(i);
            allAmount += stockPile.getElements().get(elementType);
        }
        return allAmount;
    }

    private double getAllElementArmoury(String elementType) {
        double allAmount = 0;
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            Armoury armoury = getCurrentGovernment().getArmouries().get(i);
            allAmount += armoury.getElements().get(elementType);
        }
        return allAmount;
    }

    private double getAllElementGranery(String elementType) {
        double allAmount = 0;
        for (int i = 0; i < getCurrentGovernment().getGraneries().size(); i++) {
            Granery granery = getCurrentGovernment().getGraneries().get(i);
            allAmount += granery.getElements().get(elementType);
        }
        return allAmount;
    }

    private void decreaseElementFromStockPlie(String elementType, double amount) {
        double decreaseAmount = amount;
        for (int i = 0; i < getCurrentGovernment().getStockPiles().size(); i++) {
            StockPile stockPile = getCurrentGovernment().getStockPiles().get(i);
            decreaseAmount = decreaseAmount - stockPile.getElements().get(elementType);
            if (decreaseAmount <= 0) {
                stockPile.setElements(elementType, stockPile.getElements().get(elementType) - amount);
                return;
            } else {
                stockPile.setElements(elementType, 0.0);
            }
        }
    }

    private void decreaseElementFromArmory(String elementType, int amount) {
        double decreaseAmount = amount;
        for (int i = 0; i < getCurrentGovernment().getArmouries().size(); i++) {
            Armoury armoury = ((Armoury) getCurrentGovernment().getArmouries().get(i));
            decreaseAmount = decreaseAmount - armoury.getElements(elementType);
            if (decreaseAmount <= 0) {
                armoury.setElements(elementType, armoury.getElements(elementType) - amount);
                return;
            } else {
                armoury.setElements(elementType, 0.0);
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

    private Integer numberOfEnemyNearHome(Home home) {
        Integer numberOfEnemyNearHome = 0;
        int x = home.getX();
        int y = home.getY();
        for (int i = x - 2; i < x + 2; i++) {
            for (int j = y - 2; j < y + 2; j++) {
                if (i < 1 || i > getCurrentGame().getMap().getxSize() || j < 1 || j > getCurrentGame().getMap().getySize())
                    continue;
                if (!home.getOwner().equals(mapController.getHomeByPosition(i, j).getOwner()))
                    numberOfEnemyNearHome++;
            }
        }
        return numberOfEnemyNearHome;
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
        String out = tryMoveUnit(firstHome, currentPosX, currentPosY, targetX, targetY, new boolean[1]);
        resetSelectedTroopsFromGame();
        return out;
    }

    private String tryMoveUnit(Home firstHome, int currentPosX, int currentPosY, int targetX, int targetY, boolean[] isSuccessful) {
        isSuccessful[0] = false;
        ArrayList<Troop> selectedTroops = getCurrentGame().getSelectedTroops();
        int speed = getTheMinimumSpeedOfTroops(selectedTroops);
        int distanceX = Math.abs(currentPosX - targetX);
        int distanceY = Math.abs(currentPosY - targetY);
        if ((distanceX + distanceY) > speed)
            return "The Speed of Your Troops is lower than your destination";
        if (!unitMotionController.isAnyAvailableDestination(speed, currentPosX, currentPosY, targetX, targetY)) {
            return "Can not find available race";
        }
        removeTroopsFromHome(firstHome, selectedTroops);
        Home seccondHome = mapController.getHomeByPosition(targetX, targetY);
        seccondHome.addTroops(selectedTroops);
        setTheTroopsHitpointAfterMoving(selectedTroops, unitMotionController.findTheDestinationRace(speed, currentPosX, currentPosY, targetX, targetY));

        if (seccondHome.getBuilding() != null && seccondHome.getBuilding().getType().equals("Gate") && selectedTroops.size() > 0) {
            seccondHome.setOwner(selectedTroops.get(0).getOwner());
        }

        isSuccessful[0] = true;
        return "We move our selected troops to your destination";
    }

    private void setTheTroopsHitpointAfterMoving(ArrayList<Troop> targetTroops, ArrayList<Home> race) {
        Integer mustReduced = 0;
        boolean isItPlain = false;
        for (int i = 0; i < race.size(); i++) {
            if (race.get(i).getTypeOfFloor().equals("Plain")) {
                isItPlain = true;
                break;
            }
            if (race.get(i).getBuilding() == null)
                continue;
            if (race.get(i).getBuilding().getType().equals("CagedWarDogs")) {
                mustReduced += 10;
            } else if (race.get(i).getBuilding().getType().equals("PitchDitch") || race.get(i).getBuilding().getType().equals("KillingPit")) {
                mustReduced += 5;
            }
        }
        if (isItPlain) {
            removeKilTroopsFromHome(targetTroops, race.get(race.size() - 1));
        }
        for (int i = 0; i < targetTroops.size(); i++) {
            targetTroops.get(i).setHitpoint(targetTroops.get(i).getHitpoint() - mustReduced);
        }
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
            if (numberOfRemovedTroops != 0 && troopsGovernment.equals(home.getTroops().get(i).getOwner())) {
                numberOfRemovedTroops--;
                continue;
            }
            updatedTroops.add(home.getTroops().get(i));
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
        boolean[] isSuccessful = new boolean[1];
        String firstMove = tryMoveUnit(currentHome, currentHome.getX(), currentHome.getY(), firsX, firsY, isSuccessful);
        if (isSuccessful[0]) {
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
    public String stopPatrolUint() {
        Integer indexOfYourLastPatrolledTroops = getTheLastYourPatrolTroops();
        if (indexOfYourLastPatrolledTroops == null)
            return "You have not have any patrol troops yet";
        unitMotionController.removeThePatrolTroopsByIndex(indexOfYourLastPatrolledTroops);
        return "We removed the last";
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
        boolean[] isSuccessful = new boolean[1];
        String moveBeforeAttack = tryMoveUnit(currentHome, yourX, yourY, enemyX, enemyY, isSuccessful);
        if (!isSuccessful[0])
            return moveBeforeAttack;
        this.setTheTroopsHitpointAfterMoving(yourTroops, unitMotionController.findTheDestinationRace(getTheMinimumSpeedOfTroops(yourTroops), yourX, yourY, enemyX, enemyY));
        setAttackBetweenEnemyAndYours(yourTroops, enemyTroops);
        if (enemyTroops.size() == 0)
            attackToBuilding(yourTroops, enemyHome);
        return "Attacked run successfully MyLord";
    }

    private void attackToBuilding(ArrayList<Troop> yourTroops, Home targetHome) {
        Integer power = 0;
        for (int i = 0; i < yourTroops.size(); i++) {
            power += yourTroops.get(i).getPower();
        }
        setTheWarBetweenEngineBuildingAndBuilding(power, targetHome);
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
    public String selectEngineBuilding(Matcher matcher) {
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(x, y);
        if (home == null)
            return "Invalid home pos !";
        if (home.getEngineerBuilding() == null)
            return "Not exist engine building !";
        if (!isYourHome(home))
            return "Please select a home in your home";
        getCurrentGame().setSelectEngineBuildingHome(home);
        return "We select this engine building";
    }

    public String engineAttack(Matcher matcher) {
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home targetHome = mapController.getHomeByPosition(x, y);
        EngineerBuilding selectedEngineerBuilding = getCurrentGame().getSelectEngineBuildingHome().getEngineerBuilding();
        String engineName = selectedEngineerBuilding.getName();
        if (targetHome == null)
            return "Invalid home";
        if (isYourHome(targetHome))
            return "This your home !\nYou can not attack to your home";
        if (engineName.equals("MovableManjenigh") || engineName.equals("StopManjenigh") || engineName.equals("FireStoneDropper")) {
            setTheWarBetweenEngineBuildingAndBuilding(getDestroyPowerOfEngineBuilding(engineName), targetHome);
        }
        getCurrentGame().setSelectEngineBuildingHome(null);
        return "We attacked to destination successfully";
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
//        for (int i = 0; i < enemyTroops.size() && i < yourArcherNumber; i++) {
//            reduceFromTroopHitpoint(archerAndCrossbowMen.get(i), enemyTroops.get(i));
//        }
        setAttackBetweenEnemyAndYours(archerAndCrossbowMen, enemyTroops);
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

    public String digDitch(Matcher matcher) {
        ArrayList<Troop> selectedTroops = getCurrentGame().getSelectedTroops();
        Integer xPos = Integer.parseInt(matcher.group("x"));
        Integer yPos = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(xPos, yPos);
        int tunnelers = theNumberOfTroopOfThisType("Tunneler", selectedTroops);
        if (tunnelers == 0)
            return "You do not have any tunnellers in selected home!";
        home.setDitchCreated(true);
        return "We created a ditch for you here MyLord";
    }

    public String fillDitch(Matcher matcher) {
        ArrayList<Troop> selectedTroops = getCurrentGame().getSelectedTroops();
        Integer xPos = Integer.parseInt(matcher.group("x"));
        Integer yPos = Integer.parseInt(matcher.group("y"));
        Home home = mapController.getHomeByPosition(xPos, yPos);
        int tunnelers = theNumberOfTroopOfThisType("Tunneler", selectedTroops);
        if (tunnelers == 0)
            return "You do not have any tunnellers in selected home!";
        home.setDitchCreated(false);
        return "We fill ditch for you here MyLord";
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

        ArrayList<Troop> selectedTroop = getCurrentGame().getSelectedTroops();
        Home home = getCurrentGame().getSelectedTroopsHome();
        String engineName = matcher.group("equipmentname");
        if (selectedTroop == null)
            return "Please choose troops first";
        if (!isValidEngineBuilding(engineName))
            return "<< " + engineName + " >> is not valid name please enter true engine building name";
        Integer neededEngineers = getTheNeededEngineers(engineName);
        Integer activeEngineers = getEngineerNumberFromTroops(selectedTroop);
        if (neededEngineers == null)
            return "Invalid engine building try to build !";
        if (activeEngineers < neededEngineers)
            return "More engineers needed to run this building";

        return tryMakeEngineBuilding(engineName, home, selectedTroop, neededEngineers);
    }

    private void removeFromYourActiveSelectedEngineer(Home troopHome, ArrayList<Troop> selectedTroops, Integer decreasingNumber) {
        for (int i = 0; i < selectedTroops.size(); i++) {
            if (decreasingNumber <= 0)
                return;
            if (selectedTroops.get(i).getType().equals("Engineer")) {
                ArrayList<Troop> mustBeRemoved = new ArrayList<>();
                mustBeRemoved.add(selectedTroops.get(i));
                removeTroopsFromHome(troopHome, mustBeRemoved);
                decreasingNumber--;
            }
        }
    }


    private String tryMakeEngineBuilding(String buildingName, Home targetHome, ArrayList<Troop> selectedTroops, Integer neededEngineers) {
        if (!isValidForCreateUnit(targetHome.getTypeOfFloor()))
            return "You can not build engine building in" + targetHome.getTypeOfFloor();
        if (targetHome.getEngineerBuilding() != null)
            return "An EngineBuilding exist in this home already !";
        Integer price = getPriceOfEngineBuilding(buildingName);
        Integer power = getDestroyPowerOfEngineBuilding(buildingName);
        Integer hitpoint = getHitpointOfEngineBuilding(buildingName);
        if (!isEnoughCoinTo(price))
            return "More gold need to run this unit !";
        EngineerBuilding engineerBuilding = new EngineerBuilding(getCurrentGovernment(), buildingName, price, hitpoint, power);
        targetHome.setEngineerBuilding(engineerBuilding);
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() - price);
        removeFromYourActiveSelectedEngineer(targetHome, selectedTroops, neededEngineers);
        return "We build << " + buildingName + " >> equipment for you MyLord";
    }

    private boolean isValidEngineBuilding(String buildingName) {
        for (int i = 0; i < TypeOfEngineerBuildings.values().length; i++) {
            if (TypeOfEngineerBuildings.values()[i].getName().equals(buildingName))
                return true;
        }
        return false;
    }

    private Integer getPriceOfEngineBuilding(String buildingName) {
        for (int i = 0; i < TypeOfEngineerBuildings.values().length; i++) {
            if (TypeOfEngineerBuildings.values()[i].getName().equals(buildingName))
                return TypeOfEngineerBuildings.values()[i].getPrice();
        }
        return null;
    }

    private Integer getDestroyPowerOfEngineBuilding(String buildingName) {
        for (int i = 0; i < TypeOfEngineerBuildings.values().length; i++) {
            if (TypeOfEngineerBuildings.values()[i].getName().equals(buildingName))
                return TypeOfEngineerBuildings.values()[i].getDestroyingPower();
        }
        return null;
    }

    private Integer getHitpointOfEngineBuilding(String buildingName) {
        for (int i = 0; i < TypeOfEngineerBuildings.values().length; i++) {
            if (TypeOfEngineerBuildings.values()[i].getName().equals(buildingName))
                return TypeOfEngineerBuildings.values()[i].getMaxHitpoint();
        }
        return null;
    }

    private Integer getEngineerNumberFromTroops(ArrayList<Troop> targetTroops) {
        Integer number = 0;
        for (int i = 0; i < targetTroops.size(); i++) {
            if (targetTroops.get(i).getType().equals("Engineer"))
                number++;
        }
        return number;
    }

    private Integer getTheNeededEngineers(String engineBuildingName) {
        for (int i = 0; i < TypeOfEngineerBuildings.values().length; i++) {
            if (TypeOfEngineerBuildings.values()[i].getName().equals(engineBuildingName))
                return TypeOfEngineerBuildings.values()[i].getNeededEngineers();
        }
        return null;
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
        String newTypeOfFloor = matcher.group("type");
        if (!checkFloorType(newTypeOfFloor))
            return "UnValid type of floor entered!!";
        Home home = mapController.getHomeByPosition(x, y);
        if (home == null)
            return "Home is out of border";
        if (!home.getOwner().equals(getCurrentGovernment()))
            return "You do not have permission of changing the type of Enemies Homes!!!";
        if (home.getBuilding() != null) {
            return "There is a building in << x:" + x + " y:" + y + " >> so you can not change this floor type";
        }
        home.setTypeOfFloor(newTypeOfFloor);
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
            for (int j = minY; j <= maxY; j++) {
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
        if (direction.equals("r")) {
            direction = prockRandomDirection();
        }
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
        Tree tree = new Tree(treeName);
        home.setTree(tree);
        return "We set tree in your chosen home MyLord";
    }

    private boolean isTruePlaceForTree(String floorType) {
        if (floorType.equals("FoolMeadow") || floorType.equals("Grass"))
            return false;
        return true;
    }

    public String dropUnit(Matcher matcher) {
        Integer homeX = Integer.parseInt(matcher.group("x"));
        Integer homeY = Integer.parseInt(matcher.group("y"));
        String troopName = matcher.group("type");
        Integer troopCount = Integer.parseInt(matcher.group("count"));
        Home home = mapController.getHomeByPosition(homeX, homeY);
        if (home == null)
            return "Out of board home";
        boolean[] isSuccessful = new boolean[1];
        String tryOutput;
        for (int i = 0; i < troopCount; i++) {
            tryOutput = tryToCreateUnit(home, troopName, isSuccessful);
            if (!isSuccessful[0])
                return "We crete << " + (i + 1) + " >> for you and the problem to another is :\n<< " + tryOutput + " >> !";
        }
        return "We drop all troops you want MyLord";
    }

    private boolean checkFloorType(String type) {
        for (int i = 0; i < DataBase.getTypesOfFloor().size(); i++) {
            if (DataBase.getTypesOfFloor().get(i).equals(type))
                return true;
        }
        return false;
    }

    public String openTrade() {
        TradeController tradeController = new TradeController(getCurrentGovernment(), getCurrentGame());
        tradeController.start();
        return "We exit from trade menu";
    }

    public String openShop() {
        ShopController shopController = new ShopController(getCurrentGovernment());
        shopController.start();
        return "We exit from shop menu";
    }

    public String changeTurn() {
        ArrayList<Government> allGovernment = getCurrentGame().getGovernments();
        for (int i = 0; i < allGovernment.size(); i++) {
            allGovernment.get(i).getOwner().setHighScore(allGovernment.get(i).getOwner().getHighScore() + 12);
            allGovernment.get(i).getOwner().setHighScore(allGovernment.get(i).getOwner().getHighScore() + (int) allGovernment.get(i).getCoin() / 120);
        }
        if (getCurrentGovernment().equals(allGovernment.get(allGovernment.size() - 1)) && currentTurn == getCurrentGame().getTurnNumber()) {
            LoginController.winner(currentGame);
            return "";
        } else {
            increaseCurrentTurn();
        }
        applyChanges();
        return "We change turn and the turn number is " + getCurrentGame().getCurrentTurn() + " and the player is : " + getCurrentGovernment().getOwner().getNickname() + "\n";
    }

    private void applyChanges() {
        System.out.println("<<applyChange>>");
        updateOilEngineersBeforeTurn();
        updateAngryTroopBeforeTurn();
        Integer availableMineCounter = updateStockPilesAfterTurn();
        updateArmouryAfterTurn(availableMineCounter);
        updatePopularityAndPopulationAfterTurn();
        updateGraneriesAfterTurn();
        updateTroopsAfterTurn();
        updateCoinAfterTurn();
        updateGameAfterTurn();
        updateBuildingsAfterTurn();
        updatePatrolTroopsAfterTurn();
        updateEngineBuilding();
    }

    private boolean checkTreeType(String type) {
        for (int i = 0; i < DataBase.getTypesOfTree().size(); i++) {
            if (DataBase.getTypesOfTree().get(i).equals(type))
                return true;
        }
        return false;
    }

    private void updateOilEngineersBeforeTurn() { //Pour oil if enemy near them
        ArrayList<Troop> yourAllTroops = getCurrentGovernment().getTroops();
        Home home;
        for (int i = 0; i < yourAllTroops.size(); i++) {
            if (yourAllTroops.get(i).getType().equals("Engineer") && yourAllTroops.get(i).getHome().getBuilding().equals("OilSmelter")) {
                home = yourAllTroops.get(i).getHome();
                if (yourAllTroops.get(i).equals("offensive")) {
                    if (numberOfEnemyNearHome(home) >= 1)
                        home.setOilExist(true);
                } else if (yourAllTroops.get(i).getState().equals("defensive")) {
                    if (numberOfEnemyNearHome(home) >= 3)
                        home.setOilExist(true);
                }
            }
        }
    }

    private void updateAngryTroopBeforeTurn() {
        ArrayList<Troop> yourTroops = getCurrentGovernment().getTroops();
        ArrayList<Troop> angryTroops = new ArrayList<>();
        ArrayList<Troop> allEnemyTroops = new ArrayList<>();
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < yourTroops.size(); i++) {
            if (yourTroops.get(i).getState().equals("offensive"))
                angryTroops.add(yourTroops.get(i));
        }
        for (int i = 0; i < allHomes.size(); i++) {
            ArrayList<Troop> thisHomeEnemyTroops = returnEnemyTroopsFromAll(allHomes.get(i).getTroops());
            allEnemyTroops.addAll(thisHomeEnemyTroops);
        }
        if (yourTroops.size() < allEnemyTroops.size()) {
            ArrayList<Troop> targetTroops = new ArrayList<>();
            for (int i = 0; i < yourTroops.size(); i++) {
                targetTroops.add(allEnemyTroops.get(i));
            }
            setAttackBetweenEnemyAndYours(yourTroops, targetTroops);
        } else {
            setAttackBetweenEnemyAndYours(yourTroops, allEnemyTroops);
        }

    }

    private void updatePopularityAndPopulationAfterTurn() {
        int foodRate = getCurrentGovernment().getFoodRate();
        int fearRate = getCurrentGovernment().getFearRate();
        int taxRate = getCurrentGovernment().getTaxRate();
        int mustBeAddedPopularity = 0;
        int mustBeAddedPopulation = 0;
        mustBeAddedPopularity += getTheFoodPopularityEffectPerOne(foodRate) * getCurrentGovernment().getPopularity();
        mustBeAddedPopularity += getTheTaxPopularityEffectPerOne(taxRate) * getCurrentGovernment().getPopulation();
        mustBeAddedPopularity += fearRate;

        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).getOwner().equals(getCurrentGovernment()) && allHomes.get(i).getBuilding() != null && allHomes.get(i).getBuilding() instanceof TownBuilding) {
                mustBeAddedPopularity += ((TownBuilding) allHomes.get(i).getBuilding()).getPopularityIncrease();
                mustBeAddedPopulation += ((TownBuilding) allHomes.get(i).getBuilding()).getPopulationIncrease();
            }
        }
        getCurrentGovernment().setPopularity(getCurrentGovernment().getPopularity() + mustBeAddedPopularity);
        getCurrentGovernment().setPopulation(getCurrentGovernment().getPopulation() + mustBeAddedPopulation);
    }

    private void updateGraneriesAfterTurn() {   // The production of farms

        setFoodRateEffect();
        setTheFarmEffect();
    }

    private void setTheFarmEffect() {
        ArrayList<FoodFarm> allFoodFarm = new ArrayList<>();
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).getBuilding() != null && allHomes.get(i).getOwner().equals(getCurrentGovernment()) && allHomes.get(i).getBuilding() instanceof FoodFarm)
                allFoodFarm.add(((FoodFarm) allHomes.get(i).getBuilding()));
        }

        for (int i = 0; i < allFoodFarm.size(); i++) {
            if (allFoodFarm.get(i).getProductionNumber() > 0) {
                addFoodToGranery(allFoodFarm.get(i).getProductionName(), allFoodFarm.get(i).getProductionNumber());
            }
        }
    }

    private void addFoodToGranery(String FoodName, int amount) {

        ArrayList<Granery> allGraneries = getCurrentGovernment().getGraneries();
        for (int i = 0; i < allGraneries.size(); i++) {
            if (amount <= 0)
                return;
            if (allGraneries.get(i).getCapacity() > 0) {
                if (allGraneries.get(i).getCapacity() >= amount) {
                    allGraneries.get(i).setCapacity(allGraneries.get(i).getCapacity() - amount);
                    allGraneries.get(i).setElements(FoodName, allGraneries.get(i).getElements(FoodName) + ((double) amount));
                    return;
                } else {
                    Double availableIncrease = allGraneries.get(i).getCapacity();
                    allGraneries.get(i).setCapacity(0.0);
                    allGraneries.get(i).setElements(FoodName, allGraneries.get(i).getElements(FoodName) + availableIncrease);
                    amount -= availableIncrease;
                }
            }
        }
    }

    private void setFoodRateEffect() {
        Double eatenFood = getTheEatenFoodAmountPerOne(getCurrentGovernment().getFoodRate()) * getCurrentGovernment().getPopulation();
        System.out.println("<<setFoodRate..>>" + eatenFood);
        ArrayList<Granery> allGraneries = getCurrentGovernment().getGraneries();
        HashMap<String, Double> allFoods;
        for (int i = 0; i < allGraneries.size(); i++) {
            if (eatenFood <= 0)
                break;
//            allFoods = allGraneries.get(i).getAllFoods();
            eatenFood = reduceRandomFoodFromAGranery(allGraneries.get(i), eatenFood);
        }
    }

    private Double reduceRandomFoodFromAGranery(Granery granery, Double amountOfDecrease) {
        Double existFoodAmount;
        for (int i = 0; i < Granery.foodNames.size(); i++) {
            if (amountOfDecrease <= 0)
                return amountOfDecrease;
            existFoodAmount = granery.getElements(Granery.foodNames.get(i));
            if (existFoodAmount > amountOfDecrease) {
//                allFoods.put(Granery.foodNames.get(i), existFoodAmount - amountOfDecrease);
                granery.setElements(Granery.foodNames.get(i), existFoodAmount - amountOfDecrease);
                amountOfDecrease = -1.0;
            } else {
//                allFoods.put(Granery.foodNames.get(i), 0.0);
                granery.setElements(Granery.foodNames.get(i), 0.0);
                amountOfDecrease -= existFoodAmount;
            }
        }
        Double leftDecrease = amountOfDecrease;
        return leftDecrease;
    }

    private Integer updateStockPilesAfterTurn() {   // The production of mines
        Integer availableMineCount = setTheMineUsageEffects();
        setTheMineEffects();
        return availableMineCount;
    }

    private void setTheMineEffects() {
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        ArrayList<Mine> allMines = new ArrayList<>();
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).getBuilding() != null && allHomes.get(i).getBuilding().getOwner().equals(getCurrentGovernment()) && allHomes.get(i).getBuilding() instanceof Mine)
                allMines.add(((Mine) allHomes.get(i).getBuilding()));
        }
        for (int i = 0; i < allMines.size(); i++) {
            String mineName = allMines.get(i).getType();
            if (getTheMineProductionRate(mineName) > 0)
                increaseElementOfStockPile(getTheMineProductionName(mineName), getTheMineProductionRate(mineName));
        }
    }

    private Integer setTheMineUsageEffects() {
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        ArrayList<Mine> allMines = new ArrayList<>();
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).getBuilding() != null && allHomes.get(i).getBuilding().getOwner().equals(getCurrentGovernment()) && (allHomes.get(i).getBuilding() instanceof Mine)) {
                allMines.add(((Mine) allHomes.get(i).getBuilding()));
            }
        }

        Double allNeededStone = 0.0;
        Double allNeededWood = 0.0;
        Double allNeededIron = 0.0;
        Double availableStone = getAllElementFromStockPile("stone");
        Double availableWood = getAllElementFromStockPile("wood");
        Double availableIron = getAllElementFromStockPile("iron");
        int counterOfActiveMine = 0;
        for (int i = 0; i < allMines.size(); i++) {
            String mineName = allMines.get(i).getType();
            if (allNeededStone > availableStone || allNeededIron > availableIron || allNeededWood > availableWood) {
                if (getTheMineStoneUsed(mineName) != 0 || getTheMineWoodUsed(mineName) != 0 || getTheMineIronUsed(mineName) != 0)
                    continue;
            }
            allNeededStone += getTheMineStoneUsed(mineName);
            allNeededWood += getTheMineWoodUsed(mineName);
            allNeededIron += getTheMineIronUsed(mineName);
            counterOfActiveMine++;
        }
        decreaseElementFromStockPlie("stone", allNeededStone);
        decreaseElementFromStockPlie("wood", allNeededWood);
        decreaseElementFromStockPlie("iron", allNeededIron);
        return counterOfActiveMine;
    }

    private void increaseElementOfStockPile(String productName, int amount) {
        ArrayList<StockPile> allStockpiles = getCurrentGovernment().getStockPiles();
        for (int i = 0; i < allStockpiles.size(); i++) {
            if (amount < 0)
                return;
            if (amount > allStockpiles.get(i).getCapacity()) {
                allStockpiles.get(i).setElements(productName, allStockpiles.get(i).getCapacity());
                allStockpiles.get(i).setCapacity(0.0);
            } else {
                allStockpiles.get(i).setElements(productName, amount - 0.0);
                allStockpiles.get(i).setCapacity(allStockpiles.get(i).getCapacity() - amount);
            }
        }
    }


    private void updateArmouryAfterTurn(Integer availableCounter) {
        ArrayList<Mine> allMine = new ArrayList<>();
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            Building building = allHomes.get(i).getBuilding();
            if (!(building instanceof Mine))
                return;
            if ((building.getType().equals("armourer") || building.getType().equals("Fletcher") || building.getType().equals("Poleturner") || building.getType().equals("blacksmith"))) {
                allMine.add(((Mine) building));
            }
        }
        for (int i = 0; i < allMine.size() && i < availableCounter; i++) {
            increaseElementOfStockPile(allMine.get(i).getProductionName(), allMine.get(i).getProductionAmount());
        }
    }


    private void updateTroopsAfterTurn() {   // The war effects
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        reduceAllTroopsInBadFloor(allHomes);
        for (int i = 0; i < allHomes.size(); i++) {
            ArrayList<Troop> thisHomeAllTroops = allHomes.get(i).getTroops();
            ArrayList<Troop> thisHomeDeadTroops = new ArrayList<>();
            for (int j = 0; j < thisHomeAllTroops.size(); j++) {
                if (thisHomeAllTroops.get(j).getHitpoint() < 0)
                    thisHomeDeadTroops.add(thisHomeAllTroops.get(j));
            }
            removeKilTroopsFromHome(thisHomeDeadTroops, allHomes.get(i));
        }
    }

    private void reduceAllTroopsInBadFloor(ArrayList<Home> allHome) {
        Integer mustBeDecreaseFromHitpoint;
        for (int i = 0; i < allHome.size(); i++) {
            if (allHome.get(i).getBuilding() == null)
                continue;
            mustBeDecreaseFromHitpoint = 0;
            if (allHome.get(i).getBuilding().getType().equals("CagedWarDogs")) {
                mustBeDecreaseFromHitpoint += 10;
            } else if (allHome.get(i).getBuilding().getType().equals("PitchDitch") || allHome.get(i).getBuilding().getType().equals("KillingPit")) {
                mustBeDecreaseFromHitpoint += 5;
            }
            ArrayList<Troop> thisHomeAllTroops = allHome.get(i).getTroops();
            for (int j = 0; j < thisHomeAllTroops.size(); j++) {
                thisHomeAllTroops.get(i).setHitpoint(thisHomeAllTroops.get(i).getHitpoint() - mustBeDecreaseFromHitpoint);
            }
        }
    }

    private void updateBuildingsAfterTurn() {
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).isTunnelCreated()) {
                allHomes.get(i).setBuilding(null);
                allHomes.get(i).setTunnelCreated(false);
            }
        }
        for (int i = 0; i < allHomes.size(); i++) {
            if (allHomes.get(i).getBuilding() != null && allHomes.get(i).getBuilding().getHitpoint() < 0) {
                allHomes.get(i).setBuilding(null);
            }
        }
    }

    private void updateCoinAfterTurn() {
        Double changeAmount;
        changeAmount = getTheCoinChangeAfterTaxPerOne(getCurrentGovernment().getTaxRate()) * getCurrentGovernment().getPopulation();
        getCurrentGovernment().setCoin(getCurrentGovernment().getCoin() + changeAmount);
    }


    private void updateGameAfterTurn() {
        getCurrentGame().setSelectedBuildingHome(null);
        getCurrentGame().setSelectedTroops(null);
        getCurrentGame().setSelectedTroopsHome(null);
        Integer lastIndex = getCurrentGame().getGovernments().size() - 1;
        Government lastGovernment = getCurrentGame().getGovernments().get(lastIndex);
        if (getCurrentGovernment().equals(lastGovernment)) {
            getCurrentGame().increaseCurrentTurn();
            this.setCurrentGovernment(getCurrentGame().getGovernments().get(0));
        } else {
            int nextIndex = getCurrentGame().getGovernments().indexOf(getCurrentGovernment()) + 1;
            this.setCurrentGovernment(getCurrentGame().getGovernments().get(nextIndex));

        }

    }

    private void updatePatrolTroopsAfterTurn() {
        ArrayList<PatrolTroops> allPatrolTroops = unitMotionController.getPatrolTroops();
        for (int i = 0; i < allPatrolTroops.size(); i++) {
            int raceCount = allPatrolTroops.get(i).getRacePatrollingCountUntilNow();
            allPatrolTroops.get(i).increaseRaceCount();
            ArrayList<Troop> targetTroops = allPatrolTroops.get(i).getPatrolTroops();
            ArrayList<Home> race = allPatrolTroops.get(i).getRace();
            setTheTroopsHitpointAfterMoving(targetTroops, race);
            removeTroopsFromHome(allPatrolTroops.get(i).getCurrentHome(), targetTroops);
            for (int j = 0; j < targetTroops.size(); j++) {
                if ((raceCount % 2) == 0)
                    targetTroops.get(i).setHome(race.get(0));
                else
                    targetTroops.get(i).setHome(race.get(race.size() - 1));
            }
        }
    }

    private void updateEngineBuilding() {
        ArrayList<Home> allHomes = getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            EngineerBuilding currentEngineBuilding = allHomes.get(i).getEngineerBuilding();
            if (currentEngineBuilding != null && currentEngineBuilding.getOwner().equals(getCurrentGovernment())) {
                if (allHomes.get(i).getBuilding() != null && (!allHomes.get(i).getBuilding().getOwner().equals(getCurrentGovernment()))) {
                    setTheWarBetweenEngineBuildingAndBuilding(currentEngineBuilding.getDestroyingPower(), allHomes.get(i));
                }
            }
        }
    }

    private void setTheWarBetweenEngineBuildingAndBuilding(Integer destroyingPower, Home targetHome) {
        Building targetBuilding = targetHome.getBuilding();
        if (targetBuilding.getHitpoint() < destroyingPower) {
            targetHome.setBuilding(null);
        }
        targetBuilding.setHitpoint(targetBuilding.getHitpoint() - destroyingPower);
    }

    private int getTheFoodPopularityEffectPerOne(int foodRate) {
        return foodRate * 4;
    }

    private double getTheEatenFoodAmountPerOne(int foodRate) {
        return ((double) 1 / 2 * foodRate + 1);
    }


    private Integer getTheTaxPopularityEffectPerOne(int taxRate) {
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

    private double getTheCoinChangeAfterTaxPerOne(int taxRate) {
        if (taxRate < 0) {
            return (0.2 * taxRate - 0.4);
        } else if (taxRate == 0) {
            return 0;
        } else {
            return (0.2 * taxRate + 0.4);
        }
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

    public void increaseCurrentTurn() {
        this.currentTurn++;
    }

    public String showGovernmentInfo() {
        String out = "";
        Double stone = getAllElementFromStockPile("stone");
        Double wood = getAllElementFromStockPile("wood");
        Double Iron = getAllElementFromStockPile("iron");
        Double Bow = getAllElementArmoury("Bow");
        Double Apple = getAllElementGranery("Apple");
        Government targetGovernment = getCurrentGovernment();
        out += "Name : " + targetGovernment.getOwner().getNickname();
        out += " Gold : " + targetGovernment.getCoin();
        out += "Population : " + getCurrentGovernment().getPopulation() + " - Popularity " + getCurrentGovernment().getPopularity();
        out += " Stone : " + stone + " - Iron : " + Iron + " - Wood : " + wood + " - Bow : " + Bow + " - Apple : " + Apple;
        return out;
    }
}
