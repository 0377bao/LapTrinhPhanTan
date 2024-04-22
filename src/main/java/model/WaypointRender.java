package model;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class WaypointRender extends WaypointPainter<MyWayPoint> {
	@Override
	protected void doPaint(Graphics2D arg0, JXMapViewer map, int width, int height) {
		for(MyWayPoint wp : getWaypoints()) {
			Point2D p = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
			Rectangle rec = map.getViewportBounds();
			int x = (int) (p.getX() - rec.getX());
			int y = (int) (p.getY() - rec.getY());
			
			JButton cmd = wp.getButton();
			cmd.setLocation(x - cmd.getWidth() / 2, y - cmd.getHeight());
		}
	}
}
