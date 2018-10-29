package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
/**
 * This object acts as a middle man between the GUI and the database
 * @author k47swp
 *
 */
public class DatabaseMediator {

	String databaseFileName;
	Connection connection;
	Statement statement;

	/**
	 * This is the default constructor. it simply initializes the class fields
	 * to null values. 
	 */
	DatabaseMediator() {
		databaseFileName = null;
		connection = null;
		statement = null;
	}
	
	/**
	 * This constructor creates this object, and connects to a database
	 * specified by a particular filename. This constructor is simply
	 * an overloaded version of the default constructor.
	 *  
	 * @param fname This is the name of the local file that has the database.
	 *  
	 */
	DatabaseMediator(String fname) {
		this();
		connect(fname);
	}

	/**
	 * Connect to a particular database specified.
	 * 
	 * @param fname This is the name of the local file that has the database.
	 */
	void connect(String fname) {
		// Open the connection to the database
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:" + fname);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	/**
	 * Closes the connection to the database.
	 */
	void close() {
		
		// If we aren't actually connected to a db, then nothing to do.
		if(connection==null) { return; }
		
		// Close the connection and clean up reference
		try {
			connection.close();
			connection=null;
			System.out.println("Closed db");
		} catch (SQLException e) {
			
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Creates an example table, for reference
	 */
	void createExampleTable() {

		// Make sure that the database is open
		if (connection == null) {
			System.out.println("Database is not opened");
			return;
		}
		
		// Create an example table
		try {
			statement = connection.createStatement();
			String query = "CREATE TABLE COMPANY "
					+"(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           TEXT    NOT NULL, "
					+ " AGE            INT     NOT NULL, "
					+ " ADDRESS        CHAR(50), " + " SALARY         REAL)";
			statement.executeUpdate(query);
			statement.close();
			statement = null;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// Let the user know it happened
		System.out.println("Table created successfully");
	}

	/**
	 * Main method, used for debugging, not intended to be used in production
	 */
	public static void main(String[] args) {
		
		try {
			Files.deleteIfExists(Paths.get("test2.db"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseMediator databaseMediator = new DatabaseMediator("test2.db");
		databaseMediator.close();
		databaseMediator.connect("test2.db");
		databaseMediator.createExampleTable();
		databaseMediator.close();
	}
}
