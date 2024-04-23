package controller;

import ui.NhanVienGui;

import javax.swing.*;
import java.awt.event.*;

public class NhanVienController implements ActionListener, MouseListener, FocusListener, KeyListener {
	private NhanVienGui guiNV;

	public NhanVienController(NhanVienGui guiNV) {
		super();
		this.guiNV = guiNV;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if (btn.equals("btnHinhAnh")) {
			guiNV.chonAnh();
		} else if (btn.equals("btnXoaTrang")) {
			guiNV.xoaTrang();
		} else if (btn.equals("btnTaoMa")) {
			guiNV.taoMa();
		} else if (btn.equals("btnThemNV")) {
			guiNV.themNhanVien();
		} else if (btn.equals("btnCapNhatNV")) {
			guiNV.capNhatNV();
		} else if (btn.equals("btnLocGioiTinh")) {
			guiNV.locNVTheoGioiTinh();
		} else if (btn.equals("btnLocChucVu")) {
			guiNV.locNVTheoChucVu();
		} else if (btn.equals("btnTaiLai")) {
			guiNV.taiLai();
		} else if (btn.equals("btnTimTheoMa")) {
			guiNV.timNVTheoMa();
		} else if (btn.equals("btnLocTrangThai")) {
			guiNV.locNVTheoTrangThai();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		guiNV.chonThongTin();
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
	public void focusGained(FocusEvent e) {
		guiNV.focusGained();
	}

	@Override
	public void focusLost(FocusEvent e) {
		guiNV.focusLost();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimNVTheoMa")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				guiNV.timNVTheoMa();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimTheoSdt_Ten")) {
			guiNV.timNVTheoSdt_Ten();
		}
	}

}
