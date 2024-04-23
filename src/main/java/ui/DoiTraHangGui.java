package ui;

import bus.DonDoiTraBus;
import bus.HoaDonBus;
import bus.KhachHangBus;
import bus.SanPhamBus;
import bus.impl.DonDoiTraBusImpl;
import bus.impl.HoaDonBusImpl;
import bus.impl.KhachHangBusImpl;
import bus.impl.SanPhamBusImpl;
import controller.DonDoiTraController;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.*;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import tool.Tools;

public class DoiTraHangGui extends JPanel {
	private JTextField txtTimKiemHoaDon;
	/**
	 * 
	 */
	private DefaultTableModel modelDanhSachHoaDon;
	private JTable tbDanhSachHoaDon;
	private JScrollPane srcDanhSachHoaDon;
	private DefaultTableModel modelDanhSachSanPham;
	private MyTable tbDanhSachSanPham;
	private JTextField txtMaDonDoiTra;
	private JTextField txtDiemHoanTra;
	private JTextField txtCTKM;
	private DefaultTableModel modelDonDoiTra;
	private MyTable tbDonDoiTra;
	private JTextField txtTongSoLuongDoi;
	private JTextField txtTongTienTra;
	private JTextField txtMaDonDoiTraQL;
	private JTextField txtTenNhanVienQL;
	private JTextField txtPhuongThucQL;
	private JTextField txtCTKMQL;
	private JTextField txtTongTienTraQL;
	private JTextField txtNgayLapQL;
	private JTextField txtTenKhachHangQL;
	private JTextField txtDiemHoanTraQL;
	private JTextField txtTongSoLuongDoiQL;
	private DefaultTableModel modelSanPhamQL;
	private MyTable tbSanPhamQL;
	private JTextField txtMaDonDoiTraTimKiemQL;
	private JTextField txtSDTTimKiemQL;
	private JTextField txtMaHDTimKiemQL;
	private DefaultTableModel modelDonDoiTraQL;
	private MyTable tbDonDoiTraQL;
	private JTextField txtMaHoaDonQLDDT;
	private MyCombobox cbPhuongThucDoiTra;
	private MyButton btnTaoDonDoiTtra;
	private MyButton btnThemSanPham;
	private JLabel lblMaDonDoiTra;
	private JLabel lblDiemHoanTra;
	private JLabel lblCTKM;
	private JLabel lblTongSoLuongDoi;
	private JLabel lblTongTienTra;
	private MyButton btnHoanThanhDon;
	private MyButton btnHuy;
	private MyButton btnXoaSanPham;
	private DonDoiTraBusImpl busDDT = new DonDoiTraBusImpl();
    private HoaDonBusImpl busHD = new HoaDonBusImpl();
	private List<DonDoiTra> dsDonDoiTra = busDDT.layHetDSDonDoiTra();
	private MyButton btnTimKiemKhachHang;
	private JLabel lblTenKhachHangv;
	private JLabel lblMaHoaDonv;
	private JLabel lblNgayLapHoaDonv;
	private JLabel lblSDTv;
	private String maDDTHienTai = "";
	private String maHDHienTai = "";
	private NhanVien nvHienTai;
	private JLabel lblDiemDaSuDungv;
	private JLabel lblTongTienv;
	private JTextField txtTongTienGiam;
	private SanPhamBus sanPhamBus = new SanPhamBusImpl();
	public DoiTraHangGui(NhanVien nv) {
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(250, 0, 1285, 800);
		setLayout(null);
		this.nvHienTai = nv;
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(0, 0, 1270, 790);
		add(tabbedPane);

		JPanel pnlDonDoiTra = new JPanel();
		pnlDonDoiTra.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Đơn Đổi Trả", null, pnlDonDoiTra, null);
		tabbedPane.setForegroundAt(0, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(0, new Color(97, 166, 247));
		pnlDonDoiTra.setLayout(null);

		JPanel pnlThongTinHoaDon = new JPanel();
		pnlThongTinHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinHoaDon.setLayout(null);
		pnlThongTinHoaDon.setBackground(Color.WHITE);
		pnlThongTinHoaDon.setBounds(27, 28, 542, 161);
		pnlDonDoiTra.add(pnlThongTinHoaDon);

		JLabel lblTenKhachHang = new JLabel("Tên khách hàng:");
		lblTenKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHang.setBounds(27, 34, 137, 20);
		pnlThongTinHoaDon.add(lblTenKhachHang);

		lblTenKhachHangv = new JLabel("");
		lblTenKhachHangv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHangv.setBounds(155, 34, 154, 20);
		pnlThongTinHoaDon.add(lblTenKhachHangv);

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
		lblMaHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHoaDon.setBounds(27, 74, 104, 20);
		pnlThongTinHoaDon.add(lblMaHoaDon);

		lblMaHoaDonv = new JLabel("");
		lblMaHoaDonv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHoaDonv.setBounds(155, 74, 74, 20);
		pnlThongTinHoaDon.add(lblMaHoaDonv);

		JLabel lblNgayLapHoaDon = new JLabel("Ngày lập:");
		lblNgayLapHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayLapHoaDon.setBounds(295, 74, 85, 20);
		pnlThongTinHoaDon.add(lblNgayLapHoaDon);

		lblNgayLapHoaDonv = new JLabel("");
		lblNgayLapHoaDonv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayLapHoaDonv.setBounds(390, 74, 135, 20);
		pnlThongTinHoaDon.add(lblNgayLapHoaDonv);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(295, 34, 100, 20);
		pnlThongTinHoaDon.add(lblSDT);

		lblSDTv = new JLabel("");
		lblSDTv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDTv.setBounds(390, 34, 131, 20);
		pnlThongTinHoaDon.add(lblSDTv);

		JLabel lblimS = new JLabel("Điểm đã sử dụng:");
		lblimS.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblimS.setBounds(27, 118, 118, 20);
		pnlThongTinHoaDon.add(lblimS);

		lblDiemDaSuDungv = new JLabel("");
		lblDiemDaSuDungv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiemDaSuDungv.setBounds(155, 118, 74, 20);

		pnlThongTinHoaDon.add(lblDiemDaSuDungv);

		JLabel lblTngSnPhm = new JLabel("Tổng tiền:");
		lblTngSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngSnPhm.setBounds(295, 118, 85, 20);
		pnlThongTinHoaDon.add(lblTngSnPhm);

		lblTongTienv = new JLabel("");
		lblTongTienv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTienv.setBounds(390, 118, 124, 20);
		pnlThongTinHoaDon.add(lblTongTienv);

		JPanel pnlTimKiemHoaDon = new JPanel();
		pnlTimKiemHoaDon.setBackground(new Color(255, 255, 255));
		pnlTimKiemHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlTimKiemHoaDon.setBounds(27, 212, 542, 80);
		pnlDonDoiTra.add(pnlTimKiemHoaDon);
		pnlTimKiemHoaDon.setLayout(null);

		JLabel lblTimKiemHoaDon = new JLabel("Tìm kiếm hóa đơn");
		lblTimKiemHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTimKiemHoaDon.setBackground(new Color(255, 255, 255));
		lblTimKiemHoaDon.setBounds(52, 11, 150, 20);
		pnlTimKiemHoaDon.add(lblTimKiemHoaDon);

		txtTimKiemHoaDon = new JTextField();
		txtTimKiemHoaDon.setText("Nhập số điện thoại khách hàng");
		txtTimKiemHoaDon.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		txtTimKiemHoaDon.setToolTipText("");
		txtTimKiemHoaDon.setBounds(52, 36, 320, 24);
		pnlTimKiemHoaDon.add(txtTimKiemHoaDon);
		txtTimKiemHoaDon.setColumns(10);
		txtTimKiemHoaDon.setForeground(Color.GRAY);
		txtTimKiemHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(102, 102, 102), null, null, null));
		txtTimKiemHoaDon.setActionCommand("txtTimKiemHoaDon");
		btnTimKiemKhachHang = new MyButton("Tìm kiếm");
		btnTimKiemKhachHang.setForeground(new Color(255, 255, 255));
		btnTimKiemKhachHang.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnTimKiemKhachHang.setBounds(387, 36, 117, 24);
		btnTimKiemKhachHang.setActionCommand("btnTimKiemKhachHang");
		pnlTimKiemHoaDon.add(btnTimKiemKhachHang);

		JPanel pnlDanhSachHoaDon = new JPanel();
		pnlDanhSachHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlDanhSachHoaDon.setBackground(new Color(255, 255, 255));

		pnlDanhSachHoaDon.setBounds(27, 314, 542, 427);

		pnlDonDoiTra.add(pnlDanhSachHoaDon);
		pnlDanhSachHoaDon.setLayout(null);

		JLabel lblDanhSachHoaDon = new JLabel("Danh sách hóa đơn trong 7 ngày gần nhất");
		lblDanhSachHoaDon.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDanhSachHoaDon.setBounds(55, 11, 334, 18);
		pnlDanhSachHoaDon.add(lblDanhSachHoaDon);

		modelDanhSachHoaDon = new DefaultTableModel(
				new Object[] { "Mã hóa đơn", "Ngày lập", "Nhân viên", "Số lượng SP", "Tổng tiền" }, 0);

		tbDanhSachHoaDon = new MyTable(modelDanhSachHoaDon);
		tbDanhSachHoaDon.getColumnModel().getColumn(2).setPreferredWidth(120);
		tbDanhSachHoaDon.setName("tbDanhSachHoaDon");
		srcDanhSachHoaDon = new JScrollPane(tbDanhSachHoaDon, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		srcDanhSachHoaDon.setBounds(20, 40, 500, 394);

		pnlDanhSachHoaDon.add(srcDanhSachHoaDon);

		JPanel pnlSanPhamTrongHoaDon = new JPanel();
		pnlSanPhamTrongHoaDon.setBackground(new Color(255, 255, 255));
		pnlSanPhamTrongHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

		pnlSanPhamTrongHoaDon.setBounds(592, 28, 646, 332);

		pnlDonDoiTra.add(pnlSanPhamTrongHoaDon);
		pnlSanPhamTrongHoaDon.setLayout(null);

		JLabel lblDanhSachSanPham = new JLabel("Danh sách sản phẩm trong hóa đơn");
		lblDanhSachSanPham.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDanhSachSanPham.setBounds(24, 11, 250, 17);
		pnlSanPhamTrongHoaDon.add(lblDanhSachSanPham);

		modelDanhSachSanPham = new DefaultTableModel(
				new Object[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán" }, 0);

		tbDanhSachSanPham = new MyTable(modelDanhSachSanPham);

		JScrollPane srcDanhSachSanPham = new JScrollPane(tbDanhSachSanPham, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		srcDanhSachSanPham.setBounds(20, 35, 605, 240);

		pnlSanPhamTrongHoaDon.add(srcDanhSachSanPham);

		cbPhuongThucDoiTra = new MyCombobox();
		cbPhuongThucDoiTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		cbPhuongThucDoiTra.setModel(new DefaultComboBoxModel(new String[] { "Đổi Hàng", "Trả Tiền" }));
		cbPhuongThucDoiTra.setSelectedIndex(0);
		cbPhuongThucDoiTra.setBounds(44, 294, 230, 22);
		pnlSanPhamTrongHoaDon.add(cbPhuongThucDoiTra);

		btnTaoDonDoiTtra = new MyButton("Tạo đơn");
		btnTaoDonDoiTtra.setForeground(new Color(255, 255, 255));
		btnTaoDonDoiTtra.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnTaoDonDoiTtra.setBounds(360, 294, 120, 23);
		btnTaoDonDoiTtra.setActionCommand("btnTaoDonDoiTra");
		pnlSanPhamTrongHoaDon.add(btnTaoDonDoiTtra);

		btnThemSanPham = new MyButton("Xóa");
		btnThemSanPham.setText("Thêm");
		btnThemSanPham.setForeground(new Color(255, 255, 255));
		btnThemSanPham.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnThemSanPham.setBounds(505, 294, 120, 23);
		btnThemSanPham.setActionCommand("btnThemSanPham");
		pnlSanPhamTrongHoaDon.add(btnThemSanPham);

		JPanel pnlThongTinDonDoiTra = new JPanel();
		pnlThongTinDonDoiTra.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinDonDoiTra.setBackground(new Color(255, 255, 255));

		pnlThongTinDonDoiTra.setBounds(592, 384, 646, 357);

		pnlDonDoiTra.add(pnlThongTinDonDoiTra);
		pnlThongTinDonDoiTra.setLayout(null);

		lblMaDonDoiTra = new JLabel("Mã đơn đổi trả:");
		lblMaDonDoiTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaDonDoiTra.setBounds(39, 11, 100, 20);
		pnlThongTinDonDoiTra.add(lblMaDonDoiTra);

		txtMaDonDoiTra = new JTextField();
		txtMaDonDoiTra.setEnabled(false);
		txtMaDonDoiTra.setText("");
		txtMaDonDoiTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtMaDonDoiTra.setBounds(149, 11, 141, 20);
		txtMaDonDoiTra.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTra.add(txtMaDonDoiTra);
		txtMaDonDoiTra.setColumns(10);

		lblDiemHoanTra = new JLabel("Điểm hoàn trả:");
		lblDiemHoanTra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiemHoanTra.setBounds(321, 11, 100, 20);
		pnlThongTinDonDoiTra.add(lblDiemHoanTra);

		txtDiemHoanTra = new JTextField();
		txtDiemHoanTra.setHorizontalAlignment(SwingConstants.RIGHT);
		txtDiemHoanTra.setEnabled(false);
		txtDiemHoanTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtDiemHoanTra.setDisabledTextColor(Color.black);
		txtDiemHoanTra.setText("0");
		txtDiemHoanTra.setBounds(431, 12, 55, 20);

		pnlThongTinDonDoiTra.add(txtDiemHoanTra);
		txtDiemHoanTra.setColumns(10);

		lblCTKM = new JLabel("CTKM:");
		lblCTKM.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCTKM.setBounds(39, 46, 55, 20);
		pnlThongTinDonDoiTra.add(lblCTKM);

		txtCTKM = new JTextField();
		txtCTKM.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtCTKM.setEnabled(false);
		txtCTKM.setBounds(111, 46, 179, 20);
		txtCTKM.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTra.add(txtCTKM);
		txtCTKM.setColumns(10);

		modelDonDoiTra = new DefaultTableModel(
				new Object[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán", "Lý do" }, 0);

		tbDonDoiTra = new MyTable(modelDonDoiTra);

		JScrollPane srcDonDoiTra = new JScrollPane(tbDonDoiTra, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		srcDonDoiTra.setBounds(15, 80, 605, 178);

		pnlThongTinDonDoiTra.add(srcDonDoiTra);

		lblTongSoLuongDoi = new JLabel("Tổng số lượng sản phẩm đổi hàng:");
		lblTongSoLuongDoi.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblTongSoLuongDoi.setBounds(30, 282, 260, 20);

		pnlThongTinDonDoiTra.add(lblTongSoLuongDoi);

		txtTongSoLuongDoi = new JTextField();
		txtTongSoLuongDoi.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongSoLuongDoi.setEnabled(false);

		txtTongSoLuongDoi.setBounds(299, 285, 135, 20);

		txtTongSoLuongDoi.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTra.add(txtTongSoLuongDoi);
		txtTongSoLuongDoi.setColumns(10);
		txtTongSoLuongDoi.setText("0");

		lblTongTienTra = new JLabel("Tổng tiền phải trả:");
		lblTongTienTra.setFont(new Font("Tahoma", Font.PLAIN, 14));

		lblTongTienTra.setBounds(30, 312, 150, 20);

		pnlThongTinDonDoiTra.add(lblTongTienTra);

		txtTongTienTra = new JTextField();
		txtTongTienTra.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTongTienTra.setForeground(new Color(255, 0, 0));
		txtTongTienTra.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTienTra.setEnabled(false);
		txtTongTienTra.setText("0");
		txtTongTienTra.setColumns(10);

		txtTongTienTra.setBounds(299, 315, 135, 20);

		txtTongTienTra.setDisabledTextColor(Color.red);
		pnlThongTinDonDoiTra.add(txtTongTienTra);

		btnHoanThanhDon = new MyButton("Hoàn thành đơn");
		btnHoanThanhDon.setForeground(new Color(255, 255, 255));
		btnHoanThanhDon.setBackground(new Color(87, 255, 130));
		btnHoanThanhDon.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHoanThanhDon.setActionCommand("btnHoanThanhDon");
		btnHoanThanhDon.setBounds(481, 282, 139, 23);

		pnlThongTinDonDoiTra.add(btnHoanThanhDon);

		btnHuy = new MyButton("Hủy đơn");
		btnHuy.setForeground(new Color(255, 255, 255));
		btnHuy.setBackground(new Color(255, 83, 83));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setActionCommand("btnHuy");
		btnHuy.setBounds(481, 312, 139, 23);

		pnlThongTinDonDoiTra.add(btnHuy);

		btnXoaSanPham = new MyButton("Xóa");
		btnXoaSanPham.setForeground(Color.WHITE);
		btnXoaSanPham.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnXoaSanPham.setActionCommand("btnXoaSanPham");
		btnXoaSanPham.setBounds(500, 10, 120, 23);

		pnlThongTinDonDoiTra.add(btnXoaSanPham);

		JLabel lblTngTinGim = new JLabel("Tổng tiền giảm:");
		lblTngTinGim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTngTinGim.setBounds(321, 46, 120, 20);
		pnlThongTinDonDoiTra.add(lblTngTinGim);

		txtTongTienGiam = new JTextField();
		txtTongTienGiam.setText("0");
		txtTongTienGiam.setHorizontalAlignment(SwingConstants.RIGHT);
		txtTongTienGiam.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		txtTongTienGiam.setEnabled(false);
		txtTongTienGiam.setDisabledTextColor(Color.BLACK);
		txtTongTienGiam.setColumns(10);
		txtTongTienGiam.setBounds(431, 46, 189, 20);
		pnlThongTinDonDoiTra.add(txtTongTienGiam);

		JPanel pnlQuanLyDonDoiTra = new JPanel();
		pnlQuanLyDonDoiTra.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Quản Lý Đơn Đổi Trả", null, pnlQuanLyDonDoiTra, null);
		tabbedPane.setForegroundAt(1, new Color(255, 255, 255));
		tabbedPane.setBackgroundAt(1, new Color(97, 166, 247));
		pnlQuanLyDonDoiTra.setLayout(null);

		JPanel pnlThongTinDonDoiTraQL = new JPanel();
		pnlThongTinDonDoiTraQL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongTinDonDoiTraQL.setBackground(new Color(255, 255, 255));

		pnlThongTinDonDoiTraQL.setBounds(23, 25, 1214, 226);

		pnlQuanLyDonDoiTra.add(pnlThongTinDonDoiTraQL);
		pnlThongTinDonDoiTraQL.setLayout(null);

		JLabel lblMaDonDoiTraQL = new JLabel("Mã đơn đổi trả:");
		lblMaDonDoiTraQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaDonDoiTraQL.setBounds(114, 21, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblMaDonDoiTraQL);

		JLabel lblTenNhanVienQL = new JLabel("Tên nhân viên:");
		lblTenNhanVienQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenNhanVienQL.setBounds(114, 56, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblTenNhanVienQL);

		JLabel lblPhuongThucQL = new JLabel("Phương thức đổi trả:");
		lblPhuongThucQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhuongThucQL.setBounds(114, 91, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblPhuongThucQL);

		JLabel lblCTKMQL = new JLabel("Chương trình khuyến mãi:");
		lblCTKMQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCTKMQL.setBounds(114, 126, 179, 20);
		pnlThongTinDonDoiTraQL.add(lblCTKMQL);

		JLabel lblTongTienTraQL = new JLabel("Tổng tiền phải trả:");
		lblTongTienTraQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongTienTraQL.setBounds(114, 161, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblTongTienTraQL);

		txtMaDonDoiTraQL = new JTextField();
		txtMaDonDoiTraQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaDonDoiTraQL.setEnabled(false);
		txtMaDonDoiTraQL.setBounds(292, 21, 222, 20);
		txtMaDonDoiTraQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtMaDonDoiTraQL);
		txtMaDonDoiTraQL.setColumns(10);

		txtTenNhanVienQL = new JTextField();
		txtTenNhanVienQL.setEnabled(false);
		txtTenNhanVienQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenNhanVienQL.setColumns(10);
		txtTenNhanVienQL.setDisabledTextColor(Color.black);
		txtTenNhanVienQL.setBounds(292, 56, 222, 20);
		pnlThongTinDonDoiTraQL.add(txtTenNhanVienQL);

		txtPhuongThucQL = new JTextField();
		txtPhuongThucQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtPhuongThucQL.setEnabled(false);
		txtPhuongThucQL.setColumns(10);
		txtPhuongThucQL.setBounds(292, 91, 222, 20);
		txtPhuongThucQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtPhuongThucQL);

		txtCTKMQL = new JTextField();
		txtCTKMQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtCTKMQL.setEnabled(false);
		txtCTKMQL.setColumns(10);
		txtCTKMQL.setDisabledTextColor(Color.black);
		txtCTKMQL.setBounds(292, 126, 222, 20);
		pnlThongTinDonDoiTraQL.add(txtCTKMQL);

		txtTongTienTraQL = new JTextField();
		txtTongTienTraQL.setEnabled(false);
		txtTongTienTraQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTongTienTraQL.setColumns(10);
		txtTongTienTraQL.setBounds(292, 161, 222, 20);
		txtTongTienTraQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtTongTienTraQL);

		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgayLap.setBounds(708, 56, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblNgayLap);

		JLabel lblTenKhachHangQL = new JLabel("Tên khách hàng");
		lblTenKhachHangQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKhachHangQL.setBounds(708, 91, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblTenKhachHangQL);

		JLabel lblDiemHoanTraQL = new JLabel("Điểm hoàn trả:");
		lblDiemHoanTraQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiemHoanTraQL.setBounds(708, 126, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblDiemHoanTraQL);

		JLabel lblTongSoLuongDoiQL = new JLabel("Tổng số lượng đổi:");
		lblTongSoLuongDoiQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTongSoLuongDoiQL.setBounds(708, 161, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblTongSoLuongDoiQL);

		txtNgayLapQL = new JTextField();
		txtNgayLapQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtNgayLapQL.setEnabled(false);
		txtNgayLapQL.setColumns(10);
		txtNgayLapQL.setBounds(868, 56, 222, 20);
		txtNgayLapQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtNgayLapQL);

		txtTenKhachHangQL = new JTextField();
		txtTenKhachHangQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTenKhachHangQL.setEnabled(false);
		txtTenKhachHangQL.setColumns(10);
		txtTenKhachHangQL.setBounds(868, 91, 222, 20);
		txtTenKhachHangQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtTenKhachHangQL);

		txtDiemHoanTraQL = new JTextField();
		txtDiemHoanTraQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtDiemHoanTraQL.setEnabled(false);
		txtDiemHoanTraQL.setColumns(10);
		txtDiemHoanTraQL.setBounds(868, 126, 222, 20);
		txtDiemHoanTraQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtDiemHoanTraQL);

		txtTongSoLuongDoiQL = new JTextField();
		txtTongSoLuongDoiQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtTongSoLuongDoiQL.setEnabled(false);
		txtTongSoLuongDoiQL.setColumns(10);
		txtTongSoLuongDoiQL.setBounds(868, 161, 222, 20);
		txtTongSoLuongDoiQL.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtTongSoLuongDoiQL);

		JButton btnXoaTrangQL = new MyButton("Xóa trắng");
		btnXoaTrangQL.setForeground(new Color(255, 255, 255));
		btnXoaTrangQL.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnXoaTrangQL.setBounds(513, 192, 150, 25);
		btnXoaTrangQL.setActionCommand("btnXoaTrangQL");
		pnlThongTinDonDoiTraQL.add(btnXoaTrangQL);

		JLabel lblMaHoaDonQLDDT = new JLabel("Mã hóa đơn:");
		lblMaHoaDonQLDDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHoaDonQLDDT.setBounds(708, 21, 150, 20);
		pnlThongTinDonDoiTraQL.add(lblMaHoaDonQLDDT);

		txtMaHoaDonQLDDT = new JTextField();
		txtMaHoaDonQLDDT.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaHoaDonQLDDT.setEnabled(false);
		txtMaHoaDonQLDDT.setColumns(10);
		txtMaHoaDonQLDDT.setBounds(868, 21, 222, 20);
		txtMaHoaDonQLDDT.setDisabledTextColor(Color.black);
		pnlThongTinDonDoiTraQL.add(txtMaHoaDonQLDDT);

		JPanel pnlDanhSachSanPhamQL = new JPanel();
		pnlDanhSachSanPhamQL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlDanhSachSanPhamQL.setBackground(new Color(255, 255, 255));
		pnlDanhSachSanPhamQL.setBounds(23, 267, 609, 180);
		pnlQuanLyDonDoiTra.add(pnlDanhSachSanPhamQL);
		pnlDanhSachSanPhamQL.setLayout(null);

		JLabel lblDanhSachSanPhamQL = new JLabel("Danh sách sản phẩm trong đơn đổi trả");
		lblDanhSachSanPhamQL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDanhSachSanPhamQL.setBounds(20, 11, 277, 18);
		pnlDanhSachSanPhamQL.add(lblDanhSachSanPhamQL);

		modelSanPhamQL = new DefaultTableModel(
				new Object[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Giá bán", "Lý do" }, 0);
		tbSanPhamQL = new MyTable(modelSanPhamQL);

		JScrollPane scrDanhSachSanPhamQL = new JScrollPane(tbSanPhamQL, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrDanhSachSanPhamQL.setBounds(31, 40, 559, 129);
		pnlDanhSachSanPhamQL.add(scrDanhSachSanPhamQL);

		JPanel pnlTimKiemDonDoiTraQL = new JPanel();
		pnlTimKiemDonDoiTraQL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlTimKiemDonDoiTraQL.setBackground(new Color(255, 255, 255));

		pnlTimKiemDonDoiTraQL.setBounds(658, 267, 579, 180);

		pnlQuanLyDonDoiTra.add(pnlTimKiemDonDoiTraQL);
		pnlTimKiemDonDoiTraQL.setLayout(null);

		JLabel lblTimKiemQL = new JLabel("Tim kiếm đơn đổi trả");
		lblTimKiemQL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTimKiemQL.setBounds(20, 11, 150, 18);
		pnlTimKiemDonDoiTraQL.add(lblTimKiemQL);

		JLabel lblMaDonDoiTraTimKiemQL = new JLabel("Mã đơn đổi trả:");
		lblMaDonDoiTraTimKiemQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaDonDoiTraTimKiemQL.setBounds(113, 41, 100, 20);
		pnlTimKiemDonDoiTraQL.add(lblMaDonDoiTraTimKiemQL);

		JLabel lblSDTTimkiemQL = new JLabel("Số điện thoại:");
		lblSDTTimkiemQL.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDTTimkiemQL.setBounds(113, 71, 100, 20);
		pnlTimKiemDonDoiTraQL.add(lblSDTTimkiemQL);

		JLabel lblMaHDQLTimKiem = new JLabel("Mã hóa đơn:");
		lblMaHDQLTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaHDQLTimKiem.setBounds(113, 101, 100, 20);
		pnlTimKiemDonDoiTraQL.add(lblMaHDQLTimKiem);

		txtMaDonDoiTraTimKiemQL = new JTextField();
		txtMaDonDoiTraTimKiemQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaDonDoiTraTimKiemQL.setBounds(266, 40, 213, 20);
		txtMaDonDoiTraTimKiemQL.setName("txtMaDonDoiTraTimKiemQL");
		pnlTimKiemDonDoiTraQL.add(txtMaDonDoiTraTimKiemQL);
		txtMaDonDoiTraTimKiemQL.setColumns(10);

		txtSDTTimKiemQL = new JTextField();
		txtSDTTimKiemQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtSDTTimKiemQL.setColumns(10);
		txtSDTTimKiemQL.setBounds(266, 70, 213, 20);
		txtSDTTimKiemQL.setName("txtSDTTimKiemQL");
		pnlTimKiemDonDoiTraQL.add(txtSDTTimKiemQL);

		txtMaHDTimKiemQL = new JTextField();
		txtMaHDTimKiemQL.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMaHDTimKiemQL.setColumns(10);
		txtMaHDTimKiemQL.setBounds(266, 100, 213, 20);
		txtMaHDTimKiemQL.setName("txtMaHDTimKiemQL");
		pnlTimKiemDonDoiTraQL.add(txtMaHDTimKiemQL);

		JButton btnTaiLai = new MyButton("Tải lại");
		btnTaiLai.setForeground(new Color(255, 255, 255));
		btnTaiLai.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnTaiLai.setBounds(225, 143, 130, 27);
		btnTaiLai.setActionCommand("btnTaiLai");
		pnlTimKiemDonDoiTraQL.add(btnTaiLai);

		JPanel pnlDanhSachDonDoiTraQL = new JPanel();
		pnlDanhSachDonDoiTraQL.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlDanhSachDonDoiTraQL.setBackground(new Color(255, 255, 255));

		pnlDanhSachDonDoiTraQL.setBounds(23, 462, 1214, 291);

		pnlQuanLyDonDoiTra.add(pnlDanhSachDonDoiTraQL);
		pnlDanhSachDonDoiTraQL.setLayout(null);

		JLabel lblDanhSachDonDoiTraQL = new JLabel("Danh sách đơn đổi trả");
		lblDanhSachDonDoiTraQL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDanhSachDonDoiTraQL.setBounds(20, 15, 200, 20);
		pnlDanhSachDonDoiTraQL.add(lblDanhSachDonDoiTraQL);

		modelDonDoiTraQL = new DefaultTableModel(new Object[] { "Mã đơn đổi trả", "Mã hóa đơn", "Tên nhân viên",
				"Tên khách hàng", "Ngày lập", "Tổng số lượng SP" }, 0);

		// lấy dánh sách đơn đổi trả từ dữ liệu
//		busDDT.layDanhSachDonDoiTraVaoTable(modelDonDoiTraQL, dsDonDoiTra);
		tbDonDoiTraQL = new MyTable(modelDonDoiTraQL);
		tbDonDoiTraQL.setName("tbDonDoiTraQL");

		JScrollPane srcDanhSachDonDoiTraQL = new JScrollPane(tbDonDoiTraQL,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		srcDanhSachDonDoiTraQL.setBounds(30, 41, 1158, 228);
		pnlDanhSachDonDoiTraQL.add(srcDanhSachDonDoiTraQL);

		// thêm sự kiện
		txtTimKiemHoaDon.addFocusListener(new DonDoiTraController(this));
		txtTimKiemHoaDon.addActionListener(new DonDoiTraController(this));
		btnTimKiemKhachHang.addActionListener(new DonDoiTraController(this));
		tbDanhSachHoaDon.addMouseListener(new DonDoiTraController(this));
		btnTaoDonDoiTtra.addActionListener(new DonDoiTraController(this));
		btnThemSanPham.addActionListener(new DonDoiTraController(this));
		btnXoaSanPham.addActionListener(new DonDoiTraController(this));
		btnHoanThanhDon.addActionListener(new DonDoiTraController(this));
		btnHuy.addActionListener(new DonDoiTraController(this));
		tbDonDoiTraQL.addMouseListener(new DonDoiTraController(this));
		btnXoaTrangQL.addActionListener(new DonDoiTraController(this));
		txtMaHDTimKiemQL.addKeyListener(new DonDoiTraController(this));
		txtMaDonDoiTraTimKiemQL.addKeyListener(new DonDoiTraController(this));
		txtSDTTimKiemQL.addKeyListener(new DonDoiTraController(this));
		btnTaiLai.addActionListener(new DonDoiTraController(this));
	}

	// Xóa placeholder khi trường txtTimKiemHoaDon được chọn
	public void xoaPlaceholder() {
		if (txtTimKiemHoaDon.getText().equals("Nhập số điện thoại khách hàng")) {
			txtTimKiemHoaDon.setText("");
			txtTimKiemHoaDon.setForeground(Color.BLACK);
		}
	}

	// Thêm placeholder nếu trường txtTimKiemHoaDon không có dữ liệu
	public void themPlaceholder() {
		if (txtTimKiemHoaDon.getText().isEmpty()) {
			txtTimKiemHoaDon.setForeground(Color.GRAY);
			txtTimKiemHoaDon.setText("Nhập số điện thoại khách hàng");
			txtTimKiemHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
	}

	// Tìm kiếm hóa đơn của khách hàng trong 7 ngày gần nhất
	public void timKiemHoaDonCuaKhachHangTrong7Ngay() {
		if (new KhachHangBusImpl().timKhachHangTheoSDT(txtTimKiemHoaDon.getText()) != null) {
			System.out.println(txtTimKiemHoaDon.getText());
			busDDT.layDanhSachHoaDonKhachHangTrong7Ngay(txtTimKiemHoaDon.getText(), modelDanhSachHoaDon);
		} else {
			JOptionPane.showMessageDialog(this, "Số điện thoại này không tồn tại");
		}
	}

	// Hiện hóa đơn được chọn lên khung thông tin hóa đơn
	public void hienThongTinHoaDon() {
		int row = tbDanhSachHoaDon.getSelectedRow();
		HoaDon hd = busHD.TimHoaDonTheoMa(modelDanhSachHoaDon.getValueAt(row,0).toString());
		lblTenKhachHangv.setText(hd.getKhachHang().getTenKhachHang());
		lblSDTv.setText(hd.getKhachHang().getSdt());
		lblMaHoaDonv.setText(hd.getMaHoaDon());
		lblNgayLapHoaDonv.setText(hd.getNgayLap().toString());
		maHDHienTai = hd.getMaHoaDon();
		lblDiemDaSuDungv.setText(hd.getDiemGiamGia() + "");
		lblTongTienv.setText(Tool.dinhDangTien(hd.getThanhTien()) + "");
	}

	// Hiện danh sách sản phẩm của hóa đơn được chọn
	public void hienDSSanPham() {
		int row = tbDanhSachHoaDon.getSelectedRow();
		HoaDon hd = busHD.TimHoaDonTheoMa(modelDanhSachHoaDon.getValueAt(row, 0).toString());
		hd.getDsChiTietHoaDon().forEach(System.out::println);
		busDDT.hienDanhSachSanPhamTrongHoaDon(modelDanhSachSanPham, hd.getMaHoaDon());
	}

	// Tạo đơn đổi trả
	public void taoDonDoiTra() {
		HoaDon hd = busHD.TimHoaDonTheoMa(lblMaHoaDonv.getText());
		if (hd != null) {
			txtMaDonDoiTra.setText(busDDT.taoMaDonDoiTra(hd.getMaHoaDon()));
			txtCTKM.setText(hd.getCtkm().getTenCTKM());
			cbPhuongThucDoiTra.setEnabled(false);
			maDDTHienTai = txtMaDonDoiTra.getText();
			modelDonDoiTra.setRowCount(0);
			txtTongSoLuongDoi.setText("0");
			txtTongTienTra.setText("0");
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn hóa đơn cần đổi trả");
		}
	}

	// Thêm sản phẩm vào đơn đổi trả
	public void themSanPhamVaoDonDoiTra() {
		int row = tbDanhSachSanPham.getSelectedRow();
		if (maDDTHienTai.equals("")) {
			JOptionPane.showMessageDialog(this, "Hãy tạo đơn đổi trả trước khi thêm sản phẩm");
			return;
		}
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm để thêm vào");
		} else if (Integer.parseInt(maHDHienTai.substring(2, maHDHienTai.length())) == Integer
				.parseInt(maDDTHienTai.substring(3, 3 + (maHDHienTai.substring(2, maHDHienTai.length())).length()))) {
			String maSP = modelDanhSachSanPham.getValueAt(row, 0).toString();
			String tenSP = modelDanhSachSanPham.getValueAt(row, 1).toString();
			float gia = Float.parseFloat(modelDanhSachSanPham.getValueAt(row, 3).toString().replaceAll("[,VND]", ""));
			int soLuongSPTrongHD = (int) (modelDanhSachSanPham.getValueAt(row, 2));
			String phuongThuc = (String) cbPhuongThucDoiTra.getSelectedItem();
			int diemTrongHD = busHD.TimHoaDonTheoMa(maHDHienTai).getDiemGiamGia();

			new SanPhamDoiTraGui(modelDonDoiTra, tbDonDoiTra, maSP, tenSP, gia, soLuongSPTrongHD, phuongThuc,
					txtTongSoLuongDoi, txtTongTienTra, txtDiemHoanTra, diemTrongHD,
					busHD.TimHoaDonTheoMa(maHDHienTai).getCtkm(), txtTongTienGiam).setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this,
					"Mỗi đơn đổi trả chỉ ứng với một hóa đơn, không được thêm sản phẩm của hóa đơn khác vào");
		}
	}

	// Xóa sản phẩm ra khỏi đơn đổi trả
	public void xoaSanPhamRaDonDoiTra() {
		int row[] = tbDonDoiTra.getSelectedRows();
		if (row.length == 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa");
		} else {
			if ((JOptionPane.showConfirmDialog(this, "Bạn muốn xóa sản phẩm ra khỏi đơn đổi trả", "Xóa sản phẩm",
					JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
				int i = row.length - 1;
				while (i >= 0) {
					modelDonDoiTra.removeRow(row[i]);
					i--;

				}
				busDDT.tinhTongDDT((String) cbPhuongThucDoiTra.getSelectedItem(), tbDonDoiTra, txtTongTienTra,
						txtTongSoLuongDoi, txtDiemHoanTra, busHD.TimHoaDonTheoMa(maHDHienTai).getDiemGiamGia(),
						busHD.TimHoaDonTheoMa(maHDHienTai).getCtkm(), txtTongTienGiam);
			}
		}
	}

	// Hoàn thành danh sách chi tiết đơn đổi trả
	public List<ChiTietDonDoiTra> hoanThanhChiTietDonDoiTra() {
		List<ChiTietDonDoiTra> ds = new ArrayList<ChiTietDonDoiTra>();
		for (int i = 0; i < tbDonDoiTra.getRowCount(); i++) {
			int soLuong = Integer.parseInt(tbDonDoiTra.getValueAt(i, 2).toString());
			String lyDo = tbDonDoiTra.getValueAt(i, 4).toString();
			SanPham sp = sanPhamBus.timKiemSanPham(tbDonDoiTra.getValueAt(i, 0).toString());
			float giaBan = Float.parseFloat(tbDonDoiTra.getValueAt(i, 3).toString().replaceAll("[,VND]", ""));
			ds.add(new ChiTietDonDoiTra(soLuong, lyDo, sp, giaBan));
		}
		return ds;
	}

	// Hoàn thành đơn đổi trả
	public void hoanThanhDonDoiTra() {
		String maDonDoiTra = txtMaDonDoiTra.getText();
		LocalDate ngayDoiTra = LocalDate.now();
		List<ChiTietDonDoiTra> ds = hoanThanhChiTietDonDoiTra();

		HoaDon hd = busHD.TimHoaDonTheoMa(maHDHienTai);
		String phuongThucDoiTra = cbPhuongThucDoiTra.getSelectedItem().toString();
		int diemHT = Integer.parseInt(txtDiemHoanTra.getText());
		DonDoiTra ddt = new DonDoiTra(maDonDoiTra, ngayDoiTra, phuongThucDoiTra, diemHT, hd,nvHienTai, ds);
//		ddt.setDsChiTietDonDoiTra(ds);
//		ddt.getDsChiTietDonDoiTra().forEach(System.out::println);
		if (maDonDoiTra.equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo đơn đổi trả");
		} else {
			if (ds.size() == 0) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm vào đơn đổi trả");
			} else {
				if (busDDT.themDonDoiTra(ddt)) {
					JOptionPane.showMessageDialog(this, "Đã thêm đơn đổi trả vào dữ liệu");
					xoaTrang();

					// chạy riêng luong
					Thread t = new Thread(()-> {
						modelDonDoiTraQL.setRowCount(0);
						dsDonDoiTra = busDDT.layHetDSDonDoiTra();
						busDDT.layDanhSachDonDoiTraVaoTable(modelDonDoiTraQL, dsDonDoiTra);
					});
					t.start();

					ddt.getHoaDon().getKhachHang()
							.setDiemTichLuy(ddt.getDiemHoanTra() + ddt.getHoaDon().getKhachHang().getDiemTichLuy());
					for (ChiTietDonDoiTra ctddt : ds) {
						SanPham spCapNhat = ctddt.getSanPham();
						spCapNhat.setSoLuongTon(spCapNhat.getSoLuongTon() - ctddt.getSoLuongTra());
						sanPhamBus.capNhatSoLuongTonSanPham(spCapNhat);
					}
					t.stop();
				} else {
					JOptionPane.showMessageDialog(this, "Không thành công");
				}
			}
		}

	}

	// Hàm xóa trắng các thông tin
	public void xoaTrang() {

		txtCTKM.setText("");
		txtMaDonDoiTra.setText("");
		txtDiemHoanTra.setText("0");
		cbPhuongThucDoiTra.setEnabled(true);
		modelDonDoiTra.setRowCount(0);
		txtTongTienTra.setText("0");
		txtTongSoLuongDoi.setText("0");
		maDDTHienTai = "";

	}

	public void huyDDT() {
		if (txtMaDonDoiTra.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo đơn đổi trả");
		} else {
			if ((JOptionPane.showConfirmDialog(this, "Bạn muốn hủy đơn đổi trả", "Hủy",
					JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION) {
				txtCTKM.setText("");
				txtMaDonDoiTra.setText("");
				txtDiemHoanTra.setText("0");
				cbPhuongThucDoiTra.setEnabled(true);
				modelDonDoiTra.setRowCount(0);
				txtTongTienTra.setText("0");
				txtTongSoLuongDoi.setText("0");
				maDDTHienTai = "";
				txtTongTienGiam.setText("0");
			}
		}
	}

	// Hiện thông tin đơn đổi trả được chọn
	public void hienThongTinDonDoiTra() {
		int row = tbDonDoiTraQL.getSelectedRow();
		DonDoiTra ddt = busDDT.layDonDoiTraTheoMa(tbDonDoiTraQL.getValueAt(row, 0).toString());
		txtMaDonDoiTraQL.setText(ddt.getMaDonDoiTra());
		txtTenNhanVienQL.setText(ddt.getNhanVien().getTenNhanVien());
		txtCTKMQL.setText(ddt.getHoaDon().getCtkm().getTenCTKM());
		txtDiemHoanTraQL.setText(ddt.getDiemHoanTra() + "");
		txtMaHoaDonQLDDT.setText(ddt.getHoaDon().getMaHoaDon());
		txtNgayLapQL.setText(ddt.getNgayDoiTra() + "");
		txtPhuongThucQL.setText(ddt.getPhuongThucDoiTra());
		txtTenKhachHangQL.setText(ddt.getHoaDon().getKhachHang().getTenKhachHang());
		txtTongSoLuongDoiQL.setText(ddt.tinhSoLuongDoiHang() + "");
		txtTongTienTraQL.setText(Tool.dinhDangTien(ddt.tinhTienCanTra()) + "");
		hienDanhSachChiTietDonDoiTra(ddt.getDsChiTietDonDoiTra());
	}

	// Hiện thông tin danh sách chi tiết đơn đổi trả
	public void hienDanhSachChiTietDonDoiTra(List<ChiTietDonDoiTra> ds) {
		modelSanPhamQL.setRowCount(0);
		for (ChiTietDonDoiTra ctddt : ds) {
			modelSanPhamQL.addRow(new Object[] { ctddt.getSanPham().getMaSanPham(), ctddt.getSanPham().getTenSanPham(),
					ctddt.getSoLuongTra(), Tool.dinhDangTien(ctddt.getGiaBan()), ctddt.getLyDo() });
		}
	}

	// Xóa các thông tin đơn đổi trả có sản
	public void xoaThongTinDonDoiTraCoSan() {
		txtMaDonDoiTraQL.setText("");
		txtTenNhanVienQL.setText("");
		txtCTKMQL.setText("");
		txtDiemHoanTraQL.setText("");
		txtMaHoaDonQLDDT.setText("");
		txtNgayLapQL.setText("");
		txtPhuongThucQL.setText("");
		txtTenKhachHangQL.setText("");
		txtTongSoLuongDoiQL.setText("");
		txtTongTienTraQL.setText("");
		modelSanPhamQL.setRowCount(0);
		tbDonDoiTraQL.clearSelection();
	}

	// Tìm kiếm thông tin đơn đổi trả bằng mã đơn đổi trả, số điện thoại, mã hóa đơn
	public void timKiemDonDoiTra() {
		busDDT.timKiemBangMaDonDoiTra(dsDonDoiTra, txtMaDonDoiTraTimKiemQL.getText());
		busDDT.timKiemBangSDT(dsDonDoiTra, txtSDTTimKiemQL.getText());
		busDDT.timKiemBangMaHoaDon(dsDonDoiTra, txtMaHDTimKiemQL.getText());
		modelDonDoiTraQL.setRowCount(0);
		busDDT.layDanhSachDonDoiTraVaoTable(modelDonDoiTraQL, dsDonDoiTra);
		dsDonDoiTra = busDDT.layHetDSDonDoiTra();
	}

	// Tải lại danh sách đơn đổi trả sau khi tìm kiếm
	public void taiLaiDSDDT() {
		modelDonDoiTraQL.setRowCount(0);
		busDDT.layDanhSachDonDoiTraVaoTable(modelDonDoiTraQL, dsDonDoiTra);
		txtMaDonDoiTraTimKiemQL.setText("");
		txtSDTTimKiemQL.setText("");
		txtMaHDTimKiemQL.setText("");

	}
}
