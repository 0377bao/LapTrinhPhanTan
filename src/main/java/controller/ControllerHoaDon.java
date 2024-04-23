package controller;

import ui.HoaDonGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerHoaDon implements ActionListener, MouseListener {

	private HoaDonGui view;
	
	public ControllerHoaDon(HoaDonGui view1) {
		super();
		this.view = view1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
		case "btnLocHoaDon": {
			this.view.xuLyLocHoaDon();
			break;
		}
		case "btnTaiLai": {
			this.view.xuLyTaiLaiHoaDon();
			break;
		}
		case "btnInHoaDon": {
			this.view.xuLyInHoaDon();
			break;
		}
		case "btnTaoDonGiao": {
			this.view.xuLyTaoDonGiaoHang();
			break;
		}
		default:
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String src = e.getComponent().getName();
		if(src.equals("tableHoaDon")) {
			this.view.xuLyClickQuanLyHoaDon();
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

}
