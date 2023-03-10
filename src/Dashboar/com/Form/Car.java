package Dashboar.com.Form;

import Dashboar.com.DAO.CarDAO;
import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.Helper.UitilityHelper;
import java.io.File;
import Dashboar.com.Model.Model_Car;
import Dashboar.com.swing.Notification;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
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
public final class Car extends javax.swing.JPanel {
    
    private DefaultTableModel model;
    private CarDAO dao = new CarDAO();
    private int index = 0;
    private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private JFrame fr;
    JFileChooser fileChooser = new JFileChooser();
    
    public Car() {
        initComponents();
        init();
    }
    
    private void init() {
        tbCar.setDefaultEditor(Object.class, null);
        loadTable();
       
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField6 = new javax.swing.JTextField();
        fontAwesomeIcon = new javaswingdev.FontAwesomeIcon();
        googleMaterialIcon1 = new javaswingdev.GoogleMaterialIcon();
        jPanel2 = new javax.swing.JPanel();
        lineHeader = new Dashboar.com.component.lineHeader();
        lbTitle = new javax.swing.JLabel();
        PanelGirdBadLayout = new javax.swing.JPanel();
        lbMaXe = new javax.swing.JLabel();
        lbTenXe = new javax.swing.JLabel();
        lbHangXe = new javax.swing.JLabel();
        txtMaXe = new javax.swing.JTextField();
        txtHangXe = new javax.swing.JTextField();
        lbNgayNhap = new javax.swing.JLabel();
        lbBienSo = new javax.swing.JLabel();
        lbGiaThue = new javax.swing.JLabel();
        lbSoCho = new javax.swing.JLabel();
        txtSoCho = new javax.swing.JTextField();
        lbQuaHan = new javax.swing.JLabel();
        txtGiaQuaHan = new javax.swing.JTextField();
        txtGiaThue = new javax.swing.JTextField();
        cboTrangThai = new javax.swing.JComboBox<>();
        lbTrangThai = new javax.swing.JLabel();
        txtBienSo = new javax.swing.JTextField();
        txtTenXe = new javax.swing.JTextField();
        chdNgayNhap = new com.toedter.calendar.JDateChooser();
        lbIcon = new javax.swing.JLabel();
        btThem = new Dashboar.com.swing.ButtonCustom();
        btSua = new Dashboar.com.swing.ButtonCustom();
        btLamMoi = new Dashboar.com.swing.ButtonCustom();
        btXoa = new Dashboar.com.swing.ButtonCustom();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCar = new javax.swing.JTable();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();
        lbLoad = new javax.swing.JLabel();

        jTextField6.setText("jTextField6");

        fontAwesomeIcon.setColor1(new java.awt.Color(165, 201, 202));
        fontAwesomeIcon.setColor2(new java.awt.Color(231, 230, 226));
        fontAwesomeIcon.setIcon(javaswingdev.FontAwesome.CAR);
        fontAwesomeIcon.setSize(20);

        googleMaterialIcon1.setIcon(javaswingdev.GoogleMaterialDesignIcon.CARD_GIFTCARD);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 1, 1));

        lineHeader.setPreferredSize(new java.awt.Dimension(57, 68));

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("QUẢN LÝ XE");

        javax.swing.GroupLayout lineHeaderLayout = new javax.swing.GroupLayout(lineHeader);
        lineHeader.setLayout(lineHeaderLayout);
        lineHeaderLayout.setHorizontalGroup(
            lineHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeaderLayout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        lineHeaderLayout.setVerticalGroup(
            lineHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeaderLayout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        PanelGirdBadLayout.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0};
        jPanel1Layout.rowHeights = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0};
        jPanel1Layout.columnWeights = new double[] {10.0};
        PanelGirdBadLayout.setLayout(jPanel1Layout);

        lbMaXe.setBackground(new java.awt.Color(196, 196, 196));
        lbMaXe.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaXe.setForeground(new java.awt.Color(0, 0, 0));
        lbMaXe.setText("Mã Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbMaXe, gridBagConstraints);

        lbTenXe.setBackground(new java.awt.Color(196, 196, 196));
        lbTenXe.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbTenXe.setForeground(new java.awt.Color(0, 0, 0));
        lbTenXe.setText("Tên Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbTenXe, gridBagConstraints);

        lbHangXe.setBackground(new java.awt.Color(196, 196, 196));
        lbHangXe.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbHangXe.setForeground(new java.awt.Color(0, 0, 0));
        lbHangXe.setText("Hãng Xe:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbHangXe, gridBagConstraints);

        txtMaXe.setName("Mã Xe"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtMaXe, gridBagConstraints);

        txtHangXe.setName("Hãng Xe"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtHangXe, gridBagConstraints);

        lbNgayNhap.setBackground(new java.awt.Color(196, 196, 196));
        lbNgayNhap.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbNgayNhap.setForeground(new java.awt.Color(0, 0, 0));
        lbNgayNhap.setText("Ngày Nhập:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        PanelGirdBadLayout.add(lbNgayNhap, gridBagConstraints);

        lbBienSo.setBackground(new java.awt.Color(196, 196, 196));
        lbBienSo.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbBienSo.setForeground(new java.awt.Color(0, 0, 0));
        lbBienSo.setText("Biển Số:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        PanelGirdBadLayout.add(lbBienSo, gridBagConstraints);

        lbGiaThue.setBackground(new java.awt.Color(196, 196, 196));
        lbGiaThue.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbGiaThue.setForeground(new java.awt.Color(0, 0, 0));
        lbGiaThue.setText("Giá Thuê:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbGiaThue, gridBagConstraints);

        lbSoCho.setBackground(new java.awt.Color(196, 196, 196));
        lbSoCho.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbSoCho.setForeground(new java.awt.Color(0, 0, 0));
        lbSoCho.setText("Số Chỗ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbSoCho, gridBagConstraints);

        txtSoCho.setText("0");
        txtSoCho.setName("Số Chỗ"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 135;
        PanelGirdBadLayout.add(txtSoCho, gridBagConstraints);

        lbQuaHan.setBackground(new java.awt.Color(196, 196, 196));
        lbQuaHan.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbQuaHan.setForeground(new java.awt.Color(0, 0, 0));
        lbQuaHan.setText("Quá Hạn:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 33;
        gridBagConstraints.ipady = 12;
        PanelGirdBadLayout.add(lbQuaHan, gridBagConstraints);

        txtGiaQuaHan.setText("0");
        txtGiaQuaHan.setName("Quá Hạn"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtGiaQuaHan, gridBagConstraints);

        txtGiaThue.setText("0");
        txtGiaThue.setName("Giá Thuê"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtGiaThue, gridBagConstraints);

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "Đã Thuê", "Chưa Thuê" }));
        cboTrangThai.setName("Trạng thái"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 60;
        PanelGirdBadLayout.add(cboTrangThai, gridBagConstraints);

        lbTrangThai.setBackground(new java.awt.Color(196, 196, 196));
        lbTrangThai.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbTrangThai.setForeground(new java.awt.Color(0, 0, 0));
        lbTrangThai.setText("Trạng Thái:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(lbTrangThai, gridBagConstraints);

        txtBienSo.setName("Biển số"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtBienSo, gridBagConstraints);

        txtTenXe.setName("Tên Xe"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        PanelGirdBadLayout.add(txtTenXe, gridBagConstraints);

        chdNgayNhap.setName("Ngày Nhập"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 24;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        PanelGirdBadLayout.add(chdNgayNhap, gridBagConstraints);

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setText("Image");
        lbIcon.setToolTipText("");
        lbIcon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        lbIcon.setName("Hình Ảnh"); // NOI18N
        lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbIconMousePressed(evt);
            }
        });

        btThem.setBackground(new java.awt.Color(0, 158, 255));
        btThem.setForeground(new java.awt.Color(0, 0, 0));
        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btAdd.png"))); // NOI18N
        btThem.setText("Thêm");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setBackground(new java.awt.Color(0, 158, 255));
        btSua.setForeground(new java.awt.Color(0, 0, 0));
        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btUpdate.png"))); // NOI18N
        btSua.setText("Sửa");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btLamMoi.setBackground(new java.awt.Color(0, 158, 255));
        btLamMoi.setForeground(new java.awt.Color(0, 0, 0));
        btLamMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btrefresh.png"))); // NOI18N
        btLamMoi.setText("Làm mới");
        btLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLamMoiActionPerformed(evt);
            }
        });

        btXoa.setBackground(new java.awt.Color(0, 158, 255));
        btXoa.setForeground(new java.awt.Color(0, 0, 0));
        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btDelete.png"))); // NOI18N
        btXoa.setText("Xóa");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        tbCar.setAutoCreateRowSorter(true);
        tbCar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Xe", "Tên Xe", "Hãng Xe", "Số Chỗ", "Ngày Nhập", "Hinh", "Biển Số", "Giá Thuê ($)", "Giá Quá Hạn ($)", "Trạng Thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbCar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbCarMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCar);
        if (tbCar.getColumnModel().getColumnCount() > 0) {
            tbCar.getColumnModel().getColumn(0).setPreferredWidth(25);
            tbCar.getColumnModel().getColumn(3).setPreferredWidth(25);
        }

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lineHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 944, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PanelGirdBadLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lineHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelGirdBadLayout, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbLoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadTable() {
        model = (DefaultTableModel) tbCar.getModel();
        model.setRowCount(0);
        try {
            List<Model_Car> list = dao.select();
            
            for (Model_Car car : list) {
                
                Object[] rows = {
                    car.getMaXe(),
                    car.getTenXe(),
                    car.getHangXe(),
                    car.getSucChua(),
                    car.getNgayNhap(),
                    car.getHinhAnh(),
                    car.getBienSo(),
                    car.getGiaThue(),
                    car.getGiaQuaHan(),
                    car.isTrangThai() ? "Đã Thuê" : "Chưa Thuê",};
                model.addRow(rows);
            }
        } catch (Exception e) {
            
        }
        model.fireTableDataChanged();
    }
    
    private void setModel(Model_Car model) {
        txtMaXe.setText(model.getMaXe());
        txtTenXe.setText(model.getTenXe());
        txtHangXe.setText(model.getHangXe());
        txtSoCho.setText(String.valueOf(model.getSucChua()));
//        Calendar Cnn = Calendar.getInstance();
//        Cnn.setTime(model.getNgayNhap());
//        chdNgayNhap.setCalendar(Cnn);
        chdNgayNhap.setDate(model.getNgayNhap());
        lbIcon.setToolTipText(model.getHinhAnh());
        if (model.getHinhAnh() != null) {
            lbLoad.setText("");
            lbIcon.setIcon(ImageHelper.readImageCar(model.getHinhAnh()));
        } else {
            lbIcon.setIcon(ImageHelper.readImageCar("null"));
            
        }
        txtBienSo.setText(model.getBienSo());
        txtGiaThue.setText(String.valueOf(model.getGiaThue()));
        txtGiaQuaHan.setText(String.valueOf(model.getGiaQuaHan()));
        if (model.isTrangThai()) {
            cboTrangThai.getModel().setSelectedItem("Đã Thuê");
        } else {
            cboTrangThai.getModel().setSelectedItem("Chưa Thuê");
        }
        
    }
    
    private Model_Car getModel() {
        Model_Car model_car = new Model_Car();
        model_car.setMaXe(txtMaXe.getText());
        model_car.setTenXe(txtTenXe.getText());
        model_car.setHangXe(txtHangXe.getText());
        model_car.setSucChua(Integer.valueOf(txtSoCho.getText()));
        model_car.setNgayNhap(chdNgayNhap.getDate());
        model_car.setHinhAnh(lbIcon.getToolTipText());
        model_car.setBienSo(txtBienSo.getText());
        model_car.setGiaThue(Float.valueOf(txtGiaThue.getText()));
        model_car.setGiaQuaHan(Float.valueOf(txtGiaQuaHan.getText()));
        
        if (cboTrangThai.getModel().getSelectedItem() == "Đã Thuê") {
            model_car.setTrangThai(true);
        } else {
            model_car.setTrangThai(false);
        }
        
        return model_car;
    }
    
    private void edit() {
        try {
            
            String iDCar = (String) tbCar.getValueAt(this.index, 0);
            System.out.println(iDCar);
            Model_Car model = CarDAO.findById(iDCar);
            if (model != null) {
                setModel(model);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
    }
    

    private void tbCarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCarMousePressed
        if (evt.getClickCount() == 2) {
            index = tbCar.rowAtPoint(evt.getPoint());
            edit();
            lbIcon.setText("");
        }
    }//GEN-LAST:event_tbCarMousePressed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        System.out.println(format.format(chdNgayNhap.getDate()));
        addCar();

    }//GEN-LAST:event_btThemActionPerformed
    
    private void showNotification(JFrame fr, Notification.Type type, String text) {
        Notification panel = new Notification(fr, type, text);
        panel.showNotification();
    }

    private void lbIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMousePressed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (ImageHelper.saveImageCar(file)) {
                lbIcon.setText("");
                lbIcon.setIcon(ImageHelper.readImageCar(file.getName()));
                lbIcon.setToolTipText(file.getName());
            }
        }
    }//GEN-LAST:event_lbIconMousePressed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        updateCar();
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        deleteCar();
    }//GEN-LAST:event_btXoaActionPerformed

    private void btLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLamMoiActionPerformed
        clear();
    }//GEN-LAST:event_btLamMoiActionPerformed

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        Search();
    }//GEN-LAST:event_txtSearchMousePressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed
    
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbCar.getModel());
            tbCar.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
        }
    }
    
    private void addCar() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thêm mã xe này ? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkdate(chdNgayNhap, this)) {
                if (UitilityHelper.checkImage(lbIcon, this)) {
                    if (UitilityHelper.checkNullCbo(cboTrangThai, this)) {
                        if (UitilityHelper.checkNumber(txtSoCho, this)
                                && UitilityHelper.checkNumber(txtGiaThue, this)
                                && UitilityHelper.checkNumber(txtGiaQuaHan, this)) {
                            if (UitilityHelper.CheckNullText(txtMaXe, this)
                                    && UitilityHelper.CheckNullText(txtTenXe, this)
                                    && UitilityHelper.CheckNullText(txtHangXe, this)
                                    && UitilityHelper.CheckNullText(txtSoCho, this)
                                    && UitilityHelper.CheckNullText(txtBienSo, this)
                                    && UitilityHelper.CheckNullText(txtGiaThue, this)
                                    && UitilityHelper.CheckNullText(txtGiaQuaHan, this)
                                    && UitilityHelper.checkImage(lbIcon, this)) {
                                try {
                                    Model_Car model = getModel();
                                    dao.insert(model);
                                    clear();
                                    loadTable();
                                    showNotification(fr, Notification.Type.SUCCESS, "Thêm mới thành công!");
                                } catch (SQLException e) {
                                    
                                    showNotification(fr, Notification.Type.WARNING, "Lỗi nhập dữ liệu, vui lòng kiểm tra lại dữ liệu");
                                }
                            }
                            
                        }
                    }
                    
                }
            }
            
        }
        
    }
    
    private void updateCar() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn Có muốn sửa mã xe này không? ", "Nhấn OK để cập nhật thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkdate(chdNgayNhap, this)) {
                if (UitilityHelper.checkImage(lbIcon, this)) {
                    if (UitilityHelper.checkNullCbo(cboTrangThai, this)) {
                        if (UitilityHelper.checkNumber(txtSoCho, this)
                                && UitilityHelper.checkNumber(txtGiaThue, this)
                                && UitilityHelper.checkNumber(txtGiaQuaHan, this)) {
                            if (UitilityHelper.CheckNullText(txtMaXe, this)
                                    && UitilityHelper.CheckNullText(txtTenXe, this)
                                    && UitilityHelper.CheckNullText(txtHangXe, this)
                                    && UitilityHelper.CheckNullText(txtSoCho, this)
                                    && UitilityHelper.CheckNullText(txtBienSo, this)
                                    && UitilityHelper.CheckNullText(txtGiaThue, this)
                                    && UitilityHelper.CheckNullText(txtGiaQuaHan, this)
                                    && UitilityHelper.checkImage(lbIcon, this)) {
                                try {
                                    Model_Car model = getModel();
                                    dao.update(model);
                                    loadTable();
                                    showNotification(fr, Notification.Type.SUCCESS, "Sửa thành công");
                                } catch (SQLException ex) {
                                    showNotification(fr, Notification.Type.WARNING, "Lỗi dữ liệu, vui lòng kiểm tra lại dữ liệu");
                                }
                            }
                            
                        }
                    }
                    
                }
            }
            
        }
        
    }
    
    private void deleteCar() {
        String maXe = txtMaXe.getText();
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn Có muốn xóa mã xe này không? ", "Nhấn OK để cập nhật thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (maXe.length() > 0) {
                try {
                    dao.delete(maXe);
                    loadTable();
                    clear();
                    showNotification(fr, Notification.Type.SUCCESS, "Xóa Thành Công");
                } catch (SQLException ex) {
                    showNotification(fr, Notification.Type.WARNING, "Lỗi nhập dữ liệu, vui lòng kiểm tra lại dữ liệu");
                }
            } else {
                fr = (JFrame) SwingUtilities.getWindowAncestor(this);
                showNotification(fr, Notification.Type.WARNING, "Không được để trống mã xe!");
            }
        }
    }
    
    private void clear() {
        Model_Car car = new Model_Car();
        setModel(car);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGirdBadLayout;
    private Dashboar.com.swing.ButtonCustom btLamMoi;
    private Dashboar.com.swing.ButtonCustom btSua;
    private Dashboar.com.swing.ButtonCustom btThem;
    private Dashboar.com.swing.ButtonCustom btXoa;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser chdNgayNhap;
    private javaswingdev.FontAwesomeIcon fontAwesomeIcon;
    private javaswingdev.GoogleMaterialIcon googleMaterialIcon1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lbBienSo;
    private javax.swing.JLabel lbGiaThue;
    private javax.swing.JLabel lbHangXe;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbMaXe;
    private javax.swing.JLabel lbNgayNhap;
    private javax.swing.JLabel lbQuaHan;
    private javax.swing.JLabel lbSoCho;
    private javax.swing.JLabel lbTenXe;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTrangThai;
    private Dashboar.com.component.lineHeader lineHeader;
    private javax.swing.JTable tbCar;
    private javax.swing.JTextField txtBienSo;
    private javax.swing.JTextField txtGiaQuaHan;
    private javax.swing.JTextField txtGiaThue;
    private javax.swing.JTextField txtHangXe;
    private javax.swing.JTextField txtMaXe;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    private javax.swing.JTextField txtSoCho;
    private javax.swing.JTextField txtTenXe;
    // End of variables declaration//GEN-END:variables
}
