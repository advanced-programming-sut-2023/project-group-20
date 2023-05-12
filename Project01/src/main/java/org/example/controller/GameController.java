package org.example.controller;

import org.example.model.GameInfo.Game;
import org.example.model.User;
import org.example.view.GameMenu;

import java.util.regex.Matcher;

public class GameController {
    private static User currentUser;
    private static Game currentGame;


    public GameController(Game game) {
        setCurrentGame(game);
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public static void setCurrentGame(Game currentGame) {
        GameController.currentGame = currentGame;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    public void start() {
        GameMenu gameMenu = new GameMenu();
        gameMenu.setGameController(this);
        gameMenu.run();
    }
    public static void showMap(Matcher matcher){
//        MapController.start(matcher);TODO::::
    }
    public static String showPopularity(){return null;}
    public static String showFoodList(){return null;}
    public static String showPopularityFactors(){return null;}
    public static String showFoodRate(){return null;}
    public static String setTaxRate(Matcher matcher){return null;}
    public static String showTaxRate(){return null;}
    public static String setFoodRate(Matcher matcher){return null;}
    public static String selectBuilding(Matcher matcher){return null;}
    public static String createUnit(Matcher matcher){return null;}
    public static String repair(){return null;}
    public static String selectUnit(Matcher matcher){return null;}
    public static String patrolUnit(Matcher matcher){return null;}
    public static String moveUnit(Matcher matcher){return null;}
    public static String setTroopsState(Matcher matcher){return null;}
    public static String attackToEnemy(Matcher matcher){return null;}
    public static String attack(Matcher matcher){return null;}
    public static String pourOil(Matcher matcher){return null;}
    public static String digTunnel(Matcher matcher){return null;}
    public static String buildBuilding(Matcher matcher){return null;}
    public static String disbandUnit(){return null;};
    public static String setTexture(Matcher matcher){return null;}
    public static String clear(Matcher matcher){return null;}
    public static String droprock(Matcher matcher){return null;}
    public static String prockRandomDirection(){return null;}
    public static String dropTree(Matcher matcher){return null;}
    public static boolean checkTreeName(String name){return true;}
    public static String dropBuilding(Matcher matcher){return null;}
    public static String dropUnit(Matcher matcher){return null;}
    private static boolean checkFloorType(String typeOfHome,String targetName){return true;}
    private static boolean isMovable(String typeOfFloor){return true;}
    public static String openTrade(Matcher matcher){return null;}
    public static String openShop(){return null;}
    public static void changeTurn(){}
    public static void applyChanges(){}
    public static String checkFloorType(String type){return null;}
    public static String checkWaterAreaType(String type){return null;}
    public static String checkTreeType(String type){return null;}

}
