package Dashboar.com.Helper;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author datdo
 */
public class ImageHelper {

    public static boolean saveImageKh(File file) {
        /*Kiem tra file ton tai hay chua; neu chua thi tao folder moi        */
        File dir = new File("imageKH");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path sourPath = Paths.get(file.getAbsolutePath()); // get path file
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(sourPath, destination, StandardCopyOption.REPLACE_EXISTING);
            // System.out.println("coppy succesully");
            return true;
        } catch (IOException e) {
            System.out.println("Coppy false");
            return false;
        }
    }

    public static ImageIcon readImageKH(String fileName) {
        File path = new File("imageKH", fileName);
        //System.out.println(path);
        Image img = new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    public static boolean saveImageCar(File file) {
        /*Kiem tra file ton tai hay chua; neu chua thi tao folder moi        */
        File dir = new File("imageCar");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path sourPath = Paths.get(file.getAbsolutePath()); // get path file
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(sourPath, destination, StandardCopyOption.REPLACE_EXISTING);
            // System.out.println("coppy succesully");
            return true;
        } catch (IOException e) {
            System.out.println("Coppy false");
            return false;
        }
    }

    public static ImageIcon readImageCar(String fileName) {
        File path = new File("imageCar", fileName);
        System.out.println(path);
        Image img = new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

    public static boolean saveImageNhanVien(File file) {
        /*Kiem tra file ton tai hay chua; neu chua thi tao folder moi        */
        File dir = new File("imageNV");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File newFile = new File(dir, file.getName());
        try {
            Path sourPath = Paths.get(file.getAbsolutePath()); // get path file
            Path destination = Paths.get(newFile.getAbsolutePath());
            Files.copy(sourPath, destination, StandardCopyOption.REPLACE_EXISTING);
            // System.out.println("coppy succesully");
            return true;
        } catch (IOException e) {
            System.out.println("Coppy false");
            return false;
        }
    }

    public static ImageIcon readImageNV(String fileName) {
        File path = new File("imageNV", fileName);
        //System.out.println(path);
        Image img = new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(img);
        return icon;
    }

}
