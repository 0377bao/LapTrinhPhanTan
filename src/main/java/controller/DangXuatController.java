package controller;


import ui.TrangChu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class DangXuatController implements ActionListener{
	private TrangChu view;
	
	public DangXuatController(TrangChu view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        try {
            view.xuLyDangXuat(e);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }

	

	

	

	

}
