package dao.impl;

//import bus.BUSDanhMuc;
//import bus.BUSNhaCungCap;
//import connect.ConnectDB;

import dao.SanPhamDao;
import entity.*;
import jakarta.persistence.*;
import Tool.Tool;

import java.util.List;

public class SanPhamDaoImpl implements SanPhamDao {

    private EntityManagerFactory emf;

    public SanPhamDaoImpl() {
        this.emf = Tool.initServer();
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

    @Override
    public Long timMaxSoLuongSanPhamTheoLoai(String loai) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select count(sp) from SanPham sp where sp.maSanPham like :loai");
            q.setParameter("loai", loai);
            return (Long) q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }

    }

    @Override
    public List<SanPham> layDSSanPhamTheoTrangThai(String trangThai, String loai) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select sp from SanPham sp where sp.maSanPham like :loai and sp.trangThai = :trangThai");
            q.setParameter("loai", loai);
            q.setParameter("trangThai", trangThai);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SanPham> layDSSanPhamTheoNCC(String dieuKien, String loai) {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select sp from SanPham sp where sp.maSanPham like :loai and sp.nhaCungCap.id = :dieuKien");
            q.setParameter("loai", loai);
            q.setParameter("dieuKien", dieuKien);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SanPham> layDSSachGanHet() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select sp from Sach sp where sp.soLuongTon < 10 and sp.trangThai = :trangThai");
            q.setParameter("trangThai", "Đang bán");
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SanPham> lay10SachBanChayNhat() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT ct.sanPham, ct.soLuongMua AS n " +
                    "FROM ChiTietHoaDon ct GROUP BY ct.sanPham.id ORDER BY n desc");
            q.setMaxResults(10);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SanPham> layDSSanPhamConBan() {
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select sp from SanPham sp where sp.trangThai = :isStatus");
            q.setParameter("isStatus", true);
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
