package org.example.controller;

import org.example.view.LoginMenu;
import org.example.model.DataBase;
import java.util.regex.Matcher;

public class ProfileController extends CheckController{
    public static String changePassword(Matcher matcher){
        String newPassword=matcher.group("newpassword");
        String oldPassword=matcher.group("oldpassword");
        if (newPassword==null)
            return "New password is empty";
        if (oldPassword==null)
            return "Old password is empty";
        if (!oldPassword.equals(logedInuser.getPassword()))
            return "Current password is incorrect!";
        if (oldPassword.equals(newPassword))
            return "Please enter a new password!";
        if (newPassword.equals("random"))
            newPassword=SignupController.makeRandomPassword();
        String passwordError=checkPassword(newPassword);
        if (!passwordError.equals("accepted"))
            return passwordError;
        String confirm= LoginMenu.changePassword();
        while (!confirm.equals(newPassword)){
            if (confirm.equals("regret"))
                return "Changing password failed";
            confirm=LoginMenu.changePassword();}
        logedInuser.setPassword(newPassword);
        return "Password changed";
    }
    public static String changeNickName(Matcher matcher){
        String nickname=matcher.group("nickname");
        if (nickname==null)
            return "New nickname is empty";
        logedInuser.setNickname(nickname);
        return "Nickname changed";
    }
    public static String changeUsername(Matcher matcher){
        String username=matcher.group("username");
        if (isNull(username))
            return "New username is empty";
        if (!checkUsername(username))
            return "New username format is invalid";
        if (DataBase.getUserByName(username)!=null)
            return "New username exist";
        logedInuser.setUsername(username);
        return "Username changed";
    }
    public static String changeEmail(Matcher matcher){
        String email=matcher.group("email");
        if (email==null)
            return "New email is empty";
        String Error=checkPassword(email);
        if (!Error.equals("accepted"))
            return Error;
        logedInuser.setEmail(email);
        return "Email changed";
    }
    public static String changeSlogan(Matcher matcher){
        String slogan=matcher.group("slogan");
        if (slogan==null)
            return "New password is empty";
        if (slogan.equals("random"))
            slogan=SignupController.makeRandomSlogan();
        logedInuser.setSlogan(slogan);
        return "Slogan changed";
    }
    public static String removeSlogan(){
        logedInuser.setSlogan(null);
        return "Slogan removed";
    }
    public static String showHighScore(){
        return logedInuser.getHighScore().toString();
    }
    public static String showRank(){
        return DataBase.rank(logedInuser).toString();
    }
    public static String showSlogan(){
        return logedInuser.getSlogan();
    }
    public static String showAll(){
        return "Your username:"+logedInuser.getUsername()+
                "\nyour password:"+logedInuser.getPassword()+
                "\nyour nickname:" +logedInuser.getNickname()+
                "\nyour slogan:"+ logedInuser.getSlogan()+
                "\nyour email:"+ logedInuser.getEmail()+
                "\nyour securityQuestion:"+logedInuser.getSecurityQuestion()+
                " and your answer:"+logedInuser.getSecurityQuestionAnswer()+
                "\nyour highscore"+logedInuser.getHighScore();

    }

}
