package com.example.pcmspringbot1.dto.validasi;


import com.example.pcmspringbot1.dto.response.RespGroupMenuDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ValMenuDTO {

    @NotNull(message = "Field Nama Tidak Boleh NULL")
    @NotEmpty(message = "Field Nama Tidak Boleh Kosong")
    @NotBlank(message = "Field Nama Tidak Boleh Blank")
    @Pattern(regexp = "^[\\w\\s]{6,40}$",message = "AflaNumerik Dengan Spasi Min 6 Max 40")
    private String nama;

    @NotNull(message = "Field Path Tidak Boleh NULL")
    @NotEmpty(message = "Field Path Tidak Boleh Kosong")
    @NotBlank(message = "Field Path Tidak Boleh Blank")
    @Pattern(regexp = "^[\\w\\s/]{6,40}$",message = "AflaNumerik Dengan Spasi Min 6 Max 40")
    private String path;

    private RespGroupMenuDTO groupMenu;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RespGroupMenuDTO getGroupMenu() {
        return groupMenu;
    }

    public void setGroupMenu(RespGroupMenuDTO groupMenu) {
        this.groupMenu = groupMenu;
    }
}