package bus.Impl;

import bus.DonDoiTraBus;
import dao.impl.DonDoiTraDaoImpl;
import dao.impl.HoaDonDaoImpl;
import entity.ChiTietHoaDon;
import entity.ChuongTrinhKhuyenMai;
import entity.DonDoiTra;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonDoiTraBusImpl implements DonDoiTraBus {
    DonDoiTraDaoImpl donDoiTraDao;
    public DonDoiTraBusImpl(){
        donDoiTraDao = new DonDoiTraDaoImpl();
    }
    @Override
    public boolean themDonDoiTra(DonDoiTra donDoiTra) {
        return donDoiTraDao.themDonDoiTra(donDoiTra);
    }

    @Override
    public DonDoiTra layDonDoiTraTheoMa(String maDDT) {
        return donDoiTraDao.layDonDoiTraTheoMa(maDDT);
    }

    @Override
    public List<DonDoiTra> layHetDSDonDoiTra() {
        return donDoiTraDao.layHetDSDonDoiTra();
    }

    @Override
    public List<DonDoiTra> layDonDoiTraTheoHoaDon(String maHoaDon) {
        return donDoiTraDao.layDonDoiTraTheoHoaDon(maHoaDon);
    }

    @Override
    public List<DonDoiTra> layDonDoiTraTuNgayXDenNgayY(LocalDate date1, LocalDate date2) {
        return donDoiTraDao.layDonDoiTraTuNgayXDenNgayY(date1, date2);
    }

    @Override
    public void layDanhSachHoaDonKhachHangTrong7Ngay(String sdt,DefaultTableModel model) {
         donDoiTraDao.layDanhSachHoaDonKhachHangTrong7Ngay(sdt).forEach(hd->{
             int soLuongSP = 0;
             for (ChiTietHoaDon cthd : hd.getDsChiTietHoaDon()) {
                 soLuongSP += cthd.getSoLuongMua();
             }
//             model.addRow(new Object[] { hd.getMaHoaDon(), hd.getNgayLap(), hd.getNhanVien().getTenNhanVien(),
//                     soLuongSP, Tools.dinhDangTien(hd.getThanhTien()) });
         });
    }

    @Override
    public String taoMaDonDoiTra(String maHoaDon) {
        return "DDT" + maHoaDon.substring(2, maHoaDon.length()) + 'v'
                + (donDoiTraDao.layDonDoiTraTheoHoaDon(maHoaDon).size() + 1);

    }

    @Override
    public void layDanhSachDonDoiTraVaoTable(DefaultTableModel model, List<DonDoiTra> ds) {
        for (DonDoiTra donDoiTra : ds) {
            int tongSoLuongSP = 0;
            for (int i = 0; i < donDoiTra.getDsChiTietDonDoiTra().size(); i++) {
                tongSoLuongSP += donDoiTra.getDsChiTietDonDoiTra().get(i).getSoLuongTra();
            }
            model.addRow(new Object[] { donDoiTra.getMaDonDoiTra(), donDoiTra.getHoaDon().getMaHoaDon(),
                    donDoiTra.getNhanVien().getTenNhanVien(), donDoiTra.getHoaDon().getKhachHang().getTenKhachHang(),
                    donDoiTra.getNgayDoiTra(), tongSoLuongSP });
        }
    }

    @Override
    public void hienDanhSachSanPhamTrongHoaDon(DefaultTableModel model, String maHoaDon) {
        model.setRowCount(0);
        for (ChiTietHoaDon cthd : new HoaDonDaoImpl().layHoaDonTheoMa(maHoaDon).getDsChiTietHoaDon()) {
//            model.addRow(new Object[] { cthd.getSanPham().getMaSanPham(), cthd.getSanPham().getTenSanPham(),
//                    cthd.getSoLuongMua(), Tools.dinhDangTien(cthd.getGiaBan()) });
        }
    }

    @Override
    public int tinhDiemHoanTra(float tongTien, int diemTrongHD) {
        int nuaTien = (int) tongTien / 2;
        if (nuaTien / 10000 > diemTrongHD) {
            return diemTrongHD;
        }
        return nuaTien / 10000;
    }

    @Override
    public void timKiemBangMaDonDoiTra(List<DonDoiTra> ds, String maDDT) {
        if (!maDDT.equals("")) {
            ArrayList<DonDoiTra> tam = new ArrayList<>();
            Pattern p = Pattern.compile(maDDT);
            for (DonDoiTra ddt : ds) {
                Matcher m = p.matcher(ddt.getMaDonDoiTra());
                if (m.find()) {
                    tam.add(ddt);
                }
            }
            ds.clear();
            ds.addAll(tam);
        }
    }

    @Override
    public void timKiemBangMaHoaDon(List<DonDoiTra> ds, String maHD) {
        if (!maHD.equals("")) {
            ArrayList<DonDoiTra> tam = new ArrayList<>();
            Pattern p = Pattern.compile(maHD);
            for (DonDoiTra ddt : ds) {
                Matcher m = p.matcher(ddt.getHoaDon().getMaHoaDon());
                if (m.find()) {
                    tam.add(ddt);
                }
            }
            ds.clear();
            ds.addAll(tam);
        }
    }

    @Override
    public void timKiemBangSDT(List<DonDoiTra> ds, String sdt) {

        if (!sdt.equals("")) {
            ArrayList<DonDoiTra> tam = new ArrayList<>();
            Pattern p = Pattern.compile(sdt);
            for (DonDoiTra ddt : ds) {
                Matcher m = p.matcher(ddt.getHoaDon().getKhachHang().getSdt());
                if (m.find()) {
                    tam.add(ddt);
                }
            }
            ds.clear();
            ds.addAll(tam);
        }
    }

    @Override
    public void tinhTongDDT(String PhuongThucDoiTra, JTable tb, JTextField tongTien, JTextField tongSL, JTextField diemHT, int diemTrongHD, ChuongTrinhKhuyenMai ctkm, JTextField tienGiam) {
//        if (PhuongThucDoiTra.equals("Đổi Hàng")) {
//            int tong = 0;
//            for (int i = 0; i < tb.getRowCount(); i++) {
//                tong += Integer.parseInt(tb.getValueAt(i, 2).toString());
//            }
//            tongSL.setText(tong + "");
//        } else {
//            float tong = 0;
//            float tongTienGiam = 0;
//            for (int i = 0; i < tb.getRowCount(); i++) {
//                int soLuongSP = Integer.parseInt(tb.getValueAt(i, 2).toString());
//                tongTienGiam += Float.parseFloat(tb.getValueAt(i, 3).toString().replaceAll("[,VND]", ""))
//                        * (new HoaDonBusImpl().hamLayGiamGiaCuaChiTietHoaDon(ctkm,
//                        new SanPhamBusImpl().timKiemSanPham(tb.getValueAt(i, 0).toString())) / 100)
//                        * soLuongSP
//                        + (Float.parseFloat(tb.getValueAt(i, 3).toString().replaceAll("[,VND]", ""))
//                        * (new SanPhamBusImpl().timKiemSanPham(tb.getValueAt(i, 0).toString()).getThue() / 100)
//                        * soLuongSP);
//                tong += soLuongSP * (Float.parseFloat(tb.getValueAt(i, 3).toString().replaceAll("[,VND]", "")))
//                        - tongTienGiam;
//            }
//            diemHT.setText(tinhDiemHoanTra(tong, diemTrongHD) + "");
//            tongTien.setText(Tools.dinhDangTien(tong - Float.parseFloat(diemHT.getText()) * 10000));
//
//            tienGiam.setText(Tools.dinhDangTien(tongTienGiam + Float.parseFloat(diemHT.getText()) * 10000));
//        }
    }
}
