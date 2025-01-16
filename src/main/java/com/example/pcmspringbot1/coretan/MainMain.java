package com.example.pcmspringbot1.coretan;

import com.example.pcmspringbot1.security.BcryptImpl;
import com.example.pcmspringbot1.security.Crypto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Thu 19:57
@Last Modified Thu 19:57
Version 1.0
*/
public class MainMain {

    public static void main(String[] args) {
//        String strX = "";
//
//        if(strX.startsWith("62") || strX.startsWith("0") || strX.startsWith("+62")){
//            if(strX.length()>=8 && strX.length()<=10){
//                if(strX.charAt(1)==8 || strX.charAt(2)==8){
//
//                }
//            }
//        }
//
//        if(strX.length()==16){
//            for (int i = 0; i < strX.length(); i++) {
//                if(!(strX.charAt(i)>=47 && strX.charAt(i)<=58)){
//                    break;
//                }
//            }
//        }
//        String strZ = "Pauk";
//        System.out.println(strZ.contains("ul"));
        // kode.payload.timemilis/session

//        System.out.println(Crypto.performEncrypt("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"));
//        ba0ecba5c97bcf63799c0e0ba0e8c9adedbd4f9357404d3e0806c961bf3ce8e6784fdbf889bc228cdf032f067644605257c0dba009f081e29b8498bb2509a24b08eb6ae2256113d7e351688d68eb812df08802d3ecdd60a61f59ee3a52f307412ed7e1c033b8013bca9267dae66e10495e7843b5ab79a3985ed51d2eaac643a0b5a6460f94a9db51c70239335bddf9bab899a1d15796d50d03b79891bd160abc
//        System.out.println(Crypto.performDecrypt("ba0ecba5c97bcf63799c0e0ba0e8c9adedbd4f9357404d3e0806c961bf3ce8e6784fdbf889bc228cdf032f067644605257c0dba009f081e29b8498bb2509a24b08eb6ae2256113d7e351688d68eb812df08802d3ecdd60a61f59ee3a52f307412ed7e1c033b8013bca9267dae66e10495e7843b5ab79a3985ed51d2eaac643a0b5a6460f94a9db51c70239335bddf9bab899a1d15796d50d03b79891bd160abc"));
//
//        for (int i = 0; i < 2; i++) {
//            System.out.println(Crypto.performEncrypt("Paul@123"));
//        }
//        System.out.println("Paul@123".equals(Crypto.performDecrypt("c02ba3d5895193b023388229063bbd91")));
        //$2a$11$kw/0o4vobCcpK96e/PvXI.wcDcOc.lJYJrUeT2fE5bVU9xNewCKS.
//        $2a$11$iE9PbKkW8a4WmcXgrcoxkOIZIDh9SAdCZyDHHkD7IZibnqFvC/N6K
//        System.out.println(BcryptImpl.hash("Paul@123"));
//        System.out.println(BcryptImpl.verifyHash("Paul@123","$2a$11$iE9PbKkW8a4WmcXgrcoxkOIZIDh9SAdCZyDHHkD7IZibnqFvC/N6K"));
//        System.out.println(UUID.randomUUID());
//        bf468c1c-cbfb-4f61-91a2-d97e1d420445
//        4ac72658-d4a0-4f5b-9ee4-1926593d2c1b
//        int intX = 1/0;//SQLException queue
//        3
//API 1
//API 2
//API 3
//API 4
//API 5        4 GB 2 core

//        ArrayList
//        LinkedList
//        List

//        ArrayList<String> list = new ArrayList<>();
//        List<String> l = new ArrayList<>();
//        System.out.println(System.currentTimeMillis());

        System.out.println("APP1.INFO."+System.currentTimeMillis());
        System.out.println(Crypto.performEncrypt("APP1.INFO.1737034153823"));
        String [] strToken = Crypto.performDecrypt("98c07f9fea7345d1135ee779a3769d5c7f4087095ab06e5f7a033732b4a1e04e").split("\\.");
        if(strToken.length != 3){
            return;
        }
        if(!strToken[0].equals("APP1")){

        }
        if(!strToken[1].equals("INFO")){

        }
        System.out.println(((System.currentTimeMillis()-Long.parseLong(strToken[2]))/1000)-120);
        if((((System.currentTimeMillis()-Long.parseLong(strToken[2]))/1000)-300)>0){

        }
    }
}
