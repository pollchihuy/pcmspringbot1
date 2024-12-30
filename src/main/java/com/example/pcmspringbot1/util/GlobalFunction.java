package com.example.pcmspringbot1.util;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:53
@Last Modified Fri 20:53
Version 1.0
*/

import com.example.pcmspringbot1.config.CobaConfig;
import org.springframework.stereotype.Component;

@Component
public class GlobalFunction {

    public String ok;

    public  static void print(Object obj){
        if(CobaConfig.getStrCoba().equals("y")){
            System.out.println(obj);
        }
    }
}