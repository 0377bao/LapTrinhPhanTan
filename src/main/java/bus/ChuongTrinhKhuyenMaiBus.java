package bus;

import dao.impl.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ChuongTrinhKhuyenMaiBus extends Remote{
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoMa(String ma) throws RemoteException;
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangApDung(boolean status) throws RemoteException;
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai()  throws RemoteException;
    public void tatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm)  throws RemoteException;
    public void capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, boolean status)  throws RemoteException;
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm)  throws RemoteException;
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) throws RemoteException;
    public boolean validateChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) throws RemoteException;

    public List<MucKhuyenMai> timMucKhuyenMaiTheoMaCTKM(String maCTKM) throws RemoteException;
    public String taoMa()throws RemoteException;
    public String getMessage()  throws RemoteException;
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai)  throws RemoteException;
    public boolean xoaMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai)  throws RemoteException;
    public boolean capNhatMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai)  throws RemoteException;
    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma)  throws RemoteException;

}
