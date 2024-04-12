package test;

import bus.Impl.DonGiaoHangBusImpl;
import bus.Impl.KhachHangBusImpl;
import dao.Ipml.DonGiaoHangDaoImpl;
import dao.Ipml.KhachHangDaoImpl;
import dao.Ipml.NhanVienDaoImpl;
import dao.Ipml.SanPhamDaoImpl;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySql");
//        EntityManager em = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            // Insert a new khachHang
////            KhachHang khachHang = new KhachHang("KH001", "Nguyen Van A", "0123456789", "hello", 1, 1);
////            em.persist(khachHang);
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        }
        SanPhamDaoImpl daoSp = new SanPhamDaoImpl();
//        List<SanPham> ds = daoSp.layDSSanPham();
//        ds.forEach(ty -> System.out.println(ty));
        VanPhongPham sach = new VanPhongPham("SPS001", "Sach 1", 1000, 10, "Kinh di", "a 1", "hinhanh", 1, 10, "tot", new NhaCungCap(
                "NCC1", "Nha cung cap 1", "0123456789", "dia chi 1", "email 1"
        ), "vải", new DanhMuc("DM1", "sat"));
        Sach sach1 = new Sach("SPS002", "Sach 1", 1000, 10, "Kinh di", "a 1", "hinhanh", 1, 10, "tot", new NhaCungCap(
                "NCC1", "Nha cung cap 1", "0123456789", "dia chi 1", "email 1"
        ), "vải", "baảo", 2003);
//        System.out.println(sach.getDanhMuc());
//        System.out.println(daoSp.themSanPham(sach1));
//        VanPhongPham tim = daoSp.timVanPhongPhamTheoMa("SPVPP10");
//        System.out.println(tim);
//        tim.setSoLuongTon(40);
//        System.out.println(daoSp.capNhatSoLuongTonSanPham(tim));
        NhanVienDaoImpl nhanVienIpml = new NhanVienDaoImpl();
//        List<NhanVien> dsNhanVien = nhanVienIpml.layDSNhanVien();
//        dsNhanVien.forEach(ty -> System.out.println(ty));
        NhanVien nhanVien = nhanVienIpml.layNhanVienTheoMa("NV001");
        nhanVien.setTenNhanVien("bao");
//        System.out.println(nhanVienIpml.capNhatNV(nhanVien));
//        System.out.println(nhanVienIpml.layNhanVienTheoMa("NV001"));
//        System.out.println(nhanVienIpml.layEmailNhanVienTheoMa("NV001"));

        List<MucKhuyenMai> ds = List.of(
                new MucKhuyenMai("Tieu thuyet", 20),
                new MucKhuyenMai("Báo chí", 10)
        );
        SanPham sp = daoSp.timSachTheoMa("SPS1");
        NhanVien nv = nhanVienIpml.layNhanVienTheoMa("NV1");
        ChuongTrinhKhuyenMai ctkm = new ChuongTrinhKhuyenMai("CTKM4", "Khai xuân 1", ds, false);
        KhachHang kh = new KhachHang("KH1", "bao", "0123456789", "dia chi", 1, 1);
        HoaDon hoaDon = new HoaDon("HD000883", LocalDate.now(), "hi", "hi", 0, 100, nhanVien, kh, ctkm, null, 10);
        List<ChiTietHoaDon> dscthd = List.of(
                new ChiTietHoaDon(1, 10, sp, hoaDon)
        );
        hoaDon.setDsChiTietHoaDon(dscthd);
        DonGiaoHang dgh = new DonGiaoHang("DGHD1", "bao", "0123456789", "dia chi", 10, false, "hi", 10, "hi", hoaDon);
        DonGiaoHangDaoImpl dghIpml = new DonGiaoHangDaoImpl();
//        System.out.println(dghIpml.tongSoHoaDon());

//        Phan test bus
        DonGiaoHangBusImpl dghBI = new DonGiaoHangBusImpl();
        List<DonGiaoHang> dsDonHang = dghBI.layDSDonHangDangGiao();
//        dsDonHang.forEach(ty -> System.out.println(ty));
//        List<KhachHang> dskh = new KhachHangDaoImpl().layDsKhachHangSapXepTheoTongTien();
//        dskh.forEach(ty -> System.out.println(ty));
//        System.out.println(new KhachHangBusImpl().taoMaKhachHang());
        KhachHang khthem = new KhachHangBusImpl().timKhachHangTheoMa("KH1");
        khthem.setMaKhachHang("KH40");
//        khthem.setTenKhachHang("bao 102");
        khthem.setSdt("0352761124");
        khthem.setEmail(khthem.getEmail().trim());
        System.out.println(new KhachHangBusImpl().themKhachHang(khthem));
    }
}