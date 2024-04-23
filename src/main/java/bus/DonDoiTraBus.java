package bus;

import entity.ChiTietDonDoiTra;
import entity.ChuongTrinhKhuyenMai;
import entity.DonDoiTra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface DonDoiTraBus extends Remote{
    public boolean themDonDoiTra(DonDoiTra donDoiTra) throws RemoteException;

    public DonDoiTra layDonDoiTraTheoMa(String maDDT) throws RemoteException;

    public List<DonDoiTra> layHetDSDonDoiTra() throws RemoteException;

    public List<DonDoiTra> layDonDoiTraTheoHoaDon(String maHoaDon) throws RemoteException;

    public List<DonDoiTra> layDonDoiTraTuNgayXDenNgayY(LocalDate date1, LocalDate date2) throws RemoteException;

    public void layDanhSachHoaDonKhachHangTrong7Ngay(String sdt, DefaultTableModel model) throws RemoteException;

    public String taoMaDonDoiTra(String maHoaDon) throws RemoteException;

    public void layDanhSachDonDoiTraVaoTable(DefaultTableModel model, List<DonDoiTra> ds)throws RemoteException;

    public void hienDanhSachSanPhamTrongHoaDon(DefaultTableModel model, String maHoaDon)throws RemoteException;

    public int tinhDiemHoanTra(float tongTien, int diemTrongHD)throws RemoteException;

    //    public void tinhTongDDT(String PhuongThucDoiTra, MyTable tb, JTextField tongTien, JTextField tongSL,
//                            JTextField diemHT, int diemTrongHD, ChuongTrinhKhuyenMai ctkm, JTextField tienGiam);
    public void timKiemBangMaDonDoiTra(List<DonDoiTra> ds, String maDDT) throws RemoteException;

    public void timKiemBangMaHoaDon(List<DonDoiTra> ds, String maHD) throws RemoteException;

    public void timKiemBangSDT(List<DonDoiTra> ds, String sdt) throws RemoteException;

    public void tinhTongDDT(String PhuongThucDoiTra, JTable tb, JTextField tongTien, JTextField tongSL,
                            JTextField diemHT, int diemTrongHD, ChuongTrinhKhuyenMai ctkm, JTextField tienGiam) throws RemoteException;
    public List<ChiTietDonDoiTra> layChiTietDonDoiTraTheoMaDonDoiTra(String maDonDoiTra) throws RemoteException;
}
