package model.GameInfo;

import model.User;

import java.util.HashMap;

public class Government {
    private Integer stone;
    private Integer unemployed;
    private User owner;
    private Integer popularity=0;
    private Integer foodRate=-2;
    private Integer coin=0;
    private Integer taxRate=0;
    private Integer fearRate=0;
    private HashMap<String,Integer> food=new HashMap();
    {
        food.put("Apple",0);
        food.put("Meat",0);
        food.put("Cheese",0);
        food.put("Bread",0);
    }
    public Integer getPopularity() {
        return popularity;
    }

    public HashMap<String, Integer> getHashMap() {
        return food;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public Integer getFoodRate() {
        return foodRate;
    }

    public void setTaxRate(Integer taxRate) {
        this.taxRate = taxRate;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public Integer getFearRate() {
        return fearRate;
    }

    public void setFearRate(Integer fearRate) {
        this.fearRate = fearRate;
    }

    public void setFoodRate(Integer rate){}

    public void setStone(Integer stone) {
        this.stone = stone;
    }

    public Integer getStone() {
        return stone;
    }

    private void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

}