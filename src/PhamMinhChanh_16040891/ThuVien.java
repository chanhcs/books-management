package PhamMinhChanh_16040891;

import java.util.ArrayList;

public class ThuVien {
	private static ArrayList<Sach> dsSach;
	public ThuVien() {
		dsSach = new ArrayList<Sach>();		
	}
	public static boolean themSach(Sach s) {
		if(dsSach.contains(s))
			return false;
		dsSach.add(s);
		return true;
	}
	
	public static boolean xoa(String index) {
		Sach a = new Sach(index);
		if(dsSach.contains(a)) {
			dsSach.remove(a);
			return true;
		}
		return false;
	}
	
	public static ArrayList<Sach> getDsSach() {
		return dsSach;
	}
	
	public static Sach timKiem(String maSach) {
		Sach s = new Sach(maSach);
		if(dsSach.contains(s))
			return dsSach.get(dsSach.indexOf(s));
		return null;
	}
	public static void sua(int vt, Sach s) {
		dsSach.set(vt, s);
	}
	
	
}
