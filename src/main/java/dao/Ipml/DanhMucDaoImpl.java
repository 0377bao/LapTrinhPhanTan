package dao.Ipml;

import dao.DanhMucDao;
import entity.DanhMuc;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import tool.Tool;

import java.util.List;

public class DanhMucDaoImpl implements DanhMucDao {
    private EntityManagerFactory emf;

    public DanhMucDaoImpl() {
        emf = Tool.innitServer();
    }

    @Override
    public boolean taoDanhMuc(DanhMuc danhMuc) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(danhMuc);
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
    public DanhMuc timDanhMucTheoMa(String maDanhMuc) {
        try {
            EntityManager em = emf.createEntityManager();
            return em.find(DanhMuc.class, maDanhMuc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DanhMuc> layDSDanhMuc() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("DanhMuc.findAll", DanhMuc.class).getResultList().stream().toList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public DanhMuc timDanhMucTheoTen(String tenDanhMuc) {
        try {
            EntityManager em = emf.createEntityManager();
            return em.createNamedQuery("DanhMuc.findByName", DanhMuc.class)
                    .setParameter("tenDanhMuc", tenDanhMuc)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
