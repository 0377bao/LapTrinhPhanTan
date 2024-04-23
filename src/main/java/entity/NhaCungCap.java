package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="NhaCungCap")
@NamedQueries({
		@NamedQuery(name = "NhaCungCap.findAll", query = "SELECT n FROM NhaCungCap n"),
		@NamedQuery(name = "NhaCUngCap.findById", query = "SELECT n FROM NhaCungCap n WHERE n.maNhaCungCap = :maNhaCungCap"),
		@NamedQuery(name = "NhaCungCap.findByName", query = "SELECT n FROM NhaCungCap n WHERE n.tenNhaCungCap = :tenNhaCungCap"),
})
public class NhaCungCap implements Serializable{
	@Id
	@Column(columnDefinition = "nvarchar(15)",name="maNhaCungCap", nullable = false, unique = true)
	private String maNhaCungCap;
	@Column(columnDefinition = "nvarchar(150)",name="tenNhaCungCap", nullable = false)
	private String tenNhaCungCap;
	@Column(columnDefinition = "nvarchar(150)",name="diaChi", nullable = false)
	private String diaChi;
	@Column(columnDefinition = "nvarchar(15)",name="sdt", nullable = false)
	private String sdt;
	@Column(columnDefinition = "nvarchar(50)",name="email", nullable = false)
	private String email;
	@OneToMany(mappedBy = "nhaCungCap", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SanPham> dsSanPham;

	public NhaCungCap(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email) {
		super();
		this.maNhaCungCap = maNhaCungCap;
		this.tenNhaCungCap = tenNhaCungCap;
		this.diaChi = diaChi;
		this.sdt = sdt;
		this.email = email;
		this.diaChi = diaChi;
	}

	public NhaCungCap() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getMaNhaCungCap() {
		return maNhaCungCap;
	}

	public void setMaNhaCungCap(String maNhaCungCap) {
		this.maNhaCungCap = maNhaCungCap;
	}

	public String getTenNhaCungCap() {
		return tenNhaCungCap;
	}

	public void setTenNhaCungCap(String tenNhaCungCap) {
		this.tenNhaCungCap = tenNhaCungCap;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
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

	public List<SanPham> getDsSanPham() {
		return dsSanPham;
	}

	public void setDsSanPham(List<SanPham> dsSanPham) {
		this.dsSanPham = dsSanPham;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhaCungCap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhaCungCap other = (NhaCungCap) obj;
		return Objects.equals(maNhaCungCap, other.maNhaCungCap);
	}

	@Override
	public String toString() {
		return "NhaCungCap [maNhaCungCap=" + maNhaCungCap + ", tenNhaCungCap=" + tenNhaCungCap + ", diaChi=" + diaChi
				+ ", sdt=" + sdt + ", email=" + email + "]";
	}

}
