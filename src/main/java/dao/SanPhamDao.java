package dao;

import entity.NhaCungCap;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import java.util.List;

public interface SanPhamDao {
    public List<SanPham> layDSSanPham();
    public Sach timSachTheoMa(String maS);
    public VanPhongPham timVanPhongPhamTheoMa(String maV);
    public boolean themSanPham(SanPham s);
    public boolean themSach(Sach s);
    public boolean themVanPhongPham(VanPhongPham vpp);
    public boolean capNhatSach(Sach sachSua);
    public boolean capNhatVanPhongPham(VanPhongPham vppSua);
    public boolean capNhatSoLuongTonSanPham(SanPham sp);
    public List<SanPham> layDSSanPhamConBan();
    public Long timMaxSoLuongSanPhamTheoLoai(String loai);
    public List<SanPham> layDSSanPhamTheoTrangThai(String trangThai, String loai);
    public List<SanPham> layDSSanPhamTheoNCC(String dieuKien, String loai);
    public List<SanPham> layDSSachGanHet();
    public List<SanPham> layDSVPPGanHet();
    public List<SanPham> layDSSachConBan();
    public List<SanPham> layDSVPPConBan();
  public List<SanPham> lay10SachBanChayNhat();

}
