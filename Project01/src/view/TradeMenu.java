package view;

import controller.GameController;
import controller.ShopController;
import controller.TradeController;
import java.util.regex.Matcher;

public class TradeMenu extends Menu {
    public void run(){
        System.out.println(TradeController.showAllPlayers());
        String command=getScanner().nextLine();
        Matcher matcher;
        while (true){
            if ((matcher = isMatched(command, "^trade(((?: -a (?<amount>\\d+))|(?: -p (?<price>\\d+))|(?: -t (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,4}$")) != null)
                System.out.println(TradeController.trade(matcher));
            else if ((matcher = isMatched(command, "^trade list$")) != null)
                System.out.println(TradeController.tradeList());
            else if ((matcher = isMatched(command, "^trade accept((?: -i (?<id>\\d+))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}")) != null)
                System.out.println(TradeController.tradeAccept(matcher));
            else if ((matcher = isMatched(command, "^trade history$")) != null)
                System.out.println(TradeController.tradeHistory());
            else if ((matcher = isMatched(command, "back")) != null) {
                TradeController.back();
                break;
            }
            else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }

}
