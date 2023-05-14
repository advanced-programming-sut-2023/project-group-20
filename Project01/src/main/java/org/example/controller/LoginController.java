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

    public static String startGame(Matcher matcher, User owner) {
        if (!matcher.group("num").matches("\\d+"))
            return "Please enter a number for player number (-n \\d)\n";
        if (!matcher.group("turn").matches("\\d+"))
            return "Please enter a number for number of turns (-t \\d)\n";
        if (!matcher.group("x").matches("\\d+"))
            return "Please enter a number for xmap (-t \\d)\n";
        if (!matcher.group("y").matches("\\d+"))
            return "Please enter a number for ymap (-t \\d)\n";
        String massage = "Enter a new player username";
        Integer playerNumber = Integer.parseInt(matcher.group("num"));
        Map map = new Map(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")), playerNumber);
        Game game = new Game(Integer.parseInt(matcher.group("turn")), map, playerNumber);
        Government government = new Government(owner);
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
