package com.example.pcmspringbot1.dto.validasi;

import com.example.pcmspringbot1.dto.response.RespMenuDTO;
import com.example.pcmspringbot1.model.Menu;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Wed 21:14
@Last Modified Wed 21:14
Version 1.0
*/
public class ValAksesDTO {

    @NotNull(message = "Field Nama Tidak Boleh NULL")
    @NotEmpty(message = "Field Nama Tidak Boleh Kosong")
    @NotBlank(message = "Field Nama Tidak Boleh Blank")
    @Pattern(regexp = "^[a-zA-Z\\s]{2,40}$",message = "AflaNumerik Dengan Spasi Min 2 Max 40")
    private String nama;

    @NotNull(message = "Menu Wajib DIISI")
    private List<RespMenuDTO> ltMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public @NotNull(message = "Menu Wajib DIISI") List<RespMenuDTO> getLtMenu() {
        return ltMenu;
    }

    public void setLtMenu(@NotNull(message = "Menu Wajib DIISI") List<RespMenuDTO> ltMenu) {
        this.ltMenu = ltMenu;
    }
}
