package org.example.model.GameInfo;

import java.util.ArrayList;

public class Good {
    private static ArrayList<Good> goods = new ArrayList<>();

    static {
        goods.add(new Good("Apple", 10.0, 5.0));
        goods.add(new Good("Meat", 30.0, 20.0));
        goods.add(new Good("Cheese", 15.0, 10.0));
        goods.add(new Good("Bread", 20.0, 15.0));
        goods.add(new Good("stone", 30.0, 20.0));
        goods.add(new Good("wood", 30.0, 20.0));
        goods.add(new Good("iron", 30.0, 20.0));
        goods.add(new Good("pitch", 30.0, 20.0));
        goods.add(new Good("wheat", 30.0, 20.0));
        goods.add(new Good("flour", 30.0, 20.0));
        goods.add(new Good("LeatherArmour", 60.0, 40.0));
        goods.add(new Good("Mace", 60.0, 40.0));
        goods.add(new Good("Sword", 60.0, 40.0));
        goods.add(new Good("MetalArmour", 60.0, 40.0));
        goods.add(new Good("Bow", 60.0, 40.0));
        goods.add(new Good("Crossbow", 60.0, 40.0));
        goods.add(new Good("Spear", 60.0, 40.0));
        goods.add(new Good("Pike", 60.0, 40.0));
    }

    private String name;
    private Double buy;
    private Double sell;

    private Double number;


    public Good(String name, Double buy, Double sell) {
        this.name = name;
        this.buy = buy;
        this.sell = sell;
        this.number = 0.0;
        goods.add(this);
    }

    public Double getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Double getBuy() {
        return buy;
    }

    public Double getSell() {
        return sell;
    }

    public String goodToText() {
        return (name + "; buy: " + buy + " Sell: " + sell + " number: " + number + ".\n");
    }

    public Good getGoodByName(String name) {
        if (goods.size() == 0) {
            return null;
        } else {
            for (Good good : goods) {
                if (good.getName().equals(name)) {
                    return good;
                }
            }
        }
        return null;
    }

}
