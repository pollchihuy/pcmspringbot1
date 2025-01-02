package com.example.pcmspringbot1.security;

import java.util.function.Function;

public class BcryptImpl {

    private static final BcryptCustom bcrypt = new BcryptCustom(11);

    public static String hash(String password) {
        return bcrypt.hash(password);
    }

    public static boolean verifyAndUpdateHash(String password,
                                              String hash,
                                              Function<String, Boolean> updateFunc) {
        return bcrypt.verifyAndUpdateHash(password, hash, updateFunc);
    }

    public static boolean verifyHash(String password , String hash)
    {
        return bcrypt.verifyHash(password,hash);
    }
    
    public static void main(String[] args) {
        String strUserName = "Paul@123";
//        for (int i = 0; i < 2; i++) {
//            System.out.println("Hash Ke "+(i+1)+" : " +hash(strUserName));
//        }
//        Hash Ke 1 : $2a$11$r9Uj5UuqePNLoYRmj1yUguDie6cZ0Co/3YudQFVvyPqQE7VwIkzJ6
//        Hash Ke 2 : $2a$11$RKYOQ2OgYMu51KbmhUwZZuDUMwgquAFg1U4n6wbwONjnroKCh5YhC
        System.out.println(verifyHash(strUserName,"$2a$11$r9Uj5UuqePNLoYRmj1yUguDie6cZ0Co/3YudQFVvyPqQE7VwIkzJ6"));
        System.out.println(verifyHash(strUserName,"$2a$11$RKYOQ2OgYMu51KbmhUwZZuDUMwgquAFg1U4n6wbwONjnroKCh5YhC"));

//124|-|@$1/-\

//        String strPwd = "Bima321"+strUserName;
//        String strAfterEncrypt1 = hash("Paul123");
//        System.out.println(verifyHash("Bima321Agam123","$2a$11$n9XD7.WaRvzezUfMhA1DxeD5B55kCGkHLSMYBh4l8iTLUJPxM.0si"));
//        String strAfterEncrypt2 = hash(strPwd);
//        System.out.println("strAfterEncrypt1 => "+strAfterEncrypt1);
//        System.out.println("strAfterEncrypt2 => "+strAfterEncrypt2);
//        System.out.println(verifyHash("Paul123Agam123","$2a$11$mq2Q0OKSO5PnaH6uKdPT0uRxgaq/Pjn7HTTOOGRgjSx.19xjdad.y"));
    }
}