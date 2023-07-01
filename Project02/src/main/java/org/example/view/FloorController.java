package org.example.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.controller.MapController;
import org.example.controller.TradeController;
import org.example.controller.UnitMotionController;
import org.example.enums.BuildingsImages;
import org.example.enums.TroopImages;
import org.example.model.DataBase;
import org.example.model.GameInfo.Building;
import org.example.model.GameInfo.Home;
import org.example.model.GameInfo.Troop;
import org.example.model.GameObjects.Fire;
import org.example.model.GameObjects.Floor;
import org.example.model.GameObjects.ScrollImage;
import org.example.model.GameObjects.Star;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class FloorController {
    private static ArrayList<Floor> selectedFloors = new ArrayList<>();
    public static GameMenu gameMenu;
    private static Stage stage;
    private static UnitMotionController unitMotionController;
    public TextField count;
    public TextField troopType;
    public TextField floorType;

//    public FloorController(GameMenu gameMenu) {
//        this.gameMenu = gameMenu;
//    }

    public static void addSelectedFloors(Floor selectedFloors) {
        FloorController.selectedFloors.add(selectedFloors);
        addStar(selectedFloors);
        if (FloorController.selectedFloors.size() == 2) {
            makeACommandStage("FloorCommand.fxml");
        } else if (FloorController.selectedFloors.size() == 1) {
            makeACommandStage("onClickCommand.fxml");
        }
    }

    private static void addStar(Floor floor) {
        if (selectedFloors.get(0).getStar() != null)
            selectedFloors.get(0).getStar().setRadius(0);
        Star star = new Star(floor.getX(), floor.getY());
        GameMenu.pane.getChildren().add(star);
        floor.setStar(star);
    }

    private static void addStar(Floor floor, ImagePattern imagePattern, double plusX, double plusY) {
        Star star = new Star(floor.getX() + plusX, floor.getY() + plusY);
        star.setImagePattern(imagePattern);
        GameMenu.pane.getChildren().add(star);
        floor.setStar(star);
    }

    private static void makeACommandStage(String path) {
        Stage commandStage = new Stage();
        stage = commandStage;
        URL url = FloorController.class.getResource("/FXML/" + path);
        Pane pane = null;

        try {
            pane = FXMLLoader.load(url);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("__________________");
        }
        commandStage.setScene(new Scene(pane));
        commandStage.show();
    }


    public static void removeSelectedFloors(Floor selectedFloors) {
        FloorController.selectedFloors.remove(selectedFloors);
        deleteStar(selectedFloors);
    }

    private static void deleteStar(Floor floor) {
        Star star = floor.getStar();
        if (star != null)
            star.setRadius(0);
        floor.setStar(star);
    }

    public static void showFloorDetails(Floor floor) {
        Stage stage = new Stage();
        Pane pane = new Pane();
        Home home = floor.getHome();
        Text text = new Text();
        text.setLayoutY(20);
        String details = aHomeDet(home);
        text.setText(details);
        pane.getChildren().add(text);
        stage.setScene(new Scene(pane));
        stage.show();
    }

    private static String aHomeDet(Home home) {
        String details = "";
        details += "Name : " + home.getTypeOfFloor() + " \n";
        details += "Owner : " + home.getOwner().getOwner().getNickname() + " \n";
        if (home.getBuilding() != null) {
            details += "Building : \n" + "Type : " + home.getBuilding().getType() + " \n";
            details += "Hitpoint : " + home.getBuilding().getHitpoint() + " \n";
        }
        details += "Troops : \n";
        if (home.getTroops() != null) {
            for (int i = 0; i < home.getTroops().size(); i++) {
                details += "Type : " + home.getTroops().get(i).getType() + " \n";
                details += "State : " + home.getTroops().get(i).getState() + " \n";
                details += "Power : " + home.getTroops().get(i).getPower() + " \n";
                details += "Speed : " + home.getTroops().get(i).getSpeed() + " \n";
                details += "___________________________ \n";
            }
            if (home.getTroops().size() > 0)
                details += "The total speed : " + gameMenu.getGameController().getTheMinimumSpeedOfTroops(home.getTroops()) + "\n";
        }
        return details;
    }

    public static void reset() {
        for (int i = 0; i < selectedFloors.size(); i++) {
            deleteStar(selectedFloors.get(i));
        }
        selectedFloors = new ArrayList<>();
        if (stage != null) {
            stage.close();
            stage = null;
        }
    }

    public void move() {
        unitMotionController = new UnitMotionController(gameMenu.getGameController().getCurrentGame().getMap(), gameMenu.getGameController().getCurrentGame());
        if (gameMenu.getGameController().getCurrentGame().getSelectedTroops() == null)
            DataBase.showAlert("Not selected Troop !!");
        else {
            boolean[] check = new boolean[1];
            Home first = selectedFloors.get(0).getHome();
            Home tar = selectedFloors.get(1).getHome();
            String out = gameMenu.getGameController().tryMoveUnit(first, first.getX(), first.getY(), tar.getX(), tar.getY(), check);
            if (check[0]) {
                moveAnimation(first.getX(), first.getY(), tar.getX(), tar.getY());
                first.getFloor().getTroop().setX(tar.getFloor().getX());
                first.getFloor().getTroop().setY(tar.getFloor().getY());
                tar.getFloor().setTroop(first.getFloor().getTroop());
                first.getFloor().setTroop(null);
                gameMenu.getGameController().resetSelectedTroopsFromGame();
            } else
                DataBase.showAlert(out);
        }
        reset();
    }

    private void moveAnimation(int firstX, int firstY, int tarX, int tarY) {
//        addStar(selectedFloors.get(0), new ImagePattern(DataBase.getTankImage()), 0, 0);
        int speed = gameMenu.getGameController().getTheMinimumSpeedOfTroops(gameMenu.getGameController().getCurrentGame().getSelectedTroops());
        unitMotionController = new UnitMotionController(gameMenu.getGameController().getCurrentGame().getMap(), gameMenu.getGameController().getCurrentGame());
        ArrayList<Home> race = unitMotionController.findTheDestinationRace(speed, firstX, firstY, tarX, tarY);
        int[] i = new int[1];
        i[0] = 1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(6.5 / speed), actionEvent -> movingTankImage(race, i)));
        timeline.setCycleCount(race.size() - 1);
        timeline.play();
    }

    private void movingTankImage(ArrayList<Home> race, int[] i) {
        Floor currentFloor = race.get(i[0]).getFloor();
        Floor pastFloor = race.get(i[0] - 1).getFloor();
        addStar(currentFloor, new ImagePattern(DataBase.getTankImage()), 0, 0);
        if (pastFloor.getStar() != null)
            pastFloor.getStar().setRadius(0);
        if (i[0] == race.size() - 1)
            troopEndMoveAnimation(race.get(race.size() - 1).getFloor(), race.get(0).getFloor());
        i[0]++;
    }

    private void troopEndMoveAnimation(Floor floor, Floor start) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.5), actionEvent -> visibleAndNot(floor)));
        timeline.setCycleCount(5);
        timeline.play();
        start.getStar().setRadius(0);
        System.out.println(start.getStar().getCenterX());
    }

    private void visibleAndNot(Floor floor) {
        if (floor.getStar().getRadius() == 0)
            floor.getStar().setRadius(Star.starSize);
        else
            floor.getStar().setRadius(0);
    }

    public void attack() {
        if (gameMenu.getGameController().getCurrentGame().getSelectedTroops() == null)
            DataBase.showAlert("Not selected Troop !!");
        else {
            boolean[] check = new boolean[1];
            Home tar = selectedFloors.get(1).getHome();
            String out = gameMenu.getGameController().tryToAttack(tar.getX(), tar.getY(), gameMenu.getGameController().getCurrentGame().getSelectedTroops(), check);
            if (check[0]) {
                Home first = selectedFloors.get(0).getHome();
                moveAnimation(first.getX(), first.getY(), tar.getX(), tar.getY());
                attackBaner();
                if (isAnyFireThrower(gameMenu.getGameController().getCurrentGame().getSelectedTroops()))
                    FireAnimation(tar);
                if (tar.getFloor().getBuilding() != null && tar.getBuilding() == null) {
                    removeImage(tar.getFloor().getBuilding());
                }
            } else {
                DataBase.showAlert(out);
            }
        }
        reset();
    }

    private void FireAnimation(Home tar) {
        int[] count = new int[1];
        count[0] = 0;
        Fire fire = new Fire(tar.getFloor().getX(), tar.getFloor().getY());
        tar.getFloor().setFire(fire);
        gameMenu.pane.getChildren().add(fire);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(.3), actionEvent -> firing(count, fire)));
        timeline.setCycleCount(30);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("END");
                tar.getFloor().getFire().setHeight(0);
                tar.getFloor().getFire().setWidth(0);
            }
        });
        timeline.play();
    }

    private void firing(int[] count, Fire fire) {
        fire.setImagePattern(new ImagePattern(new Image(this.getClass().getResource("/Image/Fire" + (count[0] % 4) + ".jpg").toExternalForm())));
        count[0]++;
    }

    private boolean isAnyFireThrower(ArrayList<Troop> selectedTroops) {
        for (int i = 0; i < selectedTroops.size(); i++) {
            if (selectedTroops.get(i).getType().equals("FireThrowers"))
                return true;
        }
        return false;
    }

    private void attackBaner() {
        Label label = new Label();
        GameMenu.pane.getChildren().add(label);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), actionEvent -> showAttackBaner(label)));
        timeline.setCycleCount(10);
        timeline.play();
    }

    private void showAttackBaner(Label text) {
        if (text.getText().equals("")) {
            text.setText("We run attack");
            text.setTextFill(Color.web("#ee1257"));
        } else
            text.setText("");
    }

    public void selectTroops(MouseEvent mouseEvent) {
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryToSelectUnit(selectedFloors.get(0).getHome().getX(), selectedFloors.get(0).getHome().getY(), check);
        Star star = selectedFloors.get(0).getStar();
        if (check[0]) {
            addStar(selectedFloors.get(0), new ImagePattern(new Image(this.getClass().getResource("/Image/selectedTroops.jpg").toExternalForm())), selectedFloors.get(0).getWidth() / 2, 0);
//            selectedFloors.get(0).setStar(star);
        } else {
            DataBase.showAlert(out);
            reset();
            return;
        }
//        selectedFloors.get(0).getStar().setRadius(0);
        star.setRadius(0);
        DataBase.showAlert(out);
        stage.close();
//        reset();
    }

    public void selectFloors(MouseEvent mouseEvent) {
        try {
            Home first = selectedFloors.get(0).getHome();
            Home tar = selectedFloors.get(1).getHome();
            setSelectedFloors(first, tar);
            DataBase.showAlert("These are your selected floors info");
        } catch (Exception e) {

        }
        reset();
    }

    private void setSelectedFloors(Home first, Home tar) {
        selectedFloors.get(1).getStar().setRadius(0);
        int minX = first.getX();
        int minY = first.getY();
        int maxX = tar.getX();
        int maxY = tar.getY();
        if (minX > maxX) {
            int temp = maxX;
            maxX = minX;
            minX = temp;
        }
        if (minY > maxY) {
            int temp = maxY;
            maxY = minY;
            minY = temp;
        }
        MapController mapController = new MapController(gameMenu.getGameController().getCurrentGame().getMap(), gameMenu.getGameController().getCurrentGame());
        StringBuilder det = new StringBuilder();
        try {
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    addStar(mapController.getHomeByPosition(i, j).getFloor());
                    selectedFloors.add(mapController.getHomeByPosition(i, j).getFloor());
                    det.append(aHomeDet(mapController.getHomeByPosition(i, j)));
                }
            }
        } catch (Exception e) {

        }

        ScrollPane scrollPane = new ScrollPane();
        Label info = new Label();
        System.out.println("det : " + det);
        info.setTextFill(Color.BLUE);
        info.setText(det.toString());
        scrollPane.setContent(info);
        stage.setScene(new Scene(scrollPane));
        stage.show();
    }

    public void changeFloorType() {
        if (floorType == null) {
            DataBase.showAlert("please enter type first");
            reset();
            return;
        }
        String type = floorType.getText();
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryToSetTexture(selectedFloors.get(0).getHome().getX(), selectedFloors.get(0).getHome().getY(), type, check);
        if (!check[0])
            DataBase.showAlert(out);
        else {
            System.out.println(selectedFloors.get(0).getHome().getTypeOfFloor());
            ImagePattern imageNew = GameMenuController.makeAFloor(selectedFloors.get(0).getHome()).getImagePattern();
            selectedFloors.get(0).setImagePattern(imageNew);
        }
        reset();
    }

    public String changeFloorType(String type) {
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryToSetTexture(selectedFloors.get(0).getHome().getX(), selectedFloors.get(0).getHome().getY(), type, check);
        if (!check[0])
            return out;
        else {
            System.out.println(selectedFloors.get(0).getHome().getTypeOfFloor());
            ImagePattern imageNew = GameMenuController.makeAFloor(selectedFloors.get(0).getHome()).getImagePattern();
            selectedFloors.get(0).setImagePattern(imageNew);
            return out;
        }
    }

    public void selectBuilding(MouseEvent mouseEvent) {
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryToSelectBuilding(selectedFloors.get(0).getHome().getX(), selectedFloors.get(0).getHome().getY(), check);
        Star star = selectedFloors.get(0).getStar();
        if (check[0]) {
            Floor selectFloor = selectedFloors.get(0);
            addStar(selectedFloors.get(0), new ImagePattern(new Image(this.getClass().getResource("/Image/selectedBuilding.jpg").toExternalForm())), 0, selectedFloors.get(0).getHeight() / 2);
            DataBase.showAlert(out);
            reset();
            selectedFloors.add(selectFloor);
            selectedFloors.get(0).setStar(star);
            reset();
        } else {
            DataBase.showAlert(out);
            reset();
        }

    }

    public void createUnit(MouseEvent mouseEvent) {
        String output = "";
        int i;
        Integer count;
        try {
            count = Integer.parseInt(this.count.getText());
        } catch (Exception e) {
            DataBase.showAlert("Please enter true number");
            reset();
            return;
        }
        String troopName = this.troopType.getText();

        boolean[] check = new boolean[1];
        for (i = 0; i < count && i < gameMenu.getGameController().getCurrentGovernment().getPopulation(); i++) {
            output = gameMenu.getGameController().tryToCreateUnit(gameMenu.getGameController().getCurrentGame().getSelectedBuildingHome(), troopName, check);
            if (!check[0])
                break;
        }
        DataBase.showAlert(output);
        reset();
    }

    public static void dropUnit(String name, Image image) {
        boolean[] check = new boolean[1];
        String output = gameMenu.getGameController().tryToCreateUnit(gameMenu.getGameController().getCurrentGame().getSelectedBuildingHome(), name, check);
        DataBase.showAlert(output);
        if (check[0]) {
            Home home = gameMenu.getGameController().getCurrentGame().getSelectedBuildingHome();
            ImageView imageView = new ImageView(image);
            imageView.setY(home.getFloor().getY());
            imageView.setX(home.getFloor().getX());
            imageView.setFitWidth(image.getWidth() / 2);
            imageView.setFitHeight(image.getHeight() / 2);
            home.getFloor().setTroop(imageView);
            GameMenu.pane.getChildren().add(imageView);
        }
        reset();
    }

    public void Salam(MouseEvent dragEvent) {
        System.out.println("Salam");
    }

    public void By(DragEvent dragEvent) {
        System.out.println("By");
    }

    public void buildingScroll() {
        System.out.println("ByBY");
        Pane pane = setTheScrollBar();

        ArrayList<Image> allBuildings = new ArrayList<>();
        ArrayList<String> allName = new ArrayList<>();
        for (int i = 0; i < BuildingsImages.values().length; i++) {
            allBuildings.add(BuildingsImages.values()[i].getImage());
            allName.add(BuildingsImages.values()[i].name());
        }
        setTheImagesToTheScroll(allBuildings, allName, pane, "Building");
    }

    public void troopScroll() {
        Pane pane = setTheScrollBar();
        ArrayList<Image> allTroops = new ArrayList<>();
        ArrayList<String> allName = new ArrayList<>();
        for (int i = 0; i < TroopImages.values().length; i++) {
            allTroops.add(TroopImages.values()[i].getImage());
            allName.add(TroopImages.values()[i].name());
        }
        setTheImagesToTheScroll(allTroops, allName, pane, "Troop");
    }

    private void setTheImagesToTheScroll(ArrayList<Image> allImages, ArrayList<String> allNames, Pane pane, String type) {
        for (int i = 0; i < allImages.size(); i++) {
            pane.getChildren().add(new ScrollImage(i * 100, 40, allImages.get(i), type, allNames.get(i)));
            Label label = new Label();
            label.setTextFill(Color.BLUE);
            label.setLayoutX(i * 100);
            label.setLayoutY(100);
            label.setText(allNames.get(i));
            pane.getChildren().add(label);
        }
    }

    private Pane setTheScrollBar() {
        Pane pane;
        try {
            pane = FXMLLoader.load(FloorController.class.getResource("/FXML/onClickCommand.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ScrollPane scrollPane = new ScrollPane();
        pane.getChildren().add(scrollPane);
        Pane pane1 = new Pane();
        scrollPane.setContent(pane1);
        scrollPane.setLayoutY(300);
        scrollPane.setPrefHeight(100);
        scrollPane.setPrefWidth(500);
        Label back = new Label("back");
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                backToTheFirst();
            }
        });
        pane1.getChildren().add(back);
        stage.setScene(new Scene(pane));
        return pane1;
    }

    private void backToTheFirst() {
        Pane pane;
        try {
            pane = FXMLLoader.load(FloorController.class.getResource("/FXML/onClickCommand.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(new Scene(pane));
    }

    public void makeTroopScrollBar(MouseEvent mouseEvent) {
        //TODO
        System.out.println("We make troops");
        setTheScrollBar();
    }

    public void engineBuildingScroll(MouseEvent mouseEvent) {

    }

    public void selectEngineBuilding(MouseEvent mouseEvent) {

    }

    public static void dropBuilding(String buildingName, Image buildingImage) {
        System.out.println(buildingName);
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryToMakeBuilding(selectedFloors.get(0).getHome(), buildingName, check);
        if (check[0]) {

            ImageView imageView = new ImageView(buildingImage);
            imageView.setLayoutY(selectedFloors.get(0).getY() + (double) Floor.floorSize / 4);
            imageView.setLayoutX(selectedFloors.get(0).getX() + (double) Floor.floorSize / 4);
            imageView.setFitHeight((double) Floor.floorSize / 2);
            imageView.setFitWidth((double) Floor.floorSize / 2);
            selectedFloors.get(0).setBuilding(imageView);
            GameMenu.pane.getChildren().add(imageView);
        }
        DataBase.showAlert(out);
        reset();
    }

    public void shortCuts(KeyEvent keyEvent) throws IOException {
        String key = keyEvent.getCode().getName();
        System.out.println(key);
        double firstSize = Floor.floorSize;
        if (key.equals("Add")) {
            if (Floor.floorSize >= 75)
                return;
            Floor.floorSize++;
            Star.starSize++;
            changeFloorSize(Floor.floorSize - firstSize);
        } else if (key.equals("Subtract")) {
            if (Floor.floorSize <= 50)
                return;
            Floor.floorSize--;
            Star.starSize--;
            changeFloorSize(Floor.floorSize - firstSize);
        } else if (key.equals("C")) {
            copy();
        } else if (key.equals("M")) {
            this.move();
        } else if (key.equals("A")) {
            this.attack();
        } else if (key.equals("V")) {
            paste();
        } else if (key.equals("Q")) {
            showClipBoard();
        } else if (key.equals("Z")) {
            showMiniMap();
        } else if (key.equals("H")) {
            showHelp();
        } else if (key.equals("T")) {
            showTradeMenu();
        } else if (key.equals("B")) {
            showShopMenu();
        }
    }

    private void showShopMenu() throws IOException {
        if (!isAnyShop()) {
            DataBase.showAlert("No Shop Building for you");
            return;
        }
        ShopMenuController shopMenuController=new ShopMenuController();
        shopMenuController.shopping(new Stage());
    }

    private boolean isAnyShop() {
        ArrayList<Home> allHomes = gameMenu.getGameController().getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            if (!allHomes.get(i).getOwner().equals(gameMenu.getGameController().getCurrentGovernment()))
                continue;
            if (allHomes.get(i).getBuilding() != null) {
                if (allHomes.get(i).getBuilding().getType().equals("Shop"))
                    return true;
            }
        }
        return false;
    }

    private void showTradeMenu() throws IOException {
        if (!isAnyShop()) {
            DataBase.showAlert("No Shop Building for you");
            return;
        }
        TradeMenuController tradeMenuController=new TradeMenuController();
        tradeMenuController.trading(new Stage());
    }

    private void showHelp() {
        Pane pane = new Pane();
        Label label = new Label();
        label.setTextFill(Color.BLUE);
        label.setText("C : Copy\nM : Move\nA : Attack\nV : Paste\nQ : clipboard\nZ : MiniMap\n+ : zoomIn\n- : zoomOut\nT : tradeMenu\nB : ShopMenu");
        pane.getChildren().add(label);
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(pane));
        stage1.show();
    }

    private void showMiniMap() {
        ScrollPane scrollPane = new ScrollPane();
        Pane scrollContent = new Pane();
        scrollPane.setContent(scrollContent);
        ArrayList<Home> allHomes = gameMenu.getGameController().getCurrentGame().getMap().getHomes();
        Floor.floorSize = 10;
        ArrayList<Floor> allNewFloors = new ArrayList<>();
        for (int i = 0; i < allHomes.size(); i++) {
            Floor floor = GameMenuController.makeAFloor(allHomes.get(i));
            allNewFloors.add(floor);
        }
        selectedFloors.add(new Floor(1, 2, allHomes.get(0)));
        for (int i = 0; i < allNewFloors.size(); i++) {
            selectedFloors.set(0, allNewFloors.get(i));
        }
        for (int i = 0; i < allNewFloors.size(); i++) {
            scrollContent.getChildren().add(allNewFloors.get(i));
            Floor firstFloor = allHomes.get(i).getFloor();
            if (firstFloor.getBuilding() != null) {
                ImageView imageView = new ImageView(firstFloor.getBuilding().getImage());
                imageView.setLayoutY(allNewFloors.get(i).getY() + (double) Floor.floorSize / 4);
                imageView.setLayoutX(allNewFloors.get(i).getX() + (double) Floor.floorSize / 4);
                imageView.setFitHeight((double) Floor.floorSize / 2);
                imageView.setFitWidth((double) Floor.floorSize / 2);
                scrollContent.getChildren().add(imageView);
            }
        }
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(scrollPane));
        stage1.show();
        Floor.floorSize = 75;
        reset();
    }

    private void showClipBoard() {
        Stage stage1 = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        Pane scrollContent = new Pane();
        scrollPane.setContent(scrollContent);
        stage1.setScene(new Scene(scrollPane));
        Label content = new Label();
        content.setTextFill(Color.BLUE);
        String floorInfo = "";
        for (int i = 0; i < DataBase.copiedFloors.size(); i++) {

            floorInfo += "Floor " + (i + 1) + " : " + "\n";
            floorInfo += "Floor Type : ";
            floorInfo += DataBase.copiedFloors.get(0).getHome().getTypeOfFloor() + "\n";
            floorInfo += "Direction : " + "\nX : " + DataBase.copiedFloors.get(0).getHome().getX();
            floorInfo += " Y : " + DataBase.copiedFloors.get(0).getHome().getY() + "\n";
            if (DataBase.copiedFloors.get(0).getHome().getBuilding() != null) {
                floorInfo += "Floor Building : ";
                floorInfo += DataBase.copiedFloors.get(0).getHome().getBuilding().getType() + "\n";
                floorInfo += DataBase.copiedFloors.get(0).getHome().getBuilding().getHitpoint() + "\n";
            }
            floorInfo += "____________________________";
        }
        content.setText(floorInfo);
        scrollContent.getChildren().add(content);
        stage1.show();
    }

    private void paste() {
        if (selectedFloors.size() < 1) {
            DataBase.showAlert("Please select a floor first");
            return;
        }
        this.changeFloorType(DataBase.copiedFloors.get(DataBase.copiedFloors.size() - 1).getHome().getTypeOfFloor());
        System.out.println(DataBase.copiedFloors.get(DataBase.copiedFloors.size() - 1).getHome().getBuilding().getType());
        dropBuilding(DataBase.copiedFloors.get(DataBase.copiedFloors.size() - 1).getHome().getBuilding().getType(), DataBase.copiedFloors.get(DataBase.copiedFloors.size() - 1).getBuilding().getImage());
        reset();
    }

    private void copy() {
        if (selectedFloors.size() < 1) {
            DataBase.showAlert("Please select a floor first");
            return;
        } else if (isItExist(selectedFloors.get(0))) {
            DataBase.showAlert("This Floor is already in clip board");
            return;
        }
        DataBase.copiedFloors.add(selectedFloors.get(0));
        DataBase.showAlert("We copy this floor successfully");
        reset();
    }

    private boolean isItExist(Floor floor) {
        for (int i = 0; i < DataBase.copiedFloors.size(); i++) {
            if (floor.equals(DataBase.copiedFloors.get(i)))
                return true;
        }
        return false;
    }

    private void changeFloorSize(double dif) {
        ArrayList<Home> allHomes = gameMenu.getGameController().getCurrentGame().getMap().getHomes();
        System.out.println(allHomes.size());
        for (int i = 0; i < allHomes.size(); i++) {
            allHomes.get(i).getFloor().setWidth(Floor.floorSize);
            allHomes.get(i).getFloor().setHeight(Floor.floorSize);
            //TODO
            allHomes.get(i).getFloor().setLayoutX(allHomes.get(i).getFloor().getLayoutX() + allHomes.get(i).getX() * dif);
            allHomes.get(i).getFloor().setLayoutY(allHomes.get(i).getFloor().getLayoutY() + allHomes.get(i).getY() * dif);
            try {
//                allHomes.get(i).getFloor().getBuilding().se
                allHomes.get(i).getFloor().getBuilding().setLayoutY(allHomes.get(i).getFloor().getY() + (double) Floor.floorSize / 4);
                allHomes.get(i).getFloor().getBuilding().setLayoutX(allHomes.get(i).getFloor().getX() + (double) Floor.floorSize / 4);
                allHomes.get(i).getFloor().getBuilding().setFitHeight((double) Floor.floorSize / 2);
                allHomes.get(i).getFloor().getBuilding().setFitWidth((double) Floor.floorSize / 2);
                allHomes.get(i).getFloor().getStar().setRadius(Star.starSize);
            } catch (Exception e) {

            }
        }
    }

    public void clear(MouseEvent mouseEvent) {
        boolean[] check = new boolean[1];
        String out = gameMenu.getGameController().tryClear(check, selectedFloors.get(0).getHome());
        if (check[0]) {
            changeFloorType("FlatGround");
            if (selectedFloors.get(0).getBuilding() != null) {
                removeImage(selectedFloors.get(0).getBuilding());
            }
            if (selectedFloors.get(0).getTroop() != null) {
                removeImage(selectedFloors.get(0).getTroop());
            }
        }
        DataBase.showAlert(out);
        reset();
    }

    public static void removeImage(ImageView imageView) {
        try {
            imageView.setX(-100);
            imageView.setY(-100);
        } catch (Exception e) {

        }

    }

    public void popularity(MouseEvent mouseEvent) {
        Pane pane = setTheScrollBar();
        String popName;
        Integer pop = gameMenu.getGameController().getCurrentGovernment().getPopularity();
        System.out.println(pop);
        if (pop < 30)
            popName = "RedPop";
        else if (pop > 30 && pop < 60) {
            popName = "YellowPop";
        } else
            popName = "GreenPop";
        System.out.println(popName);
//        ImageView popColorImage = new ImageView(new Image("/Image/" + popName + ".jpg"));
        ImageView popColorImage = new ImageView(new Image(this.getClass().getResource("/Image/" + popName + ".jpg").toExternalForm()));
        ImageView coin = new ImageView(new Image(this.getClass().getResource("/Image/coin.jpg").toExternalForm()));
        ImageView population = new ImageView(new Image(this.getClass().getResource("/Image/population.jpg").toExternalForm()));
        popColorImage.setX(150);
        popColorImage.setY(30);
        popColorImage.setFitWidth(40);
        popColorImage.setFitHeight(40);
        coin.setFitHeight(40);
        coin.setFitWidth(40);
        coin.setY(30);
        population.setFitWidth(40);
        population.setFitHeight(40);
        population.setX(300);
        population.setY(30);
        pane.getChildren().add(coin);
        pane.getChildren().add(population);
        Label gold = new Label("Gold : " + gameMenu.getGameController().getCurrentGovernment().getCoin());
        Label popularity = new Label("Popularity : " + gameMenu.getGameController().getCurrentGovernment().getPopularity());
        Label populationLabel = new Label("Population : " + gameMenu.getGameController().getCurrentGovernment().getPopulation());
        gold.setLayoutY(80);
        popularity.setLayoutY(80);
        popularity.setLayoutX(150);
        populationLabel.setLayoutY(80);
        populationLabel.setLayoutX(300);
        gold.setTextFill(Color.YELLOWGREEN);
        popularity.setTextFill(Color.YELLOWGREEN);
        populationLabel.setTextFill(Color.YELLOWGREEN);
        pane.getChildren().add(gold);
        pane.getChildren().add(popularity);
        pane.getChildren().add(popColorImage);
        pane.getChildren().add(populationLabel);
        //PopularityDetails
        Label label = new Label("Popularity Details");
        label.setLayoutX(150);
        pane.getChildren().add(label);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showPopularityDetails();
            }
        });
    }

    private void showPopularityDetails() {
        Pane pane = setTheScrollBar();
        Label details = new Label();
        String det = gameMenu.getGameController().showPopularityFactors();
        det += "Popularity Buildings : \n";
        det += showPopularityBuildings();
        details.setLayoutY(30);
        details.setText(det);
        details.setTextFill(Color.BLUE);
        pane.getChildren().add(details);
    }

    private HashMap<String, Integer> showPopularityBuildings() {
        ArrayList<Home> allHomes = gameMenu.getGameController().getCurrentGame().getMap().getHomes();
        HashMap<String, Integer> allPopularityBuildings = new HashMap<>();
        allPopularityBuildings.put("Cathedral", 0);
        allPopularityBuildings.put("Inn", 0);
        allPopularityBuildings.put("Church", 0);
        for (int i = 0; i < allHomes.size(); i++) {
            if (!allHomes.get(i).getOwner().equals(gameMenu.getGameController().getCurrentGovernment()))
                continue;
            if (allHomes.get(i).getBuilding() == null)
                continue;
            String buildingType = allHomes.get(i).getBuilding().getType();
            if (buildingType.equals("Cathedral") || buildingType.equals("Inn") || buildingType.equals("Church"))
                allPopularityBuildings.put(buildingType, allPopularityBuildings.get(buildingType) + 1);
        }
        return allPopularityBuildings;
    }

    public void close(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("Esc"))
            stage.close();
    }

    public static void randomSickness() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(50), actionEvent -> makeSickness()));
        DataBase.sickness = timeline;
        timeline.setCycleCount(2000);
        timeline.play();
    }

    private static void makeSickness() {
        int rand = new Random().nextInt(4);
        if (DataBase.sick != null) {
            DataBase.sick.setHeight(0);
            DataBase.sick.setWidth(0);
            DataBase.sick = null;
        }
        if (rand == 3) {
            ArrayList<Home> allHomes = gameMenu.getGameController().getCurrentGame().getMap().getHomes();
            int homeIndex = new Random().nextInt(allHomes.size() - 1);
            Home home = allHomes.get(homeIndex);
            Fire fire = new Fire(home.getFloor().getX(), home.getFloor().getY());
            fire.setImagePattern(new ImagePattern(new Image(FloorController.class.getResource("/Image/sickness.jpg").toExternalForm())));
            gameMenu.pane.getChildren().add(fire);
            DataBase.sick = fire;
            home.getOwner().setPopularity(home.getOwner().getPopularity() - 2);
        }
    }

    public void changeTurn(MouseEvent mouseEvent) {
        String out = gameMenu.getGameController().changeTurn();
        DataBase.showAlert(out);
    }
}
