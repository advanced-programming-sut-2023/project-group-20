package enums;

public enum Mines {
    ;
    private String name;
    private String productionName;
    private Integer productionRate;
    private String validFloorType;

    Mines(String name, String productionName, Integer productionRate, String validFloorType) {
        this.name = name;
        this.productionName = productionName;
        this.productionRate = productionRate;
        this.validFloorType = validFloorType;
    }

    public String getName() {
        return name;
    }

    public String getProductionName() {
        return productionName;
    }

    public Integer getProductionRate() {
        return productionRate;
    }

    public String getValidFloorType() {
        return validFloorType;
    }
}
