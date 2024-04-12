package dao;

import entity.HoaDon;
import entity.KhachHang;

import java.util.List;

public interface KhachHangDao {
    // Lấy danh sách tất cả khách hàng
    // Trả về danh sách or null
    public List<KhachHang> LayDSKhachHang();
    // Them tat ca khach hang
    // tra ve true neu them thanh cong
    // flase neu khong thanh cong
    public boolean themKhachHang(KhachHang kh);
    //Cap nhap thong tin khach hang
    //True neu thanh cong
    //Flase neu khong tim thay
    public boolean capNhatThongTinKhachHang(KhachHang kh);
    public boolean capNhatDiemTichLuyKhachHang(KhachHang kh);
    //
    public KhachHang timKhachHangTheoMa(String maKH);
    public KhachHang timKhachHangTheoSDT(String sdt);
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKH, int soGD);
    public int getMaKhachHangMax();
    public List<KhachHang> locKhachHang(String tenTim, String sdtTim);
    public List<KhachHang> layDsKhachHangSapXepTheoTongTien();
}
