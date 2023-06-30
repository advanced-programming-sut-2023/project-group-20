package org.example.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.model.GameInfo.*;
import org.example.model.GameObjects.Floor;
import org.example.model.GameObjects.Star;
import org.example.model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private GameController gameController;
    public static Pane pane;

    static {
        try {
            pane = FXMLLoader.load(GameMenu.class.getResource("/FXML/testGame.fxml"));
         } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ScrollPane scrollPane;

    static {
        try {
            scrollPane = FXMLLoader.load(GameMenu.class.getResource("/FXML/Game.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void run() {
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+)){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    gameController.showMap(matcher);
            } else if ((matcher = isMatched(command, "^show popularity factors$")) != null)
                System.out.print(gameController.showPopularityFactors());
            else if ((matcher = isMatched(command, "^show popularity$")) != null)
                System.out.println(gameController.showPopularity());
            else if ((matcher = isMatched(command, "^stop patrol$")) != null)
                System.out.println(gameController.stopPatrolUint());
            else if ((matcher = isMatched(command, "^change turn$")) != null) {
                String end = gameController.changeTurn();
                if (end.equals(""))
                    break;
                else
                    System.out.print(end);
            } else if ((matcher = isMatched(command, "^show food list$")) != null)
                System.out.println(gameController.showFoodList());
            else if ((matcher = isMatched(command, "^show food rate$")) != null)
                System.out.println(gameController.showFoodRate());
            else if ((matcher = isMatched(command, "^food rate((?: -r (?<r>\\S+))){1}$")) != null) {
                if (matcher.group("r") == null)
                    printMassage("rate is null");
                else if (checkInt(matcher.group("r")))
                    printMassage("rate must be Integer");
                else
                    System.out.println(gameController.setFoodRate(matcher));
            } else if ((matcher = isMatched(command, "^tax rate((?: -r (?<r>\\S+))){1}$")) != null) {
                if (matcher.group("r") == null)
                    printMassage("rate is null");
                else if (checkInt(matcher.group("r")))
                    printMassage("r must be Integer");
                else
                    System.out.println(gameController.setTaxRate(matcher));
            } else if ((matcher = isMatched(command, "^tax rate show$")) != null)
                System.out.println(gameController.showTaxRate());
            else if ((matcher = isMatched(command, "^open shop menu$")) != null)
                System.out.println(gameController.openShop());
            else if ((matcher = isMatched(command, "^open trade menu$")) != null)
                System.out.println(gameController.openTrade());
            else if ((matcher = isMatched(command, "^dropbuilding(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("please enter type");
                else
                    System.out.println(gameController.dropBuilding(matcher));
            } else if ((matcher = isMatched(command, "^select building(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.selectBuilding(matcher));
            } else if ((matcher = isMatched(command, "^createunit(((?: -t (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<count>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null) {
                if (matcher.group("count") == null)
                    printMassage("please enter count");
                else if (checkInt(matcher.group("count")))
                    printMassage("count must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("please enter type");
                else
                    System.out.println(gameController.createUnit(matcher));
            } else if ((matcher = isMatched(command, "^repair$")) != null)
                System.out.println(gameController.repair());
            else if ((matcher = isMatched(command, "^select unit(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.selectUnit(matcher));
            } else if ((matcher = isMatched(command, "^move unit(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.moveUnit(matcher, "x", "y"));
            } else if ((matcher = isMatched(command, "^patrol unit(((?: -x1 (?<x1>\\S+))|(?: -y1 (?<y1>\\S+))|(?: -x2 (?<x2>\\S+))|(?: -y2 (?<y2>\\S+))){0,1}){0,4}$")) != null) {
                if (matcher.group("x1") == null || matcher.group("y1") == null)
                    printMassage("-x1 or -y1 is null");
                else if (checkInt(matcher.group("x1")) || checkInt(matcher.group("y1")))
                    printMassage("x1 or y1 must be Integer");
                else if (matcher.group("x2") == null || matcher.group("y2") == null)
                    printMassage("-x2 or -y2 is null");
                else if (checkInt(matcher.group("x2")) || checkInt(matcher.group("y2")))
                    printMassage("x2 or y2 must be Integer");
                else
                    System.out.println(gameController.patrolUnit(matcher));
            } else if ((matcher = isMatched(command, "^set(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -s (?<state>((?:standing)|(?:defensive)|(?:offensive))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (!(matcher.group("state").equals("standing")
                        || matcher.group("state").equals("defensive")
                        || matcher.group("state").equals("offensive")))
                    printMassage("state should be offensive or standing or defensive");
                else
                    System.out.println(gameController.setTroopsState(matcher));
            } else if ((matcher = isMatched(command, "^attack -e (?:(?<x>\\S+)) (?:(?<y>\\S+))$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.attackToEnemy(matcher));
            } else if ((matcher = isMatched(command, "^attack(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.attack(matcher));
            } else if ((matcher = isMatched(command, "^select engine building(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.selectEngineBuilding(matcher));
            } else if ((matcher = isMatched(command, "^engine attack(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.engineAttack(matcher));
            } else if ((matcher = isMatched(command, "^pour oil(?: -d (?<direction>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\")))){1}$")) != null) {
                if (matcher.group("direction") == null)
                    printMassage("direction is null");
                else if (!(matcher.group("direction").equals("west")
                        || matcher.group("direction").equals("east")
                        || matcher.group("direction").equals("north")
                        || matcher.group("direction").equals("south")))
                    printMassage("direction should be west or east or north or south");
                else
                    System.out.println(gameController.pourOil(matcher));
            } else if ((matcher = isMatched(command, "^dig tunnel(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.digTunnel(matcher));
            } else if ((matcher = isMatched(command, "^dig Ditch(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.digDitch(matcher));
            } else if ((matcher = isMatched(command, "^fill Ditch(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.fillDitch(matcher));
            } else if ((matcher = isMatched(command, "^build((?: -q (?<equipmentname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null) {
                if (matcher.group("equipmentname") == null)
                    printMassage("-q is null");
                else
                    System.out.println(gameController.buildBuilding(matcher));
            } else if ((matcher = isMatched(command, "^disband unit$")) != null)
                System.out.println(gameController.disbandUnit());
            else if ((matcher = isMatched(command, "^settexture(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else
                    System.out.println(gameController.setTexture(matcher));
            } else if ((matcher = isMatched(command, "^settexture(((?: -x1 (?<x1>\\S+))|(?: -y1 (?<y1>\\S+))|(?: -x2 (?<x2>\\S+))|(?: -y2 (?<y2>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,5}$")) != null) {
                if (matcher.group("x1") == null || matcher.group("y1") == null)
                    printMassage("-x1 or -y1 is null");
                else if (checkInt(matcher.group("x1")) || checkInt(matcher.group("y1")))
                    printMassage("x1 or y1 must be Integer");
                else if (matcher.group("x2") == null || matcher.group("y2") == null)
                    printMassage("-x2 or -y2 is null");
                else if (checkInt(matcher.group("x2")) || checkInt(matcher.group("y2")))
                    printMassage("x2 or y2 must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else
                    System.out.println(gameController.setTextureRectangle(matcher));
            } else if ((matcher = isMatched(command, "^clear(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.clear(matcher));
            } else if ((matcher = isMatched(command, "^droprock(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -d (?<direction>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (!(matcher.group("direction").equals("west")
                        || matcher.group("direction").equals("east")
                        || matcher.group("direction").equals("north")
                        || matcher.group("direction").equals("south")))
                    printMassage("direction should be west or east or north or south");
                else
                    System.out.println(gameController.droprock(matcher));
            } else if ((matcher = isMatched(command, "^droptree(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else
                    System.out.println(gameController.dropTree(matcher));
            } else if ((matcher = isMatched(command, "^dropbuilding(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else
                    System.out.println(gameController.dropBuilding(matcher));
            } else if ((matcher = isMatched(command, "^dropunit(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<count>\\S+))){0,1}){0,4}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else if (matcher.group("count") == null)
                    printMassage("count is null");
                else if (checkInt(matcher.group("count")))
                    printMassage("count must be Integer");
                else
                    System.out.println(gameController.dropUnit(matcher));
            } else if (isMatched(command, "exit") != null) {
                System.out.println("Game Closed");
                break;
            } else if ((matcher = isMatched(command, "^show government info$")) != null) {
                System.out.println(gameController.showGovernmentInfo());
            } else if ((matcher = isMatched(command, "^show home info(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.print(gameController.showHomeInfo(matcher));

            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("1");
        testMakeGameController();
        System.out.println("aESGHDT");
        scrollPane.setContent(pane);
        Scene scene = new Scene(scrollPane);
        primaryStage.setScene(scene);
        setTheFirstFloors();

        System.out.println("2");
        System.out.println("aESRDTFU");

        FloorController.gameMenu = this;
        primaryStage.show();

        Home home = gameController.getCurrentGame().getMap().getHomes().get(0);
        home.addTroop(new Troop("FireThrowers", "MercenaryPost", gameController.getCurrentGovernment(), 5, 10, 15));
        System.out.println("Done !");
    }


    private void testMakeGameController() {
        ArrayList<Government> allGovernment = new ArrayList<>();
        allGovernment.add(new Government(new User("E1", "", "", "E1")));
        allGovernment.add(new Government(new User("E2", "", "", "E2")));
        allGovernment.add(new Government(new User("E3", "", "", "E3")));
        Game game = new Game(10, new Map(20, 5, 3), 3);
        game.getGovernments().addAll(allGovernment);
        this.gameController = new GameController(game, allGovernment.get(0));
    }

    public static void addFloorToPane(Floor floor) {
        if (floor == null)
            return;
        pane.getChildren().add(floor);
    }

    private void setTheFirstFloors() {
        ArrayList<Home> allHomes = gameController.getCurrentGame().getMap().getHomes();
        for (int i = 0; i < allHomes.size(); i++) {
            Floor floor = GameMenuController.makeAFloor(allHomes.get(i));
            allHomes.get(i).setFloor(floor);
            addFloorToPane(floor);
        }
        FloorController.randomSickness();
    }

    public GameController getGameController() {
        return gameController;
    }

}
