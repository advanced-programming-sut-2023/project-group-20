package view;

import controller.LoginController;
import controller.MapController;
import controller.ProfileController;
import controller.SignupController;

import java.util.regex.Matcher;

public class LoginMenu extends Menu{
    public void run(){
        String command=getScanner().nextLine();
        Matcher matcher;
        while (true){
            //TODO
            // Start Game Command Should Be Created
            // Make a GameController and start it
            if ((matcher = isMatched(command, "^profile change((?: -u (?<username>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null)
                System.out.println(ProfileController.changeUsername(matcher));
            else if ((matcher = isMatched(command, "^profile change((?: -n (?<nickname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null)
                System.out.println(ProfileController.changeNickName(matcher));
            else if ((matcher = isMatched(command, "^profile change password(((?: -n (?<newpassword>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -o (?<oldpassword>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null)
                System.out.println(ProfileController.changePassword(matcher));
            else if ((matcher = isMatched(command, "^profile change((?: -e (?<email>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null)
                System.out.println(ProfileController.changeEmail(matcher));
            else if ((matcher = isMatched(command, "^profile change((?: -s (?<slogan>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null)
                System.out.println(ProfileController.changeSlogan(matcher));
            else if ((matcher = isMatched(command, "^Profile remove slogan$")) != null)
                System.out.println(ProfileController.removeSlogan());
            else if ((matcher = isMatched(command, "^profile display rank$")) != null)
                System.out.println(ProfileController.showRank());
            else if ((matcher = isMatched(command, "^profile display slogan$")) != null)
                System.out.println(ProfileController.showSlogan());
            else if ((matcher = isMatched(command, "^profile display$")) != null)
                System.out.println(ProfileController.showAll());
            else if ((matcher = isMatched(command, "^profile display highscore$")) != null)
                System.out.println(ProfileController.showHighScore());
            //Map controller Chera to LoginController Ejra Mishe??
            else if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+)){0,1}){0,2}$")) != null)//??????
                MapController.start(matcher);
            else if (isMatched(command,"user logout")!=null) {
                System.out.println(LoginController.logout());
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
//        SignupController.start();
        //LoginController Tavsot SignupController EjraShode Age TamoomShe Khod Kar mire Dobare To SignupController
    }
    public static String changePassword(){
        System.out.println("Please enter your new password again AND if you regretted please enter regret");
        return getScanner().nextLine();
    }
}
