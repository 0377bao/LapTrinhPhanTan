package bus.impl;

import bus.TaiKhoanBus;
import dao.impl.NhanVienDaoImpl;
import dao.impl.TaiKhoanDaoImpl;

public class TaiKhoanBusImpl implements TaiKhoanBus {
    private TaiKhoanDaoImpl tkDI = new TaiKhoanDaoImpl();
    private NhanVienDaoImpl nvDI = new NhanVienDaoImpl();
    @Override
    public boolean kiemTraMatKhau(String taiKhoan, String matKhau) {
        return tkDI.kiemTraMatKhau(taiKhoan, matKhau);
    }

    @Override
    public boolean capNhatMatKhau(String tenTK, String mk) {
        return nvDI.capNhatMatKhauNV(tenTK, mk);
    }
}
