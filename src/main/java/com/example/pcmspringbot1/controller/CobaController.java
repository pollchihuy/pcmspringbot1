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
import com.example.pcmspringbot1.config.OtherConfig;
import com.example.pcmspringbot1.dto.response.UserRespDTO;
import com.example.pcmspringbot1.mapper.UserMapper;
import com.example.pcmspringbot1.model.GroupMenu;
import com.example.pcmspringbot1.model.User;
import com.example.pcmspringbot1.service.GroupMenuService;
import com.example.pcmspringbot1.util.GlobalFunction;
import com.example.pcmspringbot1.util.LoggingFile;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("auth")
public class CobaController {

    @Autowired
    private GroupMenuService groupMenuService;

    @Value("${cumi.goreng}")
    String strData ;

    @Autowired
    GlobalFunction globalFunction;

    Random rand = new Random();

    @Autowired
    UserMapper userMapper;


    ModelMapper modelMapper = new ModelMapper();

    @GetMapping("hello1")
    public String htmlRender(){

//        System.out.println("Cumi.Goreng Value : "+strData);
//        globalFunction.ok = "INI DATA";
        GlobalFunction.print("Hahaha");
        return CobaConfig.getStrCoba();
    }

    @PostMapping("/req-dto")
    public UserRespDTO callUserDTO(@RequestBody User user){
        return userMapper.userToUserRespDTO(user);
    }

    @PostMapping("/req-dto-auto")
    public UserRespDTO callUserDTOAuto(@RequestBody User user){
        return modelMapper.map(user, UserRespDTO.class);
    }

    @PostMapping("/req-dto-auto-list")
    public List<UserRespDTO> callUserDTOAutoList(@RequestBody List<User> user){
        return modelMapper.map(user, new TypeToken<List<UserRespDTO>>() {}.getType());
    }

    @GetMapping("/test-log")
    public String cobaLog(){
        String exceptionz = "";
        try{
            int intX = 1/0;
        }catch (Exception e){
            exceptionz = "Errornya adalah "+e.getMessage();
            LoggingFile.logException("CobaController","cobaLog",e, OtherConfig.getEnableLogFile());
        }

        return exceptionz;
    }

    @PostMapping("/save-group-menu")
    public ResponseEntity<Object> save(@RequestBody GroupMenu groupMenu, HttpServletRequest request){
        return groupMenuService.save(null,request);
    }
}