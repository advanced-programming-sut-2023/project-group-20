package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.controller.ShopController;
import org.example.model.DataBase;
import org.example.model.GameInfo.Good;

import java.io.IOException;
import java.net.URL;

public class ShopMenuController {
    public void shopping(Stage stage) throws IOException {

        URL url = LoginMenu.class.getResource("/FXML/Shop.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/14.jpg").toExternalForm(), 300, 200, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 60, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button weapon = new Button();
        weapon.setMinHeight(25);
        weapon.setText("weapons");
        weapon.setTextFill(Color.web("#FFFF00"));
        weapon.setLayoutY(150);
        weapon.setLayoutX(80);
        weapon.setMinWidth(60);
        weapon.setBackground(btnBackground);
        weapon.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Button materials = new Button();
        materials.setMinHeight(25);
        materials.setText("materials");
        materials.setTextFill(Color.web("#FFFF00"));
        materials.setLayoutY(150);
        materials.setLayoutX(150);
        materials.setMinWidth(60);
        materials.setBackground(btnBackground);
        materials.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
//        Button allGoods = new Button();
//        allGoods.setMinHeight(25);
//        allGoods.setText("allGoods");
//        allGoods.setTextFill(Color.web("#FFFF00"));
//        allGoods.setLayoutY(150);
//        allGoods.setLayoutX(220);
//        allGoods.setMinWidth(60);
//        allGoods.setBackground(btnBackground);
//        allGoods.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Label label = new Label("");
        label.setText("Market place");
        label.setLayoutX(100);
        label.setLayoutY(30);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 25px;");

        Button food = new Button();
        food.setMinHeight(25);
        food.setText("foods");
        food.setTextFill(Color.web("#FFFF00"));
        food.setLayoutY(150);
        food.setLayoutX(10);
        food.setMinWidth(60);
        food.setBackground(btnBackground);
        food.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        food.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(food, label, materials, weapon);
                food(pane, stage);
            }
        });
        weapon.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(food, label, materials, weapon);
                weapon(pane, stage);
            }
        });
        materials.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(food, label, materials, weapon);
                materials(pane,stage);
            }
        });
//        allGoods.setOnMouseClicked(new EventHandler() {
//            @Override
//            public void handle(Event event) {
//                pane.getChildren().removeAll(food, allGoods, label, materials, weapon);
//                allGoods(pane,stage);
//            }
//        });
        pane.getChildren().addAll(food, label, materials, weapon);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
    public void materials(Pane pane, Stage stage) {

        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 60, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button stone = new Button();
        stone.setMinHeight(25);
        stone.setText("stone");
        stone.setTextFill(Color.web("#FFFF00"));
        stone.setLayoutY(50);
        stone.setLayoutX(30);
        stone.setMinWidth(60);
        stone.setBackground(btnBackground);
        stone.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        stone.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 4);
                buyORsell("stone", 30, 20, pane, stage);
            }
        });
        Button wood = new Button();
        wood.setMinHeight(25);
        wood.setText("wood");
        wood.setTextFill(Color.web("#FFFF00"));
        wood.setLayoutY(50);
        wood.setLayoutX(120);
        wood.setMinWidth(60);
        wood.setBackground(btnBackground);
        wood.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        wood.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 4);
                buyORsell("wood", 30, 20, pane, stage);
            }
        });
        Button iron = new Button();
        iron.setMinHeight(25);
        iron.setText("iron");
        iron.setTextFill(Color.web("#FFFF00"));
        iron.setLayoutY(50);
        iron.setLayoutX(210);
        iron.setMinWidth(60);
        iron.setBackground(btnBackground);
        iron.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        iron.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 4);
                buyORsell("iron", 30, 20, pane, stage);
            }
        });
        Button pitch = new Button();
        pitch.setMinHeight(25);
        pitch.setText("pitch");
        pitch.setTextFill(Color.web("#FFFF00"));
        pitch.setLayoutY(150);
        pitch.setLayoutX(120);
        pitch.setMinWidth(60);
        pitch.setBackground(btnBackground);
        pitch.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        pitch.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                pane.getChildren().remove(0, 4);
                buyORsell("pitch", 30, 20, pane, stage);
            }
        });
        pane.getChildren().addAll( iron, wood, stone, pitch);
    }

    public void weapon(Pane pane, Stage stage) {

        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 60, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button Pike = new Button();
        Pike.setMinHeight(25);
        Pike.setText("Pike");
        Pike.setTextFill(Color.web("#FFFF00"));
        Pike.setLayoutY(50);
        Pike.setLayoutX(30);
        Pike.setMinWidth(60);
        Pike.setBackground(btnBackground);
        Pike.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Pike.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("Pike", 60, 40, pane, stage);
            }
        });
        Button Spear = new Button();
        Spear.setMinHeight(25);
        Spear.setText("Spear");
        Spear.setTextFill(Color.web("#FFFF00"));
        Spear.setLayoutY(50);
        Spear.setLayoutX(120);
        Spear.setMinWidth(60);
        Spear.setBackground(btnBackground);
        Spear.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Spear.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("Spear", 60, 40, pane, stage);
            }
        });
        Button Crossbow = new Button();
        Crossbow.setMinHeight(25);
        Crossbow.setText("Crossbow");
        Crossbow.setTextFill(Color.web("#FFFF00"));
        Crossbow.setLayoutY(50);
        Crossbow.setLayoutX(210);
        Crossbow.setMinWidth(60);
        Crossbow.setBackground(btnBackground);
        Crossbow.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Crossbow.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("Crossbow", 60, 50, pane, stage);
            }
        });
        Button Bow = new Button();
        Bow.setMinHeight(25);
        Bow.setText("Bow");
        Bow.setTextFill(Color.web("#FFFF00"));
        Bow.setLayoutY(100);
        Bow.setLayoutX(30);
        Bow.setMinWidth(60);
        Bow.setBackground(btnBackground);
        Bow.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Bow.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                pane.getChildren().remove(0, 8);
                buyORsell("Bow", 50, 40, pane, stage);
            }
        });
        Button MetalArmour = new Button();
        MetalArmour.setMinHeight(25);
        MetalArmour.setText("MetalArmour");
        MetalArmour.setTextFill(Color.web("#FFFF00"));
        MetalArmour.setLayoutY(100);
        MetalArmour.setLayoutX(120);
        MetalArmour.setMinWidth(60);
        MetalArmour.setBackground(btnBackground);
        MetalArmour.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        MetalArmour.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("MetalArmour", 60, 45, pane, stage);
            }
        });
        Button Sword = new Button();
        Sword.setMinHeight(25);
        Sword.setText("Sword");
        Sword.setTextFill(Color.web("#FFFF00"));
        Sword.setLayoutY(100);
        Sword.setLayoutX(210);
        Sword.setMinWidth(60);
        Sword.setBackground(btnBackground);
        Sword.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Sword.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("Sword", 60, 40, pane, stage);
            }
        });
        Button LeatherArmour = new Button();
        LeatherArmour.setMinHeight(25);
        LeatherArmour.setText("LeatherArmour");
        LeatherArmour.setTextFill(Color.web("#FFFF00"));
        LeatherArmour.setLayoutY(150);
        LeatherArmour.setLayoutX(30);
        LeatherArmour.setMinWidth(60);
        LeatherArmour.setBackground(btnBackground);
        LeatherArmour.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        LeatherArmour.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("LeatherArmour", 60, 40, pane, stage);
            }
        });
        Button Mace = new Button();
        Mace.setMinHeight(25);
        Mace.setText("Mace");
        Mace.setTextFill(Color.web("#FFFF00"));
        Mace.setLayoutY(150);
        Mace.setLayoutX(120);
        Mace.setMinWidth(60);
        Mace.setBackground(btnBackground);
        Mace.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Mace.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 8);
                buyORsell("Mace", 60, 55, pane, stage);
            }
        });
        pane.getChildren().addAll(Mace, Crossbow, Spear, MetalArmour, Pike, Bow, Sword, LeatherArmour);
    }

    public void food(Pane pane, Stage stage) {

        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 60, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button Apple = new Button();
        Apple.setMinHeight(25);
        Apple.setText("Apple");
        Apple.setTextFill(Color.web("#FFFF00"));
        Apple.setLayoutY(50);
        Apple.setLayoutX(30);
        Apple.setMinWidth(60);
        Apple.setBackground(btnBackground);
        Apple.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Apple.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 6);
                buyORsell("Apple", 10, 5, pane, stage);
            }
        });
        Button Meat = new Button();
        Meat.setMinHeight(25);
        Meat.setText("Meat");
        Meat.setTextFill(Color.web("#FFFF00"));
        Meat.setLayoutY(50);
        Meat.setLayoutX(120);
        Meat.setMinWidth(60);
        Meat.setBackground(btnBackground);
        Meat.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Meat.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 6);
                buyORsell("Meat", 30, 20, pane, stage);
            }
        });
        Button flour = new Button();
        flour.setMinHeight(25);
        flour.setText("flour");
        flour.setTextFill(Color.web("#FFFF00"));
        flour.setLayoutY(50);
        flour.setLayoutX(210);
        flour.setMinWidth(60);
        flour.setBackground(btnBackground);
        flour.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        flour.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 6);
                buyORsell("flour", 30, 20, pane, stage);
            }
        });
        Button wheat = new Button();
        wheat.setMinHeight(25);
        wheat.setText("wheat");
        wheat.setTextFill(Color.web("#FFFF00"));
        wheat.setLayoutY(150);
        wheat.setLayoutX(30);
        wheat.setMinWidth(60);
        wheat.setBackground(btnBackground);
        wheat.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        wheat.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {

                pane.getChildren().remove(0, 6);
                buyORsell("wheat", 30, 20, pane, stage);
            }
        });
        Button Bread = new Button();
        Bread.setMinHeight(25);
        Bread.setText("Bread");
        Bread.setTextFill(Color.web("#FFFF00"));
        Bread.setLayoutY(150);
        Bread.setLayoutX(120);
        Bread.setMinWidth(60);
        Bread.setBackground(btnBackground);
        Bread.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Bread.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 6);
                buyORsell("Bread", 20, 15, pane, stage);
            }
        });
        Button Cheese = new Button();
        Cheese.setMinHeight(25);
        Cheese.setText("Cheese");
        Cheese.setTextFill(Color.web("#FFFF00"));
        Cheese.setLayoutY(150);
        Cheese.setLayoutX(210);
        Cheese.setMinWidth(60);
        Cheese.setBackground(btnBackground);
        Cheese.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Cheese.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().remove(0, 6);
                buyORsell("Cheese", 15, 10, pane, stage);
            }
        });
        pane.getChildren().addAll(Cheese, flour, Meat, Bread, Apple, wheat);
    }


    public void buyORsell(String name, int buy, int sell, Pane pane, Stage stage) {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 75, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Label label = new Label();
        label.setText(name);
        label.setLayoutX(100);
        label.setLayoutY(30);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 25px;");
        Button buyButton = new Button();
        buyButton.setMinHeight(25);
        buyButton.setText("buy for " + buy + "$");
        buyButton.setTextFill(Color.web("#FFFF00"));
        buyButton.setLayoutY(100);
        buyButton.setLayoutX(50);
        buyButton.setMinWidth(75);
        buyButton.setBackground(btnBackground);
        buyButton.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        buyButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                ShopController shopController = new ShopController(FloorController.gameMenu.getGameController().getCurrentGovernment());
                String res = shopController.buy2(name, 1.0);
                if (res.equals("The item purchased")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("The item purchased");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(res);
                    alert.showAndWait();
                }
            }
        });
        Button sellButton = new Button();
        sellButton.setMinHeight(25);
        sellButton.setText("sell for " + sell + "$");
        sellButton.setTextFill(Color.web("#FFFF00"));
        sellButton.setLayoutY(100);
        sellButton.setLayoutX(190);
        sellButton.setMinWidth(75);
        sellButton.setBackground(btnBackground);
        sellButton.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        sellButton.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                ShopController shopController = new ShopController(FloorController.gameMenu.getGameController().getCurrentGovernment());
                String res = shopController.sell2(name, 1.0);
                if (res.equals("The item was sold")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("The item was sold");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(res);
                    alert.showAndWait();
                }
            }
        });
        Button Back = new Button();
        Back.setMinHeight(25);
        Back.setText("back");
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(170);
        Back.setLayoutX(120);
        Back.setMinWidth(75);
        Back.setBackground(btnBackground);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(Back, buyButton, sellButton, label);
                try {
                    shopping(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("cdxrg");
        pane.getChildren().addAll(Back, buyButton, sellButton, label);
    }
}
