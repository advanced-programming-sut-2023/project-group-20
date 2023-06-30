package org.example.model.GameObjects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Fire extends Rectangle {
    ImagePattern imagePattern = new ImagePattern(new Image(this.getClass().getResource("/Image/Fire0.jpg").toExternalForm()));
    public static int fireSize = 80;

    public Fire(double x, double y) {
        super(x, y, fireSize, fireSize);
        this.setFill(imagePattern);
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern = imagePattern;
        this.setFill(imagePattern);
    }
}
