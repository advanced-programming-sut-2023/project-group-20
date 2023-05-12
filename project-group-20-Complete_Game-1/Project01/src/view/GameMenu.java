package view;

import controller.GameController;
import controller.LoginController;
import controller.SignupController;
import model.GameInfo.Game;
import model.GameInfo.Government;
import model.GameInfo.Map;
import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenu extends Menu {
    private GameController gameController;

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void run() {
        //TODO
        // create a GameController object
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+)){0,1}){0,2}$")) != null)
                gameController.showMap(matcher);
            else if ((matcher = isMatched(command, "^show popularity factors$")) != null)
                System.out.println(gameController.showPopularityFactors());
            else if ((matcher = isMatched(command, "^show popularity$")) != null)
                System.out.println(gameController.showPopularity());
            else if ((matcher = isMatched(command, "^show food list$")) != null)
                System.out.println(gameController.showFoodList());
            else if ((matcher = isMatched(command, "^food rate((?: -r (?<r>\\S+))){1}$")) != null)
                System.out.println(gameController.setFoodRate(matcher));
            else if ((matcher = isMatched(command, "^tax rate((?: -r (?<r>\\S+))){1}$")) != null)
                System.out.println(gameController.setTaxRate(matcher));
            else if ((matcher = isMatched(command, "^tax rate show$")) != null)
                System.out.println(gameController.showTaxRate());
            else if ((matcher = isMatched(command, "^dropbuilding(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
                System.out.println(gameController.dropBuilding(matcher));
            //<<\\S+ Not \\s+ >>
            else if ((matcher = isMatched(command, "^select building(((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.selectBuilding(matcher));
            else if ((matcher = isMatched(command, "^createunit(((?: -t (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<count>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null)
                System.out.println(gameController.createUnit(matcher));
            else if ((matcher = isMatched(command, "^repair$")) != null)
                System.out.println(gameController.repair());
            else if ((matcher = isMatched(command, "^select unit(((?: -x(?<x>\\d+))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.selectUnit(matcher));
            else if ((matcher = isMatched(command, "^move unit(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.moveUnit(matcher,"x","y"));
            else if ((matcher = isMatched(command, "^patrol unit(((?: -x1 (?<x1>\\d+))|(?: -y1 (?<y1>\\d+))|(?: -x2 (?<x2>\\d+))|(?: -y2 (?<y2>\\d+))){0,1}){0,4}$")) != null)
                System.out.println(gameController.patrolUnit(matcher));
//            else if ((matcher = isMatched(command, "^set(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -s (?<state>((?:standing)|(?:defensive)|(?:offensive)))){0,1}){0,3}$")) != null)
//                System.out.println(gameController.setTroopsState(matcher));
            else if ((matcher = isMatched(command, "^attack -e (?:(?<x>\\d+)) (?:(?<y>\\d+))$")) != null)
                System.out.println(gameController.attackToEnemy(matcher));
            else if ((matcher = isMatched(command, "^attack(((?: -x (?<x>\\d+))|(?: -y (?<Y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.attack(matcher));
            else if ((matcher = isMatched(command, "^pour oil(?: -d (?<direction>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\")))){1}$")) != null)
                System.out.println(gameController.pourOil(matcher));
//            else if ((matcher = isMatched(command, "^dig tunnel((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
//                System.out.println(gameController.digTunnel(matcher));
            else if ((matcher = isMatched(command, "^build((?: -q (?<equipmentname>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){1}$")) != null)
                System.out.println(gameController.buildBuilding(matcher));
            else if ((matcher = isMatched(command, "^disband unit$")) != null)
                System.out.println(gameController.disbandUnit());
            else if ((matcher = isMatched(command, "^settexture(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
                System.out.println(gameController.setTexture(matcher));
            else if ((matcher = isMatched(command, "^settexture(((?: -x1 (?<x1>\\d+))|(?: -y1 (?<y1>\\d+))|(?: -x2 (?<x2>\\d+))|(?: -y2 (?<y2>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,5}$")) != null)
                System.out.println(gameController.setTextureRectangle(matcher));
            else if ((matcher = isMatched(command, "^clear(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.clear(matcher));
            else if ((matcher = isMatched(command, "^droprock(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -d (?<direction>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
                System.out.println(gameController.droprock(matcher));
            else if ((matcher = isMatched(command, "^droptree(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
                System.out.println(gameController.dropTree(matcher));
            else if ((matcher = isMatched(command, "^dropbuilding(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,3}$")) != null)
                System.out.println(gameController.dropBuilding(matcher));
            else if ((matcher = isMatched(command, "^dropunit(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -type (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -c (?<count>\\d+))){0,1}){0,4}$")) != null)
                System.out.println(gameController.dropUnit(matcher));
            else if (isMatched(command, "exit") != null) {
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

    //Test
    public static void main(String[] args) {
        GameMenu gameMenu=new GameMenu();
        ArrayList<Government> governments=new ArrayList<>();
        governments.add(new Government(0,0,new User("Erfan","Erfan427166","Akbar")));
        governments.add(new Government(0,0,new User("Sobhan","Sobhan427166","Asghar")));
        gameMenu.setGameController(new GameController(new Game(2,new Map(200,200,3),governments,2),governments.get(0)));
        System.out.println(gameMenu.gameController.getCurrentGame().getMap().getHomes().get(4).getTypeOfFloor());
        gameMenu.run();
    }
}
