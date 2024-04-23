package ui;


import bus.impl.DanhMucBusImpl;
import bus.impl.NhaCungCapBusImpl;
import bus.impl.SanPhamBusImpl;
import controller.SanPhamController;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.*;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

public class SanPhamGui extends JPanel {
    private JTextField txtTheLoaiSach;
    private JTextField txtMaVPP;
    private JTextField txtMaSach;
    private JTextField txtTenVPP;
    private JTextField txtTenSach;
    private JTextField txtKeSach;
    private JTextField txtKeVPP;
    private JTextField txtSoLuongVPP;
    private JTextField txtSoLuongSach;
    private JTextField txtGiaNhapVPP;
    private JTextField txtGiaNhapSach;
    private JTextField txtThueVPP;
    private JTextField txtThueSach;
    private JTextField txtLoiNhuanVPP;
    private JTextField txtLoiNhuanSach;
    private JTextField txtTacGia;
    private JTextField txtTheLoai;
    private JTextField txtNhaXB;
    private JTextField txtChatLieu;
    private JTextField txtNamXB;
    private JLabel lblHinhAnhSach;
    private JLabel lblHinhAnhVPP;
    private JButton btnHinhAnhVPP;
    private JButton btnHinhAnhSach;
    private JButton btnTaoMaVPP;
    private JButton btnTaoMaSach;
    private JTextField txtTimKiemVPPTheoMa;
    private JTextField txtTimKiemSachTheoMa;
    private JTextField txtLocTheLoai;
    private JTextField txtLocTheLoaiS;
    private JTextField txtLocNamXB;
    private JTextField txtLocTacGia;
    private JTextField txtLocTenVPP;
    private JComboBox<String> cboLocNCC_VPP;
    private JComboBox<String> cboLocNCC_Sach;
    private MyCombobox cboDanhMuc;
    private JComboBox<String> cboLocDanhMuc;
    private JButton btnThemSach;
    private JButton btnCapNhatSach;
    private JButton btnXoaTrangSach;
    private JButton btnTaiLaiSach;
    private JButton btnThemVPP;
    private JButton btnCapNhatVPP;
    private JButton btnXoaTrangVPP;
    private JButton btnTaiLaiVPP;
    private JButton btnTimMaSach;
    private JButton btnTimMaVPP;
    private JComboBox<String> cboLocTrangThaiSach;
    private JComboBox<String> cboLocTrangThaiVPP;
    private JCheckBox chkTrangThaiSach;
    private JCheckBox chkTrangThaiVPP;
    private DefaultTableModel modelVPP;
    private DefaultTableModel modelSach;
    private MyTable tableVPP;
    private MyTable tableSach;
    private String anh;
    private JTextField txtLocTenSach;
    private JLabel lblLocTen;
    private JTextField txtMaNCCSach;
    private JTextField txtTenNCCSach;
    private MyButton btnTimMaNCCSach;
    private MyButton btnThemDanhMuc;
    private JTextField txtTenNCCVPP;
    private JTextField txtMaNCCVPP;
    private MyButton btnTimMaNCCVPP;
    private JCheckBox chkSoLuongSach;
    private JCheckBox chkSoLuongVPP;

    private SanPhamBusImpl busSP = new SanPhamBusImpl();
    private NhaCungCapBusImpl busNCC = new NhaCungCapBusImpl();
    private DanhMucBusImpl busDanhMuc = new DanhMucBusImpl();

    public SanPhamGui() throws RemoteException {
        this.setBackground(new Color(255, 255, 255));
        this.setBounds(250, 0, 1285, 800);
        this.setLayout(null);

        // tạo tab
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(new Color(255, 255, 255));
        tabbedPane.setForeground(new Color(0, 0, 0));
        tabbedPane.setBounds(0, 0, 1285, 800);

        // tab sách
        JPanel pnlSach = new JPanel();
        pnlSach.setBackground(new Color(240, 240, 240));
        pnlSach.setBounds(0, 0, 1230, 800);
        pnlSach.setLayout(null);

        // thông tin sách
        JPanel pnlThongTinS = new JPanel();
        pnlThongTinS.setBackground(new Color(255, 255, 255));
        pnlThongTinS.setBounds(20, 20, 1235, 255);
        pnlThongTinS.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00F4ng Tin S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlSach.add(pnlThongTinS);
        pnlThongTinS.setLayout(null);

        JLabel lblMaSach = new JLabel("Mã sách");
        lblMaSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMaSach.setBounds(25, 35, 90, 15);
        pnlThongTinS.add(lblMaSach);

        JLabel lblTenSach = new JLabel("Tên sách");
        lblTenSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTenSach.setBounds(25, 77, 90, 15);
        pnlThongTinS.add(lblTenSach);

        JLabel lblTheLoai = new JLabel("Thể loại");
        lblTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTheLoai.setBounds(25, 161, 90, 15);
        pnlThongTinS.add(lblTheLoai);

        JLabel lblKe = new JLabel("Kệ");
        lblKe.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblKe.setBounds(519, 119, 90, 15);
        pnlThongTinS.add(lblKe);

        btnTaoMaSach = new MyButton("Tạo mã");
        btnTaoMaSach.setForeground(new Color(255, 255, 255));
        btnTaoMaSach.setBounds(357, 30, 96, 25);
        btnTaoMaSach.setActionCommand("btnTaoMaSach");
        pnlThongTinS.add(btnTaoMaSach);

        txtMaSach = new JTextField();
        txtMaSach.setEditable(false);
        txtMaSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaSach.setEnabled(false);
        txtMaSach.setColumns(10);
        txtMaSach.setBounds(116, 30, 216, 25);
        pnlThongTinS.add(txtMaSach);

        txtTenSach = new JTextField();
        txtTenSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenSach.setColumns(10);
        txtTenSach.setBounds(116, 72, 337, 25);
        pnlThongTinS.add(txtTenSach);

        txtTheLoaiSach = new JTextField();
        txtTheLoaiSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTheLoaiSach.setBounds(116, 156, 337, 25);
        pnlThongTinS.add(txtTheLoaiSach);
        txtTheLoaiSach.setName("txtTheLoaiSach");
        txtTheLoaiSach.setColumns(10);

        txtKeSach = new JTextField();
        txtKeSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtKeSach.setBounds(610, 114, 119, 25);
        pnlThongTinS.add(txtKeSach);
        txtKeSach.setColumns(10);

        JLabel lblSoLuong = new JLabel("Số lượng");
        lblSoLuong.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblSoLuong.setBounds(519, 35, 90, 15);
        pnlThongTinS.add(lblSoLuong);

        JLabel lblGiaNhap = new JLabel("Giá nhập");
        lblGiaNhap.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblGiaNhap.setBounds(519, 77, 90, 15);
        pnlThongTinS.add(lblGiaNhap);

        JLabel lblThue = new JLabel("Thuế");
        lblThue.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblThue.setBounds(787, 35, 68, 15);
        pnlThongTinS.add(lblThue);

        JLabel lblLoiNhuan = new JLabel("Lợi nhuận ");
        lblLoiNhuan.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblLoiNhuan.setBounds(761, 77, 68, 15);
        pnlThongTinS.add(lblLoiNhuan);

        JLabel lblTacGia = new JLabel("Tác giả");
        lblTacGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTacGia.setBounds(519, 161, 90, 15);
        pnlThongTinS.add(lblTacGia);

        JLabel lblNhaXB = new JLabel("Nhà xuất bản");
        lblNhaXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNhaXB.setBounds(519, 203, 90, 15);
        pnlThongTinS.add(lblNhaXB);

        JLabel lblNamXB = new JLabel("Năm xuất bản");
        lblNamXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNamXB.setBounds(739, 119, 90, 15);
        pnlThongTinS.add(lblNamXB);

        txtSoLuongSach = new JTextField();
        txtSoLuongSach.setHorizontalAlignment(SwingConstants.RIGHT);
        txtSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSoLuongSach.setBounds(609, 30, 120, 25);
        pnlThongTinS.add(txtSoLuongSach);
        txtSoLuongSach.setColumns(10);

        txtGiaNhapSach = new JTextField();
        txtGiaNhapSach.setHorizontalAlignment(SwingConstants.RIGHT);
        txtGiaNhapSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtGiaNhapSach.setBounds(609, 72, 120, 25);
        pnlThongTinS.add(txtGiaNhapSach);
        txtGiaNhapSach.setColumns(10);

        txtThueSach = new JTextField();
        txtThueSach.setEditable(false);
        txtThueSach.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThueSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtThueSach.setBounds(825, 30, 120, 25);
        pnlThongTinS.add(txtThueSach);
        txtThueSach.setColumns(10);

        txtLoiNhuanSach = new JTextField();
        txtLoiNhuanSach.setHorizontalAlignment(SwingConstants.RIGHT);
        txtLoiNhuanSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtLoiNhuanSach.setBounds(825, 72, 120, 25);
        pnlThongTinS.add(txtLoiNhuanSach);
        txtLoiNhuanSach.setColumns(10);

        txtTacGia = new JTextField();
        txtTacGia.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTacGia.setBounds(610, 156, 335, 25);
        pnlThongTinS.add(txtTacGia);
        txtTacGia.setColumns(10);

        txtNhaXB = new JTextField();
        txtNhaXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNhaXB.setBounds(610, 198, 337, 25);
        pnlThongTinS.add(txtNhaXB);
        txtNhaXB.setColumns(10);

        txtNamXB = new JTextField();
        txtNamXB.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtNamXB.setBounds(825, 114, 119, 25);
        pnlThongTinS.add(txtNamXB);
        txtNamXB.setColumns(10);

        JLabel lblPhanTramThue = new JLabel("%");
        lblPhanTramThue.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPhanTramThue.setBounds(956, 35, 30, 15);
        pnlThongTinS.add(lblPhanTramThue);

        JLabel lblPhanTramLN = new JLabel("%");
        lblPhanTramLN.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPhanTramLN.setBounds(955, 77, 30, 15);
        pnlThongTinS.add(lblPhanTramLN);

        JPanel pnlHinhAnh = new JPanel();
        pnlHinhAnh.setBackground(new Color(255, 255, 255));
        pnlHinhAnh.setBounds(1020, 27, 180, 180);
        pnlHinhAnh.setBorder(null);
        pnlHinhAnh.setForeground(new Color(255, 255, 255));
        pnlThongTinS.add(pnlHinhAnh);
        pnlHinhAnh.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        lblHinhAnhSach = new JLabel();
        pnlHinhAnh.add(lblHinhAnhSach);

        btnHinhAnhSach = new MyButton("Chọn ảnh ");
        btnHinhAnhSach.setBounds(1067, 220, 100, 21);
        btnHinhAnhSach.setForeground(new Color(255, 255, 255));
        btnHinhAnhSach.setActionCommand("btnHinhAnhSach");
        pnlThongTinS.add(btnHinhAnhSach);

        JLabel lblTrangThai_1 = new JLabel("Trạng thái");
        lblTrangThai_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTrangThai_1.setBounds(25, 203, 90, 15);
        pnlThongTinS.add(lblTrangThai_1);

        chkTrangThaiSach = new JCheckBox("Đang bán");
        chkTrangThaiSach.setSelected(true);
        chkTrangThaiSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chkTrangThaiSach.setBackground(Color.WHITE);
        chkTrangThaiSach.setBounds(116, 198, 93, 25);
        pnlThongTinS.add(chkTrangThaiSach);

        // tìm kiếm
        JPanel pnlTimKiemS = new JPanel();
        pnlTimKiemS.setBackground(new Color(255, 255, 255));
        pnlTimKiemS.setBounds(20, 290, 910, 195);
        pnlTimKiemS.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm Ki\u1EBFm",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlSach.add(pnlTimKiemS);
        pnlTimKiemS.setLayout(null);

        txtTimKiemSachTheoMa = new JTextField();
        txtTimKiemSachTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtTimKiemSachTheoMa.setText("Nhập mã sách cần tìm");
        txtTimKiemSachTheoMa.setForeground(new Color(128, 128, 128));
        txtTimKiemSachTheoMa.setBounds(25, 30, 180, 21);
        pnlTimKiemS.add(txtTimKiemSachTheoMa);
        txtTimKiemSachTheoMa.setName("txtTimKiemSachTheoMa");
        txtTimKiemSachTheoMa.setColumns(10);

        JPanel pnlLocS = new JPanel();
        pnlLocS.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "L\u1ECDc S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlLocS.setBackground(new Color(240, 240, 240));
        pnlLocS.setBounds(25, 66, 840, 110);
        pnlTimKiemS.add(pnlLocS);
        pnlLocS.setLayout(null);

        JLabel lblLocNCC = new JLabel("Nhà cung cấp");
        lblLocNCC.setBounds(25, 29, 90, 13);
        pnlLocS.add(lblLocNCC);

        JLabel lblLocTheLoai = new JLabel("Thể loại");
        lblLocTheLoai.setBounds(599, 29, 70, 13);
        pnlLocS.add(lblLocTheLoai);

        JLabel lblLocNamXB = new JLabel("Năm xuất bản");
        lblLocNamXB.setBounds(309, 29, 90, 13);
        pnlLocS.add(lblLocNamXB);

        JLabel lblLocTacGia = new JLabel("Tác giả");
        lblLocTacGia.setBounds(309, 67, 70, 13);
        pnlLocS.add(lblLocTacGia);

        txtLocTheLoaiS = new JTextField();
        txtLocTheLoaiS.setBounds(658, 25, 150, 21);
        pnlLocS.add(txtLocTheLoaiS);
        txtLocTheLoaiS.setName("txtLocTheLoaiS");
        txtLocTheLoaiS.setColumns(10);

        txtLocNamXB = new JTextField();
        txtLocNamXB.setBounds(394, 25, 150, 21);
        txtLocNamXB.setName("txtLocNamXB");
        pnlLocS.add(txtLocNamXB);
        txtLocNamXB.setColumns(10);

        txtLocTacGia = new JTextField();
        txtLocTacGia.setBounds(394, 63, 150, 21);
        pnlLocS.add(txtLocTacGia);
        txtLocTacGia.setName("txtLocTacGia");
        txtLocTacGia.setColumns(10);

        cboLocNCC_Sach = new MyCombobox();
        cboLocNCC_Sach.setBackground(new Color(255, 255, 255));
        cboLocNCC_Sach.setBounds(110, 25, 150, 21);
        cboLocNCC_Sach.setActionCommand("cboLocNCC_Sach");
        pnlLocS.add(cboLocNCC_Sach);

        txtLocTenSach = new JTextField();
        txtLocTenSach.setName("txtLocTacGia");
        txtLocTenSach.setColumns(10);
        txtLocTenSach.setBounds(110, 64, 150, 21);
        txtLocTenSach.setName("txtLocTenSach");
        pnlLocS.add(txtLocTenSach);

        lblLocTen = new JLabel("Tên sách");
        lblLocTen.setBounds(25, 67, 70, 13);
        pnlLocS.add(lblLocTen);

        btnTimMaSach = new MyButton("Tìm");
        btnTimMaSach.setForeground(new Color(255, 255, 255));
        btnTimMaSach.setBounds(215, 29, 60, 21);
        btnTimMaSach.setActionCommand("btnTimMaSach");
        pnlTimKiemS.add(btnTimMaSach);

        cboLocTrangThaiSach = new MyCombobox();
        cboLocTrangThaiSach.setBounds(715, 29, 150, 21);
        cboLocTrangThaiSach.setActionCommand("cboLocTrangThaiSach");
        pnlTimKiemS.add(cboLocTrangThaiSach);

        // dữ liệu mẫu
        cboLocTrangThaiSach.addItem("Đang bán");
        cboLocTrangThaiSach.addItem("Không còn bán");

        JLabel lblLocTrangThaiS = new JLabel("Kiểm tra trạng thái");
        lblLocTrangThaiS.setBounds(601, 33, 104, 13);
        pnlTimKiemS.add(lblLocTrangThaiS);

        chkSoLuongSach = new JCheckBox("Sản phẩm có số lượng dưới 10");
        chkSoLuongSach.setBackground(new Color(255, 255, 255));
        chkSoLuongSach.setBounds(330, 30, 209, 21);
        pnlTimKiemS.add(chkSoLuongSach);
        chkSoLuongSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chkSoLuongSach.setActionCommand("chkSoLuongSach");

        // chức năng
        JPanel pnlChucNang = new JPanel();
        pnlChucNang.setBackground(new Color(255, 255, 255));
        pnlChucNang.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ch\u1EE9c N\u0103ng",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlChucNang.setBounds(945, 290, 310, 195);
        pnlSach.add(pnlChucNang);
        pnlChucNang.setLayout(null);

        btnThemSach = new MyButton("THÊM SẢN PHẨM");
        btnThemSach.setBackground(new Color(128, 255, 128));
        btnThemSach.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemSach.setForeground(new Color(255, 255, 255));
        btnThemSach.setText("Thêm");
        btnThemSach.setBounds(35, 46, 100, 30);
        btnThemSach.setActionCommand("btnThemSach");
        pnlChucNang.add(btnThemSach);

        btnCapNhatSach = new MyButton("CẬP NHẬT ");
        btnCapNhatSach.setBackground(new Color(255, 128, 0));
        btnCapNhatSach.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCapNhatSach.setText("Cập nhật");
        btnCapNhatSach.setForeground(new Color(255, 255, 255));
        btnCapNhatSach.setBounds(175, 46, 100, 30);
        btnCapNhatSach.setActionCommand("btnCapNhatSach");
        pnlChucNang.add(btnCapNhatSach);

        btnXoaTrangSach = new MyButton("XÓA TRẮNG");
        btnXoaTrangSach.setBackground(new Color(255, 45, 45));
        btnXoaTrangSach.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXoaTrangSach.setText("Xóa trắng");
        btnXoaTrangSach.setForeground(new Color(255, 255, 255));
        btnXoaTrangSach.setBounds(35, 111, 100, 30);
        btnXoaTrangSach.setActionCommand("btnXoaTrangSach");
        pnlChucNang.add(btnXoaTrangSach);

        btnTaiLaiSach = new MyButton("TẢI LẠI");
        btnTaiLaiSach.setBackground(new Color(97, 166, 247));
        btnTaiLaiSach.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTaiLaiSach.setText("Tải lại");
        btnTaiLaiSach.setForeground(new Color(255, 255, 255));
        btnTaiLaiSach.setBounds(175, 111, 100, 30);
        btnTaiLaiSach.setActionCommand("btnTaiLaiSach");
        pnlChucNang.add(btnTaiLaiSach);

        // tạo bảng sách
        JPanel pnlTableS = new JPanel();
        pnlTableS.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh S\u00E1ch S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlTableS.setBackground(new Color(255, 255, 255));
        pnlTableS.setBounds(20, 500, 1235, 263);
        pnlSach.add(pnlTableS);
        String[] colsSach = { "Mã SP", "Tên sản phẩm", "Nhà cung cấp", "Tác giả", "Thể loại", "Nhà xuất bản",
                "Năm xuất bản", "Kệ", "Số lượng", "Giá nhập" };
        modelSach = new DefaultTableModel(colsSach, 0);
        pnlTableS.setLayout(null);
        tableSach = new MyTable(modelSach);
        tableSach.setName("tableSach");
        JScrollPane scrSach = new JScrollPane(tableSach, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrSach.setLocation(29, 20);
        scrSach.setSize(1184, 233);

        tableSach.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableSach.getColumnModel().getColumn(2).setPreferredWidth(200);
        pnlTableS.add(scrSach);

        // dữ liệu test
        txtTenSach.setText("Nóng Giận Là Bản Năng, Tĩnh Lặng Là Bản Lĩnh");
        txtKeSach.setText("A1");
        txtSoLuongSach.setText("97");
        txtThueSach.setText("7");
        txtGiaNhapSach.setText("71200");
        txtLoiNhuanSach.setText("10");
        txtTheLoaiSach.setText("Tâm lý");
        txtTacGia.setText("Tống Mặc");
        txtNhaXB.setText("NXB Thế Giới");
        txtNamXB.setText("2020");

        txtMaNCCSach = new JTextField();
        txtMaNCCSach.setForeground(new Color(128, 128, 128));
        txtMaNCCSach.setText("Mã NCC");
        txtMaNCCSach.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtMaNCCSach.setColumns(10);
        txtMaNCCSach.setBounds(323, 114, 70, 25);
        txtMaNCCSach.setName("txtMaNCCSach");
        pnlThongTinS.add(txtMaNCCSach);

        btnTimMaNCCSach = new MyButton("Tìm");
        btnTimMaNCCSach.setForeground(Color.WHITE);
        btnTimMaNCCSach.setActionCommand("btnTimMaNCCSach");
        btnTimMaNCCSach.setBounds(403, 114, 50, 25);
        pnlThongTinS.add(btnTimMaNCCSach);

        JLabel lblTenNCCSach = new JLabel("Tên NCC");
        lblTenNCCSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTenNCCSach.setBounds(25, 119, 90, 15);
        pnlThongTinS.add(lblTenNCCSach);

        txtTenNCCSach = new JTextField();
        txtTenNCCSach.setEditable(false);
        txtTenNCCSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenNCCSach.setColumns(10);
        txtTenNCCSach.setBounds(116, 114, 197, 25);
        pnlThongTinS.add(txtTenNCCSach);

////////Tab văn phòng phẩm
        JPanel pnlVPP = new JPanel();
        pnlVPP.setBackground(new Color(240, 240, 240));
        pnlVPP.setBounds(0, 0, 1230, 800);
        pnlVPP.setLayout(null);

        // thông tin văn phòng phẩm
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBackground(new Color(255, 255, 255));
        pnlThongTin.setBounds(20, 20, 1235, 255);
        pnlThongTin.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00F4ng Tin S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlVPP.add(pnlThongTin);
        pnlThongTin.setLayout(null);

        JLabel lblMaSP = new JLabel("Mã sản phẩm");
        lblMaSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblMaSP.setBounds(25, 35, 90, 15);
        pnlThongTin.add(lblMaSP);

        JLabel lblTenSP = new JLabel("Tên sản phẩm");
        lblTenSP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTenSP.setBounds(25, 77, 90, 15);
        pnlThongTin.add(lblTenSP);

        JLabel lblDanhMuc = new JLabel("Danh mục");
        lblDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblDanhMuc.setBounds(519, 119, 90, 15);
        pnlThongTin.add(lblDanhMuc);

        JLabel lblKe_1 = new JLabel("Kệ");
        lblKe_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblKe_1.setBounds(25, 161, 90, 15);
        pnlThongTin.add(lblKe_1);

        btnTaoMaVPP = new MyButton("Tạo mã");
        btnTaoMaVPP.setForeground(new Color(255, 255, 255));
        btnTaoMaVPP.setBounds(357, 30, 96, 25);
        btnTaoMaVPP.setActionCommand("btnTaoMaVPP");
        pnlThongTin.add(btnTaoMaVPP);

        txtMaVPP = new JTextField();
        txtMaVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtMaVPP.setEnabled(false);
        txtMaVPP.setColumns(10);
        txtMaVPP.setBounds(116, 30, 216, 25);
        pnlThongTin.add(txtMaVPP);

        txtTenVPP = new JTextField();
        txtTenVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenVPP.setColumns(10);
        txtTenVPP.setBounds(116, 72, 337, 25);
        pnlThongTin.add(txtTenVPP);

        txtKeVPP = new JTextField();
        txtKeVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtKeVPP.setBounds(116, 156, 337, 25);
        pnlThongTin.add(txtKeVPP);
        txtKeVPP.setColumns(10);

        JLabel lblSoLuong_1 = new JLabel("Số lượng");
        lblSoLuong_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblSoLuong_1.setBounds(519, 35, 90, 15);
        pnlThongTin.add(lblSoLuong_1);

        JLabel lblGiaNhap_1 = new JLabel("Giá nhập");
        lblGiaNhap_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblGiaNhap_1.setBounds(519, 77, 90, 15);
        pnlThongTin.add(lblGiaNhap_1);

        JLabel lblThue_1 = new JLabel("Thuế");
        lblThue_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblThue_1.setBounds(761, 35, 68, 15);
        pnlThongTin.add(lblThue_1);

        JLabel lblLoiNhuan_1 = new JLabel("Lợi nhuận ");
        lblLoiNhuan_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblLoiNhuan_1.setBounds(761, 77, 68, 15);
        pnlThongTin.add(lblLoiNhuan_1);

        JLabel lblTacGia_1 = new JLabel("Thể loại");
        lblTacGia_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTacGia_1.setBounds(519, 161, 90, 15);
        pnlThongTin.add(lblTacGia_1);

        JLabel lblChatLieu = new JLabel("Chất liệu");
        lblChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblChatLieu.setBounds(519, 203, 90, 15);
        pnlThongTin.add(lblChatLieu);

        txtSoLuongVPP = new JTextField();
        txtSoLuongVPP.setHorizontalAlignment(SwingConstants.RIGHT);
        txtSoLuongVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtSoLuongVPP.setBounds(609, 30, 120, 25);
        pnlThongTin.add(txtSoLuongVPP);
        txtSoLuongVPP.setColumns(10);

        txtGiaNhapVPP = new JTextField();
        txtGiaNhapVPP.setHorizontalAlignment(SwingConstants.RIGHT);
        txtGiaNhapVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtGiaNhapVPP.setBounds(609, 72, 120, 25);
        pnlThongTin.add(txtGiaNhapVPP);
        txtGiaNhapVPP.setColumns(10);

        txtThueVPP = new JTextField();
        txtThueVPP.setEditable(false);
        txtThueVPP.setHorizontalAlignment(SwingConstants.RIGHT);
        txtThueVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtThueVPP.setBounds(825, 30, 120, 25);
        pnlThongTin.add(txtThueVPP);
        txtThueVPP.setColumns(10);

        txtLoiNhuanVPP = new JTextField();
        txtLoiNhuanVPP.setHorizontalAlignment(SwingConstants.RIGHT);
        txtLoiNhuanVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtLoiNhuanVPP.setBounds(825, 72, 120, 25);
        pnlThongTin.add(txtLoiNhuanVPP);
        txtLoiNhuanVPP.setColumns(10);

        txtTheLoai = new JTextField();
        txtTheLoai.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTheLoai.setBounds(609, 156, 336, 25);
        pnlThongTin.add(txtTheLoai);
        txtTheLoai.setColumns(10);

        txtChatLieu = new JTextField();
        txtChatLieu.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtChatLieu.setBounds(609, 198, 337, 25);
        pnlThongTin.add(txtChatLieu);
        txtChatLieu.setColumns(10);

        JLabel lblPhanTramThue_1 = new JLabel("%");
        lblPhanTramThue_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPhanTramThue_1.setBounds(956, 35, 30, 15);
        pnlThongTin.add(lblPhanTramThue_1);

        JLabel lblPhanTramLN_1 = new JLabel("%");
        lblPhanTramLN_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblPhanTramLN_1.setBounds(956, 77, 30, 15);
        pnlThongTin.add(lblPhanTramLN_1);

        cboDanhMuc = new MyCombobox();
        cboDanhMuc.setBounds(609, 114, 337, 25);
        pnlThongTin.add(cboDanhMuc);

        JPanel pnlHinhAnh_1 = new JPanel();
        pnlHinhAnh_1.setBorder(null);
        pnlHinhAnh_1.setBackground(new Color(255, 255, 255));
        pnlHinhAnh_1.setBounds(1034, 27, 180, 180);
        pnlThongTin.add(pnlHinhAnh_1);
        pnlHinhAnh_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblHinhAnhVPP = new JLabel();
        lblHinhAnhVPP.setBounds(1046, 30, 135, 180);
        pnlHinhAnh_1.add(lblHinhAnhVPP);

        btnHinhAnhVPP = new MyButton("Chọn ảnh ");
        btnHinhAnhVPP.setBounds(1076, 220, 100, 21);
        btnHinhAnhVPP.setForeground(new Color(255, 255, 255));
        btnHinhAnhVPP.setActionCommand("btnHinhAnhVPP");
        pnlThongTin.add(btnHinhAnhVPP);

        JLabel lblTrangThai = new JLabel("Trạng thái");
        lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTrangThai.setBounds(25, 203, 90, 15);
        pnlThongTin.add(lblTrangThai);

        chkTrangThaiVPP = new JCheckBox("Đang bán");
        chkTrangThaiVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chkTrangThaiVPP.setBackground(new Color(255, 255, 255));
        chkTrangThaiVPP.setSelected(true);
        chkTrangThaiVPP.setBounds(116, 199, 93, 25);
        pnlThongTin.add(chkTrangThaiVPP);

        // tìm kiếm
        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setBackground(new Color(255, 255, 255));
        pnlTimKiem.setBounds(20, 290, 910, 195);
        pnlTimKiem.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "T\u00ECm Ki\u1EBFm",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, null));
        pnlVPP.add(pnlTimKiem);
        pnlTimKiem.setLayout(null);

        txtTimKiemVPPTheoMa = new JTextField();
        txtTimKiemVPPTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtTimKiemVPPTheoMa.setText("Nhập mã sản phẩm cần tìm");
        txtTimKiemVPPTheoMa.setForeground(new Color(128, 128, 128));
        txtTimKiemVPPTheoMa.setBounds(25, 30, 180, 21);
        pnlTimKiem.add(txtTimKiemVPPTheoMa);
        txtTimKiemVPPTheoMa.setName("txtTimKiemVPPTheoMa");
        txtTimKiemVPPTheoMa.setColumns(10);

        JPanel pnlLoc;
        pnlLoc = new JPanel();
        pnlLoc.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "L\u1ECDc S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlLoc.setBackground(new Color(240, 240, 240));
        pnlLoc.setBounds(25, 66, 840, 110);
        pnlTimKiem.add(pnlLoc);
        pnlLoc.setLayout(null);

        lblLocNCC = new JLabel("Nhà cung cấp");
        lblLocNCC.setBounds(25, 29, 90, 13);
        pnlLoc.add(lblLocNCC);

        lblLocTheLoai = new JLabel("Thể loại");
        lblLocTheLoai.setBounds(451, 67, 70, 13);
        pnlLoc.add(lblLocTheLoai);

        JLabel lblLocDanhMuc = new JLabel("Danh mục");
        lblLocDanhMuc.setBounds(25, 67, 90, 13);
        pnlLoc.add(lblLocDanhMuc);

        txtLocTheLoai = new JTextField();
        txtLocTheLoai.setBounds(517, 63, 300, 21);
        pnlLoc.add(txtLocTheLoai);
        txtLocTheLoai.setName("txtLocTheLoai");
        txtLocTheLoai.setColumns(10);

        cboLocNCC_VPP = new MyCombobox();
        cboLocNCC_VPP.setBackground(new Color(255, 255, 255));
        cboLocNCC_VPP.setBounds(110, 25, 300, 21);
        cboLocNCC_VPP.setActionCommand("cboLocNCC_VPP");
        pnlLoc.add(cboLocNCC_VPP);

        cboLocDanhMuc = new MyCombobox();
        cboLocDanhMuc.setBackground(new Color(255, 255, 255));
        cboLocDanhMuc.setBounds(110, 63, 300, 21);
        cboLocDanhMuc.setActionCommand("cboLocDanhMuc");
        pnlLoc.add(cboLocDanhMuc);

        txtLocTenVPP = new JTextField();
        txtLocTenVPP.setName("txtLocTenVPP");
        txtLocTenVPP.setColumns(10);
        txtLocTenVPP.setBounds(517, 26, 300, 21);
        pnlLoc.add(txtLocTenVPP);

        JLabel lblTn = new JLabel("Tên ");
        lblTn.setBounds(451, 29, 70, 13);
        pnlLoc.add(lblTn);

        btnTimMaVPP = new MyButton("Tìm");
        btnTimMaVPP.setForeground(new Color(255, 255, 255));
        btnTimMaVPP.setBounds(215, 30, 60, 21);
        btnTimMaVPP.setActionCommand("btnTimMaVPP");
        pnlTimKiem.add(btnTimMaVPP);

        JLabel lblLocTrangThai = new JLabel("Kiểm tra trạng thái");
        lblLocTrangThai.setBounds(601, 34, 104, 13);
        pnlTimKiem.add(lblLocTrangThai);

        cboLocTrangThaiVPP = new MyCombobox();
        cboLocTrangThaiVPP.setBounds(715, 30, 150, 21);
        cboLocTrangThaiVPP.setActionCommand("cboLocTrangThai");
        pnlTimKiem.add(cboLocTrangThaiVPP);

        chkSoLuongVPP = new JCheckBox("Sản phẩm có số lượng dưới 10");
        chkSoLuongVPP.setBackground(new Color(255, 255, 255));
        chkSoLuongVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        chkSoLuongVPP.setActionCommand("chkSoLuongVPP");
        chkSoLuongVPP.setBounds(330, 30, 209, 21);
        pnlTimKiem.add(chkSoLuongVPP);

        // dữ liệu mẫu
        cboLocTrangThaiVPP.addItem("Đang bán");
        cboLocTrangThaiVPP.addItem("Không còn bán");

        // chức năng
        JPanel pnlChucNang_1 = new JPanel();
        pnlChucNang_1.setBackground(new Color(255, 255, 255));
        pnlChucNang_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ch\u1EE9c N\u0103ng",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlChucNang_1.setBounds(945, 290, 310, 195);
        pnlVPP.add(pnlChucNang_1);
        pnlChucNang_1.setLayout(null);

        btnThemVPP = new MyButton("Thêm");
        btnThemVPP.setBackground(new Color(128, 255, 128));
        btnThemVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnThemVPP.setForeground(new Color(255, 255, 255));
        btnThemVPP.setText("Thêm");
        btnThemVPP.setBounds(35, 46, 100, 30);
        btnThemVPP.setActionCommand("btnThemVPP");
        pnlChucNang_1.add(btnThemVPP);

        btnCapNhatVPP = new MyButton("Cập nhật");
        btnCapNhatVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnCapNhatVPP.setBackground(new Color(255, 128, 0));
        btnCapNhatVPP.setText("Cập nhật");
        btnCapNhatVPP.setForeground(new Color(255, 255, 255));
        btnCapNhatVPP.setBounds(175, 46, 100, 30);
        btnCapNhatVPP.setActionCommand("btnCapNhatVPP");
        pnlChucNang_1.add(btnCapNhatVPP);

        btnXoaTrangVPP = new MyButton("Xóa trắng");
        btnXoaTrangVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXoaTrangVPP.setBackground(new Color(255, 45, 45));
        btnXoaTrangVPP.setText("Xóa trắng");
        btnXoaTrangVPP.setForeground(new Color(255, 255, 255));
        btnXoaTrangVPP.setBounds(35, 111, 100, 30);
        btnXoaTrangVPP.setActionCommand("btnXoaTrangVPP");
        pnlChucNang_1.add(btnXoaTrangVPP);

        btnTaiLaiVPP = new MyButton("Tải lại");
        btnTaiLaiVPP.setBackground(new Color(97, 166, 247));
        btnTaiLaiVPP.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnTaiLaiVPP.setText("Tải lại");
        btnTaiLaiVPP.setForeground(new Color(255, 255, 255));
        btnTaiLaiVPP.setBounds(175, 111, 100, 30);
        btnTaiLaiVPP.setActionCommand("btnTaiLaiVPP");
        pnlChucNang_1.add(btnTaiLaiVPP);

        // tạo bảng vpp
        JPanel pnlTable = new JPanel();
        pnlTable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Danh S\u00E1ch S\u1EA3n Ph\u1EA9m",
                TitledBorder.LEADING, TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
        pnlTable.setBackground(new Color(255, 255, 255));
        pnlTable.setBounds(20, 500, 1235, 263);
        pnlVPP.add(pnlTable);

        String[] cols = { "Mã SP", "Tên sản phẩm", "Nhà cung cấp", "Danh mục", "Thể loại", "Chất liệu", "Kệ",
                "Số lượng", "Giá nhập" };
        modelVPP = new DefaultTableModel(cols, 0);
        pnlTable.setLayout(null);
        tableVPP = new MyTable(modelVPP);
        tableVPP.setName("tableVPP");
        JScrollPane scr = new JScrollPane(tableVPP, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setLocation(29, 20);
        scr.setSize(1184, 233);

        tableVPP.getColumnModel().getColumn(1).setPreferredWidth(200);
        tableVPP.getColumnModel().getColumn(2).setPreferredWidth(300);
        pnlTable.add(scr);

        tabbedPane.addTab("Sách", pnlSach);
        tabbedPane.addTab("Văn phòng phẩm", pnlVPP);
        this.add(tabbedPane);

        // dữ liệu test
        txtTenVPP.setText("Máy Tính Văn Phòng Casio MX - 120B - W-DC");
        txtKeVPP.setText("D4");
        txtSoLuongVPP.setText("97");
        txtThueVPP.setText("7");
        txtGiaNhapVPP.setText("291000");
        txtLoiNhuanVPP.setText("10");
        txtTheLoai.setText("Casio");
        txtChatLieu.setText("Nhựa");

        btnThemDanhMuc = new MyButton("Chọn ảnh ");
        btnThemDanhMuc.setText("Thêm");
        btnThemDanhMuc.setForeground(Color.WHITE);
        btnThemDanhMuc.setActionCommand("btnThemDanhMuc");
        btnThemDanhMuc.setBounds(952, 113, 60, 25);
        pnlThongTin.add(btnThemDanhMuc);

        JLabel lblTenNCCVPP = new JLabel("Tên NCC");
        lblTenNCCVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblTenNCCVPP.setBounds(25, 119, 90, 15);
        pnlThongTin.add(lblTenNCCVPP);

        txtTenNCCVPP = new JTextField();
        txtTenNCCVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
        txtTenNCCVPP.setEditable(false);
        txtTenNCCVPP.setColumns(10);
        txtTenNCCVPP.setBounds(116, 114, 197, 25);
        pnlThongTin.add(txtTenNCCVPP);

        txtMaNCCVPP = new JTextField();
        txtMaNCCVPP.setText("Mã NCC");
        txtMaNCCVPP.setName("txtMaNCCVPP");
        txtMaNCCVPP.setForeground(Color.GRAY);
        txtMaNCCVPP.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtMaNCCVPP.setColumns(10);
        txtMaNCCVPP.setBounds(323, 114, 70, 25);
        pnlThongTin.add(txtMaNCCVPP);

        btnTimMaNCCVPP = new MyButton("Tìm");
        btnTimMaNCCVPP.setForeground(Color.WHITE);
        btnTimMaNCCVPP.setActionCommand("btnTimMaNCCVPP");
        btnTimMaNCCVPP.setBounds(403, 114, 50, 25);
        pnlThongTin.add(btnTimMaNCCVPP);

        // load dữ liệu
        hienThiDuLieuSach(busSP.layDSSachConBan());
        hienThiDuLieuVPP(busSP.layDSVPPConBan());

        cboLocNCC_Sach.addItem("Tất cả");
        cboLocNCC_VPP.addItem("Tất cả");
        List<NhaCungCap> dsNCC = busNCC.layDSNhaCungCap();
        for (NhaCungCap ncc : dsNCC) {
            cboLocNCC_Sach.addItem(ncc.getMaNhaCungCap());
//			cboLocNCC_Sach.addItem(ncc.getTenNhaCungCap());
            cboLocNCC_VPP.addItem(ncc.getMaNhaCungCap());
        }

        cboLocDanhMuc.addItem("Tất cả");
        List<DanhMuc> dsDM = busDanhMuc.layDSDanhMuc();
        for (DanhMuc danhMuc : dsDM) {
            cboDanhMuc.addItem(danhMuc.getTenDanhMuc());
//			cboLocDanhMuc.addItem(danhMuc.getMaDanhMuc());
            cboLocDanhMuc.addItem(danhMuc.getTenDanhMuc());
        }

        // sự kiện
        ActionListener ac = new SanPhamController(this);

        btnTaoMaSach.addActionListener(ac);
        btnHinhAnhSach.addActionListener(ac);
        btnXoaTrangSach.addActionListener(ac);
        btnThemSach.addActionListener(ac);
        btnCapNhatSach.addActionListener(ac);
        btnTaiLaiSach.addActionListener(ac);
        btnTimMaSach.addActionListener(ac);
        btnTimMaNCCSach.addActionListener(ac);
        chkSoLuongSach.addActionListener(ac);

        btnTaoMaVPP.addActionListener(ac);
        btnHinhAnhVPP.addActionListener(ac);
        btnXoaTrangVPP.addActionListener(ac);
        btnThemVPP.addActionListener(ac);
        btnCapNhatVPP.addActionListener(ac);
        btnTaiLaiVPP.addActionListener(ac);
        btnTimMaVPP.addActionListener(ac);
        btnThemDanhMuc.addActionListener(ac);
        btnTimMaNCCVPP.addActionListener(ac);
        cboLocTrangThaiSach.addActionListener(ac);
        cboLocNCC_Sach.addActionListener(ac);
        cboLocTrangThaiVPP.addActionListener(ac);
        cboLocNCC_VPP.addActionListener(ac);
        cboLocDanhMuc.addActionListener(ac);
        chkSoLuongVPP.addActionListener(ac);

        tableSach.addMouseListener(new SanPhamController(this));
        tableVPP.addMouseListener(new SanPhamController(this));

        txtLocNamXB.addKeyListener(new SanPhamController(this));
        txtLocTheLoaiS.addKeyListener(new SanPhamController(this));
        txtLocTacGia.addKeyListener(new SanPhamController(this));
        txtLocTenSach.addKeyListener(new SanPhamController(this));
        txtLocTheLoai.addKeyListener(new SanPhamController(this));
        txtLocTenVPP.addKeyListener(new SanPhamController(this));
        txtTimKiemSachTheoMa.addKeyListener(new SanPhamController(this));
        txtTimKiemVPPTheoMa.addKeyListener(new SanPhamController(this));

        txtTimKiemSachTheoMa.addFocusListener(new SanPhamController(this));
        txtTimKiemVPPTheoMa.addFocusListener(new SanPhamController(this));
        txtMaNCCSach.addFocusListener(new SanPhamController(this));
        txtMaNCCVPP.addFocusListener(new SanPhamController(this));
        txtTheLoaiSach.addFocusListener(new SanPhamController(this));
    }

    // kiểm tra số lượng sách
    public void kiemTraSoLuongSach() throws RemoteException {
        if (chkSoLuongSach.isSelected()) {
            xoaDuLieuBangSach();
            hienThiDuLieuSach(busSP.layDSSachGanHet());
        } else {
            xoaDuLieuBangSach();
            hienThiDuLieuSach(busSP.layDSSachConBan());
        }
    }

    // xử lý thuế sách
    public void focusLostThueSach() {
        if (txtTheLoaiSach.getText().equals("Giáo khoa")) {
            txtThueSach.setText("0");
        } else {
            txtThueSach.setText("7");
        }
    }

    public void focusGainedThueSach() {
        if (txtTheLoaiSach.getText().isEmpty()) {
            txtThueSach.setText("7");
        }
    }

    // chọn ncc hiện tên ncc sách
    public void chonNCCSach() throws RemoteException {
        NhaCungCap ncc = busNCC.timNhaCungCapTheoMa(txtMaNCCSach.getText().trim());
        if (ncc == null) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại");
            txtMaNCCSach.requestFocus();
        } else {
            txtTenNCCSach.setText(ncc.getTenNhaCungCap());
            txtMaNCCSach.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtMaNCCSach.setForeground(Color.GRAY);
            txtMaNCCSach.setText("Mã NCC");
        }
    }

    // xử lý lọc sách theo nhiều trường
    public void xuLyTimKiemSach() throws RemoteException {
        String namXB = txtLocNamXB.getText().trim();
        String theLoai = txtLocTheLoaiS.getText().trim();
        String tacGia = txtLocTacGia.getText().trim();

        List<SanPham> dsSach = busSP.locSachTheoNCC(cboLocNCC_Sach.getSelectedItem().toString());
        busSP.locSachTheoTen(dsSach, txtLocTenSach.getText().trim());
        if (!namXB.isEmpty()) {
            int namXB_So = Integer.parseInt(namXB);
            busSP.locSachTheoNamXB(dsSach, namXB_So);
        }
        if (!theLoai.isEmpty()) {
            busSP.locSachTheoTheLoai(dsSach, theLoai);
        }
        if (!tacGia.isEmpty()) {
            busSP.locSachTheoTacGia(dsSach, tacGia);
        }
        xoaDuLieuBangSach();
        hienThiDuLieuSach(dsSach);
    }

    // tìm sách theo mã
    public void timSachTheoMa() throws RemoteException {
        String ma = txtTimKiemSachTheoMa.getText().trim();
        if (ma.equals("Nhập mã sách cần tìm")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm cần tìm");
        } else {
            SanPham s = busSP.timKiemSanPham(ma);
            if (s == null) {
                xoaDuLieuBangSach();
            } else {
                xoaDuLieuBangSach();
                modelSach.addRow(new Object[] { s.getMaSanPham(), s.getTenSanPham(),
                        s.getNhaCungCap().getTenNhaCungCap(), ((Sach) s).getTacGia(), s.getTheLoai(),
                        ((Sach) s).getNhaXuatBan(), ((Sach) s).getNamXuatBan(), s.getKe(), s.getSoLuongTon(),
                        Tool.dinhDangTien(s.getGiaNhap()) });
            }
        }

    }

    // tải lại danh sách Sách
    public void taiLaiSach() throws RemoteException {
        xoaDuLieuBangSach();
        cboLocTrangThaiSach.setSelectedIndex(0);
        cboLocNCC_Sach.setSelectedIndex(0);
        chkSoLuongSach.setSelected(false);
        chkTrangThaiSach.setSelected(true);
        hienThiDuLieuSach(busSP.layDSSachConBan());
        txtLocNamXB.setText("");
        txtLocTacGia.setText("");
        txtLocTheLoaiS.setText("");
        txtLocTenSach.setText("");
        txtTimKiemSachTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtTimKiemSachTheoMa.setForeground(Color.GRAY);
        txtTimKiemSachTheoMa.setText("Nhập mã sách cần tìm");
    }

    // lọc sách theo trạng thái
    public void KiemTraTrangThaiSach() throws RemoteException {
        String trangThai = (String) cboLocTrangThaiSach.getSelectedItem();
        xoaDuLieuBangSach();
        hienThiDuLieuSach(busSP.kiemTraTrangThaiSach(trangThai));
    }

    // hiển thị danh sách Sách
    public void hienThiDuLieuSach(List<SanPham> dsSP) {
        System.out.println("hien thi du lieu sach");

        for (SanPham s : dsSP) {
            System.out.println(s.toString());
            if (s instanceof Sach)
                modelSach.addRow(new Object[] { s.getMaSanPham(), s.getTenSanPham(),
                        s.getNhaCungCap().getTenNhaCungCap(), ((Sach) s).getTacGia(), s.getTheLoai(),
                        ((Sach) s).getNhaXuatBan(), ((Sach) s).getNamXuatBan(), s.getKe(), s.getSoLuongTon(),
                        Tool.dinhDangTien(s.getGiaNhap()) });
        }
    }

    // xóa dữ liệu bảng sách
    public void xoaDuLieuBangSach() {
        while (modelSach.getRowCount() != 0) {
            modelSach.removeRow(0);
        }
    }

    // cập nhật sách
    public void capNhatSach() throws RemoteException {
        int r = tableSach.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        } else {
            String maSach = txtMaSach.getText().trim();
            String tenSach = txtTenSach.getText().trim();
            String ke = txtKeSach.getText().trim();
            String theLoai = txtTheLoaiSach.getText().trim();
            String tacGia = txtTacGia.getText().trim();
            String nhaXB = txtNhaXB.getText().trim();
            String hinhAnh = anh;

            String trangThai;
            if (chkTrangThaiSach.isSelected()) {
                trangThai = "Đang bán";
            } else {
                trangThai = "Không còn bán";
            }
            String tenNCC = txtTenNCCSach.getText().trim();
            NhaCungCap ncc = busNCC.timNCCTheoTen(tenNCC);
            String giaNhapChuyen = txtGiaNhapSach.getText().trim();

            if (busSP.validDataSach(maSach, tenSach, tenNCC, txtSoLuongSach.getText().trim(), giaNhapChuyen, theLoai,
                    ke, hinhAnh, txtThueSach.getText().trim(), txtLoiNhuanSach.getText().trim(), tacGia, nhaXB,
                    txtNamXB.getText().trim())) {
                int namXB = Integer.parseInt(txtNamXB.getText().trim());
                int soLuong = Integer.parseInt(txtSoLuongSach.getText().trim());
                float thue = Float.parseFloat(txtThueSach.getText().trim());
                float giaNhap = Float.parseFloat(giaNhapChuyen);
                float loiNhuan = Float.parseFloat(txtLoiNhuanSach.getText().trim());
                SanPham sach = new Sach(maSach, tenSach, soLuong, giaNhap, theLoai, ke, hinhAnh, thue, loiNhuan,
                        trangThai, ncc, tacGia, nhaXB, namXB);
                if (busSP.capNhatSanPham(sach)) {
                    taiLaiSach();
                    xoaTrangSach();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật mã");
                }
            } else {
                JOptionPane.showMessageDialog(this, busSP.mes);
            }
        }
    }

    // thêm sách
    public void themSach() throws RemoteException {
        String maSach = txtMaSach.getText().trim();
        String tenSach = txtTenSach.getText().trim();
        String ke = txtKeSach.getText().trim();
        String theLoai = txtTheLoaiSach.getText().trim();
        String tacGia = txtTacGia.getText().trim();
        String nhaXB = txtNhaXB.getText().trim();
        String hinhAnh = anh;
        String trangThai;
        if (chkTrangThaiSach.isSelected()) {
            trangThai = "Đang bán";
        } else {
            trangThai = "Không còn bán";
        }
        String tenNCC = txtTenNCCSach.getText().trim();
        NhaCungCap ncc = busNCC.timNCCTheoTen(tenNCC);
        int r = tableSach.getSelectedRow();
        if (r != -1) {
            JOptionPane.showMessageDialog(this, "Đang trong chế độ sửa không được phép thêm");
            return;
        } else {
            if (busSP.validDataSach(maSach, tenSach, tenNCC, txtSoLuongSach.getText().trim(),
                    txtGiaNhapSach.getText().trim(), theLoai, ke, hinhAnh, txtThueSach.getText().trim(),
                    txtLoiNhuanSach.getText().trim(), tacGia, nhaXB, txtNamXB.getText().trim())) {
                int namXB = Integer.parseInt(txtNamXB.getText().trim());
                int soLuong = Integer.parseInt(txtSoLuongSach.getText().trim());
                float thue = Float.parseFloat(txtThueSach.getText().trim());
                float giaNhap = Float.parseFloat(txtGiaNhapSach.getText().trim());
                float loiNhuan = Float.parseFloat(txtLoiNhuanSach.getText().trim());
                SanPham sach = new Sach(maSach, tenSach, soLuong, giaNhap, theLoai, ke, hinhAnh, thue, loiNhuan,
                        trangThai, ncc, tacGia, nhaXB, namXB);
                if (busSP.themSanPham(sach)) {
                    taiLaiSach();
                    xoaTrangSach();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                }
            } else {
                JOptionPane.showMessageDialog(this, busSP.mes);
            }
        }
    }

    // chọn ảnh sách
    public String chonAnhSach() {
        String currentDirectory = System.getProperty("user.dir");
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            lblHinhAnhSach.setIcon(new ImageIcon(selectedFile.getPath()));
            anh = "src\\image\\product\\" + selectedFile.getName();
        } else {
            anh = "Không có ảnh";
        }
        return anh;
    }

    // tạo mã sách
    public void taoMaSach() throws RemoteException {
        txtMaSach.setText(busSP.taoMaSach());
    }

    // xóa trắng sách
    public void xoaTrangSach() {
        txtMaSach.setText("");
        txtTenSach.setText("");
        txtKeSach.setText("");
        txtSoLuongSach.setText("");
        txtThueSach.setText("");
        txtGiaNhapSach.setText("");
        txtLoiNhuanSach.setText("");
        txtTheLoaiSach.setText("");
        txtTacGia.setText("");
        txtNhaXB.setText("");
        txtNamXB.setText("");
        lblHinhAnhSach.setIcon(null);
        anh = null;
        chkTrangThaiSach.setSelected(true);
        txtTenNCCSach.setText("");
        tableSach.clearSelection();
        btnTaoMaSach.setEnabled(true);
    }

    // chọn thông tin sách trong bảng hiện lên
    public void chonThongTinSach() throws RemoteException {
        int r = tableSach.getSelectedRow();
        if (r != -1) {
            txtMaSach.setText(modelSach.getValueAt(r, 0).toString());
            txtTenSach.setText(modelSach.getValueAt(r, 1).toString());
            txtKeSach.setText(modelSach.getValueAt(r, 7).toString());
            txtSoLuongSach.setText(modelSach.getValueAt(r, 8).toString());
            txtGiaNhapSach.setText(modelSach.getValueAt(r, 9).toString().replaceAll("[,VND]", ""));
            txtTheLoaiSach.setText(modelSach.getValueAt(r, 4).toString());
            txtTacGia.setText(modelSach.getValueAt(r, 3).toString());
            txtNhaXB.setText(modelSach.getValueAt(r, 5).toString());
            txtNamXB.setText(modelSach.getValueAt(r, 6).toString());
            txtTenNCCSach.setText(modelSach.getValueAt(r, 2).toString());
            anh = busSP.timKiemSanPham(modelSach.getValueAt(r, 0).toString()).getHinhAnh();
            for (SanPham sanPham : busSP.layDSSanPham()) {
                if (sanPham.getMaSanPham().equals(modelSach.getValueAt(r, 0).toString())) {
                    lblHinhAnhSach.setIcon(new ImageIcon(sanPham.getHinhAnh()));
                    txtThueSach.setText(sanPham.getThue() + "");
                    txtLoiNhuanSach.setText(sanPham.getPhanTramLoiNhuan() + "");
                    if (sanPham.getTrangThai().equals("Đang bán")) {
                        chkTrangThaiSach.setSelected(true);
                    } else {
                        chkTrangThaiSach.setSelected(false);
                    }
                }
            }
            btnTaoMaSach.setEnabled(false);
        }
    }

    public void focusGainedSach() {
        if (txtTimKiemSachTheoMa.getText().equals("Nhập mã sách cần tìm")) {
            txtTimKiemSachTheoMa.setText("");
            txtTimKiemSachTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
            txtTimKiemSachTheoMa.setForeground(Color.BLACK);
        }
    }

    public void focusLostSach() {
        if (txtTimKiemSachTheoMa.getText().isEmpty()) {
            txtTimKiemSachTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtTimKiemSachTheoMa.setForeground(Color.GRAY);
            txtTimKiemSachTheoMa.setText("Nhập mã sách cần tìm");
        }
    }

    public void focusGainedNCCSach() {
        if (txtMaNCCSach.getText().equals("Mã NCC")) {
            txtMaNCCSach.setText("");
            txtMaNCCSach.setFont(new Font("Tahoma", Font.PLAIN, 13));
            txtMaNCCSach.setForeground(Color.BLACK);
        }
    }

    public void focusLostNCCSach() {
        if (txtMaNCCSach.getText().isEmpty()) {
            txtMaNCCSach.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtMaNCCSach.setForeground(Color.GRAY);
            txtMaNCCSach.setText("Mã NCC");
        }
    }

    // kiểm tra số lượng vpp
    public void kiemTraSoLuongVPP() throws RemoteException {
        if (chkSoLuongVPP.isSelected()) {
            xoaDuLieuBangVPP();
            hienThiDuLieuVPP(busSP.layDSVPPGanHet());
        } else {
            xoaDuLieuBangVPP();
            hienThiDuLieuVPP(busSP.layDSVPPConBan());
        }

    }

    // chọn ncc hiện tên ncc vpp
    public void chonNCCVPP() throws RemoteException {
        NhaCungCap ncc = busNCC.timNhaCungCapTheoMa(txtMaNCCVPP.getText().trim());
        if (ncc == null) {
            JOptionPane.showMessageDialog(this, "Nhà cung cấp không tồn tại");
            txtMaNCCVPP.requestFocus();
        } else {
            txtTenNCCVPP.setText(ncc.getTenNhaCungCap());
            txtMaNCCVPP.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtMaNCCVPP.setForeground(Color.GRAY);
            txtMaNCCVPP.setText("Mã NCC");
        }
    }

    // load dữ liệu lên combobox danh mục
    public void doDuLieuDanhMucVaoCombobox() throws RemoteException {
        List<DanhMuc> dsDanhMuc = busDanhMuc.layDSDanhMuc();
        for (DanhMuc danhMuc : dsDanhMuc) {
            cboDanhMuc.addItem(danhMuc.getMaDanhMuc());
        }
    }

    // thêm danh mục
    public void themDanhMuc() throws RemoteException {
        new DanhMucGui(cboDanhMuc).setVisible(true);
    }

    // cập nhật vpp
    public void capNhatVPP() throws RemoteException {
        int r = tableVPP.getSelectedRow();
        if (r < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
            return;
        } else {
            String maVPP = txtMaVPP.getText().trim();
            String tenVPP = txtTenVPP.getText().trim();
            String ke = txtKeVPP.getText().trim();
            String theLoai = txtTheLoai.getText().trim();
            String chatLieu = txtChatLieu.getText().trim();
            String hinhAnh = anh;
            String trangThai;
            if (chkTrangThaiVPP.isSelected()) {
                trangThai = "Đang bán";
            } else {
                trangThai = "Không còn bán";
            }
            String tenNCC = txtTenNCCVPP.getText().trim();
            NhaCungCap ncc = busNCC.timNCCTheoTen(tenNCC);
            String tenDanhMuc = cboDanhMuc.getSelectedItem().toString();
            DanhMuc danhMuc = busDanhMuc.timDanhMucTheoTen(tenDanhMuc);
            String giaNhapChuyen = txtGiaNhapVPP.getText().trim();

            if (busSP.validDataVPP(maVPP, tenVPP, tenNCC, txtSoLuongVPP.getText().trim(), giaNhapChuyen, theLoai, ke,
                    hinhAnh, txtThueVPP.getText().trim(), txtLoiNhuanVPP.getText().trim(), chatLieu)) {
                int soLuong = Integer.parseInt(txtSoLuongVPP.getText().trim());
                float thue = Float.parseFloat(txtThueVPP.getText().trim());
                float giaNhap = Float.parseFloat(giaNhapChuyen);
                float loiNhuan = Float.parseFloat(txtLoiNhuanVPP.getText().trim());
                SanPham vpp = new VanPhongPham(maVPP, tenVPP, soLuong, giaNhap, theLoai, ke, hinhAnh, thue, loiNhuan,
                        trangThai, ncc, chatLieu, danhMuc);
                if (busSP.capNhatSanPham(vpp)) {
                    xoaTrangVPP();
                    taiLaiVPP();
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể cập nhật mã");
                }
            } else {
                JOptionPane.showMessageDialog(this, busSP.mes);
            }

        }
    }

    // xử lý tìm kiếm vpp theo nhiều trường
    public void xuLyTimKiemVPP() throws RemoteException {
        chkSoLuongVPP.setSelected(false);
        String maNCC = cboLocNCC_VPP.getSelectedItem().toString();
        String danhMuc = cboLocDanhMuc.getSelectedItem().toString();
        String theLoai = txtLocTheLoai.getText().trim();

        List<SanPham> dsVPP = busSP.locVPPTheoNCC(maNCC);

        List<SanPham> dsVPP2 = busSP.locVPPTheoDanhMuc(danhMuc, dsVPP);
        busSP.locVPPTheoTen(dsVPP, txtLocTenVPP.getText().trim());
        if (!theLoai.isEmpty()) {
            busSP.locVPPTheoTheLoai(dsVPP, theLoai);
        }
        xoaDuLieuBangVPP();
        hienThiDuLieuVPP(dsVPP2);
    }

    // tìm vpp theo mã
    public void timVPPTheoMa() throws RemoteException {
        String ma = txtTimKiemVPPTheoMa.getText().trim();
        if (ma.equals("Nhập mã sản phẩm cần tìm")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã sản phẩm cần tìm");
        } else {
            SanPham vpp = busSP.timKiemSanPham(ma);
            if (vpp == null) {
                xoaDuLieuBangVPP();
            } else {
                xoaDuLieuBangVPP();
                modelVPP.addRow(new Object[] { vpp.getMaSanPham(), vpp.getTenSanPham(),
                        vpp.getNhaCungCap().getTenNhaCungCap(), ((VanPhongPham) vpp).getDanhMuc().getTenDanhMuc(),
                        vpp.getTheLoai(), ((VanPhongPham) vpp).getChatLieu(), vpp.getKe(), vpp.getSoLuongTon(),
                        Tool.dinhDangTien(vpp.getGiaNhap()) });
            }
        }
    }

    // lọc sách theo trạng thái
    public void locVPPTheoTrangThai() throws RemoteException {
        String trangThai = (String) cboLocTrangThaiVPP.getSelectedItem();
        xoaDuLieuBangVPP();
        hienThiDuLieuVPP(busSP.kiemTraTrangThaiVPP(trangThai));
    }

    // thêm vpp
    public void themVPP() throws RemoteException {
        String maVPP = txtMaVPP.getText().trim();
        String tenVPP = txtTenVPP.getText().trim();
        String ke = txtKeVPP.getText().trim();
        String theLoai = txtTheLoai.getText().trim();
        String chatLieu = txtChatLieu.getText().trim();
        String hinhAnh = anh;
        String trangThai;
        if (chkTrangThaiSach.isSelected()) {
            trangThai = "Đang bán";
        } else {
            trangThai = "Không còn bán";
        }
        String tenNCC = txtTenNCCVPP.getText().trim();
        NhaCungCap ncc = busNCC.timNCCTheoTen(tenNCC);
        String tenDM = cboDanhMuc.getSelectedItem().toString();
        DanhMuc danhMuc = busDanhMuc.timDanhMucTheoTen(tenDM);

        int r = tableSach.getSelectedRow();
        if (r != -1) {
            JOptionPane.showMessageDialog(this, "Đang trong chế độ sửa không được phép thêm");
            return;
        } else {
            if (busSP.validDataVPP(maVPP, tenVPP, tenNCC, txtSoLuongVPP.getText().trim(),
                    txtGiaNhapVPP.getText().trim(), theLoai, ke, hinhAnh, txtThueVPP.getText().trim(),
                    txtLoiNhuanVPP.getText().trim(), chatLieu)) {
                int soLuong = Integer.parseInt(txtSoLuongVPP.getText().trim());
                float thue = Float.parseFloat(txtThueVPP.getText().trim());
                float giaNhap = Float.parseFloat(txtGiaNhapVPP.getText().trim());
                float loiNhuan = Float.parseFloat(txtLoiNhuanVPP.getText().trim());
                SanPham vpp = new VanPhongPham(maVPP, tenVPP, soLuong, giaNhap, theLoai, ke, hinhAnh, thue, loiNhuan,
                        trangThai, ncc, chatLieu, danhMuc);
                if (busSP.themSanPham(vpp)) {
                    xoaTrangVPP();
                    taiLaiVPP();
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Mã sản phẩm đã tồn tại");
                }
            } else {
                JOptionPane.showMessageDialog(this, busSP.mes);
            }
        }
    }

    // tải lại danh sách VPP
    public void taiLaiVPP() throws RemoteException {
        xoaDuLieuBangVPP();
        cboLocTrangThaiVPP.setSelectedIndex(0);
        cboLocNCC_VPP.setSelectedIndex(0);
        cboLocDanhMuc.setSelectedIndex(0);
        chkTrangThaiVPP.setSelected(true);
        hienThiDuLieuVPP(busSP.layDSVPPConBan());
        txtLocTenVPP.setText("");
        txtLocTheLoai.setText("");
        txtTimKiemVPPTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
        txtTimKiemVPPTheoMa.setForeground(Color.GRAY);
        txtTimKiemVPPTheoMa.setText("Nhập mã sản phẩm cần tìm");
    }

    // hiển thị danh sách vpp
    public void hienThiDuLieuVPP(List<SanPham> dsSP) {
        for (SanPham vpp : dsSP) {
            if (vpp instanceof VanPhongPham)
                modelVPP.addRow(new Object[] { vpp.getMaSanPham(), vpp.getTenSanPham(),
                        vpp.getNhaCungCap().getTenNhaCungCap(), ((VanPhongPham) vpp).getDanhMuc().getTenDanhMuc(),
                        vpp.getTheLoai(), ((VanPhongPham) vpp).getChatLieu(), vpp.getKe(), vpp.getSoLuongTon(),
                        Tool.dinhDangTien(vpp.getGiaNhap()) });
        }
    }

    // xóa dữ liệu bảng vpp
    public void xoaDuLieuBangVPP() {
        while (modelVPP.getRowCount() != 0) {
            modelVPP.removeRow(0);
        }
    }

    // chọn ảnh vpp
    public String chonAnhVPP() {
        String currentDirectory = System.getProperty("user.dir");
        JFileChooser fileChooser = new JFileChooser(currentDirectory);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            lblHinhAnhVPP.setIcon(new ImageIcon(selectedFile.getPath()));
            anh = "src\\image\\product\\" + selectedFile.getName();
        } else {
            anh = "Không có ảnh";
        }
        return anh;
    }

    // tạo mã vpp
    public void taoMaVPP() throws RemoteException {
        txtMaVPP.setText(busSP.taoMaVPP());
    }

    // xóa trắng vpp
    public void xoaTrangVPP() {
        txtMaVPP.setText("");
        txtTenVPP.setText("");
        txtKeVPP.setText("");
        txtSoLuongVPP.setText("");
        txtThueVPP.setText("7");
        txtGiaNhapVPP.setText("");
        txtLoiNhuanVPP.setText("");
        txtTheLoai.setText("");
        txtChatLieu.setText("");
        txtTenNCCVPP.setText("");
        lblHinhAnhVPP.setIcon(null);
        anh = null;
        cboDanhMuc.setSelectedIndex(0);
        chkTrangThaiVPP.setSelected(true);
        btnTaoMaVPP.setEnabled(true);
        tableVPP.clearSelection();
    }

    // chọn thông tin vpp trong bảng hiện lên
    public void chonThongTinVPP() throws RemoteException {
        int r = tableVPP.getSelectedRow();
        if (r != -1) {
            txtMaVPP.setText(modelVPP.getValueAt(r, 0).toString());
            txtTenVPP.setText(modelVPP.getValueAt(r, 1).toString());
            txtTheLoai.setText(modelVPP.getValueAt(r, 4).toString());
            txtChatLieu.setText(modelVPP.getValueAt(r, 5).toString());
            txtKeVPP.setText(modelVPP.getValueAt(r, 6).toString());
            txtSoLuongVPP.setText(modelVPP.getValueAt(r, 7).toString());
            txtGiaNhapVPP.setText(modelVPP.getValueAt(r, 8).toString().replaceAll("[,VND]", ""));
            txtTenNCCVPP.setText(modelVPP.getValueAt(r, 2).toString());
            anh = busSP.timKiemSanPham(modelVPP.getValueAt(r, 0).toString()).getHinhAnh();
            for (SanPham sanPham : busSP.layDSSanPham()) {
                if (sanPham.getMaSanPham().equals(modelVPP.getValueAt(r, 0).toString())) {
                    lblHinhAnhVPP.setIcon(new ImageIcon(sanPham.getHinhAnh()));
                    txtThueVPP.setText(sanPham.getThue() + "");
                    txtLoiNhuanVPP.setText(sanPham.getPhanTramLoiNhuan() + "");
                    if (sanPham.getTrangThai().equals("Đang bán")) {
                        chkTrangThaiVPP.setSelected(true);
                    } else {
                        chkTrangThaiVPP.setSelected(false);
                    }
                    String danhMuc = ((VanPhongPham) sanPham).getDanhMuc().getTenDanhMuc();
                    cboDanhMuc.setSelectedItem(danhMuc);
                }
            }
            btnTaoMaVPP.setEnabled(false);
        }
    }

    public void focusGainedVPP() {
        if (txtTimKiemVPPTheoMa.getText().equals("Nhập mã sản phẩm cần tìm")) {
            txtTimKiemVPPTheoMa.setText("");
            txtTimKiemVPPTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
            txtTimKiemVPPTheoMa.setForeground(Color.BLACK);
        }
    }

    public void focusLostVPP() {
        if (txtTimKiemVPPTheoMa.getText().isEmpty()) {
            txtTimKiemVPPTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtTimKiemVPPTheoMa.setForeground(Color.GRAY);
            txtTimKiemVPPTheoMa.setText("Nhập mã sản phẩm cần tìm");
        }
    }

    public void focusGainedNCCVPP() {
        if (txtMaNCCVPP.getText().equals("Mã NCC")) {
            txtMaNCCVPP.setText("");
            txtMaNCCVPP.setFont(new Font("Tahoma", Font.PLAIN, 13));
            txtMaNCCVPP.setForeground(Color.BLACK);
        }
    }

    public void focusLostNCCVPP() {
        if (txtMaNCCVPP.getText().isEmpty()) {
            txtMaNCCVPP.setFont(new Font("Tahoma", Font.ITALIC, 13));
            txtMaNCCVPP.setForeground(Color.GRAY);
            txtMaNCCVPP.setText("Mã NCC");
        }
    }
}