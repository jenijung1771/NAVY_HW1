package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity // This tells Hibernate to make a table out of this class
public class Plays implements Serializable {
    @EmbeddedId
    private PlaysID playsID;

    private String PlayerID;

    private String PlayerGuess;

    private String ComputerGuess;

    private String PlayerLettersCorrect;

    private String ComputerLettersCorrect;

    public int getGameID() { return playsID.getGameID(); }

    public int getTurnNumber() { return playsID.getTurnNumber(); }

    public String getPlayerID() {
        return PlayerID;
    }

    public String getPlayerGuess() {
        return PlayerGuess;
    }

    public String getComputerGuess() {
        return ComputerGuess;
    }

    public String getPlayerLettersCorrect() { return PlayerLettersCorrect; }

    public String getComputerLettersCorrect() { return ComputerLettersCorrect; }

    public void setGameID(int gameID) { playsID.setGameID(gameID); }

    public void setTurnNumber(int turnNumber) { playsID.setTurnNumber(turnNumber); }

    public void setPlayerID(String playerID) {
        PlayerID = playerID;
    }

    public void setPlayerGuess(String playerGuess) {
        PlayerGuess = playerGuess;
    }

    public void setComputerGuess(String computerGuess) {
        ComputerGuess = computerGuess;
    }

    public void setPlayerLettersCorrect(String playerLettersCorrect) { PlayerLettersCorrect = playerLettersCorrect; }

    public void setComputerLettersCorrect(String computerLettersCorrect) { ComputerLettersCorrect = computerLettersCorrect; }

    public Plays() {
        playsID = new PlaysID();
    }
}
