package bus.impl;

import bus.NhaCungCapBus;
import dao.impl.NhaCungCapDaoImpl;
import entity.NhaCungCap;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class NhaCungCapBusImpl extends UnicastRemoteObject implements NhaCungCapBus {
    private NhaCungCapDaoImpl nhaCungCapDao = new NhaCungCapDaoImpl();

    public NhaCungCapBusImpl() throws RemoteException {
        super();
    }
    public String mes = "";
    @Override
    public boolean themNCC(NhaCungCap nhaCungCap) throws RemoteException {
        return nhaCungCapDao.themNCC(nhaCungCap);
    }

    @Override
    public boolean capNhatNCC(NhaCungCap nhaCungCap) throws RemoteException {
        return nhaCungCapDao.capNhatNCC(nhaCungCap);
    }

    @Override
    public NhaCungCap timNhaCungCapTheoMa(String maNCC) throws RemoteException {
        return nhaCungCapDao.timNhaCungCapTheoMa(maNCC);
    }

    @Override
    public NhaCungCap timNCCTheoTen(String tenNCC) throws RemoteException {
        return nhaCungCapDao.timNCCTheoTen(tenNCC);
    }

    @Override
    public List<NhaCungCap> layDSNhaCungCap() throws RemoteException {
        return nhaCungCapDao.layDSNhaCungCap();
    }

    @Override
    public boolean validData(String maNhaCungCap, String tenNhaCungCap, String diaChi, String sdt, String email) throws RemoteException {
        if(maNhaCungCap.isEmpty()){
            mes = "Vui lòng nhập mã nhà cung cấp";
            return false;
        }
        if(tenNhaCungCap.isEmpty()) {
            mes = "Vui lòng nhập tên nhà cung cấp";
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
            }
        }
        if (email.isEmpty()) {
            mes = "Vui lòng nhập email";
            return false;
        } else if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$")) {
            mes = "Email không hợp lệ";
            return false;
        }
        if(diaChi.isEmpty()) {
            mes = "Vui lòng nhập địa chỉ nhà cung cấp";
            return false;
        }else if(!(diaChi.matches("^[\\p{L}0-9][\\p{L}0-9/.,' -]+"))){
            mes = "Địa chỉ nhà cung cấp không chứa ký tự đặc biệt";
            return false;
        }
        return true;
    }

    @Override
    public String taoMa() throws RemoteException {
        int max = nhaCungCapDao.layDSNhaCungCap().size() + 1;
        return "NCC" + max;
    }

    @Override
    public void locNCCTheoSdt(List<NhaCungCap> ds, String sdt) throws RemoteException {
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
    public void locNCCTheoTen(List<NhaCungCap> ds, String ten) throws RemoteException {
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
    public void locNCCTheoDiaChi(List<NhaCungCap> ds, String diaChi) throws RemoteException {
        List<NhaCungCap> dsNcc = new ArrayList<>();
        // chuyển diaChi thành pattern để so khớp
        Pattern p = Pattern.compile(diaChi, Pattern.CASE_INSENSITIVE);
        if(p.matcher("Tất cả").find()) {
            ds.clear();
            ds.addAll(nhaCungCapDao.layDSNhaCungCap());
        }else {
            ds.forEach(ncc -> {
                if(p.matcher(ncc.getDiaChi()).find()){
                    dsNcc.add(ncc);
                }
            });
            ds.clear();
            ds.addAll(dsNcc);
        }
    }

    @Override
    public String getMes() throws RemoteException {
        return this.mes;
    }
}
