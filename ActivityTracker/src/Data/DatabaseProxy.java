package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

import GUI.Panels.UserRegistrationPanel;

/**
 * This object acts as a middle man between the GUI and the database
 * 
 * @author k47swp
 *
 */
public class DatabaseProxy {

	String databaseFileName;
	Connection connection;
	Statement statement;

	/**
	 * This is the default constructor. it simply initializes the class fields to
	 * null values.
	 */
	DatabaseProxy() {
		databaseFileName = null;
		connection = null;
		statement = null;
	}

	/**
	 * This constructor creates this object, and connects to a database specified by
	 * a particular filename. This constructor is simply an overloaded version of
	 * the default constructor.
	 * 
	 * @param fname This is the name of the local file that has the database.
	 * 
	 */
	public DatabaseProxy(String fname) {
		this();
		this.databaseFileName = fname;
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
		if (connection == null) {
			return;
		}

		// Close the connection and clean up reference
		try {
			connection.close();
			connection = null;
			System.out.println("Closed db");
		} catch (SQLException e) {

			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Creates a table
	 */
	void executeQuery(String query) {

		// Make sure that the database is open
		if (connection == null) {
			System.out.println("Database is not opened");
			return;
		}

		// Create a table
		try {
			statement = connection.createStatement();
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
	
	// TODO: Change to a constant string
	// NOTE: ROWID is an implicit primary key. You must ask for it specifically in the select command.
	
	//	sqlite> insert into USER VALUES("henrique_clone", "dosp", "henrique", "password1", "dsakkdlsaklkdsa");
	//	sqlite> select rowid,* from user;
	
	//	1|henrique|dosp|henrique|password1|dsakkdlsaklkdsa
	//	2|henrique_clone|dosp|henrique|password1|dsakkdlsaklkdsa
	
	// VS...
	
	//	sqlite> select * from user;
	//	henrique|dosp|henrique|password1|dsakkdlsaklkdsa
	//	henrique_clone|dosp|henrique|password1|dsakkdlsaklkdsa

	String userTableCreationString() {
		return
			"CREATE TABLE IF NOT EXISTS USER  ("
			+ " firstName	TEXT NOT NULL, "
			+ " lastName    TEXT NOT NULL,"
			+ " username    TEXT NOT NULL,"
			+ " password	TEXT NOT NULL,"
			+ " image		BLOB" + ");";
	}
	
	// TODO: Change to a constant string
	String activityTableCreationString() {
		return
			"CREATE TABLE IF NOT EXISTS ACTIVITY  ("
			+ " userId		INTEGER, "
			+ " time		INTEGER,"
			+ " distance	REAL,"
			+ " altitude	REAL"
			+ ");";
	}
	
	/**
	 * Main method, used for debugging, not intended to be used in production
	 */
	public static void main(String[] args) {
		System.out.println(Paths.get("test2.db"));
		try {
			Files.deleteIfExists(Paths.get("test2.db"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DatabaseProxy databaseProxy = new DatabaseProxy("test2.db");
		databaseProxy.close();
		databaseProxy.connect("test2.db");
		databaseProxy.executeQuery(databaseProxy.userTableCreationString());
		databaseProxy.executeQuery(databaseProxy.activityTableCreationString());
		databaseProxy.close();
	}
}
