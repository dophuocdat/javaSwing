package Login_Register.com.main;

import Login_Register.com.Component.Login_Register;
import Login_Register.com.Component.Message;
import Login_Register.com.Component.PanelCover;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Helper.ConnectionDB;
import Login_Register.com.Model.DataLogin;
import Login_Register.com.Model.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author datdo
 */
public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private PanelCover cover;
    private Login_Register login_Register;
    private final double addZise = 30;
    private final double coverSize = 40;
    private final double loginSize = 60;
    private final DecimalFormat df = new DecimalFormat("##0.###");
    private boolean isLogin;

    public Main() {
        initComponents();
        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
        //  nhận sự kiên
        ActionListener eventRegister = (ActionEvent e) -> {
            register();
        };
        ActionListener eventLogin = (ActionEvent e) -> {
            login();
        };

        login_Register = new Login_Register(eventRegister, eventLogin);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double size = coverSize;
                double fractionLogin;
                // thêm hiệu ứng dãn panel
                if (fraction <= 0.5f) {
                    size += fraction * addZise;
                } else {
                    size += addZise - fraction * addZise;
                }

                // khi login thay đổi từ 1 - 0  và 0 - 1
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;

                    /*thay đổi title ở bản cover theo hoạt ảnh qua phải*/
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight((1f - fraction) * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    /*thay đổi title ở bản cover theo hoạt ảnh qua trái*/
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }

                }
                if (fraction >= 0.5f) {
                    login_Register.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(login_Register, "Width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%"); ////chia layout panel cover theo tỉ lệ loginSie
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }

        };
        // thêm animation với với 
        //tham số thời gian hoàn thành hoạt ảnh
        // tham số 2  đích hoạt ảnh
        Animator animator = new Animator(750, target);
        // xét tốc độ hoạt ảnh
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // hoạt ảnh chạy mượt hơn
        bg.setLayout(layout);
        // bg.setLayer(bg, ERROR);
        bg.add(cover, "Width " + coverSize + "%, pos 0al 0 n 100%"); //  add panel cover vào frame, và chiến 60% frame
        bg.add(login_Register, "Width " + loginSize + "%, pos 1al 0 n 100%");//add panel cover vào frame, và chiến 40% frame
        cover.addEvent((ActionEvent e) -> {
            // System.out.println("kick");
            if (!animator.isRunning()) {
                animator.start();
            }
        });
    }

    // sử lý sự kiện register
    public void register() {
        //System.out.println("kick Register");
        //  NhanVien nv = loginAndRegister.getModel();
        // System.out.println(nv.getUserName() + "\n" + nv.getPassword());
    }

    //  sử lý sự kiện login
    public void login() {
        System.out.println("Click Login");
        login_Register.getDataLogin();
        DataLogin dt = login_Register.getDataLogin();
        try {
            NhanVien nv = NhanVienDAO.login(dt);
            if (nv != null) {
                System.out.println(nv.getMaNV());
                // showMessage(Message.MessageType.SUCCESS, "SUCCESS");

                this.dispose();
                Dashboar.com.main.Main.main(nv);

            } else {
                showMessage(Message.MessageType.ERROR, "UserName and password incorrect");

            }
        } catch (SQLException ex) {
            showMessage(Message.MessageType.ERROR, "Error");

        }
        //  System.out.println(dt.getUserName() + "\n" + dt.getPassword());
        //showMessage(Message.MessageType.ERROR, "Login success");
    }

    //show message
    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); // insert to bg first index o;
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();

                } else {
                    ms.setShow(true);
                }
            }

        };
        // thiết lập hoạt ảnh và thời gian hoạt ảnh
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                animator.start();

            }
        }).start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        try {
            //</editor-fold>
            ConnectionDB.getIntance().connection();
        } catch (SQLException ex) {
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
