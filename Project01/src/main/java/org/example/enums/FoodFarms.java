package org.example.enums;

public enum FoodFarms {
    //TODO
//    Mill(),
//    Baker(),
//    Brewery(),
//    Granery(),
//    AppleGarden(),
//    DairyProducts(),
//    BarleyField(),
//    Hunt(),
//    WheatField(),
    ;

    private String farmName;
    private String productionName;
    private Integer productionRate;

    FoodFarms(String farmName, String productionName, Integer productionRate) {
        this.farmName = farmName;
        this.productionName = productionName;
        this.productionRate = productionRate;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getProductionName() {
        return productionName;
    }

    public Integer getProductionRate() {
        return productionRate;
    }
}
