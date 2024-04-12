package bus;

import entity.DonGiaoHang;

import javax.swing.*;
import java.util.List;

public interface DonGiaoHangBus {
    public List<DonGiaoHang> layDSDonGiaoHang();
    public DonGiaoHang timDonHangTheoMaDonHang(String maCTim);
    public DonGiaoHang timDonHangTheoTenKhachHang(String tenKhachHang);
    public boolean themDonHangMoi(DonGiaoHang dgh);
    public String taoMaDonHang();
    public boolean capNhatThongTinDonHang(DonGiaoHang dgh);
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenGiamDan(List<DonGiaoHang> ds_one);
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenTangDan(List<DonGiaoHang> ds_one);
    public boolean validateDuLieu(String maDonHang, String tenKhachHang, String sdt, JTextField soKg, JTextField soKm);
    public List<DonGiaoHang> layDSDonHangDangGiao();
    public List<DonGiaoHang> layDSDonHangBiHuy();
    public List<DonGiaoHang> layDSDonHangGiaoThanhCong();
}
