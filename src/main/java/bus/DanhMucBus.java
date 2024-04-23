package bus;

import entity.DanhMuc;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DanhMucBus extends Remote {
    public List<DanhMuc> layDSDanhMuc() throws RemoteException;
    public DanhMuc timDanhMucTheoMa(String maDanhMuc) throws RemoteException;
    public boolean themDanhMuc(DanhMuc dm) throws RemoteException;
    public String taoMa() throws RemoteException;
    public boolean validData(String ma, String ten) throws RemoteException;
    public DanhMuc timDanhMucTheoTen(String ten) throws RemoteException;

    public String getMes() throws RemoteException;
}
