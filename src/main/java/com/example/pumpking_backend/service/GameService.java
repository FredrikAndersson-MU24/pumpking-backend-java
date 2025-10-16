package com.example.pumpking_backend.service;

import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

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
