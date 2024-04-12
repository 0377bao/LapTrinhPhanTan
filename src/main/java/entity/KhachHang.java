package entity;

import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name="KhachHang")
@NamedQueries({
		@NamedQuery(name = "Customer.findAll", query = "select kh from KhachHang kh"),
		@NamedQuery(name = "Customer.findBySdt", query = "select kh from KhachHang kh where kh.sdt = :sdt"),
		@NamedQuery(name = "Customer.findTransactionHistoryOfCustomer", query = "select hd from HoaDon hd where hd.khachHang.maKhachHang =: maKH order by hd.ngayLap desc"),
		@NamedQuery(name = "Customer.filterCustomerByNameAndSdt", query = "select kh from KhachHang kh where lower(kh.tenKhachHang) like lower(:tenKhachHang) and kh.sdt like :sdt"),
		@NamedQuery(name = "Customer.sortCustomerByTotalPurchase", query = "select kh from KhachHang kh order by kh.tongTienMua desc")
})
public class KhachHang {
	@Id
    @Column(columnDefinition = "nvarchar(15)",name="maKhachHang", nullable = false, unique = true)
	private String maKhachHang;
    @Column(columnDefinition = "nvarchar(50)",name="tenKhachHang", nullable = false)
	private String tenKhachHang;
    @Column(columnDefinition = "nvarchar(15)",name="sdt", nullable = false)
	private String sdt;
    @Column(columnDefinition = "nchar(50)",name="email", nullable = false)
	private String email;
    @Column(columnDefinition = "int",name="diemTichLuy", nullable = false)
	private int diemTichLuy;
    @Column(columnDefinition = "float",name="tongTienMua", nullable = false)
	private float tongTienMua;

    public KhachHang() {

    }

	public KhachHang(String maKhachHang, String tenKhachHang, String sdt, String email, int diemTichLuy, float tongTienMua) {
		super();
		this.maKhachHang = maKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.email = email;
		this.diemTichLuy = diemTichLuy;
		this.tongTienMua = tongTienMua;
	}

	public String getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(String maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}



	public float getTongTienMua() {
		return tongTienMua;
	}

	public void congDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy += diemTichLuy;
	}
   public void congTongTienMua(float tongTienMua) {
	   this.tongTienMua += tongTienMua;
   }



	@Override
	public int hashCode() {
		return Objects.hash(maKhachHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKhachHang, other.maKhachHang);
	}

	@Override
	public String toString() {
		return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt + ", email="
				+ email + ", diemTichLuy=" + diemTichLuy +  ", tongTienMua= " + tongTienMua+ "]";
	}

	
}
