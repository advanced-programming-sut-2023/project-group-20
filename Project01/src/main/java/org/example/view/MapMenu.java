package org.example.view;

import org.example.controller.MapController;

import java.util.regex.Matcher;

public class MapMenu extends Menu{
    private MapController mapController;

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public void run() {
        System.out.print(mapController.showMap(0, 0));
        String command = getScanner().nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\S+))|(?: -y (?<y>\\S+)){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x"))||checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(mapController.showMap(0, 0));
            } else if ((matcher = isMatched(command, "^map(((?: (?<direction>\\S+){1}( (?<x>\\S*)?)?){0,2}))$")) != null) {
                if (matcher.group("direction") == null)
                    printMassage("direction is null");
                else if (checkInt(matcher.group("x"))&&matcher.group("x")!=null)
                    printMassage("x must be Integer");
                else if (!(matcher.group("direction").equals("west")
                        || matcher.group("direction").equals("east")
                        || matcher.group("direction").equals("north")
                        || matcher.group("direction").equals("south")))
                    printMassage("direction should be west or east or north or south");
                else
                    System.out.println(mapController.moveMap(matcher));
            } else if ((matcher = isMatched(command, "^show details((((?: -x (?<x>\\S+)))|(?: -y (?<y>\\S+))){0,1}){0,2}$")) != null) {
                if (matcher.group("x") == null || matcher.group("y") == null)
                    printMassage("-x or -y is null");
                else if (checkInt(matcher.group("x"))||checkInt(matcher.group("y")))
                    printMassage("x or y must be Integer");
                else
                    System.out.println(mapController.showHomeDetails(matcher));
            } else if (isMatched(command, "exit") != null) {
                System.out.println("Exited from map .Entered Game");
                return;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
    }
}
