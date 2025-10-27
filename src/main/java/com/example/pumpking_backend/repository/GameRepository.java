package com.example.pumpking_backend.repository;

import com.example.pumpking_backend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GameRepository extends JpaRepository <Game, Integer> {

    List<Game> findByFinished(boolean isFinished);
}
