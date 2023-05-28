package org.example.controller;

import org.example.model.DataBase;
import org.example.model.GameInfo.Game;
import org.example.model.GameInfo.Government;
import org.example.model.GameInfo.Map;
import org.example.model.User;
import org.example.view.LoginMenu;
import org.example.view.Menu;

import java.util.regex.Matcher;

public class LoginController extends CheckController {
    public static void start() {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }

    public static void winner(Game game) {
        User user = game.getGovernments().get(0).getOwner();
        for (int i = 0; i < game.getGovernments().size(); i++) {
            if (game.getGovernments().get(i).getOwner().getHighScore() > user.getHighScore())
                user = game.getGovernments().get(i).getOwner();
        }
        LoginMenu.winnerPrint("Lord " + user.getNickname() + " wins.");
    }


    public static String startGame(Matcher matcher, User owner) {
        if (!matcher.group("num").matches("\\d+"))
            return "Please enter a number for player number (\\d)\n";
        if (!matcher.group("turn").matches("\\d+"))
            return "Please enter a number for number of turns (\\d)\n";
        if (!matcher.group("x").matches("\\d+"))
            return "Please enter a number for xmap (\\d)\n";
        if (!matcher.group("y").matches("\\d+"))
            return "Please enter a number for ymap (\\d)\n";
        String massage = "Enter a new player username";
        Integer playerNumber = Integer.parseInt(matcher.group("num"));
        Map map = new Map(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")), playerNumber);
        Game game = new Game(Integer.parseInt(matcher.group("turn")), map, playerNumber);
        Government government = new Government(owner);
        owner.setHighScore(10);
        game.addGovernment(government);
        String username;
        for (int i = 0; i < playerNumber - 1; i++) {
            username = LoginMenu.getPlayers(massage);
            if (DataBase.getUserByName(username) == null) {
                massage = "user not found";
                i--;
            } else {
                massage = "Enter a new player username";
                Government newgovernment = new Government(DataBase.getUserByName(username));
                game.addGovernment(newgovernment);
                DataBase.getUserByName(username).setHighScore(10);
            }
        }
        GameController gameController = new GameController(game, game.getGovernments().get(0));
        gameController.start();
        return "";
    }

    public static String logout() {
        Menu.setLogedInUser(null);
        logedInuser = null;
        return "user logged out successfully!";
    }
}
