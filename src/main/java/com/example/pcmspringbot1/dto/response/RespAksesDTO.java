package com.example.pcmspringbot1.dto.response;

import com.example.pcmspringbot1.model.Menu;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;


public class RespAksesDTO {

    private String nama;

    @JsonBackReference
    private List<RespMenuDTO> ltMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public List<RespMenuDTO> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(List<RespMenuDTO> ltMenu) {
        this.ltMenu = ltMenu;
    }
}
