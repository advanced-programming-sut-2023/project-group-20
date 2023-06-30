package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.ProfileController;
import org.example.controller.SignupController;
import org.example.model.DataBase;
import org.example.model.GameInfo.ProfileAvatar;
import org.example.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class LoginMenuController {

    public Label errorLabel = new Label("error bar");

    public static void Back() throws Exception {
        Main main = new Main();
        main.start(Main.getStage());
    }

    public void goProfile() throws Exception {
        Stage primaryStage = Main.getStage();
        URL url = LoginMenu.class.getResource("/FXML/Profile.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 130, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background2 = new Background(backgroundImage2);
        Button Back = new Button("Logout");
        Back.setPrefSize(135, 25);
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(660);
        Back.setLayoutX(35);
        Back.setBackground(background2);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main main = new Main();
                try {
                    main.start(Main.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        errorLabel.setLayoutX(1100);
        errorLabel.setLayoutY(325);
        Font font = new Font(17);
        errorLabel.setFont(font);
        errorLabel.setTextFill(Color.web("#00FF00"));
        Image image3 = new Image(LoginMenu.class.getResource("/picture/background/infoBackground.png").toExternalForm(), 330, 200, false, false);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background infoBackground = new Background(backgroundImage3);
        Button info = new Button();
        info.setLayoutY(90);
        info.setLayoutX(1100);
        info.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 18px;");
        info.setPrefSize(330, 200);
        info.setBackground(infoBackground);
        info.setText(ProfileController.showAll());
        pane.getChildren().addAll(errorLabel, Back, info);
        {
            Button button1 = new Button();
            TextField textField1 = new TextField();
            textField1.setLayoutX(1100);
            textField1.setLayoutY(370);
            textField1.setBackground(background2);
            textField1.setPromptText("enter username");
            textField1.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button1.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button1.setLayoutY(370);
            button1.setMinWidth(130);
            button1.setLayoutX(1300);
            button1.setText("change username");
            button1.setTextFill(Color.web("#FFFF00"));
            button1.setBackground(background2);
            pane.getChildren().add(button1);
            pane.getChildren().add(textField1);
            textField1.setOnKeyTyped(new EventHandler() {
                @Override
                public void handle(Event event) {
                    errorLabel.setText(ProfileController.checkBeforeUsername(textField1.getText()));
                }
            });
            button1.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    username(textField1);
                    info.setText(ProfileController.showAll());
                    textField1.setText("");
                    errorLabel.setText("error bar");
                }
            });
            Button button2 = new Button();
            button2.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            TextField textField2 = new TextField();
            textField2.setLayoutY(650);
            textField2.setLayoutX(1100);
            textField2.setPromptText("enter password");
            textField2.setBackground(background2);
            textField2.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button2.setLayoutY(650);
            button2.setMinWidth(130);
            button2.setLayoutX(1300);
            button2.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    password(textField2);
                    info.setText(ProfileController.showAll());
                    textField2.setText("");
                    errorLabel.setText("error bar");
                }
            });
            textField2.setOnKeyTyped(new EventHandler() {
                @Override
                public void handle(Event event) {
                    errorLabel.setText(ProfileController.checkBeforePassword(textField2.getText()));
                }
            });
            button2.setText("change password");
            button2.setTextFill(Color.web("#FFFF00"));
            button2.setBackground(background2);
            pane.getChildren().add(button2);
            pane.getChildren().add(textField2);
            Button button3 = new Button();
            button3.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            Button button6 = new Button();
            button6.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            Label sloganLabel = new Label(ProfileController.showSlogan());
            sloganLabel.setLayoutX(5);
            sloganLabel.setStyle("-fx-text-fill: #FFFFFF;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 20px;");
            sloganLabel.setLayoutY(3);
            pane.getChildren().add(sloganLabel);
            button3.setLayoutY(440);
            button6.setLayoutY(465);
            TextField textField3 = new TextField();
            textField3.setLayoutX(1100);
            textField3.setLayoutY(452);
            textField3.setBackground(background2);
            textField3.setPromptText("new OR random");
            textField3.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button3.setLayoutX(1300);
            button6.setLayoutX(1300);
            button3.setMinWidth(130);
            button6.setMinWidth(130);
            button3.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    slogan(textField3);
                    sloganLabel.setText(textField3.getText());
                    info.setText(ProfileController.showAll());
                    textField3.setText("");
                    errorLabel.setText("error bar");
                }
            });
            button6.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText(ProfileController.removeSlogan());
                    alert.showAndWait();
                    sloganLabel.setText("slogan is empty!");
                    info.setText(ProfileController.showAll());
                    textField3.setText("");
                    errorLabel.setText("error bar");
                }
            });
            textField3.setOnKeyTyped(new EventHandler() {
                @Override
                public void handle(Event event) {
                    errorLabel.setText("slogan is good!");
                }
            });
            button3.setText("change slogan");
            button6.setText("remove slogan");
            button3.setTextFill(Color.web("#FFFF00"));
            button6.setTextFill(Color.web("#FFFF00"));
            button3.setBackground(background2);
            button6.setBackground(background2);
            pane.getChildren().add(button3);
            pane.getChildren().add(button6);
            pane.getChildren().add(textField3);

            Button button4 = new Button();
            button4.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button4.setLayoutY(510);
            TextField textField4 = new TextField();
            textField4.setLayoutY(510);
            textField4.setLayoutX(1100);
            textField4.setBackground(background2);
            textField4.setPromptText("enter email");
            textField4.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button4.setLayoutX(1300);
            button4.setMinWidth(130);
            button4.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    email(textField4);
                    info.setText(ProfileController.showAll());
                    textField4.setText("");
                    errorLabel.setText("error bar");
                }
            });
            textField4.setOnKeyTyped(new EventHandler() {
                @Override
                public void handle(Event event) {
                    errorLabel.setText(ProfileController.checkBeforeEmail(textField4.getText()));
                }
            });

            button4.setText("change email");
            button4.setTextFill(Color.web("#FFFF00"));
            button4.setBackground(background2);
            pane.getChildren().add(button4);
            pane.getChildren().add(textField4);
            Button button5 = new Button();
            button5.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button5.setLayoutY(580);
            button5.setLayoutX(1300);
            TextField textField5 = new TextField();
            textField5.setLayoutY(580);
            textField5.setPromptText("enter nickname");
            textField5.setLayoutX(1100);
            textField5.setBackground(background2);
            textField5.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            button5.setText("change nickname");
            button5.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    nickname(textField5);
                    info.setText(ProfileController.showAll());
                    textField5.setText("");
                    errorLabel.setText("error bar");
                }
            });
            textField5.setOnKeyTyped(new EventHandler() {
                @Override
                public void handle(Event event) {
                    errorLabel.setText("nickname is good!");
                }
            });
            button5.setMinWidth(130);
            button5.setTextFill(Color.web("#FFFF00"));
            button5.setBackground(background2);
            pane.getChildren().add(button5);
            pane.getChildren().add(textField5);
        }
        {
            ProfileAvatar profileAvatar;
            try {
                Integer.parseInt(LoginController.getLogedInuser().getProfileAvatar());
                profileAvatar = new ProfileAvatar(65, 80, 120, LoginController.getLogedInuser().getProfileAvatar());

            } catch (Exception e) {
                profileAvatar = new ProfileAvatar(65, 80, 120, LoginController.getLogedInuser().getProfileAvatar(), 5);
            }
            Button changeAvatar = new Button("change avatar");
            changeAvatar.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            changeAvatar.setPrefSize(130, 25);
            changeAvatar.setBackground(background2);
            changeAvatar.setLayoutY(190);
            changeAvatar.setLayoutX(10);
            ProfileAvatar finalProfileAvatar = profileAvatar;

            changeAvatar.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        avatarSetting();
                        pane.getChildren().add(finalProfileAvatar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            pane.getChildren().addAll(profileAvatar, changeAvatar);

        }
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/Backgrounds For Menus - yac/IMG_6047.PNG").toExternalForm(), 1500, 800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        Button rank = new Button("show ranks");
        rank.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        rank.setLayoutY(300);
        rank.setMinWidth(130);
        rank.setLayoutX(600);
        rank.setTextFill(Color.web("#FFFF00"));
        rank.setBackground(background2);
        rank.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    rankStage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        TextArea textArea=new TextArea();textArea.setPromptText("enter users is different lines");
        textArea.setStyle("-fx-text-fill: #0BE91F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        textArea.setLayoutY(400);
        textArea.setPrefSize(130,100);
        textArea.setLayoutX(600);
        Button startGame=new Button("start game");
        startGame.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        startGame.setLayoutY(550);
        startGame.setMinWidth(130);
        startGame.setLayoutX(600);
        startGame.setTextFill(Color.web("#FFFF00"));
        startGame.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                String msg= LoginController.startGame(1000,200,200,LoginController.getLogedInuser(),textArea.getText());
                if (!msg.equals("")){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(msg);
                    alert.showAndWait();
                }else {
                    LoginController.gameController.start();
                }
            }
        });
        startGame.setBackground(background2);
        pane.getChildren().addAll(rank,startGame,textArea);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void rankStage() throws IOException {
        Stage stage = new Stage();
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/DropdownButton/bg@2x.png").toExternalForm(), 150, 150, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/Backgrounds For Menus - yac/103-1030913_game-of-thrones-erie.jpg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        URL url = LoginMenu.class.getResource("/FXML/Question.fxml");
        Pane pane = FXMLLoader.load(url);
        pane.setBackground(background);

        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setLayoutY(100);
        scrollBar.setLayoutX(350);
        scrollBar.setMinSize(20,250);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setVisibleAmount(10);
        scrollBar.setMax(DataBase.getUsers().size()+5);
        Label rank = new Label();
        ArrayList<String> users = new ArrayList<>();
        for (User user : DataBase.getUsers()) {
            users.add(DataBase.rank(user) + ") " + user.getUsername() + " score: " + user.getHighScore());
        }
        final int[] x = {((int) scrollBar.getValue()) / 10};
        rank.setText(users.get(x[0]) + "\n" + (users.size()>(1+ x[0])? users.get(1 + x[0]):"") + "\n"+(users.size()>(2+ x[0])? users.get(2 + x[0]):"") + "\n"+(users.size()>(3+ x[0])? users.get(3 + x[0]):"") + "\n"+(users.size()>(4+ x[0])? users.get(4 + x[0]):"") + "\n"+(users.size()>(5+ x[0])? users.get(5 + x[0]):"") + "\n"+(users.size()>(6+ x[0])? users.get(6 + x[0]):"") + "\n"+(users.size()>(7+ x[0])? users.get(7+ x[0]):"") + "\n"+(users.size()>(8+ x[0])? users.get(8 + x[0]):"") + "\n"+(users.size()>(9+ x[0])? users.get(9 + x[0]):"") + "\n");
        scrollBar.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println(scrollBar.getValue());
                x[0] = ((int) scrollBar.getValue()) / 10;
                System.out.println(x[0]);
                x[0]*=10;
                rank.setText(users.get(x[0]) + "\n" + (users.size()>(1+ x[0])? users.get(1 + x[0]):"") + "\n"+(users.size()>(2+ x[0])? users.get(2 + x[0]):"") + "\n"+(users.size()>(3+ x[0])? users.get(3 + x[0]):"") + "\n"+(users.size()>(4+ x[0])? users.get(4 + x[0]):"") + "\n"+(users.size()>(5+ x[0])? users.get(5 + x[0]):"") + "\n"+(users.size()>(6+ x[0])? users.get(6 + x[0]):"") + "\n"+(users.size()>(7+ x[0])? users.get(7+ x[0]):"") + "\n"+(users.size()>(8+ x[0])? users.get(8 + x[0]):"") + "\n"+(users.size()>(9+ x[0])? users.get(9 + x[0]):"") + "\n");
            }
        });
        scrollBar.setBackground(background);

        rank.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        rank.setLayoutY(100);
        rank.setPrefSize(250, 250);
        rank.setLayoutX(100);
        rank.setTextFill(Color.web("#FFFF00"));
        pane.getChildren().addAll(rank,scrollBar);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static Pane paneAvatar;

    private static void avatarSetting() throws IOException {
        Stage stage = new Stage();
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 150, 40, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);

        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/10.jpg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        URL url = LoginMenu.class.getResource("/FXML/Question.fxml");
        Pane pane = FXMLLoader.load(url);
        {
            ProfileAvatar userAvatar;
            try {
                Integer.parseInt(LoginController.getLogedInuser().getProfileAvatar());
                userAvatar = new ProfileAvatar(47, 520, 75, LoginController.getLogedInuser().getProfileAvatar());

            } catch (Exception e) {
                userAvatar = new ProfileAvatar(47, 520, 75, LoginController.getLogedInuser().getProfileAvatar(), 5);
            }
            ProfileAvatar profileAvatar1 = new ProfileAvatar(30, 100, 150, "1");
            ProfileAvatar profileAvatar2 = new ProfileAvatar(30, 200, 150, "2");
            ProfileAvatar profileAvatar3 = new ProfileAvatar(30, 300, 150, "3");
            ProfileAvatar profileAvatar4 = new ProfileAvatar(30, 100, 250, "4");
            ProfileAvatar profileAvatar5 = new ProfileAvatar(30, 200, 250, "5");
            ProfileAvatar profileAvatar6 = new ProfileAvatar(30, 300, 250, "6");
            ProfileAvatar profileAvatar7 = new ProfileAvatar(30, 100, 350, "7");
            ProfileAvatar profileAvatar8 = new ProfileAvatar(30, 200, 350, "8");
            ProfileAvatar profileAvatar9 = new ProfileAvatar(30, 300, 350, "9");
            paneAvatar = pane;
            pane.getChildren().addAll(profileAvatar4, profileAvatar7, profileAvatar6, profileAvatar5, profileAvatar2, profileAvatar3, profileAvatar1, profileAvatar8, profileAvatar9, userAvatar);
        }
        {
            TextField textField = new TextField();
            textField.setPromptText("drag OR write");
            textField.setBackground(btnBackground);
            textField.setLayoutY(300);
            textField.setLayoutX(420);
            textField.setOnDragDropped(new EventHandler<DragEvent>() {

                @Override
                public void handle(DragEvent event) {
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasFiles()) {
                        textField.setText(db.getFiles().toString());
                        success = true;
                    }
                    event.setDropCompleted(success);
                    event.consume();
                }
            });
            textField.setPrefSize(150, 40);
            textField.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            Button ok = new Button("ok");
            ok.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
            ok.setLayoutX(420);
            ok.setLayoutY(350);
            ok.setPrefSize(150, 40);
            ok.setBackground(btnBackground);
            ok.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    try {
                        ProfileAvatar urlAvatar = new ProfileAvatar(47, 520, 75, textField.getText(), 5);
                        LoginController.getLogedInuser().setProfileAvatar(textField.getText());
                        paneAvatar.getChildren().remove(9);
                        paneAvatar.getChildren().add(urlAvatar);

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Invalid file");
                        alert.setContentText("Please choose valid path");
                        alert.showAndWait();
                    }
                }
            });
            pane.getChildren().addAll(ok, textField);
        }
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler() {
            @Override
            public void handle(Event event) {
                LoginMenuController loginMenuController = new LoginMenuController();
                try {
                    loginMenuController.goProfile();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.show();
    }

    public static void goLogin() throws IOException {
        Stage primaryStage = Main.getStage();
        URL url = LoginMenu.class.getResource("/FXML/Login.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/04.jpg").toExternalForm(), 1500, 800, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 135, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button back = new Button();
        back.setLayoutY(650);
        back.setMinWidth(135);
        back.setMinHeight(35);
        back.setTextFill(Color.web("#FFFF00"));
        back.setLayoutX(1300);
        back.setText("Back");
        back.setBackground(btnBackground);
        back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    Back();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        TextField textField = new TextField();
        textField.setLayoutY(350);
        textField.setLayoutX(700);
        textField.setMinWidth(135);
        textField.setMinHeight(35);
        textField.setFont(Font.font(15));
        textField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        textField.setPromptText("username");
        textField.setBackground(btnBackground);
        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutY(400);
        passwordField.setFont(Font.font(15));
        passwordField.setLayoutX(700);
        passwordField.setMinHeight(35);
        passwordField.setPromptText("password");
        passwordField.setMinWidth(135);
        passwordField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        passwordField.setBackground(btnBackground);
        Button button = new Button();
        button.setLayoutX(700);
        button.setLayoutY(450);
        button.setMinWidth(135);
        button.setMinHeight(35);
        button.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        button.setTextFill(Color.web("#FFFF00"));
        button.setText("LOGIN");
        button.setBackground(btnBackground);
        button.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (DataBase.getUserByName(textField.getText()) != null && DataBase.getUserByName(textField.getText()).getPassword().equals(passwordField.getText())) {
                    try {
                        captcha(textField.getText(), passwordField.getText());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    String message = SignupController.login(textField.getText(), passwordField.getText());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(message);
                    alert.showAndWait();

                }
            }
        });
        Button forgot = new Button();
        forgot.setLayoutX(700);
        forgot.setMinWidth(135);
        forgot.setMinHeight(35);
        forgot.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        forgot.setTextFill(Color.web("#FFFF00"));
        forgot.setLayoutY(500);
        forgot.setText("forgot pass");
        forgot.setBackground(btnBackground);
        forgot.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    if (DataBase.getUserByName(textField.getText()) == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("username isn't available");
                        alert.showAndWait();
                    } else
                        forgotPass(textField.getText());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(button);
        pane.getChildren().add(textField);
        pane.getChildren().add(passwordField);
        pane.getChildren().add(forgot);
        pane.getChildren().add(back);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void forgotPass(String username) throws IOException {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 135, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Stage primaryStage = new Stage();
        URL url = LoginMenu.class.getResource("/FXML/Forgot.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/10.jpg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        pane.setBackground(background);
        Label user = new Label();
        user.setText(username);
        user.setLayoutY(15);
        user.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 20px;");
        user.setLayoutX(500);
        user.setFont(Font.font(15));
        user.setTextFill(Color.web("#16EE33"));
        Label question = new Label();
        question.setText(DataBase.getUserByName(username).getSecurityQuestion());
        question.setLayoutY(150);
        question.setLayoutX(50);
        question.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        question.setTextFill(Color.web("#1657EE"));
        TextField textField = new TextField();
        textField.setMinWidth(130);
        textField.setLayoutY(200);
        textField.setMinHeight(35);
        textField.setLayoutX(50);
        textField.setPromptText("answer");
        textField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        textField.setBackground(btnBackground);
        Button button = new Button("OK");
        button.setLayoutY(255);
        button.setMinHeight(35);
        button.setMinWidth(50);
        button.setLayoutX(75);
        button.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        button.setBackground(btnBackground);
        button.setTextFill(Color.web("#FFFF00"));
        button.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                String s = "";
                if (username == null || DataBase.getUserByName(username) == null)
                    s = "Please enter a available username in username field";
                if (!textField.getText().equals(DataBase.getUserByName(username).getSecurityQuestionAnswer()))
                    s = "answer is wrong!!";
                if (!s.equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(s);
                    alert.showAndWait();
                } else {
                    pane.getChildren().remove(button);
                    pane.getChildren().remove(textField);
                    pane.getChildren().remove(question);
                    setPassForgot(pane, username, primaryStage);
                }
            }
        });
        pane.getChildren().addAll(button, textField, user, question);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void captcha(String username, String password) throws IOException {
        Stage stage = new Stage();
        URL url = LoginMenu.class.getResource("/FXML/Question.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/Backgrounds For Menus - yac/493585.jpg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        pane.setBackground(background);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 120, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);

        Button textArea = new Button();
        textArea.setLayoutY(150);
        textArea.setLayoutX(450);
        textArea.setPrefSize(110, 40);
        final String[] captcha = {randomCaptcha()};
        textArea.setBackground(backCaptcha(captcha[0]));
        TextField textField = new TextField();
        textField.setPromptText("enter captcha");
        textField.setLayoutY(200);
        textField.setLayoutX(400);
        textField.setBackground(btnBackground);
        textField.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 15px;");
        textField.setPrefSize(120, 35);
        Button button = new Button("OK");
        button.setBackground(btnBackground);
        button.setPrefSize(120, 35);
        button.setLayoutY(250);
        button.setLayoutX(320);
        button.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        button.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!captcha[0].equals(textField.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("captcha is wrong");
                    alert.showAndWait();
                    captcha[0] = randomCaptcha();
                    textField.setText("");
                    textArea.setBackground(backCaptcha(captcha[0]));
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("captcha is ok!");
                    alert.showAndWait();
                    try {
                        stage.close();
                        String message = SignupController.login(username, password);
                        if (message.equals("user logged in successfully!")) {
                            try {
                                LoginMenuController loginMenuController = new LoginMenuController();
                                loginMenuController.goProfile();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Image image3 = new Image(LoginMenu.class.getResource("/picture/Others/some of google material design/refresh.png").toExternalForm(), 20, 20, false, false);
        BackgroundImage backgroundImage3 = new BackgroundImage(image3,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background refreshBack = new Background(backgroundImage3);
        Button refresh = new Button();
        refresh.setLayoutY(155);
        refresh.setLayoutX(405);
        refresh.setBackground(refreshBack);
        refresh.setPrefSize(20, 20);
        refresh.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                captcha[0] = randomCaptcha();
                textField.setText("");
                textArea.setBackground(backCaptcha(captcha[0]));
            }
        });
        pane.getChildren().addAll(refresh, button, textArea, textField);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public static Background backCaptcha(String captcha) {
        Image imageCaptcha = new Image(LoginMenu.class.getResource("/picture/Captcha/" + captcha + ".png").toExternalForm(), 110, 35, false, false);
        BackgroundImage backgroundImageCaptcha = new BackgroundImage(imageCaptcha,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backgroundImageCaptcha);
    }

    public static String randomCaptcha() {
        Random random = new Random();
        return DataBase.getCaptcha().get(random.nextInt(0, DataBase.getCaptcha().size() - 1));
    }

    private static void setPassForgot(Pane pane, String username, Stage primaryStage) {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 135, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        PasswordField passwordField = new PasswordField();
        passwordField.setMinHeight(35);
        passwordField.setLayoutX(50);
        passwordField.setLayoutY(120);
        passwordField.setBackground(btnBackground);
        passwordField.setFont(Font.font(15));
        passwordField.setPromptText("password");
        passwordField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        Label passwordError = new Label("");
        passwordError.setText("enter pass");
        passwordError.setLayoutX(50);
        passwordError.setLayoutY(160);
        passwordError.setTextFill(Color.web("#ee1257"));
        passwordField.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                passwordError.setText(SignupController.passCheck(passwordField.getText()));
            }
        });
        Button randomPassword = new Button();
        randomPassword.setMinHeight(35);
        randomPassword.setText("random password");
        randomPassword.setTextFill(Color.web("#FFFF00"));
        randomPassword.setLayoutY(190);
        randomPassword.setLayoutX(50);
        randomPassword.setMinWidth(135);
        randomPassword.setBackground(btnBackground);
        Label random = new Label();
        random.setText("");
        random.setTextFill(Color.web("#ee1257"));
        random.setLayoutY(230);
        random.setLayoutX(50);
        randomPassword.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                String random = SignupController.makeRandomPassword();
                passwordField.setText(random);
                passwordError.setText("please enter this pass in confirm field:-->  " + random);
            }
        });
        PasswordField reEnterPassword = new PasswordField();
        reEnterPassword.setMinHeight(35);
        reEnterPassword.setLayoutX(50);
        reEnterPassword.setLayoutY(260);
        reEnterPassword.setBackground(btnBackground);
        reEnterPassword.setFont(Font.font(15));
        reEnterPassword.setPromptText("confirm pass");
        reEnterPassword.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        Label confirm = new Label();
        confirm.setText("reEnter password");
        confirm.setTextFill(Color.web("#ee1257"));
        confirm.setLayoutY(297);
        confirm.setLayoutX(50);
        reEnterPassword.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!passwordField.getText().equals(reEnterPassword.getText())) {
                    confirm.setText("password confirm isn't correct!");
                    confirm.setTextFill(Color.web("#ee1257"));
                } else {
                    confirm.setText("GOOD!");
                    confirm.setTextFill(Color.web("#FFFF00"));
                }
            }
        });
        Button ok = new Button();
        ok.setBackground(btnBackground);
        ok.setMinHeight(35);
        ok.setLayoutY(320);
        ok.setLayoutX(50);
        ok.setTextFill(Color.web("#FFFF00"));
        ok.setMinWidth(135);
        ok.setText("OK");
        ok.setFont(Font.font(17));
        ok.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!SignupController.passCheck(passwordField.getText()).equals("pass is OK!")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(SignupController.passCheck(passwordField.getText()));
                    alert.showAndWait();
                } else if (!passwordField.getText().equals(reEnterPassword.getText())) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("confirm pass is'nt correct");
                    alert.showAndWait();
                } else {
                    User user = DataBase.getUserByName(username);
                    user.setPassword(passwordField.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("pass changed.");
                    alert.showAndWait();
                    SignupController.login(user.getUsername(), user.getPassword());
                    try {
                        primaryStage.close();
                        LoginMenuController loginMenuController = new LoginMenuController();
                        loginMenuController.goProfile();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        pane.getChildren().addAll(ok, passwordError, passwordField, confirm, randomPassword, random, reEnterPassword);
    }

    private void slogan(TextField textField) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ProfileController.changeSlogan(textField.getText()));
        alert.showAndWait();
    }

    private void nickname(TextField textField) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ProfileController.changeNickName(textField.getText()));
        alert.showAndWait();
    }

    private void username(TextField textField) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ProfileController.changeUsername(textField.getText()));
        alert.showAndWait();
    }

    private void password(TextField textField) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ProfileController.changePassword(textField.getText()));
        alert.showAndWait();
    }

    private void email(TextField textField) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(ProfileController.changeEmail(textField.getText()));
        alert.showAndWait();
    }
}