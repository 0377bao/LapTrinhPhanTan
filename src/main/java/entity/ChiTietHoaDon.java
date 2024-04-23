package entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietHoaDon")
@NamedQueries({
		@NamedQuery(name = "ChiTietHoaDon.getListDetailOfBill", query = "select cthd from ChiTietHoaDon cthd where cthd.hoaDon.maHoaDon = :maHD")
})
public class ChiTietHoaDon implements Serializable{

	@Column(columnDefinition = "int", nullable = false)
	private int soLuongMua;
	@Column(columnDefinition = "float", nullable = false)
	private float giaBan;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;

	public ChiTietHoaDon(int soLuongMua, float giaBan, SanPham sanPham, HoaDon hoaDon) {
		super();
		this.soLuongMua = soLuongMua;
		this.giaBan = giaBan;
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
	}

	public ChiTietHoaDon() {

	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public float tinhThanhTien() {
		return giaBan * soLuongMua;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuongMua=" + soLuongMua + ", giaBan=" + giaBan + ", sanPham=" + sanPham + "]";
	}


}
