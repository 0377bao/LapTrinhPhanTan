package model;

import javax.swing.*;
import java.awt.*;

public class ButtonWayPoint extends JButton {
	public ButtonWayPoint() {
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setBorder(null);
		this.setIcon(new ImageIcon("src\\mapdata\\icon\\pin.png"));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setSize(new Dimension(24,24));
	}
}
