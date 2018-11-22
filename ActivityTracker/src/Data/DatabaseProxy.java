package Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

import GUI.Panels.UserRegistrationPanel;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

/**
 * This object acts as a middle man between the GUI and the database
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

	private static final String CREATE_USER_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS USER  ("
			+ " firstName	TEXT NOT NULL, " + " lastName    TEXT NOT NULL," + " username    TEXT NOT NULL,"
			+ " password	TEXT NOT NULL," + " image		BLOB" + ");";

	private static final String CREATE_ACTIVITY_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS ACTIVITY  ("
			+ " activityType	TEXT NOT NULL, " + " userID	TEXT NOT NULL, " + " date	INTEGER," + " time	REAL," 
			+ " distance	REAL," + " altitudeGain	REAL,"  + " altitudeLoss	REAL," + " pace	REAL," + " caloriesBurned	REAL" + ");";

	/**
	 * This constructor creates this object, and connects to a database specified by
	 * a particular filename. This constructor is simply an overloaded version of
	 * the default constructor.
	 * 
	 * @param fname This is the name of the local file that has the database.
	 */
	public DatabaseProxy(String fname) {
		this();
		this.databaseFileName = fname;
		connect(fname);
		this.executeUpdate(CREATE_USER_TABLE_QUERY);
		this.executeUpdate(CREATE_ACTIVITY_TABLE_QUERY);
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
	 * This runs an update query on the database
	 * 
	 * @param query This is the SQL update query to run.
	 */
	int executeUpdate(String query) {
		int affected_rows = 0;

		// Make sure that the database is open
		if (connection == null) {
			System.out.println("Database is not open");
			System.exit(0);
		}

		// Create a statement and execute the query
		try {
			statement = connection.createStatement();
			affected_rows = statement.executeUpdate(query);
			statement.close();
			statement = null;
		}

		// If the query fails, die.
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Let the user know it happened
		System.out.println("Query '" + query + "' successful.");
		System.out.println("Affected " + affected_rows + " rows.");

		return affected_rows;
	}

	/**
	 * Executes a query against the database
	 * 
	 * @param query This is the SQL statement to run.
	 * @return A ResultSet for the specified query.
	 */
	ResultSet executeQuery(String query) {
		ResultSet rs = null;
		
		// Check to see if our DB is open
		if (connection == null) {
			System.out.println("Database is not open");
			System.exit(0);
		}

		// Try to execute the query
		try {
			//statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			statement = connection.createStatement();
			rs = statement.executeQuery(query);
		}
		
		// The query failed
		catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// The query was successful
		System.out.println("Query '" + query + "' successful.");
		return rs;
	}

	/**
	 * Main method, used for testing and debugging. Not intended to be used in
	 * production
	 * 
	 * @param args
	 */
}
