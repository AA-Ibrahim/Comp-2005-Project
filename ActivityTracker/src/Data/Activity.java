package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Vector;

import javax.imageio.ImageIO;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class Activity implements DBHandler {

	String activityType;
	String userID;
	long date;
	Double time;
	Double distance;
	Double altitudeGain;
	Double altitudeLoss;
	Double pace;
	Double caloriesBurned;
	boolean isValid;
	DatabaseProxy m;

	public Activity(DatabaseProxy m, String activityType, String userId, long date, double time, double distance,
			double altitudeGain, double altitudeLoss, double pace, double caloriesBurned) {
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

	public Activity(DatabaseProxy m, User u) {
		this.m = m;
		this.userID = u.getId();
	}

	@Override
	public boolean validate() {
		String insertQuery = "INSERT INTO ACTIVITY VALUES('" + this.activityType + "', '" + this.userID + "', " + this.date
				+ ", " + this.time + ", " + this.distance + ", " + this.altitudeGain + ", " + this.altitudeLoss + ", "
				+ this.pace + ", " + this.caloriesBurned + ");";
		System.out.println(insertQuery);
		m.executeUpdate(insertQuery);
		return true;
	}
	
	public String[][] getRecordsFromRange(Date begin, Date end){
		Vector<String[]> rowVector = new Vector<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date ddate;
		long ldate;
		
		String query = "SELECT * FROM ACTIVITY " + "WHERE userid = '" + this.userID 
		+ "' AND WHERE date BETWEEN " + begin.getTime() + " AND " + end.getTime() + ";";
		
		ResultSet rs = m.executeQuery(query);

		try {
			while (rs.next()) {
				String[] rowString = new String[7];
				ldate = Long.valueOf(rs.getString("date"));
				ddate = new Date(ldate);
				rowString[0] = sdf.format(ddate);
				rowString[1] = rs.getString("time");
				rowString[2] = rs.getString("distance");
				rowString[3] = rs.getString("altitudeGain");
				rowString[4] = rs.getString("altitudeLoss");
				rowString[5] = rs.getString("pace");
				rowString[6] = rs.getString("caloriesBurned");
				rowVector.add(rowString);
			}
			String[][] rowStrings = new String[rowVector.size()][7];

			for (int i = 0; i < rowVector.size(); i++)
				rowStrings[i] = rowVector.get(i);

			return rowStrings;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[0][0];		
	}

	public String[][] getRecords() {
		String query = "SELECT * FROM ACTIVITY " + "WHERE userid = '" + this.userID + "';";
		ResultSet rs = m.executeQuery(query);

		Vector<String[]> rowVector = new Vector<>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date ddate;
		long ldate;
		try {
			while (rs.next()) {
				String[] rowString = new String[7];
				ldate = Long.valueOf(rs.getString("date"));
				ddate = new Date(ldate);
				rowString[0] = sdf.format(ddate);
				rowString[1] = rs.getString("time");
				rowString[2] = rs.getString("distance");
				rowString[3] = rs.getString("altitudeGain");
				rowString[4] = rs.getString("altitudeLoss");
				rowString[5] = rs.getString("pace");
				rowString[6] = rs.getString("caloriesBurned");
				rowVector.add(rowString);
			}
			String[][] rowStrings = new String[rowVector.size()][7];

			for (int i = 0; i < rowVector.size(); i++)
				rowStrings[i] = rowVector.get(i);

			return rowStrings;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[0][0];

	}

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

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
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

}
