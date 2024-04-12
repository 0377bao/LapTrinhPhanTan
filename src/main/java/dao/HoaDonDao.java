package dao;

import entity.HoaDon;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonDao {
    public List<HoaDon> layHetDSHoaDon();
    public HoaDon layHoaDonTheoMa(String maHoaDon);
    public boolean themHoaDon(HoaDon hd);
    public int tongSoHoaDon();
    public List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY);

}
