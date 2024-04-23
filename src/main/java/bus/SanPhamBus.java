package bus;

import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SanPhamBus extends Remote{
    public List<SanPham> layDSSanPham() throws RemoteException;
    public List<SanPham> layDSSachConBan() throws RemoteException;
    public String taoMaSach() throws RemoteException;
    public boolean validDataSach(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap,
                                 String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String tacGia, String nhaXB,
                                 String namXB) throws RemoteException;
    public boolean themSanPham(SanPham sp) throws RemoteException;
    public List<SanPham> kiemTraTrangThaiSach(String trangThai) throws RemoteException;
    public boolean capNhatSanPham(SanPham sp) throws RemoteException;
    public SanPham timKiemSanPham(String maSP) throws RemoteException;
    public List<SanPham> locSachTheoTen(List<SanPham> ds, String ten) throws RemoteException;
    public List<SanPham> locSachTheoNCC(String maNCC) throws RemoteException;
    public List<SanPham> locSachTheoNamXB(List<SanPham> ds, int namXB) throws RemoteException;
    public List<SanPham> locSachTheoTheLoai(List<SanPham> ds, String theLoai) throws RemoteException;
    public List<SanPham> locSachTheoTacGia(List<SanPham> ds, String tacGia) throws RemoteException;
    public List<SanPham> layDSSachGanHet() throws RemoteException;

    public List<SanPham> locVPPTheoNCC(String maNCC) throws RemoteException;
    public List<SanPham> locVPPTheoDanhMuc(String danhMuc, List<SanPham> dsVPP) throws RemoteException;
    public List<SanPham> locVPPTheoTheLoai(List<SanPham> ds, String theLoai) throws RemoteException;
    public List<SanPham> kiemTraTrangThaiVPP(String trangThai) throws RemoteException;
    public List<SanPham> locVPPTheoTen(List<SanPham> ds, String ten) throws RemoteException;
    public String taoMaVPP() throws RemoteException;
    public boolean validDataVPP(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap,
                                String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String chatLieu) throws RemoteException;
    public boolean capNhatSoLuongTonSanPham(SanPham sp) throws RemoteException;
    public List<SanPham> lay10SachBanChayNhat() throws RemoteException;
    public List<SanPham> layDSVPPConBan() throws RemoteException;
    public List<SanPham> layDSVPPGanHet() throws RemoteException;
    public String getMes() throws RemoteException;
}
