package ui;

import bus.impl.HoaDonBusImpl;
import bus.impl.KhachHangBusImpl;
import controller.ControllerKhachHang;
import customUI.MyButton;
import customUI.MyTable;
import entity.HoaDon;
import entity.KhachHang;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangGui extends JPanel {
	private JTextField txtMaKhachHang;
	private JTextField txtTenKhachHang;
	private JTextField txtDiemTichLuy;
	private JTextField txtSDT;
	private JTextField txtEmail;
	private JTextField txtTimTenKhachHang;
	private JTextField txtTimSDTKhachHang;
	private DefaultTableModel modelKhachHang, modelLichSuGD;
	private JTable tableKhachHang, tableLichSuGD;
	private List<KhachHang> dsKhachHang = new ArrayList<>();
	
	private KhachHangBusImpl busKhachHang = new KhachHangBusImpl();
	private HoaDonBusImpl busHoaDon = new HoaDonBusImpl();
	private ControllerKhachHang ctrlKhachHang = new ControllerKhachHang(this);
	private JTextField txtTongTien;
	private MyButton btnTaoMa;
	private MyButton btnThemKhachHang;
	private MyButton btnCapNhat;
	private MyButton btnXoaTrang;
	private JCheckBox chkSapXep;
	public KhachHangGui() {
		setBackground(new Color(240, 240, 240));
		this.setBounds(250, 0, 1250, 800);
		setLayout(null);
		
		JPanel pnlThongTinKhachHang = new JPanel();
		pnlThongTinKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlThongTinKhachHang.setBackground(new Color(255, 255, 255));
		pnlThongTinKhachHang.setBounds(10, 10, 954, 191);
		add(pnlThongTinKhachHang);
		pnlThongTinKhachHang.setLayout(null);
		
		JLabel lblThngTinKhch = new JLabel("Thông tin khách hàng");
		lblThngTinKhch.setForeground(Color.GRAY);
		lblThngTinKhch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThngTinKhch.setBounds(10, 10, 167, 20);
		pnlThongTinKhachHang.add(lblThngTinKhch);
		
		JLabel lblMKhchHng = new JLabel("Mã khách hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMKhchHng.setBounds(10, 40, 111, 25);
		pnlThongTinKhachHang.add(lblMKhchHng);
		
		txtMaKhachHang = new JTextField();
		txtMaKhachHang.setEnabled(false);
		txtMaKhachHang.setDisabledTextColor(Color.BLACK);
		txtMaKhachHang.setColumns(10);
		txtMaKhachHang.setBounds(131, 40, 230, 25);
		pnlThongTinKhachHang.add(txtMaKhachHang);
		
		JLabel lblTnKhchHng = new JLabel("Tên khách hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnKhchHng.setBounds(10, 75, 111, 25);
		pnlThongTinKhachHang.add(lblTnKhchHng);
		
		txtTenKhachHang = new JTextField();
		txtTenKhachHang.setColumns(10);
		txtTenKhachHang.setBounds(131, 76, 230, 25);
		pnlThongTinKhachHang.add(txtTenKhachHang);
		
		JLabel lblimTichLy = new JLabel("Điểm tich lũy:");
		lblimTichLy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblimTichLy.setBounds(10, 110, 111, 25);
		pnlThongTinKhachHang.add(lblimTichLy);
		
		txtDiemTichLuy = new JTextField();
		txtDiemTichLuy.setEnabled(false);
		txtDiemTichLuy.setDisabledTextColor(Color.BLACK);
		txtDiemTichLuy.setColumns(10);
		txtDiemTichLuy.setBounds(131, 111, 230, 25);
		pnlThongTinKhachHang.add(txtDiemTichLuy);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSinThoi.setBounds(508, 40, 111, 25);
		pnlThongTinKhachHang.add(lblSinThoi);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(621, 41, 230, 25);
		pnlThongTinKhachHang.add(txtSDT);
		
		JLabel lblTmKimHa_3_1 = new JLabel("Email:");
		lblTmKimHa_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTmKimHa_3_1.setBounds(508, 75, 111, 25);
		pnlThongTinKhachHang.add(lblTmKimHa_3_1);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(621, 76, 230, 25);
		pnlThongTinKhachHang.add(txtEmail);
		
		btnXoaTrang = new MyButton("Xóa trắng");
		btnXoaTrang.setActionCommand("btnXoaTrang");
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnXoaTrang.setBounds(425, 146, 99, 30);
		pnlThongTinKhachHang.add(btnXoaTrang);
		
		JLabel lblTmKimHa_3_1_1 = new JLabel("Tổng tiền:");
		lblTmKimHa_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTmKimHa_3_1_1.setBounds(508, 110, 111, 25);
		pnlThongTinKhachHang.add(lblTmKimHa_3_1_1);
		
		txtTongTien = new JTextField();
		txtTongTien.setEnabled(false);
		txtTongTien.setDisabledTextColor(Color.BLACK);
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(621, 111, 230, 25);
		pnlThongTinKhachHang.add(txtTongTien);
		
		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlChucNang.setBackground(Color.WHITE);
		pnlChucNang.setBounds(974, 10, 303, 191);
		add(pnlChucNang);
		pnlChucNang.setLayout(null);
		
		btnTaoMa = new MyButton("Tạo mã khách hàng");
		btnTaoMa.setActionCommand("btnTaoMa");
		btnTaoMa.setBackground(new Color(255, 255, 128));
		btnTaoMa.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTaoMa.setBounds(62, 33, 179, 35);
		pnlChucNang.add(btnTaoMa);
		
		btnThemKhachHang = new MyButton("Thêm khách hàng");
		btnThemKhachHang.setActionCommand("btnThemKhachHang");
		btnThemKhachHang.setBackground(new Color(128, 255, 128));
		btnThemKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemKhachHang.setBounds(62, 78, 179, 35);
		pnlChucNang.add(btnThemKhachHang);
		
		btnCapNhat = new MyButton("Cập nhật");
		btnCapNhat.setActionCommand("btnCapNhat");
		btnCapNhat.setBackground(new Color(255, 128, 0));
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCapNhat.setBounds(62, 123, 179, 35);
		pnlChucNang.add(btnCapNhat);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 211, 1267, 83);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblTmKimKhch = new JLabel("Tìm kiếm khách hàng");
		lblTmKimKhch.setForeground(Color.GRAY);
		lblTmKimKhch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTmKimKhch.setBounds(10, 10, 167, 20);
		panel.add(lblTmKimKhch);
		
		JLabel lblMKhchHng_1 = new JLabel("Tên khách hàng:");
		lblMKhchHng_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMKhchHng_1.setBounds(10, 40, 106, 25);
		panel.add(lblMKhchHng_1);
		
		txtTimTenKhachHang = new JTextField();
		txtTimTenKhachHang.setColumns(10);
		txtTimTenKhachHang.setBounds(131, 40, 345, 25);
		panel.add(txtTimTenKhachHang);
		
		JLabel lblMKhchHng_1_1 = new JLabel("Số điện thoại:");
		lblMKhchHng_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMKhchHng_1_1.setBounds(538, 40, 106, 25);
		panel.add(lblMKhchHng_1_1);
		
		txtTimSDTKhachHang = new JTextField();
		txtTimSDTKhachHang.setColumns(10);
		txtTimSDTKhachHang.setBounds(640, 41, 345, 25);
		panel.add(txtTimSDTKhachHang);
		
		chkSapXep = new JCheckBox("Sắp xếp theo tổng tiền");
		chkSapXep.setFont(new Font("Tahoma", Font.BOLD, 13));
		chkSapXep.setBackground(new Color(255, 255, 255));
		chkSapXep.setBounds(1032, 43, 184, 21);
		panel.add(chkSapXep);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(10, 304, 724, 481);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDanhSchKhch = new JLabel("Danh sách khách hàng:");
		lblDanhSchKhch.setForeground(Color.GRAY);
		lblDanhSchKhch.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhSchKhch.setBounds(10, 10, 167, 20);
		panel_1.add(lblDanhSchKhch);
		
		String[] colsKhachHang = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Điểm tích lũy", "Tổng tiền mua"};
		modelKhachHang = new DefaultTableModel(colsKhachHang, 0);
		tableKhachHang = new MyTable(modelKhachHang);
		JScrollPane srcTbKhachHang = new JScrollPane(tableKhachHang);
		srcTbKhachHang.setBounds(10, 40, 704, 431);
		tableKhachHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableKhachHang.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableKhachHang.getColumnModel().getColumn(1).setPreferredWidth(151);
		tableKhachHang.getColumnModel().getColumn(2).setPreferredWidth(150);
		tableKhachHang.getColumnModel().getColumn(3).setPreferredWidth(200);
		tableKhachHang.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableKhachHang.getColumnModel().getColumn(5).setPreferredWidth(150);
		panel_1.add(srcTbKhachHang);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBackground(Color.WHITE);
		panel_1_1.setBounds(742, 304, 535, 481);
		add(panel_1_1);
		
		JLabel lblLchSGiao = new JLabel("Lịch sử giao dịch");
		lblLchSGiao.setForeground(Color.GRAY);
		lblLchSGiao.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLchSGiao.setBounds(10, 10, 167, 20);
		panel_1_1.add(lblLchSGiao);
		
		String[] colsLichSuGD = {"Mã hóa đơn", "Mã NV", "Tên NV", "Ngày giao dịch", "Thành tiền"};
		modelLichSuGD = new DefaultTableModel(colsLichSuGD, 0);
		tableLichSuGD = new MyTable(modelLichSuGD);
		JScrollPane srcTbLichSuGD = new JScrollPane(tableLichSuGD);
		srcTbLichSuGD.setBounds(10, 40, 515, 431);
		panel_1_1.add(srcTbLichSuGD);
		// xử lý
		dsKhachHang = busKhachHang.layDSKhachHang();
		capNhatBangKhachHang(dsKhachHang);
		
		//thêm sự kiện
		tableKhachHang.addMouseListener(ctrlKhachHang);
		btnXoaTrang.addActionListener(ctrlKhachHang);
		btnTaoMa.addActionListener(ctrlKhachHang);
		btnThemKhachHang.addActionListener(ctrlKhachHang);
		btnCapNhat.addActionListener(ctrlKhachHang);
		txtTimSDTKhachHang.addKeyListener(ctrlKhachHang);
		txtTimTenKhachHang.addKeyListener(ctrlKhachHang);
		chkSapXep.addItemListener(ctrlKhachHang);
	}
	
	public void capNhatBangKhachHang(List<KhachHang> ds) {
		modelKhachHang.setRowCount(0);
		for (KhachHang khachHang : ds) {
			modelKhachHang.addRow(new Object[] {
					khachHang.getMaKhachHang(),
					khachHang.getTenKhachHang(),
					khachHang.getSdt(),
					khachHang.getEmail(),
					khachHang.getDiemTichLuy(),
					Tool.dinhDangTien(khachHang.getTongTienMua())
			});
		}
	}
	
	public void capNhatBangLichSuGD(List<HoaDon> ds) {
		modelLichSuGD.setRowCount(0);
		for (HoaDon hoaDon : ds) {
			modelLichSuGD.addRow(new Object[] {
					hoaDon.getMaHoaDon(),
					hoaDon.getNhanVien().getMaNhanVien(),
					hoaDon.getNhanVien().getTenNhanVien(),
					hoaDon.getNgayLap(),
					Tool.dinhDangTien(hoaDon.getThanhTien())
			});
		}
	}
	
	public void xuLyKhiChonBangKhachHang() {
		int row = tableKhachHang.getSelectedRow();
		String maKhachHang = tableKhachHang.getValueAt(row, 0) + "";
		if(maKhachHang.equals("KH0")) btnCapNhat.setEnabled(false); 
		else btnCapNhat.setEnabled(true);
		KhachHang kh = dsKhachHang.get(row);
		txtMaKhachHang.setText(kh.getMaKhachHang());
		txtTenKhachHang.setText(kh.getTenKhachHang());
		txtDiemTichLuy.setText(kh.getDiemTichLuy() + "");
		txtEmail.setText(kh.getEmail());
		txtSDT.setText(kh.getSdt());
		txtTongTien.setText(Tool.dinhDangTien(kh.getTongTienMua()));
		capNhatBangLichSuGD(busHoaDon.layLichSuGiaoDichKhachHang(maKhachHang, 20));
	}
	
	public void xuLyXoaTrang() {
		txtMaKhachHang.setText("");
		txtTenKhachHang.setText("");
		txtDiemTichLuy.setText("");
		txtEmail.setText("");
		txtSDT.setText("");
		txtTongTien.setText("");
		tableKhachHang.clearSelection();
		capNhatBangLichSuGD(new ArrayList<HoaDon>());
		btnCapNhat.setEnabled(true);
	}
	
	public void xuLyTaoMaKhachHang() {
		xuLyXoaTrang();
		txtMaKhachHang.setText(busKhachHang.taoMaKhachHang());
		txtDiemTichLuy.setText("0");
		txtTongTien.setText("0");
	}
	
	public void xuLyThemKhachHang() {
		String maKH = txtMaKhachHang.getText();
		String tenKH = txtTenKhachHang.getText();
		String sdt = txtSDT.getText();
		String email = txtEmail.getText();
		String thongDiep = busKhachHang.themKhachHang(new KhachHang(maKH, tenKH, sdt, email, 0, 0));
		if(thongDiep.equals("Thêm khách hàng thành công")) {
			xuLyXoaTrang();
			dsKhachHang = busKhachHang.layDSKhachHang();
			capNhatBangKhachHang(dsKhachHang);
		}
		JOptionPane.showMessageDialog(this, thongDiep);
	}
	
	public void xuLyCapNhatKhachHang() {
		int row = tableKhachHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần cập nhật");
		} else {
			String maKH = tableKhachHang.getValueAt(row, 0) + "";
			String tenKH = txtTenKhachHang.getText();
			String sdt = txtSDT.getText();
			String email = txtEmail.getText();
			int diemTichLuy = Integer.parseInt(txtDiemTichLuy.getText());
			float tongTien = Float.parseFloat(txtTongTien.getText().replaceAll("[,VND]", ""));
			String thongDiep = busKhachHang.capNhatThongTinKhachHang(new KhachHang(maKH, tenKH, sdt, email, diemTichLuy, tongTien));
			dsKhachHang = busKhachHang.layDSKhachHang();
			capNhatBangKhachHang(dsKhachHang);
			tableKhachHang.setRowSelectionInterval(row, row);
			JOptionPane.showMessageDialog(this, thongDiep);
		}
	}
	
	public void xuLyTimKhachHang() {
		String timTen = txtTimTenKhachHang.getText();
		String timSDT = txtTimSDTKhachHang.getText();
		dsKhachHang = busKhachHang.locKhachHang(timTen, timSDT);
		if(chkSapXep.isSelected()) {
			busKhachHang.sapXepTheoTongTien(dsKhachHang);
		}
		capNhatBangKhachHang(dsKhachHang);
	}
	
	public void xuLySapXepTheoTongTien() {
		if(chkSapXep.isSelected()) {
			capNhatBangKhachHang(busKhachHang.sapXepTheoTongTien(dsKhachHang));
		} else {
			xuLyTimKhachHang();
		}
	}
}
