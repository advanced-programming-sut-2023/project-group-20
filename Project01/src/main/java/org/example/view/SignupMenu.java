package org.example.view;

import org.example.controller.LoginController;
import org.example.controller.SignupController;

import java.util.regex.Matcher;

public class SignupMenu extends Menu {
    public void run() {
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^user create(((?: -u (?<username>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\"))))|(?: -p (?<password>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\"))) (?<confirmPassword>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\"))))|(?<checkSlogan> -s (?<slogan>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -n (?<nickname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -email (?<email>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,5}$")) != null)
                System.out.println(SignupController.createUser(matcher));
            else if ((matcher = isMatched(command, "^user create(((?: -u (?<username>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -n (?<nickname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -p (?<password>random))|(?: -e (?<email>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,4}$")) != null)
                System.out.println(SignupController.createUser(matcher));
            else if ((matcher = isMatched(command, "^user create(((?: -u (?<username>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -n (?<nickname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -p (?<password>random))|(?: -s (?<slogan>random))){0,1}){0,4}$")) != null)
                System.out.println(SignupController.createUser(matcher));
            else if ((matcher = isMatched(command, "^user login(((?: -u (?<username>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -p (?<password>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?<staylogedin> --stay-logged-in)){0,1}){0,3}$")) != null) {
                String message = SignupController.login(matcher);
                System.out.println(message);
                if (message.equals("user logged in successfully!"))
                    LoginController.start();
            } else if ((matcher = isMatched(command, "^forgot my password((?: -u (?<username>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null) {
                System.out.println(SignupController.forgetPassword(matcher));
            } else if (isMatched(command, "exit") != null) {
                System.out.println("Program Has Been Ended");
                System.exit(0);
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }

    public static Matcher pickSecurityQuestion(String message) {
        Matcher matcher;
        String command;
        while (true) {
            System.out.println(message + "Pick your security question: 1. What is my father’s name? 2. What" +
                    "was my first pet’s name? 3. What is my mother’s last name?");
            System.out.println("If you regret plz enter <regret> else enter security question number");
            command = getScanner().nextLine();
            if ((matcher = isMatched(command, "^question pick(((?: -q (?<number>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -a (?<answer>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<answerConfirm>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$\n")) != null)
                return matcher;
            else if (isMatched(command, "regret") != null) {
                break;
            } else
                System.out.println("Invalid command!");
        }
        return null;
    }

    public static String enterAnswerForSet(String question) {
        System.out.println(question);
        System.out.println("Please enter your answer");
        return getScanner().nextLine();
    }

    public static void showSlogan(String slogan) {
        System.out.println("Your slogan is\"" + slogan + "\"");
    }

    public static String enterNewPassword(String message) {
        System.out.println(message + "Enter new password");
        return getScanner().nextLine();
    }

    public static void reEnterPassword(String randomPassword) {
        String command = "";
        while (command.equals(randomPassword)) {
            System.out.println("Your random password is: " + randomPassword + "\nPlease re-enter your password here:");
            command = getScanner().nextLine();
        }
    }
}