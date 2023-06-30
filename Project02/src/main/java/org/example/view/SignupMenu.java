package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.SignupController;
import org.example.model.DataBase;
import java.net.URL;
import java.util.Random;

public class SignupMenu extends Menu {
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = LoginMenu.class.getResource("/FXML/MainMenu.fxml");
        Pane pane=FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/15.jpg").toExternalForm(), 1500, 700, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);
        final String[] slogan = {""};
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 135, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Label signup=new Label("");signup.setLayoutX(730);signup.setLayoutY(25);signup.setTextFill(Color.web("#FFFF00"));signup.setFont(Font.font("regular",23));signup.setText("Signup");
        TextField username=new TextField();username.setPromptText("username");username.setLayoutY(100);username.setLayoutX(600);username.setMinWidth(140);username.setBackground(btnBackground);username.setMinHeight(35);username.setFont(Font.font(15));username.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        Label usernameError=new Label("enter username");usernameError.setLayoutX(600);usernameError.setLayoutY(140);usernameError.setTextFill(Color.web("#ee1257"));
        username.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                usernameError.setText(SignupController.usernameCheck(username.getText()));
            }
        });
        PasswordField passwordField=new PasswordField();passwordField.setMinHeight(35);passwordField.setLayoutX(800);passwordField.setLayoutY(100);passwordField.setBackground(btnBackground);passwordField.setFont(Font.font(15));passwordField.setPromptText("password");passwordField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        Label passwordError=new Label("");passwordError.setText("enter pass");passwordError.setLayoutX(800);passwordError.setLayoutY(140);passwordError.setTextFill(Color.web("#ee1257"));
        passwordField.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                passwordError.setText(SignupController.passCheck(passwordField.getText()));
            }
        });
        Button randomPassword=new Button();randomPassword.setMinHeight(35);randomPassword.setText("random password");randomPassword.setTextFill(Color.web("#FFFF00"));randomPassword.setLayoutY(200);randomPassword.setLayoutX(600);randomPassword.setMinWidth(135);randomPassword.setBackground(btnBackground);
        Label random=new Label();random.setText("");random.setTextFill(Color.web("#ee1257"));random.setLayoutY(240);random.setLayoutX(600);
        randomPassword.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                String random = SignupController.makeRandomPassword();
                passwordField.setText(random);
                passwordError.setText("please enter this pass in confirm field:-->  " + random);
            }
        });
        PasswordField reEnterPassword=new PasswordField();reEnterPassword.setMinHeight(35);reEnterPassword.setLayoutX(800);reEnterPassword.setLayoutY(200);reEnterPassword.setBackground(btnBackground);reEnterPassword.setFont(Font.font(15));reEnterPassword.setPromptText("confirm pass");reEnterPassword.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        Label confirm=new Label();confirm.setText("reEnter password");confirm.setTextFill(Color.web("#ee1257"));confirm.setLayoutY(240);confirm.setLayoutX(800);
        reEnterPassword.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!passwordField.getText().equals(reEnterPassword.getText())){
                    confirm.setText("password confirm isn't correct!");
                    confirm.setTextFill(Color.web("#ee1257"));}

                else{
                    confirm.setText("GOOD!");
                    confirm.setTextFill(Color.web("#FFFF00"));
                }
            }
        });
        TextField nickname=new TextField();nickname.setMinHeight(35);nickname.setBackground(btnBackground);nickname.setLayoutY(300);nickname.setLayoutX(600);nickname.setPromptText("nickname");nickname.setMinWidth(135);nickname.setFont(Font.font(15));nickname.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        Label nickError=new Label();nickError.setLayoutX(600);nickError.setLayoutY(340);nickError.setTextFill(Color.web("#ee1257"));nickError.setText("Please enter a nickname!");
        nickname.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (nickname.getText().equals("")) {
                    nickError.setText("Please enter a nickname!");
                    nickError.setTextFill(Color.web("#ee1257"));
                }else{
                    nickError.setText("GOOD");
                    nickError.setTextFill(Color.web("#FFFF00"));
                }
            }
        });
        TextField email=new TextField();email.setMinHeight(35);email.setLayoutY(300);email.setPromptText("Email");email.setLayoutX(800);email.setBackground(btnBackground);email.setMinWidth(135);email.setFont(Font.font(15));email.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        Label emailError=new Label();emailError.setTextFill(Color.web("#ee1257"));emailError.setLayoutX(800);emailError.setLayoutY(340);
        email.setOnKeyTyped(new EventHandler() {
            @Override
            public void handle(Event event) {
                 emailError.setText(SignupController.checkMail(email.getText()));
            }
        });
        Button ok=new Button();ok.setBackground(btnBackground);ok.setMinHeight(35);ok.setLayoutY(520);ok.setLayoutX(700);ok.setTextFill(Color.web("#FFFF00"));ok.setMinWidth(135);ok.setText("OK");ok.setFont(Font.font(17));
        ok.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                String create = SignupController.createUser(username.getText(), passwordField.getText(), nickname.getText(), email.getText(), slogan[0],reEnterPassword.getText());
                if (create.equals("Good")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("User created.");
                    alert.showAndWait();
                    SignupMenu.QuestionMenu qestionMenu = new SignupMenu.QuestionMenu();
                    try {
                        qestionMenu.start(primaryStage);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(create);
                    alert.showAndWait();
                }
            }
        });
        Button back=new Button();back.setMinWidth(135);back.setMinHeight(35);back.setTextFill(Color.web("#FFFF00"));back.setLayoutY(650);back.setLayoutX(50);back.setText("back");back.setBackground(btnBackground);back.setFont(Font.font(18));
        back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                Main main=new Main();
                try {
                    main.start(primaryStage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().addAll(passwordField,reEnterPassword,usernameError,back,passwordError,random,randomPassword,confirm,ok,emailError,email,nickError,nickname,username,signup);
        pane.setBackground(background);
        CheckBox checkBox=new CheckBox();
        checkBox.setLayoutX(700);
        checkBox.setLayoutY(400);
        Label sloganLabel=new Label("Do you want slogan?");
        sloganLabel.setTextFill(Color.web("#FFF00F"));
        sloganLabel.setLayoutX(740);
        sloganLabel.setLayoutY(400);
        sloganLabel.setFont(Font.font(15));
        pane.getChildren().addAll(sloganLabel,checkBox);
        checkBox.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(sloganLabel);
                checkBox.setLayoutY(370);
                checkBox.setLayoutX(758);
                Button button=new Button();
                button.setText("random slogan");
                Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 250, 35, false, false);
                BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background btnBackground2 = new Background(backgroundImage2);
                Label slogans=new Label("Famous slogans:\n   I shall have my revenge, in this life or the next."+"\n   O MAHDI adrekni."+"\n   It is dutchman."+"\n   We will walk to Jerusalem with the Muslims together."+"\n   Hüseynçilər");
                slogans.setLayoutY(400);slogans.setLayoutX(100);slogans.setStyle("-fx-text-fill: #4AF50F;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
                TextField textField=new TextField();
                textField.setPromptText("enter a slogan OR click random");
                textField.setLayoutX(650);textField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
                textField.setPrefSize(250,35);
                textField.setBackground(btnBackground2);
                textField.setLayoutY(400);
                textField.setFont(Font.font(15));
                button.setLayoutX(700);
                button.setLayoutY(460);
                button.setPrefSize(135,35);
                button.setBackground(btnBackground);
                button.setTextFill(Color.web("#FFFF00"));
                pane.getChildren().addAll(slogans,button);
                pane.getChildren().add(textField);
                slogan[0] =textField.getText();
                button.setOnMouseClicked(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        textField.setText(SignupController.makeRandomSlogan());
                        slogan[0] =textField.getText();
                    }
                });
                slogan[0] =textField.getText();
            }
        });
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static class QuestionMenu extends Menu{

        @Override
        public void start(Stage primaryStage) throws Exception {
            primaryStage=new Stage();
            URL url = LoginMenu.class.getResource("/FXML/Question.fxml");
            Pane pane=FXMLLoader.load(url);
            Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/12.jpg").toExternalForm(), 600, 400, false, false);
            BackgroundImage backgroundImage = new BackgroundImage(image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background background=new Background(backgroundImage);
            Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 140, 25, false, false);
            BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT);
            Background btnBackground = new Background(backgroundImage2);
            pane.setBackground(background);
            Label label=new Label("Pick your security question: 1. What is my father’s name? \n2. What" +
                    "  was my first pet’s name? \n   3. What is my mother’s last name?");
            label.setLayoutY(120);
            label.setLayoutX(150);
            label.setStyle("-fx-text-fill: #DF0ED6;-fx-font-weight: bold;");
            label.setFont(Font.font(13));
            TextField questionNumber=new TextField();questionNumber.setPromptText("question number");questionNumber.setLayoutY(250);questionNumber.setLayoutX(400);questionNumber.setMinWidth(140);questionNumber.setBackground(btnBackground);questionNumber.setMinHeight(25);questionNumber.setFont(Font.font(13));questionNumber.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold;");
            TextField questionAnswer=new TextField();questionAnswer.setPromptText("question answer");questionAnswer.setLayoutY(250);questionAnswer.setLayoutX(250);questionAnswer.setMinWidth(140);questionAnswer.setBackground(btnBackground);questionAnswer.setMinHeight(25);questionAnswer.setFont(Font.font(13));questionAnswer.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold;");
            Label errorLabel=new Label("");errorLabel.setLayoutX(280);errorLabel.setLayoutY(350);errorLabel.setTextFill(Color.web("#FFFF00"));errorLabel.setFont(Font.font("regular",16));
            Button ok=new Button();ok.setBackground(btnBackground);ok.setMinHeight(25);ok.setLayoutY(300);ok.setLayoutX(300);ok.setTextFill(Color.web("#FFFF00"));ok.setMinWidth(135);ok.setText("OK");ok.setFont(Font.font(13));
            pane.getChildren().addAll(label,ok,errorLabel,questionAnswer,questionNumber);
            Stage finalPrimaryStage = primaryStage;
            ok.setOnMouseClicked(new EventHandler() {
                @Override
                public void handle(Event event) {
                    if (!(questionNumber.getText().equals("1") || questionNumber.getText().equals("2") || questionNumber.getText().equals("3"))) {
                        errorLabel.setText("please enter 1 OR 2 OR 3 in question number");
                        errorLabel.setTextFill(Color.web("#33FF33"));
                    }else  {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setContentText("question picked.");
                        alert.showAndWait();
                        pane.getChildren().removeAll(label,ok,errorLabel,questionAnswer,questionNumber);
                        captcha(finalPrimaryStage,questionNumber.getText(),questionAnswer.getText(),pane);
                    }
                }
            });
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }
    public static void captcha(Stage stage ,String questionNumber,String questionAnswer,Pane pane){

        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/Backgrounds For Menus - yac/493585.jpg").toExternalForm(), 600, 400, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background=new Background(backgroundImage);
        pane.setBackground(background);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 120, 35, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);

        Button textArea=new Button();textArea.setLayoutY(150);textArea.setLayoutX(450);textArea.setPrefSize(110,40);
        final String[] captcha = {randomCaptcha()};
        textArea.setBackground(backCaptcha(captcha[0]));
        TextField textField=new TextField();textField.setPromptText("enter captcha");textField.setLayoutY(200);textField.setLayoutX(400);textField.setBackground(btnBackground);textField.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 15px;");textField.setPrefSize(120,35);
        Button button=new Button("OK");button.setBackground(btnBackground);button.setPrefSize(120,35);button.setLayoutY(250);button.setLayoutX(320);button.setStyle("-fx-text-fill: #FFFF00;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 16px;");
        button.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!captcha[0].equals(textField.getText())){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("captcha is wrong");
                    alert.showAndWait();
                    captcha[0] =randomCaptcha();
                    textField.setText("");
                    textArea.setBackground(backCaptcha(captcha[0]));
                }else  {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("captcha is ok!");
                    alert.showAndWait();
                    SignupMenuController.OKQuestion(questionNumber,questionAnswer);
                    try {
                        stage.close();
                        LoginMenuController loginMenuController = new LoginMenuController();
                        loginMenuController.goProfile();
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
        Button refresh=new Button();refresh.setLayoutY(155);refresh.setLayoutX(405);refresh.setBackground(refreshBack);refresh.setPrefSize(20,20);
        refresh.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                captcha[0] =randomCaptcha();
                textField.setText("");
                textArea.setBackground(backCaptcha(captcha[0]));
            }
        });
        pane.getChildren().addAll(refresh,button,textArea,textField);
    }
    public static Background backCaptcha(String captcha){
        Image imageCaptcha = new Image(LoginMenu.class.getResource("/picture/Captcha/"+ captcha +".png").toExternalForm(), 110, 35, false, false);
        BackgroundImage backgroundImageCaptcha = new BackgroundImage(imageCaptcha,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return new Background(backgroundImageCaptcha);
    }
    public static String randomCaptcha(){
        Random random=new Random();
        return DataBase.getCaptcha().get(random.nextInt(0,DataBase.getCaptcha().size()-1));
    }
}