/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import utils.jdbcHelper;

/**
 *
 * @author trung
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = jdbcHelper.query(sql, args);
            while (rs.next()) {
                Object[] values = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    values[i] = rs.getObject(cols[i]);
                }
                list.add(values);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Object[]> getBangDiem(Integer makh) {
        String Sql = "{CALL sp_BangDiem(?)}";
        String[] cols = {"MaNH","Hoten","Diem"};
        return getListOfArray(Sql, cols, makh);
    }
    
    public List<Object[]> getLuongNguoiHoc() {
        String Sql = "{CALL sp_LuongNguoiHoc}";
        String[] cols = {"Nam","SoLuong","DauTien","CuoiCung"};
        return getListOfArray(Sql, cols);
    }
    
    public List<Object[]> getDiemChuyenDe() {
        String Sql = "{CAll sp_DiemChuyenDe}";
        String[] cols = {"ChuyenDe","SoHocVien","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(Sql, cols);
    }
    
    public List<Object[]> getDoanhThu(Integer nam) {
        String Sql = "{Call sp_DoanhThu}";
        String[] cols = {"ChuyenDe","SoKhoaHoc","SoHocVien","DoanhThu","ThapNhat","CaoNhat","TrungBinh"};
        return getListOfArray(Sql, cols, nam);
    }

    
}
