package Dashboar.com.Model;

import Dashboar.com.Helper.DateHelper;
import java.util.Date;

/**
 *
 * @author datdo
 */
public class Model_RentalCar {

    private int maThanhToan;
    private int maHD;
    private String maXe;
    private String maNV;
    private String maKH;
    private Date ngayThue;
    private Date ngayHienTai = DateHelper.now();
    private Date ngayTra;
    private float giaThue;
    private float tongGia;

    public Model_RentalCar() {
    }

    public Model_RentalCar(int maThanhToan, int maHD, String maXe, String maNV, String maKH, Date ngayThue, Date ngayTra, float giaThue, float tongGia) {
        this.maThanhToan = maThanhToan;
        this.maHD = maHD;
        this.maXe = maXe;
        this.maNV = maNV;
        this.maKH = maKH;
        this.ngayThue = ngayThue;
        this.ngayTra = ngayTra;
        this.giaThue = giaThue;
        this.tongGia = tongGia;
    }

    public int getMaThanhToan() {
        return maThanhToan;
    }

    public void setMaThanhToan(int maThanhToan) {
        this.maThanhToan = maThanhToan;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

    public float getTongGia() {
        return tongGia;
    }

    public void setTongGia(float tongGia) {
        this.tongGia = tongGia;
    }

    @Override
    public String toString() {
        return String.valueOf(this.maHD);
    }

}
