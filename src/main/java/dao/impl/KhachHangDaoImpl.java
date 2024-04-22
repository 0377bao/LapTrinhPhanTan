package dao.impl;

import dao.KhachHangDao;
import entity.HoaDon;
import entity.KhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import Tool.Tool;

import java.util.List;

public class KhachHangDaoImpl implements KhachHangDao {
    EntityManagerFactory emf;
    public KhachHangDaoImpl(){
        emf = Tool.initServer();
    }
    public List<KhachHang> LayDSKhachHang(){
        EntityManager em = emf.createEntityManager();
        try{
            return em.createNamedQuery("Customer.findAll",KhachHang.class)
                    .getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public boolean themKhachHang(KhachHang kh){
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(kh);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatThongTinKhachHang(KhachHang kh){
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(kh);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean capNhatDiemTichLuyKhachHang(KhachHang kh) {
        EntityManager em = emf.createEntityManager();
        try{
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.merge(kh);
            tx.commit();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public KhachHang timKhachHangTheoMa(String maKH){
        EntityManager em = emf.createEntityManager();
        KhachHang kh = null;
        try{
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            kh = em.find(KhachHang.class,maKH);
        }catch (Exception e){
            e.printStackTrace();
        }return kh;
    }
    public KhachHang timKhachHangTheoSDT(String sdt){
        EntityManager em = emf.createEntityManager();
        KhachHang kh = null;
        try {
            return em.createNamedQuery("Customer.findBySdt",KhachHang.class)
                    .setParameter("sdt",sdt)
                    .getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return kh;
    }
    // neu ta muon lay all thi soGD = 0
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKH, int soGD){
        EntityManager em = emf.createEntityManager();
        try{
            Query query = em.createNamedQuery("Customer.findTransactionHistoryOfCustomer",HoaDon.class)
                    .setParameter("maKH",maKH);
            if(soGD!=0) {
                query.setMaxResults(soGD);
            }
            return query.getResultList().stream().toList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public int getMaKhachHangMax(){
        int ma=0;
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(" SELECT CAST(SUBSTRING(maKhachHang, 3, LEN(maKhachHang) - 2) as int) AS maKhachHang  \n" +
                    "FROM KhachHang kh \n" +
                    "ORDER BY CAST(SUBSTRING(maKhachHang, 3, LEN(maKhachHang) - 2) as int) DESC");
            query.setMaxResults(1);
            ma = (Integer)query.getSingleResult();
            return ma;
        }catch (Exception e){
            e.printStackTrace();
        }
        return ma;
    }

    @Override
    public List<KhachHang> locKhachHang(String tenTim, String sdtTim) {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createNamedQuery("Customer.filterCustomerByNameAndSdt",KhachHang.class)
                    .setParameter("tenKhachHang", "%" + tenTim.toLowerCase() + "%")
                    .setParameter("sdt", "%" + sdtTim + "%")
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<KhachHang> layDsKhachHangSapXepTheoTongTien() {
        EntityManager em = emf.createEntityManager();
        try{
            return em.createNamedQuery("Customer.sortCustomerByTotalPurchase",KhachHang.class)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
