package com.kodigo.airport.utils;


import lombok.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;


public class Mail {

    private final Properties properties;
    private final Session session;

    public Mail(String ruta) throws IOException {
        this.properties = new Properties();
        loadConfig(ruta);
        session = Session.getDefaultInstance(properties);
    }

    private void loadConfig(String path) throws InvalidParameterException, IOException {
        InputStream is = new FileInputStream(path);
        this.properties.load(is);
        checkConfig();
    }

    private void checkConfig() throws InvalidParameterException {

        String[] keys = {
                "mail.smtp.host",
                "mail.smtp.port",
                "mail.smtp.user",
                "mail.smtp.password",
                "mail.smtp.starttls.enable",
                "mail.smtp.auth"
        };

        for (String key : keys) {
            if (this.properties.get(key) == null) {
                throw new InvalidParameterException("password doesn't exist. " + key);
            }
        }
    }

    public void sendEmail( String email, String title, String fileName  , String filePath) throws MessagingException {

        try {
            MimeMessage container = new MimeMessage(session);
            container.setFrom(new InternetAddress((String) this.properties.get("mail.smtp.user")));
            container.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            container.setSubject(title);

            Multipart multipart1 = new MimeMultipart();

            //attachments
            BodyPart messageBodyPart1 = new MimeBodyPart();
            DataSource source1 = new FileDataSource(filePath+fileName);
            messageBodyPart1.setDataHandler(new DataHandler(source1));
            messageBodyPart1.setFileName(fileName);
            multipart1.addBodyPart(messageBodyPart1);

            container.setContent(multipart1); //add attachments and message to Mail

            Transport t = session.getTransport("smtp");
            t.connect((String) this.properties.get("mail.smtp.user"), (String) this.properties.get("mail.smtp.password"));
            t.sendMessage(container, container.getAllRecipients());
        }catch (MessagingException e){
            e.printStackTrace();
        }

    }
}