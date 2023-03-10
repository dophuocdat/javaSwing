/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dashboar.com.Model;

import javax.swing.Icon;

/**
 *
 * @author datdo
 */
public class Model_Card {

    private Icon icon;
    private String title;
    private String value;

    public Model_Card() {
    }

    public Model_Card(Icon icon, String title, String value) {
        this.icon = icon;
        this.title = title;
        this.value = value;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
