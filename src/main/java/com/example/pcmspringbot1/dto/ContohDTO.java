package com.example.pcmspringbot1.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Mon 20:33
@Last Modified Mon 20:33
Version 1.0
*/
public class ContohDTO {
    
    private String data1;
    private String data2;

    @Pattern(regexp = "^(62|\\+62|0)8[0-9]{8,10}$")
//    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[$_\\-#])[a-zA-Z]{1,}$",message = "Wajib 16 digit numerik")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[_#\\-$])[\\w].{8,15}$",message = "Wajib 16 digit numerik")
    private String noHp;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @NotNull(message = "")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date datez;

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }
}
