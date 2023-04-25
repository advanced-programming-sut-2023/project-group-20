package controller;

import view.TradeMenu;

import java.util.regex.Matcher;

public class TradeController {
    public static void start(){
        TradeMenu tradeMenu=new TradeMenu();
        tradeMenu.run();
    }
    public static String showAllPlayers(){}
    public static String trade(Matcher matcher){}
    private static Integer setId(){}
    public static String tradeList(){}
    public static String tradeAccept(Matcher matcher){}
    public static String tradeHistory(){}
    public static void back(){
        GameController.start();
    }


}
