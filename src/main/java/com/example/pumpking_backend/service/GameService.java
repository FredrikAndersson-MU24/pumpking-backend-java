package com.example.pumpking_backend.service;

import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.repository.GameRepository;
import org.springframework.stereotype.Service;

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

    public Game createGame(Game game) {
        return new Game(game.getDay(),
                game.getTimeOfDay(),
                game.getWaterScore(),
                game.getFertilizerScore(),
                game.getWeedsScore(),
                game.getTotalScore(),
                game.getUserName());
    }

}
