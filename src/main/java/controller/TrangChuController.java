package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.TrangChu;

public class TrangChuController implements ActionListener {

	private TrangChu view;
	
	public TrangChuController(TrangChu view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		view.xuLyDieuHuong(src);
	}

}
