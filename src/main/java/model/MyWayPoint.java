package model;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWayPoint extends DefaultWaypoint {
	
	private String name;
	private JButton button;
	private PointType pointType;
	
	public PointType getPointType() {
		return pointType;
	}

	public void setPointType(PointType pointType) {
		this.pointType = pointType;
	}

	public MyWayPoint() {
	}
	
	public MyWayPoint(GeoPosition coord, PointType pointType, EvenWaypoint even, String name) {
		super(coord);
		this.name = name;
		this.pointType = pointType;
		initButton(even);
	}
	
	public void initButton(EvenWaypoint even) {
		this.button = new ButtonWayPoint();
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				even.selected(MyWayPoint.this);
			}
		});
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JButton getButton() {
		return button;
	}
	public void setButton(JButton button) {
		this.button = button;
	}

	public static enum PointType {
		START, END
	}
}
