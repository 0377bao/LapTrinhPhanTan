package dao.Ipml;


import dao.DaoHoaDon;
import entity.HoaDon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class HoaDonDaoImpl implements DaoHoaDon {
    private EntityManagerFactory emf;

    public HoaDonDaoImpl() {
        emf = tool.unitServer();
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

    public boolean themHoaDon(HoaDon hd){
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
    public int tongSoHoaDon(){
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
    public List<HoaDon> layHoaDonTuNgayXDenNgayY(LocalDate ngayX, LocalDate ngayY){
        EntityManager em = emf.createEntityManager();
        try{
            return em.createNamedQuery("HoaDon.findByNgayLap", HoaDon.class).setParameter("ngayBD",ngayX).setParameter("ngayKT",ngayY).getResultList().stream().toList();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
        return null;
    }


}
