package entities;

import org.springframework.data.repository.CrudRepository;

import entities.Game;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByPlayerID(String playerID);
    List<Game> findByGameID(int gameID);
}