package com.example.pcmspringbot1.controller;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:31
@Last Modified Fri 20:31
Version 1.0
*/

import com.example.pcmspringbot1.config.CobaConfig;
import com.example.pcmspringbot1.util.GlobalFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("auth")
public class CobaController {


    @Value("${cumi.goreng}")
    String strData ;

    @Autowired
    GlobalFunction globalFunction;

    @Autowired
    Random rand;


    @GetMapping("hello1")
    public String htmlRender(){

//        System.out.println("Cumi.Goreng Value : "+strData);
//        globalFunction.ok = "INI DATA";
        GlobalFunction.print("Hahaha");
        return CobaConfig.getStrCoba();
    }
}
