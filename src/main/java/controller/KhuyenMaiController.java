package controller;

import bus.impl.ChuongTrinhKhuyenMaiBusImpl;
import ui.KhuyenMaiGui;

import java.awt.event.*;

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
            view.taoMa();
        } else if (btn.equals("btnThem")) {
            view.themMucKhuyenMai();
        } else if (btn.equals("btnLuu")) {
            view.themCTKM();
        } else if (btn.equals("btnCapNhat")) {
            view.capNhatMucKhuyenMai();
        } else if (btn.equals("btnXoa")) {
            view.xoaMucKhuyenMai();
        } else if (btn.equals("btnLuuCapNhat")) {
            view.luuCapNhat();
        } else if (btn.equals("btnApDungCT")) {
            view.apDungCTKM();
        } else if (btn.equals("cboSanPham")) {
            view.chuyenDoiSanPham();
        } else if (btn.equals("cboTrangThai")) {
            view.xuLySuKienCboTrangThai();
        } else if (btn.equals("checkBoxChinhSuaMKM")) {
            view.xuLySuKienCheckBoxChinhSuaMKM();
        }else if(btn.equals("cboMucKM")){
            view.xuLySuKienCboMucKM(e.getSource());
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
            view.loadChiTietChuongTrinhKhuyenMai();
            view.loadChuongTrinhKhuyenMaiCanChinhSua();
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
            view.xuLyTimKiemTheoTextField(new ChuongTrinhKhuyenMaiBusImpl().layDSChuongTrinhKhuyenMai());
        }
    }
}
