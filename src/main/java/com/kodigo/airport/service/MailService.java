package com.kodigo.airport.service;

import com.kodigo.airport.utils.Mail;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import java.io.IOException;
import java.security.InvalidParameterException;

@Service
public class MailService implements IMailService{

    @Override
    public boolean sendMail() {
        boolean response;
        String filePath = "";
        String fileName = "report.xlsx";
        String emailToSend = "jmletona@gmail.com";
        String emailTitle = "Airport Report";
        try {
            Mail m = new Mail("settings/mailSettings.prop");
            m.sendEmail(emailToSend, emailTitle, fileName, filePath);
            response = true;
        } catch (InvalidParameterException | IOException | MessagingException ex) {
            response = false;
        }
        return response;
    }
}
