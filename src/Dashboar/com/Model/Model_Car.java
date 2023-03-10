package Dashboar.com.Model;

import Dashboar.com.Helper.DateHelper;
import java.util.Date;

/**
 *
 * @author datdo
 */
public class Model_Car {

    private String maXe;
    private String tenXe;
    private String hangXe;
    private int sucChua = 0;
    private Date ngayNhap = DateHelper.now();
    private String hinhAnh;
    private String bienSo;
    private float giaThue = 0f;
    private float giaQuaHan = 0f;
    private boolean trangThai;

    public Model_Car() {
    }

    public Model_Car(String maXe, String tenXe, String hangXe, int sucChua, Date ngayNhap, String hinhAnh, String bienSo, float giaThue, float giaQuaHan, boolean trangThai) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.hangXe = hangXe;
        this.sucChua = sucChua;
        this.ngayNhap = ngayNhap;
        this.hinhAnh = hinhAnh;
        this.bienSo = bienSo;
        this.giaThue = giaThue;
        this.giaQuaHan = giaQuaHan;
        this.trangThai = trangThai;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getHangXe() {
        return hangXe;
    }

    public void setHangXe(String hangXe) {
        this.hangXe = hangXe;
    }

    public int getSucChua() {
        return sucChua;
    }

    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public float getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(float giaThue) {
        this.giaThue = giaThue;
    }

    public float getGiaQuaHan() {
        return giaQuaHan;
    }

    public void setGiaQuaHan(float giaQuaHan) {
        this.giaQuaHan = giaQuaHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return this.maXe;
    }

}
