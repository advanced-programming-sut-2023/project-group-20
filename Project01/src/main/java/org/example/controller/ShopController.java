package org.example.controller;

import org.example.view.ShopMenu;
import java.util.regex.Matcher;

public class ShopController {
    public static void start(){
         ShopMenu shopMenu =new ShopMenu();
         shopMenu.run();
    }
    public static String showPriceList(){return null;}
    public static String buy(Matcher matcher){return null;}
    public static String sell(Matcher matcher){return null;}
    public static void closeShop(){
//        GameController.start();TODO::::
    }
}
