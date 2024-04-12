package bus.Impl;

import bus.KhachHangBus;
import customUI.MyTable;
import dao.Ipml.KhachHangDaoImpl;
import entity.HoaDon;
import entity.KhachHang;

import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KhachHangBusImpl implements KhachHangBus {
    private KhachHangDaoImpl khDI = new KhachHangDaoImpl();
    @Override
    public List<KhachHang> layDSKhachHang() {
        return khDI.LayDSKhachHang();
    }

    @Override
    public String themKhachHang(KhachHang kh) {
        String message = "";
        if(khDI.timKhachHangTheoMa(kh.getMaKhachHang()) != null) {
            message = "Mã khách hàng đã tồn tại";
        } else {
            message = kiemTraThongTinKhachHangHopLe(kh);
            if(message.equals("success")) {
                KhachHang khsdt = khDI.timKhachHangTheoSDT(kh.getSdt());
                if(khsdt != null) {
                    message = "Số điện thoại khách hàng đã tồn tại";
                } else {
                    if(khDI.themKhachHang(kh)) {
                        message = "Thêm khách hàng thành công";
                    } else {
                        message = "Thêm không thành công vì mã khách hàng đã tồn tại";
                    }
                }
            }
        }
        return message;
    }

    @Override
    public String capNhatThongTinKhachHang(KhachHang kh) {
        String message = kiemTraThongTinKhachHangHopLe(kh);
        if(message.equals("success")) {
            KhachHang khsdt = khDI.timKhachHangTheoSDT(kh.getSdt());
            if(khsdt != null && !khsdt.getMaKhachHang().equals(kh.getMaKhachHang())) {
                message = "Số điện thoại khách hàng đã tồn tại";
            } else {
                if(khDI.capNhatThongTinKhachHang(kh)) {
                    message = "Cập nhật khách hàng thành công";
                } else {
                    message = "Cập nhật khách hàng không thành công";
                }
            }
        }
        return message;
    }

    @Override
    public KhachHang timKhachHangTheoSDT(String sdtkh) {
        return khDI.timKhachHangTheoSDT(sdtkh);
    }

    @Override
    public List<KhachHang> locKhachHang(String tenTim, String sdtTim) {
        return khDI.locKhachHang(tenTim, sdtTim);
    }

    @Override
    public boolean capNhatDiemTichLuyKhachHang(KhachHang kh) {
        return khDI.capNhatDiemTichLuyKhachHang(kh);
    }

    @Override
    public String kiemTraThongTinKhachHangHopLe(KhachHang kh) {
        Pattern tenKH = Pattern.compile("^[\\p{L} ]+$");
        Matcher matchTenKh = tenKH.matcher(kh.getTenKhachHang());
        Pattern sdt = Pattern.compile("^(09|08|07|05|03)\\d{8}$");
        Matcher matchSdt = sdt.matcher(kh.getSdt());
        Pattern email = Pattern.compile("^(\\w)+\\@gmail.com$");
        Matcher matchEmail = email.matcher(kh.getEmail());
        // các trường hợp thông tin không hợp lệ, đưa ra message ngoài gui
        if(kh.getMaKhachHang().trim().equals("")) return "Vui lòng nhấn nút tạo mã khách hàng";
        if(kh.getTenKhachHang().trim().equals("")) return "Tên khách hàng không được để trống";
        if(!matchTenKh.find()) {
            return "Tên khách hàng chỉ chứa chữ và khoảng trắng";
        }
        if(kh.getSdt().trim().equals("")) return "SDT khách hàng không được để trống";
        if(!matchSdt.find()) {
            return "Số điện thoại phải có 10 kí tự và bắt đầu bằng (09, 08, 07, 05, 03)";
        }
        if(kh.getEmail().trim().equals("")) return "Email khách hàng không được để trống";
        if(!matchEmail.find()) {
            return "Email khách hàng không hợp lệ";
        }
        // thông tin hợp lệ
        return "success";
    }

    @Override
    public List<HoaDon> layLichSuGiaoDichKhachHang(String maKH, int soGD) {
        return khDI.layLichSuGiaoDichKhachHang(maKH, soGD);
    }

    @Override
    public String taoMaKhachHang() {
        int soKhachHang = khDI.getMaKhachHangMax() + 1;
        return "KH" + soKhachHang;
    }

    @Override
    public void sapXepTheoTongTienMua(MyTable tb, DefaultTableModel model) {
        List<KhachHang> ds = khDI.layDsKhachHangSapXepTheoTongTien();
        model.setRowCount(0);
        for(KhachHang kh: ds) {
            model.addRow(new Object[] {kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getSdt(), kh.getEmail(), kh.getDiemTichLuy(), kh.getTongTienMua()});
        }
        tb.setModel(model);
    }

    @Override
    public KhachHang timKhachHangTheoMa(String maKH) {
        return khDI.timKhachHangTheoMa(maKH);
    }

    @Override
    public List<KhachHang> sapXepTheoTongTien(List<KhachHang> ds) {
        Collections.sort(ds, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang o1, KhachHang o2) {
                Float tongTien1 = o1.getTongTienMua();
                Float tongTien2 = o2.getTongTienMua();
                return tongTien2.compareTo(tongTien1);
            }
        });
        return ds;
    }
}
