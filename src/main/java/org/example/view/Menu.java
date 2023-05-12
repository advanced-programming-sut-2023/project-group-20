package org.example.view;

import org.example.model.User;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    protected static User logedInUser;
    private static final Scanner scanner = new Scanner(System.in);

    protected static Matcher isMatched(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    protected static Scanner getScanner() {
        return scanner;
    }

    public static void setLogedInUser(User user) {
        logedInUser = user;
    }

}
