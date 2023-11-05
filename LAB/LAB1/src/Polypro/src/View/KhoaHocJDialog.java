/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package View;

import DAO.ChuyenDeDAO;
import DAO.KhoaHocDAO;
import entity.ChuyenDe;
import entity.KhoaHoc;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import utils.Auth;
import utils.MsgBox;
import utils.XDate;
import utils.logoFPT;

/**
 *
 * @author trung
 */
public final class KhoaHocJDialog extends javax.swing.JDialog {
    public static String   qrCodeImage = "";
    KhoaHocDAO khdao = new KhoaHocDAO();
    int point = -1;

    /**
     * Creates new form KhoaHocJDialog
     * @param parent
     * @param modal
     */
    public KhoaHocJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
        fillToTable();
    }

    public void init() {
        setLocationRelativeTo(null);
        setIconImage(logoFPT.getLoGoFPT());
        setTitle("QUẢN LÝ KHÓA HỌC");
        fillComboBoxChuyenDe();
        if (!Auth.isManager()) {
            txtNguoiTao.setText("");
            System.out.println("Rong");
        } else {
            txtNguoiTao.setText(Auth.user.getMaNhanVien());
            System.out.println(txtNguoiTao.getText());
            txtNgayTao.setEditable(false);
        }
    }

    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tbKhoaHoc.getModel();
        model.setRowCount(0);
        KhoaHocDAO dao = new KhoaHocDAO();

        try {
            List<KhoaHoc> ls = dao.selectAll();
            for (KhoaHoc kh : ls) {
                Object[] rowdata = {kh.getMaKhoaHoc(), kh.getMaChuyenDe(), kh.getThoiLuong(), kh.getHocPhi(), kh.getNgayKhaiGiang(), kh.getMaNhanVien(), kh.getNgayTao()};
                model.addRow(rowdata);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "loi truy van du lieu");
        }
    }

    public void fillComboBoxChuyenDe() {
        ChuyenDeDAO dao = new ChuyenDeDAO();
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChuyenDe.getModel();
        model.removeAllElements();
        try {
            List<ChuyenDe> ls = dao.selectAll();
            for (ChuyenDe cd : ls) {
                model.addElement(cd);
            }

        } catch (Exception e) {
            MsgBox.alert(this, "loi truy van du liet fillComboBoxChuyenDe");
        }
    }

    KhoaHoc getFromKhoaHoc() {
        KhoaHocDAO dao = new KhoaHocDAO();
        KhoaHoc kh = new KhoaHoc();
        ChuyenDe cd = (ChuyenDe) cboChuyenDe.getSelectedItem();

        kh.setMaChuyenDe(cd.getMaChuyenDe());
        kh.setGhiChu(txtGhiChu.getText());
        kh.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
        kh.setNgayTao(XDate.toDate(txtNgayTao.getText(), "dd/MM/yyyy"));
        kh.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        kh.setNgayKhaiGiang(XDate.toDate(txtNgayKhaiGiang.getText(), "dd/MM/yyyy"));
        kh.setMaNhanVien(Auth.user.getMaNhanVien());
        kh.setMaKhoaHoc(khdao.getMaxMaKH() + 1);
        return kh;
    }

    void display(int i) {
        KhoaHoc kh = khdao.selectAll().get(i);
        setModel(kh);
    }

    void addKhoaHoc() {
        KhoaHoc kh = getFromKhoaHoc();
        KhoaHocDAO khdao = new KhoaHocDAO();
        try {
            khdao.insert(kh);
            fillToTable();
            Reset();
            MsgBox.alert(this, "Thêm khoá học thành công");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Thêm thất bại");
        }
    }

    public void Reset() {
        cboChuyenDe.setSelectedIndex(0);
        txtHocPhi.setText("");
        txtNgayKhaiGiang.setText("");
        txtNgayTao.setText("");
        txtNguoiTao.setText("");
        txtThoiLuong.setText("");
        txtGhiChu.setText("");
    }

    void setModel(KhoaHoc kh) {
        ChuyenDeDAO cddao = new ChuyenDeDAO();
        txtNgayKhaiGiang.setText(XDate.toString(kh.getNgayKhaiGiang(), "dd/MM/yyyy"));
        txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
        txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
        txtNguoiTao.setText(kh.getMaNhanVien());
        txtNgayTao.setText(XDate.toString(kh.getNgayTao(), "dd/MM/yyyy"));
        int point = tbKhoaHoc.getSelectedRow();
        if (point > 0) {
            String MaCD = (String) tbKhoaHoc.getValueAt(point, 1);
            System.out.println(MaCD);

            ChuyenDe cd = cddao.selectById(MaCD);
            System.out.println(cd);
            cboChuyenDe.setToolTipText(cd.getTenChuyenDe());
//        cboChuyenDe.setSelectedItem(cddao.selectById(MaCD));
        } else {
            String MaCD = (String) tbKhoaHoc.getValueAt(0, 1);
            System.out.println(MaCD);

            ChuyenDe cd = cddao.selectById(MaCD);
            System.out.println(cd);
            cboChuyenDe.setToolTipText(cd.getTenChuyenDe());
//        cboChuyenDe.setSelectedItem(cddao.selectById(MaCD));
        }

    }

    void updateKhoaHoc() {
        Date NgayKG = XDate.toDate(txtNgayKhaiGiang.getText(), "dd/MM/yyyy");
        double HocPhi = Double.parseDouble(txtHocPhi.getText());
        int ThoiLuong = Integer.parseInt(txtThoiLuong.getText());
        String NguoiTao = txtNguoiTao.getText();
        Date NgayTao = XDate.toDate(txtNgayTao.getText(), "dd/MM/yyyy");
        String GhiChu = txtGhiChu.getText();

        int selectedRow = tbKhoaHoc.getSelectedRow();

        if (selectedRow == -1) {
            MsgBox.alert(this, "Vui lòng chọn khoá học cần cập nhật.");
            return;
        }
        KhoaHoc kh = khdao.selectAll().get(selectedRow);
        // Cập nhật đối tượng KhoaHoc với các giá trị mới
        kh.setMaChuyenDe(((ChuyenDe) cboChuyenDe.getSelectedItem()).getMaChuyenDe());
        kh.setGhiChu(GhiChu);
        kh.setHocPhi(HocPhi);
        kh.setMaNhanVien(NguoiTao);
        kh.setNgayKhaiGiang(NgayKG);
        kh.setNgayTao(NgayTao);
        kh.setThoiLuong(ThoiLuong);
        try {
            khdao.update(kh);
            fillToTable(); // Làm mới bảng
            Reset();
            MsgBox.alert(this, "Cập nhật khoá học thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            MsgBox.alert(this, "Cập nhật thất bại.");
        }
    }

    void deleteKhoaHoc() {
        int selectRow = tbKhoaHoc.getSelectedRow();
        if (selectRow < 0) {
            MsgBox.alert(this, "Ban Chua Chon Khoa Hoc Nao");
        } else {
            try {
                int MaKH = (int) tbKhoaHoc.getValueAt(selectRow, 0);
                khdao.delete(MaKH);
                MsgBox.alert(this, "Xoa Thanh Cong");
                fillToTable();
                Reset();
            } catch (Exception e) {
                e.getMessage();
                MsgBox.alert(this, "Xoa That Bai");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Jpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboChuyenDe = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKhaiGiang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        txtNgayTao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnDau = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbKhoaHoc = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("QUẢN LÝ KHÓA HỌC");

        jLabel2.setText("Chuyên đề");

        cboChuyenDe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Ngày khai giảng");

        jLabel4.setText("Học phí");

        jLabel5.setText("Thời lượng (giờ)");

        jLabel6.setText("Người tạo");

        jLabel7.setText("Ngày tạo");

        jLabel8.setText("Ghi chú");

        txtGhiChu.setColumns(20);
        txtGhiChu.setRows(5);
        jScrollPane1.setViewportView(txtGhiChu);

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

        javax.swing.GroupLayout JpanelLayout = new javax.swing.GroupLayout(Jpanel);
        Jpanel.setLayout(JpanelLayout);
        JpanelLayout.setHorizontalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNguoiTao)
                            .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtHocPhi)
                                    .addComponent(cboChuyenDe, 0, 301, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThoiLuong)
                            .addComponent(txtNgayKhaiGiang)
                            .addGroup(JpanelLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JpanelLayout.createSequentialGroup()
                                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addContainerGap(292, Short.MAX_VALUE))
                            .addComponent(txtNgayTao)))
                    .addGroup(JpanelLayout.createSequentialGroup()
                        .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(JpanelLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(JpanelLayout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLui, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTien, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        JpanelLayout.setVerticalGroup(
            JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnMoi)
                    .addComponent(btnCuoi)
                    .addComponent(btnTien)
                    .addComponent(btnLui)
                    .addComponent(btnDau))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CẬP NHẬT", Jpanel);

        tbKhoaHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ KH", "CHUYÊN ĐỀ", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ));
        tbKhoaHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKhoaHocMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbKhoaHoc);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DANH SÁCH", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        addKhoaHoc();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        Reset();
    }//GEN-LAST:event_btnMoiActionPerformed

    private void tbKhoaHocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKhoaHocMouseClicked
        // TODO add your handling code here:
        KhoaHocDAO khdao = new KhoaHocDAO();
        int getSelectRow = tbKhoaHoc.getSelectedRow();
        if (getSelectRow >= 0) {
            KhoaHoc kh = khdao.selectAll().get(getSelectRow);
            setModel(kh);
        }
    }//GEN-LAST:event_tbKhoaHocMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateKhoaHoc();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteKhoaHoc();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDauActionPerformed
        // TODO add your handling code here:
        point = 0;
        display(point);
    }//GEN-LAST:event_btnDauActionPerformed

    private void btnLuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuiActionPerformed
        // TODO add your handling code here:
        List<KhoaHoc> ls =khdao.selectAll();
        point--;
        if(point<0){
            point = ls.size();
            point--;
        }
        display(point);
        
    }//GEN-LAST:event_btnLuiActionPerformed

    private void btnTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTienActionPerformed
        // TODO add your handling code here:
        List<KhoaHoc> ls =khdao.selectAll();
        point ++;
        if(point>=ls.size()){
            point = 0;
        }
        display(point);
    }//GEN-LAST:event_btnTienActionPerformed

    private void btnCuoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuoiActionPerformed
        // TODO add your handling code here:
        List<KhoaHoc> ls =khdao.selectAll();
        point = ls.size()-1;
        display(point);
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
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KhoaHocJDialog.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                KhoaHocJDialog dialog = new KhoaHocJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel Jpanel;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboChuyenDe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbKhoaHoc;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtNgayKhaiGiang;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
