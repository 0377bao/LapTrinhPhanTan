package controller;

import ui.ThongKeGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

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
            try {
                guiThongKe.thayDoiDuLieuBangVaBieuDoTheoComBoBoxTKTheoTuan();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("cbThongKeCuaHangTheoQuy")) {
            try {
                guiThongKe.thayDoiDuLieuBangVaBieuDoTheoQuyCuaCH();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("cboThongKeThangCH")) {
            try {
                guiThongKe.thayDoiDuLieuBangVaPanelTrongThangCuaCH();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("cbThongKeTrangThaiSanPham") || src.equals("cbThongKeSanPhamTrongQuy")) {
            try {
                guiThongKe.thayDoiDuLieuBangThongKeSanPham();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (src.equals("cbThongKeNhanVien")) {
            try {
                guiThongKe.thayDoiDuLieuNhanVien();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
