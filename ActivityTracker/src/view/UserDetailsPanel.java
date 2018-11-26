package view;

import java.awt.event.ActionListener;

import model.User;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/
/*
 * This panel displays the main panel to display to the user once he/she has logged in
 */
public class UserDetailsPanel extends javax.swing.JPanel {
	private javax.swing.JButton jbEditProfile;
	private javax.swing.JButton jbImportData;
	private javax.swing.JButton jbLogout;
	private javax.swing.JButton jbMyActivity;
	private javax.swing.JButton jbMyFriends;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel7;
	private User u;

	public UserDetailsPanel() {
		u = null;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jLabel7 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jbMyActivity = new javax.swing.JButton();
		jbEditProfile = new javax.swing.JButton();
		jbLogout = new javax.swing.JButton();
		jbMyFriends = new javax.swing.JButton();
		jbImportData = new javax.swing.JButton();

		setBorder(javax.swing.BorderFactory.createTitledBorder("User Details"));
		setPreferredSize(new java.awt.Dimension(600, 100));

		jLabel7.setText("not logged in");

		jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/resources/user.jpg"))); // NOI18N

		jbMyActivity.setText("My Activity");
		jbMyActivity.setEnabled(false);

		jbEditProfile.setText("edit profile");
		jbEditProfile.setEnabled(false);

		jbLogout.setText("logout");
		jbLogout.setEnabled(false);

		jbMyFriends.setText("My Friends");
		jbMyFriends.setEnabled(false);

		jbImportData.setText("Import Data");
		jbImportData.setEnabled(false);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addComponent(jLabel10)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel7)
						.addGroup(layout.createSequentialGroup().addComponent(jbEditProfile)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jbLogout)))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jbMyFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jbImportData, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(jbMyActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jbEditProfile).addComponent(jbLogout).addComponent(jbMyFriends)
										.addComponent(jbMyActivity).addComponent(jbImportData))
								.addComponent(jLabel10))
						.addGap(0, 0, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup().addComponent(jLabel7)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}
	
	public void setUser(User u) {
		if (u == null) {
			jLabel7.setText("not logged in");
			jbLogout.setEnabled(false);
			jbImportData.setEnabled(false);
			jbMyActivity.setEnabled(false);
			return;
		}
		this.u = u;
		jLabel7.setText(u.getFirstName() + " " + u.getLastName());
		jbLogout.setEnabled(true);
		jbImportData.setEnabled(true);
		jbMyActivity.setEnabled(true);
	}
	
	public void addImportDataListener(ActionListener ae) {
		jbImportData.addActionListener(ae);
	}
	
	public void addLogoutListener(ActionListener ae) {
		jbLogout.addActionListener(ae);
	}
	
	public void addMyActivityListener(ActionListener ae) {
		jbMyActivity.addActionListener(ae);
	}
	
	public User getUser() {
		return u;
	}
}
