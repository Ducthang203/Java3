/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.KhoaHoc;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import utils.jdbcHelper;

public class KhoaHocDAO extends PolyproDAO<KhoaHoc, Integer> {
    java.sql.Date sqlDate;
    final String INSERT_SQL = "insert into KhoaHoc (MaKH,MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) values (?,?,?,?,?,?,?,?)";
    final String UPDATE_SQL = "UPDATE KhoaHoc set MaCD = ? ,HocPhi = ?,ThoiLuong = ?,NgayKG = ?,GhiChu = ?,MaNV = ?,NgayTao = ? Where MaKH = ?";
    final String DELETE_SQL = "delete KhoaHoc where MaKH = ?";
    final String SELECT_ALL_SQL = "select * from KhoaHoc";
    final String SELECT_BY_ID_SQL = "select * from KhoaHoc where MaKH = ?";
    final String SELECT_BY_CHUYENDE_SQL = "select * from KhoaHoc where MaCD = ?";
    final String SELECT_BY_MaxMaKH_SQL = "select max(MaKH) AS MaxMaKH from KhoaHoc";
    

    @Override
    public void insert(KhoaHoc entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaKhoaHoc(), entity.getMaChuyenDe(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKhaiGiang(), entity.getGhiChu(), entity.getMaNhanVien(), entity.getNgayTao());
    }
    public Date convertDate(java.util.Date date) {
        sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    @Override
    public void update(KhoaHoc entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getMaChuyenDe(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKhaiGiang(), entity.getGhiChu(), entity.getMaNhanVien(), entity.getNgayTao(), entity.getMaKhoaHoc());
    }

    @Override
    public void delete(Integer id) {

        jdbcHelper.update(DELETE_SQL, id);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }
    

    @Override
    public KhoaHoc selectById(Integer id) {
        List<KhoaHoc> list = selectBySql(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }
    public List<KhoaHoc> selectByChuyenDe(String MaCD){
       return selectBySql(SELECT_BY_CHUYENDE_SQL, MaCD);   
    }

    @Override
    public List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                KhoaHoc KH = new KhoaHoc();
                KH.setMaKhoaHoc(rs.getInt("MaKH"));
                KH.setMaChuyenDe(rs.getString("MaCD"));
                KH.setHocPhi(rs.getDouble("HocPhi"));
                KH.setThoiLuong(rs.getInt("ThoiLuong"));
                KH.setNgayKhaiGiang(rs.getDate("NgayKG"));
                KH.setGhiChu(rs.getString("GhiChu"));
                KH.setMaNhanVien(rs.getString("MaNV"));
                KH.setNgayTao(rs.getDate("NgayTao"));
                list.add(KH);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public int getMaxMaKH(){
        try {
            ResultSet rs = jdbcHelper.query(SELECT_BY_MaxMaKH_SQL);
            if(rs.next()){
                return rs.getInt("MaxMaKH");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return 0;// Trả về 0 nếu không tìm thấy giá trị MAX(MaKH)
    }

    public List<Integer> selectYears() {
        String sql="SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";
        List<Integer> list=new ArrayList<>();
        try {
           ResultSet rs = jdbcHelper.query(sql);
           while(rs.next()){
                 list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } 
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    

}
