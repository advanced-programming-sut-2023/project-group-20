package org.example.view;

import org.example.controller.MapController;

import java.util.regex.Matcher;

public class MapMenu extends Menu{
    private MapController mapController;

    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    public void run(){
        // TODO
        //  MAKE A MapController
        System.out.println(mapController.showMap(0,0));
        String command=getScanner().nextLine();
        Matcher matcher;
        while (true){
            if ((matcher = isMatched(command, "^show map((?: -x (?<x>\\d+))|(?: -y (?<y>\\d+)){0,1}){0,2}$")) != null)
                System.out.println(mapController.showMap(0,0));
            else if ((matcher = isMatched(command, "^map(((?: (?<direction>\\S+){1}( (?<x>\\d*)?)?){0,2}))$")) != null)
                System.out.println(mapController.moveMap(matcher));
//            else if ((matcher = isMatched(command, "^show details(((?: -x (?<x>\\d+)))|(?: -y (?<y>\\d+))){0,1}){0,2}$")) != null)
//                System.out.println(mapController.showHomeDetails(matcher));
            else if (isMatched(command,"exit")!=null) {
                System.out.println("Exited from map .Entered Game");
//                break;
                return;
            } else
                System.out.println("Invalid command!");
            command = getScanner().nextLine();
        }
//        MapController.exit();

    }
    public static void showMap(String map){System.out.println(map);}
}
