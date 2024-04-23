package dao.impl;

import dao.DonDoiTraDao;
import entity.ChiTietDonDoiTra;
import entity.DonDoiTra;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tool.Tool;

import java.time.LocalDate;
import java.util.List;

public class DonDoiTraDaoImpl implements DonDoiTraDao {
    private EntityManagerFactory emf;

    public DonDoiTraDaoImpl() {
        emf = Tool.initServer();
    }

    @Override
    public boolean themDonDoiTra(DonDoiTra donDoiTra) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(donDoiTra);
            donDoiTra.getDsChiTietDonDoiTra().forEach(ct -> {
                ct.setDonDoiTra(donDoiTra);
                em.persist(ct);
            });
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public DonDoiTra layDonDoiTraTheoMa(String maDDT) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.find(DonDoiTra.class, maDDT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonDoiTra> layHetDSDonDoiTra() {
        try {
            EntityManager em = emf.createEntityManager();
            return em.createNamedQuery("DonDoiTra.findAll", DonDoiTra.class).getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonDoiTra> layDonDoiTraTheoHoaDon(String maHoaDon) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.createNamedQuery("DonDoiTra.findByHoaDon", DonDoiTra.class)
                        .setParameter("maHoaDon", maHoaDon).getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonDoiTra> layDonDoiTraTuNgayXDenNgayY(LocalDate date1, LocalDate date2) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.createNamedQuery("DonDoiTra.findByNgayXDenNgayY", DonDoiTra.class)
                        .setParameter("date1", date1)
                        .setParameter("date2", date2)
                        .getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<HoaDon> layDanhSachHoaDonKhachHangTrong7Ngay(String sdt) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.createQuery("SELECT h FROM HoaDon h WHERE h.khachHang.sdt = :sdt AND h.ngayLap BETWEEN :date1 AND :date2", HoaDon.class)
                        .setParameter("sdt", sdt)
                        .setParameter("date1", LocalDate.now().minusDays(7))
                        .setParameter("date2", LocalDate.now())
                        .getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonDoiTra> timKiemBangSDT(String sdt) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.createNamedQuery("DonDoiTra.fineByPhone", DonDoiTra.class)
                        .setParameter("sdt", sdt)
                        .getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DonDoiTra> layDonDoiTraTheoNhanVien(String maNhanVien, LocalDate date1, LocalDate date2) {
        try {
            EntityManager em = emf.createEntityManager();
                return em.createNamedQuery("DonDoiTra.findByNhanVien", DonDoiTra.class)
                        .setParameter("maNhanVien", maNhanVien)
                        .setParameter("date1", date1)
                        .setParameter("date2", date2)
                        .getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ChiTietDonDoiTra> layChiTietDonDoiTraTheoMaDonDoiTra(String maDonDoiTra) {
        try {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createQuery("SELECT ct FROM ChiTietDonDoiTra ct WHERE ct.donDoiTra.maDonDoiTra = :maDonDoiTra", ChiTietDonDoiTra.class)
                        .setParameter("maDonDoiTra", maDonDoiTra)
                        .getResultList().stream().toList();
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
