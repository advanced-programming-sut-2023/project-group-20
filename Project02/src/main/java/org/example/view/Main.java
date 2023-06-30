package org.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ZoomEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    private static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage=primaryStage;
        URL url = LoginMenu.class.getResource("/FXML/Main.fxml");
        Pane pane= FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/02.jpg").toExternalForm(), 1500, 800, false, false);
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

    public void goLogin(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu=new LoginMenu();
        loginMenu.start(stage);
    }

    public void goSignup(MouseEvent mouseEvent) throws Exception {
        SignupMenu signupMenu=new SignupMenu();
        signupMenu.start(stage);
    }

    public static Stage getStage() {
        return stage;
    }
}
