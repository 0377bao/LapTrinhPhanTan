package controller;

import ui.KhachHangGui;

import java.awt.event.*;

public class ControllerKhachHang implements MouseListener, ActionListener, KeyListener, ItemListener {
	private KhachHangGui view;

	public ControllerKhachHang(KhachHangGui view) {
		super();
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		this.view.xuLyKhiChonBangKhachHang();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
			case "btnTaoMa": {
				this.view.xuLyTaoMaKhachHang();
				break;
			}
			case "btnXoaTrang": {
				this.view.xuLyXoaTrang();
				break;
			}
			case "btnThemKhachHang":
				this.view.xuLyThemKhachHang();
				break;
			case "btnCapNhat":
				this.view.xuLyCapNhatKhachHang();
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + src);
			}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.view.xuLyTimKhachHang();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		this.view.xuLySapXepTheoTongTien();
	}

}
