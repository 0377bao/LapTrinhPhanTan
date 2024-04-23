package bus;

import customUI.MyTable;
import entity.HoaDon;
import entity.KhachHang;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KhachHangBus extends Remote  {
    public List<KhachHang> layDSKhachHang() throws RemoteException;
    public String themKhachHang(KhachHang kh) throws RemoteException;
    public String capNhatThongTinKhachHang(KhachHang kh) throws RemoteException;
    public KhachHang timKhachHangTheoSDT(String sdtkh) throws RemoteException;
    public List<KhachHang> locKhachHang(String tenTim, String sdtTim) throws RemoteException;
    public boolean capNhatDiemTichLuyKhachHang(KhachHang kh) throws RemoteException;
    public String kiemTraThongTinKhachHangHopLe(KhachHang kh) throws RemoteException;
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKH, int soGD) throws RemoteException;
    public String taoMaKhachHang() throws RemoteException;
    public void sapXepTheoTongTienMua(MyTable tb, DefaultTableModel model) throws RemoteException;
    public KhachHang timKhachHangTheoMa(String maKH) throws RemoteException;
    public List<KhachHang> sapXepTheoTongTien(List<KhachHang> ds) throws RemoteException;
}
