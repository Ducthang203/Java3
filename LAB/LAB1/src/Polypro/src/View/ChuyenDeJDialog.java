/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.ChuyenDeDAO;
import com.sun.source.tree.TryTree;
import entity.ChuyenDe;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
import utils.logoFPT;

/**
 *
 * @author trung
 */
public class ChuyenDeJDialog extends javax.swing.JDialog {

    ChuyenDeDAO dao = new ChuyenDeDAO();
    ArrayList<ChuyenDe> ls = new ArrayList<>();
    int current = -1;

    /**
     * Creates new form ChuyenDe1JDialog
     */
    public ChuyenDeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        fillToTable();
    }

    public void init() {
        setLocationRelativeTo(null);
        setIconImage(logoFPT.getLoGoFPT());
        setTitle("QUẢN LÝ CHUYỂN ĐỀ");

    }

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tbChuyenDe.getModel();
        ChuyenDeDAO CDdao = new ChuyenDeDAO();
        model.setRowCount(0);
        try {
            List<ChuyenDe> ls = CDdao.selectAll();
            for (ChuyenDe CD : ls) {
                Object[] rowdata = {CD.getMaChuyenDe(), CD.getTenChuyenDe(), CD.getHocPhi(), CD.getThoiLuong(), CD.getHinh()};
                model.addRow(rowdata);

            }
        } catch (Exception e) {
            MsgBox.alert(this, "Loi truy van du lieu");
        }

    }

    public void setModel(ChuyenDe cd) {
        txtMaChuyenDe.setText(cd.getMaChuyenDe());
        txtTenChuyenDe.setText(cd.getTenChuyenDe());
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
        txtHinh.setText(cd.getHinh());
    }

    public void Reset() {
        txtHinh.setText("");
        txtHocPhi.setText("");
        txtMaChuyenDe.setText("");
        txtMoTaChuyenDe.setText("");
        txtTenChuyenDe.setText("");
        txtThoiLuong.setText("");
        txtHinh.setIcon(null);
    }

    public void Display(int i) {
        List<ChuyenDe> ls = dao.selectAll();
        ChuyenDe cd = ls.get(i);
//        txtHinh.setText(cd.getHinh());
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
        txtMaChuyenDe.setText(cd.getMaChuyenDe());
        txtTenChuyenDe.setText(cd.getTenChuyenDe());
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
        String hinhPath = cd.getHinh();
        if (hinhPath != null && !hinhPath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(hinhPath);
            Image image = imageIcon.getImage().getScaledInstance(txtHinh.getWidth(), txtHinh.getHeight(), Image.SCALE_SMOOTH);
            txtHinh.setIcon(new ImageIcon(image));
        } else {
            // Nếu không có hình ảnh, xoá hình hiện tại
            txtHinh.setIcon(null);
        }
    }

    public void AddChuyenDe() {
        String MaCD = txtMaChuyenDe.getText().trim();
        String TenCd = txtTenChuyenDe.getText().trim();
        double hocPhi = Double.parseDouble(txtHocPhi.getText().trim());
        int ThoiLuong = Integer.parseInt(txtThoiLuong.getText().trim());

        ChuyenDe cd = dao.selectById(MaCD);
//       Hiển thị hộp thoại lựa chọn tệp để chọn tệp hình ảnh
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp Hình Ảnh", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String img = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                if (cd != null && cd.getMaChuyenDe().equals(MaCD)) {
                    MsgBox.alert(this, "Ma Chuyen De Da Ton Tai");
                } else {
                    ChuyenDe cd1 = new ChuyenDe(MaCD, TenCd, hocPhi, ThoiLuong, img, "");
                    dao.insert(cd1);
                    MsgBox.alert(this, "Them Thanh Cong");
                    Reset();
                    fillToTable();
                }
            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }
        }

    }

    public void deleteChuyenDe() {
        String MaCD = txtMaChuyenDe.getText().trim();
        ChuyenDe cd = dao.selectById(MaCD);
        if (cd != null) {
            dao.delete(MaCD);
            MsgBox.alert(this, "Xoa Thanh Cong");
            Reset();
            fillToTable();
        } else {
            MsgBox.alert(this, "MaCD Khong Ton Tai");
        }
    }

    public void updateChuyenDe() {
        String MaCD = txtMaChuyenDe.getText().trim();
        String TenCd = txtTenChuyenDe.getText().trim();
        double hocPhi = Double.parseDouble(txtHocPhi.getText().trim());
        int ThoiLuong = Integer.parseInt(txtThoiLuong.getText().trim());
        String Hing = txtHinh.getText().trim();
        ChuyenDe cd = dao.selectById(MaCD);

        boolean KiemTra = MsgBox.confirm(this, "Ban Co Muon Thay Doi Hinh Anh Khong");
        if (KiemTra) {

//       Hiển thị hộp thoại lựa chọn tệp để chọn tệp hình ảnh
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp Hình Ảnh", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                String img = fileChooser.getSelectedFile().getAbsolutePath();
                if (cd != null) {
                    try {
                        cd.setHinh(img);
                        cd.setHocPhi(hocPhi);
                        cd.setMaChuyenDe(MaCD);
                        cd.setTenChuyenDe(TenCd);
                        cd.setThoiLuong(ThoiLuong);
                        dao.update(cd);
                        MsgBox.alert(this, "cap nhat thanh cong");
                        Reset();
                        fillToTable();
                    } catch (Exception e) {
                    }
                } else {
                    MsgBox.alert(this, "MaNV Khong Ton Tai");
                }
            }
        } else {
            if (cd != null) {
                try {
                    cd.setHinh(Hing);
                    cd.setHocPhi(hocPhi);
                    cd.setMaChuyenDe(MaCD);
                    cd.setTenChuyenDe(TenCd);
                    cd.setThoiLuong(ThoiLuong);
                    dao.update(cd);
                    MsgBox.alert(this, "cap nhat thanh cong");
                    Reset();
                    fillToTable();
                } catch (Exception e) {
                }
            } else {
                MsgBox.alert(this, "MaNV Khong Ton Tai");
            }
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

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaChuyenDe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenChuyenDe = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTaChuyenDe = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnDau = new javax.swing.JButton();
        txtHinh = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbChuyenDe = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 204));
        jLabel1.setText("QUẢN LÝ CHUYỂN ĐỀ");

        jLabel2.setText("Hình logo");

        jLabel3.setText("Mã chuyên đề");

        jLabel4.setText("Tên chuyên đề");

        jLabel5.setText("Thời lượng (Giờ)");

        jLabel6.setText("Học Phí");

        jLabel7.setText("Mô tả chuyên đề");

        txtMoTaChuyenDe.setColumns(20);
        txtMoTaChuyenDe.setRows(5);
        jScrollPane1.setViewportView(txtMoTaChuyenDe);

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnCuoi.setText(">|");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        btnTien.setText(">>");
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnLui.setText("<<");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnDau.setText("|<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaChuyenDe)
                            .addComponent(txtTenChuyenDe)
                            .addComponent(txtThoiLuong)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtHocPhi)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(126, 126, 126)
                                .addComponent(jLabel3))
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMaChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(txtHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnCuoi)
                    .addComponent(btnTien)
                    .addComponent(btnLui)
                    .addComponent(btnDau))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CẬP NHẬT", jPanel1);

        tbChuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ CD", "TÊN CD", "HỌC PHÍ", "THỜI LƯỢNG", "HÌNH"
            }
        ));
        tbChuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbChuyenDeMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbChuyenDe);
        if (tbChuyenDe.getColumnModel().getColumnCount() > 0) {
            tbChuyenDe.getColumnModel().getColumn(1).setPreferredWidth(300);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbChuyenDeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbChuyenDeMouseClicked
        // TODO add your handling code here:
        int selectRow = tbChuyenDe.getSelectedRow();
        if (selectRow >= 0) {
            ChuyenDe cd = dao.selectAll().get(selectRow);
            setModel(cd);
            String hinhPath = cd.getHinh();
            if (hinhPath != null && !hinhPath.isEmpty()) {
                ImageIcon imageIcon = new ImageIcon(hinhPath);
                Image image = imageIcon.getImage().getScaledInstance(txtHinh.getWidth(), txtHinh.getHeight(), Image.SCALE_SMOOTH);
                txtHinh.setIcon(new ImageIcon(image));
            } else {
                // Nếu không có hình ảnh, xoá hình hiện tại
                txtHinh.setIcon(null);
            }
        }
    }//GEN-LAST:event_tbChuyenDeMouseClicked

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        AddChuyenDe();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteChuyenDe();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateChuyenDe();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        // TODO add your handling code here:
        List<ChuyenDe> ls = dao.selectAll();
        current = 0;
        Display(current);
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        List<ChuyenDe> ls = dao.selectAll();
        current--;
        if(current <0){
            current = ls.size();
            current--;
        }
        Display(current);
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        List<ChuyenDe> ls = dao.selectAll();
        current++;
        if(current >= ls.size() ){
            current = 0;    
        }
        Display(current);
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        // TODO add your handling code here:
        List<ChuyenDe> ls = dao.selectAll();
        current = ls.size() - 1;
        Display(current);
    }//GEN-LAST:event_btnCuoiActionPerformed

    
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
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChuyenDeJDialog dialog = new ChuyenDeJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbChuyenDe;
    private javax.swing.JLabel txtHinh;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaChuyenDe;
    private javax.swing.JTextArea txtMoTaChuyenDe;
    private javax.swing.JTextField txtTenChuyenDe;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
