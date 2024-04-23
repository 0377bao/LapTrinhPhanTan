package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table (name = "NhanVien")
@NamedQueries({
		@NamedQuery(name = "NhanVien.layDSNhanVien", query = "SELECT nv FROM NhanVien nv"),
		@NamedQuery(name = "NhanVien.layNhanVienTheoMa", query = "SELECT nv FROM NhanVien nv where nv.maNhanVien = :maNhanVien"),
		@NamedQuery(name = "NhanVien.capNhatMatKhauNV", query = "UPDATE TaiKhoan tk SET tk.matKhau = :matKhau WHERE tk.nhanVien.maNhanVien = :maNhanVien"),
		@NamedQuery(name = "NhanVien.locNVTheoChucVu", query = "SELECT nv FROM NhanVien nv where nv.chucVu = :chucVu"),
		@NamedQuery(name = "NhanVien.locNVTheoGioiTinh", query = "SELECT nv FROM NhanVien nv where nv.gioiTinh = :gioiTinh"),
		@NamedQuery(name = "NhanVien.locNVTheoTrangThai", query = "SELECT nv FROM NhanVien nv where nv.trangThai = :trangThai")
})
public class NhanVien implements Serializable{
	@Id
	@Column(name = "maNhanVien", columnDefinition = "NVARCHAR(15)", nullable = false)
	private String maNhanVien;
	@Column(name = "tenNhanVien", columnDefinition = "NVARCHAR(50)", nullable = false)
	private String tenNhanVien;
	@Column(name = "sdt", columnDefinition = "NVARCHAR(15)", nullable = false)
	private String sdt;
	@Column(name = "email", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String email;
	@Column(name = "gioiTinh", columnDefinition = "BIT", nullable = false)
	private boolean gioiTinh;
	@Column(name = "diaChi", columnDefinition = "NVARCHAR(150)", nullable = false)
	private String diaChi;
	@Column(name = "chucVu", columnDefinition = "NVARCHAR(30)", nullable = false)
	private String chucVu;
	@Column(name = "cCCD", columnDefinition = "NVARCHAR(15)", nullable = false)
	private String cCCD;
	@Column(name = "hinhAnh", columnDefinition = "NVARCHAR(100)", nullable = false)
	private String hinhAnh;
	@Column(name = "trangThai", columnDefinition = "NVARCHAR(15)")
	private String trangThai;

	public NhanVien(String maNhanVien, String tenNhanVien, String sdt, String email, boolean gioiTinh, String diaChi,
					String chucVu, String cCCD, String hinhAnh, String trangThai) {
		super();
		this.maNhanVien = maNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.sdt = sdt;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.diaChi = diaChi;
		this.chucVu = chucVu;
		this.cCCD = cCCD;
		this.hinhAnh = hinhAnh;
		this.trangThai = trangThai;
	}

	public NhanVien() {

	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
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

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", sdt=" + sdt + ", email="
				+ email + ", gioiTinh=" + gioiTinh + ", diaChi=" + diaChi + ", chucVu=" + chucVu + ", cCCD=" + cCCD
				+ ", hinhAnh=" + hinhAnh + ", trangThai=" + trangThai +  "]";
	}

}