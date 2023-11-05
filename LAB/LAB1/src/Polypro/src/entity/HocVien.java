/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author trung
 */
public class HocVien {

    private int maHocVien;
    private int maKhoaHoc;
    private String maNguoiHoc;
    private double diem = -1;

    public HocVien() {
    }

    public HocVien(int maHocVien, int maKhoaHoc, String maNguoiHoc, double diem) {
        this.maHocVien = maHocVien;
        this.maKhoaHoc = maKhoaHoc;
        this.maNguoiHoc = maNguoiHoc;
        this.diem = diem;
    }

    public int getMaHocVien() {
        return maHocVien;
    }

    public void setMaHocVien(int maHocVien) {
        this.maHocVien = maHocVien;
    }

    public int getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(int maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getMaNguoiHoc() {
        return maNguoiHoc;
    }

    public void setMaNguoiHoc(String maNguoiHoc) {
        this.maNguoiHoc = maNguoiHoc;
    }

    public double getDiem() {
        return diem;
    }

    public void setDiem(double diem) {
        this.diem = diem;
    }

    @Override
    public String toString() {
        return this.toString();
    }
    

}
