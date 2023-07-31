package application;

import java.util.Date;

public class Review {
    private int reviewID;
    private Gamers gamer;
    private Game game;
    private String reviewDescription;
    private Date reviewDate;
    private int stars;

    
    public Review(int reviewID, Gamers gamer, Game game, String reviewDescription, Date reviewDate, int stars) {
        this.reviewID = reviewID;
        this.gamer = gamer;
        this.game = game;
        this.reviewDescription = reviewDescription;
        this.reviewDate = reviewDate;
        this.stars = stars;
    }
    
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public Gamers getGamer() {
        return gamer;
    }

    public void setGamer(Gamers gamer) {
        this.gamer = gamer;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
