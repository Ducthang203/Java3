/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {

    static SimpleDateFormat formater = new SimpleDateFormat();

    /**
     * Chuyen doi String sang Date
     *
     * date la String can chuyen pattern la dinh dang thoi gian return Date la
     * ket qua
     */
    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Chuyen doi Date sang String
     *
     * date la Date can chuyen pattern la dinh dang thoi gian return String la
     * ket qua
     */
    public static String toString(Date date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.format(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
        tra ve thoi gian hien tai
     */
    public static Date now() {
        return new Date();
    }

    /**
     * Boo sung so ngay vao thoi gian
     *
     * date la thoi gian hien co days so ngay can bo sung vao date return Date
     * la ket qua
     */
    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }
}
