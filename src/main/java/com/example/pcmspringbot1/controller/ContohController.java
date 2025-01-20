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
    public void contohKirimEmail() throws Exception {
        String[] strVerify = new String[3];
        strVerify[0] = "Verifikasi Email";
        strVerify[1] = "Paul Christian";
        strVerify[2] = String.valueOf(new Random().nextInt(111111,999999));

        String strContent = new ReadTextFileSB("ver_regis.html").getContentFile();
        strContent = strContent.replace("#JKVM3NH",strVerify[0]);//Kepentingan
        strContent = strContent.replace("XF#31NN",strVerify[1]);//Nama Lengkap
        strContent = strContent.replace("8U0_1GH$",strVerify[2]);//TOKEN

        System.out.println(SMTPConfig.getEmailHost());
        String [] strEmail = {"poll.chihuy@gmail.com","poll.exact@gmail.com"};
        SMTPCore sc = new SMTPCore();
        sc.sendMailWithAttachment(strEmail,
                "Verifikasi Registrasi",
                strContent,
                "SSL",null);
    }
}
