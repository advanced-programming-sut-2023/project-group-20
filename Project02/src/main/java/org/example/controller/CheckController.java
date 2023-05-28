package org.example.controller;

import org.example.model.User;

public abstract class CheckController {
    protected static boolean checkUsername(String username) {
        return username.matches("[A-Z,a-z,_,0-9]+");
    }

    protected static User logedInuser;

    protected static String checkPassword(String password) {
        if (password.length() < 6)
            return "Password length should be greater than 5.";
        if (!password.matches(".*[A-Z].*"))
            return "Password should have character from A to Z.";
        if (!password.matches(".*[a-z].*"))
            return "Password should have character from a to z.";
        if (!password.matches(".*[0-9].*"))
            return "Password should have character from 0 to 9.";
        if (!password.matches(".*[^a-z,^A-z,^0-9].*"))
            return "Password should have character other than (A-Z,a-z,0-9).";
        return "accepted";
    }

    protected static String checkEmail(String email) {
        if (!email.contains("@"))
            return "Email hasn't @";
        String[] emailSplitByAt = email.split("@");
        if (emailSplitByAt.length > 2)
            return "Email can't have more than one @";
        if (!emailSplitByAt[1].contains("."))
            return "Email hasn't . after At";
        for (String part : emailSplitByAt) {
            if (!part.matches("[A-Z,a-z,_,0-9,.]+"))
                return "Email format should be like this:<something>@<something>.<something>";
        }
        return "accepted";
    }

    protected static boolean isNull(Object object) {
        if (object == null)
            return true;
        else if (object.equals(""))
            return true;
        else if (object.equals(null))
            return true;
        else return false;
    }

    protected static String deleteQuotation(String string) {
        if (string == null)
            return null;
        if (string.startsWith("\"") && string.endsWith("\""))
            return string.substring(1, string.length() - 1);
        return string;
    }
}

