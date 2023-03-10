package Login_Register.com.Component;

import Login_Register.com.Swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.sound.sampled.Mixer;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author datdo
 */
public class PanelCover extends javax.swing.JPanel {

    private ActionListener event;
    private final MigLayout layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));

    public PanelCover() {
        initComponents();
        layout = new MigLayout("wrap, fill", "[center]", "push[]25[]10[]25[]push");
        setLayout(layout);
        setOpaque(false);
        init();
    }

    private void init() {
        title = new JLabel("Welcome Back");
        title.setFont(new Font(Font.SANS_SERIF, 1, 30));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        description = new JLabel("Login with your personal info");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        description1 = new JLabel("To keep connected with us please");
        description1.setForeground(new Color(245, 245, 245));
        add(description1);
        button = new ButtonOutLine();
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(255, 255, 255));
        button.setText("SIGN IN");
        // nhận sự kiện và thực hiện 
        button.addActionListener((ActionEvent e) -> {
            event.actionPerformed(e);
        });
        add(button, "w 60%, h 40");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(14, 107, 237), 0, getHeight(), new Color(80, 123, 199));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    //thêm sự kiện
    public void addEvent(ActionListener event) {
        this.event = event;
    }

    public void login(boolean login) {
        if (this.isLogin != login) {
            if (login) {
                title.setText("Hello, Friend!");
                description.setText("Enter your personal details");
                description1.setText("and start journey with us");
                button.setText("SIGN UP");

            } else {
                title.setText("WELCOM BACK!");
                description.setText("To keep connected with us please");
                description1.setText("login with your personal info");
                button.setText("SIGN IN");
            }
            this.isLogin = login;
        }
    }

    /*thay đổi register bên trái, register bên phải
     thay đổi Login bên trái, login bên phải
     */
    public void registerRight(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void registerLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }

    public void loginRight(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 0");

    }

    public void loginLeft(double v) {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 0");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
