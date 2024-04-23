package bus.impl;

import bus.ChuongTrinhKhuyenMaiBus;
import dao.ChuongTrinhKhuyenMaiDao;
import dao.impl.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;
import jakarta.persistence.Query;


import java.util.List;

public class ChuongTrinhKhuyenMaiBusImpl implements ChuongTrinhKhuyenMaiBus {
    private String mes = "";
    ChuongTrinhKhuyenMaiDao ctkmDao = new ChuongTrinhKhuyenMaiDaoImpl();

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoMa(String ma) {
        ChuongTrinhKhuyenMai ctkm = ctkmDao.timChuongTrinhKhuyenMaiTheoId(ma);
        if (ctkm != null)
            return ctkm;
        return null;
    }

    @Override
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangApDung(boolean status) {
        ChuongTrinhKhuyenMai ctkm = ctkmDao.timChuongTrinhKhuyenMaiDangSuDung(status);
        if (ctkm != null)
            return ctkm;
        return null;
    }

    @Override
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai() {
        List<ChuongTrinhKhuyenMai> dsCTKM = ctkmDao.layDSChuongTrinhKhuyenMai();
        if (dsCTKM != null)
            return dsCTKM;
        return null;
    }

    @Override
    public void tatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        ctkmDao.tatTrangThaiChuongTrinhKhuyenMai(ctkm);
    }

    @Override
    public void capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, boolean status) {
        ctkmDao.capNhatTrangThaiChuongTrinhKhuyenMai(ctkm, status);
    }

    @Override
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        return ctkmDao.themChuongTrinhKhuyenMai(ctkm);
    }

    @Override
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        return ctkmDao.capNhatChuongTrinhKhuyenMai(ctkm);
    }

    @Override
    public boolean validateChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm) {
        if (ctkm.getMaCTKM().equals("")) {
            mes = "Vui lòng tạo mã chương trình khuyến mãi trước";
            return false;
        }
        if (ctkm.getTenCTKM().equals("") || ctkm.getTenCTKM().startsWith(" ")) {
            mes = "Vui lòng nhập tên chương trình khuyến mãi";
            return false;
        } else if (ctkm.getTenCTKM().startsWith("/")) {
            mes = "Tên chương trình khuyến mãi không bắt đầu bằng dấu /";
            return false;
        } else if (!ctkm.getTenCTKM().matches("^[\\p{L}0-9 ]+\\/?[\\p{L}0-9 ]+$")) {
            mes = "Tên chương trình khuyến mãi không hợp lệ, tên chương trình chỉ được bao gồm chữ, số, dấu / " + "và khoảng trắng, không có kí tự đặc biệt";
            return false;
        }
        return true;
    }

    @Override
    public String getMessage() {
        return mes;
    }

    @Override
    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma) {
        return ctkmDao.layDSMucKhuyenMaiCuaCTKM(ma);
    }

    @Override
    public List<MucKhuyenMai> timMucKhuyenMaiTheoMaCTKM(String maCTKM) {
        return ctkmDao.timMucKhuyenMaiTheoMaCTKM(maCTKM);
    }

    @Override
    public String taoMa() {
        return "CTKM" + (ctkmDao.layDSChuongTrinhKhuyenMai().size() + 1);
    }


    @Override
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        return ctkmDao.themMucKhuyenMaiVaoCTKM(maCTKM, mucKhuyenMai);
    }

    @Override
    public boolean xoaMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        return ctkmDao.xoaMucKhuyenMaiCuaCTKM(maCTKM, mucKhuyenMai);
    }

    @Override
    public boolean capNhatMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai) {
        return ctkmDao.capNhatMucKhuyenMaiCuaCTKM(maCTKM, mucKhuyenMai);
    }

}
