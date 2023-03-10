package Dashboar.com.Form;

import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.swing.Notification;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
import java.awt.Color;
import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author datdo
 */
public class Profile extends javax.swing.JPanel {

    NhanVienDAO dao = new NhanVienDAO();
    private JFrame fr;
    NhanVien user;
    JFileChooser fileChooser = new JFileChooser();

    public Profile(NhanVien user) {
        this.user = user;
        initComponents();
        System.out.println(user.getMaNV());
        init();
    }

    private void init() {
        String id = user.getMaNV();
        NhanVien model = dao.showAll(id);
        setModel(model);
       txtHoTen.setPrefixIcon(IconHovaTen.toIcon());
       txtSDT.setPrefixIcon(iconPhone.toIcon());
       txtDiaChi.setPrefixIcon(iconDiaChi.toIcon());
       txtMatKhau.setPrefixIcon(googleMaterialIcon1.toIcon());
    }

    private NhanVien getmodel() {
        NhanVien model = new NhanVien();
        model.setHovaTen(txtHoTen.getText());
        model.setsDT(txtSDT.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setPassword(txtMatKhau.getText());
        model.setHinhAnh(lbIcon.getToolTipText());
        model.setMaNV(txtHoTen.getToolTipText());

        return model;
    }

    private void setModel(NhanVien model) {
        txtHoTen.setText(model.getHovaTen());
        txtSDT.setText(model.getsDT());
        txtDiaChi.setText(model.getDiaChi());
        txtMatKhau.setText(model.getPassword());
        lbIcon.setToolTipText(model.getHinhAnh());
        if (model.getHinhAnh() != null) {
            lbIcon.setIcon(ImageHelper.readImageNV(model.getHinhAnh()));
        } else {
            lbIcon.setIcon(ImageHelper.readImageNV("null"));

        }
        txtHoTen.setToolTipText(model.getMaNV());
    }

    private void doiMatKhau() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thêm mã xe này ? ", "Nhấn OK để cập nhật lại thông tin");
        if (txtThayDoiMK.getText().trim().length() > 0) {
            if (obj.getMessageType() == MessagDialog.MessageType.OK) {
                txtXacNhanMKM.setBackground(Color.white);
                txtMatKhau.setBackground(Color.white);
//        String mk = txtMatKhau.getText().trim();
                String newMk = txtThayDoiMK.getText().trim();
                String rlMk = txtXacNhanMKM.getText().trim();
                if (newMk.equals(rlMk)) {
                    try {
                        NhanVien model = getmodel();
                        model.setPassword(txtXacNhanMKM.getText().trim());
                        dao.updateProfile(model);
                        showNotification(fr, Notification.Type.SUCCESS, "Cập nhật thành công");
                    } catch (SQLException ex) {
                        Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    txtXacNhanMKM.setBackground(Color.pink);
                    showNotification(fr, Notification.Type.WARNING, "Vui lòng kiểm tra lại mật khẩu");
                }
            }
        } else {
            try {
                NhanVien model = getmodel();
                dao.updateProfile(model);
                showNotification(fr, Notification.Type.SUCCESS, "Cập nhật thành công");
            } catch (SQLException ex) {
                Logger.getLogger(Profile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void update() {
        doiMatKhau();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtChucVu = new javax.swing.JLabel();
        IconHovaTen = new javaswingdev.FontAwesomeIcon();
        iconPhone = new javaswingdev.FontAwesomeIcon();
        iconDiaChi = new javaswingdev.FontAwesomeIcon();
        googleMaterialIcon1 = new javaswingdev.GoogleMaterialIcon();
        lineHeader1 = new Dashboar.com.component.lineHeader();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtMatKhau = new Login_Register.com.Swing.MyTextFiled();
        txtSDT = new Login_Register.com.Swing.MyTextFiled();
        txtDiaChi = new Login_Register.com.Swing.MyTextFiled();
        txtXacNhanMKM = new Login_Register.com.Swing.MyTextFiled();
        txtThayDoiMK = new Login_Register.com.Swing.MyTextFiled();
        txtHoTen = new Login_Register.com.Swing.MyTextFiled();
        btSua = new Dashboar.com.swing.ButtonCustom();
        lbIcon = new Dashboar.com.swing.imageAvatar();

        txtChucVu.setText("jLabel1");

        IconHovaTen.setColor1(new java.awt.Color(220, 36, 36));
        IconHovaTen.setColor2(new java.awt.Color(74, 86, 157));
        IconHovaTen.setIcon(javaswingdev.FontAwesome.USER);

        iconPhone.setColor1(new java.awt.Color(220, 36, 36));
        iconPhone.setColor2(new java.awt.Color(74, 86, 157));
        iconPhone.setIcon(javaswingdev.FontAwesome.PHONE);

        iconDiaChi.setColor1(new java.awt.Color(43, 192, 228));
        iconDiaChi.setColor2(new java.awt.Color(234, 236, 198));
        iconDiaChi.setIcon(javaswingdev.FontAwesome.ADDRESS_CARD);

        googleMaterialIcon1.setColor1(new java.awt.Color(43, 192, 228));
        googleMaterialIcon1.setColor2(new java.awt.Color(234, 236, 198));
        googleMaterialIcon1.setIcon(javaswingdev.GoogleMaterialDesignIcon.KEYBOARD);

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PROFILE");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.gray)));

        txtMatKhau.setEditable(false);
        txtMatKhau.setHint("Mật Khẩu");

        txtSDT.setHint("Số Điện Thoại");

        txtDiaChi.setHint("Địa Chỉ");

        txtXacNhanMKM.setHint("Nhập Lại Mật Khẩu");
        txtXacNhanMKM.setName("Nhập lại mật khẩu"); // NOI18N

        txtThayDoiMK.setHint("Thay Đổi Mật Khẩu");
        txtThayDoiMK.setName("Đổi Mật Khẩu"); // NOI18N
        txtThayDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThayDoiMKActionPerformed(evt);
            }
        });

        txtHoTen.setHint("Họ và tên");

        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/updateCT.png"))); // NOI18N
        btSua.setText("Update");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbIconMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtXacNhanMKM, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(txtThayDoiMK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtThayDoiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtXacNhanMKM, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(83, 83, 83))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtThayDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThayDoiMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtThayDoiMKActionPerformed

    private void lbIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMousePressed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (ImageHelper.saveImageNhanVien(file)) {
                lbIcon.setIcon(ImageHelper.readImageNV(file.getName()));
                lbIcon.setToolTipText(file.getName());
            }
        }
    }//GEN-LAST:event_lbIconMousePressed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        update();

    }//GEN-LAST:event_btSuaActionPerformed
    private void showNotification(JFrame fr, Notification.Type type, String text) {
        Notification panel = new Notification(fr, type, text);
        panel.showNotification();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javaswingdev.FontAwesomeIcon IconHovaTen;
    private Dashboar.com.swing.ButtonCustom btSua;
    private javaswingdev.GoogleMaterialIcon googleMaterialIcon1;
    private javaswingdev.FontAwesomeIcon iconDiaChi;
    private javaswingdev.FontAwesomeIcon iconPhone;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private Dashboar.com.swing.imageAvatar lbIcon;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JLabel txtChucVu;
    private Login_Register.com.Swing.MyTextFiled txtDiaChi;
    private Login_Register.com.Swing.MyTextFiled txtHoTen;
    private Login_Register.com.Swing.MyTextFiled txtMatKhau;
    private Login_Register.com.Swing.MyTextFiled txtSDT;
    private Login_Register.com.Swing.MyTextFiled txtThayDoiMK;
    private Login_Register.com.Swing.MyTextFiled txtXacNhanMKM;
    // End of variables declaration//GEN-END:variables
}
