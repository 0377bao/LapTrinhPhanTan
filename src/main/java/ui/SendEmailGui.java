package ui;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class SendEmailGui extends JFrame {
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtTo;
	private JTextField txtSubject;
	private JTextArea mes;

	public SendEmailGui() {
		setTitle("SendEmail");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Send", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(40, 20, 400, 100);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(20, 25, 80, 13);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(20, 59, 80, 13);
		panel.add(lblNewLabel_1);

		txtUsername = new JTextField();
		txtUsername.setBounds(97, 22, 275, 19);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(97, 56, 275, 19);
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		txtUsername.setText("thuykieu.13032003@gmail.com");
		txtPassword.setText("tirfdrdpsbjxqemq");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Receive",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(40, 159, 400, 194);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("To");
		lblNewLabel_2.setBounds(20, 25, 45, 13);
		panel_1.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Subject");
		lblNewLabel_3.setBounds(20, 60, 80, 13);
		panel_1.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Message");
		lblNewLabel_4.setBounds(20, 96, 80, 13);
		panel_1.add(lblNewLabel_4);

		txtTo = new JTextField();
		txtTo.setBounds(97, 22, 271, 19);
		panel_1.add(txtTo);
		txtTo.setColumns(10);

		txtSubject = new JTextField();
		txtSubject.setBounds(97, 57, 271, 19);
		panel_1.add(txtSubject);
		txtSubject.setColumns(10);

		mes = new JTextArea();
		mes.setBounds(97, 90, 271, 81);
		panel_1.add(mes);

		txtTo.setText("thuykieu130303@gmail.com,trongdat1822@gmail.com,vomanhhieu62@gmail.com");
		txtSubject.setText("HELLO");
		mes.setText("Chúc bạn may mắn lần sau");
		
		JButton btnNewButton = new JButton("SEND");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendEmail(txtUsername.getText(), txtPassword.getText(), txtTo.getText(), txtSubject.getText(),
						mes.getText());
			}
		});
		btnNewButton.setBackground(new Color(128, 255, 128));
		btnNewButton.setBounds(355, 388, 85, 21);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(245, 388, 85, 21);
		getContentPane().add(btnNewButton_1);

	}

	public void sendEmail(String from, String password, String to, String subject, String message) {
//        String from = "thuykieu.13032003@gmail.com";  
//        String password = "tirfdrdpsbjxqemq";  

		String host = "smtp.gmail.com"; // For Gmail

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		if (to.isEmpty()) {
	        JOptionPane.showMessageDialog(this, "Please provide at least one recipient address.");
	        return;
	    }
		
		try {
			MimeMessage mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(from));
//			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			String[] emails = to.split(",");
            InternetAddress[] toAddresses = new InternetAddress[emails.length];
            for (int i = 0; i < emails.length; i++) {
                toAddresses[i] = new InternetAddress(emails[i].trim());
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);
            
			mimeMessage.setSubject(subject);
			mimeMessage.setText(message);

			Transport.send(mimeMessage);
			JOptionPane.showMessageDialog(this, "Email sent successfully");
		} catch (MessagingException mex) {
			JOptionPane.showMessageDialog(this, "Error: " + mex.getMessage());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SendEmailGui emailSender = new SendEmailGui();
			emailSender.setVisible(true);
		});
	}
}
