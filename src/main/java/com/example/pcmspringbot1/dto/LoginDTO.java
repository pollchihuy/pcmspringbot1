package com.example.pcmspringbot1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class LoginDTO {

    @NotNull(message = "gak boleh null")
    @NotBlank(message = "gak boleh blank")
    @NotEmpty(message = "gak boleh empty")
    @Pattern(regexp = "^[0-9]{5,10}$",message = "Angka Saja Min 5 Max 10 karakter")
    private String username;
    private String password;

    @JsonProperty("nama-depan")
    private String namaDepan;

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
