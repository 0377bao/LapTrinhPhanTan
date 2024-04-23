package bus;

import entity.ChiTietHoaDon;
import entity.ChuongTrinhKhuyenMai;
import entity.HoaDon;
import entity.SanPham;

import java.time.LocalDate;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HoaDonBus extends Remote  {
    public List<HoaDon> layHetDSHoaDon() throws RemoteException;
    public HoaDon TimHoaDonTheoMa(String maHoaDon) throws RemoteException;
    public boolean themHoaDon(HoaDon hd) throws RemoteException;
    public float hamLayGiamGiaCuaChiTietHoaDon(ChuongTrinhKhuyenMai ctkm, SanPham sp) throws RemoteException;
    public String taoMaHoaDon() throws RemoteException;
    public List<HoaDon> locHoaDonQLHD(String maHD, String maNV, String sdt, String httt, java.util.Date tuNgay, java.util.Date denNgay, String tongTien) throws RemoteException;
    public boolean kiemTraHoaDonLocQLHD(HoaDon hd, String maHD, String maNV, String sdt, String httt, java.util.Date tuNgay, java.util.Date denNgay, String tongTien) throws RemoteException;
    public int layTongSoHoaDonTrongHeThong() throws RemoteException;
    List<HoaDon> layLichSuGiaoDichKhachHang(String maKhachHang, int soGD) throws RemoteException;

    List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY) throws RemoteException;
    public List<ChiTietHoaDon> layDSChiTietHoaDonTheoMaHD(String maHD) throws RemoteException;
}
