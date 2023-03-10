package Dashboar.com.component;

import Dashboar.com.Event.EventMenuSelected;
import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Model.ModelMenu;
import Dashboar.com.Model.Model_buttom;
import Dashboar.com.swing.ButtonCustom;
import Dashboar.com.swing.MenuItem;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author datdo
 */
public class Menu extends javax.swing.JPanel {

    private NhanVien user;
    private NhanVienDAO dao = new NhanVienDAO();

    private MigLayout layout;
    private JPanel panelMenu;
    private JButton cmdMenu;
    private JButton cmdLogOut;
    private Header header;
    private Buttom buttom;
    private EventMenuSelected event;

    public void setEvent(EventMenuSelected event) {
        this.event = event;
    }

    public Menu(NhanVien user) {
        this.user = user;
        initComponents();
        setOpaque(false);
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "5[]0[]push[60]0"));
        panelMenu = new JPanel();
        header = new Header();
        buttom = new Buttom(user);
        createButtonMenu();
        createButtonLogOut();
        panelMenu.setOpaque(false);
        layout = new MigLayout(" fillx, wrap", "0[fill]0", "50[]3[]0"); // set layout cho panel menu top 50, 3, buttom 0
        panelMenu.setLayout(layout);
        add(cmdMenu, "pos 1al 0al 100% 50");
        add(header); // thêm header
        add(cmdLogOut, "pos 1al 1al 100% 100,height 60!");
        add(panelMenu);
        add(buttom);

    }

    public void addMenu(ModelMenu menu) {
        MenuItem item = new MenuItem(menu.getIcon(), menu.getMenuName(), panelMenu.getComponentCount());
        item.addEvent((int index) -> {
            clearMenu(index);
            //System.out.println(index);
        });
        item.addEvent(event);
        panelMenu.add(item);
    }



    private void createButtonMenu() {
        cmdMenu = new JButton();
        cmdMenu.setContentAreaFilled(false);
        cmdMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMenu.setIcon(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/menu.png")));
        cmdMenu.setBorder(new EmptyBorder(5, 12, 5, 12));
    }

    private void createButtonLogOut() {
        cmdLogOut = new ButtonCustom();
        cmdLogOut.setIcon(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/exit.png")));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, Color.decode("#56CCF2"), 0, getHeight(), Color.decode("#2F80ED"));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());

    }

    private void clearMenu(int exceptIndex) {
        for (Component com : panelMenu.getComponents()) {
            MenuItem item = (MenuItem) com;
            if (item.getIndex() != exceptIndex) {
                item.setSelected(false);
            }
        }
    }

    public void addMenuEvent(ActionListener event) {
        cmdMenu.addActionListener(event);
    }

    public void addLogOutEvent(ActionListener event) {

        cmdLogOut.addActionListener(event);
    }

    public void setAlpha(float alpha) {
        header.setAlpha(alpha);
        buttom.setAlpha(alpha);
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
