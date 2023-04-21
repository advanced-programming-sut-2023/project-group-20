package controller;

import model.Game.Game;
import model.Game.Home;
import model.User;
import view.GameMenu;

import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {
    private static User currentUser;
    private static Game currentGame;
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public static void setCurrentGame(Game currentGame) {
        GameController.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public static void start(){
        GameMenu gameMenu=new GameMenu();
        gameMenu.run();
    }
    public static void showMap(Matcher matcher){
        MapController.start(matcher);
    }
    public static String showPopularity(){}
    public static String showFoodList(){}
    public static String showFoodRate(){}
    public static void setTaxRate(Matcher matcher){}
    public static String showTaxRate(){}
    public static String setFoodRate(Matcher matcher){}
    public static String dropBuilding(Matcher matcher){}

    public static String selectBuilding(Matcher matcher){}
    public static String createUnit(Matcher matcher){}
    public static String repair(){}
    public static String selectUnit(Matcher matcher){}
    public static String patrolUnit(Matcher matcher){}
    public static String moveUnit(Matcher matcher){}
    public static String setTroopsState(Matcher matcher){}
    public static void attackToEnemy(Matcher matcher){}
    public static void pourOil(Matcher matcher){}
    public static String digTunnel(Matcher matcher){}
    public static String buildBuilding(Matcher matcher){}
    public static String disbandUnit(){};
    public static void setTexture(Matcher matcher){}
    public static String clear(Matcher matcher){}
    public static String droprock(Matcher matcher){}
    public static String prockRandomDirection(){}
    public static String dropTree(Matcher matcher){}
    public static boolean checkTreeName(String name){}
    public static String dropBuilding(Matcher matcher){}
    public static String dropUnit(Matcher matcher){}
    private static boolean checkFloorType(String typeOfHome,String targetName){}
    private static boolean isMovable(String typeOfFloor){}
    public static String openTrade(Matcher matcher){}
    public static String openShop(){}
    public static void changeTurn(){}
    public static void applyChanges(){}
    public static String checkFloorType(String type){}
    public static String checkWaterAreaType(String type){}
    public static String checkTreeType(String type){}
}
