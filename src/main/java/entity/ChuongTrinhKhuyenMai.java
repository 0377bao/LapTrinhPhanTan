package entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity

@Table(name = "ChuongTrinhKhuyenMai")
@NamedQueries({
	@NamedQuery(name = "ChuongTrinhKhuyenMai.layDSChuongTrinhKhuyenMai", query = "select ctkm from ChuongTrinhKhuyenMai ctkm"),
	@NamedQuery(name = "ChuongTrinhKhuyenMai.timChuongTrinhKhuyenMaiTheoId", query = "select ctkm from ChuongTrinhKhuyenMai ctkm where ctkm.maCTKM = :id"),
	@NamedQuery(name = "ChuongTrinhKhuyenMai. timChuongTrinhKhuyenMaiDangSuDung", query = "select ctkm from ChuongTrinhKhuyenMai ctkm where ctkm.trangThai = :status"),
	@NamedQuery(name ="ChuongTrinhKhuyenMai.capNhatTrangThaiChuongTrinhKhuyenMai", query = "update ChuongTrinhKhuyenMai ctkm set ctkm.trangThai = :code where ctkm.id = :id")

})
public class ChuongTrinhKhuyenMai {
	@Id
	@Column(columnDefinition = "nvarchar(15)",name="maCTKM", nullable = false, unique = true)
	private String maCTKM;
	@Column(columnDefinition = "nvarchar(50)",name="tenCTKM", nullable = false)
	private String tenCTKM;
	@OneToMany(mappedBy = "chuongTrinhKhuyenMai", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MucKhuyenMai> dsMucKhuyenMai = new ArrayList<>();
	@Column(columnDefinition = "bit",name="trangThai", nullable = false)
	private boolean trangThai;

	public ChuongTrinhKhuyenMai(){}

	public ChuongTrinhKhuyenMai(String maCTKM, String tenCTKM, List<MucKhuyenMai> dsMucKhuyenMai, boolean trangThai) {
		super();
		this.maCTKM = maCTKM;
		this.tenCTKM = tenCTKM;
		this.dsMucKhuyenMai = dsMucKhuyenMai;
		this.trangThai = trangThai;
	}

	public String getMaCTKM() {
		return maCTKM;
	}

	public void setMaCTKM(String maCTKM) {
		this.maCTKM = maCTKM;
	}

	public String getTenCTKM() {
		return tenCTKM;
	}

	public void setTenCTKM(String tenCTKM) {
		this.tenCTKM = tenCTKM;
	}

	public List<MucKhuyenMai> getDsMucKhuyenMai() {
		return dsMucKhuyenMai;
	}

	public void setDsMucKhuyenMai(List<MucKhuyenMai> dsMucKhuyenMai) {
		this.dsMucKhuyenMai = dsMucKhuyenMai;
	}


	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCTKM);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuongTrinhKhuyenMai other = (ChuongTrinhKhuyenMai) obj;
		return Objects.equals(maCTKM, other.maCTKM);
	}

	@Override
	public String toString() {
		return "ChuongTrinhKhuyenMai [maCTKM=" + maCTKM + ", tenCTKM=" + tenCTKM + ", dsMucKhuyenMai=" + dsMucKhuyenMai
				+ ", trangThai=" + trangThai + "]";
	}

	

	
}
