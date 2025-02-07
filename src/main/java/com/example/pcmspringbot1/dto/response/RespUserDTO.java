package com.example.pcmspringbot1.dto.response;


import com.example.pcmspringbot1.model.Akses;
import jakarta.persistence.*;

import java.time.LocalDate;

public class RespUserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String alamat;
    private String noHp;
    private String nama;
    private LocalDate tanggalLahir;
    private RespAksesDTO akses;

    public LocalDate getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public RespAksesDTO getAkses() {
        return akses;
    }

    public void setAkses(RespAksesDTO akses) {
        this.akses = akses;
    }
}
