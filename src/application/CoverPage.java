package application;

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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.Date;

public class CoverPage extends Application {
	
	
	
	TextField playerID;
	TextField playerPassword;
	public int gamerID;    
	private ResultSet dbresultSet;
	
	@Override
	public void start(Stage primaryStage) {
		try {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		
		pane.add(new Label("Insert your ID: "), 0, 0);
		playerID = new TextField();
		pane.add(playerID, 1, 0);
		
		// Add a button to Update player by ID
		Button btnLoggin = new Button("Log in");
		pane.add(btnLoggin, 3, 0,1,2);
		btnLoggin.setPrefWidth(150);
		btnLoggin.setPrefHeight(50);
		
		pane.add(new Label("Insert your password: "), 0, 1);
		playerPassword = new TextField();
		pane.add(playerPassword, 1, 1);				
		
		String sql = "SELECT GAMETITLE,GAMEDEVELOPER,GAMERELEASEDATE,GAMEPRICE FROM GAME FETCH FIRST 10 ROWS ONLY";
        PreparedStatement statement = DbConn.connection.prepareStatement(sql);
        ResultSet dbresultSet = statement.executeQuery();
		
		TableView tableView = new TableView();		
		
		while (dbresultSet.next()) {
			
            String gameTitle = dbresultSet.getString("GAMETITLE");
            String gameDeveloper = dbresultSet.getString("GAMEDEVELOPER");
            Date gameReleaseDate = dbresultSet.getDate("GAMERELEASEDATE");
            Double gamePrice = dbresultSet.getDouble("GAMEPRICE");            
            
            
            Game game = new Game(gameTitle, gameDeveloper, gameReleaseDate, gamePrice);
            tableView.getItems().add(game);}		
		
        TableColumn<Game, String> gameTitleCol = new TableColumn<>("Game Title");
        gameTitleCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getGameTitle()));
        
        
        TableColumn<Game, String> gameDeveloperCol = new TableColumn<>("Game Developer");
        gameDeveloperCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getGameDeveloper()));
        
        TableColumn<Game, Date> releaseDateCol = new TableColumn<>("Release Date");
        releaseDateCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getGameReleaseDate()));
        
        TableColumn<Game, Double> gamePriceCol = new TableColumn<>("Price");
        gamePriceCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getGamePrice()));
                     
        TableColumn<Game, Button> btnPayment = new TableColumn<>("Buy");
        btnPayment.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().getBtnPayment()));
        
        pane.setStyle(" -fx-background-color: linear-gradient(to bottom, #1dbbdd44, #93f9b944);");
        
        gameTitleCol.setStyle("-fx-alignment: CENTER;");
        gameDeveloperCol.setStyle("-fx-alignment: CENTER;");
        releaseDateCol.setStyle("-fx-alignment: CENTER;");
        gamePriceCol.setStyle("-fx-alignment: CENTER;");
        btnPayment.setStyle("-fx-alignment: CENTER;"); 
        
        
        gameTitleCol.setPrefWidth(90);
        gameDeveloperCol.setPrefWidth(120);
        releaseDateCol.setPrefWidth(100);
        gamePriceCol.setPrefWidth(55);
        btnPayment.setPrefWidth(70);
        tableView.setFixedCellSize(33);        
        tableView.getColumns().addAll(gameTitleCol, gameDeveloperCol,releaseDateCol, gamePriceCol,btnPayment);
        
        pane.add(tableView,0,4,4,1);
        
        dbresultSet.close();
        statement.close();
        
        btnLoggin.setOnAction(e -> {
        	
        	try {
                // Check the user ID here, and if it's valid, create the new scene
                gamerID = Integer.parseInt(playerID.getText());
                String password = playerPassword.getText();
                // Get the user ID from the TextField

                // Assuming you have a method to validate the user ID in the DbConn class
                boolean isValidUserID = DbConn.isValidLogin(gamerID,password);
                System.out.println(isValidUserID);

                if (isValidUserID) {
                    // Create the new scene for the logged-in user
                    // Replace "LoggedInPage" with the name of the class that represents the logged-in page
                    Scene loggedInScene = new Scene(new Library().getLayout(gamerID), 800, 600);

                    // Set the new scene as the stage's scene
                    primaryStage.setScene(loggedInScene);
                } else {
                    // Display an error message or take appropriate action if the user ID is invalid
                    System.out.println("Invalid user ID. Please try again.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        	
        });
		
        
        
        
		//Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("Welcome to Steam");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		//DbConn.connection.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}


