package controller;

import ui.NhaCungCapGui;

import javax.swing.*;
import java.awt.event.*;

public class NhaCungCapController implements ActionListener, MouseListener, FocusListener, KeyListener {
	private NhaCungCapGui guiNCC;

	public NhaCungCapController(NhaCungCapGui
										guiNCC) {
		super();
		this.guiNCC = guiNCC;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if (btn.equals("btnTaoMa")) {
			guiNCC.taoMa();
		} else if (btn.equals("btnXoaTrang")) {
			guiNCC.xoaTrang();
		} else if (btn.equals("btnThemNCC")) {
			guiNCC.themNCC();
		} else if (btn.equals("btnCapNhat")) {
			guiNCC.CapNhatNCC();
		} else if (btn.equals("btnTaiLai")) {
			guiNCC.taiLai();
		} else if (btn.equals("cboDiaChi")) {
			guiNCC.xuLyTimKiem();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		guiNCC.chonThongTin();
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
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimTheoMa_Sdt")) {
			guiNCC.focusGainedSdt();
		} else if (txt.getName().equals("txtTimTheoTen")) {
			guiNCC.focusGainedTen();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimTheoMa_Sdt")) {
			guiNCC.focusLostSdt();
		} else if (txt.getName().equals("txtTimTheoTen")) {
			guiNCC.focusLostTen();
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
		guiNCC.xuLyTimKiem();
	}
}
