package bus.impl;

import bus.ThongKeBus;
import dao.DonDoiTraDao;
import dao.HoaDonDao;
import dao.NhanVienDao;
import dao.SanPhamDao;
import dao.impl.DonDoiTraDaoImpl;
import dao.impl.HoaDonDaoImpl;
import dao.impl.NhanVienDaoImpl;
import dao.impl.SanPhamDaoImpl;
import entity.ChiTietDonDoiTra;
import entity.ChiTietHoaDon;
import entity.DonDoiTra;
import entity.HoaDon;
import entity.NhanVien;
import entity.SanPham;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ThongKeBusImpl implements ThongKeBus {
    DonDoiTraDao donDoiTraDao = new DonDoiTraDaoImpl();
    HoaDonDao hoaDonDao = new HoaDonDaoImpl();
    NhanVienDao nhanVienDao = new NhanVienDaoImpl();
    SanPhamDao sanPhamDao = new SanPhamDaoImpl();

    // hàm lấy đơn đổi trả trong từ ngày khởi đầu đến ngày kết thúc của nhân viên nv
    @Override
    public List<DonDoiTra> dsDDTTheoNV(NhanVien nv, LocalDate x, LocalDate y) {
        List<DonDoiTra> dsDTTTheoNV = donDoiTraDao.layDonDoiTraTheoNhanVien(nv.getMaNhanVien(), x, y);
        if (dsDTTTheoNV != null) {
            return dsDTTTheoNV;
        }
        return null;
    }

    // hàm lấy số hóa đơn từ ngày khởi đầu đến ngày kết thúc của nhân viên nv
    @Override
    public List<HoaDon> dsHDTheoNV(NhanVien nv, LocalDate x, LocalDate y) {
        List<HoaDon> dsHDTheoNV = hoaDonDao.layHoaDonTheoNhanVien(nv.getMaNhanVien(), x, y);
        if (dsHDTheoNV != null) {
            return dsHDTheoNV;
        }
        return null;
    }

    // hàm lấy doanh thu từ ngày khởi đầu đến ngày kết thúc của nhân viên nv
    @Override
    public double tongDoanhThu(NhanVien nv, LocalDate x, LocalDate y) {
        return hoaDonDao.tongDoanhThuTheoNhanVien(nv.getMaNhanVien(), x, y);
    }

    // hàm lấy số sản phẩm từ ngày khởi đầu đến ngày kết thúc của nhân viên nv
    @Override
    public int tongSoSanPham(NhanVien nv, LocalDate x, LocalDate y) {
        return hoaDonDao.tongSanPhamTheoNhanVien(nv.getMaNhanVien(), x, y);
    }

    @Override
    public int tinhSoNgayTrongTuanNay() {
        int ngayTrongTuan = 0;
        String thuHT = LocalDate.now().getDayOfWeek().toString();
        switch (thuHT) {
            case "MONDAY": {
                ngayTrongTuan = 1;
                break;
            }
            case "TUESDAY": {
                ngayTrongTuan = 2;
                break;
            }
            case "WEDNESDAY": {
                ngayTrongTuan = 3;
                break;
            }
            case "THURSDAY": {
                ngayTrongTuan = 4;
                break;
            }
            case "FRIDAY": {
                ngayTrongTuan = 5;
                break;
            }
            case "SATURDAY": {
                ngayTrongTuan = 6;
                break;
            }
            case "SUNDAY": {
                ngayTrongTuan = 7;
                break;
            }
        }
        return ngayTrongTuan;
    }

    @Override
    public void taoDuLieuComBoBoxTKCuaNV(DefaultComboBoxModel<String> model) {
        int ngayHT = LocalDate.now().getDayOfMonth();
        int ngayTrongTuan = tinhSoNgayTrongTuanNay();

        if ((ngayHT - ngayTrongTuan) <= 0) {
            model.addElement("ngày " + 1 + "-" + "ngày " + ngayHT);
            return;
        } else {
            model.addElement("ngày " + (ngayHT - ngayTrongTuan + 1) + "-ngày " + ngayHT);
            ngayHT -= ngayTrongTuan;
            while (ngayHT > 0) {
                if (ngayHT > 7) {
                    model.addElement("ngày " + (ngayHT - 6) + "-ngày " + ngayHT);
                } else {
                    model.addElement("ngày " + 1 + "-ngày " + ngayHT);
                }
                ngayHT = ngayHT - 7;
            }
            return;
        }
    }

    @Override
    public void layDuLieuThongKeChiTietCuaNVTheoTuan(DefaultTableModel model, DefaultComboBoxModel<String> modelCB, NhanVien nv) {
        String thuTrongTuan[] = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"};
        int thu = 0;
        String khoangCach[] = modelCB.getSelectedItem().toString().replace("ngày ", "").split("-");
        if (Integer.parseInt(khoangCach[1]) < 7) {
            thu = 7 - Integer.parseInt(khoangCach[1]);
        }
        for (int i = Integer.parseInt(khoangCach[0]); i <= Integer.parseInt(khoangCach[1]); i++) {

            int soHoaDon = dsHDTheoNV(nv,
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i),
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i)).size();
            int soDonDoiTra = dsDDTTheoNV(nv,
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i),
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i)).size();
            double tongDoanhThu = tongDoanhThu(nv,
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i),
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i));
            int tongSP = tongSoSanPham(nv, LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i), LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i));
            model.addRow(new Object[]{thuTrongTuan[thu], soHoaDon, tongDoanhThu, tongSP, soDonDoiTra});
            thu += 1;
        }
    }

    @Override
    public void layDuLieuThongKeChiTietCuaNVTheoTuanVaoBieuDo(DefaultComboBoxModel<String> cbmodel, NhanVien nv, DefaultCategoryDataset dataSet) {
        String thuTrongTuan[] = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"};
        int thu = 0;
        String khoangCach[] = cbmodel.getSelectedItem().toString().replace("ngày ", "").split("-");
        if (Integer.parseInt(khoangCach[1]) < 7) {
            thu = 7 - Integer.parseInt(khoangCach[1]);
        }
        for (int i = Integer.parseInt(khoangCach[0]); i <= Integer.parseInt(khoangCach[1]); i++) {
            double tongDoanhThu = tongDoanhThu(nv,
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i),
                    LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), i));
            dataSet.setValue(tongDoanhThu, "VNĐ", thuTrongTuan[thu]);
            thu += 1;
        }
    }

    @Override
    public void taoDuLieuComBoBoxThongKeCHTheoQuy(DefaultComboBoxModel<String> model) {
        for (int i = LocalDate.now().getYear(); i >= 2020; i--) {
            model.addElement(i + "");
        }
    }

    @Override
    public void thongKeThongTinCuaHangTheoQuy(String nam, DefaultTableModel model, DefaultPieDataset dataSet) {
        int namTK = Integer.parseInt(nam);
        LocalDate ngayBDQ1 = LocalDate.of(namTK, 1, 1);
        LocalDate ngayKTQ1 = LocalDate.of(namTK, 3, 31);
        float tongDoanhThuQ1 = 0;
        int tongSLQ1 = 0;
        int tongHoaDonQ1 = 0;
        int tongDDTQ1 = 0;

        LocalDate ngayBDQ2 = LocalDate.of(namTK, 4, 1);
        LocalDate ngayKTQ2 = LocalDate.of(namTK, 6, 30);
        float tongDoanhThuQ2 = 0;
        int tongSLQ2 = 0;
        int tongHoaDonQ2 = 0;
        int tongDDTQ2 = 0;

        LocalDate ngayBDQ3 = LocalDate.of(namTK, 7, 1);
        LocalDate ngayKTQ3 = LocalDate.of(namTK, 9, 30);
        float tongDoanhThuQ3 = 0;
        int tongSLQ3 = 0;
        int tongHoaDonQ3 = 0;
        int tongDDTQ3 = 0;

        LocalDate ngayBDQ4 = LocalDate.of(namTK, 10, 1);
        LocalDate ngayKTQ4 = LocalDate.of(namTK, 12, 31);
        float tongDoanhThuQ4 = 0;
        int tongSLQ4 = 0;
        int tongHoaDonQ4 = 0;
        int tongDDTQ4 = 0;

        for (NhanVien nv : nhanVienDao.layDSNhanVien()) {
            tongDoanhThuQ1 += tongDoanhThu(nv, ngayBDQ1, ngayKTQ1);
            tongSLQ1 += tongSoSanPham(nv, ngayBDQ1, ngayKTQ1);
            tongHoaDonQ1 += dsHDTheoNV(nv, ngayBDQ1, ngayKTQ1).size();
            tongDDTQ1 += dsDDTTheoNV(nv, ngayBDQ1, ngayKTQ1).size();
            tongDoanhThuQ2 += tongDoanhThu(nv, ngayBDQ2, ngayKTQ2);
            tongSLQ2 += tongSoSanPham(nv, ngayBDQ2, ngayKTQ2);
            tongHoaDonQ2 += dsHDTheoNV(nv, ngayBDQ2, ngayKTQ2).size();
            tongDDTQ2 += dsDDTTheoNV(nv, ngayBDQ2, ngayKTQ2).size();
            tongDoanhThuQ3 += tongDoanhThu(nv, ngayBDQ3, ngayKTQ3);
            tongSLQ3 += tongSoSanPham(nv, ngayBDQ3, ngayKTQ3);
            tongHoaDonQ3 += dsHDTheoNV(nv, ngayBDQ3, ngayKTQ3).size();
            tongDDTQ3 += dsDDTTheoNV(nv, ngayBDQ3, ngayKTQ3).size();
            tongDoanhThuQ4 += tongDoanhThu(nv, ngayBDQ4, ngayKTQ4);
            tongSLQ4 += tongSoSanPham(nv, ngayBDQ4, ngayKTQ4);
            tongHoaDonQ4 += dsHDTheoNV(nv, ngayBDQ4, ngayKTQ4).size();
            tongDDTQ4 += dsDDTTheoNV(nv, ngayBDQ4, ngayKTQ4).size();
        }
        model.addRow(new Object[]{"Quý 1", tongHoaDonQ1, tongSLQ1, tongDDTQ1, tongDoanhThuQ1});
        model.addRow(new Object[]{"Quý 2", tongHoaDonQ2, tongSLQ2, tongDDTQ2, tongDoanhThuQ2});
        model.addRow(new Object[]{"Quý 3", tongHoaDonQ3, tongSLQ3, tongDDTQ3, tongDoanhThuQ3});
        model.addRow(new Object[]{"Quý 4", tongHoaDonQ4, tongSLQ4, tongDDTQ4, tongDoanhThuQ4});
        dataSet.setValue("Quý 1", tongDoanhThuQ1);
        dataSet.setValue("Quý 2", tongDoanhThuQ2);
        dataSet.setValue("Quý 3", tongDoanhThuQ3);
        dataSet.setValue("Quý 4", tongDoanhThuQ4);
    }

    @Override
    public void thongKeCuaHangTrongThang(String thang, JLabel soHD, JLabel doanhThu, JLabel soLuongSP, JLabel soDDT, DefaultTableModel model) {
        int thangTK = Integer.parseInt(thang.substring(6));
        int soNgayTrongThang = YearMonth.of(LocalDate.now().getYear(), thangTK).lengthOfMonth();
        float tongDoanhThu = 0;
        int tongSL = 0;
        int tongHoaDon = 0;
        int tongDDT = 0;
        for (int i = 1; i <= soNgayTrongThang; i++) {
            float tDT = 0;
            int tSL = 0;
            int tHD = 0;
            int tDDT = 0;
            for (NhanVien nv : nhanVienDao.layDSNhanVien()) {
                tDT += tongDoanhThu(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, i),
                        LocalDate.of(LocalDate.now().getYear(), thangTK, i));
                tSL += tongSoSanPham(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, i),
                        LocalDate.of(LocalDate.now().getYear(), thangTK, i));
                tHD += dsHDTheoNV(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, i),
                        LocalDate.of(LocalDate.now().getYear(), thangTK, i)).size();
                tDDT += dsDDTTheoNV(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, i),
                        LocalDate.of(LocalDate.now().getYear(), thangTK, i)).size();
            }
            tongDoanhThu += tDT;
            tongSL += tSL;
            tongHoaDon += tHD;
            tongDDT += tDDT;
            model.addRow(new Object[]{"Ngày " + i, tHD, tSL, tDDT, tDT});
        }
        soHD.setText(tongHoaDon + "");
        doanhThu.setText(tongDoanhThu + "");
        soLuongSP.setText(tongSL + "");
        soDDT.setText(tongDDT + "");
    }

    @Override
    public void lay10SPBanChayNhat(String trangThaiTK, DefaultTableModel modelS, DefaultTableModel modelVPP, LocalDate x, LocalDate y) {
        List<String> SP = new ArrayList<>();
        List<Integer> SL = new ArrayList<>();
        SP.add("");
        SL.add(0);
        int bienChay = 1;
        int flag = 0;

        for (NhanVien nv : nhanVienDao.layDSNhanVien()) {
            for (HoaDon hd : dsHDTheoNV(nv, x, y)) {
                for (ChiTietHoaDon cthd : new HoaDonBusImpl().layDSChiTietHoaDonTheoMaHD(hd.getMaHoaDon())) {
                    for (int i = 0; i < SP.size(); ++i) {
                        if (i == 0 && SP.size() == 1) {
                            SP.set(i, cthd.getSanPham().getMaSanPham());
                            SL.set(i, cthd.getSoLuongMua());
                            SP.add("");
                            SL.add(0);
                            flag = 1;
                            break;
                        }
                        if (SP.get(i).equals(cthd.getSanPham().getMaSanPham())) {
                            SL.set(i, SL.get(i) + cthd.getSoLuongMua());
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        SP.add(cthd.getSanPham().getMaSanPham());
                        SL.add(cthd.getSoLuongMua());
                        bienChay++;
                    }
                    flag = 0;
                }
            }
        }

        if (SP.size() == 1) {
            return;
        }
        SP.remove(1);
        SL.remove(1);

        int soVPP = 0, soSach = 0;
        while ((soVPP <= 10 || soSach <= 10) && SP.size() > 0) {
            int max = 0;
            int node = 0;
            int min = SL.get(0);

            if (trangThaiTK.equals("Bán chạy nhất")) {
                for (int i = 0; i < SP.size(); i++) {
                    if (max < SL.get(i)) {
                        max = SL.get(i);
                        node = i;
                    }
                }
            } else {
                for (int i = 0; i < SP.size(); i++) {
                    if (min > SL.get(i)) {
                        min = SL.get(i);
                        node = i;
                    }
                }
            }

            SanPham sp = new SanPhamBusImpl().timKiemSanPham(SP.get(node));

//            SanPham sp = null;
            if (sp.getMaSanPham().toUpperCase().startsWith("SPS"))
                sp = sanPhamDao.timSachTheoMa(SP.get(node));
            else
                sp = sanPhamDao.timVanPhongPhamTheoMa(SP.get(node));


            if (sp.getMaSanPham().startsWith("SPS")) {

                if (soSach < 10) {
                    modelS.addRow(new Object[]{SP.get(node), sp.getTenSanPham(), SL.get(node),
                            sp.getGiaBan(), sp.getPhanTramLoiNhuan()});
                    soSach += 1;
                }
                SP.remove(node);
                SL.remove(node);
            }
            if (sp.getMaSanPham().startsWith("SPVPP")) {
                if (soVPP < 10) {
                    modelVPP.addRow(new Object[]{SP.get(node), sp.getTenSanPham(), SL.get(node),
                            sp.getGiaBan(), sp.getPhanTramLoiNhuan()});
                    soVPP += 1;
                }
                SP.remove(node);
                SL.remove(node);
            }
        }

    }

    @Override
    public void lay10SPBiDoiTraNhieuNhat(DefaultTableModel modelDT, LocalDate x, LocalDate y) {
        List<String> SP = new ArrayList<>();
        List<Integer> SL = new ArrayList<>();
        SP.add("");
        SL.add(0);

        int flag = 0;
        for (NhanVien nv : nhanVienDao.layDSNhanVien()) {

            for (DonDoiTra ddt : dsDDTTheoNV(nv, x, y)) {
                for (ChiTietDonDoiTra ctddt : new DonDoiTraBusImpl().layChiTietDonDoiTraTheoMaDonDoiTra(ddt.getMaDonDoiTra())) {
                    for (int i = 0; i < SP.size(); ++i) {
                        if (i == 0 && SP.size() == 1) {
                            SP.set(i, ctddt.getSanPham().getMaSanPham());
                            SL.set(i, ctddt.getSoLuongTra());
                            SP.add("");
                            SL.add(0);
                            flag = 1;
                            break;
                        }
                        if (SP.get(i).equals(ctddt.getSanPham().getMaSanPham())) {
                            SL.set(i, SL.get(i) + ctddt.getSoLuongTra());
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0) {
                        SP.add(ctddt.getSanPham().getMaSanPham());
                        SL.add(ctddt.getSoLuongTra());

                    }
                    flag = 0;
                }
            }
        }
        if (SP.size() == 1) {
            return;
        }
        SP.remove(1);
        SL.remove(1);
        while (SP.size() > 0) {
            int max = 0;
            int node = 0;

            for (int i = 0; i < SP.size(); i++) {
                if (max < SL.get(i)) {
                    max = SL.get(i);
                    node = i;
                }
                SanPham sp = new SanPhamBusImpl().timKiemSanPham(SP.get(node));

                if (sp.getMaSanPham().toUpperCase().startsWith("SPS"))
                    sp = sanPhamDao.timSachTheoMa(SP.get(node));
                else
                    sp = sanPhamDao.timVanPhongPhamTheoMa(SP.get(node));

                modelDT.addRow(new Object[]{SP.get(node), sp.getTenSanPham(), SL.get(node),
                        sp.getGiaBan(), sp.getPhanTramLoiNhuan()});
                SP.remove(node);
                SL.remove(node);
            }
        }
    }

    @Override
    public void ThongKeSanPham(String trangThaiTK, String Quy, DefaultTableModel modelSach, DefaultTableModel modelVPP) {
        LocalDate x = LocalDate.now();
        LocalDate y = LocalDate.now();
        if (Quy.equals("Quý 1")) {
            x = LocalDate.of(LocalDate.now().getYear(), 1, 1);
            y = LocalDate.of(LocalDate.now().getYear(), 3, 31);
        } else if (Quy.equals("Quý 2")) {
            x = LocalDate.of(LocalDate.now().getYear(), 4, 1);
            y = LocalDate.of(LocalDate.now().getYear(), 6, 30);
        } else if (Quy.equals("Quý 3")) {
            x = LocalDate.of(LocalDate.now().getYear(), 7, 1);
            y = LocalDate.of(LocalDate.now().getYear(), 9, 30);
        } else if (Quy.equals("Quý 4")) {
            x = LocalDate.of(LocalDate.now().getYear(), 10, 1);
            y = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        }
        modelSach.setRowCount(0);
        modelVPP.setRowCount(0);
        lay10SPBanChayNhat(trangThaiTK, modelSach, modelVPP, x, y);
    }

    @Override
    public void hienThiThongTinThongKeNV(JLabel tongNV, JLabel nvDaNghi, JLabel nvBanHang, JLabel nvQuanLy) {
        tongNV.setText(nhanVienDao.layDSNhanVien().size() + "");
        int nvdaNghi = 0, nvBH = 0, nvQL = 0;

        for (NhanVien nv : nhanVienDao.layDSNhanVien()) {
            if (nv.getTrangThai().equals("Đã nghỉ")) {
                nvdaNghi++;
            } else {
                if (nv.getChucVu().equals("Quản lý")) {
                    nvQL++;
                } else {
                    nvBH++;
                }
            }
        }
        nvDaNghi.setText(nvdaNghi + "");
        nvBanHang.setText(nvBH + "");
        nvQuanLy.setText(nvQL + "");
    }

    @Override
    public void hienSoLieuCuaNhanVienTrongThang(String thang, DefaultTableModel model, DefaultCategoryDataset dataSet) {
        thang = thang.replace("Tháng ", "");
        int thangTK = Integer.parseInt(thang);
        int soNgayTrongThang = YearMonth.of(LocalDate.now().getYear(), thangTK).lengthOfMonth();
        for (NhanVien nv : nhanVienDao.layDSNhanVien()) {
            double doanhThu = tongDoanhThu(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, 1),
                    LocalDate.of(LocalDate.now().getYear(), thangTK, soNgayTrongThang));
            int tongSP = tongSoSanPham(nv, LocalDate.of(LocalDate.now().getYear(), thangTK, 1),
                    LocalDate.of(LocalDate.now().getYear(), thangTK, soNgayTrongThang));

            model.addRow(new Object[]{nv.getMaNhanVien(), nv.getTenNhanVien(), tongSP, doanhThu});
            dataSet.setValue(doanhThu, "VND", nv.getMaNhanVien());
        }
    }
}