package controller;

import ui.BanHangGui;

import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BanHangController implements ActionListener, KeyListener, MouseListener {

	private BanHangGui view;

	public BanHangController(BanHangGui view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
		case "btnTaoMoiHoaDon": {
            try {
                this.view.xuLyTaoHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnTimKhachHang": {
            try {
                this.view.xuLyTimKhachHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "txtTimKhachHang": {
            try {
                this.view.xuLyTimKhachHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnTimSanPham": {
            try {
                this.view.xyLyTimSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "txtMaSanPham": {
            try {
                this.view.xyLyTimSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnThemSanPham": {
            try {
                this.view.xuLyThemSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "cbbDiemGiamGia": {
            try {
                this.view.xuLyChonDiemGiamGia();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnThanhToan": {
            try {
                this.view.xuLyThanhToan();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnLamMoiHoaDon": {
            try {
                this.view.xuLyLamMoi();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnXoaTatCa": {
            try {
                this.view.xyLyXoaTatCaSanPhamTrongGioHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnXoa": {
            try {
                this.view.xyLyXoaSanPhamTrongGioHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnCapNhat": {
            try {
                this.view.xuLyCapNhatSoLuongGioHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnHuyHoaDon": {
            try {
                this.view.xuLyHuyHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                this.view.xuLyClickHoaDonCho();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
