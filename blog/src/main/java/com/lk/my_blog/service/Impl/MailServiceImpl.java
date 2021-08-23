package com.lk.my_blog.service.Impl;

import com.lk.my_blog.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.MessageExpression;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author: 刘康
 * @Date: 2021/7/29 14:14
 * @Description: 邮件发送实现类
 */
@Service
public class MailServiceImpl implements MailService {
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMail(String to, String subject, String context,String from) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(context);
        simpleMailMessage.setFrom(from);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendAttachmentsMail(String to, String subject, String context, String filePath){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(context,true);
            messageHelper.setFrom(from);
            FileSystemResource resource = new FileSystemResource(new File(filePath));
            String filename = resource.getFilename();
            messageHelper.addAttachment(filename,resource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        mailSender.send(mimeMessage);

    }

    @Override
    public void sendImageMail(String to, String subject, String context, String rscPath, String rscId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper=null;
        try {
            messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(context,true);
            messageHelper.setFrom(from);
            FileSystemResource resource = new FileSystemResource(new File(rscPath));
            messageHelper.addInline(rscId,resource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMessage);
    }
}
