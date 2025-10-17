package com.example.pumpking_backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "games")
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int day;
    private int timeOfDay;
    private List<Integer> waterScore;
    private boolean fertilizerScore;
    private int weedsScore;
    private int totalScore;
    private String userName;
    @CreationTimestamp
    private Date creationDate;
    @CurrentTimestamp
    private Date completionDate;


    public Game() {
    }

    public Game(int day, int timeOfDay, List<Integer>  waterScore, boolean fertilizerScore, int weedsScore, int totalScore, String userName) {
        this.day = day;
        this.timeOfDay = timeOfDay;
        this.waterScore = waterScore;
        this.fertilizerScore = fertilizerScore;
        this.weedsScore = weedsScore;
        this.totalScore = totalScore;
        this.userName = userName;
    }


    public int getId() {
        return id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(int timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public List<Integer>  getWaterScore() {
        return waterScore;
    }

    public void setWaterScore(List<Integer>  waterScore) {
        this.waterScore = waterScore;
    }

    public boolean getFertilizerScore() {
        return fertilizerScore;
    }

    public void setFertilizerScore(boolean  fertilizerScore) {
        this.fertilizerScore = fertilizerScore;
    }

    public int getWeedsScore() {
        return weedsScore;
    }

    public void setWeedsScore(int weedsScore) {
        this.weedsScore = weedsScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
}
