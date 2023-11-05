/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.ChuyenDe;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.jdbcHelper;
public class ChuyenDeDAO extends PolyproDAO<ChuyenDe, String >{
    final String INSERT_SQL = "insert into ChuyenDe (MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) values (?,?,?,?,?,?)";
    final String UPDATE_SQL = "update ChuyenDe set  TenCD = ?,HocPhi = ?,ThoiLuong = ?,Hinh = ?,MoTa = ? where MaCD = ?";
    final String DELETE_SQL = "delete ChuyenDe where MaCD = ?";
    final String SELECT_ALL_SQL = "select * from ChuyenDe";
    final String SELECT_BY_ID_SQL = "select * from ChuyenDe where MaCD = ?";
    @Override
    public void insert(ChuyenDe entity) {
        jdbcHelper.update(INSERT_SQL, entity.getMaChuyenDe(),entity.getTenChuyenDe(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa());
        
    }

    @Override
    public void update(ChuyenDe entity) {
        jdbcHelper.update(UPDATE_SQL, entity.getTenChuyenDe(),entity.getHocPhi(),entity.getThoiLuong(),entity.getHinh(),entity.getMoTa(),entity.getMaChuyenDe());
    }

    @Override
    public void delete(String id) {
        jdbcHelper.update(DELETE_SQL,id);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public ChuyenDe selectById(String id) {
        List<ChuyenDe> list = selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public ChuyenDe selectByTenDe(String TenCD) {
        List<ChuyenDe> list = selectBySql(SELECT_BY_ID_SQL, TenCD);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {                
                ChuyenDe CD = new ChuyenDe();
                CD.setMaChuyenDe(rs.getString("MaCD"));
                CD.setTenChuyenDe(rs.getString("TenCD"));
                CD.setHocPhi(rs.getDouble("HocPhi"));
                CD.setThoiLuong(rs.getInt("ThoiLuong"));
                CD.setHinh(rs.getString("Hinh"));
                CD.setMoTa(rs.getString("Mota"));
                list.add(CD);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
