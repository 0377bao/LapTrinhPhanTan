package controller;

import ui.NhanVienGui;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

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
            try {
                guiNV.taoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnThemNV")) {
            try {
                guiNV.themNhanVien();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnCapNhatNV")) {
            try {
                guiNV.capNhatNV();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnLocGioiTinh")) {
            try {
                guiNV.locNVTheoGioiTinh();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnLocChucVu")) {
            try {
                guiNV.locNVTheoChucVu();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTaiLai")) {
            try {
                guiNV.taiLai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTimTheoMa")) {
            try {
                guiNV.timNVTheoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnLocTrangThai")) {
            try {
                guiNV.locNVTheoTrangThai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
        try {
            guiNV.chonThongTin();
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
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
                try {
                    guiNV.timNVTheoMa();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtTimTheoSdt_Ten")) {
            try {
                guiNV.timNVTheoSdt_Ten();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}

}
