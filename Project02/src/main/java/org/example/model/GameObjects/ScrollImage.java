package org.example.model.GameObjects;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.view.FloorController;

public class ScrollImage extends Rectangle {
    public static int scrollImageSize = 40;
    private ImagePattern imagePattern;
    private String type;
    private String name;

    public ScrollImage(int x, int y, Image image, String type, String name) {
        super(x, y, scrollImageSize, scrollImageSize);
        this.imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
        setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() < 2)
                    return;
                if (type.equals("Building")) {
                    FloorController.dropBuilding(name, image);
                } else if (type.equals("Troop")) {
                    FloorController.dropUnit(name);
                } else if (type.equals("EngineBuilding")) {

                }
            }
        });
    }

    public void setImagePattern(Image image) {
        this.imagePattern = new ImagePattern(image);
        this.setFill(imagePattern);
    }
}
