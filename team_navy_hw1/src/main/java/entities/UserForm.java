package entities;

public class UserForm {
    private String guessword;
    private String username;
    private int gameID;
    private int turnNum;

    public String getguessword() {
        return guessword;
    }

    public void setguessword(String guessword) {
        this.guessword = guessword;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public int getGameID() { return gameID; }

    public void setGameID(int gameID) { this.gameID = gameID; }

    public int getTurnNum() { return turnNum; }

    public void setTurnNum(int turnNum) { this.turnNum = turnNum; }
}
