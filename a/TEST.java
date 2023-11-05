/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package test2;
import java.util.ArrayList;
import DAO.ConnectionSQL;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class TEST extends javax.swing.JFrame {
 ArrayList<dia> list = new ArrayList<>();
    int current = 0;
    String head[] = {"MaSach","TenSach","SoLuong"};
    DefaultTableModel model = new DefaultTableModel(head,0);
    public TEST() throws Exception {
       
        initComponents();
        loadDataToTable();
        setLocationRelativeTo(null);
    }

   public void loadDataToTable() throws Exception{
        model.setRowCount(0);
        
        Connection con = ConnectionSQL.openConnection();
        Statement stm = con.createStatement();
        String sql = "Select * From testkn";
        
        ResultSet rs = stm.executeQuery(sql);
        while(rs.next()){
            Vector row = new Vector();
            row.add(rs.getString(1));
            row.add(rs.getString(2));
            row.add(rs.getInt(3));
            model.addRow(row);
        }
        tblDia.setModel(model);
        con.close();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMA = new javax.swing.JTextField();
        txtTieuDe = new javax.swing.JTextField();
        txtSL = new javax.swing.JTextField();
        bttThem = new javax.swing.JButton();
        bttLuu = new javax.swing.JButton();
        bttXoa = new javax.swing.JButton();
        bttupdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDia = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("mã sách");

        jLabel2.setText("Tên Sách");

        jLabel3.setText("Số lượng");

        txtMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMAActionPerformed(evt);
            }
        });

        bttThem.setText("NEW");
        bttThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttThemActionPerformed(evt);
            }
        });

        bttLuu.setText("Save");
        bttLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttLuuActionPerformed(evt);
            }
        });

        bttXoa.setText("DELETE");
        bttXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttXoaActionPerformed(evt);
            }
        });

        bttupdate.setText("Update");
        bttupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttupdateActionPerformed(evt);
            }
        });

        tblDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã", "Tên Sách", "Số Lượng"
            }
        ));
        tblDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDia);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel4.setText("NHẬP SÁCH");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMA, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bttLuu, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(bttXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(bttThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bttupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(73, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(161, 161, 161)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(bttXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(bttThem))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bttLuu)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bttupdate)
                            .addComponent(jLabel3)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTieuDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bttThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttThemActionPerformed
      txtMA.setText("");
      txtTieuDe.setText("");
      txtSL.setText("");
      
    }//GEN-LAST:event_bttThemActionPerformed

    private void bttXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttXoaActionPerformed
      try {
            current = tblDia.getSelectedRow();
            String makh = (String) model.getValueAt(current, 0);
            
            Connection con = ConnectionSQL.openConnection();
            String sql = "Delete From testkn where MaSach=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, makh);
            stm.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Delete Thành Công");
            con.close();
            stm.close();
            loadDataToTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bttXoaActionPerformed

    private void bttLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttLuuActionPerformed
         try {
            Connection con = ConnectionSQL.openConnection();
            String sql = "Insert Into testkn Values(?,?,?)";
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, txtMA.getText());
            stm.setString(2, txtTieuDe.getText());
            stm.setInt(3, Integer.parseInt(txtSL.getText()));
            stm.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Save Thành Công");
            loadDataToTable();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_bttLuuActionPerformed

    private void bttupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttupdateActionPerformed
      if(txtMA.getText().trim().equals("")){
            JOptionPane.showMessageDialog(this, "Nhập Mã Sinh Viên");
            txtMA.requestFocus();
        }
        
        try {
            Connection con = ConnectionSQL.openConnection();
            String sql = "Update testkn set MaSach=?,TenSach=?,SoLuong=? where MaSach=?";
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, txtMA.getText());
            stm.setString(2, txtTieuDe.getText());
            stm.setString(3, txtSL.getText());
        
            stm.setString(4, txtMA.getText());
            stm.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Update thành công");
            con.close();
           loadDataToTable();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_bttupdateActionPerformed

    private void tblDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDiaMouseClicked
      int row = tblDia.getSelectedRow();
        txtMA.setText(tblDia.getValueAt(row, 0).toString());
        txtTieuDe.setText(tblDia.getValueAt(row, 1).toString());
        txtSL.setText(tblDia.getValueAt(row, 2).toString());
    }//GEN-LAST:event_tblDiaMouseClicked

    private void txtMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMAActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TEST.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TEST().setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }        
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bttLuu;
    private javax.swing.JButton bttThem;
    private javax.swing.JButton bttXoa;
    private javax.swing.JButton bttupdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblDia;
    private javax.swing.JTextField txtMA;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTieuDe;
    // End of variables declaration//GEN-END:variables
}
