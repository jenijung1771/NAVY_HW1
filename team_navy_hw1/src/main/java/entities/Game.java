package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity // This tells Hibernate to make a table out of this class
public class Game {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int GameID;

    private String PlayerID;

    private Timestamp DateStarted;

    private String PlayerWord;

    private String ComputerWord;

    private String Outcome;

    public int getGameID() {
        return GameID;
    }

    public String getPlayerID() { return PlayerID; }

    public Timestamp getDateStarted() {
        return DateStarted;
    }

    public String getPlayerWord() {
        return PlayerWord;
    }

    public String getComputerWord() {
        return ComputerWord;
    }

    public String getOutcome() {
        return Outcome;
    }

    public void setGameID(int gameID) {
        GameID = gameID;
    }

    public void setPlayerID(String playerID) { PlayerID = playerID; }

    public void setDateStarted(Timestamp dateStarted) {
        DateStarted = dateStarted;
    }

    public void setPlayerWord(String playerWord) {
        PlayerWord = playerWord;
    }

    public void setComputerWord(String computerWord) {
        ComputerWord = computerWord;
    }

    public void setOutcome(String outcome) {
        Outcome = outcome;
    }

    public Game(String playerID, Timestamp dateStarted, String playerWord, String computerWord) {
        PlayerID = playerID;
        DateStarted = dateStarted;
        PlayerWord = playerWord;
        ComputerWord = computerWord;
    }
}
