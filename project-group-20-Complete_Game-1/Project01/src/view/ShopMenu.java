package view;

import controller.ShopController;
import model.GameInfo.Government;

import java.util.regex.Matcher;

public class ShopMenu extends Menu{
    public void run(){
        String command=getScanner().nextLine();
        Matcher matcher;
        ShopController ShopController = new ShopController(new Government());//TODO : !!!
        while (true){
            if ((matcher = isMatched(command, "^show price list$")) != null)
                System.out.println(ShopController.showPriceList());//TODO : REGEX
            else if ((matcher = isMatched(command, "^buy(((?: -a (?<amount>\\d+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null)
                System.out.println(ShopController.buy(matcher));
            else if ((matcher = isMatched(command, "^sell(((?: -a (?<amount>\\d+))|(?: -i (?<id>(?:\"[^\"]+\")|(?:(?!\")\\S+(?<!\"))))){0,1}){0,2}$")) != null)
                System.out.println(ShopController.sell(matcher));
            else if ((matcher = isMatched(command, "back")) != null) {
                ShopController.closeShop();
                break;
            }
            else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }
}
