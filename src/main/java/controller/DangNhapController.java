package controller;

import ui.DangNhapGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;


public class DangNhapController implements ActionListener{
	private DangNhapGui view;
	
	public DangNhapController(DangNhapGui view) {
		super();
		this.view = view;
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String src = e.getActionCommand();
		if(src.equals("btnDangNhap") || src.equals("txtMatKhau")) {
            try {
                this.view.xuLyDangNhap();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
		if(src.equals("btnQuenMatKhau")) {
            try {
                this.view.xuLyQuenMatKhau();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
		if(src.equals("txtTenDangNhap")) {
			this.view.xuLyEnterTenTaiKhoan();
		}
	}
}
