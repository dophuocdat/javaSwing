package Dashboar.com.Form;

import Dashboar.com.DAO.CustomerDAO;
import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.Helper.Notification;
import Dashboar.com.Helper.UitilityHelper;
import Dashboar.com.Model.Model_Customer;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
import java.io.File;
import java.sql.SQLException;
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
public class CustomerMgm extends javax.swing.JPanel {

    DefaultTableModel model;
    private NhanVien user;
    CustomerDAO dao = new CustomerDAO();
    NhanVienDAO nvDao = new NhanVienDAO();
    int index = 0;
    JFileChooser fileChooser = new JFileChooser();
    private JFrame fr;

    public CustomerMgm(NhanVien user) {
        initComponents();
        this.user = user;
        init();
    }

    private void init() {
        loadTable();
        txtMaNV.setText(user.getMaNV());
        tbCustomer.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        tbCustomer = new javax.swing.JTable();
        lineHeader1 = new Dashboar.com.component.lineHeader();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbMaKH = new javax.swing.JLabel();
        lbHoVaTen = new javax.swing.JLabel();
        lbGioiTinh = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        lbDiaChi = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        txtpassword = new javax.swing.JTextField();
        lbUserName = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        lbMaNV = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtMaNV = new javax.swing.JTextField();
        btThem = new Dashboar.com.swing.ButtonCustom();
        btSua = new Dashboar.com.swing.ButtonCustom();
        btXoa = new Dashboar.com.swing.ButtonCustom();
        btMoi = new Dashboar.com.swing.ButtonCustom();
        lbIcon = new javax.swing.JLabel();
        lbLoad = new javax.swing.JLabel();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();

        tbCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ Và Tên", "Giới Tính", "Số Điện Thoại", "Địa Chỉ", "Hình Ảnh", "Username", "Password", "Mã NV"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbCustomerMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbCustomer);

        jLabel9.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("QUẢN LÝ KHÁCH HÀNG");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0, 4, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1Layout.columnWeights = new double[] {0.0};
        jPanel1.setLayout(jPanel1Layout);

        lbMaKH.setBackground(new java.awt.Color(196, 196, 196));
        lbMaKH.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaKH.setForeground(new java.awt.Color(0, 0, 0));
        lbMaKH.setText("Mã KH:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbMaKH, gridBagConstraints);

        lbHoVaTen.setBackground(new java.awt.Color(196, 196, 196));
        lbHoVaTen.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbHoVaTen.setForeground(new java.awt.Color(0, 0, 0));
        lbHoVaTen.setText("Họ Và Tên:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbHoVaTen, gridBagConstraints);

        lbGioiTinh.setBackground(new java.awt.Color(196, 196, 196));
        lbGioiTinh.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbGioiTinh.setForeground(new java.awt.Color(0, 0, 0));
        lbGioiTinh.setText("Giới Tính:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbGioiTinh, gridBagConstraints);

        txtMaKH.setName("Mã khách hàng"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtMaKH, gridBagConstraints);

        lbDiaChi.setBackground(new java.awt.Color(196, 196, 196));
        lbDiaChi.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbDiaChi.setForeground(new java.awt.Color(0, 0, 0));
        lbDiaChi.setText("Địa Chỉ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 9;
        jPanel1.add(lbDiaChi, gridBagConstraints);

        lbPassword.setBackground(new java.awt.Color(196, 196, 196));
        lbPassword.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(0, 0, 0));
        lbPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        jPanel1.add(lbPassword, gridBagConstraints);

        lbSDT.setBackground(new java.awt.Color(196, 196, 196));
        lbSDT.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbSDT.setForeground(new java.awt.Color(0, 0, 0));
        lbSDT.setText("SĐT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbSDT, gridBagConstraints);

        txtpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpasswordActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtpassword, gridBagConstraints);

        lbUserName.setBackground(new java.awt.Color(196, 196, 196));
        lbUserName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbUserName.setForeground(new java.awt.Color(0, 0, 0));
        lbUserName.setText("Username");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbUserName, gridBagConstraints);

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setName("Giới tính"); // NOI18N
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(cboGioiTinh, gridBagConstraints);

        lbMaNV.setBackground(new java.awt.Color(196, 196, 196));
        lbMaNV.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(0, 0, 0));
        lbMaNV.setText("Mã NV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbMaNV, gridBagConstraints);

        txtHoVaTen.setName("Họ và tên"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtHoVaTen, gridBagConstraints);

        txtUsername.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtUsername, gridBagConstraints);

        txtSDT.setName("Số điện thoại"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtSDT, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 121;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtDiaChi, gridBagConstraints);

        txtMaNV.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel1.add(txtMaNV, gridBagConstraints);

        btThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/AddCTs.png"))); // NOI18N
        btThem.setText("Add");
        btThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThemActionPerformed(evt);
            }
        });

        btSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/updateCT.png"))); // NOI18N
        btSua.setText("Update");
        btSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuaActionPerformed(evt);
            }
        });

        btXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btDelete.png"))); // NOI18N
        btXoa.setText("Delete");
        btXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXoaActionPerformed(evt);
            }
        });

        btMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Dashboar/com/Icon/btClear.png"))); // NOI18N
        btMoi.setText("Clear");
        btMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMoiActionPerformed(evt);
            }
        });

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setText("Image");
        lbIcon.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        lbIcon.setPreferredSize(new java.awt.Dimension(35, 22));
        lbIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbIconMousePressed(evt);
            }
        });

        lbLoad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 20, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadTable() {
        model = (DefaultTableModel) tbCustomer.getModel();
        model.setRowCount(0);
        try {
            List<Model_Customer> list = dao.select();

            for (Model_Customer ctm : list) {
                Object[] rows = {
                    ctm.getMaKh(),
                    ctm.getHoVaTen(),
                    ctm.isGioiTinh() ? "Nam" : "Nữ",
                    ctm.getsDT(),
                    ctm.getDiaChi(),
                    ctm.getHinhAnh(),
                    // ct.getNgayHienTai(),
                    ctm.getUsername(),
                    ctm.getPassword(),
                    ctm.getMaNV()};
                model.addRow(rows);
            }
        } catch (Exception e) {

        }
        model.fireTableDataChanged();
    }

    private Model_Customer getModel() {
        Model_Customer model = new Model_Customer();
        model.setMaKh(txtMaKH.getText());
        model.setHoVaTen(txtHoVaTen.getText());
        if (cboGioiTinh.getModel().getSelectedItem() == "Nam") {
            model.setGioiTinh(true);
        } else {
            model.setGioiTinh(false);
        }
        model.setsDT(txtSDT.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setHinhAnh(lbIcon.getToolTipText());
        model.setUsername(txtUsername.getText());
        model.setPassword(txtpassword.getText());
        model.setMaNV(txtMaNV.getText());
        return model;
    }

    private void setModel(Model_Customer model) {
        txtMaKH.setText(model.getMaKh());
        txtHoVaTen.setText(model.getHoVaTen());
        if (model.isGioiTinh()) {
            cboGioiTinh.getModel().setSelectedItem("Nam");
        } else {
            cboGioiTinh.getModel().setSelectedItem("Nữ");
        }
        txtSDT.setText(model.getsDT());
        txtDiaChi.setText(model.getDiaChi());
        lbIcon.setToolTipText(model.getHinhAnh());
        if (model.getHinhAnh() != null) {
            lbLoad.setText("");
            lbIcon.setIcon(ImageHelper.readImageKH(model.getHinhAnh()));
        } else {
            lbIcon.setIcon(ImageHelper.readImageKH("null"));
        }
        txtUsername.setText(model.getUsername());
        txtpassword.setText(model.getPassword());
        txtMaNV.setText(user.getMaNV());

    }

    private void edit() {
        try {
            String idKH = (String) tbCustomer.getValueAt(this.index, 0);
            System.out.println(idKH);
            Model_Customer model = dao.findbyID(idKH);
            if (model != null) {
                setModel(model);

            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void addCustomer() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thêm thông tin khách hàng? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboGioiTinh, this)) {
                if (UitilityHelper.CheckNullText(txtMaKH, this)
                        && UitilityHelper.CheckNullText(txtHoVaTen, this)
                        && UitilityHelper.CheckNullText(txtDiaChi, this)
                        && UitilityHelper.CheckNullText(txtSDT, this)) {
                    if (UitilityHelper.checkImage(lbIcon, this)) {
                        try {
                            Model_Customer model = getModel();
                            dao.insert(model);
                            loadTable();
                            Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Thêm thành công!");
                        } catch (SQLException ex) {
                            Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");
                        }
                    }

                }

            }

        }
    }

    private void updateCutomer() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thây đổi dữ liệu khách hàng ? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboGioiTinh, this)) {
                if (UitilityHelper.CheckNullText(txtMaKH, this)
                        && UitilityHelper.CheckNullText(txtHoVaTen, this)
                        && UitilityHelper.CheckNullText(txtDiaChi, this)
                        && UitilityHelper.CheckNullText(txtSDT, this)) {
                    if (UitilityHelper.checkImage(lbIcon, this)) {
                        try {
                            Model_Customer model = getModel();
                            dao.update(model);
                            loadTable();
                            Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Cập nhật thành công!");
                        } catch (SQLException ex) {
                            Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");
                        }
                    }

                }

            }

        }

    }

    private void deleteCustomer() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn xóa mã khách hàng này? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.CheckNullText(txtMaKH, this)) {
                try {
                    String id = txtMaKH.getText();
                    dao.delete(id);
                    loadTable();
                    Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.SUCCESS, "Cập nhật thành công!");
                } catch (SQLException ex) {
                    Notification.showNotification(fr, Dashboar.com.swing.Notification.Type.WARNING, "Lỗi dữ liệu, Vui lòng kiểm tra lại dữ liệu!");

                }
            }
        }
    }

    private void clear() {
        //Model_Contract model = new Model_Contract();
        setModel(new Model_Customer());
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbCustomer.getModel());
            tbCustomer.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
            System.out.println("Lỗi");
        }
    }

    private void txtpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpasswordActionPerformed

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        addCustomer();

    }//GEN-LAST:event_btThemActionPerformed

    private void txtSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSearchMousePressed
        Search();
    }//GEN-LAST:event_txtSearchMousePressed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void tbCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCustomerMousePressed
        if (evt.getClickCount() == 2) {
            index = tbCustomer.rowAtPoint(evt.getPoint());
            edit();
            lbIcon.setText("");
        }
    }//GEN-LAST:event_tbCustomerMousePressed

    private void lbIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMousePressed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (ImageHelper.saveImageKh(file)) {
                lbIcon.setText("");
                lbIcon.setIcon(ImageHelper.readImageKH(file.getName()));
                lbIcon.setToolTipText(file.getName());
            }
        }
    }//GEN-LAST:event_lbIconMousePressed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        updateCutomer();
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        deleteCustomer();
    }//GEN-LAST:event_btXoaActionPerformed

    private void btMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMoiActionPerformed
        clear();
    }//GEN-LAST:event_btMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboar.com.swing.ButtonCustom btMoi;
    private Dashboar.com.swing.ButtonCustom btSua;
    private Dashboar.com.swing.ButtonCustom btThem;
    private Dashboar.com.swing.ButtonCustom btXoa;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDiaChi;
    private javax.swing.JLabel lbGioiTinh;
    private javax.swing.JLabel lbHoVaTen;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbUserName;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JTable tbCustomer;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtpassword;
    // End of variables declaration//GEN-END:variables
}
