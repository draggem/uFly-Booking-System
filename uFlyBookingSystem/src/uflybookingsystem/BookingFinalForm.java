/*
* This form allows you to select final booking preferences and export the booking to the database
 */
package uflybookingsystem;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import uflybookingsystem.BusinessObjects.Booking;
import uflybookingsystem.BusinessObjects.Flight;
import uflybookingsystem.BusinessObjects.Plane;


public class BookingFinalForm extends javax.swing.JFrame {
    Booking itemBooked;
    Flight bookedFlight;
    
    /**
     * Creates new form BookingFinalForm
     * sets Flight label to display the flight selected in the Booking form
     * populates the quantity combo box
     * @param itemBooked
     */
    public BookingFinalForm(Booking itemBooked) {
        initComponents();
        this.itemBooked = itemBooked;
        createQuantityModel();
        
    }
    
    //creates the quantity model to populate the combo box
    //person cannot book more than 12 tickets at a time
    //check is performed to see how many tickets are available for booking
    public void createQuantityModel(){
        bookedFlight = DatabaseOperations.getFlightByFLightNumber(itemBooked.getFlightNumber());
        lblFlight.setText(itemBooked.getFlightNumber());
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.removeAllElements();
        int seatsAvailable = bookedFlight.getSeatsTaken();
        //Find Number of Seats
        
        
        
        if (seatsAvailable > 12){
            for (int i=1; i<=12;i++){
                model.addElement(i);
            }
            cboQuantity.setModel(model);
        }else{
            for(int i = 1; i <= seatsAvailable; i++){
                model.addElement(i);
            }
            cboQuantity.setModel(model);
        }
        
        
        for (int i=1; i <= 12; i++){
            
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        msgMessage = new javax.swing.JOptionPane();
        lblTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblFlight = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboQuantity = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        chkInsurance = new javax.swing.JCheckBox();
        btnBook = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTitle.setText("Select Tickets");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Flight:");

        lblFlight.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblFlight.setText("Not Selected");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("How many tickets would you like to get:");

        cboQuantity.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboQuantity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Would you like to get insurance?");

        chkInsurance.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        chkInsurance.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkInsuranceItemStateChanged(evt);
            }
        });
        chkInsurance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkInsuranceActionPerformed(evt);
            }
        });

        btnBook.setBackground(new java.awt.Color(255, 215, 137));
        btnBook.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBook.setText("Book the Flight");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookActionPerformed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(255, 215, 137));
        btnClose.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnClose)
                            .addComponent(btnBook)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chkInsurance))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(lblFlight))
                                    .addGap(64, 64, 64)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(cboQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(19, 63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFlight)
                    .addComponent(cboQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkInsurance)
                    .addComponent(jLabel5))
                .addGap(46, 46, 46)
                .addComponent(btnBook)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //action for the checkbox to set insurance
    private void chkInsuranceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkInsuranceItemStateChanged
        
        if (chkInsurance.isSelected()){
            itemBooked.setIsInsured(true);
        }else{
            itemBooked.setIsInsured(false);
        }
        
    }//GEN-LAST:event_chkInsuranceItemStateChanged

    //action for the Booking button
    //final booking details are set and the object is exported to the Booking table
    //The message is displayed that the booking was added successfuly
    private void btnBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookActionPerformed
        bookedFlight = DatabaseOperations.getFlightByFLightNumber(itemBooked.getFlightNumber());
        
        //save quantity to booked item
        if(cboQuantity.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "This Plane is Fully Booked!");
        }else{
            itemBooked.setQuantity((int)cboQuantity.getSelectedItem());
            //change database flight
            bookedFlight.setSeatsTaken(bookedFlight.getSeatsTaken() - (int)cboQuantity.getSelectedItem());
        }

        DatabaseOperations.UpdateFlight(bookedFlight);
        

        //update quantity combobox
        if(itemBooked.isIsInsured()){
            itemBooked.setPrice((itemBooked.getPrice()* (int)cboQuantity.getSelectedItem()) + 20);
        }else{
            itemBooked.setPrice((itemBooked.getPrice()* (int)cboQuantity.getSelectedItem()));
        }
       
        //Saves the booking to the Database and shows booking was successful!
        if(cboQuantity.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "This Plane is Fully Booked!");
        }else{
            DatabaseOperations.AddBooking(itemBooked);
            JOptionPane.showMessageDialog(this, "Booking was Added Successfully!");
        }
        
        
        //Update Tickets available
        createQuantityModel();
    }//GEN-LAST:event_btnBookActionPerformed

    //action for closing the form
    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void chkInsuranceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkInsuranceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkInsuranceActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BookingFinalForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BookingFinalForm().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnClose;
    private javax.swing.JComboBox cboQuantity;
    private javax.swing.JCheckBox chkInsurance;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblFlight;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JOptionPane msgMessage;
    // End of variables declaration//GEN-END:variables
}
