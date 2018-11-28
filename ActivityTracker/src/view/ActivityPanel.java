package view;

import java.awt.BorderLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/

/*
 * This panel displays the 
 */

public class ActivityPanel extends javax.swing.JPanel {

	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jtActivity;
	private StatisticsPanel spanel;

	public ActivityPanel() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jtActivity = new javax.swing.JTable();
		spanel = new StatisticsPanel();
		setLayout(new java.awt.BorderLayout());

		// sorting fix
		DefaultTableModel tableModel = new DefaultTableModel(new Object[][] { { null, null, null, null, null, null, null }, },
				new String[] { "Date", "Time(min)", "Distance(m)", "Alt. Gain(m)", "Alt. Loss(m)", "Pace(m/min)", "Calories(kcal)" }) {
			@Override
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return Integer.class;
				case 2:
					return Integer.class;
				case 3:
					return Integer.class;
				case 4:
					return Integer.class;
				case 5:
					return Integer.class;
				case 6:
					return Integer.class;
				default:
					return String.class;
				}
			}
		};
		
		jtActivity.setModel(tableModel);
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
