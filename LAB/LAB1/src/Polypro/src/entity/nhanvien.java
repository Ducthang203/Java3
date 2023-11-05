/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author trung
 */
public class nhanvien {
    private String maNhanVien;
    private String hoTen;
    private String matKhau;
    private boolean vaiTro = false;

    public nhanvien() {
    }

    public nhanvien(String maNhanVien, String hoTen, String matKhau, boolean vaiTro) {
        this.maNhanVien = maNhanVien;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
        this.vaiTro = vaiTro;
    }

    

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    
    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    

    @Override
    public String toString() {
        return this.hoTen;
    }
    
    
}
