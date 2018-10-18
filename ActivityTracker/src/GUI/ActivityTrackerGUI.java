package GUI;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

 */
import GUI.Panels.ActivityData;
import GUI.Panels.ContextPanel;
import GUI.Panels.MyDevicesPanel;
import GUI.Panels.StatusPanel;
import GUI.Panels.UserDetailsPanel;
import GUI.Panels.UserLoginPanel;
import GUI.Panels.UserRegistrationPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ActivityTrackerGUI extends JFrame {

    UserDetailsPanel userDetails;
    UserLoginPanel userLogin;
    UserRegistrationPanel userRegistration;
    ContextPanel context;
    StatusPanel status;
    MyDevicesPanel myDevices;
    ActivityData activityData;



    ActivityTrackerGUI() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializePanels();
        initializePanelListeners();
        changeToSigninLayout();
    }
    
    private void initializePanels() {
        userDetails = new UserDetailsPanel();
        status = new StatusPanel();
        activityData = new ActivityData();
        userLogin = new UserLoginPanel();
        userRegistration = new UserRegistrationPanel();
        myDevices = new MyDevicesPanel();
        context = new ContextPanel();
    }

    private void initializePanelListeners() {
        userLogin.addCreateAccountListener(ae -> {
            changeToRegistrationLayout();
        });
        userLogin.addSignInListener(ae -> {
            changeToActivityLayout();
        });
        userRegistration.addSigninListener(ae -> {
            changeToSigninLayout();
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

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ActivityTrackerGUI().setVisible(true);
            }
        });
    }

}
