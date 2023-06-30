package org.example.controller;

import org.example.view.LoginMenu;
import org.example.model.DataBase;

import java.util.regex.Matcher;

public class ProfileController extends CheckController {
    public static String changePassword(String newPassword) {
        if (newPassword == null)
            return "New password is empty";
        String passwordError = checkPassword(newPassword);
        if (!passwordError.equals("accepted"))
            return passwordError;
        logedInuser.setPassword(newPassword);
        return "Password changed";
    }

    public static String checkBeforePassword(String newPassword) {
        if (newPassword == null)
            return "New password is empty";
        String passwordError = checkPassword(newPassword);
        if (!passwordError.equals("accepted"))
            return passwordError;
        return "Password is ok!";
    }

    public static String changeNickName(String nickname) {
        if (nickname == null)
            return "New nickname is empty";
        logedInuser.setNickname(nickname);
        return "Nickname changed";
    }

    public static String checkBeforeUsername(String username) {
        if (isNull(username))
            return "New username is empty";
        if (!checkUsername(username))
            return "New username format is invalid";
        if (DataBase.getUserByName(username) != null)
            return "New username exist";
        return "Username is ok";
    }

    public static String changeUsername(String username) {
        if (isNull(username))
            return "New username is empty";
        if (!checkUsername(username))
            return "New username format is invalid";
        if (DataBase.getUserByName(username) != null)
            return "New username exist";
        logedInuser.setUsername(username);
        return "Username changed";
    }

    public static String checkBeforeEmail(String email) {
        if (email == null)
            return "New email is empty";
        String Error = checkEmail(email);
        if (!Error.equals("accepted"))
            return Error;
        if (DataBase.getEmails() != null || DataBase.getEmails().size() == 0)
            for (String email1 : DataBase.getEmails()) {
                if (email1.equalsIgnoreCase(email))
                    return "Email is exist";
            }
        return "Email is ok";
    }

    public static String changeEmail(String email) {
        if (email == null)
            return "New email is empty";
        String Error = checkEmail(email);
        if (!Error.equals("accepted"))
            return Error;
        if (DataBase.getEmails() != null || DataBase.getEmails().size() == 0)
            for (String email1 : DataBase.getEmails()) {
                if (email1.equalsIgnoreCase(email))
                    return "Email is exist";
            }
        if (logedInuser.getEmail() != null)
            DataBase.getEmails().remove(logedInuser.getEmail());
        DataBase.getEmails().add(email);
        logedInuser.setEmail(email);
        return "Email changed";
    }

    public static String changeSlogan(String slogan) {
        if (slogan == null)
            return "New slogan is empty";
        if (slogan.equals("random"))
            slogan = SignupController.makeRandomSlogan();
        logedInuser.setSlogan(slogan);
        return "Slogan changed";
    }

    public static String removeSlogan() {
        logedInuser.setSlogan(null);
        return "Slogan removed";
    }

    public static String showHighScore() {
        return logedInuser.getHighScore().toString();
    }

    public static String showRank() {
        return DataBase.rank(logedInuser).toString();
    }

    public static String showSlogan() {
        String slogan = logedInuser.getSlogan();
        if (logedInuser.getSlogan() == null)
            slogan = "slogan is empty!";
        else if (logedInuser.getSlogan().equals(""))
            slogan = "slogan is empty!";
        return slogan;
    }

    public static String showAll() {
        String slogan = logedInuser.getSlogan();
        if (logedInuser.getSlogan() == null)
            slogan = "slogan is empty!";
        else if (logedInuser.getSlogan().equals(""))
            slogan = "slogan is empty!";
        return "username: " + logedInuser.getUsername() +
                "\npassword: " + logedInuser.getPassword() +
                "\nnickname: " + logedInuser.getNickname() +
                "\nslogan: " + slogan +
                "\nemail: " + logedInuser.getEmail() +
                "\nhighscore: " + logedInuser.getHighScore();

    }

}
