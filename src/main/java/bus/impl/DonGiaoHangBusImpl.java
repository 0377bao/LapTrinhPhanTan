package bus.impl;

import bus.DonGiaoHangBus;
import dao.impl.DonGiaoHangDaoImpl;
import entity.DonGiaoHang;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DonGiaoHangBusImpl implements DonGiaoHangBus {
    private DonGiaoHangDaoImpl dghDI = new DonGiaoHangDaoImpl();
    private String mes = "";

    public String getMessgage() {
        return mes;
    }
    @Override
    public List<DonGiaoHang> layDSDonGiaoHang() {
        return dghDI.layDSDonHang();
    }

    @Override
    public DonGiaoHang timDonHangTheoMaDonHang(String maCTim) {
        return dghDI.timDonHangTheoMaDonHang(maCTim);
    }

    @Override
    public DonGiaoHang timDonHangTheoTenKhachHang(String tenKhachHang) {
        return dghDI.timDonHangTheoTenKhachHang(tenKhachHang);
    }

    @Override
    public boolean themDonHangMoi(DonGiaoHang dgh) {
        return dghDI.themDonGiaoHang(dgh);
    }

    @Override
    public String taoMaDonHang() {
        int maHoaDon = dghDI.tongSoHoaDon() + 1;
        return "DGH" + maHoaDon;
    }

    @Override
    public boolean capNhatThongTinDonHang(DonGiaoHang dgh) {
        return dghDI.capNhatThongTinDonHang(dgh   );
    }

    @Override
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenGiamDan(List<DonGiaoHang> ds_one) {
        List<DonGiaoHang> ds = ds_one.size() > 0 ? ds_one : dghDI.layDSDonHang();
        Collections.sort(ds,new Comparator<DonGiaoHang>() {
            @Override
            public int compare(DonGiaoHang o1, DonGiaoHang o2) {
                Float kh1 = (Float)o1.getTienVanChuyen();
                Float kh2 = (Float)o2.getTienVanChuyen();
                return kh2.compareTo(kh1);
            }
        });
        return ds;
    }

    @Override
    public List<DonGiaoHang> sapXepTheoTongTienVanChuyenTangDan(List<DonGiaoHang> ds_one) {
        List<DonGiaoHang> ds = ds_one.size() > 0 ? ds_one : dghDI.layDSDonHang();
        Collections.sort(ds,new Comparator<DonGiaoHang>() {

            @Override
            public int compare(DonGiaoHang o1, DonGiaoHang o2) {
                Float kh1 = (Float)o1.getTienVanChuyen();
                Float kh2 = (Float)o2.getTienVanChuyen();
                return kh1.compareTo(kh2);
            }
        });
        return ds;
    }

    @Override
    public boolean validateDuLieu(String maDonHang, String tenKhachHang, String sdt, JTextField soKg, JTextField soKm) {
        if(maDonHang.equals("")) {
            mes = "Vui lòng tạo mã đơn hàng trước";
            return false;
        }
        if(tenKhachHang.equals("")) {
            mes = "Vui lòng nhập tên khách hàng";
            return false;
        }else if(!tenKhachHang.matches("^[\\p{L} ]+$")) {
            mes = "Tên không hợp lệ, tên chỉ bao gồm chữ và khoảng trắng";
            return false;
        }
        if(sdt.equals("")) {
            mes = "Vui lòng nhập số điện thoại để liên lạc nhận hàng";
            return false;
        }else if(!sdt.matches("^(02|03|05|07|08|09)\\d{8}$")) {
            mes = "Số điện thoại không hợp lệ, số điện thoại phải bắt đầu bằng 02 hoặc 03 hoặc 05 hoặc 07 hoặc 08 hoặc 09 và theo sau là 8 chữ số";
            return false;
        }

        if(soKg.getText().equals("")) {
            mes = "Vui lòng nhập số kg";
            return false;
        }else {
            try {
                int soKgs = Integer.parseInt(soKg.getText());
                if(soKgs < 0) {
                    mes = "Số kg phải là 1 số dương";
                    return false;
                }
            }catch(Exception e) {
                mes = "Số kg chỉ bao gồm kí tự số";
                return false;
            }
        }
        if(soKm.getText().equals("")) {
            mes = "Vui lòng chọn chức năng tính khoảng cách để biết tiền chuyển và tiến hành tạo đơn giao hàng";
            return false;
        }else {
            try {
                float soKms = Float.parseFloat(soKm.getText());
                if(soKms > 40) {
                    mes = "Số km vượt quá mức quy định nên không giao hàng";
                    return false;
                }
            }catch(Exception e) {
                return false;
            }

        }
        return true;
    }

    @Override
    public List<DonGiaoHang> layDSDonHangDangGiao() {
        return dghDI.layDSDonHangDangGiao();
    }

    @Override
    public List<DonGiaoHang> layDSDonHangBiHuy() {
        return dghDI.layDSDonHangBiHuy();
    }

    @Override
    public List<DonGiaoHang> layDSDonHangGiaoThanhCong() {
        return dghDI.layDSDonHangGiaoThanhCong();
    }
}
