package dao.impl;

import dao.ChuongTrinhKhuyenMaiDao;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import Tool.Tool;

import java.util.List;

public class ChuongTrinhKhuyenMaiDaoImpl implements ChuongTrinhKhuyenMaiDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public ChuongTrinhKhuyenMaiDaoImpl() {
        emf = Tool.initServer();
        em = emf.createEntityManager();
    }

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoId(String id) {
        return em.find(ChuongTrinhKhuyenMai.class, id);
    }

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangSuDung(boolean isStatus) {
        Query q = em.createNamedQuery("ChuongTrinhKhuyenMai. timChuongTrinhKhuyenMaiDangSuDung", ChuongTrinhKhuyenMai.class);
        q.setParameter("status", isStatus);
        return (ChuongTrinhKhuyenMai) q.getSingleResult();
    }

    @Override
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai() {
        Query q = em.createNamedQuery("ChuongTrinhKhuyenMai.layDSChuongTrinhKhuyenMai", ChuongTrinhKhuyenMai.class);
        return q.getResultList();
    }

    @Override
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
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
    public boolean capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, SaleProgramStatus status) {
        int resultValue = 0;
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("ChuongTrinhKhuyenMai.capNhatTrangThaiChuongTrinhKhuyenMai", ChuongTrinhKhuyenMai.class);
            q.setParameter("id", ctkm.getMaCTKM());
            if (status == SaleProgramStatus.INACTIVE) {
                q.setParameter("code", false);
            } else if (status == SaleProgramStatus.ACTIVE) {
                q.setParameter("code", true);
            }
            resultValue = q.executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return resultValue > 0;
    }

    @Override
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
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
        em.getTransaction().begin();
        ctkm.setTrangThai(false);
        em.merge(ctkm);
        em.getTransaction().commit();
    }

    @Override
    public boolean kiemTraTenChuongTrinhKhuyenMai(String ten) {
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
        try {
            Query query = em.createQuery("SELECT m FROM MucKhuyenMai m JOIN ChuongTrinhKhuyenMai km ON m.chuongTrinhKhuyenMai.id = km.id WHERE m.chuongTrinhKhuyenMai.id = :maCTKM");
            query.setParameter("maCTKM", maCTKM);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try {
            em.getTransaction().begin();
            em.persist(mucKhuyenMai);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean xoaMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
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
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try {
            em.getTransaction().begin();
            em.merge(mucKhuyenMai);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma) {
        Query q = em.createQuery("select m from MucKhuyenMai m join m.chuongTrinhKhuyenMai c where m.chuongTrinhKhuyenMai.id = :ma");
        q.setParameter("ma", ma);
        return (List<MucKhuyenMai>) q.getResultList();
    }

    public enum SaleProgramStatus {
        ACTIVE, INACTIVE
    }

    ;
}
