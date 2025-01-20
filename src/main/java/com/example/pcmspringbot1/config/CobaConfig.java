package com.example.pcmspringbot1.config;

import org.springframework.beans.factory.annotation.Value;
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
