package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.imageio.ImageIO;

import controller.DatabaseProxy;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class User implements DBHandler {

	private String username;
	private String id;
	private String password;
	private String firstName;
	private String lastName;
	boolean isValid;
	private BufferedImage image;
	DatabaseProxy m;
	String encodedImage = "no";

	/**
	 * Use this constructor when creating a new user
	 * 
	 * @param m         Database proxy object
	 * @param username  New user's username
	 * @param password  New user's password
	 * @param firstName New user's first name
	 * @param lastName  New user's last name
	 * @param image     not implemented
	 */
	public User(DatabaseProxy m, String username, String password, String firstName, String lastName, Image image) {
		this.m = m;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isValid = this.validate();
	}

	/**
	 * Use this constructor when validating an existing user
	 * 
	 * @param m        The database proxy object
	 * @param username The username
	 * @param password The password
	 */
	public User(DatabaseProxy m, String username, String password) {
		this.m = m;
		this.username = username;
		this.password = password;
		this.firstName = null;
		this.lastName = null;
		this.image = null;
		this.isValid = validate();
	}

	/**
	 * Validation function for this object's current instance against the database.
	 * 
	 * Used by the constructor for two reasons 1. You would like to validate a new
	 * user 2. You would like to validate an existing user.
	 * 
	 * If the firstName class field is null, we are assuming that we are validating
	 * an existing user, otherwise we are creating a user in the db.
	 * 
	 * @return true or false, depending if the user is valid in the db
	 */
	public boolean validate() {

		// CASE 1: If we are validating an existing user, the firstName will be null
		if (this.firstName == null) {

			// Build the query
			String query = "SELECT rowid, * FROM USER " + "WHERE username = '" + this.username + "' "
					+ "AND password = '" + this.password.hashCode() + "';";

			// Run the query
			ResultSet rs = m.executeQuery(query);

			// There were no rows
			try {
				if (!rs.next())
					return false;
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}

			// Try to set the class fields from the first row of the query
			// result
			try {
				this.setId(rs.getString("rowid"));
				this.setFirstName(rs.getString("firstName"));
				this.setLastName(rs.getString("lastName"));
				this.setUsername(rs.getString("username"));
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}

			// If the class field ID is not null, the we have a valid user
			if (this.getId() != null) {
				return true;
			}

			// If we got here, our user must not exist
			return false;
		}

		// CASE 2: We are creating a user
		// Try to add this user to the db
		String query = "INSERT INTO USER VALUES('" + this.firstName + "', '" + this.lastName + "', " + "'"
				+ this.username + "', '" + this.password.hashCode() + "', '" + "null" + "');";
		System.out.println(query);
		m.executeUpdate(query);

		// Check to see if the created user exists by pretending that we are logging in
		// Setting the first name to null will trigger the login validation
		this.setFirstName(null);

		// Return the result of the login validation
		return validate();
	}

	//////////////////////////////////////////////////////////////////////////
	// Getters and setters

	public boolean getValid() {
		return isValid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BufferedImage getImage() {
		return image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
