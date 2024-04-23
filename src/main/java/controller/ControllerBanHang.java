package controller;

import ui.BanHangGui;

import java.awt.event.*;

public class ControllerBanHang implements ActionListener, KeyListener, MouseListener {

	private BanHangGui view;

	public ControllerBanHang(BanHangGui view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
		case "btnTaoMoiHoaDon": {
			this.view.xuLyTaoHoaDon();
			break;
		}
		case "btnTimKhachHang": {
			this.view.xuLyTimKhachHang();
			break;
		}
		case "txtTimKhachHang": {
			this.view.xuLyTimKhachHang();
			break;
		}
		case "btnTimSanPham": {
			this.view.xyLyTimSanPham();
			break;
		}
		case "txtMaSanPham": {
			this.view.xyLyTimSanPham();
			break;
		}
		case "btnThemSanPham": {
			this.view.xuLyThemSanPham();
			break;
		}
		case "cbbDiemGiamGia": {
			this.view.xuLyChonDiemGiamGia();
			break;
		}
		case "btnThanhToan": {
			this.view.xuLyThanhToan();
			break;
		}
		case "btnLamMoiHoaDon": {
			this.view.xuLyLamMoi();
			break;
		}
		case "btnXoaTatCa": {
			this.view.xyLyXoaTatCaSanPhamTrongGioHang();
			break;
		}
		case "btnXoa": {
			this.view.xyLyXoaSanPhamTrongGioHang();
			break;
		}
		case "btnCapNhat": {
			this.view.xuLyCapNhatSoLuongGioHang();
			break;
		}
		case "btnHuyHoaDon": {
			this.view.xuLyHuyHoaDon();
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// nếu không phải là số hoặc phím xóa thì không nhận cho txt nhập tiền khách đưa
		String thaoTacTrenTextField = e.getComponent().getName();
		if(thaoTacTrenTextField.equals("txtTienKhachDua")) {
			char c = e.getKeyChar();
			if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
				e.consume();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		//xử lý khi nhập vào không phải là ký tự không định nghĩa như shilft hay alt...
		if(e.getKeyChar() != KeyEvent.CHAR_UNDEFINED) {
			// phân biệt nhập trên txt nào
			String thaoTacTrenTextField = e.getComponent().getName();
			if(thaoTacTrenTextField.equals("txtTienKhachDua")) {
				view.xuLySuKienNhapTien();
			}
			if(thaoTacTrenTextField.equals("keyTxtTimHoaDonCho")) {
				view.xuLySuKienNhapTimHoaDonCho();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String src = e.getComponent().getName();
		if(src.equals("tableHoaDonCho")) {
			this.view.xuLyClickHoaDonCho();
		}
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
