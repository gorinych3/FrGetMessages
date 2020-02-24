package ru.gorinych3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Properties;

@EnableJpaRepositories("ru.gorinych3.repositories")
@EntityScan("ru.gorinych3.models")
@SpringBootApplication
public class Application {

    @Autowired
    private MyProp property;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    TaskScheduler threadPoolTaskShcheduler(){
        return new ThreadPoolTaskScheduler();
    }



    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(property.getUsername());
        mailSender.setPassword(property.getPassword());


        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.debug", "true");
        props.put("mail.smtp.user", property.getUsername());
        props.put("mail.smtp.password", property.getPassword());


        return mailSender;
    }


    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(property.getUsername());
        message.setText(
                "This is the test email template for your email:\n%s\n");
        return message;
    }

}
