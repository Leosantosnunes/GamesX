package application;

import java.util.Date;

public class FriendList {
    private int gamerId;
    private int friendId;
    private String firstName;
    private String friendsNickname;
    private Date dateAdded;
    private String friendsStatus;

    // Constructors
    public FriendList() {
    }
    
    public FriendList(String firstName,String friendsNickname,String friendsStatus) {
    	this.firstName = firstName;
    	this.friendsNickname = friendsNickname;
    	this.friendsStatus = friendsStatus;
    }

    public FriendList(int gamerId, int friendId, String friendsNickname, Date dateAdded, String friendsStatus) {
        this.gamerId = gamerId;
        this.friendId = friendId;
        this.friendsNickname = friendsNickname;
        this.dateAdded = dateAdded;
        this.friendsStatus = friendsStatus;
    }

    // Getters and Setters
    public String getfirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getFriendsNickname() {
        return friendsNickname;
    }

    public void setFriendsNickname(String friendsNickname) {
        this.friendsNickname = friendsNickname;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getFriendsStatus() {
        return friendsStatus;
    }

    public void setFriendsStatus(String friendsStatus) {
        this.friendsStatus = friendsStatus;
    }

    // You can override the toString() method if you need to print the object's information
    @Override
    public String toString() {
        return "Friend{" +
                "gamerId=" + gamerId +
                ", friendId=" + friendId +
                ", friendsNickname='" + friendsNickname + '\'' +
                ", dateAdded=" + dateAdded +
                ", friendsStatus='" + friendsStatus + '\'' +
                '}';
    }
}
