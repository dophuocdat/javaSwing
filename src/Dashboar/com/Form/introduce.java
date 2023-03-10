package Dashboar.com.Form;

/**
 *
 * @author datdo
 */
public class introduce extends javax.swing.JPanel {

    public introduce() {
        initComponents();
        init();
    }

    private void init() {
        jTextPane.setText("<h1 style=\"text-align:center;color: rgb(72, 158, 228);font-size:40px\">Car Rental System</h1>\n"
                + "    <div class=\"\" style=\"font-family:Cambria, Cochin, Georgia, Times, 'Times New Roman', serif; color:rgb(55, 44, 44)\">\n"
                + "        <h3>Hệ thống cho thuê xe (CRS) là một hệ thống dựa trên web cho một công ty thuê xe. Hệ thống này công ty để\n"
                + "            cung\n"
                + "            cấp\n"
                + "            dịch vụ của họ cho công chúng thông qua Internet và cũng ghi lại các hồ sơ về dịch vụ của họ. </h3>\n"
                + "        <h3>Đây là một công ty thuê ô tô trong một thời gian ngắn trong vài ngày hoặc tuần. Các công ty cho thuê xe hoạt\n"
                + "            động bằng cách mua hoặc cho thuê một số khoản phí. </h3>\n"
                + "        <h3> Để làm cho dịch vụ này trở nên phổ biến và dễ tiếp cận hơn với công chúng, nó đã\n"
                + "            được chuyển đổi thành hệ thống cơ sở AWEB và được kết nối với Internet là mọi người đều có thể có quyền truy\n"
                + "            cập\n"
                + "            vào nó. </h3>\n"
                + "        <h3> Phát triển một hệ thống dựa trên web sẽ giúp quản lý các giao dịch kinh doanh của việc thuê xe. Các đội\n"
                + "            tàu có thể được cấu trúc theo nhiều cách mà chúng có thể được sở hữu hoàn toàn, chúng có thể được cho thuê,\n"
                + "            Orthey có thể được sở hữu theo chương trình mua lại được đảm bảo hoặc nhánh tài chính của nhà sản xuất.\n"
                + "        </h3>\n"
                + "    </div>\n"
                + "</br> </br>"
                + "    <div style=\"float:right; text-align:center;\">\n"
                + "        <h1>Đỗ Phước Đạt</h1>\n"
                + "        <h2>PD05579</h2>\n"
                + "        <h3>Dự Án 1 (Quản Lý Thuê Ô Tô)</h3>\n"
                + "    </div>\n"
                + "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane = new javax.swing.JTextPane();

        jTextPane.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(jTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane;
    // End of variables declaration//GEN-END:variables
}
