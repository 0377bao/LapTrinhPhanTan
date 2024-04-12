package entity;

import jakarta.persistence.*;


import java.util.Objects;

@Entity
@Table(name = "DanhMuc")
@NamedQueries({
		@NamedQuery(name = "DanhMuc.findAll", query = "SELECT d FROM DanhMuc d"),
		@NamedQuery(name = "DanhMuc.findByName", query = "SELECT d FROM DanhMuc d WHERE d.tenDanhMuc = :tenDanhMuc"),
		@NamedQuery(name = "DanhMuc.findById", query = "SELECT d FROM DanhMuc d WHERE d.maDanhMuc = :maDanhMuc"),
})
public class DanhMuc {
	@Id
	@Column(name = "maDanhMuc", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
	private String maDanhMuc;
	@Column(name = "tenDanhMuc", columnDefinition = "nvarchar(50)", nullable = false)
	private String tenDanhMuc;

	public DanhMuc() {
	}

	public DanhMuc(String maDanhMuc, String tenDanhMuc) {
		super();
		this.maDanhMuc = maDanhMuc;
		this.tenDanhMuc = tenDanhMuc;
	}

	public DanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getMaDanhMuc() {
		return maDanhMuc;
	}

	public void setMaDanhMuc(String maDanhMuc) {
		this.maDanhMuc = maDanhMuc;
	}

	public String getTenDanhMuc() {
		return tenDanhMuc;
	}

	public void setTenDanhMuc(String tenDanhMuc) {
		this.tenDanhMuc = tenDanhMuc;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDanhMuc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DanhMuc other = (DanhMuc) obj;
		return Objects.equals(maDanhMuc, other.maDanhMuc);
	}

	@Override
	public String toString() {
		return "DanhMuc [maDanhMuc=" + maDanhMuc + ", tenDanhMuc=" + tenDanhMuc + "]";
	}

}
