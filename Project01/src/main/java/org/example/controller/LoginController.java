package org.example.controller;

import org.example.view.LoginMenu;
import org.example.view.Menu;

import java.util.regex.Matcher;

public class LoginController extends CheckController {
    public static void start(){
        LoginMenu loginMenu=new LoginMenu();
        loginMenu.run();
    }
    public static void startGame(Matcher matcher){}
    public static String logout(){
        Menu.setLogedInUser(null);
        logedInuser=null;
        return "user logged out successfully!";
    }
}
