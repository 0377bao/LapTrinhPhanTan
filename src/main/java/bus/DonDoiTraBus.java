package bus;

import entity.ChuongTrinhKhuyenMai;
import entity.DonDoiTra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public interface DonDoiTraBus {
    public boolean themDonDoiTra(DonDoiTra donDoiTra);
    public DonDoiTra layDonDoiTraTheoMa(String maDDT);
    public List<DonDoiTra> layHetDSDonDoiTra();
    public List<DonDoiTra> layDonDoiTraTheoHoaDon(String maHoaDon);
    public List<DonDoiTra> layDonDoiTraTuNgayXDenNgayY(LocalDate date1, LocalDate date2);
    public void layDanhSachHoaDonKhachHangTrong7Ngay(String sdt,DefaultTableModel model);
    public String taoMaDonDoiTra(String maHoaDon);
    public void layDanhSachDonDoiTraVaoTable(DefaultTableModel model, List<DonDoiTra> ds);
    public void hienDanhSachSanPhamTrongHoaDon(DefaultTableModel model, String maHoaDon);
    public int tinhDiemHoanTra(float tongTien,int diemTrongHD);
//    public void tinhTongDDT(String PhuongThucDoiTra, MyTable tb, JTextField tongTien, JTextField tongSL,
//                            JTextField diemHT, int diemTrongHD, ChuongTrinhKhuyenMai ctkm, JTextField tienGiam);
public void timKiemBangMaDonDoiTra(List<DonDoiTra> ds, String maDDT);
public void timKiemBangMaHoaDon(List<DonDoiTra> ds, String maHD);
public void timKiemBangSDT(List<DonDoiTra> ds, String sdt);
public void tinhTongDDT(String PhuongThucDoiTra, JTable tb, JTextField tongTien, JTextField tongSL,
                        JTextField diemHT, int diemTrongHD, ChuongTrinhKhuyenMai ctkm, JTextField tienGiam);
}
