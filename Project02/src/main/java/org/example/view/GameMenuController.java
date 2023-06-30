package org.example.view;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import org.example.enums.FloorType;
import org.example.model.GameInfo.Home;
import org.example.model.GameObjects.Floor;

public class GameMenuController {
    public static Floor makeAFloor(Home home) {
        Image image = getTheFloorImageByName(home.getTypeOfFloor());
        if (image == null)
            return null;
        Floor floor = new Floor(0, 0, home);
        ImagePattern imagePattern = new ImagePattern(image);
        double floorImageXPos = (home.getX() - 1) * floor.getWidth();
        double floorImageYPos = (home.getY() - 1) * floor.getHeight();
        floor.setX(floorImageXPos+10);
        floor.setY(floorImageYPos+10);
        floor.setImagePattern(imagePattern);
        return floor;
    }

    private static Image getTheFloorImageByName(String floorType) {
        for (int i = 0; i < FloorType.values().length; i++) {
            if (floorType.equals(FloorType.values()[i].name()))
                return FloorType.values()[i].getImage();
        }
        return null;
    }

}
