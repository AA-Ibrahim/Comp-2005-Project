package view;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/
/*
 * This panel contains the context information with the option to select the date ranges of the runs 
 */

public class ContextPanel extends javax.swing.JPanel {

	public static final int ACTIVITY = 1;
	public static final int DEVICES = 2;

	private JComboBox<String> jcb;

	private JDatePanelImpl datePanel, datePanel2;
	private JDatePickerImpl datePicker, datePicker2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;

	private javax.swing.JLabel jlContextInfo;
	private javax.swing.JPanel jPanel1;
	private UtilDateModel model, model2;
	private JButton ok, clear;
	private Properties properties;

	public ContextPanel() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		String[] months = { "ALL MONTHS", "January", "February", "March", "April", "May", "June", "July", "August",
				"September", "October", "November", "December" };
		jcb = new JComboBox<String>(months);

		jlContextInfo = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();

		jLabel2 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		ok = new JButton("Get Range");
		jlContextInfo = new javax.swing.JLabel();
		properties = new Properties();
		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model, properties);
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, properties);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateComponentFormatter());
		properties.put("text.today", "Today");

		properties.put("text.month", "Month");
		properties.put("text.year", "Year");

		setLayout(new java.awt.BorderLayout());

		jlContextInfo.setText(
				"<html><h2>Import data <h3>Choose a device among the supported choices <h3>Your activity will be imported.<h4>Sort your data by date below");
		add(jlContextInfo, java.awt.BorderLayout.CENTER);

		jPanel1.setLayout(new java.awt.GridLayout(5, 2));

		jLabel2.setText("Start Date");
		jPanel1.add(jLabel2);

		jPanel1.add(datePicker);

		jLabel1.setText("End Date");
		jPanel1.add(jLabel1);

		jPanel1.add(datePicker2);
		jPanel1.add(ok);
		clear = new JButton("Clear");
		clear.addActionListener(ae -> {
			model.setValue(null);
			model2.setValue(null);
		});
		jPanel1.add(clear);

		jLabel3.setText("By Month");
		jPanel1.add(jLabel3);

		jPanel1.add(jcb);
		
		
		jcb.addActionListener(ae -> {
			String s = (String) jcb.getSelectedItem();
			int sel = 0;
		
			// Figure out which month is selected
			for (int i = 0; i < months.length; i++) {
				if (s.equals(months[i])) {
					sel = i;
					break;
				}
			}

			// If the selection is all ranges, set the chooser to null
			if (sel == 0) {
				model.setValue(null);
				model2.setValue(null);
			} else {
				// Otherwise, set the choosers
				int year = datePanel.getModel().getYear();
				
				// Set the start date
				Calendar c = Calendar.getInstance();
				c.set(Calendar.YEAR, year);
				c.set(year,  sel-1,  1, 0, 0, 0);
				
				// Send the end date
				model.setValue(c.getTime());
				c.set(Calendar.YEAR, year);
				c.set(year,  sel,  0, 23, 0, 0);
				
				// Click the button
				model2.setValue(c.getTime());
				ok.doClick();
			}

		});

		add(jPanel1, java.awt.BorderLayout.PAGE_END);
	}

	public void addRangeListener(ActionListener ae) {
		ok.addActionListener(ae);
	}

	public void changeState(int statetype) {
		switch (statetype) {
		case ACTIVITY:
			jlContextInfo.setText(
					"<html><h2>Your Activity\n<h3>Here's what you have done\n<h3>Details and statistics are on the left");
			break;
		case DEVICES:
			jlContextInfo.setText(
					"<html><h2>Import data\n<h3>Choose a device among the supported choices\n<h3>Your activity will be imported.");
		}
	}

	public Date getRange1() {
		return model.getValue();
	}

	public Date getRange2() {
		return model2.getValue();
	}
}
