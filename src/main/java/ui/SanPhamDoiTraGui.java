package ui;

import bus.HoaDonBus;
import bus.SanPhamBus;
import bus.impl.HoaDonBusImpl;
import bus.impl.SanPhamBusImpl;
import customUI.MyButton;
import customUI.MyTable;
import entity.ChuongTrinhKhuyenMai;
import tool.Tool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SanPhamDoiTraGui extends JFrame implements ActionListener {
	private JTextField textField;
	private JTextField textField_1;
	private String ma, ten, phuongThucDoiTra;
	private float gia;
	private int soLuongTrongHD, diemTrongHD;
	private DefaultTableModel model;
	private MyTable tb;
	private JTextField tongSoSP, tongTien, diemHT, txtTongGiamGia;
	private ChuongTrinhKhuyenMai ctkm;

	private HoaDonBus hoaDonBus = new HoaDonBusImpl();
	private SanPhamBus sanPhamBus = new SanPhamBusImpl();

	public SanPhamDoiTraGui(DefaultTableModel model, MyTable tb, String ma, String ten, float gia, int soLuongTrongHD,
							String phuongThucDoiTra, JTextField tongSoSP, JTextField tongTien, JTextField diemHT, int diemTrongHD,
							ChuongTrinhKhuyenMai ctkm, JTextField txtTongTienGiam) {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		this.setSize(600, 200);
		this.setTitle("Thông tin sản phẩm thêm vào");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		this.ma = ma;
		this.ten = ten;
		this.gia = gia;
		this.model = model;
		this.soLuongTrongHD = soLuongTrongHD;
		this.phuongThucDoiTra = phuongThucDoiTra;
		this.tongSoSP = tongSoSP;
		this.tongTien = tongTien;
		this.tb = tb;
		this.diemHT = diemHT;
		this.diemTrongHD = diemTrongHD;
		this.ctkm = ctkm;
		this.txtTongGiamGia = txtTongTienGiam;
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(59, 26, 460, 40);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Lý do");
		lblNewLabel.setBounds(30, 11, 36, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(126, 8, 295, 24);
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(59, 77, 460, 40);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setBounds(30, 11, 60, 20);
		lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_1.add(lblSLng);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 8, 295, 24);
		panel_1.add(textField_1);

		JButton btnThemSanPhamP = new MyButton("Thêm");
		btnThemSanPhamP.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemSanPhamP.setBounds(228, 131, 100, 25);
		getContentPane().add(btnThemSanPhamP);
		btnThemSanPhamP.addActionListener(this);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (validata()) {
			this.setVisible(false);
			if (timSanPhamTrongDonDoiTra() == -1) {
				model.addRow(new Object[] { ma, ten, Integer.parseInt(textField_1.getText().trim()),
						new Tool().dinhDangTien(gia), textField_1.getText().trim() + "-" + textField.getText().trim() });
				thayDoiTienVaSoLuong();
			} else {

				if ((Integer.parseInt(tb.getValueAt(timSanPhamTrongDonDoiTra(), 2).toString())
						+ Integer.parseInt(textField_1.getText().trim())) > soLuongTrongHD) {
					JOptionPane.showMessageDialog(this, "Số lượng đổi trả phải thấp hơn hoặc bằng số lượng đã mua");
				} else if ((Integer.parseInt(tb.getValueAt(timSanPhamTrongDonDoiTra(), 2).toString())
						+ Integer.parseInt(textField_1.getText().trim())) > new SanPhamBusImpl().timKiemSanPham(ma)
								.getSoLuongTon()) {
					JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ để thực hiện đổi trả");
				} else {
					thayDoiTienVaSoLuong();
					tb.setValueAt((Integer.parseInt(tb.getValueAt(timSanPhamTrongDonDoiTra(), 2).toString())
							+ Integer.parseInt(textField_1.getText().trim())), timSanPhamTrongDonDoiTra(), 2);
					if (!(tb.getValueAt(timSanPhamTrongDonDoiTra(), 4).toString().trim().toUpperCase()
							.equals(textField.getText().trim().toUpperCase()))) {
						tb.setValueAt(tb.getValueAt(timSanPhamTrongDonDoiTra(), 4) + ", " + textField_1.getText() + "-"
								+ textField.getText(), timSanPhamTrongDonDoiTra(), 4);
					}
				}
			}
		}

	}

	public void thayDoiTienVaSoLuong() {
		if (phuongThucDoiTra.equals("Đổi Hàng")) {
			tongSoSP.setText(Integer.parseInt(tongSoSP.getText()) + Integer.parseInt(textField_1.getText()) + "");
		} else {
			int soLuongSP = Integer.parseInt(textField_1.getText());
			float tongTienDDT = Float.parseFloat(tongTien.getText().replaceAll("[,VND]", "")) + ((gia - gia
					* (hoaDonBus.hamLayGiamGiaCuaChiTietHoaDon(ctkm, new SanPhamBusImpl().timKiemSanPham(ma)) / 100))
					* soLuongSP) + Integer.parseInt(diemHT.getText()) * 10000
					- (gia * (sanPhamBus.timKiemSanPham(ma).getThue() / 100) * soLuongSP);
			float tongTienGiam = Float.parseFloat(txtTongGiamGia.getText().replaceAll("[,VND]", ""))
					+ (gia * (hoaDonBus.hamLayGiamGiaCuaChiTietHoaDon(ctkm, new SanPhamBusImpl().timKiemSanPham(ma))
							/ 100) * soLuongSP)
					+ (soDiemHoanTra(tongTienDDT) * 10000 - Integer.parseInt(diemHT.getText()) * 10000)
					+ (gia * (sanPhamBus.timKiemSanPham(ma).getThue() / 100) * soLuongSP);
			tongTien.setText(new Tool().dinhDangTien((tongTienDDT - soDiemHoanTra(tongTienDDT) * 10000)) + "");

			diemHT.setText(soDiemHoanTra(tongTienDDT) + "");

			txtTongGiamGia.setText(new Tool().dinhDangTien(tongTienGiam));

		}
	}

	public boolean validata() {
		try {
			int soLuong = Integer.parseInt(textField_1.getText().trim());
			if (soLuong <= 0 || soLuong > soLuongTrongHD) {
				JOptionPane.showMessageDialog(this,
						"Số lượng phải lớn hơn 0 và nhỏ hơn hoặc bằng số lượng trong hóa đơn");
				return false;
			} else if (soLuong > new SanPhamBusImpl().timKiemSanPham(ma).getSoLuongTon()) {
				JOptionPane.showMessageDialog(this, "Số lượng tồn không đủ để thực hiện đổi trả");
				return false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Hãy nhập đúng thông tin số lượng");
			return false;
		}
		if (textField.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin");
			return false;
		}
		return true;
	}

	public int timSanPhamTrongDonDoiTra() {
		for (int i = 0; i < tb.getRowCount(); i++) {
			if (tb.getValueAt(i, 0) == ma) {
				return i;
			}
		}
		return -1;
	}

	// hàm tính điểm trả lại trong đơn đổi trả
	// Nếu số điểm nhiều hơn 50% số tiền thì lấy số điểm ứng với 50% số tiền trả
	// Nếu số điểm nhỏ hơn 50% số tiền thì hoàn lại tất cả điểm
	public int soDiemHoanTra(float tongTien) {
		int nuaTien = (int) tongTien / 2;
		if (nuaTien / 10000 > diemTrongHD) {
			return diemTrongHD;
		}
		return nuaTien / 10000;
	}
}
