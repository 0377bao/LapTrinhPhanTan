package bus;

import entity.DanhMuc;

import java.util.List;

public interface DanhMucBus {
    public List<DanhMuc> layDSDanhMuc();
    public DanhMuc timDanhMucTheoMa(String maDanhMuc);
    public boolean themDanhMuc(DanhMuc dm);
    public String taoMa();
    public boolean validData(String ma, String ten);
    public DanhMuc timDanhMucTheoTen(String ten);

}
