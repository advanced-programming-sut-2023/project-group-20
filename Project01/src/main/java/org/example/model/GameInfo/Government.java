package org.example.model.GameInfo;

import org.example.model.User;

public class Government {
    private Integer xBorder;
    private Integer yBorder;
    private Integer unemployed;
    private User owner;
    private Integer popularity=0;
    private Integer foodRate=-2;
    private Integer coin=0;
    private Integer taxRate=0;
    private Integer fearRate=0;

    public Government(User owner) {
        this.owner = owner;
    }

    private Integer population=0;
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

    private void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

}