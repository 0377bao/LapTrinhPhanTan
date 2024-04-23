package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.TrangChu;

public class DangXuatController implements ActionListener{
	private TrangChu view;
	
	public DangXuatController(TrangChu view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		view.xuLyDangXuat(e);
	}

	

	

	

	

}
