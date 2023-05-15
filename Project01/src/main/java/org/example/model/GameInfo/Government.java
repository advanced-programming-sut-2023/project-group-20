package org.example.model.GameInfo;

import org.example.model.User;

import java.util.ArrayList;

public class Government {
    //TODO
    // Clear it never used
    private Integer unemployed = 10;
    private User owner;
    private Integer popularity = 100;
    private Integer foodRate = -2;
    private double coin = 100000;
    private Integer taxRate = 0;
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Armoury> armouries = new ArrayList<>();
    private ArrayList<StockPile> stockPiles = new ArrayList<>();
    private ArrayList<Granery> graneries = new ArrayList<>();
    private Integer fearRate = 0;
    private Integer usedStone;
    private Integer usedWood;
    private Integer usedIron;

    public Government(User owner) {
        this.owner = owner;
    }


    private Integer population = 10;

    public ArrayList<Granery> getGraneries() {
        return graneries;
    }

    public void addGranery(Granery granery) {
        graneries.add(granery);
    }

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

//    public String getName() {
//        return name;
//    }

    public Integer getTaxRate() {
        return taxRate;
    }

    public Integer getFearRate() {
        return fearRate;
    }

    public double getCoin() {
        return coin;
    }

    public ArrayList<Armoury> getArmouries() {
        return armouries;
    }

    public void addArmoury(Armoury armoury) {
        this.armouries.add(armoury);
    }

    public void setFearRate(Integer fearRate) {
        this.fearRate = fearRate;
    }

    public void setFoodRate(Integer rate) {
        this.foodRate = rate;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public void addTroop(Troop troop) {
        this.troops.add(troop);
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public ArrayList<StockPile> getStockPiles() {
        return stockPiles;
    }

    public void addStockPile(StockPile stockPile) {
        this.stockPiles.add(stockPile);
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getUnemployed() {
        return unemployed;
    }

    public void setUnemployed(Integer unemployed) {
        this.unemployed = unemployed;
    }

    public Integer getUsedStone() {
        return usedStone;
    }

    public Integer getUsedWood() {
        return usedWood;
    }

    public Integer getUsedIron() {
        return usedIron;
    }

    public void setUsedStone(Integer usedStone) {
        this.usedStone = usedStone;
    }

    public void setUsedWood(Integer usedWood) {
        this.usedWood = usedWood;
    }

    public void setUsedIron(Integer usedIron) {
        this.usedIron = usedIron;
    }
}