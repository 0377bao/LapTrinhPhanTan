package dao;

import entity.DanhMuc;

import java.util.List;

public interface DanhMucDao {
    public boolean taoDanhMuc(DanhMuc danhMuc);
    public DanhMuc timDanhMucTheoMa(String maDanhMuc);
    public List<DanhMuc> layDSDanhMuc();
    public DanhMuc timDanhMucTheoTen(String tenDanhMuc);

}
