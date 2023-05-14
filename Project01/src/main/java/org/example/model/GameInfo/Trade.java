package org.example.model.GameInfo;

import org.example.model.User;

public class Trade {
    private User receiver;
    private User sender;
    private String message;
    private Troop troop;
    private Integer coin;
    private Integer stone;
    private Integer amount;

    public Trade(User receiver, User sender) {
        this.receiver = receiver;
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTroop(Troop troop) {
        this.troop = troop;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public void setStone(Integer stone) {
        this.stone = stone;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getMessage() {
        return message;
    }

    public Troop getTroop() {
        return troop;
    }

    public Integer getCoin() {
        return coin;
    }

    public Integer getStone() {
        return stone;
    }

    public Integer getAmount() {
        return amount;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }
}
