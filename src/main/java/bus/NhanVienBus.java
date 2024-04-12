package bus;

import entity.NhanVien;
import entity.TaiKhoan;

import java.util.ArrayList;
import java.util.List;

public interface NhanVienBus {
    public boolean themNhanVien(NhanVien nv, TaiKhoan tk);
    public boolean capNhatNV(NhanVien nv);
    public List<NhanVien> layDSNhanVien();
    public NhanVien layNhanVienTheoMa(String maNV);
    public boolean capNhatMatKhauNhanVien(String maNV, String matKhau);
    public String layEmailNhanVienTheoMa(String maNV);
    public String layMatKhauNhanVienTheoMa(String ma);
    public boolean validData(String maNhanVien, String tenNhanVien, String sdt, String email, String hinhAnh,
                             String diaChi, String cCCD, String taiKhoan);
    public String taoMaNV();
    public List<NhanVien> locNVTheoChucVu(String chucVu);
    public List<NhanVien> locNVTheoGioiTinh(boolean gt);
    public List<NhanVien> locNVTheoTrangThai(String trangThai);
    public void timNVTheoSdt(ArrayList<NhanVien> ds, String sdt);
    public void timNVTheoTen(ArrayList<NhanVien> ds, String ten);
}
