package dao.impl;

import dao.NhanVienDao;
import entity.NhanVien;
import entity.TaiKhoan;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import tool.Tool;

import java.util.List;

public class NhanVienDaoImpl implements NhanVienDao {
    private EntityManagerFactory emf;

    public NhanVienDaoImpl() {
        this.emf = Tool.initServer();
    }
    @Override
    public List<NhanVien> layDSNhanVien() {
        List<NhanVien> ds;
        EntityManager em = emf.createEntityManager();
        try {
            ds = em.createNamedQuery("NhanVien.layDSNhanVien", NhanVien.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ds;
    }

    @Override
    public boolean themNhanVien(NhanVien nv, TaiKhoan tk) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(nv);
            em.persist(tk);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatNV(NhanVien nv) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(nv);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean capNhatMatKhauNV(String nv, String matKhau) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            int result = em.createNamedQuery("NhanVien.capNhatMatKhauNV")
                    .setParameter("matKhau", matKhau)
                    .setParameter("maNhanVien", nv)
                    .executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public NhanVien layNhanVienTheoMa(String maNV) {
        EntityManager em = emf.createEntityManager();
        try {
            NhanVien nv = em.createNamedQuery("NhanVien.layNhanVienTheoMa", NhanVien.class).
                    setParameter("maNhanVien", maNV).getSingleResult();
            return nv;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String layMatKhauNhanVienTheoMa(String ma) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(TaiKhoan.class, ma).getMatKhau();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String layEmailNhanVienTheoMa(String maNV) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(NhanVien.class, maNV).getEmail();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVien> locNVTheoChucVu(String chucVu) {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createNamedQuery("NhanVien.locNVTheoChucVu", NhanVien.class)
                    .setParameter("chucVu", chucVu)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVien> locNVTheoGioiTinh(boolean gt) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("NhanVien.locNVTheoGioiTinh", NhanVien.class)
                    .setParameter("gioiTinh", gt)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<NhanVien> locNVTheoTrangThai(String trangThai) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createNamedQuery("NhanVien.locNVTheoTrangThai", NhanVien.class)
                    .setParameter("trangThai", trangThai)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
