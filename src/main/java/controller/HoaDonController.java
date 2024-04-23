package controller;

import ui.HoaDonGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class HoaDonController implements ActionListener, MouseListener {

	private HoaDonGui view;
	
	public HoaDonController(HoaDonGui view1) {
		super();
		this.view = view1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
		switch (src) {
		case "btnLocHoaDon": {
            try {
                this.view.xuLyLocHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnTaiLai": {
            try {
                this.view.xuLyTaiLaiHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
            break;
		}
		case "btnInHoaDon": {
			this.view.xuLyInHoaDon();
			break;
		}
		case "btnTaoDonGiao": {
            try {
                this.view.xuLyTaoDonGiaoHang();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
            try {
                this.view.xuLyClickQuanLyHoaDon();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
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
