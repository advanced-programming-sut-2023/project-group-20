package view;

import controller.GameController;
import controller.LoginController;
import controller.SignupController;

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
            else if ((matcher = isMatched(command, "^select building(((?: -x (?<x>\\s+))|(?: -y (?<y>\\s+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.selectBuilding());
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
            else if ((matcher = isMatched(command, "^set(((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))|(?: -s (?<state>((?:standing)|(?:defensive)|(?:offensive)))){0,1}){0,3}$")) != null)
                System.out.println(gameController.setTroopsState(matcher));
            else if ((matcher = isMatched(command, "^attack -e (?:(?<x>\\d+)) (?:(?<y>\\d+))$")) != null)
                System.out.println(gameController.attackToEnemy(matcher));
            else if ((matcher = isMatched(command, "^attack(((?: -x (?<x>\\d+))|(?: -y (?<Y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.attack(matcher));
            else if ((matcher = isMatched(command, "^pour oil(?: -d (?<direction>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\")))){1}$")) != null)
                System.out.println(gameController.pourOil(matcher));
            else if ((matcher = isMatched(command, "^dig tunnel((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(gameController.digTunnel(matcher));
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
}
