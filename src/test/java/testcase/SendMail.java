package testcase;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

    public static void main(String[]args) throws IOException {

        final String username = "amreen.noor@thomsonreuters.com";
        final String password ="Numan@12";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amreen.noor@thomsonreuters.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("rashmi.p@thomsonreuters.com"));
            message.setSubject("Test");
            message.setText("HI");

            //Transport.send(message);

          //  System.out.println("Done");
            MimeBodyPart messageBodyPart = new MimeBodyPart();

            Multipart multipart = new MimeMultipart();

            messageBodyPart = new MimeBodyPart();
            String file = "C:\\Users\\UX011746\\Desktop\\ServiceNowQAAutomation\\ServiceNowQAAutomation\\SNOWQA\\test-output\\STMExtentReport11.html";
            String fileName = "STMExtentReport11";
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            System.out.println("Sending");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
