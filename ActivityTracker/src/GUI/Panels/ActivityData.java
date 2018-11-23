package GUI.Panels;

import java.awt.BorderLayout;

import javax.swing.JTable;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/

public class ActivityData extends javax.swing.JPanel {

	private javax.swing.JScrollPane jScrollPane1;                
    private javax.swing.JTable jtActivity;
	private StatisticsPanel spanel;

	public ActivityData() {
		initComponents();
	}
	
	@SuppressWarnings("unchecked")                        
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jtActivity = new javax.swing.JTable();
        spanel = new StatisticsPanel();
        setLayout(new java.awt.BorderLayout());

        jtActivity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                
            },
            new String [] {
                "Date", "Time", "Distance", "Alt. Gain", "Alt. Loss", "Pace", "Calories"
            }
        ));
        this.setPreferredSize(new java.awt.Dimension(800, 400));
        jScrollPane1.setViewportView(jtActivity);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
        add(spanel, BorderLayout.SOUTH);
    }
    
	public JTable getTable() {
		return jtActivity;
	}
	
    public void setJTable(JTable jt) {
		this.jtActivity = jt;
	}
    
    public void setStatistics(String s) {
		this.spanel.updatePanel(s);
	}
}
