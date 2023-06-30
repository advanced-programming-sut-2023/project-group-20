package org.example.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.ProfileController;

import java.net.URL;
import java.util.regex.Matcher;

public class LoginMenu extends Menu{

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
