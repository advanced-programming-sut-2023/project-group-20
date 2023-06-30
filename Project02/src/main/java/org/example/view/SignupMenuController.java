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

    public static void OKQuestion(String questionNumber,String questionAnswer) {
        SignupController.getCreatingUser().setSecurityQuestion(questionNumber);
        SignupController.getCreatingUser().setSecurityQuestionAnswer(questionAnswer);
        DataBase.addUserToDataBase(SignupController.getCreatingUser());
        SignupController.login(SignupController.getCreatingUser().getUsername(), SignupController.getCreatingUser().getPassword());
    }
}
