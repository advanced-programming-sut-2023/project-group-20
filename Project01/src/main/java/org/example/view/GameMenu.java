package org.example.view;

import org.example.controller.GameController;
import org.example.controller.UnitMotionController;
import org.example.model.GameInfo.Game;
import org.example.model.GameInfo.Government;
import org.example.model.GameInfo.Home;
import org.example.model.GameInfo.Map;
import org.example.model.User;

import java.util.ArrayList;

import org.example.controller.LoginController;

import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private GameController gameController;

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
                System.out.println(gameController.showPopularityFactors());
            else if ((matcher = isMatched(command, "^show popularity$")) != null)
                System.out.println(gameController.showPopularity());
            else if ((matcher = isMatched(command, "^show food list$")) != null)
                System.out.println(gameController.showFoodList());
            else if ((matcher = isMatched(command, "^food rate((?: -r (?<r>\\S+))){1}$")) != null) {
                if (matcher.group("r") == null)
                    printMassage("rate is null");
                else if (checkInt(matcher.group("r")))
                    printMassage("r must be Integer");
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
            else if ((matcher = isMatched(command, "^select unit(((?: -x(?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
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
            } else if ((matcher = isMatched(command, "^attack(((?: -x (?<x>\\S+))|(?: -y (?<Y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x")) || checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(gameController.attack(matcher));
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
                    System.out.println(gameController.setTexture(matcher));
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
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
//        LoginController.start();
        //Bade Game Kodesh Mire Too LoginController
        //TODO
        // Bayad GameController To While LoginController Ejra She


    }

    //TEST
    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        Game game = new Game(4, new Map(200, 200, 3), 2);
        Government government = new Government(new User("Erfan", "Erfan427166", "Edi"));
        game.addGovernment(government);
        game.addGovernment(new Government(new User("Ali","QQ","AliAgha")));
        GameController gameController1 = new GameController(game, government);
        gameMenu.setGameController(gameController1);
        gameMenu.run();
    }
}
