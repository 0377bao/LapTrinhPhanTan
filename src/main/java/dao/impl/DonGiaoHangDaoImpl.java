package dao.impl;

import dao.DonGiaoHangDao;
import entity.DonGiaoHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import tool.Tool;

import java.util.List;

public class DonGiaoHangDaoImpl implements DonGiaoHangDao {
    private EntityManagerFactory emf;

    public DonGiaoHangDaoImpl() {
        this.emf = Tool.initServer();
    }

    @Override
    public List<DonGiaoHang> layDSDonHang() {
        List<DonGiaoHang> ds;
        EntityManager em = emf.createEntityManager();
        try {
            ds = em.createNamedQuery("DonGiaoHang.layDSDonGiaoHang", DonGiaoHang.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ds;
    }

    @Override
    public boolean themDonGiaoHang(DonGiaoHang dgh) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(dgh);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatThongTinDonHang(DonGiaoHang dgh) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(dgh);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DonGiaoHang timDonHangTheoMaDonHang(String maCTim) {
        EntityManager em = emf.createEntityManager();
        return em.find(DonGiaoHang.class, maCTim);
    }

    @Override
    public DonGiaoHang timDonHangTheoTenKhachHang(String tenKhachHang) {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("DonGiaoHang.timDonHangTheoTenKhachHang", DonGiaoHang.class)
                .setParameter("tenKhachHang", tenKhachHang)
                . getResultList()
                .stream().findFirst().orElse(null);
    }

    @Override
    public int tongSoHoaDon() {
        EntityManager em = emf.createEntityManager();
        return ((Long) em.createNamedQuery("DonGiaoHang.tongSoHoaDon")
                .getSingleResult()).intValue();
    }

    @Override
    public List<DonGiaoHang> layDSDonHangDangGiao() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("DonGiaoHang.layDSDonHangDangGiao", DonGiaoHang.class).getResultList();
    }

    @Override
    public List<DonGiaoHang> layDSDonHangBiHuy() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("DonGiaoHang.layDSDonHangBiHuy", DonGiaoHang.class).getResultList();
    }

    @Override
    public List<DonGiaoHang> layDSDonHangGiaoThanhCong() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("DonGiaoHang.layDSDonHangGiaoThanhCong", DonGiaoHang.class).getResultList();
    }
}
