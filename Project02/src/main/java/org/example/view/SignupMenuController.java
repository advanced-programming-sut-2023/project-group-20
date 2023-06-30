package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.controller.SignupController;
import org.example.model.DataBase;

public class SignupMenuController {

    public TextField username;
    public Label usernameError;
    public Label passwordError;
    public TextField confirmPassword;
    public TextField nickname;
    public TextField email;
    public Label emailError;
    public Label confirmError;
    public Label nicknameError;
    public Label createError;
    public PasswordField password;

    public void back(MouseEvent mouseEvent) throws Exception {
        Main main = new Main();
        main.start(Main.getStage());
    }

    public void OK(MouseEvent mouseEvent) throws Exception {
        if (username.getText().equals("")) {
            usernameError.setText("Please enter username!");
            usernameError.setTextFill(Color.web("#ee1257"));
        }
        if (password.getText().equals("")) {
            passwordError.setText("Please enter password!");
            passwordError.setTextFill(Color.web("#ee1257"));
        }
        if (nickname.getText().equals("")) {
            nicknameError.setText("Please enter a nickname!");
            nicknameError.setTextFill(Color.web("#ee1257"));
        }
        if (!password.getText().equals(confirmPassword.getText())) {
            confirmError.setText("password confirm isn't correct!");
            createError.setTextFill(Color.web("#ee1257"));
        }
        String create = SignupController.createUser(username.getText(), password.getText(), nickname.getText(), email.getText());
        if (!create.equals("Good")) {
            createError.setText(create);
            createError.setTextFill(Color.web("#ee1257"));
        } else {
            SignupMenu.QuestionMenu qestionMenu = new SignupMenu.QuestionMenu();
            qestionMenu.start(Main.getStage());
        }
    }

    public void usernameKey(KeyEvent keyEvent) {
        usernameError.setText(SignupController.usernameCheck(username.getText()));
        usernameError.setTextFill(Color.web("#ee1257"));
    }

    public void passwordKey(KeyEvent keyEvent) {
        passwordError.setText(SignupController.passCheck(password.getText()));
        passwordError.setTextFill(Color.web("#ee1257"));
    }

    public void randomPass(MouseEvent mouseEvent) {
        String random = SignupController.makeRandomPassword();
        System.out.println(random);
        password.setText(random);
        passwordError.setText("please enter this pass in confirm field:-->  " + random);
        passwordError.setTextFill(Color.web("#ee1257"));

    }

    public void confirmKey(KeyEvent keyEvent) {
        if (!password.getText().equals(confirmPassword.getText())) {
            confirmError.setText("password confirm isn't correct!");
            createError.setTextFill(Color.web("#ee1257"));
        }
    }

    public TextField questionNumber;
    public TextField questionAnswer;
    public Label errorLabel;

    public void OKQuestion(MouseEvent mouseEvent) {
        if (!(questionNumber.getText().equals("1") || questionNumber.getText().equals("2") || questionNumber.getText().equals("3"))) {
            errorLabel.setText("please enter 1 OR 2 OR 3");
            errorLabel.setTextFill(Color.web("ee1257"));
        }
        SignupController.getCreatingUser().setSecurityQuestion(questionNumber.getText());
        SignupController.getCreatingUser().setSecurityQuestionAnswer(questionAnswer.getText());
        DataBase.addUserToDataBase(SignupController.getCreatingUser());
        errorLabel.setText("Question picked!");
        errorLabel.setTextFill(Color.web("ffc300"));
    }
}
