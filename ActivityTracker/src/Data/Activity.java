package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import javax.imageio.ImageIO;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class Activity implements DBHandler {

	String activityType;
	String date;
	String username;
	Double time;
	Double distance;
	Double altitude;
	boolean isValid;
	BufferedImage image;
	DatabaseProxy m;
	String encodedImage = "no";

	/*
	 * param
	 */
	public Activity(DatabaseProxy m, String activityType, String date, String username, Double time, Double distance, Double altitude, Image image) {
		this.m = m;
		this.activityType = activityType;
		this.date = date;
		this.username = username;
		this.time = time;
		this.distance = distance;
		this.altitude = altitude;
		// this.image = null;
		this.isValid = this.validate();
	}

	public final String SHOW_RECORDS = "SELECT rowid, * FROM ACTIVITY " + "WHERE username = " + this.username + ";";

	public final String INSERT_ACTIVITY = "INSERT INTO ACTIVITY VALUES(" + this.activityType + ", " + this.date + ", "
			+ this.username + ", " + this.time + ", " + this.distance + ", " + this.altitude + ", " + encodedImage + ");";

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public DatabaseProxy getM() {
		return m;
	}

	public void setM(DatabaseProxy m) {
		this.m = m;
	}

	public String getEncodedImage() {
		return encodedImage;
	}

	public void setEncodedImage(String encodedImage) {
		this.encodedImage = encodedImage;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		// Run the query
		ResultSet rs = m.executeQuery(SHOW_RECORDS);
		// There were no rows
		try {
			if(rs.next() == false) { 
				System.out.println("No Records Exist");
				return false; 
				}
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.exit(-1);

		}
		System.out.print("There were records");
		return true;
	}

	
}
