package ui;

import javax.swing.*;
import java.awt.*;

public class PopUp extends JFrame {
	public PopUp(String message) {
		getContentPane().setBackground(new Color(255, 255, 255));
		this.setSize(400, 100);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		JLabel lblTitle = new JLabel(message);
		lblTitle.setBackground(new Color(255, 255, 255));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));

		// lấy hình gốc
		ImageIcon logoFrame = new ImageIcon("src\\main\\java\\image\\iconcontrolbtntrangchu\\loading.gif");
		lblTitle.setIcon(logoFrame);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(lblTitle, BorderLayout.CENTER);
		ImageIcon logoFrame1 = new ImageIcon(new ImageIcon("src\\main\\java\\image\\logodangnhap\\logo.png").getImage()
				.getScaledInstance(70, 70, Image.SCALE_SMOOTH));
		this.setIconImage(logoFrame1.getImage());
	}
}
