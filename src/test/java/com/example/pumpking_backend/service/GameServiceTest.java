package com.example.pumpking_backend.service;

import com.example.pumpking_backend.model.Game;
import com.example.pumpking_backend.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks GameService gameService;

    @Test
    void getGameScoreReturnsUpdatedGameIfValidDay() {
        //Arrange
        List<Integer> oldWaterScore = new ArrayList<>();
        oldWaterScore.add(3);
        boolean fertilizerScore = true;
        int weedsScore = 1;
        int prevTotal = 1000;
        int newTotal = 2600;
        Game prevGame = new Game(1,1, 0, oldWaterScore, fertilizerScore, weedsScore, prevTotal);
        Game newGame = new Game(1, 2, 0, oldWaterScore, fertilizerScore, weedsScore);
        when(gameRepository.findById(1)).thenReturn(Optional.of(prevGame));
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> invocation.getArgument(0)
        );
        //Act
        Game response = gameService.getGameScore(newGame);
        //Assert
        assertEquals(newTotal, response.getTotalScore());
        verify(gameRepository).save(any(Game.class));
    }
}