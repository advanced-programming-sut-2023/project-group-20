package controller;

import view.ShopMenu;

import java.util.regex.Matcher;

public class ShopController {
    public static void start(){
         ShopMenu shopMenu =new ShopMenu();
         shopMenu.run();
    }
    public static String showPriceList(){}
    public static String buy(Matcher matcher){}
    public static String sell(Matcher matcher){}
    public static void closeShop(){}
}
