package application;

import java.sql.*;
import javafx.application.Application;
import javafx.stage.Stage;	

public class DbConn extends Application
{
	public static Connection connection;
@Override
public void start(Stage primaryStage)
{
try
{
System.out.println("> Start Program ...");
Class.forName("oracle.jdbc.driver.OracleDriver");
System.out.println("> Driver Loaded successfully.");
//establish a connection
connection = DriverManager.getConnection("jdbc:oracle:thin:@199.212.26.208:1521:SQLD","COMP214_M23_zo_12", "password");

System.out.println("> Database connected successfully.");

if (connection != null) {
    System.out.println("> Database connected successfully.");

    // Call the method to launch the Graphical User Interface (GraphInterface)
    launchGraphInterface(primaryStage);
} else {
    System.err.println("> Error: Database connection is null.");
}
}
catch(Exception e)
{
e.printStackTrace();
}
}
//Separate method to launch GraphInterface to ensure connection is established before GUI is created
private void launchGraphInterface(Stage primaryStage) {
	CoverPage gui = new CoverPage();
    gui.start(primaryStage);
}

public static void main(String[] args) {
    launch(args);
}

public static boolean isValidLogin(int userID, String password) {
    try {
    	
        String sql = "SELECT GAMER_ID, GAMERPASSWORD FROM GAMER WHERE GAMER_ID = ? AND GAMERPASSWORD = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, userID);
        statement.setString(2, password);

        // Execute the query and get the result set
        ResultSet resultSetLoggin = statement.executeQuery();

        // Check if the user ID exists in the database
        if (resultSetLoggin.next()) {
            Integer storedUsername = resultSetLoggin.getInt("GAMER_ID");
            String storedPassword = resultSetLoggin.getString("GAMERPASSWORD");
           System.out.println(storedUsername);
           System.out.println(storedPassword);

            // Compare the provided password with the stored password
            if (password.equals(storedPassword)) {
                // Login is valid
                return true;
            }
        }

        // Close resources
        resultSetLoggin.close();
        statement.close();
        
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Invalid login
    return false;
}
}