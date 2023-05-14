package org.example.controller;

import org.example.model.GameInfo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UnitMotionController {
    //    private ArrayList<ArrayList<Troop>> patrolTroops = new ArrayList<>();
    private static Integer counter = 0;
    private ArrayList<PatrolTroops> patrolTroops = new ArrayList<>();
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
        speed = reduceSpeedIfInWater(speed, mapController.getHomeByPosition(firstX, firstY));
        return findRace(0, 0, speed, end, homes, firstX, firstY, targetX, targetY);
    }

    //TODO
    // Maybe Have Some problems


    //    private ArrayList<Home> findRace(int fatherX, int fatherY, int speed, boolean[] end, ArrayList<Home> raceHomes, int currentX, int currentY, int targetX, int targetY) {
//        if (end[0])
//            return raceHomes;
//        if (currentX - targetX > speed || currentX - targetX < -speed || currentY - targetY > speed || currentY - targetY < -speed) {
//            if (raceHomes.size() - 1 < 0)
//                return raceHomes;
//            raceHomes.remove(raceHomes.size() - 1);
//            return raceHomes;
//        }
//        raceHomes.add(mapController.getHomeByPosition(currentX, currentY));
//        //REMOVE//
//        System.out.println(end[0]);
//        System.out.println(UnitMotionController.counter += 1);
//        System.out.println("CX " + currentX + " CY " + currentY + " TX " + targetX + " TY " + targetY);
//        //REMOVE//
//        if (currentX == targetX && currentY == targetY) {
//            end[0] = true;
//            return raceHomes;
//        }
//
//        if (fatherX != -1) {
//            System.out.println("S0");
//
//            if (currentX - 1 > 0 && currentX < map.getxSize() && isFreeToMove(currentX, currentY)) {
//                System.out.println("YES");
//                findRace(1, 0, speed, end, raceHomes, currentX - 1, currentY, targetX, targetY);
//                UnitMotionController.counter++;
//            }
//        }
//        if (fatherX != 1) {
//            System.out.println("S1");
//
//            if (currentX + 1 > 0 && currentX < map.getxSize() && isFreeToMove(currentX, currentY)) {
//                findRace(-1, 0, speed, end, raceHomes, currentX + 1, currentY, targetX, targetY);
//                UnitMotionController.counter++;
//
//            }
//        }
//        if (fatherY != -1) {
//            System.out.println("S2");
//            if (currentY - 1 > 0 && currentY < map.getySize() && isFreeToMove(currentX, currentY)) {
//                findRace(0, +1, speed, end, raceHomes, currentX, currentY - 1, targetX, targetY);
//                UnitMotionController.counter++;
//
//            }
//        }
//        if (fatherY != -1) {
//            System.out.println("S3");
//            if (currentY + 1 > 0 && currentY < map.getySize() && isFreeToMove(currentX, currentY)) {
//                findRace(0, -1, speed, end, raceHomes, currentX, currentY + 1, targetX, targetY);
//                UnitMotionController.counter++;
//
//            }
//        }
//
//        return raceHomes;
//    }
    private ArrayList<Home> findRace(int fatherX, int fatherY, int speed, boolean[] end, ArrayList<Home> raceHomes, int currentX, int currentY, int targetX, int targetY) {
        if (currentX - targetX > speed || currentX - targetX < -speed || currentY - targetY > speed || currentY - targetY < -speed) {
            return raceHomes;
        }
        if (currentX > targetX) {
            for (int i = currentX; i >= targetX; i--) {
                raceHomes.add(mapController.getHomeByPosition(i, currentY));
            }
        } else {
            for (int i = currentX; i <= targetX; i++) {
                raceHomes.add(mapController.getHomeByPosition(i, currentY));
            }
        }
        Integer lastX = raceHomes.get(raceHomes.size() - 1).getX();
        if (currentY > targetY) {
            for (int i = currentY - 1; i >= targetY; i--) {
                raceHomes.add(mapController.getHomeByPosition(lastX, i));
            }
        } else {
            for (int i = currentY + 1; i <= targetY; i++) {
                raceHomes.add(mapController.getHomeByPosition(lastX, i));
            }
        }
        return raceHomes;
    }

    private boolean isFreeToMove(int homeX, int homeY) {
//        Home home = mapController.getHomeByPosition(homeX, homeY);
//        if (home.getBuilding() != null)
//            return false;
//            //TODO
//        else if (home.getTypeOfFloor().equals("")) {//|| home.getTypeOfFloor().equals("") || home.getTypeOfFloor().equals("") ||) {
//            return false;
//        }
        return true;

    }

    public boolean isAnyAvailableDestination(int speed, int firstX, int firstY, int targetX, int targetY) {
        ArrayList<Home> homes = new ArrayList<>();
        boolean[] end = new boolean[1];
        speed = reduceSpeedIfInWater(speed, mapController.getHomeByPosition(firstX, firstY));
        end[0] = false;
        if (findRace(0, 0, speed, end, homes, firstX, firstY, targetX, targetY).size() == 0)
            return false;
        return true;
    }

    public ArrayList<PatrolTroops> getPatrolTroops() {
        return patrolTroops;
    }

    public void addPatrolTroops(PatrolTroops patrolTroops) {
        this.patrolTroops.add(patrolTroops);
    }

    public void removeThePatrolTroopsByIndex(int index) {
        this.patrolTroops.remove(index);
    }

    private Integer reduceSpeedIfInWater(Integer firstSpeed, Home home) {
        if (home.getTypeOfFloor().equals("DeapLessWater"))
            return firstSpeed * 2 / 3;
        return firstSpeed;
    }
}
