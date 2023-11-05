/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import DAO.ChuyenDeDAO;
import entity.ChuyenDe;
import java.util.List;


public class TestDemo {
    public static void main(String[] args) {
        ChuyenDeDAO dao = new ChuyenDeDAO();
     
//        dao.insert(new nhanvien("KienTT", "Tran Trung Kien", "123456", true));
        List<ChuyenDe> ls = dao.selectAll();
        for (ChuyenDe cd : ls) {
//            System.out.println("=> "+nv.getHoTen());
//            System.out.println("=> "+nv.getMaNhanVien());
//            System.out.println("=> "+nv.getMatKhau());
//            System.out.println("=> "+nv.isVaiTro());
//            System.out.println("=====================================");
            System.out.println("=> "+cd.getTenChuyenDe());
        }
//        ThongKeDAO dao = new ThongKeDAO();
//        List<Object[]> list = dao.getLuongNguoiHoc();
//        for (Object[] obj : list) {
//            System.out.println(" => "+obj[0]+ " => "+obj[1]+ " => "+obj[2]+ " => "+obj[3]);
//        }
    }

}
