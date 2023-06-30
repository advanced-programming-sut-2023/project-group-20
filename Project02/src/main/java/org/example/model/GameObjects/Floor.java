package org.example.model.GameObjects;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.example.model.GameInfo.Home;
import org.example.view.FloorController;

public class Floor extends Rectangle {
    private ImagePattern imagePattern = new ImagePattern(new Image(Floor.class.getResource("/Image/14.jpg").toExternalForm()));
    private final Home home;
    private boolean isFloorSelected = false;
    private Star star;
    public static int floorSize = 75;
    private ImageView building;
    private Fire fire;

    public Floor(int x, int y, Home home) {
        super(x, y, floorSize, floorSize);
        this.setFill(imagePattern);
        this.home = home;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ImageView imageView = new ImageView(imagePattern.getImage());
                if (event.getClickCount() == 2) {
                    isFloorSelected = !isFloorSelected;
                    if (isFloorSelected)
                        FloorController.addSelectedFloors(getTheFloor());
                    else
                        FloorController.removeSelectedFloors(getTheFloor());
                }
                setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        FloorController.showFloorDetails(getTheFloor());
                    }
                });
            }
        });

    }

    public ImagePattern getImagePattern() {
        return imagePattern;
    }

    public void setImagePattern(ImagePattern imagePattern) {
        this.imagePattern = imagePattern;
        this.setFill(imagePattern);
    }

    public Home getHome() {
        return home;
    }

    public void setStar(Star star) {
        this.star = star;
    }

    public Star getStar() {
        return star;
    }

    public Floor getTheFloor() {
        return this;
    }

    public void setBuilding(ImageView building) {
        this.building = building;
    }

    public ImageView getBuilding() {
        return building;
    }

    public Fire getFire() {
        return fire;
    }

    public void setFire(Fire fire) {
        this.fire = fire;
    }
}
