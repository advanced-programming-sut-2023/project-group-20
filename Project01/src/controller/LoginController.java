package controller;

import view.LoginMenu;

import java.util.regex.Matcher;

public class LoginController extends CheckController {
    public static void start(){
        LoginMenu loginMenu=new LoginMenu();
        loginMenu.run();
    }
    public static void startGame(Matcher matcher){}
    public static String logout(){}
}
