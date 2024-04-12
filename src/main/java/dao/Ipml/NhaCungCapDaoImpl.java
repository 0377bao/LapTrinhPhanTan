package dao.Ipml;

import dao.NhaCungCapDao;
import entity.NhaCungCap;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tool.Tool;

import java.util.List;

public class NhaCungCapDaoImpl implements NhaCungCapDao {
    private EntityManagerFactory emf;

    public NhaCungCapDaoImpl() {
        emf = Tool.initDriver();
    }

    @Override
    public boolean themNCC(NhaCungCap nhaCungCap) {
        try {
            EntityManager em = emf.createEntityManager();
            try {
                em.getTransaction().begin();
                em.persist(nhaCungCap);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean capNhatNCC(NhaCungCap nhaCungCap) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(nhaCungCap);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
        return false;
    }

    @Override
    public NhaCungCap timNhaCungCapTheoMa(String maNCC) {
        try {
            EntityManager em = emf.createEntityManager();
            try {
                return em.find(NhaCungCap.class, maNCC);
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public NhaCungCap timNCCTheoTen(String tenNCC) {
        try {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createNamedQuery("NhaCungCap.findByName", NhaCungCap.class)
                        .setParameter("tenNhaCungCap", tenNCC).getSingleResult();
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<NhaCungCap> layDSNhaCungCap() {
        try {
            EntityManager em = emf.createEntityManager();
            try {
                return em.createNamedQuery("NhaCungCap.findAll", NhaCungCap.class).getResultList();
            } finally {
                em.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
