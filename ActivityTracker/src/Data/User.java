package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

public class User implements DBHandler {

	String username;
	String password;
	String firstName;
	String lastName;
	boolean isValid;
	BufferedImage image;
	DatabaseProxy m;
	
	User(DatabaseProxy m, String username, String password, String firstName, String lastName, Image image) {
		this.m = m;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		//this.image = null;
		isValid = this.validate();
	}
	
	User(String username, String password) {
		this.username = username;
		this.password = password;
		this.firstName = null;
		this.lastName = null;
		this.image = null;
		isValid = this.validate();
	}
	
	public boolean writeToDB() {
		return false;
	}
	
	public boolean validate() {
//	http://www.sqlitetutorial.net/sqlite-select/
//		SELECT DISTINCT column_list
//		FROM table_list
//		  JOIN table ON join_condition
//		WHERE row_filter
//		ORDER BY column
//		LIMIT count OFFSET offset
//		GROUP BY column
//		HAVING group_filter;
		
		// CASE 1: If we are validating an existing user, the firstName will be null
		if(this.firstName == null) {
			String query = "SELECT rowid, * FROM USER "
					+ "WHERE username = " + this.username + ", "
					+ "WHERE password = " + this.password + ";";
			// need a function to execute query and see if there is a matching row
		}
		
		// CASE 2: We are creating a user
		
		// Ugly hack to store image into a string
		// DOESNT WORK, FIX LATER
		//ByteArrayOutputStream os = new ByteArrayOutputStream();
		//ImageIO.write(image, "png", os);
		//String encodedImage = new String(Base64.getEncoder().encodeToString(os))
		String encodedImage = "no";
		String query = "INSERT INTO USER VALUES("
				+ this.firstName + ", "
				+ this.lastName + ", "
				+ this.username + ", "
				+ this.password + ", "
				+ encodedImage + ");";
		
		return false;
	}

	
}
