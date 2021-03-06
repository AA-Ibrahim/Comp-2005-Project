package view;

import java.awt.Component;
import java.awt.FileDialog;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Activity;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/
/*
 * This panel displays the options to import data, with a file manager
 */
public class MyDevicesPanel extends javax.swing.JPanel {

    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	
	public MyDevicesPanel() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {
		jButton6 = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton7 = new javax.swing.JButton();

		setBorder(javax.swing.BorderFactory.createTitledBorder("My Devices"));
		setPreferredSize(new java.awt.Dimension(600, 500));

		jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resources/comp.png"))); // NOI18N
		jButton6.setText("jButton6");
		jButton6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		jButton6.setBorderPainted(false);

		jLabel1.setText("Your Computer");


		jLabel2.setText("Garmin Monitor");

		jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resources/bit.jpg"))); // NOI18N
		jButton7.setText("jButton6");
		jButton7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		jButton7.setBorderPainted(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 587, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(30, 30, 30)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														313, Short.MAX_VALUE)
												.addComponent(jLabel2))
										.addGroup(layout.createSequentialGroup()
												.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(30, 30, 30))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 476, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup().addGap(95, 95, 95)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 85,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1).addComponent(jLabel2))
										.addContainerGap(272, Short.MAX_VALUE))));
	}
	
	public void addImportListener(ActionListener ae) {
        jButton6.addActionListener(ae);
    }
}
