package bus;

import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import java.util.List;

public interface SanPhamBus {
    public List<SanPham> layDSSanPham();
    public List<SanPham> layDSSachConBan();
    public String taoMaSach();
    public boolean validDataSach(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap,
                                 String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String tacGia, String nhaXB,
                                 String namXB);
    public boolean themSanPham(SanPham sp);
    public List<SanPham> kiemTraTrangThaiSach(String trangThai);
    public boolean capNhatSanPham(SanPham sp);
    public SanPham timKiemSanPham(String maSP);
    public List<SanPham> locSachTheoTen(List<SanPham> ds, String ten);
    public List<SanPham> locSachTheoNCC(String maNCC);
    public List<SanPham> locSachTheoNamXB(List<SanPham> ds, int namXB);
    public List<SanPham> locSachTheoTheLoai(List<SanPham> ds, String theLoai);
    public List<SanPham> locSachTheoTacGia(List<SanPham> ds, String tacGia);
    public List<SanPham> layDSSachGanHet();
    public List<SanPham> layDSVPPGanHet();
    public List<SanPham> layDSVPPConBan();
    public List<SanPham> locVPPTheoNCC(String maNCC);
    public List<SanPham> locVPPTheoDanhMuc(String danhMuc, List<SanPham> dsVPP);
    public List<SanPham> locVPPTheoTheLoai(List<SanPham> ds, String theLoai);
    public List<SanPham> kiemTraTrangThaiVPP(String trangThai);
    public List<SanPham> locVPPTheoTen(List<SanPham> ds, String ten);
    public String taoMaVPP();
    public boolean validDataVPP(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap,
                                String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String chatLieu);
    public boolean capNhatSoLuongTonSanPham(SanPham sp);
    public List<SanPham> lay10SachBanChayNhat();
}
