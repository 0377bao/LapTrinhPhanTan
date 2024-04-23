package controller;

import ui.TrangChu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


public class TrangChuController implements ActionListener {

	private TrangChu view;
	
	public TrangChuController(TrangChu view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();
        try {
            view.xuLyDieuHuong(src);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        } catch (NotBoundException ex) {
            throw new RuntimeException(ex);
        } catch (RemoteException ex) {
            throw new RuntimeException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
