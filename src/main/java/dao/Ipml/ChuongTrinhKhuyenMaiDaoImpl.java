package dao.Ipml;

import dao.ChuongTrinhKhuyenMaiDao;
import entity.ChuongTrinhKhuyenMai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class ChuongTrinhKhuyenMaiDaoImpl implements ChuongTrinhKhuyenMaiDao {
    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoId(String id) {
        return em.find(ChuongTrinhKhuyenMai.class, id);
    }

    @Override
    public ChuongTrinhKhuyenMai  timChuongTrinhKhuyenMaiDangSuDung(boolean isStatus) {
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
        try{
            em.getTransaction().begin();
            Query q = em.createNamedQuery("ChuongTrinhKhuyenMai.capNhatTrangThaiChuongTrinhKhuyenMai", ChuongTrinhKhuyenMai.class);
            q.setParameter("id", ctkm.getMaCTKM());
            if(status == SaleProgramStatus.INACTIVE) {
                q.setParameter("code", false);
            }else if(status == SaleProgramStatus.ACTIVE) {
                q.setParameter("code", true);
            }
            resultValue = q.executeUpdate();
            em.getTransaction().commit();
        }catch(Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return resultValue > 0;
    }

    public enum SaleProgramStatus {
        ACTIVE, INACTIVE
    }
    private EntityManagerFactory emf;
    private EntityManager em;
    private final String PERSISTENCE_UNIT_NAME = "JPA TASK DAO MSSQL";

    public ChuongTrinhKhuyenMaiDaoImpl() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.em = emf.createEntityManager();
    }
}
