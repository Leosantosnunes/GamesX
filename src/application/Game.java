package application;

import java.util.Date;

public class Game {
	private int gameID;
    private String gameTitle;
    private String gameDeveloper;
    private Date gameReleaseDate;
    private double gamePrice;

    // Constructor
    public Game(int gameID, String gameTitle, String gameDeveloper, Date gameReleaseDate, double gamePrice) {
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
        this.gameReleaseDate = gameReleaseDate;
        this.gamePrice = gamePrice;
    }
    
    public Game(String gameTitle) {        
        this.gameTitle = gameTitle;        
    }
    
    public Game(String gameTitle, String gameDeveloper, Date gameReleaseDate, double gamePrice) {        
        this.gameTitle = gameTitle;
        this.gameDeveloper = gameDeveloper;
        this.gameReleaseDate = gameReleaseDate;
        this.gamePrice = gamePrice;
    }

    // Getter and Setter methods for each attribute
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getGameDeveloper() {
        return gameDeveloper;
    }

    public void setGameDeveloper(String gameDeveloper) {
        this.gameDeveloper = gameDeveloper;
    }

    public Date getGameReleaseDate() {
        return gameReleaseDate;
    }

    public void setGameReleaseDate(Date gameReleaseDate) {
        this.gameReleaseDate = gameReleaseDate;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(double gamePrice) {
        this.gamePrice = gamePrice;
    }

}
