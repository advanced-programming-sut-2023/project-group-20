package org.example.enums;

import javafx.scene.image.Image;

import java.util.ArrayList;

public enum TroopImages {
    Archer(new Image(TroopImages.class.getResource("/Image/"+"Archer"+".jpg").toExternalForm())),
//TODO
    CrossbowMen(new Image(TroopImages.class.getResource("/Image/"+"CrossbowMen"+".jpg").toExternalForm())),
    SpearMen(new Image(TroopImages.class.getResource("/Image/"+"SpearMen"+".jpg").toExternalForm())),
    PikeMen(new Image(TroopImages.class.getResource("/Image/"+"PikeMen"+".jpg").toExternalForm())),
    MaceMen(new Image(TroopImages.class.getResource("/Image/"+"MaceMen"+".jpg").toExternalForm())),
//    SwordsMen(new Image(TroopImages.class.getResource("/Image/"+"SwordsMen"+".jpg").toExternalForm())),
    Knight(new Image(TroopImages.class.getResource("/Image/"+"Knight"+".jpg").toExternalForm())),
    Tunneler(new Image(TroopImages.class.getResource("/Image/"+"Tunneler"+".jpg").toExternalForm())),
//    LadderMen(new Image(TroopImages.class.getResource("/Image/"+"LadderMen"+".jpg").toExternalForm())),
    Engineer(new Image(TroopImages.class.getResource("/Image/"+"Engineer"+".jpg").toExternalForm())),
//    BlackMonk(new Image(TroopImages.class.getResource("/Image/"+"BlackMonk"+".jpg").toExternalForm())),
    ArabianBow(new Image(TroopImages.class.getResource("/Image/"+"ArabianBow"+".jpg").toExternalForm())),
    Slaves(new Image(TroopImages.class.getResource("/Image/"+"Slaves"+".jpg").toExternalForm())),
//    Slingers(new Image(TroopImages.class.getResource("/Image/"+"Slingers"+".jpg").toExternalForm())),
    Assassins(new Image(TroopImages.class.getResource("/Image/"+"Assassins"+".jpg").toExternalForm())),
//    HorseArchers(new Image(TroopImages.class.getResource("/Image/"+"HorseArchers"+".jpg").toExternalForm())),
//    ArabianSwordsMen(new Image(TroopImages.class.getResource("/Image/"+"ArabianSwordsMen"+".jpg").toExternalForm())),
    FireThrowers(new Image(TroopImages.class.getResource("/Image/"+"FireThrowers"+".jpg").toExternalForm())),
    ;
    Image image;

    TroopImages(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
