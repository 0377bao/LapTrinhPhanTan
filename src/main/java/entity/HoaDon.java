package entity;

//import bus.BUSHoaDon;
import bus.impl.HoaDonBusImpl;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "HoaDon")
@NamedQueries({
		@NamedQuery(name = "HoaDon.findAll", query = "SELECT hd FROM HoaDon hd"),
		@NamedQuery(name = "HoaDon.findByMaHoaDon", query = "SELECT hd FROM HoaDon hd WHERE hd.maHoaDon = :maHoaDon"),
		@NamedQuery(name = "HoaDon.findByNgayLap", query = "SELECT hd from HoaDon hd WHERE hd.ngayLap between :ngayBD and :ngayKT")

})
public class HoaDon {
	@Id
	@Column(columnDefinition = "nvarchar(15)", nullable = false)
	private String maHoaDon;
	@Column(columnDefinition = "date", nullable = false)
	private LocalDate ngayLap;
	@Column(columnDefinition = "nvarchar(50)", nullable = false)
	private String hinhThucThanhToan;
	@Column(columnDefinition = "nvarchar(50)")
	private String ghiChu;

	@Column(columnDefinition = "int", nullable = false)
	private int diemGiamGia;
	@Column(columnDefinition = "float", nullable = false)
	private float giamGia;
	@ManyToOne()
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maKhachHang")
	private KhachHang khachHang;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maCTKM")
	private ChuongTrinhKhuyenMai ctkm;
	@Column(columnDefinition = "float", nullable = false)
	private float tienKhachDua;
	@OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ChiTietHoaDon> dsChiTietHoaDon = new ArrayList<>();

	@Column(columnDefinition = "float", nullable = false)
	private float thanhTien = 0;

	public HoaDon(String maHoaDon, LocalDate ngayLap, String hinhThucThanhToan, String ghiChu, int diemGiamGia,
				  float giamGia, NhanVien nhanVien, KhachHang khachHang, ChuongTrinhKhuyenMai ctkm,
				  List<ChiTietHoaDon> dsChiTietHoaDon, float tienKhachDua) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayLap = ngayLap;
		this.hinhThucThanhToan = hinhThucThanhToan;
		this.ghiChu = ghiChu;
		this.diemGiamGia = diemGiamGia;
		this.giamGia = giamGia;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.ctkm = ctkm;
		this.dsChiTietHoaDon = dsChiTietHoaDon;
		// NOTE: chưa có phương tính getThanhTien() nên để mặc định 0
		this.thanhTien = 0;
		this.tienKhachDua = tienKhachDua;
	}

	public HoaDon() {

	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public LocalDate getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(LocalDate ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getHinhThucThanhToan() {
		return hinhThucThanhToan;
	}

	public void setHinhThucThanhToan(String hinhThucThanhToan) {
		this.hinhThucThanhToan = hinhThucThanhToan;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getDiemGiamGia() {
		return diemGiamGia;
	}

	public void setDiemGiamGia(int diemGiamGia) {
		this.diemGiamGia = diemGiamGia;
	}

	public float getGiamGia() {
		return giamGia;
	}

	public void setGiamGia(float giamGia) {
		this.giamGia = giamGia;
	}

	public float getTienKhachDua() {
		return this.tienKhachDua;
	}

	public void setTienKhachDua(float tienKhachDua) {
		this.tienKhachDua = tienKhachDua;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public ChuongTrinhKhuyenMai getCtkm() {
		return ctkm;
	}

	public void setCtkm(ChuongTrinhKhuyenMai ctkm) {
		this.ctkm = ctkm;
	}

	public List<ChiTietHoaDon> getDsChiTietHoaDon() {
		return dsChiTietHoaDon;
	}

	public void setDsChiTietHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon) {
		this.dsChiTietHoaDon = dsChiTietHoaDon;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	public float tinhTongTien() {
		float result = 0;
		for (ChiTietHoaDon cthd : dsChiTietHoaDon) {
			result += cthd.tinhThanhTien();
		}
		return result;
	}

	public float tinhGiamGia() {
		float result = 0;
		for(ChiTietHoaDon cthd : dsChiTietHoaDon) {
			float giamGia = new HoaDonBusImpl().hamLayGiamGiaCuaChiTietHoaDon(this.ctkm, cthd.getSanPham());
			result += cthd.tinhThanhTien() * giamGia / 100;
		}
		return result;
	}

	public float getThanhTien() {
		return tinhTongTien() - tinhGiamGia() - (diemGiamGia * 10000) + tinhThue();
	}

	public float tinhTienThua() {
		return this.tienKhachDua - getThanhTien();
	}

	public float tinhThue() {
		float result = 0;
		for(ChiTietHoaDon cthd : dsChiTietHoaDon) {
			result += cthd.getGiaBan() * cthd.getSanPham().getThue() / 100 * cthd.getSoLuongMua();

		}
		return result;
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayLap=" + ngayLap + ", hinhThucThanhToan=" + hinhThucThanhToan
				+ ", ghiChu=" + ghiChu + ", diemGiamGia=" + diemGiamGia + ", giamGia=" + giamGia + ", nhanVien="
				+ nhanVien + ", khachHang=" + khachHang + ", ctkm=" + ctkm + "tienKhachDua=" + tienKhachDua + ", dsChiTietHoaDon=" + dsChiTietHoaDon
				+ "]";
	}


}
