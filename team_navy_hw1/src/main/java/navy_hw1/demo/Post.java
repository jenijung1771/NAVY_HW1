package navy_hw1.demo;

import entities.Game;
import entities.Plays;

import java.util.Date;
import java.util.List;

public class Post {
    private long id;
    private String username;
    private String password;
    private boolean createNew;
    private List<Game> games;
    private List<Plays> turns;
    private Date created_at;
    private Date updated_at;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getCreateNew() { return createNew; }

    public void setCreateNew(boolean createNew) { this.createNew = createNew; }

    public List<Game> getGames() { return games; }

    public void setGames(List<Game> games) { this.games = games; }

    public List<Plays> getTurns() { return turns; }

    public void setTurns(List<Plays> turns) { this.turns = turns; }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }


}

