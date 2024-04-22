package dao;

import dao.impl.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;

import java.util.List;

public interface ChuongTrinhKhuyenMaiDao {
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoId(String id);
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangSuDung(boolean isStatus);
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai();
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public boolean capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, ChuongTrinhKhuyenMaiDaoImpl.SaleProgramStatus status);
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public void tatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);

    public List<MucKhuyenMai> layDSMucKhuyenMaiCuaCTKM(String ma);
    public boolean themMucKhuyenMaiVaoCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai);
    public boolean xoaMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai);
    public boolean capNhatMucKhuyenMaiCuaCTKM(String maCTKM, MucKhuyenMai mucKhuyenMai);

}
