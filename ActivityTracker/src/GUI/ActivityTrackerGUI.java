package GUI;

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

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Data.Activity;
import Data.DatabaseProxy;
import Data.User;
import GUI.Panels.ActivityData;
import GUI.Panels.ContextPanel;
import GUI.Panels.MyDevicesPanel;
import GUI.Panels.StatusPanel;
import GUI.Panels.UserDetailsPanel;
import GUI.Panels.UserLoginPanel;
import GUI.Panels.UserRegistrationPanel;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class ActivityTrackerGUI extends JFrame {

	private static final long serialVersionUID = 7637719486842764464L;
	private UserDetailsPanel userDetails;
	private UserLoginPanel userLogin;
	private UserRegistrationPanel userRegistration;
	private ContextPanel context;
	private StatusPanel status;
	private MyDevicesPanel myDevices;
	private ActivityData activityData;
	private DatabaseProxy databaseProxy;

	/**
	 * Constructor for the GUI
	 */
	public ActivityTrackerGUI() {
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
		activityData = new ActivityData();
		userLogin = new UserLoginPanel();
		userRegistration = new UserRegistrationPanel();
		myDevices = new MyDevicesPanel();
		context = new ContextPanel();
	}

	// Initialize panel listeners
	private void initializePanelListeners() {
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
			changeToActivityLayout();
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
				changeToActivityLayout();
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
				changeToActivityLayout();
			}

			// Otherwise the user was not valid
			else {
				// TODO: Use a better error message
				status.setStatus("Create failed");
			}
		});

		myDevices.addImportListener(ae -> {
			JFrame j = (JFrame) SwingUtilities.getWindowAncestor((Component) ae.getSource());
			FileDialog fd = new FileDialog(j, "Test", FileDialog.LOAD);
			fd.setFile("*.csv");
			fd.setVisible(true);
			String filename = fd.getFile();
			if (filename == null) {
				status.setStatus("You cancelled the choice");
			} else {
				BufferedReader reader;
				try {
					Charset charset = Charset.forName("UTF-8");
					User u = userDetails.getUser();
					String userid = u.getId();
					String activityType = "run";
					String sdate = "";// = Long.toString(date);
					double time = 0;
					double distance = 0;
					double altitudeGain = 0;
					double altitudeLoss = 0;
					double pace = 0;
					double calories = 0;
					double altitude = 0;
					double altitudePast = 0;
					double altitudeDifference = 0;
					int lineNum = 0;
					String[] words;
					Activity a;

					// Necessary evil to make format file to our specification
					FileInputStream is1=new FileInputStream(fd.getDirectory() + "/" + fd.getFile());
					File file = new File("../ActivityTracker/src/Data/input2.csv");
					FileInputStream is2=new FileInputStream(file);
					SequenceInputStream is=new SequenceInputStream(is1, is2);
					reader = new BufferedReader(new InputStreamReader(is, charset));
					String line = reader.readLine();
					
					while (line != null) {
						
						words =  line.split(",");
						
						if (words[0].equals("0") && lineNum > 0) {

							a = new Activity(databaseProxy, activityType, userid, sdate, time, distance,
									altitudeGain, altitudeLoss, pace, calories);

							altitudeGain = 0;
							altitudeLoss = 0;
						}
						else {
							time = Double.valueOf(words[0]);
							distance = Double.valueOf(words[1]);
							altitude = Double.valueOf(words[2]);	
							sdate = words[3];
							pace = Math.round(100.0*distance/time)/100.0;
							calories = 80 * distance;
							altitudeDifference = altitude - altitudePast;
							
							if (altitudeDifference >= 0) {
								altitudeGain += altitudeDifference;
							} else {
								altitudeLoss += altitudeDifference;
							}
							altitudePast = altitude;
						}
						
						line = reader.readLine();
						lineNum++;
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.exit(-1);

				}
				
				System.out.println("Import successful.");

			}
			this.changeToActivityLayout();
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
	private void changeToActivityLayout() {
		framePreInitialize();
		updateTable();
		add(activityData, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.ACTIVITY);
		setSize(1024, 768);
		framePostInitialize();
	}

	// This should probably be somewhere else?
	private void updateTable() {
		JTable jtActivity = activityData.getTable();
		String[][] data = new Activity(databaseProxy, userDetails.getUser()).getRecords();
		DefaultTableModel dm = (DefaultTableModel) jtActivity.getModel();
		dm.setRowCount(0);
		for (String[] rowData : data)
			dm.addRow(rowData);
		activityData.setJTable(jtActivity);
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

	//////////////////////////////////////////////////////////////////////
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
