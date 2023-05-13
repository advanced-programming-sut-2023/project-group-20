package org.example.controller;

import org.example.model.User;
import org.example.view.Menu;
import org.example.view.SignupMenu;
import org.example.model.DataBase;
import java.util.Random;
import java.util.regex.Matcher;

public class SignupController extends CheckController {
    private static boolean stayLoggedIn = false;
    private static Integer invalidLogin = 0;
    private static String invalidUsername = "";

    public static void start() {
        SignupMenu signupMenu = new SignupMenu();
        signupMenu.run();
//        try {
//            signupMenu.run();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }

    public static String createUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String slogan = matcher.group("slogan");
        if (isNull(username))
            return "Username is empty";
        if (isNull(password))
            return "Password is empty";
        if (isNull(matcher.group("nickname")))
            return "Nickname is empty";
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
            return "Username is exit BUT you can choose this Username" + username;
        }
        if (matcher.group("checkSlogan") != null && slogan == null)
            return "You doesn't entered any slogan after -s";
        if (slogan.equals("random"))
            slogan = makeRandomSlogan();
        if (password.equals("random"))
            password = makeRandomPassword();
        String passwordError = checkPassword(password);
        if (!passwordError.equals("accepted"))
            return passwordError;
        if (!password.equals("random") && !password.equals(matcher.group("confirmPassword")))
            return "Password confirm isn't correct";
        User user = new User(username, password, matcher.group("nickname"));
        if (matcher.group("email") != null) {
            String userEmail = matcher.group("email");
            for (String email : DataBase.getEmails()) {
                if (email.equalsIgnoreCase(userEmail))
                    return "Email is exist";
            }
            String emailError = checkEmail(userEmail);
            if (!emailError.equals("accepted"))
                return emailError;
            user.setEmail(userEmail);
        }
        Matcher matcher1 = SignupMenu.pickSecurityQuestion("");
        String number;
        while (true) {
            if (matcher1 == null)
                return "Creating user regretted";
            else
                number = matcher1.group("number");
            if (!(number.equals("1") || number.equals("2") || number.equals("3")))
                matcher1 = SignupMenu.pickSecurityQuestion("Invalid number for Pick\n");
            else if (matcher1.group("answer").equals("answerConfirm"))
                break;
            else matcher1 = SignupMenu.pickSecurityQuestion("Answer confirm is wrong\n");
        }
        if (slogan != null)
            user.setSlogan(slogan);
        user.setSecurityQuestion(number);
        user.setSecurityQuestionAnswer(matcher1.group("answer"));
        DataBase.addUserToDataBase(user);
        return "User created";
    }
//
//    private static void setQuestionAndAnswer(Integer questionNumber, String answer) {
//    }

    public static String login(Matcher matcher) {
        String username = matcher.group("username");
        if (username==null&&matcher.group("password")==null)
            if (!stayLoggedIn)
                return "You aren't stayed logged in";
        else if (DataBase.getUserByName(username) == null)
            return "User doesn't exist";
        else if (matcher.group("password") != null && !DataBase.getUserByName(username).getPassword().equals(matcher.group("password"))) {
            if (username.equals(invalidUsername)) {
                skipTime();
                invalidLogin++;
            } else {
                invalidLogin = 1;
                invalidUsername = username;
            }
            return "Username and password didn't match!";
        }
        stayLoggedIn = matcher.group("staylogedin") != null;
        invalidLogin = 0;
        invalidUsername = "";
        Menu.setLogedInUser(DataBase.getUserByName(username));
        logedInuser = DataBase.getUserByName(username);
        return "user logged in successfully!";
    }

    public static String makeRandomSlogan() {
        Random random = new Random();
        int randomInt = random.nextInt(4);
        String slogan = DataBase.getSlogans().get(randomInt);
        SignupMenu.showSlogan(slogan);
        return slogan;
    }

    public static String forgetPassword(Matcher matcher) {
        if (matcher.group("username") == null)
            return "Please enter a available username";
        User user = DataBase.getUserByName(matcher.group("username"));
        while (true) {
            if (SignupMenu.enterAnswerForSet(user.getSecurityQuestion()).equals(user.getSecurityQuestionAnswer()))
                break;
        }
        String passwordError = "";
        String password;
        do {
            password = SignupMenu.enterNewPassword(passwordError);
            if (password.equals("random"))
                password = makeRandomPassword();
            passwordError = checkPassword(password);
        } while (!passwordError.equals("accepted"));
        logedInuser.setPassword(password);
        if (invalidUsername.equals(matcher.group("username"))) {
            invalidUsername = "";
            invalidLogin = 0;
        }
        return "Forgot password:password changed";
    }

    public static String makeRandomPassword() {
        Random random = new Random();
        double randomNum = random.nextInt(100) * 1.01;
        String randomPass = "Is" + randomNum + "l@m";
        SignupMenu.reEnterPassword(randomPass);
        return randomPass;
    }

    private static void skipTime() {
        try {
            Thread.sleep(invalidLogin * 1000);
        } catch (InterruptedException e) {
            System.out.println("time not skipped");
        }
    }
}
