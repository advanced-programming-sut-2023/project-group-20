package org.example.view;

import org.example.controller.ShopController;

import java.util.regex.Matcher;

public class ShopMenu extends Menu {
    public void run() {
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^show price list$")) != null)
                System.out.println(ShopController.showPriceList());
            else if ((matcher = isMatched(command, "^buy(((?: -a (?<amount>\\S+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null) {
                if (matcher.group("amount") == null)
                    printMassage("amount is null");
                else if (checkInt(matcher.group("amount")))
                    printMassage("amount must be Integer");
                else
                    System.out.println(ShopController.buy(matcher));
            } else if ((matcher = isMatched(command, "^sell(((?: -a (?<amount>\\S+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null) {
                if (matcher.group("amount") == null)
                    printMassage("amount is null");
                else if (checkInt(matcher.group("amount")))
                    printMassage("amount must be Integer");
                else
                    System.out.println(ShopController.sell(matcher));
            } else if ((matcher = isMatched(command, "back")) != null) {
                ShopController.closeShop();
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }
}
