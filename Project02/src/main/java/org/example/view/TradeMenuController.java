package org.example.view;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.example.controller.TradeController;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;

import static org.example.view.Menu.isMatched;

public class TradeMenuController {
    public void trading(Stage stage) throws IOException {
        URL url = LoginMenu.class.getResource("/FXML/Trade.fxml");
        Pane pane = FXMLLoader.load(url);
        Image image = new Image(SignupMenu.class.getResource("/picture/background/backgrounds/14.jpg").toExternalForm(), 300, 200, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 100, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Button trade = new Button();
        trade.setMinHeight(25);
        trade.setText("trade");
        trade.setTextFill(Color.web("#FFFF00"));
        trade.setLayoutY(70);
        trade.setLayoutX(30);
        trade.setMinWidth(100);
        trade.setBackground(btnBackground);
        trade.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Button tradeList = new Button();
        tradeList.setMinHeight(25);
        tradeList.setText("tradeList");
        tradeList.setTextFill(Color.web("#FFFF00"));
        tradeList.setLayoutY(70);
        tradeList.setLayoutX(170);
        tradeList.setMinWidth(100);
        tradeList.setBackground(btnBackground);
        tradeList.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Label label = new Label();
        label.setText("Trade");
        label.setLayoutX(100);
        label.setLayoutY(30);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 25px;");

        Button tradeHistory = new Button();
        tradeHistory.setMinHeight(25);
        tradeHistory.setText("tradeHistory");
        tradeHistory.setTextFill(Color.web("#FFFF00"));
        tradeHistory.setLayoutY(120);
        tradeHistory.setLayoutX(30);
        tradeHistory.setMinWidth(100);
        tradeHistory.setBackground(btnBackground);
        tradeHistory.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Button tradeAccept = new Button();
        tradeAccept.setMinHeight(25);
        tradeAccept.setText("tradeAccept");
        tradeAccept.setTextFill(Color.web("#FFFF00"));
        tradeAccept.setLayoutY(120);
        tradeAccept.setLayoutX(170);
        tradeAccept.setMinWidth(100);
        tradeAccept.setBackground(btnBackground);
        tradeAccept.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        tradeAccept.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(tradeAccept, label, tradeList, trade,tradeHistory);
                tradeAccept(pane, stage);
            }
        });
        tradeHistory.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(tradeAccept, label, tradeList, trade,tradeHistory);
                tradeHistory(pane, stage);
            }
        });
        trade.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(tradeAccept, label, tradeList, trade,tradeHistory);
                trade(pane, stage);
            }
        });
        tradeList.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(tradeAccept, label, tradeList, trade,tradeHistory);
                tradeList(pane,stage);
            }
        });
        pane.getChildren().addAll(tradeAccept, label, tradeList, trade,tradeHistory);
        pane.setBackground(background);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void tradeAccept(Pane pane, Stage stage) {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 100, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        TradeController tradeController = new TradeController(FloorController.gameMenu.getGameController().getCurrentGovernment(), FloorController.gameMenu.getGameController().getCurrentGame());
        Label label = new Label();
        label.setText(tradeController.tradeList());
        label.setLayoutX(30);
        label.setLayoutY(50);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 13px;");
        TextField message = new TextField();
        message.setPromptText("message");
        message.setLayoutY(130);
        message.setLayoutX(30);
        message.setMinWidth(100);
        message.setBackground(btnBackground);
        message.setMinHeight(25);
        message.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        TextField priseField = new TextField();
        priseField.setPromptText("trade id");
        priseField.setLayoutY(130);
        priseField.setLayoutX(170);
        priseField.setMinWidth(100);
        priseField.setBackground(btnBackground);
        priseField.setMinHeight(25);
        priseField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Button trade = new Button();
        trade.setMinHeight(25);
        trade.setText("accept");
        trade.setTextFill(Color.web("#FFFF00"));
        trade.setLayoutY(160);
        trade.setLayoutX(30);
        trade.setMinWidth(100);
        trade.setBackground(btnBackground);
        trade.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        pane.getChildren().addAll(trade,priseField,message);
        trade.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    int price = Integer.parseInt(priseField.getText());
                    String msg = message.getText();
                    TradeController tradeController = new TradeController(FloorController.gameMenu.getGameController().getCurrentGovernment(), FloorController.gameMenu.getGameController().getCurrentGame());
                    Matcher matcher;
                    String command = "trade accept -i "+price+ " -m " + msg;
                    matcher = isMatched(command, "^trade accept(((?: -i (?<id>\\S+))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}");
                    String res=tradeController.tradeAccept(matcher);
                    if (res.equals("Trade done!")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Trade accepted!");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(res);
                        alert.showAndWait();
                    }


                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid id");
                    alert.showAndWait();
                }
            }

        });
        Button Back = new Button();
        Back.setMinHeight(25);
        Back.setText("back");
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(160);
        Back.setLayoutX(170);
        Back.setMinWidth(100);
        Back.setBackground(btnBackground);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(Back, trade,priseField,message);
                try {
                    trading(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(Back);
    }

    public void tradeHistory(Pane pane,Stage stage){
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 100, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        TradeController tradeController = new TradeController(FloorController.gameMenu.getGameController().getCurrentGovernment(), FloorController.gameMenu.getGameController().getCurrentGame());
        Label label = new Label();
        label.setText(tradeController.tradeList());
        label.setLayoutX(50);
        label.setLayoutY(50);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 15px;");
        Button Back = new Button();
        Back.setMinHeight(25);
        Back.setText("back");
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(170);
        Back.setLayoutX(160);
        Back.setMinWidth(100);
        Back.setBackground(btnBackground);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        pane.getChildren().addAll(Back,label);
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(Back, label);
                try {
                    trading(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void tradeList(Pane pane,Stage stage){
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 100, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        TradeController tradeController = new TradeController(FloorController.gameMenu.getGameController().getCurrentGovernment(), FloorController.gameMenu.getGameController().getCurrentGame());
        Label label = new Label();
        label.setText(tradeController.tradeList());
        label.setLayoutX(50);
        label.setLayoutY(50);
        label.setTextFill(Color.web("#FFFF00"));
        label.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 15px;");
        Button Back = new Button();
        Back.setMinHeight(25);
        Back.setText("back");
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(170);
        Back.setLayoutX(160);
        Back.setMinWidth(100);
        Back.setBackground(btnBackground);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        pane.getChildren().addAll(Back,label);
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(Back, label);
                try {
                    trading(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void trade(Pane pane, Stage stage) {
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
        Label label = new Label("");
        label.setText("Choose trade type");
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
                materials(pane, stage);
            }
        });
        pane.getChildren().addAll(food, label, materials, weapon);
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
                trade2("stone", pane, stage);
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
                trade2("wood", pane, stage);
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
                trade2("iron", pane, stage);
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
                trade2("pitch", pane, stage);
            }
        });
        pane.getChildren().addAll(iron, wood, stone, pitch);
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
                trade2("Pike", pane, stage);
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
                trade2("Spear", pane, stage);
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
                trade2("Crossbow", pane, stage);
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
                trade2("Bow", pane, stage);
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
                trade2("MetalArmour", pane, stage);
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
                trade2("Sword", pane, stage);
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
                trade2("LeatherArmour", pane, stage);
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
                trade2("Mace",  pane, stage);
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
                trade2("Apple", pane, stage);
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
                trade2("Meat", pane, stage);
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
                trade2("flour", pane, stage);
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
                trade2("wheat", pane, stage);
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
                trade2("Bread", pane, stage);
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
                trade2("Cheese", pane, stage);
            }
        });
        pane.getChildren().addAll(Cheese, flour, Meat, Bread, Apple, wheat);
    }

    public void trade2(String type, Pane pane, Stage stage) {
        Image image2 = new Image(LoginMenu.class.getResource("/picture/Others/buttons/btn/btn.jpg").toExternalForm(), 100, 25, false, false);
        BackgroundImage backgroundImage2 = new BackgroundImage(image2,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background btnBackground = new Background(backgroundImage2);
        Label lable = new Label(type);
        lable.setLayoutX(120);
        lable.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 20px;");
        lable.setLayoutY(20);
        lable.setTextFill(Color.web("#ee1257"));
        TextField username = new TextField();
        username.setPromptText("amount");
        username.setLayoutY(70);
        username.setLayoutX(30);
        username.setMinWidth(100);
        username.setBackground(btnBackground);
        username.setMinHeight(25);
        username.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        TextField message = new TextField();
        message.setPromptText("message");
        message.setLayoutY(120);
        message.setLayoutX(100);
        message.setMinWidth(100);
        message.setBackground(btnBackground);
        message.setMinHeight(25);
        message.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        TextField priseField = new TextField();
        priseField.setPromptText("price");
        priseField.setLayoutY(70);
        priseField.setLayoutX(170);
        priseField.setMinWidth(100);
        priseField.setBackground(btnBackground);
        priseField.setMinHeight(25);
        priseField.setStyle("-fx-text-fill: #33FFFC;-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Button trade = new Button();
        trade.setMinHeight(25);
        trade.setText("trade");
        trade.setTextFill(Color.web("#FFFF00"));
        trade.setLayoutY(160);
        trade.setLayoutX(30);
        trade.setMinWidth(100);
        trade.setBackground(btnBackground);
        trade.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        pane.getChildren().addAll(trade,priseField,username,message,lable);
        trade.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    int amount = Integer.parseInt(username.getText());
                    int price = Integer.parseInt(priseField.getText());
                    String msg = message.getText();

                    TradeController tradeController = new TradeController(FloorController.gameMenu.getGameController().getCurrentGovernment(), FloorController.gameMenu.getGameController().getCurrentGame());
                    Matcher matcher;
                    String command = "trade -a " + amount + " -p " + price + " -t " + type + " -m " + msg;
                    matcher = isMatched(command, "^trade(((?: -a (?<amount>\\S+))|(?: -p (?<price>\\S+))|(?: -t (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,4}$");
                    String res=tradeController.trade(matcher);
                    if (res.equals("Your request or donation has been created.")){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("Your request or donation has been created.");
                        alert.showAndWait();
                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText(res);
                        alert.showAndWait();
                    }


                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("invalid prise or amount");
                    alert.showAndWait();
                }
            }

        });
        Button Back = new Button();
        Back.setMinHeight(25);
        Back.setText("back");
        Back.setTextFill(Color.web("#FFFF00"));
        Back.setLayoutY(160);
        Back.setLayoutX(170);
        Back.setMinWidth(100);
        Back.setBackground(btnBackground);
        Back.setStyle("-fx-font-style: italic;-fx-font-weight: bold; -fx-font-size: 11px;");
        Back.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                pane.getChildren().removeAll(Back, trade,priseField,username,message,lable);
                try {
                    trading(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        pane.getChildren().add(Back);
    }
}
