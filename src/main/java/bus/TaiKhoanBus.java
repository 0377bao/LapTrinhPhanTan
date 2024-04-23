package bus;

import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TaiKhoanBus extends Remote{
    public boolean kiemTraMatKhau(String taiKhoan, String matKhau) throws RemoteException;
    public boolean capNhatMatKhau(String tenTK, String mk) throws RemoteException;
}