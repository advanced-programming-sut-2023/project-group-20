package model.GameInfo;

public class Mine extends Building {
    private Integer productionAmount;
    private String productionName;
    private final String validFloorType;

    public Mine(Government owner, String type, Integer maxHitpoint, Integer needWorkers, Integer price, Integer neededStone, Integer neededWood, Integer productionAmount, String productionName, String validFloorType) {
        super(owner, type, maxHitpoint, needWorkers, price, neededStone, neededWood);
        this.productionAmount = productionAmount;
        this.productionName = productionName;
        this.validFloorType = validFloorType;
    }

    public Integer getProductionAmount() {
        return productionAmount;
    }

    public String getProductionName() {
        return productionName;
    }

    public String getValidFloorType() {
        return validFloorType;
    }
}
