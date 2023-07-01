package org.example.controller;

import javafx.scene.paint.Color;
import org.example.model.User;
import org.example.view.Menu;
import org.example.view.SignupMenu;
import org.example.model.DataBase;

import java.util.Random;
import java.util.regex.Matcher;

public class SignupController extends CheckController {
    private static User stayLoggedIn = null;
    private static Integer invalidLogin = 0;
    private static String invalidUsername = "";
    private static User creatingUser;

    public static String createUser(String username, String password, String nickname, String email,String slogan,String confirm) {
        if (isNull(username))
            return "Username is empty";
        if (!SignupController.usernameCheck(username).equals("username is OK!"))
            return SignupController.usernameCheck(username);
        if (!SignupController.passCheck(password).equals("pass is OK!"))
            return SignupController.passCheck(password);
        if (!password.equals(confirm))
            return "password confirm isn't correct!";
        if (!checkMail(email).equals("email performed"))
            return checkMail(email);
        if (nickname.equals("")) {
            return ("Please enter a nickname!");
        }
        if (isNull(password))
            return "Password is empty";
        if (isNull(nickname))
            return "Nickname is empty";
        if (isNull(email))
            return "Email is empty";
        String emailError = checkMail(email);
        if (! emailError.equals("email performed"))
            return emailError;
        User user = new User(username, password, nickname, email);
        if (!slogan.equals(""))
            user.setSlogan(slogan);
        creatingUser = user;
        return "Good";
    }

    public static String checkMail(String string) {
        for (String email : DataBase.getEmails()) {
            if (email.equalsIgnoreCase(string))
                return "Email is exist";
        }
        String emailError = checkEmail(string);
        if (!emailError.equals("accepted"))
            return emailError;
        return "email performed";
    }

    public static String login(String username, String password) {
        if (isNull(username))
            return "Username is empty";
        if (DataBase.getUserByName(username) == null)
            return "User doesn't exist";
        if (password == null)
            if (stayLoggedIn != null && !stayLoggedIn.equals(DataBase.getUserByName(username)))
                return "You aren't stayed logged in";
            else if (!stayLoggedIn.equals(DataBase.getUserByName(username))) return "please enter password";
            else {
                invalidLogin = 0;
                invalidUsername = "";
                Menu.setLogedInUser(DataBase.getUserByName(username));
                logedInuser = DataBase.getUserByName(username);
                return "user logged in successfully!";
            }
        if (password != null && !DataBase.getUserByName(username).getPassword().equals(password)) {
            if (username.equals(invalidUsername)) {
                skipTime();
                invalidLogin++;
                System.out.println(invalidLogin);
            } else {
                invalidLogin = 1;
                invalidUsername = username;
            }
            return "Username and password didn't match!";
        }
        if (!password.equals(DataBase.getUserByName(username).getPassword()))
            return "password is incorrect";
        invalidLogin = 0;
        invalidUsername = "";
        Menu.setLogedInUser(DataBase.getUserByName(username));
        logedInuser = DataBase.getUserByName(username);
        return "user logged in successfully!";
    }

    public static String makeRandomSlogan() {
        Random random = new Random();
        int randomInt = random.nextInt(5);
        return DataBase.getSlogans().get(randomInt);
    }

    public static String makeRandomPassword() {
        Random random = new Random();
        double randomNum = random.nextInt(2, 10) * random.nextInt(1, 10) * 1.23;
        String randomPass = "Is" + randomNum + "l@m";
        return randomPass;
    }

    private static void skipTime() {
        try {
            Thread.sleep(invalidLogin * 5000);
        } catch (InterruptedException e) {
            System.out.println("time not skipped");
        }
    }

    public static String passCheck(String password) {
        String passwordError = checkPassword(password);
        if (!passwordError.equals("accepted"))
            return passwordError + ") pass is weak!";
        else return "pass is OK!";
    }

    public static String usernameCheck(String username) {
        if (!checkUsername(username))
            return "Username format is invalid";
        if (DataBase.getUserByName(username) != null) {
            while (true) {
                if (DataBase.getUserByName(username + "_") != null)
                    username = username + ("_");
                else {
                    username = username + ("_");
                    break;
                }
            }
            return "Username is exit BUT you can choose this Username " + username;
        }
        return "username is OK!";
    }

    public static User getCreatingUser() {
        return creatingUser;
    }
}
