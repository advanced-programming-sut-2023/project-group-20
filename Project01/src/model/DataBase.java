package model;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<User> users=new ArrayList<>();
    private final static ArrayList<String> securityQuestions=new ArrayList<>();
    static {
        securityQuestions.add("What is my father’s name?");
        securityQuestions.add("What was my first pet’s name?");
        securityQuestions.add("What is my mother’s last name");
    }
    private final static ArrayList<String> typesOfFloor=new ArrayList<>();
    static {
        typesOfFloor.add("FlatGround");
        typesOfFloor.add("GravelGround");
        typesOfFloor.add("Rock");
        typesOfFloor.add("StoneGround");
        typesOfFloor.add("IronGround");
        typesOfFloor.add("Grass");
        typesOfFloor.add("Meadow");
        typesOfFloor.add("FoolMeadow");
    }
    private final static ArrayList<String> typesOftree=new ArrayList<>();
    static {
        typesOftree.add("Palm");
        typesOftree.add("DesertTree");
        typesOftree.add("Olive");
        typesOftree.add("Cherry");
        typesOftree.add("Coconut");

    }
    private final static ArrayList<String> typesOfWaterArea=new ArrayList<>();
    static {
        typesOfWaterArea.add("Plain");
        typesOfWaterArea.add("DeapLessWater");
        typesOfWaterArea.add("River");
        typesOfWaterArea.add("SmallPond");
        typesOfWaterArea.add("BigPond");
        typesOfWaterArea.add("Oil");
        typesOfWaterArea.add("Sea");
        typesOfWaterArea.add("Beach");
    }
    public static void addUserToDataBase(User user){
        users.add(user);
    }
    public static User getUserByName(String username){}
    public static Integer rank(User user){}

    public static String selectSecurityQuestion(Integer number){}
}
