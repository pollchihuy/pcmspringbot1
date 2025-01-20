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
//        String strUserName = "paul123Paul@123";
        String strUserName = "Paul@123";
        System.out.println(hash(strUserName));
//        strUserName = "Paul@123456789";
        System.out.println(hash(strUserName).length());
        System.out.println(verifyHash("paul123Paul@123","$2a$11$udu1/4a6//OCjiDxNrrwY.HJb4N.mC/zTCKbmlYbHkgB53q5Y/uMi"));
//        for (int i = 0; i < 2; i++) {
//            System.out.println("Hash Ke "+(i+1)+" : " +hash(strUserName));
//        }
//        Hash Ke 1 : $2a$11$r9Uj5UuqePNLoYRmj1yUguDie6cZ0Co/3YudQFVvyPqQE7VwIkzJ6
//        Hash Ke 2 : $2a$11$RKYOQ2OgYMu51KbmhUwZZuDUMwgquAFg1U4n6wbwONjnroKCh5YhC
        System.out.println(verifyHash(strUserName,"$2a$11$r9Uj5UuqePNLoYRmj1yUguDie6cZ0Co/3YudQFVvyPqQE7VwIkzJ6"));
        System.out.println(verifyHash(strUserName,"$2a$11$RKYOQ2OgYMu51KbmhUwZZuDUMwgquAFg1U4n6wbwONjnroKCh5YhC"));
    }
}