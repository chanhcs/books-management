package PhamMinhChanh_16040891;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FrmSach extends JFrame implements ActionListener, MouseListener {

	private JTextField txtMaSach;
	private JTextField txtTenSach;
	private JTextField txtSoLuong;
	private JTextField txtDonGia;

	private JLabel lblMaSach;
	private JLabel lblTenSach;
	private JLabel lblSoLuong;
	private JLabel lblDonGia;

	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private Database database;
	private JTable table;
	public static DefaultTableModel tableModel;
	static ThuVien thuvien = new ThuVien();

	public FrmSach() {
		setTitle("Quản Lý Sách");
		setSize(700, 650);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		Box b = Box.createVerticalBox();
		b.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		Box b1, b2, b3;
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(3));
		b1.add(lblMaSach = new JLabel("Mã Sách:"));
		b1.add(txtMaSach = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(3));
		b2.add(lblTenSach = new JLabel("Tên Sách:"));
		b2.add(txtTenSach = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(3));

		b3.add(lblSoLuong = new JLabel("Số Lượng:"));
		b3.add(txtSoLuong = new JTextField());
		b3.add(Box.createHorizontalStrut(30));
		b3.add(lblDonGia = new JLabel("Đơn Giá:"));
		b3.add(txtDonGia = new JTextField());

		lblMaSach.setPreferredSize(lblSoLuong.getPreferredSize());

		lblTenSach.setPreferredSize(lblSoLuong.getPreferredSize());
		add(b, BorderLayout.NORTH);

		Box bl = Box.createVerticalBox();
		bl.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách:"));
		bl.add(Box.createHorizontalStrut(10));
		String header[] = "MaSach;TenSach;SoLuong;DonGia".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(table = new JTable(tableModel));
		table.setRowHeight(25);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		bl.add(scroll);
		add(bl, BorderLayout.SOUTH);

		JPanel pnlCen = new JPanel();
		pnlCen.add(btnThem = new JButton("Thêm"));
		pnlCen.add(btnSua = new JButton("Sửa"));
		pnlCen.add(btnXoa = new JButton("Xóa"));
		pnlCen.add(btnLuu = new JButton("Lưu"));
		add(pnlCen, BorderLayout.CENTER);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
		table.addMouseListener(this);
		database.LoadDatabase();
	}

	private void themActions() {
		String maSach, tenSach = null;
		double donGia = 0.0;
		int soLuong = 0;
		try {
			maSach = txtMaSach.getText();
			if (maSach.equals(""))
				JOptionPane.showMessageDialog(this, "Mã sách không rỗng");
			else if (thuvien.timKiem(maSach) != null) {
				JOptionPane.showMessageDialog(this, "trùng mã");
				return;
			}
			String s = "";
			tenSach = txtTenSach.getText();
			donGia = Double.parseDouble(txtDonGia.getText());
			soLuong = Integer.parseInt(txtSoLuong.getText());
			Sach b = new Sach(maSach, tenSach, soLuong, donGia);
			thuvien.themSach(b);
			String a[] = { maSach, tenSach, String.valueOf(soLuong), String.valueOf(donGia) };
			tableModel.addRow(a);
		} catch (Exception e) {

		}
	}

	public void xoaRongActions() {
		txtMaSach.setText("");
		txtTenSach.setText("");
		txtSoLuong.setText("");
		txtDonGia.setText("");
	}

	public void xoaActions() {
		int row = table.getSelectedRow();
		try {
			if (row != -1) {
				String maSach = (String) table.getValueAt(row, 0);

				int hoiNhac = JOptionPane.showConfirmDialog(this, "Bạn có chắc xóa không?", "chú ý",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {
					if (thuvien.xoa(maSach)) {
						tableModel.removeRow(row);
						xoaRongActions();
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Phát sinh lỗi");
			return;
		}
	}
	
	
	
	
	
	
	
	
	
	
	public void suaActions() {
		int row = table.getSelectedRow();
		String maSach = txtMaSach.getText();
		String tenSach = txtTenSach.getText();
		int soLuong = Integer.parseInt(txtSoLuong.getText());
		double donGia = Double.parseDouble(txtDonGia.getText());
		Sach s = new Sach(maSach, tenSach, soLuong, donGia);
		
	

		
		
		
		thuvien.sua(row, s);		
		tableModel.setValueAt(txtTenSach.getText(), row, 1);
		tableModel.setValueAt(txtSoLuong.getText(), row, 2);
		tableModel.setValueAt(txtDonGia.getText(), row, 3);		

		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		txtMaSach.setText(table.getValueAt(row, 0).toString());
		txtTenSach.setText(table.getValueAt(row, 1).toString());
		txtSoLuong.setText(table.getValueAt(row, 2).toString());
		txtDonGia.setText(table.getValueAt(row, 3).toString());

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThem))
			themActions();
		if (o.equals(btnXoa))
			xoaActions();
		if (o.equals(btnSua)) 
			suaActions();
		if(o.equals(btnLuu)) {
			Database.SaveDatabase(ThuVien.getDsSach());
			JOptionPane.showMessageDialog(this, "Lưu thành công");
		}
			
			
	

	}

}
