package org.example.enums;

import javafx.scene.image.Image;

public enum BuildingsImages {
    IronMine(new Image(BuildingsImages.class.getResource("/Image/" + "IronMine" + ".jpg").toExternalForm())),
//TODO
    StoneMine(new Image(BuildingsImages.class.getResource("/Image/" + "StoneMine" + ".jpg").toExternalForm())),
    WoodCutter(new Image(BuildingsImages.class.getResource("/Image/" + "WoodCutter" + ".jpg").toExternalForm())),
    OxTether(new Image(BuildingsImages.class.getResource("/Image/" + "OxTether" + ".jpg").toExternalForm())),
    StockPile(new Image(BuildingsImages.class.getResource("/Image/" + "StockPile" + ".jpg").toExternalForm())),
    armourer(new Image(BuildingsImages.class.getResource("/Image/" + "armourer" + ".jpg").toExternalForm())),
    blacksmith(new Image(BuildingsImages.class.getResource("/Image/" + "blacksmith" + ".jpg").toExternalForm())),
    Fletcher(new Image(BuildingsImages.class.getResource("/Image/" + "Fletcher" + ".jpg").toExternalForm())),
    Poleturner(new Image(BuildingsImages.class.getResource("/Image/" + "Poleturner" + ".jpg").toExternalForm())),
//    Step(new Image(BuildingsImages.class.getResource("/Image/" + "Step" + ".jpg").toExternalForm())),
    Wall(new Image(BuildingsImages.class.getResource("/Image/" + "Wall" + ".jpg").toExternalForm())),
    SmallStoneGatehouse(new Image(BuildingsImages.class.getResource("/Image/" + "SmallStoneGatehouse" + ".jpg").toExternalForm())),
    BigStoneGatehouse(new Image(BuildingsImages.class.getResource("/Image/" + "BigStoneGatehouse" + ".jpg").toExternalForm())),
    Drawbridge(new Image(BuildingsImages.class.getResource("/Image/" + "Drawbridge" + ".jpg").toExternalForm())),
    LookoutTower(new Image(BuildingsImages.class.getResource("/Image/" + "LookoutTower" + ".jpg").toExternalForm())),
//    PerimeterTower(new Image(BuildingsImages.class.getResource("/Image/" + "PerimeterTower" + ".jpg").toExternalForm())),
//    DefensiveTower(new Image(BuildingsImages.class.getResource("/Image/" + "DefensiveTower" + ".jpg").toExternalForm())),
//    SquareTower(new Image(BuildingsImages.class.getResource("/Image/" + "SquareTower" + ".jpg").toExternalForm())),
//    CircleTower(new Image(BuildingsImages.class.getResource("/Image/" + "CircleTower" + ".jpg").toExternalForm())),
    Armoury(new Image(BuildingsImages.class.getResource("/Image/" + "Armoury" + ".jpg").toExternalForm())),
    Barrack(new Image(BuildingsImages.class.getResource("/Image/" + "Barrack" + ".jpg").toExternalForm())),
    MercenaryPost(new Image(BuildingsImages.class.getResource("/Image/" + "MercenaryPost" + ".jpg").toExternalForm())),
    EngineerGuild(new Image(BuildingsImages.class.getResource("/Image/" + "EngineerGuild" + ".jpg").toExternalForm())),
    KillingPit(new Image(BuildingsImages.class.getResource("/Image/" + "KillingPit" + ".jpg").toExternalForm())),
//    OilSmelter(new Image(BuildingsImages.class.getResource("/Image/" + "OilSmelter" + ".jpg").toExternalForm())),
//    PitchDitch(new Image(BuildingsImages.class.getResource("/Image/" + "PitchDitch" + ".jpg").toExternalForm())),
    CagedWarDogs(new Image(BuildingsImages.class.getResource("/Image/" + "CagedWarDogs" + ".jpg").toExternalForm())),
//    SiegeTent(new Image(BuildingsImages.class.getResource("/Image/" + "SiegeTent" + ".jpg").toExternalForm())),
//    Stable(new Image(BuildingsImages.class.getResource("/Image/" + "Stable" + ".jpg").toExternalForm())),
//    //    //FoodFarm
//    Mill(new Image(BuildingsImages.class.getResource("/Image/" + "Mill" + ".jpg").toExternalForm())),
//    Baker(new Image(BuildingsImages.class.getResource("/Image/" + "Baker" + ".jpg").toExternalForm())),
//    Brewery(new Image(BuildingsImages.class.getResource("/Image/" + "Brewery" + ".jpg").toExternalForm())),
    Granery(new Image(BuildingsImages.class.getResource("/Image/" + "Granery" + ".jpg").toExternalForm())),
    AppleGarden(new Image(BuildingsImages.class.getResource("/Image/" + "AppleGarden" + ".jpg").toExternalForm())),
    DairyProducts(new Image(BuildingsImages.class.getResource("/Image/" + "DairyProducts" + ".jpg").toExternalForm())),
//    BarleyField(new Image(BuildingsImages.class.getResource("/Image/" + "BarleyField" + ".jpg").toExternalForm())),
    Hunt(new Image(BuildingsImages.class.getResource("/Image/" + "Hunt" + ".jpg").toExternalForm())),
//    WheatField(new Image(BuildingsImages.class.getResource("/Image/" + "WheatField" + ".jpg").toExternalForm())),
//    //    //TownBuilding
//    Inn(new Image(BuildingsImages.class.getResource("/Image/" + "Inn" + ".jpg").toExternalForm())),
    Hovel(new Image(BuildingsImages.class.getResource("/Image/" + "Hovel" + ".jpg").toExternalForm())),
    Church(new Image(BuildingsImages.class.getResource("/Image/" + "Church" + ".jpg").toExternalForm())),
    Cathedral(new Image(BuildingsImages.class.getResource("/Image/" + "Cathedral" + ".jpg").toExternalForm())),
    ;
    Image image;

    BuildingsImages(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
}
