package GUI;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/

import GUI.Panels.UserLoginPanel;
import GUI.Panels.UserRegistrationPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class ActivityTrackerGUI extends JFrame {

    ActivityTrackerGUI() {
        setLayout(new BorderLayout());
        setSize(new Dimension(800, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jpEast = new UserLoginPanel();
        JPanel jpWest = new UserRegistrationPanel();
        
        add(jpEast, BorderLayout.EAST);
        add(jpWest, BorderLayout.WEST);
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
