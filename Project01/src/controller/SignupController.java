package controller;

import view.SignupMenu;
import java.util.regex.Matcher;

public class SignupController extends CheckController{
    private static boolean stayLoggedIn=false;
    private Integer invalidLogin=0;
    public static void start(){
        SignupMenu signupMenu=new SignupMenu();
        signupMenu.run();
    }
    public static String createUser(Matcher matcher){}
    private static void setQuestionAndAnswer(Integer questionNumber,String answer){}
    public static String login(Matcher matcher){}
    private static String makeRandomSlogan(){}
    public static void forgetPassword(Matcher matcher){}
    private static Integer makeRandomQuestion(){}
    private static String makeRandomPassword(){}
    private static void skipTime(){}
}
