package com.example.pcmspringbot1.dto.report;

import com.example.pcmspringbot1.dto.validasi.ValLoginDTO;

import java.util.List;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Thu 18:42
@Last Modified Thu 18:42
Version 1.0
*/
public class ReportDTO {


    private List<ValLoginDTO> list;
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<ValLoginDTO> getList() {
        return list;
    }

    public void setList(List<ValLoginDTO> list) {
        this.list = list;
    }
}
