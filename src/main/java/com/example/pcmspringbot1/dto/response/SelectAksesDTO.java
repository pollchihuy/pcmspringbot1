package com.example.pcmspringbot1.dto.response;

import java.util.List;

public class SelectAksesDTO {

    private Long id;
    private String nama;
    private List<SelectMenuDTO> ltMenu;

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

    public List<SelectMenuDTO> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(List<SelectMenuDTO> ltMenu) {
        this.ltMenu = ltMenu;
    }
}
