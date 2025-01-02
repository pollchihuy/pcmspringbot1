package com.example.pcmspringbot1.controller;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Thu 18:44
@Last Modified Thu 18:44
Version 1.0
*/

import com.example.pcmspringbot1.util.FileUtility;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("hello")
public class ContohUpload {

    @PostMapping("/kirim-file")
    public String submitFile(
            @RequestParam(value = "kiriman") MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String uploadDir = "user-files/";
        FileUtility.saveFile(uploadDir, fileName, file);
        return "Berhasil mengunggah file " + fileName;
    }
}