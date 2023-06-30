package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.SignupController;
import java.net.URL;
import java.util.regex.Matcher;

public class SignupMenu extends Menu {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/Signup.fxml");
        Pane pane=FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/01.jpg").toExternalForm(), 1500, 800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);
        pane.setBackground(background);
        CheckBox checkBox=new CheckBox();
        checkBox.setLayoutX(750);
        checkBox.setLayoutY(550);
        pane.getChildren().add(checkBox);
        Label sloganLabel=new Label();
        checkBox.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("222222222");
                Button button=new Button();
                button.setText("random slogan");
                TextField textField=new TextField("enter slogan");
                textField.setLayoutX(750);
                textField.setLayoutY(550);
                button.setLayoutX(850);
                button.setLayoutY(550);
                pane.getChildren().add(button);
                pane.getChildren().add(textField);
                button.setOnMouseClicked(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        System.out.println("333333333");

                        sloganLabel.setText(SignupController.makeRandomSlogan());
                        sloganLabel.setLayoutX(950);
                        sloganLabel.setLayoutY(550);
                        pane.getChildren().add(sloganLabel);
                    }
                });
            }
        });
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void run() {
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^user create(((?: -u (?<username>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\"))))|(?: -p (?<password>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\")))( (?<confirmPassword>(?:\"[^\"]*\")|(?:(?!\")\\S+(?<!\")))){0,1})|(?<checkSlogan> -s (?<slogan>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -n (?<nickname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -e (?<email>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,5}$")) != null)
                System.out.println();
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
            if ((matcher = isMatched(command, "^question pick(((?: -q (?<number>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -a (?<answer>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<answerConfirm>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
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
        while (!command.equals(randomPassword)) {
            System.out.println("Your random password is: " + randomPassword + "\nPlease re-enter your password here:");
            command = getScanner().nextLine();
        }
    }
    public static class QuestionMenu extends Menu{

        @Override
        public void start(Stage primaryStage) throws Exception {
            URL url = LoginMenu.class.getResource("/FXML/Question.fxml");
            Pane pane=FXMLLoader.load(url);
            Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/05.jpg").toExternalForm(), 1500, 800, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background=new Background(backgroundImage);
            pane.setBackground(background);
            Label label=new Label("Pick your security question: 1. What is my father’s name? \n2. What" +
                    "was my first pet’s name? \n3. What is my mother’s last name?");
            label.setLayoutY(100);
            label.setLayoutX(750);
            label.setTextFill(Color.web("0076a3"));
            pane.getChildren().add(label);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
}