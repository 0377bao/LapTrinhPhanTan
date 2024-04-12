package bus.Impl;

import bus.HoaDonBus;
import dao.impl.HoaDonDaoImpl;
import dao.impl.KhachHangDaoImpl;
import entity.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HoaDonBusImpl implements HoaDonBus {

    HoaDonDaoImpl hoaDonDao;
    KhachHangDaoImpl khachHangDao;
    public HoaDonBusImpl(){
        hoaDonDao = new HoaDonDaoImpl();
        khachHangDao = new KhachHangDaoImpl();
    }
    @Override
    public List<HoaDon> layHetDSHoaDon() {
        return hoaDonDao.layHetDSHoaDon();
    }

    @Override
    public HoaDon TimHoaDonTheoMa(String maHoaDon) {
        return hoaDonDao.layHoaDonTheoMa(maHoaDon);
    }

    @Override
    public boolean themHoaDon(HoaDon hd) {
        return hoaDonDao.themHoaDon(hd);
    }

    @Override
    public float hamLayGiamGiaCuaChiTietHoaDon(ChuongTrinhKhuyenMai ctkm, SanPham sp) {
        String theLoai = "";
        if(sp instanceof Sach) theLoai = sp.getTheLoai();
        else theLoai = ((VanPhongPham) sp).getDanhMuc().getTenDanhMuc();
        for (MucKhuyenMai mkm : ctkm.getDsMucKhuyenMai()) {
            if(mkm.getTenMucKhuyenMai().equals(theLoai)) return mkm.getTiLeKhuyenMai();
        }
        return 0;
    }

    @Override
    public String taoMaHoaDon() {
        long timestamp = Instant.now().toEpochMilli();
        String maHoaDon = String.valueOf(timestamp % 1000000);
        return "HD" + maHoaDon;
    }
    @Override
    public ArrayList<HoaDon> locHoaDonQLHD(String maHD, String maNV, String sdt, String httt, Date tuNgay, Date denNgay, String tongTien) {
        ArrayList<HoaDon> ds = new ArrayList<>();
        for (HoaDon hoaDon : layHetDSHoaDon()) {
            if(kiemTraHoaDonLocQLHD(hoaDon, maHD, maNV, sdt, httt, tuNgay, denNgay, tongTien)) ds.add(hoaDon);
        }
        return ds;
    }


    @Override
    public boolean kiemTraHoaDonLocQLHD(HoaDon hd, String maHD, String maNV, String sdt, String httt, Date tuNgay, Date denNgay, String tongTien) {
        Pattern pmaHD = Pattern.compile(maHD,Pattern.CASE_INSENSITIVE);
        Matcher mmaHD = pmaHD.matcher(hd.getMaHoaDon());
        if(!mmaHD.find()) return false;
        Pattern pNV = Pattern.compile(maNV,Pattern.CASE_INSENSITIVE);
        Matcher mNV = pNV.matcher(hd.getNhanVien().getMaNhanVien());
        if(!mNV.find()) return false;
        Pattern pSDT = Pattern.compile(sdt);
        Matcher mSDT = pSDT.matcher(hd.getKhachHang().getSdt());
        if(!mSDT.find()) return false;
        if(!hd.getHinhThucThanhToan().equals(httt) && !httt.equals("Tất cả")) return false;
        if(tuNgay != null) {
            LocalDate lcdtuNgay = tuNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(lcdtuNgay.isAfter(hd.getNgayLap())) return false;
        }
        if(denNgay != null) {
            LocalDate lcddenNgay = denNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(lcddenNgay.isBefore(hd.getNgayLap())) return false;
        }
        if(tongTien.equals("Dưới 1.000.000")) {
            if(hd.tinhTongTien() >= 1000000) return false;
        }
        if(tongTien.equals("Từ 1.000.000 đến 2.000.000")) {
            if(hd.tinhTongTien() < 1000000 || hd.tinhTongTien() >= 2000000) return false;
        }
        if(tongTien.equals("Từ 2.000.000 đến 5.000.000")) {
            if(hd.tinhTongTien() < 2000000 || hd.tinhTongTien() >= 5000000) return false;
        }
        if(tongTien.equals("Từ 5.000.000 đến 10.000.000")) {
            if(hd.tinhTongTien() < 5000000 || hd.tinhTongTien() >= 10000000) return false;
        }
        if(tongTien.equals("Trên 10.000.000")) {
            if(hd.tinhTongTien() <= 10000000) return false;
        }
        return true;
    }

    @Override
    public int layTongSoHoaDonTrongHeThong() {
        return hoaDonDao.tongSoHoaDon();
    }


    @Override
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKhachHang, int soGD) {
        return khachHangDao.layLichSuGiaoDichKhachHang(maKhachHang,soGD);
    }

    @Override
    public List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY) {
        return hoaDonDao.layHoaDonTuNgayXDenNgayY(ngayX,ngayY);
    }
}
