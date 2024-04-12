package bus;

import entity.TaiKhoan;

public interface TaiKhoanBus {
    public boolean kiemTraMatKhau(String taiKhoan, String matKhau);
    public boolean capNhatMatKhau(String tenTK, String mk);
}
