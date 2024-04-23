package controller;

import bus.impl.ChuongTrinhKhuyenMaiBusImpl;
import ui.KhuyenMaiGui;

import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class KhuyenMaiController implements MouseListener, ActionListener, KeyListener {
    private KhuyenMaiGui view;

    public KhuyenMaiController(KhuyenMaiGui view) {
        super();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        if (btn.equals("btnTaoMa")) {
            try {
                view.taoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnThem")) {
            try {
                view.themMucKhuyenMai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnLuu")) {
            try {
                view.themCTKM();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnCapNhat")) {
            view.capNhatMucKhuyenMai();
        } else if (btn.equals("btnXoa")) {
            view.xoaMucKhuyenMai();
        } else if (btn.equals("btnLuuCapNhat")) {
            try {
                view.luuCapNhat();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("btnApDungCT")) {
            try {
                view.apDungCTKM();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboSanPham")) {
            try {
                view.chuyenDoiSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("cboTrangThai")) {
            try {
                view.xuLySuKienCboTrangThai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (btn.equals("checkBoxChinhSuaMKM")) {
            try {
                view.xuLySuKienCheckBoxChinhSuaMKM();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }else if(btn.equals("cboMucKM")){
            try {
                view.xuLySuKienCboMucKM(e.getSource());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        String btn = e.getComponent().getName();
        if (btn.equals("tbMucKhuyenMai")) {
            view.loadChiTietMucKhuyenMai();
            view.loadComboboxTheoMucKhuyenMai();
        } else if (btn.equals("tbDSKhuyenMai")) {
            try {
                view.loadChiTietChuongTrinhKhuyenMai();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            try {
                view.loadChuongTrinhKhuyenMaiCanChinhSua();
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

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        char key = e.getKeyChar();
        if((Character.isLetterOrDigit(key))|| key == e.VK_BACK_SPACE) {
            try {
                view.xuLyTimKiemTheoTextField(new ChuongTrinhKhuyenMaiBusImpl().layDSChuongTrinhKhuyenMai());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
