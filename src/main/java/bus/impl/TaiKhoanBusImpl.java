package bus.impl;

import bus.TaiKhoanBus;
import dao.impl.NhanVienDaoImpl;
import dao.impl.TaiKhoanDaoImpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TaiKhoanBusImpl extends UnicastRemoteObject implements TaiKhoanBus {
    private TaiKhoanDaoImpl tkDI;
    private NhanVienDaoImpl nvDI;
    public TaiKhoanBusImpl() throws RemoteException{
        tkDI = new TaiKhoanDaoImpl();
        nvDI = new NhanVienDaoImpl();
    }
    @Override
    public boolean kiemTraMatKhau(String taiKhoan, String matKhau)  throws RemoteException{
        return tkDI.kiemTraMatKhau(taiKhoan, matKhau);
    }

    @Override
    public boolean capNhatMatKhau(String tenTK, String mk)  throws RemoteException{
        return nvDI.capNhatMatKhauNV(tenTK, mk);
    }
}