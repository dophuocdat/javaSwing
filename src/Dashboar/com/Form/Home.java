package Dashboar.com.Form;

import Chart.chart.ModelChart;
import Dashboar.com.DAO.CarDAO;
import Dashboar.com.DAO.ContractDAO;
import Dashboar.com.DAO.CustomerDAO;
import Dashboar.com.Model.Model_Card;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;

/**
 *
 * @author datdo
 */
public class Home extends javax.swing.JPanel {

    int soLuongXe = 0;
    int xeDaThue = 0;
    int soLuongKhach = 0;
    int xeChuaThue =0;
    CarDAO carDAO = new CarDAO();
    ContractDAO ctdao = new ContractDAO();
    CustomerDAO customerDAO = new CustomerDAO();

    public Home() {
        initComponents();
        // setOpaque(false);
        init();

    }

    private void init() {
        ThongTin();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/car60px.png")), "Số Lượng xe", String.valueOf(soLuongXe)));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/rentalCar60px.png")), "Xe Đã Thuê", String.valueOf(xeDaThue)));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/xeChuaThue.png")), "Xe Chưa Thuê", String.valueOf(xeChuaThue)));
        card4.setData(new Model_Card(new ImageIcon(getClass().getResource("/Dashboar/com/Icon/soLuongkhach60px.png")), "Sỗ Lượng Khách", String.valueOf(soLuongKhach)));
        chart.setTitle("Biểu Đồ Hoạt Động");
        chart.addLegend("Số lượng xe", Color.decode("#f5af19"), Color.decode("#f12711"));
        chart.addLegend("Khách hàng", Color.decode("#a044ff"), Color.decode("#6a3093"));
        chart.addLegend("Đã thuê", Color.decode("#38ef7d"), Color.decode("#11998e"));
        chart.addLegend("Còn lại", Color.decode("#0575E6"), Color.decode("#021B79"));
        chart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        chart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        chart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        chart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        chart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        chart.addData(new ModelChart("June", new double[]{450, 500, 700, 900}));
        chart.start();

    }

    private void ThongTin() {
        soLuongXe = carDAO.countCar();
        xeDaThue = ctdao.count();
        soLuongKhach = customerDAO.count();
        xeChuaThue = carDAO.xeChuaThue();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JLayeredPane();
        card1 = new Dashboar.com.component.Card();
        card2 = new Dashboar.com.component.Card();
        card3 = new Dashboar.com.component.Card();
        card4 = new Dashboar.com.component.Card();
        jCalendar = new com.toedter.calendar.JCalendar();
        chart = new Chart.chart.BarChart();

        Panel.setLayout(new java.awt.GridLayout(1, 0, 7, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        Panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        Panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        Panel.add(card3);

        card4.setColor1(new java.awt.Color(241, 208, 62));
        card4.setColor2(new java.awt.Color(211, 184, 61));
        Panel.add(card4);

        jCalendar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jCalendar.setNullDateButtonText("");
        jCalendar.setTodayButtonText("");
        jCalendar.setWeekOfYearVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 878, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Panel;
    private Dashboar.com.component.Card card1;
    private Dashboar.com.component.Card card2;
    private Dashboar.com.component.Card card3;
    private Dashboar.com.component.Card card4;
    private Chart.chart.BarChart chart;
    private com.toedter.calendar.JCalendar jCalendar;
    // End of variables declaration//GEN-END:variables
}
