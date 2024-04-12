package dao;

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
}
