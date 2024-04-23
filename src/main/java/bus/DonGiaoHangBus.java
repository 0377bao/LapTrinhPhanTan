package bus;

import entity.DonGiaoHang;

import javax.swing.*;
import java.util.List;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DonGiaoHangBus extends Remote {
    public List<DonGiaoHang> layDSDonGiaoHang() throws RemoteException;
    public DonGiaoHang timDonHangTheoMaDonHang(String maCTim)throws RemoteException;
    public DonGiaoHang timDonHangTheoTenKhachHang(String tenKhachHang) throws RemoteException;
    public boolean themDonHangMoi(DonGiaoHang dgh) throws RemoteException;
    public String taoMaDonHang() throws RemoteException;
    public boolean capNhatThongTinDonHang(DonGiaoHang dgh) throws RemoteException;
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenGiamDan(List<DonGiaoHang> ds_one) throws RemoteException;
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenTangDan(List<DonGiaoHang> ds_one) throws RemoteException;
    public boolean validateDuLieu(String maDonHang, String tenKhachHang, String sdt, JTextField soKg, JTextField soKm) throws RemoteException;
    public List<DonGiaoHang> layDSDonHangDangGiao() throws RemoteException;
    public List<DonGiaoHang> layDSDonHangBiHuy() throws RemoteException;
    public List<DonGiaoHang> layDSDonHangGiaoThanhCong() throws RemoteException;
    public String getMessage() throws RemoteException;
}
