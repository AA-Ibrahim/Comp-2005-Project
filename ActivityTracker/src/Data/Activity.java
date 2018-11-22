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
	String userID;
	String date;
	Double time;
	Double distance;
	Double altitudeGain;
	Double altitudeLoss;
	Double pace;
	Double caloriesBurned;
	boolean isValid;
	DatabaseProxy m;
	
	/*
	 * param
	 */
	public Activity(DatabaseProxy m, String activityType, String userId, String date, Double time, Double distance, Double altitudeGain, Double altitudeLoss, Double pace, Double caloriesBurned ) {
		this.m = m;
		this.activityType = activityType;
		this.userID = userId;
		this.time = time;
		this.distance = distance;
		this.altitudeGain = altitudeGain;
		this.altitudeLoss = altitudeLoss;
		this.date = date;
		this.pace = pace;
		this.caloriesBurned = caloriesBurned;
		this.isValid = this.validate();
	}

	public final String SHOW_RECORDS = "SELECT rowid, * FROM ACTIVITY " + "WHERE rowid = " + this.userID + ";";

	public final String INSERT_ACTIVITY = "INSERT INTO ACTIVITY VALUES(" + this.activityType + ", " +  this.userID + ", " + this.date + ", " + this.time + ", "
			 + this.distance + ", " + this.altitudeGain + ", "  + this.altitudeLoss + ", " +  this.pace + ", " + this.caloriesBurned + ");";

	

	public String getActivityType() {
		return activityType;
	}



	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}



	public String getUserID() {
		return userID;
	}



	public void setUserID(String userID) {
		this.userID = userID;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
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



	public Double getAltitudeGain() {
		return altitudeGain;
	}



	public void setAltitudeGain(Double altitudeGain) {
		this.altitudeGain = altitudeGain;
	}



	public Double getAltitudeLoss() {
		return altitudeLoss;
	}



	public void setAltitudeLoss(Double altitudeLoss) {
		this.altitudeLoss = altitudeLoss;
	}



	public Double getPace() {
		return pace;
	}



	public void setPace(Double pace) {
		this.pace = pace;
	}



	public Double getCaloriesBurned() {
		return caloriesBurned;
	}



	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}



	public boolean isValid() {
		return isValid;
	}



	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}



	public DatabaseProxy getM() {
		return m;
	}



	public void setM(DatabaseProxy m) {
		this.m = m;
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
