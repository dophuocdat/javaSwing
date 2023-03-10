package Dashboar.com.main;

import Dashboar.com.Form.Home;
import Dashboar.com.Form.Car;
import Dashboar.com.Form.Contract;
import Dashboar.com.Form.CustomerMgm;
import Dashboar.com.Form.EmployeeMgm;
import Dashboar.com.Form.Payment;
import Dashboar.com.Form.Profile;
import Dashboar.com.Form.Report;
import Dashboar.com.Form.introduce;
import Dashboar.com.Model.ModelMenu;
import Dashboar.com.component.Menu;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author datdo
 */
public final class Main extends javax.swing.JFrame {

    private NhanVien user;

    private Menu menu;
    private JPanel main = new JPanel();
    private MigLayout layout;
    private Animator animator;
    private boolean menuShow;

    public Main(NhanVien user) {
        initComponents();
        this.user = user;
        setIconImage(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/CarSystem.png")).getImage());
        getContentPane().setBackground(new Color(255, 255, 255));
        //System.out.println(user.getMaNV());
        init();
    }

    private void init() {
        menu = new Menu(user);
        layout = new MigLayout("fill", "0[]0[]0", "0[fill]0"); // set layout cho body
        body.setLayout(layout);
        main.setOpaque(false);
        main.setLayout(new BorderLayout()); // layout mới các thành phần không có khoảng cách
        menu.addMenuEvent((ActionEvent e) -> {
            System.out.println("clik menu");
            if (!animator.isRunning()) {
                animator.start();
            }
        });
        menu.addLogOutEvent((e) -> {
            System.out.println("LogOut");
            this.dispose();
            new Login_Register.com.main.Main().setVisible(true);

        });
        menu.setEvent((int index) -> {
            if (index == 0) {
                showForm(new Home());
            } else if (index == 1) {
                showForm(new Car());
            } else if (index == 2) {
                showForm(new Contract());
            } else if (index == 3) {
                showForm(new Payment(user));
            } else if (index == 4) {
                showForm(new CustomerMgm(user));
            } else if (index == 5) {
                showForm(new introduce());
            } else if (index == 6 && user.isChucVu() == true) {
                showForm(new Report());
            } else if (index == 6 && !user.isChucVu()) {
                showForm(new Profile(user));
            } else if (index == 7 && user.isChucVu() == true) {
                showForm(new EmployeeMgm(user));
            } else if (index == 7 && !user.isChucVu()) {
                showForm(new Profile(user));
            } else if (index == 8) {
                showForm(new Profile(user));
            }
            //System.out.println("Menu Select: " + index);
        });

        // luu ý chi có admin mới xem được report
        if (user.isChucVu() == true) {
            menu.addMenu(new ModelMenu("Home", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/home.png"))));
            menu.addMenu(new ModelMenu("Car", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/car.png"))));
            menu.addMenu(new ModelMenu("Contract", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/contract.png"))));
            menu.addMenu(new ModelMenu("Payment", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/payment.png"))));
            menu.addMenu(new ModelMenu("Customer Manager", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/customer.png"))));
            menu.addMenu(new ModelMenu("Introduce", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/Introduce.png"))));
            menu.addMenu(new ModelMenu("Report", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/report.png"))));
            menu.addMenu(new ModelMenu("Employee Manager", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/employee.png"))));
        } else {
            menu.addMenu(new ModelMenu("Home", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/home.png"))));
            menu.addMenu(new ModelMenu("Car", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/car.png"))));
            menu.addMenu(new ModelMenu("Contract", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/contract.png"))));
            menu.addMenu(new ModelMenu("Payment", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/payment.png"))));
            menu.addMenu(new ModelMenu("Customer Manager", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/customer.png"))));
            menu.addMenu(new ModelMenu("Introduce", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/Introduce.png"))));

        }
        menu.addMenu(new ModelMenu("Profile", new ImageIcon(getClass().getResource("/Dashboar/com/Icon/user.png"))));
        body.add(menu, "w 50!"); //min:preferred:max dấu chấm than định dạng cho tất cả 50:50:50 thay vì null:50:null...
        body.add(main, "w 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menuShow) {
                    // System.out.println(fraction); 
                    width = 50 + (150 * (1f - fraction)); // run 200 -> 50
                    menu.setAlpha(1f - fraction);
                    // System.out.println(width);
                } else {
                    width = 50 + (150 * fraction);
                    menu.setAlpha(fraction);
                    // System.out.println(width); // run 50 -> 200
                }
                layout.setComponentConstraints(menu, "w " + width + "!");
                body.revalidate();
            }

            @Override
            public void end() {
                menuShow = !menuShow;
            }

        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        showForm(new Home());
    }

    private void showForm(Component com) {
        main.removeAll();
        main.add(com);
        main.repaint();
        main.revalidate();

    }

   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RENTAL CAR SYSTEM");
        setResizable(false);

        body.setBackground(new java.awt.Color(245, 245, 245));
        body.setPreferredSize(new java.awt.Dimension(1000, 600));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(NhanVien user) {
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
//        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
