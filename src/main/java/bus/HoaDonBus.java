package bus;

import entity.ChuongTrinhKhuyenMai;
import entity.HoaDon;
import entity.SanPham;

import java.time.LocalDate;
import java.util.List;

public interface HoaDonBus {
    public List<HoaDon> layHetDSHoaDon();
    public HoaDon TimHoaDonTheoMa(String maHoaDon);
    public boolean themHoaDon(HoaDon hd);
    public float hamLayGiamGiaCuaChiTietHoaDon(ChuongTrinhKhuyenMai ctkm, SanPham sp);
    public String taoMaHoaDon();
    public List<HoaDon> locHoaDonQLHD(String maHD, String maNV, String sdt, String httt, java.util.Date tuNgay, java.util.Date denNgay, String tongTien);
    public boolean kiemTraHoaDonLocQLHD(HoaDon hd, String maHD, String maNV, String sdt, String httt, java.util.Date tuNgay, java.util.Date denNgay, String tongTien);
    public int layTongSoHoaDonTrongHeThong();
    List<HoaDon> layLichSuGiaoDichKhachHang(String maKhachHang, int soGD);

    List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY);
}
