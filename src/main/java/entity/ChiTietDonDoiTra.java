package entity;

import jakarta.persistence.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Entity

@Table(name = "ChiTietDonDoiTra")
public class ChiTietDonDoiTra {
	@Column(columnDefinition = "int", name = "soLuongTra", nullable = false)
	private int soLuongTra;
	@Column(columnDefinition = "nvarchar(50)", name = "lyDo", nullable = false)
	private String lyDo;
	@Column(columnDefinition = "float", name = "giaBan", nullable = false)
	private float giaBan;
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maDonDoiTra")
	private DonDoiTra donDoiTra;

	@Id
	@ManyToOne
	@JoinColumn(name = "maSanPham")
	private SanPham sanPham;


	public float getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(float giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuongTra() {
		return soLuongTra;
	}

	public void setSoLuongTra(int soLuongTra) {
		this.soLuongTra = soLuongTra;
	}

	public String getLyDo() {
		return lyDo;
	}

	public void setLyDo(String lyDo) {
		this.lyDo = lyDo;
	}

	public DonDoiTra getDonDoiTra() {
		return donDoiTra;
	}

	public void setDonDoiTra(DonDoiTra donDoiTra) {
		this.donDoiTra = donDoiTra;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public ChiTietDonDoiTra() {
	}

	public ChiTietDonDoiTra(int soLuongTra, String lyDo, float giaBan, DonDoiTra donDoiTra, SanPham sanPham) {
		this.soLuongTra = soLuongTra;
		this.lyDo = lyDo;
		this.giaBan = giaBan;
		this.donDoiTra = donDoiTra;
		this.sanPham = sanPham;
	}

	public ChiTietDonDoiTra(int soLuongTra, String lyDo,SanPham sanPham, float giaBan) {
		this.soLuongTra = soLuongTra;
		this.lyDo = lyDo;
		this.giaBan = giaBan;
		this.sanPham = sanPham;
	}

	@Override
	public String toString() {
		return "ChiTietDonDoiTra [soLuongTra=" + soLuongTra + ", lyDo=" + lyDo + ", sanPham=" + sanPham + ", giaBan="
				+ giaBan + "]";
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
		Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
		if (thisEffectiveClass != oEffectiveClass) return false;
		ChiTietDonDoiTra that = (ChiTietDonDoiTra) o;
		return getDonDoiTra() != null && Objects.equals(getDonDoiTra(), that.getDonDoiTra());
	}

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
