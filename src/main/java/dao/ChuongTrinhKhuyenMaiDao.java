package dao;

import dao.Ipml.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;

import java.util.List;

public interface ChuongTrinhKhuyenMaiDao {
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoId(String id);
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangSuDung(boolean isStatus);
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai();
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public boolean capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, ChuongTrinhKhuyenMaiDaoImpl.SaleProgramStatus status);
}
