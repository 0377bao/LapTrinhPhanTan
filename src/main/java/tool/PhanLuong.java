package tool;


import ui.PopUp;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class PhanLuong extends SwingWorker<Void, Void> {
    private PopUp frame;
    private String mail1;
    private String subject;
    private String message;
    private String messageSuccess;

    public PhanLuong(PopUp frame, String mail1, String subject, String message, String messageSuccess) {
        super();
        this.messageSuccess = messageSuccess;
        this.frame = frame;
        this.mail1 = mail1;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void process(java.util.List<Void> chunks) {
        // Hiển thị thông báo trong EDT
        frame.setVisible(true);
    }

    @Override
    protected void done() {
        // Ẩn thông báo khi công việc kết thúc
        frame.setVisible(false);
    }


    @Override
    protected Void doInBackground() throws Exception {
        // TODO Auto-generated method stub
        publish();
        String mess = Tool.sendEmail(mail1, subject, message, messageSuccess);
        JOptionPane.showMessageDialog(null, mess);
        return null;
    }


}
