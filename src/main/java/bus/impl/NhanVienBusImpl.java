package bus.impl;

import bus.NhanVienBus;
import dao.impl.NhanVienDaoImpl;
import entity.NhanVien;
import entity.TaiKhoan;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NhanVienBusImpl extends UnicastRemoteObject implements NhanVienBus {
    private NhanVienDaoImpl nhanVienDaoImpl;
    public String mes="";
    public NhanVienBusImpl() throws RemoteException {
        nhanVienDaoImpl = new NhanVienDaoImpl();
    }
    @Override
    public boolean themNhanVien(NhanVien nv, TaiKhoan tk) throws RemoteException {
        return nhanVienDaoImpl.themNhanVien(nv, tk);
    }

    @Override
    public boolean capNhatNV(NhanVien nv) throws RemoteException {
        return nhanVienDaoImpl.capNhatNV(nv);
    }

    @Override
    public List<NhanVien> layDSNhanVien() throws RemoteException {
        return nhanVienDaoImpl.layDSNhanVien();
    }

    @Override
    public NhanVien layNhanVienTheoMa(String maNV) throws RemoteException {
        return nhanVienDaoImpl.layNhanVienTheoMa(maNV);
    }

    @Override
    public boolean capNhatMatKhauNhanVien(String maNV, String matKhau) throws RemoteException {
        return nhanVienDaoImpl.capNhatMatKhauNV(maNV, matKhau);
    }

    @Override
    public String layEmailNhanVienTheoMa(String maNV) throws RemoteException {
        return nhanVienDaoImpl.layEmailNhanVienTheoMa(maNV);
    }

    @Override
    public String layMatKhauNhanVienTheoMa(String ma) throws RemoteException {
        return nhanVienDaoImpl.layMatKhauNhanVienTheoMa(ma);
    }

    @Override
    public boolean validData(String maNhanVien, String tenNhanVien, String sdt, String email, String hinhAnh, String diaChi, String cCCD, String taiKhoan) throws RemoteException {
        if (maNhanVien.equals("")) {
            mes = "Vui lòng nhấn chọn Tạo mã";
            return false;
        }
        if (tenNhanVien.equals("")) {
            mes = "Vui lòng nhập tên nhân viên";
            return false;
        } else {
            if (!(tenNhanVien.matches("^[\\p{L}][\\p{L} ]+"))) {
                mes = "Tên nhân viên không chứa ký tự đặc biệt và số";
                return false;
            }
        }
        if (sdt.equals("")) {
            mes = "Vui lòng nhập số điện thoại";
            return false;
        } else {
            if (!(sdt.matches("^02\\d{9}$") || sdt.matches("^0[3579]\\d{8}$"))) {
                mes = "Số điện thoại bắt đầu là 02 gồm 11 số hoặc bắt đầu là 03 - 05 - 07 - 09 gồm 10 số";
                return false;
            }
            if (nhanVienDaoImpl.layNhanVienTheoMa(maNhanVien) == null) {
                for (NhanVien nv : nhanVienDaoImpl.layDSNhanVien()) {
                    if (nv.getSdt().equals(sdt)) {
                        mes = "Số điện thoại đã tồn tại";
                        return false;
                    }
                }
            } else {
                return true;
            }
        }
        if (cCCD.equals("")) {
            mes = "Vui lòng nhập căn cước công dân";
            return false;
        } else {
            if (!(cCCD.matches("^0\\d{11}"))) {
                mes = "CCCD gồm 12 số và bắt đầu bằng 0";
                return false;
            }
            for (NhanVien nv : nhanVienDaoImpl.layDSNhanVien()) {
                if (nv.getcCCD().equals(cCCD)) {
                    mes = "Căn cước công dân đã tồn tại";
                    return false;
                }
            }
        }
        if (email.equals("")) {
            mes = "Vui lòng nhập email";
            return false;
        } else {
            if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                mes = "Email không hợp lệ";
                return false;
            }
        }
        if (diaChi.equals("")) {
            mes = "Vui lòng nhập địa chỉ";
            return false;
        } else {
            if (!(diaChi.matches("^[\\p{L}0-9][\\p{L}0-9/.,' -]+"))) {
                mes = "Địa chỉ không chứa ký tự đặc biệt";
                return false;
            }
        }
        if (taiKhoan.equals("")) {
            mes = "Vui lòng nhập mật khẩu để tạo tài khoản";
            return false;
        } else {
            if (!(taiKhoan.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$"))) {
                mes = "Mật khẩu ít nhất 8 ký tự bao gồm chữ hoa, chữ thường, ký tự đặc biệt và số";
                return false;
            }
        }
        if (hinhAnh == null) {
            mes = "Vui lòng chọn ảnh";
            return false;
        }
        return true;
    }

    @Override
    public String taoMaNV() throws RemoteException {
        int max = layDSNhanVien().size() + 1;
        return "NV" + max;
    }

    @Override
    public List<NhanVien> locNVTheoChucVu(String chucVu) throws RemoteException {
        return nhanVienDaoImpl.locNVTheoChucVu(chucVu);
    }

    @Override
    public List<NhanVien> locNVTheoGioiTinh(boolean gt) throws RemoteException {
        return nhanVienDaoImpl.locNVTheoGioiTinh(gt);
    }

    @Override
    public List<NhanVien> locNVTheoTrangThai(String trangThai) throws RemoteException {
        return nhanVienDaoImpl.locNVTheoTrangThai(trangThai);
    }

    @Override
    public void timNVTheoSdt(List<NhanVien> ds, String sdt) throws RemoteException {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        Pattern p = Pattern.compile(sdt);
        for (NhanVien nv : layDSNhanVien()) {
            Matcher m = p.matcher(nv.getSdt());
            if (m.find()) {
                dsNV.add(nv);
            }
        }
        ds.clear();
        ds.addAll(dsNV);
    }

    @Override
    public void timNVTheoTen(List<NhanVien> ds, String ten) throws RemoteException {
        ArrayList<NhanVien> dsNV = new ArrayList<>();
        Pattern p = Pattern.compile(ten, Pattern.CASE_INSENSITIVE);
        for (NhanVien nv : layDSNhanVien()) {
            Matcher m = p.matcher(nv.getTenNhanVien());
            if (m.find()) {
                dsNV.add(nv);
            }
        }
        if (dsNV.size() > 0) {
            ds.clear();
            ds.addAll(dsNV);
        }
    }

    @Override
    public String getMes() throws RemoteException {
        return this.mes;
    }
}
