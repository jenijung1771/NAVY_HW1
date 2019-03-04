package entities;

import org.springframework.data.repository.CrudRepository;

import entities.Plays;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlaysRepository extends CrudRepository<Plays, Integer> {

}