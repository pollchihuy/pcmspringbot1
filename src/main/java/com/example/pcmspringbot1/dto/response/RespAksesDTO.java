package com.example.pcmspringbot1.dto.response;

import com.example.pcmspringbot1.model.Menu;

import java.util.List;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Wed 19:38
@Last Modified Wed 19:38
Version 1.0
*/
public class RespAksesDTO {

    private String nama;

    private List<Menu> ltMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<Menu> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(List<Menu> ltMenu) {
        this.ltMenu = ltMenu;
    }
}
