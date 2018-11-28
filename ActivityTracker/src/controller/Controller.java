package controller;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.SequenceInputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Activity;
import model.User;
import view.ActivityPanel;
import view.ContextPanel;
import view.MyDevicesPanel;
import view.StatusPanel;
import view.UserDetailsPanel;
import view.UserLoginPanel;
import view.UserRegistrationPanel;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

/**
 * This object acts as a middle man between the view and the model
 */

public class Controller extends JFrame {

	private static final long serialVersionUID = 7637719486842764464L;
	private UserDetailsPanel userDetails;
	private UserLoginPanel userLogin;
	private UserRegistrationPanel userRegistration;
	private ContextPanel context;
	private StatusPanel status;
	private MyDevicesPanel myDevices;
	private ActivityPanel activityData;
	private DatabaseProxy databaseProxy;

	/**
	 * Constructor for the GUI
	 */
	public Controller() {
		setTitle("Catch-up! Standard Edition");
		setResizable(false);
		initializeFields();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initializePanelListeners();
		changeToSigninLayout();
	}

	/**
	 * Initialize the class fields
	 */
	private void initializeFields() {
		databaseProxy = new DatabaseProxy("database.sqlite");
		initializePanels();
	}

	/**
	 * Initialize GUI panels
	 */
	private void initializePanels() {
		userDetails = new UserDetailsPanel();
		status = new StatusPanel();
		activityData = new ActivityPanel();
		userLogin = new UserLoginPanel();
		userRegistration = new UserRegistrationPanel();
		myDevices = new MyDevicesPanel();
		context = new ContextPanel();
	}

	// Initialize panel listeners
	private void initializePanelListeners() {		
		//////////////////////////////////////////////////////////////////////
		// Listeners for the Context panel
		// Range Button Listener
		// ....
		context.addRangeListener(ae -> {
			Date a1 = context.getRange1();
			Date a2 = context.getRange2();
			
			// If no start date specified, choose the beginning of the epoch
			if(a1==null) { a1 = new Date(1000*60*60*24*365); }
			
			// If no end date specified, choose the end of time
			if(a2==null) { a2 = new Date(Long.MAX_VALUE - 1000*60*60*24*365); }
			
			// Fix date range
			changeToActivityLayout((a1.compareTo(a2) < 0)?a1:a2, (a1.compareTo(a2)<0)?a2:a1);
		});
		
		/////////////////////////////////////////////////////////////////////
		// Logout Button Listener
		// Logs out current user
		userDetails.addImportDataListener(ae -> {
			changeToImportLayout();
		});
		// MyActivity Button Listener
		// Switches to my activity
		userDetails.addMyActivityListener(ae -> {
			changeToActivityLayout(null, null);
		});
		
		//////////////////////////////////////////////////////////////////////
		// Listeners for the UserLogin panel
		// Logout Button Listener
		// Logs out current user
		userDetails.addLogoutListener(ae -> {
			userDetails.setUser(null);
			userLogin.clearFields(); // remove the password
			changeToSigninLayout();
		});
		// Logout Button Listener
		// Logs out current user
		userDetails.addImportDataListener(ae -> {
			changeToImportLayout();
		});
		// MyActivity Button Listener
		// Switches to my activity
		userDetails.addMyActivityListener(ae -> {
			changeToActivityLayout(null, null);
		});

		//////////////////////////////////////////////////////////////////////
		// Listeners for the UserLogin panel
		// "Create Account" Button Listener
		// Changes window layout to UserRegistrationPanel
		userLogin.addCreateAccountListener(ae -> {
			changeToRegistrationLayout();
		});
		// "Sign In" Button Listener
		// Creates a user object
		userLogin.addSignInListener(ae -> {

			// Create a object object that corresponds to the fields
			// It'll validate itself, and we can check this validation
			User u = new User(databaseProxy, userLogin.getUsername(), userLogin.getPassword());

			// If this user object is valid wrt to the db
			if (u.getValid()) {
				// Login is successful
				userDetails.setUser(u);
				status.setStatus("Login successful");
				changeToActivityLayout(null, null);
			}

			else {
				// TODO: Use a better error message
				status.setStatus("Login failed");
				userLogin.clearFields();
			}
		});

		//////////////////////////////////////////////////////////////////////
		// Listeners for UserRegistration panel
		// "Sign In" Button listener
		userRegistration.addSigninListener(ae -> {
			changeToSigninLayout();
		});
		// "Create Account" Button listener
		// Create a user object
		userRegistration.addCreateAccountListener(ae -> {

			// Return if the fields are blank
			if (userRegistration.getFirstName().equals("") || userRegistration.getLastName().equals("")
					|| userRegistration.getUsername().equals("") || userRegistration.getPassword().equals("")) {
				status.setStatus("Error: Must fill in all fields");
				return;
			}

			// Create a new object to validate itself
			User u = new User(databaseProxy, userRegistration.getUsername(), userRegistration.getPassword(),
					userRegistration.getFirstName(), userRegistration.getLastName(), null);

			// If this user object is valid wrt to the db
			if (u.getValid()) {
				// Login is successful
				userDetails.setUser(u);
				status.setStatus("Login successful");
				userRegistration.clearFields();
				changeToActivityLayout(null, null);
			}

			// Otherwise the user was not valid
			else {
				status.setStatus("Creating User Failed");
			}
		});
		// select the file to import data from
		myDevices.addImportListener(ae -> {
			JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) ae.getSource());
			FileDialog fd = new FileDialog(j, "Test", FileDialog.LOAD);
			fd.setFile("*.csv");
			fd.setVisible(true);
			String filename = fd.getFile();
			
			// if no file selected
			if (filename == null) {
				status.setStatus("You cancelled the choice");
			} else {
				// read the file
				BufferedReader reader;
				try {
					//initalize variables
					Charset charset = Charset.forName("UTF-8");
					User u = userDetails.getUser();
					String userid = u.getId(), activityType = "run", sdate = "";
					Date ddate;
					long date=0;
					double time=0, distance=0, altitudeGain=0, altitudeLoss=0, pace=0, 
							calories=0, altitude=0, altitudePast=0, altitudeDifference=0;
					int lineNum = 0;
					String[] words;
					Activity a;

					// Concatenate file with 0s at the end from input2.csv to ensure there is a visible end to run object
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					FileInputStream is=new FileInputStream(fd.getDirectory() + "/" + fd.getFile());
					reader = new BufferedReader(new InputStreamReader(is));
					// read the first line
					String line = reader.readLine();
					
					// loop through all lines
					while (line != null) {
						
						words =  line.split(",");
						
						// if start of a new run
						if (words[0].equals("0") && lineNum > 0) {

							// create run object and add to database
							a = new Activity(databaseProxy, activityType, userid, date, time, distance,
									altitudeGain, altitudeLoss, pace, calories);

							// reset altitude
							altitudeGain = 0;
							altitudeLoss = 0;
						}
						else {
							// set all the values
							time = Double.valueOf(words[0]);
							distance = Double.valueOf(words[1]);
							altitude = Double.valueOf(words[2]);	
							sdate = words[3];
							ddate = sdf.parse(sdate);
							date = ddate.getTime();
							pace = Math.round(100.0*distance/time)/100.0;
							calories = Math.round(80.0*distance)/1000.0;
							// calculate altitude difference
							altitudeDifference = altitude - altitudePast;
							
							if (altitudeDifference >= 0) {
								altitudeGain += Math.round(100.0*altitudeDifference)/100.0;
							} else {
								altitudeLoss += Math.round(100.0*altitudeDifference)/100.0;
							}
							altitudePast = altitude;
						}
						
						// get next line data
						line = reader.readLine();
						lineNum++;
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);

				}
				
				System.out.println("Import successful.");

			}
			// refresh activity layout panel
			this.changeToActivityLayout(null, null);
		});
	}

	//////////////////////////////////////////////////////////////////////
	// Changes the frame to reflect the registration layout
	private void changeToRegistrationLayout() {
		framePreInitialize();
		add(userRegistration, BorderLayout.EAST);
		framePostInitialize();
	}

	// Changes the frame to reflect the sign in layout
	private void changeToSigninLayout() {
		framePreInitialize();
		add(userLogin, BorderLayout.EAST);
		framePostInitialize();
	}

	// Changes the frame to reflect the activity layout
	private void changeToActivityLayout(Date start, Date end) {
		framePreInitialize();
		updateTable(start, end);
		add(activityData, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.ACTIVITY);
		framePostInitialize();
	}

	// This method updates the table as well as the statistics panel below
	private void updateTable(Date start, Date end) {
		// initialize variables
		JTable jtActivity = activityData.getTable();
		String[][] data;
		double totalTime=0, totalDistance=0, totalAltitudeGain=0, totalAltitudeLoss=0, totalPace=0, totalCaloriesBurned=0, numberOfLines=0;
		double averageTime, averageDistance, averageAltitudeGain, averageAltitudeLoss, averagePace, averageCaloriesBurned;
		String statistics;
		
		// if given a range, get runs from that range, else get all runs for the user
		if(start==null)
			data = new Activity(databaseProxy, userDetails.getUser()).getRecords();
		else {
			data = new Activity(databaseProxy, userDetails.getUser()).getRecordsFromRange(start, end);
		}
		
		// populate the table with data
		DefaultTableModel dm = (DefaultTableModel) jtActivity.getModel();
		dm.setRowCount(0);
		// add each run to the table
		for (String[] rowData : data) {
			totalTime = totalTime + Double.valueOf(rowData[1]);
			totalDistance = totalDistance + Double.valueOf(rowData[2]);
			totalAltitudeGain = totalAltitudeGain + Double.valueOf(rowData[3]);
			totalAltitudeLoss = totalAltitudeLoss + Double.valueOf(rowData[4]);
			totalPace = totalPace + Double.valueOf(rowData[5]);
			totalCaloriesBurned = totalCaloriesBurned + Double.valueOf(rowData[6]);
			numberOfLines++;
			
			// Create a vector consisting of the proper data types for the JTable
			Vector k = new Vector();
			
			k.add(rowData[0]);
			
			for(int i=1; i<7; i++)
				k.add(Math.round(100.0*Double.valueOf(rowData[i]))/100.0);
			dm.addRow(k);
		}
		// calculate the average results for the statistics
		averageTime = totalTime /numberOfLines;
		averageDistance = totalDistance/numberOfLines;
		averageAltitudeGain = totalAltitudeGain/numberOfLines;
		averageAltitudeLoss = totalAltitudeLoss/numberOfLines;
		averagePace = totalPace/numberOfLines;
		averageCaloriesBurned = totalCaloriesBurned/numberOfLines;
		
		// add the values to statistics data
		DecimalFormat df = new DecimalFormat("###.##");
		
		statistics = "Avg Time = " + df.format(averageTime) 
				+ ", Avg Dist = " + df.format(averageDistance)
				+ ", Avg Alt Gain = " + df.format(averageAltitudeGain)
				+ ", Avg Alt Loss = " + df.format(averageAltitudeLoss)
				+ ", Avg Pace = " + df.format(averagePace)
				+ ", Avg Cal = " + df.format(averageCaloriesBurned);
		
		// update values to table and refresh
		activityData.setJTable(jtActivity);
		activityData.setStatistics(statistics);
		jtActivity.setAutoCreateRowSorter(true);
		dm.fireTableDataChanged();
		jtActivity.repaint();
	}

	// Changes the frame to the import activity layout
	private void changeToImportLayout() {
		framePreInitialize();
		add(myDevices, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.DEVICES);
		framePostInitialize();
	}

	// Pre-initialize the frame
	private void framePreInitialize() {
		getContentPane().removeAll();
		add(userDetails, BorderLayout.NORTH);
		add(status, BorderLayout.SOUTH);
	}

	// Post-initialization of the frame
	private void framePostInitialize() {
		pack();
		repaint();
	}
}
