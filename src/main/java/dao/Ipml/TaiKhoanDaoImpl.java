package dao.Ipml;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import jakarta.persistence.*;
import tool.Tool;

public class TaiKhoanDaoImpl implements TaiKhoanDao {
    private EntityManagerFactory emf;
    private EntityManager em;

    public TaiKhoanDaoImpl() {
        this.emf = Tool.innitServer();
        this.em = emf.createEntityManager();
    }


    @Override
    public boolean kiemTraMatKhau(String username, String password) {
        boolean flag = false;
        try {
            em.getTransaction().begin();
            Query q = em.createNamedQuery("TaiKhoan.kiemTraMatKhau", TaiKhoan.class);
            q.setParameter("username", username);
            q.setParameter("password", password);
            if(q.getSingleResult() != null) {
                flag = true;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
        return flag;
    }

}
