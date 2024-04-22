package dao.impl;


import dao.HoaDonDao;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import Tool.Tool;

import java.time.LocalDate;
import java.util.List;

public class HoaDonDaoImpl implements HoaDonDao {
    private EntityManagerFactory emf;

    public HoaDonDaoImpl() {
        emf = Tool.initServer();
    }

    public List<HoaDon> layHetDSHoaDon() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HoaDon.findAll", HoaDon.class).getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return null;
    }

    public HoaDon layHoaDonTheoMa(String maHoaDon) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("HoaDon.findByMaHoaDon", HoaDon.class).setParameter("maHoaDon", maHoaDon).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
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
        } finally {
            em.close();
            emf.close();
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
        } finally {
            em.close();
            emf.close();
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
            return em.createQuery("SELECT SUM(hd.thanhTien) FROM HoaDon hd " +
                            "WHERE hd.nhanVien.maNhanVien = :maNhanVien AND hd.ngayLap BETWEEN :date1 AND :date2", Double.class)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2).getSingleResult();
        } catch (Exception e) {
        }
        return 0;
    }

    @Override
    public int tongSanPhamTheoNhanVien(String maNV, LocalDate date1, LocalDate date2) {
        EntityManager em = emf.createEntityManager();
        Long result;
        try {
            result = em.createQuery("SELECT SUM(cthd.soLuongMua) FROM ChiTietHoaDon cthd JOIN cthd.hoaDon hd " +
                            "WHERE hd.nhanVien.maNhanVien = :maNhanVien AND hd.ngayLap BETWEEN :date1 AND :date2", Long.class)
                    .setParameter("maNhanVien", maNV)
                    .setParameter("date1", date1)
                    .setParameter("date2", date2).getSingleResult();
            return result == null ? 0 : result.intValue();
        } catch (Exception e) {
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

}
