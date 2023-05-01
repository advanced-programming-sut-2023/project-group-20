package enums;

public enum BuildingTypes {
    Stable("WarBuilding",10);
    String buildingGroup;
    int price;
    int neededWood;
    int neededStone;
    int neededWorker;
    int neededEngineers;
    int hitpoint;
    int popularityEffect;
    int production;
    int capacityOfLivingPeople;
    int capacityOfTroop;




    BuildingTypes(String buildingGroup,int price) {
        this.buildingGroup = buildingGroup;
        this.price=price;
    }
}
