package Dashboar.com.Form;

import Dashboar.com.Event.EventCallBack;
import Dashboar.com.Event.EventTextField;
import Dashboar.com.Helper.ImageHelper;
import Dashboar.com.Helper.MessagDialog;
import Dashboar.com.Helper.UitilityHelper;
import java.io.File;
import Dashboar.com.swing.Notification;
import Login_Register.com.Dao.NhanVienDAO;
import Login_Register.com.Model.NhanVien;
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
public final class EmployeeMgm extends javax.swing.JPanel {
    
    private DefaultTableModel model;
    private NhanVienDAO dao = new NhanVienDAO();
    private int index = 0;
    private final SimpleDateFormat format = new SimpleDateFormat("dd-MM-YYYY");
    private JFrame fr;
    JFileChooser fileChooser = new JFileChooser();
    private NhanVien user;
    
    public EmployeeMgm(NhanVien user) {
        this.user = user;
        initComponents();
        init();
    }
    
    private void init() {
        tbEmp.setDefaultEditor(Object.class, null);
        loadTable();
        
    }
    
    private void loadTable() {
        model = (DefaultTableModel) tbEmp.getModel();
        model.setRowCount(0);
        try {
            List<NhanVien> list = dao.select();
            
            for (NhanVien nv : list) {
                Object[] rows = {
                    nv.getMaNV(),
                    nv.getHovaTen(),
                    nv.getsDT(),
                    nv.getDiaChi(),
                    // nv.getHinhAnh(),
                    nv.getUserName(),
                    nv.getPassword()};
                model.addRow(rows);
            }
        } catch (Exception e) {
            
        }
        model.fireTableDataChanged();
    }
    
    private void setModel(NhanVien model) {
        txtMaNV.setText(model.getMaNV());
        txtHoVaTen.setText(model.getHovaTen());
        txtDiaChi.setText(model.getDiaChi());
        txtSDT.setText(model.getsDT());
        txtUsername.setText(model.getUserName());
        txtPassword.setText(model.getPassword());
        lbIcon.setToolTipText(model.getHinhAnh());
        if (model.getHinhAnh() != null) {
            lbLoad.setText("");
            lbIcon.setIcon(ImageHelper.readImageNV(model.getHinhAnh()));
        } else {
            lbIcon.setIcon(ImageHelper.readImageNV("null"));
            
        }
        if (model.isChucVu() == true) {
            cboChucVu.setSelectedIndex(1);
        } else {
            cboChucVu.setSelectedIndex(0);
        }
        
    }
    
    private NhanVien getModel() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHovaTen(txtHoVaTen.getText());
        model.setDiaChi(txtDiaChi.getText());
        model.setsDT(txtSDT.getText());
        model.setPassword(txtPassword.getText());
        model.setHinhAnh(lbIcon.getToolTipText());
        model.setUserName(txtUsername.getText());
        if (cboChucVu.getSelectedItem().equals("Quản Lý")) {
            model.setChucVu(true);
        } else {
            model.setChucVu(false);
            
        }
        return model;
    }
    
    private void edit() {
        try {
            
            String iDEMP = (String) tbEmp.getValueAt(this.index, 0);
            System.out.println(iDEMP);
            NhanVien model = dao.selectById(iDEMP);
            if (model != null) {
                setModel(model);
                txtUsername.setEditable(false);
                txtMaNV.setEditable(false);
                
            }
        } catch (Exception e) {
            System.out.println(e.toString());
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
            TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbEmp.getModel());
            tbEmp.setRowSorter(sorter);
            if (txtSearch.getText().length() >= 0) {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + txtSearch.getText()));
            } else {
                sorter.setRowFilter(null);
            }
        } catch (Exception e) {
        }
    }
    
    private void insert() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn muốn thêm mã xe này ? ", "Nhấn OK để cập nhật lại thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboChucVu, this)) {
                if (UitilityHelper.CheckNullText(txtMaNV, this)
                        && UitilityHelper.CheckNullText(txtHoVaTen, this)
                        && UitilityHelper.CheckNullText(txtDiaChi, this)
                        && UitilityHelper.CheckNullText(txtUsername, this)
                        && UitilityHelper.CheckNullText(txtPassword, this)
                        && UitilityHelper.CheckNullText(txtSDT, this)) {
                    if (UitilityHelper.checkImage(lbIcon, this)) {
                        try {
                            NhanVien model = getModel();
                            dao.insert(model);
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
    
    private void update() {
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn Có muốn sửa mã xe này không? ", "Nhấn OK để cập nhật thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (UitilityHelper.checkNullCbo(cboChucVu, this)) {
                if (UitilityHelper.CheckNullText(txtMaNV, this)
                        && UitilityHelper.CheckNullText(txtHoVaTen, this)
                        && UitilityHelper.CheckNullText(txtDiaChi, this)
                        && UitilityHelper.CheckNullText(txtUsername, this)
                        && UitilityHelper.CheckNullText(txtPassword, this)
                        && UitilityHelper.CheckNullText(txtSDT, this)) {
                    if (UitilityHelper.checkImage(lbIcon, this)) {
                        try {
                            NhanVien model = getModel();
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
    
    private void delete() {
        String maNV = txtMaNV.getText();
        fr = (JFrame) SwingUtilities.getWindowAncestor(this);
        MessagDialog obj = new MessagDialog(fr);
        obj.showMessage("Bạn Có muốn xóa mã xe này không? ", "Nhấn OK để cập nhật thông tin");
        if (obj.getMessageType() == MessagDialog.MessageType.OK) {
            if (maNV.length() > 0) {
                try {
                    dao.delete(maNV);
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
        NhanVien model = new NhanVien();
        setModel(model);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTextField6 = new javax.swing.JTextField();
        fontAwesomeIcon = new javaswingdev.FontAwesomeIcon();
        googleMaterialIcon1 = new javaswingdev.GoogleMaterialIcon();
        jPanel2 = new javax.swing.JPanel();
        lineHeader1 = new Dashboar.com.component.lineHeader();
        lbTitle = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbMaNV = new javax.swing.JLabel();
        lbHoVaTen = new javax.swing.JLabel();
        lbSDT = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        lbUsername = new javax.swing.JLabel();
        lbPassword = new javax.swing.JLabel();
        lbDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        txtHoVaTen = new javax.swing.JTextField();
        lbChucVu = new javax.swing.JLabel();
        cboChucVu = new javax.swing.JComboBox<>();
        lbIcon = new javax.swing.JLabel();
        btThem = new Dashboar.com.swing.ButtonCustom();
        btSua = new Dashboar.com.swing.ButtonCustom();
        btLamMoi = new Dashboar.com.swing.ButtonCustom();
        btXoa = new Dashboar.com.swing.ButtonCustom();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbEmp = new javax.swing.JTable();
        txtSearch = new Dashboar.com.component.TextFieldAnimation();
        lbLoad = new javax.swing.JLabel();

        jTextField6.setText("jTextField6");

        fontAwesomeIcon.setColor1(new java.awt.Color(165, 201, 202));
        fontAwesomeIcon.setColor2(new java.awt.Color(231, 230, 226));
        fontAwesomeIcon.setIcon(javaswingdev.FontAwesome.CAR);
        fontAwesomeIcon.setSize(20);

        googleMaterialIcon1.setIcon(javaswingdev.GoogleMaterialDesignIcon.CARD_GIFTCARD);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 1, 1, 1));

        lineHeader1.setPreferredSize(new java.awt.Dimension(57, 68));

        lbTitle.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("QUẢN LÝ NHÂN VIÊN");

        javax.swing.GroupLayout lineHeader1Layout = new javax.swing.GroupLayout(lineHeader1);
        lineHeader1.setLayout(lineHeader1Layout);
        lineHeader1Layout.setHorizontalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeader1Layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        lineHeader1Layout.setVerticalGroup(
            lineHeader1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lineHeader1Layout.createSequentialGroup()
                .addComponent(lbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0};
        jPanel1Layout.rowHeights = new int[] {0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0, 8, 0};
        jPanel1Layout.columnWeights = new double[] {10.0};
        jPanel1.setLayout(jPanel1Layout);

        lbMaNV.setBackground(new java.awt.Color(196, 196, 196));
        lbMaNV.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMaNV.setForeground(new java.awt.Color(0, 0, 0));
        lbMaNV.setText("Mã NV:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbMaNV, gridBagConstraints);

        lbHoVaTen.setBackground(new java.awt.Color(196, 196, 196));
        lbHoVaTen.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbHoVaTen.setForeground(new java.awt.Color(0, 0, 0));
        lbHoVaTen.setText("Họ Và Tên:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbHoVaTen, gridBagConstraints);

        lbSDT.setBackground(new java.awt.Color(196, 196, 196));
        lbSDT.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbSDT.setForeground(new java.awt.Color(0, 0, 0));
        lbSDT.setText("SĐT:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbSDT, gridBagConstraints);

        txtMaNV.setName("Mã Nhân Viên"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        jPanel1.add(txtMaNV, gridBagConstraints);

        txtSDT.setName("Số điện thoại"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        jPanel1.add(txtSDT, gridBagConstraints);

        lbUsername.setBackground(new java.awt.Color(196, 196, 196));
        lbUsername.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbUsername.setForeground(new java.awt.Color(0, 0, 0));
        lbUsername.setText("Username:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 30;
        jPanel1.add(lbUsername, gridBagConstraints);

        lbPassword.setBackground(new java.awt.Color(196, 196, 196));
        lbPassword.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbPassword.setForeground(new java.awt.Color(0, 0, 0));
        lbPassword.setText("Password:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbPassword, gridBagConstraints);

        lbDiaChi.setBackground(new java.awt.Color(196, 196, 196));
        lbDiaChi.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbDiaChi.setForeground(new java.awt.Color(0, 0, 0));
        lbDiaChi.setText("Địa Chỉ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbDiaChi, gridBagConstraints);

        txtDiaChi.setName("Địa chỉ"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel1.add(txtDiaChi, gridBagConstraints);

        txtPassword.setName("Password"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        jPanel1.add(txtPassword, gridBagConstraints);

        txtUsername.setName("Username"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        jPanel1.add(txtUsername, gridBagConstraints);

        txtHoVaTen.setName("Họ và tên"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 112;
        jPanel1.add(txtHoVaTen, gridBagConstraints);

        lbChucVu.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbChucVu.setForeground(new java.awt.Color(0, 0, 0));
        lbChucVu.setText("Chức Vụ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lbChucVu, gridBagConstraints);

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân Viên", "Quản Lý" }));
        cboChucVu.setName("Chức Vụ"); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(cboChucVu, gridBagConstraints);

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

        tbEmp.setAutoCreateRowSorter(true);
        tbEmp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ Và Tên", "Số Điện Thoại", "Địa Chỉ", "Username", "Password"
            }
        ));
        tbEmp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbEmpMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbEmp);

        txtSearch.setEditable(false);
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
                    .addComponent(lineHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 965, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lineHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btThem, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btSua, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
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
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    private void tbEmpMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbEmpMousePressed
        if (evt.getClickCount() == 2) {
            index = tbEmp.rowAtPoint(evt.getPoint());
            edit();
            lbIcon.setText("");
        }
    }//GEN-LAST:event_tbEmpMousePressed

    private void btThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThemActionPerformed
        insert();
    }//GEN-LAST:event_btThemActionPerformed

    private void lbIconMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbIconMousePressed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (ImageHelper.saveImageNhanVien(file)) {
                lbIcon.setText("");
                lbIcon.setIcon(ImageHelper.readImageNV(file.getName()));
                lbIcon.setToolTipText(file.getName());
            }
        }
    }//GEN-LAST:event_lbIconMousePressed

    private void btSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuaActionPerformed
        update();
    }//GEN-LAST:event_btSuaActionPerformed

    private void btXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXoaActionPerformed
        delete();
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
    
    private void showNotification(JFrame fr, Notification.Type type, String text) {
        Notification panel = new Notification(fr, type, text);
        panel.showNotification();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Dashboar.com.swing.ButtonCustom btLamMoi;
    private Dashboar.com.swing.ButtonCustom btSua;
    private Dashboar.com.swing.ButtonCustom btThem;
    private Dashboar.com.swing.ButtonCustom btXoa;
    private javax.swing.JComboBox<String> cboChucVu;
    private javaswingdev.FontAwesomeIcon fontAwesomeIcon;
    private javaswingdev.GoogleMaterialIcon googleMaterialIcon1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lbChucVu;
    private javax.swing.JLabel lbDiaChi;
    private javax.swing.JLabel lbHoVaTen;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbLoad;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbPassword;
    private javax.swing.JLabel lbSDT;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbUsername;
    private Dashboar.com.component.lineHeader lineHeader1;
    private javax.swing.JTable tbEmp;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSDT;
    private Dashboar.com.component.TextFieldAnimation txtSearch;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
