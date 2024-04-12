package bus.Impl;

import bus.NhaCungCapBus;
import dao.Ipml.NhaCungCapDaoImpl;
import entity.NhaCungCap;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NhaCungCapBusImpl implements NhaCungCapBus {
    private NhaCungCapDaoImpl nhaCungCapDao = new NhaCungCapDaoImpl();
    public String mes = "";
    @Override
    public boolean themNCC(NhaCungCap nhaCungCap) {
        return nhaCungCapDao.themNCC(nhaCungCap);
    }

    @Override
    public boolean capNhatNCC(NhaCungCap nhaCungCap) {
        return nhaCungCapDao.capNhatNCC(nhaCungCap);
    }

    @Override
    public NhaCungCap timNhaCungCapTheoMa(String maNCC) {
        return nhaCungCapDao.timNhaCungCapTheoMa(maNCC);
    }

    @Override
    public NhaCungCap timNCCTheoTen(String tenNCC) {
        return nhaCungCapDao.timNCCTheoTen(tenNCC);
    }

    @Override
    public List<NhaCungCap> layDSNhaCungCap() {
        return nhaCungCapDao.layDSNhaCungCap();
    }

    @Override
    public boolean validData(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email) {
        if(maNhaCungCap.isEmpty()){
            mes = "Vui lòng nhập mã nhà cung cấp";
            return false;
        }
        if(tenNhaCungCap.isEmpty()) {
            mes = "Vui lòng nhập tên nhà cung cấp";
            return false;
        }
        if(diaChi.isEmpty()) {
            mes = "Vui lòng nhập địa chỉ nhà cung cấp";
            return false;
        }else if(!(diaChi.matches("^[\\p{L}0-9][\\p{L}0-9/.,' -]+"))){
            mes = "Địa chỉ nhà cung cấp không chứa ký tự đặc biệt";
            return false;
        }
        if(sdt.isEmpty()) {
            mes = "Vui lòng nhập số điện thoại";
            return false;
        }else {
            if(!(sdt.matches("^02\\d{9}$") || sdt.matches("^0[3579]\\d{8}$"))) {
                mes = "Số điện thoại bắt đầu là 02 gồm 11 số hoặc bắt đầu là 03 - 05 - 07 - 09 gồm 10 số";
                return false;
            }else if(nhaCungCapDao.timNhaCungCapTheoMa(maNhaCungCap) == null) {
                for (NhaCungCap ncc : nhaCungCapDao.layDSNhaCungCap()) {
                    if (ncc.getSdt().equals(sdt)) {
                        mes = "Số điện thoại đã tồn tại";
                        return false;
                    }
                }
                return true;
            }
        }
        if (email.isEmpty()) {
            mes = "Vui lòng nhập email";
            return false;
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$")) {
                mes = "Email không hợp lệ";
                return false;
        }
        return true;
    }

    @Override
    public String taoMa() {
        int max = nhaCungCapDao.layDSNhaCungCap().size() + 1;
        return "NCC" + max;
    }

    @Override
    public void locNCCTheoSdt(List<NhaCungCap> ds, String sdt) {
        List<NhaCungCap> dsNcc = new ArrayList<>();
        // chuyển sdt thành pattern để so khớp
        Pattern p = Pattern.compile(sdt, Pattern.CASE_INSENSITIVE);
        ds.forEach(ncc -> {
            if(p.matcher(ncc.getSdt()).find()){
                dsNcc.add(ncc);
            }
        });
        ds.clear();
        ds.addAll(dsNcc);
    }

    @Override
    public void locNCCTheoTen(List<NhaCungCap> ds, String ten) {
        List<NhaCungCap> dsNcc = new ArrayList<>();
        // chuyển ten thành pattern để so khớp
        Pattern p = Pattern.compile(ten, Pattern.CASE_INSENSITIVE);
        ds.forEach(ncc -> {
            if(p.matcher(ncc.getTenNhaCungCap()).find()){
                dsNcc.add(ncc);
            }
        });
        ds.clear();
        ds.addAll(dsNcc);
    }

    @Override
    public void locNCCTheoDiaChi(List<NhaCungCap> ds, String diaChi) {
        List<NhaCungCap> dsNcc = new ArrayList<>();
        // chuyển diaChi thành pattern để so khớp
        Pattern p = Pattern.compile(diaChi, Pattern.CASE_INSENSITIVE);
        ds.forEach(ncc -> {
            if(p.matcher(ncc.getDiaChi()).find()){
                dsNcc.add(ncc);
            }
        });
        ds.clear();
        ds.addAll(dsNcc);
    }
}
