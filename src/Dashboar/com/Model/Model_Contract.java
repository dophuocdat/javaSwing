package Dashboar.com.Model;

import Dashboar.com.Helper.DateHelper;
import java.util.Date;

/**
 *
 * @author datdo
 */
public class Model_Contract {

    private int maThue;
    private String maXe;
    private String tenXe;
    private String bienSo;
    private String maKH;
    private Date ngayThue;
    private Date ngayHienTai = DateHelper.now();
    private Date ngayTra;
    private float giaThue;
    private float giaQuaHan;
    private boolean trangThai;

    public Model_Contract() {
    }

    public Model_Contract(int maThue, String maXe, String tenXe, String bienSo, String maKH, Date ngayThue, Date ngayTra, float giaThue, float giaQuaHan, boolean trangThai) {
        this.maThue = maThue;
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.bienSo = bienSo;
        this.maKH = maKH;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.giaThue = giaThue;
        this.giaQuaHan = giaQuaHan;
        this.trangThai = trangThai;
    }

    public Model_Contract(int maThue) {
        this.maThue = maThue;
    }
    

    public int getMaThue() {
        return maThue;
    }

    public void setMaThue(int maThue) {
        this.maThue = maThue;
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

    public String getBienSo() {
        return bienSo;
    }

    public void setBienSo(String bienSo) {
        this.bienSo = bienSo;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public Date getNgayHienTai() {
        return ngayHienTai;
    }

    public void setNgayHienTai(Date ngayHienTai) {
        this.ngayHienTai = ngayHienTai;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
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
        return String.valueOf(this.maThue);
    }

}
