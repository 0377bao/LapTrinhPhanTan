package dao.impl;


import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import tool.Tool;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonDaoImpl implements HoaDonDao {
    private EntityManagerFactory emf;

    public HoaDonDaoImpl() {
        emf = Tool.initServer();
    }

    public List<HoaDon> layHetDSHoaDon() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("HoaDon.findAll", HoaDon.class).getResultList().stream().toList();
    }

    public HoaDon layHoaDonTheoMa(String maHoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HoaDon.findByMaHoaDon", HoaDon.class).setParameter("maHoaDon", maHoaDon).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean themHoaDon(HoaDon hd) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(hd);
            hd.getDsChiTietHoaDon().forEach(cthd -> {
                cthd.setHoaDon(hd);
                em.persist(cthd);
            });
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return false;
    }

    public int tongSoHoaDon() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HoaDon.findAll", HoaDon.class).getResultList().size();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return 0;
    }

    // lay hoa don tu ngay X den ngay Y
    public List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HoaDon.findByNgayLap", HoaDon.class).setParameter("ngayBD", ngayX).setParameter("ngayKT", ngayY).getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HoaDon> layHoaDonTheoNhanVien(String maNV, LocalDate date1, LocalDate date2) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT hd FROM HoaDon hd WHERE hd.nhanVien.maNhanVien = :maNhanVien AND hd.ngayLap BETWEEN :date1 AND :date2", HoaDon.class)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public double tongDoanhThuTheoNhanVien(String maNV, LocalDate date1, LocalDate date2) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd WHERE hd.nhanVien.maNhanVien = :maNhanVien AND hd.ngayLap BETWEEN :date1 AND :date2", Double.class)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int tongSanPhamTheoNhanVien(String maNV, LocalDate date1, LocalDate date2) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT SUM(cthd.soLuongMua) FROM ChiTietHoaDon cthd JOIN HoaDon hd ON cthd.hoaDon.id = hd.id WHERE hd.nhanVien.maNhanVien = :maNhanVien AND hd.ngayLap BETWEEN :date1 AND :date2", Integer.class)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return 0;
    }

    @Override
    public List<ChiTietHoaDon> layDSChiTietHoaDonTheoMaHD(String maHD) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT cthd FROM ChiTietHoaDon cthd JOIN cthd.hoaDon hd WHERE hd.maHoaDon = :maHoaDon", ChiTietHoaDon.class)
                    .setParameter("maHoaDon", maHD).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public List<HoaDon> locHoaDonQLHD(String maHD, String maNV, String sdt, String httt, Date tuNgay, Date denNgay, String tongTien) {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT hd FROM HoaDon hd";
        boolean flag = false;
        if(!maHD.trim().equals("") || !maNV.trim().equals("") || !sdt.trim().equals("") || !httt.trim().equals("Tất cả") || tuNgay != null || denNgay != null) {
            query += " WHERE";
        }
        if(!maHD.trim().equals("")) {
            query = handleQuery(flag, query);
            flag = true;
            query += " hd.maHoaDon LIKE :maHoaDon";
        }
        if(!maNV.trim().equals("")) {
            query = handleQuery(flag, query);
            flag = true;
            query += " hd.nhanVien.maNhanVien LIKE :maNhanVien";
        }
        if(!sdt.trim().equals("")) {
            query = handleQuery(flag, query);
            flag = true;
            query += " hd.khachHang.sdt LIKE :soDienThoai";
        }
        if(!httt.trim().equals("Tất cả")) {
            query = handleQuery(flag, query);
            flag = true;
            query += " hd.hinhThucThanhToan = :hinhThucThanhToan";
        }
        if (tuNgay != null || denNgay != null) {
            query = handleQuery(flag, query);
            flag = true;
            query += " hd.ngayLap BETWEEN :tuNgay AND :denNgay";
        }
        Query queryrun = em.createQuery(query, HoaDon.class);
        if(!maHD.trim().equals("")) {
            queryrun.setParameter("maHoaDon", "%" + maHD + "%");
        }
        if(!maNV.trim().equals("")) {
            queryrun.setParameter("maNhanVien", "%" + maNV + "%");
        }
        if(!sdt.trim().equals("")) {
            queryrun.setParameter("soDienThoai", "%" + sdt + "%");
        }
        if(!httt.trim().equals("Tất cả")) {
            queryrun.setParameter("hinhThucThanhToan", httt);
        }
        if (tuNgay != null) {
            LocalDate lcdtuNgay = tuNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            queryrun.setParameter("tuNgay", lcdtuNgay);
        }
        if (denNgay != null) {
            LocalDate lcddenNgay = denNgay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            queryrun.setParameter("denNgay", lcddenNgay);
        }
        if(tuNgay == null && denNgay != null) {
            queryrun.setParameter("tuNgay", LocalDate.of(1900, 1, 1));
        }
        if(denNgay == null && tuNgay != null) {
            LocalDate date = LocalDate.now();
            queryrun.setParameter("denNgay", date);
        }
        return queryrun.getResultList();
    }

    public String handleQuery(boolean flag, String query) {
        if (flag) {
            query += " AND";
        }
        return query;
    }
}
