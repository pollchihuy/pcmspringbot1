package com.example.pcmspringbot1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/*
IntelliJ IDEA 2024.1.4 (Ultimate Edition)
Build #IU-241.18034.62, built on June 21, 2024
@Author pollc a.k.a. Paul Christian
Java Developer
Created on Mon 20:18
@Last Modified Mon 20:18
Version 1.0
*/
public class UserDTO {

    private String id;
    private String nama;
    @JsonProperty("jenis-kelamin")
    private String jenisKelamin;
    private Integer umur;
    @JsonProperty("alamat")
    private AlamatDTO alamatDTO;

    private List<String> list;

    @JsonProperty("contoh")
    private List<ContohDTO> ltContohDTO;

    public List<ContohDTO> getLtContohDTO() {
        return ltContohDTO;
    }

    public void setLtContohDTO(List<ContohDTO> ltContohDTO) {
        this.ltContohDTO = ltContohDTO;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Integer getUmur() {
        return umur;
    }

    public void setUmur(Integer umur) {
        this.umur = umur;
    }

    public AlamatDTO getAlamatDTO() {
        return alamatDTO;
    }

    public void setAlamatDTO(AlamatDTO alamatDTO) {
        this.alamatDTO = alamatDTO;
    }
}
