package application;

import javafx.scene.layout.VBox;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import java.util.Date;

public class Library {
	private Gamers gamer;
    private Game game;
    private int playTime;
    private String gameTitle;

    // Constructor
    public Library(Gamers gamer, Game game, int playTime) {
        this.gamer = gamer;
        this.game = game;
        this.playTime = playTime;
    }
    
    public Library(int playTime, String gameTitle) {
    	this.playTime = playTime;
    	this.gameTitle = gameTitle;
    }
    
    public Library () {}

    // Getter and Setter methods for each attribute
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

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
    
    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }
    
    public Pane getLayout(int gamerID) {
        try {
            // Create the root layout (VBox) for the LibraryPage
            Pane rootLayout = new Pane();
            
            String sqlName = "select FIRSTNAME from GAMER WHERE GAMER_ID = ?";
            PreparedStatement statementName = DbConn.connection.prepareStatement(sqlName);
            statementName.setInt(1, gamerID);
            ResultSet dbresultSetName = statementName.executeQuery();

            if (dbresultSetName.next()) {
                String firstName = dbresultSetName.getString("FIRSTNAME");
                
                Label headingLabel = new Label("WELCOME " + firstName);
                headingLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
                
                rootLayout.getChildren().add(headingLabel);
            }

            dbresultSetName.close();
            statementName.close();
            
            

            String sql = "select GAMETITLE, PLAYTIME from library join game using (game_id) WHERE GAMER_ID = ?";
            PreparedStatement statement = DbConn.connection.prepareStatement(sql);
            statement.setInt(1, gamerID);
            ResultSet dbresultSet = statement.executeQuery();

            TableView tableView = new TableView();
            
            while (dbresultSet.next()) {
                String gameTitle = dbresultSet.getString("GAMETITLE");
                Integer playTime = dbresultSet.getInt("PLAYTIME");                
                
                
                Library library = new Library(playTime,gameTitle);
                tableView.getItems().addAll(library);
                
            }

            TableColumn<Library, String> gameTitleCol = new TableColumn<>("Game Title");
            gameTitleCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getGameTitle()));

            TableColumn<Library, Integer> playTimeCol = new TableColumn<>("Play Time");
            playTimeCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getPlayTime()));

            tableView.getColumns().addAll(gameTitleCol, playTimeCol);
            

            dbresultSet.close();
            statement.close();
            

            TableView<FriendList> friendsTableView = new TableView<>();
            TableColumn<FriendList, String> friendFirstNameCol = new TableColumn<>("First Name");
            friendFirstNameCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getfirstName()));

            TableColumn<FriendList, String> friendNicknameCol = new TableColumn<>("Nickname");
            friendNicknameCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFriendsNickname()));

            TableColumn<FriendList, String> friendStatusCol = new TableColumn<>("Status");
            friendStatusCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getFriendsStatus()));

            friendsTableView.getColumns().addAll(friendFirstNameCol, friendNicknameCol, friendStatusCol);

            // Fetch and populate data for friends TableView
            String sqlFriends = "SELECT FIRSTNAME, FRIENDSNICKNAME, FRIENDSSTATUS FROM FRIENDS JOIN GAMER ON FRIEND_ID = GAMER.GAMER_ID WHERE FRIENDS.GAMER_ID = ?";
            PreparedStatement statementFriends = DbConn.connection.prepareStatement(sqlFriends);
            statementFriends.setInt(1, gamerID);
            ResultSet resultSetFriends = statementFriends.executeQuery();

            ObservableList<FriendList> friendsList = FXCollections.observableArrayList();

            while (resultSetFriends.next()) {
                String friendFirstName = resultSetFriends.getString("FIRSTNAME");
                String friendNickname = resultSetFriends.getString("FRIENDSNICKNAME");
                String friendStatus = resultSetFriends.getString("FRIENDSSTATUS");

                FriendList friend = new FriendList(friendFirstName, friendNickname, friendStatus);
                friendsList.add(friend);
            }

            friendsTableView.setItems(friendsList);

            resultSetFriends.close();
            statementFriends.close();
            
            VBox friendsVBox = new VBox();
            friendsVBox.setSpacing(5);
            friendsVBox.setPadding(new Insets(9, 0, 0, 9));
            friendsVBox.setMinWidth(330);

            VBox gamesVBox = new VBox(); // Corrected to create a new VBox for gamesVBox
            gamesVBox.setSpacing(5);
            gamesVBox.setPadding(new Insets(9, 0, 0, 9));
            gamesVBox.setMinWidth(450);

            Label friendsLabel = new Label("Friends");
            friendsLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            // Add the Friends title label and the friends TableView to the friendsVBox
            friendsVBox.getChildren().addAll(friendsLabel, friendsTableView);

            // Create the label for games (already done in the previous code)
            Label gamesLabel = new Label("Games");
            gamesLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

            // Add the gamesLabel and the tableView to the gamesVBox
            gamesVBox.getChildren().addAll(gamesLabel, tableView);

            // Create both TableViews side by side in an HBox
            HBox hbox = new HBox(gamesVBox, friendsVBox);
            HBox.setHgrow(gamesVBox, Priority.ALWAYS); // Allow gamesVBox to grow horizontally            
            rootLayout.getChildren().addAll(hbox);
            hbox.relocate(0,40);
            

            return rootLayout;
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        return new Pane();
    }

}
