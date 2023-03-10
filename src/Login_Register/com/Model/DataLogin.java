/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Login_Register.com.Model;

/**
 *
 * @author datdo
 */
public class DataLogin {

    private String maNV;
    private String userName;
    private String password;

    public DataLogin() {
    }

    public DataLogin(String userName, String password) {
        this.userName = userName;

        this.password = password;
    }

    public DataLogin(String maNV, String userName, String password) {
        this.maNV = maNV;
        this.userName = userName;
        this.password = password;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

}
