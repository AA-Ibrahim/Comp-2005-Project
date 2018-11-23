package GUI.Panels;

import java.awt.event.ActionListener;

/*
	COMP 2005 Group Project
	A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty
*/


public class UserRegistrationPanel extends javax.swing.JPanel {
    private javax.swing.JButton jbCreateAccount;  
    private javax.swing.JButton jbSignin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jtfBirthday;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfFirstname;
    private javax.swing.JTextField jtfLastname;
    private javax.swing.JPasswordField jtfPassword;
    private javax.swing.JTextField jtfUsername;

    public UserRegistrationPanel() {
        initComponents();
    }
    
    public void addCreateAccountListener(ActionListener ae) {
    	jbCreateAccount.addActionListener(ae);
    }
    
    public void addSigninListener(ActionListener ae) {
        jbSignin.addActionListener(ae);
    }
    
    public void clearFields() {
		jtfUsername.setText("");
		jtfPassword.setText("");	
	}
	
    public String getFirstName() {
		return jtfFirstname.getText();
	}

	public String getLastName() {
		return jtfLastname.getText();
	}

	public String getPassword() {
		System.out.println("pw is " + jtfPassword.getPassword().hashCode());
		return String.valueOf(jtfPassword.getPassword());
	}

	public String getUsername() {
		return jtfUsername.getText();
	}

	@SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jtfFirstname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jtfLastname = new javax.swing.JTextField();
        jtfBirthday = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jtfUsername = new javax.swing.JTextField();
        jbCreateAccount = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jtfPassword = new javax.swing.JPasswordField();
        jbSignin = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("User Registration"));
        setPreferredSize(new java.awt.Dimension(800, 300));

        jLabel1.setText("First Name");

        jtfFirstname.setName(""); // NOI18N

        jLabel2.setText("Last Name");

        jLabel3.setText("Birthday");

        jLabel4.setText("Email");

        jLabel5.setText("Username");

        jbCreateAccount.setText("Create Account");

        jLabel6.setText("Password");

        jbSignin.setText("Sign In");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jtfEmail)
                            
                            .addComponent(jtfLastname)
                            .addComponent(jtfFirstname)
                            .addComponent(jtfPassword)))
                    .addComponent(jbSignin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jtfFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jtfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreateAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSignin))
        );
    }
}
