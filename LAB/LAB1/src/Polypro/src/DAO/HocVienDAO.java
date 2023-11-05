/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.HocVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;

public class HocVienDAO extends PolyproDAO<HocVien, Integer> {

    final String INSERT_SQL = "SET IDENTITY_INSERT HocVien ON;"
            + "insert into HocVien (MaHV,MaKH,MaNH,Diem) values (?,?,?,?)"
            + "SET IDENTITY_INSERT HocVien OFF;";
    final String UPDATE_SQL = "UPDATE HocVien set MaKH = ? ,MaNH = ? ,Diem = ? where MaHV = ? ";
    final String DELETE_SQL = "delete hocvien where MaHV = ?  ";
    final String SELECT_ALL_SQL = "select * from hocvien";
    final String SELECT_BY_ID_SQL = "Select * from hocvien where MaHV = ? ";
    final String SELECT_BY_KHOAHOC_SQL = "Select * from hocvien where MaKH = ? ";
    final String SELECT_BY_MaxMaHV_SQL = "select max(MaHV) AS MaxMaHV from hocvien";

    @Override
    public void insert(HocVien entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaHocVien(), entity.getMaKhoaHoc(), entity.getMaNguoiHoc(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaKhoaHoc(), entity.getMaNguoiHoc(), entity.getDiem(), entity.getMaHocVien());
    }

    @Override
    public void delete(Integer id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<HocVien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public List<HocVien> selectByKhoaHoc(int MaKH) {
        return selectBySql(SELECT_BY_KHOAHOC_SQL, MaKH);
    }

    @Override
    public HocVien selectById(Integer id) {
        List<HocVien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HocVien> selectBySql(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                HocVien HV = new HocVien();
                HV.setMaHocVien(rs.getInt("MaHV"));
                HV.setMaKhoaHoc(rs.getInt("MaKH"));
                HV.setMaNguoiHoc(rs.getString("MaNH"));
                HV.setDiem(rs.getDouble("Diem"));
                list.add(HV);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int getMaxMaHV(){
        try {
            ResultSet rs = jdbcHelper.query(SELECT_BY_MaxMaHV_SQL);
            if(rs.next()){
                return rs.getInt("MaxMaHV");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;// Trả về 0 nếu không tìm thấy giá trị MAX(MaKH)
    }

}
