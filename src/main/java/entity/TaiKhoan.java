package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "TaiKhoan")
@NamedQueries({
		@NamedQuery(name = "TaiKhoan.layDSTaiKhoan", query = "select tk from TaiKhoan tk"),
		@NamedQuery(name = "TaiKhoan.kiemTraMatKhau", query = "select tk from TaiKhoan tk where tk.nhanVien.maNhanVien = :username and tk.matKhau = :password")

})
public class TaiKhoan {
	@Id
	@OneToOne
	@JoinColumn(name = "tenTaiKhoan", columnDefinition = "nvarchar(15)", nullable = false)
	private NhanVien nhanVien;
	private String matKhau;

	public TaiKhoan(NhanVien nv, String matKhau) {
		super();
		this.nhanVien = nv;
		this.matKhau = matKhau;
	}

	public TaiKhoan() {

	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TaiKhoan taiKhoan = (TaiKhoan) o;
		return Objects.equals(nhanVien, taiKhoan.nhanVien);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nhanVien);
	}

	@Override
	public String toString() {
		return "TaiKhoan{" +
				"nhanVien=" + nhanVien +
				", matKhau='" + matKhau + '\'' +
				'}';
	}
}
