package controller;

import ui.ThongKeGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ThongKeController implements ActionListener, MouseListener {
    ThongKeGui guiThongKe;

    public ThongKeController(ThongKeGui guiThongKe) {
        this.guiThongKe = guiThongKe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String src = e.getActionCommand();
        if (src.equals("cboThongKeTheoTuan")) {
            guiThongKe.thayDoiDuLieuBangVaBieuDoTheoComBoBoxTKTheoTuan();
        } else if (src.equals("cbThongKeCuaHangTheoQuy")) {
            guiThongKe.thayDoiDuLieuBangVaBieuDoTheoQuyCuaCH();
        } else if (src.equals("cboThongKeThangCH")) {
            guiThongKe.thayDoiDuLieuBangVaPanelTrongThangCuaCH();
        } else if (src.equals("cbThongKeTrangThaiSanPham") || src.equals("cbThongKeSanPhamTrongQuy")) {
            guiThongKe.thayDoiDuLieuBangThongKeSanPham();
        } else if (src.equals("cbThongKeNhanVien")) {
            guiThongKe.thayDoiDuLieuNhanVien();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
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

}
