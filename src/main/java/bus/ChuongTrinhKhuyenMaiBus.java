package bus;

import dao.impl.ChuongTrinhKhuyenMaiDaoImpl;
import entity.ChuongTrinhKhuyenMai;
import entity.MucKhuyenMai;

import java.util.List;

public interface ChuongTrinhKhuyenMaiBus {
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiTheoMa(String ma);
    public ChuongTrinhKhuyenMai timChuongTrinhKhuyenMaiDangApDung(boolean status);
    public List<ChuongTrinhKhuyenMai> layDSChuongTrinhKhuyenMai();
    public void tatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public void capNhatTrangThaiChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm, ChuongTrinhKhuyenMaiDaoImpl.SaleProgramStatus status);
    public boolean themChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public boolean capNhatChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
    public boolean validateChuongTrinhKhuyenMai(ChuongTrinhKhuyenMai ctkm);
}
