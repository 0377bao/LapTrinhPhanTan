package ui;

import bus.impl.NhanVienBusImpl;
import controller.QuenMatKhauNhapMatKhauMoiController;
import customUI.CustomImage;
import customUI.MyButton;

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
import java.rmi.RemoteException;

public class QuenMatKhauNhapMatKhauMoiGui extends JFrame {
    private JPanel contentPane;
    private JTextField txtMatKhau;
    private String maNhanVien;
    private JTextField txtNhapLaiMatKhau;
    private MyButton btnXacNhan;
    private NhanVienBusImpl nhanVienBus = new NhanVienBusImpl();
    private QuenMatKhauNhapMatKhauMoiController ctrNhapMatKhau = new QuenMatKhauNhapMatKhauMoiController(this);

    public QuenMatKhauNhapMatKhauMoiGui(String maNhanVien) throws RemoteException {
        this.maNhanVien = maNhanVien;
        this.setTitle("ĐĂNG NHẬP PHẦN MỀM");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 994, 684);
        this.setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setContentPane(contentPane);
        //xét logo
        ImageIcon logoFrame = new ImageIcon(new ImageIcon("src\\main\\java\\image\\logodangnhap\\logo.png").getImage()
                .getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        this.setIconImage(logoFrame.getImage());
        JLabel lblBgrDangNhap = new JLabel();
        lblBgrDangNhap.setBounds(0, 0, 994, 684);
        lblBgrDangNhap.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
//		lấy hình gốc
        ImageIcon iconLblBgr = new ImageIcon("src\\main\\java\\image\\imagepanel\\hinhnendangnhap.jpg");
//		phóng to hình
        Image scaledImage = ((ImageIcon) iconLblBgr).getImage().getScaledInstance(lblBgrDangNhap.getWidth(), lblBgrDangNhap.getHeight(), Image.SCALE_SMOOTH);
//		gán lại hình
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
//		gán hình logo vào
        JLabel lblImageUser = new JLabel(new CustomImage().taoHinhTronAvt("src\\main\\java\\image\\imagepanel\\hinhnenuserdangnhap.png", 200));
        lblImageUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImageUser.setBounds(42, 94, 200, 200);
        pnlContent.add(lblImageUser);

//		add độ ưu tiên cho phần tử
        this.setComponentZOrder(pnlContent, 0);

        JLabel lblQuenMatKhau = new JLabel("QUÊN MẬT KHẨU");
        lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
        lblQuenMatKhau.setForeground(new Color(10, 110, 227));
        lblQuenMatKhau.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblQuenMatKhau.setBounds(391, 61, 189, 25);
        pnlContent.add(lblQuenMatKhau);

        JLabel lblNhapMK = new JLabel("Nhập mật khẩu mới:");
        lblNhapMK.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNhapMK.setBounds(327, 116, 136, 19);
        pnlContent.add(lblNhapMK);

        txtMatKhau = new JPasswordField();
        txtMatKhau.setActionCommand("txtMatKhau");
        txtMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtMatKhau.setBounds(327, 145, 317, 30);
        pnlContent.add(txtMatKhau);
        txtMatKhau.setColumns(10);

        JLabel lblNhapLaiMK = new JLabel("Nhập lại mật khẩu mới:");
        lblNhapLaiMK.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblNhapLaiMK.setBounds(327, 193, 189, 19);
        pnlContent.add(lblNhapLaiMK);

        txtNhapLaiMatKhau = new JPasswordField();
        txtNhapLaiMatKhau.setActionCommand("txtNhapLaiMatKhau");
        txtNhapLaiMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtNhapLaiMatKhau.setColumns(10);
        txtNhapLaiMatKhau.setBounds(327, 222, 317, 30);
        pnlContent.add(txtNhapLaiMatKhau);

        btnXacNhan = new MyButton("Xác nhận");
        btnXacNhan.setActionCommand("btnXacNhan");
        btnXacNhan.setIcon(new ImageIcon("src\\main\\java\\image\\iconcontrolbtntrangchu\\iconcheck.png"));
        btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXacNhan.setBounds(417, 299, 136, 30);
        pnlContent.add(btnXacNhan);
        this.setComponentZOrder(lblBgrDangNhap, 1);

        btnXacNhan.addActionListener(ctrNhapMatKhau);
        txtMatKhau.addActionListener(ctrNhapMatKhau);
        txtNhapLaiMatKhau.addActionListener(ctrNhapMatKhau);
    }

    public void xuLyXacNhan() throws RemoteException {
        String matKhau = txtMatKhau.getText();
        String xacNhanMatKhau = txtNhapLaiMatKhau.getText();

        if (matKhau.trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
        } else {
            if (!matKhau.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$")) {
                JOptionPane.showMessageDialog(this, "Mật khẩu ít nhất 8 ký tự bao gồm chữ hoa, chữ thường, ký tự đặc biệt và số");
            } else {
                if (matKhau.equals(xacNhanMatKhau)) {
                    if (nhanVienBus.capNhatMatKhauNhanVien(maNhanVien, matKhau)) {
                        JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu thành công");
                        new DangNhapGui().setVisible(true);
                        ;
                        this.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Cập nhật mật khẩu không thành công");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Mật khẩu và xác nhận mật khẩu không khớp");
                }
            }
        }
    }

    public void xuLyEnterMatKhauMoi() {
        txtNhapLaiMatKhau.requestFocus();
    }
}
