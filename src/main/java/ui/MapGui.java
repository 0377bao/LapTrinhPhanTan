package ui;

import customUI.MyButton;
import customUI.MyCombobox;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.util.List;
import java.util.*;

public class MapGui extends JFrame {

    private JPanel contentPane;
    private JXMapViewerCustom Jxmapviewer = new JXMapViewerCustom();
    private TileFactoryInfo info = new OSMTileFactoryInfo();
    private Set<MyWayPoint> myWaypoints = new HashSet<>();
    private EvenWaypoint even;
    private List<RoutingData> routingData = new ArrayList<>();
    private Point mousePoint;
    private JTextField textField;
    private JTextField txtHi;
    private JTextField textField_1;
    private ArrayList resultRoutig = new ArrayList<>();
    private GiaoHangGui guiGH;
    private MapGui frame;

    public MapGui(GiaoHangGui gh) {
        frame = this;
        guiGH = gh;
        this.setTitle("MAP TOOLS");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 1075, 698);
        this.setLocationRelativeTo(null);
        even = this.getEven();

        //xét logo
        ImageIcon logoFrame = new ImageIcon(new ImageIcon("src\\image\\logodangnhap\\logo.png").getImage()
                .getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        this.setIconImage(logoFrame.getImage());

        MyWayPoint wayPoint = new MyWayPoint(new GeoPosition(10.822065, 106.686810), MyWayPoint.PointType.START, even, "Nơi giao hàng");
        addWaypoint(wayPoint);

        setContentPane(Jxmapviewer);

        init();

        JComboBox comboMapType = new MyCombobox();
        comboMapType.addItem("Open stree");
        comboMapType.addItem("Virtual Earth");
        comboMapType.addItem("Hybrid");
        comboMapType.addItem("Satellite");
        comboMapType.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = comboMapType.getSelectedIndex();
                if(index == 0) {
                    info = new OSMTileFactoryInfo();
                } else if (index == 1) {
                    info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
                } else if (index == 2) {
                    info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.HYBRID);
                } else if (index == 3) {
                    info = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.SATELLITE);
                }
                DefaultTileFactory df = new DefaultTileFactory(info);
                Jxmapviewer.setTileFactory(df);
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.setBounds(0, 0, 144, 164);
        addPopup(Jxmapviewer, popupMenu);

        JButton btnNewButton_1 = new JButton("Chọn nơi giao");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(MapGui.this, "Vui lòng nhập địa chỉ nhận hàng");
                }else {
                    GeoPosition geop = Jxmapviewer.convertPointToGeoPosition(mousePoint);
                    MyWayPoint wayPoint = new MyWayPoint(new GeoPosition(geop.getLatitude(), geop.getLongitude()), MyWayPoint.PointType.END, even, "Nơi nhận hàng");
                    addWaypoint(wayPoint);
                    popupMenu.setVisible(false);
                }
            }
        });
        popupMenu.add(btnNewButton_1);
        comboMapType.setBounds(906, 10, 145, 30);
        Jxmapviewer.add(comboMapType);

        JButton btnDatLai = new MyButton("Đặt lại");
        btnDatLai.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cleatWaypoint();
            }
        });
        btnDatLai.setBounds(10, 10, 132, 30);
        Jxmapviewer.add(btnDatLai);

        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        panel.setBounds(0, 542, 1061, 119);
        Jxmapviewer.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nơi Nhận:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(15, 44, 76, 19);
        panel.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBounds(96, 43, 882, 25);
        panel.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Nơi Giao:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(15, 15, 76, 19);
        panel.add(lblNewLabel_1);

        txtHi = new JTextField();
        txtHi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtHi.setText("Trường Đại học Công nghiệp TP.HCM, 12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh, Việt Nam");
        txtHi.setDisabledTextColor(Color.BLACK);
        txtHi.setEnabled(false);
        txtHi.setColumns(10);
        txtHi.setBounds(96, 14, 955, 25);
        panel.add(txtHi);

        JButton btnTim = new MyButton("Tìm");
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String diaChi = textField.getText();
                if(diaChi.equals("")) {
                    JOptionPane.showMessageDialog(MapGui.this, "Vui lòng nhập địa chỉ nhận hàng");
                } else {
                    ArrayList<Double> toaDo = chuyenDoiToaDo(diaChi);
                    if(toaDo.size() == 0) {
                        JOptionPane.showMessageDialog(MapGui.this, "Địa chỉ nhận hàng không hợp lệ");
                    } else {
                        MyWayPoint wayPoint = new MyWayPoint(new GeoPosition(toaDo.get(0), toaDo.get(1)), MyWayPoint.PointType.END, even, "Nơi nhận hàng");
                        addWaypoint(wayPoint);
                        if(myWaypoints.size() == 2) {
                            Jxmapviewer.setAddressLocation(new GeoPosition(toaDo.get(0), toaDo.get(1)));
                        }
                    }
                }
            }
        });
        btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnTim.setBounds(981, 43, 70, 25);
        panel.add(btnTim);

        JLabel lblQungng = new JLabel("Quãng đường:");
        lblQungng.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblQungng.setBounds(372, 81, 114, 19);
        panel.add(lblQungng);

        textField_1 = new JTextField();
        textField_1.setEnabled(false);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField_1.setColumns(10);
        textField_1.setBounds(477, 78, 102, 25);
        panel.add(textField_1);

        JButton btnHoanTat = new MyButton("Hoàn tất");
        btnHoanTat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(textField_1.getText().equals("")) {
                    JOptionPane.showMessageDialog(frame, "Vui lòng nhập địa chỉ nhận hàng và chọn chức năng tìm trước khi hoàn tất");
                    return;
                }else {
                    guiGH.hoanThanhKM(textField_1.getText());
                    guiGH.layDiaChi(textField.getText());
                }
                guiGH.kiemTraDeDongMap(e.getActionCommand(), frame);
                try {
                    guiGH.tinhThanhTienDH();
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnHoanTat.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnHoanTat.setBounds(589, 78, 114, 25);
        panel.add(btnHoanTat);

        Jxmapviewer.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    mousePoint = e.getPoint();
                    popupMenu.show(Jxmapviewer, e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }
    public String getTextFielQuangDuong() {
        return textField.getText();
    }

    public void init() {
        DefaultTileFactory df = new DefaultTileFactory(info);
        Jxmapviewer.setTileFactory(df);
        GeoPosition geo = new GeoPosition(10.822065, 106.686810);
        Jxmapviewer.setAddressLocation(geo);
        Jxmapviewer.setZoom(3);

        MouseInputListener mm = new PanMouseInputListener(Jxmapviewer);
        Jxmapviewer.addMouseListener(mm);
        Jxmapviewer.addMouseMotionListener(mm);
        Jxmapviewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(Jxmapviewer));
        Jxmapviewer.setLayout(null);
    }

    public void initWaypoint() {
        WaypointPainter<MyWayPoint> wp = new WaypointRender();
        wp.setWaypoints(myWaypoints);
        Jxmapviewer.setOverlayPainter(wp);
        for(MyWayPoint d : myWaypoints) {
            Jxmapviewer.add(d.getButton());
        }
        //		routing data
        if(myWaypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (MyWayPoint w : myWaypoints) {
                if(w.getPointType() == MyWayPoint.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == MyWayPoint.PointType.END) {
                    end = w.getPosition();
                }
            }
            if(start != null && end != null) {
                resultRoutig = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
                if(resultRoutig.size() == 0) {
                    JOptionPane.showMessageDialog(MapGui.this, "Nơi nhận hàng không hợp lệ hoặc nằm ngoài phạm vi lãnh thổ Việt Nam");
                    Iterator<MyWayPoint> iterator = myWaypoints.iterator();
                    MyWayPoint lastElement = null;

                    while (iterator.hasNext()) {
                        lastElement = iterator.next();
                    }

                    if (lastElement != null) {
                        Jxmapviewer.remove(lastElement.getButton());
                        myWaypoints.remove(lastElement);
                    }
                }
                else {
                    routingData = (List)resultRoutig.get(0);
                    double roundedNumber = Math.round((double)resultRoutig.get(1) * 10.0) / 10.0;
                    textField_1.setText(roundedNumber + "Km");
                }
            } else {
                routingData.clear();
            }
        }
        Jxmapviewer.setRoutingData(routingData);
    }

    public void cleatWaypoint() {
        for(MyWayPoint d : myWaypoints) {
            Jxmapviewer.remove(d.getButton());
        }
        routingData.clear();
        if (!myWaypoints.isEmpty() && myWaypoints.size() > 1 ) {
            List<MyWayPoint> tempList = new ArrayList<>(myWaypoints);
            MyWayPoint lastElement = tempList.remove(tempList.size() - 1);
            myWaypoints = new HashSet<>(tempList);
        }
        textField.setText("");
        textField_1.setText("");
        initWaypoint();
        GeoPosition geo = new GeoPosition(10.822065, 106.686810);
        Jxmapviewer.setAddressLocation(geo);
    }

    public void addWaypoint(MyWayPoint wayPoint) {
        for(MyWayPoint d : myWaypoints) {
            Jxmapviewer.remove(d.getButton());
        }
        Iterator<MyWayPoint>iter = myWaypoints.iterator();
        while(iter.hasNext()) {
            if(iter.next().getPointType() == wayPoint.getPointType()) {
                iter.remove();
            }
        }
        myWaypoints.add(wayPoint);
        initWaypoint();
    }

    public EvenWaypoint getEven() {
        return new EvenWaypoint() {
            @Override
            public void selected(MyWayPoint wayPoint) {
                JOptionPane.showMessageDialog(MapGui.this, wayPoint.getName());
            }
        };
    }
    private static void addPopup(Component component, final JPopupMenu popup) {
        component.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showMenu(e);
                }
            }
            private void showMenu(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
    }

    public ArrayList<Double> chuyenDoiToaDo(String diaChi) {
        ArrayList<Double> toaDo = new ArrayList<>();
        try {
            String address = diaChi;

            // Tạo URL cho Geocoding API của Nominatim
            String encodedAddress = URLEncoder.encode(address, "UTF-8");
            String apiUrl = "https://nominatim.openstreetmap.org/search?format=json&q=" + encodedAddress;

            // Gửi yêu cầu đến API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Đọc dữ liệu JSON trả về
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }

            reader.close();

            // Xử lý dữ liệu JSON để lấy tọa độ
            // Phân tích dữ liệu JSON
            JSONArray results = new JSONArray(response.toString());

            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                double latitude = firstResult.getDouble("lat");
                double longitude = firstResult.getDouble("lon");
                toaDo.add(Double.valueOf(latitude));
                toaDo.add(Double.valueOf(longitude));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toaDo;
    }



}
