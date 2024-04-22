package controller;

import ui.DangNhapGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			this.view.xuLyDangNhap();
		}
		if(src.equals("btnQuenMatKhau")) {
			this.view.xuLyQuenMatKhau();
		}
		if(src.equals("txtTenDangNhap")) {
			this.view.xuLyEnterTenTaiKhoan();
		}
	}
}
