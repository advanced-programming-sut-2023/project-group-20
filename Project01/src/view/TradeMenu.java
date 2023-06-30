package view;

import controller.GameController;
import controller.TradeController;

public class TradeMenu extends Menu {
    public void run(){
        System.out.println(TradeController.showAllPlayers());
    }

}
