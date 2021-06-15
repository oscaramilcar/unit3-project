package org.kodigo.project.notifications;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public class EmailSender implements IFlightNotifier{
    @Override
    public void notify(String pdfFile, String excelFile) {
        final String username = "pc340355@gmail.com";
        final String password = "P@ss.$$#test";

        String fromEmail = "pc340355@gmail.com";
        String toEmail = "oscar454534@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "25"); //port Gmail SMTP 25 o 465

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start message email
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Reports");

            Multipart content = new MimeMultipart();

            //Text body part
            MimeBodyPart bodyContent = new MimeBodyPart();
            bodyContent.setText("Flight reports!!");

            //pdf Attachment
            MimeBodyPart pdfAttacment = new MimeBodyPart();
            pdfAttacment.attachFile(pdfFile);

            MimeBodyPart txtAttacment = new MimeBodyPart();
            txtAttacment.attachFile(excelFile);

            content.addBodyPart(bodyContent);
            content.addBodyPart(pdfAttacment);
            content.addBodyPart(txtAttacment);

            //Attach multipart to message
            msg.setContent(content);

            Transport.send(msg);
            System.out.println("Report Sent Successfully!!");
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
    }
}
