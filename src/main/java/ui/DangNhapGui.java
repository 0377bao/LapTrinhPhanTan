package ui;

import bus.NhanVienBus;
import bus.TaiKhoanBus;
import bus.impl.NhanVienBusImpl;
import bus.impl.TaiKhoanBusImpl;
import controller.DangNhapController;
import customUI.CustomImage;
import customUI.MyButton;
import entity.NhanVien;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

public class DangNhapGui extends JFrame {

    private JPanel contentPane;
    private JTextField txtTenDangNhap;
    private JTextField txtMatKhau;
    private MyButton btnQuenMatKhau;
    private MyButton btnDangNhap;
    private TaiKhoanBus taiKhoanBus = new TaiKhoanBusImpl();
    private NhanVienBus nhanVienBus = new NhanVienBusImpl();

    public DangNhapGui() {
        this.setTitle("ĐĂNG NHẬP PHẦN MỀM");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 994, 684);
        this.setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setContentPane(contentPane);
        // xét logo
        ImageIcon logoFrame = new ImageIcon(new ImageIcon("src\\main\\java\\image\\logodangnhap\\logo.png").getImage()
                .getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        this.setIconImage(logoFrame.getImage());
        JLabel lblBgrDangNhap = new JLabel();
        lblBgrDangNhap.setBounds(0, 0, 994, 684);
        lblBgrDangNhap.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // lấy hình gốc
        ImageIcon iconLblBgr = new ImageIcon("src\\main\\java\\image\\imagepanel\\hinhnendangnhap.jpg");
        // phóng to hình
        Image scaledImage = ((ImageIcon) iconLblBgr).getImage().getScaledInstance(lblBgrDangNhap.getWidth(),
                lblBgrDangNhap.getHeight(), Image.SCALE_SMOOTH);
        // gán lại hình
        iconLblBgr = new ImageIcon(scaledImage);
        contentPane.setLayout(null);
        lblBgrDangNhap.setIcon(iconLblBgr);
        getContentPane().add(lblBgrDangNhap);

        JPanel pnlContent = new JPanel();
        pnlContent.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        pnlContent.setBounds(160, 143, 674, 398);
        pnlContent.setBackground(new Color(240, 240, 240));
        getContentPane().add(pnlContent);
        pnlContent.setLayout(null);
        // gán hình logo vào
        JLabel lblImageUser = new JLabel(
                CustomImage.taoHinhTronAvt("src\\main\\java\\image\\imagepanel\\hinhnenuserdangnhap.png", 200));
//                new CustumImage().taoHinhTronAvt("src\\image\\imagepanel\\hinhnenuserdangnhap.png", 200));
        lblImageUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImageUser.setBounds(42, 94, 200, 200);
        pnlContent.add(lblImageUser);

        // add độ ưu tiên cho phần tử
        this.setComponentZOrder(pnlContent, 0);

        JLabel lblDangNhap = new JLabel("ĐĂNG NHẬP");
        lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lblDangNhap.setForeground(new Color(10, 110, 227));
        lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDangNhap.setBounds(414, 61, 143, 25);
        pnlContent.add(lblDangNhap);

        JLabel lblTenDangNhap = new JLabel("Tên đăng nhập:");
        lblTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTenDangNhap.setBounds(327, 116, 121, 19);
        pnlContent.add(lblTenDangNhap);

        txtTenDangNhap = new JTextField();
        txtTenDangNhap.setActionCommand("txtTenDangNhap");
        txtTenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtTenDangNhap.setText("NV1");
        txtTenDangNhap.setBounds(327, 145, 317, 30);
        pnlContent.add(txtTenDangNhap);
        txtTenDangNhap.setColumns(10);

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblMatKhau.setBounds(327, 193, 121, 19);
        pnlContent.add(lblMatKhau);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setActionCommand("txtMatKhau");
        txtMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));

        txtMatKhau.setText("Sang1234@");
        txtMatKhau.setColumns(10);
        txtMatKhau.setBounds(327, 222, 317, 30);

        pnlContent.add(txtMatKhau);

        btnQuenMatKhau = new MyButton("Quên mật khẩu?");
        btnQuenMatKhau.setActionCommand("btnQuenMatKhau");
        btnQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnQuenMatKhau.setBounds(508, 273, 136, 25);
        pnlContent.add(btnQuenMatKhau);

        btnDangNhap = new MyButton("Đăng nhập");
        btnDangNhap.setName("button1");
        btnDangNhap.setIcon(new ImageIcon("src\\image\\iconcontrolbtntrangchu\\icondangnhap.png"));
        btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnDangNhap.setBounds(417, 339, 136, 30);
        btnDangNhap.setActionCommand("btnDangNhap");
        pnlContent.add(btnDangNhap);
        this.setComponentZOrder(lblBgrDangNhap, 1);

        // Thêm sự kiện
        ActionListener ac = new DangNhapController(this);
        btnDangNhap.addActionListener(ac);
        btnQuenMatKhau.addActionListener(ac);
        txtTenDangNhap.addActionListener(ac);
        txtMatKhau.addActionListener(ac);
    }

    public void xuLyDangNhap() {
        String taiKhoan = txtTenDangNhap.getText().trim();
        String matKhau = txtMatKhau.getText().trim();

        if (taiKhoan.equals("")) {
            JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống");
        } else if (matKhau.equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
        } else {
            boolean kiemTra = taiKhoanBus.kiemTraMatKhau(taiKhoan, matKhau);
            NhanVien nv = nhanVienBus.layNhanVienTheoMa(taiKhoan);
            if (kiemTra) {
                if (!nv.getChucVu().equals("Quản lý")) {
                    TrangChu trangChu = new TrangChu(nhanVienBus.layNhanVienTheoMa(taiKhoan));
                    trangChu.xuLyTinhNangTheoChucVuCuaNhanVien();
                    trangChu.setVisible(true);
                } else {
                    TrangChu trangChu = new TrangChu(nhanVienBus.layNhanVienTheoMa(taiKhoan));
                    trangChu.setVisible(true);
                }
                this.setVisible(false);

            } else {
                if (nv == null) {
                    JOptionPane.showMessageDialog(this, "Tài khoản nhân viên không tồn tại");
                } else {
                    JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác");
                }
            }
        }
    }

    public void xuLyQuenMatKhau() {
        String taiKhoan = txtTenDangNhap.getText();
        if (taiKhoan.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tài khoản quên mật khẩu");
        } else {
            String email = nhanVienBus.layEmailNhanVienTheoMa(taiKhoan);
            if (email == null) JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại");
            else {
                new QuenMatKhauNhapMaGui(taiKhoan, email).setVisible(true);
                ;
                this.setVisible(false);
            }
        }
    }

    public void xuLyEnterTenTaiKhoan() {
        txtMatKhau.requestFocus();
        txtMatKhau.selectAll();
    }
}