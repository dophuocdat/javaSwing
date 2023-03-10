package Login_Register.com.Dao;

import Login_Register.com.Helper.ConnectionDB;
import Login_Register.com.Model.DataLogin;
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
public class NhanVienDAO {
    
    public static NhanVien readFormResultSet(ResultSet r) throws SQLException {
        // NhanVien model = new NhanVien(maNV, hovaTen, sDT, diaChi, userName, password);
        NhanVien model = new NhanVien();
        model.setMaNV(r.getString("MaNV"));
        model.setHovaTen(r.getString("Ho_Va_Ten"));
        model.setsDT(r.getString("So_Dien_Thoai"));
        model.setDiaChi(r.getString("Dia_Chi"));
        model.setUserName(r.getString("Username"));
        model.setPassword(r.getString("Password"));
        model.setChucVu(r.getBoolean("Chuc_Vu"));
        model.setHinhAnh(r.getString("Hinh_Anh"));
        
        return model;
    }
    
    public static List<NhanVien> select(String sql, Object... agrs) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet r = (ResultSet) ConnectionDB.Query(sql, agrs);
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
    
    public List<NhanVien> select() {
        String sql = "SELECT * FROM `nhanvien`";
        return select(sql);
    }
    
    public NhanVien selectById(String id) {
        String sql = "SELECT * From `nhanvien` WHERE `MaNV` = ?";
        List<NhanVien> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }
    
    public NhanVien showAll(String id) {
        String sql = "SELECT * FROM `nhanvien` where `MaNV` = ?";
        List<NhanVien> list = select(sql, id);
        return !list.isEmpty() ? list.get(0) : null;
    }
    
    public static NhanVien login(DataLogin login) throws SQLException {
        NhanVien data = null;
        String sql = "SELECT `MaNV`,`Username`,`Password`,`Chuc_Vu` FROM `nhanvien` WHERE `Username` = ? AND `Password` = ?";
        PreparedStatement p = ConnectionDB.preparedStatement(sql,
                login.getUserName(),
                login.getPassword());
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            String maNV = rs.getString(1);
            String userName = rs.getString(2);
            String password = rs.getString(3);
            boolean chucvu = rs.getBoolean(4);
            data = new NhanVien(maNV, userName, password, chucvu);
            // System.out.println(data.getUserName() + "\n" + data.getPassword());
        }
        rs.close();
        p.close();
        return data;
    }
    
    public void updateProfile(NhanVien model) throws SQLException {
        String sql = "UPDATE `nhanvien` SET `Ho_Va_Ten`=?,`So_Dien_Thoai`=?,`Dia_Chi`=?,`Hinh_Anh`=?,`Password`=? where `MaNV` =?";
        ConnectionDB.executeUpdate(sql,
                model.getHovaTen(), model.getsDT(), model.getDiaChi(), model.getHinhAnh(), model.getPassword(), model.getMaNV()
        );
    }
    
    public void update(NhanVien model) throws SQLException {
        String sql = "UPDATE `nhanvien` SET `Ho_Va_Ten`=?,`So_Dien_Thoai`=?,`Dia_Chi`=?,`Hinh_Anh`=?,`Username`=?,`Password`=?,`Chuc_Vu`=? where `MaNV` =?";
        ConnectionDB.executeUpdate(sql,
                model.getHovaTen(), model.getsDT(), model.getDiaChi(), model.getHinhAnh(), model.getUserName(), model.getPassword(), model.isChucVu(), model.getMaNV()
        );
    }
    
    public void insert(NhanVien model) throws SQLException {
        String sql = "INSERT INTO `nhanvien`(`MaNV`, `Ho_Va_Ten`, `So_Dien_Thoai`, `Dia_Chi`, `Hinh_Anh`, `Username`, `Password`, `Chuc_Vu`) VALUES (?,?,?,?,?,?,?,?)";
        ConnectionDB.executeUpdate(sql,
                model.getMaNV(), model.getHovaTen(), model.getsDT(), model.getDiaChi(), model.getHinhAnh(), model.getUserName(), model.getPassword(), model.isChucVu());
    }
    
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM `nhanvien` WHERE `MaNV` =?";
        ConnectionDB.executeUpdate(sql, id);
        
    }
}
