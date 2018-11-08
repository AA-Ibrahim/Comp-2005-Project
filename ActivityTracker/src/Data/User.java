package Data;

import java.awt.Image;

public class User implements DBHandler {

	String username;
	String password;
	String firstName;
	String lastName;
	boolean isValid;
	Image image;
	DatabaseMediator m;
	
	User(DatabaseMediator m, String username, String password, String firstName, String lastName, Image image) {
		this.m = m;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.image = image;
		isValid = this.validate();
	}
	
	User(String username, String password) {
		this.username = username;
		this.password = password;
		isValid = this.validate();
	}
	
	public boolean writeToDB() {
		return false;
	}
	
	public boolean validate() {
		return false;
	}

	
}
