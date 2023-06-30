package org.example.controller;

import org.example.model.DataBase;
import org.example.model.GameInfo.Game;
import org.example.model.GameInfo.Government;
import org.example.model.GameInfo.Map;
import org.example.model.User;
import org.example.view.LoginMenu;
import org.example.view.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LoginController extends CheckController {
    public static User getLogedInuser() {
        return logedInuser;
    }

    public static void winner(Game game) {
        User user = game.getGovernments().get(0).getOwner();
        for (int i = 0; i < game.getGovernments().size(); i++) {
            if (game.getGovernments().get(i).getOwner().getHighScore() > user.getHighScore())
                user = game.getGovernments().get(i).getOwner();
        }
        LoginMenu.winnerPrint("Lord " + user.getNickname() + " wins.");
    }
public static GameController gameController;

    public static String startGame(int turns,int x,int y, User owner,String string) {
        String[] temp=string.split("\n");
        ArrayList<String>users=new ArrayList<>();
        for (int i = 0; i < temp.length; i++) {
            users.add(temp[i]);
        }
        for (int i = 0; i < users.size(); i++) {
            if (DataBase.getUserByName(users.get(i))==null)
                return users.get(i)+" is not available";
            if (users.get(i).equals(owner.getUsername()))
                users.remove(i);
        }

        int playerNumber=users.size()+1;
        Map map = new Map(x,y, playerNumber);
        Game game = new Game(turns, map, playerNumber);
        Government government = new Government(owner);
        owner.setHighScore(10);
        game.addGovernment(government);
        String username;
        for (int i = 0; i < users.size(); i++) {
            username = users.get(i);
            {
                Government newgovernment = new Government(DataBase.getUserByName(username));
                game.addGovernment(newgovernment);
                DataBase.getUserByName(username).setHighScore(10);
            }
        }
        GameController gameController1 = new GameController(game, game.getGovernments().get(0));
        gameController=gameController1;
        return "";
    }

    public static String logout() {
        Menu.setLogedInUser(null);
        logedInuser = null;
        return "user logged out successfully!";
    }
}
