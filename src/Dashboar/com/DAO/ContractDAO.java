package Dashboar.com.DAO;

import Dashboar.com.Model.Model_Contract;
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
public class ContractDAO {

    public static Model_Contract readFormResultSet(ResultSet r) throws SQLException {
        Model_Contract model = new Model_Contract();
        model.setMaThue(r.getInt("Ma_Thue"));
        model.setMaXe(r.getString("Ma_Xe"));
        model.setTenXe(r.getString("Ten_Xe"));
        model.setBienSo(r.getString("Bien_So"));
        model.setMaKH(r.getString("MaKH"));
        model.setNgayThue(r.getDate("Ngay_Thue"));
        model.setNgayHienTai(r.getDate("Ngay_Hien_Tai"));
        model.setNgayTra(r.getDate("Ngay_Tra"));
        model.setGiaThue(r.getFloat("Gia_Thue"));
        model.setGiaQuaHan(r.getFloat("Gia_Qua_Han"));
        model.setTrangThai(r.getBoolean("Trang_Thai_Thue"));
        return model;
    }

    public static List<Model_Contract> select(String sql, Object... args) {
        List<Model_Contract> list = new ArrayList<>();
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

    public List<Model_Contract> select() {
        String sql = "SELECT * FROM `hopdong`";
        return select(sql);
    }

    public List<Model_Contract> selectMaHD() {
        String sql = "SELECT * FROM `hopdong` WHERE `Trang_Thai_Thue` = 0";
        return select(sql);
    }

//    public Model_Contract findByIdCar(String id) {
//        String sql = "SELECT * FROM `hopdong` WHERE `Ma_Xe` = ?";
//        List<Model_Contract> list = select(sql, id);
//        return !list.isEmpty() ? list.get(0) : null;
//    }
    public Model_Contract findByIdCT(String id) {
        String sql = "SELECT * FROM `hopdong` WHERE `MaKH` = ?";
        List<Model_Contract> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public Model_Contract findByIdrental(String id) {
        String sql = "SELECT * FROM `hopdong` WHERE `Ma_Thue` = ?";
        List<Model_Contract> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }

    public void insert(Model_Contract contract) throws SQLException {
        String sql = "INSERT INTO `hopdong` ( `Ma_Xe`, `Ten_Xe`, `Bien_So`, `MaKH`, `Ngay_Thue`, `Ngay_Hien_Tai`, `Ngay_Tra`, `Gia_Thue`, `Gia_Qua_Han`, `Trang_Thai_Thue`) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, '0')";
        ConnectionDB.executeUpdate(sql,
                contract.getMaXe(),
                contract.getTenXe(),
                contract.getBienSo(),
                contract.getMaKH(),
                contract.getNgayThue(),
                contract.getNgayHienTai(),
                contract.getNgayTra(),
                contract.getGiaThue(),
                contract.getGiaQuaHan()
        );
    }

    public void update(Model_Contract contract) throws SQLException {
        String sql = "UPDATE `hopdong` SET `Ma_Xe`=?,`Ten_Xe`=?,`Bien_So`=?,`MaKH`=?,`Ngay_Thue`=?,`Ngay_Hien_Tai`=?,`Ngay_Tra`=?,`Gia_Thue`=?,`Gia_Qua_Han`=?,`Trang_Thai_Thue`=? WHERE `Ma_Thue`=?";
        ConnectionDB.executeUpdate(sql,
                contract.getMaXe(),
                contract.getTenXe(),
                contract.getBienSo(),
                contract.getMaKH(),
                contract.getNgayThue(),
                contract.getNgayHienTai(),
                contract.getNgayTra(),
                contract.getGiaThue(),
                contract.getGiaQuaHan(),
                contract.isTrangThai(),
                contract.getMaThue());

    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM `hopdong` WHERE `Ma_Thue` = ?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public void updateStatusCar(String id) throws SQLException {
        String sql = "UPDATE `xe` SET`Trang_Thai`= 0 WHERE `Ma_Xe` =?";
        ConnectionDB.executeUpdate(sql, id);
    }

    public List<String> selectct() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * from `khachhang`";
            ResultSet rs = ConnectionDB.Query(sql);
            while (rs.next()) {
                list.add(rs.getString("MaKH"));
                list.add(rs.getString("Ho_Va_Ten"));
                list.add(rs.getString("Dia_Chi"));
                list.add(rs.getString("So_Dien_Thoai"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List< Model_Contract> selectID(String id) {
        List<Model_Contract> list = new ArrayList<>();
        Model_Contract model = new Model_Contract();
        try {
            String sql = "SELECT `Trang_Thai_Thue` FROM `hopdong` WHERE `Ma_Thue` = ?;";
            ResultSet rs = ConnectionDB.Query(sql, id);
            while (rs.next()) {
                model.setTrangThai(rs.getBoolean(1));

            }
            list.add(model);
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public String status(String id) {
        String status = "";
        try {
            String sql = "SELECT `Trang_Thai_Thue` FROM `hopdong` WHERE `Ma_Thue` = ?;";
            ResultSet rs = ConnectionDB.Query(sql, id);
            while (rs.next()) {
                status = String.valueOf(rs.getBoolean(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public int count() {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM `hopdong` WHERE `Trang_Thai_Thue` = 0";
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
//     String a = status("15");
//        System.out.println(a);
//    }
}
