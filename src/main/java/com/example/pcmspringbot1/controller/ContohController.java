package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.config.SMTPConfig;
import com.example.pcmspringbot1.core.SMTPCore;
import com.example.pcmspringbot1.util.ReadTextFileSB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@RequestMapping("contoh")
public class ContohController {

    @GetMapping("kirim-email")
    public String contohKirimEmail() throws Exception {
        String[] strVerify = new String[3];
        strVerify[0] = "Verifikasi Email";
        strVerify[1] = "Paul Christian";
        strVerify[2] = String.valueOf(new Random().nextInt(111111,999999));
        String  strContent = new ReadTextFileSB("ver_regis.html").getContentFile();
        strContent = strContent.replace("#JKVM3NH",strVerify[0]);//Kepentingan
        strContent = strContent.replace("XF#31NN",strVerify[1]);//Nama Lengkap
        strContent = strContent.replace("8U0_1GH$",strVerify[2]);//TOKEN
        final String content = strContent;
        System.out.println(SMTPConfig.getEmailHost());
        String [] strEmail = {"poll.chihuy@gmail.com","poll.exact@gmail.com"};
        String [] strImage = {"D:\\icon\\SendPhoto.png","D:\\icon\\setting.png"};
        SMTPCore sc = new SMTPCore();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                sc.sendMailWithAttachment(strEmail,
                        "Verifikasi Registrasi",
                        content,
                        "SSL",strImage);
            }
        });
        t.start();
        return "Proses Selesai !!";
    }
}