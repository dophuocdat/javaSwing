package Dashboar.com.DAO;

import Dashboar.com.Model.Model_Customer;
import Login_Register.com.Helper.ConnectionDB;
import Login_Register.com.Model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author datdo
 */
public class CustomerDAO {

    public static Model_Customer readFormResultSet(ResultSet r) throws SQLException {
        Model_Customer model = new Model_Customer();
        model.setMaKh(r.getString("MaKH"));
        model.setMaNV(r.getString("MaNV"));
        model.setHoVaTen(r.getString("Ho_Va_Ten"));
        model.setGioiTinh(r.getBoolean("Gioi_Tinh"));
        model.setsDT(r.getString("So_Dien_Thoai"));
        model.setDiaChi(r.getString("Dia_Chi"));
        model.setHinhAnh(r.getString("Hinh_Anh"));
        model.setUsername(r.getString("Username"));
        model.setPassword(r.getString("Password"));
        return model;
    }

    public static List<Model_Customer> select(String sql, Object... args) {
        List<Model_Customer> list = new ArrayList<>();
        ResultSet r = ConnectionDB.Query(sql, args);
        try {
            try {
                while (r.next()) {
                    list.add(readFormResultSet(r));

                }
            } finally {
                r.getStatement().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public List<Model_Customer> select() {
        String sql = "SELECT * FROM `khachhang`";
        return select(sql);
    }

    public void insert(Model_Customer ctm) throws SQLException {
        String sql = "INSERT INTO `khachhang` ( `MaKH`,`MaNV`, `Ho_Va_Ten`, `Gioi_Tinh`, `So_Dien_Thoai`, `Dia_Chi`, `Hinh_Anh`, `Username`, `Password`) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?);";
        ConnectionDB.executeUpdate(sql,
                ctm.getMaKh(),
                ctm.getMaNV(),
                ctm.getHoVaTen(),
                ctm.isGioiTinh(),
                ctm.getsDT(),
                ctm.getDiaChi(),
                ctm.getHinhAnh(),
                ctm.getUsername(),
                ctm.getPassword());
    }

    public Model_Customer findbyID(String id) {
        String sql = "SELECT * FROM `khachhang` WHERE `MaKH` = ?";
        List<Model_Customer> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public Model_Customer findbyIDNv(String id) {
        String sql = "SELECT * FROM `khachhang` WHERE `MaNV` =?";
        List<Model_Customer> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM `khachhang` WHERE `MaKH` =?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void update(Model_Customer ctm) throws SQLException {
        String sql = "UPDATE `khachhang` SET `MaNV`=?,`Ho_Va_Ten`=?,`Gioi_Tinh`=?,`So_Dien_Thoai`=?,`Dia_Chi`=?,`Hinh_Anh`=?,`Username`=?,`Password`=? WHERE `MaKH` =?";
        ConnectionDB.executeUpdate(sql,
                ctm.getMaNV(),
                ctm.getHoVaTen(),
                ctm.isGioiTinh(),
                ctm.getsDT(),
                ctm.getDiaChi(),
                ctm.getHinhAnh(),
                ctm.getUsername(),
                ctm.getPassword(),
                ctm.getMaKh());
    }

    public List<String> thongtin(String id) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT `MaKH`,`Ho_Va_Ten`,`Gioi_Tinh`,`So_Dien_Thoai`,`Dia_Chi` FROM `khachhang` where `MaKH` =?  ";
            ResultSet rs = ConnectionDB.Query(sql, id);
            while (rs.next()) {
                list.add(rs.getString("MaKH"));
                list.add(rs.getString("Ho_Va_Ten"));
                list.add(rs.getString("Gioi_Tinh"));
                list.add(rs.getString("So_Dien_Thoai"));
                list.add(rs.getString("Dia_Chi"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       public  int count() {
        int count = 0;
        String sql = "SELECT COUNT(*) from khachhang";
        ResultSet rs = ConnectionDB.Query(sql);
        try {
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

//    public static void main(String[] args) {
//        List<String> list = CustomerDAO.thongtin();
//        for (String string : list) {
//            System.out.println(string);
//            
//        }
//    }
}
