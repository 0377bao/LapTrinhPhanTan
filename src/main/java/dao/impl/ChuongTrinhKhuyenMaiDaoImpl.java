package dao.impl;

import dao.ChuongTrinhKhuyenMaiDao;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import tool.Tool;

import java.util.List;

public class ChuongTrinhKhuyenMaiDaoImpl implements ChuongTrinhKhuyenMaiDao {
    private EntityManagerFactory emf;

    public ChuongTrinhKhuyenMaiDaoImpl() {
        emf = Tool.initServer();
    }

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoId(String id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(ChuongTrinhKhuyenMai.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangSuDung(boolean isStatus) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("ChuongTrinhKhuyenMai. timChuongTrinhKhuyenMaiDangSuDung", ChuongTrinhKhuyenMai.class);
        q.setParameter("status", isStatus);
        return (ChuongTrinhKhuyenMai) q.getSingleResult();
    }

    @Override
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("ChuongTrinhKhuyenMai.layDSChuongTrinhKhuyenMai", ChuongTrinhKhuyenMai.class);
        return q.getResultList();
    }

    @Override
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        EntityManager em = emf.createEntityManager();
        boolean flag = false;
        try {
            em.getTransaction().begin();
            em.persist(ctkm);
            ctkm.getDsMucKhuyenMai().forEach(mucKhuyenMai -> {
                mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
                em.persist(mucKhuyenMai);
            });
            em.getTransaction().commit();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return flag;
    }

    @Override
    public boolean capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, boolean status) {
        EntityManager e = emf.createEntityManager();
        try {
            e.getTransaction().begin();
            e.createNamedQuery("ChuongTrinhKhuyenMai.capNhatTrangThaiChuongTrinhKhuyenMai")
                    .setParameter("id", ctkm.getMaCTKM())
                    .setParameter("code", status)
                    .executeUpdate();
            e.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ctkm);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return false;
    }

    @Override
    public void tatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ctkm.setTrangThai(false);
        em.merge(ctkm);
        em.getTransaction().commit();
    }

    public boolean kiemTraTenChuongTrinhKhuyenMai(String ten) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT count(c) > 0 FROM ChuongTrinhKhuyenMai c WHERE c.tenCTKM = :ten");
            query.setParameter("ten", ten).getSingleResult();
            return (boolean) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // trung ham
    @Override
    public List<MucKhuyenMai> timMucKhuyenMaiTheoMaCTKM(String maCTKM) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT m FROM MucKhuyenMai m JOIN ChuongTrinhKhuyenMai km ON m.chuongTrinhKhuyenMai.id = km.id WHERE m.chuongTrinhKhuyenMai.id = :maCTKM");
            query.setParameter("maCTKM", maCTKM);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        EntityManager em = emf.createEntityManager();
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try{
            em.getTransaction().begin();
            em.persist(mucKhuyenMai);
            em.getTransaction().commit();
            return true;

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        EntityManager em = emf.createEntityManager();
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try {
            em.getTransaction().begin();
            em.remove(em.contains(mucKhuyenMai) ? mucKhuyenMai : em.merge(mucKhuyenMai));
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        EntityManager em = emf.createEntityManager();
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try {
            em.getTransaction().begin();
            em.merge(mucKhuyenMai);
            em.getTransaction().commit();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma) {
        EntityManager e = emf.createEntityManager();
        try {
            Query query = e.createQuery("SELECT m FROM MucKhuyenMai m JOIN ChuongTrinhKhuyenMai km ON m.chuongTrinhKhuyenMai.id = km.id WHERE m.chuongTrinhKhuyenMai.id = :ma");
            query.setParameter("ma", ma);
            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
