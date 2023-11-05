/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.NhanVienDAO;

import entity.nhanvien;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import utils.MsgBox;
import utils.logoFPT;

/**
 *
 * @author trung
 */
public final class NhanVienJDialog extends javax.swing.JDialog {

    public static String qrCodeImage = "";
    java.awt.Frame parent = null;
    NhanVienDAO dao = new NhanVienDAO();
    ArrayList<nhanvien> ls = new ArrayList<>();
    int current = -1;

    /**
     * Creates new form NhanVienJDialog
     * @param parent
     * @param modal
     */
    public NhanVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        fillTable();
    }

    public void init() {
        setLocationRelativeTo(null);
        setIconImage(logoFPT.getLoGoFPT());
        setTitle("QUẢN LÝ NHÂN VIÊN");

    }

    public void DisPlay(int i) {
        List<nhanvien> ls = dao.selectAll();
        nhanvien nv = ls.get(i);
        txtMaNhanVien.setText(nv.getMaNhanVien());
        txtMatKhau.setText(nv.getMatKhau());
        txtXacNhanMatKhau.setText(nv.getMatKhau());
        txtHoVaTen.setText(nv.getHoTen());
        boolean vaitro = nv.isVaiTro();
        if (vaitro) {
            rdoNhanVien.setSelected(true);
        } else {
            rdoTruongPhong.setSelected(true);
        }

    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0);
        try {
            List<nhanvien> ls = dao.selectAll();
            for (nhanvien nv : ls) {
                Object[] rowdata = {nv.getMaNhanVien(), nv.getMatKhau(), nv.getHoTen(), nv.isVaiTro() ? "NhanVien" : "Truong Phong"};
                model.addRow(rowdata);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "loi truy van du lieu");
        }
    }

    public void Reset() {
        txtHoVaTen.setText("");
        txtMaNhanVien.setText("");
        txtMatKhau.setText("");
        txtXacNhanMatKhau.setText("");
        buttonGroup1.clearSelection();
        txtMaNhanVien.requestFocus();
    }

    public List<nhanvien> getAll() {
        return ls;
    }

    public void setModel(nhanvien nv) {
        txtMaNhanVien.setText(nv.getMaNhanVien());
        txtMatKhau.setText(nv.getMatKhau());
        txtHoVaTen.setText(nv.getHoTen());
        txtXacNhanMatKhau.setText(nv.getMatKhau());
        boolean VaiTro = nv.isVaiTro();
        if (VaiTro) {
            rdoNhanVien.setSelected(true);
        } else {
            rdoTruongPhong.setSelected(true);
        }

    }

    public void addNhanVien() {
        String MaNV = txtMaNhanVien.getText().trim();
        String MatKhau = txtMatKhau.getText().trim();
        String XacNhanMatKhau = txtXacNhanMatKhau.getText().trim();
        String HoTen = txtHoVaTen.getText().trim();
        boolean VaiTro = rdoNhanVien.isSelected();

        nhanvien nv1 = dao.selectById(MaNV);
        // nv1 tra ve gia tri null khi khong tim thay nhan vien
        try {
            if (nv1 != null && nv1.getMaNhanVien().equals(MaNV)) {
                MsgBox.alert(this, "MaNV Da Ton Tai");
            } else if (MatKhau.equals(XacNhanMatKhau)) {

                nhanvien nv = new nhanvien(MaNV, HoTen, MatKhau, VaiTro);
                dao.insert(nv);
                MsgBox.alert(this, "Them Thanh Cong");
                Reset();
                fillTable();
            } else {
                MsgBox.alert(this, "ERROR: Xac Nhan Mat Khau ");
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public void updateNhanVien() {
        String HoTen = txtHoVaTen.getText().trim();
        String MatKhau = txtMatKhau.getText().trim();
        String XacNhanMatKhau = txtXacNhanMatKhau.getText().trim();
        boolean VaiTro = rdoNhanVien.isSelected();
        String MaNV = txtMaNhanVien.getText().trim();
        nhanvien nv = dao.selectById(MaNV);
        if (!MatKhau.equals(XacNhanMatKhau)) {
            MsgBox.alert(this, "Xac Nhan Mat Khau khong Dung");
        } else if (nv != null) {
            nv.setHoTen(HoTen);
            nv.setMatKhau(MatKhau);
            nv.setVaiTro(VaiTro);
            dao.update(nv);
            MsgBox.alert(this, "cap nhat thanh cong");
            Reset();
            fillTable();
        } else {
            MsgBox.alert(this, "MaNV Khong Ton Tai");
        }
    }

    public void deleteNhanVien() {
        String MaNV = txtMaNhanVien.getText().trim();
        nhanvien nv = dao.selectById(MaNV);
        if (nv != null) {
            dao.delete(MaNV);
            MsgBox.alert(this, "Xoa thanh cong");
            Reset();
            fillTable();
        } else {
            MsgBox.alert(this, "MaNV Khong Ton Tai");
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtXacNhanMatKhau = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoTruongPhong = new javax.swing.JRadioButton();
        rdoNhanVien = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnDau = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        btnQRCode = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("QUẢN  LÝ NHÂN VIÊN QUẢN TRỊ ");

        jLabel2.setText("Mã nhân viên ");

        jLabel3.setText("Mật khẩu");

        jLabel4.setText("Xác nhận mật khẩu");

        jLabel5.setText("Họ và tên");

        jLabel6.setText("Vai trò");

        buttonGroup1.add(rdoTruongPhong);
        rdoTruongPhong.setText("Trưởng phòng");

        buttonGroup1.add(rdoNhanVien);
        rdoNhanVien.setText("Nhân viên");

        btnThem.setText("Thêm ");
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

        btnDau.setText("|<");
        btnDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDauActionPerformed(evt);
            }
        });

        btnLui.setText("<<");
        btnLui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuiActionPerformed(evt);
            }
        });

        btnTien.setText(">>");
        btnTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTienActionPerformed(evt);
            }
        });

        btnCuoi.setText(">|");
        btnCuoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuoiActionPerformed(evt);
            }
        });

        btnQRCode.setText("QRCode");
        btnQRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQRCodeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhau)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(txtMaNhanVien)
                    .addComponent(txtXacNhanMatKhau)
                    .addComponent(txtHoVaTen)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rdoTruongPhong)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoNhanVien)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnQRCode)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHoVaTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoTruongPhong)
                            .addComponent(rdoNhanVien))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnQRCode)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnDau)
                    .addComponent(btnLui)
                    .addComponent(btnTien)
                    .addComponent(btnCuoi))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("CẬP NHẬT", jPanel1);

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NHÂN VIÊN", "MẬT KHẨU", "HỌ VÀ TÊN", "VAI TRÒ"
            }
        ));
        tbNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);
        if (tbNhanVien.getColumnModel().getColumnCount() > 0) {
            tbNhanVien.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jTabbedPane2.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateNhanVien();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addNhanVien();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tbNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNhanVienMouseClicked
        // TODO add your handling code here:
        int selectedRow = tbNhanVien.getSelectedRow();
        System.out.println(selectedRow);
        if (selectedRow >= 0) {
            nhanvien nv = dao.selectAll().get(selectedRow);
            setModel(nv);
        }
        jTabbedPane2.setSelectedIndex(0);

    }//GEN-LAST:event_tbNhanVienMouseClicked

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteNhanVien();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        // TODO add your handling code here:
        current = 0;
        DisPlay(current);
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        List<nhanvien> ls = dao.selectAll();
        current--;
        if (current < 0) {
            current = ls.size();
            current--;
        }
        DisPlay(current);
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        List<nhanvien> ls = dao.selectAll();
        current++;
        if (current >= ls.size()) {
            current = 0;
        }
        DisPlay(current);
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        // TODO add your handling code here:
        List<nhanvien> ls = dao.selectAll();
        current = ls.size() - 1;
        DisPlay(current);
    }//GEN-LAST:event_btnCuoiActionPerformed

    private void btnQRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQRCodeActionPerformed
        // TODO add your handling code here:
        try {
            String MaNV = txtMaNhanVien.getText().trim();
            String MatKhau = txtMatKhau.getText().trim();
            String XacNhanMatKhau = txtXacNhanMatKhau.getText().trim();
            String HoTen = txtHoVaTen.getText().trim();
            boolean VaiTro = rdoNhanVien.isSelected();
            nhanvien chuyenDe = new nhanvien(MaNV, HoTen, MatKhau, VaiTro);
            String qrCodeText = "Chuyen De: " + chuyenDe.getMaNhanVien() + " Khoa hoc khai giang: " + chuyenDe.getHoTen();
            String filePath = chuyenDe.getMaNhanVien() + "_" + chuyenDe.getHoTen() + "_" + chuyenDe.getMatKhau() + ".jpg";
            File destination = new File("storeFiles", filePath);

            filePath = Paths.get(destination.getAbsolutePath()).toString();
            int size = 200;
            String fileType = "jpg";
            File qrFile = new File(filePath);
            QRCodeUtil.createQRImage(qrFile, qrCodeText, size, fileType);
            this.qrCodeImage = filePath;
            new QRCodeJDialog(this.parent, true).setVisible(true);
        } catch (WriterException ex) {
        } catch (IOException ex) {
        }
    }//GEN-LAST:event_btnQRCodeActionPerformed

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
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhanVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhanVienJDialog dialog = new NhanVienJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnQRCode;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JRadioButton rdoNhanVien;
    private javax.swing.JRadioButton rdoTruongPhong;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables

}
