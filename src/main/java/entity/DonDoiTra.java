package entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
@Entity
@Table(name = "DonDoiTra")
@NamedQueries({
		@NamedQuery(name = "DonDoiTra.findAll", query = "SELECT d FROM DonDoiTra d"),
		@NamedQuery(name = "DonDoiTra.findByMaDonDoiTra", query = "SELECT d FROM DonDoiTra d WHERE d.maDonDoiTra = :maDonDoiTra"),
		@NamedQuery(name = "DonDoiTra.findByHoaDon", query = "SELECT d FROM DonDoiTra d WHERE d.hoaDon.maHoaDon = :maHoaDon"),
		@NamedQuery(name = "DonDoiTra.findByNgayXDenNgayY", query = "SELECT d FROM DonDoiTra d WHERE d.ngayDoiTra BETWEEN :date1 AND :date2"),
		@NamedQuery(name = "DonDoiTra.fineByPhone", query = "SELECT d FROM DonDoiTra d WHERE d.hoaDon.khachHang.sdt like :sdt"),
})
public class DonDoiTra {
	@Id
	@Column(columnDefinition = "nvarchar(15)", nullable = false, unique = true)
	private String maDonDoiTra;
	@Column(columnDefinition = "date", nullable = false)
	private LocalDate ngayDoiTra;
	@Column(columnDefinition = "nvarchar(50)", name="phuongThucDoiTra", nullable = false)
	private String phuongThucDoiTra;
	@Column(columnDefinition = "int", name = "diemHoanTra",nullable = false)
	private int diemHoanTra;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maHoaDon")
	private HoaDon hoaDon;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNhanVien")
	private NhanVien nhanVien;
	@OneToMany(mappedBy = "donDoiTra", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private ArrayList<ChiTietDonDoiTra> dsChiTietDonDoiTra;

	public LocalDate getNgayDoiTra() {
		return ngayDoiTra;
	}

	public void setNgayDoiTra(LocalDate ngayDoiTra) {
		this.ngayDoiTra = ngayDoiTra;
	}

	public String getPhuongThucDoiTra() {
		return phuongThucDoiTra;
	}

	public void setPhuongThucDoiTra(String phuongThucDoiTra) {
		this.phuongThucDoiTra = phuongThucDoiTra;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public ArrayList<ChiTietDonDoiTra> getDsChiTietDonDoiTra() {
		return dsChiTietDonDoiTra;
	}

	public void setDsChiTietDonDoiTra(ArrayList<ChiTietDonDoiTra> dsChiTietDonDoiTra) {
		this.dsChiTietDonDoiTra = dsChiTietDonDoiTra;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public String getMaDonDoiTra() {
		return maDonDoiTra;
	}

	public int getDiemHoanTra() {
		return diemHoanTra;
	}

	public void setDiemHoanTra(int diemHoanTra) {
		this.diemHoanTra = diemHoanTra;
	}

	public DonDoiTra(String maDonDoiTra, LocalDate ngayDoiTra, String phuongThucDoiTra, int diemHoanTra, HoaDon hoaDon, NhanVien nhanVien, ArrayList<ChiTietDonDoiTra> dsChiTietDonDoiTra) {
		this.maDonDoiTra = maDonDoiTra;
		this.ngayDoiTra = ngayDoiTra;
		this.phuongThucDoiTra = phuongThucDoiTra;
		this.diemHoanTra = diemHoanTra;
		this.hoaDon = hoaDon;
		this.nhanVien = nhanVien;
		this.dsChiTietDonDoiTra = dsChiTietDonDoiTra;
	}

	public DonDoiTra() {

	}

	@Override
	public int hashCode() {
		return Objects.hash(maDonDoiTra);
	}

	public float tinhTienCanTra() {
		if(phuongThucDoiTra.equals("Đổi Hàng")) {
			return 0;
		}
		float tongTien = 0;
		for (ChiTietDonDoiTra ctddt : dsChiTietDonDoiTra) {
			tongTien +=ctddt.getGiaBan()*ctddt.getSoLuongTra();
		}
		return tongTien-diemHoanTra*10000;
	}

	public int tinhSoLuongDoiHang() {
		int tongSoLuong = 0;
		for(ChiTietDonDoiTra ctddt: dsChiTietDonDoiTra) {
			tongSoLuong += ctddt.getSoLuongTra();
		}
		return tongSoLuong;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DonDoiTra other = (DonDoiTra) obj;
		return Objects.equals(maDonDoiTra, other.maDonDoiTra);
	}




	@Override
	public String toString() {
		return "DonDoiTra [maDonDoiTra=" + maDonDoiTra + ", ngayDoiTra=" + ngayDoiTra + ", dsChiTietDonDoiTra="
				+ dsChiTietDonDoiTra + ", hoaDon=" + hoaDon + ", nhanVien=" + nhanVien + ", phuongThucDoiTra="
				+ phuongThucDoiTra + ", diemHoanTra=" + diemHoanTra + "]";
	}







}
