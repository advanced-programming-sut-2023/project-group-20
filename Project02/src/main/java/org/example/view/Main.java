package org.example.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.controller.ProfileController;

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
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/02.jpg").toExternalForm(), 1500, 700, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 130, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button login=new Button();login.setMinHeight(35);login.setText("login");login.setTextFill(Color.web("#FFFF00"));login.setLayoutY(180);login.setLayoutX(250);login.setMinWidth(135);login.setBackground(btnBackground);login.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        login.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    goLogin((MouseEvent) event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Button signup=new Button();signup.setMinHeight(35);signup.setText("signup");signup.setTextFill(Color.web("#FFFF00"));signup.setLayoutY(180);signup.setLayoutX(1200);signup.setMinWidth(135);signup.setBackground(btnBackground);signup.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        signup.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    goSignup((MouseEvent) event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Label label=new Label("");label.setText("Stronghold");label.setLayoutX(690);label.setLayoutY(15);label.setTextFill(Color.web("#FFFF00"));label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 40px;");
        pane.getChildren().addAll(login,signup,label);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void goLogin(MouseEvent mouseEvent) throws Exception {
        LoginMenu loginMenu=new LoginMenu();
        LoginMenuController.goLogin();
    }

    public void goSignup(MouseEvent mouseEvent) throws Exception {
        SignupMenu signupMenu=new SignupMenu();
        signupMenu.start(stage);

    }


    public static Stage getStage() {
        return stage;
    }

    public void setBackground(ActionEvent actionEvent) {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 130, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundImage2);
//        loginBtn.setBackground(background2);

    }
}
