package dao;

import entity.DonDoiTra;
import entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface DonDoiTraDao {
    public boolean themDonDoiTra(DonDoiTra donDoiTra);

    public DonDoiTra layDonDoiTraTheoMa(String maDDT);

    public List<DonDoiTra> layHetDSDonDoiTra();

    public List<DonDoiTra> layDonDoiTraTheoHoaDon(String maHoaDon);

    public List<DonDoiTra> layDonDoiTraTuNgayXDenNgayY(LocalDate date1, LocalDate date2);

    public List<HoaDon> layDanhSachHoaDonKhachHangTrong7Ngay(String sdt);

    public List<DonDoiTra> timKiemBangSDT(String sdt);

    public List<DonDoiTra> layDonDoiTraTheoNhanVien(String maNhanVien, LocalDate date1, LocalDate date2);
}
