package com.example.pcmspringbot1.config;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Random;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 21:05
@Last Modified Fri 21:05
Version 1.0
*/
@Configuration
@PropertySource("classpath:other.properties")
public class OtherConfig {

    private static String enableLogFile;

    public static String getEnableLogFile() {
        return enableLogFile;
    }

    @Value("${enable.logfile}")
    private void setEnableLogFile(String enableLogFile) {
        OtherConfig.enableLogFile = enableLogFile;
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
