package Dashboar.com.Form;

import Dashboar.com.DAO.CarDAO;
import Dashboar.com.DAO.ContractDAO;
import Dashboar.com.DAO.CustomerDAO;
import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import Dashboar.com.Helper.DateHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.Helper.Notification;
import Dashboar.com.Helper.UitilityHelper;
import Dashboar.com.Model.Model_Car;
import Dashboar.com.Model.Model_Contract;
import Dashboar.com.Model.Model_Customer;
import java.awt.print.PrinterException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
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
public class Contract extends javax.swing.JPanel {

    DefaultTableModel model;
    ContractDAO dao = new ContractDAO();
    CarDAO carDao = new CarDAO();
    CustomerDAO ctDao = new CustomerDAO();
    private JFrame fr;
    private int index = 0;
    //JFileChooser fileChooser = new JFileChooser();
    //private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");

    public Contract() {
        initComponents();
        init();
    }

    private void init() {
        fillComBoxCar();
        fillComBoxCt();
        tbContract.setDefaultEditor(Object.class, null);
        jdNgayHienTai.setDate(new Date());
        //jdNgayHienTai.setEnabled(false);
        loadTable();
    }

    private void loadTable() {
        model = (DefaultTableModel) tbContract.getModel();
        model.setRowCount(0);
        try {
            List<Model_Contract> list = dao.select();
            for (Model_Contract ct : list) {
                Object[] rows = {
                    ct.getMaThue(),
                    ct.getMaXe(),
                    ct.getTenXe(),
                    ct.getBienSo(),
                    ct.getMaKH(),
                    ct.getNgayThue(),
                    // ct.getNgayHienTai(),
                    ct.getNgayTra(),
                    ct.getGiaThue(),
                    ct.getGiaQuaHan(),
                    ct.isTrangThai() ? "R???i" : "Ch??a"};
                model.addRow(rows);
            }
        } catch (Exception e) {

        }
        model.fireTableDataChanged();
    }

    private void setModel(Model_Contract model) {
//        txtMaThue.setText(String.valueOf(model.getMaThue()));
        txtTenXe.setText(model.getTenXe());
        txtBienSo.setText(model.getBienSo());
        cboMaKH.getModel().setSelectedItem(model.getMaKH());
        jdNgayThue.setDate(model.getNgayThue());
        jdNgayHienTai.setDate(model.getNgayHienTai());
        jdNgayTra.setDate(model.getNgayTra());
        txtGiaThue.setText(String.valueOf(model.getGiaThue()));
        txtQuaHan.setText(String.valueOf(model.getGiaQuaHan()));
        if (model.isTrangThai()) {
            cboTrangThai.getModel().setSelectedItem("???? Thanh To??n");
        } else {
            cboTrangThai.getModel().setSelectedItem("Ch??a Thanh To??n");
        }
        cboMaXe.setToolTipText(String.valueOf(model.getMaThue()));

        cboMaXe.getModel().setSelectedItem(model.getMaXe());
    }

    private Model_Contract getModel() {
        Model_Contract model_Contract = new Model_Contract();
//        System.out.println(cboMaXe.getToolTipText());
        model_Contract.setMaXe(String.valueOf(cboMaXe.getSelectedItem()));
        model_Contract.setTenXe(txtTenXe.getText());
        model_Contract.setBienSo(txtBienSo.getText());
        model_Contract.setMaKH(String.valueOf(cboMaKH.getSelectedItem()));
        model_Contract.setNgayThue(jdNgayThue.getDate());
        model_Contract.setNgayHienTai(jdNgayHienTai.getDate());
        model_Contract.setNgayTra(jdNgayTra.getDate());
        model_Contract.setGiaThue(Float.valueOf(txtGiaThue.getText()));
        model_Contract.setGiaQuaHan(Float.valueOf(txtQuaHan.getText()));
        if (cboMaXe.getToolTipText() != null) {
            model_Contract.setMaThue(Integer.valueOf(cboMaXe.getToolTipText()));
        }
        if (cboTrangThai.getModel().getSelectedItem() == "???? Thanh To??n") {
            model_Contract.setTrangThai(true);
        } else {
            model_Contract.setTrangThai(false);
        }

        return model_Contract;
    }

    private void fillComBoxCar() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaXe.getModel();
        model.removeAllElements();
        try {
            List<Model_Car> list = carDao.select();
            for (Model_Car mc : list) {
                model.addElement(mc); // add object to the model      
            }
        } catch (Exception e) {
            System.out.println("L???i fillCombobox");
        }
    }

    private void fillComBoxCt() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboMaKH.getModel();
        model.removeAllElements();
        try {
            List<Model_Customer> list = ctDao.select();
            for (Model_Customer ct : list) {
                model.addElement(ct); // add object to the model      
            }
        } catch (Exception e) {
            System.out.println("L???i fillCombobox");
        }
    }

    private void status(boolean b) {
//        txtTenXe.setEditable(b);
//        txtBienSo.setEditable(b);
//        txtGiaThue.setEditable(b);
//        txtQuaHan.setEditable(b);
    }

    private void selectComboBoxCar() {
        Model_Car mc = (Model_Car) cboMaXe.getSelectedItem();
        txtTenXe.setText(mc.getTenXe());
        txtBienSo.setText(mc.getBienSo());
        txtGiaThue.setText(String.valueOf(mc.getGiaThue()));
        txtQuaHan.setText(String.valueOf(mc.getGiaQuaHan()));
        statusCar();
        status(false);
    }

    private void statusCar() {
        String maXe = String.valueOf(cboMaXe.getSelectedItem());
        String status = carDao.status(maXe);
        if (status.equals("true")) {
            lbStatus.setText("Tr???ng th??i: M?? xe ???? ???????c Thu??");
            btThem.setEnabled(false);
        } else {
            lbStatus.setText(" ");
            btThem.setEnabled(true);
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

                }
            }

            @Override
            public void onCancel() {

            }
        });

    }

    public void fillTableSearch() {
        try {
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbContract.getModel());
            tbContract.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
            System.out.println("L???i");
        }
    }

    private void edit() {
        try {
            String iDCT = String.valueOf(tbContract.getValueAt(this.index, 0));
            // System.out.println(iDCT);
            Model_Contract modelCT = dao.findByIdrental(iDCT);
            if (modelCT != null) {
                setModel(modelCT);
            } else {
                System.out.println("l???i");
            }
        } catch (Exception e) {
        }

    }

    private void clear() {
        try {
            Model_Contract model = new Model_Contract();
            status(true);
            setModel(model);
        } catch (Exception e) {
        }
    }

    private void addContract() {
        long days = DateHelper.daysBetween(jdNgayTra.getDate(), jdNgayThue.getDate());
//        System.out.println(days);
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("B???n mu???n th??m m?? h???p ?????ng n??y ? ", "Nh???n OK ????? c???p nh???t l???i th??ng tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboMaXe, this)
                    && UitilityHelper.checkNullCbo(cboMaKH, this)
                    && UitilityHelper.checkNullCbo(cboTrangThai, this)) {
                if (UitilityHelper.CheckNullText(txtTenXe, this)
                        && UitilityHelper.CheckNullText(txtBienSo, this)) {
                    if (UitilityHelper.checkNumber(txtGiaThue, this)
                            && UitilityHelper.checkNumber(txtQuaHan, this)) {
                        if (UitilityHelper.checkdate(jdNgayHienTai, this)
                                && UitilityHelper.checkdate(jdNgayThue, this)
                                && UitilityHelper.checkdate(jdNgayTra, this)) {
                            if (days >= 2) {
                                try {
                                    String id = String.valueOf(cboMaXe.getSelectedItem());
                                    Model_Contract model = getModel();
                                    dao.insert(model);
                                    carDao.updateStatus(id);
                                    clear();
                                    loadTable();
                                    Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Th??m th??nh c??ng!");
                                } catch (SQLException ex) {
                                    Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "L???i d??? li???u, Vui l??ng ki???m tra l???i d??? li???u!");
                                }
                            } else {
                                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Ng??y tr??? c??ch ??t nh???t 2 ng??y!");
                            }
                        }
                    }
                }
            }

        }
    }

    private void updateContract() {
        System.out.println(cboMaXe.getSelectedItem());
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("B???n mu???n s???a m?? h???p ?????ng n??y ? ", "Nh???n OK ????? c???p nh???t l???i th??ng tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboMaXe, this)
                    && UitilityHelper.checkNullCbo(cboMaKH, this)
                    && UitilityHelper.checkNullCbo(cboTrangThai, this)) {
                if (UitilityHelper.CheckNullText(txtTenXe, this)
                        && UitilityHelper.CheckNullText(txtBienSo, this)) {
                    if (UitilityHelper.checkNumber(txtGiaThue, this)
                            && UitilityHelper.checkNumber(txtQuaHan, this)) {
                        if (UitilityHelper.checkdate(jdNgayHienTai, this)
                                && UitilityHelper.checkdate(jdNgayThue, this)
                                && UitilityHelper.checkdate(jdNgayTra, this)) {
                            try {
                                Model_Contract model = getModel();
                                System.out.println(model);
                                dao.update(model);
                                loadTable();
                                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "C???p nh???t th??nh c??ng!");
                            } catch (SQLException ex) {
                                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "L???i d??? li???u, Vui l??ng ki???m tra l???i d??? li???u!");
                            }
                        }
                    }
                }
            }

        }
    }

    private void deleteContract() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("B???n c?? mu???n h???p ?????ng n??y?", "Nh???n OK ????? x??c nh???n");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            try {
                String id = cboMaXe.getToolTipText();
                String idCar = String.valueOf(cboMaXe.getSelectedItem());
                dao.delete(id);
                dao.updateStatusCar(idCar);
                clear();
                loadTable();
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "X??a th??nh c??ng!");
            } catch (SQLException ex) {
                Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "L???i d??? li???u, Vui l??ng ki???m tra l???i d??? li???u!");
            }

        }
    }

    private void Refresh() {
        LocalDate date = LocalDate.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        List<String> list = new ArrayList<>();
        String nt = String.valueOf(cboMaKH.getSelectedItem());
        String maxe = String.valueOf(cboMaXe.getSelectedItem());
        String bienSo = txtBienSo.getText();
        String tenxe = txtTenXe.getText();
        String giaThue = txtGiaThue.getText();
        String giaQuaHan = txtQuaHan.getText();
        String ngayThue = DateHelper.toString(jdNgayThue.getDate());
        String ngayTra = DateHelper.toString(jdNgayTra.getDate());
        list = dao.selectct();

        txtPanel.setContentType("text/html");
        txtPanel.setText("<div style=\"border-style:dotted; padding-left: 20px;\">\n"
                + "        <h1 style=\"text-align:center\">H???p ????ng thu?? xe c??ng ty rental</h1>\n"
                + "        <p style=\"float:right\">Ng??y " + date.getDayOfMonth() + " th??ng " + date.getMonthValue() + " n??m " + date.getYear() + "</p><br><br><br>\n"
                + "        <p>Ch??ng t??i g???m c??:</p>\n"
                + "        <p>Ng?????i thu??: " + nt + "</p>\n"
                + "        <p>?????a ch???: " + list.get(2) + "</p>\n"
                + "        <p>S??? ??i???n tho???i: " + list.get(3) + "</p>\n"
                + "        <p>?????i di???n cho c??ng ty: Thu?? Xe Oto DPD</p>\n"
                + "        <p>?????a ch???: ???? N???ng</p>\n"
                + "        <p>S??? ??i???n tho???i:0481547845</p>\n"
                + "        <h2 style=\"text-align:center\">Th??ng tin xe</h2>\n"
                + "        <p>M?? Xe: " + maxe + "</p>\n"
                + "        <p>T??n Xe:" + tenxe + "</p>\n"
                + "        <p>Bi???n S???:" + bienSo + "</p>\n"
                + "        <p>Ti???n thu??: " + giaThue + "/Ng??y</p>\n"
                + "        <p>Qu?? h???n:  " + giaQuaHan + "/Ng??y</p>\n"
                + "        <p>Th??i gian thu??:" + ngayThue + "</p>\n"
                + "        <p> Th???i gian tr???: " + ngayTra + "</p>\n"
                + "        <div style=\"float:left\">\n"
                + "            <h3>B??n A</h3>\n"
                + "            <p>" + "C??ng ty thu?? xe" + "</p>\n"
                + "        </div>\n"
                + "        <div style=\"float:right\">\n"
                + "            <h3>B??n B </h3>\n"
                + "            <p>" + list.get(1) + "</p>\n"
                + "        </div>\n"
                + "    </div>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        txtMaThue = new javax.swing.JTextField();
        lineHeader1 = new Dashboar.com.component.lineHeader();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbContract = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPanel = new javax.swing.JTextPane();
        btThem = new Dashboar.com.swing.ButtonCustom();
        btSua = new Dashboar.com.swing.ButtonCustom();
        btXoa = new Dashboar.com.swing.ButtonCustom();
        btRefresh = new Dashboar.com.swing.ButtonCustom();
        jPanel1 = new javax.swing.JPanel();
        lbMaXe = new javax.swing.JLabel();
        lbTenXe = new javax.swing.JLabel();
        lbBienSo = new javax.swing.JLabel();
        txtTenXe = new javax.swing.JTextField();
        txtBienSo = new javax.swing.JTextField();
        lbNgayThue = new javax.swing.JLabel();
        lbNgayTra = new javax.swing.JLabel();
        lnGiaThue = new javax.swing.JLabel();
        lbMaKH = new javax.swing.JLabel();
        lbQuaHan = new javax.swing.JLabel();
        txtQuaHan = new javax.swing.JTextField();
        cboMaKH = new javax.swing.JComboBox<>();
        lbNgayHienTai = new javax.swing.JLabel();
        jdNgayHienTai = new com.toedter.calendar.JDateChooser();
        jdNgayTra = new com.toedter.calendar.JDateChooser();
        txtGiaThue = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        lbTrangThai = new javax.swing.JLabel();
        cboMaXe = new javax.swing.JComboBox<>();
        jdNgayThue = new com.toedter.calendar.JDateChooser();
        lbStatus = new javax.swing.JLabel();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();
        lbLoad = new javax.swing.JLabel();
        btPrint = new Dashboar.com.swing.ButtonCustom();

        txtMaThue.setText("jTextField1");

        lineHeader1.setPreferredSize(new java.awt.Dimension(57, 68));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("H???P ?????NG");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeader1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbContract.setAutoCreateRowSorter(true);
        tbContract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? Thu??", "M?? Xe", "T??n Xe", "Bi???n S???", "M?? KH", "Ng??y Thu??", "Ng??y Tr???", "Gi?? Thu??", "Qu?? H???n", "Tr???ng Th??i"
            }
        ));
        tbContract.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbContractMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbContract);
        if (tbContract.getColumnModel().getColumnCount() > 0) {
            tbContract.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbContract.getColumnModel().getColumn(1).setPreferredWidth(30);
        }

        txtPanel.setEditable(false);
        jScrollPane2.setViewportView(txtPanel);

        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btAdd.png"))); // NOI18N
        btThem.setText("Th??m");
        btThem.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btThem.setIconTextGap(10);
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btUpdate.png"))); // NOI18N
        btSua.setText("S???a");
        btSua.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btSua.setIconTextGap(10);
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btDelete.png"))); // NOI18N
        btXoa.setText("X??a");
        btXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btXoa.setIconTextGap(10);
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btrefresh.png"))); // NOI18N
        btRefresh.setText("Refresh");
        btRefresh.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btRefresh.setIconTextGap(10);
        btRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0};
        jPanel1Layout.rowHeights = new int[] {0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0, 6, 0};
        jPanel1Layout.columnWeights = new double[] {10.0};
        jPanel1.setLayout(jPanel1Layout);

        lbMaXe.setBackground(new java.awt.Color(196, 196, 196));
        lbMaXe.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbMaXe.setForeground(new java.awt.Color(0, 0, 0));
        lbMaXe.setText("M?? Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbMaXe, gridBagConstraints);

        lbTenXe.setBackground(new java.awt.Color(196, 196, 196));
        lbTenXe.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbTenXe.setForeground(new java.awt.Color(0, 0, 0));
        lbTenXe.setText("T??n Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbTenXe, gridBagConstraints);

        lbBienSo.setBackground(new java.awt.Color(196, 196, 196));
        lbBienSo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbBienSo.setForeground(new java.awt.Color(0, 0, 0));
        lbBienSo.setText("Bi???n s???:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbBienSo, gridBagConstraints);

        txtTenXe.setEditable(false);
        txtTenXe.setName("T??n Xe"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 135;
        jPanel1.add(txtTenXe, gridBagConstraints);

        txtBienSo.setEditable(false);
        txtBienSo.setName("Bi???n s???"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 135;
        jPanel1.add(txtBienSo, gridBagConstraints);

        lbNgayThue.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayThue.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbNgayThue.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayThue.setText("Ng??y Thu??:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        jPanel1.add(lbNgayThue, gridBagConstraints);

        lbNgayTra.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayTra.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbNgayTra.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayTra.setText("Ng??y Tr???:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        jPanel1.add(lbNgayTra, gridBagConstraints);

        lnGiaThue.setBackground(new java.awt.Color(196, 196, 196));
        lnGiaThue.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lnGiaThue.setForeground(new java.awt.Color(0, 0, 0));
        lnGiaThue.setText("Gi?? Thu??:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        jPanel1.add(lnGiaThue, gridBagConstraints);

        lbMaKH.setBackground(new java.awt.Color(196, 196, 196));
        lbMaKH.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbMaKH.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKH.setText("M?? KH:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbMaKH, gridBagConstraints);

        lbQuaHan.setBackground(new java.awt.Color(196, 196, 196));
        lbQuaHan.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbQuaHan.setForeground(new java.awt.Color(0, 0, 0));
        lbQuaHan.setText("Qu?? H???n:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 12;
        jPanel1.add(lbQuaHan, gridBagConstraints);

        txtQuaHan.setEditable(false);
        txtQuaHan.setName("Gi?? H???n"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 120;
        jPanel1.add(txtQuaHan, gridBagConstraints);

        cboMaKH.setName("M?? Kh??ch H??ng"); // NOI18N
        cboMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaKHActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 130;
        jPanel1.add(cboMaKH, gridBagConstraints);

        lbNgayHienTai.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayHienTai.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbNgayHienTai.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayHienTai.setText("Ng??y Hi???n t???i:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbNgayHienTai, gridBagConstraints);

        jdNgayHienTai.setEnabled(false);
        jdNgayHienTai.setName("Ng??y Hi???n T???i"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        jPanel1.add(jdNgayHienTai, gridBagConstraints);

        jdNgayTra.setName("Ng??y Tr???"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 80;
        jPanel1.add(jdNgayTra, gridBagConstraints);

        txtGiaThue.setEditable(false);
        txtGiaThue.setName("Gi?? Thu??"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 120;
        jPanel1.add(txtGiaThue, gridBagConstraints);

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ch??a Thanh To??n", "???? Thanh To??n" }));
        cboTrangThai.setEnabled(false);
        cboTrangThai.setName("Tr???ng Th??i"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 35;
        jPanel1.add(cboTrangThai, gridBagConstraints);

        lbTrangThai.setBackground(new java.awt.Color(196, 196, 196));
        lbTrangThai.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(0, 0, 0));
        lbTrangThai.setText("Tr???ng Th??i:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbTrangThai, gridBagConstraints);

        cboMaXe.setName("M?? Xe"); // NOI18N
        cboMaXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMaXeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 130;
        jPanel1.add(cboMaXe, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(jdNgayThue, gridBagConstraints);

        lbStatus.setText("status:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbStatus, gridBagConstraints);

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtSearchMousePressed(evt);
            }
        });

        lbLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btPrint.png"))); // NOI18N
        btPrint.setText("In");
        btPrint.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btPrint.setIconTextGap(15);
        btPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrintActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPrint, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed

        addContract();
    }//GEN-LAST:event_btThemActionPerformed


    private void cboMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaKHActionPerformed
        //selectComboBoxCT();
    }//GEN-LAST:event_cboMaKHActionPerformed

    private void btRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshActionPerformed
        Refresh();
    }//GEN-LAST:event_btRefreshActionPerformed


    private void cboMaXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMaXeActionPerformed
        selectComboBoxCar();
    }//GEN-LAST:event_cboMaXeActionPerformed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        updateContract();
    }//GEN-LAST:event_btSuaActionPerformed

    private void tbContractMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbContractMousePressed
        if (evt.getClickCount() == 2) {
            index = tbContract.rowAtPoint(evt.getPoint());
            edit();
            loadTable();

        }
    }//GEN-LAST:event_tbContractMousePressed

    private void btPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrintActionPerformed
        try {
            txtPanel.print();
        } catch (PrinterException ex) {
            Logger.getLogger(Contract.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrintActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        deleteContract();
    }//GEN-LAST:event_btXoaActionPerformed

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed

        Search();

    }//GEN-LAST:event_txtSearchMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboar.com.swing.ButtonCustom btPrint;
    private Dashboar.com.swing.ButtonCustom btRefresh;
    private Dashboar.com.swing.ButtonCustom btSua;
    private Dashboar.com.swing.ButtonCustom btThem;
    private Dashboar.com.swing.ButtonCustom btXoa;
    private javax.swing.JComboBox<String> cboMaKH;
    private javax.swing.JComboBox<String> cboMaXe;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdNgayHienTai;
    private com.toedter.calendar.JDateChooser jdNgayThue;
    private com.toedter.calendar.JDateChooser jdNgayTra;
    private javax.swing.JLabel lbBienSo;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaXe;
    private javax.swing.JLabel lbNgayHienTai;
    private javax.swing.JLabel lbNgayThue;
    private javax.swing.JLabel lbNgayTra;
    private javax.swing.JLabel lbQuaHan;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JLabel lbTenXe;
    private javax.swing.JLabel lbTrangThai;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JLabel lnGiaThue;
    private javax.swing.JTable tbContract;
    private javax.swing.JTextField txtBienSo;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JTextField txtMaThue;
    private javax.swing.JTextPane txtPanel;
    private javax.swing.JTextField txtQuaHan;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    private javax.swing.JTextField txtTenXe;
    // End of variables declaration//GEN-END:variables
}
