/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboar.com.Helper;

import javax.swing.JFrame;

/**
 *
 * @author datdo
 */
public class Notification {

    public static void showNotification(JFrame fr, Dashboar.com.swing.Notification.Type type, String text) {
        Dashboar.com.swing.Notification panel = new Dashboar.com.swing.Notification(fr, type, text);
        panel.showNotification();
    }
}
