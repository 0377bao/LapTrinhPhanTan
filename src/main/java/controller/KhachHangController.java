package controller;

import ui.KhachHangGui;

import java.awt.event.*;
import java.rmi.RemoteException;

public class KhachHangController implements MouseListener, ActionListener, KeyListener, ItemListener {
	private KhachHangGui view;

	public KhachHangController(KhachHangGui view) {
		super();
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
        try {
            this.view.xuLyKhiChonBangKhachHang();
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
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
			case "btnTaoMa": {
                try {
                    this.view.xuLyTaoMaKhachHang();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                break;
			}
			case "btnXoaTrang": {
                try {
                    this.view.xuLyXoaTrang();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                break;
			}
			case "btnThemKhachHang":
                try {
                    this.view.xuLyThemKhachHang();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                break;
			case "btnCapNhat":
                try {
                    this.view.xuLyCapNhatKhachHang();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
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
        try {
            this.view.xuLyTimKhachHang();
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
        try {
            this.view.xuLySapXepTheoTongTien();
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

}
