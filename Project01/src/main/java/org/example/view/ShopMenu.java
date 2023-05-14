package org.example.view;

import org.example.controller.ShopController;

import java.util.regex.Matcher;

public class ShopMenu extends Menu {
    ShopController shopController;

    public void setShopController(ShopController shopController) {
        this.shopController = shopController;
    }

    public void run() {
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^show price list$")) != null)
                System.out.println(shopController.showPriceList());
            else if ((matcher = isMatched(command, "^buy(((?: -a (?<amount>\\S+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null) {
                if (matcher.group("amount") == null)
                    printMassage("amount is null");
                else if (checkInt(matcher.group("amount")))
                    printMassage("amount must be Integer");
                else
                    System.out.println(shopController.buy(matcher));
            } else if ((matcher = isMatched(command, "^sell(((?: -a (?<amount>\\S+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null) {
                if (matcher.group("amount") == null)
                    printMassage("amount is null");
                else if (checkInt(matcher.group("amount")))
                    printMassage("amount must be Integer");
                else
                    System.out.println(shopController.sell(matcher));
            } else if ((matcher = isMatched(command, "back")) != null) {
//                shopController.closeShop();
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }
}
