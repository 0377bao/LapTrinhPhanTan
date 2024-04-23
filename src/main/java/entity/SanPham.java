package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="SanPham")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
@NamedQueries(
		{
				@NamedQuery(name = "SanPham.layDanhSachSanPham", query = "SELECT s FROM SanPham s"),
				@NamedQuery(name = "SanPham.capNhatSoLuongTonSanPham", query = "UPDATE SanPham sp SET sp.soLuongTon = :soLuongTon WHERE sp.maSanPham = :maSanPham"),
		}
)
public class SanPham implements Serializable{
	@Id
	@Column(columnDefinition = "nvarchar(15)", name="maSanPham", nullable = false, unique = true)
	private String maSanPham;
	@Column(columnDefinition = "nvarchar(150)", name="tenSanPham", nullable = false)
	private String tenSanPham;
	@Column(columnDefinition = "int", name="soLuongTon", nullable = false)
	private int soLuongTon;
	@Column(columnDefinition = "float", name="giaNhap", nullable = false)
	private float giaNhap;
	@Column(columnDefinition = "nvarchar(30)", name="theLoai", nullable = false)
	private String theLoai;
	@Column(columnDefinition = "nvarchar(20)", name="ke", nullable = false)
	private String ke;
	@Column(columnDefinition = "nvarchar(100)", name="hinhAnh", nullable = false)
	private String hinhAnh;
	@Column(columnDefinition = "float", name="thue", nullable = false)
	private float thue;
	@Column(columnDefinition = "float", name="phanTramLoiNhuan", nullable = false)
	private float phanTramLoiNhuan;
	@Column(columnDefinition = "nvarchar(30)", name="trangThai", nullable = false)
	private String trangThai;
	@Column(columnDefinition = "float", name = "giaBan", nullable = false)
	private float giaBan;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhaCungCap", columnDefinition = "nvarchar(15)", nullable = false)
	private NhaCungCap nhaCungCap;

	public SanPham(String maSanPham, String tenSanPham, int soLuongTon, float giaNhap, String theLoai, String ke,
			String hinhAnh, float thue, float phanTramLoiNhuan, String trangThai, NhaCungCap nhaCungCap) {
		super();
		this.maSanPham = maSanPham;
		this.tenSanPham = tenSanPham;
		this.soLuongTon = soLuongTon;
		this.giaNhap = giaNhap;
		this.theLoai = theLoai;
		this.ke = ke;
		this.hinhAnh = hinhAnh;
		this.thue = thue;
		this.phanTramLoiNhuan = phanTramLoiNhuan;
		this.trangThai = trangThai;
		this.nhaCungCap = nhaCungCap;
		this.giaBan = this.getGiaBan();

	}

	public SanPham(String maSanPham) {
		super();
		this.maSanPham = maSanPham;
	}

	public SanPham() {}

	public String getMaSanPham() {
		return maSanPham;
	}

	public void setMaSanPham(String maSanPham) {
		this.maSanPham = maSanPham;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public float getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(float giaNhap) {
		this.giaNhap = giaNhap;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public String getKe() {
		return ke;
	}

	public void setKe(String ke) {
		this.ke = ke;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public float getThue() {
		return thue;
	}

	public void setThue(float thue) {
		this.thue = thue;
	}

	public float getPhanTramLoiNhuan() {
		return phanTramLoiNhuan;
	}

	public void setPhanTramLoiNhuan(float phanTramLoiNhuan) {
		this.phanTramLoiNhuan = phanTramLoiNhuan;
	}

	public float getGiaBan() {
		return this.giaNhap * (1 + this.phanTramLoiNhuan / 100);
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public NhaCungCap getNhaCungCap() {
		return nhaCungCap;
	}

	public void setNhaCungCap(NhaCungCap nhaCungCap) {
		this.nhaCungCap = nhaCungCap;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	@Override
	public String toString() {
		return "SanPham [maSanPham=" + maSanPham + ", tenSanPham=" + tenSanPham + ", soLuongTon=" + soLuongTon
				+ ", giaNhap=" + giaNhap + ", theLoai=" + theLoai + ", ke=" + ke + ", hinhAnh=" + hinhAnh + ", thue="
				+ thue + ", phanTramLoiNhuan=" + phanTramLoiNhuan + ", trangThai=" + trangThai + ", nhaCungCap="
				+ nhaCungCap + ", giaBan=" + this.getGiaBan() + "]";
	}
}
