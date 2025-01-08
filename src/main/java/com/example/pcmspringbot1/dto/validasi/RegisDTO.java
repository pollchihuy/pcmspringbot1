package com.example.pcmspringbot1.dto.validasi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Fri 20:16
@Last Modified Fri 20:16
Version 1.0
*/
public class RegisDTO {

    @NotNull(message = "gak boleh null")
    @NotBlank(message = "gak boleh blank")
    @NotEmpty(message = "gak boleh empty")
    @Pattern(regexp = "^[0-9]{5,10}$",message = "Angka Saja Min 5 Max 10 karakter")
    private String username;
    
    private String password;
    private String email;
    private String alamat;
    private String noHp;
}
