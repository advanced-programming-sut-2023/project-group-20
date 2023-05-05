package controller;

import model.GameInfo.Game;
import model.GameInfo.Home;
import model.GameInfo.Map;
import model.GameInfo.Troop;

import java.util.ArrayList;

public class UnitMotionController {
    private ArrayList<ArrayList<Troop>> patrolTroops = new ArrayList<>();
    private Map map;
    private MapController mapController;

    public UnitMotionController(Map map, Game game) {
        this.map = map;
        mapController = new MapController(map, game);
    }

    public ArrayList<Home> findTheDestinationRace(int speed, int firstX, int firstY, int targetX, int targetY) {
        ArrayList<Home> homes = new ArrayList<>();
        boolean[] end = new boolean[1];
        end[0] = false;
        return findRace(speed, end, homes, firstX, firstY, targetX, targetY);
    }

    //TODO
    // Maybe Have Some problems
    private ArrayList<Home> findRace(int speed, boolean[] end, ArrayList<Home> raceHomes, int currentX, int currentY, int targetX, int targetY) {
        if (end[0])
            return raceHomes;
        if (currentX - targetX > speed || currentX - targetX < -speed || currentY - targetY > speed || currentY - targetY < -speed) {
            raceHomes.remove(raceHomes.size() - 1);
            return raceHomes;
        }
        raceHomes.add(mapController.getHomeByPosition(currentX, currentY));
        if (currentX == targetX && currentY == targetY) {
            end[0] = true;
            return raceHomes;
        }
        if (currentX - 1 > 0 && currentX < map.getxSize() && isFreeTpMove(currentX, currentY))
            findRace(speed, end, raceHomes, currentX - 1, currentY, targetX, targetY);
        if (currentX + 1 > 0 && currentX < map.getxSize() && isFreeTpMove(currentX, currentY))
            findRace(speed, end, raceHomes, currentX + 1, currentY, targetX, targetY);
        if (currentY - 1 > 0 && currentY < map.getySize() && isFreeTpMove(currentX, currentY))
            findRace(speed, end, raceHomes, currentX, currentY - 1, targetX, targetY);
        if (currentY - 1 > 0 && currentY < map.getySize() && isFreeTpMove(currentX, currentY))
            findRace(speed, end, raceHomes, currentX, currentY + 1, targetX, targetY);
        return raceHomes;
    }

    private boolean isFreeTpMove(int homeX, int homeY) {
        Home home = mapController.getHomeByPosition(homeX, homeY);
        if (home.getBuilding() != null)
            return false;
        else if (home.getTypeOfFloor().equals("") || home.getTypeOfFloor().equals("") || home.getTypeOfFloor().equals("") ||) {
            return false;
        }
        return true;

    }

    public boolean isAnyAvailableDestination(int speed, int firstX, int firstY, int targetX, int targetY) {
        ArrayList<Home> homes = new ArrayList<>();
        boolean[] end = new boolean[1];
        end[0] = false;
        if (findRace(speed, end, homes, firstX, firstY, targetX, targetY).size() == 0)
            return false;
        return true;
    }

    public ArrayList<ArrayList<Troop>> getPatrolTroops() {
        return patrolTroops;
    }

    public void addPatrolTroops(ArrayList<Troop> patrolTroops) {
        this.patrolTroops.add(patrolTroops);
    }
}
