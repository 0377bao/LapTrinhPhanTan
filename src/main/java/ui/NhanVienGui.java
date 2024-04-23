package ui;

import bus.impl.NhanVienBusImpl;
import bus.impl.TaiKhoanBusImpl;
import controller.NhanVienController;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;
import java.util.List;

public class NhanVienGui extends JPanel {
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtSdt;
	private JTextField txtCCCD;
	private JTextField txtEmail;
	private JTextField txtDiaChi;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private JComboBox<String> cboChucVu;
	private JLabel lblHinhAnh;
	private JButton btnHinhAnh;
	private JButton btnTaoMa;
	private JTextField txtTimNVTheoMa;
	private JTextField txtTimTheoSdt_Ten;
	private JButton btnTimTheoMa;
	private JButton btnLocGioiTinh;
	private JButton btnLocChucVu;
	private JButton btnThemNV;
	private JButton btnCapNhatNV;
	private JButton btnXoaTrang;
	private JButton btnTaiLai;
	private JButton btnLocTrangThai;
	private DefaultTableModel modelNV;
	private MyTable table;
	private JScrollPane scr;
	private JTextField txtTenTK;
	private JPasswordField txtMatKhau;
	private String anh;
	private JCheckBox chkTrangThai;

	private NhanVienBusImpl busNhanVien = new NhanVienBusImpl();
	private TaiKhoanBusImpl busTaiKhoan = new TaiKhoanBusImpl();
	private List<NhanVien> dsNV = busNhanVien.layDSNhanVien();

	public NhanVienGui() throws RemoteException {
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(250, 0, 1285, 800);
		setLayout(null);

		JPanel pnl = new JPanel();
		pnl.setBackground(new Color(240, 240, 240));
		pnl.setBounds(25, 10, 1250, 780);
		this.add(pnl);
		pnl.setLayout(null);

		JPanel pnlThongTinNV = new JPanel();
		pnlThongTinNV.setBackground(new Color(255, 255, 255));
		pnlThongTinNV.setBorder(new TitledBorder(null, "Th\u00F4ng Tin Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlThongTinNV.setBounds(20, 20, 672, 365);
		pnl.add(pnlThongTinNV);
		pnlThongTinNV.setLayout(null);

		JPanel pnlHinhAnh = new JPanel();
		pnlHinhAnh.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlHinhAnh.setBounds(30, 30, 171, 228);
		pnlHinhAnh.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnlThongTinNV.add(pnlHinhAnh);

		lblHinhAnh = new JLabel("");
		pnlHinhAnh.add(lblHinhAnh);

		btnHinhAnh = new MyButton("Chọn ảnh");
		btnHinhAnh.setForeground(new Color(255, 255, 255));
		btnHinhAnh.setBounds(70, 270, 90, 21);
		btnHinhAnh.setActionCommand("btnHinhAnh");
		pnlThongTinNV.add(btnHinhAnh);

		JLabel lblMaNV = new JLabel("Mã nhân viên");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMaNV.setBounds(247, 35, 90, 15);
		pnlThongTinNV.add(lblMaNV);

		JLabel lblTenNV = new JLabel("Tên nhân viên");
		lblTenNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenNV.setBounds(247, 77, 90, 15);
		pnlThongTinNV.add(lblTenNV);

		JLabel lblSdt = new JLabel("Số điện thoại");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblSdt.setBounds(247, 119, 90, 15);
		pnlThongTinNV.add(lblSdt);

		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGioiTinh.setBounds(247, 159, 90, 15);
		pnlThongTinNV.add(lblGioiTinh);

		JLabel lblCCCD = new JLabel("CCCD");
		lblCCCD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCCCD.setBounds(247, 199, 90, 15);
		pnlThongTinNV.add(lblCCCD);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblEmail.setBounds(247, 242, 90, 15);
		pnlThongTinNV.add(lblEmail);

		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblChucVu.setBounds(247, 283, 90, 15);
		pnlThongTinNV.add(lblChucVu);

		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDiaChi.setBounds(247, 325, 45, 15);
		pnlThongTinNV.add(lblDiaChi);

		txtMaNV = new JTextField();
		txtMaNV.setEnabled(false);
		txtMaNV.setEditable(false);
		txtMaNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaNV.setBounds(337, 30, 191, 25);
		pnlThongTinNV.add(txtMaNV);
		txtMaNV.setColumns(10);

		txtTenNV = new JTextField();
		txtTenNV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenNV.setBounds(337, 72, 300, 25);
		pnlThongTinNV.add(txtTenNV);
		txtTenNV.setColumns(10);

		txtSdt = new JTextField();
		txtSdt.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSdt.setBounds(337, 114, 300, 25);
		pnlThongTinNV.add(txtSdt);
		txtSdt.setColumns(10);

		txtCCCD = new JTextField();
		txtCCCD.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtCCCD.setBounds(337, 194, 300, 25);
		pnlThongTinNV.add(txtCCCD);
		txtCCCD.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtEmail.setBounds(337, 236, 300, 25);
		pnlThongTinNV.add(txtEmail);
		txtEmail.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtDiaChi.setBounds(337, 320, 300, 25);
		pnlThongTinNV.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		radNam = new JRadioButton("Nam");
		radNam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radNam.setBackground(new Color(255, 255, 255));
		radNam.setSelected(true);
		radNam.setBounds(337, 156, 90, 21);
		pnlThongTinNV.add(radNam);

		radNu = new JRadioButton("Nữ");
		radNu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		radNu.setBackground(new Color(255, 255, 255));
		radNu.setBounds(429, 156, 90, 21);
		pnlThongTinNV.add(radNu);

		ButtonGroup gr = new ButtonGroup();
		gr.add(radNu);
		gr.add(radNam);

		cboChucVu = new MyCombobox();
		cboChucVu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cboChucVu.setBounds(337, 278, 300, 25);
		pnlThongTinNV.add(cboChucVu);

		// dữ liệu mẫu
		cboChucVu.addItem("Bán hàng");
		cboChucVu.addItem("Quản lý");

		btnTaoMa = new MyButton("Tạo mã");
		btnTaoMa.setForeground(new Color(255, 255, 255));
		btnTaoMa.setBounds(541, 30, 96, 25);
		btnTaoMa.setActionCommand("btnTaoMa");
		pnlThongTinNV.add(btnTaoMa);

		// tìm kiếm
		JPanel pnlTimKiemNV = new JPanel();
		pnlTimKiemNV.setBackground(new Color(255, 255, 255));
		pnlTimKiemNV.setBorder(new TitledBorder(null, "T\u00ECm Ki\u1EBFm", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlTimKiemNV.setBounds(20, 400, 1208, 75);
		pnl.add(pnlTimKiemNV);
		pnlTimKiemNV.setLayout(null);

		txtTimNVTheoMa = new JTextField();
		txtTimNVTheoMa.setText("Nhập mã nhân viên cần tìm");
		txtTimNVTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtTimNVTheoMa.setForeground(new Color(128, 128, 128));
		txtTimNVTheoMa.setBounds(30, 25, 185, 28);
		pnlTimKiemNV.add(txtTimNVTheoMa);
		txtTimNVTheoMa.setColumns(10);
		txtTimNVTheoMa.setName("txtTimNVTheoMa");

		btnTimTheoMa = new MyButton("Tìm");
		btnTimTheoMa.setForeground(new Color(255, 255, 255));
		btnTimTheoMa.setBounds(223, 30, 85, 21);
		btnTimTheoMa.setActionCommand("btnTimTheoMa");
		pnlTimKiemNV.add(btnTimTheoMa);

		txtTimTheoSdt_Ten = new JTextField();
		txtTimTheoSdt_Ten.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTimTheoSdt_Ten.setForeground(new Color(0, 0, 0));
		txtTimTheoSdt_Ten.setBounds(730, 25, 449, 25);
		pnlTimKiemNV.add(txtTimTheoSdt_Ten);
		txtTimTheoSdt_Ten.setName("txtTimTheoSdt_Ten");
		txtTimTheoSdt_Ten.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nhập số điện thoại hoặc tên nhân viên cần tìm");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(441, 32, 312, 15);
		pnlTimKiemNV.add(lblNewLabel);

		JPanel pnlTaiKhoan = new JPanel();
		pnlTaiKhoan.setBorder(new TitledBorder(null, "T\u1EA1o t\u00E0i kho\u1EA3n", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlTaiKhoan.setForeground(new Color(0, 0, 0));
		pnlTaiKhoan.setBackground(new Color(255, 255, 255));
		pnlTaiKhoan.setBounds(708, 20, 520, 120);
		pnl.add(pnlTaiKhoan);
		pnlTaiKhoan.setLayout(null);

		JLabel lblTenTK = new JLabel("Tên tài khoản");
		lblTenTK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTenTK.setBounds(70, 35, 90, 15);
		pnlTaiKhoan.add(lblTenTK);

		JLabel lblMatKhau = new JLabel("Mật khẩu");
		lblMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatKhau.setBounds(70, 77, 90, 15);
		pnlTaiKhoan.add(lblMatKhau);

		txtTenTK = new JTextField();
		txtTenTK.setEnabled(false);
		txtTenTK.setEditable(false);
		txtTenTK.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenTK.setBounds(163, 30, 280, 25);
		pnlTaiKhoan.add(txtTenTK);
		txtTenTK.setColumns(10);

		txtMatKhau = new JPasswordField();
		txtMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMatKhau.setBounds(163, 72, 280, 25);
		pnlTaiKhoan.add(txtMatKhau);
		txtMatKhau.setColumns(10);

		// chức năng
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(255, 255, 255));
		pnlChucNang.setBorder(new TitledBorder(null, "Ch\u1EE9c N\u0103ng", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlChucNang.setBounds(708, 155, 520, 140);
		pnl.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		btnThemNV = new MyButton("Thêm");
		btnThemNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemNV.setForeground(new Color(255, 255, 255));
		btnThemNV.setBounds(70, 35, 150, 30);
		btnThemNV.setActionCommand("btnThemNV");
		pnlChucNang.add(btnThemNV);

		btnCapNhatNV = new MyButton("Cặp nhật");
		btnCapNhatNV.setBackground(new Color(255, 128, 0));
		btnCapNhatNV.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCapNhatNV.setForeground(new Color(255, 255, 255));
		btnCapNhatNV.setText("Cập nhật");
		btnCapNhatNV.setBounds(290, 35, 150, 30);
		btnCapNhatNV.setActionCommand("btnCapNhatNV");
		pnlChucNang.add(btnCapNhatNV);

		btnXoaTrang = new MyButton("Xóa trắng");
		btnXoaTrang.setBackground(new Color(255, 45, 45));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaTrang.setForeground(new Color(255, 255, 255));
		btnXoaTrang.setBounds(70, 89, 150, 30);
		btnXoaTrang.setActionCommand("btnXoaTrang");
		pnlChucNang.add(btnXoaTrang);

		btnTaiLai = new MyButton("Tải lại");
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTaiLai.setForeground(new Color(255, 255, 255));
		btnTaiLai.setBounds(290, 89, 150, 30);
		btnTaiLai.setActionCommand("btnTaiLai");
		pnlChucNang.add(btnTaiLai);

		JPanel pnlLocNV = new JPanel();
		pnlLocNV.setBounds(708, 309, 520, 75);
		pnl.add(pnlLocNV);
		pnlLocNV.setBorder(new TitledBorder(null, "L\u1ECDc Nh\u00E2n Vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlLocNV.setBackground(new Color(255, 255, 255));
		pnlLocNV.setLayout(null);

		btnLocGioiTinh = new MyButton("Lọc theo giới tính");
//		 btnLocGioiTinh.setIcon(new
//		 ImageIcon(GUINhanVien.class.getResource("/image/sanpham/icons8-filter-16.png")));
		btnLocGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocGioiTinh.setForeground(new Color(255, 255, 255));
		btnLocGioiTinh.setText("Giới tính");
		btnLocGioiTinh.setBounds(50, 25, 100, 30);
		btnLocGioiTinh.setActionCommand("btnLocGioiTinh");
		pnlLocNV.add(btnLocGioiTinh);

		btnLocChucVu = new MyButton("Lọc theo chức vụ");
		// btnLocChucVu.setIcon(new
		// ImageIcon(GUINhanVien.class.getResource("/image/sanpham/icons8-filter-16.png")));
		btnLocChucVu.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocChucVu.setForeground(new Color(255, 255, 255));
		btnLocChucVu.setText("Chức vụ");
		btnLocChucVu.setBounds(216, 25, 100, 30);
		btnLocChucVu.setActionCommand("btnLocChucVu");
		pnlLocNV.add(btnLocChucVu);

		btnLocTrangThai = new MyButton("Lọc theo trạng thái");
		btnLocTrangThai.setText("Trạng thái");
		btnLocTrangThai.setForeground(Color.WHITE);
		btnLocTrangThai.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLocTrangThai.setActionCommand("btnLocTrangThai");
		btnLocTrangThai.setBounds(382, 25, 100, 30);
		pnlLocNV.add(btnLocTrangThai);

		// tạo bảng
		JPanel pnlTable = new JPanel();
		pnlTable.setBorder(new TitledBorder(null, "Danh s\u00E1ch nh\u00E2n vi\u00EAn", TitledBorder.LEADING,
				TitledBorder.BELOW_TOP, null, new Color(0, 0, 0)));
		pnlTable.setBackground(new Color(255, 255, 255));
		pnlTable.setBounds(20, 490, 1208, 280);
		pnl.add(pnlTable);
		String[] cols = { "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Giới tính", "CCCD", "Email", "Chức vụ",
				"Địa chỉ" };
		modelNV = new DefaultTableModel(cols, 0);
		pnlTable.setLayout(null);
		table = new MyTable(modelNV);
		scr = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scr.setLocation(20, 25);
		scr.setSize(1166, 237);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(300);
		pnlTable.add(scr);

		// load dữ liệu lên bảng
		hienThiDS(busNhanVien.locNVTheoTrangThai("Đang làm"));

		// dữ liệu test
		txtTenNV.setText("Lê Thị Thúy Kiều");
		txtDiaChi.setText("Tây Ninh");
		txtCCCD.setText("072202045786");
		txtSdt.setText("0785498147");
		txtEmail.setText("abc@gmail.com");

		JLabel lblTrangThai = new JLabel("Trạng thái");
		lblTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTrangThai.setBounds(30, 325, 80, 15);
		pnlThongTinNV.add(lblTrangThai);

		chkTrangThai = new JCheckBox("Đang làm");
		chkTrangThai.setSelected(true);
		chkTrangThai.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chkTrangThai.setForeground(new Color(0, 0, 0));
		chkTrangThai.setBackground(new Color(255, 255, 255));
		chkTrangThai.setBounds(108, 321, 93, 25);
		pnlThongTinNV.add(chkTrangThai);

		// sự kiện
		ActionListener ac = new NhanVienController(this);

		btnHinhAnh.addActionListener(ac);
		btnTaoMa.addActionListener(ac);
		btnThemNV.addActionListener(ac);
		btnCapNhatNV.addActionListener(ac);
		btnTaiLai.addActionListener(ac);
		btnXoaTrang.addActionListener(ac);
		btnLocGioiTinh.addActionListener(ac);
		btnLocChucVu.addActionListener(ac);
		btnLocTrangThai.addActionListener(ac);
		btnTimTheoMa.addActionListener(ac);

		txtTimNVTheoMa.addFocusListener(new NhanVienController(this));
		table.addMouseListener(new NhanVienController(this));
		txtTimTheoSdt_Ten.addKeyListener(new NhanVienController(this));
		txtTimNVTheoMa.addKeyListener(new NhanVienController(this));
	}

	// lọc nhân viên theo trạng thái
	public void locNVTheoTrangThai() throws RemoteException {
		String trangThai = null;
		if (chkTrangThai.isSelected()) {
			trangThai = "Đang làm";
		} else {
			trangThai = "Đã nghỉ";
		}
		xoaDuLieuBang();
		hienThiDS(busNhanVien.locNVTheoTrangThai(trangThai));
	}

	// tìm nhân viên theo sdt hoặc tên
	public void timNVTheoSdt_Ten() throws RemoteException {
		xuLyTimTheoSdt_Ten(busNhanVien.layDSNhanVien());
	}

	// xử lý tìm nhân viên theo sdt hoặc tên
	public void xuLyTimTheoSdt_Ten(List<NhanVien> ds) throws RemoteException {
		String txt = txtTimTheoSdt_Ten.getText().trim();
		busNhanVien.timNVTheoSdt(ds, txt);
		busNhanVien.timNVTheoTen(ds, txt);
		xoaDuLieuBang();
		hienThiDS(ds);
	}

	// tìm nhân viên theo mã
	public void timNVTheoMa() throws RemoteException {
		String maNV = txtTimNVTheoMa.getText().trim();
		if (maNV.isEmpty() || maNV.equals("Nhập mã nhân viên cần tìm")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên cần tìm");
		} else {
			NhanVien nv = busNhanVien.layNhanVienTheoMa(maNV);
			if (nv == null) {
				xoaDuLieuBang();
			} else {
				xoaDuLieuBang();
				modelNV.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSdt(),
						nv.isGioiTinh() ? "Nam" : "Nữ", nv.getcCCD(), nv.getEmail(), nv.getChucVu(), nv.getDiaChi(),
						busNhanVien.layMatKhauNhanVienTheoMa(maNV) });
			}
		}
	}

	// tải lại danh sách
	public void taiLai() throws RemoteException {
		radNam.setSelected(true);
		cboChucVu.setSelectedIndex(0);
		chkTrangThai.setSelected(true);
		xoaDuLieuBang();
		hienThiDS(busNhanVien.locNVTheoTrangThai("Đang làm"));
		txtTimTheoSdt_Ten.setText("");
		txtTimNVTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtTimNVTheoMa.setForeground(Color.GRAY);
		txtTimNVTheoMa.setText("Nhập mã nhân viên cần tìm");
	}

	// lọc nhân viên theo chức vụ
	public void locNVTheoChucVu() throws RemoteException {
		String chucVu = cboChucVu.getSelectedItem().toString();
		xoaDuLieuBang();
		hienThiDS(busNhanVien.locNVTheoChucVu(chucVu));
	}

	// lọc nhân viên theo giới tính
	public void locNVTheoGioiTinh() throws RemoteException {
		boolean trangThaiGioiTinh;
		if (radNam.isSelected()) {
			trangThaiGioiTinh = true;
		} else {
			trangThaiGioiTinh = false;
		}
		xoaDuLieuBang();
		hienThiDS(busNhanVien.locNVTheoGioiTinh(trangThaiGioiTinh));
	}

	// hiển thị danh sách
	public void hienThiDS(List<NhanVien> dsNV) {
		for (NhanVien nv : dsNV) {
			modelNV.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getSdt(),
					nv.isGioiTinh() ? "Nam" : "Nữ", nv.getcCCD(), nv.getEmail(), nv.getChucVu(), nv.getDiaChi() });
		}
	}

	// cập nhật
	public void capNhatNV() throws RemoteException {
		int r = table.getSelectedRow();
		if (r < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa");
			return;
		} else {
			String maNV = txtMaNV.getText().trim();
			String tenNV = txtTenNV.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			boolean gt = true;
			if (radNam.isSelected()) {
				gt = true;
			} else {
				gt = false;
			}
			String diaChi = txtDiaChi.getText();
			String chucVu = (String) cboChucVu.getSelectedItem();
			String cccd = txtCCCD.getText();
			String hinhAnh = anh;
			String trangThai;
			if (chkTrangThai.isSelected()) {
				trangThai = "Đang làm";
			} else {
				trangThai = "Đã nghỉ";
			}
			String mk = txtMatKhau.getText();
			if (busNhanVien.validData(maNV, tenNV, sdt, email, hinhAnh, diaChi, cccd, mk)) {

				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, gt, diaChi, chucVu, cccd, hinhAnh, trangThai);
				TaiKhoan tk = new TaiKhoan(nv, mk);
				if (busNhanVien.capNhatNV(nv)) {
					taiLai();
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Cập nhật thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Không thể cập nhật mã");
				}
			} else {
				JOptionPane.showMessageDialog(this, busNhanVien.mes);
			}
		}
	}

	public void xoaDuLieuBang() {
		while (modelNV.getRowCount() != 0) {
			modelNV.removeRow(0);
		}
	}

	// thêm nhân viên
	public void themNhanVien() throws RemoteException {
		String maNV = txtMaNV.getText().trim();
		String tenNV = txtTenNV.getText().trim();
		String sdt = txtSdt.getText().trim();
		String email = txtEmail.getText().trim();
		String diaChi = txtDiaChi.getText();
		String cccd = txtCCCD.getText();
		String chucVu = (String) cboChucVu.getSelectedItem();
		String hinhAnh = anh;
		String trangThai;
		if (chkTrangThai.isSelected()) {
			trangThai = "Đang làm";
		} else {
			trangThai = "Đã nghỉ";
		}
		boolean gt;
		if (radNam.isSelected()) {
			gt = true;
		} else {
			gt = false;
		}
		String mk = txtMatKhau.getText();

		if (table.getSelectedRow() != -1) {
			JOptionPane.showMessageDialog(this, "Đang trong chế độ sửa không được phép thêm");
		} else {
			if (busNhanVien.validData(maNV, tenNV, sdt, email, hinhAnh, diaChi, cccd, mk)) {
				NhanVien nv = new NhanVien(maNV, tenNV, sdt, email, gt, diaChi, chucVu, cccd, hinhAnh, trangThai);
				TaiKhoan tk = new TaiKhoan(nv, mk);
				if (busNhanVien.themNhanVien(nv, tk)) {
					taiLai();
					xoaTrang();
					JOptionPane.showMessageDialog(this, "Thêm thành công");
				} else {
					JOptionPane.showMessageDialog(this, "Thêm thất bại");
				}
			} else {
				JOptionPane.showMessageDialog(this, busNhanVien.mes);
			}
		}
	}

	// tạo mã
	public void taoMa() throws RemoteException {
		txtMaNV.setText(busNhanVien.taoMaNV());
		txtTenTK.setText(busNhanVien.taoMaNV());
	}

	// chọn ảnh
	public String chonAnh() {
		String currentDirectory = System.getProperty("user.dir");
		JFileChooser fileChooser = new JFileChooser(currentDirectory);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			lblHinhAnh.setIcon(new ImageIcon(selectedFile.getPath()));
			anh = "src\\main\\java\\image\\avtemployee\\" + selectedFile.getName();
			return anh;
		} else {
			anh = "Không có ảnh";
		}
		return anh;
	}

	// chọn thông tin trong bảng hiện lên
	public void chonThongTin() throws RemoteException {
		int r = table.getSelectedRow();
		if (r != -1) {
			NhanVien nvTemp =  busNhanVien.layNhanVienTheoMa(modelNV.getValueAt(r, 0).toString());
			System.out.println(nvTemp.getHinhAnh());
			txtMaNV.setText(modelNV.getValueAt(r, 0).toString());
			txtTenNV.setText(modelNV.getValueAt(r, 1).toString());
			txtSdt.setText(modelNV.getValueAt(r, 2).toString());
			txtCCCD.setText(modelNV.getValueAt(r, 4).toString());
			txtEmail.setText(modelNV.getValueAt(r, 5).toString());
			txtDiaChi.setText(modelNV.getValueAt(r, 7).toString());
			lblHinhAnh.setIcon(new ImageIcon(nvTemp.getHinhAnh()));
			txtMatKhau.setEditable(false);
			txtMatKhau.setEnabled(false);
			txtTenTK.setText(modelNV.getValueAt(r, 0).toString());
			anh = busNhanVien.layNhanVienTheoMa(modelNV.getValueAt(r, 0).toString()).getHinhAnh();
			if (modelNV.getValueAt(r, 3).toString().equals("Nam"))
				radNam.setSelected(true);
			else
				radNu.setSelected(true);
			if (modelNV.getValueAt(r, 6).toString().equals("Bán hàng")) {
				cboChucVu.setSelectedIndex(0);
			} else if (modelNV.getValueAt(r, 6).toString().equals("Quản lý")) {
				cboChucVu.setSelectedIndex(1);
			}
			for (NhanVien nv : dsNV) {
				if (nv.getMaNhanVien().equals(modelNV.getValueAt(r, 0).toString())) {
					lblHinhAnh.setIcon(new ImageIcon(nv.getHinhAnh()));
					txtMatKhau.setText(busNhanVien.layMatKhauNhanVienTheoMa(nv.getMaNhanVien()));
					if (nv.getTrangThai().equals("Đang làm")) {
						chkTrangThai.setSelected(true);
					} else {
						chkTrangThai.setSelected(false);
					}
				}
			}
			btnTaoMa.setEnabled(false);
		}
	}

	// xóa trắng
	public void xoaTrang() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtSdt.setText("");
		txtCCCD.setText("");
		txtEmail.setText("");
		txtDiaChi.setText("");
		txtTenTK.setText("");
		txtMatKhau.setText("");
		txtMatKhau.setEditable(true);
		txtMatKhau.setEnabled(true);
		radNam.setSelected(true);
		cboChucVu.setSelectedIndex(0);
		lblHinhAnh.setIcon(null);
		anh = null;
		btnTaoMa.setEnabled(true);
		chkTrangThai.setSelected(true);
		table.clearSelection();
	}

	public void focusGained() {
		if (txtTimNVTheoMa.getText().equals("Nhập mã nhân viên cần tìm")) {
			txtTimNVTheoMa.setText("");
			txtTimNVTheoMa.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTimNVTheoMa.setForeground(Color.BLACK);
		}
	}

	public void focusLost() {
		if (txtTimNVTheoMa.getText().isEmpty()) {
			txtTimNVTheoMa.setFont(new Font("Tahoma", Font.ITALIC, 13));
			txtTimNVTheoMa.setForeground(Color.GRAY);
			txtTimNVTheoMa.setText("Nhập mã nhân viên cần tìm");
		}
	}
}
