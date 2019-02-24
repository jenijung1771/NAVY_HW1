package entities;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import entities.Player;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PlayerRepository extends CrudRepository<Player, Integer> {
    @Procedure(name = "ValidateLogin")
    Integer validateLogin(@Param("username") String username, @Param("loginpassword") String loginpassword);

    @Procedure(name = "NewLogin")
    Integer newLogin(@Param("username") String username, @Param("loginpassword") String loginpassword);
}