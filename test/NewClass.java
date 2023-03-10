
import com.mysql.jdbc.SQLError;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author datdo
 */
public class NewClass {

    public static void main(String[] args) {
        String s1 = "Đỗ Phước Đạt ";
        s1 = s1.trim();
        int lenght = s1.trim().length();
        System.out.println("Ten: " + s1.substring(s1.lastIndexOf(" ") + 1, s1.length()));
    }
}
