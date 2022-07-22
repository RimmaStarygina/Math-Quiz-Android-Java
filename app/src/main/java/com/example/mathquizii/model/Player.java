package com.example.mathquizii.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable{

    private String playerName;
    private int playerScore;
    public ArrayList<Question> questionsList= new ArrayList<>();

    public Player(String playerName, int playerScore) {
        this.playerName = playerName;
        this.playerScore = playerScore;
    }

    public Player(String playerName, int playerScore, ArrayList<Question> questionsList) {
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.questionsList = questionsList;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerScore=" + playerScore +
                ", questionsList=" + questionsList +
                '}';
    }
}

