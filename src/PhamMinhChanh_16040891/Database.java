package PhamMinhChanh_16040891;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Database {
	private static final String FILENAME = "dulieu.txt";
	static ThuVien thuvien = new ThuVien();

	public static void LoadDatabase() {
		BufferedReader br = null;
		try {
			if (new File(FILENAME).exists()) {
				br = new BufferedReader(new FileReader(FILENAME));
				br.readLine();
				while (br.ready()) {
					String line = br.readLine();
					if (line != null && !line.trim().equals("")) {
						String[] a = line.split(";");
						Sach s = new Sach(a[0], a[1], Integer.parseInt(a[2]), Double.parseDouble(a[3]));
						thuvien.themSach(s);
						FrmSach.tableModel.addRow(a);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void SaveDatabase(ArrayList<Sach> dsSach) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(FILENAME));
			bw.write("MaSach;TenSach;DonGia;SoLuong\n");
			for (Sach s : dsSach) {
				bw.write(s.toString() + "\n");
			}
			bw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
