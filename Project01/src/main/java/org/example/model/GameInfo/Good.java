package org.example.model.GameInfo;

import java.util.ArrayList;

public class Good {
    private static ArrayList<Good> goods = new ArrayList<>();
    static {
        Good Apple = new Good("Apple", 10.0, 5.0);
        Good Meat = new Good("Meat", 10.0, 5.0);
        Good Cheese = new Good("Cheese", 10.0, 5.0);
        Good Bread = new Good("Bread", 10.0, 5.0);
        Good stone = new Good ("stone", 30.0, 20.0);
        Good wood = new Good ("wood", 30.0, 20.0);
        Good iron = new Good ("iron", 30.0, 20.0);
        Good pitch = new Good ("pitch", 30.0, 20.0);
        Good wheat = new Good ("wheat", 30.0, 20.0);
        Good flour = new Good ("flour", 30.0, 20.0);
        Good LeatherArmour = new Good ("LeatherArmour", 60.0, 40.0);
        Good Mace = new Good ("Mace", 60.0, 40.0);
        Good Sword = new Good ("Sword", 60.0, 40.0);
        Good MetalArmour = new Good ("MetalArmour", 60.0, 40.0);
        Good Bow = new Good ("Bow", 60.0, 40.0);
        Good Crossbow = new Good ("Crossbow", 60.0, 40.0);
        Good Spear = new Good ("Spear", 60.0, 40.0);
        Good Pike = new Good ("Pike", 60.0, 40.0);
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
