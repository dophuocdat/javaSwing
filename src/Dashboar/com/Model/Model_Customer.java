package Dashboar.com.Model;

/**
 *
 * @author datdo
 */
public class Model_Customer {

    private String maKh;
    private String maNV;
    private String hoVaTen;
    private boolean gioiTinh;
    private String sDT;
    private String diaChi;
    private String hinhAnh;
    private String username;
    private String password;

    public Model_Customer() {
    }

    public Model_Customer(String maKh, String maNV, String hoVaTen, boolean gioiTinh, String sDT, String diaChi, String hinhAnh, String username, String password) {
        this.maKh = maKh;
        this.maNV = maNV;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.sDT = sDT;
        this.diaChi = diaChi;
        this.hinhAnh = hinhAnh;
        this.username = username;
        this.password = password;
    }

    public Model_Customer(String maKh, String hoVaTen, String sDT, String diaChi) {
        this.maKh = maKh;
        this.hoVaTen = hoVaTen;
        this.sDT = sDT;
        this.diaChi = diaChi;

    }

    public String getMaKh() {
        return maKh;
    }

    public void setMaKh(String maKh) {
        this.maKh = maKh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.maKh;
    }

}
