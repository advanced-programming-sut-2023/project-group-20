package model.GameInfo;

import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private Integer xBorder;
    private Integer yBorder;
    private Integer unemployed;
    private User owner;
    private String name = owner.getUsername();
    private Integer popularity = 100;
    private Integer foodRate = -2;
    private double coin = 0;
    private Integer taxRate = 0;
    private ArrayList troops = new ArrayList<Troop>();
    private ArrayList armouries = new ArrayList<Armoury>();
    private ArrayList stockPiles = new ArrayList<StockPile>();
    private ArrayList graneries = new ArrayList<Granery>();
    private Integer fearRate = 0;

    public Government(Integer xBorder, Integer yBorder, User owner) {
        this.xBorder = xBorder;
        this.yBorder = yBorder;
        this.owner = owner;
    }

    private Integer population = 0;

    public ArrayList getGraneries() {
        return graneries;
    }

    public void addGranery(Granery granery) {
        graneries.add(granery);
    }
    //    private HashMap<String,Integer> food=new HashMap();
//    {
//        food.put("Apple",0);
//        food.put("Meat",0);
//        food.put("Cheese",0);
//        food.put("Bread",0);
//    }

    public Integer getPopularity() {
        return popularity;
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

    public void setCoin(double coin) {
        this.coin = coin;
    }

    public String getName() {
        return name;
    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public Integer getFearRate() {
        return fearRate;
    }

    public double getCoin() {
        return coin;
    }

    public ArrayList getArmouries() {
        return armouries;
    }

    public void addArmoury(Armoury armoury) {
        this.armouries.add(armoury);
    }

    public void setFearRate(Integer fearRate) {
        this.fearRate = fearRate;
    }

    public void setFoodRate(Integer rate) {
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void addTroop(Troop troop) {
        this.troops.add(troop);
    }

    public ArrayList getStockPiles() {
        return stockPiles;
    }

    public void addStockPile(StockPile stockPile) {
        this.stockPiles.add(stockPile);
    }
}