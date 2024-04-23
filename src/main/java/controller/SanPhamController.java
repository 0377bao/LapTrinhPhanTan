package controller;

import customUI.MyTable;
import ui.SanPhamGui;

import javax.swing.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SanPhamController implements ActionListener, MouseListener, KeyListener, FocusListener {
	private SanPhamGui guiSP;

	public SanPhamController(SanPhamGui guiSP) {
		super();
		this.guiSP = guiSP;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if (btn.equals("btnXoaTrangSach")) {
			guiSP.xoaTrangSach();
		} else if (btn.equals("btnTaoMaSach")) {
            try {
                guiSP.taoMaSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnHinhAnhSach")) {
			guiSP.chonAnhSach();
		} else if (btn.equals("btnThemSach")) {
            try {
                guiSP.themSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnCapNhatSach")) {
            try {
                guiSP.capNhatSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTaiLaiSach")) {
            try {
                guiSP.taiLaiSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboLocTrangThaiSach")) {
            try {
                guiSP.KiemTraTrangThaiSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboLocNCC_Sach")) {
            try {
                guiSP.xuLyTimKiemSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTimMaSach")) {
            try {
                guiSP.timSachTheoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("chkSoLuongSach")) {
            try {
                guiSP.kiemTraSoLuongSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTimMaNCCSach")) {
            try {
                guiSP.chonNCCSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnXoaTrangVPP")) {
			guiSP.xoaTrangVPP();
		} else if (btn.equals("btnTaoMaVPP")) {
            try {
                guiSP.taoMaVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnHinhAnhVPP")) {
			guiSP.chonAnhVPP();
		} else if (btn.equals("btnThemVPP")) {
            try {
                guiSP.themVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnCapNhatVPP")) {
            try {
                guiSP.capNhatVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTaiLaiVPP")) {
            try {
                guiSP.taiLaiVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboLocTrangThai")) {
            try {
                guiSP.locVPPTheoTrangThai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboLocNCC_VPP")) {
            try {
                guiSP.xuLyTimKiemVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboLocDanhMuc")) {
            try {
                guiSP.xuLyTimKiemVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTimMaVPP")) {
            try {
                guiSP.timVPPTheoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("chkSoLuongVPP")) {
            try {
                guiSP.kiemTraSoLuongVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnThemDanhMuc")) {
            try {
                guiSP.themDanhMuc();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnTimMaNCCVPP")) {
            try {
                guiSP.chonNCCVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
                try {
                    guiSP.timSachTheoMa();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
		}
		if (txt.getName().equals("txtTimKiemVPPTheoMa")) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    guiSP.timVPPTheoMa();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField txt = (JTextField) e.getSource();
		if (txt.getName().equals("txtLocNamXB") || txt.getName().equals("txtLocTheLoaiS")
				|| txt.getName().equals("txtLocTacGia") || txt.getName().equals("txtLocTenSach")) {
            try {
                guiSP.xuLyTimKiemSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (txt.getName().equals("txtLocTheLoai") || txt.getName().equals("txtLocTenVPP")) {
            try {
                guiSP.xuLyTimKiemVPP();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		MyTable clickedTable = (MyTable) e.getSource();
		if (clickedTable.getName().equals("tableSach")) {
            try {
                guiSP.chonThongTinSach();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            try {
                guiSP.chonThongTinVPP();
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
