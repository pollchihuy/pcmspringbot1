package com.example.pcmspringbot1.util;

import com.example.pcmspringbot1.config.SMTPConfig;
import com.example.pcmspringbot1.core.SMTPCore;
import java.util.Random;

public class SendMailOTP {

    public static void verifyRegisOTP(String subject, String nama ,String email,String token) {
        try{
            String[] strVerify = new String[3];
            strVerify[0] = subject;
            strVerify[1] = nama;
            strVerify[2] = token;
            String  strContent = new ReadTextFileSB("ver_regis.html").getContentFile();
            strContent = strContent.replace("#JKVM3NH",strVerify[0]);//Kepentingan
            strContent = strContent.replace("XF#31NN",strVerify[1]);//Nama Lengkap
            strContent = strContent.replace("8U0_1GH$",strVerify[2]);//TOKEN
            final String content = strContent;
            System.out.println(SMTPConfig.getEmailHost());
            String [] strEmail = {email};
            String [] strImage = null;//isi kalau mau menggunakan attachment, parameter nya jg diubah
            SMTPCore sc = new SMTPCore();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    sc.sendMailWithAttachment(strEmail,
                            subject,
                            content,
                            "SSL",strImage);
                }
            });
            t.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
