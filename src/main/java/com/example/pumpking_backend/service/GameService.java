package com.example.pumpking_backend.service;

import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.repository.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private static final Map<Integer, Double> MULTIPLIERS = Map.of(
            -1, 0.9,
            0, 0.4,
            1, 0.6,
            2, 0.8,
            3, 1.0,
            4, 0.8,
            5, 0.6,
            6, 0.4,
            7, 0.2
    );

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game getGameScore(Game newGame) {
        Game prevGame = gameRepository.findById(newGame.getId()).orElse(null);
        boolean isNew = false;
        if (prevGame == null) {
            if (newGame.getDay() != 0) {
                throw new IllegalArgumentException("invalid day for new game, should be zero");
            }
            prevGame = gameRepository.save(newGame);
            isNew = true;
        }
        if (!isNew && (newGame.getDay() <= prevGame.getDay()) || newGame.getDay() > 30 ) {
            System.out.println(isNew);
            System.out.println(newGame.getDay());
            throw new IllegalArgumentException("invalid day value. must be greater than prevDay and less than 30");
        }
        int waterScore = calculateWaterScore(newGame.getWaterScore());
        int fertilizerScore = calculateFertilizerScore(newGame.getFertilizerScore(), newGame.getDay());
        int prevTotalScore = prevGame.getTotalScore();
        System.out.println("waterscoer: " + waterScore);
        System.out.println("fertilizerscore:" + fertilizerScore);
        System.out.println("prevtotal: " + prevTotalScore);
        System.out.println("newtotal: " + prevTotalScore + waterScore + fertilizerScore);
        prevGame.setDay(newGame.getDay());
        prevGame.setTotalScore(prevTotalScore + waterScore + fertilizerScore);
        //System.out.println("newtotalfromrepo: " + gameRepository.findById(prevGame.getId()).get().getTotalScore());
        return gameRepository.save(prevGame);
    }

    private int calculateFertilizerScore(boolean fertilizerScore, int day) {
        double fertilizerScoreMultiplier = 1;
        if ((fertilizerScore && day < 5) || (!fertilizerScore && day >= 5)) {
            fertilizerScoreMultiplier *= MULTIPLIERS.get(7);
        }  else if ((fertilizerScore && (day % 2 == 0)) ){
            fertilizerScoreMultiplier *= MULTIPLIERS.get(5);
        } else {
            fertilizerScoreMultiplier *= MULTIPLIERS.get(3);
        }
        System.out.println("fertilizerMultiplier: " + fertilizerScoreMultiplier);
        return (int) (fertilizerScoreMultiplier * 1000);
    }

    private int calculateWaterScore (List<Integer> waterScore) {
        double waterScoreMultiplier = 1;
        int timesWaterdCorrectly = 0;
        if (waterScore.isEmpty()) {
            return (int) (0.1 * 1000);
        }
        for (int score : waterScore){
            if (score == 3) {
                if (timesWaterdCorrectly == 0) {
                    waterScoreMultiplier *= MULTIPLIERS.get(score);
                    timesWaterdCorrectly = 1;
                } else {
                    waterScoreMultiplier *= MULTIPLIERS.get(-1);
                }
            }  else {
                waterScoreMultiplier *= MULTIPLIERS.get(score);
            }
        }
        System.out.println("waterMultiplier: " + waterScoreMultiplier);
        return (int) (waterScoreMultiplier * 1000);
    }

    public void deleteGameById(int id) {
        Game savedGame = gameRepository.findById(id).orElse(null);
        System.out.println(id);
        if (savedGame == null) {
            throw new EntityNotFoundException();
        } else {
            gameRepository.deleteById(id);
        }
    }
}
