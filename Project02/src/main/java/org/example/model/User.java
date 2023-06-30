package org.example.model;

import org.example.model.GameInfo.ProfileAvatar;

public class User {
    private String username;
    private String password;
    private String nickname;
    private String slogan;
    private String email;
    private String securityQuestion;
    private String profileAvatar;
    private String securityQuestionAnswer;
    private Integer highScore = 0;

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public User(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.profileAvatar="6";
    }

    public void setProfileAvatar(String profileAvatar) {
        this.profileAvatar = profileAvatar;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighScore(Integer highScore) {
        this.highScore = highScore;
    }

    public Integer getHighScore() {
        return highScore;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getNickname() {
        return nickname;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityQuestion() {
        if (securityQuestion.equals("1"))
            return "What is my father’s name?";
        if (securityQuestion.equals("2"))
            return "was my first pet’s name?";
        else return "What is my mother’s last name";
    }
    public String getSecurityQuestionNumber() {
        return securityQuestion;
    }
    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getProfileAvatar() {
        return profileAvatar;
    }

    public void setSecurityQuestionAnswer(String securityQuestionAnswer) {
        this.securityQuestionAnswer = securityQuestionAnswer;
    }
}
