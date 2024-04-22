package dao;

import entity.ChiTietHoaDon;
import entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonDao {
    public List<HoaDon> layHetDSHoaDon();

    public HoaDon layHoaDonTheoMa(String maHoaDon);

    public boolean themHoaDon(HoaDon hd);

    public int tongSoHoaDon();

    public List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY);

    public List<HoaDon> layHoaDonTheoNhanVien(String maNV, LocalDate date1, LocalDate date2);

    public double tongDoanhThuTheoNhanVien(String maNV, LocalDate date1, LocalDate date2);

    public int tongSanPhamTheoNhanVien(String maNV, LocalDate date1, LocalDate date2);
    public List<ChiTietHoaDon> layDSChiTietHoaDonTheoMaHD(String maHD);
}
