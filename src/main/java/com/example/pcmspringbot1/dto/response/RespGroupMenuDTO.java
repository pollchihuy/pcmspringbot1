package com.example.pcmspringbot1.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RespGroupMenuDTO {
    private Long id;

    @JsonProperty("nama")
    private String namaGroupMenu;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaGroupMenu() {
        return namaGroupMenu;
    }

    public void setNamaGroupMenu(String namaGroupMenu) {
        this.namaGroupMenu = namaGroupMenu;
    }
}
