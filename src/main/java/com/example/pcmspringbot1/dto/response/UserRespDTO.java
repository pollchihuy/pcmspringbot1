package com.example.pcmspringbot1.dto.response;

import com.example.pcmspringbot1.model.Akses;

public class UserRespDTO {

    private Long id;
    private String username;
    private String email;
    private String alamat;
    private String noHp;
    private AksesDTO akses;

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

    public AksesDTO getAkses() {
        return akses;
    }

    public void setAkses(AksesDTO akses) {
        this.akses = akses;
    }
}
