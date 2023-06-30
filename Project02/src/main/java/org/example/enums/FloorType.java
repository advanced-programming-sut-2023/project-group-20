package org.example.enums;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public enum FloorType {
    FlatGround(new Image(FloorType.class.getResource("/Image/FlatGround.jpg").toExternalForm())),
//    GravelGround(new Image(FloorType.class.getResource("/Image/GravelGround.jpg").toExternalForm())),
    Rock(new Image(FloorType.class.getResource("/Image/Rock.jpg").toExternalForm())),
    StoneGround(new Image(FloorType.class.getResource("/Image/StoneGround.jpg").toExternalForm())),
    IronGround(new Image(FloorType.class.getResource("/Image/IronGround.jpg").toExternalForm())),
    Grass(new Image(FloorType.class.getResource("/Image/Grass.jpg").toExternalForm())),
//    Meadow(new Image(FloorType.class.getResource("/Image/Meadow.jpg").toExternalForm())),
//    FoolMeadow(new Image(FloorType.class.getResource("/Image/FoolMeadow.jpg").toExternalForm())),
//    Floor(new Image(FloorType.class.getResource("/Image/Floor.jpg").toExternalForm())),
//    Oil(new Image(FloorType.class.getResource("/Image/Oil.jpg").toExternalForm())),
//    Plain(new Image(FloorType.class.getResource("/Image/Plain.jpg").toExternalForm())),
//    DeapLessWater(new Image(FloorType.class.getResource("/Image/DeapLessWater.jpg").toExternalForm())),
//    River(new Image(FloorType.class.getResource("/Image/River.jpg").toExternalForm())),
//    SmallPond(new Image(FloorType.class.getResource("/Image/SmallPond.jpg").toExternalForm())),
//    BigPond(new Image(FloorType.class.getResource("/Image/BigPond.jpg").toExternalForm())),
    Beach(new Image(FloorType.class.getResource("/Image/Beach.jpg").toExternalForm())),
    Sea(new Image(FloorType.class.getResource("/Image/Sea.jpg").toExternalForm()))
    ;
    Image image;

    FloorType(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
