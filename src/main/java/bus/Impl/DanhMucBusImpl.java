package bus.Impl;

import bus.DanhMucBus;
import dao.impl.DanhMucDaoImpl;
import entity.DanhMuc;

import java.util.List;

public class DanhMucBusImpl implements DanhMucBus {
    private DanhMucDaoImpl danhMucDao = new DanhMucDaoImpl();
    public String mes = "";
    @Override
    public List<DanhMuc> layDSDanhMuc() {
        return danhMucDao.layDSDanhMuc();
    }

    @Override
    public DanhMuc timDanhMucTheoMa(String maDanhMuc) {
        return danhMucDao.timDanhMucTheoMa(maDanhMuc);
    }

    @Override
    public boolean themDanhMuc(DanhMuc dm) {
        return danhMucDao.taoDanhMuc(dm);
    }

    @Override
    public String taoMa() {
        int max = danhMucDao.layDSDanhMuc().size() + 1;
        return "DM" + max;
    }

    @Override
    public boolean validData(String ma, String ten) {
        if(ma.isEmpty()){
            mes = "Vui lòng nhấn chọn tạo mã";
            return false;
        }
        if(ten.isEmpty()) {
            mes = "Vui lòng nhập tên danh mục";
            return false;
        }else if(!(ten.matches("^[\\p{L}][\\p{L}/.,' -]+"))) {
            mes = "Danh mục không chưa ký tự đặc biệt và số";
            return false;
        }
        return true;
    }

    @Override
    public DanhMuc timDanhMucTheoTen(String ten) {
        return danhMucDao.timDanhMucTheoTen(ten);
    }
}
