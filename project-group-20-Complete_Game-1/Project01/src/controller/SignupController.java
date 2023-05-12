package controller;

import model.DataBase;
import model.User;
import view.Menu;
import view.SignupMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.regex.Matcher;

public class SignupController extends CheckController{
    private static boolean stayLoggedIn=false;
    private static Integer invalidLogin=0;
    private static String invalidUsername="";
    public static void start(){
        SignupMenu signupMenu=new SignupMenu();
        signupMenu.run();
    }
    public static String createUser(Matcher matcher){
        String username=matcher.group("username");
        String password=matcher.group("password");
        String slogan=matcher.group("slogan");
        if (username==null)
            return "Username is empty";
        if (password==null)
            return "Password is empty";
        if (matcher.group("nickname")==null)
            return "Nickname is empty";
        if (!checkUsername(username))
            return "Username format is invalid";
        if (DataBase.getUserByName(username)!=null){
            while (true){
                if (DataBase.getUserByName(username+"_")!=null)
                    username=username+("_");
                else {username=username+("_");break;}
            }
            return "Username is exit BUT you can choose this Username"+username;}
        if (matcher.group("checkSlogan")!=null&&slogan==null)
            return "You doesn't entered any slogan after -s";
        if (slogan.equals("random"))
            slogan=makeRandomSlogan();
        if (password.equals("random"))
            password=makeRandomPassword();
        String passwordError=checkPassword(password);
        if (!passwordError.equals("accepted"))
            return passwordError;
        if (!password.equals("random")&&!password.equals(matcher.group("confirmPassword")))
            return "Password confirm isn't correct";
        User user=new User(username,password,matcher.group("nickname"));
        if (matcher.group("email")!=null){
        String userEmail=matcher.group("email");
        for (String email:DataBase.getEmails()) {
            if (email.equalsIgnoreCase(userEmail))
                return "Email is exist";
        }
        String emailError=checkEmail(userEmail);
        if (!emailError.equals("accepted"))
            return emailError;
        user.setEmail(userEmail);}
        Matcher matcher1=SignupMenu.pickSecurityQuestion("");
        String number;
        while (true){
            if (matcher1==null)
                return "Creating user regretted";
            else
                number=matcher1.group("number");
            if (!(number.equals("1")||number.equals("2")||number.equals("3")))
                matcher1=SignupMenu.pickSecurityQuestion("Invalid number for Pick\n");
            else if (matcher1.group("answer").equals("answerConfirm"))
                break;
            else matcher1=SignupMenu.pickSecurityQuestion("Answer confirm is wrong\n");
        }
        if (slogan!=null)
            user.setSlogan(slogan);
        user.setSecurityQuestion(number);
        user.setSecurityQuestionAnswer(matcher1.group("answer"));
        DataBase.addUserToDataBase(user);
        return "User created";
    }
    private static void setQuestionAndAnswer(Integer questionNumber,String answer){}
    public static String login(Matcher matcher){
        String username=matcher.group("username");
        if (DataBase.getUserByName(username)==null)
            return "User doesn't exist";
        if(matcher.group("password")!=null&&!DataBase.getUserByName(username).getPassword().equals(matcher.group("password"))){
            if (username.equals(invalidUsername)){
                skipTime();
                invalidLogin++;
            }else{
                invalidLogin=1;
                invalidUsername=username;
            }
            return "Username and password didn't match!";}
        stayLoggedIn= matcher.group("staylogedin") != null;
        Menu.setLogedInUser(DataBase.getUserByName(username));
        logedInuser=DataBase.getUserByName(username);
        return "user logged in successfully!";
    }
    public static String makeRandomSlogan(){
        Random random=new Random();
        int randomInt=random.nextInt(8);
        //TODO
        return "";
    }
    public static void forgetPassword(Matcher matcher){
        if (matcher.group("username")==null)
            return;
        User user=DataBase.getUserByName(matcher.group("username"));
        while(true){
            if (SignupMenu.enterAnswerForgot(user.getSecurityQuestion()).equals(user.getSecurityQuestionAnswer()))
                break;
        }
        String passwordError="";
        do {
            passwordError = checkPassword(SignupMenu.enterNewPassword(passwordError));
        } while (!passwordError.equals("accepted"));
    }
    private static Integer makeRandomQuestion(){
        //TODO
        return null;
    }
    public static String makeRandomPassword(){
        Random random=new Random();
        double randomNum=random.nextInt(100)*1.01;
        String randomPass= "Is"+randomNum+"l@m";
        SignupMenu.reEnterPassword(randomPass);
        return randomPass;
    }
    private static void skipTime(){
        int delay = 3000;
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            }
        };
        new Timer(delay, taskPerformer).start();




//        long startTime = System.currentTimeMillis();
//        long elapsedTime = 0;
//        while (elapsedTime < invalidLogin*1000) {
//            elapsedTime = (new Date()).getTime() - startTime;
//        }
    }
}
