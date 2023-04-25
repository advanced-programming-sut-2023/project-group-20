package view;

import controller.MapController;
import java.util.regex.Matcher;

public class MapMenu extends Menu{
    public void run(){
        String command=getScanner().nextLine();
        Matcher matcher;
        while (true){
            if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+)){0,1}){0,2}$")) != null)
                System.out.println(MapController.showMap(matcher));
            else if ((matcher = isMatched(command, "^map(((?: (?<direction>\\S+){1}( (?<x>\\d*)?)?){0,2}))$")) != null)
                System.out.println(MapController.moveMap(matcher));
            else if ((matcher = isMatched(command, "^show details(((?: -x (?<x>\\d+)))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
                System.out.println(MapController.homeDetails(matcher));
            else if (isMatched(command,"exit")!=null) {
                System.out.println("Exited from map .Entered Game");
                break;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
        MapController.exit();
    }
    public static void showMap(String map){System.out.println(map);}
}
