package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PlaysID implements Serializable {

    @Column(name = "GameID", nullable = false)
    private int gameID;

    @Column(name = "TurnNumber", nullable = false)
    private int TurnNumber;

    public int getGameID() { return gameID; }

    public void setGameID(int gameID) { this.gameID = gameID; }

    public int getTurnNumber() { return TurnNumber; }

    public void setTurnNumber(int turnNumber) { TurnNumber = turnNumber; }
}
