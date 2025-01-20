package com.example.pcmspringbot1.controller;

import com.example.pcmspringbot1.dto.validasi.ValRegisDTO;
import com.example.pcmspringbot1.dto.validasi.ValLoginDTO;
import com.example.pcmspringbot1.dto.validasi.ValVerifyRegisDTO;
import com.example.pcmspringbot1.service.AppUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    /**
     * LOGIN
     * REGISTRASI
     * FORGOT PASSWORD
     */

    @Autowired
    AppUserDetailService appUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody ValLoginDTO valLoginDTO,
                                        HttpServletRequest request) {
        return appUserDetailService.login(appUserDetailService.convertToUser(valLoginDTO),request);
    }

    @PostMapping("/regis")
    public ResponseEntity<Object> register(@Valid @RequestBody ValRegisDTO regisDTO, HttpServletRequest request){
        return appUserDetailService.regis(appUserDetailService.convertToUser(regisDTO),request);
    }

    @PostMapping("/verify-regis")
    public ResponseEntity<Object> verifyRegister(@Valid @RequestBody ValVerifyRegisDTO valVerifyRegisDTO, HttpServletRequest request){
        return appUserDetailService.verifyRegis(appUserDetailService.convertToUser(valVerifyRegisDTO),request);
    }


}
