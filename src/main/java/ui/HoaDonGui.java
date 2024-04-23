package ui;

import bus.impl.HoaDonBusImpl;
import com.toedter.calendar.JDateChooser;
import controller.HoaDonController;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HoaDonGui extends JPanel {
	//	Phần biến của quản lý hóa đơn
	private JTextField txtQLHDTimHoaDon;
	private JTextField txtQLHDMaNhanVien;
	private JTextField txtQLHDDienThoaiKH;
	private MyCombobox cbbQLHDHinhThucTT;
	private MyButton btnInHoaDon;
	private MyButton btnLocHoaDon;
	private MyButton btnTaoDonGiao;
	private MyButton btnTaiLai;
	private DefaultTableModel modelHoaDon, modelChiTietHoaDon;
	private JTable tableHoaDon, tableChiTietHoaDon;
	private JDateChooser dateQLHDTuNgay;
	private JDateChooser dateQLHDDenNGay;
	private MyCombobox cbbQLHDTongTien;
	private BanHangGui guiBanHang = null;
	private HoaDonBusImpl busHoaDon = new HoaDonBusImpl();
	private List<HoaDon> dsHoaDon = busHoaDon.layHetDSHoaDon();
	HoaDonController acHoaDon = new HoaDonController(this);
	
	public HoaDonGui(BanHangGui gui) throws RemoteException {
		this.guiBanHang = gui;
		this.setLayout(null);

		JPanel pnlTimHoaDon = new JPanel();
		pnlTimHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlTimHoaDon.setBackground(new Color(255, 255, 255));
		pnlTimHoaDon.setBounds(10, 10, 954, 197);
		this.add(pnlTimHoaDon);
		pnlTimHoaDon.setLayout(null);

		JLabel lblTmHan = new JLabel("Tìm hóa đơn");
		lblTmHan.setForeground(Color.GRAY);
		lblTmHan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTmHan.setBounds(10, 10, 111, 20);
		pnlTimHoaDon.add(lblTmHan);

		JLabel lblTmKimHa = new JLabel("Tìm kiếm hóa đơn:");
		lblTmKimHa.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTmKimHa.setBounds(10, 40, 111, 25);
		pnlTimHoaDon.add(lblTmKimHa);

		txtQLHDTimHoaDon = new JTextField();
		txtQLHDTimHoaDon.setBounds(156, 40, 230, 25);
		pnlTimHoaDon.add(txtQLHDTimHoaDon);
		txtQLHDTimHoaDon.setColumns(10);

		JLabel lblTnNhnVin = new JLabel("Mã nhân viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnNhnVin.setBounds(10, 75, 111, 25);
		pnlTimHoaDon.add(lblTnNhnVin);

		txtQLHDMaNhanVien = new JTextField();
		txtQLHDMaNhanVien.setColumns(10);
		txtQLHDMaNhanVien.setBounds(156, 75, 230, 25);
		pnlTimHoaDon.add(txtQLHDMaNhanVien);

		JLabel lblSdtKhchHng_1 = new JLabel("SDT khách hàng:");
		lblSdtKhchHng_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSdtKhchHng_1.setBounds(10, 110, 111, 25);
		pnlTimHoaDon.add(lblSdtKhchHng_1);

		txtQLHDDienThoaiKH = new JTextField();
		txtQLHDDienThoaiKH.setColumns(10);
		txtQLHDDienThoaiKH.setBounds(156, 110, 230, 25);
		pnlTimHoaDon.add(txtQLHDDienThoaiKH);

		JLabel lblHnhThcThanh_1 = new JLabel("Hình thức thanh toán:");
		lblHnhThcThanh_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHnhThcThanh_1.setBounds(10, 145, 136, 25);
		pnlTimHoaDon.add(lblHnhThcThanh_1);

		cbbQLHDHinhThucTT = new MyCombobox();
		cbbQLHDHinhThucTT.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbbQLHDHinhThucTT.setBounds(156, 145, 230, 25);
		cbbQLHDHinhThucTT.addItem("Tất cả");
		cbbQLHDHinhThucTT.addItem("Tiền mặt");
		cbbQLHDHinhThucTT.addItem("Chuyển khoản");
		pnlTimHoaDon.add(cbbQLHDHinhThucTT);

		JLabel lblTNgy = new JLabel("Từ ngày:");
		lblTNgy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTNgy.setBounds(508, 40, 77, 25);
		pnlTimHoaDon.add(lblTNgy);

		JLabel lblnNgy = new JLabel("Đến ngày:");
		lblnNgy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblnNgy.setBounds(508, 75, 77, 25);
		pnlTimHoaDon.add(lblnNgy);

		JPanel pnlQLHDChucNang = new JPanel();
		pnlQLHDChucNang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQLHDChucNang.setBackground(new Color(255, 255, 255));
		pnlQLHDChucNang.setBounds(974, 10, 303, 197);
		this.add(pnlQLHDChucNang);
		pnlQLHDChucNang.setLayout(null);

		btnInHoaDon = new MyButton("In hóa đơn");
		btnInHoaDon.setActionCommand("btnInHoaDon");
		btnInHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnInHoaDon.setBounds(70, 56, 163, 36);
		pnlQLHDChucNang.add(btnInHoaDon);

		btnTaiLai = new MyButton("Tải lại");
		btnTaiLai.setActionCommand("btnTaiLai");
		btnTaiLai.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTaiLai.setBounds(70, 102, 163, 36);
		pnlQLHDChucNang.add(btnTaiLai);

		btnTaoDonGiao = new MyButton("Tạo đơn giao hàng");
		btnTaoDonGiao.setActionCommand("btnTaoDonGiao");
		btnTaoDonGiao.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTaoDonGiao.setBounds(70, 149, 163, 36);
		pnlQLHDChucNang.add(btnTaoDonGiao);

		btnLocHoaDon = new MyButton("Lọc hóa đơn");
		btnLocHoaDon.setActionCommand("btnLocHoaDon");
		btnLocHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLocHoaDon.setBounds(70, 10, 163, 36);
		pnlQLHDChucNang.add(btnLocHoaDon);

		JPanel pnlQLHDDSHoaDon = new JPanel();
		pnlQLHDDSHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQLHDDSHoaDon.setBackground(Color.WHITE);
		pnlQLHDDSHoaDon.setBounds(10, 217, 1267, 280);
		this.add(pnlQLHDDSHoaDon);
		pnlQLHDDSHoaDon.setLayout(null);

		JLabel lblDanhSchHa = new JLabel("Danh sách hóa đơn");
		lblDanhSchHa.setForeground(Color.GRAY);
		lblDanhSchHa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDanhSchHa.setBounds(10, 10, 137, 20);
		pnlQLHDDSHoaDon.add(lblDanhSchHa);

		String[] colsHoaDon = {"Mã hóa đơn", "Tổng tiền", "Tổng thuế", "Giảm giá", "Thanh toán", "Tiền khách trả", "Tiền thừa", "Phương thức thanh toán", "Ngày lập hóa đơn", "Tên CTKM", "Mã NV", "Tên NV", "Tên KH", "SDT KH", "Ghi chú"};
		modelHoaDon = new DefaultTableModel(colsHoaDon, 0);
		tableHoaDon = new MyTable(modelHoaDon);
		tableHoaDon.setName("tableHoaDon");
		JScrollPane srcTbHoaDon = new JScrollPane(tableHoaDon);
		srcTbHoaDon.setBounds(10, 40, 1247, 230);
		tableHoaDon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for(int i = 0; i < tableHoaDon.getColumnCount(); i++) {
			tableHoaDon.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		pnlQLHDDSHoaDon.add(srcTbHoaDon);

		JPanel pnlQLHDChiTietHoaDon = new JPanel();
		pnlQLHDChiTietHoaDon.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQLHDChiTietHoaDon.setBackground(Color.WHITE);
		pnlQLHDChiTietHoaDon.setBounds(10, 507, 1267, 249);
		this.add(pnlQLHDChiTietHoaDon);
		pnlQLHDChiTietHoaDon.setLayout(null);

		JLabel lblChiTitHa = new JLabel("Chi tiết hóa đơn");
		lblChiTitHa.setForeground(Color.GRAY);
		lblChiTitHa.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblChiTitHa.setBounds(10, 10, 137, 20);
		pnlQLHDChiTietHoaDon.add(lblChiTitHa);

		String[] colsChiTietHoaDon = {"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thuế", "Giảm giá", "Thành tiền"};
		modelChiTietHoaDon = new DefaultTableModel(colsChiTietHoaDon, 0);
		tableChiTietHoaDon = new MyTable(modelChiTietHoaDon);
		JScrollPane srcTbChiTietHoaDon = new JScrollPane(tableChiTietHoaDon);
		srcTbChiTietHoaDon.setBounds(10, 40, 1247, 199);
		pnlQLHDChiTietHoaDon.add(srcTbChiTietHoaDon);

		dateQLHDTuNgay = new JDateChooser();
		dateQLHDTuNgay.setLocale(new Locale("vi", "VN"));
		dateQLHDTuNgay.setBounds(595, 40, 230, 25);

		dateQLHDDenNGay = new JDateChooser();
		dateQLHDDenNGay.setLocale(new Locale("vi", "VN"));
		dateQLHDDenNGay.setBounds(595, 75, 230, 25);

		pnlTimHoaDon.add(dateQLHDTuNgay);
		pnlTimHoaDon.add(dateQLHDDenNGay);

		JLabel lblSdtKhchHng_1_1 = new JLabel("Tổng tiền:");
		lblSdtKhchHng_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSdtKhchHng_1_1.setBounds(508, 110, 67, 25);
		pnlTimHoaDon.add(lblSdtKhchHng_1_1);

		cbbQLHDTongTien = new MyCombobox();
		cbbQLHDTongTien.setFont(new Font("Tahoma", Font.BOLD, 12));
		cbbQLHDTongTien.addItem("Tất cả");
		cbbQLHDTongTien.addItem("Dưới 1.000.000");
		cbbQLHDTongTien.addItem("Từ 1.000.000 đến 2.000.000");
		cbbQLHDTongTien.addItem("Từ 2.000.000 đến 5.000.000");
		cbbQLHDTongTien.addItem("Từ 5.000.000 đến 10.000.000");
		cbbQLHDTongTien.addItem("Trên 10.000.000");
		cbbQLHDTongTien.setBounds(595, 113, 230, 25);
		pnlTimHoaDon.add(cbbQLHDTongTien);

		// xử lý cho phần quản lý hóa đơn
		capNhatBangDSHoaDon();
		
		// phần thêm sự kiện
		tableHoaDon.addMouseListener(acHoaDon);
		btnLocHoaDon.addActionListener(acHoaDon);
		btnTaiLai.addActionListener(acHoaDon);
		btnInHoaDon.addActionListener(acHoaDon);
		btnTaoDonGiao.addActionListener(acHoaDon);
	}
	
	// phần xử lý code cho quản lý hóa đơn
		public void capNhatBangDSHoaDon() throws RemoteException {
			modelHoaDon.setRowCount(0);
			for (HoaDon hoaDon : dsHoaDon) {
				modelHoaDon.addRow(new Object[] {
						hoaDon.getMaHoaDon(),
						Tool.dinhDangTien(hoaDon.tinhTongTien()),
						Tool.dinhDangTien(hoaDon.tinhThue()),
						Tool.dinhDangTien(hoaDon.tinhGiamGia() + (hoaDon.getDiemGiamGia() * 10000)),
						Tool.dinhDangTien(hoaDon.getThanhTien()),
						Tool.dinhDangTien(hoaDon.getTienKhachDua()),
						Tool.dinhDangTien(hoaDon.tinhTienThua()),
						hoaDon.getHinhThucThanhToan(),
						hoaDon.getNgayLap(),
						hoaDon.getCtkm().getTenCTKM(),
						hoaDon.getNhanVien().getMaNhanVien(),
						hoaDon.getNhanVien().getTenNhanVien(),
						hoaDon.getKhachHang().getTenKhachHang(),
						hoaDon.getKhachHang().getSdt(),
						hoaDon.getGhiChu().equals("") ? "Trống" : hoaDon.getGhiChu().equals("")
				});
			}
		}

		public void capNhatBangChiTietHoaDon(HoaDon hd) throws RemoteException {
			modelChiTietHoaDon.setRowCount(0);
			for (ChiTietHoaDon ct : hd.getDsChiTietHoaDon()) {
				float giamGia = busHoaDon.hamLayGiamGiaCuaChiTietHoaDon(hd.getCtkm(), ct.getSanPham());
				float tongTien = ct.getGiaBan() * ct.getSoLuongMua() * (1 + ct.getSanPham().getThue() / 100) * (1 - giamGia / 100);
				modelChiTietHoaDon.addRow(new Object[] {
						ct.getSanPham().getMaSanPham(),
						ct.getSanPham().getTenSanPham(),
						ct.getSoLuongMua(),
						Tool.dinhDangTien(ct.getGiaBan()),
						ct.getSanPham().getThue() + "%",
						giamGia + "%",
						Tool.dinhDangTien(tongTien)
				});
			}
		}

		public void xuLyClickQuanLyHoaDon() throws RemoteException {
			int row = tableHoaDon.getSelectedRow();
			HoaDon temp = dsHoaDon.get(row);
			capNhatBangChiTietHoaDon(temp);
		}

		public void xuLyLocHoaDon() throws RemoteException {
			Date tuNgay = dateQLHDTuNgay.getDate();
			Date denNgay = dateQLHDDenNGay.getDate();
			String maHD = txtQLHDTimHoaDon.getText();
			String maNV = txtQLHDMaNhanVien.getText();
			String sdt = txtQLHDDienThoaiKH.getText();
			String httt = cbbQLHDHinhThucTT.getSelectedItem() + "";
			String tongTien = cbbQLHDTongTien.getSelectedItem() + "";
			if(tuNgay != null && denNgay != null) {
				LocalDate lcdtuNgay = tuNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate lcddenNgay = denNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if(lcdtuNgay.isAfter(lcddenNgay)) {
					JOptionPane.showMessageDialog(this, "Ngày lọc hóa đơn không hợp lệ (Từ ngày phải đứng trước đến ngày)");
					return;
				}
			}
			dsHoaDon = busHoaDon.locHoaDonQLHD(maHD, maNV, sdt, httt, tuNgay, denNgay, tongTien);
			capNhatBangDSHoaDon();
		}

		public void xuLyInHoaDon() {
			int row = tableHoaDon.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn cần in");
			} else {
				Tool.inHoaDon(dsHoaDon.get(row), dsHoaDon.get(row).getCtkm());
				JOptionPane.showMessageDialog(this, "In hóa đơn " + dsHoaDon.get(row).getMaHoaDon() + " thành công");
			}
		}

		public void xuLyTaiLaiHoaDon() throws RemoteException {
			this.xuLyXoaTrangThongTin();
			capNhatBangDSHoaDon();
		}
		
		public void xuLyXoaTrangThongTin() throws RemoteException {
			txtQLHDTimHoaDon.setText("");
			txtQLHDMaNhanVien.setText("");
			txtQLHDDienThoaiKH.setText("");
			cbbQLHDHinhThucTT.setSelectedIndex(0);
			dateQLHDDenNGay.setDate(null);
			dateQLHDTuNgay.setDate(null);
			cbbQLHDTongTien.setSelectedIndex(0);
			tableHoaDon.clearSelection();
			dsHoaDon = busHoaDon.layHetDSHoaDon();
			modelChiTietHoaDon.setRowCount(0);
		}
		
		public void xuLyKhiThemHoaDon(HoaDon hoaDonThem) throws RemoteException {
			this.xuLyXoaTrangThongTin();
			capNhatBangDSHoaDon();
		}
		
		public void xuLyTaoDonGiaoHang() throws RemoteException {
			int row = tableHoaDon.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn cần chuyên thành đơn giao hàng");
			} else {
				guiBanHang.chuyenHoaDonQuaGiaoHang(dsHoaDon.get(row));
			}
		}
}
