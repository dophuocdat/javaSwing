package Dashboar.com.Form;

import Dashboar.com.DAO.ReportDAO;
import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author datdo
 */
public class Report extends javax.swing.JPanel {

    private DateChooser chDate = new DateChooser();
    private final ReportDAO dao = new ReportDAO();
    private SimpleDateFormat dfm = new SimpleDateFormat("YYYY-MM-dd");

    public Report() {
        initComponents();
        chDate.setTextField(txtDate);
        chDate.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        chDate.setLabelCurrentDayVisible(false);
        chDate.setDateFormat(new SimpleDateFormat("dd-MM-YYYY"));
        init();
    }

    private void init() {
        fillcomboBox();
        fillTableDate();

    }

    private void filltabel() {
        DefaultTableModel model = (DefaultTableModel) tbBaoCaoHang.getModel();
        model.setRowCount(0);
        String hang = String.valueOf(cboTypeCar.getSelectedItem());
        List<Object[]> list = dao.getThongKeHang(hang);
        for (Object[] row : list) {
            System.out.println(Arrays.deepToString(row));
            model.addRow(row);
        }

    }

    private void fillcomboBox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboTypeCar.getModel();
        List<String> list = dao.fillHangXe();
        for (String item : list) {
            model.addElement(item);
        }

    }

    private void fillTableDate() {
        DefaultTableModel model = (DefaultTableModel) tbDate.getModel();

        chDate.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                model.setRowCount(0);
                String dateFrom = dfm.format(date.getFromDate());
                String todate = dfm.format(date.getToDate());
                lbTongTK.setText(dao.getTongThongKeTG(dateFrom, todate)+" $");
                List<Object[]> list = dao.getThongKeThoiGian(dateFrom, todate);
                for (Object[] row : list) {
                    System.out.println(Arrays.deepToString(row));
                    model.addRow(row);
                }
                model.fireTableDataChanged();
            }

        });
    }

    public void Search() {
        txtSearch.addEvent(new EventTextField() {
            @Override
            public void onPressed(EventCallBack call) {
                //  Test
                try {
                    for (int i = 1; i <= 100; i++) {
                        lbLoad.setText("Load: " + i + "%");
                        Thread.sleep(10);
                    }
                    fillTableSearch();
                    call.done();

                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }

            @Override
            public void onCancel() {

            }
        });

    }

    public void fillTableSearch() {
        try {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbDate.getModel());
            tbDate.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
            System.out.println("Lỗi");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lineHeader1 = new Dashboar.com.component.lineHeader();
        jLabel1 = new javax.swing.JLabel();
        Tab = new javax.swing.JTabbedPane();
        jpnHang = new javax.swing.JPanel();
        cboTypeCar = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBaoCaoHang = new javax.swing.JTable();
        btPrint = new Dashboar.com.swing.ButtonCustom();
        jpnDate = new javax.swing.JPanel();
        txtDate = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDate = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbTongTK = new javax.swing.JLabel();
        lbKhoangTG = new javax.swing.JLabel();
        lbLoad = new javax.swing.JLabel();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();
        btPrintBC2 = new Dashboar.com.swing.ButtonCustom();

        lineHeader1.setPreferredSize(new java.awt.Dimension(57, 68));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BÁO CÁO");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lineHeader1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        cboTypeCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTypeCarActionPerformed(evt);
            }
        });

        tbBaoCaoHang.setAutoCreateRowSorter(true);
        tbBaoCaoHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Xe", "Tên Xe", "Hãng Xe", "Tổng Tiền "
            }
        ));
        jScrollPane1.setViewportView(tbBaoCaoHang);

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btPrint.png"))); // NOI18N
        btPrint.setText("Print");
        btPrint.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btPrint.setIconTextGap(10);
        btPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnHangLayout = new javax.swing.GroupLayout(jpnHang);
        jpnHang.setLayout(jpnHangLayout);
        jpnHangLayout.setHorizontalGroup(
            jpnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnHangLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jpnHangLayout.createSequentialGroup()
                        .addGroup(jpnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTypeCar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 699, Short.MAX_VALUE))))
        );
        jpnHangLayout.setVerticalGroup(
            jpnHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnHangLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(cboTypeCar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        Tab.addTab("Báo Cáo (Hãng Xe)", jpnHang);

        tbDate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KH", "Mã Xe", "Tên Xe", "Hãng Xe", "Ngày Thuê", "Ngày Trả", "Tổng Giá"
            }
        ));
        jScrollPane2.setViewportView(tbDate);

        jLabel2.setFont(new java.awt.Font("sansserif", 2, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tổng:");

        lbTongTK.setFont(new java.awt.Font("sansserif", 2, 36)); // NOI18N
        lbTongTK.setForeground(new java.awt.Color(255, 102, 102));
        lbTongTK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTongTK.setText("Giá");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTongTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbTongTK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                .addContainerGap())
        );

        lbKhoangTG.setText("Khoảng Thời Gian:");

        lbLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
        });

        btPrintBC2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btPrint.png"))); // NOI18N
        btPrintBC2.setText("Print");
        btPrintBC2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btPrintBC2.setIconTextGap(10);
        btPrintBC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintBC2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnDateLayout = new javax.swing.GroupLayout(jpnDate);
        jpnDate.setLayout(jpnDateLayout);
        jpnDateLayout.setHorizontalGroup(
            jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
                    .addGroup(jpnDateLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jpnDateLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnDateLayout.createSequentialGroup()
                                .addComponent(btPrintBC2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnDateLayout.createSequentialGroup()
                                .addComponent(lbKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jpnDateLayout.setVerticalGroup(
            jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDateLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKhoangTG, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate))
                .addGroup(jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnDateLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(jpnDateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jpnDateLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btPrintBC2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Tab.addTab("Báo Cáo(Date)", jpnDate);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
            .addComponent(Tab)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tab))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboTypeCarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeCarActionPerformed
        //System.out.println(cboTypeCar.getSelectedItem());
        filltabel();
    }//GEN-LAST:event_cboTypeCarActionPerformed

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        Search();
    }//GEN-LAST:event_txtSearchMousePressed

    private void btPrintBC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintBC2ActionPerformed
        try {
            tbDate.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintBC2ActionPerformed

    private void btPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintActionPerformed
        try {
            tbBaoCaoHang.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tab;
    private Dashboar.com.swing.ButtonCustom btPrint;
    private Dashboar.com.swing.ButtonCustom btPrintBC2;
    private javax.swing.JComboBox<String> cboTypeCar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jpnDate;
    private javax.swing.JPanel jpnHang;
    private javax.swing.JLabel lbKhoangTG;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbTongTK;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JTable tbBaoCaoHang;
    private javax.swing.JTable tbDate;
    private javax.swing.JTextField txtDate;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    // End of variables declaration//GEN-END:variables
}
