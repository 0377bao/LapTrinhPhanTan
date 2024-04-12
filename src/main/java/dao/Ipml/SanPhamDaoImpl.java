package dao.Ipml;

//import bus.BUSDanhMuc;
//import bus.BUSNhaCungCap;
//import connect.ConnectDB;

import dao.SanPhamDao;
import entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import tool.Tool;

import java.util.List;

public class SanPhamDaoImpl implements SanPhamDao {

    private EntityManagerFactory emf;

    public SanPhamDaoImpl() {
        this.emf = Tool.initDriver();
    }


    //	 lấy danh sách sản phẩm
    public List<SanPham> layDSSanPham() {
        List<SanPham> ds;
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SanPham> query = em.createNamedQuery("SanPham.layDanhSachSanPham", SanPham.class);
            ds = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return ds;
    }

    // tìm sách theo mã
    public Sach timSachTheoMa(String maS) {
        Sach sach = null;
        EntityManager em = emf.createEntityManager();
        try {
            sach = em.find(Sach.class, maS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sach;
    }

    // tìm văn phòng phẩm theo mã
    public VanPhongPham timVanPhongPhamTheoMa(String maV) {
        VanPhongPham vpp = null;
        EntityManager em = emf.createEntityManager();
        try {
            vpp = em.find(VanPhongPham.class, maV);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vpp;
    }

    // Thêm sản phẩm
    public boolean themSanPham(SanPham s) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // thêm sách
    public boolean themSach(Sach s) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(s);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // thêm văn phòng phẩm
    public boolean themVanPhongPham(VanPhongPham vpp) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(vpp);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //	// cập nhật sách
    public boolean capNhatSach(Sach sachSua) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(sachSua);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // cập nhật văn phòng phẩm
    public boolean capNhatVanPhongPham(VanPhongPham vppSua) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            em.merge(vppSua);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean capNhatSoLuongTonSanPham(SanPham sp) {
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            int result = em.createNamedQuery("SanPham.capNhatSoLuongTonSanPham")
                    .setParameter("soLuongTon", sp.getSoLuongTon())
                    .setParameter("maSanPham", sp.getMaSanPham())
                    .executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

}
