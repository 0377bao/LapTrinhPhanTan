package customUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonSidebar extends JButton implements MouseListener{
	public ButtonSidebar(String title) {
		super(title);
		this.setFocusPainted(false);
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setFont(new Font("Segoe UI", Font.BOLD, 14));
		this.setHorizontalAlignment(JButton.LEFT);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		this.setBackground(new Color(255,165,0));
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		this.setBackground(new ViewDangNhap().mauChuDao);
	}
}