package org.example.enums;

public enum BuildingTypes {
    //TODO
    // Add all building from data base here
    // Do not remove them from dataBase!!!!

    //Mines
    //TODO
//    IronMine(),
//    StoneMine(),
//    WoodCutter(),
//    OxTether(),
//    StockPile(),
//    armourer(),
//    blacksmith(),
//    Fletcher(),
//    Poleturner(),
//    //WarBuildings
//    SmallStoneGatehouse(),
//    BigStoneGatehouse(),
//    Drawbridge(),
//    LookoutTower(),
//    PerimeterTower(),
//    DefensiveTower(),
//    SquareTower(),
//    CircleTower (),
//    Armoury (),
    Barrack(100,"Barrack",0,"",0,15,0,50,0,0,0,0),
//    MercenaryPost (),
//    EngineerGuild (),
//    KillingPit  (),
//    OilSmelter (),
//    PitchDitch(),
//    CagedWarDogs (),
//    SiegeTent(),
//    Stable (),
//    //FoodFarm
//    Mill (),
//    Baker(),
//    Brewery(),
    Granery (50,"Granery",0,"",0,0,0,100,0,0,0,0),
//    AppleGarden (),
//    DairyProducts (),
//    BarleyField (),
//    Hunt(),
//    WheatField(),
//    //TownBuilding
//    Inn (),
//    Hovel (),
//    Church(),
//    Cathedral (),
    ;

    BuildingTypes(Integer maxHitpoint, String type, Integer productionAmount, String productionName, Integer neededWorkers, Integer neededStone, Integer neededWood, Integer price, Integer fireRange, Integer defendRange, Integer damageAmount, Integer popularityRate) {
        this.maxHitpoint = maxHitpoint;
        this.type = type;
        this.productionAmount = productionAmount;
        this.productionName = productionName;
        this.neededWorkers = neededWorkers;
        this.neededStone = neededStone;
        this.neededWood = neededWood;
        this.price = price;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.damageAmount = damageAmount;
        this.popularityRate = popularityRate;
    }

    private Integer maxHitpoint;
    private String type;
    private Integer productionAmount;
    private String productionName;
    private Integer neededWorkers;
    private Integer neededStone;
    private Integer neededWood;
    private Integer price;
    private Integer fireRange;
    private Integer defendRange;
    private Integer damageAmount;
    private Integer popularityRate;

    public Integer getMaxHitpoint() {
        return maxHitpoint;
    }

    public String getType() {
        return type;
    }

    public Integer getProductionAmount() {
        return productionAmount;
    }

    public String getProductionName() {
        return productionName;
    }

    public Integer getNeededStone() {
        return neededStone;
    }

    public Integer getNeededWood() {
        return neededWood;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getFireRange() {
        return fireRange;
    }

    public Integer getDefendRange() {
        return defendRange;
    }

    public Integer getDamageAmount() {
        return damageAmount;
    }

    public Integer getPopularityRate() {
        return popularityRate;
    }

    public Integer getNeededWorkers() {
        return neededWorkers;
    }
}
