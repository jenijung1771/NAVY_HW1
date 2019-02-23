package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class Plays {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int GameID;

    private int TurnNumber;

    private String PlayerID;

    private String PlayerGuess;

    private String ComputerGuess;

    public int getGameID() {
        return GameID;
    }

    public int getTurnNumber() {
        return TurnNumber;
    }

    public String getPlayerID() {
        return PlayerID;
    }

    public String getPlayerGuess() {
        return PlayerGuess;
    }

    public String getComputerGuess() {
        return ComputerGuess;
    }

    public void setGameID(int gameID) {
        GameID = gameID;
    }

    public void setTurnNumber(int turnNumber) {
        TurnNumber = turnNumber;
    }

    public void setPlayerID(String playerID) {
        PlayerID = playerID;
    }

    public void setPlayerGuess(String playerGuess) {
        PlayerGuess = playerGuess;
    }

    public void setComputerGuess(String computerGuess) {
        ComputerGuess = computerGuess;
    }
}
