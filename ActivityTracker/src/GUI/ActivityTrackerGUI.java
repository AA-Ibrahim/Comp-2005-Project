package GUI;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

 */
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

	/**
	 * Initialize panel listeners
	 */
	private void initializePanelListeners() {
		
		// Listeners for the userLogin panel
		
		// Create Account Button Listener
		userLogin.addCreateAccountListener(ae -> {
			changeToRegistrationLayout();
		});
		
		// Sign In Button Listener
		userLogin.addSignInListener(ae -> {
			User u = new User(databaseProxy, userLogin.getUsername(), userLogin.getPassword());
			if (u.isValid()) {
				changeToActivityLayout();
			} else {
				status.setStatus("Login failed");
				userRegistration.clearFields();
			}
		});
		
		// 
		userRegistration.addSigninListener(ae -> {
			// TODO: Create User Object
			// TODO: Execute Query
			changeToSigninLayout();
		});
		//	public User(DatabaseProxy m, String username, String password, String firstName, String lastName, Image image) {

		userRegistration.addCreateAccountListener(ae -> {
			User u = new User(
					databaseProxy,
					userRegistration.getUsername(),
					userRegistration.getPassword(),
					userRegistration.getFirstName(),
					userRegistration.getLastName(),
					null
					);
			if(u.isValid()) {
				changeToActivityLayout();
			}
			else {
				status.setStatus("Create failed");
			}
					
		});
	}

	private void changeToRegistrationLayout() {
		framePreInitialize();
		add(userRegistration, BorderLayout.EAST);
		framePostInitialize();
	}

	private void changeToSigninLayout() {
		framePreInitialize();
		add(userLogin, BorderLayout.EAST);
		framePostInitialize();
	}

	private void changeToActivityLayout() {
		framePreInitialize();
		add(activityData, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.ACTIVITY);
		setSize(new Dimension(1024, 768));
		framePostInitialize();
	}

	private void framePreInitialize() {
		getContentPane().removeAll();
		add(userDetails, BorderLayout.NORTH);
		add(status, BorderLayout.SOUTH);
	}

	private void framePostInitialize() {
		pack();
		repaint();
	}

	private void changeToImportLayout() {
		framePreInitialize();
		add(myDevices, BorderLayout.WEST);
		add(context, BorderLayout.EAST);
		context.changeState(ContextPanel.DEVICES);
		framePostInitialize();
	}
}
