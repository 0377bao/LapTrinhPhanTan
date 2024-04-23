package controller;

import ui.QuenMatKhauNhapMaGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class QuenMatKhauNhapMaController implements ActionListener {
    private QuenMatKhauNhapMaGui view;

    public QuenMatKhauNhapMaController(QuenMatKhauNhapMaGui view) {
        super();
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String src = e.getActionCommand();
        if (src.equals("btnGuiMa")) {
            this.view.xuLyGuiMa();
        }
        if (src.equals("btnXacNhan") || src.equals("txtXacNhan")) {
            try {
                this.view.xuLyXacNhan();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
