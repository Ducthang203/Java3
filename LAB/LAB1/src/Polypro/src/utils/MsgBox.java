/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.JOptionPane;


public class MsgBox {
    /*
        hien thi thong bao cho nguoi dung
        parent : la cua so thong bao
        message : la thong bao
    */
    public static void alert(Component parent, String message){
        JOptionPane.showMessageDialog(parent, message,"Hệ thống quản lý đào tạo",JOptionPane.INFORMATION_MESSAGE);
    }
    /*
        hien thi thong bao va yeu cau nguoi dung xac nhan
        parent : la cua so thong bao
        message : la cau hoi yes/no
        return la ket qua nhan duoc true/false
    */
    public static boolean confirm(Component parent, String message){
        int result = JOptionPane.showConfirmDialog(parent, message,"Hệ thống quản lý đào tạo",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    } 
    /*
        hien thi thong bao yeu cau nhap du lieu
        parent : la cua so thong bao
        message : la thong bao nhac nho nhap
        return la ket qua nhan duoc tu nguoi su dung nhap vao
    */
    public static String prompt(Component parent, String message){
        return JOptionPane.showInputDialog(parent, message, "Hệ thống quản lý đào tạo", JOptionPane.INFORMATION_MESSAGE);
    }
}
