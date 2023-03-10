package Login_Register.com.Helper;

import Login_Register.com.Model.NhanVien;

/**
 *
 * @author datdo
 */
public class authHelper {

    public static NhanVien USER = null;

    // xoa thong tin nguoi dung khi có yeu cau dang xuat
    public static void logout() {
        authHelper.USER = null;
    }

    public static boolean authenticated() {
        return authHelper.USER != null;
    }
}
