package dao;

import entity.NhaCungCap;

import java.util.List;

public interface NhaCungCapDao {
    public boolean themNCC(NhaCungCap nhaCungCap);
    public boolean capNhatNCC(NhaCungCap nhaCungCap);
    public NhaCungCap timNhaCungCapTheoMa(String maNCC);
    public NhaCungCap timNCCTheoTen(String tenNCC);
    public List<NhaCungCap> layDSNhaCungCap();
}
