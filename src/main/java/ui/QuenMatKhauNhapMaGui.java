package ui;

import controller.QuenMatKhauNhapMaController;
import customUI.CustomImage;
import customUI.MyButton;
import tool.PhanLuong;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class QuenMatKhauNhapMaGui extends JFrame {

    private String email;
    private String maNhanVien;
    private String maXacNhan = "";
    private JPanel contentPane;
    private
    JTextField txtTenDangNhap;
    private JTextField txtXacNhan;
    private MyButton btnGuiMa;
    private MyButton btnXacNhan;
    private QuenMatKhauNhapMaController ctrQuenMK = new QuenMatKhauNhapMaController(this);

    public QuenMatKhauNhapMaGui(String maNhanVien, String email) {
        this.maNhanVien = maNhanVien;
        this.email = email;
        this.setSize(1000, 700);
        this.setTitle("QUÊN MẬT KHẨU");
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

        JLabel lblDangNhap = new JLabel("QUÊN MẬT KHẨU");
        lblDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
        lblDangNhap.setForeground(new Color(10, 110, 227));
        lblDangNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblDangNhap.setBounds(364, 61, 220, 25);
        pnlContent.add(lblDangNhap);

        btnXacNhan = new MyButton("Xác nhận");
        btnXacNhan.setActionCommand("btnXacNhan");
        btnXacNhan.setIcon(new ImageIcon("src\\main\\java\\image\\iconcontrolbtntrangchu\\iconcheck.png"));
        btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnXacNhan.setBounds(416, 340, 136, 30);
        pnlContent.add(btnXacNhan);

        JTextArea txtThongBao = new JTextArea();
        txtThongBao.setEnabled(false);
        txtThongBao.setDisabledTextColor(Color.black);
        txtThongBao.setFont(new Font("Monospaced", Font.BOLD, 15));
        txtThongBao.setText("Email của nhân viên: \n" + email);
        txtThongBao.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        txtThongBao.setBounds(321, 126, 312, 72);
        pnlContent.add(txtThongBao);

        JLabel lblXacNhan = new JLabel("Nhập mã xác nhận:");
        lblXacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblXacNhan.setBounds(321, 259, 153, 19);
        pnlContent.add(lblXacNhan);

        txtXacNhan = new JTextField();
        txtXacNhan.setActionCommand("txtXacNhan");
        txtXacNhan.setFont(new Font("Tahoma", Font.BOLD, 13));
        txtXacNhan.setColumns(10);
        txtXacNhan.setBounds(321, 288, 317, 30);
        pnlContent.add(txtXacNhan);

        btnGuiMa = new MyButton("Đăng nhập");
        btnGuiMa.setActionCommand("btnGuiMa");
        btnGuiMa.setIcon(new ImageIcon("src\\main\\java\\image\\iconcontrolbtntrangchu\\iconsendemail.png"));
        btnGuiMa.setText("Gửi mã 4 số");
        btnGuiMa.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnGuiMa.setBounds(416, 215, 136, 30);
        pnlContent.add(btnGuiMa);

        this.setComponentZOrder(lblBgrDangNhap, 1);
        //xử lý
        btnGuiMa.addActionListener(ctrQuenMK);
        btnXacNhan.addActionListener(ctrQuenMK);
        txtXacNhan.addActionListener(ctrQuenMK);
    }

    // gửi mã 4 số
    public String randomMa4So() {
        // Tạo một đối tượng Random
        Random random = new Random();
        String result = "";
        // Sinh số nguyên ngẫu nhiên từ 0 đến 9
        while (result.length() != 4) {
            result += random.nextInt(10);
        }
        return result;
    }

    public void xuLyGuiMa() {
        maXacNhan = randomMa4So();
        PopUp popup = new PopUp("Đang gửi mã xác nhận về email");
        new PhanLuong(popup, email, "Nhà sách HBDK gửi mã xác nhận 4 số", "Mã xác nhận quên mật khẩu của bạn là " + maXacNhan, "Hệ thống vừa gửi mã xác nhận gồm 4 số về email").execute();
    }

    public void xuLyXacNhan() {
        if (maXacNhan.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng gửi mã xác nhận về email trước");
        } else {
            String maXacNhanNhap = txtXacNhan.getText();
            if (maXacNhanNhap.equals(maXacNhan)) {
                new QuenMatKhauNhapMatKhauMoiGui(maNhanVien).setVisible(true);
                ;
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Mã xác nhận không trùng khớp");
            }
        }
    }
}