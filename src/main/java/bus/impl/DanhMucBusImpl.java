package bus.impl;

import bus.DanhMucBus;
import dao.impl.DanhMucDaoImpl;
import entity.DanhMuc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DanhMucBusImpl extends UnicastRemoteObject implements DanhMucBus {
    private DanhMucDaoImpl danhMucDaoImpl ;
    public DanhMucBusImpl() throws RemoteException {
        danhMucDaoImpl = new DanhMucDaoImpl();
    }
    public String mes = "";
    @Override
    public List<DanhMuc> layDSDanhMuc() throws RemoteException {
        return danhMucDaoImpl.layDSDanhMuc();
    }

    @Override
    public DanhMuc timDanhMucTheoMa(String maDanhMuc) throws RemoteException {
        return danhMucDaoImpl.timDanhMucTheoMa(maDanhMuc);
    }

    @Override
    public boolean themDanhMuc(DanhMuc dm) throws RemoteException {
        return danhMucDaoImpl.taoDanhMuc(dm);
    }

    @Override
    public String taoMa() throws RemoteException {
        int max = danhMucDaoImpl.layDSDanhMuc().size() + 1;
        return "DM" + max;
    }

    @Override
    public boolean validData(String ma, String ten) throws RemoteException{
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
    public DanhMuc timDanhMucTheoTen(String ten) throws RemoteException {
        return danhMucDaoImpl.timDanhMucTheoTen(ten);
    }

    @Override
    public String getMes() throws RemoteException {
        return this.mes;
    }
}
