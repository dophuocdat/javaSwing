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
public class Model_buttom {

    private Icon icon;
    private String name;
    private String rule;

    public Model_buttom() {
    }

    public Model_buttom(Icon icon, String name, String rule) {
        this.icon = icon;
        this.name = name;
        this.rule = rule;
    }

    public Model_buttom(String name, String rule) {

        this.name = name;
        this.rule = rule;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

}
