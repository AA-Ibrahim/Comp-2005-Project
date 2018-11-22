package GUI.Panels;

import java.util.Properties;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

/*

    COMP 2005 Group Project
    A. Ibrahim, H. Dos Prazeres, S. Parson, V. Nagisetty

*/

public class ContextPanel extends javax.swing.JPanel {

    public static final int ACTIVITY = 1;
    public static final int DEVICES = 2;
    
    public ContextPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jlContextInfo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        
        jlContextInfo = new javax.swing.JLabel();
        properties = new Properties();
        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model,properties);
        datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
       	properties.put("text.today", "Today");
       	model2 = new UtilDateModel();
       	datePanel2 = new JDatePanelImpl(model,properties);
       	datePicker2 = new JDatePickerImpl(datePanel, new DateComponentFormatter());

    	properties.put("text.month", "Month");
    	properties.put("text.year", "Year");
        
        setLayout(new java.awt.BorderLayout());

        jlContextInfo.setText("<html><h2>Import data <h3>Choose a device among the supported choices <h3>Your activity will be imported.<h4>Sort your data by date below");
        add(jlContextInfo, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.GridLayout(2, 2));

        jLabel2.setText("Start Date");
        jPanel1.add(jLabel2);

        jPanel1.add(datePicker);

        jLabel1.setText("End Date");
        jPanel1.add(jLabel1);

        jPanel1.add(datePicker2);

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private JDatePickerImpl datePicker, datePicker2;
    private Properties properties;
    private JDatePanelImpl datePanel, datePanel2;
    private UtilDateModel model, model2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlContextInfo;
    // End of variables declaration                   

    public void changeState(int statetype) {
        switch(statetype) {
            case ACTIVITY:
                jlContextInfo.setText("<html><h2>Your Activity\n<h3>Here's what you have done\n<h3>Details and statistics are on the left");
                break;
            case DEVICES:
                jlContextInfo.setText("<html><h2>Import data\n<h3>Choose a device among the supported choices\n<h3>Your activity will be imported.");  
              
        }
    }
}
