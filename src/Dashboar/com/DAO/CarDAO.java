package Dashboar.com.DAO;

import Dashboar.com.Model.Model_Car;
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
public class CarDAO {

    public static Model_Car readFormResultSet(ResultSet r) throws SQLException {
        Model_Car model = new Model_Car();
        model.setMaXe(r.getString("Ma_Xe"));
        model.setTenXe(r.getString("Ten_Xe"));
        model.setHangXe(r.getString("Hang_Xe"));
        model.setSucChua(r.getInt("Suc_Chua"));
        model.setNgayNhap(r.getDate("Ngay_Nhap"));
        model.setHinhAnh(r.getString("Hinh_Anh"));
        model.setBienSo(r.getString("Bien_So"));
        model.setGiaThue(r.getFloat("Gia_Thue"));
        model.setGiaQuaHan(r.getFloat("Gia_Qua_Han"));
        model.setTrangThai(r.getBoolean("Trang_Thai"));
        return model;
    }

    public static List<Model_Car> select(String sql, Object... args) {
        List<Model_Car> list = new ArrayList<>();
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

    public List<Model_Car> select() {
        String sql = "SELECT * FROM `xe`";
        return select(sql);
    }

    public static Model_Car findById(String id) {
        String sql = "SELECT * FROM `xe` WHERE `Ma_Xe` = ?";
        List<Model_Car> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public void insert(Model_Car car) throws SQLException {
        String sql = "INSERT INTO `xe` (`Ma_Xe`, `Ten_Xe`, `Hang_Xe`, `Suc_Chua`, `Ngay_Nhap`, `Hinh_Anh`, `Bien_so`, `Gia_Thue`, `Gia_Qua_Han`, `Trang_Thai`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ConnectionDB.executeUpdate(sql,
                car.getMaXe(),
                car.getTenXe(),
                car.getHangXe(),
                car.getSucChua(),
                car.getNgayNhap(),
                car.getHinhAnh(),
                car.getBienSo(),
                car.getGiaThue(),
                car.getGiaQuaHan(),
                car.isTrangThai());

    }

    public void update(Model_Car car) throws SQLException {
        String sql = "UPDATE `xe` SET `Ma_Xe` =?, `Ten_Xe` =?, `Hang_Xe` =?, `Suc_Chua` = ?, `Ngay_Nhap` = ?, `Hinh_Anh` = ?, `Bien_so` = ?, `Gia_Thue` = ?, `Gia_Qua_Han` = ?, `Trang_Thai` = ? WHERE `xe`.`Ma_Xe` = ?";
        ConnectionDB.executeUpdate(sql,
                car.getMaXe(),
                car.getTenXe(),
                car.getHangXe(),
                car.getSucChua(),
                car.getNgayNhap(),
                car.getHinhAnh(),
                car.getBienSo(),
                car.getGiaThue(),
                car.getGiaQuaHan(),
                car.isTrangThai(),
                car.getMaXe());
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM `xe` WHERE `xe`.`Ma_Xe` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void updateStatus(String id) throws SQLException {
        String sql = "UPDATE `xe` SET `Trang_Thai`= 1 where `Ma_Xe` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public int countCar() {
        int count = 0;
        String sql = "SELECT COUNT(*) from xe";
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

    public int xeChuaThue() {
        int count = 0;
        String sql = "SELECT COUNT(*) from xe  WHERE `Trang_Thai` = 0";
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

    public String status(String id) {
        String status = "";
        try {
            String sql = "SELECT `Trang_Thai` FROM `xe` WHERE Ma_Xe =?;";
            ResultSet rs = ConnectionDB.Query(sql, id);
            while (rs.next()) {
                status = String.valueOf(rs.getBoolean(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
//    public static void main(String[] args) {
//        int count = countCar();
//        System.out.println(count);
//    }

  

}
