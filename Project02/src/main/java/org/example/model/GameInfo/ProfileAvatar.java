package org.example.model.GameInfo;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import org.example.controller.CheckController;
import org.example.controller.LoginController;
import org.example.view.LoginMenuController;

import javax.net.ssl.SSLContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileAvatar extends Circle {
    private String number;
    public ProfileAvatar(int r, int x, int y, String number) {
        super(x, y, r);
        this.number=number;
        this.setFill(new ImagePattern(new Image(ProfileAvatar.class.getResource("/picture/background/profile backgrounds/Avatars/" + number + ".png").toExternalForm())));
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LoginController.getLogedInuser().setProfileAvatar(ProfileAvatar.this.number);
                System.out.println(number);
                LoginMenuController.paneAvatar.getChildren().remove(9);
                LoginMenuController.paneAvatar.getChildren().add(new ProfileAvatar(47,520,75,LoginController.getLogedInuser().getProfileAvatar()));
                System.out.println(ProfileAvatar.this.getCenterX());
            }
        });
    }
    public ProfileAvatar(int r, int x, int y,String url,int a) throws FileNotFoundException {
        super(x,y,r);
        InputStream stream = new FileInputStream(url);
        Image image = new Image(stream);
        this.setFill(new ImagePattern(image));
    }
}