package ui;

import bus.impl.DanhMucBusImpl;
import customUI.MyButton;
import customUI.MyCombobox;
import entity.DanhMuc;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

public class DanhMucGui extends JFrame implements ActionListener {
	private JTextField txtMaDanhMuc;
	private JTextField txtTenDanhMuc;
	private MyButton btnTaoMa;
	private MyButton btnThemDanhMuc;
	private MyCombobox cboDanhMuc;

	DanhMucBusImpl busDanhMuc = new DanhMucBusImpl();

	public DanhMucGui(MyCombobox cboDanhMuc) throws RemoteException {
		this.cboDanhMuc = cboDanhMuc;
		this.setSize(600, 200);
		this.setTitle("Thêm danh mục");
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(31, 20, 533, 104);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblMaDanhMuc = new JLabel("Mã danh mục");
		lblMaDanhMuc.setBounds(20, 27, 100, 20);
		lblMaDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblMaDanhMuc);

		txtMaDanhMuc = new JTextField();
		txtMaDanhMuc.setEditable(false);
		txtMaDanhMuc.setEnabled(false);
		txtMaDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMaDanhMuc.setBounds(135, 24, 295, 24);
		panel.add(txtMaDanhMuc);
		txtMaDanhMuc.setColumns(10);

		JLabel lblTenDanhMuc = new JLabel("Tên danh mục");
		lblTenDanhMuc.setBounds(20, 66, 100, 20);
		lblTenDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblTenDanhMuc);

		txtTenDanhMuc = new JTextField();
		txtTenDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtTenDanhMuc.setColumns(10);
		txtTenDanhMuc.setBounds(135, 66, 295, 24);
		panel.add(txtTenDanhMuc);

		btnTaoMa = new MyButton("Tạo mã");
		btnTaoMa.setBounds(453, 24, 70, 24);
		panel.add(btnTaoMa);

		btnThemDanhMuc = new MyButton("Thêm");
		btnThemDanhMuc.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnThemDanhMuc.setBounds(228, 131, 100, 25);
		getContentPane().add(btnThemDanhMuc);

		btnThemDanhMuc.addActionListener(this);
		btnTaoMa.addActionListener(this);

	}

	public void taoMa() throws RemoteException {
		txtMaDanhMuc.setText(busDanhMuc.taoMa());
	}

	public void themDanhMuc() throws RemoteException {
		String ma = txtMaDanhMuc.getText().trim();
		String ten = txtTenDanhMuc.getText().trim();
		if (busDanhMuc.validData(ma, ten)) {
			DanhMuc dm = new DanhMuc(ma, ten);
			if (busDanhMuc.themDanhMuc(dm)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công");
				List<DanhMuc> dsDanhMuc =  busDanhMuc.layDSDanhMuc();
				cboDanhMuc.removeAllItems();
				for (DanhMuc danhMuc : dsDanhMuc) {
					cboDanhMuc.addItem(danhMuc.getTenDanhMuc());
				}
				this.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(this, "Mã danh mục đã tồn tại");
			}
		} else {
			JOptionPane.showMessageDialog(this, busDanhMuc.mes);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTaoMa)) {
            try {
                taoMa();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        } else if (o.equals(btnThemDanhMuc)) {
            try {
                themDanhMuc();
            } catch (RemoteException ex) {
                throw new RuntimeException(ex);
            }
        }
	}
}
