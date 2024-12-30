package com.example.pcmspringbot1.config;

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
@PropertySource("classpath:")
public class OtherConfig {


    @Bean
    public Random getRandom(){
        return new Random();
    }
}
