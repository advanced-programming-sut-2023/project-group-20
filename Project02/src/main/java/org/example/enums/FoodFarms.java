package org.example.enums;

public enum FoodFarms {
    Mill("Mill", 10, "flour"),
    Baker("Baker", 5, "Bread"),
    Brewery("Brewery", 3, ""),
    Granery("Granery", 0, ""),
    AppleGarden("AppleGarden", 15, "Apple"),
    DairyProducts("DairyProducts", 10, "Cheese"),
    BarleyField("BarleyField", 0, ""),
    Hunt("Hunt", 5, "Meat"),
    WheatField("WheatField", 8, "wheat"),
    ;

    private String farmName;
    private String productionName;
    private Integer productionRate;

    FoodFarms(String farmName, Integer productionRate, String productionName) {
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
