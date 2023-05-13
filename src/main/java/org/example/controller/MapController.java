package org.example.controller;

import org.example.model.DataBase;
import org.example.model.GameInfo.*;
import org.example.view.MapMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class MapController {
    private int xMapPos;
    private int yMapPos;
    private Map map;
    private Game currentGame;

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void start(Matcher matcher) {
        this.xMapPos = Integer.parseInt(matcher.group("x"));
        this.yMapPos = Integer.parseInt(matcher.group("y"));
        MapMenu mapMenu = new MapMenu();
        mapMenu.setMapController(this);
        mapMenu.run();
    }

    public MapController(Map map, Game currentGame) {
        this.map = map;
        setCurrentGame(currentGame);
    }

    public String showMap(int movementY, int movementX) {
        String output = new String();
        xMapPos += movementX;
        yMapPos += movementY;
        if (xMapPos <= 0 || yMapPos <= 0)
            return "Your position for x and y is not correct";
        for (int i = +1; i >= -1; i--) {
            for (int j = -1; j <= +1; j++) {
                output += printHome(getHomeByPosition(j + xMapPos, i + yMapPos));
                output += " ";
            }
            output += "\n";
        }
        return output;
    }

    private String printHome(Home home) {
//        if (home.getX() < 0 || home.getX() > map.getxSize()) {
//            return "*";
//        } else if (home.getY() < 0 || home.getY() > map.getySize()) {
//            return "*";
//        }
        if (home == null) {
            return "*";
        } else if (home.getTroops().size() != 0) {
            return "S";
        } else if (home.getBuilding() != null) {
            return "B";
        } else if (home.getTree() != null) {
            return "T";
        } else {
            return home.getTypeOfFloor();
        }
    }

    public String moveMap(Matcher matcher) {
        //TODO
        //  MUST BE DEFINDE BY MATCHER
        //  MUST BE DEFINDE BY MATCHER
        int movementY = 1;
        int movementX = 0;
        return showMap(movementY, movementX);
    }

    public String showHomeDetails(Matcher matcher) {
        String output;
        Integer x = Integer.parseInt(matcher.group("x"));
        Integer y = Integer.parseInt(matcher.group("y"));
        Home home = this.getHomeByPosition(x, y);
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
            output += "Building: " + home.getBuilding().getType() + "\n";
        }
        return output;
    }

    private String amountOfAllSameTroops(Home home) {
        HashMap hashMap = new HashMap<String, Integer>();
        String output = "";
        ArrayList troops = home.getTroops();
        ArrayList typeOfTroops = DataBase.getTypesOfTroops();


        for (int i = 0; i < typeOfTroops.size(); i++) {
            hashMap.put(typeOfTroops.get(i), 0);
        }

        for (int i = 0; i < troops.size(); i++) {
            for (int j = 0; j < typeOfTroops.size(); j++) {
                if (((Troop) troops.get(i)).getType().equals(typeOfTroops.get(j))) {
                    hashMap.put(typeOfTroops.get(j), ((Integer) hashMap.get(typeOfTroops.get(j))) + 1);
                }
            }
        }
        for (int i = 0; i < typeOfTroops.size(); i++) {
            if (((Integer) hashMap.get(typeOfTroops.get(i))) != 0) {
                output += typeOfTroops.get(i) + ": " + hashMap.get(typeOfTroops.get(i));
            }
        }
        return output;
    }

    public Home getHomeByPosition(Integer x, Integer y) {
        for (int i = 0; i < map.getHomes().size(); i++) {
            if (((Home) map.getHomes().get(i)).getX().equals(x) && ((Home) map.getHomes().get(i)).getY().equals(y)) {
                return ((Home) map.getHomes().get(i));
            }
        }
        return null;
    }

//    public static void exit() {
//        GameController.start();
//    }

    //TODO
    // Reset All Homes After Turn Changing
    public void resetAllHomes() {
        for (int i = 1; i < map.getxSize(); i++) {
            for (int j = 1; j < map.getySize(); j++) {
                resetHome(this.getHomeByPosition(i, j));
            }
        }
    }

    private void resetHome(Home home) {
        //TODO
    }

}
