package org.example.controller;

import org.example.model.DataBase;
import org.example.model.GameInfo.Game;
import org.example.model.GameInfo.Government;
import org.example.model.GameInfo.Trade;
import org.example.model.User;
import org.example.view.TradeMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;


public class TradeController {
    public void start() {
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.setTradeController(this);
        tradeMenu.run();
    }

    private Government currentGovernment;
    private Game currentGame;

    public TradeController(Government currentGovernment, Game currentGame) {
        this.currentGovernment = currentGovernment;
        this.currentGame = currentGame;
    }

    public String showAllPlayers() {
        String txt = "";
        int i = 1;
        ArrayList<Government> allGovernments = currentGame.getGovernments();
        for (int j = 0; j < allGovernments.size(); j++) {
            txt += (i + ") " + allGovernments.get(i).getOwner().getNickname() + ".\n");
        }
        return txt;
    }

    public String trade(Matcher matcher) {
        String granery = "AppleMeatCheeseBread";
        String stockpile = "stonewoodironpitchwheatflour";
        String armoury = "LeatherArmourMaceSwordMetalArmourBowCrossbowSpearPike";
        ShopController shopController = new ShopController(currentGovernment);
        String type = matcher.group("type");
        Double amount = Double.parseDouble(matcher.group("amount"));
        Double price = Double.parseDouble(matcher.group("price"));
        String message = matcher.group("message");

        Trade trade = new Trade(currentGovernment, type, amount, price, message);

        return "Your request or donation has been created.";
    }

    public String tradeList() {
        String txt = "";
        for (Trade trade : Trade.trades) {
            txt += (trade.getId() + ") sender:" + trade.getReceiverOrSender() + "," + trade.getType() + " :" + trade.getAmount() + ", price: " + trade.getPrice() + "; message:" + trade.getMessage() + ".\n");
        }
        return txt;
    }

    public String tradeAccept(Matcher matcher) {
        int id = Integer.parseInt(matcher.group("id"));
        String message = matcher.group("message");
        Matcher matcher2;
        Government send;
        Government receive;
        Double firstCoin;
        if (Trade.getTradeById(id).getSender() != null) {
            receive = currentGovernment;
            send = Trade.getTradeById(id).getSender();
        } else {//Receiver!=null
            send = currentGovernment;
            receive = Trade.getTradeById(id).getReceiver();
        }

        ShopController shopController = new ShopController(send);
        firstCoin = send.getCoin();
        send.setCoin(firstCoin + 10000.0);
        if (!shopController.sell2(Trade.getTradeById(id).getType(), Trade.getTradeById(id).getAmount()).equals("The item was sold")) {
            send.setCoin(firstCoin);
            return "sender: not enough good!";
        }

        ShopController shopController2 = new ShopController(receive);
        firstCoin = receive.getCoin();
        receive.setCoin(firstCoin + 10000.0);
        if (!shopController.buy2(Trade.getTradeById(id).getType(), Trade.getTradeById(id).getAmount()).equals("The item was purchased")) {
            receive.setCoin(firstCoin);
            return "receiver: not enough space!";
        }
        send.setCoin(firstCoin + Trade.getTradeById(id).getPrice());
        receive.setCoin(firstCoin + Trade.getTradeById(id).getPrice());
        Trade.getTradeById(id).setSender(send);
        Trade.getTradeById(id).setReceiver(receive);
        Trade.getTradeById(id).setFlag(true);
        Trade.getTradeById(id).setMessage2(message);

        return "Trade done!";
    }

    public String tradeHistory() {
        String txt = "";
        for (int i = 0; i < Trade.trades.size(); i++) {
            if (!Trade.trades.get(i).isFlag()) {
                txt += (Trade.trades.get(i).getId() + ") sender:" + Trade.trades.get(i).getReceiverOrSender() + "," + Trade.trades.get(i).getType() + " :" + Trade.trades.get(i).getAmount() + ", price: " + Trade.trades.get(i).getPrice() + "; message:" + Trade.trades.get(i).getMessage() + ".\n");
                Trade.trades.get(i).setFlag(true);
            }
        }
        return txt;
    }

    public void back() {
        //TODO
//         GameController.start();
    }


}
