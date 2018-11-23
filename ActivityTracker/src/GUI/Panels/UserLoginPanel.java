package GUI.Panels;

import java.awt.event.ActionListener;

/*
    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/

public class UserLoginPanel extends javax.swing.JPanel {
    private javax.swing.JButton jbCreateAccount;
    private javax.swing.JButton jbSignin;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jpwPassword;
    private javax.swing.JTextField jtfUsername;

    public UserLoginPanel() {
        initComponents();
    }
    
    public void addCreateAccountListener(ActionListener ae) {
        jbCreateAccount.addActionListener(ae);
    }
    
    public void addSignInListener(ActionListener ae) {
        jbSignin.addActionListener(ae);
    }
    
    public void clearFields() {
		jtfUsername.setText("");
		jpwPassword.setText("");	
	}
    
    public String getPassword() {
		return String.valueOf(jpwPassword.getPassword());
	}
    
	public String getUsername() {
		return jtfUsername.getText().toString();
	}

	@SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel7 = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jbSignin = new javax.swing.JButton();
        jpwPassword = new javax.swing.JPasswordField();
        jbCreateAccount = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("User Login"));
        setPreferredSize(new java.awt.Dimension(800, 300));

        jLabel7.setText("Username");

        jLabel8.setText("Password");

        jbSignin.setText("Sign In");

        jbCreateAccount.setText("Create Account");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jbCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbSignin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfUsername)
                            .addComponent(jpwPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 322, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jpwPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addComponent(jbSignin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }
}
