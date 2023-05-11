package enums;

public enum TreeTypes {
    //TODO
    // Set your enums
    Palm("", 1),
    DesertTree(),
    Olive(),
    Cherry(),
    Coconut(),
    ;

    TreeTypes(String typeOfTree, String productionName, Integer productionRate, Integer price) {
        this.productionName = productionName;
        this.productionRate = productionRate;
        this.typeOfTree = typeOfTree;
    }

    private String typeOfTree;
    private String productionName;
    private Integer productionRate;
    private Integer price;

    public Integer getProductionRate() {
        return productionRate;
    }

    public String getProductionName() {
        return productionName;
    }

    public String getTypeOfTree() {
        return typeOfTree;
    }

    public Integer getPrice() {
        return price;
    }
}
