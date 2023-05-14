package org.example.view;

import org.example.controller.TradeController;
import java.util.regex.Matcher;

public class TradeMenu extends Menu {
    public void run() {
        System.out.println(TradeController.showAllPlayers());
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^trade(((?: -a (?<amount>\\S+))|(?: -p (?<price>\\S+))|(?: -t (?<type>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,4}$")) != null) {
                if (matcher.group("amount") == null)
                    printMassage("amount is null");
                else if (checkInt(matcher.group("amount")))
                    printMassage("amount must be Integer");
                else if (matcher.group("price") == null)
                    printMassage("price is null");
                else if (matcher.group("type") == null)
                    printMassage("type is null");
                else
                    System.out.println(TradeController.trade(matcher));
            } else if ((matcher = isMatched(command, "^trade list$")) != null)
                System.out.println(TradeController.tradeList());
            else if ((matcher = isMatched(command, "^trade accept((?: -i (?<id>\\S+))|(?: -m (?<message>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}")) != null) {
                if (matcher.group("id") == null)
                    printMassage("id is null");
                else if (checkInt(matcher.group("id")))
                    printMassage("id must be Integer");
                else
                    System.out.println(TradeController.tradeAccept(matcher));
            } else if ((matcher = isMatched(command, "^trade history$")) != null)
                System.out.println(TradeController.tradeHistory());
            else if ((matcher = isMatched(command, "back")) != null) {
                TradeController.back();
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }

}
