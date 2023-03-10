package Login_Register.com.Component;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author datdo
 */
public class Message extends javax.swing.JPanel {

    private MessageType messageType = MessageType.SUCCESS; // trạng thái mặc định Success
    private boolean Show;

    public boolean isShow() {
        return Show;
    }

    public void setShow(boolean isShow) {
        this.Show = isShow;
    }

    // tạo trạng thái cho message
    public static enum MessageType {
        SUCCESS, ERROR;
    }

    public Message() {
        initComponents();
        setOpaque(false);
        setVisible(false);
    }

    // truyền tham số và hiện thông báo
    public void showMessage(MessageType messageType, String message) {
        this.messageType = messageType;
        lblMess.setText(message);
        if (messageType == MessageType.SUCCESS) {
            lblMess.setIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/success.png")));
        } else {
            lblMess.setIcon(new ImageIcon(getClass().getResource("/Login_Register/com/Icon/error.png")));

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMess = new javax.swing.JLabel();

        lblMess.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblMess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMess.setText("Message");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMess, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (messageType == MessageType.SUCCESS) {
            g2.setColor(new Color(15, 174, 37));
        } else {
            g2.setColor(new Color(240, 52, 53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(245, 245, 245));
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMess;
    // End of variables declaration//GEN-END:variables
}
