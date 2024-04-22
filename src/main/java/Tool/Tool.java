package Tool;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Tool {

    public static EntityManagerFactory initServer() {
        return Persistence.createEntityManagerFactory("MySql");
    }

    public static String dinhDangTien(double tien) {
        return String.format("%,.0f VND", tien);
    }

    public static String sendEmail(String to, String subject, String message, String messageSuccess) {
        String from = "thuykieu.13032003@gmail.com";
        String password = "tirfdrdpsbjxqemq";

        String host = "smtp.gmail.com";

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
            return "Vui lòng nhập email người nhận";
        }

        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));

            String[] emails = to.split(",");
            InternetAddress[] toAddresses = new InternetAddress[emails.length];
            for (int i = 0; i < emails.length; i++) {
                toAddresses[i] = new InternetAddress(emails[i].trim());
            }
            mimeMessage.setRecipients(Message.RecipientType.TO, toAddresses);

            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport.send(mimeMessage);

        } catch (MessagingException mex) {

        }
        return messageSuccess;
    }
}
