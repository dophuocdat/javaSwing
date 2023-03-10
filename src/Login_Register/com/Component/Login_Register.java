package Login_Register.com.Component;

import Login_Register.com.Model.DataLogin;
import Login_Register.com.Model.NhanVien;
import Login_Register.com.Swing.Button;
import Login_Register.com.Swing.MyPassword;
import Login_Register.com.Swing.MyTextFiled;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author datdo
 */
public class Login_Register extends javax.swing.JLayeredPane {

    public NhanVien model;
    public DataLogin dataLogin;

    public NhanVien getModel() {
        return model;
    }

    public DataLogin getDataLogin() {
        return dataLogin;
    }

    public Login_Register(ActionListener eventRegister, ActionListener eventLogin) {
        initComponents();
        initRegister(eventRegister);
        initLogin(eventLogin);
        Login.setVisible(false);
        Register.setVisible(true);

    }

    private void initRegister(ActionListener eventRegister) {
        Register.setLayout(new MigLayout("wrap", "push[center]push", "push[]30[]10[]10[]10[]10[]25[]push"));
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font(Font.SANS_SERIF, 1, 30));
        label.setForeground(new Color(14, 107, 237));
        Register.add(label);
        MyTextFiled txtFullName = new MyTextFiled();
        txtFullName.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/name.png")));
        txtFullName.setHint("Fullname");
        Register.add(txtFullName, "w 60%");

        MyTextFiled txtSDT = new MyTextFiled();
        txtSDT.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/phone.png")));
        txtSDT.setHint("Nuumber phone");
        Register.add(txtSDT, "w 60%");

        MyTextFiled txtDiaChi = new MyTextFiled();
        txtDiaChi.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/address.png")));
        txtDiaChi.setHint("Address");
        Register.add(txtDiaChi, "w 60%");

        MyTextFiled txtUser = new MyTextFiled();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/User.png")));
        txtUser.setHint("Username");
        Register.add(txtUser, "W 60%");

        MyPassword txtPassword = new MyPassword();
        txtPassword.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/password.png")));
        txtPassword.setHint("Password");
        Register.add(txtPassword, "w 60%");

        Button cmd = new Button();
        cmd.addActionListener(eventRegister);
        cmd.setBackground(new Color(14, 107, 237));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        cmd.addActionListener((ActionEvent e) -> {
            String fullName = txtFullName.getText().trim();
            String DiaChi = txtDiaChi.getText().trim();
            String username = txtUser.getText().trim();
            String numPhone = txtSDT.getText().trim();
            String password = String.valueOf(txtPassword.getPassword());
            model = new NhanVien(fullName,numPhone, DiaChi, username, password);

        });
        Register.add(cmd, "w 40%, h 40 ");

    }

    private void initLogin(ActionListener eventLogin) {
        Login.setLayout(new MigLayout("Wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        JLabel label = new JLabel("Sign In");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(14, 107, 237));
        Login.add(label);

        MyTextFiled txtUserName = new MyTextFiled();
        txtUserName.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/User.png")));
        txtUserName.setHint("UserName");
        Login.add(txtUserName, "w 60%");

        MyPassword txtPass = new MyPassword();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/password.png")));
        txtPass.setHint("Password");
        Login.add(txtPass, "w 60%");

        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.add(cmdForget);

        Button cmd = new Button();
        cmd.setBackground(new Color(14, 107, 237));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN IN");
        cmd.addActionListener(eventLogin);
        cmd.addActionListener((ActionEvent e) -> {
            String userName = txtUserName.getText().trim();
            String password = String.valueOf(txtPass.getPassword());
            dataLogin = new DataLogin(userName, password);
        });
        Login.add(cmd, "w 40%, h 40");
    }

    public void showRegister(boolean isShow) {
        if (isShow) {
            Register.setVisible(true);
            Login.setVisible(false);
        } else {
            Register.setVisible(false);
            Login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Login = new javax.swing.JPanel();
        Register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Login, "card2");

        Register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout RegisterLayout = new javax.swing.GroupLayout(Register);
        Register.setLayout(RegisterLayout);
        RegisterLayout.setHorizontalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        RegisterLayout.setVerticalGroup(
            RegisterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(Register, "card3");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Register;
    // End of variables declaration//GEN-END:variables
}
