/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.nhanvien;


public class Auth {
    // tao doi tuong user , doi tuong nay chua thong tin  nguoi su dung sau khi dang nhap 
    public static nhanvien user = null;
    // xoa thong tin cua nguoi su dung khi co yeu cau dang xuat
    public static void clear(){
        Auth.user = null;
    }
    // kiem tra xem nguoi dung da dang nhap hay chua
    public static boolean isLogin(){
        return Auth.user != null;
        
    }
    //kiem tra vai tro xem nguoi dung la quan ly hay nhan vien
    public static boolean isManager(){
        return Auth.isLogin() && user.isVaiTro();
    }
    
}
