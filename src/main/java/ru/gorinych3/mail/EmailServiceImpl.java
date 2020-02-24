package ru.gorinych3.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import ru.gorinych3.MyProp;

import javax.mail.internet.MimeMessage;
import java.io.InputStream;

@Component
public class EmailServiceImpl implements JavaMailSender {

    @Qualifier("getJavaMailSender")
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private MyProp property;

    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(property.getUsername());
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }

    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {

    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {

    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
