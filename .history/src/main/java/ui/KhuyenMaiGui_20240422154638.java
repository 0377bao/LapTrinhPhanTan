package ui;

import bus.ChuongTrinhKhuyenMaiBus;
import bus.DanhMucBus;
import bus.impl.ChuongTrinhKhuyenMaiBusImpl;
import bus.impl.DanhMucBusImpl;
import bus.impl.KhachHangBusImpl;
import controller.KhuyenMaiController;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import dao.impl.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;
import entity.DanhMuc;
import entity.KhachHang;
import entity.MucKhuyenMai;
import tool.PhanLuong;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

//public class KhuyenMaiGui extends JPanel implements MouseListener{
public class KhuyenMaiGui extends JPanel {
    private final DefaultTableModel modelMucKhuyenMai;
    private final DefaultTableModel modelDSKhuyenMai;
    private final DefaultTableModel modelChiTietKM;
    private final JTextField txtMaCTKM;
    private final JTextField txtPhanTram;
    private final JTextField txtMaCTKMTK;
    private final JTextField txtTenCTKMTK;
    private final JTextField txtTrangThai;
    private final JTextField txtMaDuocChon;
    private final JTextArea txtAreaTenCTKM;
    private final JCheckBox checkBoxChinhSuaMKM;
    private final MyButton btnApDungCT;
    private final MyButton btnLuu;
    private final MyButton btnThem;
    private final MyButton btnTaoMaCTKM;
    private final MyButton btnCapNhat;
    private final MyButton btnXoa;
    private final MyButton btnLuuCapNhat;
    private final MyCombobox cboTrangThai;
    private final MyCombobox cboSanPham;
    private final MyCombobox cboMucKM;
    private final MyCombobox cboTheLoai;
    private final MyTable tbMucKhuyenMai;
    private final MyTable tbDSKhuyenMai;
    private final MyTable tbChiTietKM;
    private int viTriDongDuocChon = -1;
    private String arrayTheLoaiSach[] = new String[] { "Chính trị", "Kinh tế", "Lịch sử", "Giả tưởng", "Giáo khoa",
            "Kinh dị", "Nấu ăn", "Tâm lý", "Tình cảm", "Thiếu nhi", "Truyện", "Văn học nghệ thuật" };
    private String arrayTheLoaiVPP[] = new String[] { "Balo", "Bàn", "Bút", "Thước", "Đèn học", "Vở",
            "Máy tính cầm tay", "Ghế" };
    private final List<MucKhuyenMai> dsMucKhuyenMaiDangTao = new ArrayList<>();
    private final ChuongTrinhKhuyenMaiBus ctkmBus = new ChuongTrinhKhuyenMaiBusImpl();
    private int soLuongMucKhuyenMaiHienTai = 0;
    private List<MucKhuyenMai> dsMucKhuyenMaiDaBiXoa = new ArrayList<>();

    private PopUp thongBao = new PopUp("Đang tiến hành gửi thông báo");

    public KhuyenMaiGui() {
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(250, 0, 1250, 800);
        setLayout(null);

        modelMucKhuyenMai = new DefaultTableModel(new Object[] { "Tên mục khuyến mãi", "Phần trăm" }, 0);
        modelDSKhuyenMai = new DefaultTableModel(new Object[] { "STT", "MaCTKM", "TenCTKM", "Trạng thái" }, 0);
        modelChiTietKM = new DefaultTableModel(new Object[] { "Tên mục khuyến mãi", "Phần trăm" }, 0);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(20, 0, 1245, 789);
        add(panel_2);
        panel_2.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 10, 549, 767);
        panel_2.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(97, 166, 247));
        panel_1.setBounds(60, 11, 433, 40);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel = new JLabel("Chương trình khuyến mãi");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(89, 7, 245, 24);
        panel_1.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Mã CTKM:");
        lblNewLabel_1.setBounds(44, 62, 78, 14);
        panel.add(lblNewLabel_1);

        txtMaCTKM = new JTextField();
        txtMaCTKM.setEditable(false);
        txtMaCTKM.setBounds(153, 62, 315, 20);
        panel.add(txtMaCTKM);
        txtMaCTKM.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Tên CTKM:");
        lblNewLabel_2.setBounds(42, 98, 78, 14);
        panel.add(lblNewLabel_2);

        txtAreaTenCTKM = new JTextArea();
        txtAreaTenCTKM.setBackground(new Color(255, 255, 255));
        txtAreaTenCTKM.setBounds(153, 93, 315, 45);
        txtAreaTenCTKM.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        panel.add(txtAreaTenCTKM);

        btnLuu = new MyButton("Lưu");
        btnLuu.setBackground(new Color(97, 166, 247));
        btnLuu.setBounds(348, 183, 120, 30);
        btnLuu.setIcon(new ImageIcon("src\\image\\khuyenmai\\iconsave.png"));
        btnLuu.setActionCommand("btnLuu");
        panel.add(btnLuu);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_3.setBounds(23, 222, 500, 522);
        panel.add(panel_3);
        panel_3.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("Sản phẩm:");
        lblNewLabel_3.setBounds(34, 56, 63, 14);
        panel_3.add(lblNewLabel_3);

        cboSanPham = new MyCombobox();
        cboSanPham.setBounds(169, 52, 235, 22);
        cboSanPham.addItem("Sách");
        cboSanPham.addItem("Văn phòng phẩm");
        cboSanPham.setActionCommand("cboSanPham");
        panel_3.add(cboSanPham);

        JLabel lblNewLabel_4 = new JLabel("Phần trăm:");
        lblNewLabel_4.setBounds(34, 95, 63, 14);
        panel_3.add(lblNewLabel_4);

        txtPhanTram = new JTextField();
        txtPhanTram.setBounds(169, 92, 235, 20);
        panel_3.add(txtPhanTram);
        txtPhanTram.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel("Thể loại:");
        lblNewLabel_5.setBounds(34, 137, 63, 14);
        panel_3.add(lblNewLabel_5);

        cboTheLoai = new MyCombobox();
        cboTheLoai.setBounds(169, 133, 235, 22);
        cboTheLoai.addItem("Chính trị");
        cboTheLoai.addItem("Giả tưởng");
        cboTheLoai.addItem("Giáo khoa");
        cboTheLoai.addItem("Kinh dị");
        cboTheLoai.addItem("Kinh tế");
        cboTheLoai.addItem("Lịch sử");
        cboTheLoai.addItem("Nấu ăn");
        cboTheLoai.addItem("Tâm lý");
        cboTheLoai.addItem("Tình cảm");
        cboTheLoai.addItem("Thiếu nhi");
        cboTheLoai.addItem("Truyện");
        cboTheLoai.addItem("Văn học nghệ thuật");
        panel_3.add(cboTheLoai);

        btnThem = new MyButton("Thêm");
        btnThem.setBackground(new Color(97, 166, 247));
        btnThem.setBounds(372, 194, 106, 30);
        btnThem.setIcon(new ImageIcon("src\\image\\khuyenmai\\iconplus.png"));
        btnThem.setActionCommand("btnThem");
        panel_3.add(btnThem);

        tbMucKhuyenMai = new MyTable(modelMucKhuyenMai);
        JScrollPane scrollPaneMKM = new JScrollPane(tbMucKhuyenMai, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneMKM.setBounds(24, 255, 452, 246);
        tbMucKhuyenMai.setName("tbMucKhuyenMai");
        panel_3.add(scrollPaneMKM);

        JLabel lblNewLabel_8 = new JLabel("Tạo tên mục khuyến mãi");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_8.setBounds(34, 11, 187, 20);
        panel_3.add(lblNewLabel_8);

        JPanel panel_9 = new JPanel();
        panel_9.setBorder(new TitledBorder(null, "Danh sách mục khuyến mãi đang tạo", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        panel_9.setBounds(10, 234, 480, 278);
        panel_3.add(panel_9);

        btnXoa = new MyButton("Xóa");
        btnXoa.setText("Xóa");
        btnXoa.setBackground(new Color(97, 166, 247));
        btnXoa.setBounds(256, 194, 106, 30);
        btnXoa.setEnabled(false);
        btnXoa.setActionCommand("btnXoa");
        panel_3.add(btnXoa);

        btnCapNhat = new MyButton("Cập nhật");
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setBackground(new Color(97, 166, 247));
        btnCapNhat.setBounds(140, 194, 106, 30);
        btnCapNhat.setEnabled(false);
        btnCapNhat.setActionCommand("btnCapNhat");
        panel_3.add(btnCapNhat);

        btnLuuCapNhat = new MyButton("Cập nhật");
        btnLuuCapNhat.setText("Lưu cập nhật");
        btnLuuCapNhat.setBackground(new Color(97, 166, 247));
        btnLuuCapNhat.setBounds(25, 194, 106, 30);
        btnLuuCapNhat.setEnabled(false);
        btnLuuCapNhat.setActionCommand("btnLuuCapNhat");
        panel_3.add(btnLuuCapNhat);

        JLabel lblNewLabel_13 = new JLabel("Trạng thái:");
        lblNewLabel_13.setBounds(42, 157, 78, 14);
        panel.add(lblNewLabel_13);

        txtTrangThai = new JTextField();
        txtTrangThai.setEditable(false);
        txtTrangThai.setText("Không áp dụng");
        txtTrangThai.setBounds(153, 153, 315, 20);
        panel.add(txtTrangThai);
        txtTrangThai.setColumns(10);

        btnTaoMaCTKM = new MyButton("Lưu");
        btnTaoMaCTKM.setText("Tạo mã CTKM");
        btnTaoMaCTKM.setBackground(new Color(97, 166, 247));
        btnTaoMaCTKM.setBounds(203, 183, 120, 30);
        btnTaoMaCTKM.setActionCommand("btnTaoMa");
        panel.add(btnTaoMaCTKM);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_4.setBounds(580, 11, 655, 162);
        panel_2.add(panel_4);
        panel_4.setLayout(null);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(new Color(97, 166, 247));
        panel_5.setBounds(22, 11, 148, 40);
        panel_4.add(panel_5);
        panel_5.setLayout(null);

        JLabel lblNewLabel_7 = new JLabel("Tìm kiếm");
        lblNewLabel_7.setForeground(new Color(255, 255, 255));
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(37, 7, 73, 24);
        panel_5.add(lblNewLabel_7);

        JPanel panel_7 = new JPanel();
        panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel_7.setBounds(24, 83, 608, 60);
        panel_4.add(panel_7);
        panel_7.setLayout(null);

        JLabel lblNewLabel_9 = new JLabel("Mã CTKM:");
        lblNewLabel_9.setBounds(29, 24, 78, 14);
        panel_7.add(lblNewLabel_9);

        txtMaCTKMTK = new JTextField();
        txtMaCTKMTK.setBounds(97, 20, 188, 20);
        txtMaCTKMTK.setName("maCT");
        panel_7.add(txtMaCTKMTK);
        txtMaCTKMTK.setColumns(10);

        JLabel lblNewLabel_9_1 = new JLabel("Tên CTKM:");
        lblNewLabel_9_1.setBounds(337, 24, 78, 14);
        panel_7.add(lblNewLabel_9_1);

        txtTenCTKMTK = new JTextField();
        txtTenCTKMTK.setName("tenCT");
        txtTenCTKMTK.setColumns(10);
        txtTenCTKMTK.setBounds(410, 21, 188, 20);
        panel_7.add(txtTenCTKMTK);

        JPanel panel_6 = new JPanel();
        panel_6.setBackground(new Color(255, 255, 255));
        panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_6.setBounds(580, 184, 655, 594);
        panel_2.add(panel_6);
        panel_6.setLayout(null);

        tbDSKhuyenMai = new MyTable(modelDSKhuyenMai);
        JScrollPane scrollPaneDSKM = new JScrollPane(tbDSKhuyenMai, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneDSKM.setBounds(10, 144, 635, 196);
        tbDSKhuyenMai.setName("tbDSKhuyenMai");
        panel_6.add(scrollPaneDSKM);

        JLabel lblNewLabel_10 = new JLabel("Danh sách chương trình khuyến mãi");
        lblNewLabel_10.setBounds(10, 119, 237, 14);
        panel_6.add(lblNewLabel_10);

        tbChiTietKM = new MyTable(modelChiTietKM);
        JScrollPane scrollPaneCTKM = new JScrollPane(tbChiTietKM, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneCTKM.setBounds(10, 418, 635, 165);
        panel_6.add(scrollPaneCTKM);

        JPanel panel_8 = new JPanel();
        panel_8.setBorder(new TitledBorder(null, "Chức năng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_8.setBounds(10, 11, 635, 97);
        panel_6.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblNewLabel_11 = new JLabel("Lọc theo tên mục khuyến mãi");
        lblNewLabel_11.setBounds(10, 26, 171, 14);
        panel_8.add(lblNewLabel_11);

        cboMucKM = new MyCombobox();
        cboMucKM.setActionCommand("cboMucKM");
        cboMucKM.setBounds(175, 22, 156, 22);
        cboMucKM.addItem("Tất cả");
        cboMucKM.addItem("Balo");
        cboMucKM.addItem("Bàn");
        cboMucKM.addItem("Bút");
        cboMucKM.addItem("Thước");
        cboMucKM.addItem("Chính trị");
        cboMucKM.addItem("Đèn học");
        cboMucKM.addItem("Ghế");
        cboMucKM.addItem("Giả tưởng");
        cboMucKM.addItem("Giáo khoa");
        cboMucKM.addItem("Kinh dị");
        cboMucKM.addItem("Kinh tế");
        cboMucKM.addItem("Lịch sử");
        cboMucKM.addItem("Máy tính cầm tay");
        cboMucKM.addItem("Nấu ăn");
        cboMucKM.addItem("Tâm lý");
        cboMucKM.addItem("Tình cảm");
        cboMucKM.addItem("Thiếu nhi");
        cboMucKM.addItem("Truyện");
        cboMucKM.addItem("Văn học nghệ thuật");
        cboMucKM.addItem("Vở");

        panel_8.add(cboMucKM);

        JLabel lblNewLabel_12 = new JLabel("Lọc theo trạng thái");
        lblNewLabel_12.setBounds(10, 68, 125, 14);
        panel_8.add(lblNewLabel_12);

        cboTrangThai = new MyCombobox();
        cboTrangThai.setBounds(175, 64, 156, 22);
        cboTrangThai.addItem("Tất cả");
        cboTrangThai.addItem("Đang áp dụng");
        cboTrangThai.addItem("Không áp dụng");
        cboTrangThai.setActionCommand("cboTrangThai");

        panel_8.add(cboTrangThai);

        btnApDungCT = new MyButton("Áp dụng chương trình khuyến mãi");
        btnApDungCT.setBackground(new Color(0, 255, 0));
        btnApDungCT.setBounds(356, 18, 269, 30);
        btnApDungCT.setActionCommand("btnApDungCT");
        panel_8.add(btnApDungCT);

        JLabel lblNewLabel_14 = new JLabel("Mã CTKM được chọn");
        lblNewLabel_14.setBounds(356, 69, 134, 13);
        panel_8.add(lblNewLabel_14);

        txtMaDuocChon = new JTextField();
        txtMaDuocChon.setFont(new Font("Tahoma", Font.BOLD, 10));
        txtMaDuocChon.setEnabled(false);
        txtMaDuocChon.setBounds(500, 66, 125, 20);
        panel_8.add(txtMaDuocChon);
        txtMaDuocChon.setColumns(10);

        JButton btnNewButton = new JButton("");
        btnNewButton.setEnabled(false);
        btnNewButton.setBounds(311, 349, 30, 30);
        btnNewButton.setIcon(new ImageIcon("src\\image\\khuyenmai\\iconarrowdown.png"));
        panel_6.add(btnNewButton);

        JLabel lblNewLabel_6 = new JLabel("Chi tiết chương trình khuyến mãi");
        lblNewLabel_6.setBounds(10, 395, 237, 13);
        panel_6.add(lblNewLabel_6);

        checkBoxChinhSuaMKM = new JCheckBox("Chỉnh sửa mục khuyến mãi");
        checkBoxChinhSuaMKM.setBackground(new Color(255, 255, 255));
        checkBoxChinhSuaMKM.setBounds(446, 116, 199, 21);
        checkBoxChinhSuaMKM.setFocusable(false);
        checkBoxChinhSuaMKM.setActionCommand("checkBoxChinhSuaMKM");
        panel_6.add(checkBoxChinhSuaMKM);

        loadDSKhuyenMai();

        MouseListener mouse = new KhuyenMaiController(this);
        tbDSKhuyenMai.addMouseListener(mouse);
        tbMucKhuyenMai.addMouseListener(mouse);

        ActionListener ac = new KhuyenMaiController(this);
        cboTrangThai.addActionListener(ac);
        cboMucKM.addActionListener(ac);
        btnApDungCT.addActionListener(ac);
        cboSanPham.addActionListener(ac);
        btnTaoMaCTKM.addActionListener(ac);
        btnThem.addActionListener(ac);
        btnLuu.addActionListener(ac);
        btnXoa.addActionListener(ac);
        btnCapNhat.addActionListener(ac);
        btnLuuCapNhat.addActionListener(ac);
        checkBoxChinhSuaMKM.addActionListener(ac);

        KeyListener key = new KhuyenMaiController(this);
        txtMaCTKMTK.addKeyListener(key);
        txtTenCTKMTK.addKeyListener(key);

    }

    /* load danh sách ctkm vào bảng danh sách ctkm */
    public void loadDSKhuyenMai() {
        modelDSKhuyenMai.setRowCount(0);
        int stt = 0;
        List<ChuongTrinhKhuyenMai> ds = ctkmBus.layDSChuongTrinhKhuyenMai();
        for (ChuongTrinhKhuyenMai ctkm : ds) {
            modelDSKhuyenMai.addRow(new Object[] { ++stt, ctkm.getMaCTKM(), ctkm.getTenCTKM(),
                    ctkm.isTrangThai() ? "Đang áp dụng" : "Không áp dụng" });
        }
    }

    /* load danh sách mục khuyến mãi của 1 ctkm vào bảng chi tiết ctkm */
    public void loadChiTietChuongTrinhKhuyenMai() {
        modelChiTietKM.setRowCount(0);
        int index = tbDSKhuyenMai.getSelectedRow() != -1 ? tbDSKhuyenMai.getSelectedRow() : viTriDongDuocChon;
        viTriDongDuocChon = index;
        String ma = modelDSKhuyenMai.getValueAt(index, 1).toString();
        txtMaDuocChon.setText(ma);
        ChuongTrinhKhuyenMai ctkm = ctkmBus.timChuongTrinhKhuyenMaiTheoMa(ma);
        ctkm.setDsMucKhuyenMai(ctkmBus.layDSMucKhuyenMaiCuaCTKM(ma));

        for (MucKhuyenMai mkm : ctkm.getDsMucKhuyenMai()) {
            modelChiTietKM.addRow(new Object[] { mkm.getTenMucKhuyenMai(), mkm.getTiLeKhuyenMai() });
        }
        cboMucKM.setSelectedIndex(0);
    }

    /*
     * load danh sách mục khuyến mãi của 1 ctkm cần chỉnh sửa vào bảng ds mục khuyến
     * mãi đang tạo
     */
    public void loadChuongTrinhKhuyenMaiCanChinhSua() {
        if (checkBoxChinhSuaMKM.isSelected()) {
            modelChiTietKM.setRowCount(0);
            int viTri = tbDSKhuyenMai.getSelectedRow();
            txtMaCTKM.setText(modelDSKhuyenMai.getValueAt(viTri, 1).toString());
            txtAreaTenCTKM.setText(modelDSKhuyenMai.getValueAt(viTri, 2).toString());
            txtTrangThai.setText(modelDSKhuyenMai.getValueAt(viTri, 3).toString());
            txtAreaTenCTKM.setEditable(false);
            txtAreaTenCTKM.setEnabled(false);
            List<MucKhuyenMai> ds = ctkmBus
                    .timChuongTrinhKhuyenMaiTheoMa(modelDSKhuyenMai.getValueAt(viTri, 1).toString())
                    .getDsMucKhuyenMai();
            dsMucKhuyenMaiDangTao.clear();
            dsMucKhuyenMaiDangTao.addAll(ds);
            soLuongMucKhuyenMaiHienTai = dsMucKhuyenMaiDangTao.size();
            modelMucKhuyenMai.setRowCount(0);
            for (MucKhuyenMai mkm : dsMucKhuyenMaiDangTao) {
                modelMucKhuyenMai.addRow(new Object[] { mkm.getTenMucKhuyenMai(), mkm.getTiLeKhuyenMai() });
            }
        } else {
            soLuongMucKhuyenMaiHienTai = 0;
            modelMucKhuyenMai.setRowCount(0);
            txtMaCTKM.setText("");
            txtAreaTenCTKM.setText("");
            txtTrangThai.setText("Không áp dụng");
            txtAreaTenCTKM.setEditable(true);
            dsMucKhuyenMaiDangTao.clear();
            loadChiTietChuongTrinhKhuyenMai();
        }

    }

    public void loadChiTietMucKhuyenMai() {
        int index = tbMucKhuyenMai.getSelectedRow();
        if (index != -1) {
            txtPhanTram.setText(modelMucKhuyenMai.getValueAt(index, 1).toString());
            cboTheLoai.setSelectedItem(modelMucKhuyenMai.getValueAt(index, 0).toString());
        }
    }

    /* lọc ctkm theo trạng thái */
    public void xuLySuKienCboTrangThai() {
        txtMaDuocChon.setText("");
        viTriDongDuocChon = -1;
        int stt = 0;
        int index = cboTrangThai.getSelectedIndex();
        List<ChuongTrinhKhuyenMai> ds = ctkmBus.layDSChuongTrinhKhuyenMai();
        modelDSKhuyenMai.setRowCount(0);
        modelChiTietKM.setRowCount(0);
        if (index == 0) {
            for (ChuongTrinhKhuyenMai ctkm : ds) {
                modelDSKhuyenMai.addRow(new Object[] { ++stt, ctkm.getMaCTKM(), ctkm.getTenCTKM(),
                        ctkm.isTrangThai() ? "Đang áp dụng" : "Không áp dụng" });
            }
            xoaTrangToanBo();
        }
        if (index == 1) {
            for (ChuongTrinhKhuyenMai ctkm : ds) {
                if (ctkm.isTrangThai()) {
                    modelDSKhuyenMai
                            .addRow(new Object[] { ++stt, ctkm.getMaCTKM(), ctkm.getTenCTKM(), "Đang áp dụng" });
                }
            }
            xoaTrangToanBo();
        }
        if (index == 2) {
            for (ChuongTrinhKhuyenMai ctkm : ds) {
                if (!ctkm.isTrangThai()) {
                    modelDSKhuyenMai
                            .addRow(new Object[] { ++stt, ctkm.getMaCTKM(), ctkm.getTenCTKM(), "Không áp dụng" });
                }
            }
            xoaTrangToanBo();
        }
    }

    public void taoMa() {
        txtMaCTKM.setText(ctkmBus.taoMa());
    }

    /* Thêm mục khuyến mãi vào bảng ds mục khuyến mãi */
    public void themMucKhuyenMai() {
        if (txtPhanTram.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập phần trăm giảm giá cho sản phẩm");
        } else {
            float phanTram = 0;
            try {
                phanTram = Float.parseFloat(txtPhanTram.getText());
                if (phanTram < 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm phải là 1 số dương");
                    return;
                }
                if (phanTram > 100) {
                    JOptionPane.showMessageDialog(this, "Phần trăm không được vượt quá 100");
                    return;
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Phần trăm phải là kí tự số");
                return;
            }

            String theLoai = cboTheLoai.getSelectedItem().toString();
            MucKhuyenMai m = new MucKhuyenMai(theLoai, phanTram);

            // kiểm tra mục khuyến mãi đã tồn tại hay chưa
            if (kiemTraMucKhuyenMai(m)) {
                m.setChuongTrinhKhuyenMai(ctkmBus.timChuongTrinhKhuyenMaiTheoMa(txtMaCTKM.getText()));
                dsMucKhuyenMaiDangTao.add(m);
            } else {
                JOptionPane.showMessageDialog(this, "Mục khuyến mãi đã tồn tại");
                return;
            }
            modelMucKhuyenMai.setRowCount(0);
            for (MucKhuyenMai mkm : dsMucKhuyenMaiDangTao) {
                modelMucKhuyenMai.addRow(new Object[] { mkm.getTenMucKhuyenMai(), mkm.getTiLeKhuyenMai() });
            }
        }
        txtPhanTram.setText("");
    }

    // chuyển đổi thể loại sách sang văn phòng phẩm và ngược lại
    public void chuyenDoiSanPham() {
        if (cboSanPham.getSelectedIndex() == 0) {
            cboTheLoai.removeAllItems();
            cboTheLoai.addItem("Chính trị");
            cboTheLoai.addItem("Giả tưởng");
            cboTheLoai.addItem("Giáo khoa");
            cboTheLoai.addItem("Kinh dị");
            cboTheLoai.addItem("Kinh tế");
            cboTheLoai.addItem("Lịch sử");
            cboTheLoai.addItem("Nấu ăn");
            cboTheLoai.addItem("Tâm lý");
            cboTheLoai.addItem("Tình cảm");
            cboTheLoai.addItem("Thiếu nhi");
            cboTheLoai.addItem("Truyện");
            cboTheLoai.addItem("Văn học nghệ thuật");
        } else {
            cboTheLoai.removeAllItems();
            DanhMucBus danhMucBus = new DanhMucBusImpl();
            List<DanhMuc> dsDanhMuc = danhMucBus.layDSDanhMuc();
            for (DanhMuc dm : dsDanhMuc) {
                cboTheLoai.addItem(dm.getTenDanhMuc());
            }
        }
    }

    // kiểm tra mục khuyến mãi đã tồn tại hay chưa
    public boolean kiemTraMucKhuyenMai(MucKhuyenMai m) {
        for (MucKhuyenMai mk : dsMucKhuyenMaiDangTao) {
            if (mk.getTenMucKhuyenMai().trim().equals(m.getTenMucKhuyenMai().trim())) {
                return false;
            }
        }
        return true;
    }

    public void themCTKM() {
        String maCTKM = txtMaCTKM.getText();
        String tenCTKM = txtAreaTenCTKM.getText();
        boolean trangThai = !txtTrangThai.getText().trim().equals("Không áp dụng");
        ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai(maCTKM, tenCTKM, dsMucKhuyenMaiDangTao, trangThai);
        String listItem = loadMucKhuyenMai(ctkm);
        String gmail = layGmailKhachHang();
        if (ctkmBus.validateChuongTrinhKhuyenMai(ctkm)) {
            if (ctkmBus.themChuongTrinhKhuyenMai(ctkm)) {
                JOptionPane.showMessageDialog(this, "Thêm thành công chương trình khuyến mãi");
                dsMucKhuyenMaiDangTao.clear();
                modelDSKhuyenMai.setRowCount(0);
                loadDSKhuyenMai();

                // loadDSKhuyenMai();
                // capNhatTrangThaiBangDSKhuyenMai();
                modelMucKhuyenMai.setRowCount(0);
                xoaTrangTextField();
                btnXoa.setEnabled(false);
                btnCapNhat.setEnabled(false);

                // gửi chương trình khuyến mãi cho tất cả khách hàng
                if (JOptionPane.showConfirmDialog(this,
                        "Bạn có muốn gửi chương trình khuyến mãi này cho khách hàng không?", "Có",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    // String userMail = "thuykieu.13032003@gmail.com";
                    // String password = "tirfdrdpsbjxqemq";
                    String subject = "Chương trình khuyến mãi tri ân khách hàng của hiệu sách HBDK";
                    String message = "Chào quý khách hàng thân mến, những người đã và đang mua hàng tại hiệu sách chúng tôi.\n"
                            + "Nhân dịp " + "'" + ctkm.getTenCTKM() + "'"
                            + " hiệu sách của chúng tôi sẽ áp dụng ưu đãi cho những sản phẩm sau:\n"
                            + listItem + "\n" + "Cảm ơn bạn đã ủng hộ hiệu sách của chúng tôi!\n" + "Trân trọng";

                    PhanLuong pl = new PhanLuong(thongBao, gmail.trim(), subject, message,
                            "Gửi chương trình khuyến mãi thành công");
                    pl.execute();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Thêm chương trình khuyến mãi thất bại do trùng mã CTKM");
            }
        } else {
            JOptionPane.showMessageDialog(this, ctkmBus.getMessage());
        }
    }

    // load mục khuyến mãi
    public String loadMucKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        String message = "";
        for (MucKhuyenMai c : ctkm.getDsMucKhuyenMai()) {
            message += "+ " + c.getTenMucKhuyenMai() + " : " + c.getTiLeKhuyenMai() + "%" + "\n";
        }
        return message;
    }

    public void capNhatTrangThaiBangDSKhuyenMai() {
        modelDSKhuyenMai.setRowCount(0);
        loadDSKhuyenMai();
    }

    public void capNhatMucKhuyenMai() {
        int index = tbMucKhuyenMai.getSelectedRow();
        float phanTram = -1;
        String tenMucKhuyenMai = "";
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mục khuyến mãi cần cập nhật");
            return;
        } else {
            try {
                phanTram = Float.parseFloat(txtPhanTram.getText());
                if (phanTram <= 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm không được <= 0");
                    return;
                }
                if (phanTram > 100) {
                    JOptionPane.showMessageDialog(this, "Phần trăm không được vượt quá 100");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Phần trăm không hợp lệ");
                return;
            }
        }
        modelMucKhuyenMai.setValueAt(phanTram, index, 1);
        if (!checkBoxChinhSuaMKM.isSelected()) {
            tenMucKhuyenMai = cboTheLoai.getSelectedItem().toString();
            modelMucKhuyenMai.setValueAt(tenMucKhuyenMai, index, 0);
        }
        MucKhuyenMai m = new MucKhuyenMai(
                tenMucKhuyenMai.equals(" ") ? modelMucKhuyenMai.getValueAt(index, 0).toString() : tenMucKhuyenMai,
                phanTram);
        dsMucKhuyenMaiDangTao.set(index, m);
        if (checkBoxChinhSuaMKM.isSelected()) {
            txtAreaTenCTKM.setEditable(false);
        } else {
            txtAreaTenCTKM.setEditable(true);
        }

        txtPhanTram.setText("");
        tbMucKhuyenMai.clearSelection();
        cboSanPham.setEnabled(true);
        cboTheLoai.setEnabled(true);
    }

    public void xoaMucKhuyenMai() {
        int index = tbMucKhuyenMai.getSelectedRow();
        System.out.println("vi tri " + index);
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mục khuyến mãi cần xóa");
        } else {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa mục khuyến mãi này không", "Yes",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                modelMucKhuyenMai.removeRow(index);
                dsMucKhuyenMaiDaBiXoa.add(dsMucKhuyenMaiDangTao.get(index));
                System.out.println(dsMucKhuyenMaiDaBiXoa.get(0));
                dsMucKhuyenMaiDangTao.remove(index);
                cboSanPham.setEnabled(true);
                cboTheLoai.setEnabled(true);
                txtPhanTram.setText("");
            }
        }
    }

    public void luuCapNhat() {
        boolean flag = false;
        String mes = "";
        ChuongTrinhKhuyenMai ctkm_ht = new ChuongTrinhKhuyenMaiBusImpl()
                .timChuongTrinhKhuyenMaiTheoMa(txtMaCTKM.getText());
        // xu ly cap nhat muc khuyen mai cua chuong trinh khuyen mai
        System.out.println(soLuongMucKhuyenMaiHienTai);
        System.out.println("-------------------");
        System.out.println(dsMucKhuyenMaiDangTao.size());
        if (soLuongMucKhuyenMaiHienTai < dsMucKhuyenMaiDangTao.size()) {
            int dem = soLuongMucKhuyenMaiHienTai;
            for (int i = dem; i < dsMucKhuyenMaiDangTao.size(); i++) {
                flag = new ChuongTrinhKhuyenMaiBusImpl().themMucKhuyenMaiVaoCTKM(ctkm_ht.getMaCTKM(),
                        dsMucKhuyenMaiDangTao.get(i));
                System.out.println(i);
            }
        } else if (soLuongMucKhuyenMaiHienTai == dsMucKhuyenMaiDangTao.size()) {
            for (MucKhuyenMai m : dsMucKhuyenMaiDangTao) {
                try {
                    flag = new ChuongTrinhKhuyenMaiBusImpl().capNhatMucKhuyenMaiCuaCTKM(ctkm_ht.getMaCTKM(), m);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } else if (soLuongMucKhuyenMaiHienTai > dsMucKhuyenMaiDangTao.size()) {

            for (int i = 0; i < dsMucKhuyenMaiDaBiXoa.size(); i++) {
                try {
                    flag = new ChuongTrinhKhuyenMaiBusImpl().xoaMucKhuyenMaiCuaCTKM(ctkm_ht.getMaCTKM(),
                            dsMucKhuyenMaiDaBiXoa.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (flag == true) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công mục khuyến mãi của chương trình này");
            xoaTrangToanBo();
            btnCapNhat.setEnabled(false);
            dsMucKhuyenMaiDangTao.clear();
            checkBoxChinhSuaMKM.setSelected(false);
        } else {
            // JOptionPane.showMessageDialog(this, "Cập nhật không thành công mục khuyến mãi
            // vì trùng khóa");
            JOptionPane.showMessageDialog(this, mes);
            return;
        }
    }

    public void xuLySuKienCheckBoxChinhSuaMKM() {
        if (checkBoxChinhSuaMKM.isSelected()) {
            if (tbDSKhuyenMai.getSelectedRow() != -1) {
                loadChuongTrinhKhuyenMaiCanChinhSua();
                btnCapNhat.setEnabled(true);
                btnLuuCapNhat.setEnabled(true);
                btnXoa.setEnabled(true);
                btnLuu.setEnabled(false);
                btnTaoMaCTKM.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng chọn chương trình khuyến mãi có mục khuyến mãi cần chỉnh sửa");
                checkBoxChinhSuaMKM.setSelected(false);
            }
        } else {
            loadChuongTrinhKhuyenMaiCanChinhSua();
            btnLuuCapNhat.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            cboSanPham.setEnabled(true);
            cboTheLoai.setEnabled(true);
            txtPhanTram.setText("");
            btnLuu.setEnabled(true);
            btnTaoMaCTKM.setEnabled(true);
        }
    }

    public void apDungCTKM() {
        String ma = modelDSKhuyenMai.getValueAt(viTriDongDuocChon, 1).toString();
        // chuyển trạng thái cái sản phẩm về false hết
        List<ChuongTrinhKhuyenMai> ds = ctkmBus.layDSChuongTrinhKhuyenMai();
        for (ChuongTrinhKhuyenMai ctkm : ds) {
            ctkmBus.capNhatTrangThaiChuongTrinhKhuyenMai(ctkm, false);
        }

        // cập nhật chương trình khuyến mãi chuẩn bị áp dụng
        ChuongTrinhKhuyenMai temp = ctkmBus.timChuongTrinhKhuyenMaiTheoMa(ma);
        temp.setTrangThai(true);
        ctkmBus.capNhatTrangThaiChuongTrinhKhuyenMai(temp, true);
        ;

        // load lại dữ liệu
        // modelDSKhuyenMai.setRowCount(0);
        loadDSKhuyenMai();
        tbDSKhuyenMai.getSelectionModel().setSelectionInterval(viTriDongDuocChon, viTriDongDuocChon);

    }

    public void locTheoTenMucKhuyenMai(String ten, List<MucKhuyenMai> ds, DefaultTableModel model) {
        model.setRowCount(0);
        for (MucKhuyenMai mkm : ds) {
            if (mkm.getTenMucKhuyenMai().equals(ten)) {
                model.addRow(new Object[] { mkm.getTenMucKhuyenMai(), mkm.getTiLeKhuyenMai() });
            }
        }
    }

    // lấy gmail khách hàng
    public String layGmailKhachHang() {
        String mail = "";
        List<KhachHang> dsKh = new KhachHangBusImpl().layDSKhachHang();
        for (KhachHang kh : dsKh) {
            if (dsKh.indexOf(kh) == 0) {
                continue;
            }
            if (dsKh.indexOf(kh) == dsKh.size() - 1) {
                mail += kh.getEmail();
            } else {
                mail += kh.getEmail() + ",";
            }
        }
        return mail;
    }

    public void xuLySuKienCboMucKM(Object o) {
        if (viTriDongDuocChon == -1 && o.equals(cboMucKM)) {
            JOptionPane.showMessageDialog(this, "Chỉ lọc khi đã chọn chương trình khuyến mãi");
            cboMucKM.setSelectedIndex(0);
        } else if (viTriDongDuocChon != -1 && o.equals(cboMucKM)) {
            String ma = modelDSKhuyenMai.getValueAt(viTriDongDuocChon, 1).toString();
            List<MucKhuyenMai> ds = new ChuongTrinhKhuyenMaiBusImpl().layDSMucKhuyenMaiCuaCTKM(ma);
            String index = cboMucKM.getSelectedItem().toString();
            switch (index) {
                case "Balo": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Bàn": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Bút": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Thước": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Chính trị": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Đèn học": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Ghế": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Giả tưởng": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Giáo khoa": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Kinh dị": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Kinh tế": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Lịch sử": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Máy tính cầm tay": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Nấu ăn": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Tâm lý": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Tình cảm": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Thiếu nhi": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Truyện": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Văn học nghệ thuật": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Vở": {
                    locTheoTenMucKhuyenMai(index, ds, modelChiTietKM);
                    break;
                }
                case "Tất cả": {
                    loadChiTietChuongTrinhKhuyenMai();
                    break;
                }

            }
        }

    }

    public void xoaTrangToanBo() {
        xoaTrangTextField();
        modelMucKhuyenMai.setRowCount(0);
        cboSanPham.setEnabled(true);
        cboTheLoai.setEnabled(true);
        txtAreaTenCTKM.setEditable(true);
        tbDSKhuyenMai.clearSelection();
        txtPhanTram.setText("");
        modelChiTietKM.setRowCount(0);
        btnLuuCapNhat.setEnabled(false);
        btnLuu.setEnabled(true);
        btnTaoMaCTKM.setEnabled(true);
        txtMaDuocChon.setText("");
        txtTrangThai.setText("Không áp dụng");
        viTriDongDuocChon = -1;
    }

    public void xoaTrangTextField() {
        txtMaCTKM.setText("");
        txtAreaTenCTKM.setText("");

    }

    public void xuLyTimKiemTheoMaCTKM(List<ChuongTrinhKhuyenMai> ds) {
        String text = txtMaCTKMTK.getText().trim();
        List<ChuongTrinhKhuyenMai> temp = new ArrayList<>();
        Pattern maPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        for (ChuongTrinhKhuyenMai ctkm : ds) {
            if (maPattern.matcher(ctkm.getMaCTKM()).find()) {
                temp.add(ctkm);
            }
        }
        ds.clear();
        ds.addAll(temp);
    }

    public void xuLyTimKiemTheoTextField(List<ChuongTrinhKhuyenMai> ds) {
        xuLyTimKiemTheoMaCTKM(ds);
        xuLyTimKiemTheoTenCTKM(ds);
        modelDSKhuyenMai.setRowCount(0);
        loadDSKhuyenMaiTheoTimKiem(ds);

    }

    public void xuLyTimKiemTheoTenCTKM(List<ChuongTrinhKhuyenMai> ds) {
        String text = txtTenCTKMTK.getText();
        List<ChuongTrinhKhuyenMai> temp = new ArrayList<>();
        Pattern maPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
        for (ChuongTrinhKhuyenMai ctkm : ds) {
            if (maPattern.matcher(ctkm.getTenCTKM()).find()) {
                temp.add(ctkm);
            }
        }
        ds.clear();
        ds.addAll(temp);
    }

    public void loadDSKhuyenMaiTheoTimKiem(List<ChuongTrinhKhuyenMai> ds) {
        int stt = 0;
        for (ChuongTrinhKhuyenMai ctkm : ds) {
            modelDSKhuyenMai.addRow(new Object[] { ++stt, ctkm.getMaCTKM(), ctkm.getTenCTKM(),
                    ctkm.isTrangThai() ? "Đang áp dụng" : "Không áp dụng" });
        }
    }

    public String kiemTraTheLoaiThuocSanPham(String m) {
        for (String SanPham : arrayTheLoaiSach) {
            if (SanPham.equals(m)) {
                return "Sản phẩm";
            }
        }

        for (String Vpp : arrayTheLoaiVPP) {
            if (Vpp.equals(m)) {
                return "Văn phòng phẩm";
            }
        }
        return null;
    }

    public String catChuoiPhanTram(String chuoi) {
        if (chuoi.length() == 3) {
            return chuoi.substring(0, 1);
        }
        if (chuoi.length() == 4) {
            return chuoi.substring(0, 2);
        }
        if (chuoi.length() == 5) {
            return chuoi.substring(0, 3);
        }
        return null;
    }

    public void loadComboboxTheoMucKhuyenMai() {
        int row = tbMucKhuyenMai.getSelectedRow();
        String theLoai = modelMucKhuyenMai.getValueAt(row, 0).toString();
        String sanPham = kiemTraTheLoaiThuocSanPham(theLoai);
        if (sanPham.equals("Sản phẩm")) {
            cboSanPham.setSelectedIndex(0);
            cboTheLoai.setSelectedItem(theLoai);
        } else {
            cboSanPham.setSelectedIndex(1);
            cboTheLoai.setSelectedItem(theLoai);
        }
        String phanTramTemp = catChuoiPhanTram(modelMucKhuyenMai.getValueAt(row, 1).toString());
        txtPhanTram.setText(phanTramTemp);
        if (tbDSKhuyenMai.getSelectedRow() != -1) {
            cboSanPham.setEnabled(false);
            cboTheLoai.setEnabled(false);
        } else {
            cboSanPham.setEnabled(true);
            cboTheLoai.setEnabled(true);
            btnXoa.setEnabled(true);
            btnCapNhat.setEnabled(true);
        }

    }

}