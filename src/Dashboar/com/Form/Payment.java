package Dashboar.com.Form;

import Dashboar.com.DAO.ContractDAO;
import Dashboar.com.DAO.CustomerDAO;
import Dashboar.com.DAO.RentalCarDAO;
import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import Dashboar.com.Helper.DateHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.Helper.Notification;
import Dashboar.com.Model.Model_Contract;
import Dashboar.com.Model.Model_RentalCar;
import Login_Register.com.Model.NhanVien;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author datdo
 */
public class Payment extends javax.swing.JPanel {

    private NhanVien user;
    private DefaultTableModel model;
    private ContractDAO ctDAO = new ContractDAO();
    private CustomerDAO cmDAO = new CustomerDAO();
    private int index = 0;
    private int row = 0; // dùng cho popup
    private RentalCarDAO dao = new RentalCarDAO();
    DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
    private JFrame fr;
    JFileChooser fileChooser = new JFileChooser();

    public Payment(NhanVien user) {
        initComponents();
        this.user = user;
        init();
        //System.out.println(user.getMaNV());
    }

    private void init() {
        tbPayment.setDefaultEditor(Object.class, null);
        loadTable();
        jdNgayHienTai.setDate(new Date());
        jdNgayHienTai.setEnabled(false);
        fillCombobox();

    }

    private Model_RentalCar getModel() {
        Model_RentalCar model = new Model_RentalCar();
        // model.setMaThanhToan(Integer.valueOf(cboHD.getToolTipText()));
        String hd = String.valueOf(cboHD.getSelectedItem());
        model.setMaHD(Integer.parseInt(hd));
        model.setMaXe(txtMaXe.getText());
        model.setMaNV(txtMaNV.getText());
        model.setMaKH(txtMaKH.getText());
        model.setNgayThue(jdNgayThue.getDate());
        model.setNgayTra(jdNgayTra.getDate());
        model.setNgayHienTai(jdNgayHienTai.getDate());
        model.setGiaThue(Float.valueOf(txtGiaThue.getText()));
        model.setTongGia(Float.valueOf(txtTong.getText()));
        return model;
    }

    private void setModel(Model_RentalCar model) {

        txtMaXe.setText(model.getMaXe());
        txtMaNV.setText(model.getMaNV());
        txtMaKH.setText(model.getMaKH());
        txtGiaThue.setText(String.valueOf(model.getGiaThue()));
        txtTong.setText(String.valueOf(model.getTongGia()));
        jdNgayThue.setDate(model.getNgayThue());
        jdNgayTra.setDate(model.getNgayTra());
        jdNgayHienTai.setDate(model.getNgayHienTai());
        //System.out.println(model.getMaHD());
        cboHD.getModel().setSelectedItem(model.getMaHD());
        cboHD.setToolTipText(String.valueOf(model.getMaThanhToan()));
    }

    private void edit() {
        try {
            String maHD = String.valueOf(tbPayment.getValueAt(this.index, 0));
            Model_RentalCar model = dao.findbyidHD(maHD);
            if (model != null) {
                setModel(model);
                tongThanhToan();
                //statusContract();
            } else {
                System.out.println("Lỗi");
            }
        } catch (Exception e) {
        }
    }

    private void payment() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thanh toán hợp đồng này? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            try {
                Model_RentalCar model = getModel();
                dao.insertAndUpdate(model);
                loadTable();

                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Thêm thành công!");
            } catch (SQLException ex) {
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");
                Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void delete(int index) {

        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thanh toán hợp đồng này? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            try {
                String maHD = String.valueOf(tbPayment.getValueAt(index, 0));
                dao.delete(maHD);
                loadTable();

                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Xóa thành công!");
            } catch (SQLException ex) {
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");
            }
        }

    }

    private void HuyTT(int index) {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn hủy hợp đồng này? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            try {
                String MaTT = String.valueOf(tbPayment.getValueAt(index, 0)); // lấy mã thay toán từ bảng
                int idTT = Integer.valueOf(MaTT);
                System.out.println(idTT);
                String idCar = txtMaXe.getText();
                dao.deleteAndUpdate(idCar, idTT);
                loadTable();
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Hủy thành công!");
            } catch (SQLException ex) {
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");
            }
        }
    }

    private void Refresh() {
        try {
            LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
            String maTT = cboHD.getToolTipText();
            String MaKH = txtMaKH.getText();
            //System.out.println(MaKH);
            List<String> list = cmDAO.thongtin(MaKH); //Thông tin Khách hàng
            List<String> list2 = dao.thongtin(maTT); // thông tin Thuê
            String TGQh = txtThoiGianQH.getText();
            String TQh = txtGiaphat.getText();
            String giaPhat = txtQuaHan.getText();
            String maHD = String.valueOf(cboHD.getSelectedItem());
            String Gia = txtTongGiaThue.getText(); // chưa cộng tiền phạt
            txtPanel.setContentType("text/html");
            txtPanel.setText("  <div style=\"border-style:dotted;\">\n"
                    + "        <h1 style=\"text-align:center\">Thông tin thanh toán </h1>\n"
                    + "        <p style=\"text-align:right;margin-right: 25px;\">Ngày " + date.getDayOfMonth() + " tháng " + date.getMonthValue() + " năm " + date.getYear() + ".</p><br><br><br>\n"
                    + "        <div style=\" margin-left: 30px ;\">\n"
                    + "            <p>Mã Thanh Toán: " + maTT + "</p>\n"
                    + "            <p>Mã Hợp Đồng: " + maHD + "</p>\n"
                    + "            <p>Tên Khách hàng: " + list.get(1) + "</p>\n"
                    + "            <p>Địa Chỉ: " + list.get(4) + "</p>\n"
                    + "            <p>Số Điện thoại: " + list.get(3) + "</p>\n"
                    + "            <p>Mã Xe: " + list2.get(2) + "</p>\n"
                    + "            <p>Tên Xe: " + list2.get(9) + "</p>\n"
                    + "            <p>Ngày Thuê: " + list2.get(4) + "</p>\n"
                    + "            <p>Ngày Trả: " + list2.get(6) + "</p>\n"
                    + "            <p>Giá Thuê: " + list2.get(7) + "</p>\n"
                    + "            <p>Giá Trả: " + Gia + " </p>\n"
                    + "            <p>Số ngày quá hạn: " + TGQh + "</p>\n"
                    + "            <p>Giá Phạt/Ngày: " + giaPhat + "/Ngày</p>\n"
                    + "            <p>Tổng Giá Phạt: " + TQh + "</p>\n"
                    + "            <p style=\"text-align:right; margin-right: 40px;\">Tổng Thanh Toán: " + list2.get(8) + "</p>\n"
                    + "        </div>\n"
                    + "    </div>"
            );
        } catch (Exception e) {
        }
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbPayment.getModel());
            tbPayment.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
        }
    }

    private void loadTable() {
        model = (DefaultTableModel) tbPayment.getModel();
        model.setRowCount(0);
        try {
            List<Model_RentalCar> list = dao.select();

            for (Model_RentalCar rt : list) {

                Object[] rows = {
                    rt.getMaThanhToan(),
                    rt.getMaHD(),
                    rt.getMaXe(),
                    rt.getMaNV(),
                    rt.getMaKH(),
                    rt.getNgayThue(),
                    rt.getNgayHienTai(),
                    rt.getNgayTra(),
                    rt.getGiaThue(),
                    rt.getTongGia()
                };
                model.addRow(rows);
            }
        } catch (Exception e) {

        }
        model.fireTableDataChanged();
    }

    private void fillCombobox() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboHD.getModel();
        model.removeAllElements();
        try {
            List<Model_Contract> list = ctDAO.selectMaHD();
            for (Model_Contract mdct : list) {
                model.addElement(mdct);
            }
        } catch (Exception e) {
            System.out.println("Lối fillCombobox");
        }
    }

    private void tongThanhToan() {
        double a = 0;
        double tongQuaHan = 0;
        double tongNgayQuaHan = 0;
        double GiaThue = Float.valueOf(txtGiaThue.getText());
        double quaHan = Float.valueOf(txtQuaHan.getText());
        double tongNgayThue = DateHelper.daysBetween(jdNgayTra.getDate(), jdNgayThue.getDate());
        //System.out.println("Tong Ngay Thue: " + tongNgayThue);
        double tongGiaThue = (tongNgayThue * GiaThue);
        //System.out.println("gia qua han: " + quaHan);
        //System.out.println("Tổng Giá Thuê: " + tongGiaThue);
        tongNgayQuaHan = DateHelper.daysBetween(jdNgayTra.getDate(), jdNgayHienTai.getDate());
        //System.out.println("Tong Ngay Qua Han: " + tongNgayQuaHan);

        if (tongNgayQuaHan < 0) {
            double tongNgayQH = Math.abs(tongNgayQuaHan);
            //System.out.println("Tong Ngay Qua Han" + tongNgayQH);
            tongQuaHan = (tongNgayQH * quaHan);
            a = tongGiaThue + tongQuaHan;
            lbdetail.setText(String.valueOf(tongGiaThue) + " /" + tongNgayThue + "/Ngày, QH: " + tongQuaHan + " /" + tongNgayQH +"/Ngày");
        } else {
            double tongNgayQH = Math.abs(tongNgayQuaHan);
            tongQuaHan = (tongNgayQH * quaHan);
            a = tongGiaThue - tongQuaHan;

            lbdetail.setText(String.valueOf(tongGiaThue) + " /" + tongNgayThue + "/Ngày, QH: " + "-" + tongQuaHan + " / -" + tongNgayQH+"/Ngày");
        }
        //System.out.println(a);
        txtTong.setText(a + "");
        txtGiaphat.setText(tongQuaHan + "");
        txtTongGiaThue.setText(tongGiaThue + ""); // chưa cộng tiền phạt
        txtThoiGianQH.setText(Math.abs(tongNgayQuaHan) + " "); // khong nằm trong form

    }

    private void status(boolean st) {
        txtGiaThue.setEnabled(st);
        txtMaKH.setEnabled(st);
        txtMaNV.setEnabled(st);
        txtMaXe.setEnabled(st);
        txtQuaHan.setEnabled(st);
        txtTong.setEnabled(st);

    }

//    private void statusContract() {
//        String maHD = String.valueOf(cboHD.getSelectedItem());
//        String status = ctDAO.status(maHD);
//        if (status.equals("false")) {
//            lbStatusContract.setText("Trạng thái: Chưa được thanh toán");
//            btPayment.setEnabled(true);
//        } else {
//            lbStatusContract.setText("Trạng thái: Đã được thanh toán");
//            btPayment.setEnabled(false);
//        }
//    }

    private void selectCombobox() {
        Model_Contract model = (Model_Contract) cboHD.getSelectedItem();
        txtMaXe.setText(model.getMaXe());
        txtMaKH.setText(model.getMaKH());
        txtMaNV.setText(user.getMaNV());
        txtGiaThue.setText(String.valueOf(model.getGiaThue()));
        txtQuaHan.setText(String.valueOf(model.getGiaQuaHan()));
        jdNgayTra.setDate(model.getNgayTra());
        jdNgayThue.setDate(model.getNgayThue());
        status(false);
        tongThanhToan();
       // statusContract();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtThoiGianQH = new javax.swing.JLabel();
        txtGiaphat = new javax.swing.JLabel();
        txtTongGiaThue = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        menuXoa = new javax.swing.JMenuItem();
        menuHuy = new javax.swing.JMenuItem();
        lineHeader1 = new Dashboar.com.component.lineHeader();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPayment = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lbMaHD = new javax.swing.JLabel();
        lbMaXe = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        txtMaXe = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        lbNgayThue = new javax.swing.JLabel();
        lbNgayTra = new javax.swing.JLabel();
        lbGiaThue = new javax.swing.JLabel();
        jdNgayThue = new com.toedter.calendar.JDateChooser();
        lbMaKH = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        lbTongGia = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        cboHD = new javax.swing.JComboBox<>();
        lbNgayHienTai = new javax.swing.JLabel();
        jdNgayHienTai = new com.toedter.calendar.JDateChooser();
        jdNgayTra = new com.toedter.calendar.JDateChooser();
        txtGiaThue = new javax.swing.JTextField();
        lbQuaHan = new javax.swing.JLabel();
        txtQuaHan = new javax.swing.JTextField();
        lbdetail = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPanel = new javax.swing.JTextPane();
        btPayment = new Dashboar.com.swing.ButtonCustom();
        btHuyTT = new Dashboar.com.swing.ButtonCustom();
        btRefresh = new Dashboar.com.swing.ButtonCustom();
        btPrint = new Dashboar.com.swing.ButtonCustom();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();
        lbLoad = new javax.swing.JLabel();
        btXoa = new Dashboar.com.swing.ButtonCustom();

        txtThoiGianQH.setText("jLabel1");

        txtGiaphat.setText("jLabel1");

        txtTongGiaThue.setText("jLabel1");

        jPopupMenu1.setBackground(new java.awt.Color(157, 203, 203));

        menuXoa.setBackground(new java.awt.Color(157, 203, 203));
        menuXoa.setText("Xóa");
        menuXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuXoaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuXoa);

        menuHuy.setBackground(new java.awt.Color(157, 203, 203));
        menuHuy.setText("Hủy");
        menuHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuHuyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(menuHuy);

        lineHeader1.setPreferredSize(new java.awt.Dimension(57, 68));

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("THANH TOÁN");
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeader1Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbPayment.setAutoCreateRowSorter(true);
        tbPayment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã TT", "Mã HD", "Mã Xe", "Mã NV", "Mã KH", "Ngày Thuê", "Ngày Hiện Tại", "Ngày Trả", "Giá Thuê", "Tổng Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPaymentMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbPaymentMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbPayment);
        if (tbPayment.getColumnModel().getColumnCount() > 0) {
            tbPayment.getColumnModel().getColumn(0).setPreferredWidth(20);
            tbPayment.getColumnModel().getColumn(1).setPreferredWidth(20);
            tbPayment.getColumnModel().getColumn(2).setPreferredWidth(20);
            tbPayment.getColumnModel().getColumn(3).setPreferredWidth(20);
            tbPayment.getColumnModel().getColumn(4).setPreferredWidth(20);
            tbPayment.getColumnModel().getColumn(5).setPreferredWidth(35);
            tbPayment.getColumnModel().getColumn(6).setPreferredWidth(35);
            tbPayment.getColumnModel().getColumn(7).setPreferredWidth(35);
            tbPayment.getColumnModel().getColumn(8).setPreferredWidth(25);
            tbPayment.getColumnModel().getColumn(9).setPreferredWidth(25);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        lbMaHD.setBackground(new java.awt.Color(196, 196, 196));
        lbMaHD.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaHD.setForeground(new java.awt.Color(0, 0, 0));
        lbMaHD.setText("Mã HD:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbMaHD, gridBagConstraints);

        lbMaXe.setBackground(new java.awt.Color(196, 196, 196));
        lbMaXe.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaXe.setForeground(new java.awt.Color(0, 0, 0));
        lbMaXe.setText("Mã Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbMaXe, gridBagConstraints);

        lbMaNV.setBackground(new java.awt.Color(196, 196, 196));
        lbMaNV.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(0, 0, 0));
        lbMaNV.setText("Mã NV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbMaNV, gridBagConstraints);

        txtMaXe.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtMaXe, gridBagConstraints);

        txtMaNV.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 152;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtMaNV, gridBagConstraints);

        lbNgayThue.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayThue.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNgayThue.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayThue.setText("Ngày Thuê:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbNgayThue, gridBagConstraints);

        lbNgayTra.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayTra.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNgayTra.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayTra.setText("Ngày Trả:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbNgayTra, gridBagConstraints);

        lbGiaThue.setBackground(new java.awt.Color(196, 196, 196));
        lbGiaThue.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbGiaThue.setForeground(new java.awt.Color(0, 0, 0));
        lbGiaThue.setText("Giá Thuê:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbGiaThue, gridBagConstraints);

        jdNgayThue.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 108;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jdNgayThue, gridBagConstraints);

        lbMaKH.setBackground(new java.awt.Color(196, 196, 196));
        lbMaKH.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaKH.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKH.setText("Mã KH:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 31;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbMaKH, gridBagConstraints);

        txtMaKH.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 152;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtMaKH, gridBagConstraints);

        lbTongGia.setBackground(new java.awt.Color(196, 196, 196));
        lbTongGia.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbTongGia.setForeground(new java.awt.Color(0, 0, 0));
        lbTongGia.setText("Tổng Giá:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbTongGia, gridBagConstraints);

        txtTong.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtTong, gridBagConstraints);

        cboHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboHDActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(cboHD, gridBagConstraints);

        lbNgayHienTai.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayHienTai.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNgayHienTai.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayHienTai.setText("Ngày Hiện tại:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbNgayHienTai, gridBagConstraints);

        jdNgayHienTai.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jdNgayHienTai, gridBagConstraints);

        jdNgayTra.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 111;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(jdNgayTra, gridBagConstraints);

        txtGiaThue.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtGiaThue, gridBagConstraints);

        lbQuaHan.setBackground(new java.awt.Color(196, 196, 196));
        lbQuaHan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbQuaHan.setForeground(new java.awt.Color(0, 0, 0));
        lbQuaHan.setText("Quá Hạn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(lbQuaHan, gridBagConstraints);

        txtQuaHan.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 155;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtQuaHan, gridBagConstraints);

        lbdetail.setFont(new java.awt.Font("sansserif", 1, 11)); // NOI18N
        lbdetail.setForeground(new java.awt.Color(255, 102, 102));
        lbdetail.setText("detail:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 26;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbdetail, gridBagConstraints);

        txtPanel.setFont(new java.awt.Font("sansserif", 0, 8)); // NOI18N
        jScrollPane2.setViewportView(txtPanel);

        btPayment.setBorder(null);
        btPayment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btPayment.png"))); // NOI18N
        btPayment.setText("Thanh Toán");
        btPayment.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btPayment.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btPayment.setIconTextGap(1);
        btPayment.setName(""); // NOI18N
        btPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPaymentActionPerformed(evt);
            }
        });

        btHuyTT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/Cancel.png"))); // NOI18N
        btHuyTT.setText("Hủy");
        btHuyTT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btHuyTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btHuyTTActionPerformed(evt);
            }
        });

        btRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btrefresh.png"))); // NOI18N
        btRefresh.setText(" Làm mới");
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btPrint.png"))); // NOI18N
        btPrint.setText("In");
        btPrint.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btPrint.setIconTextGap(10);
        btPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        lbLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/Delete.png"))); // NOI18N
        btXoa.setText("Delete");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineHeader1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(523, 523, 523)
                        .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(28, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btPayment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btHuyTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(12, 12, 12))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btHuyTT, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void cboHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboHDActionPerformed
        selectCombobox();
    }//GEN-LAST:event_cboHDActionPerformed

    private void btPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPaymentActionPerformed
        payment();

    }//GEN-LAST:event_btPaymentActionPerformed

    private void btHuyTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btHuyTTActionPerformed
        HuyTT(this.index);
    }//GEN-LAST:event_btHuyTTActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btRefreshActionPerformed

    private void btPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintActionPerformed
        try {
            txtPanel.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Payment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintActionPerformed

    private void tbPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPaymentMouseClicked
        if (evt.getClickCount() == 2) {
            index = tbPayment.rowAtPoint(evt.getPoint());
            edit();
            loadTable();
        }
    }//GEN-LAST:event_tbPaymentMouseClicked

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        Search();
    }//GEN-LAST:event_txtSearchMousePressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void menuXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuXoaActionPerformed
        delete(this.row);
    }//GEN-LAST:event_menuXoaActionPerformed

    private void menuHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuHuyActionPerformed
        HuyTT(this.row);
        //System.out.println(this.row);

    }//GEN-LAST:event_menuHuyActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        delete(this.index);
    }//GEN-LAST:event_btXoaActionPerformed

    private void tbPaymentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPaymentMouseReleased
        maybeShowPopup(evt);
    }//GEN-LAST:event_tbPaymentMouseReleased

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            // get row that pointer is over
            row = tbPayment.rowAtPoint(e.getPoint());
            // if pointer is over a selected row, show popup
            if (tbPayment.isRowSelected(row)) {
                jPopupMenu1.show(e.getComponent(), e.getX(), e.getY());

            }

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboar.com.swing.ButtonCustom btHuyTT;
    private Dashboar.com.swing.ButtonCustom btPayment;
    private Dashboar.com.swing.ButtonCustom btPrint;
    private Dashboar.com.swing.ButtonCustom btRefresh;
    private Dashboar.com.swing.ButtonCustom btXoa;
    private javax.swing.JComboBox<String> cboHD;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdNgayHienTai;
    private com.toedter.calendar.JDateChooser jdNgayThue;
    private com.toedter.calendar.JDateChooser jdNgayTra;
    private javax.swing.JLabel lbGiaThue;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbMaHD;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMaXe;
    private javax.swing.JLabel lbNgayHienTai;
    private javax.swing.JLabel lbNgayThue;
    private javax.swing.JLabel lbNgayTra;
    private javax.swing.JLabel lbQuaHan;
    private javax.swing.JLabel lbTongGia;
    private javax.swing.JLabel lbdetail;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JMenuItem menuHuy;
    private javax.swing.JMenuItem menuXoa;
    private javax.swing.JTable tbPayment;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JLabel txtGiaphat;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaXe;
    private javax.swing.JTextPane txtPanel;
    private javax.swing.JTextField txtQuaHan;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    private javax.swing.JLabel txtThoiGianQH;
    private javax.swing.JTextField txtTong;
    private javax.swing.JLabel txtTongGiaThue;
    // End of variables declaration//GEN-END:variables
}
