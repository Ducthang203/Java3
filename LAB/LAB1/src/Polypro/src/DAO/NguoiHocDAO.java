/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.NguoiHoc;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;
import java.sql.ResultSet;
import java.util.Date;

public class NguoiHocDAO extends PolyproDAO<NguoiHoc, String> {
    java.sql.Date sqlDate;
    final String INSERT_SQL = "insert into NguoiHoc (MaNH,HoTen,GioiTinh,NgaySinh,DienThoai,Email,GhiChu,MaNV,NgayDK) values (?,?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "update NguoiHoc set  HoTen = ? ,GioiTinh = ?,NgaySinh = ?,DienThoai = ?,Email = ?,GhiChu = ?,MaNV= ?,NgayDK = ? where MaNH = ?";
    final String DELETE_SQL = "DELETE NguoiHoc where MaNH = ?";
    final String SELECT_ALL_SQl = "select * from NguoiHoc";
    final String SELECT_BY_ID_SQL = "Select * from NguoiHoc where MaNH  = ?";
    final String SELECT_NotInCount_SQL = "Select * from NguoiHoc where HoTen like ? and MaNH    not in (select MaNh from HocVien Where MaKH = ?)";

    @Override
    public void insert(NguoiHoc entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaNguoiHoc(), entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNhanVien(), entity.getNgayDangKy());
    }

    public Date convertDate(java.util.Date date) {
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    @Override
    public void update(NguoiHoc entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getHoTen(), entity.isGioiTinh(), entity.getNgaySinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNhanVien(), entity.getNgayDangKy(), entity.getMaNguoiHoc());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQl);
    }

    @Override
    public NguoiHoc selectById(String id) {
        List<NguoiHoc> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NguoiHoc> selectBySql(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                NguoiHoc NH = new NguoiHoc();
                NH.setMaNguoiHoc(rs.getString("MaNH"));
                NH.setHoTen(rs.getString("HoTen"));
                NH.setGioiTinh(rs.getBoolean("GioiTinh"));
                NH.setNgaySinh(rs.getDate("NgaySinh"));
                NH.setDienThoai(rs.getString("DienThoai"));
                NH.setEmail(rs.getString("Email"));
                NH.setGhiChu(rs.getString("GhiChu"));
                NH.setMaNhanVien(rs.getString("MaNV"));
                NH.setNgayDangKy(rs.getDate("NgayDK"));
                list.add(NH);
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return list;
    }

    public List<NguoiHoc> selectNotInCount(int MaKH, String keywork) {
        return selectBySql(SELECT_NotInCount_SQL, "%" + keywork + "%", MaKH);
    }
    public List<NguoiHoc> selectByKeyword_Demo(String keyword){
        String sql="SELECT * FROM NguoiHoc WHERE HoTen LIKE ? or MaNH LIKE ?  or Email LIKE ? or DienThoai LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%","%"+keyword+"%","%"+keyword+"%","%"+keyword+"%");
    }
//    public File chonFileExce

}
