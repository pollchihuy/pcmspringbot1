package com.example.pcmspringbot1.dto.report;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on 18/02/2025 17:34
@Last Modified 18/02/2025 17:34
Version 1.0
*/
public class ReportMenuDTO {

    private Long id;
    private String nama;
    private String group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
