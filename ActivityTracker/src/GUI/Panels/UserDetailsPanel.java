package GUI.Panels;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/

public class UserDetailsPanel extends javax.swing.JPanel {

    public UserDetailsPanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/Resources/user.jpg"))); // NOI18N

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
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbEditProfile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbLogout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbMyFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbImportData, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbMyActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jbEditProfile)
                        .addComponent(jbLogout)
                        .addComponent(jbMyFriends)
                        .addComponent(jbMyActivity)
                        .addComponent(jbImportData))
                    .addComponent(jLabel10))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JButton jbEditProfile;
    private javax.swing.JButton jbImportData;
    private javax.swing.JButton jbLogout;
    private javax.swing.JButton jbMyActivity;
    private javax.swing.JButton jbMyFriends;
    // End of variables declaration//GEN-END:variables

}