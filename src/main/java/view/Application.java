package view;

import bus.impl.SanPhamBusImpl;
import ui.DangNhapGui;
import ui.SanPhamGui;

public class Application {
    public static void main(String[] args) {
        try {
//            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA Phan Mem Ban Sach MySql");
//            EntityManager em = emf.createEntityManager();
        }catch(Exception e) {
            e.printStackTrace();
        }
        new DangNhapGui().setVisible(true);
    }
}
