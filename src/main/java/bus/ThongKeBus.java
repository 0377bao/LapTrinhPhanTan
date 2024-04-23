package bus;

import entity.DonDoiTra;
import entity.HoaDon;
import entity.NhanVien;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface ThongKeBus extends Remote{

    public List<DonDoiTra> dsDDTTheoNV(NhanVien nv, LocalDate x, LocalDate y) throws RemoteException;

    public List<HoaDon> dsHDTheoNV(NhanVien nv, LocalDate x, LocalDate y)  throws RemoteException;

    public double tongDoanhThu(NhanVien nv, LocalDate x, LocalDate y)  throws RemoteException;

    public int tongSoSanPham(NhanVien nv, LocalDate x, LocalDate y) throws RemoteException;

    public int tinhSoNgayTrongTuanNay() throws RemoteException;

    public void taoDuLieuComBoBoxTKCuaNV(DefaultComboBoxModel<String> model) throws RemoteException;

    public void layDuLieuThongKeChiTietCuaNVTheoTuan(DefaultTableModel model, DefaultComboBoxModel<String> modelCB, NhanVien nv) throws RemoteException;

    public void layDuLieuThongKeChiTietCuaNVTheoTuanVaoBieuDo(DefaultComboBoxModel<String> cbmodel, NhanVien nv, DefaultCategoryDataset dataSet) throws RemoteException;

    public void taoDuLieuComBoBoxThongKeCHTheoQuy(DefaultComboBoxModel<String> model) throws RemoteException;

    public void thongKeThongTinCuaHangTheoQuy(String nam, DefaultTableModel model, DefaultPieDataset dataSet) throws RemoteException;

    public void thongKeCuaHangTrongThang(String thang, JLabel soHD, JLabel doanhThu, JLabel soLuongSP, JLabel soDDT, DefaultTableModel model) throws RemoteException;

    public void lay10SPBanChayNhat(String trangThaiTK, DefaultTableModel modelS, DefaultTableModel modelVPP, LocalDate x, LocalDate y) throws RemoteException;

    public void lay10SPBiDoiTraNhieuNhat(DefaultTableModel modelDT, LocalDate x, LocalDate y) throws RemoteException;

    public void ThongKeSanPham(String trangThaiTK, String Quy, DefaultTableModel modelSach, DefaultTableModel modelVPP) throws RemoteException;

    public void hienThiThongTinThongKeNV(JLabel tongNV, JLabel nvDaNghi, JLabel nvBanHang, JLabel nvQuanLy) throws RemoteException;

    public void hienSoLieuCuaNhanVienTrongThang(String thang, DefaultTableModel model, DefaultCategoryDataset dataSet) throws RemoteException;
}