package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bus.NhanVienBus;
import bus.impl.NhanVienBusImpl;

import customUI.CustomImage;
import customUI.MyButton;
import entity.NhanVien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPasswordField;

public class ThongTinNhanVienGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAvtNhanVien;
    private NhanVienBus busNV = new NhanVienBusImpl();
    private JPasswordField passwordField;
    private MyButton btnDangXuat, btnDoiMatKhau;
    private NhanVien nv;
    private TrangChu viewTC;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			ConnectDB.getInstance().connect();
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUIThongTinNhanVien frame = new GUIThongTinNhanVien();
//					frame.setVisible(true);
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
//		
//	}

	/**
	 * Create the frame.
	 */
	public ThongTinNhanVienGui(NhanVien nvHienTai, TrangChu view) {
		this.setTitle("Thông tin nhân viên");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.nv = nvHienTai;
		this.viewTC = view;
		setBounds(550, 200, 528, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 518, 442);
		contentPane.add(panel);
		panel.setLayout(null);
		
		int widthLblAvtNhanVien = 130;
		lblAvtNhanVien = new JLabel(new CustomImage().taoHinhTronAvt(nv.getHinhAnh(), widthLblAvtNhanVien));
		lblAvtNhanVien.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAvtNhanVien.setBounds(190, 15, widthLblAvtNhanVien, widthLblAvtNhanVien);
		panel.add(lblAvtNhanVien);
		
		JLabel lblNewLabel = new JLabel(nv.getTenNhanVien());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(169, 153, 159, 20);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã nhân viên: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(53, 192, 126, 20);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Số điện thoại: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(53, 228, 126, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Giới tính: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(54, 262, 81, 20);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("CCCD: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(53, 296, 68, 20);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Chức vụ: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(53, 330, 82, 20);
		panel.add(lblNewLabel_5);
		
		btnDangXuat = new MyButton("Đăng xuất");
		btnDangXuat.setForeground(new Color(0, 0, 0));
		btnDangXuat.setBackground(new Color(255, 57, 57));
		btnDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDangXuat.setBounds(82, 391, 137, 25);
		panel.add(btnDangXuat);
		
		btnDoiMatKhau = new MyButton("Đổi mật khẩu");
		btnDoiMatKhau.setForeground(new Color(0, 0, 0));
		btnDoiMatKhau.setBackground(new Color(236, 236, 0));
		btnDoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDoiMatKhau.setBounds(294, 391, 137, 25);
		panel.add(btnDoiMatKhau);
		
		JLabel lblMaNhanVien = new JLabel(nv.getMaNhanVien());
		lblMaNhanVien.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMaNhanVien.setBounds(184, 192, 183, 20);
		panel.add(lblMaNhanVien);
		
		JLabel lblSDT = new JLabel(nv.getSdt());
		lblSDT.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSDT.setBounds(185, 228, 170, 20);
		panel.add(lblSDT);
		
		JLabel lblGioiTinh = new JLabel(nv.isGioiTinh() ? "Nam" : "Nữ");
		lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGioiTinh.setHorizontalAlignment(SwingConstants.LEFT);
		lblGioiTinh.setBounds(185, 262, 97, 20);
		panel.add(lblGioiTinh);
		
		JLabel lblCCCD = new JLabel(nv.getcCCD());
		lblCCCD.setHorizontalAlignment(SwingConstants.LEFT);
		lblCCCD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCCCD.setBounds(185, 296, 170, 20);
		panel.add(lblCCCD);
		
		JLabel lblChucVu = new JLabel(nv.getChucVu());
		lblChucVu.setHorizontalAlignment(SwingConstants.LEFT);
		lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChucVu.setBounds(185, 330, 170, 20);
		panel.add(lblChucVu);
		
		btnDangXuat.addActionListener(this);
		btnDoiMatKhau.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDangXuat)) {
			if(JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				new DangNhapGui().setVisible(true);
				this.setVisible(false);
				viewTC.setVisible(false);
			}
		}
		if(o.equals(btnDoiMatKhau)) {
			new DoiMatKhauGui(nv, this).setVisible(true);
			this.setVisible(false);
		}
	}
}
