package controller;

import ui.GiaoHangGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

public class GiaoHangController implements ActionListener, KeyListener{
	private GiaoHangGui view;
	public GiaoHangController(GiaoHangGui view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
        try {
            view.XuLyDonHang(src);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
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
		// TODO Auto-generated method stub
        try {
            view.xuLySuKienTimKiem(e);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
