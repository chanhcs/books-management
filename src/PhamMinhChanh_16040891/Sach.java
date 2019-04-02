package PhamMinhChanh_16040891;

public class Sach {
	private String maSach;
	private String tenSach;
	private int soLuong;
	private double donGia;
	public Sach(String maSach, String tenSach, int soLuong, double donGia) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}
	
	public Sach(String maSach) {
		this(maSach, "ten sach", 0, 0.0);
	}

	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSach == null) ? 0 : maSach.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sach other = (Sach) obj;
		if (maSach == null) {
			if (other.maSach != null)
				return false;
		} else if (!maSach.equals(other.maSach))
			return false;
		return true;
	}
	
	public String toString() {
		return maSach + ";" + tenSach + ";" + soLuong +";" + donGia;
	 }
	
}
