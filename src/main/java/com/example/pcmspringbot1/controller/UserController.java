package com.example.pcmspringbot1.controller;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:25
@Last Modified Fri 20:25
Version 1.0
*/
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @GetMapping("hello")
    public Object getData(){
        Map<String,Object> map = new HashMap<>();
        map.put("message","Hello World");
        map.put("timestamp",new Date());

        return map;
    }
}
