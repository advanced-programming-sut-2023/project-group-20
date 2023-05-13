package org.example.controller;

import org.example.view.ShopMenu;
import java.util.regex.Matcher;

public class ShopController {
    public static void start(){
         ShopMenu shopMenu =new ShopMenu();
         shopMenu.run();
    }
    public static String showPriceList(){
        //TODO
        return "";
    }
    public static String buy(Matcher matcher){
        //TODO
        return "";
    }
    public static String sell(Matcher matcher){
        //TODO
        return "";
    }
    public static void closeShop(){
        //TODO
//        GameController.start();
    }
}
