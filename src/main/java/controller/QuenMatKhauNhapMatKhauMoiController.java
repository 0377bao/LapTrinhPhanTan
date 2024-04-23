package controller;

import ui.QuenMatKhauNhapMatKhauMoiGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class QuenMatKhauNhapMatKhauMoiController implements ActionListener {

	private QuenMatKhauNhapMatKhauMoiGui view;
	
	public QuenMatKhauNhapMatKhauMoiController(QuenMatKhauNhapMatKhauMoiGui view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("btnXacNhan") || src.equals("txtNhapLaiMatKhau")) {
            try {
                this.view.xuLyXacNhan();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
		if(src.equals("txtMatKhau")) {
			this.view.xuLyEnterMatKhauMoi();
		}
	}
	
}
