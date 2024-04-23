package controller;

import ui.NhaCungCapGui;

import javax.swing.*;
import java.awt.event.*;
import java.rmi.RemoteException;

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
            try {
                guiNCC.taoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnXoaTrang")) {
			guiNCC.xoaTrang();
		} else if (btn.equals("btnThemNCC")) {
            try {
                guiNCC.themNCC();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnCapNhat")) {
            try {
                guiNCC.CapNhatNCC();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTaiLai")) {
            try {
                guiNCC.taiLai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboDiaChi")) {
            try {
                guiNCC.xuLyTimKiem();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
        try {
            guiNCC.xuLyTimKiem();
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
