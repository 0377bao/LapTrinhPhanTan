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
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("ChuongTrinhKhuyenMai.capNhatTrangThaiChuongTrinhKhuyenMai");
            q.setParameter("id", ctkm.getMaCTKM());
            if (status == SaleProgramStatus.INACTIVE) {
                q.setParameter("code", false);
            } else if (status == SaleProgramStatus.ACTIVE) {
                q.setParameter("code", true);
            }
            q.executeUpdate();
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }

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
    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma) {
        Query q =  em.createQuery("select m from MucKhuyenMai m join m.chuongTrinhKhuyenMai c where m.chuongTrinhKhuyenMai.id = :ma");
        q.setParameter("ma", ma);
        return (List<MucKhuyenMai>) q.getResultList();
    }

    @Override
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
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
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try{
            em.getTransaction().begin();
            em.remove(mucKhuyenMai);
            em.getTransaction().commit();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        ChuongTrinhKhuyenMai ctkm = timChuongTrinhKhuyenMaiTheoId(maCTKM);
        mucKhuyenMai.setChuongTrinhKhuyenMai(ctkm);
        try{
            em.getTransaction().begin();
            em.merge(mucKhuyenMai);
            em.getTransaction().commit();
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public enum SaleProgramStatus {
        ACTIVE, INACTIVE
    };
}
