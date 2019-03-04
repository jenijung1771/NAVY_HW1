package entities;

import javax.persistence.*;

@Entity
@Table(name="Player")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "ValidateLogin",
                                   procedureName = "navyjottodb.ValidateLogin",
                                   parameters = {
                                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "loginpassword", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = Integer.class)
        }),
        @NamedStoredProcedureQuery(name = "NewLogin",
                                   procedureName = "navyjottodb.NewLogin",
                                   parameters = {
                                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "loginpassword", type = String.class),
                                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "result", type = Integer.class)
        })
})
public class Player {
    @Id
    private String PlayerID;

    private String PlayerPassword;

    private int NumberOfGames;

    public String getPlayerID() {
        return PlayerID;
    }

    public String getPlayerPassword() {
        return PlayerPassword;
    }

    public int getNumberOfGames() {
        return NumberOfGames;
    }

    public void setPlayerID(String playerID) {
        PlayerID = playerID;
    }

    public void setPlayerPassword(String playerPassword) {
        PlayerPassword = playerPassword;
    }

    public void setNumberOfGames(int numberOfGames) {
        NumberOfGames = numberOfGames;
    }
}