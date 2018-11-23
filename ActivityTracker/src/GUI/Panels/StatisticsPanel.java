package GUI.Panels;

/*

COMP 2005 Group Project
A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/
public class StatisticsPanel extends javax.swing.JPanel {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;

    public StatisticsPanel() {
        initComponents();
    }                  
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Statistics"));
        setLayout(new javax.swing.OverlayLayout(this));

        jScrollPane1.setViewportView(jTextPane1);

        add(jScrollPane1);
        jTextPane1.setText("test\n\n\nterasdfsdfsd");
        jTextPane1.setEditable(false);
    }
    
    public void updatePanel(String s) {
    	jTextPane1.setText(s);
    }
}
