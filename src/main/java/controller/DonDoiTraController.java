package controller;

import customUI.MyTable;
import ui.DoiTraHangGui;

import javax.swing.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DonDoiTraController implements FocusListener, ActionListener, MouseListener, KeyListener {
	private DoiTraHangGui guiDonDoiTra;

	public DonDoiTraController(DoiTraHangGui guiDonDoiTra) {
		super();
		this.guiDonDoiTra = guiDonDoiTra;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		guiDonDoiTra.xoaPlaceholder();

	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		guiDonDoiTra.themPlaceholder();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("btnTimKiemKhachHang")|| src.equals("txtTimKiemHoaDon")) {
            try {
                guiDonDoiTra.timKiemHoaDonCuaKhachHangTrong7Ngay();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("btnTaoDonDoiTra")) {
            try {
                guiDonDoiTra.taoDonDoiTra();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("btnThemSanPham")) {
            try {
                guiDonDoiTra.themSanPhamVaoDonDoiTra();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("btnXoaSanPham")) {
            try {
                guiDonDoiTra.xoaSanPhamRaDonDoiTra();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("btnHoanThanhDon")) {
            try {
                guiDonDoiTra.hoanThanhDonDoiTra();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("btnHuy")) {
			guiDonDoiTra.huyDDT();
		} else if (src.equals("btnXoaTrangQL")) {
			guiDonDoiTra.xoaThongTinDonDoiTraCoSan();
		} else if (src.equals("btnTaiLai")) {
            try {
                guiDonDoiTra.taiLaiDSDDT();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		MyTable clickedTable = (MyTable) e.getSource();
		if (clickedTable.getName().equals("tbDanhSachHoaDon")) {
            try {
                guiDonDoiTra.hienThongTinHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                guiDonDoiTra.hienDSSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                guiDonDoiTra.hienThongTinDonDoiTra();
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
		// TODO Auto-generated method stub
		JTextField txtKey = (JTextField) e.getSource();
		if (txtKey.getName().equals("txtMaDonDoiTraTimKiemQL") || txtKey.getName().equals("txtSDTTimKiemQL")
				|| txtKey.getName().equals("txtMaHDTimKiemQL")) {
            try {
                guiDonDoiTra.timKiemDonDoiTra();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}
}
