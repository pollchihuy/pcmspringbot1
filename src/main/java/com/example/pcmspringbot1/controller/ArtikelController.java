package com.example.pcmspringbot1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Thu 21:03
@Last Modified Thu 21:03
Version 1.0
*/
@RestController
@RequestMapping("artikel")
public class ArtikelController {

    @GetMapping("/generate")
    public void generateArtikel(){

    }

    @GetMapping("/baca")
    public void bacaArtikel(){

    }
}
