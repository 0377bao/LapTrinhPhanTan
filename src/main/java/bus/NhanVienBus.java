package bus;

import entity.NhanVien;
import entity.TaiKhoan;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface NhanVienBus extends Remote{
    public boolean themNhanVien(NhanVien nv, TaiKhoan tk) throws RemoteException;
    public boolean capNhatNV(NhanVien nv) throws RemoteException;
    public List<NhanVien> layDSNhanVien() throws RemoteException;
    public NhanVien layNhanVienTheoMa(String maNV) throws RemoteException;
    public boolean capNhatMatKhauNhanVien(String maNV, String matKhau) throws RemoteException;
    public String layEmailNhanVienTheoMa(String maNV) throws RemoteException;
    public String layMatKhauNhanVienTheoMa(String ma) throws RemoteException;
    public boolean validData(String maNhanVien, String tenNhanVien, String sdt, String email, String hinhAnh,
                             String diaChi, String cCCD, String taiKhoan) throws RemoteException;
    public String taoMaNV() throws RemoteException;
    public List<NhanVien> locNVTheoChucVu(String chucVu) throws RemoteException;
    public List<NhanVien> locNVTheoGioiTinh(boolean gt) throws RemoteException;
    public List<NhanVien> locNVTheoTrangThai(String trangThai) throws RemoteException;
    public void timNVTheoSdt(List<NhanVien> ds, String sdt) throws RemoteException;
    public void timNVTheoTen(List<NhanVien> ds, String ten) throws RemoteException;
    public String getMes() throws RemoteException;
}
