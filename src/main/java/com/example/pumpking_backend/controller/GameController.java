package com.example.pumpking_backend.controller;


import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.service.GameService;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/finished")
    public ResponseEntity<List<Game>> getFinishedGames() {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.getFinishedGames());
    }

    @PostMapping("/daytick")
    public ResponseEntity<Game> dayTick(@RequestBody Game game) {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.dayTick(game));
    }

    @PostMapping("/saveAtEndOfGame")
    public ResponseEntity<List<Game>> saveAtEndOfGame(@RequestBody Game game) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.OK).body(gameService.saveAtEndOfGame(game));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGame(@PathVariable int id) {
        gameService.deleteGameById(id);
    }

}
