package com.example.pumpking_backend.controller;


import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.service.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public Game saveGame(Game game) {
        return gameService.createGame(game);
    }


}
