package entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("VanPhongPham")
public class VanPhongPham extends SanPham {
	@Column(name = "chatLieu", columnDefinition = "nvarchar(50)")
	private String chatLieu;
	@ManyToOne
	@JoinColumn(name = "maDanhMuc")
	private DanhMuc danhMuc;

	public VanPhongPham() {
	}

	public VanPhongPham(String maSanPham, String tenSanPham, int soLuongTon, float giaNhap, String theLoai, String ke,
			String hinhAnh, float thue, float phanTramLoiNhuan, String trangThai, NhaCungCap nhaCungCap,
			String chatLieu, DanhMuc danhMuc) {
		super(maSanPham, tenSanPham, soLuongTon, giaNhap, theLoai, ke, hinhAnh, thue, phanTramLoiNhuan,
				trangThai, nhaCungCap);
		this.chatLieu = chatLieu;
		this.danhMuc = danhMuc;
	}
	public String getChatLieu() {
		return chatLieu;
	}

	public void setChatLieu(String chatLieu) {
		this.chatLieu = chatLieu;
	}

	public DanhMuc getDanhMuc() {
		return danhMuc;
	}

	public void setDanhMuc(DanhMuc danhMuc) {
		this.danhMuc = danhMuc;
	}

	@Override
	public String toString() {
		return super.toString() + "VanPhongPham [chatLieu=" + chatLieu + ", danhMuc=" + danhMuc + "]";
	}

}
