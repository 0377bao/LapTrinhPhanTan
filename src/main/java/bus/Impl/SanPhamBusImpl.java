package bus.Impl;

import bus.SanPhamBus;
import dao.Ipml.SanPhamDaoImpl;
import entity.Sach;
import entity.SanPham;
import entity.VanPhongPham;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SanPhamBusImpl implements SanPhamBus {
    public String mes ="";
    private final String MASACH_STARTWITH = "%SPS%";
    private final String MAVPP_STARTWITH = "%SPVPP%";
    private SanPhamDaoImpl sanPhamDao = new SanPhamDaoImpl();
    @Override
    public List<SanPham> layDSSanPham() {
        return sanPhamDao.layDSSanPham();
    }

    @Override
    public List<SanPham> layDSSachConBan() {
        return sanPhamDao.layDSSanPhamConBan();
    }

    @Override
    public String taoMaSach() {
        long max = sanPhamDao.timMaxSoLuongSanPhamTheoLoai("%S%") + 1;
        return "SPS" + max;
    }

    @Override
    public boolean validDataSach(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap, String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String tacGia, String nhaXB, String namXB) {
        if(maSanPham.isEmpty()) {
            mes = "Vui lòng nhấn chọn Tạo mã";
            return false;
        }

        if (tenSanPham.isEmpty()) {
            mes = "Vui lòng nhập tên sản phẩm";
            return false;
        }
        if (ncc.isEmpty()) {
            mes = "Vui lòng nhập mã nhà cung cấp";
            return false;
        }
        if (theLoai.isEmpty()) {
            mes = "Vui lòng nhập thể loại";
            return false;
        } else if (!theLoai.matches("[\\p{L}0-9/.,' -]+")) {
                mes = "Thể loại không chứa ký tự đặc biệt";
                return false;
        }
        if (!soLuongTon.isEmpty()) {
            try {
                int sl = Integer.parseInt(soLuongTon);
                if (sl < 0) {
                    mes = "Số lượng không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Số lượng phải là số nguyên dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập số lượng";
            return false;
        }
        if (!thue.isEmpty()) {
            try {
                double x = Double.parseDouble(thue);
                if (x < 0) {
                    mes = "Thuế không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Thuế phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập thuế";
            return false;
        }
        if (!giaNhap.isEmpty()) {
            try {
                double x = Double.parseDouble(giaNhap);
                if (x < 0) {
                    mes = "Giá nhập không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Giá nhập phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập giá nhập";
            return false;
        }
        if (!loiNhuan.isEmpty()) {
            try {
                double x = Double.parseDouble(loiNhuan);
                if (x < 0) {
                    mes = "Lợi nhuận không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Lợi nhuận phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập lợi nhuận";
            return false;
        }
        if (ke.isEmpty()) {
            mes = "Vui lòng nhập tên kệ";
            return false;
        } else if (!ke.matches("^[A-C]\\d+$")) {
                mes = "Tên kệ bắt đầu là A hoặc B hoặc C theo sau là số";
                return false;
        }
        if (!namXB.isEmpty()) {
            try {
                LocalDate localDate = LocalDate.now();
                int year = localDate.getYear();
                int nam = Integer.parseInt(namXB);
                if (nam < 0 || nam > year) {
                    mes = "Năm xuất bản không được âm và nhỏ hơn năm hiện tại";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Năm xuất bản phải là số nguyên";
                return false;
            }
        } else {
            mes = "Vui lòng nhập năm xuất bản";
            return false;
        }
        if (tacGia.isEmpty()) {
            mes = "Vui lòng nhập tác giả";
            return false;
        } else if (!tacGia.matches("[\\p{L}0-9/.,' -]+")) {
                mes = "Tác giả không chứa ký tự đặc biệt";
                return false;
        }
        if (nhaXB.isEmpty()) {
            mes = "Vui lòng nhập tên nhà xuất bản";
            return false;
        }
        if (hinhAnh == null) {
            mes = "Vui lòng chọn ảnh";
            return false;
        }
        return true;
    }

    @Override
    public boolean themSanPham(SanPham sp) {
        return sp.getMaSanPham().toUpperCase().startsWith("SPS") ? sanPhamDao.themSanPham((Sach) sp) : sanPhamDao.themSanPham((VanPhongPham) sp);
    }

    @Override
    public List<SanPham> kiemTraTrangThaiSach(String trangThai) {

        return sanPhamDao.layDSSanPhamTheoTrangThai(trangThai, MASACH_STARTWITH);
    }

    @Override
    public boolean capNhatSanPham(SanPham sp) {
        return  sp.getMaSanPham().toUpperCase().startsWith("SPS") ? sanPhamDao.capNhatSach((Sach) sp) : sanPhamDao.capNhatVanPhongPham((VanPhongPham) sp);
    }

    @Override
    public SanPham timKiemSanPham(String maSP) {
        return sanPhamDao.timSachTheoMa(maSP.toUpperCase()) != null ? sanPhamDao.timSachTheoMa(maSP.toUpperCase()) : sanPhamDao.timVanPhongPhamTheoMa(maSP.toUpperCase());
    }

    @Override
    public List<SanPham> locSachTheoTen(List<SanPham> ds, String ten) {
        List<SanPham> temp = new ArrayList<>();
        Pattern p = Pattern.compile(ten, Pattern.CASE_INSENSITIVE);
        for (SanPham sanPham : ds) {
            if(sanPham.getMaSanPham().startsWith(MASACH_STARTWITH)) {
                Matcher m = p.matcher(sanPham.getTenSanPham());
                if (m.find()) {
                    temp.add(sanPham);
                }
            }
        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public List<SanPham> locSachTheoNCC(String maNCC) {
        return sanPhamDao.layDSSanPhamTheoNCC(maNCC, MASACH_STARTWITH);
    }

    @Override
    public List<SanPham> locSachTheoNamXB(List<SanPham> ds, int namXB) {
        ArrayList<SanPham> temp = new ArrayList<>();
        for (SanPham sanPham : ds) {
            if(sanPham instanceof Sach){
                if (((Sach) sanPham).getNamXuatBan() == namXB) {
                    temp.add(sanPham);
                }
            }
        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public List<SanPham> locSachTheoTheLoai(List<SanPham> ds, String theLoai) {
        ArrayList<SanPham> temp = new ArrayList<>();
        Pattern p = Pattern.compile(theLoai, Pattern.CASE_INSENSITIVE);
        for (SanPham sanPham : ds) {
            if(sanPham.getMaSanPham().startsWith(MASACH_STARTWITH)) {
                Matcher m = p.matcher(((Sach)sanPham).getTheLoai());
                if (m.find()) {
                    temp.add(sanPham);
                }
            }
        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public List<SanPham> locSachTheoTacGia(List<SanPham> ds, String tacGia) {
        ArrayList<SanPham> temp = new ArrayList<>();
        Pattern p = Pattern.compile(tacGia, Pattern.CASE_INSENSITIVE);
        for (SanPham sanPham : ds) {
            if(sanPham.getMaSanPham().startsWith(MASACH_STARTWITH)){
                Matcher m = p.matcher(((Sach) sanPham).getTacGia());
                if (m.find()) {
                    temp.add(sanPham);
                }
            }
        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public List<SanPham> layDSSachGanHet() {
        return sanPhamDao.layDSSachGanHet();
    }

    @Override
    public List<SanPham> locVPPTheoNCC(String maNCC) {
        return sanPhamDao.layDSSanPhamTheoNCC(maNCC, MAVPP_STARTWITH);
    }

    @Override
    public List<SanPham> locVPPTheoDanhMuc(String danhMuc, List<SanPham> dsVPP) {
        List<SanPham> temp = new ArrayList<>();
        for (SanPham sanPham : dsVPP) {
            if (sanPham.getMaSanPham().startsWith(MAVPP_STARTWITH) && danhMuc.equals("Tất cả")) {
                temp.add(sanPham);
            } else {
                if (sanPham.getMaSanPham().startsWith(MAVPP_STARTWITH)
                        && ((VanPhongPham) sanPham).getDanhMuc().getTenDanhMuc().equals(danhMuc)) {
                    temp.add(sanPham);
                }
            }
        }
        dsVPP.clear();
        dsVPP.addAll(temp);
        return dsVPP;
    }

    @Override
    public List<SanPham> locVPPTheoTheLoai(List<SanPham> ds, String theLoai) {
        List<SanPham> temp = new ArrayList<>();
        Pattern p = Pattern.compile(theLoai, Pattern.CASE_INSENSITIVE);
        for (SanPham sanPham : ds) {
            if(sanPham.getMaSanPham().startsWith(MAVPP_STARTWITH)) {
                Matcher m = p.matcher(((VanPhongPham)sanPham).getTheLoai());
                if (m.find()) {
                    temp.add(sanPham);
                }
            }

        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public List<SanPham> kiemTraTrangThaiVPP(String trangThai) {
        return sanPhamDao.layDSSanPhamTheoTrangThai(trangThai, MAVPP_STARTWITH);
    }

    @Override
    public List<SanPham> locVPPTheoTen(List<SanPham> ds, String ten) {
        List<SanPham> temp = new ArrayList<>();
        Pattern p = Pattern.compile(ten, Pattern.CASE_INSENSITIVE);
        for (SanPham sanPham : ds) {
            if(sanPham.getMaSanPham().startsWith(MAVPP_STARTWITH)) {
                Matcher m = p.matcher(((VanPhongPham)sanPham).getTenSanPham());
                if (m.find()) {
                    temp.add(sanPham);
                }
            }

        }
        ds.clear();
        ds.addAll(temp);
        return ds;
    }

    @Override
    public String taoMaVPP() {
        long max = sanPhamDao.timMaxSoLuongSanPhamTheoLoai("%VPP%") + 1;
        return "SPVPP" + max;
    }

    @Override
    public boolean validDataVPP(String maSanPham, String tenSanPham, String ncc, String soLuongTon, String giaNhap, String theLoai, String ke, String hinhAnh, String thue, String loiNhuan, String chatLieu) {
        if (maSanPham.isEmpty()) {
            mes = "Vui lòng nhấn chọn Tạo mã";
            return false;
        }
        if (tenSanPham.isEmpty()) {
            mes = "Vui lòng nhập tên sản phẩm";
            return false;
        }
        if (ncc.isEmpty()) {
            mes = "Vui lòng nhập mã nhà cung cấp";
            return false;
        }
        if (ke.isEmpty()) {
            mes = "Vui lòng nhập tên kệ";
            return false;
        } else {
            if (!ke.matches("^[D-F]\\d+$")) {
                mes = "Tên kệ bắt đầu là D hoặc E hoặc F theo sau là số";
                return false;
            }
        }

        if (!soLuongTon.isEmpty()) {
            try {
                int sl = Integer.parseInt(soLuongTon);
                if (sl < 0) {
                    mes = "Số lượng không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Số lượng phải là số nguyên dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập số lượng";
            return false;
        }
        if (!thue.isEmpty()) {
            try {
                double x = Double.parseDouble(thue);
                if (x < 0) {
                    mes = "Thuế không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Thuế phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập thuế";
            return false;
        }
        if (!giaNhap.isEmpty()) {
            try {
                double x = Double.parseDouble(giaNhap);
                if (x < 0) {
                    mes = "Giá nhập không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Giá nhập phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập giá nhập";
            return false;
        }
        if (!loiNhuan.isEmpty()) {
            try {
                double x = Double.parseDouble(loiNhuan);
                if (x < 0) {
                    mes = "Lợi nhuận không được âm";
                    return false;
                }
            } catch (NumberFormatException e) {
                mes = "Lợi nhuận phải là số dương";
                return false;
            }
        } else {
            mes = "Vui lòng nhập lợi nhuận";
            return false;
        }
        if (theLoai.isEmpty()) {
            mes = "Vui lòng nhập thể loại";
            return false;
        } else {
            if (!theLoai.matches("[\\p{L}0-9 ]+")) {
                mes = "Thể loại không chứa ký tự đặc biệt và số";
                return false;
            }
        }
        if (chatLieu.isEmpty()) {
            mes = "Vui lòng nhập chất liệu";
            return false;
        } else {
            if (!chatLieu.matches("[\\p{L}0-9 ]+")) {
                mes = "Chất liệu không chứa ký tự đặc biệt";
                return false;
            }
        }
        if (hinhAnh == null) {
            mes = "Vui lòng chọn ảnh";
            return false;
        }
        return true;
    }

    @Override
    public boolean capNhatSoLuongTonSanPham(SanPham sp) {
        return sanPhamDao.capNhatSoLuongTonSanPham(sp);
    }
}
