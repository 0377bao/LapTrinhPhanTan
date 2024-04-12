package dao;

import entity.DonGiaoHang;

import java.util.ArrayList;
import java.util.List;

public interface DonGiaoHangDao {
    public List<DonGiaoHang> layDSDonHang();
    public boolean themDonGiaoHang(DonGiaoHang dgh);
    public boolean capNhatThongTinDonHang(DonGiaoHang dgh);
    public DonGiaoHang timDonHangTheoMaDonHang(String maCTim);

    public DonGiaoHang timDonHangTheoTenKhachHang(String tenKhachHang);

    public int tongSoHoaDon();
    public List<DonGiaoHang> layDSDonHangDangGiao();

    public List<DonGiaoHang> layDSDonHangBiHuy();

    public List<DonGiaoHang> layDSDonHangGiaoThanhCong();
}
