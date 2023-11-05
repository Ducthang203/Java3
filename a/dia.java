/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test2;

/**
 *
 * @author THAI
 */
public class dia {
     private String MaSach;
    private String TenSach;
    private int SoLuong;

    public dia() {
    }

    public dia(String MaSach, String TenSach, int SoLuong) {
        this.MaSach = MaSach;
        this.TenSach = TenSach;
        this.SoLuong = SoLuong;
    }

    public String getMaSach() {
        return MaSach;
    }

    public void setMaSach(String MaSach) {
        this.MaSach = MaSach;
    }
     public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String TenSach) {
        this.TenSach = TenSach;
    }


    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }
    
    
    
}

