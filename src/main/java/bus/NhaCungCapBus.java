package bus;

import entity.NhaCungCap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface NhaCungCapBus extends Remote {
    public boolean themNCC(NhaCungCap nhaCungCap) throws RemoteException;
    public boolean capNhatNCC(NhaCungCap nhaCungCap) throws RemoteException;
    public NhaCungCap timNhaCungCapTheoMa(String maNCC) throws RemoteException;
    public NhaCungCap timNCCTheoTen(String tenNCC) throws RemoteException;
    public List<NhaCungCap> layDSNhaCungCap() throws RemoteException;
    public boolean validData(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email) throws RemoteException;
    public String taoMa() throws RemoteException;
    public void locNCCTheoSdt(List<NhaCungCap> ds, String sdt) throws RemoteException;
    public void locNCCTheoTen(List<NhaCungCap> ds, String ten) throws RemoteException;
    public void locNCCTheoDiaChi(List<NhaCungCap>  ds, String diaChi) throws RemoteException;
    public String getMes() throws RemoteException;
}
