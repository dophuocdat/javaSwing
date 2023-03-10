package Dashboar.com.component;

import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Model.Model_buttom;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

/**
 *
 * @author datdo
 */
public class Buttom extends javax.swing.JPanel {

    private NhanVien user;
    private NhanVienDAO dao = new NhanVienDAO();
    private float alpha;

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public Buttom(NhanVien user) {
        this.user = user;
        initComponents();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setOpaque(false);
        setBackground(new Color(65, 152, 216));
        System.out.println(user.getMaNV());
        test(user.getMaNV());
    }

    private void test(String id) {
        NhanVien user = dao.selectById(id);
        String chucVu = "";
        String ten = user.getHovaTen().trim();
        ten = ten.substring(ten.lastIndexOf(" ") + 1, ten.length());
        if (user.isChucVu() == true) {
            chucVu = "Quản Lý";
        } else {
            chucVu = "Nhân Viên";
        }
        Model_buttom bt = new Model_buttom(ImageHelper.readImageNV(user.getHinhAnh()), ten, chucVu);
        setData(bt);
    }

    public void setData(Model_buttom data) {
        lbIcon.setIcon(data.getIcon());
        lbName.setText(data.getName());
        lbRule.setText(data.getRule());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new Dashboar.com.swing.imageAvatar();
        lbName = new javax.swing.JLabel();
        lbRule = new javax.swing.JLabel();

        lbIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/profile.jpg"))); // NOI18N

        lbName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(207, 207, 207));
        lbName.setText(" Phước Đạt");

        lbRule.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbRule.setForeground(new java.awt.Color(207, 207, 207));
        lbRule.setText("Admin");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName)
                    .addComponent(lbRule))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRule))
                    .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 20, 20);
        super.paint(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboar.com.swing.imageAvatar lbIcon;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRule;
    // End of variables declaration//GEN-END:variables
}
