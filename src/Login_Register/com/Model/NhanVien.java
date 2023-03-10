/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login_Register.com.Model;

import Login_Register.com.Dao.NhanVienDAO;
import java.util.List;

/**
 *
 * @author datdo
 */
public class NhanVien {

    private String maNV;
    private String hovaTen;
    private String sDT;
    private String diaChi;
    private String hinhAnh;
    private String userName;
    private String password;
    private boolean chucVu;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hovaTen, String sDT, String diaChi, String userName, String password, boolean chucVu, String hinhAnh) {
        this.maNV = maNV;
        this.hovaTen = hovaTen;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.userName = userName;
        this.password = password;
        this.chucVu = chucVu;
        this.hinhAnh = hinhAnh;
    }

    public NhanVien(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public NhanVien(String maNV, String userName, String password, boolean chucVu) {
        this.maNV = maNV;
        this.userName = userName;
        this.password = password;
        this.chucVu = chucVu;
    }

    public NhanVien(String hovaTen, String sDT, String diaChi, String userName, String password) {
        this.hovaTen = hovaTen;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.userName = userName;
        this.password = password;
    }

    public NhanVien(String userName, String password, boolean chucVu) {
        this.userName = userName;
        this.password = password;
        this.chucVu = chucVu;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHovaTen() {
        return hovaTen;
    }

    public void setHovaTen(String hovaTen) {
        this.hovaTen = hovaTen;
    }

    public String getsDT() {
        return sDT;
    }

    public void setsDT(String sDT) {
        this.sDT = sDT;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isChucVu() {
        return chucVu;
    }

    public void setChucVu(boolean chucVu) {
        this.chucVu = chucVu;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return this.maNV;
    }

}
