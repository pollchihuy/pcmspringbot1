package com.example.pcmspringbot1.config;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 21:30
@Last Modified Fri 21:30
Version 1.0
*/

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:coba.properties")
public class CobaConfig {


    public static String strCoba;

    @Value("${coba.coba}")
    private void setStrCoba(String strCoba) {
        CobaConfig.strCoba = strCoba;
    }

    public static String getStrCoba() {
        return strCoba;
    }

    
}
