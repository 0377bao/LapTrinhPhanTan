package bus;

import entity.DonDoiTra;
import entity.HoaDon;
import entity.NhanVien;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public interface ThongKeBus {

    public List<DonDoiTra> dsDDTTheoNV(NhanVien nv, LocalDate x, LocalDate y);

    public List<HoaDon> dsHDTheoNV(NhanVien nv, LocalDate x, LocalDate y);

    public double tongDoanhThu(NhanVien nv, LocalDate x, LocalDate y);

    public int tongSoSanPham(NhanVien nv, LocalDate x, LocalDate y);

    public int tinhSoNgayTrongTuanNay();

    public void taoDuLieuComBoBoxTKCuaNV(DefaultComboBoxModel<String> model);

    public void layDuLieuThongKeChiTietCuaNVTheoTuan(DefaultTableModel model, DefaultComboBoxModel<String> modelCB, NhanVien nv);

    public void layDuLieuThongKeChiTietCuaNVTheoTuanVaoBieuDo(DefaultComboBoxModel<String> cbmodel, NhanVien nv, DefaultCategoryDataset dataSet);

    public void taoDuLieuComBoBoxThongKeCHTheoQuy(DefaultComboBoxModel<String> model);

    public void thongKeThongTinCuaHangTheoQuy(String nam, DefaultTableModel model, DefaultPieDataset dataSet);

    public void thongKeCuaHangTrongThang(String thang, JLabel soHD, JLabel doanhThu, JLabel soLuongSP, JLabel soDDT, DefaultTableModel model);

    public void lay10SPBanChayNhat(String trangThaiTK, DefaultTableModel modelS, DefaultTableModel modelVPP, LocalDate x, LocalDate y);

    public void lay10SPBiDoiTraNhieuNhat(DefaultTableModel modelDT, LocalDate x, LocalDate y);

    public void ThongKeSanPham(String trangThaiTK, String Quy, DefaultTableModel modelSach, DefaultTableModel modelVPP);

    public void hienThiThongTinThongKeNV(JLabel tongNV, JLabel nvDaNghi, JLabel nvBanHang, JLabel nvQuanLy);

    public void hienSoLieuCuaNhanVienTrongThang(String thang, DefaultTableModel model, DefaultCategoryDataset dataSet);
}
