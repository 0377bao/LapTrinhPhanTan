package view;

import bus.*;
import bus.impl.*;
import controller.BanHangController;
import controller.DangNhapController;
//import ui.BanHangGui;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    private static final String URL = "rmi://MSI-MODERN14:1919/";
    public static void main(String[] args) throws  Exception{
        Context context = new InitialContext();
        LocateRegistry.createRegistry(1919);

        // bus
        ChuongTrinhKhuyenMaiBus ctkmBus = new ChuongTrinhKhuyenMaiBusImpl();
        DanhMucBus dmBus = new DanhMucBusImpl();
        DonDoiTraBus ddtBus = new DonDoiTraBusImpl();
        DonGiaoHangBus dghBus = new DonGiaoHangBusImpl();
        HoaDonBus hdBus = new HoaDonBusImpl();
        KhachHangBus khBus = new KhachHangBusImpl();
        NhanVienBus nvBus = new NhanVienBusImpl();
        SanPhamBus spBus = new SanPhamBusImpl();
        TaiKhoanBus tkBus = new TaiKhoanBusImpl();
        ThongKeBus tkeBus = new ThongKeBusImpl();

        context.bind(URL+ "ChuongTrinhKhuyenMaiBus", ctkmBus);
        context.bind(URL+ "DanhMucBus", dmBus);
        context.bind(URL+ "DonDoiTraBus", ddtBus);
        context.bind(URL+ "DonGiaoHangBus", dghBus);
        context.bind(URL+ "HoaDonBus", hdBus);
        context.bind(URL+ "KhachHangBus", khBus);
        context.bind(URL+ "NhanVienBus", nvBus);
        context.bind(URL+ "SanPhamBus", spBus);
        context.bind(URL+ "TaiKhoanBus", tkBus);
        context.bind(URL+ "ThongKeBus", tkeBus);
        System.out.println("Server is running......");

    }
}
