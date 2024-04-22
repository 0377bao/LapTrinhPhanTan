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
        //new GUIGiaoHang(new HoaDonBusImpl().TimHoaDonTheoMa("HD01")).setVisible(true);
        new DangNhapGui().setVisible(true);
//            new SanPhamGui().setVisible(true);
//        new SanPhamBusImpl().layDSSachConBan().forEach(System.out::println);
    }
}
