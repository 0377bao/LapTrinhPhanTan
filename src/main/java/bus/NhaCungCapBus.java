package bus;

import entity.NhaCungCap;

import java.util.List;

public interface NhaCungCapBus {
    public boolean themNCC(NhaCungCap nhaCungCap);
    public boolean capNhatNCC(NhaCungCap nhaCungCap);
    public NhaCungCap timNhaCungCapTheoMa(String maNCC);
    public NhaCungCap timNCCTheoTen(String tenNCC);
    public List<NhaCungCap> layDSNhaCungCap();
    public boolean validData(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email);
    public String taoMa();
    public void locNCCTheoSdt(List<NhaCungCap> ds, String sdt);
    public void locNCCTheoTen(List<NhaCungCap> ds, String ten);
    public void locNCCTheoDiaChi(List<NhaCungCap>  ds, String diaChi);
}
