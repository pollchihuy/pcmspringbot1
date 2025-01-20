package com.example.pcmspringbot1.core;

import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.config.SMTPConfig;
import com.example.pcmspringbot1.util.LoggingFile;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Callable;

//public class SMTPCore implements Callable<String> {
public class SMTPCore{

    Properties prop ;
    private Message message ;
    private Session session;
    private String strDestination;
    private StringBuilder sBuild = new StringBuilder();
    private MimeBodyPart messageBodyPart;
    private Multipart multipart;

    private String[] mailTo;
    private String subject;
    private String content;
    private String [] attachment;

//    public SMTPCore(String []mailTo, String subject, String content, String[] attachment) {
//        this.mailTo = mailTo;
//        this.subject = subject;
//        this.content = content;
//        this.attachment = attachment;
//    }
//
//    @Override
//    public String call() throws Exception {
//        sendMailWithAttachment(mailTo,subject,content,"SSL",attachment);
//        return "";
//    }

    private Properties getTLSProp()
    {
        prop = new Properties();
        prop.put("mail.smtp.host", SMTPConfig.getEmailHost());
        prop.put("mail.smtp.port", SMTPConfig.getEmailPortTLS());
        prop.put("mail.smtp.auth", SMTPConfig.getEmailAuth());
        prop.put("mail.smtp.starttls.enable", SMTPConfig.getEmailStartTLSEnable());
        prop.put("mail.smtp.timeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));
        prop.put("mail.smtp.connectiontimeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));
        prop.put("mail.smtp.writetimeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));
        return prop;
    }

    private Properties getSSLProp()
    {
        prop = new Properties();
        prop.put("mail.smtp.host", SMTPConfig.getEmailHost());
        prop.put("mail.smtp.port", SMTPConfig.getEmailPortSSL());
        prop.put("mail.smtp.auth", SMTPConfig.getEmailAuth());
        prop.put("mail.smtp.socketFactory.port", SMTPConfig.getEmailStartTLSEnable());
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.timeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));
        prop.put("mail.smtp.connectiontimeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));
        prop.put("mail.smtp.writetimeout", (Long.parseLong(SMTPConfig.getEmailSMTPTimeout())*1000));

        return prop;
    }

    public boolean sendMailWithAttachment(String[] strMailTo,
                                          String strSubject,
                                          String strContentMessage,
                                          String strLayer, String[] attachFiles) {
        Properties execProp ;


        if(strLayer.equals("SSL")) {
            execProp = getSSLProp();
        }
        else
        {
            execProp = getTLSProp();
        }

        sBuild.setLength(0);
        for(int i=0;i<strMailTo.length;i++)
        {
            sBuild.append(strMailTo[i]).append(",");

        }
        strDestination = sBuild.toString();
        strDestination = strDestination.substring(0,strDestination.length()-1);

        session = Session.getInstance(execProp,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SMTPConfig.getEmailUserName(), SMTPConfig.getEmailPassword());
                    }
                });


        try {
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTPConfig.getEmailUserName()));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(strDestination)
            );
            message.setSentDate(new Date());
            message.setSubject(strSubject);

            // creates message part
            messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(strContentMessage, "text/html");

            // creates multi-part
            multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // adds attachments
            if (attachFiles != null && attachFiles.length > 0) {
                for (String filePath : attachFiles) {
                    MimeBodyPart attachPart = new MimeBodyPart();

                    try {
                        attachPart.attachFile(filePath);
                    } catch (Exception ex) {
                        throw new Exception(ex.getMessage());
                    }
                    multipart.addBodyPart(attachPart);
                }
            }

            // sets the multi-part as e-mail's content
            message.setContent(multipart);

            // sends the e-mail
            Transport.send(message);
        } catch (Exception e) {
            LoggingFile.logException("SMTPCORE","sendMailWithAttachment",e, OtherConfig.getEnableLogFile());
            return false;
        }
        return true;
    }
}