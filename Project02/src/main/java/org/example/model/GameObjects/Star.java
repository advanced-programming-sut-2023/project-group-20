package org.example.model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Star extends Circle {
    private ImagePattern imagePattern = new ImagePattern(new Image(Floor.class.getResource("/Image/star.jpg").toExternalForm()));
    public static int starSize = 13;

    public Star(double x, double y) {
        super(x, y, starSize);
        this.setFill(imagePattern);
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern = imagePattern;
        this.setFill(imagePattern);
    }
}
