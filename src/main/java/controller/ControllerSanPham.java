package controller;

import customUI.MyTable;
import ui.SanPhamGui;

import javax.swing.*;
import java.awt.event.*;

public class ControllerSanPham implements ActionListener, MouseListener, KeyListener, FocusListener {
	private SanPhamGui guiSP;

	public ControllerSanPham(SanPhamGui guiSP) {
		super();
		this.guiSP = guiSP;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if (btn.equals("btnXoaTrangSach")) {
			guiSP.xoaTrangSach();
		} else if (btn.equals("btnTaoMaSach")) {
			guiSP.taoMaSach();
		} else if (btn.equals("btnHinhAnhSach")) {
			guiSP.chonAnhSach();
		} else if (btn.equals("btnThemSach")) {
			guiSP.themSach();
		} else if (btn.equals("btnCapNhatSach")) {
			guiSP.capNhatSach();
		} else if (btn.equals("btnTaiLaiSach")) {
			guiSP.taiLaiSach();
		} else if (btn.equals("cboLocTrangThaiSach")) {
			guiSP.KiemTraTrangThaiSach();
		} else if (btn.equals("cboLocNCC_Sach")) {
			guiSP.xuLyTimKiemSach();
		} else if (btn.equals("btnTimMaSach")) {
			guiSP.timSachTheoMa();
		} else if (btn.equals("chkSoLuongSach")) {
			guiSP.kiemTraSoLuongSach();
		} else if (btn.equals("btnTimMaNCCSach")) {
			guiSP.chonNCCSach();
		} else if (btn.equals("btnXoaTrangVPP")) {
			guiSP.xoaTrangVPP();
		} else if (btn.equals("btnTaoMaVPP")) {
			guiSP.taoMaVPP();
		} else if (btn.equals("btnHinhAnhVPP")) {
			guiSP.chonAnhVPP();
		} else if (btn.equals("btnThemVPP")) {
			guiSP.themVPP();
		} else if (btn.equals("btnCapNhatVPP")) {
			guiSP.capNhatVPP();
		} else if (btn.equals("btnTaiLaiVPP")) {
			guiSP.taiLaiVPP();
		} else if (btn.equals("cboLocTrangThai")) {
			guiSP.locVPPTheoTrangThai();
		} else if (btn.equals("cboLocNCC_VPP")) {
			guiSP.xuLyTimKiemVPP();
		} else if (btn.equals("cboLocDanhMuc")) {
			guiSP.xuLyTimKiemVPP();
		} else if (btn.equals("btnTimMaVPP")) {
			guiSP.timVPPTheoMa();
		} else if (btn.equals("chkSoLuongVPP")) {
			guiSP.kiemTraSoLuongVPP();
		} else if (btn.equals("btnThemDanhMuc")) {
			guiSP.themDanhMuc();
		} else if (btn.equals("btnTimMaNCCVPP")) {
			guiSP.chonNCCVPP();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimKiemSachTheoMa")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				guiSP.timSachTheoMa();
			}
		}
		if (txt.getName().equals("txtTimKiemVPPTheoMa")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				guiSP.timVPPTheoMa();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtLocNamXB") || txt.getName().equals("txtLocTheLoaiS")
				|| txt.getName().equals("txtLocTacGia") || txt.getName().equals("txtLocTenSach")) {
			guiSP.xuLyTimKiemSach();
		} else if (txt.getName().equals("txtLocTheLoai") || txt.getName().equals("txtLocTenVPP")) {
			guiSP.xuLyTimKiemVPP();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MyTable clickedTable = (MyTable) e.getSource();
		if (clickedTable.getName().equals("tableSach")) {
			guiSP.chonThongTinSach();
		} else {
			guiSP.chonThongTinVPP();
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

	@Override
	public void focusGained(FocusEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimKiemSachTheoMa")) {
			guiSP.focusGainedSach();
		} else if (txt.getName().equals("txtTimKiemVPPTheoMa")) {
			guiSP.focusGainedVPP();
		} else if (txt.getName().equals("txtMaNCCSach")) {
			guiSP.focusGainedNCCSach();
		} else if (txt.getName().equals("txtMaNCCVPP")) {
			guiSP.focusGainedNCCVPP();
		} else if (txt.getName().equals("txtTheLoaiSach")) {
			guiSP.focusGainedThueSach();
		}

	}

	@Override
	public void focusLost(FocusEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimKiemSach")) {
			guiSP.focusLostSach();
		} else if (txt.getName().equals("txtTimKiemVPP")) {
			guiSP.focusLostVPP();
		} else if (txt.getName().equals("txtMaNCCSach")) {
			guiSP.focusLostNCCSach();
		} else if (txt.getName().equals("txtMaNCCVPP")) {
			guiSP.focusLostNCCVPP();
		} else if (txt.getName().equals("txtTheLoaiSach")) {
			guiSP.focusLostThueSach();
		}
	}

}
