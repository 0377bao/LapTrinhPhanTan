package customUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MauCacDongTrongBang extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JLabel label = (JLabel) c;
        // đổi màu dòng được chọn
        if (isSelected) {
            c.setBackground(new Color(67, 121, 161));
            c.setForeground(Color.white);
        } else {
            // Đổi màu nền cho hàng chẵn thành màu xám
            if (row % 2 == 0) {
                c.setBackground(new Color(217, 217, 217));
                c.setForeground(Color.black);
            } else {
                c.setBackground(new Color(235, 235, 235));
                c.setForeground(Color.black);
            }
        }
        return c;
    }
}