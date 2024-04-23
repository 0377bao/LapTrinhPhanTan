package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;

@Entity

@Table(name= "MucKhuyenMai")
public class MucKhuyenMai implements Serializable{
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maCTKM", columnDefinition = "nvarchar(15)", nullable = false)
	private ChuongTrinhKhuyenMai chuongTrinhKhuyenMai;
	@Id
	@Column(name = "tenMucKhuyenMai", columnDefinition = "nvarchar(50)", nullable = false)
	private String tenMucKhuyenMai;
	@Column(name = "tiLeKhuyenMai", columnDefinition = "float", nullable = false)
	private float tiLeKhuyenMai;

	public MucKhuyenMai() {
	}

	public MucKhuyenMai(String tenMucKhuyenMai, float tiLeKhuyenMai) {
		super();
		this.tenMucKhuyenMai = tenMucKhuyenMai;
		this.tiLeKhuyenMai = tiLeKhuyenMai;
	}

	public String getTenMucKhuyenMai() {
		return tenMucKhuyenMai;
	}

	public void setTenMucKhuyenMai(String tenMucKhuyenMai) {
		this.tenMucKhuyenMai = tenMucKhuyenMai;
	}

	public float getTiLeKhuyenMai() {
		return tiLeKhuyenMai;
	}

	public void setTiLeKhuyenMai(float tiLeKhuyenMai) {
		this.tiLeKhuyenMai = tiLeKhuyenMai;
	}

	public ChuongTrinhKhuyenMai getChuongTrinhKhuyenMai() {
		return chuongTrinhKhuyenMai;
	}

	public void setChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai chuongTrinhKhuyenMai) {
		this.chuongTrinhKhuyenMai = chuongTrinhKhuyenMai;
	}

	@Override
	public String toString() {
		return "MucKhuyenMai [tenMucKhuyenMai=" + tenMucKhuyenMai + ", tiLeKhuyenMai=" + tiLeKhuyenMai + "]";
	}

	
}
