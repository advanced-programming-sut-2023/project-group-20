package org.example.controller;

import org.example.view.TradeMenu;

import java.util.regex.Matcher;

public class TradeController {
    public static void start(){
        TradeMenu tradeMenu=new TradeMenu();
        tradeMenu.run();
    }
    public static String showAllPlayers(){return null;}
    public static String trade(Matcher matcher){return null;}
    private static Integer setId(){return null;}
    public static String tradeList(){return null;}
    public static String tradeAccept(Matcher matcher){return null;}
    public static String tradeHistory(){return null;}
    public static void back(){
//        GameController.start(); TODO::::
    }


}
