package controller;

import view.MapMenu;
import java.util.regex.Matcher;

public class MapController {
    public static void start(Matcher matcher){
        showMap(matcher);
        MapMenu mapMenu=new MapMenu();
        mapMenu.run();
    }
    public static String showMap(Matcher matcher){}
    public static String moveMap(Matcher matcher){}
    public static String homeDetails(Matcher matcher){}
    public static void exit(){
        GameController.start();
    }
}
