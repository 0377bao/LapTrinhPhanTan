package dao;

import entity.NhanVien;
import entity.TaiKhoan;

import java.util.List;

public interface NhanVienDao {
    public List<NhanVien> layDSNhanVien();
    public boolean themNhanVien(NhanVien nv, TaiKhoan tk);
    public boolean capNhatNV(NhanVien nv);
    public boolean capNhatMatKhauNV(String nv, String matKhau);
    public NhanVien layNhanVienTheoMa(String maNV);
    public String layMatKhauNhanVienTheoMa(String ma);
    public String layEmailNhanVienTheoMa(String maNV);
}
