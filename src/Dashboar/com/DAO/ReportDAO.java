/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboar.com.DAO;

import Login_Register.com.Helper.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author datdo
 */
public class ReportDAO {

    public List<String> fillHangXe() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT xe.Hang_Xe FROM `xe` GROUP BY xe.Hang_Xe";
            ResultSet rs = ConnectionDB.Query(sql);

            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Object[]> getThongKeHang(String Hang) {
        List<Object[]> list = new ArrayList<>();
        try {

            String sql = "{CALL `ThongKeTheoHang`(?)}";
            ResultSet rs = ConnectionDB.Query(sql, Hang);
            while (rs.next()) {
                Object[] model = {
                    rs.getString("Ma_Xe"),
                    rs.getString("Ten_Xe"),
                    rs.getString("Hang_Xe"),
                    rs.getFloat("Tong_Gia"),};
                list.add(model);
            }
        } catch (SQLException e) {
            throw new RuntimeException();

        }
        return list;
    }

    public List<Object[]> getThongKeThoiGian(String DateFrom, String toDate) {
        List<Object[]> list = new ArrayList<>();
        try {
            String sql = "{CALL `ThongKeTheoTG`(?,?)}";
            ResultSet rs = ConnectionDB.Query(sql, DateFrom, toDate);
            while (rs.next()) {
                Object[] model = {
                    rs.getString("Ma_KH"),
                    rs.getString("Ma_Xe"),
                    rs.getString("Ten_Xe"),
                    rs.getString("Hang_Xe"),
                    rs.getDate("Ngay_Thue"),
                    rs.getDate("Ngay_Tra"),
                    rs.getFloat("tong_Gia")
                };
                list.add(model);
            }
        } catch (SQLException e) {
            throw new RuntimeException();

        }
        return list;
    }

    public String getTongThongKeTG(String DateFrom, String toDate) {
        String ouput = "";
        CallableStatement cstmt = null;
        String sql = "{CALL `ThongKeTongGia`(?,?,?)}";
        try {
            cstmt = (CallableStatement) ConnectionDB.preparedStatement(sql);
            cstmt.setString(1, DateFrom);
            cstmt.setString(2, toDate);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();
            ouput = cstmt.getString(3);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ouput;

    }

//    public static void main(String[] args) {
//        String a = getTongThongKeTG("2022-11-29", "2022-11-30");
//        System.out.println(a);
//    }
}
