package customUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class MyTable extends JTable{
	public MyTable(DefaultTableModel model) {
		super(model);
        
		JTableHeader header = this.getTableHeader();
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
		// xét màu nền cho thanh tiêu đề
		this.getTableHeader().setBackground(new Color(211, 213, 216));
		//xét height cho thanh tiêu đề
		this.getTableHeader().setPreferredSize(new Dimension(0, 25));
		//xét màu nền cho bảng
		this.setBackground(new Color(213, 217, 223));
		//xét chiều cao cho các dòng trong bảng
		this.setRowHeight(25);
		// đổi màu các dòng trong bảng
		this.setDefaultRenderer(Object.class, new MauCacDongTrongBang());
		// bỏ hiển thị dạng lưới
		this.setShowGrid(false);
	}
	
	// không cho edit trực tiếp trên bảng
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}