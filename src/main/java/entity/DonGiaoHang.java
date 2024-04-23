package entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "DonGiaoHang")
@NamedQueries({
		@NamedQuery(name = "DonGiaoHang.layDSDonGiaoHang", query = "SELECT dgh FROM DonGiaoHang dgh"),
		@NamedQuery(name = "DonGiaoHang.timDonHangTheoTenKhachHang", query = "SELECT dgh FROM DonGiaoHang dgh WHERE dgh.tenKhachHang = :tenKhachHang"),
		@NamedQuery(name = "DonGiaoHang.tongSoHoaDon", query = "SELECT count(dgh) FROM DonGiaoHang dgh"),
		@NamedQuery(name = "DonGiaoHang.layDSDonHangDangGiao", query = "SELECT dgh FROM DonGiaoHang dgh WHERE dgh.trangThai = false AND dgh.ghiChu = ''"),
		@NamedQuery(name = "DonGiaoHang.layDSDonHangBiHuy", query = "SELECT dgh FROM DonGiaoHang dgh WHERE dgh.trangThai = false AND dgh.ghiChu <> ''"),
		@NamedQuery(name = "DonGiaoHang.layDSDonHangGiaoThanhCong", query = "SELECT dgh FROM DonGiaoHang dgh WHERE dgh.trangThai = true"),
})
public class DonGiaoHang {
	@Id
	@Column(name = "maDonGiaoHang", columnDefinition = "nvarchar(15)", nullable = false, unique = true)
	private String maDonGiaoHang;
	@Column(name = "tenKhachHang", columnDefinition = "nvarchar(50)", nullable = false)
	private String tenKhachHang;
	@Column(name="sdt", columnDefinition = "nchar(15)", nullable = false)
	private String sdt;
	@Column(name = "diaChi", columnDefinition = "nvarchar(150)", nullable = false)
	private String diaChi;
	@Column(name = "soKg", columnDefinition = "int", nullable = false)
	private int soKg;
	@Column(name = "trangThai", columnDefinition = "bit", nullable = false)
	private boolean trangThai;
	@Column(name = "soKm", columnDefinition = "float", nullable = false)
	private float soKm;
	@Column(name = "ghiChu", columnDefinition = "nvarchar(150)")
	private String ghiChu;
	@Column(name="tienVanChuyen",columnDefinition = "float", nullable = false)
	private float tienVanChuyen;
	@Column(name="phuongThucThanhToan",columnDefinition = "nvarchar(50)", nullable = false)
	private String phuongThucThanhToan;
	@OneToOne
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;

	public DonGiaoHang() {
	}
	
	public DonGiaoHang(String ma, HoaDon hd) {
		this.maDonGiaoHang = ma;
		this.hoaDon = hd;
	}

	public DonGiaoHang(String maDonGiaoHang, String tenKhachHang, String sdt, String diaChi, int soKg,
			boolean trangThai, String ghiChu, float tienVanChuyen,String phuongThucThanhToan, HoaDon hoaDon) {
		super();
		this.maDonGiaoHang = maDonGiaoHang;
		this.tenKhachHang = tenKhachHang;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.soKg = soKg;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.tienVanChuyen = tienVanChuyen;
		this.phuongThucThanhToan = phuongThucThanhToan;
		this.hoaDon = hoaDon;
	}

	public String getMaDonGiaoHang() {
		return maDonGiaoHang;
	}

	public void setMaDonGiaoHang(String maDonGiaoHang) {
		this.maDonGiaoHang = maDonGiaoHang;
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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getSoKg() {
		return soKg;
	}

	public void setSoKg(int soKg) {
		this.soKg = soKg;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public float getSoKm() {
		return soKm;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}


	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public float getTienVanChuyen() {
		return tienVanChuyen;
	}


	public String getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(String phuongThucThanhToan) {
		this.phuongThucThanhToan = phuongThucThanhToan;
	}

	public float tinhSoKm(float km) {

		// tính số km gọi bằng google map
		this.soKm = km;
		return this.soKm;
	}

	public float tinhTienVanChuyen() {
		float result = 0;
		// trường hợp giao hàng miễn phí
		if (hoaDon.getThanhTien() >= 3000000) {
			result = 0;
		}
		// trường hợp tính tiền vận chuyển
		if (soKm > 40) {
			return -1; // trường hợp này không giao hàng
		} else {
			if (soKg <= 50) {
				result = soKm * 3000;
			} else if (soKg <= 100) {
				result = soKm * 5000;
			} else if (soKg <= 150) {
				result = soKm * 7000;
			}
		}
		this.tienVanChuyen = result;
		return tienVanChuyen;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDonGiaoHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonGiaoHang other = (DonGiaoHang) obj;
		return Objects.equals(maDonGiaoHang, other.maDonGiaoHang);
	}

	@Override
	public String toString() {
		return "DonGiaoHang [maDonGiaoHang=" + maDonGiaoHang + ", tenKhachHang=" + tenKhachHang + ", sdt=" + sdt
				+ ", diaChi=" + diaChi + ", soKg=" + soKg + ", trangThai=" + trangThai + ", soKm=" + soKm + ", ghiChu="
				+ ghiChu + ", tienVanChuyen=" + tienVanChuyen + ", hoaDon=" + hoaDon + "]";
	}

}
