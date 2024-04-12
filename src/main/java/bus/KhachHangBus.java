package bus;

import customUI.MyTable;
import entity.HoaDon;
import entity.KhachHang;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public interface KhachHangBus {
    public List<KhachHang> layDSKhachHang();
    public String themKhachHang(KhachHang kh);
    public String capNhatThongTinKhachHang(KhachHang kh);
    public KhachHang timKhachHangTheoSDT(String sdtkh);
    public List<KhachHang> locKhachHang(String tenTim, String sdtTim);
    public boolean capNhatDiemTichLuyKhachHang(KhachHang kh);
    public String kiemTraThongTinKhachHangHopLe(KhachHang kh);
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKH, int soGD);
    public String taoMaKhachHang();
    public void sapXepTheoTongTienMua(MyTable tb, DefaultTableModel model);
    public KhachHang timKhachHangTheoMa(String maKH);
    public List<KhachHang> sapXepTheoTongTien(List<KhachHang> ds);
}
