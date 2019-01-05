package com.elbraulio.survey.mail;

import com.elbraulio.survey.utils.BProperties;
import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * @author Braulio Lopez (brauliop.3@gmail.com)
 */
public final class Mail {
    private final MailContent content;
    private final Logger logger = Logger.getLogger(Mail.class);

    public Mail(MailContent content) {
        this.content = content;
    }

    public void send() {
        if(content.to().trim().isEmpty()) {
            return;
        }
        try {
            logger.info("sending mail to " + content.to());
            BProperties bProps = new BProperties();
            final String user = bProps.prop("user");
            final String pass = bProps.prop("pass");
            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", bProps.prop("smtp.ssl.trust"));
            props.put("mail.smtp.host", bProps.prop("smtp.host"));
            props.put("mail.smtp.port", bProps.prop("smtp.port"));
            props.put("mail.smtp.auth", bProps.prop("smtp.auth"));
            props.put("mail.smtp.starttls.enable", bProps.prop("smtp.starttls.enable"));
            Authenticator auth = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, pass);
                }
            };
            Session session = Session.getInstance(props, auth);
            String fromEmail = bProps.prop("user");
            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setFrom(new InternetAddress(fromEmail,
                    "rosgh survey for research on ROS Answers"));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
            msg.setSubject(content.subject(), "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(
                            content.to(), false
                    )
            );
            Multipart multipart = new MimeMultipart();
            BodyPart htmlBodyPart = new MimeBodyPart();
            htmlBodyPart.setContent(content.body(), "text/html; charset=utf-8");
            multipart.addBodyPart(htmlBodyPart);
            msg.setContent(multipart);
            Transport.send(msg);
        } catch (UnsupportedEncodingException | MessagingException e) {
            this.logger.error("Error sending mail", e);
        }
    }
}
