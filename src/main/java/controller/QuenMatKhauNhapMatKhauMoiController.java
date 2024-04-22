package controller;

import ui.QuenMatKhauNhapMatKhauMoiGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuenMatKhauNhapMatKhauMoiController implements ActionListener {

	private QuenMatKhauNhapMatKhauMoiGui view;
	
	public QuenMatKhauNhapMatKhauMoiController(QuenMatKhauNhapMatKhauMoiGui view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		if(src.equals("btnXacNhan") || src.equals("txtNhapLaiMatKhau")) {
			this.view.xuLyXacNhan();
		}
		if(src.equals("txtMatKhau")) {
			this.view.xuLyEnterMatKhauMoi();
		}
	}
	
}
