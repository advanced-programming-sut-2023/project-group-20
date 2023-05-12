package controller;

import model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CheckController {
    public static boolean checkUsername(String username){return username.matches("[A-Z,a-z,_,0-9]+");}
    public static User logedInuser;
    public static String checkPassword(String password){
        if (password.length() < 6)
            return "Length should be greater than 5\n";
        if (!password.matches(".*[A-Z].*"))
            return "Password should have character from A to Z\n";
        if (!password.matches(".*[a-z].*"))
            return "Password should have character from a to z\n";
        if (!password.matches(".*[0-9].*"))
            return "Password should have character from 0 to 9\n";
        if (!password.matches(".*[^a-z,^A-z,^0-9].*"))
            return "Password should have character other than (A-Z,a-z,0-9)\n";
        return "accepted";
    }
    public static String checkEmail(String email){
        if (!email.contains("@"))
            return "Email hasn't @";
        String [] emailSplitByAt=email.split("@");
        if (emailSplitByAt.length>2)
            return "Email can't have more than one @";
        if (!emailSplitByAt[1].contains("."))
            return "Email hasn't . after At";
        for (String part:emailSplitByAt) {
            if (!part.matches("[A-Z,a-z,_,0-9,.]+"))
                return "Email format should be like this:<something>@<something>.<something>";
        }
        return "accepted";
    }
    public static boolean checkTheNumberInputTruth(String mustBeChecked){
        Pattern pattern=Pattern.compile("-{0,1}//d+");
        Matcher matcher=pattern.matcher(mustBeChecked);
        return matcher.find();
    }
}

