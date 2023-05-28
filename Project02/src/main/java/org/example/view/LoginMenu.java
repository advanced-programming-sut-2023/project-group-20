package org.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.ProfileController;

import java.net.URL;
import java.util.regex.Matcher;

public class LoginMenu extends Menu{
    public void run(){
        String command=getScanner().nextLine();
        Matcher matcher;
        while (true){
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
            else if ((matcher = isMatched(command, "^start game playernum: ((?<num>\\S+)) turn: ((?<turn>\\S+)) xmap: ((?<x>\\S+)) ymap: ((?<y>\\S+))$")) != null)
                System.out.print(LoginController.startGame(matcher,logedInUser));
            else if ((matcher = isMatched(command, "^profile display$")) != null)
                System.out.println(ProfileController.showAll());
            else if ((matcher = isMatched(command, "^profile display highscore$")) != null)
                System.out.println(ProfileController.showHighScore());
            else if (isMatched(command,"user logout")!=null) {
                System.out.println(LoginController.logout());
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }
    public static String changePassword(){
        System.out.println("Please enter your new password again AND if you regretted please enter <regret>");
        return getScanner().nextLine();
    }
    public static String getPlayers(String massage){
        System.out.println(massage);
        return getScanner().nextLine();
    }
    public static void winnerPrint(String string){
        System.out.println(string);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/Login.fxml");
        Pane pane= FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/04.jpg").toExternalForm(), 1500, 800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
