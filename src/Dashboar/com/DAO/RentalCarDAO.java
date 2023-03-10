package Dashboar.com.DAO;

import Dashboar.com.Model.Model_Contract;
import Dashboar.com.Model.Model_RentalCar;
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
public class RentalCarDAO {

    public static Model_RentalCar readFormResultSet(ResultSet r) throws SQLException {
        Model_RentalCar model = new Model_RentalCar();
        model.setMaThanhToan(r.getInt("Ma_Thanh_Toan"));
        model.setMaHD(r.getInt("Ma_HD"));
        model.setMaXe(r.getString("ma_Xe"));
        model.setMaNV(r.getString("MaNV"));
        model.setMaKH(r.getString("Ma_KH"));
        model.setNgayThue(r.getDate("Ngay_Thue"));
        model.setNgayHienTai(r.getDate("Ngay_Hien_Tai"));
        model.setNgayTra(r.getDate("Ngay_Tra"));
        model.setGiaThue(r.getFloat("Gia_Thue"));
        model.setTongGia(r.getFloat("tong_Gia"));
        return model;
    }

    public static List<Model_RentalCar> select(String sql, Object... args) {
        List<Model_RentalCar> list = new ArrayList<>();
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

    public List<Model_RentalCar> select() {
        String sql = "SELECT * FROM `thanhtoan`";
        return select(sql);
    }

    public Model_RentalCar findbyidHD(String id) {
        String sql = "SELECT * FROM `thanhtoan` WHERE `Ma_Thanh_Toan` = ?";
        List<Model_RentalCar> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public void insert(Model_RentalCar model) throws SQLException {
        String sql = "INSERT INTO `thanhtoan` (`Ma_HD`, `ma_Xe`, `MaNV`, `Ma_KH`, `Ngay_Thue`, `Ngay_Hien_Tai`, `Ngay_Tra`, `Gia_Thue`, `tong_Gia`) VALUES ( ?, ?, ?, ?, ?, ?,?, ?, ?);";
        ConnectionDB.executeUpdate(sql,
                model.getMaHD(),
                model.getMaXe(),
                model.getMaNV(),
                model.getMaKH(),
                model.getNgayThue(),
                model.getNgayHienTai(),
                model.getNgayTra(),
                model.getGiaThue(),
                model.getTongGia());
    }

    public void insertAndUpdate(Model_RentalCar model) throws SQLException {
        String sql = "{CALL `inserts`(?,?,?,?,?,?,?,?,?)}";
        ConnectionDB.executeUpdate(sql,
                model.getMaHD(),
                model.getMaXe(),
                model.getMaNV(),
                model.getMaKH(),
                model.getNgayThue(),
                model.getNgayHienTai(),
                model.getNgayTra(),
                model.getGiaThue(),
                model.getTongGia());
    }

    public void deleteAndUpdate(String idCar, int idTT) throws SQLException {
        String sql = "{CALL `Delete`(?,?)}";
        ConnectionDB.executeUpdate(sql, idTT,idCar);

    }

    // thay đổi trạng thái sau khi thanh toán
    // trạng thái hợp đồng chuyển thành đã thanh toán
    // trạng thái xe chuyển thành chưa thuê
    public void updateStatusHD(String id) throws SQLException {
        String sql = " UPDATE `hopdong` SET `Trang_Thai_Thue` = 1 WHERE `Ma_Thue` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void updateStatusCar(String id) throws SQLException {
        String sql = "UPDATE `xe` SET`Trang_Thai`= 0 WHERE `Ma_Xe` =?";
        ConnectionDB.executeUpdate(sql, id);
    }

    // thay đổi trạng thái khi payment delete
    // trạng thái hợp đồng chuyển thành Chưa thanh toán
    // trạng thái xe chuyển thành đã thuê
    public void updateStatusHD2(String id) throws SQLException {
        String sql = " UPDATE `hopdong` SET `Trang_Thai_Thue` = 0 WHERE `Ma_Thue` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void updateStatusCar2(String id) throws SQLException {
        String sql = "UPDATE `xe` SET`Trang_Thai`= 1 WHERE `Ma_Xe` =?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM `thanhtoan` WHERE `Ma_Thanh_Toan` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public List<String> thongtin(String id) {
        List<String> list = new ArrayList<>();
        try {
            // String sql = "SELECT `Ma_Thanh_Toan`,`Ma_HD`,`ma_Xe`,`MaNV`,`Ngay_Thue`,`Ngay_Hien_Tai`,`Ngay_Tra`,`Gia_Thue`,`tong_Gia` FROM `thanhtoan` where `Ma_Thanh_Toan` = ?  ";
            String sql = "SELECT `Ma_Thanh_Toan`,`Ma_HD`,thanhtoan.`ma_Xe`,`MaNV`,thanhtoan.`Ngay_Thue`,thanhtoan.`Ngay_Hien_Tai`,thanhtoan.`Ngay_Tra`,thanhtoan.`Gia_Thue`,"
                    + "`tong_Gia`, hopdong.Ten_Xe FROM `thanhtoan` INNER JOIN hopdong on thanhtoan.Ma_HD = hopdong.Ma_Thue WHERE Ma_Thanh_Toan = ? ";
            ResultSet rs = ConnectionDB.Query(sql, id);
            while (rs.next()) {
                list.add(rs.getString("Ma_Thanh_Toan"));
                list.add(rs.getString("Ma_HD"));
                list.add(rs.getString("ma_Xe"));
                list.add(rs.getString("MaNV"));
                list.add(rs.getString("Ngay_Thue"));
                list.add(rs.getString("Ngay_Hien_Tai"));
                list.add(rs.getString("Ngay_Tra"));
                list.add(rs.getString("Gia_Thue"));
                list.add(rs.getString("tong_Gia"));
                list.add(rs.getString("Ten_Xe"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

//     public static void main(String[] args) {
//          List<String> list = RentalCarDAO.thongtin();
//         for (String string : list) {
//             System.out.println(string);
//             
//         }
//       }
}
