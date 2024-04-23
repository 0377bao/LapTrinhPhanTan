package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bus.NhanVienBus;
import bus.impl.NhanVienBusImpl;
import bus.impl.TaiKhoanBusImpl;
import customUI.MyButton;
import entity.NhanVien;

import java.awt.Color;
import java.rmi.RemoteException;
import javax.swing.JTextField;
import javax.swing.JPasswordField;


public class DoiMatKhauGui extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField pwdMatKhauCu;
	private JPasswordField pwdMatKhauMoi;
	private JPasswordField pwdXacNhanMatKhauMoi;
    private MyButton btnHuyDoiMatKhau, btnDoiMatKhau;
    private NhanVien nvHienTai;
    private ThongTinNhanVienGui guiTTNV;

	public DoiMatKhauGui(NhanVien nv, ThongTinNhanVienGui guiNV) {
		this.setTitle("Đổi mật khẩu");
		this.nvHienTai = nv;
		this.guiTTNV = guiNV;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 200, 450, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 436, 283);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Đổi mật khẩu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(146, 24, 131, 20);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBorder(null);
		panel_1.setBounds(29, 54, 377, 176);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tên tài khoản: ");
		lblNewLabel_1.setBounds(10, 10, 111, 20);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu cũ: ");
		lblNewLabel_1_1.setBounds(10, 52, 111, 20);
		panel_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_2 = new JLabel("Mật khẩu mới: ");
		lblNewLabel_1_2.setBounds(10, 95, 111, 20);
		panel_1.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_1_3 = new JLabel("Nhập lại mật khẩu mới: ");
		lblNewLabel_1_3.setBounds(10, 137, 173, 20);
		panel_1.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(166, 10, 201, 20);
		textField.setText(nv.getMaNhanVien());
		panel_1.add(textField);
		textField.setColumns(10);
		
		pwdMatKhauCu= new JPasswordField();
		pwdMatKhauCu.setBounds(166, 52, 201, 20);
		panel_1.add(pwdMatKhauCu);
		
		pwdMatKhauMoi = new JPasswordField();
		pwdMatKhauMoi.setBounds(166, 95, 201, 20);
		panel_1.add(pwdMatKhauMoi);
		
		pwdXacNhanMatKhauMoi = new JPasswordField();
		pwdXacNhanMatKhauMoi.setBounds(166, 137, 201, 20);
		panel_1.add(pwdXacNhanMatKhauMoi);
		
		btnHuyDoiMatKhau = new MyButton("Hủy");
		btnHuyDoiMatKhau.setBackground(new Color(255, 0, 0));
		btnHuyDoiMatKhau.setBounds(79, 241, 85, 21);
		panel.add(btnHuyDoiMatKhau);
		
		btnDoiMatKhau = new MyButton("Ok");
		btnDoiMatKhau.setBackground(new Color(128, 255, 0));
		btnDoiMatKhau.setBounds(250, 241, 85, 21);
		panel.add(btnDoiMatKhau);
		
		btnDoiMatKhau.addActionListener(this);
		btnHuyDoiMatKhau.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnDoiMatKhau)) {
			int flag = 1;
			@SuppressWarnings("deprecation")
			String mkCu = pwdMatKhauCu.getText();
			if(mkCu.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu cũ");
				flag = 0;
				return;
			}

            try {
                if(!new NhanVienBusImpl().layMatKhauNhanVienTheoMa(nvHienTai.getMaNhanVien()).equals(mkCu)) {
                    JOptionPane.showMessageDialog(this, "Mật khẩu cũ không trùng khớp! Vui lòng nhập lại");
                    flag = 0;
                    return;
                }
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }

            String mkMoi = pwdMatKhauMoi.getText();
			String xacNhanMk = pwdXacNhanMatKhauMoi.getText();
			if(mkMoi.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập mật khẩu mới");
				flag = 0;
				return;
			}
			
			if(xacNhanMk.equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng xác nhận lại mật khẩu");
				flag = 0;
				return;
			}
			if(!mkMoi.equals(xacNhanMk)) {
				JOptionPane.showMessageDialog(this, "Mật khẩu mới và mật khẩu xác nhận không trùng khớp! Vui lòng nhập lại");
				flag = 0;
				return;
			}
			if(flag == 1) {
                try {
                    if(new TaiKhoanBusImpl().capNhatMatKhau(textField.getText(), mkMoi)) {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                        guiTTNV.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại");
                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
		}
		if(o.equals(btnHuyDoiMatKhau)) {
			this.setVisible(false);
		}
	}
}
