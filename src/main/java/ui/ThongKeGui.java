package ui;


import bus.impl.SanPhamBusImpl;
import bus.impl.ThongKeBusImpl;
import controller.ThongKeController;
import customUI.MyTable;
import entity.NhanVien;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import Tool.Tool;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;

public class ThongKeGui extends JFrame {
    private DefaultTableModel modelThongKeChiTietCuaNVTheoTuan;
    private MyTable tbThongKeChiTietCuaNVTheoTuan;
    private MyTable tbDanhSachHoaDonCaNhan_1;
    private DefaultTableModel modelThongKeDoanhThuCacQuy;
    private DefaultTableModel modelHoaDonTheoThang;
    private MyTable tbThongKeDoanhThuCacQuy;
    private DefaultTableModel modelThongKeNhanVien;
    private JTable tbThongKeNhanVien;
    private DefaultTableModel modelThongKeThanhPhanTrongThang;
    private MyTable tbThongKeThanhPhanTrongThang;
    private DefaultTableModel modelDanhSachSanPhamDoiTra;
    private MyTable tbDanhSachSanPhamDoiTra;
    private DefaultTableModel modelDanhSachTop10SachTrongQuy;
    private MyTable tbDanhSachTop10SachTrongQuy;
    private DefaultTableModel modelDanhVPPTop10SachTrongQuy;
    private DefaultTableModel modelDanhSachTop10VPPTrongQuy;
    private MyTable tbDanhSachTop10VPPTrongQuy;
    private JLabel lblSoHoaDon;
    private JLabel lblTDoanhThu;
    private JLabel lblSoLuongSP;
    private JLabel lblSoLuongDDT;
    private ThongKeBusImpl busTK = new ThongKeBusImpl();
    private JComboBox cboThongKeTheoTuan;
    private DefaultComboBoxModel cbmodelThongKeTheoTuan;
    private DefaultCategoryDataset datasetThongKeDoanhThuNVTrongTuan = new DefaultCategoryDataset();
    private DefaultCategoryDataset datasetThongKeDoanhThuNV = new DefaultCategoryDataset();
    private DefaultPieDataset datasetThongKeTheoQuy = new DefaultPieDataset();
    NhanVien nv;
    private JPanel pnlThongKeTrongTuan;
    private ChartPanel cpnlThongKeDoanhThuTheoQuy;
    private JComboBox cbThongKeCuaHangTheoQuy;
    private DefaultComboBoxModel cbmodelThongKeCuaHangTheoQuy;
    private JFreeChart barChart;
    private JComboBox cboThongKeThangCH;
    private JLabel lblSoHoaDonCH;
    private JLabel lblTDoanhThuCH;
    private JLabel lblSoLuongSPCH;
    private JLabel lblSoLuongDDTCH;
    private JComboBox cbThongKeSanPhamTrongQuy;
    private JComboBox cbThongKeTrangThaiSanPham;
    private JComboBox cbThongKeNhanVien;
    private JPanel pnlCaNhan;
    private JTabbedPane tabbedPane;

    public ThongKeGui(NhanVien nv) {
        this.setBackground(new Color(240, 240, 240));
        this.setBounds(250, 0, 1285, 800);
        setLayout(null);
        this.nv = nv;
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tabbedPane.setBounds(0, 0, 1257, 790);
        add(tabbedPane);
        taoTabCaNhan();

        JPanel pnlCuaHang = new JPanel();


        JPanel pnlThongKeDoanhThuQuy = new JPanel();
        pnlThongKeDoanhThuQuy.setBackground(new Color(255, 255, 255));
        pnlThongKeDoanhThuQuy.setBounds(21, 20, 1205, 264);
        pnlCuaHang.add(pnlThongKeDoanhThuQuy);
        pnlThongKeDoanhThuQuy.setLayout(null);

        JLabel lblNewLabel = new JLabel("Thống kê doanh thu các quý theo năm");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 10, 290, 20);
        pnlThongKeDoanhThuQuy.add(lblNewLabel);

        cbThongKeCuaHangTheoQuy = new JComboBox();
        cbmodelThongKeCuaHangTheoQuy = new DefaultComboBoxModel();
        busTK.taoDuLieuComBoBoxThongKeCHTheoQuy(cbmodelThongKeCuaHangTheoQuy);
        cbThongKeCuaHangTheoQuy.setModel(cbmodelThongKeCuaHangTheoQuy);
        cbThongKeCuaHangTheoQuy.setSelectedIndex(1);
        cbThongKeCuaHangTheoQuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbThongKeCuaHangTheoQuy.setBounds(294, 8, 171, 24);
        cbThongKeCuaHangTheoQuy.setActionCommand("cbThongKeCuaHangTheoQuy");
        pnlThongKeDoanhThuQuy.add(cbThongKeCuaHangTheoQuy);

        modelThongKeDoanhThuCacQuy = new DefaultTableModel(
                new Object[]{"Quý", "Tổng hóa đơn", "Tống số lượng SP", "Tổng đơn đổi trả", "Doanh Thu"}, 0);

        tbThongKeDoanhThuCacQuy = new MyTable(modelThongKeDoanhThuCacQuy);
        tbThongKeDoanhThuCacQuy.setShowGrid(false);
        tbThongKeDoanhThuCacQuy.setFont(new Font("Tahoma", Font.PLAIN, 13));
        tbThongKeDoanhThuCacQuy.setRowHeight(40);
        tbThongKeDoanhThuCacQuy.getColumnModel().getColumn(4).setPreferredWidth(150);
        JScrollPane srcThongKeDoanhThuCacQuy = new JScrollPane(tbThongKeDoanhThuCacQuy, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        srcThongKeDoanhThuCacQuy.setBounds(20, 59, 528, 184);
        pnlThongKeDoanhThuQuy.add(srcThongKeDoanhThuCacQuy);

        JFreeChart chart = ChartFactory.createPieChart("Biểu đồ doanh thu theo quý", datasetThongKeTheoQuy, true, true, true);
        cpnlThongKeDoanhThuTheoQuy = new ChartPanel(chart);
        cpnlThongKeDoanhThuTheoQuy.setSize(594, 240);
        cpnlThongKeDoanhThuTheoQuy.setLocation(576, 11);
        pnlThongKeDoanhThuQuy.add(cpnlThongKeDoanhThuTheoQuy);

        // Thêm dữ liệu vào bảng và biểu đồ
        thayDoiDuLieuBangVaBieuDoTheoQuyCuaCH();

        JPanel pnlThongKeCacThanhPhanThangCH = new JPanel();
        pnlThongKeCacThanhPhanThangCH.setBackground(new Color(255, 255, 255));
        pnlThongKeCacThanhPhanThangCH.setBounds(21, 303, 1205, 434);
        pnlCuaHang.add(pnlThongKeCacThanhPhanThangCH);
        pnlThongKeCacThanhPhanThangCH.setLayout(null);

        JPanel pnlSoHoaDonCH = new JPanel();
        pnlSoHoaDonCH.setLayout(null);
        pnlSoHoaDonCH.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlSoHoaDonCH.setBackground(new Color(128, 128, 192));
        pnlSoHoaDonCH.setBounds(20, 109, 255, 115);
        pnlThongKeCacThanhPhanThangCH.add(pnlSoHoaDonCH);

        JLabel lblThongKeSoHoaDonCH = new JLabel("Số hóa đơn");
        lblThongKeSoHoaDonCH.setForeground(Color.WHITE);
        lblThongKeSoHoaDonCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoHoaDonCH.setBounds(25, 20, 120, 25);
        pnlSoHoaDonCH.add(lblThongKeSoHoaDonCH);

        lblSoHoaDonCH = new JLabel();
        lblSoHoaDonCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoHoaDonCH.setForeground(Color.WHITE);
        lblSoHoaDonCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoHoaDonCH.setBounds(68, 60, 104, 40);
        pnlSoHoaDonCH.add(lblSoHoaDonCH);

        JLabel lblDonViSoHoaDonCH = new JLabel("Đơn");
        lblDonViSoHoaDonCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDonCH.setForeground(Color.WHITE);
        lblDonViSoHoaDonCH.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoHoaDonCH.setBounds(171, 60, 55, 40);
        pnlSoHoaDonCH.add(lblDonViSoHoaDonCH);

        JPanel pnlDoanhThuCH = new JPanel();
        pnlDoanhThuCH.setLayout(null);
        pnlDoanhThuCH.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlDoanhThuCH.setBackground(new Color(255, 128, 128));
        pnlDoanhThuCH.setBounds(312, 109, 255, 115);
        pnlThongKeCacThanhPhanThangCH.add(pnlDoanhThuCH);

        JLabel lblThongKeDoanhThuCH = new JLabel("Doanh thu");
        lblThongKeDoanhThuCH.setForeground(Color.WHITE);
        lblThongKeDoanhThuCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeDoanhThuCH.setBounds(25, 20, 120, 25);
        pnlDoanhThuCH.add(lblThongKeDoanhThuCH);

        lblTDoanhThuCH = new JLabel();
        lblTDoanhThuCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTDoanhThuCH.setForeground(Color.WHITE);
        lblTDoanhThuCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTDoanhThuCH.setBounds(10, 60, 159, 40);
        pnlDoanhThuCH.add(lblTDoanhThuCH);

        JLabel lblDonViDoanhThuCH = new JLabel("VNĐ");
        lblDonViDoanhThuCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViDoanhThuCH.setForeground(Color.WHITE);
        lblDonViDoanhThuCH.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViDoanhThuCH.setBounds(168, 60, 55, 40);
        pnlDoanhThuCH.add(lblDonViDoanhThuCH);

        JPanel pnlSoLuongSPCH = new JPanel();
        pnlSoLuongSPCH.setLayout(null);
        pnlSoLuongSPCH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongSPCH.setBackground(new Color(122, 191, 81));
        pnlSoLuongSPCH.setBounds(20, 263, 255, 115);
        pnlThongKeCacThanhPhanThangCH.add(pnlSoLuongSPCH);

        JLabel lblThongKeSoLuongSPCH = new JLabel("Số lượng sản phẩm");
        lblThongKeSoLuongSPCH.setForeground(Color.WHITE);
        lblThongKeSoLuongSPCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoLuongSPCH.setBounds(25, 20, 187, 25);
        pnlSoLuongSPCH.add(lblThongKeSoLuongSPCH);

        lblSoLuongSPCH = new JLabel();
        lblSoLuongSPCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoLuongSPCH.setForeground(Color.WHITE);
        lblSoLuongSPCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoLuongSPCH.setBounds(35, 60, 83, 40);
        pnlSoLuongSPCH.add(lblSoLuongSPCH);

        JLabel lblDonViSoLuongSPCH = new JLabel("Sản phẩm");
        lblDonViSoLuongSPCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoLuongSPCH.setForeground(Color.WHITE);
        lblDonViSoLuongSPCH.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoLuongSPCH.setBounds(131, 60, 100, 40);
        pnlSoLuongSPCH.add(lblDonViSoLuongSPCH);

        JPanel pnlSoLuongDoiTraCH = new JPanel();
        pnlSoLuongDoiTraCH.setLayout(null);
        pnlSoLuongDoiTraCH.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongDoiTraCH.setBackground(new Color(241, 169, 103));
        pnlSoLuongDoiTraCH.setBounds(312, 269, 255, 109);
        pnlThongKeCacThanhPhanThangCH.add(pnlSoLuongDoiTraCH);

        JLabel lblThongKeSoLuongDDTCH = new JLabel("Số lượng đơn đổi trả");
        lblThongKeSoLuongDDTCH.setForeground(Color.WHITE);
        lblThongKeSoLuongDDTCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoLuongDDTCH.setBounds(25, 20, 203, 25);
        pnlSoLuongDoiTraCH.add(lblThongKeSoLuongDDTCH);

        lblSoLuongDDTCH = new JLabel();
        lblSoLuongDDTCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoLuongDDTCH.setForeground(Color.WHITE);
        lblSoLuongDDTCH.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoLuongDDTCH.setBounds(52, 60, 121, 40);
        pnlSoLuongDoiTraCH.add(lblSoLuongDDTCH);

        JLabel lblDonViSoLuongDDTCH = new JLabel("Đơn");
        lblDonViSoLuongDDTCH.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoLuongDDTCH.setForeground(Color.WHITE);
        lblDonViSoLuongDDTCH.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoLuongDDTCH.setBounds(183, 60, 45, 40);
        pnlSoLuongDoiTraCH.add(lblDonViSoLuongDDTCH);

        JLabel lblThongKeThanhPhanCH = new JLabel("Thống kê các thành phần ");
        lblThongKeThanhPhanCH.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblThongKeThanhPhanCH.setBounds(10, 12, 290, 20);
        pnlThongKeCacThanhPhanThangCH.add(lblThongKeThanhPhanCH);

        cboThongKeThangCH = new JComboBox();
        cboThongKeThangCH.setModel(new DefaultComboBoxModel(new String[]{"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
        cboThongKeThangCH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cboThongKeThangCH.setBounds(210, 57, 171, 24);
        cboThongKeThangCH.setActionCommand("cboThongKeThangCH");
        pnlThongKeCacThanhPhanThangCH.add(cboThongKeThangCH);

        JLabel lblTngHan_1 = new JLabel("Tổng hóa đơn trong tháng");
        lblTngHan_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTngHan_1.setBounds(584, 12, 199, 20);
        pnlThongKeCacThanhPhanThangCH.add(lblTngHan_1);


        modelThongKeThanhPhanTrongThang = new DefaultTableModel(
                new Object[]{"Ngày", "Tổng hóa đơn", "Tổng sản phẩm", "Tổng đơn đổi trả", "Doanh Thu"}, 0);
        thayDoiDuLieuBangVaPanelTrongThangCuaCH();


        tbThongKeThanhPhanTrongThang = new MyTable(modelThongKeThanhPhanTrongThang);

        JScrollPane scrThongKeThanhPhanTrongThang = new JScrollPane(tbThongKeThanhPhanTrongThang);
        scrThongKeThanhPhanTrongThang.setBounds(600, 57, 571, 351);
        pnlThongKeCacThanhPhanThangCH.add(scrThongKeThanhPhanTrongThang);


        JPanel pnlThongKeNhanVien = new JPanel();

        JPanel pnlThongKeCacThanhPhanThangCH_1 = new JPanel();
        pnlThongKeCacThanhPhanThangCH_1.setLayout(null);
        pnlThongKeCacThanhPhanThangCH_1.setBackground(Color.WHITE);
        pnlThongKeCacThanhPhanThangCH_1.setBounds(24, 22, 1207, 180);
        pnlThongKeNhanVien.add(pnlThongKeCacThanhPhanThangCH_1);

        JPanel pnlSoHoaDonCH_1 = new JPanel();
        pnlSoHoaDonCH_1.setLayout(null);
        pnlSoHoaDonCH_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlSoHoaDonCH_1.setBackground(new Color(128, 128, 192));
        pnlSoHoaDonCH_1.setBounds(32, 42, 255, 115);
        pnlThongKeCacThanhPhanThangCH_1.add(pnlSoHoaDonCH_1);

        JLabel lblThongKeSoHoaDonCH_1 = new JLabel("Tổng nhân viên");
        lblThongKeSoHoaDonCH_1.setForeground(Color.WHITE);
        lblThongKeSoHoaDonCH_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoHoaDonCH_1.setBounds(25, 20, 155, 25);
        pnlSoHoaDonCH_1.add(lblThongKeSoHoaDonCH_1);

        JLabel lblTongNV = new JLabel();
        lblTongNV.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTongNV.setForeground(Color.WHITE);
        lblTongNV.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblTongNV.setBounds(82, 59, 47, 40);
        pnlSoHoaDonCH_1.add(lblTongNV);

        JLabel lblDonViSoHoaDonCH_1 = new JLabel("Nhân viên");
        lblDonViSoHoaDonCH_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDonCH_1.setForeground(Color.WHITE);
        lblDonViSoHoaDonCH_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDonViSoHoaDonCH_1.setBounds(139, 61, 87, 40);
        pnlSoHoaDonCH_1.add(lblDonViSoHoaDonCH_1);

        JPanel pnlDoanhThuCH_1 = new JPanel();
        pnlDoanhThuCH_1.setLayout(null);
        pnlDoanhThuCH_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlDoanhThuCH_1.setBackground(new Color(255, 128, 128));
        pnlDoanhThuCH_1.setBounds(324, 42, 255, 115);
        pnlThongKeCacThanhPhanThangCH_1.add(pnlDoanhThuCH_1);

        JLabel lblThongKeDoanhThuCH_1 = new JLabel("Nhân viên bán hàng");
        lblThongKeDoanhThuCH_1.setForeground(Color.WHITE);
        lblThongKeDoanhThuCH_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeDoanhThuCH_1.setBounds(25, 20, 220, 25);
        pnlDoanhThuCH_1.add(lblThongKeDoanhThuCH_1);

        JLabel lblNVBanHang = new JLabel();
        lblNVBanHang.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNVBanHang.setForeground(Color.WHITE);
        lblNVBanHang.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNVBanHang.setBounds(82, 59, 47, 40);
        pnlDoanhThuCH_1.add(lblNVBanHang);

        JLabel lblDonViSoHoaDonCH_1_1 = new JLabel("Nhân viên");
        lblDonViSoHoaDonCH_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDonCH_1_1.setForeground(Color.WHITE);
        lblDonViSoHoaDonCH_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDonViSoHoaDonCH_1_1.setBounds(139, 61, 87, 40);
        pnlDoanhThuCH_1.add(lblDonViSoHoaDonCH_1_1);

        JPanel pnlSoLuongSPCH_1 = new JPanel();
        pnlSoLuongSPCH_1.setLayout(null);
        pnlSoLuongSPCH_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongSPCH_1.setBackground(new Color(122, 191, 81));
        pnlSoLuongSPCH_1.setBounds(616, 42, 255, 115);
        pnlThongKeCacThanhPhanThangCH_1.add(pnlSoLuongSPCH_1);

        JLabel lblThongKeDoanhThuCH_1_1 = new JLabel("Nhân viên đã nghỉ");
        lblThongKeDoanhThuCH_1_1.setForeground(Color.WHITE);
        lblThongKeDoanhThuCH_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeDoanhThuCH_1_1.setBounds(25, 20, 230, 25);
        pnlSoLuongSPCH_1.add(lblThongKeDoanhThuCH_1_1);

        JLabel lblNVDaNghi = new JLabel();
        lblNVDaNghi.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNVDaNghi.setForeground(Color.WHITE);
        lblNVDaNghi.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNVDaNghi.setBounds(82, 59, 47, 40);
        pnlSoLuongSPCH_1.add(lblNVDaNghi);

        JLabel lblDonViSoHoaDonCH_1_1_1 = new JLabel("Nhân viên");
        lblDonViSoHoaDonCH_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDonCH_1_1_1.setForeground(Color.WHITE);
        lblDonViSoHoaDonCH_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDonViSoHoaDonCH_1_1_1.setBounds(139, 61, 87, 40);
        pnlSoLuongSPCH_1.add(lblDonViSoHoaDonCH_1_1_1);

        JPanel pnlSoLuongDoiTraCH_1 = new JPanel();
        pnlSoLuongDoiTraCH_1.setLayout(null);
        pnlSoLuongDoiTraCH_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongDoiTraCH_1.setBackground(new Color(241, 169, 103));
        pnlSoLuongDoiTraCH_1.setBounds(906, 42, 255, 109);
        pnlThongKeCacThanhPhanThangCH_1.add(pnlSoLuongDoiTraCH_1);

        JLabel lblThongKeDoanhThuCH_1_2 = new JLabel("Nhân viên quản lý");
        lblThongKeDoanhThuCH_1_2.setForeground(Color.WHITE);
        lblThongKeDoanhThuCH_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeDoanhThuCH_1_2.setBounds(25, 20, 179, 25);
        pnlSoLuongDoiTraCH_1.add(lblThongKeDoanhThuCH_1_2);

        JLabel lblNVQL = new JLabel();
        lblNVQL.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNVQL.setForeground(Color.WHITE);
        lblNVQL.setFont(new Font("Tahoma", Font.BOLD, 23));
        lblNVQL.setBounds(82, 59, 47, 40);
        pnlSoLuongDoiTraCH_1.add(lblNVQL);

        JLabel lblDonViSoHoaDonCH_1_1_2 = new JLabel("Nhân viên");
        lblDonViSoHoaDonCH_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDonCH_1_1_2.setForeground(Color.WHITE);
        lblDonViSoHoaDonCH_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDonViSoHoaDonCH_1_1_2.setBounds(145, 61, 87, 40);
        pnlSoLuongDoiTraCH_1.add(lblDonViSoHoaDonCH_1_1_2);

        JLabel lblThongKeThanhPhanTheoThangCH_1 = new JLabel("Tổng số nhân viên trong cửa hàng");
        lblThongKeThanhPhanTheoThangCH_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblThongKeThanhPhanTheoThangCH_1.setBounds(10, 12, 290, 20);
        pnlThongKeCacThanhPhanThangCH_1.add(lblThongKeThanhPhanTheoThangCH_1);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(24, 217, 665, 521);

        // Hiển thị thông tin thống kê nhân viên
        busTK.hienThiThongTinThongKeNV(lblTongNV, lblNVDaNghi, lblNVBanHang, lblNVQL);


        pnlThongKeNhanVien.add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(711, 217, 520, 521);
        pnlThongKeNhanVien.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Thống kê số liệu của từng nhân viên");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(10, 11, 320, 20);
        panel_1.add(lblNewLabel_1);

        cbThongKeNhanVien = new JComboBox();
        cbThongKeNhanVien.setModel(new DefaultComboBoxModel(new String[]{"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"}));
        cbThongKeNhanVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbThongKeNhanVien.setBounds(140, 42, 142, 22);
        cbThongKeNhanVien.setActionCommand("cbThongKeNhanVien");
        panel_1.add(cbThongKeNhanVien);

        JLabel lblNewLabel_2 = new JLabel("Năm 2024");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(20, 42, 100, 20);
        panel_1.add(lblNewLabel_2);

        modelThongKeNhanVien = new DefaultTableModel(
                new Object[]{"Mã nhân viên", "Tên nhân viên", "Tổng sản phẩm", "Doanh Thu"}, 0);


        tbThongKeNhanVien = new MyTable(modelThongKeNhanVien);
        tbThongKeNhanVien.setRowHeight(31);
        tbThongKeNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbThongKeNhanVien.getColumnModel().getColumn(0).setPreferredWidth(100);
        tbThongKeNhanVien.getColumnModel().getColumn(1).setPreferredWidth(120);
        tbThongKeNhanVien.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbThongKeNhanVien.getColumnModel().getColumn(3).setPreferredWidth(150);


        // hàm đổ dữ liệu vào bảng thống kê nhân viên
        thayDoiDuLieuNhanVien();

        JScrollPane scrollPane = new JScrollPane(tbThongKeNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(20, 101, 469, 398);

        JFreeChart bCThongKeDoanhThuNhanVien = ChartFactory.createBarChart("Biểu đồ doanh thu theo tháng", "Mã nhân viên", "VNĐ", datasetThongKeDoanhThuNV, PlotOrientation.VERTICAL, false, true, true);
        ChartPanel cpnlThongKeDoanhThuNhanVien = new ChartPanel(bCThongKeDoanhThuNhanVien);
        cpnlThongKeDoanhThuNhanVien.setBackground(new Color(255, 255, 255));
        cpnlThongKeDoanhThuNhanVien.setSize(645, 470);
        cpnlThongKeDoanhThuNhanVien.setLocation(10, 30);


        panel.add(cpnlThongKeDoanhThuNhanVien);

        panel_1.add(scrollPane);

        JPanel pnlThongKeSanPham = new JPanel();


        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(255, 255, 255));
        panel_3.setBounds(20, 20, 582, 277);
        pnlThongKeSanPham.add(panel_3);
        panel_3.setLayout(null);

        JPanel panel_6 = new JPanel();
        panel_6.setBackground(new Color(166, 166, 210));
        panel_6.setBounds(21, 28, 539, 224);
        panel_3.add(panel_6);
        panel_6.setLayout(null);

        JLabel lblNewLabel_3 = new JLabel("Tổng số sản phẩm:");
        lblNewLabel_3.setForeground(new Color(255, 255, 255));
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_3.setBounds(28, 23, 200, 30);
        panel_6.add(lblNewLabel_3);

        JPanel panel_7 = new JPanel();
        panel_7.setBackground(new Color(255, 182, 147));
        panel_7.setBounds(23, 78, 225, 118);
        panel_6.add(panel_7);
        panel_7.setLayout(null);

        JLabel lblNewLabel_3_1 = new JLabel("Đang được bán");
        lblNewLabel_3_1.setForeground(Color.WHITE);
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_3_1.setBounds(10, 11, 228, 30);
        panel_7.add(lblNewLabel_3_1);

        JLabel lblSanPhamConBan = new JLabel("65");
        lblSanPhamConBan.setForeground(Color.WHITE);
        lblSanPhamConBan.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSanPhamConBan.setBounds(80, 58, 44, 30);
        lblSanPhamConBan.setText(new SanPhamBusImpl().kiemTraTrangThaiSach("1").size() + new SanPhamBusImpl().kiemTraTrangThaiVPP("1").size() + "");
        panel_7.add(lblSanPhamConBan);

        JLabel lblNewLabel_4_1_1 = new JLabel("sản phẩm");
        lblNewLabel_4_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_4_1_1.setBounds(118, 58, 120, 30);
        panel_7.add(lblNewLabel_4_1_1);

        JPanel panel_8 = new JPanel();
        panel_8.setBackground(new Color(255, 172, 214));
        panel_8.setBounds(267, 78, 248, 118);
        panel_6.add(panel_8);
        panel_8.setLayout(null);

        JLabel lblNewLabel_3_1_1 = new JLabel("Không còn bán");
        lblNewLabel_3_1_1.setForeground(Color.WHITE);
        lblNewLabel_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel_3_1_1.setBounds(10, 11, 200, 30);
        panel_8.add(lblNewLabel_3_1_1);

        JLabel lblSanPhamKhongConBan = new JLabel("65");
        lblSanPhamKhongConBan.setForeground(Color.WHITE);
        lblSanPhamKhongConBan.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblSanPhamKhongConBan.setBounds(91, 58, 44, 30);
        lblSanPhamKhongConBan.setText(new SanPhamBusImpl().kiemTraTrangThaiSach("0").size() + new SanPhamBusImpl().kiemTraTrangThaiVPP("0").size() + "");
        panel_8.add(lblSanPhamKhongConBan);

        JLabel lblNewLabel_4_1_1_2 = new JLabel("sản phẩm");
        lblNewLabel_4_1_1_2.setForeground(Color.WHITE);
        lblNewLabel_4_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_4_1_1_2.setBounds(127, 58, 121, 30);
        panel_8.add(lblNewLabel_4_1_1_2);

        JLabel lblTongSanPham = new JLabel();
        lblTongSanPham.setForeground(new Color(255, 255, 255));
        lblTongSanPham.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTongSanPham.setBounds(216, 22, 60, 30);
        lblTongSanPham.setText(new SanPhamBusImpl().layDSSanPham().size() + "");

        panel_6.add(lblTongSanPham);

        JLabel lblNewLabel_4_1_1_1 = new JLabel("sản phẩm");
        lblNewLabel_4_1_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_4_1_1_1.setBounds(266, 22, 154, 30);
        panel_6.add(lblNewLabel_4_1_1_1);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(new Color(255, 255, 255));
        panel_4.setBounds(20, 320, 1207, 430);
        pnlThongKeSanPham.add(panel_4);
        panel_4.setLayout(null);

        cbThongKeSanPhamTrongQuy = new JComboBox();
        cbThongKeSanPhamTrongQuy.setModel(new DefaultComboBoxModel(new String[]{"Quý 1", "Quý 2", "Quý 3", "Quý 4"}));
        cbThongKeSanPhamTrongQuy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbThongKeSanPhamTrongQuy.setBounds(281, 44, 145, 22);
        cbThongKeSanPhamTrongQuy.setActionCommand("cbThongKeSanPhamTrongQuy");
        panel_4.add(cbThongKeSanPhamTrongQuy);

        JLabel lblNewLabel_5 = new JLabel("Thống kê top 10 sản phẩm ");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_5.setBounds(10, 11, 200, 20);
        panel_4.add(lblNewLabel_5);

        cbThongKeTrangThaiSanPham = new JComboBox();
        cbThongKeTrangThaiSanPham.setModel(new DefaultComboBoxModel(new String[]{"Bán chạy nhất", "Bán ít nhất"}));
        cbThongKeTrangThaiSanPham.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbThongKeTrangThaiSanPham.setBounds(61, 44, 145, 22);
        cbThongKeTrangThaiSanPham.setActionCommand("cbThongKeTrangThaiSanPham");
        panel_4.add(cbThongKeTrangThaiSanPham);

        JLabel lblNewLabel_6 = new JLabel("Trong");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_6.setBounds(219, 45, 49, 20);
        panel_4.add(lblNewLabel_6);

        modelDanhSachTop10SachTrongQuy = new DefaultTableModel(
                new Object[]{"Mã sản phẩm", "Tên sản phẩm", "số lượng bán", "giá bán", "% lợi nhuận"}, 0);


        tbDanhSachTop10SachTrongQuy = new MyTable(modelDanhSachTop10SachTrongQuy);

        JScrollPane scrollPane_1 = new JScrollPane(tbDanhSachTop10SachTrongQuy);
        scrollPane_1.setBounds(30, 98, 545, 307);
        panel_4.add(scrollPane_1);

        modelDanhSachTop10VPPTrongQuy = new DefaultTableModel(
                new Object[]{"Mã sản phẩm", "Tên sản phẩm", "số lượng bán", "giá bán", "% lợi nhuận"}, 0);


        tbDanhSachTop10VPPTrongQuy = new MyTable(modelDanhSachTop10VPPTrongQuy);

        JScrollPane scrollPane_2 = new JScrollPane(tbDanhSachTop10VPPTrongQuy);
        scrollPane_2.setBounds(605, 98, 573, 307);
        panel_4.add(scrollPane_2);

        JLabel lblNewLabel_7 = new JLabel("Sách");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_7.setBounds(33, 77, 49, 20);
        panel_4.add(lblNewLabel_7);

        JLabel lblNewLabel_7_1 = new JLabel("Văn phòng phẩm");
        lblNewLabel_7_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_7_1.setBounds(605, 76, 130, 20);
        panel_4.add(lblNewLabel_7_1);

        JPanel panel_9 = new JPanel();
        panel_9.setBackground(new Color(255, 255, 255));
        panel_9.setBounds(629, 20, 598, 277);
        pnlThongKeSanPham.add(panel_9);
        panel_9.setLayout(null);

        modelDanhSachSanPhamDoiTra = new DefaultTableModel(
                new Object[]{"Mã sản phẩm", "Tên sản phẩm", "giá bán", "số lượng đổi trả"}, 0);

        tbDanhSachSanPhamDoiTra = new MyTable(modelDanhSachSanPhamDoiTra);

        JScrollPane srcDanhSachSanPhamBiDoiTra = new JScrollPane(tbDanhSachSanPhamDoiTra);
        srcDanhSachSanPhamBiDoiTra.setBounds(23, 36, 547, 216);
        panel_9.add(srcDanhSachSanPhamBiDoiTra);

        JLabel lblNewLabel_8 = new JLabel("Danh sách sản phẩm bị đổi trả trong năm");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_8.setBounds(10, 11, 318, 20);
        panel_9.add(lblNewLabel_8);

		thayDoiDuLieuBangThongKeSanPham();
		thongKeSanPhamBiDoiTra();

        //thêm sự kiện
        cbThongKeCuaHangTheoQuy.addActionListener(new ThongKeController(this));
        cboThongKeThangCH.addActionListener(new ThongKeController(this));
        cbThongKeSanPhamTrongQuy.addActionListener(new ThongKeController(this));
        cbThongKeTrangThaiSanPham.addActionListener(new ThongKeController(this));

        cbThongKeNhanVien.addActionListener(new ThongKeController(this));
        tbThongKeNhanVien.addMouseListener(new ThongKeController(this));

        if (nv.getChucVu().equals("Quản lý")) {
            tabbedPane.addTab("Sản phẩm", null, pnlThongKeSanPham, null);
            pnlThongKeSanPham.setLayout(null);
            tabbedPane.addTab("Nhân viên", null, pnlThongKeNhanVien, null);
            pnlThongKeNhanVien.setLayout(null);
            tabbedPane.addTab("Cửa hàng", null, pnlCuaHang, null);
            pnlCuaHang.setLayout(null);
        }

    }

    //Hàm tạo dữ liệu cho biểu đồ thống kê nhân viên
    private CategoryDataset taoBieuDoDoanhThuNhanVien() {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        dataSet.addValue(4000000, "VNĐ", "NV1");
        dataSet.addValue(1000000, "VNĐ", "NV2");
        dataSet.addValue(3000000, "VNĐ", "NV3");
        dataSet.addValue(7000000, "VNĐ", "NV4");
        dataSet.addValue(2000000, "VNĐ", "NV5");
        dataSet.addValue(9000000, "VNĐ", "NV6");
        dataSet.addValue(3000000, "VNĐ", "NV7");
        dataSet.addValue(5000000, "VNĐ", "NV8");
        dataSet.addValue(9000000, "VNĐ", "NV9");
        dataSet.addValue(4000000, "VNĐ", "NV10");

        return dataSet;
    }

    // Hàm thêm dữ liệu bảng và biểu đồ dữ liệu thống kê theo tuần của nhân viên
    public void thayDoiDuLieuBangVaBieuDoTheoComBoBoxTKTheoTuan() {
        modelThongKeChiTietCuaNVTheoTuan.setRowCount(0);
        busTK.layDuLieuThongKeChiTietCuaNVTheoTuan(modelThongKeChiTietCuaNVTheoTuan, cbmodelThongKeTheoTuan, nv);
        datasetThongKeDoanhThuNVTrongTuan.clear();
        busTK.layDuLieuThongKeChiTietCuaNVTheoTuanVaoBieuDo(cbmodelThongKeTheoTuan, nv, datasetThongKeDoanhThuNVTrongTuan);

    }

    // Hàm thêm dữ liệu bảng và biểu đồ dữ liệu thống kê theo quý của cửa hàng
    public void thayDoiDuLieuBangVaBieuDoTheoQuyCuaCH() {
        modelThongKeDoanhThuCacQuy.setRowCount(0);
        datasetThongKeTheoQuy.clear();
        busTK.thongKeThongTinCuaHangTheoQuy(cbmodelThongKeCuaHangTheoQuy.getSelectedItem().toString(), modelThongKeDoanhThuCacQuy, datasetThongKeTheoQuy);
        ;
    }

    // Hàm thêm dữ liệu vào bảng và panel thống kê trong tháng của cửa hàng
    public void thayDoiDuLieuBangVaPanelTrongThangCuaCH() {
        modelThongKeThanhPhanTrongThang.setRowCount(0);
        busTK.thongKeCuaHangTrongThang(cboThongKeThangCH.getSelectedItem().toString(), lblSoHoaDonCH, lblTDoanhThuCH, lblSoLuongSPCH, lblSoLuongDDTCH, modelThongKeThanhPhanTrongThang);
    }

    //Hàm thêm dữ liệu vào bảng thống kê sản phẩm
    public void thayDoiDuLieuBangThongKeSanPham() {
        busTK.ThongKeSanPham(cbThongKeTrangThaiSanPham.getSelectedItem().toString(), cbThongKeSanPhamTrongQuy.getSelectedItem().toString(), modelDanhSachTop10SachTrongQuy, modelDanhSachTop10VPPTrongQuy);
    }

    //Hàm thống kê sản phẩm bị đổi trả
    public void thongKeSanPhamBiDoiTra() {
        busTK.lay10SPBiDoiTraNhieuNhat(modelDanhSachSanPhamDoiTra, LocalDate.of(LocalDate.now().getYear(), 1, 1), LocalDate.of(LocalDate.now().getYear(), 12, 31));
    }

    // Hàm thống kê số liệu nhân viên
    public void thayDoiDuLieuNhanVien() {
        modelThongKeNhanVien.setRowCount(0);
        datasetThongKeDoanhThuNV.clear();
        busTK.hienSoLieuCuaNhanVienTrongThang(cbThongKeNhanVien.getSelectedItem().toString(), modelThongKeNhanVien, datasetThongKeDoanhThuNV);
    }

    // Tạo tab thống kê cá nhân cập nhập mỗi lần ấn vào
    public void taoTabCaNhan() {
        pnlCaNhan = new JPanel();
        tabbedPane.insertTab("Cá nhân", null, pnlCaNhan, null, 0);
        pnlCaNhan.setLayout(null);

        JPanel pnlThongKeTrongNgay = new JPanel();
        pnlThongKeTrongNgay.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongKeTrongNgay.setLayout(null);
        pnlThongKeTrongNgay.setBackground(Color.WHITE);
        pnlThongKeTrongNgay.setBounds(15, 11, 1214, 205);
        pnlCaNhan.add(pnlThongKeTrongNgay);

        JPanel pnlSoHoaDon = new JPanel();
        pnlSoHoaDon.setBackground(new Color(128, 128, 192));
        pnlSoHoaDon.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlSoHoaDon.setLayout(null);
        pnlSoHoaDon.setBounds(42, 45, 260, 140);
        pnlThongKeTrongNgay.add(pnlSoHoaDon);

        JLabel lblThongKeSoHoaDon = new JLabel("Số hóa đơn");
        lblThongKeSoHoaDon.setForeground(Color.WHITE);
        lblThongKeSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoHoaDon.setBounds(25, 25, 120, 25);
        pnlSoHoaDon.add(lblThongKeSoHoaDon);

        lblSoHoaDon = new JLabel();
        lblSoHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoHoaDon.setForeground(Color.WHITE);
        lblSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoHoaDon.setBounds(100, 77, 72, 40);
        lblSoHoaDon.setText(busTK.dsHDTheoNV(nv, LocalDate.now(), LocalDate.now()).size() + "");
        pnlSoHoaDon.add(lblSoHoaDon);

        JLabel lblDonViSoHoaDon = new JLabel("Đơn");
        lblDonViSoHoaDon.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoHoaDon.setForeground(Color.WHITE);
        lblDonViSoHoaDon.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoHoaDon.setBounds(171, 77, 55, 40);
        pnlSoHoaDon.add(lblDonViSoHoaDon);

        JPanel pnlDoanhThu = new JPanel();
        pnlDoanhThu.setBackground(new Color(255, 128, 128));
        pnlDoanhThu.setBorder(new BevelBorder(BevelBorder.RAISED, null, new Color(192, 192, 192), null, null));
        pnlDoanhThu.setLayout(null);
        pnlDoanhThu.setBounds(330, 45, 260, 140);
        pnlThongKeTrongNgay.add(pnlDoanhThu);

        JLabel lblThongKeDoanhThu = new JLabel("Doanh thu");
        lblThongKeDoanhThu.setBounds(25, 25, 120, 25);
        pnlDoanhThu.add(lblThongKeDoanhThu);
        lblThongKeDoanhThu.setForeground(new Color(255, 255, 255));
        lblThongKeDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));

        lblTDoanhThu = new JLabel();
        lblTDoanhThu.setBounds(10, 76, 152, 40);
        pnlDoanhThu.add(lblTDoanhThu);
        lblTDoanhThu.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTDoanhThu.setForeground(new Color(255, 255, 255));
        lblTDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTDoanhThu.setText(Tool.dinhDangTien(busTK.tongDoanhThu(nv, LocalDate.now(), LocalDate.now())).replace("VND", "") + "");

        JLabel lblDonViDoanhThu = new JLabel("VNĐ");
        lblDonViDoanhThu.setBounds(168, 76, 55, 40);
        pnlDoanhThu.add(lblDonViDoanhThu);
        lblDonViDoanhThu.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViDoanhThu.setForeground(Color.WHITE);
        lblDonViDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 20));

        JPanel pnlSoLuongSP = new JPanel();
        pnlSoLuongSP.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongSP.setBackground(new Color(122, 191, 81));
        pnlSoLuongSP.setLayout(null);
        pnlSoLuongSP.setBounds(622, 45, 260, 140);
        pnlThongKeTrongNgay.add(pnlSoLuongSP);

        JLabel lblThongKeSoLuongSP = new JLabel("Số lượng sản phẩm");
        lblThongKeSoLuongSP.setForeground(Color.WHITE);
        lblThongKeSoLuongSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoLuongSP.setBounds(25, 25, 187, 25);
        pnlSoLuongSP.add(lblThongKeSoLuongSP);

        lblSoLuongSP = new JLabel();
        lblSoLuongSP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoLuongSP.setForeground(Color.WHITE);
        lblSoLuongSP.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoLuongSP.setBounds(46, 74, 72, 40);
        lblSoLuongSP.setText(busTK.tongSoSanPham(nv, LocalDate.now(), LocalDate.now()) + "");
        pnlSoLuongSP.add(lblSoLuongSP);

        JLabel lblDonViSoLuongSP = new JLabel("Sản phẩm");
        lblDonViSoLuongSP.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoLuongSP.setForeground(Color.WHITE);
        lblDonViSoLuongSP.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoLuongSP.setBounds(131, 75, 100, 40);
        pnlSoLuongSP.add(lblDonViSoLuongSP);

        JPanel pnlSoLuongDoiTra = new JPanel();
        pnlSoLuongDoiTra.setBorder(new BevelBorder(BevelBorder.LOWERED, null, new Color(192, 192, 192), null, null));
        pnlSoLuongDoiTra.setBackground(new Color(241, 169, 103));
        pnlSoLuongDoiTra.setLayout(null);
        pnlSoLuongDoiTra.setBounds(912, 45, 260, 140);
        pnlThongKeTrongNgay.add(pnlSoLuongDoiTra);

        JLabel lblThongKeSoLuongDDT = new JLabel("Số lượng đơn đổi trả");
        lblThongKeSoLuongDDT.setForeground(Color.WHITE);
        lblThongKeSoLuongDDT.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblThongKeSoLuongDDT.setBounds(25, 25, 203, 25);
        pnlSoLuongDoiTra.add(lblThongKeSoLuongDDT);

        lblSoLuongDDT = new JLabel();
        lblSoLuongDDT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSoLuongDDT.setForeground(Color.WHITE);
        lblSoLuongDDT.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblSoLuongDDT.setBounds(101, 72, 72, 40);
        lblSoLuongDDT.setText(busTK.dsDDTTheoNV(nv, LocalDate.now(), LocalDate.now()).size() + "");
        pnlSoLuongDoiTra.add(lblSoLuongDDT);

        JLabel lblDonViSoLuongDDT = new JLabel("Đơn");
        lblDonViSoLuongDDT.setHorizontalAlignment(SwingConstants.RIGHT);
        lblDonViSoLuongDDT.setForeground(Color.WHITE);
        lblDonViSoLuongDDT.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDonViSoLuongDDT.setBounds(183, 73, 45, 40);
        pnlSoLuongDoiTra.add(lblDonViSoLuongDDT);

        JLabel lblThongKeTrongNgay = new JLabel("Thống kê trong ngày");
        lblThongKeTrongNgay.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblThongKeTrongNgay.setBounds(15, 15, 200, 20);
        pnlThongKeTrongNgay.add(lblThongKeTrongNgay);

        pnlThongKeTrongTuan = new JPanel();
        pnlThongKeTrongTuan.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongKeTrongTuan.setLayout(null);
        pnlThongKeTrongTuan.setBackground(Color.WHITE);
        pnlThongKeTrongTuan.setBounds(15, 235, 609, 515);
        pnlCaNhan.add(pnlThongKeTrongTuan);

        JPanel pnlBieuDoThongKeCaNhan = new JPanel();
        pnlBieuDoThongKeCaNhan.setLayout(null);
        pnlBieuDoThongKeCaNhan.setBounds(23, 55, 569, 439);


        cboThongKeTheoTuan = new JComboBox();
        cbmodelThongKeTheoTuan = new DefaultComboBoxModel(new String[]{});
        cboThongKeTheoTuan.setModel(cbmodelThongKeTheoTuan);
        cboThongKeTheoTuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cboThongKeTheoTuan.setBounds(364, 13, 228, 24);
        cboThongKeTheoTuan.setActionCommand("cboThongKeTheoTuan");
        busTK.taoDuLieuComBoBoxTKCuaNV(cbmodelThongKeTheoTuan);
        pnlThongKeTrongTuan.add(cboThongKeTheoTuan);

        busTK.layDuLieuThongKeChiTietCuaNVTheoTuanVaoBieuDo(cbmodelThongKeTheoTuan, nv, datasetThongKeDoanhThuNVTrongTuan);
        barChart = ChartFactory.createBarChart("Biểu đồ doanh thu theo tuần", "Thứ", "VNĐ", datasetThongKeDoanhThuNVTrongTuan, PlotOrientation.VERTICAL, false, false, true);
        ChartPanel cpnlDoanhThuTheoTuan = new ChartPanel(barChart);

        cpnlDoanhThuTheoTuan.setSize(549, 419);
        cpnlDoanhThuTheoTuan.setLocation(10, 10);
        cpnlDoanhThuTheoTuan.setPreferredSize(new Dimension(500, 400));
        pnlBieuDoThongKeCaNhan.add(cpnlDoanhThuTheoTuan);
        cpnlDoanhThuTheoTuan.setLayout(null);
        pnlThongKeTrongTuan.add(pnlBieuDoThongKeCaNhan);


        JLabel lblThongKeTrongNgay_1 = new JLabel("Thống kê trong tuần");
        lblThongKeTrongNgay_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblThongKeTrongNgay_1.setBounds(23, 15, 200, 20);
        pnlThongKeTrongTuan.add(lblThongKeTrongNgay_1);

        JPanel pnlThongKeDanhSachHoaDon = new JPanel();
        pnlThongKeDanhSachHoaDon.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlThongKeDanhSachHoaDon.setLayout(null);
        pnlThongKeDanhSachHoaDon.setBackground(Color.WHITE);
        pnlThongKeDanhSachHoaDon.setBounds(645, 235, 584, 515);
        pnlCaNhan.add(pnlThongKeDanhSachHoaDon);

        modelThongKeChiTietCuaNVTheoTuan = new DefaultTableModel(
                new Object[]{"Ngày trong tuần", "Số hóa đơn", "Doanh thu", "Số lượng SP", "Số đơn đổi trả"}, 0);
        busTK.layDuLieuThongKeChiTietCuaNVTheoTuan(modelThongKeChiTietCuaNVTheoTuan, cbmodelThongKeTheoTuan, nv);
        tbThongKeChiTietCuaNVTheoTuan = new MyTable(modelThongKeChiTietCuaNVTheoTuan);
        tbThongKeChiTietCuaNVTheoTuan.getColumnModel().getColumn(2).setPreferredWidth(120);
        tbThongKeChiTietCuaNVTheoTuan.setRowHeight(57);

        JScrollPane srcBangThongKeChiTietCuaNVTheoTuan = new JScrollPane(tbThongKeChiTietCuaNVTheoTuan, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        srcBangThongKeChiTietCuaNVTheoTuan.setBounds(25, 60, 526, 433);
        pnlThongKeDanhSachHoaDon.add(srcBangThongKeChiTietCuaNVTheoTuan);

        JLabel lblDanhSachHoaDonNhanVien = new JLabel("Bảng thống kê chi tiết của nhân viên theo tuần");
        lblDanhSachHoaDonNhanVien.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDanhSachHoaDonNhanVien.setBounds(25, 26, 351, 20);
        pnlThongKeDanhSachHoaDon.add(lblDanhSachHoaDonNhanVien);
        cboThongKeTheoTuan.addActionListener(new ThongKeController(this));
    }

    // Hàm xóa tab cá nhân trước đó
    public void xoaTabCaNhan() {
        tabbedPane.remove(0);
    }

}
