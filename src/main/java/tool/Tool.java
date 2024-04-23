
package tool;

import java.io.FileOutputStream;
import java.text.Format;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import bus.impl.HoaDonBusImpl;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.ChiTietHoaDon;
import entity.ChuongTrinhKhuyenMai;
import entity.DonGiaoHang;
import entity.HoaDon;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Tool {
	public static EntityManagerFactory initServer() {
		return Persistence.createEntityManagerFactory("MySql");
	}
	public static String dinhDangTien(float tien) {
		return String.format("%,.0f VND", tien);
	}

	public static void inHoaDon(HoaDon hoaDonIn, ChuongTrinhKhuyenMai ctkmCuaHoaDon) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("src/bill/" + hoaDonIn.getMaHoaDon() + ".pdf"));
			document.open();

			// Sử dụng font chữ hỗ trợ UTF-8 (ví dụ: Arial Unicode MS)
			BaseFont bf = BaseFont.createFont("src/font/arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 12);
			Font fontBold = new Font(bf, 12, Font.BOLD);
			Font fontItalic = new Font(bf, 12, Font.ITALIC);
			Font titleFont = new Font(font.getBaseFont(), 14, Font.NORMAL);
			Font titleFontBold = new Font(font.getBaseFont(), 14, Font.BOLD);

			// Tiêu đề hóa đơn
			Paragraph tenCuaHang = new Paragraph("NHÀ SÁCH HBDK", titleFont);
			tenCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(tenCuaHang);
			Paragraph diaChiCuaHang = new Paragraph(
					"12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh, Việt Nam", titleFont);
			diaChiCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(diaChiCuaHang);
			Paragraph sdtCuaHang = new Paragraph("SDT: 0353426938\n\n", titleFont);
			sdtCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(sdtCuaHang);
			Paragraph title = new Paragraph("HÓA ĐƠN BÁN HÀNG\n\n", titleFontBold);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			// Logo công ty (đổi đường dẫn ảnh)
			// Image logo = Image.getInstance("src/logo.png");
			// logo.scaleAbsolute(100f, 100f);
			// logo.setAlignment(Element.ALIGN_CENTER);
			// document.add(logo);

			// Thông tin khách hàng
			Paragraph tenCTKM = new Paragraph("Tên CTKM: " + hoaDonIn.getCtkm().getTenCTKM(), font);
			document.add(tenCTKM);
			Paragraph ngayMua = new Paragraph("Ngày mua: " + hoaDonIn.getNgayLap(), font);
			document.add(ngayMua);
			Paragraph nhanVienBanHang = new Paragraph("Nhân viên bán hàng: " + hoaDonIn.getNhanVien().getTenNhanVien(),
					font);
			document.add(nhanVienBanHang);
			Paragraph tenKhachHang = new Paragraph("Tên khách hàng: " + hoaDonIn.getKhachHang().getTenKhachHang(),
					font);
			document.add(tenKhachHang);
			Paragraph diemGiamGia = new Paragraph("Điểm giảm giá: " + hoaDonIn.getDiemGiamGia() + "\n\n", font);
			document.add(diemGiamGia);

			// Bảng chi tiết hóa đơn
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);

			PdfPCell cell = new PdfPCell(new Phrase("Mặt hàng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Số lượng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Đơn giá", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Thuế", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Giảm giá", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Tổng cộng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Thêm các hàng dữ liệu vào bảng
			// Ví dụ:

			for (ChiTietHoaDon cthd : hoaDonIn.getDsChiTietHoaDon()) {
				table.addCell(new Phrase(cthd.getSanPham().getTenSanPham(), font));
				table.addCell(new Phrase(cthd.getSoLuongMua() + "", font));
				table.addCell(new Phrase(dinhDangTien(cthd.getSanPham().getGiaBan()) + "", font));
				table.addCell(new Phrase(cthd.getSanPham().getThue() + "%", font));
				float giamGia = new HoaDonBusImpl().hamLayGiamGiaCuaChiTietHoaDon(ctkmCuaHoaDon, cthd.getSanPham());
				table.addCell(new Phrase(giamGia + "%", font));
				table.addCell(new Phrase(dinhDangTien(cthd.tinhThanhTien() * (1 - giamGia / 100)), font));
			}

			table.setWidths(new float[] { 2, 1, 1, 1, 1, 1 });
			document.add(table);

			// bảng tổng cộng
			PdfPTable tableTongCong = new PdfPTable(4);
			tableTongCong.setWidthPercentage(100);
			tableTongCong.setSpacingBefore(10f);
			tableTongCong.setSpacingAfter(10f);
			Paragraph paragraph1 = new Paragraph("Tổng số lượng:", font);
			PdfPCell cell1 = new PdfPCell(paragraph1);
			cell1.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell1);

			int tongSoLuong = 0;
			for (ChiTietHoaDon cthd : hoaDonIn.getDsChiTietHoaDon()) {
				tongSoLuong += cthd.getSoLuongMua();
			}

			Paragraph paragraph2 = new Paragraph(tongSoLuong + "", fontBold);
			PdfPCell cell2 = new PdfPCell(paragraph2);
			cell2.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell2);

			Paragraph paragraph3 = new Paragraph("Thành tiền: ", font);
			PdfPCell cell3 = new PdfPCell(paragraph3);
			cell3.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell3);

			Paragraph paragraph4 = new Paragraph(dinhDangTien(hoaDonIn.getThanhTien()), fontBold);
			PdfPCell cell4 = new PdfPCell(paragraph4);
			cell4.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell4);

			document.add(tableTongCong);

			// Chữ ký
			Paragraph signature = new Paragraph("\n===================================", font);
			signature.setAlignment(Element.ALIGN_CENTER);
			document.add(signature);

			Paragraph thanks = new Paragraph("CHÂN THÀNH CẢM ƠN QUÝ KHÁCH ĐÃ MUA HÀNG", fontItalic);
			thanks.setAlignment(Element.ALIGN_CENTER);
			document.add(thanks);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void inPhieuGiaoHang(DonGiaoHang dgh) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream("src/bill/" + dgh.getMaDonGiaoHang() + ".pdf"));
			document.open();

			// Sử dụng font chữ hỗ trợ UTF-8 (ví dụ: Arial Unicode MS)
			BaseFont bf = BaseFont.createFont("src/font/arial-unicode-ms.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 12);
			Font fontBold = new Font(bf, 12, Font.BOLD);
			Font fontItalic = new Font(bf, 12, Font.ITALIC);
			Font titleFont = new Font(font.getBaseFont(), 14, Font.NORMAL);
			Font titleFontBold = new Font(font.getBaseFont(), 14, Font.BOLD);

			// Tiêu đề hóa đơn
			Paragraph tenCuaHang = new Paragraph("NHÀ SÁCH HBDK", titleFont);
			tenCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(tenCuaHang);
			Paragraph diaChiCuaHang = new Paragraph(
					"12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh, Việt Nam", titleFont);
			diaChiCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(diaChiCuaHang);
			Paragraph sdtCuaHang = new Paragraph("SDT: 0353426938\n\n", titleFont);
			sdtCuaHang.setAlignment(Element.ALIGN_CENTER);
			document.add(sdtCuaHang);
			Paragraph title = new Paragraph("PHIẾU GIAO HÀNG\n\n", titleFontBold);
			title.setAlignment(Element.ALIGN_CENTER);
			document.add(title);

			Paragraph line = new Paragraph(
					"------------------------------------------------------------------------------");
			line.setAlignment(Element.ALIGN_CENTER);
			document.add(line);

			// Thông tin đơn hàng
			Paragraph maDonHang = new Paragraph("Mã đơn hàng: " + dgh.getMaDonGiaoHang(), font);
			document.add(maDonHang);
			Paragraph ngayMua = new Paragraph("Ngày mua: " + dgh.getHoaDon().getNgayLap(), font);
			document.add(ngayMua);
			Paragraph nhanVienBanHang = new Paragraph(
					"Nhân viên bán hàng: " + dgh.getHoaDon().getNhanVien().getTenNhanVien(), font);
			document.add(nhanVienBanHang);
			Paragraph tenKhachHang = new Paragraph("Tên khách hàng: " + dgh.getTenKhachHang(), font);
			document.add(tenKhachHang);
			Paragraph sdt = new Paragraph("Số điện thoại: " + dgh.getSdt(), font);
			document.add(sdt);
			Paragraph diaChi = new Paragraph("Địa chỉ: " + dgh.getDiaChi(), font);
			document.add(diaChi);
			Paragraph trongLuong = new Paragraph("Trọng lượng: " + dgh.getSoKg() + " kg", font);
			document.add(trongLuong);
			Paragraph title_ds = new Paragraph("Danh sách sản phẩm: ", font);
			document.add(title_ds);

			// Bảng chi tiết hóa đơn
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10f);
			PdfPCell cell = new PdfPCell(new Phrase("STT", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Mặt hàng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Số lượng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Đơn giá", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Thuế", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Giảm giá", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			cell = new PdfPCell(new Phrase("Tổng cộng", font));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Thêm các hàng dữ liệu vào bảng

			int stt = 0;
			for (ChiTietHoaDon cthd : dgh.getHoaDon().getDsChiTietHoaDon()) {
				table.addCell(new Phrase(++stt + "", font));
				table.addCell(new Phrase(cthd.getSanPham().getTenSanPham(), font));
				table.addCell(new Phrase(cthd.getSoLuongMua() + "", font));
				table.addCell(new Phrase(dinhDangTien(cthd.getSanPham().getGiaBan()) + "", font));
				table.addCell(new Phrase(cthd.getSanPham().getThue() + "%", font));
				float giamGia = new HoaDonBusImpl().hamLayGiamGiaCuaChiTietHoaDon(dgh.getHoaDon().getCtkm(),
						cthd.getSanPham());
				table.addCell(new Phrase(giamGia + "%", font));
				table.addCell(new Phrase(dinhDangTien(cthd.tinhThanhTien() * (1 - giamGia / 100)), font));
			}

			table.setWidths(new float[] { 1, 2, 1, 1, 1, 1, 1 });
			document.add(table);

//	            bảng tổng cộng
			PdfPTable tableTongCong = new PdfPTable(6);
			tableTongCong.setWidthPercentage(100);
			tableTongCong.setSpacingBefore(10f);
			tableTongCong.setSpacingAfter(10f);
			Paragraph paragraph1 = new Paragraph("Tổng số lượng:", font);
			PdfPCell cell1 = new PdfPCell(paragraph1);
			cell1.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell1);

			int tongSoLuong = 0;
			for (ChiTietHoaDon cthd : dgh.getHoaDon().getDsChiTietHoaDon()) {
				tongSoLuong += cthd.getSoLuongMua();
			}

			Paragraph paragraph2 = new Paragraph(tongSoLuong + "", fontBold);
			PdfPCell cell2 = new PdfPCell(paragraph2);
			cell2.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell2);

			Paragraph paragraph3 = new Paragraph("Điểm giảm giá: ", font);
			PdfPCell cell3 = new PdfPCell(paragraph3);
			cell3.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell3);

			Paragraph paragraph4 = new Paragraph(dgh.getHoaDon().getDiemGiamGia() + "", fontBold);
			PdfPCell cell4 = new PdfPCell(paragraph4);
			cell4.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell4);

			Paragraph paragraph5 = new Paragraph("Thành tiền: ", font);
			PdfPCell cell5 = new PdfPCell(paragraph5);
			cell5.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell5);

			Paragraph paragraph6 = new Paragraph(dinhDangTien(dgh.getHoaDon().getThanhTien()), fontBold);
			PdfPCell cell6 = new PdfPCell(paragraph6);
			cell6.setBorder(Rectangle.NO_BORDER);
			tableTongCong.addCell(cell6);

			document.add(tableTongCong);
			Paragraph tienVanChuyen_title = new Paragraph("Tiền vận chuyển: " + dinhDangTien(dgh.getTienVanChuyen()),
					font);
			document.add(tienVanChuyen_title);

			// Chữ ký
			Paragraph signature = new Paragraph("\n===================================", font);
			signature.setAlignment(Element.ALIGN_CENTER);
			document.add(signature);

			Paragraph thanks = new Paragraph("CHÂN THÀNH CẢM ƠN QUÝ KHÁCH ĐÃ MUA HÀNG", fontItalic);
			thanks.setAlignment(Element.ALIGN_CENTER);
			document.add(thanks);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String sendEmail(String to, String subject, String message, String messageSuccess) {
		String from = "thuykieu.13032003@gmail.com";
		String password = "tirfdrdpsbjxqemq";

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		if (to.isEmpty()) {
			return "Vui lòng nhập email người nhận";
		}

		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));

			String[] emails = to.split(",");
			InternetAddress[] toAddresses = new InternetAddress[emails.length];
			for (int i = 0; i < emails.length; i++) {
				toAddresses[i] = new InternetAddress(emails[i].trim());
			}
			mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);

			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

			Transport.send(mimeMessage);

//			return messageSuccess;
		} catch (MessagingException mex) {

		}
		return messageSuccess;
	}

    public static String dinhDangTien(double tien) {
        return String.format("%,.0f VND", tien);
    }


}

