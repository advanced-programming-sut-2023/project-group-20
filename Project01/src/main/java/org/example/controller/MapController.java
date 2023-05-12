package org.example.controller;

import org.example.model.GameInfo.Home;
import org.example.model.GameInfo.Map;
import org.example.model.GameInfo.Mine;
import org.example.view.MapMenu;

import java.util.regex.Matcher;

public class MapController {
    private int xMap;
    private int yMap;
    private Map map = GameController.getCurrentGame().getMap();

    public void start(Matcher matcher) {
        this.xMap = Integer.parseInt(matcher.group("x"));
        this.yMap = Integer.parseInt(matcher.group("y"));
        showMap(0, 0);   //  WE SHOULD PRINT THE STRING OF THIS FUNC
        MapMenu mapMenu = new MapMenu();
        mapMenu.run();
    }

    public String showMap(int movementY, int movementX) {

        Map map = GameController.getCurrentGame().getMap();
        String output = new String();
        xMap += movementX;
        yMap += movementY;
        if (xMap <= 0 || yMap <= 0)
            return "Your position for x and y is not correct";
        for (int i = +1; i >= -1; i--) {
            for (int j = -1; j <= +1; j++) {
                printHome(output, map.getHomeByPosition(j, i));
                output += " ";
            }
            output += "\n";
        }
        return output;
    }

    private void printHome(String output, Home home) {
        if (home.getX() < 0 || home.getX() > map.getxSize()) {
            output += "*";
            return;
        } else if (home.getY() < 0 || home.getY() > map.getySize()) {
            output += "*";
            return;
        } else if (home.getTroops().size() == 0) {
            output += "S";
            return;
        } else if (home.getBuilding() != null) {
            output += "B";
            return;
        } else if (home.getTree() != null) {
            output += "T";
            return;
        } else {
            output += home.getTypeOfFloor();
            return;
        }
    }

    public String moveMap(Matcher matcher) {
        int movementY = 0;    //  MUST BE DEFINDE BY MATCHER
        int movementX = 0;    //  MUST BE DEFINDE BY MATCHER
        showMap(movementY, movementX);
        return null;
    }

    public String showHomeDetails(Matcher matcher) {
        String output = new String();
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home home = this.map.getHomeByPosition(x, y);
        output = "Home Details:\n";
        output += "Floor Type: " + home.getTypeOfFloor() + "\n";
        if (home.getBuilding() != null && home.getBuilding() instanceof Mine) {
            output += "Mine Type: " + (home.getBuilding()).getType() + " --> Production Amount: " + ((Mine) home.getBuilding()).getProductionAmount() + "\n";
        }
        if (home.getTroops().size() != 0) {
            output += "Troops:\n";
            output += amountOfAllSameTroops(home);
        }
        if (home.getBuilding() != null) {
            output+="Building: "+home.getBuilding().getType()+"\n";
        }
        return output;
    }

    private String amountOfAllSameTroops(Home home) {
//        HashMap hashMap = new HashMap<String, Integer>();
//        String output = "";
//        ArrayList troops = home.getTroops();
//        ArrayList typeOfTroops = DataBase.getTypesOfTroops();
//
//        for (int i = 0; i < typeOfTroops.size(); i++) {
//            hashMap.put(typeOfTroops.get(i), 0);
//        }
//TODO::::
//        for (int i = 0; i < troops.size(); i++) {
//            for (int j = 0; j < typeOfTroops.size(); j++) {
//                if (((Troop) troops.get(i)).getType().equals(typeOfTroops.get(j))) {
//                    hashMap.put(typeOfTroops.get(j), ((Integer) hashMap.get(typeOfTroops.get(j))) + 1);
//                }
//            }
//        }
//        for (int i = 0; i < typeOfTroops.size(); i++) {
//            if (((Integer) hashMap.get(typeOfTroops.get(i))) != 0) {
//                output += typeOfTroops.get(i) + ": " + hashMap.get(typeOfTroops.get(i));
//            }
//        }
//        return output;
        return null;
    }

    public static String homeDetails(Matcher matcher) {
        return null;
    }

    public static void exit() {
//        GameController.start(); TODO::::
    }

    public static void resetAllHomes() {
        Map map = GameController.getCurrentGame().getMap();
        for (int i = 1; i < map.getxSize(); i++) {
            for (int j = 1; j < map.getySize(); j++) {
                resetHome(map.getHomeByPosition(i, j));
            }
        }
    }

    private static void resetHome(Home home) {
        home.setTroopsSelected(false);
    }


    private static boolean checkTheBorder(int x, int neighbour) {
        Map map = GameController.getCurrentGame().getMap();
        if (0 < x + neighbour && x + neighbour <= map.getySize())
            return true;
        return false;
    }
}
