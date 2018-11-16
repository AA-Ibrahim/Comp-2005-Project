package GUI;

import GUI.Panels.ActivityData;
import Data.DatabaseProxy;
import Data.User;
import GUI.Panels.ContextPanel;
import GUI.Panels.MyDevicesPanel;
import GUI.Panels.StatusPanel;
import GUI.Panels.UserDetailsPanel;
import GUI.Panels.UserLoginPanel;
import GUI.Panels.UserRegistrationPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

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
		// "Create Account" Button Listener
		// Changes window layout to UserRegistrationPanel
		userLogin.addCreateAccountListener(ae -> {
			changeToRegistrationLayout();
		});

		// "Sign In" Button Listener
		// Creates a user object
		userLogin.addSignInListener(ae -> {
			User u = new User(databaseProxy, userLogin.getUsername(), userLogin.getPassword());

			// If this user object is valid wrt to the db
			if (u.isValid()) {
				// Login is successful
				// TODO: Change the UserDetailsPanel
				changeToActivityLayout();
			}

			else {
				// TODO: Use a better error message
				status.setStatus("Login failed");
				userRegistration.clearFields();
			}
		});

		//////////////////////////////////////////////////////////////////////
		// Listeners for UserRegistration panel
		// "Sign In" Button listener
		userRegistration.addSigninListener(ae -> {
			// TODO: Create User Object
			// TODO: Execute Query
			changeToSigninLayout();
		});

		// "Create Account" Button listener
		// Create a user object
		userRegistration.addCreateAccountListener(ae -> {
			// Reference for object: (DatabaseProxy m, String username, String password,
			// String firstName, String lastName, Image image) {
			User u = new User(databaseProxy, userRegistration.getUsername(), userRegistration.getPassword(),
					userRegistration.getFirstName(), userRegistration.getLastName(), null);

			// If this user object is valid wrt to the db
			if (u.isValid()) {
				// Login is successful
				// TODO: Change the UserDetailsPanel
				changeToActivityLayout();
			}

			// Otherwise the user was not valid
			else {
				// TODO: Use a better error message
				status.setStatus("Create failed");
			}
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
		add(activityData, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.ACTIVITY);
		setSize(1024, 768);
		framePostInitialize();
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
