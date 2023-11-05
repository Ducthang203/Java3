/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.nhanvien;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;
import java.sql.ResultSet;

/**
 *
 * @author trung
 */
public class NhanVienDAO extends PolyproDAO<nhanvien, String> {

    final String INSERT_SQL = "insert into NhanVien (MaNV,MatKhau,HoTen,VaiTro) values (?,?,?,?);";
    final String UPDATE_SQL = "update nhanvien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?;";
    final String DELETE_SQL = "delete nhanvien where MaNV = ?;";
    final String SELECT_ALL_SQL = "select * from nhanvien";
    final String SELECT_BY_ID_SQL = "select * from nhanvien where MaNV = ?";

    @Override
    public void insert(nhanvien entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaNhanVien(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro());
    }

    @Override
    public void update(nhanvien entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getMaNhanVien());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<nhanvien> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public nhanvien selectById(String id) {
        List<nhanvien> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<nhanvien> selectBySql(String sql, Object... args) {
        List<nhanvien> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                nhanvien entity = new nhanvien();
                entity.setMaNhanVien(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

}
