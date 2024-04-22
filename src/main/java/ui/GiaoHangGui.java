package ui;


import bus.impl.DonGiaoHangBusImpl;
import bus.impl.HoaDonBusImpl;
import controller.ControllerGiaoHang;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.DonGiaoHang;
import entity.HoaDon;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class GiaoHangGui extends JPanel implements MouseListener{
		private JTextField txtTKMaDonHang;
		private JTextField txtTKSDT_KH;
		private JTextField txtTKTenKhachHang;
		private DefaultTableModel modelDonHang, modelQLDonHangHT, modelQLDonHangCHT;
		private MyTable tbDonHang, tbTabQLDonHang;
		private JScrollPane scrollPaneQLGH;
		private MyCombobox cboLocDonHang, cboPTTT;
        private JPanel pnlQuanLyDonHang, pnlGiaoHang;
        private MyButton btnHuyDon, btnThanhCong, btnInPhieuDonHang, btnTaoMaDonHang, btnTaoDonHang, btnHuyTaoDon, btnXoaTrang, btnTinhKhoangCach;
        private JTextField txtDiaChi;
        private JTextField txtSDT;
        private JTextField txtTenKhachHang;
        private JTextField txtMaDonHang;
        private JTextField txtMaHoaDon;
        private JTextField txtNgayLap;
        private JTextField txtTenNVLapHD;
        private JTextField txtSoKG;
        private JTextField txtSoKM;
        private JTextField txtChaHonThnh;
        private JTextField txtThanhTien;
        private JTextField txtMaDonHangQL;
        private JTextField txtSDTQL;
        private JTextField txtTenKhachHangQL;
        private HoaDon hd;
        private JTabbedPane tabbedPane;
        private JCheckBox checkBoxKH;
        private DonGiaoHangBusImpl busGiaoHang = new DonGiaoHangBusImpl();
        private int soKgTemp = 0;
        private float soKmTemp = 0;
        private JCheckBox checkBoxSapXep;
        private ArrayList<DonGiaoHang> dsTemp_one = new ArrayList<>();
		public GiaoHangGui(HoaDon HD) {
			this.hd = HD;
			this.setBackground(new Color(255, 255, 255));
			this.setBounds(250, 0, 1250, 800);
			this.setLayout(null);
			
		    tabbedPane = new JTabbedPane();
			tabbedPane.setBounds(21, 0, 1245, 789);
			
			// tab giao hàng
			pnlGiaoHang = new JPanel();
			pnlGiaoHang.setBackground(new Color(255, 255, 255));
			pnlGiaoHang.setBounds(20, 0, 1195, 800);
			
			
			pnlGiaoHang.setBounds(20, 0, 1195, 800);
			
			tabbedPane.add("Giao Hàng", pnlGiaoHang);
			pnlGiaoHang.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel.setBounds(10, 11, 1220, 219);
			pnlGiaoHang.add(panel);
			panel.setLayout(null);
			
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_3.setBackground(new Color(255, 255, 255));
			panel_3.setBounds(10, 11, 393, 197);
			panel.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblMaDonHang = new JLabel("Mã đơn hàng");
			lblMaDonHang.setBounds(10, 37, 88, 17);
			panel_3.add(lblMaDonHang);
			
			JLabel lblMaHoaDon = new JLabel("Mã hóa đơn");
			lblMaHoaDon.setBounds(10, 67, 76, 17);
			panel_3.add(lblMaHoaDon);
			
			JLabel lblNgayLap = new JLabel("Ngày lập");
			lblNgayLap.setBounds(10, 99, 76, 17);
			panel_3.add(lblNgayLap);
			
			JLabel lblNewLabel_2 = new JLabel("Tên nhân viên lập hóa đơn");
			lblNewLabel_2.setBounds(10, 130, 153, 17);
			panel_3.add(lblNewLabel_2);
			
			JLabel lblNewLabel_3 = new JLabel("Thông tin đơn hàng");
			lblNewLabel_3.setForeground(new Color(255, 128, 0));
			lblNewLabel_3.setBackground(new Color(255, 255, 255));
			lblNewLabel_3.setBounds(151, 6, 141, 17);
			panel_3.add(lblNewLabel_3);
			
			JLabel lblPTTT = new JLabel("Phương thức thanh toán");
			lblPTTT.setBounds(10, 162, 153, 17);
			panel_3.add(lblPTTT);
			
			txtMaDonHang = new JTextField();
			txtMaDonHang.setBackground(new Color(255, 255, 255));
			txtMaDonHang.setEditable(false);
			txtMaDonHang.setBounds(173, 34, 200, 20);
			panel_3.add(txtMaDonHang);
			txtMaDonHang.setColumns(10);
			
			txtMaHoaDon = new JTextField();
			txtMaHoaDon.setBackground(new Color(255, 255, 255));
			txtMaHoaDon.setEditable(false);
			txtMaHoaDon.setBounds(173, 64, 200, 20);
			panel_3.add(txtMaHoaDon);
			txtMaHoaDon.setColumns(10);
			
			txtNgayLap = new JTextField();
			txtNgayLap.setBackground(new Color(255, 255, 255));
			txtNgayLap.setEditable(false);
			txtNgayLap.setBounds(173, 96, 200, 20);
			panel_3.add(txtNgayLap);
			txtNgayLap.setColumns(10);
			
			txtTenNVLapHD = new JTextField();
			txtTenNVLapHD.setBackground(new Color(255, 255, 255));
			txtTenNVLapHD.setEditable(false);
			txtTenNVLapHD.setBounds(173, 128, 200, 20);
			panel_3.add(txtTenNVLapHD);
			txtTenNVLapHD.setColumns(10);
			
			cboPTTT = new MyCombobox();
			cboPTTT.setBounds(173, 159, 200, 22);
			cboPTTT.addItem("Tiền mặt");
			cboPTTT.addItem("Chuyển khoản");
			panel_3.add(cboPTTT);
			
			JPanel panel_4 = new JPanel();
			panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_4.setBackground(new Color(255, 255, 255));
			panel_4.setBounds(413, 11, 404, 197);
			panel.add(panel_4);
			panel_4.setLayout(null);
			
			JLabel lblTenKhachHang = new JLabel("Tên khách hàng");
			lblTenKhachHang.setBounds(22, 39, 103, 17);
			panel_4.add(lblTenKhachHang);
			
			JLabel lblDiaChi = new JLabel("Địa chỉ");
			lblDiaChi.setBounds(22, 150, 46, 17);
			panel_4.add(lblDiaChi);
			
			JLabel lblSDT = new JLabel("SĐT");
			lblSDT.setBounds(22, 106, 46, 17);
			panel_4.add(lblSDT);
			
			JLabel lblNewLabel_4 = new JLabel("Thông tin khách hàng");
			lblNewLabel_4.setForeground(new Color(255, 128, 0));
			lblNewLabel_4.setBounds(150, 6, 160, 17);
			panel_4.add(lblNewLabel_4);
			
			txtDiaChi = new JTextField();
			txtDiaChi.setBackground(new Color(255, 255, 255));
			txtDiaChi.setEditable(false);
			txtDiaChi.setBounds(133, 149, 240, 20);
			panel_4.add(txtDiaChi);
			txtDiaChi.setColumns(10);
			
			txtSDT = new JTextField();
			txtSDT.setBounds(133, 105, 240, 20);
			panel_4.add(txtSDT);
			txtSDT.setColumns(10);
			
			txtTenKhachHang = new JTextField();
			txtTenKhachHang.setBounds(133, 38, 240, 20);
			panel_4.add(txtTenKhachHang);
			txtTenKhachHang.setColumns(10);
			
			checkBoxKH = new JCheckBox("Chọn khách hàng mặc định");
			checkBoxKH.setBackground(new Color(255, 255, 255));
			checkBoxKH.setBounds(130, 70, 222, 21);
			panel_4.add(checkBoxKH);
			
			JPanel panel_5 = new JPanel();
			panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_5.setBackground(new Color(255, 255, 255));
			panel_5.setBounds(827, 11, 383, 197);
			panel.add(panel_5);
			panel_5.setLayout(null);
			
			JLabel lblNewLabel_5 = new JLabel("Thông tin giao hàng");
			lblNewLabel_5.setForeground(new Color(255, 128, 0));
			lblNewLabel_5.setBounds(161, 6, 149, 17);
			panel_5.add(lblNewLabel_5);
			
			JLabel lblSoKm = new JLabel("Số KM");
			lblSoKm.setBounds(10, 70, 46, 17);
			panel_5.add(lblSoKm);
			
			JLabel lblSoKg = new JLabel("Số KG");
			lblSoKg.setBounds(10, 37, 46, 17);
			panel_5.add(lblSoKg);
			
			JLabel lblTrangThai = new JLabel("Trạng thái");
			lblTrangThai.setBounds(10, 110, 82, 17);
			panel_5.add(lblTrangThai);
			
			JLabel lblVanChuyen = new JLabel("Tiền vận chuyển");
			lblVanChuyen.setBounds(10, 150, 103, 17);
			panel_5.add(lblVanChuyen);
			
			
			btnTinhKhoangCach = new MyButton("Khoảng cách");
			btnTinhKhoangCach.setBounds(224, 66, 149, 27);
			panel_5.add(btnTinhKhoangCach);
			btnTinhKhoangCach.setBackground(new Color(255, 0, 0));
			btnTinhKhoangCach.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\iconmap.png"));
			
			txtSoKG = new JTextField();
			txtSoKG.setBounds(117, 34, 256, 20);
			panel_5.add(txtSoKG);
			txtSoKG.setColumns(10);
			
			txtSoKM = new JTextField();
			txtSoKM.setBackground(new Color(255, 255, 255));
			txtSoKM.setEditable(false);
			txtSoKM.setBounds(117, 66, 97, 20);
			panel_5.add(txtSoKM);
			txtSoKM.setColumns(10);
			
			txtChaHonThnh = new JTextField();
			txtChaHonThnh.setBackground(new Color(255, 255, 255));
			txtChaHonThnh.setEditable(false);
			txtChaHonThnh.setText("Chưa hoàn thành");
			txtChaHonThnh.setBounds(117, 107, 256, 20);
			panel_5.add(txtChaHonThnh);
			txtChaHonThnh.setColumns(10);
			
			txtThanhTien = new JTextField();
			txtThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
			txtThanhTien.setForeground(new Color(255, 128, 64));
			txtThanhTien.setEditable(false);
			txtThanhTien.setFont(new Font("Tahoma", Font.BOLD, 16));
			txtThanhTien.setText(new Tool().dinhDangTien(0));
			txtThanhTien.setBackground(new Color(255, 255, 255));
			txtThanhTien.setBounds(117, 147, 256, 20);
			panel_5.add(txtThanhTien);
			txtThanhTien.setColumns(10);
			
			btnTaoDonHang = new MyButton("Tạo đơn hàng");
			
			btnTaoDonHang.setBackground(new Color(97, 166, 247));
			btnTaoDonHang.setBounds(1059, 247, 157, 30);
			btnTaoDonHang.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\icongiaohang.png"));
			pnlGiaoHang.add(btnTaoDonHang);
			
			btnTaoMaDonHang = new MyButton("Tạo mã đơn hàng");
			
			btnTaoMaDonHang.setBackground(new Color(97, 166, 247));
			btnTaoMaDonHang.setBounds(869, 247, 157, 30);
			btnTaoMaDonHang.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\iconqr.png"));
			pnlGiaoHang.add(btnTaoMaDonHang);
			
			btnInPhieuDonHang = new MyButton("In phiếu đơn hàng");
			btnInPhieuDonHang.setBackground(new Color(97, 166, 247));
			btnInPhieuDonHang.setBounds(680, 247, 157, 30);
			btnInPhieuDonHang.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\iconprint.png"));
			pnlGiaoHang.add(btnInPhieuDonHang);
			
			btnHuyTaoDon = new MyButton("In phiếu đơn hàng");
			btnHuyTaoDon.setText("Hủy tạo đơn");
			btnHuyTaoDon.setBackground(new Color(97, 166, 247));
			btnHuyTaoDon.setBounds(489, 247, 157, 30);
			btnHuyTaoDon.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\iconcancel.png"));
			pnlGiaoHang.add(btnHuyTaoDon);
			
			JLabel lblTìmKiem = new JLabel("Tìm kiếm");
			lblTìmKiem.setBounds(10, 290, 72, 14);
			pnlGiaoHang.add(lblTìmKiem);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_1.setBounds(10, 315, 1220, 70);
			pnlGiaoHang.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblMaDonHangTK = new JLabel("Mã đơn hàng:");
			lblMaDonHangTK.setBounds(39, 29, 91, 14);
			panel_1.add(lblMaDonHangTK);
			
			txtTKMaDonHang = new JTextField();
			txtTKMaDonHang.setBounds(139, 26, 142, 20);
			panel_1.add(txtTKMaDonHang);
			txtTKMaDonHang.setColumns(10);
			
			JLabel lblTenNhanVienGHTK = new JLabel("Số điện thoại khách hàng:");
			lblTenNhanVienGHTK.setBounds(404, 29, 168, 14);
			panel_1.add(lblTenNhanVienGHTK);
			
			txtTKSDT_KH = new JTextField();
			txtTKSDT_KH.setBounds(582, 26, 142, 20);
			panel_1.add(txtTKSDT_KH);
			txtTKSDT_KH.setColumns(10);
			
			JLabel lblTenKhachHangTK = new JLabel("Tên khách hàng:");
			lblTenKhachHangTK.setBounds(849, 29, 114, 14);
			panel_1.add(lblTenKhachHangTK);
			
			txtTKTenKhachHang = new JTextField();
			txtTKTenKhachHang.setBounds(984, 26, 142, 20);
			panel_1.add(txtTKTenKhachHang);
			txtTKTenKhachHang.setColumns(10);
			
			JLabel lblDSDonGiaoHang = new JLabel("Danh sách đơn hàng đang giao ");
			lblDSDonGiaoHang.setBounds(10, 404, 199, 14);
			pnlGiaoHang.add(lblDSDonGiaoHang);
			
			
			modelDonHang = new DefaultTableModel(new Object[] {"Mã đơn hàng","Mã hóa đơn","Tên nhân viên lập hóa đơn", "Ngày lập", "Tên khách hàng", "Địa chỉ", "SĐT", "Trọng lượng", "Tiền vận chuyển", "Trạng thái"}, 0);

			loaddulieulentable(modelDonHang);
			tbDonHang = new MyTable(modelDonHang);
			tbDonHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
			xetKichThuocChoTable(tbDonHang);
		   
			
			JScrollPane scrollPane = new JScrollPane(tbDonHang, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(10, 429, 1220, 280);
			pnlGiaoHang.add(scrollPane);
			
			btnThanhCong = new MyButton("Thành công");
			btnThanhCong.setBackground(new Color(0, 255, 128));
			
			btnThanhCong.setBounds(645, 720, 163, 30);
			btnThanhCong.setIcon(new ImageIcon("src\\image\\iconbtnGiaoHang\\iconsuccesfull.png"));
			pnlGiaoHang.add(btnThanhCong);
			
			btnHuyDon = new MyButton("Bị hủy");
			btnHuyDon.setBackground(new Color(255, 128, 128));
			btnHuyDon.setBounds(391, 720, 163, 30);
			pnlGiaoHang.add(btnHuyDon);
			
			btnXoaTrang = new MyButton("In phiếu đơn hàng");
			btnXoaTrang.setText("Xóa trắng");
			btnXoaTrang.setBackground(new Color(97, 166, 247));
			btnXoaTrang.setBounds(299, 247, 157, 30);
			pnlGiaoHang.add(btnXoaTrang);
			
	
			
			// ghi chu
			// tab quản lý giao hàng
			pnlQuanLyDonHang = new JPanel();
			pnlQuanLyDonHang.setBackground(new Color(255, 255, 255));
			
			tabbedPane.add("Quản Lý Đơn Hàng",pnlQuanLyDonHang);
			pnlQuanLyDonHang.setLayout(null);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(200, 221, 242));
			panel_2.setBounds(10, 141, 305, 38);
			pnlQuanLyDonHang.add(panel_2);
			panel_2.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Danh sách hóa đơn giao hàng");
			lblNewLabel.setBackground(new Color(200, 221, 242));
			lblNewLabel.setBounds(10, 8, 285, 23);
			panel_2.add(lblNewLabel);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel lblNewLabel_1 = new JLabel("Lọc:");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(968, 154, 51, 25);
			pnlQuanLyDonHang.add(lblNewLabel_1);
			
			cboLocDonHang = new MyCombobox();
			cboLocDonHang.setFont(new Font("Tahoma", Font.PLAIN, 16));
			cboLocDonHang.setBounds(1057, 155, 173, 22);
			cboLocDonHang.addItem("Hoàn thành");
			cboLocDonHang.addItem("Chưa hoàn thành");
			
			
			pnlQuanLyDonHang.add(cboLocDonHang);
			
			// table model đơn hàng chưa hoàn thành
            modelQLDonHangCHT = new DefaultTableModel(new Object[] {"Mã đơn hàng","Mã hóa đơn","Tên nhân viên lập hóa đơn","Ngày lập", "Tên khách hàng", "Địa chỉ", "SĐT", "Trọng lượng", "Tiền vận chuyển", "Trạng thái", "Ghi chú"}, 0);
			
			modelQLDonHangHT = new DefaultTableModel(new Object[] {"Mã đơn hàng","Mã hóa đơn","Tên nhân viên lập hóa đơn","Ngày lập", "Tên khách hàng", "Địa chỉ", "SĐT", "Trọng lượng", "Tiền vận chuyển", "Trạng thái"}, 0);
			tbTabQLDonHang = new MyTable(modelQLDonHangHT);
			loaddulieulentableTabQL(modelQLDonHangHT, new DonGiaoHangBusImpl().layDSDonGiaoHang());

		    tbTabQLDonHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
 
		    xetKichThuocChoTable(tbTabQLDonHang);
		    
			
			scrollPaneQLGH = new JScrollPane(tbTabQLDonHang, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPaneQLGH.setBounds(10, 204, 1220, 546);
			
			pnlQuanLyDonHang.add(scrollPaneQLGH);
			
			JPanel panel_6 = new JPanel();
			panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			panel_6.setBounds(10, 52, 1220, 69);
			pnlQuanLyDonHang.add(panel_6);
			panel_6.setLayout(null);
			
			JLabel lblNewLabel_14 = new JLabel("Mã đơn hàng:");
			lblNewLabel_14.setBounds(32, 30, 87, 14);
			panel_6.add(lblNewLabel_14);
			
			txtMaDonHangQL = new JTextField();
			txtMaDonHangQL.setBounds(129, 27, 130, 20);
			panel_6.add(txtMaDonHangQL);
			txtMaDonHangQL.setColumns(10);
			
			JLabel lblNewLabel_16 = new JLabel("Số điện thoại khách hàng:");
			lblNewLabel_16.setBounds(315, 30, 173, 14);
			panel_6.add(lblNewLabel_16);
			
			txtSDTQL = new JTextField();
			txtSDTQL.setBounds(498, 28, 130, 20);
			panel_6.add(txtSDTQL);
			txtSDTQL.setColumns(10);
			
			JLabel lblNewLabel_17 = new JLabel("Tên khách hàng:");
			lblNewLabel_17.setBounds(698, 30, 98, 14);
			panel_6.add(lblNewLabel_17);
			
			txtTenKhachHangQL = new JTextField();
			txtTenKhachHangQL.setBounds(823, 28, 130, 20);
			panel_6.add(txtTenKhachHangQL);
			txtTenKhachHangQL.setColumns(10);
			
			checkBoxSapXep = new JCheckBox("Sắp xếp tổng tiền");
			checkBoxSapXep.setBounds(1037, 27, 151, 21);
			checkBoxSapXep.setFocusable(false);
			panel_6.add(checkBoxSapXep);
			
			JPanel panel_7 = new JPanel();
			panel_7.setBackground(new Color(200, 221, 242));
			panel_7.setBounds(10, 11, 149, 27);
			pnlQuanLyDonHang.add(panel_7);
			panel_7.setLayout(null);
			
			JLabel lblNewLabel_13 = new JLabel("Chức năng");
			lblNewLabel_13.setBackground(new Color(200, 221, 242));
			lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_13.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel_13.setBounds(21, 4, 102, 20);
			panel_7.add(lblNewLabel_13);
			
			cboLocDonHang.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					chuyenDoiTable();
				}
			});
			add(tabbedPane);
			
			XuLyKhiCoDonHang();
			// đăng kí sự kiện cho các nút
			ActionListener ac = new ControllerGiaoHang(this);
			btnTaoDonHang.addActionListener(ac);
			btnXoaTrang.addActionListener(ac);
			btnTaoMaDonHang.addActionListener(ac);
			btnHuyDon.addActionListener(ac);
			btnInPhieuDonHang.addActionListener(ac);
			btnHuyTaoDon.addActionListener(ac);
			btnTinhKhoangCach.addActionListener(ac);
			btnThanhCong.addActionListener(ac);
			
			// đăng kí sự kiện cho table
			tbDonHang.addMouseListener(this);
			
			// đăng kí sự kiện cho các textfield tìm kiếm
			KeyListener key = new ControllerGiaoHang(this);
		
			txtTKMaDonHang.addKeyListener(key);
			txtTKTenKhachHang.addKeyListener(key);
			txtTKSDT_KH.addKeyListener(key);
			
			// tabQLGiaoHang
			txtMaDonHangQL.addKeyListener(key);
			txtSDTQL.addKeyListener(key);
			txtTenKhachHangQL.addKeyListener(key);
			txtSoKG.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					tinhThanhTienDH();
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			checkBoxKH.addActionListener(ac);
			checkBoxSapXep.addActionListener(ac);
	}
		
		public void XuLyKhiCoDonHang() {
			if(hd == null) {
				btnTaoDonHang.setEnabled(false);
				btnTaoMaDonHang.setEnabled(false);
				btnHuyTaoDon.setEnabled(false);
				btnTinhKhoangCach.setEnabled(false);
			}else {
				txtMaHoaDon.setText(hd.getMaHoaDon());
				txtTenNVLapHD.setText(hd.getNhanVien().getTenNhanVien());
				txtNgayLap.setText(hd.getNgayLap().toString());
			}
		}
		
		
		public void chuyenDoiTable() {
			int index = cboLocDonHang.getSelectedIndex();
			 
			if(index == 0) {
				modelQLDonHangHT.setRowCount(0);
				loaddulieulentableTabQL(modelQLDonHangHT, new DonGiaoHangBusImpl().layDSDonGiaoHang());
				tbTabQLDonHang.setModel(modelQLDonHangHT);
				xetKichThuocChoTable(tbTabQLDonHang);
			   
			}
			if(index == 1) {
				modelQLDonHangCHT.setRowCount(0);
				loaddulieulentableTabQL(modelQLDonHangCHT, new DonGiaoHangBusImpl().layDSDonGiaoHang());
				tbTabQLDonHang.setModel(modelQLDonHangCHT);
				tbTabQLDonHang.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				tbTabQLDonHang.getColumnModel().getColumn(0).setPreferredWidth(100);
				tbTabQLDonHang.getColumnModel().getColumn(1).setPreferredWidth(100);
				tbTabQLDonHang.getColumnModel().getColumn(2).setPreferredWidth(200);
				tbTabQLDonHang.getColumnModel().getColumn(3).setPreferredWidth(80);
				tbTabQLDonHang.getColumnModel().getColumn(4).setPreferredWidth(250);
				tbTabQLDonHang.getColumnModel().getColumn(5).setPreferredWidth(300);
			    for(int i = 6 ; i < tbTabQLDonHang.getColumnCount() - 1 ; i++) {
			    	tbTabQLDonHang.getColumnModel().getColumn(i).setPreferredWidth(120);
			    }  
			    tbTabQLDonHang.getColumnModel().getColumn(10).setPreferredWidth(300);
			}
		}
		
		// xử lý các thao tác trên đơn giao hàng
	    public void XuLyDonHang(String src) {
	    	if(src.equals("Chọn khách hàng mặc định")) {
	    		if(hd != null) {
	    			if(checkBoxKH.isSelected()) {
	    				if(hd.getKhachHang().getTenKhachHang().equalsIgnoreCase("Khách hàng lẻ")) {
	    					JOptionPane.showMessageDialog(this, "Vì là khách hàng lẻ nên vui lòng nhập thông tin");
	    					checkBoxKH.setSelected(false);
	    					return;
	    				}else {
	    					txtTenKhachHang.setText(hd.getKhachHang().getTenKhachHang());
			    			txtTenKhachHang.setEditable(false);
			    			txtSDT.setText(hd.getKhachHang().getSdt());
			    			txtSDT.setEditable(false);
	    				}
		    			
		    		}else {
		    			txtTenKhachHang.setText("");
		    			txtTenKhachHang.setEditable(true);
		    			txtSDT.setText("");
		    			txtSDT.setEditable(true);
		    		}
	    		}else {
	    			JOptionPane.showMessageDialog(this, "Không có hóa đơn để lấy tên khách hàng");
	    			checkBoxKH.setSelected(false);
	    			return;
	    		}
	    		
	    		
	    	}
	    	if(src.equals("Tạo đơn hàng")) {
	    		int sokg = soKgTemp;
	    		float soKm = soKmTemp;
	    		String maDonHang = txtMaDonHang.getText();
	    		String maHoaDon = txtMaHoaDon.getText();
                hd = new HoaDonBusImpl().TimHoaDonTheoMa(maHoaDon);
	    		String phuongThucThanhToan = cboPTTT.getSelectedItem().toString();
	    		String sdt = txtSDT.getText();
	    		String tenKhachHang = txtTenKhachHang.getText();
	    		String diaChi = txtDiaChi.getText();
                String ghiChu = "";
	    		String temp = txtThanhTien.getText().replace(',', ' ');
	    		float tienVanChuyen = Float.parseFloat(temp.substring(0, temp.length() - 3).replaceAll(" ", ""));
	    	    boolean trangThai = txtChaHonThnh.getText().equals("Chưa hoàn thành") ? false : true;
	    	    DonGiaoHang dgh = new DonGiaoHang(maDonHang, tenKhachHang, sdt, diaChi, sokg, trangThai, ghiChu, tienVanChuyen,phuongThucThanhToan, hd);
	    	    dgh.tinhSoKm(soKm);
	    	    
	    	    if(busGiaoHang.validateDuLieu(maDonHang,tenKhachHang, sdt, txtSoKG, txtSoKM)) {
	    	    	String text = kiemTraHoaDonDaGiaoChua();
	    	    	if(!text.equals("")) {
	    	    		JOptionPane.showMessageDialog(this, text);
	    	    		return;
	    	    	}
	    	    	if(busGiaoHang.themDonHangMoi(dgh)) {
		    			modelDonHang.setRowCount(0);
		    			loaddulieulentable(modelDonHang);
		    			JOptionPane.showMessageDialog(this, "Tạo đơn hàng thành công");
		    			xoaTrangThongTinDonHang();
		    			btnTaoDonHang.setEnabled(false);
		    			checkBoxKH.setSelected(false);
		    			btnTinhKhoangCach.setEnabled(false);
		    			btnHuyDon.setEnabled(false);
		    			btnTaoMaDonHang.setEnabled(false);
		    		}else {
		    			JOptionPane.showMessageDialog(this, "Tạo đơn hàng thất bại do mã đơn hàng đã tồn tại");
		    		}
	    	    }else {
	    	    	JOptionPane.showMessageDialog(this, busGiaoHang.getMessgage());
	    	    }
	    	    
	    		
	    	}
	    	if(src.equals("Xóa trắng")) {
	    		if(hd != null || !txtMaDonHang.getText().isEmpty()) {
	    			txtTenKhachHang.setText("");
		    		txtSDT.setText("");
		    		txtSoKG.setText("");
		    		txtDiaChi.setText("");
		    		txtSoKM.setText("");
		    		txtThanhTien.setText("0 VND");
		    		txtTenKhachHang.setEditable(true);
		    		txtSDT.setEditable(true);
		    		checkBoxKH.setSelected(false);
	    		}
	    		
	    	}
	    	if(src.equals("Hủy tạo đơn")) {
	    		if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy tạo đơn hàng này không?", "Yes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	    			xoaTrangThongTinDonHang();
		    		btnTaoDonHang.setEnabled(false);
	    		}else {
	    			JOptionPane.showMessageDialog(this, "Đơn giao hàng được giữ lại");
	    		}

	    	}
	    	if(src.equals("Tạo mã đơn hàng")) {            
	    		txtMaDonHang.setText(busGiaoHang.taoMaDonHang());
	    	}
	    	if(src.equals("Khoảng cách")) {
	    		MapGui mapui = new MapGui(this);
	    		mapui.setVisible(true);	
	    	}
	    	if(src.equals("Bị hủy")) {
	    		int index = tbDonHang.getSelectedRow();
	    		if(index == -1) {
	    			JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng bị hủy");
	    			return;
	    		}else {
	    			String maDH = modelDonHang.getValueAt(index, 0).toString();
		    		DonGiaoHang dghCT = new DonGiaoHangBusImpl().timDonHangTheoMaDonHang(maDH);
		    	    String ghiChu = JOptionPane.showInputDialog(this, "Nhập lý do đơn hàng bị hủy");
		    	    if(ghiChu != null) {
		    	    	if(ghiChu.equals("")) {
		    	    		JOptionPane.showMessageDialog(this, "Vui lòng nhập lý do hủy đơn hàng");
		    	    		return;
		    	    	}else {
		    	    		dghCT.setGhiChu(ghiChu);
				    		modelQLDonHangCHT.addRow(new Object[] {dghCT.getMaDonGiaoHang(), dghCT.getHoaDon().getMaHoaDon(), 
				    				dghCT.getHoaDon().getNhanVien().getTenNhanVien(), dghCT.getHoaDon().getNgayLap(),
				    				dghCT.getTenKhachHang(), dghCT.getDiaChi(), dghCT.getSdt(), dghCT.getSoKg(), new Tool().dinhDangTien(dghCT.getTienVanChuyen()),
				                    dghCT.isTrangThai() == false ? "Chưa hoàn thành" : "", dghCT.getGhiChu()});
				    		if(new DonGiaoHangBusImpl().capNhatThongTinDonHang(dghCT)) {
				    			xoaTrangThongTinDonHang();
				    			JOptionPane.showMessageDialog(this, "Đơn hàng đã bị hủy");
				    			modelDonHang.removeRow(index);
				    		}else {
				    			JOptionPane.showMessageDialog(this, "Hủy đơn hàng không thành công do gặp lỗi");
				    			return;
				    		}
		    	    	}
		    	    	
		    	    }
	    		}

	    	}
	    	if(src.equals("Thành công")) {
	    		int index = tbDonHang.getSelectedRow();
	    		if(index == -1) {
	    			JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng giao thành công");
	    			return;
	    		}else {
	    			String maDH = modelDonHang.getValueAt(index, 0).toString();
		    		DonGiaoHang dghCT = new DonGiaoHangBusImpl().timDonHangTheoMaDonHang(maDH);
		    		dghCT.setTrangThai(true);
		    		modelQLDonHangHT.addRow(new Object[] {dghCT.getMaDonGiaoHang(), dghCT.getHoaDon().getMaHoaDon(), 
		    				dghCT.getHoaDon().getNhanVien().getTenNhanVien(), dghCT.getHoaDon().getNgayLap(),
		    				dghCT.getTenKhachHang(), dghCT.getDiaChi(), dghCT.getSdt(), dghCT.getSoKg(), new Tool().dinhDangTien(dghCT.getTienVanChuyen()),
		                    dghCT.isTrangThai() ? "Hoàn thành": ""});
		    		if(new DonGiaoHangBusImpl().capNhatThongTinDonHang(dghCT)) {
		    			xoaTrangThongTinDonHang();
		    			JOptionPane.showMessageDialog(this, "Đơn hàng giao thành công");
		    			modelDonHang.removeRow(index);
		    		}
	    		}
	    		
	    	}
	    	if(src.equals("Sắp xếp tổng tiền")) {
	    		
	    		if(checkBoxSapXep.isSelected()) {
	    			List<DonGiaoHang> dsTemp = new DonGiaoHangBusImpl().sapXepTheoTongTienVanChuyenTangDan(dsTemp_one);
	    			if(cboLocDonHang.getSelectedIndex() == 0) {
		    			modelQLDonHangHT.setRowCount(0);
		    			for(DonGiaoHang dgh : dsTemp) {
		    				if(dgh.isTrangThai() == true) {
		    	    			modelQLDonHangHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		    		                    dgh.isTrangThai() ? "Hoàn thành": ""});
		    	    		}
		    			}
		    		}
		    		if(cboLocDonHang.getSelectedIndex() == 1) {
		    			modelQLDonHangCHT.setRowCount(0);
		    			for(DonGiaoHang dgh : dsTemp) {
		    				if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("") == false) {
		    					modelQLDonHangCHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		    		                    dgh.isTrangThai() == false ? "Chưa hoàn thành": "", dgh.getGhiChu()});
		    	    		}
		    			}
		    				
		    		}
	    		}else {
	    			List<DonGiaoHang> dsTemp = new DonGiaoHangBusImpl().sapXepTheoTongTienVanChuyenGiamDan(dsTemp_one);
	    			if(cboLocDonHang.getSelectedIndex() == 0) {
		    			modelQLDonHangHT.setRowCount(0);
		    			for(DonGiaoHang dgh : dsTemp) {
		    				if(dgh.isTrangThai() == true) {
		    	    			modelQLDonHangHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		    		                    dgh.isTrangThai() ? "Hoàn thành": ""});
		    	    		}
		    			}
		    		}
		    		if(cboLocDonHang.getSelectedIndex() == 1) {
		    			modelQLDonHangCHT.setRowCount(0);
		    			for(DonGiaoHang dgh : dsTemp) {
		    				if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("") == false) {
		    					modelQLDonHangCHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		    		                    dgh.isTrangThai() == false ? "Chưa hoàn thành": "", dgh.getGhiChu()});
		    	    		}
		    			}
		    				
		    		}
	    		}
	    		
	    	}
	    	if(src.equals("In phiếu đơn hàng")) {
	    		int index = tbDonHang.getSelectedRow();
	    		if(index != -1) {
	    			String ma = modelDonHang.getValueAt(index, 0).toString();
		    		DonGiaoHang dghCT = new DonGiaoHangBusImpl().timDonHangTheoMaDonHang(ma);
		    		Tool printer = new Tool();
		    		//printer.inPhieuGiaoHang(dghCT);
		    		JOptionPane.showMessageDialog(this, "In phiếu đơn hàng thành công");
		    		xoaTrangThongTinDonHang();
		    		tbDonHang.clearSelection();
	    		}else {
	    			JOptionPane.showMessageDialog(this, "Vui lòng chọn đơn hàng cần in phiếu");
	    			return;
	    		}
	    		
	    	}
	    }
	    
	    public void hoanThanhKM(String txt) {
	    	String km = xuLyChuoiKilomet(txt);
	    	txtSoKM.setText(km);
	    }
	    
//	    public void layDiaChi(String diaDiem) {
//	    	txtDiaChi.setText(diaDiem);
//	    }
//	    public void kiemTraDeDongMap(String src, MapGui map) {
//	    	if(src.equals("Hoàn tất")) {
//	    		map.setVisible(false);
//	    	}
//	    }
	    
	    public void tinhThanhTienDH() {
	    	
	    	if(txtSoKG.getText().equals("") == false && txtSoKM.getText().equals("") == false) {
    			DonGiaoHang dh = new DonGiaoHang(txtMaDonHang.getText(), hd);
    			soKgTemp = Integer.parseInt(txtSoKG.getText());
    			soKmTemp = Float.parseFloat(txtSoKM.getText());
    			dh.tinhSoKm(soKmTemp);
    			dh.setSoKg(soKgTemp);
    			float result = dh.tinhTienVanChuyen();
    			if(result == -1) {
    				JOptionPane.showMessageDialog(this, "Số kilomet trên mức quy định nên không giao hàng");
    				return;
    			}else if(result == 0){
    				JOptionPane.showMessageDialog(this, "Đơn hàng được vận chuyển miễn phí");
    				txtThanhTien.setText(new Tool().dinhDangTien(result) + "");
    			}
    			else{
    				txtThanhTien.setText(new Tool().dinhDangTien(result) + "");
    			}
    		}
	    }
	    
	    public String xuLyChuoiKilomet(String kilomet) {
	    	char[] c = kilomet.toCharArray();
	    	int index = 0;
	    	for(int i = 0 ; i < c.length ; i++) {
	    		if(c[i] == 'K') {
	    			index = i;
	    		}
	    	}
	    	return kilomet.substring(0, index);
	    }
	    
	    public void xoaTrangThongTinDonHang() {
	    	txtMaDonHang.setText("");
    		txtMaHoaDon.setText("");
    		txtTenNVLapHD.setText("");
    		txtTenKhachHang.setText("");
    		txtSDT.setText("");
    		txtSoKG.setText("");
    		txtDiaChi.setText("");
    		txtSoKM.setText("");
    		txtThanhTien.setText("");
    		txtNgayLap.setText("");
	    }
	    
	    public void loaddulieulentable(DefaultTableModel modelTemp) {
	    	List<DonGiaoHang> ds = new DonGiaoHangBusImpl().layDSDonGiaoHang();
	    	for(DonGiaoHang dgh : ds) {
	    		if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("")) {
	    		modelTemp.addRow(new Object[] {dgh.getMaDonGiaoHang(),dgh.getHoaDon().getMaHoaDon(), dgh.getHoaDon().getNhanVien().getTenNhanVien(),
	    									dgh.getHoaDon().getNgayLap(), dgh.getTenKhachHang(), dgh.getDiaChi(),
	    									dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()), dgh.isTrangThai() ? "" : "chưa hoàn thành"});
	    		}
	    	}
	    }
	    
	    public void loaddulieulentableTabQL(DefaultTableModel modelTemp, List<DonGiaoHang> ds) {
	    	for(DonGiaoHang dgh : ds) {
	    		if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("") == false) {
	    			modelQLDonHangCHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		                    dgh.isTrangThai() == false ? "Chưa hoàn thành": "", dgh.getGhiChu()});
	    		}
	    		if(dgh.isTrangThai()) {
	    			modelQLDonHangHT.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
		    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
		    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), new Tool().dinhDangTien(dgh.getTienVanChuyen()),
		                    dgh.isTrangThai() ? "Hoàn thành": ""});
	    		}
	    	}
	    }
	    
	    
	    public void xuLySuKienTimKiem(KeyEvent e) {
	    	char keyWord = e.getKeyChar();
	    	if((Character.isLetterOrDigit(keyWord) || keyWord == e.VK_BACK_SPACE) && tabbedPane.getSelectedIndex() == 0) {
	    		xuLyTimKiemTheoCacFieldTabGiaoHang(new DonGiaoHangBusImpl().layDSDonGiaoHang());
	    	}
	    	if((Character.isLetterOrDigit(keyWord) || keyWord == e.VK_BACK_SPACE) && tabbedPane.getSelectedIndex() == 1) {
	    		xuLyTimKiemTheoCacFieldTabQLGiaoHang(new DonGiaoHangBusImpl().layDSDonGiaoHang());
	    	}
	    }
	    
	    public void xuLyTimKiemTheoCacFieldTabGiaoHang(List<DonGiaoHang> ds) {
	    	xuLyTrenFieldTimMaDonHang(ds);
	    	xuLyTrenFieldSDT(ds);
	    	xuLyTrenFieldTenKhachHang(ds);
	    	modelDonHang.setRowCount(0);
	    	loadDuLieuLenBangTheoYeuCau(modelDonHang, ds);
	    	
	    }
	    
	    public void xuLyTrenFieldTimMaDonHang(List<DonGiaoHang> ds) {
	    	String text = txtTKMaDonHang.getText().trim();
	        ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern maDonHangPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        for(DonGiaoHang dgh : ds) {
	        	if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("")) {
	        		if(maDonHangPattern.matcher(dgh.getMaDonGiaoHang()).find()) {
		        		temp.add(dgh);
		        	}
	        	}
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void xuLyTrenFieldSDT(List<DonGiaoHang> ds) {
	    	String text = txtTKSDT_KH.getText().trim();
	    	ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern sdt_KH_Pattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        for(DonGiaoHang dgh : ds) {
	        	if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("")) {
	        		if(sdt_KH_Pattern.matcher(dgh.getSdt()).find()) {
		        		temp.add(dgh);
		        	}
	        	}
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void xuLyTrenFieldTenKhachHang(List<DonGiaoHang> ds) {
	    	String text = txtTKTenKhachHang.getText().trim();
	    	ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern tenKhachHangPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        for(DonGiaoHang dgh : ds) {
	        	if(dgh.isTrangThai() == false && dgh.getGhiChu().equals("")) {
	        		if(tenKhachHangPattern.matcher(dgh.getTenKhachHang()).find()) {
		        		temp.add(dgh);
		        	}
	        	}
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void loadDuLieuLenBangTheoYeuCau(DefaultTableModel modelTemp, List<DonGiaoHang> ds) {
	    	for(DonGiaoHang dgh : ds) {
	    		modelTemp.addRow(new Object[] {dgh.getMaDonGiaoHang(), dgh.getHoaDon().getMaHoaDon(), 
	    				dgh.getHoaDon().getNhanVien().getTenNhanVien(), dgh.getHoaDon().getNgayLap(),
	    				dgh.getTenKhachHang(), dgh.getDiaChi(), dgh.getSdt(), dgh.getSoKg(), dgh.getTienVanChuyen(),
	                    dgh.isTrangThai() == false ? "Đang giao hàng": ""});
	    	}
	    }
	    
	    public void xuLyTimKiemTheoCacFieldTabQLGiaoHang(List<DonGiaoHang> ds) {
	    	xuLyTimKiemFieldMaDonHangTabQl(ds);
	    	xuLyTimKiemFieldSDTTabQL(ds);
	    	xuLyTimKiemFieldTenKhachHangTabQL(ds);
	    	if(cboLocDonHang.getSelectedIndex() == 0) {
	    		dsTemp_one.clear();
	    		modelQLDonHangHT.setRowCount(0);
	    		dsTemp_one.addAll(ds);
	    		loaddulieulentableTabQL(modelQLDonHangHT, ds);
	    	}else {
	    		dsTemp_one.clear();
	    		modelQLDonHangCHT.setRowCount(0);
	    		dsTemp_one.addAll(ds);
	    		loaddulieulentableTabQL(modelQLDonHangCHT, ds);
	    	}
	    	
	    }
	    
	    public void xuLyTimKiemFieldMaDonHangTabQl(List<DonGiaoHang> ds) {
	    	String text = txtMaDonHangQL.getText().trim();
	    	ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern maDonHangTabQLPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        if(cboLocDonHang.getSelectedIndex() == 0) {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == true && dgh.getGhiChu().equals("")) {
		        		if(maDonHangTabQLPattern.matcher(dgh.getMaDonGiaoHang()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }else {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == false && !dgh.getGhiChu().equals("")) {
		        		if(maDonHangTabQLPattern.matcher(dgh.getMaDonGiaoHang()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void xuLyTimKiemFieldTenKhachHangTabQL(List<DonGiaoHang> ds) {
	    	String text = txtTenKhachHangQL.getText().trim();
	    	ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern tenKhachHangTabQLPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        if(cboLocDonHang.getSelectedIndex() == 0) {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == true && dgh.getGhiChu().equals("")) {
		        		if(tenKhachHangTabQLPattern.matcher(dgh.getTenKhachHang()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }else {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == false && !dgh.getGhiChu().equals("")) {
		        		if(tenKhachHangTabQLPattern.matcher(dgh.getTenKhachHang()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void xuLyTimKiemFieldSDTTabQL(List<DonGiaoHang> ds) {
	    	String text = txtSDTQL.getText().trim();
	    	ArrayList<DonGiaoHang> temp = new ArrayList<>();
	        Pattern sdtTabQLPattern = Pattern.compile(text, Pattern.CASE_INSENSITIVE);
	        if(cboLocDonHang.getSelectedIndex() == 0) {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == true && dgh.getGhiChu().equals("")) {
		        		if(sdtTabQLPattern.matcher(dgh.getSdt()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }else {
	        	for(DonGiaoHang dgh : ds) {
		        	if(dgh.isTrangThai() == false && !dgh.getGhiChu().equals("")) {
		        		if(sdtTabQLPattern.matcher(dgh.getSdt()).find()) {
			        		temp.add(dgh);
			        	}
		        	}
		        }
	        }
	        ds.clear();
	        ds.addAll(temp);
	    }
	    
	    public void xetKichThuocChoTable(MyTable tb) {
	    	tb.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    	tb.getColumnModel().getColumn(0).setPreferredWidth(100);
	    	tb.getColumnModel().getColumn(1).setPreferredWidth(100);
	    	tb.getColumnModel().getColumn(2).setPreferredWidth(180);
	    	tb.getColumnModel().getColumn(3).setPreferredWidth(80);
	    	tb.getColumnModel().getColumn(4).setPreferredWidth(180);
	    	tb.getColumnModel().getColumn(5).setPreferredWidth(300);
	    	tb.getColumnModel().getColumn(6).setPreferredWidth(100);
	    	tb.getColumnModel().getColumn(7).setPreferredWidth(80);
	    	tb.getColumnModel().getColumn(8).setPreferredWidth(120);
	    	tb.getColumnModel().getColumn(9).setPreferredWidth(150); 
		   
	    }

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int index = tbDonHang.getSelectedRow();
			txtMaDonHang.setText(modelDonHang.getValueAt(index, 0).toString());
			DonGiaoHang dgh = new DonGiaoHangBusImpl().timDonHangTheoMaDonHang(txtMaDonHang.getText());
			txtMaHoaDon.setText(dgh.getHoaDon().getMaHoaDon());
			txtNgayLap.setText(dgh.getHoaDon().getNgayLap() + "");
			txtTenNVLapHD.setText(dgh.getHoaDon().getNhanVien().getTenNhanVien());
			cboPTTT.setSelectedIndex(dgh.getPhuongThucThanhToan().equals("Tiền mặt") == true ? 0: 1);
			txtTenKhachHang.setText(dgh.getTenKhachHang());
			txtSDT.setText(dgh.getSdt());
			txtDiaChi.setText(dgh.getDiaChi());
			txtSoKG.setText(dgh.getSoKg() + "");
			txtSoKM.setText(dgh.getSoKm() + "");
			txtThanhTien.setText(new Tool().dinhDangTien(dgh.getTienVanChuyen()));
			checkBoxKH.setEnabled(false);
			txtTenKhachHang.setEditable(false);
			txtSoKG.setEditable(false);
			txtSDT.setEditable(false);
			btnTinhKhoangCach.setEnabled(false);
			cboPTTT.setEnabled(false);
		}
		
		public String kiemTraHoaDonDaGiaoChua() {
			List<DonGiaoHang> ds = busGiaoHang.layDSDonHangDangGiao();
			List<DonGiaoHang> ds1 = busGiaoHang.layDSDonHangBiHuy();
			List<DonGiaoHang> ds2 = busGiaoHang.layDSDonHangGiaoThanhCong();
			for(DonGiaoHang dgh : ds) {
				if(dgh.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
					return "Hóa đơn này đang được giao\nVui lòng chọn hóa đơn khác";
				}
			}
			
			for(DonGiaoHang dgh : ds1) {
				if(dgh.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
					return "Hóa đơn này đã thuộc đơn hàng bị hủy, không cho phép tạo lại đơn mới";
				}
			}
			
			for(DonGiaoHang dgh : ds2) {
				if(dgh.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
					return "Hóa đơn này đã giao thành công\nVui lòng chọn hóa đơn khác";
				}
			}
			return "";
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
}
