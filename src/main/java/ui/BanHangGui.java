package ui;

import bus.impl.ChuongTrinhKhuyenMaiBusImpl;
import bus.impl.HoaDonBusImpl;
import bus.impl.KhachHangBusImpl;
import bus.impl.SanPhamBusImpl;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import controller.ControllerBanHang;
import customUI.MyButton;
import customUI.MyCombobox;
import customUI.MyTable;
import entity.*;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BanHangGui extends JPanel implements Runnable,ThreadFactory{
	private DefaultTableModel modelHoaDonCho, modelGioHang;
	private JTable tableHoaDonCho, tableGioHang;
	private JTextField txtTimKhachHang;
	private JTextField txtMaSanPham;
	private JTextField txtTienKhachDua;
	private boolean guiHoaDonIsNull = true;
	String resultScanQRBefore = "null";
	private PopUp popUp = new PopUp("Đang xử lý");
	
	// gui hóa đơn
	private HoaDonGui guiHoaDon = null;
	private BanHangGui thisGUI = null;

	//QR code
	private TrangChu view;
	private WebcamPanel panel = null;
	private Webcam webcam = null;
	private Executor executor = Executors.newSingleThreadExecutor(this);
	private JPanel pnlKhungQuetMa;
	private ImageIcon iconLblBgr;
	private JLabel lblAnhSanPham;
	private JLabel lblMaSach;
	private JLabel lblTenSach;
	private JPanel pnlSanPhamSach;
	private JPanel pnlSanPham;
	private JLabel lblThueSach;
	private JLabel lblGiaBanSach;
	private JLabel lblTheLoaiSach;
	private JLabel lblTacGia;
	private JLabel lblNhaXuatBan;
	private JLabel lblNamSanXuat;
	private JLabel lblSoLuongTonSach;
	private JPanel pnlSanPhamVPP;
	private JLabel lblMaVPP;
	private JLabel lblTenVPP;
	private JLabel lblThueVPP;
	private JLabel lblGiaVPP;
	private JLabel lblTheLoaiVPP;
	private JLabel lblDanhMuc;
	private JLabel lblChatLieu;
	private JLabel lblSoLuongTonVPP;
	private JLabel lblMaKhachHang;
	private JLabel lblTenKhachHang;
	private JLabel lblDiemTichLuy;
	//	khai báo bus
	private HoaDonBusImpl busHoaDon = new HoaDonBusImpl();
	private KhachHangBusImpl busKhachHang = new KhachHangBusImpl();
	private ChuongTrinhKhuyenMaiBusImpl busCTKM = new ChuongTrinhKhuyenMaiBusImpl();
	private SanPhamBusImpl busSanPham = new SanPhamBusImpl();
	//	Phần dữ liệu cho hóa đơn
	private List<HoaDon> dsHoaDonCho = new ArrayList<>();
	private NhanVien nhanVienBanHang = null;
	private HoaDon hoaDonHienTai = null;
	private KhachHang khachHang = busKhachHang.timKhachHangTheoMa("KH0");
	private ChuongTrinhKhuyenMai ctkmCuaHoaDon = busCTKM.timChuongTrinhKhuyenMaiDangApDung(true);
	private SanPham sanPhamTim = null;
	private JLabel lblMaHoaDon;
	private JLabel lblTongTien;
	private JLabel lblTenChuongTrinhKhuyenMai;
	private JLabel lblThanhToan;
	private JLabel lblTienThua;
	private JTextArea txtGhiChu;
	private MyCombobox cbbHinhThucThanhToan;
	private MyCombobox cbbDiemGiamGia;
	private MyButton btnHuyHoaDon;
	private MyButton btnLamMoiHoaDon;
	private MyButton btnTaoMoiHoaDon;
	private MyButton btnThanhToan;
	private ControllerBanHang acBanHang = new ControllerBanHang(this);
	private MyButton btnTimSanPham;
	private MyButton btnTimKhachHang;
	private MyButton btnThemSanPham;
	private MyButton btnXoaTatCa;
	private MyButton btnXoa;
	private MyButton btnCapNhat;
	private JTextField txtTimHoaDonCho;
	private JLabel lblTienGiamCTKM;
	private JLabel lblTienGiamDGG;
	private JLabel lblTongThue;
	public BanHangGui(TrangChu view, ArrayList<HoaDon> ds, NhanVien nv) {
		this.view = view;
		this.dsHoaDonCho = ds;
		this.hoaDonHienTai = dsHoaDonCho.size() == 0 ? null : dsHoaDonCho.get(0);
		this.nhanVienBanHang = nv;
		this.setBackground(new Color(255, 255, 255));
		this.setBounds(250, 0, 1302, 800);
		setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1292, 793);
		add(tabbedPane);

		//		Phần bán hàng
		JPanel pnlBanHang = new JPanel();
		tabbedPane.addTab("Bán hàng", null, pnlBanHang, null);
		tabbedPane.addTab("Quản lý hóa đơn", null, null, null);
		pnlBanHang.setLayout(null);

		JPanel pnlThanhToan = new JPanel();
		pnlThanhToan.setBackground(new Color(255, 255, 255));
		pnlThanhToan.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
		pnlThanhToan.setBounds(959, 10, 318, 746);
		pnlBanHang.add(pnlThanhToan);
		pnlThanhToan.setLayout(null);

		JLabel lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMHan.setBounds(10, 10, 86, 30);
		pnlThanhToan.add(lblMHan);

		JLabel lblTngTin = new JLabel("Tổng tiền:");
		lblTngTin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTngTin.setBounds(10, 50, 72, 30);
		pnlThanhToan.add(lblTngTin);

		JLabel lblimGimGi = new JLabel("Điểm giảm giá:");
		lblimGimGi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblimGimGi.setBounds(10, 170, 99, 30);
		pnlThanhToan.add(lblimGimGi);

		JLabel lblTinGimGi = new JLabel("Tên CTKM:");
		lblTinGimGi.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinGimGi.setBounds(10, 90, 99, 30);
		pnlThanhToan.add(lblTinGimGi);

		JLabel lblThanhTon = new JLabel("Thanh toán:");
		lblThanhTon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblThanhTon.setBounds(10, 290, 86, 30);
		pnlThanhToan.add(lblThanhTon);

		JLabel lblTinKhcha = new JLabel("Tiền khách đưa:");
		lblTinKhcha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinKhcha.setBounds(10, 330, 111, 30);
		pnlThanhToan.add(lblTinKhcha);

		JLabel lblTinTha = new JLabel("Tiền thừa:");
		lblTinTha.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinTha.setBounds(10, 370, 72, 30);
		pnlThanhToan.add(lblTinTha);

		JLabel lblHnhThcThanh = new JLabel("Hình thức TT:");
		lblHnhThcThanh.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHnhThcThanh.setBounds(10, 410, 99, 30);
		pnlThanhToan.add(lblHnhThcThanh);

		JLabel lblHnhThcThanh_1_1 = new JLabel("Ghi chú:");
		lblHnhThcThanh_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblHnhThcThanh_1_1.setBounds(10, 450, 65, 30);
		pnlThanhToan.add(lblHnhThcThanh_1_1);

		lblMaHoaDon = new JLabel("Vui lòng tạo!");
		lblMaHoaDon.setForeground(new Color(255, 128, 64));
		lblMaHoaDon.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMaHoaDon.setBounds(170, 10, 138, 30);
		pnlThanhToan.add(lblMaHoaDon);

		lblTongTien = new JLabel("0");
		lblTongTien.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongTien.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongTien.setBounds(170, 50, 138, 30);
		pnlThanhToan.add(lblTongTien);

		lblTenChuongTrinhKhuyenMai = new JLabel(ctkmCuaHoaDon != null ? ctkmCuaHoaDon.getTenCTKM() : "Không có CTKM");
		lblTenChuongTrinhKhuyenMai.setHorizontalAlignment(SwingConstants.LEFT);
		lblTenChuongTrinhKhuyenMai.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTenChuongTrinhKhuyenMai.setBounds(170, 90, 138, 30);
		pnlThanhToan.add(lblTenChuongTrinhKhuyenMai);

		lblThanhToan = new JLabel("0");
		lblThanhToan.setForeground(new Color(255, 128, 64));
		lblThanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		lblThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThanhToan.setBounds(170, 290, 138, 30);
		pnlThanhToan.add(lblThanhToan);

		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setName("txtTienKhachDua");
		txtTienKhachDua.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtTienKhachDua.setBounds(170, 332, 138, 30);
		pnlThanhToan.add(txtTienKhachDua);
		txtTienKhachDua.setColumns(10);

		lblTienThua = new JLabel("0");
		lblTienThua.setForeground(new Color(0, 128, 255));
		lblTienThua.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienThua.setBounds(170, 370, 138, 30);
		pnlThanhToan.add(lblTienThua);

		txtGhiChu = new JTextArea();
		txtGhiChu.setBounds(10, 480, 298, 88);
		txtGhiChu.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlThanhToan.add(txtGhiChu);

		cbbHinhThucThanhToan = new MyCombobox();
		cbbHinhThucThanhToan.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbbHinhThucThanhToan.setBounds(170, 410, 138, 30);
		cbbHinhThucThanhToan.addItem("Tiền mặt");
		cbbHinhThucThanhToan.addItem("Chuyển khoản");
		pnlThanhToan.add(cbbHinhThucThanhToan);

		cbbDiemGiamGia = new MyCombobox();
		cbbDiemGiamGia.setActionCommand("cbbDiemGiamGia");
		cbbDiemGiamGia.setFont(new Font("Tahoma", Font.BOLD, 13));
		cbbDiemGiamGia.setBounds(170, 170, 138, 30);
		pnlThanhToan.add(cbbDiemGiamGia);

		btnHuyHoaDon = new MyButton("Hủy hóa đơn");
		btnHuyHoaDon.setActionCommand("btnHuyHoaDon");
		btnHuyHoaDon.setForeground(new Color(255, 255, 255));
		btnHuyHoaDon.setBackground(new Color(255, 0, 0));
		btnHuyHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnHuyHoaDon.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\iconnhantrang.png"));
		btnHuyHoaDon.setBounds(10, 578, 140, 30);
		pnlThanhToan.add(btnHuyHoaDon);

		btnLamMoiHoaDon = new MyButton("Làm mới");
		btnLamMoiHoaDon.setActionCommand("btnLamMoiHoaDon");
		btnLamMoiHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLamMoiHoaDon.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\iconlammoi.png"));
		btnLamMoiHoaDon.setBounds(168, 578, 140, 30);
		pnlThanhToan.add(btnLamMoiHoaDon);

		btnTaoMoiHoaDon = new MyButton("Tạo hóa đơn");
		btnTaoMoiHoaDon.setActionCommand("btnTaoMoiHoaDon");
		btnTaoMoiHoaDon.setBackground(new Color(255, 255, 128));
		btnTaoMoiHoaDon.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnTaoMoiHoaDon.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\icontaomoi.png"));
		btnTaoMoiHoaDon.setBounds(10, 618, 298, 39);
		pnlThanhToan.add(btnTaoMoiHoaDon);

		btnThanhToan = new MyButton("Thanh toán");
		btnThanhToan.setActionCommand("btnThanhToan");
		btnThanhToan.setBackground(new Color(128, 255, 128));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThanhToan.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\iconthanhtoan.png"));
		btnThanhToan.setBounds(10, 667, 298, 69);
		pnlThanhToan.add(btnThanhToan);

		JLabel lblTinGimCtkm = new JLabel("Tiền giảm CTKM:");
		lblTinGimCtkm.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinGimCtkm.setBounds(10, 130, 111, 30);
		pnlThanhToan.add(lblTinGimCtkm);

		lblTienGiamCTKM = new JLabel("0");
		lblTienGiamCTKM.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienGiamCTKM.setForeground(new Color(0, 0, 0));
		lblTienGiamCTKM.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienGiamCTKM.setBounds(170, 130, 138, 30);
		pnlThanhToan.add(lblTienGiamCTKM);

		JLabel lblTinGimDgg = new JLabel("Tiền giảm DGG:");
		lblTinGimDgg.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTinGimDgg.setBounds(10, 210, 111, 30);
		pnlThanhToan.add(lblTinGimDgg);

		lblTienGiamDGG = new JLabel("0");
		lblTienGiamDGG.setHorizontalAlignment(SwingConstants.LEFT);
		lblTienGiamDGG.setForeground(new Color(0, 0, 0));
		lblTienGiamDGG.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTienGiamDGG.setBounds(170, 210, 138, 30);
		pnlThanhToan.add(lblTienGiamDGG);

		JLabel lblTngThu = new JLabel("Tổng thuế:");
		lblTngThu.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTngThu.setBounds(10, 250, 111, 30);
		pnlThanhToan.add(lblTngThu);

		lblTongThue = new JLabel("0");
		lblTongThue.setHorizontalAlignment(SwingConstants.LEFT);
		lblTongThue.setForeground(new Color(0, 0, 0));
		lblTongThue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTongThue.setBounds(170, 250, 138, 30);
		pnlThanhToan.add(lblTongThue);

		//		Phần panel cho hóa đơn chờ
		JPanel pnlHoaDonCho = new JPanel();
		pnlHoaDonCho.setBackground(new Color(255, 255, 255));
		pnlHoaDonCho.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlHoaDonCho.setBounds(10, 10, 592, 200);
		pnlBanHang.add(pnlHoaDonCho);
		pnlHoaDonCho.setLayout(null);

		JLabel lblTitleHoaDonCho = new JLabel("Hóa đơn chờ");
		lblTitleHoaDonCho.setForeground(new Color(128, 128, 128));
		lblTitleHoaDonCho.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitleHoaDonCho.setBounds(10, 10, 111, 20);
		pnlHoaDonCho.add(lblTitleHoaDonCho);

		String[] colsHoaDonCho = {"Mã hóa đơn", "Ngày tạo","Nhân viên tạo", "Tên khách hàng", "Số điện thoại"};
		modelHoaDonCho = new DefaultTableModel(colsHoaDonCho, 0);
		tableHoaDonCho = new MyTable(modelHoaDonCho);
		tableHoaDonCho.setName("tableHoaDonCho");
		JScrollPane srcTbHoaDonCho = new JScrollPane(tableHoaDonCho);
		srcTbHoaDonCho.setBounds(10, 69, 572, 121);
		pnlHoaDonCho.add(srcTbHoaDonCho);

		JLabel lblTmHan_1 = new JLabel("Tìm kiếm theo SDT khách hàng:");
		lblTmHan_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTmHan_1.setBounds(10, 34, 202, 20);
		pnlHoaDonCho.add(lblTmHan_1);

		txtTimHoaDonCho = new JTextField();
		txtTimHoaDonCho.setName("keyTxtTimHoaDonCho");
		txtTimHoaDonCho.setColumns(10);
		txtTimHoaDonCho.setBounds(213, 34, 369, 25);
		pnlHoaDonCho.add(txtTimHoaDonCho);
		//		 Phần panel giỏ hàng
		JPanel pnlGioHang = new JPanel();
		pnlGioHang.setBackground(new Color(255, 255, 255));
		pnlGioHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlGioHang.setBounds(10, 525, 939, 230);
		pnlBanHang.add(pnlGioHang);
		pnlGioHang.setLayout(null);

		JLabel lblTitleGiohang = new JLabel("Giỏ hàng");
		lblTitleGiohang.setForeground(new Color(128, 128, 128));
		lblTitleGiohang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitleGiohang.setBounds(10, 10, 111, 20);
		pnlGioHang.add(lblTitleGiohang);

		String[] colsGiohang = {"Mã sản phẩm", "Tên sản phẩm", "Đơn giá", "Thuế","Số lượng", "Giảm giá", "Thành tiền"};
		modelGioHang = new DefaultTableModel(colsGiohang, 0);
		tableGioHang = new MyTable(modelGioHang);
		JScrollPane srcTbGioHang = new JScrollPane(tableGioHang);
		srcTbGioHang.setBounds(10, 39, 672, 181);
		tableGioHang.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableGioHang.getColumnModel().getColumn(1).setPreferredWidth(130);
		pnlGioHang.add(srcTbGioHang);

		btnXoaTatCa = new MyButton("Xóa tất cả");
		btnXoaTatCa.setActionCommand("btnXoaTatCa");
		btnXoaTatCa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoaTatCa.setBounds(741, 55, 151, 35);
		pnlGioHang.add(btnXoaTatCa);

		btnXoa = new MyButton("Xóa sản phẩm");
		btnXoa.setActionCommand("btnXoa");
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnXoa.setBounds(741, 109, 151, 35);
		pnlGioHang.add(btnXoa);

		btnCapNhat = new MyButton("Cập nhật số lượng");
		btnCapNhat.setActionCommand("btnCapNhat");
		btnCapNhat.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCapNhat.setBounds(741, 164, 151, 35);
		pnlGioHang.add(btnCapNhat);

		//		Phần panel sản phẩm
		pnlSanPham = new JPanel();
		pnlSanPham.setBackground(new Color(255, 255, 255));
		pnlSanPham.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlSanPham.setBounds(272, 220, 677, 295);
		pnlBanHang.add(pnlSanPham);
		pnlSanPham.setLayout(null);

		JLabel lblTimSanPham = new JLabel("Mã sản phẩm:");
		lblTimSanPham.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTimSanPham.setBounds(10, 17, 93, 20);
		pnlSanPham.add(lblTimSanPham);

		txtMaSanPham = new JTextField();
		txtMaSanPham.setActionCommand("txtMaSanPham");
		txtMaSanPham.setColumns(10);
		txtMaSanPham.setBounds(102, 17, 440, 25);
		pnlSanPham.add(txtMaSanPham);

		btnTimSanPham = new MyButton("Tìm sản phẩm");
		btnTimSanPham.setActionCommand("btnTimSanPham");
		btnTimSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimSanPham.setBounds(552, 17, 119, 25);
		pnlSanPham.add(btnTimSanPham);

		btnThemSanPham = new MyButton("Thêm sản phẩm");
		btnThemSanPham.setActionCommand("btnThemSanPham");
		btnThemSanPham.setText("Thêm");
		btnThemSanPham.setBackground(new Color(255, 255, 128));
		btnThemSanPham.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\iconcong.png"));
		btnThemSanPham.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnThemSanPham.setBounds(540, 165, 131, 35);
		pnlSanPham.add(btnThemSanPham);

		lblAnhSanPham = new JLabel();
		lblAnhSanPham.setBounds(21, 77, 131, 189);
		lblAnhSanPham.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		//		lấy hình gốc
		iconLblBgr = new ImageIcon("src\\image\\imagepanel\\logobrand.jpg");
		//		phóng to hình
		Image scaledImage = ((ImageIcon) iconLblBgr).getImage().getScaledInstance(lblAnhSanPham.getWidth(), lblAnhSanPham.getHeight(), Image.SCALE_SMOOTH);
		//		gán lại hình
		iconLblBgr = new ImageIcon(scaledImage);
		lblAnhSanPham.setIcon(iconLblBgr);
		pnlSanPham.add(lblAnhSanPham);

		pnlSanPhamSach = new JPanel();
		pnlSanPhamSach.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlSanPhamSach.setBounds(179, 52, 351, 233);
		pnlSanPham.add(pnlSanPhamSach);
		pnlSanPhamSach.setLayout(null);

		JLabel lblMSnPhm = new JLabel("Mã sản phẩm:");
		lblMSnPhm.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMSnPhm.setBounds(10, 10, 89, 15);
		pnlSanPhamSach.add(lblMSnPhm);

		lblMaSach = new JLabel();
		lblMaSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaSach.setBounds(112, 10, 229, 15);
		pnlSanPhamSach.add(lblMaSach);

		JLabel lblTnSnPhm = new JLabel("Tên sản phẩm:");
		lblTnSnPhm.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnSnPhm.setBounds(10, 35, 89, 15);
		pnlSanPhamSach.add(lblTnSnPhm);

		lblTenSach = new JLabel();
		lblTenSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenSach.setBounds(112, 35, 229, 15);
		pnlSanPhamSach.add(lblTenSach);

		JLabel lblThu = new JLabel("Thuế:");
		lblThu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThu.setBounds(10, 60, 89, 15);
		pnlSanPhamSach.add(lblThu);

		lblThueSach = new JLabel();
		lblThueSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThueSach.setBounds(112, 60, 229, 15);
		pnlSanPhamSach.add(lblThueSach);

		JLabel lblGiBn = new JLabel("Giá bán:");
		lblGiBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiBn.setBounds(10, 85, 89, 15);
		pnlSanPhamSach.add(lblGiBn);

		lblGiaBanSach = new JLabel();
		lblGiaBanSach.setForeground(new Color(255, 0, 0));
		lblGiaBanSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiaBanSach.setBounds(112, 85, 229, 15);
		pnlSanPhamSach.add(lblGiaBanSach);

		JLabel lblThLoi = new JLabel("Thể loại:");
		lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThLoi.setBounds(10, 110, 89, 15);
		pnlSanPhamSach.add(lblThLoi);

		lblTheLoaiSach = new JLabel();
		lblTheLoaiSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTheLoaiSach.setBounds(112, 110, 229, 15);
		pnlSanPhamSach.add(lblTheLoaiSach);

		JLabel lblTcGi = new JLabel("Tác giả:");
		lblTcGi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTcGi.setBounds(10, 135, 89, 15);
		pnlSanPhamSach.add(lblTcGi);

		lblTacGia = new JLabel();
		lblTacGia.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTacGia.setBounds(112, 135, 229, 15);
		pnlSanPhamSach.add(lblTacGia);

		JLabel lblNhXutBn = new JLabel("Nhà xuất bản:");
		lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhXutBn.setBounds(10, 160, 89, 15);
		pnlSanPhamSach.add(lblNhXutBn);

		lblNhaXuatBan = new JLabel();
		lblNhaXuatBan.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhaXuatBan.setBounds(112, 160, 229, 15);
		pnlSanPhamSach.add(lblNhaXuatBan);

		JLabel lblNmSnXut = new JLabel("Năm sản xuất:");
		lblNmSnXut.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmSnXut.setBounds(10, 185, 89, 15);
		pnlSanPhamSach.add(lblNmSnXut);

		lblNamSanXuat = new JLabel();
		lblNamSanXuat.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNamSanXuat.setBounds(112, 185, 229, 15);
		pnlSanPhamSach.add(lblNamSanXuat);

		JLabel lblSLngTn = new JLabel("Số lượng tồn:");
		lblSLngTn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSLngTn.setBounds(10, 208, 89, 15);
		pnlSanPhamSach.add(lblSLngTn);

		lblSoLuongTonSach = new JLabel();
		lblSoLuongTonSach.setForeground(new Color(255, 128, 0));
		lblSoLuongTonSach.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuongTonSach.setBounds(112, 208, 229, 15);
		pnlSanPhamSach.add(lblSoLuongTonSach);

		pnlSanPhamVPP = new JPanel();
		pnlSanPhamVPP.setLayout(null);
		pnlSanPhamVPP.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlSanPhamVPP.setBounds(179, 52, 351, 233);

		JLabel lblMSnPhm_1 = new JLabel("Mã sản phẩm:");
		lblMSnPhm_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMSnPhm_1.setBounds(10, 10, 89, 15);
		pnlSanPhamVPP.add(lblMSnPhm_1);

		lblMaVPP = new JLabel("SP02");
		lblMaVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaVPP.setBounds(112, 10, 229, 15);
		pnlSanPhamVPP.add(lblMaVPP);

		JLabel lblTnSnPhm_1 = new JLabel("Tên sản phẩm:");
		lblTnSnPhm_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTnSnPhm_1.setBounds(10, 35, 89, 15);
		pnlSanPhamVPP.add(lblTnSnPhm_1);

		lblTenVPP = new JLabel();
		lblTenVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenVPP.setBounds(112, 35, 229, 15);
		pnlSanPhamVPP.add(lblTenVPP);

		JLabel lblThu_1 = new JLabel("Thuế:");
		lblThu_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThu_1.setBounds(10, 60, 89, 15);
		pnlSanPhamVPP.add(lblThu_1);

		lblThueVPP = new JLabel();
		lblThueVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThueVPP.setBounds(112, 60, 229, 15);
		pnlSanPhamVPP.add(lblThueVPP);

		JLabel lblGiBn_1 = new JLabel("Giá bán:");
		lblGiBn_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiBn_1.setBounds(10, 85, 89, 15);
		pnlSanPhamVPP.add(lblGiBn_1);

		lblGiaVPP = new JLabel();
		lblGiaVPP.setForeground(Color.RED);
		lblGiaVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblGiaVPP.setBounds(112, 85, 229, 15);
		pnlSanPhamVPP.add(lblGiaVPP);

		JLabel lblThLoi_1 = new JLabel("Thể loại:");
		lblThLoi_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThLoi_1.setBounds(10, 135, 89, 15);
		pnlSanPhamVPP.add(lblThLoi_1);

		lblTheLoaiVPP = new JLabel();
		lblTheLoaiVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTheLoaiVPP.setBounds(112, 135, 229, 15);
		pnlSanPhamVPP.add(lblTheLoaiVPP);

		JLabel lblTcGi_1 = new JLabel("Danh mục:");
		lblTcGi_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTcGi_1.setBounds(10, 110, 89, 15);
		pnlSanPhamVPP.add(lblTcGi_1);

		lblDanhMuc = new JLabel();
		lblDanhMuc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDanhMuc.setBounds(112, 110, 229, 15);
		pnlSanPhamVPP.add(lblDanhMuc);

		JLabel lblNhXutBn_1 = new JLabel("Chất liệu:");
		lblNhXutBn_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNhXutBn_1.setBounds(10, 160, 89, 15);
		pnlSanPhamVPP.add(lblNhXutBn_1);

		lblChatLieu = new JLabel();
		lblChatLieu.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblChatLieu.setBounds(112, 160, 229, 15);
		pnlSanPhamVPP.add(lblChatLieu);

		JLabel lblNmSnXut_1_1 = new JLabel("Số lượng tồn:");
		lblNmSnXut_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNmSnXut_1_1.setBounds(10, 185, 89, 15);
		pnlSanPhamVPP.add(lblNmSnXut_1_1);

		lblSoLuongTonVPP = new JLabel();
		lblSoLuongTonVPP.setForeground(new Color(255, 128, 64));
		lblSoLuongTonVPP.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSoLuongTonVPP.setBounds(112, 185, 229, 15);
		pnlSanPhamVPP.add(lblSoLuongTonVPP);

		//		Phần panel khách hàng
		JPanel pnlKhachHang = new JPanel();
		pnlKhachHang.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlKhachHang.setBackground(Color.WHITE);
		pnlKhachHang.setBounds(612, 10, 337, 200);
		pnlBanHang.add(pnlKhachHang);
		pnlKhachHang.setLayout(null);

		JLabel lblTitleKhachHang = new JLabel("Khách hàng");
		lblTitleKhachHang.setForeground(new Color(128, 128, 128));
		lblTitleKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTitleKhachHang.setBounds(10, 10, 111, 20);
		pnlKhachHang.add(lblTitleKhachHang);

		JLabel lblSdtKhchHng = new JLabel("SDT khách hàng:");
		lblSdtKhchHng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSdtKhchHng.setBounds(10, 40, 111, 20);
		pnlKhachHang.add(lblSdtKhchHng);

		txtTimKhachHang = new JTextField();
		txtTimKhachHang.setActionCommand("txtTimKhachHang");
		txtTimKhachHang.setBounds(10, 70, 228, 25);
		pnlKhachHang.add(txtTimKhachHang);
		txtTimKhachHang.setColumns(10);

		btnTimKhachHang = new MyButton("Tìm");
		btnTimKhachHang.setActionCommand("btnTimKhachHang");
		btnTimKhachHang.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnTimKhachHang.setBounds(248, 70, 79, 25);
		pnlKhachHang.add(btnTimKhachHang);

		JPanel pnlKhachHangDaChon = new JPanel();
		pnlKhachHangDaChon.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), null, null, null));
		pnlKhachHangDaChon.setBounds(10, 105, 317, 85);
		pnlKhachHang.add(pnlKhachHangDaChon);
		pnlKhachHangDaChon.setLayout(null);

		JLabel lblMKhchHng = new JLabel("Mã khách hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMKhchHng.setBounds(10, 10, 92, 15);
		pnlKhachHangDaChon.add(lblMKhchHng);

		lblMaKhachHang = new JLabel();
		lblMaKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaKhachHang.setBounds(112, 10, 195, 15);
		pnlKhachHangDaChon.add(lblMaKhachHang);

		JLabel lblSdtKhchHng_1_2 = new JLabel("Tên khách hàng:");
		lblSdtKhchHng_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSdtKhchHng_1_2.setBounds(10, 35, 96, 15);
		pnlKhachHangDaChon.add(lblSdtKhchHng_1_2);

		lblTenKhachHang = new JLabel();
		lblTenKhachHang.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTenKhachHang.setBounds(116, 35, 191, 15);
		pnlKhachHangDaChon.add(lblTenKhachHang);

		JLabel lblSdtKhchHng_1_2_1 = new JLabel("Điểm tích lũy:");
		lblSdtKhchHng_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSdtKhchHng_1_2_1.setBounds(10, 60, 82, 15);
		pnlKhachHangDaChon.add(lblSdtKhchHng_1_2_1);

		lblDiemTichLuy = new JLabel();
		lblDiemTichLuy.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDiemTichLuy.setBounds(112, 60, 195, 15);
		pnlKhachHangDaChon.add(lblDiemTichLuy);

		this.capNhatThongTinKhachHangGiaoDien();

		JPanel pnlQuetMa = new JPanel();
		pnlQuetMa.setLayout(null);
		pnlQuetMa.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlQuetMa.setBackground(Color.WHITE);
		pnlQuetMa.setBounds(10, 220, 252, 296);
		pnlBanHang.add(pnlQuetMa);

		JLabel lblQutMSn = new JLabel("Quét mã sản phẩm");
		lblQutMSn.setForeground(new Color(128, 128, 128));
		lblQutMSn.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblQutMSn.setBounds(10, 10, 136, 20);
		pnlQuetMa.add(lblQutMSn);

		pnlKhungQuetMa = new JPanel();
		pnlKhungQuetMa.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnlKhungQuetMa.setBounds(10, 40, 232, 246);
		pnlQuetMa.add(pnlKhungQuetMa);

		this.capNhatDSHoaDonCho(dsHoaDonCho);
		this.capNhatHoaDonHienTai();
		if(dsHoaDonCho.size() != 0) {
			tableHoaDonCho.setRowSelectionInterval(0, 0);
			cbbDiemGiamGia.removeAllItems();
			for(int i = 0; i <= khachHang.getDiemTichLuy() / 5; i++) {
				cbbDiemGiamGia.addItem(5 * i);
			}
		}

		//Tạo webcam
		initWebCam();
		// Phần thêm sự kiện cho các phần tử:
		btnHuyHoaDon.addActionListener(acBanHang);
		btnLamMoiHoaDon.addActionListener(acBanHang);
		btnTaoMoiHoaDon.addActionListener(acBanHang);
		btnThanhToan.addActionListener(acBanHang);
		btnTimKhachHang.addActionListener(acBanHang);
		btnTimSanPham.addActionListener(acBanHang);
		btnThemSanPham.addActionListener(acBanHang);
		cbbDiemGiamGia.addActionListener(acBanHang);
		txtTienKhachDua.addKeyListener(acBanHang);
		tableHoaDonCho.addMouseListener(acBanHang);
		btnXoaTatCa.addActionListener(acBanHang);
		btnXoa.addActionListener(acBanHang);
		txtMaSanPham.addActionListener(acBanHang);
		txtTimKhachHang.addActionListener(acBanHang);
		btnCapNhat.addActionListener(acBanHang);

		// cập nhật this_gui
		thisGUI = this;
		// phân thêm sự kiện key
		txtTimHoaDonCho.addKeyListener(acBanHang);

		// sự kiện chuyển tab
		tabbedPane.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// Xử lý sự kiện khi tab thay đổi
				int selectedIndex = tabbedPane.getSelectedIndex();
				if(selectedIndex == 1 && guiHoaDonIsNull) {
					popUp.setVisible(true);
					guiHoaDon = new HoaDonGui(thisGUI);
					popUp.setVisible(false);
					tabbedPane.setComponentAt(selectedIndex, guiHoaDon);
					guiHoaDonIsNull = false;
				}
			}
		});
	}
	//Hàm tạo QR code
	private void initWebCam() {
		Dimension size = WebcamResolution.QVGA.getSize();
		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);

		panel = new WebcamPanel(webcam);
		panel.setSize(232, 246);
		panel.setPreferredSize(size);
		panel.setFPSDisplayed(true);
		pnlKhungQuetMa.add(panel);
		executor.execute(this);
	}
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r,"My Thread");
		t.setDaemon(true);

		return t;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		do {
			try {
				Thread.sleep(100);
			}catch (InterruptedException ex) {
				Logger.getLogger(BanHangGui.class.getName()).log(Level.SEVERE,null,ex);
			}
			Result result = null;
			BufferedImage image = null;

			if(webcam.isOpen()) {
				if((image = webcam.getImage())==null) {
					continue;
				}
			}
			if(image != null) {
				LuminanceSource source = new BufferedImageLuminanceSource(image);

				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// TODO Auto-generated catch block

				}
			}
			if(result != null && !result.getText().equals(resultScanQRBefore)) {
				txtMaSanPham.setText(result.getText());
				SanPham sanPhamQuet = new SanPhamBusImpl().timKiemSanPham(result.getText());
				sanPhamTim = sanPhamQuet;
				this.capNhatPanelTimKiemSanPham();
				resultScanQRBefore = result.getText();
			}
		}while(view.indexFrame.equals("Bán hàng"));
		webcam.close();
	}

	public void capNhatPanelTimKiemSanPham() {
		iconLblBgr = new ImageIcon(sanPhamTim.getHinhAnh());
		//		phóng to hình
		Image scaledImage = ((ImageIcon) iconLblBgr).getImage().getScaledInstance(lblAnhSanPham.getWidth(), lblAnhSanPham.getHeight(), Image.SCALE_SMOOTH);
		//		gán lại hình
		iconLblBgr = new ImageIcon(scaledImage);
		lblAnhSanPham.setIcon(iconLblBgr);
		pnlSanPham.add(lblAnhSanPham);
		pnlSanPham.remove(pnlSanPhamSach);
		pnlSanPham.remove(pnlSanPhamVPP);
		if(sanPhamTim.getMaSanPham().startsWith("SPS")) {
			lblMaSach.setText(sanPhamTim.getMaSanPham());
			lblTenSach.setText(sanPhamTim.getTenSanPham());
			lblThueSach.setText(sanPhamTim.getThue() + "");
			lblGiaBanSach.setText(Tool.dinhDangTien(sanPhamTim.getGiaBan()));
			lblTheLoaiSach.setText(sanPhamTim.getTheLoai());
			lblTacGia.setText(((Sach) sanPhamTim).getTacGia());
			lblNhaXuatBan.setText(((Sach) sanPhamTim).getNhaXuatBan());
			lblNamSanXuat.setText(((Sach) sanPhamTim).getNamXuatBan() + "");
			lblSoLuongTonSach.setText(sanPhamTim.getSoLuongTon() + "");
			pnlSanPham.add(pnlSanPhamSach);
		} else {
			lblMaVPP.setText(sanPhamTim.getMaSanPham());
			lblTenVPP.setText(sanPhamTim.getTenSanPham());
			lblThueVPP.setText(sanPhamTim.getThue() + "");
			lblGiaVPP.setText(Tool.dinhDangTien(sanPhamTim.getGiaBan()));
			lblTheLoaiVPP.setText(sanPhamTim.getTheLoai());
			lblSoLuongTonVPP.setText(sanPhamTim.getSoLuongTon() + "");
			lblDanhMuc.setText(((VanPhongPham) sanPhamTim).getDanhMuc().getTenDanhMuc());
			lblChatLieu.setText(((VanPhongPham) sanPhamTim).getChatLieu() + "");
			pnlSanPham.add(pnlSanPhamVPP);
		}
		pnlSanPham.repaint();
	}

	public void capNhatDSHoaDonCho(List<HoaDon> dsHDCho) {
		modelHoaDonCho.setRowCount(0);
		for (HoaDon hoaDon : dsHDCho) {
			modelHoaDonCho.addRow(new Object[] {
					hoaDon.getMaHoaDon(), hoaDon.getNgayLap(), hoaDon.getNhanVien().getTenNhanVien(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getKhachHang().getSdt()
			});
		}
	}

	public void capNhatHoaDonHienTai() {
		if(hoaDonHienTai != null) {
			khachHang = hoaDonHienTai.getKhachHang();
			ctkmCuaHoaDon = hoaDonHienTai.getCtkm();
			this.capNhatGioHang();
			this.capNhatThongTinThanhToan();
		}
		this.capNhatThongTinKhachHangGiaoDien();
	}

	public void capNhatThongTinKhachHangGiaoDien() {
		txtTimKhachHang.setText(khachHang.getSdt());
		lblMaKhachHang.setText(khachHang.getMaKhachHang());
		lblTenKhachHang.setText(khachHang.getTenKhachHang());
		lblDiemTichLuy.setText(khachHang.getDiemTichLuy() + "");

	}

	public void capNhatGioHang() {
		modelGioHang.setRowCount(0);
		for (ChiTietHoaDon cthd : hoaDonHienTai.getDsChiTietHoaDon()) {
			float giamGia = busHoaDon.hamLayGiamGiaCuaChiTietHoaDon(ctkmCuaHoaDon, cthd.getSanPham());
			float tongTien = cthd.getGiaBan() * cthd.getSoLuongMua();
			modelGioHang.addRow(new Object[] {
					cthd.getSanPham().getMaSanPham(), cthd.getSanPham().getTenSanPham(), Tool.dinhDangTien(cthd.getGiaBan()), cthd.getSanPham().getThue() + "%", cthd.getSoLuongMua(), giamGia + "%", Tool.dinhDangTien(tongTien)
			});
		}
	}

	public void capNhatThongTinThanhToan() {
		lblMaHoaDon.setText(hoaDonHienTai.getMaHoaDon());
		lblTongTien.setText(Tool.dinhDangTien(hoaDonHienTai.tinhTongTien()));
		lblTienGiamCTKM.setText(Tool.dinhDangTien(hoaDonHienTai.tinhGiamGia()));
		lblTenChuongTrinhKhuyenMai.setText(hoaDonHienTai.getCtkm().getTenCTKM());
		lblTienGiamDGG.setText(Tool.dinhDangTien(hoaDonHienTai.getDiemGiamGia() * 10));
		lblTongThue.setText(Tool.dinhDangTien(hoaDonHienTai.tinhThue()));
		lblThanhToan.setText(Tool.dinhDangTien(hoaDonHienTai.getThanhTien()));
		xuLyHienThiTienThua();
	}

	public void xuLyTaoHoaDon() {
		if(hoaDonHienTai != null) {
			if(hoaDonHienTai.getKhachHang().getMaKhachHang().equals("KH0")) {
				JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn chờ mới khi hóa đơn hiện tại là khách hàng lẻ");
				return;
			}
		}
		for (HoaDon hoaDon : dsHoaDonCho) {
			if(hoaDon.getKhachHang().getSdt().trim().equals("0")) {
				JOptionPane.showMessageDialog(this, "Danh sách đang tồn tài một hóa đơn chờ của khách hàng lẻ không thể tạo thêm cho khách hàng lẻ");
				return;
			}
		}
		ctkmCuaHoaDon = busCTKM.timChuongTrinhKhuyenMaiDangApDung(true);
		String maHoaDon = busHoaDon.taoMaHoaDon();
		khachHang = busKhachHang.timKhachHangTheoMa("KH0");
		hoaDonHienTai = new HoaDon(maHoaDon, LocalDate.now(), "", "", 0, 0, nhanVienBanHang, khachHang, ctkmCuaHoaDon, new ArrayList<ChiTietHoaDon>(), 0);
		this.capNhatHoaDonHienTai();
		dsHoaDonCho.add(hoaDonHienTai);
		this.capNhatDSHoaDonCho(dsHoaDonCho);
		cbbDiemGiamGia.removeAllItems();
		cbbHinhThucThanhToan.setSelectedIndex(0);
		txtTienKhachDua.setText("");
		txtTimKhachHang.setText("");
		txtGhiChu.setText("");
		txtTimHoaDonCho.setText("");
		xuLyHienThiTienThua();
		int row = tableHoaDonCho.getModel().getRowCount();
		tableHoaDonCho.setRowSelectionInterval(row - 1, row -1);
	}

	public void xuLyTimKhachHang() {
		if(hoaDonHienTai == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		} else {
			if(txtTimKhachHang.getText().trim().equals("") || txtTimKhachHang.getText().trim().equals("0")) {
				for (HoaDon hoaDon : dsHoaDonCho) {
					if(hoaDon.getKhachHang().getSdt().equals("0")) {
						JOptionPane.showMessageDialog(this, "Danh sách đang tồn tài một hóa đơn chờ của khách hàng lẻ không thể tạo thêm cho khách hàng lẻ");
						return;
					}
				}
				khachHang = busKhachHang.timKhachHangTheoSDT("0");
			}else {
				KhachHang khachHangTim = busKhachHang.timKhachHangTheoSDT(txtTimKhachHang.getText());
				if(khachHangTim == null) {
					JOptionPane.showMessageDialog(this, "Số điện thoại khách hàng không tồn tại");
					return;
				} else {
					for (HoaDon hoaDon : dsHoaDonCho) {
						if(hoaDon.getKhachHang().getMaKhachHang().equals(khachHangTim.getMaKhachHang())) {
							JOptionPane.showMessageDialog(this, "Khách hàng có hóa đơn chờ chưa thanh toán không thể tạo thêm hóa đơn");
							return;
						}
					}
					khachHang = khachHangTim;
				}
			}
			if(hoaDonHienTai != null) {
				hoaDonHienTai.setKhachHang(khachHang);
			}
			this.capNhatThongTinKhachHangGiaoDien();
			int rowTableHDCSelected = tableHoaDonCho.getSelectedRow();
			if(rowTableHDCSelected != -1) {
				modelHoaDonCho.setValueAt(khachHang.getTenKhachHang(), rowTableHDCSelected, 3);
				modelHoaDonCho.setValueAt(khachHang.getSdt(), rowTableHDCSelected, 4);
				cbbDiemGiamGia.removeAllItems();
				for(int i = 0; i <= khachHang.getDiemTichLuy() / 5; i++) {
					cbbDiemGiamGia.addItem(5 * i);
				}
			}
			String sdtTim = txtTimHoaDonCho.getText();
			if(!sdtTim.equals("")) {
				xuLySuKienNhapTimHoaDonCho();
			}
		}
	}

	public void xyLyTimSanPham() {
		SanPham sanPhamTim = busSanPham.timKiemSanPham(txtMaSanPham.getText());
		if(sanPhamTim == null) JOptionPane.showMessageDialog(this, "Mã sản phẩm không tồn tại");
		else {
			this.sanPhamTim = sanPhamTim;
			this.capNhatPanelTimKiemSanPham();
		}
	}

	public void xuLyThemSanPham() {
		if(hoaDonHienTai == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		} else {
			if(sanPhamTim == null) JOptionPane.showMessageDialog(this, "Vui tìm sản phẩm cần thêm vào giỏ hàng");
			else {
				for (ChiTietHoaDon cthd : hoaDonHienTai.getDsChiTietHoaDon()) {
					if(cthd.getSanPham().getMaSanPham().equals(sanPhamTim.getMaSanPham())) {
						JOptionPane.showMessageDialog(this, "Sản phẩm đã tồn tại trong giỏ hàng");
						return;
					}
				}
				int soLuongMua = xuLySoLuongMua(sanPhamTim, "Nhập vào số lượng sản phẩm cần mua", sanPhamTim.getSoLuongTon());
				if(soLuongMua != -1) {
					hoaDonHienTai.getDsChiTietHoaDon().add(new ChiTietHoaDon(soLuongMua, sanPhamTim.getGiaBan(), sanPhamTim, hoaDonHienTai));
					capNhatGioHang();
					capNhatThongTinThanhToan();
				}
			}
		}
	}

	public int xuLySoLuongMua(SanPham s, String mess, int soLuong) {
		int i = -1;
		try {
			String soluongmua = JOptionPane.showInputDialog(mess);
			if(soluongmua != null) {
				i = Integer.parseInt(soluongmua);
				if(i < 1) {
					JOptionPane.showMessageDialog(this, "Số lượng mua phải là số nguyên dương");
					i = -1;
				}
				if(i > soLuong) {
					JOptionPane.showMessageDialog(this, "Số lượng sản phẩm tồn kho không đủ");
					i = -1;
				}
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số lượng mua phải là số nguyên dương");
		}
		return i;
	}

	public void xuLyChonDiemGiamGia() {
		if(cbbDiemGiamGia.getSelectedItem() != null) {
			int diemGiamGia = Integer.parseInt(cbbDiemGiamGia.getSelectedItem() + "");
			hoaDonHienTai.setDiemGiamGia(diemGiamGia);
			this.capNhatThongTinThanhToan();
		}
	}

	public void xuLySuKienNhapTien() {
		if(hoaDonHienTai == null) {
			txtTienKhachDua.setText("");
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		} else {
			xuLyHienThiTienThua();
		}
	}

	public void xuLyHienThiTienThua() {
		String duLieu = txtTienKhachDua.getText().equals("") ? "0" : txtTienKhachDua.getText();
		if(!duLieu.equals("") ) {
			try {
				float tienKhachDua = Float.parseFloat(duLieu);
				hoaDonHienTai.setTienKhachDua(tienKhachDua);
				float tienTraKhach = hoaDonHienTai.tinhTienThua();
				lblTienThua.setText(Tool.dinhDangTien(tienTraKhach));
				if(tienTraKhach < 0) {
					lblTienThua.setForeground(Color.red);
				}else {
					lblTienThua.setForeground(Color.BLUE);
				}
			} catch (NumberFormatException e) {
				lblTienThua.setText("Không hợp lệ");
			}
		}
	}

	public void xuLyLamMoi() {
		khachHang = busKhachHang.timKhachHangTheoMa("KH0");
		capNhatThongTinKhachHangGiaoDien();
		if(hoaDonHienTai != null) {
			hoaDonHienTai.setKhachHang(khachHang);
			hoaDonHienTai.getDsChiTietHoaDon().clear();
			hoaDonHienTai.setDiemGiamGia(0);
			hoaDonHienTai.setTienKhachDua(0);
			capNhatGioHang();
			capNhatThongTinThanhToan();
		}
		txtTimKhachHang.setText("");
		cbbDiemGiamGia.removeAllItems();
		cbbHinhThucThanhToan.setSelectedIndex(0);
		lblThanhToan.setText("0");
		txtTienKhachDua.setText("");
		lblTienThua.setText("");
		txtGhiChu.setText("");
	}

	public void xuLyThanhToan() {
		if(hoaDonHienTai == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		} else {
			if(hoaDonHienTai.getDsChiTietHoaDon().size() == 0) {
				JOptionPane.showMessageDialog(this, "Không có sản phẩm nào trong giỏ hàng");
			} else {
				float tienTraKhach = hoaDonHienTai.tinhTienThua();
				if(tienTraKhach < 0) {
					JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ để thanh toán");
					txtTienKhachDua.requestFocus();
				}else {
					String hinhThucThanhToan = cbbHinhThucThanhToan.getSelectedItem() + "";
					String ghiChu = txtGhiChu.getText();
					hoaDonHienTai.setHinhThucThanhToan(hinhThucThanhToan);
					hoaDonHienTai.setGhiChu(ghiChu);
					if(busHoaDon.themHoaDon(hoaDonHienTai)) {
						Tool.inHoaDon(hoaDonHienTai, ctkmCuaHoaDon);
						if(!hoaDonHienTai.getKhachHang().getMaKhachHang().equals("KH0")) {
							int diemCong = (int) (hoaDonHienTai.getThanhTien() / 100000);
							khachHang.setDiemTichLuy(khachHang.getDiemTichLuy() - hoaDonHienTai.getDiemGiamGia() + diemCong);
							khachHang.setTongTienMua(khachHang.getTongTienMua() + hoaDonHienTai.getThanhTien());
							busKhachHang.capNhatDiemTichLuyKhachHang(khachHang);
						}
						for (ChiTietHoaDon cthd : hoaDonHienTai.getDsChiTietHoaDon()) {
							SanPham spCapNhat = cthd.getSanPham();
							spCapNhat.setSoLuongTon(spCapNhat.getSoLuongTon() - cthd.getSoLuongMua());
							busSanPham.capNhatSoLuongTonSanPham(spCapNhat);
						}
						if(guiHoaDon != null) guiHoaDon.xuLyKhiThemHoaDon(hoaDonHienTai);
						int luaChon = JOptionPane.showConfirmDialog(this, "Thanh toán thành công và xuất hóa đơn. Bạn có muốn chuyển hóa đơn thành đơn giao hàng không?", ghiChu, JOptionPane.YES_NO_OPTION);
						if(luaChon == 0) {
							this.chuyenHoaDonQuaGiaoHang(hoaDonHienTai);
						}
						dsHoaDonCho.remove(hoaDonHienTai);
						xuLyLamMoi();
						txtTimHoaDonCho.setText("");
						lblMaHoaDon.setText("Vui lòng tạo!");
						lblTienThua.setText("0");
						hoaDonHienTai = null;
						this.capNhatDSHoaDonCho(dsHoaDonCho);
					} else {
						JOptionPane.showMessageDialog(this, "Thanh toán không thành công vì lý do hệ thống");
					}
				}
			}
		}
	}

	public void xuLyClickHoaDonCho() {
		int row = tableHoaDonCho.getSelectedRow();
		String maHDClick = tableHoaDonCho.getValueAt(row, 0) + "";
		for (HoaDon hoaDon : dsHoaDonCho) {
			if(hoaDon.getMaHoaDon().equals(maHDClick)) hoaDonHienTai = hoaDon;
		}
		capNhatHoaDonHienTai();
		cbbDiemGiamGia.removeAllItems();
		for(int i = 0; i <= khachHang.getDiemTichLuy() / 5; i++) {
			cbbDiemGiamGia.addItem(5 * i);
		}
	}

	public void xyLyXoaTatCaSanPhamTrongGioHang() {
		if(hoaDonHienTai == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		} else {
			hoaDonHienTai.getDsChiTietHoaDon().clear();
			capNhatGioHang();
			capNhatThongTinThanhToan();
		}
	}

	public void xyLyXoaSanPhamTrongGioHang() {
		int row = tableGioHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng cần xóa");
		} else {
			hoaDonHienTai.getDsChiTietHoaDon().remove(row);
			capNhatGioHang();
			capNhatThongTinThanhToan();
		}
	}

	public void xuLyCapNhatSoLuongGioHang() {
		int row = tableGioHang.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng cần cập nhật");
		} else {
			String maSPTim = tableGioHang.getValueAt(row, 0) + "";
			int soLuongTon = busSanPham.timKiemSanPham(maSPTim).getSoLuongTon();
			int soLuongMua = xuLySoLuongMua(sanPhamTim, "Nhập vào số lượng sản phẩm cần cập nhật", soLuongTon);
			if(soLuongMua != -1) {
				hoaDonHienTai.getDsChiTietHoaDon().get(row).setSoLuongMua(soLuongMua);
				capNhatGioHang();
				capNhatThongTinThanhToan();
			}
		}
	}

	public void xuLyHuyHoaDon() {
		if(hoaDonHienTai != null) {
			int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn", "Xác nhận hủy hóa đơn", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				dsHoaDonCho.remove(hoaDonHienTai);
				xuLyLamMoi();
				lblMaHoaDon.setText("Vui lòng tạo!");
				lblTienThua.setText("0");
				hoaDonHienTai = null;
				capNhatDSHoaDonCho(dsHoaDonCho);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn");
		}
	}

	public void xuLySuKienNhapTimHoaDonCho() {
		String sdtTim = txtTimHoaDonCho.getText();
		ArrayList<HoaDon> temp = new ArrayList<>();
		for (HoaDon hoaDon : dsHoaDonCho) {
			if(hoaDon.getKhachHang().getSdt().indexOf(sdtTim) != -1) {
				temp.add(hoaDon);
			}
		}
		capNhatDSHoaDonCho(temp);
	}
	
	public void chuyenHoaDonQuaGiaoHang(HoaDon hoaDonChuyen) {
		view.chuyenHoaDonQuaGiaoHang(hoaDonChuyen);
	}
}
