import javax.swing.SwingUtilities;
import GUI.ActivityTrackerGUI;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class Application {
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
