package Dashboar.com.Helper;

import Dashboar.com.Form.Car;
import Dashboar.com.swing.Notification;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UitilityHelper {

    public static boolean CheckNullText(JTextField txt, Component pn) {
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(pn);
        txt.setBackground(Color.white);
        if (txt.getText().trim().length() > 0) {
            //showNotification(fr, Notification.Type.SUCCESS, "Thêm Thành Công");
            return true;
        } else {
            txt.setBackground(Color.pink);
            showNotification(fr, Notification.Type.WARNING, txt.getName() + " Không được để trống!");
            return false;
        }
    }

    public static boolean checkdate(JDateChooser d, Component pn) {
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(pn);
        Date date = d.getDate();
        if (date == null) {
            showNotification(fr, Notification.Type.WARNING, d.getName() + " Không được để trống!");
            return false;
        } else {

            return true;
        }
    }

    public static boolean checkNullCbo(JComboBox com, Component pn) {
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(pn);
        if (com.getSelectedItem().equals("")) {
            showNotification(fr, Notification.Type.WARNING, com.getName() + " Không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkImage(JLabel lb, Component pn) {
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(pn);
        if (lb.getToolTipText() == null) {
            showNotification(fr, Notification.Type.WARNING, lb.getName() + " Không được để trống!");
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkNumber(JTextField txt, Component pn) {
        System.out.println(txt.getText());
        JFrame fr = (JFrame) SwingUtilities.getWindowAncestor(pn);
        txt.setBackground(Color.white);
        try {
            float num = Float.valueOf(txt.getText());
            if (num <= 0f) {
                txt.setBackground(Color.pink);
                showNotification(fr, Notification.Type.WARNING, txt.getName() + " Không được để trống!");
                return false;
            } else {
                txt.setBackground(Color.white);
                return true;
            }

        } catch (NumberFormatException e) {
            txt.setBackground(Color.pink);
            showNotification(fr, Notification.Type.WARNING, txt.getName() + " Không đúng định dạng!");
            return false;
        }

    }

    private static void showNotification(JFrame fr, Notification.Type type, String text) {
        Notification panel = new Notification(fr, type, text);
        panel.showNotification();
    }
}
